package com.jx.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComConfig;

@Service("comConfigService")
public class ComConfigService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	/****************************custom * start***********************************/
	
	/**
	 * 根据configType 获取配置
	 * @return ComConfig
	 * @throws Exception
	 */
	public ComConfig findByConfigType(String configType) throws Exception {
		return (ComConfig) dao.findForObject("ComConfigMapper.findByConfigType", configType);
	}
	
	/****************************custom * end  ***********************************/
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ComConfig comConfig
	 * @return 主键 id
	 * @throws Exception
	 */
	public int add(ComConfig comConfig) throws Exception {
		return (int)dao.add("ComConfigMapper.add", comConfig);
	}
	
	/**
	 * 新增
	 * @param PageData pd
	 * @return 主键 id
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("ComConfigMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param ComConfig comConfig
	 * @throws Exception
	 */
	public void edit(ComConfig comConfig) throws Exception {
		dao.edit("ComConfigMapper.edit", comConfig);
	}

	/**
	 * 修改 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("ComConfigMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param int id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("ComConfigMapper.deleteById", id);
	}
	
	/**
	 * 删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComConfigMapper.deleteByPd", pd);
	}
	
	/**
	 * 批量删除 
	 * @param PageData pd
	 * @throws Exception
	 */
	public void batchDeleteByIds(String ids) throws Exception {
		dao.delete("ComConfigMapper.batchDeleteByIds", ids);
	}

	/**
	 * 通过id获取(类)数据
	 * @param int id
	 * @return ComConfig
	 * @throws Exception
	 */
	public ComConfig findById(int id) throws Exception {
		return (ComConfig) dao.findForObject("ComConfigMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param int id
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("ComConfigMapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param PageData pd
	 * @return PageData
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ComConfigMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComConfig> listAllByPd(PageData pd) throws Exception {
		return (List<ComConfig>) dao.findForList("ComConfigMapper.listAllByPd", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllPd(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComConfigMapper.listAllPd", bgPage);
	}
	
	/****************************common * end***********************************/
}