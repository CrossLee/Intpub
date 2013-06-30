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
	<script type="text/javascript" src="js/common.js"></script>
	<script language="JavaScript">
		
		var contextPath = "<%=basePath%>";
	
		var PartModule = new function(){
			this.queryParts = function(pageNo){
				$.ajax({
					type:"POST",
					url:"/Intpub/modulepartspace/queryPartsByCondition.action?pageNo="+pageNo,
					dataType:"HTML",
					data:$('#partModuleListForm').serialize(),
					success:function(data){
							$("#partList").html(data);
						//}
					},
					error:function(){
						alert("request error");
					}
				});
			};
			this.initAddModulesPage = function(partCode)
			{
				$.ajax({
					type:"POST",
					url:"/Intpub/modulepartspace/initAddModulesPage.action?selectedPartCode="+partCode,
					dataType:"HTML",
					data:$('#addPartModulesForm').serialize(),
					success:function(data){
						alertWin("Parts", data, 500,200);
						
					},
					error:function(){
						alert("request error");
					}
				});
			};
			this.addPartModules = function(){

				// $("addPartsList").children().each(function(){$(this).attr("selected","selected")})
				$("#addModulesList").children().each(function()
				{
					$(this).attr("selected","selected");
				});
				
				$.ajax({
					type:"POST",
					url:"/Intpub/modulepartspace/addModulesToPart.action?pageNo=1",
					dataType:"HTML",
					data:$('#addPartModulesForm').serialize(),
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
			this.addModule = function()
			{
				$("#deleteModulesList option:selected").remove().appendTo('#addModulesList');
			};
			this.deleteModule = function()
			{
				$("#addModulesList option:selected").remove().appendTo('#deleteModulesList');
			};
		};
		$(document).ready(function(){
			// get validate code
		});
	</script>
  </head>
  
  <body>
  	<div id="man_zone">
  	<form action="" id="partModuleListForm" name="partModuleListForm" method="post">
  		<table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
  				<td class="left_title_1"><span class="left-title">名称</span></td>
  				<td><input type="text" id="partName" name="queryPartCondition.partName" /></td>
  				<td class="left_title_1">状态</td>
  				<td>
  					<select id="status" name="queryPartCondition.status">
  						<option value="0">-请选择-</option>
			        	<option value="1">启用</option>
			        	<option value="2">禁用</option>
			        </select>
  				</td>
  				<td colspan="2"><input type="button" class="button_class" value="查询" onclick="javascript:PartModule.queryParts(1);"/></td>
  			</tr>
  		</table>
  		<div id="partList">
  		
  		</div>
  		
  	</form>
  	</div>
  </body>
</html>
