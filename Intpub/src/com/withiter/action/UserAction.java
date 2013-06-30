/*
 * Created on 2012-5-178
 *
 * UserAction.java
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
 * 2012-5-18           Sam                          1.0         New
 * ---------------------------------------------------------------------------------
 */
package com.withiter.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.withiter.dto.MenuDTO;
import com.withiter.dto.PageDTO;
import com.withiter.dto.UserDTO;
import com.withiter.service.UserService;
import com.withiter.util.StringUtil;

public class UserAction extends ActionSupport {

	
	private static final long serialVersionUID = 9126600003504035977L;
	private static final Log log = LogFactory.getLog(UserAction.class);
	private UserService userService;

	private String username;
	private String userCode;
	private String userpwd;
	private int status;
	private int id;
	private String loginState;
	private MenuDTO menu;
	private ArrayList<MenuDTO> menuList;
	private ArrayList<UserDTO> userList;
	@SuppressWarnings("unchecked")
	private Map roleMap;
	@SuppressWarnings("unchecked")
	private Map userMap;
	@SuppressWarnings("unchecked")
	private Map noUserMap;
	private UserDTO user;
	private String flag;
	private String page;  //访问页面名称
	private int rolecode;
	private int size;   //用来存放list的大小
	private String[] addUserList;
	private PageDTO pageDTO;
	private int pageNo;

	
	public String login(){
		
		user=userService.queryUser(username, userpwd);
		if(user!=null){
			ActionContext context = ActionContext.getContext();
	        Map<String, Object> session = context.getSession();
	        session.put("userDTO", user);
			menuList=userService.queryMenus(username, userpwd);
			if(menuList!=null){
				if(menuList.size()==0){
					loginState = "noRole";	
				}else{
					
			        session.put("menuList", menuList);
					loginState = "success";
				}
			}else{
				loginState = "error";
			}
		}else{
			loginState = "noUser";
		}
		
		return "userObj";
	}
    
	public String add(){
		ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        UserDTO userDTO=(UserDTO)session.get("userDTO");
        String currentUser="";
        if(userDTO==null){
        	currentUser=user.getUserCode();
        }else{
        	currentUser=userDTO.getUserCode();
        }
		if(userService.addUser(user,currentUser)){
				loginState="addSuccess";
		}else{
				loginState="addError";
		}
		
		return "userObj";
	}
	
	public String update(){
		ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        String currentUser=((UserDTO)session.get("userDTO")).getUserCode();
		user.setUpdateBy(currentUser);
		if(userService.updateUser(user)){
			loginState = "updateSuccess";
		}else{
			loginState = "updateError";
		}
		
		return "userObj";
	}
	
	
	public String modify(){
		System.out.println(rolecode);
		if(userService.updateUserRole(addUserList, rolecode)){
			loginState = "modifySuccess";
		}else{
			loginState = "modifyError";
		}
		
		return "userObj";
	}
	
	public String delete(){
		ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
		if(userService.deleteUser(id,((UserDTO)session.get("userDTO")).getUserCode())){
			username=StringUtil.emptyToString(username);
			pageDTO=userService.queryUser(username, status,"",1);
		}
		
		return "querySucc";
	}
	
	public String query(){
		if(flag.trim().equals("isExist")){
			if(userService.isExistUser(userCode)){
				loginState="Exists";
			}else{
				loginState="notExists";
			}
			return "userObj";
		}else if(flag.trim().equals("updateByQuery")||flag.trim().equals("select")){
			userList=userService.queryUser("", 0,userCode);
			if(userList==null||userList.size()==0){
				return "error";
			}else{
				user=userList.get(0);
			}
			return "updateByQuerySucc";
		}else if(flag.trim().equals("roleQuery")){
			
			roleMap=userService.queryRole();
			return "roleQuery";
		}else if(flag.trim().equals("roleUser")){
			userMap=userService.querySelectUser(rolecode);
			noUserMap=userService.queryNoSelectUser();
			return "roleUser";
		}else{
			username=StringUtil.emptyToString(username);
			pageDTO=userService.queryUser(username, status,"",pageNo);
			
			return "querySucc";
		}
	}
	
	public String load(){
		if(page.trim().equals("add")){
			return "add";
		}else if(page.trim().equals("update")) {
			return "update";
		}else if(page.trim().equals("delete")) {
			return "delete";
		}else if(page.trim().equals("query")) {
			return "query";
		}else if(page.trim().equals("self")) {
			ActionContext context = ActionContext.getContext();
	        Map<String, Object> session = context.getSession();
	        String currentUser=((UserDTO)session.get("userDTO")).getUserCode();
			user=userService.queryUser(currentUser);
			return "self";
		}else{
			return "error";
		}
	}
	
	public MenuDTO getMenu() {
		return menu;
	}

	public void setMenu(MenuDTO menu) {
		this.menu = menu;
	}

	public ArrayList<MenuDTO> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<MenuDTO> menuList) {
		this.menuList = menuList;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUserpwd() {
		return userpwd;
	}

	public void setUserpwd(String userpwd) {
		this.userpwd = userpwd;
	}

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public ArrayList<UserDTO> getUserList() {
		return userList;
	}

	public void setUserList(ArrayList<UserDTO> userList) {
		this.userList = userList;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@SuppressWarnings("unchecked")
	public Map getRoleMap() {
		return roleMap;
	}

	@SuppressWarnings("unchecked")
	public void setRoleMap(Map roleMap) {
		this.roleMap = roleMap;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public int getRolecode() {
		return rolecode;
	}

	public void setRolecode(int rolecode) {
		this.rolecode = rolecode;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}


	public String[] getAddUserList() {
		return addUserList;
	}

	public void setAddUserList(String[] addUserList) {
		this.addUserList = addUserList;
	}

	public int getPageNo() {
		return this.pageNo;
	}
   
		
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}

	@SuppressWarnings("unchecked")
	public Map getUserMap() {
		return userMap;
	}

	@SuppressWarnings("unchecked")
	public void setUserMap(Map userMap) {
		this.userMap = userMap;
	}

	@SuppressWarnings("unchecked")
	public Map getNoUserMap() {
		return noUserMap;
	}

	@SuppressWarnings("unchecked")
	public void setNoUserMap(Map noUserMap) {
		this.noUserMap = noUserMap;
	}
	
}
