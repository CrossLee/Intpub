<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>user list</title>

</head>

<body>
<table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
	  			<th>用户帐号</th>
	  			<th>用户名称</th>
	  			<th>状态</th>
	  			<th>用户角色</th>
	  			<th>创建时间</th>
	  			<th>操作</th>
  			</tr>
  			<s:iterator value="pageDTO.list" var="user" status="s">
  			    <tr class="td_title_2">
	  			<td>${user.userCode}</td>
	  			<td>${user.userName}</td>
	  			<td>
	  			<s:if test='#user.status==1'>
	  			启用
	 	        </s:if>
	 	        <s:else>
	 	                    禁用
		        </s:else>
	 	        </td>
	 	        <td>${user.roleName}</td>
	 	        <td>${user.createDate}</td>
	 	        <s:if test='%{flag=="query"}'>
	  				<td><input type="button" value="查  看" class="button_class" onclick="javascript:QueryUser.look('${user.userCode}');"></input></td>
  			    </s:if>
  			     <s:if test='%{flag=="update"}'>
	  				<td><input type="button" value="修  改" class="button_class" onclick="javascript:UpdateUser.update('${user.userCode}');"></input></td>
  			    </s:if>
  			     <s:if test='%{flag=="delete"}'>
	  				<td><input type="button" value="删  除" class="button_class" onclick="javascript:DeleteUser.dels('${user.id}');"></input></td>
  			    </s:if>
  			</tr>
  			</s:iterator>
  		</table>
  		<s:if test='%{flag=="query"}'>
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
			<a style="cursor:hand" href="javascript:void()" onclick="QueryUser.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="QueryUser.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:elseif> <s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
			<a style="cursor:hand" href="javascript:void()" onclick="QueryUser.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="QueryUser.query('${pageNo-1}')">上一页</a>
			<font color="#999999">下一页</font>
			<font color="#999999">尾页</font>

		</s:elseif> <s:else>
			<a style="cursor:hand" href="javascript:void()" onclick="QueryUser.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="QueryUser.query('${pageNo-1}')">上一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="QueryUser.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="QueryUser.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
		 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳  转" class="button_class" onclick="QueryUser.query($('#pageNoPro').val())"/></td>
	</tr>
</table>
  		</s:if>
  		<s:if test='%{flag=="update"}'>
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
			<a style="cursor:hand" href="javascript:void()" onclick="UpdateUser.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="UpdateUser.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:elseif> <s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
			<a style="cursor:hand" href="javascript:void()" onclick="UpdateUser.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="UpdateUser.query('${pageNo-1}')">上一页</a>
			<font color="#999999">下一页</font>
			<font color="#999999">尾页</font>

		</s:elseif> <s:else>
			<a style="cursor:hand" href="javascript:void()" onclick="UpdateUser.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="UpdateUser.query('${pageNo-1}')">上一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="UpdateUser.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="UpdateUser.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
		 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳  转" class="button_class" onclick="UpdateUser.query($('#pageNoPro').val())"/></td>
	</tr>
</table>
  		</s:if>
  			     <s:if test='%{flag=="delete"}'>
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
			<a style="cursor:hand" href="javascript:void()" onclick="DeleteUser.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="DeleteUser.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:elseif> <s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
			<a style="cursor:hand" href="javascript:void()" onclick="DeleteUser.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="DeleteUser.query('${pageNo-1}')">上一页</a>
			<font color="#999999">下一页</font>
			<font color="#999999">尾页</font>

		</s:elseif> <s:else>
			<a style="cursor:hand" href="javascript:void()" onclick="DeleteUser.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="DeleteUser.query('${pageNo-1}')">上一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="DeleteUser.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="DeleteUser.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
		 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳  转" class="button_class" onclick="DeleteUser.query($('#pageNoPro').val())"/></td>
	</tr>
</table>
  			    </s:if>

</body>
</html>
