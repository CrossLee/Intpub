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

<title>info list</title>

</head>

<body>
<table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
	  			<th width="27%">标题</th>
	  			<th width="18%">栏目名称</th>
	  			<th width="11%">状态</th>
	  			<th width="23%">创建时间</th>
	  			<th width="21%">操作</th>
  			</tr>
  			<s:iterator value="pageDTO.list" var="info" status="s">
  			    <tr class="td_title_2">
	  			<td height="20">${info.title}</td>
	  			<td >${info.codeName}</td>
	  			<td>
	  			<s:if test='#info.status==1'>
	  			  上载
	 	        </s:if>
	 	        <s:else>
	 	                       发布
		        </s:else>
	 	        </td>
	 	        <td>${info.createDate}</td>
	 	       <s:if test='%{type=="upload"}'>
	  				<td>
	  				<input type="button" value="查  看" class="button_class_new" onclick="javascript:InfoUser.look('${info.infoId}');"></input>
	  				<input type="button" value="修  改" class="button_class_new" onclick="javascript:InfoUser.updateByUpload('${info.infoId}');"></input>
	  				<input type="button" value="删  除" class="button_class_new" onclick="javascript:InfoUser.dels('${info.infoId}');"></input>
	  				</td>
  			   </s:if>
  			   <s:if test='%{type=="publish"}'>
	  				<td>
	  				<input type="button" value="查  看" class="button_class_new" onclick="javascript:PubInfo.look('${info.infoId}');"></input>
	  				<input type="button" value="发  布" class="button_class_new" onclick="javascript:PubInfo.updateByUpload('${info.infoId}',${info.status});"></input>
	  				<input type="button" value="删  除" class="button_class_new" onclick="javascript:PubInfo.dels('${info.infoId}');"></input>
	  				</td>
  			   </s:if>
  			</tr>
  			</s:iterator>
  		</table>
 <s:if test='%{type=="upload"}'>
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
			<a style="cursor:hand" href="javascript:void()" onclick="InfoUser.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="InfoUser.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:elseif> <s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
			<a style="cursor:hand" href="javascript:void()" onclick="InfoUser.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="InfoUser.query('${pageNo-1}')">上一页</a>
			<font color="#999999">下一页</font>
			<font color="#999999">尾页</font>

		</s:elseif> <s:else>
			<a style="cursor:hand" href="javascript:void()" onclick="InfoUser.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="InfoUser.query('${pageNo-1}')">上一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="InfoUser.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="InfoUser.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
		 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳  转" class="button_class" onclick="InfoUser.query($('#pageNoPro').val())"/></td>
	</tr>
</table>
</s:if>
 <s:if test='%{type=="publish"}'>
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
			<a style="cursor:hand" href="javascript:void()" onclick="PubInfo.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="PubInfo.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:elseif> <s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
			<a style="cursor:hand" href="javascript:void()" onclick="PubInfo.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="PubInfo.query('${pageNo-1}')">上一页</a>
			<font color="#999999">下一页</font>
			<font color="#999999">尾页</font>

		</s:elseif> <s:else>
			<a style="cursor:hand" href="javascript:void()" onclick="PubInfo.query(1)">首页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="PubInfo.query('${pageNo-1}')">上一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="PubInfo.query('${pageNo+1}')">下一页</a>
			<a style="cursor:hand" href="javascript:void()" onclick="PubInfo.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
		</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
		 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳  转" class="button_class" onclick="PubInfo.query($('#pageNoPro').val())"/></td>
	</tr>
</table>
</s:if>
</body>
</html>
