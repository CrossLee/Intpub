/*
 * Created on 2012-5-8
 *
 * RoleAction.java
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
package com.withiter.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.withiter.dto.MenuDTO;
import com.withiter.dto.PageDTO;
import com.withiter.dto.RoleDTO;
import com.withiter.dto.RoleMenuDTO;
import com.withiter.dto.UserDTO;
import com.withiter.service.RoleService;
import com.withiter.service.UserService;
import com.withiter.util.ConstantUtil;
import com.withiter.util.DateUtil;
import com.withiter.util.SequenceUtil;
import com.withiter.util.StringUtil;

/**
 * @author gl65293
 */
public class RoleAction extends ActionSupport {

	/**
     * 
     */
	private static final long serialVersionUID = 1L;
	private static final Log log = LogFactory.getLog(RoleAction.class);

	private int roleCode;
	private String roleName;
	private int status;
	private String createBy;
	private String createDate;
	private String updateBy;
	private String updateDate;
	private List<UserDTO> roleMembers;
	private List<RoleDTO> roleList;

	private boolean operationState;

	private RoleService roleService;
	private UserService userService;

	// for page
	private String flag;
	private PageDTO pageDTO;
	private int pageNo;
	private String pageNoPro;

	// for query one object
	private RoleDTO roleDTO;
	
	// for query menus
	List<RoleMenuDTO> menus;
	List<MenuDTO> unSelectedMenus;
	
	// for manage menus
	String menuString;
	String manageMenus;
	
	public String jspAdd() {
		return "add";
	}

	public String jspDelete() {
		roleList = roleService.queryRoles();
		return "delete";
	}

	public String jspModify() {
		roleList = roleService.queryRoles();
		return "modify";
	}

	public String jspQuery() {
		roleList = roleService.queryRoles();
		return "query";
	}
	
	public String jspRoleMenu() {
		roleList = roleService.queryRoles();
		return "rolemenu";
	}

	/**
	 * query role
	 * 
	 * @return jsp
	 */
	public String query() {
		System.out.println(flag);
		System.out.println(pageNo);
		System.out.println(status);
//		roleList = roleService.queryRoles();
		
		if(!flag.trim().equals("")){
			// role details
			if(flag.trim().equals("queryDTO")){
				System.out.println(roleCode);
				System.out.println(flag);
				roleDTO = roleService.queryOneRole(String.valueOf(roleCode));
				if(roleDTO != null){
					return "queryOneSuccess";
				} else {
					return ERROR;
				}
			}
			
			// modify role
			if(flag.trim().equals("modifyDTO")){
				System.out.println(roleCode);
				System.out.println(flag);
				roleDTO = roleService.queryOneRole(String.valueOf(roleCode));
				if(roleDTO != null){
					return "queryOneSuccess";
				} else {
					return ERROR;
				}
			}
			if(flag.trim().equals("query") || flag.trim().equals("modify") || flag.trim().equals("delete") || flag.trim().equals("menu")){
				pageDTO = roleService.queryRoles(pageNo);
			}
			if(flag.trim().equals("queryBySearch") || flag.trim().equals("modifyBySearch") || flag.trim().equals("deleteBySearch") || flag.trim().equals("menuBySearch")){
				roleName = StringUtil.emptyToString(roleName);
				if(status == 0){
					pageDTO = roleService.queryRoles(roleName,pageNo);
				}else{
					pageDTO = roleService.queryRoles(roleName, status, pageNo);
				}
			}
			
			return SUCCESS;
		}else{
			return ERROR;
		}
	}

	// get role's menu
	public String queryMenu(){
		if(flag.trim().equals("queryMenu")){
			System.out.println(roleCode);
			System.out.println(flag);
			menus = roleService.queryRoleMenu(String.valueOf(roleCode));
			
			List<MenuDTO> allMenus = userService.queryMenus();
			unSelectedMenus = userService.queryMenus();
			
			for(int i = 0; i < allMenus.size(); i++){
				MenuDTO menu = allMenus.get(i);
				for(RoleMenuDTO m : menus){
					if(String.valueOf(menu.getMenuCode()).equals(String.valueOf(m.getMenuCode()))){
						unSelectedMenus.remove(i);
					}
				}
			}
		}
		
		
		return "queryOneSuccess";
	}
	
	// get role's menu
	public String saveMenu(){
		System.out.println(roleCode);
		System.out.println(menuString);
		
		String[] menuArray = menuString.split(", ");
		List<MenuDTO> allMenus = userService.queryMenus();
		List<RoleMenuDTO> rolemenus = new ArrayList<RoleMenuDTO>();
		for(MenuDTO menuDTO : allMenus){
			for(String str : menuArray){
				if(str.equals(String.valueOf(menuDTO.getMenuCode()))){
					RoleMenuDTO rm = new RoleMenuDTO();
					rm.setMenuCode(menuDTO.getMenuCode());
					rm.setMenuName(menuDTO.getMenuName());
					rm.setParentCode(Integer.parseInt(menuDTO.getParentCode()));
					rm.setRoleCode(roleCode);
					rolemenus.add(rm);
				}
			}
		}
		
		boolean flag = roleService.updateRoleMenus(roleCode,rolemenus);

		if(flag){
			manageMenus = "true";
		}else{
			manageMenus = "false";
		}
		
		return "roleObj";
	}
	
