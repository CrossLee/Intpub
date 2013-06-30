<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Add User</title>
<script language="JavaScript">

	var AddUser = new function(){
		// validate username & password
		this.validate = function(){
			var username = $('#username').val();
			if(username == ''){
				alert("Please input username");
				return false;	
			}
			var usercode = $('#usercode').val();
			if(usercode == ''){
				alert("Please input usercode");
				return false;	
			}
			if(usercode.length<6){
				alert("帐号不能少于6位");
				return false;	
			}
			if(usercode.length>=19){
				alert("帐号不能多于18位");
				return false;	
			}
			var password = $('#password').val();
			if(password == ''){
				alert("Please input password");
				return false;	
			}
			if(password.length<6){
				alert("密码不能少于6位");
				return false;	
			}
			if(password.length>=19){
				alert("密码不能多于18位");
				return false;	
			}
			var compassword = $('#compassword').val();
			if(compassword == ''){
				alert("Please input comfirmpassword");
				return false;	
			}
			if(password != compassword){
				alert("两次输入不一致!");
				return false;	
			}
			return true;
		};
		this.add = function(){
			if(!this.validate()){
				return false;
			}
			var code=$("#usercode").val();
			$.ajax({
				type:"POST",
				url:"/Intpub/userspace/queryUser.action",
				dataType:"JSON",
				data:"flag=isExist&userCode="+code,
				success:function(data){
					if(data.loginState == 'Exists'){
						alert("该帐号已存在!");
					}else{
						$.ajax({
							type:"POST",
							url:"/Intpub/userspace/addUser.action",
							dataType:"JSON",
							data:$('#addUserForm').serialize(),
							success:function(data){
								if(data.loginState == 'addSuccess'){
									alert("添加用户成功!");
									 $('#username').val("");
									 $('#usercode').val("");
									 $('#password').val("");
									 $('#compassword').val("");
								}else{
									alert("添加用户失败!");
								}	
							},
							error:function(){
								alert("request error");
							}
						});
						//$("#addUserForm").submit();
					}	
				},
				error:function(){
					alert("request error");
				}
			});
		};

		this.clearText=function(){
			 $('#username').val("");
			 $('#usercode').val("");
			 $('#password').val("");
			 $('#compassword').val("");
			 $('#status').val("1");
		};	
	};
	$(document).ready(function(){
		
	});
</script>

</head>
<body>
<form id="addUserForm" action="../userspace/addUser.action" method="post">
<div id="man_zone">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <tr>
      <td width="18%" class="left_title_1"><span class="left-title">用户名称</span></td>
      <td width="82%"><input id="username" name="user.userName" type="text" value="" /></td>
    </tr>
    <tr>
      <td class="left_title_2">用户帐号</td>
      <td><input id="usercode" name="user.userCode" type="text" value="" /></td>
    </tr>
    <tr>
      <td class="left_title_1">密          码</td>
      <td><input id="password" name="user.password" type="password" value="" /></td>
    </tr>
    <tr>
      <td class="left_title_2">确认密码</td>
      <td><input id="compassword" name="comfirmpass" type="password" value="" /></td>
    </tr>
    <tr>
      <td class="left_title_1">是否启用</td>
      <td><select id="status" name="user.status">
             <option value="1">是</option>
             <option value="2">否</option>
          </select>
      </td>
    </tr>
    <tr>
      <td class="left_title_2">&nbsp;</td>
      <td><input name="sumbit" value="提  交" class="button_class" type="button" onclick="AddUser.add()" />&nbsp;&nbsp;&nbsp;&nbsp;<input name="reset" value="重  置" class="button_class" type="button" onclick="AddUser.clearText()"/></td>
    </tr>
  </table>
</div>
		
</form>
</body>
</html>