package com.jx.background.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.service.BgMenuService;
import com.jx.common.config.BaseController;
import com.jx.common.config.PageData;
import com.jx.common.service.ComDictService;
import com.jx.common.util.AppUtil;

/**
 * 类名称：DicttionariesController 创建人：FH 创建时间：2014年9月2日
 * @version
 */
@Controller
@RequestMapping(value = "/background/dict")
public class BgDictController extends BaseController {

	@Resource(name = "bgMenuService")
	private BgMenuService bgMenuService;
	@Resource(name = "comDictService")
	private ComDictService comDictService;

	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save(PrintWriter out) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pdp = new PageData();
		pdp = this.getPageData();

		String parentId = pd.getString("parentId");
		pdp.put("dictId", parentId);

		if (null == pd.getString("dictId") || "".equals(pd.getString("dictId"))) {
			if (null != parentId && "0".equals(parentId)) {
				pd.put("jb", 1);
				pd.put("pbm", pd.getString("encode"));
			} else {
				pdp = comDictService.findPdById(parentId);
				pd.put("jb", Integer.parseInt(pdp.get("jb").toString()) + 1);
				pd.put("pbm", pdp.getString("encode") + "_" + pd.getString("encode"));
			}
//			pd.put("dictId", this.get32UUID()); // ID
			comDictService.addByPd(pd);
		} else {
			pdp = comDictService.findPdById(parentId);
			if (null != parentId && "0".equals(parentId)) {
				pd.put("pbm", pd.getString("encode"));
			} else {
				pd.put("pbm", pdp.getString("encode") + "_" + pd.getString("encode"));
			}
			comDictService.editByPd(pd);
		}

		mv.addObject("msg", "success");
		mv.setViewName("background/bgSaveResult");
		return mv;

	}

	/**
	 * 列表
	 */
	
	@RequestMapping(value = "list")
	public ModelAndView list(BgPage bgPage) throws Exception {

		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String parentId = pd.getString("parentId");
		List<PageData> szdList = new ArrayList<PageData>();

		if (null != parentId && !"".equals(parentId) && !"0".equals(parentId)) {

			// 返回按钮用
			PageData pdp = new PageData();
			pdp = this.getPageData();

			pdp.put("dictId", parentId);
			pdp = comDictService.findPdById(parentId);
			mv.addObject("pdp", pdp);

			// 头部导航
			szdList = new ArrayList<PageData>();
			this.getZDname(parentId); // 逆序
			Collections.reverse(szdList);

		}

		String name = pd.getString("name");
		if (null != name && !"".equals(name)) {
			name = name.trim();
			pd.put("name", name);
		}
		bgPage.setShowCount(5); // 设置每页显示条数
		bgPage.setPd(pd);
		List<PageData> varList = comDictService.dictlistPage(bgPage);

		mv.addObject("varList", varList);
		mv.addObject("varsList", szdList);
		mv.addObject("pd", pd);
		mv.setViewName("background/dict/bgDictList");

		return mv;
	}

	// 递归
	public void getZDname(String parentId) {
		logBefore(logger, "递归");
		List<PageData> szdList = new ArrayList<PageData>();
		try {
			PageData pdps = new PageData();
			pdps.put("dictId", parentId);
			pdps = comDictService.findPdById(parentId);
			if (pdps != null) {
				szdList.add(pdps);
				String parentIds = pdps.getString("parentId");
				this.getZDname(parentIds);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/toAdd")
	public ModelAndView toAdd() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			mv.addObject("pd", pd);
			mv.setViewName("background/dict/bgDictEdit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return mv;
	}

	/**
	 * 去编辑页面
	 */
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(String roleId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = comDictService.findById(pd);
		if (Integer.parseInt(comDictService.findCount(pd).get("ZS").toString()) != 0) {
			mv.addObject("msg", "no");
		} else {
			mv.addObject("msg", "ok");
		}
		mv.setViewName("background/dict/bgDictEdit");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 判断编码是否存在
	 */
	@RequestMapping(value = "/has")
	public void has(PrintWriter out) {
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (comDictService.findBmCount(pd) != null) {
				out.write("error");
			} else {
				out.write("success");
			}
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}

	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public Object del() {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		String errInfo = "";
		try {
			pd = this.getPageData();

			if (Integer.parseInt(comDictService.findCount(pd).get("ZS").toString()) != 0) {
				errInfo = "false";
			} else {
				comDictService.deleteById(pd);
				errInfo = "success";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);

	}

}