	/**
	 * add role
	 * 
	 * @return JSON format data
	 */
	public String add() {
		if (log.isDebugEnabled()) {
			log.debug("the role name will be added is : " + roleName);
		}

		System.out.println(status);

		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		UserDTO user = (UserDTO) session.get("userDTO");
		String currentTime = DateUtil.getTodayTime();
		RoleDTO role = new RoleDTO();
		role.setRoleCode(SequenceUtil.getInstance().getNextKeyValue(
				ConstantUtil.SequenceName.ROLE_SEQUENCE));
		role.setRoleName(roleName);
		role.setStatus(status);

		role.setCreateby(user.getUserName());
		role.setCreatedate(currentTime);

		role.setUpdateby(user.getUserName());
		role.setUpdatedate(currentTime);

		boolean flag = roleService.addRole(role);
		operationState = true && flag;

		return "roleObj";
	}

	/**
	 * modify role
	 * 
	 * @return JSON format data
	 */
	public String modify() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		UserDTO user = (UserDTO) session.get("userDTO");
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		int roleCode = Integer.parseInt(request.getParameter("roleCode"));

		if (log.isDebugEnabled()) {
			log.debug("the role's id witch will be modified is : " + roleCode);
		}

		String currentTime = DateUtil.getTodayTime();
		RoleDTO role = new RoleDTO();
		role.setRoleCode(roleCode);
		role.setRoleName(roleName);
		role.setStatus(status);
		role.setUpdateby(user.getUserName());
		role.setUpdatedate(currentTime);

		boolean flag = roleService.modifyRole(role);
		operationState = true && flag;

		return "roleObj";
	}

	/**
	 * delete role
	 * 
	 * @return JSON format data
	 */
	public String delete() {
		ActionContext context = ActionContext.getContext();
		Map<String, Object> session = context.getSession();
		UserDTO user = (UserDTO) session.get("userDTO");
		HttpServletRequest request = (HttpServletRequest) context
				.get(ServletActionContext.HTTP_REQUEST);
		int roleCode = Integer.parseInt(request.getParameter("roleCode"));

		if (log.isDebugEnabled()) {
			log.debug("the role's id witch will be modified is : " + roleCode);
		}

		String currentTime = DateUtil.getTodayTime();
		RoleDTO role = new RoleDTO();
		role.setRoleCode(roleCode);
		role.setStatus(status);
		role.setUpdateby(user.getUserName());
		role.setUpdatedate(currentTime);

		boolean flag = roleService.deleteRole(role);
		operationState = true && flag;

		return "roleObj";
	}

	/**
	 * modify role members
	 * 
	 * @return JSON format data
	 */
	public String modifyRoleMembers() {

		return "roleObj";
	}

	/**
	 * add role members
	 * 
	 * @return JSON format data
	 */
	public String addRoleMembers() {

		return "roleObj";
	}

	public int getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(int roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}

	public List<UserDTO> getRoleMembers() {
		return roleMembers;
	}

	public void setRoleMembers(List<UserDTO> roleMembers) {
		this.roleMembers = roleMembers;
	}

	public boolean isOperationState() {
		return operationState;
	}

	public void setOperationState(boolean operationState) {
		this.operationState = operationState;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public List<RoleDTO> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<RoleDTO> roleList) {
		this.roleList = roleList;
	}

	public PageDTO getPageDTO() {
		return pageDTO;
	}

	public void setPageDTO(PageDTO pageDTO) {
		this.pageDTO = pageDTO;
	}

	public int getPageNo() {
		return pageNo;
	}

	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}

	public String getPageNoPro() {
		return pageNoPro;
	}

	public void setPageNoPro(String pageNoPro) {
		this.pageNoPro = pageNoPro;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public RoleDTO getRoleDTO() {
		return roleDTO;
	}

	public void setRoleDTO(RoleDTO roleDTO) {
		this.roleDTO = roleDTO;
	}

	public List<RoleMenuDTO> getMenus() {
		return menus;
	}

	public void setMenus(List<RoleMenuDTO> menus) {
		this.menus = menus;
	}

	public List<MenuDTO> getUnSelectedMenus() {
		return unSelectedMenus;
	}

	public void setUnSelectedMenus(List<MenuDTO> unSelectedMenus) {
		this.unSelectedMenus = unSelectedMenus;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public String getManageMenus() {
		return manageMenus;
	}

	public void setManageMenus(String manageMenus) {
		this.manageMenus = manageMenus;
	}

	public String getMenuString() {
		return menuString;
	}

	public void setMenuString(String menuString) {
		this.menuString = menuString;
	}
	
}
