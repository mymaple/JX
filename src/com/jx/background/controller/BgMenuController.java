package com.jx.background.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.entity.BgMenu;
import com.jx.background.service.BgMenuService;
import com.jx.system.config.BaseController;
import com.jx.system.config.PageData;

import net.sf.json.JSONArray;

/**
 * 类名称：MenuController 创建人：FH 创建时间：2014年7月1日
 * @version
 */
@Controller
@RequestMapping(value = "/background/menu")
public class BgMenuController extends BaseController {

	@Resource(name = "bgMenuService")
	private BgMenuService bgMenuService;

	/**
	 * 显示菜单列表
	 * @param model
	 * @return
	 */
	@RequestMapping
	public ModelAndView list() throws Exception {
		ModelAndView mv = this.getModelAndView();
		try {
			List<BgMenu> menuList = bgMenuService.listAllTopMenu();
			mv.addObject("menuList", menuList);
			mv.setViewName("system/menu/menu_list");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 请求新增菜单页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toAdd")
	public ModelAndView toAdd() throws Exception {
		ModelAndView mv = this.getModelAndView();
		try {
			List<BgMenu> menuList = bgMenuService.listAllTopMenu();
			mv.addObject("menuList", menuList);
			mv.setViewName("system/menu/menu_add");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 保存菜单信息
	 * @param menu
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(BgMenu menu) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			menu.setMenuId(String.valueOf(Integer.parseInt(bgMenuService.getMaxMenuId(pd).get("MID").toString()) + 1));

			String parentId = menu.getParentId();
			if (!"0".equals(parentId)) {
				// 处理菜单类型====
				pd.put("menuId", parentId);
				pd = bgMenuService.findByMenuId(pd);
				menu.setMenuType(pd.getString("menuType"));
				// 处理菜单类型====
			}
			bgMenuService.addMenu(menu);
			mv.addObject("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			mv.addObject("msg", "failed");
		}

		mv.setViewName("save_result");
		return mv;

	}

	/**
	 * 请求编辑菜单页面
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(String menuId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			pd.put("menuId", menuId);
			pd = bgMenuService.findByMenuId(pd);
			List<BgMenu> menuList = bgMenuService.listAllTopMenu();
			mv.addObject("menuList", menuList);
			mv.addObject("pd", pd);
			mv.setViewName("system/menu/menu_edit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 请求编辑菜单图标页面
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/toEditicon")
	public ModelAndView toEditicon(String menuId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			pd.put("menuId", menuId);
			mv.addObject("pd", pd);
			mv.setViewName("system/menu/menu_icon");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 保存菜单图标 (顶部菜单)
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/editicon")
	public ModelAndView editicon() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			pd = bgMenuService.changeMenuIcon(pd);
			mv.addObject("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			mv.addObject("msg", "failed");
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 保存编辑
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();

			String parentId = pd.getString("parentId");
			if (null == parentId || "".equals(parentId)) {
				parentId = "0";
				pd.put("parentId", parentId);
			}

			if ("0".equals(parentId)) {
				// 处理菜单类型====
				bgMenuService.changeMenuType(pd);
				// 处理菜单类型====
			}

			pd = bgMenuService.editMenu(pd);
			mv.addObject("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			mv.addObject("msg", "failed");
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 获取当前菜单的所有子菜单
	 * @param menuId
	 * @param response
	 */
	@RequestMapping(value = "/sub")
	public void getSub(@RequestParam String menuId, HttpServletResponse response) throws Exception {
		try {
			List<BgMenu> subMenu = bgMenuService.listSubMenuByParentId(menuId);
			JSONArray arr = JSONArray.fromObject(subMenu);
			PrintWriter out;

			response.setCharacterEncoding("utf-8");
			out = response.getWriter();
			String json = arr.toString();
			out.write(json);
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	/**
	 * 删除菜单
	 * @param menuId
	 * @param out
	 */
	@RequestMapping(value = "/del")
	public void delete(@RequestParam String menuId, PrintWriter out) throws Exception {

		try {
			bgMenuService.deleteMenuAndSubMenuById(menuId);
			out.write("success");
			out.flush();
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}
}
