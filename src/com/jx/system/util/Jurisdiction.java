package com.jx.system.util;

import java.util.List;
import java.util.Map;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.jx.background.entity.BgMenu;
import com.jx.system.config.Const;

/**
 * 权限处理
 * @author:fh
 */
public class Jurisdiction {

	/**
	 * 访问权限及初始化按钮权限(控制按钮的显示)
	 * @param menuUrl 菜单路径
	 * @return
	 */
	public static boolean hasJurisdiction(String menuUrl) {
		// 判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
		/**
		 * 根据点击的菜单的xxx.do去菜单中的URL去匹配，当匹配到了此菜单，判断是否有此菜单的权限，没有的话跳转到404页面 根据按钮权限，授权按钮(当前点的菜单和角色中各按钮的权限匹对)
		 */
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Boolean b = true;
		List<BgMenu> menuList = (List) session.getAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST); // 获取菜单列表

		for (int i = 0; i < menuList.size(); i++) {
			for (int j = 0; j < menuList.get(i).getSubBgMenuList().size(); j++) {
				if (menuList.get(i).getSubBgMenuList().get(j).getMenuUrl().split(".do")[0].equals(menuUrl.split(".do")[0])) {
					if (!menuList.get(i).getSubBgMenuList().get(j).isHasMenu()) { // 判断有无此菜单权限
						return false;
					} else { // 按钮判断
						Map<String, String> map = (Map<String, String>) session.getAttribute(Const.SESSION_BG_QX_STR);// 按钮权限
						map.remove("add");
						map.remove("del");
						map.remove("edit");
						map.remove("cha");
						String menuId = menuList.get(i).getSubBgMenuList().get(j).getMenuId();
						String userName = session.getAttribute(Const.SESSION_BG_USERNAME_STR).toString(); // 获取当前登录者loginname
						Boolean isAdmin = "admin".equals(userName);
						map.put("add", (RightsHelper.testRights(map.get("adds"), menuId)) || isAdmin ? "1" : "0");
						map.put("del", RightsHelper.testRights(map.get("dels"), menuId) || isAdmin ? "1" : "0");
						map.put("edit", RightsHelper.testRights(map.get("edits"), menuId) || isAdmin ? "1" : "0");
						map.put("cha", RightsHelper.testRights(map.get("chas"), menuId) || isAdmin ? "1" : "0");
						session.removeAttribute(Const.SESSION_BG_QX_STR);
						session.setAttribute(Const.SESSION_BG_QX_STR, map); // 重新分配按钮权限
					}
				}
			}
		}
		return true;
	}

	/**
	 * 按钮权限(方法中校验)
	 * @param menuUrl 菜单路径
	 * @param type 类型(add、del、edit、cha)
	 * @return
	 */
	public static boolean buttonJurisdiction(String menuUrl, String type) {
		// 判断是否拥有当前点击菜单的权限（内部过滤,防止通过url进入跳过菜单权限）
		/**
		 * 根据点击的菜单的xxx.do去菜单中的URL去匹配，当匹配到了此菜单，判断是否有此菜单的权限，没有的话跳转到404页面 根据按钮权限，授权按钮(当前点的菜单和角色中各按钮的权限匹对)
		 */
		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		Boolean b = true;
		List<BgMenu> menuList = (List) session.getAttribute(Const.SESSION_BG_ALLMENU_INRANK_LIST); // 获取菜单列表

		for (int i = 0; i < menuList.size(); i++) {
			for (int j = 0; j < menuList.get(i).getSubBgMenuList().size(); j++) {
				if (menuList.get(i).getSubBgMenuList().get(j).getMenuUrl().split(".do")[0].equals(menuUrl.split(".do")[0])) {
					if (!menuList.get(i).getSubBgMenuList().get(j).isHasMenu()) { // 判断有无此菜单权限
						return false;
					} else { // 按钮判断
						Map<String, String> map = (Map<String, String>) session.getAttribute(Const.SESSION_BG_QX_STR);// 按钮权限
						String menuId = menuList.get(i).getSubBgMenuList().get(j).getMenuId();
						String userName = session.getAttribute(Const.SESSION_BG_USERNAME_STR).toString(); // 获取当前登录者loginname
						Boolean isAdmin = "admin".equals(userName);
						if ("add".equals(type)) {
							return ((RightsHelper.testRights(map.get("adds"), menuId)) || isAdmin);
						} else if ("del".equals(type)) {
							return ((RightsHelper.testRights(map.get("dels"), menuId)) || isAdmin);
						} else if ("edit".equals(type)) {
							return ((RightsHelper.testRights(map.get("edits"), menuId)) || isAdmin);
						} else if ("cha".equals(type)) {
							return ((RightsHelper.testRights(map.get("chas"), menuId)) || isAdmin);
						}
					}
				}
			}
		}
		return true;
	}

}
