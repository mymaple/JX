package com.jx.background.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.entity.BgConfig;
import com.jx.background.service.AppuserService;
import com.jx.background.service.BgConfigService;
import com.jx.background.service.BgUserService;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComDict;
import com.jx.common.service.ComDictService;
import com.jx.common.util.AppUtil;
import com.jx.common.util.DelAllFile;
import com.jx.common.util.FileDownload;
import com.jx.common.util.FileZip;
import com.jx.common.util.Freemarker;
import com.jx.common.util.PathUtil;
import com.jx.common.util.StringUtil;
import com.jx.common.util.Tools;

/**
 * 类名称：HeadController 创建人：FH 创建时间：2014年8月16日
 * @version
 */
@Controller
@RequestMapping(value = "/background/config")
public class BgConfigController extends BaseController {

	@Resource(name = "bgUserService")
	private BgUserService bgUserService;
	@Resource(name = "appuserService")
	private AppuserService appuserService;
	@Resource(name = "bgConfigService")
	private BgConfigService bgConfigService;
	@Resource(name = "comDictService")
	private ComDictService comDictService;


	
	
	/**
	 * 去系统设置页面
	 */
	@RequestMapping(value = "/goEditConfig")
	public ModelAndView goEditConfig() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<BgConfig> bgConfigList = bgConfigService.listBgConfig();
		
		for(int i=0;i<bgConfigList.size();i++){
			BgConfig bgConfig = bgConfigList.get(i);
			pd.put(bgConfig.getConfigType(), bgConfig);
		}

		mv.setViewName("background/config/bgConfigEdit");
		mv.addObject("pd", pd);

