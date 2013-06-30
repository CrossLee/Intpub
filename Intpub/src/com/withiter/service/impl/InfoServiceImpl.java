/*
 * Created on 2012-6-9
 *
 * InfoServiceImpl.java
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
 * 2012-6-9       Administrator                    1.0         New
 * ---------------------------------------------------------------------------------
 */
package com.withiter.service.impl;

import java.util.Map;

import com.withiter.dao.InfoDao;
import com.withiter.dto.InfoDTO;
import com.withiter.dto.PageDTO;
import com.withiter.service.InfoService;

public class InfoServiceImpl implements InfoService{
	private InfoDao infoDao;

	public InfoDao getInfoDao() {
		return infoDao;
	}

	public void setInfoDao(InfoDao infoDao) {
		this.infoDao = infoDao;
	}

	public boolean addInfo(InfoDTO info) {

		return infoDao.addInfo(info);
	}

	public boolean deleteInfo(int id) {
		
		return infoDao.deleteInfo(id);
	}

	public InfoDTO queryInfo(int id) {

		return infoDao.queryInfo(id);
	}

	public PageDTO queryInfo(String userCode, int status, int pageNo) {

		return infoDao.queryInfo(userCode, status, pageNo);
	}

	public PageDTO queryInfo(String userCode, String partCode, int status, int pageNo) {

		return infoDao.queryInfo(userCode, partCode, status, pageNo);
	}

	public PageDTO queryInfo(String usercode, String partcode, String date, int status, int pageNo) {
		
		return infoDao.queryInfo(usercode, partcode, date, status, pageNo);
	}

	public boolean updateInfo(InfoDTO info) {

		return infoDao.updateInfo(info);
	}

	@SuppressWarnings("unchecked")
	public Map queryPart() {
		
		return infoDao.queryPart();
	}
	
}
