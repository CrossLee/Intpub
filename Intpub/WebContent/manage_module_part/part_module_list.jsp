<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<table width="99%" border="0" align="center" cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
	  			<th>名称</th>
	  			<th>状态</th>
	  			<th>备份</th>
	  			<th>排序</th>
	  			<th>操作</th>
  			</tr>
  			<s:iterator value="pageDTO.list" var="p" status="s">
  			<tr  class="td_title_2">
	  			<td><s:property value="partName"/></td>
	  			<td><!--<s:property value="status"/>-->
	  				<s:if test='status==1'>
		  			启用
		 	        </s:if> <s:else>
		 	                    禁用
			        </s:else>
	  			</td>
	  			<td><s:property value="backup"/></td>
	  			<td><s:property value="sort"/></td>
	  			<td><input type="button" value="增加"  class="button_class" onclick="javascript:PartModule.initAddModulesPage(<s:property value='partCode'/>);"></input></td>
  			</tr>
  			</s:iterator>
  		</table>
  		<table align="center" width="99%" border="0" align="center" >
			<tr>
				<td align="center">
				共<font color="#CC3333"><s:property value="pageDTO.totalRecords" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;每页<font color="#CC3333">
				<s:property value="pageDTO.pageSize" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;第<font color="#CC3333"><s:property value="pageDTO.pageNo" />/<s:property value="pageDTO.getTotalPages()" /></font>页 
				<s:if test="pageDTO.pageNo==1&&pageDTO.getTotalPages()==1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:if> <s:elseif test="pageDTO.pageNo==1&&pageDTO.getTotalPages()!=1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<a style="cursor:hand" href="javascript:void()" onclick="PartModule.queryParts('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void()" onclick="PartModule.queryParts('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:elseif> <s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
					<a style="cursor:hand" href="javascript:void()" onclick="PartModule.queryParts(1)">首页</a>
					<a style="cursor:hand" href="javascript:void()" onclick="PartModule.queryParts('${pageNo-1}')">上一页</a>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
		
				</s:elseif> <s:else>
					<a style="cursor:hand" href="javascript:void()" onclick="PartModule.queryParts(1)">首页</a>
					<a style="cursor:hand" href="javascript:void()" onclick="PartModule.queryParts('${pageNo-1}')">上一页</a>
					<a style="cursor:hand" href="javascript:void()" onclick="PartModule.queryParts('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void()" onclick="PartModule.queryParts('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
				 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳转" onclick="PartModule.queryParts($('#pageNoPro').val())"/></td>
			</tr>
		</table>
</html>
