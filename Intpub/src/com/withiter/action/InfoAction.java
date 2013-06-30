/*
 * Created on 2012-6-9
 *
 * InfoAction.java
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
package com.withiter.action;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.withiter.dto.InfoDTO;
import com.withiter.dto.PageDTO;
import com.withiter.dto.UserDTO;
import com.withiter.service.InfoService;

public class InfoAction extends ActionSupport {

	private static final long serialVersionUID = 2011217921171822590L;
	private static final Log log = LogFactory.getLog(InfoAction.class);
	private String content;
	private String page;
	private String flag;
	private InfoService infoService;
	private String loginState;
	private InfoDTO info;
	@SuppressWarnings("unchecked")
	private Map partMap;
	@SuppressWarnings("unchecked")
	private Map moduleMap;
	private PageDTO pageDTO;
	private int pageNo;
	private int status;
	private int infoId;
	private String partCode;
	private String date;
	private String title;
	private String type;
	public String add(){
		ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        String currentUser=((UserDTO)session.get("userDTO")).getUserCode();
        info=new InfoDTO();
        info.setContent(content);
        System.out.println(title);
        info.setTitle(title);
        info.setCode(partCode);
        info.setCreateBy(currentUser);
		if(infoService.addInfo(info)){
				loginState="addSuccess";
		}else{
				loginState="addError";
		}
		
		return "infoObj";
	}
	public String query(){
		System.out.println("--"+flag);
		if(flag.trim().equals("upload")||flag.trim().equals("publish")){
			
			partMap=infoService.queryPart();
			return flag;
		}else if(flag.trim().equals("query")){
			ActionContext context = ActionContext.getContext();
	        Map<String, Object> session = context.getSession();
	        String currentUser=((UserDTO)session.get("userDTO")).getUserCode();
			pageDTO=infoService.queryInfo(currentUser,partCode,date,status,pageNo);
		
			return "querySucc";
		}else if(flag.trim().equals("updateByUpload")||flag.trim().equals("select")){
			info=infoService.queryInfo(infoId);
			partMap=infoService.queryPart();
			if(info==null){
				return "error";
			}
			return "updateByQuerySucc";
		}else if(flag.trim().equals("updateByPublish")||flag.trim().equals("selectByPublish")){
			info=infoService.queryInfo(infoId);
			partMap=infoService.queryPart();
			if(info==null){
				return "error";
			}
			return "updateByPublishSucc";
		}else{
			return "";
		} 
	}
	public String load(){
		if(page.trim().equals("upload_add")){
			partMap=infoService.queryPart();
			return "upload_add";
		}else{
			return "";
		} 
	}

	public String update(){
		if(flag.trim().equals("updateByUpload")){
			info=new InfoDTO();
	        info.setContent(content);
	        info.setTitle(title);
	        info.setCode(partCode);
	        info.setInfoId(infoId);
	        info.setStatus(status);
			if(infoService.updateInfo(info)){
				loginState="addSuccess";
			}else{
				loginState="addError";
			}
			return "infoObj";
		}else{
			return "";
		} 
	}
	
	public String delete(){
		ActionContext context = ActionContext.getContext();
        Map<String, Object> session = context.getSession();
        String currentUser=((UserDTO)session.get("userDTO")).getUserCode();
      
        if(type.equals("upload")){
        	 status=1;
		}else if(type.equals("publish")){
			 status=0;
		}else{
			 status=2;
		}
        if(infoService.deleteInfo(infoId)){
			pageDTO=infoService.queryInfo(currentUser,"",date,status,1);
		}
		return "querySucc";
	}
	
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}

	public InfoService getInfoService() {
		return infoService;
	}

	public void setInfoService(InfoService infoService) {
		this.infoService = infoService;
	}
	public String getLoginState() {
		return loginState;
	}
	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}
	public InfoDTO getInfo() {
		return info;
	}
	public void setInfo(InfoDTO info) {
		this.info = info;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@SuppressWarnings("unchecked")
	public Map getPartMap() {
		return partMap;
	}
	@SuppressWarnings("unchecked")
	public void setPartMap(Map partMap) {
		this.partMap = partMap;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public String getPartCode() {
		return partCode;
	}
	public void setPartCode(String partCode) {
		this.partCode = partCode;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getInfoId() {
		return infoId;
	}
	public void setInfoId(int infoId) {
		this.infoId = infoId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@SuppressWarnings("unchecked")
	public Map getModuleMap() {
		return moduleMap;
	}
	@SuppressWarnings("unchecked")
	public void setModuleMap(Map moduleMap) {
		this.moduleMap = moduleMap;
	}
}
