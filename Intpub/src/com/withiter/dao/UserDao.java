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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.withiter.dto.MenuDTO;
import com.withiter.dto.PageDTO;
import com.withiter.dto.UserDTO;

public interface UserDao {

	boolean addUser(UserDTO user,String currentUser);
	
	public ArrayList<MenuDTO> queryMenus(String id,String pass);
	
	public boolean updateUser(UserDTO user);
	
	public boolean deleteUser(int  userid,String usercode);
	
	public boolean isExistUser(String userCode);
	
	public ArrayList<UserDTO> queryUser(String name,int status,String usercode);
	
	public PageDTO queryUser(String name,int status,String usercode,int pageNo);
	
	public UserDTO queryUser(String code,String pass);
	
	public UserDTO queryUser(String code);
	
	@SuppressWarnings("unchecked")
	public Map querySelectUser(int roleCode);
	@SuppressWarnings("unchecked")
	public Map queryNoSelectUser();
	@SuppressWarnings("unchecked")
	public Map queryRole() ;
	
	public ArrayList<UserDTO> queryUser(int rolecode);
	
	public boolean updateUserRole(String[] addUserList,int rolecode);
	
	public String getUserName(String userid,int rolecode);
	/**
	 * @return
	 */
	List<MenuDTO> queryMenus();
}
