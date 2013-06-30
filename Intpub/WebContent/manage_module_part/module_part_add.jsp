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

</head>

<body>
<div class="man_zoneCls">
<form action="" name="addModulePartsForm" id="addModulePartsForm">
		<input type="hidden" name="selectedModuleCode" id="selectedModuleCode" value="${selectedModule.moduleCode }"/>
  		<!-- <table width="99%" border="0" align="center" cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
  				<td class="left_title_1"><span class="left-title">名称</span></td>
  				<td><s:property value="selectedModule.moduleName"/></td>
  			</tr>
  			<tr>
  				<td class="left_title_2"><span class="left-title">描述</span></td>
  				<td><s:property value="selectedModule.description"/></td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">状态</span></td>
  				<td>
					<s:if test='selectedModule.status==1'>
		  				启用
		 	        </s:if>
		 	        <s:else>
		 	                                禁用
			        </s:else>
				</td>
  			</tr>
  			<tr>
  				<td class="left_title_2"><span class="left-title">数量</span></td>
  				<td><s:property value="selectedModule.amount"/></td>
  			</tr>
  			<tr>
  				<td class="left_title_2"><span class="left-title">显示长度</span></td>
  				<td><s:property value="selectedModule.displayLen"/></td>
  			</tr>
  		</table> -->
		<table width="99%" border="0" align="center" cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
  				<td class="left_title_1"><span class="left-title">请选择栏目</span></td>
  			</tr>
  			<tr>
  				<td>
  				
  					<s:select id="deletePartsList" name="deletePartsList" ondblclick="javascript:ModulePart.addPart();" list="needToAddParts" listKey="partCode" listValue="partName" multiple="true" cssStyle="width: 150px;height:200px;"></s:select>
  					<br/>
                    <input type="button" class="button_class" value="添加到右边" onclick="javascript:ModulePart.addPart();"/>
  				</td>
  				<td>
	  				<s:select id="addPartsList" name="addPartsList" ondblclick="javascript:ModulePart.deletePart();" list="selectedModule.parts" listKey="partCode" listValue="partName" multiple="true" cssStyle="width: 150px;height:200px;"></s:select>
	  				<br/>
  					<input type="button" class="button_class" value="删除到左边" onclick="javascript:ModulePart.deletePart();"/>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<input type="button" class="button_class" value="提交" onclick="javascript:ModulePart.addModuleParts()"/>
  				</td>
  			</tr>
  		</table>
</form>
</div>
</body>
</html>
