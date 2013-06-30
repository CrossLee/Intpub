/*
 * Created on 2012-5-23
 *
 * PartAction.java
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
 * 2012-5-23       Administrator                    1.0         New
 * ---------------------------------------------------------------------------------
 */
package com.withiter.action;

import java.util.ArrayList;
import java.util.List;

import com.withiter.dto.PageDTO;
import com.withiter.dto.PartDTO;
import com.withiter.service.PartService;

public class PartAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 461872841324990470L;

	private PartService partService;

	private PartDTO part;
	
	private PartDTO queryCondition;

	private String partState;

	private String returnType;

	private List<PartDTO> parts;
	
	private String selectedPartCode;
	
	private PageDTO pageDTO;
	
	private int pageNo;

	public String addPart() {
		try {
			partService.addPart(part);
			partState = "success";
			part = null;
		} catch (Exception e) {
			return ERROR;
		}

		return SUCCESS;
	}

	public String queryAllParts() {

		try {
			parts = partService.queryAllParts();
		} catch (Exception e) {
			return ERROR;
		}

		return returnType;
	}

	public String deletePartByPartCode()
	{
		try {
				partService.deletePartByPartCode(selectedPartCode);
				List<Integer> status = new ArrayList<Integer>();
				if (null != queryCondition) {
				if (0 == queryCondition.getStatus()) {
					status.add(1);
					status.add(2);
				}
				else
				{
					status.add(queryCondition.getStatus());
				}
			}
			
			pageDTO = partService.queryPartsByConditionPagging(queryCondition, status, pageNo);
			partState = "success";
		} catch (Exception e) {
			partState = "error";
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String queryPartDetail()
	{
		try {
				part = partService.queryPartById(selectedPartCode);
		} catch (Exception e) {
			return ERROR;
		}
		return returnType;
	}
	
	public String updatePartByPartCode()
	{
		try {
			PartDTO updatePart = partService.queryPartById(part.getPartCode());
			if (null != part) {
				updatePart.setPartName(part.getPartName());
				updatePart.setBackup(part.getBackup());
				if(0 != part.getStatus())
				{
					updatePart.setStatus(part.getStatus());
				}
				if(0 != part.getSort())
				{
					updatePart.setSort(part.getSort());
				}
				partService.updatePartByPartCode(updatePart);
			}
				List<Integer> status = new ArrayList<Integer>();
				queryCondition = new PartDTO();
				if (0 == queryCondition.getStatus()) {
					status.add(1);
					status.add(2);
				}
				else
				{
					status.add(queryCondition.getStatus());
				}
			
			pageDTO = partService.queryPartsByConditionPagging(queryCondition, status, pageNo);
			partState = "success";
			
		} catch (Exception e) {
			partState = "error";
			return ERROR;
		}
		return SUCCESS;
	}
	
	public String queryPartsByCondition() {

		try {
			
				List<Integer> status = new ArrayList<Integer>();
				if (null != queryCondition) {
				if (0 == queryCondition.getStatus()) {
					status.add(1);
					status.add(2);
				}
				else
				{
					status.add(queryCondition.getStatus());
				}
			}
			
			pageDTO = partService.queryPartsByConditionPagging(queryCondition, status, pageNo);
			partState = "success";
		} catch (Exception e) {
			partState = "error";
			return ERROR;
		}

		return returnType;

	}

	public String getPartState() {
		return partState;
	}

	public void setPartState(String partState) {
		this.partState = partState;
	}

	public PartDTO getPart() {
		return part;
	}

	public void setPart(PartDTO part) {
		this.part = part;
	}

	public String getReturnType() {
		return returnType;
	}

	public void setReturnType(String returnType) {
		this.returnType = returnType;
	}

	public List<PartDTO> getParts() {
		return parts;
	}

	public void setParts(List<PartDTO> parts) {
		this.parts = parts;
	}

	public String getSelectedPartCode() {
		return selectedPartCode;
	}

	public void setSelectedPartCode(String selectedPartCode) {
		this.selectedPartCode = selectedPartCode;
	}

	public PartDTO getQueryCondition() {
		return queryCondition;
	}

	public void setQueryCondition(PartDTO queryCondition) {
		this.queryCondition = queryCondition;
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

	public PartService getPartService() {
		return partService;
	}

	public void setPartService(PartService partService) {
		this.partService = partService;
	}
}
