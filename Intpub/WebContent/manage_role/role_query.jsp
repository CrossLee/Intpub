<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">
	var QueryRole = new function(){
		this.details = function(roleCode){
			$.ajax({
				type:"POST",
				url:"/Intpub/rolespace/queryRoleAction.action",
				data:"flag=queryDTO&roleCode="+roleCode,
				dataType:"HTML",
				success:function(data){
					//$("#rolePageDiv").html(data); 
                    alertWin("Role Details",data,600,300);
					
				},
				error:function(){
					alert("request error");
				}
			});
		};


		// paging
		this.query = function(pageno){
		    if(!((/^(\+|-)?\d+$/.test( pageno ))|| pageno<0)){
		    	alert("页数中请输入正整数！");
		        return;
		    }
			
			//var rolename=$("#rolename").val();
			//var status=$("#status").val();
			$.ajax({
				type:"POST",
				url:"/Intpub/rolespace/queryRoleAction.action",
				//data:"flag=query&pageNo="+pageno+"&rolename="+rolename+"&status="+status,
				data:"flag=query&pageNo="+pageno,
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
				data:"flag=queryBySearch&pageNo="+pageno+"&roleName="+rolename+"&status="+status,
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
		QueryRole.query(1);
	});
</script>
<div id="man_zone">
	<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<tr>
			<td width="14%" class="left_title_1"><span class="left-title">查询关键字</span></td>
			<td width="20%"><input id="rolename" name="rolename" id="rolename"
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
				onclick=QueryRole.queryBySearch(1);
	/>&nbsp;&nbsp;&nbsp;&nbsp; <input
				name="reset" class="button_class" value="重  置" type="button" /></td>
		</tr>
	</table>
	<br />
	
	<div id="rolePageDiv">
	</div>
</div>