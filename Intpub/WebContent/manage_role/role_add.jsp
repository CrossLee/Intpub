<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add Role</title>
<script language="JavaScript">

	var AddRole = new function(){
		// validate username & password
		this.validate = function(){
			var rolename = $('#rolename').val();
			if(rolename == ''){
				alert("Please input rolename");
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
				url:"/Intpub/rolespace/addRoleAction.action",
				dataType:"JSON",
				data:$('#addRoleForm').serialize(),
				success:function(data){
					//alert(data.operationState);
					if(data.operationState == true){
						alert("添加成功");
						$('#rolename').val("");
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
		};
	};
	$(document).ready(function(){
		// get validate code
	});
</script>

</head>
<body>

<div id="man_zone">
<form id="addRoleForm">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <tr>
      <td width="18%" class="left_title_1"><span class="left-title">角色名称</span></td>
      <td width="82%"><input id="rolename" name=roleName type="text" value="" /></td>
    </tr>
    <tr>
      <td class="left_title_2">是否启用</td>
      <td><select id="status" name="status">
             <option value="1">是</option>
             <option value="2">否</option>
          </select>
      </td>
    </tr>
    <tr>
      <td class="left_title_1">&nbsp;</td>
      <td>
      	<input class="button_class" onclick="javascript:AddRole.add();" type="button" value="添  加"/>
      	&nbsp;&nbsp;&nbsp;&nbsp;<input value="重  置" class="button_class" type="reset"/>
      </td>
    </tr>
  </table>
  </form>
</div>

	
</body>
</html>