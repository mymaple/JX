package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.entity.BgRole;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;

@Service("bgRoleService")
public class BgRoleService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	// ==================================================================
	
	/**
	 * 添加角色
	 */
	public void addRole(PageData pd) throws Exception {
		dao.findForList("BgRoleMapper.addRole", pd);
	}
	
	/**
	 * 根据roleId 删除角色
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteRoleById(String roleId) throws Exception {
		dao.delete("BgRoleMapper.deleteRoleById", roleId);
	}
	
	/**
	 * 根据roleId 删除K权限表里对应的数据
	 * @param roleId
	 * @throws Exception
	 */
	public void deleteKeFuById(String roleId) throws Exception {
		dao.delete("BgRoleMapper.deleteKeFuById", roleId);
	}

	// 删除G权限表里对应的数据
	public void deleteGById(String roleId) throws Exception {
		dao.delete("BgRoleMapper.deleteGById", roleId);
	}

	
	
	/**
	 * 编辑角色
	 */
	public PageData editRole(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgRoleMapper.editRole", pd);
	}

	/**
	 * 列出二级角色信息
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> listAllSecondRoles() throws Exception {
		return (List<BgRole>) dao.findForList("BgRoleMapper.listAllSecondRoles", null);

	}
	
	public void updateRoleRights(BgRole role) throws Exception {
		dao.edit("BgRoleMapper.updateRoleRights", role);
	}

	/**
	 * 权限(增删改查)
	 */
	public void updateQx(String msg, PageData pd) throws Exception {
		dao.edit("BgRoleMapper." + msg, pd);
	}

	/**
	 * 客服权限
	 */
	public void updateKFQx(String msg, PageData pd) throws Exception {
		dao.edit("BgRoleMapper." + msg, pd);
	}

	/**
	 * 列出会员二级角色信息
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> listAllAppSecondRoles() throws Exception {
		return (List<BgRole>) dao.findForList("BgRoleMapper.listAllAppSecondRoles", null);

	}

	/**
	 * 列出所有顶级角色
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> listAllTopRoles() throws Exception {
		return (List<BgRole>) dao.findForList("BgRoleMapper.listAllTopRoles", null);
	}
	
	/**
	 * 通过当前登录用的roleId获取管理权限数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findGlByRoleId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgRoleMapper.findGlByRoleId", pd);
	}

	/**
	 * 通过当前登录用的角色id获取用户权限数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findYHbyrid(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgRoleMapper.findYHbyrid", pd);
	}

	/**
	 * 列出此角色下的所有用户 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllUByRid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("BgRoleMapper.listAllUByRid", pd);

	}

	/**
	 * 列出此角色下的所有会员 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllAppUByRid(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("BgRoleMapper.listAllAppUByRid", pd);

	}

	/**
	 * 列出此部门的所有下级
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<BgRole> listAllRolesByPId(PageData pd) throws Exception {
		return (List<BgRole>) dao.findForList("BgRoleMapper.listAllRolesByPId", pd);

	}

	/**
	 * 列出K权限表里的数据
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllkefu(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("BgRoleMapper.listAllkefu", pd);
	}

	/**
	 * 列出G权限表里的数据 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllGysQX(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("BgRoleMapper.listAllGysQX", pd);
	}

	

	public BgRole getRoleById(String roleId) throws Exception {
		return (BgRole) dao.findForObject("BgRoleMapper.getRoleById", roleId);

	}

	

	/**
	 * Gc权限
	 */
	public void gysqxc(String msg, PageData pd) throws Exception {
		dao.edit("BgRoleMapper." + msg, pd);
	}

	/**
	 * 给全部子职位加菜单权限
	 */
	public void setAllRights(PageData pd) throws Exception {
		dao.edit("BgRoleMapper.setAllRights", pd);

	}


	/**
	 * 保存客服权限
	 */
	public void saveKeFu(PageData pd) throws Exception {
		dao.findForList("BgRoleMapper.saveKeFu", pd);
	}

	/**
	 * 保存G权限
	 */
	public void saveGYSQX(PageData pd) throws Exception {
		dao.findForList("BgRoleMapper.saveGYSQX", pd);
	}

	/**
	 * 通过id查找
	 */
	public PageData findObjectById(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgRoleMapper.findObjectById", pd);
	}



}
