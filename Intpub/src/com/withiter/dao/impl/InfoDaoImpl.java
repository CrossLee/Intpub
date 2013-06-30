/*
 * Created on 2012-6-9
 *
 * InfoDaoImpl.java
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
package com.withiter.dao.impl;

import java.util.ArrayList;
import java.util.Map;

import com.withiter.dao.InfoDao;
import com.withiter.dto.InfoDTO;
import com.withiter.dto.PageDTO;
import com.withiter.util.DateUtil;
import com.withiter.util.FetchData;
import com.withiter.util.SequenceUtil;
import com.withiter.util.ConstantUtil.Info_Status;

public class InfoDaoImpl implements InfoDao{
	/**
	 * @param info  Info对象
	 * @return  增加info成功返回true否则返回false
	 */
	public boolean addInfo(InfoDTO info) {
		String sqlStr="insert into intpub_info(infoId,content,title,code,status,createBy,publishTime,createDate,updateDate) values("+getId()+",?,?,?,"+Info_Status.STATE_SAVE+",?,'','"+DateUtil.getTodayTime()+"','')";
		String colStr[]={info.getContent(),info.getTitle(),info.getCode(),info.getCreateBy()};
		System.out.println("+++++++++++"+info.getCode());
		int i=FetchData.updateData(sqlStr, colStr);
		if(i<=0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @param info  info对象      
	 * @return  更新info成功返回true否则返回false
	 */
	public boolean updateInfo(InfoDTO info) {
		String sqlStr="update intpub_info set ";
		sqlStr+=" title=?,content=?,code=?,status="+info.getStatus()+",updateDate='"+DateUtil.getTodayTime()+"' where infoId="+info.getInfoId();
		System.out.println(sqlStr);
		System.out.println(info.getTitle());
		System.out.println(info.getContent());
		System.out.println(info.getCode());
		String colStr[]={info.getTitle(),info.getContent(),info.getCode()};
		    
		int i=FetchData.updateData(sqlStr, colStr);    
		if(i<=0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @param info  info对象
	 * @return  删除info成功返回true否则返回false
	 */
	public boolean deleteInfo(int id) {
		String sqlStr="update intpub_info set status="+Info_Status.STATE_DELETED+",updateDate='"+DateUtil.getTodayTime()+"' where infoId="+id;
		System.out.println(sqlStr);
		int i=FetchData.updateData(sqlStr);
		if(i<=0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * @param id
	 * @return  
	 */
	public InfoDTO queryInfo(int id) {
		String sql="select * from intpub_info where infoId="+id+"";
		
		InfoDTO info=null;
        ArrayList<Object> listObj=FetchData.queryObject(sql,"com.withiter.dto.InfoDTO");
        if(listObj.size()!=0){
        	info=(InfoDTO)listObj.get(0);
		}
		return info;
	}
	
	/**
	 * @param usercode 用户帐号    pageNo为第几页 status 信息状态
	 * @return 分页查询使用,查询的用户所发布或上载的信息
	 */
	public PageDTO queryInfo(String userCode,int status,int pageNo) {
		String sql="SELECT infoId,title,code,IFNULL((select partName from intpub_part where partCode=code),'') codeName,content,status,createBy,publishTime,createDate,updateDate FROM intpub_info b where b.status= "+status;
		if(!userCode.trim().equals("")){
			sql+=" and b.createBy = '"+userCode+"'";
		}
		sql+=" order by date_format(createDate,'%Y-%c-%d %h:%i:%s') desc ";
		return FetchData.findPageDTO(pageNo,10,sql,"com.withiter.dto.InfoDTO");
	}
	
	
	/**
	 * @param usercode 用户帐号    pageNo为第几页 status 信息状态 partCode模块或栏目代码
	 * @return 分页查询使用,查询的用户所发布的信息
	 */
	public PageDTO queryInfo(String userCode,String partCode,int status,int pageNo) {
		String sql="SELECT infoId,title,code,IFNULL((select partName from intpub_part where partCode=code),'') codeName,content,status,createBy,publishTime,createDate,updateDate FROM intpub_info b where b.status= "+status+" and code='"+partCode+"' ";
		if(!userCode.trim().equals("")){
			sql+=" and b.createBy = '"+userCode+"'";
		}
		sql+=" order by date_format(createDate,'%Y-%c-%d %h:%i:%s') desc ";
		return FetchData.findPageDTO(pageNo,10,sql,"com.withiter.dto.InfoDTO");
	}
	
	
	/**
	 * @param usercode 用户帐号    pageNo为第几页 status 信息状态 partCode栏目代码 date 查询时间
	 * @return 分页查询使用,查询的用户所发布的信息
	 */
	public PageDTO queryInfo(String usercode,String partCode,String date,int status,int pageNo) {
		String sql="SELECT infoId,title,code,IFNULL((select partName from intpub_part where partCode=code),'') codeName,content,status,createBy,publishTime,createDate,updateDate FROM intpub_info b where 1=1 ";
		if(!usercode.trim().equals("")){
			sql+=" and b.createBy = '"+usercode+"'";
		}
		if(!partCode.trim().equals("")){
			sql+=" and code ='"+partCode+"'";
		}
		if(!date.trim().equals("")){
			sql+=" and createDate >= '"+date+"'";
		}
		//发布菜单页面  状态为0的 查询除删除以外的所有信息
		if(status==0){
			sql+=" and b.status !="+Info_Status.STATE_DELETED;
		}else{
			sql+=" and b.status ="+status;
		}
		sql+=" order by date_format(createDate,'%Y-%c-%d %h:%i:%s') desc ";
		System.out.println(sql);
		return FetchData.findPageDTO(pageNo,10,sql,"com.withiter.dto.InfoDTO");
	}
	
	@SuppressWarnings("unchecked")
	public Map queryPart() {
        String sql="select partCode code ,partName name from intpub_part where status!= "+Info_Status.STATE_DELETED;
        System.out.println(sql);
        Map partMap=FetchData.getSelectMapData(sql);
        return  partMap;
	}
	/**
	 * @return primary key
	 */
	public int getId(){
		return SequenceUtil.getInstance().getNextKeyValue("intpub_info");
	}
}
