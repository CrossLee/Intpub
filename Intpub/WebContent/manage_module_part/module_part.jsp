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
    
    <title>Module Part</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script language="JavaScript">
		
		var contextPath = "<%=basePath%>";
	
		var ModulePart = new function(){
			// login request
			this.queryModules = function(pageNo)
			{
				$.ajax({
					type:"POST",
					url:"/Intpub/modulepartspace/queryModulesByCondition.action?returnType=modulePartsList&pageNo="+pageNo,
					dataType:"HTML",
					data:$('#modulePartListForm').serialize(),
					success:function(data){
							$("#moduleList").html(data);
					},
					error:function(){
						alert("request error");
					}
				});
			};
			this.initAddPartsPage = function(moduleCode)
			{
				$.ajax({
					type:"POST",
					url:"/Intpub/modulepartspace/initAddPartsPage.action?selectedModuleCode="+moduleCode,
					dataType:"HTML",
					data:$('#modulePartListForm').serialize(),
					success:function(data){
						alertWin("Parts", data, 500,200);
						
					},
					error:function(){
						alert("request error");
					}
				});
			};
			this.addModuleParts = function(){

				// $("addPartsList").children().each(function(){$(this).attr("selected","selected")})
				$("#addPartsList").children().each(function()
				{
					$(this).attr("selected","selected");
				});
				
				$.ajax({
					type:"POST",
					url:"/Intpub/modulepartspace/addPartsToModule.action?pageNo=1",
					dataType:"HTML",
					data:$('#addModulePartsForm').serialize(),
					success:function(data){
							$("div").remove("#hideDiv");
							$("div").remove("#dispDiv");
							$("#moduleList").html(data);
					},
					error:function(){
						alert("request error");
					}
				});
			};
			this.addPart = function()
			{
				$("#deletePartsList option:selected").remove().appendTo('#addPartsList');
			};
			this.deletePart = function()
			{
				$("#addPartsList option:selected").remove().appendTo('#deletePartsList');
			};
		};
		
	</script>
  </head>
  
  <body>
  	<div id="man_zone">
  	<form action="" id="modulePartListForm" name="modulePartListForm" method="post">
  		<table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
  				<td class="left_title_1"><span class="left-title">名称</span></td>
  				<td><input type="text" id="moduleName" name="queryModuleCondition.moduleName" /></td>
  			
  				<td class="left_title_1"><span class="left-title">描述</span></td>
  				<td><input type="text" id="description" name="queryModuleCondition.description" /></td>
  				<td class="left_title_1"><span class="left-title">状态</span></td>
  				<td>
  					<select id="status" name="queryModuleCondition.status">
  						<option value="0">-请选择-</option>
			        	<option value="1">启用</option>
			        	<option value="2">禁用</option>
			        </select>
  				</td>
  			
  				<td colspan="2"><input type="button" class="button_class" value="查询" onclick="javascript:ModulePart.queryModules(1);"/></td>
  			</tr>
  		</table>
  		<div id="moduleList">
  		</div>
  	</form>
  	</div>
  </body>
</html>
