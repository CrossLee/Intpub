/*
 * Created on 2012-5-7
 *
 * ModuleAction.java
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
package com.withiter.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.withiter.dto.ModuleDTO;
import com.withiter.dto.PageDTO;
import com.withiter.service.ModuleService;
import com.withiter.util.ConstantUtil;

public class ModuleAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8677219866448751318L;

	private static final Log log = LogFactory.getLog(ModuleAction.class);

	private ModuleService moduleService;

	private ModuleDTO module;
	
	private ModuleDTO moduleDetail;

	private List<ModuleDTO> modules;

	private String moduleState;

	private String selectedModuleCode;
	
	private String returnType;

	private PageDTO pageDTO;
	
	private int pageNo;
	
	private ModuleDTO queryCondition;
	
	public String addModule() {
		log.debug("Enter the method addModule()");

		try {
			moduleService.addModule(module);
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		module = null;
		moduleState = "success";
		return "moduleAddObj";
	}

	public String queryModuleByModuleCode() {
		try {
			module = moduleService.queryModuleByModuleCode(selectedModuleCode);
			moduleState = SUCCESS;
		} catch (Exception e) {
			moduleState = ERROR;
			return ERROR;
		}

		return SUCCESS;
	}

	public String queryModuleDetail()
	{
		try {
			moduleDetail = moduleService.queryModuleByModuleCode(selectedModuleCode);
			moduleState = SUCCESS;
		} catch (Exception e) {
			moduleState = ERROR;
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String queryModulesByCondition() {
		log.debug("Enter the method addModule()");

		try {
			if (null != queryCondition) {
				List<Integer> status = new ArrayList<Integer>();
				if (0 == queryCondition.getStatus()) {
					status.add(1);
					status.add(2);
				}
				else
				{
					status.add(queryCondition.getStatus());
				}
				
				pageDTO = moduleService.queryModulesByConditionPagging(queryCondition,status,pageNo);
				//modules = moduleService.queryModulesByCondition(module);
				moduleState = "success";
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return returnType;
	}

	public String deleteModule()
	{
		
		try {
			
			moduleService.deleteModuleByModuleCode(selectedModuleCode);
			List<Integer> status = new ArrayList<Integer>();
			if(0 == module.getStatus())
			{
				status.add(ConstantUtil.Status.STATE_DISPLAY);
				status.add(ConstantUtil.Status.STATE_HIDDEN);
			}
			else
			{
				status.add(module.getStatus());
			}
			pageDTO = moduleService.queryModulesByConditionPagging(module, status, pageNo);
			moduleState = SUCCESS;
		} catch (Exception e) {
			moduleState = ERROR;
			return ERROR;
		}
		return SUCCESS;
		
	}
	
	public String queryAllModules() {
//		log.debug("Enter the method addModule()");
//
//		try {
//			
//			List<Integer> status = new ArrayList<Integer>();
//			status.add(1);
//			status.add(2);
//			pageDTO = moduleService.queryAllModulesPagging(status,pageNo);
//			//modules = moduleService.queryAllModules();
//			// if (null == page) {
//			// getPager("1", "first", modules.size());
//			// }
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		moduleState = "success";
		return this.returnType;
	}

	public String updateModule() {
		log.debug("Enter the method updateModule()");
		try {
			ModuleDTO updateModule = moduleService.queryModuleByModuleCode(moduleDetail.getModuleCode());

			if (null != moduleDetail) {
				updateModule.setModuleName(moduleDetail.getModuleName());
				updateModule.setDescription(moduleDetail.getDescription());
				updateModule.setDisplayLen(moduleDetail.getDisplayLen());
				if (moduleDetail.getStatus() != 0) {
					updateModule.setStatus(moduleDetail.getStatus());
				}
				updateModule.setAmount(moduleDetail.getAmount());
			}

			moduleService.updateModuleByModuleCode(updateModule);
			ModuleDTO condition = new ModuleDTO();
			List<Integer> status = new ArrayList<Integer>();
			status.add(ConstantUtil.Status.STATE_DISPLAY);
			status.add(ConstantUtil.Status.STATE_HIDDEN);
			pageDTO = moduleService.queryModulesByConditionPagging(condition, status, pageNo);
			module = null;
			moduleState = "success";
		} catch (Exception e) {
			moduleState = ERROR;
			return ERROR;
		}

		return SUCCESS;
	}

	public String queryDetailForUpdate()
	{
		try {
			moduleDetail = moduleService.queryModuleByModuleCode(selectedModuleCode);
			moduleState = SUCCESS;
		} catch (Exception e) {
			moduleState = ERROR;
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public String queryModulesForUpdate()
	{
		try {
			List<Integer> status = new ArrayList<Integer>();
			status.add(ConstantUtil.Status.STATE_DISPLAY);
			status.add(ConstantUtil.Status.STATE_HIDDEN);
			modules = moduleService.queryModulesByCondition(module,status);
			module = null;
		} catch (Exception e) {
			return ERROR;
		}
		
		return SUCCESS;
	}
	
	public ModuleDTO getModule() {
		return module;
	}

	public void setModule(ModuleDTO module) {
		this.module = module;
	}

	public ModuleDTO getModuleDetail() {
		return moduleDetail;
	}

	public void setModuleDetail(ModuleDTO moduleDetail) {
		this.moduleDetail = moduleDetail;
	}

	public String getModuleState() {
		return moduleState;
	}

	public void setModuleState(String moduleState) {
		this.moduleState = moduleState;
	}

	public List<ModuleDTO> getModules() {
		return modules;
	}

	public void setModules(List<ModuleDTO> modules) {
		this.modules = modules;
	}

	public String getSelectedModuleCode() {
		return selectedModuleCode;
	}

	public void setSelectedModuleCode(String selectedModuleCode) {
		this.selectedModuleCode = selectedModuleCode;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
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

	public ModuleDTO getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(ModuleDTO queryCondition) {
		this.queryCondition = queryCondition;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public ModuleService getModuleService() {
		return moduleService;
	}

	public void setModuleService(ModuleService moduleService) {
		this.moduleService = moduleService;
	}
}
