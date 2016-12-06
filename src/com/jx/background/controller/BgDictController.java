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
import com.jx.common.entity.ComDict;
import com.jx.common.service.ComDictService;
import com.jx.common.util.AppUtil;
import com.jx.common.util.JudgeUtil;

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
	 * 列表
	 */
	List<ComDict> navDictList ;
	@RequestMapping(value = "/list")
	public ModelAndView list(BgPage bgPage) throws Exception {

		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String parentId = pd.getString("parentId");
		
		if (JudgeUtil.stringCanToZZS(parentId)) {
			// 头部导航
			navDictList = new ArrayList<ComDict>();
			this.getDictFamilyTree(Integer.parseInt(parentId)); // 逆序
			Collections.reverse(navDictList);
			
			// 返回按钮用
			mv.addObject("backId", navDictList.get(navDictList.size()-1).getParentId());
		}else{
			pd.put("parentId", "0");
		}

		String name = pd.getString("name");
		if (null != name && !"".equals(name)) {
			name = name.trim();
			pd.put("name", name);
		}
		bgPage.setShowCount(5); // 设置每页显示条数
		bgPage.setPd(pd);
		List<PageData> childrenDictList = comDictService.dictlistPage(bgPage);

		mv.addObject("childrenDictList", childrenDictList);
		mv.addObject("navDictList", navDictList);
		mv.addObject("pd", pd);

		mv.setViewName("background/dict/bgDictList");
		return mv;
	}


	/**
	 * 保存
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		String parentId = pd.getString("parentId");
		ComDict comdic = new ComDict();

		if (null == pd.getString("dictId") || "".equals(pd.getString("dictId"))) {
			if (null != parentId && "0".equals(parentId)) {
				pd.put("level", 1);
				pd.put("allEncode", pd.getString("encode"));
			} else {
				comdic = comDictService.findById(Integer.parseInt(parentId));
				pd.put("level", comdic.getLevel() + 1);
				pd.put("allEncode", comdic.getEncode() + "_" + pd.getString("encode"));
			}
//			pd.put("dictId", this.get32UUID()); // ID
			comDictService.addByPd(pd);
		} else {
			if (null != parentId && "0".equals(parentId)) {
				pd.put("allEncode", pd.getString("encode"));
			} else {
				comdic = comDictService.findById(Integer.parseInt(parentId));
				pd.put("allEncode", comdic.getEncode() + "_" + pd.getString("encode"));
			}
			comDictService.editByPd(pd);
		}
		mv.addObject("msg", "success");
		
		mv.setViewName("background/bgSaveResult");
		return mv;

	}


	// 递归
	public void getDictFamilyTree(int parentId) {
		logBefore(logger, "递归");
		try {
			ComDict comDict = new ComDict();
			comDict = comDictService.findById(parentId);
			if (comDict != null) {
				navDictList.add(comDict);
				int parentIds = comDict.getParentId();
				this.getDictFamilyTree(parentIds);
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
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		mv.setViewName("background/dict/bgDictEdit");
		return mv;
	}

	/**
	 * 去编辑页面
	 */
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd = comDictService.findPdByPd(pd);
		if (Integer.parseInt(comDictService.findCount(pd).get("count").toString()) > 0) {
			mv.addObject("msg", "no");
		} else {
			mv.addObject("msg", "ok");
		}
		mv.addObject("pd", pd);
		
		mv.setViewName("background/dict/bgDictEdit");
		return mv;
	}

	/**
	 * 判断编码是否存在
	 */
	@RequestMapping(value = "/hasEncode")
	public void hasEncode(PrintWriter out) {
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (comDictService.hasEncodeByPd(pd) != null) {
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

			if (Integer.parseInt(comDictService.findCount(pd).get("count").toString()) != 0) {
				errInfo = "false";
			} else {
				comDictService.deleteByPd(pd);
				errInfo = "success";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);

	}

}
