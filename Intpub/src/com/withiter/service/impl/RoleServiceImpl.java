/*
 * Created on 2012-5-8
 *
 * RoleServiceImpl.java
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
package com.withiter.service.impl;

import java.util.List;

import com.withiter.dao.RoleDao;
import com.withiter.dto.PageDTO;
import com.withiter.dto.RoleDTO;
import com.withiter.dto.RoleMenuDTO;
import com.withiter.service.RoleService;

/**
 * @author CrossLee
 *
 */
public class RoleServiceImpl implements RoleService {

	private RoleDao roleDao;
	
	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#addRole(com.withiter.dto.RoleDTO)
	 */
	@Override
	public boolean addRole(RoleDTO role) {
		return roleDao.addRole(role);
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#deleteRole(java.lang.String)
	 */
	@Override
	public boolean deleteRole(RoleDTO role) {
		// TODO Auto-generated method stub
		return roleDao.deleteRole(role);
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#queryOneRole(java.lang.String)
	 */
	@Override
	public RoleDTO queryOneRole(String roleId) {
		return roleDao.queryRole(roleId);
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#queryRoles()
	 */
	@Override
	public List<RoleDTO> queryRoles() {
		return roleDao.queryRoles();
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#updateRole(com.withiter.dto.RoleDTO)
	 */
	@Override
	public boolean updateRole(RoleDTO role) {
		// TODO Auto-generated method stub
		return false;
	}

	public RoleDao getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#modifyRole(com.withiter.dto.RoleDTO)
	 */
	@Override
	public boolean modifyRole(RoleDTO role) {
		// TODO Auto-generated method stub
		return roleDao.modifyRole(role);
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#queryRoles(int)
	 */
	@Override
	public PageDTO queryRoles(int pageNo) {
		// TODO Auto-generated method stub
		return roleDao.queryRoles(pageNo);
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#queryRoles(java.lang.String, int, int)
	 */
	@Override
	public PageDTO queryRoles(String roleName, int status, int pageNo) {
		// TODO Auto-generated method stub
		return roleDao.queryRoles(roleName, status, pageNo);
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#queryRoles(java.lang.String, int)
	 */
	@Override
	public PageDTO queryRoles(String roleName, int pageNo) {
		// TODO Auto-generated method stub
		return roleDao.queryRoles(roleName, pageNo);
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#queryRoleMenu(java.lang.String)
	 */
	@Override
	public List<RoleMenuDTO> queryRoleMenu(String roleCode) {
		// TODO Auto-generated method stub
		return roleDao.queryMenu(roleCode);
	}

	/* (non-Javadoc)
	 * @see com.withiter.service.RoleService#updateRoleMenus(int, java.lang.String[])
	 */
	@Override
	public boolean updateRoleMenus(int roleCode, List<RoleMenuDTO> menuArray) {
		// TODO Auto-generated method stub
		return roleDao.updateRoleMenus(roleCode, menuArray);
	}

}
