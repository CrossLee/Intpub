<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User Detail</title>
<script type="text/javascript" src="../js/jquery-1.7.2.min.js"></script>
<script language="JavaScript">

	var RealUpdateUser = new function(){
		this.update = function(){
			$.ajax({
				type:"POST",
				url:"/Intpub/userspace/updateUser.action",
				dataType:"JSON",
				data:$('#updateUserForm').serialize(),
				success:function(data){
					if(data.loginState == 'updateSuccess'){
						alert("更新用户成功!");
						self.close();
						
					}else{
						alert("更新用户失败!");
					}	
				},
				error:function(){
					alert("request error");
				}
			});
		};
	};
	$(document).ready(function(){
		$("#status").val("${user.status}");
	});
</script>

</head>
<body>
<form id="updateUserForm" action="../userspace/updateUser.action" method="post">
<div id="man_zone">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <tr>
      <td width="18%" class="left_title_1"><span class="left-title">用户名称</span></td>
      <td width="82%"><input id="username" name="user.userName" type="text" value="${user.userName}" readonly="readonly" /></td>
    </tr>
    <tr>
      <td class="left_title_2">用户帐号</td>
      <td><input id="usercode" name="user.userCode" type="text" value="${user.userCode}" readonly="readonly" /></td>
    </tr>
    <tr>
     	 	<td class="left_title_1">旧  密   码</td>
      		<td><input id="password" name="user.password" type="password" value="${user.password}" readonly="readonly" /></td>
    </tr>
    <tr>
      <td class="left_title_1">是否启用</td>
      <td><select id="status" name="user.status" >
             <option value="1">是</option>
             <option value="2">否</option>
          </select>
      </td>
    </tr>
    <s:if test="%{flag=='updateByQuery'}">
     	<tr>
      		<td class="left_title_2">&nbsp;</td>
      		<td><input name="sumbit" class="button_class" value="提  交" type="button" onclick="RealUpdateUser.update()" /><input name="reset" class="button_class" value="重  置" type="button"/></td>
     	</tr>
    </s:if>
    
  </table>
</div>
		<input type="hidden" value="${user.id}" name="user.id" />
</form>
</body>
</html>