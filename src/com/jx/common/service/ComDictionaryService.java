package com.jx.common.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.common.config.BgPage;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.common.entity.ComDictionary;

@Service("bgDictionaryService")
public class ComDictionaryService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param comDictionary
	 * @return
	 * @throws Exception
	 */
	public int add(ComDictionary comDictionary) throws Exception {
		return (int)dao.add("ComDictionaryMapper.add", comDictionary);
	}
	
	/**
	 * 新增
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("ComDictionaryMapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param comDictionary
	 * @throws Exception
	 */
	public void edit(ComDictionary comDictionary) throws Exception {
		dao.edit("ComDictionaryMapper.edit", comDictionary);
	}

	/**
	 * 修改 
	 * @param pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("ComDictionaryMapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("ComDictionaryMapper.deleteById", id);
	}

	/**
	 * 通过id获取(类)数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ComDictionary findById(int id) throws Exception {
		return (ComDictionary) dao.findForObject("ComDictionaryMapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("ComDictionaryMapper.findPdById", id);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<ComDictionary> listAll() throws Exception {
		return (List<ComDictionary>) dao.findForList("ComDictionaryMapper.listAll", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param bgPage
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllPd(BgPage bgPage) throws Exception {
		return (List<PageData>) dao.findForList("ComDictionaryMapper.listAllPd", bgPage);
	}
	
	/****************************common * end***********************************/
	
	/****************************custom * start**********************************/

	// 查询总数
	public PageData findCount(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ComDictionaryMapper.findCount", pd);
	}

	// 查询某编码
	public PageData findBmCount(PageData pd) throws Exception {
		return (PageData) dao.findForObject("ComDictionaryMapper.findBmCount", pd);
	}

	// 列出同一父类id下的数据
	public List<PageData> dictlistPage(BgPage page) throws Exception {
		return (List<PageData>) dao.findForList("ComDictionaryMapper.dictlistPage", page);

	}
	
	/****************************custom * end**********************************/

}
