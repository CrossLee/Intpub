/*
 * Created on 2012-5-8
 *
 * RoleDao.java
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
 * 2012-5-8       CrossLee                    1.0         New
 * ---------------------------------------------------------------------------------
 */
/**
 * 
 */
package com.withiter.dao;

import java.util.List;

import com.withiter.dto.PageDTO;
import com.withiter.dto.RoleDTO;
import com.withiter.dto.RoleMenuDTO;

/**
 * @author CrossLee
 *
 */
public interface RoleDao {

	/**
	 * @param role
	 * @return
	 */
	boolean addRole(RoleDTO role);

	/**
	 * @param role
	 * @return
	 */
	boolean deleteRole(RoleDTO role);

	/**
	 * @param role
	 * @return
	 */
	boolean modifyRole(RoleDTO role);

	/**
	 * @param roleCode
	 * @return
	 */
	RoleDTO queryRole(String roleCode);

	/**
	 * @return
	 */
	List<RoleDTO> queryRoles();

	/**
	 * @param pageNo
	 * @return
	 */
	PageDTO queryRoles(int pageNo);

	/**
	 * @param roleName
	 * @param status
	 * @param pageNo
	 * @return
	 */
	PageDTO queryRoles(String roleName, int status, int pageNo);

	/**
	 * @param roleName
	 * @param pageNo
	 * @return
	 */
	PageDTO queryRoles(String roleName, int pageNo);

	/**
	 * @param roleCode
	 * @return
	 */
	List<RoleMenuDTO> queryMenu(String roleCode);

	/**
	 * @param roleCode
	 * @param menuArray
	 * @return
	 */
	boolean updateRoleMenus(int roleCode, List<RoleMenuDTO> menuArray);

}
