/*
 * Created on 2012-5-7
 *
 * ModuleService.java
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
package com.withiter.service;

import java.util.List;

import com.withiter.dto.ModuleDTO;
import com.withiter.dto.PageDTO;

public interface ModuleService {

	void addModule(ModuleDTO module) throws Exception;

	List<ModuleDTO> queryAllModules() throws Exception;

	List<ModuleDTO> queryModulesByCondition(ModuleDTO module, List<Integer> status) throws Exception;

	ModuleDTO queryModuleByModuleCode(String selectedModuleCode) throws Exception;

	void updateModuleByModuleCode(ModuleDTO updateModule) throws Exception;

	void deleteModuleByModuleCode(String selectedModuleCode) throws Exception;

	PageDTO queryAllModulesPagging(List<Integer> status, int pageNo) throws Exception;

	PageDTO queryModulesByConditionPagging(ModuleDTO module,
			List<Integer> status, int pageNo) throws Exception;

	List<ModuleDTO> queryModulesByPartCode(String selectedPartCode) throws Exception;

	List<ModuleDTO> queryModulesByNotInPartCode(String selectedPartCode,
			List<Integer> status) throws Exception;
}
