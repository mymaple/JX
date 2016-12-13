package com.jx.${objectModuleNL}.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.${controlModuleNL}.config.${controlModuleEU}Page;
import com.jx.common.config.DaoSupport;
import com.jx.common.config.PageData;
import com.jx.objectModuleNL.entity.${objectModuleEU}${objectNameU};

@Service("${objectModuleEL}${objectNameU}Service")
public class ${objectModuleEU}${objectNameU}Service {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/****************************common * start***********************************/
	
	/**
	 * 新增 
	 * @param ${objectModuleEL}${objectNameU}
	 * @return
	 * @throws Exception
	 */
	public int add(${objectModuleEU}${objectNameU} ${objectModuleEL}${objectNameU}) throws Exception {
		return (int)dao.add("${objectModuleEU}${objectNameU}Mapper.add", ${objectModuleEL}${objectNameU});
	}
	
	/**
	 * 新增
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public int addByPd(PageData pd) throws Exception {
		return (int)dao.add("${objectModuleEU}${objectNameU}Mapper.addByPd", pd);
	}
	
	/**
	 * 修改 
	 * @param ${objectModuleEL}${objectNameU}
	 * @throws Exception
	 */
	public void edit(${objectModuleEU}${objectNameU} ${objectModuleEL}${objectNameU}) throws Exception {
		dao.edit("${objectModuleEU}${objectNameU}Mapper.edit", ${objectModuleEL}${objectNameU});
	}

	/**
	 * 修改 
	 * @param pd
	 * @throws Exception
	 */
	public void editByPd(PageData pd) throws Exception {
		dao.edit("${objectModuleEU}${objectNameU}Mapper.editByPd", pd);
	}
	
	/**
	 * 删除 
	 * @param id
	 * @throws Exception
	 */
	public void deleteById(int id) throws Exception {
		dao.delete("${objectModuleEU}${objectNameU}Mapper.deleteById", id);
	}
	
	/**
	 * 删除 
	 * @param pd
	 * @throws Exception
	 */
	public void deleteByPd(PageData pd) throws Exception {
		dao.delete("${objectModuleEU}${objectNameU}Mapper.deleteByPd", pd);
	}

	/**
	 * 通过id获取(类)数据
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public ${objectModuleEU}${objectNameU} findById(int id) throws Exception {
		return (${objectModuleEU}${objectNameU}) dao.findForObject("${objectModuleEU}${objectNameU}Mapper.findById", id);
	}
	
	/**
	 * 通过id获取(PageData)数据 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public PageData findPdById(int id) throws Exception {
		return (PageData) dao.findForObject("${objectModuleEU}${objectNameU}Mapper.findPdById", id);
	}
	
	/**
	 * 通过pd获取(PageData)数据 
	 * @param pd
	 * @return
	 * @throws Exception
	 */
	public PageData findPdByPd(PageData pd) throws Exception {
		return (PageData) dao.findForObject("${objectModuleEU}${objectNameU}Mapper.findPdByPd", pd);
	}
	
	/**
	 * 获取(类)List数据
	 * @return
	 * @throws Exception
	 */
	public List<${objectModuleEU}${objectNameU}> listAll() throws Exception {
		return (List<${objectModuleEU}${objectNameU}>) dao.findForList("${objectModuleEU}${objectNameU}Mapper.listAll", null);
	}
	
	/**
	 * 获取分页(PageData)List数据
	 * @param ${controlModuleEL}Page
	 * @return
	 * @throws Exception
	 */
	public List<PageData> listAllPd(${controlModuleEU}Page ${controlModuleEL}Page) throws Exception {
		return (List<PageData>) dao.findForList("${objectModuleEU}${objectNameU}Mapper.listAllPd", ${controlModuleEL}Page);
	}
	
	/****************************common * end***********************************/
}