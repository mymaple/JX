package com.jx.background.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.entity.BgConfig;
import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRole;
import com.jx.background.entity.BgUser;
import com.jx.background.service.BgConfigService;
import com.jx.background.service.BgMenuService;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.DateUtil;
import com.jx.common.util.DrawImageUtil;
import com.jx.common.util.RightsHelper;
import com.jx.common.util.Tools;

/*
 * 总入口
 */
@Controller
@RequestMapping(value = "/background/main")
public class BgMainController extends BaseController {

	@Resource(name = "bgUserService")
	private BgUserService bgUserService;
	@Resource(name = "bgMenuService")
	private BgMenuService bgMenuService;
	@Resource(name = "bgRoleService")
	private BgRoleService bgRoleService;
	@Resource(name = "bgConfigService")
	private BgConfigService bgConfigService;

	
	
	/**
	 * 获取验证码
	 * @return
	 */
	@RequestMapping(value = "/getVerificationCode")
	public void getVerificationCode(HttpServletResponse response) {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		String verificationCode = DrawImageUtil.drawImg(output);

		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.setAttribute(Const.SESSION_BG_VERIFICATIONCODE_STR, verificationCode);

		try {
			ServletOutputStream out = response.getOutputStream();
			output.writeTo(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		
		pd = this.getPageData();
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		BgConfig bgConfigSystem = (BgConfig) session.getAttribute(Const.CONFIG_BG_SYSTEM_OBJ);
		if (bgConfigSystem == null) {
			bgConfigSystem = bgConfigService.findConfigByConfigType(Const.CONFIG_BG_SYSTEM_OBJ);
			session.setAttribute(Const.CONFIG_BG_SYSTEM_OBJ,bgConfigSystem);
		}
		
		pd.put("systemName", bgConfigSystem.getParam1()); // 读取系统名称
		mv.addObject("pd", pd);
		mv.setViewName("background/main/bgLogin");
		return mv;
	}
	
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout() {

		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();

		session.removeAttribute(Const.SESSION_BG_USER_OBJ);
		session.removeAttribute(Const.SESSION_BG_ROLEPERMISSIONS_STR);
		session.removeAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST);
		session.removeAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST);
		session.removeAttribute(Const.SESSION_BG_QX_STR);
		session.removeAttribute(Const.SESSION_BG_USERNAME_STR);
		session.removeAttribute(Const.SESSION_BG_USER_ROLE_OBJ);
		session.removeAttribute(Const.SESSION_BG_CHANGEMENU_STR);

		// shiro销毁登录
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String msg = pd.getString("msg");
		pd.put("msg", msg);
		
		BgConfig bgConfigSystem = null;
		try {
			bgConfigSystem = bgConfigService.findConfigByConfigType(Const.CONFIG_BG_SYSTEM_OBJ);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pd.put("systemName", bgConfigSystem==null?"":bgConfigSystem.getParam1()); // 读取系统名称
		mv.addObject("pd", pd);
		mv.setViewName("background/main/bgLogin");
		
		return mv;
	}

	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value = "/login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String keyData[] = pd.getString("keyData").replaceAll("ndknsdkfjksdfj", "").replaceAll("kgnlkfsl", "").split(",jx,");

		if (null != keyData && keyData.length == 3) {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			String sessionBgVerificationCode = (String) session.getAttribute(Const.SESSION_BG_VERIFICATIONCODE_STR); // 获取session中的验证码

			String bgVerificationCode = keyData[2];
			
			//开发跳过、、登录
			bgVerificationCode = sessionBgVerificationCode;
			
			
			if (null == bgVerificationCode || "".equals(bgVerificationCode)) {
				errInfo = "nullcode"; // 验证码为空
			} else {
				String userName = keyData[0];
				String password = keyData[1];
				pd.put("userName", userName);
				if (Tools.notEmpty(sessionBgVerificationCode) && sessionBgVerificationCode.equalsIgnoreCase(bgVerificationCode)) {
					String passwd = new SimpleHash("SHA-1", userName, password).toString(); // 密码加密
					pd.put("password", passwd);
					BgUser bgUser = new BgUser();
					bgUser = bgUserService.checkUserByNameAndPwd(pd);
					if (bgUser != null) {
						
						bgUser = this.changeLoginIpInfo(bgUser);
						
						session.setAttribute(Const.SESSION_BG_USER_OBJ, bgUser);
						session.removeAttribute(Const.SESSION_BG_VERIFICATIONCODE_STR);

						// shiro加入身份验证
						Subject subject = SecurityUtils.getSubject();
						UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
						try {
							subject.login(token);
						} catch (AuthenticationException e) {
							errInfo = "身份验证失败！";
						}
					} else {
						errInfo = "usererror"; // 用户名或密码有误
					}
				} else {
					errInfo = "codeerror"; // 验证码输入有误
				}
				if (Tools.isEmpty(errInfo)) {
					errInfo = "success"; // 验证成功
				}
			}
		} else {
			errInfo = "error"; // 缺少参数
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 访问系统首页
	 */
	@RequestMapping(value = "/{changeMenu}")
	public ModelAndView loginToIndex(@PathVariable("changeMenu") String changeMenu) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			BgUser bgUser = (BgUser) session.getAttribute(Const.SESSION_BG_USER_OBJ);
			if (bgUser != null) {
				BgUser bgUserRole = (BgUser) session.getAttribute(Const.SESSION_BG_USER_ROLE_OBJ);
				if (null == bgUserRole) {
					bgUser = bgUserService.getUserRoleById(bgUser.getUserId());
					session.setAttribute(Const.SESSION_BG_USER_ROLE_OBJ, bgUser);
				} else {
					bgUser = bgUserRole;
				}
				BgRole bgRole = bgUser.getBgRole();
				String rolePermissions = bgRole != null ? bgRole.getPermissions() : "";
				// 避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
				session.setAttribute(Const.SESSION_BG_ROLEPERMISSIONS_STR, rolePermissions); // 将角色权限存入session
				session.setAttribute(Const.SESSION_BG_USERNAME_STR, bgUser.getUserName()); // 放入用户名

				List<BgMenu> bgAllMenuInRankList = new ArrayList<BgMenu>();

				if (null == session.getAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST)) {
					bgAllMenuInRankList = bgMenuService.listAllMenuInRank();
					if (Tools.notEmpty(rolePermissions)) {
						for (BgMenu bgMenu : bgAllMenuInRankList) {
							bgMenu.setHasMenu(RightsHelper.testRights(rolePermissions, bgMenu.getMenuId()));
							if (bgMenu.isHasMenu()) {
								List<BgMenu> subBgMenuList = bgMenu.getSubBgMenuList();
								for (BgMenu subBgMenu : subBgMenuList) {
									subBgMenu.setHasMenu(RightsHelper.testRights(rolePermissions, subBgMenu.getMenuId()));
								}
							}
						}
					}
					session.setAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST, bgAllMenuInRankList); // 菜单权限放入session中
				} else {
					bgAllMenuInRankList = (List<BgMenu>) session.getAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST);
				}

