<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Role</title>

<script type="text/javascript">
	var DeleteRole = new function(){
		this.deleteRole = function(roleCode){
			if(window.confirm("此操作不可恢复，确认要删除吗？")){
				$.ajax({
					type:"POST",
					url:"/Intpub/rolespace/deleteRoleAction.action",
					dataType:"JSON",
					data:"roleCode="+roleCode,
					success:function(data){
						if(data.operationState == true){
							alert("删除成功");
							LeftFrame.changeframe('删除角色信息', '角色基本管理', 'rolespace/jspDeleteAction.action');
						}
						/*
						if(data.loginState == 'success'){
							window.location.href = "index.jsp";
						}
						*/
					},
					error:function(){
						alert("request error");
					}
				});
			}else{
			}
		};


		// paging
		this.query = function(pageno){

		    if(!((/^(\+|-)?\d+$/.test( pageno ))|| pageno<0)){  
		    	alert("页数中请输入正整数！");
		        return;
		    } 
			
			var rolename=$("#rolename").val();
			var status=$("#status").val();
			$.ajax({
				type:"POST",
				url:"/Intpub/rolespace/queryRoleAction.action",
				data:"flag=delete&pageNo="+pageno+"&rolename="+rolename+"&status="+status,
				dataType:"HTML",
				success:function(data){
						$("#rolePageDiv").html(data); 
				},
				error:function(){
					alert("request error");
				}
			});
		};

		// search paging
		this.queryBySearch = function(pageno){
		    if(!((/^(\+|-)?\d+$/.test( pageno ))|| pageno<0)){
		    	alert("页数中请输入正整数！");
		        return;
		    }
			
			var rolename=$("#rolename").val();
			var status=$("#status").val();
			$.ajax({
				type:"POST",
				url:"/Intpub/rolespace/queryRoleAction.action",
				data:"flag=query&pageNo="+pageno+"&rolename="+rolename+"&status="+status,
				dataType:"HTML",
				success:function(data){
					$("#rolePageDiv").html(data);
				},
				error:function(){
					alert("request error");
				}
			});
		};
	};

	$(document).ready(function(){
		DeleteRole.query(1);
	});
</script>

</head>
<body>
<div id="man_zone">
<form id="queryUserForm" action="../userspace/queryUser.action"
	method="post">

<table width="99%" border="0" align="center" cellpadding="3"
	cellspacing="1" class="table_style">
	<tr>
		<td width="14%" class="left_title_1"><span class="left-title">查询关键字</span></td>
		<td width="20%"><input id="username" name="user.userName"
			type="text" value="" /></td>
		<td width="14%" class="left_title_1">是否启用</td>
		<td width="16%"><select id="status" name="user.status"
			style="width: 90px">
			<option value="0">-请选择-</option>
			<option value="1">是</option>
			<option value="2">否</option>
		</select></td>
		<td width="32%">&nbsp;&nbsp;&nbsp;&nbsp;<input name="sumbit"
			class="button_class" value="查  询" type="button"
			onclick="DeleteUser.query(1)" />&nbsp;&nbsp;&nbsp;&nbsp; <input
			name="reset" class="button_class" value="重  置" type="button" /></td>
	</tr>
</table>
	<br/>
	<div id="rolePageDiv">
	</div>
</form>
</div>
</body>
</html>