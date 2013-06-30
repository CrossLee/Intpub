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
	
		var PartAdd = new function(){
			// validate username & password
			this.validate = function(){
				var partName = $('#partName').val();
				var description = $('#backup').val();
				if(partName == ''){
					alert("Please input part name");
					return false;	
				}
				if(description == ''){
					alert("Please input backup");
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
					url:"/Intpub/partspace/partAdd.action",
					dataType:"HTML",
					data:$('#partAddForm').serialize(),
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
  	<form action="" id="partAddForm" name="partAddForm" method="post">
  		<table width="99%" border="0" align="center" cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
  				<td class="left_title_1"><span class="left-title">名称</span></td>
  				<td><input type="text" id="partName" name="part.partName" /></td>
  			</tr>
  			<tr>
  				<td class="left_title_2"><span class="left-title">备份</span></td>
  				<td><input type="text" id="backup" name="part.backup" /></td>
  			</tr>
  			<tr>
  				<td class="left_title_1"><span class="left-title">状态</span></td>
  				<td>
					<select id="status" name="part.status">
			        	<option value="1">启用</option>
			        	<option value="2">禁用</option>
			        </select>
				</td>
  			</tr>
  			<tr>
  				<td class="left_title_2"><span class="left-title">排序</span></td>
  				<td><input type="text" id="sort" name="part.sort" /></td>
  			</tr>
  			<tr>
  				<td class="left_title_2">&nbsp;</td>
  				<td><input type="button" value="增加" onclick="javascript:PartAdd.add();" class="button_class"/></td>
  			</tr>
  		</table>
  	</form>
  	</div>
  </body>
</html>
