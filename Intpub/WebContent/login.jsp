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

	var Login = new function(){
		// validate username & password
		this.validate = function(){
			var username = $('#username').val();
			var userpwd = $('#password').val();
			if(username == ''){
				alert("Please input username");
				return false;	
			}
			if(userpwd == ''){
				alert("Please input password");
				return false;
			}
			return true;
		};
		// login request
		this.login = function(){
			if(!this.validate()){
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
		this.register = function(){
			window.location.href = "register.jsp";
		};
		this.clearText=function(){
			$('#username').val("");
			$('#password').val("");
		}
	};
	$(document).ready(function(){
		// get validate code
		$('#username').focus();
		$('#password').bind('keydown',function(e){
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
				<td width="49%" align="right">
					<table width="91%" height="532" border="0" cellpadding="0" cellspacing="0" class="login_bg2">
						<tr>
							<td height="138" valign="top">
								<table width="89%" height="427" border="0" cellpadding="0" cellspacing="0">
									<tr><td height="149">&nbsp;</td></tr>
									<tr><td  height="193" align="right" valign="middle"><img	src="images/logo.png" width="279" height="68"></td></tr>
									<tr>
										<td align="right" >
											<table width="100%" border="0" cellpadding="0" cellspacing="0">
												
												<tr>
													<td width="30%" >&nbsp;</td>
													<td width="30%" height="40">
														<img src="images/icon-demo.gif" width="16" height="16">
														<a href="http://www.withiter.com" target="_blank" class="left_txt3">使用说明</a>
													</td>
													<td width="35%">
														<img src="images/icon-login-seaver.gif" width="16" height="16">
														<a href="javascript:void(0);" onclick="Login.register()" class="left_txt3">注         册</a>
													</td>
												</tr>
											</table>
										</td>
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
							<td width="96%" height="38"><span class="login_txt_bt">登陆信息管理系统</span></td>
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
															<input id="username" name="username" value="800312" size="20">
														</td>
													</tr>
													<tr>
														<td width="13%" height="35">
															<span class="login_txt">密 码： &nbsp;&nbsp; </span>
														</td>
														<td height="35" colspan="2" align="left">
															<input type="password" size="20" id="password" name="userpwd" value="000000">
															<img src="images/luck.gif" width="19" height="18">
														</td>
													</tr>
													<tr>
														<td height="35">&nbsp;</td>
														<td width="10%" height="35" align="left">
															<input onclick="javascript:Login.login();" name="submit" type="button" id="submit" value="登 陆" />
														</td>
														<td width="67%" align="left">
															<input name="reset" type="button" id="reset" value="重 置" onclick="javascript:Login.clearText();" />
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
