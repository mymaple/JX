package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.background.entity.BgUser;
import com.jx.system.config.DaoSupport;
import com.jx.system.config.PageData;

@Service("bgUserService")
public class BgUserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;

	// ======================================================================================

	/**
	 * 通过userId获取数据
	 */
	public PageData findByUserId(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgUserXMapper.findByUserId", pd);
	}

	/**
	 * 通过userName获取数据
	 */
	public PageData findByUserName(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgUserXMapper.findByUserName", pd);
	}

	/**
	 * 通过email获取数据
	 */
	public PageData findByEmail(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgUserXMapper.findByEmail", pd);
	}

	/**
	 * 通过userNumber获取数据
	 */
	public PageData findByUserNumber(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgUserXMapper.findByUserNumber", pd);
	}

	/**
	 * 保存用户
	 */
	public void saveUser(PageData pd) throws Exception {
		dao.save("BgUserXMapper.saveUser", pd);
	}

	/**
	 * 修改用户
	 */
	public void editUser(PageData pd) throws Exception {
		dao.update("BgUserXMapper.editUser", pd);
	}

	/**
	 * 换皮肤
	 */
	public void changeSkin(PageData pd) throws Exception {
		dao.update("BgUserXMapper.changeSkin", pd);
	}

	/**
	 * 删除用户
	 */
	public void deleteUser(PageData pd) throws Exception {
		dao.delete("BgUserXMapper.deleteUser", pd);
	}

	/**
	 * 批量删除用户
	 */
	public void deleteMoreUser(String[] arrayUserIds) throws Exception {
		dao.delete("BgUserXMapper.deleteMoreUser", arrayUserIds);
	}

	/**
	 * 用户列表(用户组)
	 */
	public List<PageData> listUser(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgUserXMapper.listUser", bgPage);
	}

	/**
	 * 用户列表(全部)
	 */
	public List<PageData> listAllUser(PageData pd) throws Exception {
		return (List<PageData>) dao.findForList("BgUserXMapper.listAllUser", pd);
	}

	/**
	 * 用户列表(供应商用户)
	 */
	/*public List<PageData> listGPdPageUser(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgUserXMapper.userGlistPage", bgPage);
	}
*/
	/**
	 * 保存用户IP
	 */
	public void saveLoginIp(PageData pd) throws Exception {
		dao.update("BgUserXMapper.saveLoginIp", pd);
	}

	/**
	 * 登录判断
	 */
	public PageData checkUserByNameAndPwd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgUserXMapper.checkUserByNameAndPwd", pd);
	}

	/**
	 * 更新登录时间
	 */
	public void updateLastLogin(PageData pd) throws Exception {
		dao.update("BgUserXMapper.updateLastLogin", pd);
	}

	/**
	 * 通过userId获取数据
	 */
	public BgUser getUserAndRoleById(String userId) throws Exception {
		return (BgUser) dao.findForObject("UserMapper.getUserAndRoleById", userId);
	}

}
