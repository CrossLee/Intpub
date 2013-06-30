<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query User</title>
<script language="JavaScript">

	var RoleUser = new function(){
		this.validate = function(){
			
			if($("#addUserList").children().length==0){
				alert("保存成功!");
				return false;	
			}
			
			return true;
		};
		this.query = function(){
			
			var role=$("#rolecode").val();
			if($.trim(role)==""){
				role="10003";
			}	
			$.ajax({
				type:"POST",
				url:"/Intpub/userspace/queryUser.action",
				data:"flag=roleUser&rolecode="+role,
				dataType:"HTML",
				success:function(data){
				    $("#roleDiv").html(data);
				},
				error:function(){
					alert("request error");
				}
			});
		};
		this.addUserRole = function(){
			
			if(!this.validate()){
				return false;
			}
			$("#addUserList").children().each(function()
					{
						$(this).attr("selected","selected");
			});
			
			$.ajax({
				type:"POST",
				url:"/Intpub/userspace/modifyUser.action",
				data:$('#UserRoleForm').serialize(),
				dataType:"JSON",
				success:function(data){
					if(data.loginState == 'hasRole'){
					    alert(data.username+"已经拥有其他角色!");
					}else if(data.loginState == 'modifySuccess'){
					    alert("角色分配成功!");
					}else if(data.loginState == 'modifyError'){
					    alert("角色分配失败!");
					}
				},
				error:function(){
					alert("request error");
				}
			});
		};

		this.addUser = function()
        {
           $("#deleteUserList option:selected").remove().appendTo('#addUserList');
        };
        this.deleteUser = function()
        {
           $("#addUserList option:selected").remove().appendTo('#deleteUserList');
        };

		
	};
	$(document).ready(function(){
		RoleUser.query();
	});

	
	
</script>

</head>
<body>
<form id="UserRoleForm" name="UserRoleForm" action="" method="post">
<div id="man_zone">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <tr>
      <td colspan="2" class="left_title_center"  align="center"><span  style="align:'center'">角色名称</span>
       <select id="rolecode" name="rolecode" onchange="RoleUser.query()">
       		<c:forEach items="${roleMap}" var="obj">
             	 <option value="${obj.key}">${obj.value}</option>
        	</c:forEach>
        </select>
       </td>
    </tr>
    
  </table>
  <br/>
    <div id="roleDiv">
       
    </div>
</div>
</form>

</body>
</html>