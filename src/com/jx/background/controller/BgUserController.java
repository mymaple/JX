package com.jx.background.controller;

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
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.entity.BgRole;
import com.jx.background.entity.BgUser;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.Jurisdiction;
import com.jx.common.util.ObjectExcelView;
import com.jx.background.service.BgMenuService;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;

/** 
 * 类名称：BgUserController
 * 创建人：maple
 * 创建时间：2016-12-15
 */
@Controller
@RequestMapping(value="/background/user")
public class BgUserController extends BaseController {
	
	String menuUrl = "background/user/list.do"; // 菜单地址(权限用)
	@Resource(name = "bgUserService")
	private BgUserService bgUserService;
	@Resource(name = "bgRoleService")
	private BgRoleService bgRoleService;
	@Resource(name = "bgMenuService")
	private BgMenuService bgMenuService;
	
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(BgPage bgPage){
		logBefore(logger, "列表bgUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		//关键词检索条件
		String keywords = pd.getString("keywords");				
		if(null != keywords && !"".equals(keywords)){
			pd.put("keywords", keywords.trim());
		}

		String lastLoginTimeStart = pd.getString("lastLoginTimeStart");
		if (lastLoginTimeStart != null && !"".equals(lastLoginTimeStart)) {
			lastLoginTimeStart = lastLoginTimeStart + " 00:00:00";
			pd.put("lastLoginStart", lastLoginTimeStart);
		}
		
		String lastLoginTimeEnd = pd.getString("lastLoginTimeEnd");
		if (lastLoginTimeEnd != null && !"".equals(lastLoginTimeEnd)) {
			lastLoginTimeEnd = lastLoginTimeEnd + " 00:00:00";
			pd.put("lastLoginTimeEnd", lastLoginTimeEnd);
		}
		
		try{
			bgPage.setPd(pd);
			List<PageData>	userList = bgUserService.listAllPd(bgPage);	//列出bgUser列表
			List<BgRole> roleList = bgRoleService.listAllSecondRoles(); // 列出所有二级角色
			
			mv.addObject("userList", userList);
			mv.addObject("roleList", roleList);
			
			mv.addObject("pd", pd);
//			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
			
			mv.setViewName("background/user/bgUserList");
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
		logBefore(logger, "去新增bgUser页面");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<BgRole> roleList = new ArrayList<BgRole>();
			roleList = bgRoleService.listAllSecondRoles(); // 列出所有二级角色
			
			mv.addObject("roleList", roleList);
			mv.addObject("msg", "add");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/user/bgUserEdit");
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
		logBefore(logger, "新增bgUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Date nowTime = new Date();

		pd.put("rights", ""); // 权限
		pd.put("lastLoginTime", nowTime); // 最后登录时间
		pd.put("lastLoginTimeIp", ""); // loginIp
		pd.put("status", "0"); // 状态
		pd.put("skin", "default"); // 默认皮肤
		pd.put("modifyTime", nowTime); // 修改时间时间
		
		pd.put("password", new SimpleHash("SHA-1", pd.getString("userName"), pd.getString("password")).toString());
		
		if(null == bgUserService.findByUserName(pd)){	//判断用户名是否存在
			bgUserService.addByPd(pd); 					//执行保存
			mv.addObject("msg","success");
		}else{
			mv.addObject("msg","failed");
		}
		
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/toEdit")
	public ModelAndView toEdit(){
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		logBefore(logger, "去修改bgUser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = bgUserService.findPdByPd(pd);	//根据ID读取
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
			
			mv.setViewName("background/user/bgUserEdit");
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
		logBefore(logger, "修改bgUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		bgUserService.editByPd(pd);
		mv.addObject("msg","success");
		mv.setViewName("background/bgSaveResult");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除bgUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			bgUserService.deleteByPd(pd);
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
		logBefore(logger, "批量删除bgUser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String userIds = pd.getString("userIds");
			if(null != userIds && !"".equals(userIds)){
				bgUserService.batchDeleteByIds(userIds);
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
		logBefore(logger, "导出bgUser到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("系统用户表 主键id");   //1
			titles.add("用户名");	//0+2
			titles.add("密码");	//1+2
			titles.add("名字");	//2+2
			titles.add("拥有的权限");	//3+2
			titles.add("角色id");	//4+2
			titles.add("上次登录时间");	//5+2
			titles.add("上次登录IP");	//6+2
			titles.add("状态");	//7+2
			titles.add("备注");	//8+2
			titles.add("皮肤");	//9+2
			titles.add("电子邮箱");	//10+2
			titles.add("用户编号");	//11+2
			titles.add("电话号码");	//12+2
			dataMap.put("titles", titles);
			List<BgUser> varOList = bgUserService.listAllByPd(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				
				vpd.put("var1",varOList.get(i).getUserId());
				vpd.put("var2", varOList.get(i).getUserName());	//2
				vpd.put("var3", varOList.get(i).getPassword());	//3
				vpd.put("var4", varOList.get(i).getName());	//4
				vpd.put("var5", varOList.get(i).getRights());	//5
				vpd.put("var6", varOList.get(i).getRoleId());	//6
				vpd.put("var7", varOList.get(i).getLastLoginTime());	//7
				vpd.put("var8", varOList.get(i).getLastLoginIp());	//8
				vpd.put("var9", varOList.get(i).getStatus());	//9
				vpd.put("var10", varOList.get(i).getRemarks());	//10
				vpd.put("var11", varOList.get(i).getSkin());	//11
				vpd.put("var12", varOList.get(i).getEmail());	//12
				vpd.put("var13", varOList.get(i).getUserNumber());	//13
				vpd.put("var14", varOList.get(i).getPhone());	//14
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
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * 获取当前用户信息
	 */
	@RequestMapping(value = "/findUser")
	@ResponseBody
	public Object findUser() {
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			BgUser bgUser = new BgUser();
			bgUser = (BgUser) session.getAttribute(Const.SESSION_BG_USER_OBJ);

			if (null == bgUser) {
				String userName = session.getAttribute(Const.SESSION_BG_USERNAME_STR).toString(); // 获取当前登录者loginname
				pd.put("userName", userName);
				bgUser = bgUserService.findByUserName(pd);
				session.setAttribute(Const.SESSION_BG_USER_OBJ, bgUser);
			}
			map.put("bgUser", bgUser);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 判断用户名是否存在
	 */
	@RequestMapping(value = "/hasUser")
	@ResponseBody
	public Object hasUser() {
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (bgUserService.findByUserName(pd) != null) {
				errInfo = "error";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo); // 返回结果
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 判断邮箱是否存在
	 */
	@RequestMapping(value = "/hasEmail")
	@ResponseBody
	public Object hasEmail() {
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try {
			pd = this.getPageData();

			if (bgUserService.findByEmail(pd) != null) {
				errInfo = "error";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo); // 返回结果
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 判断编码是否存在
	 */
	@RequestMapping(value = "/hasUserNumber")
	@ResponseBody
	public Object hasUserNumber() {
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (bgUserService.findByUserNumber(pd) != null) {
				errInfo = "error";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo); // 返回结果
		return AppUtil.returnObject(new PageData(), map);
	}
	
	/**
	 * 判断手机号是否存在
	 */
	@RequestMapping(value = "/hasPhone")
	@ResponseBody
	public Object hasPhone() {
		Map<String, String> map = new HashMap<String, String>();
		String errInfo = "success";
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (bgUserService.findByPhone(pd) != null) {
				errInfo = "error";
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo); // 返回结果
		return AppUtil.returnObject(new PageData(), map);
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
