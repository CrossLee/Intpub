/*
 * Created on 2012-5-17
 *
 * UserDao.java
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
 * 2012-5-17           Sam                          1.0         New
 * ---------------------------------------------------------------------------------
 */
package com.withiter.dao;


import java.util.Map;

import com.withiter.dto.InfoDTO;
import com.withiter.dto.PageDTO;

public interface InfoDao {
	public boolean addInfo(InfoDTO info);

	public boolean updateInfo(InfoDTO info);
	
	public boolean deleteInfo(int id);
	
	public InfoDTO queryInfo(int id);
	
	public PageDTO queryInfo(String userCode,int status,int pageNo);
	
	public PageDTO queryInfo(String userCode,String partCode,int status,int pageNo);
	
	public PageDTO queryInfo(String usercode,String partcode,String date,int status,int pageNo);
	
	
	@SuppressWarnings("unchecked")
	public Map queryPart();
}
