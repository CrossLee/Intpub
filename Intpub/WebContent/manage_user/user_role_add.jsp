<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>user list</title>

</head>

<body>
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
      <tr>
               <td   align="center" style="align:center"><span style="align:center;font-weight:bold" >未分配的用户</span></td>
               <td  align="center" ><span class="left-title;font-weight:bold">角色中的用户</span></td>
      </tr>
           <tr>
               <td align="center">
                  <s:select id="deleteUserList" name="deleteUserList" ondblclick="javascript:RoleUser.addUser();" list="noUserMap"  multiple="true" cssStyle="width: 150px;height:200px;"></s:select>
                  <br/>
                    <input type="button" class="button_class" style="width:140px" value="添加到右边" onclick="javascript:RoleUser.addUser();"/>
               </td>
               <td align="center">
                   <s:select id="addUserList" name="addUserList" ondblclick="javascript:RoleUser.deleteUser();" list="userMap"  multiple="true" cssStyle="width: 150px;height:200px;"></s:select>
                   <br/>
                  <input type="button" class="button_class" style="width:140px" value="删除到左边" onclick="javascript:RoleUser.deleteUser();"/>
               </td>
           </tr>
          
  </table>
  
   <table border="0" width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
      <tr><td align="center" colspan="4">
            &nbsp;&nbsp;&nbsp;&nbsp;<input name="button" class="button_class" value="保存" type="button"  onclick="RoleUser.addUserRole()" />
          </td>
      </tr>
  </table>
</body>
</html>
