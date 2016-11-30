package com.jx.background.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.jx.background.entity.BgConfig;
import com.jx.system.config.DaoSupport;

@Service("bgConfigService")
public class BgConfigService {
	
	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	
	// ==================================================================
	
	/**
	 * 根据congifType 获取配置
	 * @return
	 * @throws Exception
	 */
	public BgConfig findConfigByConfigType(String congifType) throws Exception {
		return (BgConfig) dao.findForObject("BgConfigMapper.findConfigByConfigType", congifType);
	}
	
	/**
	 * 获取所有配置
	 * @return
	 * @throws Exception
	 */
	public List<BgConfig> listBgConfig() throws Exception {
		return (List<BgConfig>) dao.findForList("BgConfigMapper.listBgConfig", null);
	}
}
