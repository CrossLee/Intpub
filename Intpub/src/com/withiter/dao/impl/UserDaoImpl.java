/*
 * Created on 2012-5-1
 *
 * UserDaoImpl.java
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
 * 2012-5-1       CrossLee                    1.0         New
 * 2012-5-17         Sam                      2.0         Update
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
import java.util.Map;

import com.withiter.dao.UserDao;
import com.withiter.dto.MenuDTO;
import com.withiter.dto.PageDTO;
import com.withiter.dto.UserDTO;
import com.withiter.util.ConnectionPool;
import com.withiter.util.DateUtil;
import com.withiter.util.FetchData;
import com.withiter.util.SequenceUtil;
import com.withiter.util.ConstantUtil.Status;


public class UserDaoImpl implements UserDao {

	/**
	 * @param user  user对象
	 * @return  增加user成功返回true否则返回false
	 */
	public boolean addUser(UserDTO user,String currentUser) {
		String sqlStr="insert into intpub_user values("+getId()+",?,?,?,"+Status.STATE_DISPLAY+",null,?,'"+DateUtil.getTodayTime()+"','','')";
		String colStr[]={user.getUserCode(),user.getPassword(),user.getUserName(),currentUser};
		System.out.println("currentUser=="+currentUser);
		int i=FetchData.updateData(sqlStr, colStr);
		if(i<=0){
			return false;
		}else{
			return true;
		}
	}
   
	/**
	 * @param user  user对象      
	 * @return  更新user成功返回true否则返回false
	 */
	public boolean updateUser(UserDTO user) {
		String sqlStr="update intpub_user set ";
		sqlStr+="userCode=?,password=?,userName=?,status="+user.getStatus()+",updateBy='"+user.getUpdateBy()+"',updateDate='"+DateUtil.getTodayTime()+"' where id="+user.getId();
		String colStr[]={user.getUserCode(),user.getPassword(),user.getUserName()};
		    
		int i=FetchData.updateData(sqlStr, colStr);    
		if(i<=0){
			return false;
		}else{
			return true;
		}
	}
   
	
	/**
	 * @param userids  多个用户id,rolecode 角色代码
	 * @return  更新用户角色成功与否 
	 */
	public boolean updateUserRole(String[] addUserList,int rolecode) {
		Connection conn = null;
		Statement st = null;
		boolean bl = true;
		try {
			conn = ConnectionPool.getInstance().getConnection();
			conn.setAutoCommit(false);
			st = conn.createStatement();
			String sqlStr="";
			sqlStr="update intpub_user set roleCode=null where roleCode="+rolecode;
			st.executeUpdate(sqlStr);
			System.out.println(sqlStr);
			for(int i=0;i<addUserList.length;i++){
				if(!addUserList[i].equals("")){
					sqlStr="update intpub_user set roleCode="+rolecode+" where  id="+Integer.parseInt(addUserList[i]);
					System.out.println("=="+sqlStr);
					st.executeUpdate(sqlStr);
				}
			}
			conn.commit();
		} catch (SQLException se) {
			bl=false;
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
		return bl;
		
	}
	
	
	/**
	 * @param userid  用户id,rolecode 角色代码
	 * @return  返回用户名称 
	 */
	public String getUserName(String userid,int rolecode){
		String sql="select userName from intpub_user a,intpub_role b  where a.id="+Integer.parseInt(userid)+" and a.roleCode=b.roleCode and b.roleCode!="+rolecode;

		return FetchData.getSingleData(sql);
	}
	
	/**
	 * @param user  user对象
	 * @return  删除user成功返回true否则返回false
	 */
	public boolean deleteUser(int  userid,String usercode) {
		String sqlStr="update intpub_user set status="+Status.STATE_DELETED+",updateBy='"+usercode+"',updateDate='"+DateUtil.getTodayTime()+"' where id="+userid;
		
		int i=FetchData.updateData(sqlStr);
		if(i<=0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @param userCode  用户帐号
	 * @return  判断帐号是否存在,返回布尔类型 
	 */
	public boolean isExistUser(String userCode){
		String sql="select userCode from intpub_user where userCode='"+userCode+"'";

		return FetchData.searchData(sql);
	}
	
	/**
	 * @param id,pass 帐号和密码
	 * @return 返回存放用户菜单的list
	 */
	public ArrayList<MenuDTO> queryMenus(String id,String pass) {
        String sql="select * from intpub_menu where display='1' order by menuCode ";
        ArrayList<Object> listObj=FetchData.queryObject(sql,"com.withiter.dto.MenuDTO");
		ArrayList<MenuDTO> menuList=new ArrayList<MenuDTO>();
        for(int i=0;i<listObj.size();i++){
        	MenuDTO menu=(MenuDTO)listObj.get(i);
        	menuList.add(menu);
		}
        return  menuList;
	}
	
	/**
	 * @param name 要查的用户名 status 帐号的启用状态
	 * @return 查询的用户组
	 */
	public ArrayList<UserDTO> queryUser(String name,int status,String usercode) {
        String sql="SELECT id,userCode,password,userName,status,roleCode,(select roleName from intpub_role a where a.roleCode=b.roleCode) roleName,createBy,createDate,updateBy,updateDate FROM intpub_user b where b.status!= "+Status.STATE_DELETED;
        if(status!=0){
        	sql+=" and b.status="+status;
        }
        if(!name.trim().equals("")){
        	sql+=" and b.username like '%"+name+"%'";
        }
        if(!usercode.trim().equals("")){
        	sql+=" and b.usercode = '"+usercode+"'";
        }
        sql+=" order by userCode ";
        ArrayList<Object> listObj=FetchData.queryObject(sql,"com.withiter.dto.UserDTO");
		ArrayList<UserDTO> userList=new ArrayList<UserDTO>();
        for(int i=0;i<listObj.size();i++){
        	UserDTO menu=(UserDTO)listObj.get(i);
        	userList.add(menu);
		}
        return  userList;
	}
	
	/**
	 * @param name 要查的用户名 status 帐号的启用状态 pageNo为第几页
	 * @return 分页查询使用,查询的用户组
	 */
	public PageDTO queryUser(String name,int status,String usercode,int pageNo) {
		String sql="SELECT id,userCode,password,userName,status,roleCode,(select roleName from intpub_role a where a.roleCode=b.roleCode) roleName,createBy,createDate,updateBy,updateDate FROM intpub_user b where b.status!= "+Status.STATE_DELETED;
		if(status!=0){
			sql+=" and b.status="+status;
		}
		if(!name.trim().equals("")){
			sql+=" and b.username like '%"+name+"%'";
		}
		if(!usercode.trim().equals("")){
			sql+=" and b.usercode = '"+usercode+"'";
		}
		sql+=" order by userCode ";
		return FetchData.findPageDTO(pageNo,10,sql,"com.withiter.dto.UserDTO");
	}
	
	/**
	 * @param 
	 * @return 返回user对象
	 */
	public UserDTO queryUser(String code,String pass) {
        String sql="select * from intpub_user where userCode='"+code+"' and password='"+pass+"'";
        UserDTO user=null;
        ArrayList<Object> listObj=FetchData.queryObject(sql,"com.withiter.dto.UserDTO");
        if(listObj.size()!=0){
        	user=(UserDTO)listObj.get(0);
		}
        return  user;
	}
	
	
	/**
	 * @param 
	 * @return 返回包含role的map
	 */
	@SuppressWarnings("unchecked")
	public Map queryRole() {
        String sql="select roleCode,roleName from intpub_role where status!= "+Status.STATE_DELETED+" order by roleCode";
		
        Map roleMap=FetchData.getSelectMapData(sql);
        
        return  roleMap;
	}
	
	/**
	 * @param 
	 * @return 返回某个角色包含user的map
	 */
	@SuppressWarnings("unchecked")
	public Map querySelectUser(int roleCode) {
        String sql="select id,userName from intpub_user where status!= "+Status.STATE_DELETED+"  and roleCode="+roleCode+" order by userCode";
		
        Map userMap=FetchData.getSelectMapData(sql);
        
        return  userMap;
	}
	
	/**
	 * @param 
	 * @return 返回还没有分配角色的user的map
	 */
	@SuppressWarnings("unchecked")
	public Map queryNoSelectUser() {
        String sql="select id,userName from intpub_user where status!= "+Status.STATE_DELETED+" and roleCode is  null order by userCode";
		
        Map userMap=FetchData.getSelectMapData(sql);
        
        return  userMap;
	}
	
	
	/**
	 * @param name 某一角色下的用户组
	 * @return 查询的用户组
	 */
	public ArrayList<UserDTO> queryUser(int rolecode) {
        String sql="select * from intpub_user where status!= "+Status.STATE_DELETED+" and rolecode="+rolecode;
       
        sql+=" order by userCode ";
        ArrayList<Object> listObj=FetchData.queryObject(sql,"com.withiter.dto.UserDTO");
		ArrayList<UserDTO> userList=new ArrayList<UserDTO>();
        for(int i=0;i<listObj.size();i++){
        	UserDTO menu=(UserDTO)listObj.get(i);
        	userList.add(menu);
		}
        return  userList;
	}
	/**
	 * @return primary key
	 */
	public int getId(){
		return SequenceUtil.getInstance().getNextKeyValue("intpub_user");
	}
	
	
	public UserDTO queryUser(String code) {
		String sql="select * from intpub_user where userCode='"+code+"'";
		System.out.println(sql);
        UserDTO user=null;
        ArrayList<Object> listObj=FetchData.queryObject(sql,"com.withiter.dto.UserDTO");
        if(listObj.size()!=0){
        	user=(UserDTO)listObj.get(0);
		}
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.withiter.dao.UserDao#queryMenus()
	 */
	public List<MenuDTO> queryMenus() {
		String sql="select * from intpub_menu where display='1' order by menuCode ";
        ArrayList<Object> listObj=FetchData.queryObject(sql,"com.withiter.dto.MenuDTO");
		ArrayList<MenuDTO> menuList=new ArrayList<MenuDTO>();
        for(int i=0;i<listObj.size();i++){
        	MenuDTO menu=(MenuDTO)listObj.get(i);
        	menuList.add(menu);
		}
        return  menuList;
	}
}
