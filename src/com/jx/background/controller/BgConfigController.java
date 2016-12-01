package com.jx.background.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.entity.BgConfig;
import com.jx.background.service.AppuserService;
import com.jx.background.service.BgConfigService;
import com.jx.background.service.BgUserService;
import com.jx.system.config.BaseController;
import com.jx.system.config.Const;
import com.jx.system.config.PageData;
import com.jx.system.util.AppUtil;
import com.jx.system.util.Tools;

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
	@RequestMapping(value = "/goProductCode")
	public ModelAndView goProductCode() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/head/productCode");
		return mv;
	}

}
