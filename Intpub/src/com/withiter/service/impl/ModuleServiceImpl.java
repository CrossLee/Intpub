/*
 * Created on 2012-5-7
 *
 * ModuleServiceImpl.java
 *
 * Copyright (C) 2012 by Withiter Software & Technology Services (Shanghai) Limited.
 * All rights reserved. Withiter Software & Technology Services (Shanghai) Limited 
 * claims copyright in this computer program as an unpublished work. Claim of copyright 
 * does not imply waiver of other rights.
 *
 * NOTICE OF PROPRIETARY RIGHTS
 *
 * This program is a confidential trade secret and the property of 
 * Withiter Software & Technology Services (Shanghai) Limited. Use, examination, 
 * reproduction, disassembly, decompiling, transfer and/or disclosure to others of 
 * all or any part of this software program are strictly prohibited except by express 
 * written agreement with Withiter Software & Technology Services (Shanghai) Limited.
 */
/*
 * ---------------------------------------------------------------------------------
 * Modification History
 * Date               Author                     Version     Description
 * 2012-5-7       Administrator                    1.0         New
 * ---------------------------------------------------------------------------------
 */
package com.withiter.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.withiter.dao.ModuleDao;
import com.withiter.dto.ModuleDTO;
import com.withiter.dto.PageDTO;
import com.withiter.service.ModuleService;
import com.withiter.util.ConstantUtil;
import com.withiter.util.DateUtil;
import com.withiter.util.SequenceUtil;

public class ModuleServiceImpl implements ModuleService {

	private ModuleDao moduleDao;
	
	public void addModule(ModuleDTO module) throws Exception {
		
		int moduleCode = SequenceUtil.getInstance().getNextKeyValue(ConstantUtil.SequenceName.MOUDLE_SEQUENCE);
		module.setModuleCode("m" + moduleCode);
		module.setCreatedBy("001");
		module.setCreatedDate(DateUtil.getTodayTime());
		module.setUpdatedBy("001");
		module.setUpdatedDate(DateUtil.getTodayTime());
//		module.setStatus(1);
		moduleDao.addModule(module);
	}

	public List<ModuleDTO> queryAllModules() throws Exception {
		// TODO Auto-generated method stub
		ModuleDTO module  = new ModuleDTO();
//		module.setStatus(ConstantUtil.Status.STATE_DISPLAY);
		List<Integer> status = new ArrayList<Integer>();
		status.add(ConstantUtil.Status.STATE_DISPLAY);
		status.add(ConstantUtil.Status.STATE_HIDDEN);
		return moduleDao.queryModulesByCondition(module,status);
	}
	
	@Override
	public PageDTO queryAllModulesPagging(List<Integer> status, int pageNo)
			throws Exception {
		ModuleDTO module  = new ModuleDTO();
		PageDTO pageDTO = moduleDao.queryModulesByConditionPagging(module,status,pageNo);
		return pageDTO;
	}
	

	@Override
	public PageDTO queryModulesByConditionPagging(ModuleDTO module,
			List<Integer> status, int pageNo) throws Exception {
		PageDTO pageDTO = moduleDao.queryModulesByConditionPagging(module,status,pageNo);
		return pageDTO;
	}


	public List<ModuleDTO> queryModulesByCondition(ModuleDTO module, List<Integer> status)
			throws Exception {
		// TODO Auto-generated method stub
		return moduleDao.queryModulesByCondition(module,status);
	}
	
	public ModuleDTO queryModuleByModuleCode(String selectedModuleCode) throws Exception {
		
		ModuleDTO condition = new ModuleDTO();
		condition.setModuleCode(selectedModuleCode);
		//condition.setStatus(ConstantUtil.Status.STATE_DISPLAY);
		List<Integer> status = new ArrayList<Integer>();
		List<ModuleDTO> modules = moduleDao.queryModulesByCondition(condition,status);
		if (null != modules && !modules.isEmpty()) {
			return modules.get(0);
		}
		return null;
	}
	
	public void updateModuleByModuleCode(ModuleDTO updateModule)
			throws Exception {
		
		if (null == updateModule) {
			throw new Exception("The module must not be null when update");
		}
		
		updateModule.setUpdatedBy("0002");
		updateModule.setUpdatedDate(DateUtil.getTodayTime());
		
		moduleDao.updateModuleByModuleCode(updateModule);
		
	}
	
	public void deleteModuleByModuleCode(String selectedModuleCode)
			throws Exception {
		
		ModuleDTO module = new ModuleDTO();
		module.setModuleCode(selectedModuleCode);
		module.setStatus(ConstantUtil.Status.STATE_DELETED);
		module.setUpdatedBy("1002");
		module.setUpdatedDate(DateUtil.getTodayTime());
		moduleDao.updateModuleByModuleCode(module);
		
	}
	
	@Override
	public List<ModuleDTO> queryModulesByPartCode(String selectedPartCode)
			throws Exception {
		
		List<ModuleDTO> modules = moduleDao.queryModulesByPartCode(selectedPartCode);
		
		return modules;
	}
	
	@Override
	public List<ModuleDTO> queryModulesByNotInPartCode(
			String selectedPartCode, List<Integer> status) throws Exception {
		
		List<ModuleDTO> modules = moduleDao.queryModulesByNotInPartCode(selectedPartCode,status);
		
		return modules;
	}

	public ModuleDao getModuleDao() {
		return moduleDao;
	}

	public void setModuleDao(ModuleDao moduleDao) {
		this.moduleDao = moduleDao;
	}
}
