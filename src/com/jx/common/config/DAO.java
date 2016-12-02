package com.jx.common.config;

import java.util.List;

public interface DAO {

	/**
	 * 新增对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object add(String str, Object obj) throws Exception ;
	
	/**
	 * 批量新增
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object batchAdd(String str, List objs) throws Exception ;
	
	/**
	 * 删除对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object delete(String str, Object obj) throws Exception ;
	
	/**
	 * 批量删除对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object batchDelete(String str, List objs) throws Exception ;
	
	/**
	 * 修改对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object edit(String str, Object obj) throws Exception ;
	
	/**
	 * 批量修改
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public void batchEdit(String str, List objs) throws Exception ;


	/**
	 * 查找对象
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForObject(String str, Object obj) throws Exception;

	/**
	 * 查找对象封装成List
	 * @param str
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForList(String str, Object obj) throws Exception;

	/**
	 * 查找对象封装成Map
	 * @param s
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	public Object findForMap(String sql, Object obj, String key, String value) throws Exception;

}
