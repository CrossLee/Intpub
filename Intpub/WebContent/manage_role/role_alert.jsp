<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
<s:if test="%{flag == 'queryDTO'}">
	<div id="man_zone" style="height: auto;">
	<form id="addRoleForm">
	  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
	    <tr>
	      <td width="40%" class="left_title_1"><span class="left-title">角色名称</span></td>
	      <td width="60%">${roleDTO.roleName}</td>
	    </tr>
	    <tr>
	      <td class="left_title_2">是否启用</td>
	      <td>
	      	<s:if test='%{roleDTO.status==1}'>启用</s:if>
	      	<s:else>禁用</s:else>
	      </td>
	    </tr>
	    <tr>
	      <td width="18%" class="left_title_1"><span class="left-title">创建用户</span></td>
	      <td width="82%">${roleDTO.createby}</td>
	    </tr>
	    <tr>
	      <td class="left_title_2">创建日期</td>
	      <td>
	      	${roleDTO.createdate}
	      </td>
	    </tr>
	    <tr>
	      <td width="18%" class="left_title_1"><span class="left-title">修改用户</span></td>
	      <td width="82%">${roleDTO.updateby}</td>
	    </tr>
	    <tr>
	      <td class="left_title_2">修改日期</td>
	      <td>
	      	${roleDTO.updatedate}
	      </td>
	    </tr>
	    
	    <tr>
	      <td colspan="2">
	      	<input class="button_class" onclick="javascript:closeWin();" type="button" value="确  定"/>
	      </td>
	    </tr>
	  </table>
	  </form>
	</div>
</s:if>

<s:if test="%{flag == 'modifyDTO'}">
	<div id="man_zone" style="height: auto;">
	<form id="modifyRoleAlertForm">
	  <input type="hidden" name="roleCode" value="${roleCode }">
	  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
	    <tr>
	      <td width="40%" class="left_title_1"><span class="left-title">角色名称</span></td>
	      <td width="60%"><input type="text" name="roleName" value="${roleDTO.roleName}"></td>
	    </tr>
	    <tr>
	      <td class="left_title_2">是否启用</td>
	      <td>
	      	<select name="status">
	      		<option title="启用" value="1" <s:if test='%{roleDTO.status==1}'>selected="selected"</s:if>>启用</option>
	      		<option title="禁用" value="2" <s:if test='%{roleDTO.status==2}'>selected="selected"</s:if>>禁用</option>
	      	</select>
	      </td>
	    </tr>
	    <tr>
	      <td width="18%" class="left_title_1"><span class="left-title">创建用户</span></td>
	      <td width="82%">${roleDTO.createby}</td>
	    </tr>
	    <tr>
	      <td class="left_title_2">创建日期</td>
	      <td>
	      	${roleDTO.createdate}
	      </td>
	    </tr>
	    <tr>
	      <td width="18%" class="left_title_1"><span class="left-title">修改用户</span></td>
	      <td width="82%">${roleDTO.updateby}</td>
	    </tr>
	    <tr>
	      <td class="left_title_2">修改日期</td>
	      <td>
	      	${roleDTO.updatedate}
	      </td>
	    </tr>
	    
	    <tr>
	      <td colspan="2">
	      	<input class="button_class" onclick="javascript:closeWin();" type="button" value="取  消"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      	<input class="button_class" onclick="javascript:ModifyRole.save();" type="button" value="保  存"/>
	      </td>
	    </tr>
	  </table>
	  </form>
	</div>
</s:if>

<s:if test="%{flag == 'queryMenu'}">
	<div id="alertBoxAddPlaylist" >
		<form id="addPlaylistForm" method="post" name="addPlaylistForm" action="">
		<input id="roleCodeID" type="hidden" value="${roleCode }" />
		<div style="float: left;">
			<div style="margin: 20px 0 0 20px;">请选择其角色对应的菜单：</div>
			<div style="float: left;text-align: center;margin: 0 0 0 20px;">
				<div>未选择菜单</div>
				<s:select ondblclick="select1Dbclick();" multiple="true" id="select1" name="select1" cssStyle="width: 240px;height:314px;" list="unSelectedMenus" listKey="menuCode" listValue="menuName"/>
				<a href="#" id="add" onclick="addClick();" class="move">添加到右边&gt;&gt;</a>
			</div>
			
			<div style="float: left;text-align: center;margin: 0 0 0 10px;">
				<div>已选择菜单</div>
				<s:select ondblclick="select2Dbclick();" multiple="true" id="select2" name="select2" cssStyle="width: 240px;height:314px;" list="menus" listKey="menuCode" listValue="menuName"></s:select>
				<a href="#" id="remove" onclick="removeClick();" class="move">&lt;&lt; 删除到左边</a>
			</div>
		</div>
		<div style="position: relative;right:30px; top:390px;cursor: pointer;">
			<span onclick="javascript:RoleMenu.saveRoleMenu()">保存</span>
		</div>
		</form>
	</div>
</s:if>
</body>
</html>