/*
 * Created on 2012-6-4
 *
 * ModulePartDao.java
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
package com.withiter.dao;

import java.util.List;

import com.withiter.dto.ModulePartDTO;

public interface ModulePartDao {

	void addModulePart(ModulePartDTO modulePartDTO) throws Exception;

	void deleteModulePart(ModulePartDTO modulePartDTO) throws Exception;

	List<ModulePartDTO> queryModulePartsByConditon(ModulePartDTO condition) throws Exception;

}
