<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Query User</title>
<script language="JavaScript">

	var DeleteUser = new function(){
		
		this.query = function(pageno){
			if(!((/^(\+|-)?\d+$/.test( pageno ))|| pageno<0)){  
			    	alert("页数中请输入正整数！");  
			        return; 
			}
			var username=$("#username").val();
			var status=$("#status").val();
			$.ajax({
				type:"POST",
				url:"/Intpub/userspace/queryUser.action",
				data:"flag=delete&pageNo="+pageno+"&username="+username+"&status="+status,
				dataType:"HTML",
				success:function(data){
						$("#userdelDiv").html(data);
				},
				error:function(){
					alert("request error");
				}
			});
		};
		this.dels = function(oid){
			
			if(window.confirm("确定要删除该帐户吗?")){
				$.ajax({
					type:"POST",
					url:"/Intpub/userspace/deleteUser.action",
					data:"flag=delete&id="+oid,
					dataType:"HTML",
					success:function(data){
					        alert("删除成功!");
							$("#userdelDiv").html(data);
					},
					error:function(){
						alert("删除失败!");
					}
				});
			}
		};

		this.clearText=function(){
			$("#username").val("");
			$("#status").val("0");
		};	
	};
	$(document).ready(function(){
		DeleteUser.query(1);
	});
</script>

</head>
<body>
<form id="queryUserForm" action="../userspace/queryUser.action" method="post">
<div id="man_zone">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <tr>
      <td width="14%" class="left_title_1"><span class="left-title">用户名称</span></td>
      <td width="20%" ><input id="username" name="user.userName" type="text" value="" /></td>
      <td width="14%" class="left_title_1">是否启用</td>
      <td width="16%" ><select id="status" name="user.status" style="width:90px">
             <option value="0">-请选择-</option>
             <option value="1">是</option>
             <option value="2">否</option>
          </select>
      </td>
       <td width="32%">&nbsp;&nbsp;&nbsp;&nbsp;<input name="sumbit" class="button_class" value="查  询" type="button" onclick="DeleteUser.query(1)" />&nbsp;&nbsp;&nbsp;&nbsp;
       <input name="reset" class="button_class" value="重  置" type="button" onclick="DeleteUser.clearText()"/>
      </td>
    </tr>
  </table>
  <br/>
  <div id="userdelDiv">
  
  </div>
</div>
</form>
</body>
</html>