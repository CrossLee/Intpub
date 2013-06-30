<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Intpub Login</title>
<link href="css/loginStyle.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script language="JavaScript">

	var Register = new function(){
		// validate username & password
		this.validateRegister = function(){
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
			if(!this.validateRegister()){
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
							data:$('#registerForm').serialize(),
							success:function(data){
								if(data.loginState == 'addSuccess'){
									alert("注册用户成功!");
									 $('#username').val("");
									 $('#usercode').val("");
									 $('#password').val("");
									 $('#compassword').val("");
								}else{
									alert("注册用户失败!");
								}	
							},
							error:function(){
								alert("注册用户失败!");
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
		this.validateLogin = function(){
			var username = $('#user').val();
			var userpwd = $('#pass').val();
			if(username == ''){
				alert("请输入账号!");
				return false;	
			}
			if(userpwd == ''){
				alert("请输入密码!");
				return false;
			}
			return true;
		};
		// login request
		this.login = function(){
			if(!this.validateLogin()){
				return false;
			}
			$.ajax({
				type:"POST",
				url:"/Intpub/userspace/loginUser.action",
				dataType:"JSON",
				data:$('#loginForm').serialize(),
				success:function(data){
					if(data.loginState == 'success'){
						window.location.href = "index.jsp";
					}else if(data.loginState == 'noRole'){
						alert("您没有权限!");
					}else{
						alert("帐号或者密码错误!");
					}
				},
				error:function(){
					alert("登陆错误，请联系管理员!");
				}
			});
		};
		this.clearText=function(){
			$('#user').val("");
			$('#pass').val("");
		}
	};
	$(document).ready(function(){
		// get validate code
		$('#user').focus();
		$('#pass').bind('keydown',function(e){
			var key = e.which;
			if(key == 13){
				Login.login();
			}
	    });
	});
</script>

</head>
<body class="body">
<table width="100%" height="166" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td height="42" valign="top">
			<table width="100%" height="42" border="0" cellpadding="0" cellspacing="0" class="login_top_bg">
				<tr>
					<td width="1%" height="21">&nbsp;</td>
					<td height="42">&nbsp;</td>
					<td width="17%">&nbsp;</td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td valign="top">
		<table width="100%" height="532" border="0" cellpadding="0"	cellspacing="0" class="login_bg">
			<tr>
				<td width="49%" align="right" valign="bottom">
					<table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
						
						<tr>
							<td width="30%" colspan="2">&nbsp;</td>
						</tr>
						<tr>
							<td width="30%">&nbsp;</td>
							<td width="70%" height="38"><span class="login_txt_bt">注&nbsp;&nbsp;&nbsp;&nbsp; 册</span></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td height="21" align="right">
								<table cellSpacing="0" cellPadding="0" width="100%" border="0" align="right" height="328">
									<tr>
										<td height="164" colspan="2" align="right">
											<form id="registerForm" name="registerForm" method="post">
												<table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" >
													<tr>
														<td width="17%" height="38">
															<span class="login_txt">用户名称：&nbsp;&nbsp;</span>
														</td>
														<td height="38" colspan="2" align="left">
														    <input id="status"  type="hidden" name="user.status" value="1">
															<input id="username" name="user.userName" value="" size="20">
														</td>
													</tr>
													<tr>
														<td width="17%" height="38">
															<span class="login_txt">用户帐号：&nbsp;&nbsp;</span>
														</td>
														<td height="38" colspan="2" align="left">
															<input id="usercode" name="user.userCode" value="" size="20">
														</td>
													</tr>
													<tr>
														<td width="17%" height="35">
															<span class="login_txt">密          码： &nbsp;&nbsp; </span>
														</td>
														<td height="35" colspan="2" align="left">
															<input type="password" size="20" id="password" name="user.password" value="">
															<img src="images/luck.gif" width="19" height="18">
														</td>
													</tr>
													<tr>
														<td width="17%" height="35">
															<span class="login_txt">确认密码： &nbsp;&nbsp; </span>
														</td>
														<td height="35" colspan="2" align="left">
															<input type="password" size="20" id="compassword" name="comfirmpass" value="">
															<img src="images/luck.gif" width="19" height="18">
														</td>
													</tr>
													<tr>
														<td height="35">&nbsp;</td>
														<td width="20%" height="35" align="left">
															<input onclick="javascript:Register.add();" style="cursor:pointer" name="button" type="button"  value="注 册" />
														</td>
														<td width="60%" align="left">
														</td>
													</tr>
												</table>
											</form>
										</td>
									</tr>
									<tr>
										<td width="433" height="164" align="right" valign="bottom">
											&nbsp;
										</td>
										<td width="57" align="right" valign="bottom">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
				<td width="1%">&nbsp;</td>
				<td width="50%" valign="bottom">
					<table width="100%" height="59" border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="4%">&nbsp;</td>
							<td width="96%" height="38"><span class="login_txt_bt">登陆信息网后台管理</span></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
							<td height="21">
								<table cellSpacing="0" cellPadding="0" width="100%" border="0" id="table211" height="328">
									<tr>
										<td height="164" colspan="2" align="center">
											<form id="loginForm" name="loginForm" method="post">
												<table cellSpacing="0" cellPadding="0" width="100%" border="0" height="143" id="table212">
													<tr>
														<td width="13%" height="38">
															<span class="login_txt">管理员：&nbsp;&nbsp;</span>
														</td>
														<td height="38" colspan="2" align="left">
															<input id="user" name="username" value="" size="20">
														</td>
													</tr>
													<tr>
														<td width="13%" height="35">
															<span class="login_txt">密 码： &nbsp;&nbsp; </span>
														</td>
														<td height="35" colspan="2" align="left">
															<input type="password" size="20" id="pass" name="userpwd" value="">
															<img src="images/luck.gif" width="19" height="18">
														</td>
													</tr>
													<tr>
														<td height="35">&nbsp;</td>
														<td width="10%" height="35" align="left">
															<input onclick="javascript:Register.login();" style="cursor:pointer" name="submit" type="button" id="submit" value="登 陆" />
														</td>
														<td width="67%" align="left">
															<input name="reset" type="reset" style="cursor:pointer" id="reset" value="重 置" onclick="javascript:Register.clearText();" />
														</td>
													</tr>
												</table>
											</form>
										</td>
									</tr>
									<tr>
										<td width="433" height="164" align="right" valign="bottom">
											<img src="images/login-wel.gif" width="242" height="138">
										</td>
										<td width="57" align="right" valign="bottom">&nbsp;</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td height="20">
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="login-buttom-bg">
			<tr>
				<td align="center">
					<span class="login-buttom-txt">Copyright &copy; 2010-2012 www.withiter.com</span>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>
</body>
</html>
