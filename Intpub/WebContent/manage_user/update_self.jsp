<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Detail</title>
<script language="JavaScript">

	var RealUpdateSelf= new function(){
		this.validate = function(){
			var username = $('#username').val();
			
			if(username == ''){
				alert("请输入新用户名!");
				return false;	
			}
			if($("#ifPass").attr("checked")){
				var oldPass = $('#oldPass').val();
				if(oldPass == ''){
					alert("请输入原来的密码!");
					return false;	
				}
				if(oldPass != '${session.userDTO.password}'){
					alert("输入的旧密码不正确!");
					return false;	
				}
				var password = $('#pass').val();
				if(password == ''){
					alert("请输入新密码!");
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
					alert("请再输入一次密码!");
					return false;	
				}
				if(password != compassword){
					alert("两次输入不一致!");
					return false;	
				}
			 }
			return true;
		};

		
		
		this.update = function(){
			if(!this.validate()){
				return false;
			}
			if($("#ifPass").attr("checked")){
				$("input[name='user.password']").val($("#pass").val());
			}else{
				 $("input[name='user.password']").val("${session.userDTO.password}");
			}
			$.ajax({
				type:"POST",
				url:"/Intpub/userspace/updateUser.action",
				dataType:"JSON",
				data:$('#updateSelfForm').serialize(),
				success:function(data){
					if(data.loginState == 'updateSuccess'){
						//if($("#ifPass").attr("checked")){
							alert("更新用户成功,请重新登录!");
							window.location.href="../Intpub/login.jsp";
						//}else{
						//	alert("更新用户成功!");
						//}	
					}else{
						alert("更新用户失败!");
					}	
				},
				error:function(){
					alert("request error");
				}
			});
		};
		this.displayPass=function(){
			if($("#ifPass").attr("checked")){
                  $(".passTr").css("display","");
			}else{
				 $(".passTr").css("display","none");
			}
		};
		this.clearText=function(){
			$("#username").val("${user.userName}");
			$("#oldPass").val("");
			$("#pass").val("");
			$("#compassword").val("");
			//$("#ifPass").attr("checked","checked");
		};	
	};
	$(document).ready(function(){
		
	});
</script>

</head>
<body>
<form id="updateSelfForm"  method="post">
<div id="man_zone">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <tr>
      <td width="18%" class="left_title_1"><span class="left-title">用户名称</span></td>
      <td width="82%"><input id="username" name="user.userName" type="text" value="${user.userName}" /></td>
    </tr>
    <tr>
      <td class="left_title_2">用户帐号</td>
      <td><input id="usercode" name="user.userCode" type="text" value="${user.userCode}" readonly="readonly" /><input type="checkbox" id="ifPass" checked="checked" onchange="RealUpdateSelf.displayPass()"/>是否修改密码</td>
    </tr>
    	<tr class="passTr">
     	 	<td class="left_title_1">旧  密   码</td>
      		<td><input id="oldPass" name="oldPass" type="password" value="" /></td>
    	</tr>
    <tr class="passTr">
      <td class="left_title_2"> 新  密   码</td>
      <td><input id="pass" name="pass" type="password" value="" /></td>
    </tr>
   	 <tr class="passTr">
      	<td class="left_title_1">确认密码</td>
     	<td><input id="compassword" name="comfirmpass" type="password" value="" /></td>
    </tr>
     	<tr>
      		<td class="left_title_2">&nbsp;</td>
      		<td><input name="sumbit" class="button_class" value="提  交" type="button" onclick="RealUpdateSelf.update()" /><input name="reset" class="button_class" value="重  置" type="button" onclick="RealUpdateSelf.clearText()"/></td>
     	</tr>
    
  </table>
</div>
		<input type="hidden" value="${user.id}" name="user.id" />
		<input type="hidden" value="1" name="user.status" />
		<input type="hidden" value="" name="user.password" />
</form>
</body>
</html>