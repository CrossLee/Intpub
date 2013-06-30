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
	
		var ModuleAdd = new function(){
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
			this.add = function(){
				if(!this.validate()){
					return false;
				}
				$.ajax({
					type:"POST",
					url:"/Intpub/modulespace/moduleAdd.action",
					dataType:"HTML",
					data:$('#moduleAddForm').serialize(),
					success:function(data){
						$("#mainDiv").html(data);
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
  	<form action="" id="moduleAddForm" name="moduleAddForm" method="post">
  		<table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
  				<td class="left_title_1"><span class="left-title">模块名称:</span></td>
  				<td><input type="text" id="moduleName" name="module.moduleName" value="${module.moduleName }"/></td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">模块描述:</span></td>
  				<td><input type="text" id="description" name="module.description" /></td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">状态</span></td>
  				<td>
  					<select id="status" name="module.status">
			        	<option value="1">启用</option>
			        	<option value="2">禁用</option>
			        </select>
  				<!--<s:select name="module.status" id="status" list="{1,2,3}"></s:select>-->
  				</td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">栏目数量:</span></td>
  				<td><input type="text" id="amount" name="module.amount" /></td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">显示长度:</span></td>
  				<td><input type="text" id="displayLen" name="module.displayLen" /></td>
  			</tr>
  			<tr>
  				<td class="left_title_2">&nbsp;</td>
  				<td><input type="button" value="增加" class="button_class" onclick="javascript:ModuleAdd.add();"/></td>
  			</tr>
  		</table>
  	</form>
  	</div>
  </body>
</html>
