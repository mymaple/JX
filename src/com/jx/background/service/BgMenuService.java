package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.background.entity.BgMenu;

@Service("bgMenuService")
public class BgMenuService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
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
	 * 获取maxMenuId
	 * @param PageData pd
	 * @return
	 * @throws Exception
	 */
	public int getMaxMenuId(PageData pd) throws Exception {
		return (int) dao.findForObject("BgMenuMapper.getMaxMenuId", pd);
	}
	
	/**
	 * 根据parentId 获取所有直接子菜单 
	 * @param int parentId
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listSubBgMenuByParentId(int parentId) throws Exception {
		return (List<BgMenu>) dao.findForList("BgMenuMapper.listSubBgMenuByParentId", parentId);
	}
	
	/**
	 * 获取所有菜单并填充每个菜单的子菜单列表(菜单管理)(递归处理)
	 * @param int menuId
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listAllBgMenu(int menuId) throws Exception {
		List<BgMenu> bgMenuList = this.listSubBgMenuByParentId(menuId);
		for(BgMenu bgMenu : bgMenuList){
			bgMenu.setMenuUrl("background/menu/toEdit.do?menuId="+bgMenu.getMenuId());
			bgMenu.setSubBgMenuList(this.listAllBgMenu(bgMenu.getMenuId()));
			bgMenu.setTarget("treeFrame");
		}
		return bgMenuList;
	}

	/**
	 * 获取所有菜单并填充每个菜单的子菜单列表(系统菜单列表)(递归处理)
	 * @param int menuId
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listAllMenuInRank(int menuId) throws Exception {
		List<BgMenu> bgMenuList = this.listSubBgMenuByParentId(menuId);
		for(BgMenu bgMenu : bgMenuList){
			bgMenu.setSubBgMenuList(this.listAllMenuInRank(bgMenu.getMenuId()));
			bgMenu.setTarget("treeFrame");
		}
		return bgMenuList;
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgMenu bgMenu
	 * @return 主键 id
	 * @throws Exception
	 */
	public int add(BgMenu bgMenu) throws Exception {
		return (int)dao.add("BgMenuMapper.add", bgMenu);
	}
	
	/**
	 * 新增
	 * @param PageData pd
	 * @return 主键 id
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("BgMenuMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param BgMenu bgMenu
	 * @throws Exception
	 */
	public void edit(BgMenu bgMenu) throws Exception {
		dao.edit("BgMenuMapper.edit", bgMenu);
	}

	/**
	 * 修改 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("BgMenuMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param int id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("BgMenuMapper.deleteById", id);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgMenuMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String ids) throws Exception {
		dao.delete("BgMenuMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param int id
	 * @return BgMenu
	 * @throws Exception
	 */
	public BgMenu findById(int id) throws Exception {
		return (BgMenu) dao.findForObject("BgMenuMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param int id
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("BgMenuMapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgMenuMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgMenu> listAllByPd(PageData pd) throws Exception {
		return (List<BgMenu>) dao.findForList("BgMenuMapper.listAllByPd", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllPd(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgMenuMapper.listAllPd", bgPage);
	}
	
	/****************************common * end***********************************/
}