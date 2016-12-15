package com.jx.background.entity;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.config.BgPage;
import com.jx.background.service.BgMenuService;
import com.jx.background.service.BgRoleService;
import com.jx.background.service.BgUserService;
import com.jx.common.config.BaseController;
import com.jx.common.config.Const;
import com.jx.common.config.PageData;
import com.jx.common.util.AppUtil;
import com.jx.common.util.FileDownload;
import com.jx.common.util.FileUpload;
import com.jx.common.util.GetPinyin;
import com.jx.common.util.Jurisdiction;
import com.jx.common.util.ObjectExcelRead;
import com.jx.common.util.ObjectExcelView;
import com.jx.common.util.PathUtil;
import com.jx.common.util.Tools;

/**
 * 类名称：UserController 创建人：FH 创建时间：2014年6月28日
 * @version
 */
@Controller
@RequestMapping(value = "/background/user")
public class BgUserController extends BaseController {

	String menuUrl = "background/user/listUsers.do"; // 菜单地址(权限用)
	@Resource(name = "bgUserService")
	private BgUserService bgUserService;
	@Resource(name = "bgRoleService")
	private BgRoleService bgRoleService;
	@Resource(name = "bgMenuService")
	private BgMenuService bgMenuService;

	/**
	 * 新增用户
	 */
	@RequestMapping(value = "/addUser")
	public ModelAndView addUser(PrintWriter out) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		pd.put("userId", this.get32UUID()); // ID
		pd.put("permissions", ""); // 权限
		pd.put("lastLogin", ""); // 最后登录时间
		pd.put("loginIp", ""); // loginIp
		pd.put("status", "0"); // 状态
		pd.put("skin", "default"); // 默认皮肤

		pd.put("password", new SimpleHash("SHA-1", pd.getString("userName"), pd.getString("password")).toString());