		return mv;
	}
	

	/**
	 * 去编辑邮箱页面
	 */
	@RequestMapping(value = "/editEmail")
	public ModelAndView editEmail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/head/edit_email");
		mv.addObject("pd", pd);
		return mv;
	}
	
	/**
	 * 保存系统设置1
	 */
	@RequestMapping(value = "/editConfig")
	public ModelAndView editConfig() throws Exception {
		ModelAndView mv = this.getModelAndView();
		
		String[] configIds = (String[])this.getRequest().getParameterValues("configId");
		String[] configTypes = (String[])this.getRequest().getParameterValues("configType");
		String[] configNames = (String[])this.getRequest().getParameterValues("configName");
		String[] param1s = (String[])this.getRequest().getParameterValues("param1");
		String[] param2s = (String[])this.getRequest().getParameterValues("param2");
		String[] param3s = (String[])this.getRequest().getParameterValues("param3");
		String[] param4s = (String[])this.getRequest().getParameterValues("param4");
		String[] isOpens = (String[])this.getRequest().getParameterValues("isOpen");
		
		for(int i = 0; i < configIds.length; i++){
			BgConfig bgConfig = new BgConfig();
			bgConfig.setConfigId(configIds[i]);
			bgConfig.setConfigType(configTypes[i]);
			bgConfig.setConfigName(configNames[i]);
			bgConfig.setParam1(param1s[i]);
			bgConfig.setParam2(param2s[i]);
			bgConfig.setParam3(param3s[i]);
			bgConfig.setParam4(param4s[i]);
			bgConfig.setIsOpen(isOpens[i]);
			bgConfigService.editConfig(bgConfig);
		}
		mv.addObject("msg", "OK");
		mv.setViewName("background/bgSaveResult");
		return mv;
	}

	/**
	 * 去发送短信页面
	 */
	@RequestMapping(value = "/goSendSms")
	public ModelAndView goSendSms() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/head/send_sms");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 发送短信
	 */
	@RequestMapping(value = "/sendSms")
	@ResponseBody
	public Object sendSms() {
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "ok"; // 发送状态
		int count = 0; // 统计发送成功条数
		int zcount = 0; // 理论条数

		List<PageData> pdList = new ArrayList<PageData>();

		String PHONEs = pd.getString("PHONE"); // 对方邮箱
		String CONTENT = pd.getString("CONTENT"); // 内容
		String isAll = pd.getString("isAll"); // 是否发送给全体成员 yes or no
		String TYPE = pd.getString("TYPE"); // 类型 1：短信接口1 2：短信接口2
		String fmsg = pd.getString("fmsg"); // 判断是系统用户还是会员 "appuser"为会员用户

		if ("yes".endsWith(isAll)) {
			try {
				List<PageData> userList = new ArrayList<PageData>();

				userList = "appuser".equals(fmsg) ? appuserService.listAllUser(pd) : bgUserService.listAllUser(pd);

				zcount = userList.size();
				try {
					for (int i = 0; i < userList.size(); i++) {
						if (Tools.checkMobileNumber(userList.get(i).getString("PHONE"))) { // 手机号格式不对就跳过
							if ("1".equals(TYPE)) {
								SmsUtil.sendSms1(userList.get(i).getString("PHONE"), CONTENT); // 调用发短信函数1
							} else {
								SmsUtil.sendSms2(userList.get(i).getString("PHONE"), CONTENT); // 调用发短信函数2
							}
							count++;
						} else {
							continue;
						}
					}
					msg = "ok";
				} catch (Exception e) {
					msg = "error";
				}

			} catch (Exception e) {
				msg = "error";
			}
		} else {
			PHONEs = PHONEs.replaceAll("；", ";");
			PHONEs = PHONEs.replaceAll(" ", "");
			String[] arrTITLE = PHONEs.split(";");
			zcount = arrTITLE.length;
			try {
				for (int i = 0; i < arrTITLE.length; i++) {
					if (Tools.checkMobileNumber(arrTITLE[i])) { // 手机号式不对就跳过
						if ("1".equals(TYPE)) {
							SmsUtil.sendSms1(arrTITLE[i], CONTENT); // 调用发短信函数1
						} else {
							SmsUtil.sendSms2(arrTITLE[i], CONTENT); // 调用发短信函数2
						}
						count++;
					} else {
						continue;
					}
				}
				msg = "ok";
			} catch (Exception e) {
				msg = "error";
			}
		}
		pd.put("msg", msg);
		pd.put("count", count); // 成功数
		pd.put("ecount", zcount - count); // 失败数
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}

	/**
	 * 去发送电子邮件页面
	 */
	@RequestMapping(value = "/goSendEmail")
	public ModelAndView goSendEmail() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		mv.setViewName("system/head/send_email");
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 发送电子邮件
	 */
	@RequestMapping(value = "/sendEmail")
	@ResponseBody
	public Object sendEmail() {
		PageData pd = new PageData();
		pd = this.getPageData();
		Map<String, Object> map = new HashMap<String, Object>();
		String msg = "ok"; // 发送状态
		int count = 0; // 统计发送成功条数
		int zcount = 0; // 理论条数

		String strEMAIL = Tools.readTxtFile(Const.EMAIL); // 读取邮件配置

		List<PageData> pdList = new ArrayList<PageData>();

		String toEMAIL = pd.getString("EMAIL"); // 对方邮箱
		String TITLE = pd.getString("TITLE"); // 标题
		String CONTENT = pd.getString("CONTENT"); // 内容
		String TYPE = pd.getString("TYPE"); // 类型
		String isAll = pd.getString("isAll"); // 是否发送给全体成员 yes or no

		String fmsg = pd.getString("fmsg"); // 判断是系统用户还是会员 "appuser"为会员用户

		if (null != strEMAIL && !"".equals(strEMAIL)) {
			String strEM[] = strEMAIL.split(",fh,");
			if (strEM.length == 4) {
				if ("yes".endsWith(isAll)) {
					try {
						List<PageData> userList = new ArrayList<PageData>();

						userList = "appuser".equals(fmsg) ? appuserService.listAllUser(pd) : bgUserService.listAllUser(pd);

						zcount = userList.size();
						try {
							for (int i = 0; i < userList.size(); i++) {
								if (Tools.checkEmail(userList.get(i).getString("EMAIL"))) { // 邮箱格式不对就跳过
									SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], userList.get(i).getString("EMAIL"), TITLE, CONTENT, TYPE);// 调用发送邮件函数
									count++;
								} else {
									continue;
								}
							}
							msg = "ok";
						} catch (Exception e) {
							msg = "error";
						}

					} catch (Exception e) {
						msg = "error";
					}
				} else {
					toEMAIL = toEMAIL.replaceAll("；", ";");
					toEMAIL = toEMAIL.replaceAll(" ", "");
					String[] arrTITLE = toEMAIL.split(";");
					zcount = arrTITLE.length;
					try {
						for (int i = 0; i < arrTITLE.length; i++) {
							if (Tools.checkEmail(arrTITLE[i])) { // 邮箱格式不对就跳过
								SimpleMailSender.sendEmail(strEM[0], strEM[1], strEM[2], strEM[3], arrTITLE[i], TITLE, CONTENT, TYPE);// 调用发送邮件函数
								count++;
							} else {
								continue;
							}
						}
						msg = "ok";
					} catch (Exception e) {
						msg = "error";
					}
				}
			} else {
				msg = "error";
			}
		} else {
			msg = "error";
		}
		pd.put("msg", msg);
		pd.put("count", count); // 成功数
		pd.put("ecount", zcount - count); // 失败数
		pdList.add(pd);
		map.put("list", pdList);
		return AppUtil.returnObject(pd, map);
	}





	/**
	 * 保存系统设置2
	 */
	@RequestMapping(value = "/saveSys2")
	public ModelAndView saveSys2() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Tools.writeFile(Const.FWATERM, pd.getString("isCheck1") + ",fh," + pd.getString("fcontent") + ",fh," + pd.getString("fontSize") + ",fh," + pd.getString("fontX") + ",fh," + pd.getString("fontY")); // 文字水印配置
		Tools.writeFile(Const.IWATERM, pd.getString("isCheck2") + ",fh," + pd.getString("imgUrl") + ",fh," + pd.getString("imgX") + ",fh," + pd.getString("imgY")); // 图片水印配置
		Watermark.fushValue();
		mv.addObject("msg", "OK");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 保存系统设置3
	 */
	@RequestMapping(value = "/saveSys3")
	public ModelAndView saveSys3() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		Tools.writeFile(Const.WEIXIN, pd.getString("Token")); // 写入微信配置
		Tools.writeFile(Const.WEBSOCKET, pd.getString("WIMIP") + ",fh," + pd.getString("WIMPORT") + ",fh," + pd.getString("OLIP") + ",fh," + pd.getString("OLPORT")); // websocket配置
		mv.addObject("msg", "OK");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 去代码生成器页面
	 */
	@RequestMapping(value = "/toCreateCode")
	public ModelAndView toCreateCode() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("background/config/bgCodeCreate");
		return mv;
	}
	
	/**
	 * 生成代码
	 */
	@RequestMapping(value = "/codeCreate")
	public void codeCreate(HttpServletResponse response) throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();

		/* ============================================================================================= */
		String controlModule = pd.getString("controlModule"); // 控制模块名 ========1
		ComDict comDict = comDictService.findByAllEncode(controlModule);
		String controlModuleNL = comDict.getName();
		String controlModuleNU = StringUtil.firstToUpper(controlModuleNL);
		String controlModuleEL = comDict.getEncode();
		String controlModuleEU = StringUtil.firstToUpper(controlModuleNL);
		
		String objectModule = pd.getString("objectModule"); // 控制模块名 ========2
		comDict = comDictService.findByAllEncode(objectModule);
		String objectModuleNL = comDict.getName();
		String objectModuleNU = StringUtil.firstToUpper(objectModuleNL);
		String objectModuleEL = comDict.getEncode();
		String objectModuleEU = StringUtil.firstToUpper(objectModuleEL);
		
		String objectNameL = StringUtil.firstToLower(pd.getString("objectName")); // 类名 ========3
		String objectNameU = StringUtil.firstToUpper(objectNameL);
		
		String tableName = pd.getString("tableName"); // 表中文名 ========4
		
		String fieldCountStr = pd.getString("fieldCount"); // 属性总数
		int fieldCount = 0;
		if (null != fieldCountStr && !"".equals(fieldCountStr)) {
			fieldCount = Integer.parseInt(fieldCountStr);
		}
		List<String[]> fieldList = new ArrayList<String[]>(); // 属性集合 ========5
		for (int i = 0; i < fieldCount; i++) {
			String[] field = pd.getString("field" + i).split(",maple,");
			field[0] = StringUtil.firstToLower(field[0]);
			String[] fieldAdd = {StringUtil.firstToUpper(field[0])};
			fieldList.add((String[])ArrayUtils.addAll(fieldAdd, field)); // 属性放到集合里面
		}

		Map<String, Object> root = new HashMap<String, Object>(); // 创建数据模型
		
		root.put("controlModuleNL", controlModuleNL); // background
		root.put("controlModuleNU", controlModuleNU); // Background
		root.put("controlModuleEL", controlModuleEL); // bg
		root.put("controlModuleEU", controlModuleEU); // Bg
		root.put("objectModuleNL", objectModuleNL); // comon
		root.put("objectModuleNU", objectModuleNU); // Comon
		root.put("objectModuleEL", objectModuleEL); // com
		root.put("objectModuleEU", objectModuleEU); // 	Com
		root.put("objectNameL", objectNameL); // user
		root.put("objectNameU", objectNameU); // User
		root.put("tableName", tableName); // User
		
		root.put("fieldCount", fieldCount);
		root.put("fieldList", fieldList);
		
		root.put("nowDate", new Date()); // 当前日期

		DelAllFile.delFolder(PathUtil.getClasspath() + "admin/ftl"); // 生成代码前,先清空之前生成的代码
		/* ============================================================================================= */

		String filePath = "admin/ftl/code/"; // 存放路径
		String ftlPath = "createCode"; // ftl路径

		/* 生成controller */
		Freemarker.printFile("controllerTemplate.ftl", root, controlModuleNL + "/controller/" + controlModuleEU + objectNameU + "Controller.java", filePath, ftlPath);

		/* 生成service */
		Freemarker.printFile("serviceTemplate.ftl", root, objectModuleNU + "/service/" + objectModuleEU  + objectNameU + "Service.java", filePath, ftlPath);

		/* 生成entity */
		Freemarker.printFile("entityTemplate.ftl", root, objectModuleNU + "/entity/" + objectModuleEU  + objectNameU + ".java", filePath, ftlPath);
		
		/* 生成mybatis xml Mysql*/
		Freemarker.printFile("mapperMysqlTemplate.ftl", root, "mybatis/" + objectModuleNU + "/" + objectModuleEU  + objectNameU + "Mapper.xml", filePath, ftlPath);
		/* 生成mybatis xml Oracle*/
		//Freemarker.printFile("mapperOracleTemplate.ftl", root, "mybatis_oracle/" + packageName + "/" + objectName + "Mapper.xml", filePath, ftlPath);

		/* 生成SQL脚本 Mysql*/
		Freemarker.printFile("mysql_SQL_Template.ftl", root, "mysql数据库脚本/" + objectModuleEL + objectNameU + ".sql", filePath, ftlPath);
		/* 生成SQL脚本 Oracle*/
		//Freemarker.printFile("oracle_SQL_Template.ftl", root, "oracle数据库脚本/" + tabletop + objectName.toUpperCase() + ".sql", filePath, ftlPath);

		/* 生成jsp页面 */
//		Freemarker.printFile("jsp_list_Template.ftl", root, "jsp/" + controlModuleNL + "/" + objectNameL + "/" + objectModuleEL + objectNameU + "List.jsp", filePath, ftlPath);
//		Freemarker.printFile("jsp_edit_Template.ftl", root, "jsp/" + controlModuleNL + "/" + objectNameL + "/" + objectModuleEL + objectNameU + "Edit.jsp", filePath, ftlPath);

		/* 生成说明文档 */
//		Freemarker.printFile("docTemplate.ftl", root, "说明.doc", filePath, ftlPath);

		// this.print("oracle_SQL_Template.ftl", root); 控制台打印

		/* 生成的全部代码压缩成zip文件 */
		FileZip.zip(PathUtil.getClasspath() + "admin/ftl/code", PathUtil.getClasspath() + "admin/ftl/code.zip");

		/* 下载代码 */
		FileDownload.fileDownload(response, PathUtil.getClasspath() + "admin/ftl/code.zip", "code.zip");

	}

}
