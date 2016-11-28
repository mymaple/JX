package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.entity.BgUser;
import com.jx.system.config.BgPage;
import com.jx.system.config.DaoSupport;
import com.jx.system.config.PageData;

@Service("bgUserService")
public class BgUserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	// ==========================================================================

	/**
	 * 新增用户
	 */
	public void addUser(PageData pd) throws Exception {
		dao.add("BgUserMapper.addUser", pd);
	}
	
	/**
	 * 删除用户
	 */
	public void deleteUser(PageData pd) throws Exception {
		dao.delete("BgUserMapper.deleteUser", pd);
	}

	/**
	 * 批量删除用户
	 */
	public void deleteMoreUser(String[] arrayUserIds) throws Exception {
		dao.delete("BgUserMapper.deleteMoreUser", arrayUserIds);
	}
	
	/**
	 * 修改用户
	 */
	public void editUser(PageData pd) throws Exception {
		dao.edit("BgUserMapper.editUser", pd);
	}

	/**
	 * 更新皮肤
	 */
	public void changeSkin(PageData pd) throws Exception {
		dao.edit("BgUserMapper.changeSkin", pd);
	}
	
	/**
	 * 更新用户IP
	 */
	public void changeLoginIpInfo(BgUser bgUser) throws Exception {
		dao.edit("BgUserMapper.changeLoginIpInfo", bgUser);
	}
	

	/**
	 * 更新用户IP
	 */
	public void changeLoginIp(PageData pd) throws Exception {
		dao.edit("BgUserMapper.changeLoginIp", pd);
	}
	
	/**
	 * 更新登录时间
	 */
	public void changeLastLogin(PageData pd) throws Exception {
		dao.edit("BgUserMapper.changeLastLogin", pd);
	}
	
	/**
	 * 通过userId获取数据
	 */
	public BgUser findByUserId(PageData pd) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.findByUserId", pd);
	}

	/**
	 * 通过userName获取数据
	 */
	public BgUser findByUserName(PageData pd) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.findByUserName", pd);
	}

	/**
	 * 通过email获取数据
	 */
	public BgUser findByEmail(PageData pd) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.findByEmail", pd);
	}

	/**
	 * 通过userNumber获取数据
	 */
	public BgUser findByUserNumber(PageData pd) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.findByUserNumber", pd);
	}
	

	/**
	 * 用户列表(用户组)
	 */
	public List<BgUser> listUser(BgPage bgPage) throws Exception {
		return (List<BgUser>) dao.findForList("BgUserMapper.listUser", bgPage);
	}

	/**
	 * 用户列表(全部)
	 */
	public List<BgUser> listAllUser(PageData pd) throws Exception {
		return (List<BgUser>) dao.findForList("BgUserMapper.listAllUser", pd);
	}

	/**
	 * 用户列表(供应商用户)
	 */
	/*public List<PageData> listGPdPageUser(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgUserMapper.userGlistPage", bgPage);
	}
*/


	/**
	 * 登录判断
	 */
	public BgUser checkUserByNameAndPwd(PageData pd) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.checkUserByNameAndPwd", pd);
	}


	/**
	 * 通过userId获取用户角色
	 */
	public BgUser getUserRoleById(String userId) throws Exception {
		return (BgUser) dao.findForObject("BgUserMapper.getUserRoleById", userId);
	}


}
