/*
 * Created on 2012-5-17
 *
 * UserServiceImpl.java
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
package com.withiter.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.withiter.dao.UserDao;
import com.withiter.dto.MenuDTO;
import com.withiter.dto.PageDTO;
import com.withiter.dto.UserDTO;
import com.withiter.service.UserService;

public class UserServiceImpl implements UserService{

	private UserDao userDao;

	public boolean addUser(UserDTO user,String currentUser) {

		return userDao.addUser(user,currentUser);
	}
	
    public ArrayList<MenuDTO> queryMenus(String id,String pass) {
        
		return userDao.queryMenus(id, pass);
	}

    public boolean deleteUser(int  userid,String usercode){
		
		return userDao.deleteUser(userid,usercode);
	}

	public boolean isExistUser(String userCode) {
		
		return userDao.isExistUser(userCode);
	}

	public boolean updateUser(UserDTO user) {
		
		return userDao.updateUser(user);
	}

	public ArrayList<UserDTO> queryUser(String name, int status,String usercode) {
		
		return userDao.queryUser(name, status,usercode);
	}
	
	public UserDTO queryUser(String code, String pass) {
		
		return userDao.queryUser(code, pass);
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}


	public ArrayList<UserDTO> queryUser(int rolecode){
		return userDao.queryUser(rolecode);
	}

	public boolean updateUserRole(String[] addUserList, int rolecode) {

		return userDao.updateUserRole(addUserList, rolecode);
	}

	public String getUserName(String userid, int rolecode) {
		
		return userDao.getUserName(userid, rolecode);
	}

	public PageDTO queryUser(String name, int status, String usercode,int pageNo) {
		return userDao.queryUser(name, status, usercode, pageNo);
	}

	public UserDTO queryUser(String code) {

		return userDao.queryUser(code);
	}

	@SuppressWarnings("unchecked")
	public Map querySelectUser(int roleCode){
		
		return userDao.querySelectUser(roleCode);
	}
	
	@SuppressWarnings("unchecked")
	public Map queryNoSelectUser(){
		
		return userDao.queryNoSelectUser();
	}

	@SuppressWarnings("unchecked")
	public Map queryRole() {
		
		return userDao.queryRole();
	}
	
	public List<MenuDTO> queryMenus() {
		// TODO Auto-generated method stub
		return userDao.queryMenus();
	}
}
