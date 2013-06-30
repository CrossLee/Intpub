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
<form action="" name="addPartModulesForm" id="addPartModulesForm">
		<input type="hidden" name="selectedPartCode" id="selectedPartCode" value="${selectedPart.partCode }"/>
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
  				
  					<s:select id="deleteModulesList" name="deleteModulesList" ondblclick="javascript:PartModule.addModule();" list="needToAddModules" listKey="moduleCode" listValue="moduleName" multiple="true" cssStyle="width: 150px;height:200px;"></s:select>
  					<br/>
                    <input type="button" class="button_class" value="添加到右边" onclick="javascript:PartModule.addModule();"/>
  				</td>
  				<td>
	  				<s:select id="addModulesList" name="addModulesList" ondblclick="javascript:PartModule.deleteModule();" list="addedModules" listKey="moduleCode" listValue="moduleName" multiple="true" cssStyle="width: 150px;height:200px;"></s:select>
	  				<br/>
  					<input type="button" class="button_class" value="删除到左边" onclick="javascript:PartModule.deleteModule();"/>
  				</td>
  			</tr>
  			<tr>
  				<td>
  					<input type="button" class="button_class" value="提交" onclick="javascript:PartModule.addPartModules()"/>
  				</td>
  			</tr>
  		</table>
</form>
</div>
</body>
</html>
