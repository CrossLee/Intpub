/*
 * Created on 2012-5-23
 *
 * PartServiceImpl.java
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
package com.withiter.service.impl;

import java.util.List;

import com.withiter.dao.PartDao;
import com.withiter.dto.PageDTO;
import com.withiter.dto.PartDTO;
import com.withiter.service.PartService;
import com.withiter.util.ConstantUtil;
import com.withiter.util.DateUtil;
import com.withiter.util.SequenceUtil;

public class PartServiceImpl implements PartService {

	private PartDao partDao;

	public void addPart(PartDTO part) throws Exception {
		
		if (null == part) {
			throw new Exception("The part need to add must not be null");
		}
		
		int partCode = SequenceUtil.getInstance().getNextKeyValue(ConstantUtil.SequenceName.PART_SEQUENCE);
		part.setPartCode("p" + partCode);
		part.setStatus(ConstantUtil.Status.STATE_DISPLAY);
		part.setCreatedBy("001");
		part.setCreatedDate(DateUtil.getTodayTime());
		part.setUpdatedBy("001");
		part.setUpdatedDate(DateUtil.getTodayTime());
		partDao.addPart(part);
		
	}
	
	@Override
	public PartDTO queryPartById(String id) throws Exception {
		
		PartDTO part = new PartDTO();
		part.setPartCode(id);
		List<PartDTO> parts = partDao.queryPartsByCondition(part);
		return parts.get(0);
	}
	
	@Override
	public List<PartDTO> queryPartsByCondition(PartDTO part) throws Exception {
		
		List<PartDTO> parts = partDao.queryPartsByCondition(part);
		return parts;
	}
	
	public PageDTO queryPartsByConditionPagging(PartDTO queryCondition,List<Integer> status,int pageNo) throws Exception
	{
		PageDTO pageDTO = partDao.queryPartsByConditionPagging(queryCondition,status,pageNo);
		return pageDTO;
	}
	
	@Override
	public List<PartDTO> queryAllParts() throws Exception {
		
		PartDTO part = new PartDTO();
		List<PartDTO> parts = partDao.queryPartsByCondition(part);
		return parts;
	}
	
	@Override
	public void deletePartByPartCode(String selectedPartCode) throws Exception {

		PartDTO part = new PartDTO();
		part.setPartCode(selectedPartCode);
		part.setStatus(ConstantUtil.Status.STATE_DELETED);
		part.setUpdatedBy("1002");
		part.setUpdatedDate(DateUtil.getTodayTime());
		partDao.updatePartByPartCode(part);
		
	}
	
	@Override
	public List<PartDTO> queryPartsModuleCode(String selectedModuleCode)
			throws Exception {
		
		List<PartDTO> parts = partDao.queryPartsModuleCode(selectedModuleCode);
		return parts;
	}

	@Override
	public void updatePartByPartCode(PartDTO part) throws Exception {

		part.setUpdatedBy("1002");
		part.setUpdatedDate(DateUtil.getTodayTime());
		partDao.updatePartByPartCode(part);
		
	}
	
	@Override
	public List<PartDTO> queryPartsByNotInModuleCode(String selectedModuleCode,
			List<Integer> status) throws Exception {
		List<PartDTO> parts = partDao.queryPartsByNotInModuleCode(selectedModuleCode,status);
		return parts;
	}
	
	@Override
	public PageDTO queryPartsModuleCodePagging(String selectedModuleCode,
			int pageNo) throws Exception {
		PageDTO pageDTO = partDao.queryPartsModuleCodePagging(selectedModuleCode,pageNo);
		return pageDTO;
	}
	
	@Override
	public PageDTO queryPartsByNotInModuleCodePagging(
			String selectedModuleCode, List<Integer> status, int pageAddPartsNo)
			throws Exception {
		PageDTO pageDTO = partDao.queryPartsModuleCodePagging(selectedModuleCode,status,pageAddPartsNo);
		return pageDTO;
	}
	
	public PartDao getPartDao() {
		return partDao;
	}

	public void setPartDao(PartDao partDao) {
		this.partDao = partDao;
	}

}
