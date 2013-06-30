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
	<script language="JavaScript">
		
		var contextPath = "<%=basePath%>";
	
		var ModuleList = new function(){
			// validate username & password
			this.validate = function(){
				var moduleName = $('#moduleName').val();
				var description = $('#description').val();
				if(moduleName == ''){
					alert("Please input module name");
					return false;	
				}
				if(description == ''){
					alert("Please input description");
					return false;
				}
				return true;
			};
			// login request
			this.toDetail = function(moduleCode){
				
				$.ajax({
					type:"POST",
					url:"/Intpub/modulespace/queryModuleDetail.action?selectedModuleCode=" + moduleCode,
					dataType:"HTML",
					data:$('#moduleListForm').serialize(),
					success:function(data){
						$("#moduleDetail").html(data);
						popupAlert("moduleDetail",400,200,null);
					},
					error:function(){
						alert("request error");
					}
				});
				
				//window.location.href = contextPath + "modulespace/toModuleUpdate.action?selectedModuleCode=" + moduleCode;
			};
			this.queryModules = function(pageNo){
				$.ajax({
					type:"POST",
					url:"/Intpub/modulespace/queryModuleByCondition.action?returnType=queryList&pageNo="+pageNo,
					dataType:"HTML",
					data:$('#moduleListForm').serialize(),
					success:function(data){
							$("#moduleList").html(data);
							$("#moduleDetail").html("");
					},
					error:function(){
						alert("request error");
					}
				});
			};
		};
		$(document).ready(function(){
			// get validate code
		});
	</script>
  </head>
  
  <body>
  	<div id="man_zone">
  	<form action="" id="moduleListForm" name="moduleListForm" method="post">
  		<table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
  				<td class="left_title_1"><span class="left-title">名称</span></td>
  				<td><input type="text" id="moduleName" name="queryCondition.moduleName" /></td>
  			
  				<td class="left_title_1"><span class="left-title">描述</span></td>
  				<td><input type="text" id="description" name="queryCondition.description" /></td>
  				<td class="left_title_1"><span class="left-title">状态</span></td>
  				<td>
  					<select id="status" name="queryCondition.status">
  						<option value="0">-请选择-</option>
			        	<option value="1">是</option>
			        	<option value="2">否</option>
			        </select>
  					<!--<s:select name="module.status" id="status" list="{1,2,3}"></s:select>-->
  				</td>
  			
  				<td colspan="2"><input type="button" class="button_class" value="查询" onclick="javascript:ModuleList.queryModules(1);"/></td>
  			</tr>
  		</table>
  		<div id="moduleList">
  		</div>
  		<div id="moduleDetail" style="display: none">
  		</div>
  	</form>
  	</div>
  </body>
</html>
