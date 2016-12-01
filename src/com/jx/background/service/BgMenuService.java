package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.entity.BgMenu;
import com.jx.system.config.DaoSupport;
import com.jx.system.config.PageData;

@Service("bgMenuService")
public class BgMenuService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	// ==================================================================
	
	/**
	 * 新增菜单
	 * @param bgMenu
	 * @throws Exception
	 */
	public void addMenu(BgMenu bgMenu) throws Exception {
		dao.add("BgMenuMapper.addMenu", bgMenu);
	}
	
	/**
	 * 根据menuId删除菜单和二级子菜单
	 * @param menuId
	 * @throws Exception
	 */
	public void deleteMenuAndSubMenuById(String menuId) throws Exception {
		dao.delete("BgMenuMapper.deleteMenuAndSubMenuById", menuId);
	}
	
	/**
	 * 编辑菜单
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData editMenu(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgMenuMapper.editMenu", pd);
	}
	
	/**
	 * 修改菜单图标menuIcon (顶部菜单)
	 */
	public PageData changeMenuIcon(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgMenuMapper.changeMenuIcon", pd);
	}

	/**
	 * 更新子菜单类型菜单 menuType
	 */
	public PageData changeMenuType(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgMenuMapper.changeMenuType", pd);
	}
	
	/**
	 * 根据menuId获取菜单
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findByMenuId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgMenuMapper.findByMenuId", pd);

	}

	/**
	 * 获取所有顶级菜单
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listAllTopMenu() throws Exception {
		return (List<BgMenu>) dao.findForList("BgMenuMapper.listAllTopMenu", null);
	}
	
	/**
	 * 根据parentId 获取所有直接子菜单 
	 * @param parentId
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listSubMenuByParentId(String parentId) throws Exception {
		return (List<BgMenu>) dao.findForList("BgMenuMapper.listSubMenuByParentId", parentId);
	}
	
	/**
	 * 获取所有菜单(已分级)
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listAllMenuInRank() throws Exception {
		List<BgMenu> rl = this.listAllTopMenu();
		for (BgMenu bgMenu : rl) {
			List<BgMenu> subBgMenuList = this.listSubMenuByParentId(bgMenu.getMenuId());
			bgMenu.setSubBgMenuList(subBgMenuList);
		}
		return rl;
	}

	public List<BgMenu> listAllSubMenu() throws Exception {
		return (List<BgMenu>) dao.findForList("BgMenuMapper.listAllSubMenu", null);

	}


	/**
	 * 获取maxMenuId
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int getMaxMenuId(PageData pd) throws Exception {
		return (int) dao.findForObject("BgMenuMapper.getMaxMenuId", pd);
	}

}