				// 切换菜单=====
				List<BgMenu> bgMenuInCurrentList = new ArrayList<BgMenu>();
				// if(null == session.getAttribute(Const.SESSION_BG_MENULIST) || ("yes".equals(pd.getString("changeMenu")))){
				if (null == session.getAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST) || ("yes".equals(changeMenu))) {
					List<BgMenu> bgMenuInCurrentList1 = new ArrayList<BgMenu>();
					List<BgMenu> bgMenuInCurrentList2 = new ArrayList<BgMenu>();

					// 拆分菜单
					for (int i = 0; i < bgAllMenuInRankList.size(); i++) {
						BgMenu bgMenu = bgAllMenuInRankList.get(i);
						if ("1".equals(bgMenu.getMenuType())) {
							bgMenuInCurrentList1.add(bgMenu);
						} else {
							bgMenuInCurrentList2.add(bgMenu);
						}
					}

					session.removeAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST);
					if ("2".equals(session.getAttribute("changeMenu"))) {
						session.setAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST, bgMenuInCurrentList1);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "1");
						bgMenuInCurrentList = bgMenuInCurrentList1;
					} else {
						session.setAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST, bgMenuInCurrentList2);
						session.removeAttribute("changeMenu");
						session.setAttribute("changeMenu", "2");
						bgMenuInCurrentList = bgMenuInCurrentList2;
					}
				} else {
					bgMenuInCurrentList = (List<BgMenu>) session.getAttribute(Const.SESSION_BG_MENU_INCURRTEN_LIST);
				}
				// 切换菜单=====

				/*if (null == session.getAttribute(Const.SESSION_BG_QX)) {
					session.setAttribute(Const.SESSION_BG_QX, this.getUserQx(session)); // 按钮权限放到session中
				}*/

				// FusionCharts 报表
				String strXML = "<graph caption='前12个月订单销量柱状图' xAxisName='月份' yAxisName='值' decimalPrecision='0' formatNumberScale='0'><set name='2013-05' value='4' color='AFD8F8'/><set name='2013-04' value='0' color='AFD8F8'/><set name='2013-03' value='0' color='AFD8F8'/><set name='2013-02' value='0' color='AFD8F8'/><set name='2013-01' value='0' color='AFD8F8'/><set name='2012-01' value='0' color='AFD8F8'/><set name='2012-11' value='0' color='AFD8F8'/><set name='2012-10' value='0' color='AFD8F8'/><set name='2012-09' value='0' color='AFD8F8'/><set name='2012-08' value='0' color='AFD8F8'/><set name='2012-07' value='0' color='AFD8F8'/><set name='2012-06' value='0' color='AFD8F8'/></graph>";
				mv.addObject("strXML", strXML);
				// FusionCharts 报表

				// 读取websocket配置
				BgConfig bgConfigOnlineManage = (BgConfig) session.getAttribute(Const.CONFIG_BG_ONLINEMANAGE_OBJ);
				if (bgConfigOnlineManage == null) {
					bgConfigOnlineManage = bgConfigService.findConfigByConfigType(Const.CONFIG_BG_ONLINEMANAGE_OBJ);
					session.setAttribute(Const.CONFIG_BG_ONLINEMANAGE_OBJ,bgConfigOnlineManage);
				}
				
				BgConfig bgConfigInstantChat = (BgConfig) session.getAttribute(Const.CONFIG_BG_INSTANTCHAT_OBJ);
				if (bgConfigInstantChat == null) {
					bgConfigInstantChat = bgConfigService.findConfigByConfigType(Const.CONFIG_BG_INSTANTCHAT_OBJ);
					session.setAttribute(Const.CONFIG_BG_INSTANTCHAT_OBJ,bgConfigInstantChat);
				}
				
				pd.put("onlineManageIp", bgConfigOnlineManage.getParam1());
				pd.put("onlineManagePort", bgConfigOnlineManage.getParam2());
				pd.put("instantChatIp", bgConfigInstantChat.getParam1());
				pd.put("instantChatPort", bgConfigInstantChat.getParam2());
				// 读取websocket配置
				mv.addObject("bgUser", bgUser);
				mv.addObject("bgMenuInCurrentList", bgMenuInCurrentList);
				mv.setViewName("background/main/bgIndex");
			} else {
				mv.setViewName("background/main/bgLogin");// session失效后跳转登录页面
			}
			
			BgConfig bgConfigSystem = (BgConfig) session.getAttribute(Const.CONFIG_BG_SYSTEM_OBJ);
			if (bgConfigSystem == null) {
				bgConfigSystem = bgConfigService.findConfigByConfigType(Const.CONFIG_BG_SYSTEM_OBJ);
				session.setAttribute(Const.CONFIG_BG_SYSTEM_OBJ,bgConfigSystem);
			}
			pd.put("systemName", bgConfigSystem.getParam1()); // 读取系统名称

		} catch (Exception e) {
			mv.setViewName("background/main/bgLogin");
			logger.error(e.getMessage(), e);
		}
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 进入tab标签
	 * @return
	 */
	@RequestMapping(value = "/tab")
	public String tab() {
		return "background/main/bgTab";
	}

	/**
	 * 进入首页后的默认页面
	 * @return
	 */
	@RequestMapping(value = "/default")
	public String defaultPage() {
		return "background/main/bgDefault";
	}

	/**
	 * 获取用户权限
	 */
	public Map<String, String> getUserQx(Session session) {
		PageData pd = new PageData();
		Map<String, String> map = new HashMap<String, String>();
		try {
			String userName = session.getAttribute(Const.SESSION_BG_USERNAME_STR).toString();
			String roleId = bgUserService.findByUserName(pd).getRoleId();

			pd.put(Const.SESSION_BG_USERNAME_STR, userName);
			pd.put("roleId", roleId);
			pd = bgRoleService.findObjectById(pd);

			PageData pd2 = new PageData();
			pd2.put(Const.SESSION_BG_USERNAME_STR, userName);
			pd2.put("roleId", roleId);
			pd2 = bgRoleService.findGlByRoleId(pd2);
			
			if (null != pd2) {
				map.put("FX_QX", pd2.get("FX_QX").toString());
				map.put("FW_QX", pd2.get("FW_QX").toString());
				map.put("QX1", pd2.get("QX1").toString());
				map.put("QX2", pd2.get("QX2").toString());
				map.put("QX3", pd2.get("QX3").toString());
				map.put("QX4", pd2.get("QX4").toString());

				pd2.put("roleId", roleId);
				pd2 = bgRoleService.findYHbyrid(pd2);
				map.put("C1", pd2.get("C1").toString());
				map.put("C2", pd2.get("C2").toString());
				map.put("C3", pd2.get("C3").toString());
				map.put("C4", pd2.get("C4").toString());
				map.put("Q1", pd2.get("Q1").toString());
				map.put("Q2", pd2.get("Q2").toString());
				map.put("Q3", pd2.get("Q3").toString());
				map.put("Q4", pd2.get("Q4").toString());
			}

			map.put("adds", pd.getString("ADD_QX"));
			map.put("dels", pd.getString("DEL_QX"));
			map.put("edits", pd.getString("EDIT_QX"));
			map.put("chas", pd.getString("CHA_QX"));

			// System.out.println(map);

		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return map;
	}
	
	
	public BgUser changeLoginIpInfo(BgUser bgUser) throws Exception {
		HttpServletRequest request = this.getRequest();
		String loginIp = "";
		if (request.getHeader("x-forwarded-for") == null) {
			loginIp = request.getRemoteAddr();
		} else {
			loginIp = request.getHeader("x-forwarded-for");
		}
		String lastLogin = DateUtil.getTime().toString();
		bgUser.setLastLogin(lastLogin);
		bgUser.setLoginIp(loginIp);
		bgUserService.changeLoginIpInfo(bgUser);
		return bgUser;
	}
	

}