		if (null == bgUserService.findByUserName(pd)) {
			if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
				bgUserService.addUser(pd);
			} // 判断新增权限
			mv.addObject("msg", "success");
		} else {
			mv.addObject("msg", "failed");
		}
		mv.setViewName("background/bgSaveResult");
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

	/**
	 * 修改用户
	 */
	@RequestMapping(value = "/editUser")
	public ModelAndView editUser() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if (pd.getString("password") != null && !"".equals(pd.getString("password"))) {
			pd.put("password", new SimpleHash("SHA-1", pd.getString("userName"), pd.getString("password")).toString());
		}
		if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			bgUserService.editUser(pd);
		}
		mv.addObject("msg", "success");
		mv.setViewName("background/bgSaveResult");
		return mv;
	}

	/**
	 * 去修改用户页面
	 */
	@RequestMapping(value = "/goEditUser")
	public ModelAndView goEditUser() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		// 顶部修改个人资料
		String fx = pd.getString("fx");

		// System.out.println(fx);

		if ("head".equals(fx)) {
			mv.addObject("fx", "head");
		} else {
			mv.addObject("fx", "user");
		}

		List<BgRole> roleList = bgRoleService.listAllSecondRoles(); // 列出所有二级角色
		BgUser bgUser = bgUserService.findByUserId(pd); // 根据ID读取
		mv.setViewName("background/user/userEdit");
		mv.addObject("msg", "editUser");
		mv.addObject("bgUser", bgUser);
		mv.addObject("roleList", roleList);

		return mv;
	}
	
	/**
	 * 换 皮肤
	 */
	@RequestMapping(value = "/changeSkin")
	public void changeSkin(PrintWriter out) {
		PageData pd = new PageData();
		try {
			pd = this.getPageData();

			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			String userName = session.getAttribute(Const.SESSION_BG_USERNAME_STR).toString();// 获取当前登录者loginname
			pd.put("userName", userName);
			bgUserService.changeSkin(pd);
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}

	/**
	 * 去新增用户页面
	 */
	@RequestMapping(value = "/goAddU")
	public ModelAndView goAddU() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<BgRole> roleList;

		roleList = bgRoleService.listAllSecondRoles(); // 列出所有二级角色

		mv.setViewName("system/user/user_edit");
		mv.addObject("msg", "saveU");
		mv.addObject("pd", pd);
		mv.addObject("roleList", roleList);

		return mv;
	}

	/**
	 * 显示用户列表(用户组)
	 */
	@RequestMapping(value = "/userList")
	public ModelAndView userList(BgPage bgPage) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		String userName = pd.getString("userName");

		if (null != userName && !"".equals(userName)) {
			userName = userName.trim();
			pd.put("userName", userName);
		}

		String lastLoginStart = pd.getString("lastLoginStart");
		String lastLoginEnd = pd.getString("lastLoginEnd");

		if (lastLoginStart != null && !"".equals(lastLoginStart)) {
			lastLoginStart = lastLoginStart + " 00:00:00";
			pd.put("lastLoginStart", lastLoginStart);
		}
		if (lastLoginEnd != null && !"".equals(lastLoginEnd)) {
			lastLoginEnd = lastLoginEnd + " 00:00:00";
			pd.put("lastLoginEnd", lastLoginEnd);
		}

		bgPage.setPd(pd);
		List<PageData> userList = bgUserService.listUser(bgPage); // 列出用户列表
		List<BgRole> roleList = bgRoleService.listAllSecondRoles(); // 列出所有二级角色

		mv.addObject("userList", userList);
		mv.addObject("roleList", roleList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_BG_QX_STR, this.getHC()); // 按钮权限
		mv.setViewName("background/user/bgUserList");
		
		return mv;
	}

	/**
	 * 显示用户列表(tab方式)
	 */
	@RequestMapping(value = "/listtabUsers")
	public ModelAndView listtabUsers(BgPage bgPage) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		List<PageData> userList = bgUserService.listAllUser(pd); // 列出用户列表
		mv.setViewName("system/user/user_tb_list");
		mv.addObject("userList", userList);
		mv.addObject("pd", pd);
		mv.addObject(Const.SESSION_BG_QX_STR, this.getHC()); // 按钮权限
		return mv;
	}

	/**
	 * 删除用户
	 */
	@RequestMapping(value = "/deleteU")
	public void deleteU(PrintWriter out) {
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
				bgUserService.deleteUser(pd);
			}
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value = "/deleteAllU")
	@ResponseBody
	public Object deleteAllU() {
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String userIds = pd.getString("userIds");

			if (null != userIds && !"".equals(userIds)) {
				String arrayUserIds[] = userIds.split(",");
				if (Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
					bgUserService.deleteMoreUser(arrayUserIds);
				}
				pd.put("msg", "ok");
			} else {
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

	// ===================================================================================================

	/*
	 * 导出用户信息到EXCEL
	 * @return
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			if (Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
				// 检索条件===
				String userName = pd.getString("userName");
				if (null != userName && !"".equals(userName)) {
					userName = userName.trim();
					pd.put("userName", userName);
				}
				String lastLoginStart = pd.getString("lastLoginStart");
				String lastLoginEnd = pd.getString("lastLoginEnd");
				if (lastLoginStart != null && !"".equals(lastLoginStart)) {
					lastLoginStart = lastLoginStart + " 00:00:00";
					pd.put("lastLoginStart", lastLoginStart);
				}
				if (lastLoginEnd != null && !"".equals(lastLoginEnd)) {
					lastLoginEnd = lastLoginEnd + " 00:00:00";
					pd.put("lastLoginEnd", lastLoginEnd);
				}
				// 检索条件===

				Map<String, Object> dataMap = new HashMap<String, Object>();
				List<String> titles = new ArrayList<String>();

				titles.add("用户名"); // 1
				titles.add("编号"); // 2
				titles.add("姓名"); // 3
				titles.add("职位"); // 4
				titles.add("手机"); // 5
				titles.add("邮箱"); // 6
				titles.add("最近登录"); // 7
				titles.add("上次登录IP"); // 8

				dataMap.put("titles", titles);

				List<PageData> userList = bgUserService.listAllUser(pd);
				List<PageData> varList = new ArrayList<PageData>();
				for (int i = 0; i < userList.size(); i++) {
					PageData vpd = new PageData();
					vpd.put("var1", userList.get(i).getString("userName")); // 1
					vpd.put("var2", userList.get(i).getString("userNumber")); // 2
					vpd.put("var3", userList.get(i).getString("name")); // 3
					vpd.put("var4", userList.get(i).getString("ROLE_NAME")); // 4
					vpd.put("var5", userList.get(i).getString("phone")); // 5
					vpd.put("var6", userList.get(i).getString("EMAIL")); // 6
					vpd.put("var7", userList.get(i).getString("lastLogin")); // 7
					vpd.put("var8", userList.get(i).getString("loginIp")); // 8
					varList.add(vpd);
				}
				dataMap.put("varList", varList);
				ObjectExcelView erv = new ObjectExcelView(); // 执行excel操作
				mv = new ModelAndView(erv, dataMap);
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 打开上传EXCEL页面
	 */
	@RequestMapping(value = "/goUploadExcel")
	public ModelAndView goUploadExcel() throws Exception {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("system/user/uploadexcel");
		return mv;
	}

	/**
	 * 下载模版
	 */
	@RequestMapping(value = "/downExcel")
	public void downExcel(HttpServletResponse response) throws Exception {

		FileDownload.fileDownload(response, PathUtil.getClasspath() + Const.FILEPATHFILE + "Users.xls", "Users.xls");

	}

	/**
	 * 从EXCEL导入到数据库
	 */
	@RequestMapping(value = "/readExcel")
	public ModelAndView readExcel(@RequestParam(value = "excel", required = false) MultipartFile file) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
			return null;
		}
		if (null != file && !file.isEmpty()) {
			String filePath = PathUtil.getClasspath() + Const.FILEPATHFILE; // 文件上传路径
			String fileName = FileUpload.fileUp(file, filePath, "userexcel"); // 执行上传

			List<PageData> listPd = (List) ObjectExcelRead.readExcel(filePath, fileName, 2, 0, 0); // 执行读EXCEL操作,读出的数据导入List 2:从第3行开始；0:从第A列开始；0:第0个sheet

			/* 存入数据库操作====================================== */
			pd.put("rights", ""); // 权限
			pd.put("lastLogin", ""); // 最后登录时间
			pd.put("loginIp", ""); // loginIp
			pd.put("status", "0"); // 状态
			pd.put("skin", "default"); // 默认皮肤

			List<BgRole> roleList = bgRoleService.listAllSecondRoles(); // 列出所有二级角色

			pd.put("roleId", roleList.get(0).getRoleId()); // 设置角色ID为随便第一个
			/**
			 * var0 :编号 var1 :姓名 var2 :手机 var3 :邮箱 var4 :备注
			 */
			for (int i = 0; i < listPd.size(); i++) {
				pd.put("userId", this.get32UUID()); // ID
				pd.put("name", listPd.get(i).getString("var1")); // 姓名

				String userName = GetPinyin.getPingYin(listPd.get(i).getString("var1")); // 根据姓名汉字生成全拼
				pd.put("userName", userName);
				if (bgUserService.findByUserName(pd) != null) { // 判断用户名是否重复
					userName = GetPinyin.getPingYin(listPd.get(i).getString("var1")) + Tools.getRandomNum();
					pd.put("userName", userName);
				}
				pd.put("BZ", listPd.get(i).getString("var4")); // 备注
				if (Tools.checkEmail(listPd.get(i).getString("var3"))) { // 邮箱格式不对就跳过
					pd.put("EMAIL", listPd.get(i).getString("var3"));
					if (bgUserService.findByEmail(pd) != null) { // 邮箱已存在就跳过
						continue;
					}
				} else {
					continue;
				}

				pd.put("userNumber", listPd.get(i).getString("var0")); // 编号已存在就跳过
				pd.put("phone", listPd.get(i).getString("var2")); // 手机号

				pd.put("password", new SimpleHash("SHA-1", userName, "123").toString()); // 默认密码123
				if (bgUserService.findByUserNumber(pd) != null) {
					continue;
				}
				bgUserService.addUser(pd);
			}
			/* 存入数据库操作====================================== */

			mv.addObject("msg", "success");
		}

		mv.setViewName("save_result");
		return mv;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}

	/* ===============================权限================================== */
	public Map<String, String> getHC() {
		Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>) session.getAttribute(Const.SESSION_BG_QX_STR);
	}
	/* ===============================权限================================== */
}
