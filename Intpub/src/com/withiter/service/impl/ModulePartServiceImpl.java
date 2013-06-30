/*
 * Created on 2012-6-4
 *
 * ModulePartServiceImpl.java
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
 * 2012-6-4       Administrator                    1.0         New
 * ---------------------------------------------------------------------------------
 */
package com.withiter.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.withiter.dao.ModulePartDao;
import com.withiter.dto.ModulePartDTO;
import com.withiter.service.ModulePartService;

public class ModulePartServiceImpl implements ModulePartService {

	private ModulePartDao modulePartDao;

	@Override
	public void addModuleParts(List<ModulePartDTO> moduleParts)
			throws Exception {
		
		if (null!=moduleParts && !moduleParts.isEmpty()) {
			for (ModulePartDTO modulePartDTO : moduleParts) {
				modulePartDao.addModulePart(modulePartDTO);
			}
			
		}
		
	}
	
	@Override
	public void deleteModuleParts(List<ModulePartDTO> moduleParts)
			throws Exception {
		
		if (null!=moduleParts && !moduleParts.isEmpty()) {
			for (ModulePartDTO modulePartDTO : moduleParts) {
				modulePartDao.deleteModulePart(modulePartDTO);
			}
			
		}
		
	}
	
	@Override
	public List<ModulePartDTO> queryModulePartsByModuleCode(
			String selectedModuleCode) throws Exception {
	
		ModulePartDTO condition = new ModulePartDTO();
		condition.setModuleCode(selectedModuleCode);
		
		List<ModulePartDTO> moduleParts = modulePartDao.queryModulePartsByConditon(condition);
		
		return moduleParts;
	}
	
	@Override
	public void updateModulePartsByModuleCode(String selectedModuleCode,
			List<String> addPartsList) throws Exception {

		ModulePartDTO condition = new ModulePartDTO();
		condition.setModuleCode(selectedModuleCode);
		
		List<ModulePartDTO> moduleParts = modulePartDao.queryModulePartsByConditon(condition);
		List<String> modulePartCodes = new ArrayList<String>();
		if (null != moduleParts && !moduleParts.isEmpty()) {
			
			for (ModulePartDTO modulePartDTO : moduleParts) {
				modulePartCodes.add(modulePartDTO.getPartCode());
			}
		}
		
		List<String> needToAdd = new ArrayList<String>();
		List<String> needToDelete = new ArrayList<String>();
		getNeedToAddAndNeedToDelete(addPartsList,modulePartCodes,needToAdd,needToDelete);
		
		if (!needToAdd.isEmpty()) {
			ModulePartDTO modulePart = null;
			for (String partCode : needToAdd) {
				modulePart = new ModulePartDTO();
				modulePart.setModuleCode(selectedModuleCode);
				modulePart.setPartCode(partCode);
				modulePartDao.addModulePart(modulePart);
			}
		}
		
		if (!needToDelete.isEmpty()) {
			ModulePartDTO modulePart = null;
			for (String partCode : needToDelete) {
				modulePart = new ModulePartDTO();
				modulePart.setModuleCode(selectedModuleCode);
				modulePart.setPartCode(partCode);
				modulePartDao.deleteModulePart(modulePart);
			}
		}
		
	}
	
	@Override
	public void updatePartModulesByPartCode(String selectedPartCode,
			List<String> addModulesList) throws Exception {

		ModulePartDTO condition = new ModulePartDTO();
		condition.setPartCode(selectedPartCode);
		
		List<ModulePartDTO> moduleParts = modulePartDao.queryModulePartsByConditon(condition);
		List<String> modulePartCodes = new ArrayList<String>();
		if (null != moduleParts && !moduleParts.isEmpty()) {
			
			for (ModulePartDTO modulePartDTO : moduleParts) {
				modulePartCodes.add(modulePartDTO.getModuleCode());
			}
		}
		
		List<String> needToAdd = new ArrayList<String>();
		List<String> needToDelete = new ArrayList<String>();
		getNeedToAddAndNeedToDelete(addModulesList,modulePartCodes,needToAdd,needToDelete);
		
		if (!needToAdd.isEmpty()) {
			ModulePartDTO modulePart = null;
			for (String moduleCode : needToAdd) {
				modulePart = new ModulePartDTO();
				modulePart.setModuleCode(moduleCode);
				modulePart.setPartCode(selectedPartCode);
				modulePartDao.addModulePart(modulePart);
			}
		}
		
		if (!needToDelete.isEmpty()) {
			ModulePartDTO modulePart = null;
			for (String moduleCode : needToDelete) {
				modulePart = new ModulePartDTO();
				modulePart.setModuleCode(moduleCode);
				modulePart.setPartCode(selectedPartCode);
				modulePartDao.deleteModulePart(modulePart);
			}
		}
		
	}
	
	private void getNeedToAddAndNeedToDelete(List<String> addPartsList,
			List<String> modulePartsCode, List<String> needToAdd,
			List<String> needToDelete) {
		
		for (int i = 0; i < addPartsList.size(); i++) {
			
			String addModulePartCode = addPartsList.get(i);
			
			boolean flag = false;
			
			for (int j = 0; j < modulePartsCode.size(); j++) {
				
				if(modulePartsCode.get(j).equals(addModulePartCode))
				{
					flag = true;
					break;
				}
			}
			if(flag)
			{
				continue;
			}
			else
			{
				needToAdd.add(addModulePartCode);
			}
			
		}
		
		for (int i = 0; i < modulePartsCode.size(); i++) {
			
			String addModulePartCode = modulePartsCode.get(i);
			
			boolean flag = false;
			
			for (int j = 0; j < addPartsList.size(); j++) {
				
				if(addPartsList.get(j).equals(addModulePartCode))
				{
					flag = true;
					break;
				}
			}
			if(flag)
			{
				continue;
			}
			else
			{
				needToDelete.add(addModulePartCode);
			}
			
		}
		
	}

	public ModulePartDao getModulePartDao() {
		return modulePartDao;
	}

	public void setModulePartDao(ModulePartDao modulePartDao) {
		this.modulePartDao = modulePartDao;
	}

}
