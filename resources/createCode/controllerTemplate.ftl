package com.jx.${controlModuleNL}.controller;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.${controlModuleNL}.config.${controlModuleEU}Page;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.Jurisdiction;
import com.jx.common.util.ObjectExcelView;
import com.jx.${objectModuleNL}.service.${objectModuleEU}${objectNameU}Service;

/** 
 * 类名称：${controlModuleEU}${objectNameU}Controller
 * 创建人：maple
 * 创建时间：${nowDate?string("yyyy-MM-dd")}
 */
@Controller
@RequestMapping(value="/${controlModuleNL}/${objectNameL}")
public class ${controlModuleEU}${objectNameU}Controller extends BaseController {
	
	@Resource(name="${objectModuleEL}${objectNameU}Service")
	private ${objectModuleEU}${objectNameU}Service ${objectModuleEL}${objectNameU}Service;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(${controlModuleEU}Page ${controlModuleEL}Page){
		logBefore(logger, "列表${objectModuleEL}${objectNameU}");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			${controlModuleEL}Page.setPd(pd);
			List<PageData>	varList = ${objectModuleEL}${objectNameU}Service.listAllPd(${controlModuleEL}Page);	//列出${objectModuleEL}${objectNameU}列表
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			
			mv.setViewName("${controlModuleNL}/${objectNameL}/${controlModuleEL}${objectNameU}List");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/toAdd")
	public ModelAndView toAdd(){
		logBefore(logger, "去新增${objectModuleEL}${objectNameU}页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.addObject("msg", "add");
			mv.addObject("pd", pd);
			
			mv.setViewName("${controlModuleNL}/${objectNameL}/${controlModuleEL}${objectNameU}Edit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/add")
	public ModelAndView add() throws Exception{
		logBefore(logger, "新增${objectModuleEL}${objectNameU}");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		${objectModuleEL}${objectNameU}Service.addByPd(pd);
		
		mv.addObject("msg","success");
		
		mv.setViewName("${controlModuleNL}/${controlModuleEL}SaveResult");
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(){
		logBefore(logger, "去修改${objectModuleEL}${objectNameU}页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = ${objectModuleEL}${objectNameU}Service.findPdByPd(pd);	//根据ID读取
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			
			mv.setViewName("${controlModuleNL}/${objectNameL}/${controlModuleEL}${objectNameU}Edit");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改${objectModuleEL}${objectNameU}");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		${objectModuleEL}${objectNameU}Service.editByPd(pd);
		mv.addObject("msg","success");
		mv.setViewName("${controlModuleNL}/${controlModuleEL}SaveResult");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除${objectModuleEL}${objectNameU}");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			${objectModuleEL}${objectNameU}Service.deleteByPd(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
	}
	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/batchDelete")
	@ResponseBody
	public Object batchDelete() {
		logBefore(logger, "批量删除${objectModuleEL}${objectNameU}");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String ${objectNameL}Ids = pd.getString("${objectNameL}Ids");
			if(null != ${objectNameL}Ids && !"".equals(${objectNameL}Ids)){
				${objectModuleEL}${objectNameU}Service.batchDeleteByIds(${objectNameL}Ids);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出${objectModuleEL}${objectNameU}到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("${tableName} 主键id");   //1
	<#list fieldList as var>
			titles.add("${var[2]}");	//${var_index}+2
	</#list>
			dataMap.put("titles", titles);
			List<${objectModuleEU}${objectNameU}> varOList = ${objectModuleEL}${objectNameU}Service.listAllByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
				vpd.put("var${1}",varOList.get(i).get${objectNameU}Id();
	<#list fieldList as var>
				vpd.put("var${var_index+2}", varOList.get(i).get${var[0]}());	//${var_index+2}
	</#list>
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/* ===============================权限================================== */
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
