/*
 * Created on 2012-6-4
 *
 * ModulePartDaoImpl.java
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
package com.withiter.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.withiter.dao.ModulePartDao;
import com.withiter.dto.ModulePartDTO;
import com.withiter.util.FetchData;

public class ModulePartDaoImpl implements ModulePartDao{

	@Override
	public void addModulePart(ModulePartDTO modulePartDTO) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into intpub_module_part(moduleCode,partCode) values(");
		sql.append("'" + modulePartDTO.getModuleCode() + "'").append(",");
		sql.append("'" + modulePartDTO.getPartCode() + "'");
		sql.append(")");
		FetchData.updateData(sql.toString());
		
	}

	@Override
	public void deleteModulePart(ModulePartDTO modulePartDTO) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("delete from intpub_module_part where ");
		sql.append("moduleCode = '");
		sql.append(modulePartDTO.getModuleCode()).append("' and ");
		sql.append("partCode = '");
		sql.append(modulePartDTO.getPartCode() + "' ");
		FetchData.updateData(sql.toString());
		
	}

	@Override
	public List<ModulePartDTO> queryModulePartsByConditon(ModulePartDTO conditon) throws Exception {
		
		StringBuffer sql = new StringBuffer();
		sql.append("select imp.moduleCode,imp.partCode from intpub_module_part imp where 1=1 ");
		if(null!=conditon.getModuleCode() && !"".equals(conditon.getModuleCode()))
		{
			sql.append(" and imp.moduleCode = '" + conditon.getModuleCode() + "' " );
		}
		
		if(null!=conditon.getPartCode() && !"".equals(conditon.getPartCode()))
		{
			sql.append(" and imp.partCode = '" + conditon.getPartCode() + "'");
		}
		
		List<ModulePartDTO> moduleParts = new ArrayList<ModulePartDTO>();
		List<List<String>> partData = FetchData.getListData(sql.toString());
		ModulePartDTO modulePart = null;
		for(List<String> list : partData)
		{
			modulePart = new ModulePartDTO();
			modulePart.setModuleCode(list.get(0));
			modulePart.setPartCode(list.get(1));
			moduleParts.add(modulePart);
		}
		return moduleParts;
	}

}
