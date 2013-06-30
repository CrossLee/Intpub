/*
 * Created on 2012-6-4
 *
 * ModulePartAction.java
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
package com.withiter.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.withiter.dto.ModuleDTO;
import com.withiter.dto.PageDTO;
import com.withiter.dto.PartDTO;
import com.withiter.service.ModulePartService;
import com.withiter.service.ModuleService;
import com.withiter.service.PartService;
import com.withiter.util.ConstantUtil;

public class ModulePartAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5403749591162203017L;

	private static final Log log = LogFactory.getLog(ModulePartAction.class);

	private ModuleService moduleService;

	private PartService partService;

	private ModulePartService modulePartService;

	private List<PartDTO> needToAddParts;

	private String modulePartState;

	private String returnType;

	private String selectedModuleCode;

	private ModuleDTO selectedModule;
	
	private String selectedPartCode;
	
	private PartDTO selectedPart;
	
	private List<ModuleDTO> addedModules;
	
	private List<ModuleDTO> needToAddModules;
	
	private ModuleDTO queryModuleCondition;

	private PartDTO queryPartCondition;
	
	private List<String> addPartsList;

	private List<String> deletePartsList;
	
	private List<String> addModulesList;
	
	private List<String> deleteModulesList;
	
	private PageDTO pageDTO;
	
	private int pageNo;
	
	public String queryModulesByCondition() {
		log.debug("Enter the method addModule()");

		try {
			if (null != queryModuleCondition) {
				List<Integer> status = new ArrayList<Integer>();
				if (0 == queryModuleCondition.getStatus()) {
					status.add(1);
					status.add(2);
				}
				else
				{
					status.add(queryModuleCondition.getStatus());
				}
				
				pageDTO = moduleService.queryModulesByConditionPagging(queryModuleCondition,status,pageNo);
				//modules = moduleService.queryModulesByCondition(module);
				modulePartState = "success";
			}

		} catch (Exception e) {
			modulePartState = "error";
			e.printStackTrace();
			return ERROR;
		}

		return returnType;
	}
	
	public String queryPartsByCondition()
	{
		try {
			if (null != queryPartCondition) {
				List<Integer> status = new ArrayList<Integer>();
				if (0 == queryPartCondition.getStatus()) {
					status.add(1);
					status.add(2);
				}
				else
				{
					status.add(queryPartCondition.getStatus());
				}
				
				pageDTO = partService.queryPartsByConditionPagging(queryPartCondition, status, pageNo);
				//modules = moduleService.queryModulesByCondition(module);
				modulePartState = "success";
			}

		} catch (Exception e) {
			modulePartState = "error";
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String queryPartsByModuleCode()
	{
		
		try {
			selectedModule = moduleService.queryModuleByModuleCode(selectedModuleCode);
			List<PartDTO> parts = partService.queryPartsModuleCode(selectedModuleCode);
			if(null != selectedModule)
			{
				selectedModule.setParts(parts);
			}
			List<Integer> status = new ArrayList<Integer>();
			status.add(ConstantUtil.Status.STATE_DISPLAY);
			status.add(ConstantUtil.Status.STATE_HIDDEN);
			needToAddParts = partService.queryPartsByNotInModuleCode(selectedModuleCode, status);
			
			modulePartState = "success";
		} catch (Exception e) {
			modulePartState = "error";
			e.printStackTrace();
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String addPartsToModule()
	{
		try {
			if (null != addPartsList && !addPartsList.isEmpty()) {
				
				modulePartService.updateModulePartsByModuleCode(selectedModuleCode,addPartsList);
				
			}
			queryModuleCondition = new ModuleDTO();
			List<Integer> status = new ArrayList<Integer>();
			if (0 == queryModuleCondition.getStatus()) {
				status.add(1);
				status.add(2);
			}
			else
			{
				status.add(queryModuleCondition.getStatus());
			}
			
			pageDTO = moduleService.queryModulesByConditionPagging(queryModuleCondition,status,pageNo);
			modulePartState = "success";
		} catch (Exception e) {
			modulePartState = "error";
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String queryModulesByPartCode()
	{
		try {
			selectedPart = partService.queryPartById(selectedPartCode);
			addedModules = moduleService.queryModulesByPartCode(selectedPartCode);
			
			List<Integer> status = new ArrayList<Integer>();
			status.add(ConstantUtil.Status.STATE_DISPLAY);
			status.add(ConstantUtil.Status.STATE_HIDDEN);
			needToAddModules = moduleService.queryModulesByNotInPartCode(selectedPartCode, status);
			
			modulePartState = "success";
		} catch (Exception e) {
			modulePartState = "error";
			e.printStackTrace();
			return ERROR;
		}
		
		
		return SUCCESS;
	}
	
	public String addModulesToPart()
	{
		try {
			if (null != addModulesList && !addModulesList.isEmpty()) {
				
				modulePartService.updatePartModulesByPartCode(selectedPartCode,addModulesList);
				
			}
			queryPartCondition = new PartDTO();
			List<Integer> status = new ArrayList<Integer>();
			if (0 == queryPartCondition.getStatus()) {
				status.add(1);
				status.add(2);
			}
			else
			{
				status.add(queryPartCondition.getStatus());
			}
			
			pageDTO = partService.queryPartsByConditionPagging(queryPartCondition,status,pageNo);
			modulePartState = "success";
		} catch (Exception e) {
			modulePartState = "error";
			e.printStackTrace();
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String getModulePartState() {
		return modulePartState;
	}

	public void setModulePartState(String modulePartState) {
		this.modulePartState = modulePartState;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}

	public PartService getPartService() {
		return partService;
	}

	public void setPartService(PartService partService) {
		this.partService = partService;
	}

	public List<ModuleDTO> getNeedToAddModules() {
		return needToAddModules;
	}

	public void setNeedToAddModules(List<ModuleDTO> needToAddModules) {
		this.needToAddModules = needToAddModules;
	}

	public ModulePartService getModulePartService() {
		return modulePartService;
	}

	public void setModulePartService(ModulePartService modulePartService) {
		this.modulePartService = modulePartService;
	}

	public List<PartDTO> getNeedToAddParts() {
		return needToAddParts;
	}

	public void setNeedToAddParts(List<PartDTO> needToAddParts) {
		this.needToAddParts = needToAddParts;
	}

	public ModuleDTO getSelectedModule() {
		return selectedModule;
	}

	public void setSelectedModule(ModuleDTO selectedModule) {
		this.selectedModule = selectedModule;
	}

	public ModuleDTO getQueryModuleCondition() {
		return queryModuleCondition;
	}

	public void setQueryModuleCondition(ModuleDTO queryModuleCondition) {
		this.queryModuleCondition = queryModuleCondition;
	}

	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getSelectedModuleCode() {
		return selectedModuleCode;
	}

	public void setSelectedModuleCode(String selectedModuleCode) {
		this.selectedModuleCode = selectedModuleCode;
	}

	public String getSelectedPartCode() {
		return selectedPartCode;
	}

	public void setSelectedPartCode(String selectedPartCode) {
		this.selectedPartCode = selectedPartCode;
	}

	public PartDTO getSelectedPart() {
		return selectedPart;
	}

	public void setSelectedPart(PartDTO selectedPart) {
		this.selectedPart = selectedPart;
	}

	public PartDTO getQueryPartCondition() {
		return queryPartCondition;
	}

	public void setQueryPartCondition(PartDTO queryPartCondition) {
		this.queryPartCondition = queryPartCondition;
	}

	public List<String> getAddPartsList() {
		return addPartsList;
	}

	public void setAddPartsList(List<String> addPartsList) {
		this.addPartsList = addPartsList;
	}

	public List<String> getDeletePartsList() {
		return deletePartsList;
	}

	public void setDeletePartsList(List<String> deletePartsList) {
		this.deletePartsList = deletePartsList;
	}

	public List<String> getAddModulesList() {
		return addModulesList;
	}

	public void setAddModulesList(List<String> addModulesList) {
		this.addModulesList = addModulesList;
	}

	public List<String> getDeleteModulesList() {
		return deleteModulesList;
	}

	public void setDeleteModulesList(List<String> deleteModulesList) {
		this.deleteModulesList = deleteModulesList;
	}

	public List<ModuleDTO> getAddedModules() {
		return addedModules;
	}

	public void setAddedModules(List<ModuleDTO> addedModules) {
		this.addedModules = addedModules;
	}
	
}
