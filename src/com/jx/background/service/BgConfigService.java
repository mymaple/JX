package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.background.entity.BgConfig;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;

@Service("bgConfigService")
public class BgConfigService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 根据configType 获取配置
	 * @return BgConfig
	 * @throws Exception
	 */
	public BgConfig findByConfigType(String configType) throws Exception {
		return (BgConfig) dao.findForObject("BgConfigMapper.findByConfigType", configType);
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param BgConfig bgConfig
	 * @return 主键 id
	 * @throws Exception
	 */
	public int add(BgConfig bgConfig) throws Exception {
		return (int)dao.add("BgConfigMapper.add", bgConfig);
	}
	
	/**
	 * 新增
	 * @param PageData pd
	 * @return 主键 id
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("BgConfigMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param BgConfig bgConfig
	 * @throws Exception
	 */
	public void edit(BgConfig bgConfig) throws Exception {
		dao.edit("BgConfigMapper.edit", bgConfig);
	}

	/**
	 * 修改 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("BgConfigMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param int id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("BgConfigMapper.deleteById", id);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("BgConfigMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String ids) throws Exception {
		dao.delete("BgConfigMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param int id
	 * @return BgConfig
	 * @throws Exception
	 */
	public BgConfig findById(int id) throws Exception {
		return (BgConfig) dao.findForObject("BgConfigMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param int id
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("BgConfigMapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("BgConfigMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<BgConfig> listAllByPd(PageData pd) throws Exception {
		return (List<BgConfig>) dao.findForList("BgConfigMapper.listAllByPd", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllPd(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("BgConfigMapper.listAllPd", bgPage);
	}
	
	/****************************common * end***********************************/
}