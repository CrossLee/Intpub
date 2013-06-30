<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
  </head>
  
  <body>
  	<div class="man_zoneCls">
  		<table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
  				<td class="left_title_1"><span class="left-title">模块名称</span></td>
  				<td>
  					<input type="hidden" id="moduleCode" name="moduleDetail.moduleCode" value="${moduleDetail.moduleCode}" />
  					<input type="text" id="moduleName" name="moduleDetail.moduleName" value="${moduleDetail.moduleName}" disabled="disabled"/>
  				</td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">模块描述</span></td>
  				<td><input type="text" id="description" name="moduleDetail.description" value="${moduleDetail.description}" disabled="disabled"/></td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">状态</span></td>
  				<td>
  					<s:if test='moduleDetail.status==1'>
		  			启用
		 	        </s:if> <s:else>
		 	                    禁用
			        </s:else>
  				</td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">栏目数量</span></td>
  				<td><input type="text" id="amount" name="moduleDetail.amount" value="${moduleDetail.amount}" disabled="disabled"/></td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">显示长度</span></td>
  				<td><input type="text" id="displayLen" name="moduleDetail.displayLen" value="${moduleDetail.displayLen}" disabled="disabled"/></td>
  			</tr>
  		</table>
  	</div>
  </body>
</html>
