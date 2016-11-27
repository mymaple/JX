package com.jx.background.controller;

import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jx.background.entity.BgMenu;
import com.jx.background.entity.BgRole;
import com.jx.background.service.BgMenuService;
import com.jx.background.service.BgRoleService;
import com.jx.system.config.BaseController;
import com.jx.system.config.BgPage;
import com.jx.system.config.Const;
import com.jx.system.config.PageData;
import com.jx.system.util.AppUtil;
import com.jx.system.util.Jurisdiction;
import com.jx.system.util.RightsHelper;
import com.jx.system.util.Tools;

import net.sf.json.JSONArray;

/**
 * 类名称：RoleController 创建人：FH 创建时间：2014年6月30日
 * @version
 */
@Controller
@RequestMapping(value = "/bgRole")
public class BgRoleController extends BaseController {

	String menuUrl = "bgRole.do"; // 菜单地址(权限用)
	@Resource(name = "bgMenuService")
	private BgMenuService bgMenuService;
	@Resource(name = "bgRoleService")
	private BgRoleService bgRoleService;

	/**
	 * 权限(增删改查)
	 */
	@RequestMapping(value = "/qx")
	public ModelAndView qx() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String msg = pd.getString("msg");
			if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
				bgRoleService.updateQx(msg, pd);
			}
			mv.setViewName("save_result");
			mv.addObject("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * K权限
	 */
	@RequestMapping(value = "/kfqx")
	public ModelAndView kfqx() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String msg = pd.getString("msg");
			if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
				bgRoleService.updateKFQx(msg, pd);
			}
			mv.setViewName("save_result");
			mv.addObject("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * c权限
	 */
	@RequestMapping(value = "/gysqxc")
	public ModelAndView gysqxc() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			String msg = pd.getString("msg");
			if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
				bgRoleService.gysqxc(msg, pd);
			}
			mv.setViewName("save_result");
			mv.addObject("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 列表
	 */
	@RequestMapping
	public ModelAndView list(BgPage bgPage) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();

		String roleId = pd.getString("roleId");
		if (roleId == null || "".equals(roleId)) {
			pd.put("roleId", "1");
		}
		List<BgRole> roleList = bgRoleService.listAllTopRoles(); // 列出所有部门
		List<BgRole> roleList_z = bgRoleService.listAllRolesByPId(pd); // 列出此部门的所有下级

		List<PageData> kefuqxlist = bgRoleService.listAllkefu(pd); // 管理权限列表
		List<PageData> gysqxlist = bgRoleService.listAllGysQX(pd); // 用户权限列表
		pd = bgRoleService.findObjectById(pd); // 取得点击部门
		mv.addObject("pd", pd);
		mv.addObject("kefuqxlist", kefuqxlist);
		mv.addObject("gysqxlist", gysqxlist);
		mv.addObject("roleList", roleList);
		mv.addObject("roleList_z", roleList_z);
		mv.setViewName("system/bgRole/role_list");
		mv.addObject(Const.SESSION_BG_QX, this.getHC()); // 按钮权限

		return mv;
	}

	/**
	 * 新增页面
	 */
	@RequestMapping(value = "/toAdd")
	public ModelAndView toAdd(BgPage bgPage) {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			mv.setViewName("system/bgRole/role_add");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 保存新增信息
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView add() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();

			String parent_id = pd.getString("PARENT_ID"); // 父类角色id
			pd.put("roleId", parent_id);
			if ("0".equals(parent_id)) {
				pd.put("RIGHTS", "");
			} else {
				String rights = bgRoleService.findObjectById(pd).getString("RIGHTS");
				pd.put("RIGHTS", (null == rights) ? "" : rights);
			}

			pd.put("QX_ID", "");

			String UUID = this.get32UUID();

			pd.put("GL_ID", UUID);
			pd.put("FX_QX", 0); // 发信权限
			pd.put("FW_QX", 0); // 服务权限
			pd.put("QX1", 0); // 操作权限
			pd.put("QX2", 0); // 产品权限
			pd.put("QX3", 0); // 预留权限
			pd.put("QX4", 0); // 预留权限
			if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
				bgRoleService.saveKeFu(pd);
			}// 保存到K权限表

			pd.put("U_ID", UUID);
			pd.put("C1", 0); // 每日发信数量
			pd.put("C2", 0);
			pd.put("C3", 0);
			pd.put("C4", 0);
			pd.put("Q1", 0); // 权限1
			pd.put("Q2", 0); // 权限2
			pd.put("Q3", 0);
			pd.put("Q4", 0);
			if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
				bgRoleService.saveGYSQX(pd);
			}// 保存到G权限表
			pd.put("QX_ID", UUID);

			pd.put("roleId", UUID);
			pd.put("ADD_QX", "0");
			pd.put("DEL_QX", "0");
			pd.put("EDIT_QX", "0");
			pd.put("CHA_QX", "0");
			if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
				bgRoleService.addRole(pd);
			}
			mv.addObject("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			mv.addObject("msg", "failed");
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 请求编辑
	 */
	@RequestMapping(value = "/toEdit")
	public ModelAndView toEdit(String roleId) throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			pd.put("roleId", roleId);
			pd = bgRoleService.findObjectById(pd);
			mv.setViewName("system/bgRole/role_edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 编辑
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();
			if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
				pd = bgRoleService.editRole(pd);
			}
			mv.addObject("msg", "success");
		} catch (Exception e) {
			logger.error(e.toString(), e);
			mv.addObject("msg", "failed");
		}
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 请求角色菜单授权页面
	 */
	@RequestMapping(value = "/auth")
	public String auth(@RequestParam String roleId, Model model) throws Exception {

		try {
			List<BgMenu> menuList = bgMenuService.listAllMenuInRank();
			BgRole bgRole = bgRoleService.getRoleById(roleId);
			String roleRights = bgRole.getPermissions();
			if (Tools.notEmpty(roleRights)) {
				for (BgMenu bgMenu : menuList) {
					bgMenu.setHasMenu(RightsHelper.testRights(roleRights, bgMenu.getMenuId()));
					if (bgMenu.isHasMenu()) {
						List<BgMenu> subMenuList = bgMenu.getSubMenu();
						for (BgMenu sub : subMenuList) {
							sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMenuId()));
						}
					}
				}
			}
			JSONArray arr = JSONArray.fromObject(menuList);
			String json = arr.toString();
			json = json.replaceAll("menuId", "id").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
			model.addAttribute("zTreeNodes", json);
			model.addAttribute("roleId", roleId);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

		return "authorization";
	}

	/**
	 * 请求角色按钮授权页面
	 */
	@RequestMapping(value = "/button")
	public ModelAndView button(@RequestParam String roleId, @RequestParam String msg, Model model) throws Exception {
		ModelAndView mv = this.getModelAndView();
		try {
			List<BgMenu> menuList = bgMenuService.listAllMenuInRank();
			BgRole bgRole = bgRoleService.getRoleById(roleId);

			String roleRights = "";
			if ("add_qx".equals(msg)) {
				roleRights = bgRole.getAddPermission();
			} else if ("del_qx".equals(msg)) {
				roleRights = bgRole.getDelPermission();
			} else if ("edit_qx".equals(msg)) {
				roleRights = bgRole.getEditPermission();
			} else if ("cha_qx".equals(msg)) {
				roleRights = bgRole.getFindPermission();
			}

			if (Tools.notEmpty(roleRights)) {
				for (BgMenu bgMenu : menuList) {
					bgMenu.setHasMenu(RightsHelper.testRights(roleRights, bgMenu.getMenuId()));
					if (bgMenu.isHasMenu()) {
						List<BgMenu> subMenuList = bgMenu.getSubMenu();
						for (BgMenu sub : subMenuList) {
							sub.setHasMenu(RightsHelper.testRights(roleRights, sub.getMenuId()));
						}
					}
				}
			}
			JSONArray arr = JSONArray.fromObject(menuList);
			String json = arr.toString();
			// System.out.println(json);
			json = json.replaceAll("menuId", "id").replaceAll("menuName", "name").replaceAll("subMenu", "nodes").replaceAll("hasMenu", "checked");
			mv.addObject("zTreeNodes", json);
			mv.addObject("roleId", roleId);
			mv.addObject("msg", msg);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		mv.setViewName("system/bgRole/role_button");
		return mv;
	}

	/**
	 * 保存角色菜单权限
	 */
	@RequestMapping(value = "/auth/save")
	public void saveAuth(@RequestParam String roleId, @RequestParam String menuIds, PrintWriter out) throws Exception {
		PageData pd = new PageData();
		try {
			if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
				if (null != menuIds && !"".equals(menuIds.trim())) {
					BigInteger rights = RightsHelper.sumRights(Tools.str2StrArray(menuIds));
					BgRole bgRole = bgRoleService.getRoleById(roleId);
					bgRole.setPermissions(rights.toString());
					bgRoleService.updateRoleRights(bgRole);
					pd.put("rights", rights.toString());
				} else {
					BgRole bgRole = new BgRole();
					bgRole.setPermissions("");
					bgRole.setRoleId(roleId);
					bgRoleService.updateRoleRights(bgRole);
					pd.put("rights", "");
				}

				pd.put("roleId", roleId);
				bgRoleService.setAllRights(pd);
			}
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	/**
	 * 保存角色按钮权限
	 */
	@RequestMapping(value = "/roleButton/save")
	public void orleButton(@RequestParam String roleId, @RequestParam String menuIds, @RequestParam String msg, PrintWriter out) throws Exception {
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
				if (null != menuIds && !"".equals(menuIds.trim())) {
					BigInteger rights = RightsHelper.sumRights(Tools.str2StrArray(menuIds));
					pd.put("value", rights.toString());
				} else {
					pd.put("value", "");
				}
				pd.put("roleId", roleId);
				bgRoleService.updateQx(msg, pd);
			}
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
	}

	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public Object deleteRole(@RequestParam String roleId) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		String errInfo = "";
		try {
			if (Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
				pd.put("roleId", roleId);
				List<BgRole> roleList_z = bgRoleService.listAllRolesByPId(pd); // 列出此部门的所有下级
				if (roleList_z.size() > 0) {
					errInfo = "false";
				} else {

					List<PageData> userlist = bgRoleService.listAllUByRid(pd);
					List<PageData> appuserlist = bgRoleService.listAllAppUByRid(pd);
					if (userlist.size() > 0 || appuserlist.size() > 0) {
						errInfo = "false2";
					} else {
						bgRoleService.deleteRoleById(roleId);
						bgRoleService.deleteKeFuById(roleId);
						bgRoleService.deleteGById(roleId);
						errInfo = "success";
					}
				}
			}
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}

	/* ===============================权限================================== */
	public Map<String, String> getHC() {
		Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>) session.getAttribute(Const.SESSION_BG_QX);
	}
	/* ===============================权限================================== */

}
