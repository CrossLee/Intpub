/*
 * Created on 2012-5-23
 *
 * PartDaoImpl.java
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
package com.withiter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.withiter.dao.PartDao;
import com.withiter.dto.PageDTO;
import com.withiter.dto.PartDTO;
import com.withiter.util.FetchData;

public class PartDaoImpl implements PartDao {

	public void addPart(PartDTO part) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into intpub_part(partCode,partName,status,backup,sort,createby,createdate,updateby,updatedate) values(");
		sql.append("'").append(part.getPartCode()).append("',");
		sql.append("'").append(part.getPartName()).append("',");
		sql.append(part.getStatus()).append(",");
		sql.append("'").append(part.getBackup()).append("',");
		sql.append(part.getSort()).append(",");
		sql.append("'").append(part.getCreatedBy()).append("',");
		sql.append("'").append(part.getCreatedDate()).append("',");
		sql.append("'").append(part.getUpdatedBy()).append("',");
		sql.append("'").append(part.getUpdatedDate()).append("'");
		sql.append(")");
		FetchData.updateData(sql.toString());
		
	}

	@Override
	public List<PartDTO> queryPartsByCondition(PartDTO condition) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select * from intpub_part ip where 1=1 ");
		if (null != condition.getPartCode() && !"".equals(condition.getPartCode())) {
			sql.append(" and ip.partCode='").append(condition.getPartCode()).append("'");
		}
		
		if (null != condition.getPartName() && !"".equals(condition.getPartName())) {
			sql.append(" and ip.partName like '%").append(condition.getPartName()).append("%'");
		}
		
		if (0 != condition.getStatus()) {
			sql.append(" and ip.status = ").append(condition.getStatus());
		}
		
		if (null != condition.getBackup() && !"".equals(condition.getBackup())) {
			sql.append(" and ip.backup like '%").append(condition.getBackup()).append("%'");
		}
		
		if (0 != condition.getSort()) {
			sql.append(" and ip.backup like =").append(condition.getSort());
		}
		
		List<PartDTO> parts = new ArrayList<PartDTO>();
		List<List<String>> partData = FetchData.getListData(sql.toString());
		PartDTO part = null;
		for(List<String> list : partData)
		{
			part = new PartDTO();
			part.setPartCode(list.get(0));
			part.setPartName(String.valueOf(list.get(1)));
			part.setStatus(Integer.valueOf(list.get(2)));
			part.setBackup(String.valueOf(list.get(3)));
			part.setSort(Integer.valueOf(list.get(4)));
			part.setCreatedBy(String.valueOf(list.get(5)));
			part.setCreatedDate(String.valueOf(list.get(6)));
			part.setUpdatedBy(String.valueOf(list.get(7)));
			part.setUpdatedDate(String.valueOf(list.get(8)));
			parts.add(part);
		}
		
		return parts;
	}

	@Override
	public void updatePartByPartCode(PartDTO part) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("update intpub_part set partCode = '" + part.getPartCode() + "'");
		if (null != part.getPartName() && !"".equals(part.getPartName())) {
			sql.append(", partName = '" + part.getPartName() + "'");
		}
		if (0 != part.getSort()) {
			sql.append(", sort = '" + part.getSort() + "'");
		}
		if (null != part.getBackup() && !"".equals(part.getBackup())) {
			sql.append(", backup = " + part.getBackup());
		}
		if (0 != part.getStatus()) {
			sql.append(", status = " + part.getStatus());
		}
		if (null != part.getUpdatedBy() && !"".equals(part.getBackup())) {
			sql.append(", updateby = '" + part.getUpdatedBy() + "'");
		}
		if (null != part.getUpdatedDate() && !"".equals(part.getBackup())) {
			sql.append(", updatedate = '" + part.getUpdatedDate() + "'");
		}
		
		sql.append(" where partCode = '" + part.getPartCode()+ "'");
		
		FetchData.updateData(sql.toString());
		
	}

	@Override
	public List<PartDTO> queryPartsModuleCode(String selectedModuleCode)
			throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select ip.* from intpub_part ip, intpub_module_part imp where 1=1 ");
		if (null != selectedModuleCode && !"".equals(selectedModuleCode)) {
			sql.append(" and ip.partCode=imp.PARTCODE and imp.MODULECODE = '").append(selectedModuleCode).append("'");
		}
		
		List<PartDTO> parts = new ArrayList<PartDTO>();
		List<List<String>> partData = FetchData.getListData(sql.toString());
		PartDTO part = null;
		for(List<String> list : partData)
		{
			part = new PartDTO();
			part.setPartCode(list.get(0));
			part.setPartName(String.valueOf(list.get(1)));
			part.setStatus(Integer.valueOf(list.get(2)));
			part.setBackup(String.valueOf(list.get(3)));
			part.setSort(Integer.valueOf(list.get(4)));
			part.setCreatedBy(String.valueOf(list.get(5)));
			part.setCreatedDate(String.valueOf(list.get(6)));
			part.setUpdatedBy(String.valueOf(list.get(7)));
			part.setUpdatedDate(String.valueOf(list.get(8)));
			parts.add(part);
		}
		
		return parts;
	}

	@Override
	public PageDTO queryPartsModuleCodePagging(String selectedModuleCode,
			int pageNo) throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("select ip.* from intpub_part ip, intpub_module_part imp where 1=1 ");
		if (null != selectedModuleCode && !"".equals(selectedModuleCode)) {
			sql.append(" and ip.partCode=imp.PARTCODE and imp.MODULECODE = '").append(selectedModuleCode).append("'");
		}
		return findPageDTO(pageNo, 10, sql, "com.withiter.dto.PartDTO");
	}
	
	@Override
	public List<PartDTO> queryPartsByNotInModuleCode(
			String selectedModuleCode, List<Integer> status) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select ip.* from intpub_part ip where ip.partCode not in (select partCode from intpub_module_part imp where imp.ModuleCode ='" + selectedModuleCode + "' )");
		if (null != status && !status.isEmpty()) {
			sql.append(" and ip.status in (");
			for (int i = 0; i<status.size();i++) {
				if (i == status.size()-1) {
					sql.append(status.get(i));
				}
				else
				{
					sql.append(status.get(i) + ",");
				}
				
			}
			sql.append(")");
		}
		
		List<PartDTO> parts = new ArrayList<PartDTO>();
		List<List<String>> partData = FetchData.getListData(sql.toString());
		PartDTO part = null;
		for(List<String> list : partData)
		{
			part = new PartDTO();
			part.setPartCode(list.get(0));
			part.setPartName(String.valueOf(list.get(1)));
			part.setStatus(Integer.valueOf(list.get(2)));
			part.setBackup(String.valueOf(list.get(3)));
			part.setSort(Integer.valueOf(list.get(4)));
			part.setCreatedBy(String.valueOf(list.get(5)));
			part.setCreatedDate(String.valueOf(list.get(6)));
			part.setUpdatedBy(String.valueOf(list.get(7)));
			part.setUpdatedDate(String.valueOf(list.get(8)));
			parts.add(part);
		}
		
		return parts;
	}

	@Override
	public PageDTO queryPartsModuleCodePagging(String selectedModuleCode,
			List<Integer> status, int pageAddPartsNo) throws Exception {
		StringBuffer sql = new StringBuffer();
		sql.append("select ip.* from intpub_part ip where ip.partCode not in (select partCode from intpub_module_part imp where imp.ModuleCode ='" + selectedModuleCode + "' )");
		if (null != status && !status.isEmpty()) {
			sql.append(" and ip.status in (");
			for (int i = 0; i<status.size();i++) {
				if (i == status.size()-1) {
					sql.append(status.get(i));
				}
				else
				{
					sql.append(status.get(i) + ",");
				}
				
			}
			sql.append(")");
		}
		return findPageDTO(pageAddPartsNo,10,sql,"com.withiter.dto.PartDTO");
	}
	@Override
	public PageDTO queryPartsByConditionPagging(PartDTO queryCondition,
			List<Integer> status, int pageNo) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select ip.partCode,ip.partName,ip.status,ip.backup,ip.sort,ip.createby,ip.createdate,ip.updateby,ip.updatedate from intpub_part ip where 1=1 ");
		if (null != queryCondition.getPartCode() && !"".equals(queryCondition.getPartCode())) {
			sql.append(" and ip.partCode='").append(queryCondition.getPartCode()).append("' ");
		}
		
		if (null != queryCondition.getPartName() && !"".equals(queryCondition.getPartName())) {
			sql.append(" and ip.partName like '%").append(queryCondition.getPartName()).append("%'");
		}
		
		if (null != queryCondition.getBackup() && !"".equals(queryCondition.getBackup())) {
			sql.append(" and ip.backup like '%").append(queryCondition.getBackup()).append("%'");
		}
		
		if (0 != queryCondition.getSort()) {
			sql.append(" and ip.backup like =").append(queryCondition.getSort());
		}
		
		if (null != status && !status.isEmpty()) {
			sql.append(" and ip.status in (");
			
			for (int i = 0; i < status.size(); i++) {
				if (i == status.size()-1) {
					sql.append(status.get(i));
				}
				else
				{
					sql.append(status.get(i) + ",");
				}
			}
			
			sql.append(")");
		}
		
		return findPageDTO(pageNo,10,sql,"com.withiter.dto.PartDTO");
	}

	private PageDTO findPageDTO(int pageNo, int pageSize, StringBuffer sql,
			String objPath) {
		
		List totalList = FetchData.getListData(sql.toString());
		PageDTO pageDTO = new PageDTO();
		List<PartDTO> parts = new ArrayList<PartDTO>();
		int pages = (totalList.size() + pageSize - 1)/pageSize;
		
		if (pageNo>pageSize) {
			pageNo=pages;
		}
		
		if (pageNo!=0) {
			sql.append(" limit " + (pageNo-1)*pageSize + "," + pageSize);
			List<List<String>> partData = FetchData.getListData(sql.toString());
			PartDTO part = null;
			if(null != partData && !partData.isEmpty())
			{
				for (List<String> list : partData) {
					part = new PartDTO();
					part.setPartCode(list.get(0));
					part.setPartName(list.get(1));
					part.setStatus(Integer.valueOf(list.get(2)));
					part.setBackup(list.get(3));
					part.setSort(Integer.valueOf(list.get(4)));
					part.setCreatedBy(list.get(5));
					part.setCreatedDate(list.get(6));
					part.setUpdatedBy(list.get(7));
					part.setUpdatedDate(list.get(8));
					parts.add(part);
				}
			}
			
		}
		
		int totalRecords=totalList.size();
		
		pageDTO.setTotalRecords(totalRecords);
		pageDTO.setList(parts);
		pageDTO.setPageNo(pageNo);
		pageDTO.setPageSize(10);
		
		return pageDTO;
	}
}
