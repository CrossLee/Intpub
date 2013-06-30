/*
 * Created on 2012-5-8
 *
 * RoleDaoImpl.java
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
package com.withiter.dao.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.withiter.dao.RoleDao;
import com.withiter.dto.PageDTO;
import com.withiter.dto.RoleDTO;
import com.withiter.dto.RoleMenuDTO;
import com.withiter.util.ConnectionPool;
import com.withiter.util.FetchData;

/**
 * @author gl65293
 */
public class RoleDaoImpl implements RoleDao {

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.withiter.dao.RoleDao#addRole(com.withiter.dto.RoleDTO)
	 */
	@Override
	public boolean addRole(RoleDTO role) {
		String sql = generateSql(role, "INSERT");
		int count = FetchData.updateData(sql);
		return (count > 0) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.withiter.dao.RoleDao#deleteRole(java.lang.String)
	 */
	@Override
	public boolean deleteRole(RoleDTO role) {
		String sql = generateSql(role, "DELETE");
		int count = FetchData.updateData(sql);
		return (count > 0) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.withiter.dao.RoleDao#modifyRole(com.withiter.dto.RoleDTO)
	 */
	@Override
	public boolean modifyRole(RoleDTO role) {
		String sql = generateSql(role, "UPDATE");
		int count = FetchData.updateData(sql);
		return (count > 0) ? true : false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.withiter.dao.RoleDao#queryRole(java.lang.String)
	 */
	@Override
	public RoleDTO queryRole(String roleCode) {
		String sql = "select * from intpub_role where roleCode = " + roleCode
				+ ";";
		List<Object> list = FetchData.queryObject(sql,
				"com.withiter.dto.RoleDTO");
		if (list.size() > 0) {
			return (RoleDTO) list.get(0);
		} else {
			return null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.withiter.dao.RoleDao#queryRoles()
	 */
	@Override
	public List<RoleDTO> queryRoles() {
		String sql = "select * from intpub_role where status != '3';";
		List<Object> list = FetchData.queryObject(sql,
				"com.withiter.dto.RoleDTO");
		List<RoleDTO> roleList = new ArrayList<RoleDTO>();
		for (int i = 0; i < list.size(); i++) {
			roleList.add((RoleDTO) list.get(i));
		}
		return roleList;
	}

	/**
	 * @param role
	 *            the Role Object to INSERT or UPDATE or DELETE
	 * @param type
	 *            INSERT or UPDATE
	 * @return SQL for INSERT and UPDATE and DELETE
	 */
	private static String generateSql(RoleDTO role, String type) {
		String sql = null;
		int roleCode = role.getRoleCode();
		int status = role.getStatus();
		String roleName = role.getRoleName();
		String createBy = role.getCreateby();
		String createDate = role.getCreatedate();
		String updateBy = role.getUpdateby();
		String updateDate = role.getUpdatedate();

		if (type.equals("INSERT")) {
			sql = "insert into intpub_role(roleCode,status,roleName,createby,createdate) values("
					+ roleCode
					+ ","
					+ status
					+ ",'"
					+ roleName
					+ "','"
					+ createBy + "','" + createDate + "');";
		}
		if (type.equals("UPDATE")) {
			sql = "update intpub_role set status = '" + status
					+ "' , roleName = '" + roleName + "', updateby = '"
					+ updateBy + "', updatedate = '" + updateDate
					+ "' where roleCode = '" + roleCode + "';";
		}
		if (type.equals("DELETE")) {
			sql = "update intpub_role set status = '3', updateby = '"
					+ updateBy + "', updatedate = '" + updateDate
					+ "' where roleCode = '" + roleCode + "';";
		}
		return sql;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.withiter.dao.RoleDao#queryRoles(int)
	 */
	@Override
	public PageDTO queryRoles(int pageNo) {
		// TODO Auto-generated method stub
		String sql = "select * from intpub_role where status != '3'";
		System.out.println(sql);
		return FetchData.findPageDTO(pageNo, 10, sql,
				"com.withiter.dto.RoleDTO");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.withiter.dao.RoleDao#queryRoles(java.lang.String, int, int)
	 */
	@Override
	public PageDTO queryRoles(String roleName, int status, int pageNo) {
		String sql = "select * from intpub_role where roleName like '%"
				+ roleName + "%' and status = '" + status + "'";
		System.out.println(sql);
		return FetchData.findPageDTO(pageNo, 10, sql,
				"com.withiter.dto.RoleDTO");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.withiter.dao.RoleDao#queryRoles(java.lang.String, int)
	 */
	@Override
	public PageDTO queryRoles(String roleName, int pageNo) {
		// TODO Auto-generated method stub
		String sql = "select * from intpub_role where roleName like '%"
				+ roleName + "%' and status != '3'";

		System.out.println(sql);
		return FetchData.findPageDTO(pageNo, 10, sql,
				"com.withiter.dto.RoleDTO");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.withiter.dao.RoleDao#queryMenu(java.lang.String)
	 */
	@Override
	public List<RoleMenuDTO> queryMenu(String roleCode) {
		// TODO Auto-generated method stub
		String sql = "select * from intpub_menu_role where roleCode = " + roleCode
				+ ";";
		List<Object> list = FetchData.queryObject(sql,
				"com.withiter.dto.RoleMenuDTO");
		
		List<RoleMenuDTO> menus = new ArrayList<RoleMenuDTO>();
		for(int i = 0; i < list.size(); i++){
			menus.add((RoleMenuDTO)list.get(i));
		}
		return menus;
	}

	/* (non-Javadoc)
	 * @see com.withiter.dao.RoleDao#updateRoleMenus(int, java.util.List)
	 */
	@Override
	public boolean updateRoleMenus(int roleCode, List<RoleMenuDTO> menuArray) {
		// TODO Auto-generated method stub
		
		boolean flag = false;
		
		Connection conn = null;
		Statement st = null;
		String sqlDel = "delete from intpub_menu_role where roleCode = "+roleCode;
		String sqlAdd = "";
		System.out.println(sqlDel);
		conn = ConnectionPool.getInstance().getConnection();
		try {
			conn.setAutoCommit(false);
			st = conn.createStatement();
			st.executeUpdate(sqlDel);
			for(int i = 0; i < menuArray.size(); i++){
				RoleMenuDTO menu = menuArray.get(i);
				int menuCode = menu.getMenuCode();
				int rolecode = menu.getRoleCode();
				int parentCode = menu.getParentCode();
				String menuName = menu.getMenuName();
				sqlAdd = "insert into intpub_menu_role values("+menuCode+","+rolecode+","+parentCode+",'"+menuName +"');";
				st.executeUpdate(sqlAdd);
			}
			conn.commit();
			flag = true;
		} catch (SQLException se) {
			try {
				conn.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			se.printStackTrace();
		} finally {
			try {
				if (st != null) {
					st.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return flag;
	}

}
