/*
 * Created on 2012-5-8
 *
 * ModuleDaoImpl.java
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
 * 2012-5-8       Administrator                    1.0         New
 * ---------------------------------------------------------------------------------
 */
package com.withiter.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.withiter.dao.ModuleDao;
import com.withiter.dto.ModuleDTO;
import com.withiter.dto.PageDTO;
import com.withiter.util.ConnectionPool;
import com.withiter.util.FetchData;

public class ModuleDaoImpl implements ModuleDao {

	
	public void addModule(ModuleDTO module) throws Exception {
		Connection conn = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			
			String sql = "insert into intpub_module(moduleCode,moduleName,status,profile,amount,displayLen," +
			"createby,createdate,updateby,updatedate) values(?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, module.getModuleCode());
			ps.setString(2, module.getModuleName());
			ps.setInt(3, module.getStatus());
			ps.setString(4, module.getDescription());
			ps.setInt(5, module.getAmount());
			ps.setInt(6, module.getDisplayLen());
			ps.setString(7, module.getCreatedBy());
			ps.setString(8, module.getCreatedDate());
			ps.setString(9, module.getUpdatedBy());
			ps.setString(10, module.getUpdatedDate());
			ps.execute();
			//conn.commit();
			
		} catch (SQLException e) {
			throw new Exception("Failed to add module",e);
		}
		finally
		{
			conn.close(); 
		}
		
	}

	@Override
	public List<ModuleDTO> queryAllModules() throws Exception{
	
		Connection conn = null;
		List<ModuleDTO> modules = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			
			String sql = "select m.moduleCode,m.moduleName,m.status,m.profile,m.amount,m.displayLen,m.createby,m.createdate,m.updateby,m.updatedate from intpub_module m order by m.moduleCode;";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			modules = new ArrayList<ModuleDTO>();
			if (null != rs) {
				ModuleDTO module = null;
				while(rs.next())
				{
					module = new ModuleDTO();
					module.setModuleCode(rs.getString("moduleCode"));
					module.setModuleName(rs.getString("moduleName"));
					module.setStatus(rs.getInt("status"));
					module.setDescription(rs.getString("profile"));
					module.setAmount(rs.getInt("amount"));
					module.setDisplayLen(rs.getInt("displayLen"));
					module.setCreatedBy(rs.getString("createby"));
					module.setCreatedDate(rs.getString("createdate"));
					module.setUpdatedBy(rs.getString("updateby"));
					module.setUpdatedDate(rs.getString("updatedate"));
					modules.add(module);
				}
				
			}
			
			//conn.commit();
			
		} catch (SQLException e) {
			throw new Exception("Failed to query all module",e);
		}
		finally
		{
			conn.close(); 
		}
		
		return modules;
	}

	@Override
	public List<ModuleDTO> queryModulesByCondition(ModuleDTO condition, List<Integer> status)
			throws Exception {
	
		Connection conn = null;
		List<ModuleDTO> modules = null;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			
			StringBuffer sql = new StringBuffer();
			sql.append("select m.moduleCode,m.moduleName,m.status,m.profile,m.amount,m.displayLen,m.createby,m.createdate,m.updateby,m.updatedate from intpub_module m where 1=1 ");
			
			if (null != condition.getModuleCode() && !"".equals(condition.getModuleCode())) {
				sql.append("and m.moduleCode = '" + condition.getModuleCode() + "' ");
			}
			
			if (null != condition.getModuleName() && !"".equals(condition.getModuleName())) {
				sql.append("and m.moduleName like '%" + condition.getModuleName() + "%' ");
			}
			if (null != condition.getDescription() && !"".equals(condition.getDescription())) {
				sql.append("and m.profile like '%" + condition.getDescription() + "%' ");
			}
			
			if (null != status && !status.isEmpty()) {
				sql.append(" and m.status in (");
				
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
			
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql.toString());
			modules = new ArrayList<ModuleDTO>();
			if (null != rs) {
				ModuleDTO module = null;
				while(rs.next())
				{
					module = new ModuleDTO();
					module.setModuleCode(rs.getString("moduleCode"));
					module.setModuleName(rs.getString("moduleName"));
					module.setStatus(rs.getInt("status"));
					module.setDescription(rs.getString("profile"));
					module.setAmount(rs.getInt("amount"));
					module.setDisplayLen(rs.getInt("displayLen"));
					module.setCreatedBy(rs.getString("createby"));
					module.setCreatedDate(rs.getString("createdate"));
					module.setUpdatedBy(rs.getString("updateby"));
					module.setUpdatedDate(rs.getString("updatedate"));
					modules.add(module);
				}
				
			}
			
			//conn.commit();
			
		} catch (SQLException e) {
			throw new Exception("Failed to query all module",e);
		}
		finally
		{
			conn.close(); 
		}
		
		return modules;
	}

	@Override
	public PageDTO queryModulesByConditionPagging(ModuleDTO condition,
			List<Integer> status, int pageNo) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select m.moduleCode,m.moduleName,m.status,m.profile,m.amount,m.displayLen,m.createby,m.createdate,m.updateby,m.updatedate from intpub_module m where 1=1 ");
		
		if (null != condition.getModuleCode() && !"".equals(condition.getModuleCode())) {
			sql.append("and m.moduleCode = '" + condition.getModuleCode() + "' ");
		}
		
		if (null != condition.getModuleName() && !"".equals(condition.getModuleName())) {
			sql.append("and m.moduleName like '%" + condition.getModuleName() + "%' ");
		}
		if (null != condition.getDescription() && !"".equals(condition.getDescription())) {
			sql.append("and m.profile like '%" + condition.getDescription() + "%' ");
		}
		
		if (null != status && !status.isEmpty()) {
			sql.append(" and m.status in (");
			
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
		
		return findPageDTO(pageNo,10,sql,"com.withiter.dto.ModuleDTO");
	}
	
	private PageDTO findPageDTO(int pageNo, int pageSize, StringBuffer sql,
			String objPath) {
		
		List totalList = FetchData.getListData(sql.toString());
		PageDTO pageDTO = new PageDTO();
		List<ModuleDTO> modules = new ArrayList<ModuleDTO>();;
		int pages = (totalList.size() + pageSize - 1)/pageSize;
		
		if (pageNo>pageSize) {
			pageNo=pages;
		}
		
		if (pageNo!=0) {
			sql.append(" limit " + (pageNo-1)*pageSize + "," + pageSize);
			//list1 = FetchData.queryObject(sql.toString(), objPath);
			List<List<String>> moduleData = FetchData.getListData(sql.toString());
			ModuleDTO module = null;
			if(null != moduleData && !moduleData.isEmpty())
			{
				for(List<String> list : moduleData)
				{
					module = new ModuleDTO();
					module.setModuleCode(list.get(0));
					module.setModuleName(list.get(1));
					module.setStatus(Integer.valueOf(list.get(2)));
					module.setDescription(list.get(3));
					module.setAmount(Integer.valueOf(list.get(4)));
					module.setDisplayLen(Integer.valueOf(list.get(5)));
					module.setCreatedBy(list.get(6));
					module.setCreatedDate(list.get(7));
					module.setUpdatedBy(list.get(8));
					module.setUpdatedDate(list.get(9));
					modules.add(module);
				}
			}
		}
		
		System.out.println(sql.toString());
		int totalRecords=totalList.size();
		
		pageDTO.setTotalRecords(totalRecords);
		pageDTO.setList(modules);
		pageDTO.setPageNo(pageNo);
		pageDTO.setPageSize(10);
		
		return pageDTO;
	}

	@Override
	public void updateModuleByModuleCode(ModuleDTO updateModule)
			throws Exception {

		StringBuffer sql = new StringBuffer();
		sql.append("update intpub_module set moduleCode = '" + updateModule.getModuleCode() + "' ");
		if (null != updateModule.getModuleName()) {
			sql.append(", moduleName = '" + updateModule.getModuleName() + "'");
		}
		if (null != updateModule.getDescription()) {
			sql.append(", profile = '" + updateModule.getDescription() + "'");
		}
		if (0 != updateModule.getAmount()) {
			sql.append(", amount = " + updateModule.getAmount());
		}
		if (0 != updateModule.getDisplayLen()) {
			sql.append(", displayLen = " + updateModule.getDisplayLen());
		}
		if (0 != updateModule.getStatus()) {
			sql.append(", status = " + updateModule.getStatus());
		}
		if (null != updateModule.getUpdatedBy()) {
			sql.append(", updateby = '" + updateModule.getUpdatedBy() + "'");
		}
		if (null != updateModule.getUpdatedDate()) {
			sql.append(", updatedate = '" + updateModule.getUpdatedDate() + "'");
		}
		
		sql.append(" where moduleCode = '" + updateModule.getModuleCode() + "' ");
		
		FetchData.updateData(sql.toString());
		
	}

	@Override
	public List<ModuleDTO> queryModulesByPartCode(String selectedPartCode)
			throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select im.* from intpub_module im, intpub_module_part imp where 1=1 ");
		if (null != selectedPartCode && !"".equals(selectedPartCode)) {
			sql.append(" and im.moduleCode=imp.moduleCode and imp.partCode = '").append(selectedPartCode).append("' ");
		}
		
		List<ModuleDTO> modules = new ArrayList<ModuleDTO>();
		List<List<String>> partData = FetchData.getListData(sql.toString());
		ModuleDTO module = null;
		for(List<String> list : partData)
		{
			module = new ModuleDTO();
			module.setModuleCode(list.get(0));
			module.setModuleName(list.get(1));
			module.setStatus(Integer.valueOf(list.get(2)));
			module.setDescription(list.get(3));
			module.setAmount(Integer.valueOf(list.get(4)));
			module.setDisplayLen(Integer.valueOf(list.get(5)));
			module.setCreatedBy(list.get(6));
			module.setCreatedDate(list.get(7));
			module.setUpdatedBy(list.get(8));
			module.setUpdatedDate(list.get(9));
			modules.add(module);
		}
		return modules;
	}

	@Override
	public List<ModuleDTO> queryModulesByNotInPartCode(String selectedPartCode,List<Integer> status)
			throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select im.* from intpub_module im where im.moduleCode not in (select moduleCode from intpub_module_part imp where imp.partCode ='" + selectedPartCode + "')");
		
		if (null != status && !status.isEmpty()) {
			sql.append(" and im.status in (");
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
		
		List<ModuleDTO> modules = new ArrayList<ModuleDTO>();
		List<List<String>> partData = FetchData.getListData(sql.toString());
		ModuleDTO module = null;
		for(List<String> list : partData)
		{
			module = new ModuleDTO();
			module.setModuleCode(list.get(0));
			module.setModuleName(list.get(1));
			module.setStatus(Integer.valueOf(list.get(2)));
			module.setDescription(list.get(3));
			module.setAmount(Integer.valueOf(list.get(4)));
			module.setDisplayLen(Integer.valueOf(list.get(5)));
			module.setCreatedBy(list.get(6));
			module.setCreatedDate(list.get(7));
			module.setUpdatedBy(list.get(8));
			module.setUpdatedDate(list.get(9));
			modules.add(module);
		}
		return modules;
	}

}
