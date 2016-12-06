package com.jx.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComDict;

@Service("comDictService")
public class ComDictService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param comDict
	 * @return
	 * @throws Exception
	 */
	public int add(ComDict comDict) throws Exception {
		return (int)dao.add("ComDictMapper.add", comDict);
	}
	
	/**
	 * 新增
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("ComDictMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param comDict
	 * @throws Exception
	 */
	public void edit(ComDict comDict) throws Exception {
		dao.edit("ComDictMapper.edit", comDict);
	}

	/**
	 * 修改 
	 * @param pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("ComDictMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("ComDictMapper.deleteById", id);
	}
	
	/**
	 * 删除 
	 * @param pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("ComDictMapper.deleteByPd", pd);
	}

	/**
	 * 通过id获取(类)数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ComDict findById(int id) throws Exception {
		return (ComDict) dao.findForObject("ComDictMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("ComDictMapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ComDictMapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComDict> listAll() throws Exception {
		return (List<ComDict>) dao.findForList("ComDictMapper.listAll", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllPd(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComDictMapper.listAllPd", bgPage);
	}
	
	/****************************common * end***********************************/
	
	/****************************custom * start**********************************/

	// 查询总数
	public PageData findCount(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ComDictMapper.findCount", pd);
	}

	// 查询某编码
	public PageData hasEncodeByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ComDictMapper.hasEncodeByPd", pd);
	}

	// 列出同一父类id下的数据
	public List<PageData> dictlistPage(BgPage page) throws Exception {
		return (List<PageData>) dao.findForList("ComDictMapper.dictlistPage", page);
	}
	
	//获取字典参数列表
	public List<ComDict> listParamByEncode(String encode) throws Exception {
		return (List<ComDict>) dao.findForList("ComDictMapper.listParamByEncode", encode);
	}
	
	/****************************custom * end**********************************/

}
