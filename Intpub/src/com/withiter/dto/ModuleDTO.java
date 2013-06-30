/*
 * Created on 2012-5-7
 *
 * ModuleDTO.java
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
package com.withiter.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 
 * 模块类
 * 
 * @author Administrator
 *
 */
public class ModuleDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8850103905798449715L;
	
	private String moduleCode;
	
	private String moduleName;
	
	private int status;
	
	private String description;
	
	private int amount;
	
	private int displayLen;
	
	private List<PartDTO> parts;
	
	private String createdBy;
	
	private String createdDate;
	
	private String updatedBy;
	
	private String updatedDate;

	public String getModuleCode() {
		return moduleCode;
	}

	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getDisplayLen() {
		return displayLen;
	}

	public void setDisplayLen(int displayLen) {
		this.displayLen = displayLen;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public List<PartDTO> getParts() {
		return parts;
	}

	public void setParts(List<PartDTO> parts) {
		this.parts = parts;
	}

	public static void main(String[] args) {
		ModuleDTO m = new ModuleDTO();
		System.out.println(m.getModuleCode());
	}
	
}
