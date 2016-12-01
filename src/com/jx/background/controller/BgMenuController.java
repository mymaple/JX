package com.jx.background.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.entity.BgMenu;
import com.jx.background.service.BgMenuService;
import com.jx.system.config.BaseController;
import com.jx.system.config.PageData;
import com.jx.system.util.AppUtil;

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
	@RequestMapping(value = "/menuList")
	public ModelAndView menuList() throws Exception {
		ModelAndView mv = this.getModelAndView();
		try {
			List<BgMenu> menuList = bgMenuService.listAllTopMenu();
			mv.addObject("menuList", menuList);
			mv.setViewName("background/menu/bgMenuList");
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
	@RequestMapping(value = "/toAddMenu")
	public ModelAndView toAddMenu() throws Exception {
		ModelAndView mv = this.getModelAndView();
		try {
			List<BgMenu> menuList = bgMenuService.listAllTopMenu();
			mv.addObject("menuList", menuList);
			mv.setViewName("background/menu/bgMenuAdd");
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
	@RequestMapping(value = "/addMenu")
	public ModelAndView addMenu(BgMenu menu) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			menu.setMenuId(String.valueOf(bgMenuService.getMaxMenuId(pd) + 1));

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

		mv.setViewName("background/bgSaveResult");
		return mv;
	}

	/**
	 * 请求编辑菜单页面
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/toEditMenu")
	public ModelAndView toEditMenu(String menuId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			pd.put("menuId", menuId);
			pd = bgMenuService.findByMenuId(pd);
			List<BgMenu> menuList = bgMenuService.listAllTopMenu();
			mv.addObject("menuList", menuList);
			mv.addObject("pd", pd);
			mv.setViewName("background/menu/bgMenuEdit");
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
	@RequestMapping(value = "/toChangeMenuIcon")
	public ModelAndView toChangeMenuIcon(String menuId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			pd.put("menuId", menuId);
			mv.addObject("pd", pd);
			mv.setViewName("background/menu/bgMenuIconChange");
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
	@RequestMapping(value = "/changeMenuIcon")
	public ModelAndView changeMenuIcon() throws Exception {
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
		mv.setViewName("background/bgSaveResult");
		return mv;
	}

	/**
	 * 保存编辑
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/editMenu")
	public ModelAndView editMenu() throws Exception {
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
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 获取当前菜单的所有子菜单
	 * @param menuId 
	 * @return
	 */
	@RequestMapping(value = "/submenuList")
	@ResponseBody
	public Object submenuList() {
		Map<String, List<BgMenu>> map = new HashMap<String, List<BgMenu>>();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			List<BgMenu> subMenuList = bgMenuService.listSubMenuByParentId(pd.getString("menuId"));
			map.put("subMenuList", subMenuList); // 返回结果
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(new PageData(), map);
		
	}

	/**
	 * 获取当前菜单的所有子菜单
	 * @param menuId
	 * @param response
	 */
/*	@RequestMapping(value = "/submenuList")
	public void submenuList(@RequestParam String menuId, HttpServletResponse response) throws Exception {
		try {
			List<BgMenu> subMenuList = bgMenuService.listSubMenuByParentId(menuId);
			JSONArray arr = JSONArray.fromObject(subMenuList);
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
	}*/

	/**
	 * 删除菜单
	 * @param menuId
	 * @param out
	 */
	@RequestMapping(value = "/deleteMenu")
	public void deleteMenu(@RequestParam String menuId, PrintWriter out) throws Exception {

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
