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
  	<form id="updateDetailForm" name="updateDetailForm">
  		<table>
  			<tr>
  				<td class="left_title_1"><span class="left-title">名称</span></td>
  				<td>
  					<input type="hidden" id="partCodeUpdate" name="part.partCode" value="${part.partCode }"/>
  					<input type="text" id="partNameUpdate" name="part.partName" value="${part.partName }"/>
  				</td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">备份</span></td>
  				<td><input type="text" id="backupUpdate" name="part.backup"  value="${part.backup }"/></td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">状态</span></td>
  				<td>
  					<select id="statusUpdate" name="part.status">
  						<option value="0">-请选择-</option>
			        	<option value="1">启用</option>
			        	<option value="2">禁用</option>
			        </select>
  				</td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">排序</span></td>
  				<td><input type="text" id="sortUpdate" name="part.sort" value="${part.sort }"/></td>
  			</tr>
  			<tr>
  				<td class="left_title_2"></td>
  				<td>
  					<input type="button" class="button_class" value="更新" onclick="javascript:PartUpdate.update(<s:property value='partCode'/>);"></input>
				</td>
  			</tr>
  			
  		</table>
  		</form>
  		</div>
  </body>
</html>
