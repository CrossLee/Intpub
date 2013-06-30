<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<table width="99%" border="0" align="center" cellpadding="3"
		cellspacing="1" class="table_style">
		<tr>
			<th>角色名称</th>
			<th>角色状态</th>
			<th>创建用户</th>
			<th>创建时间</th>
			<th>更新用户</th>
			<th>操作</th>
		</tr>
		<s:iterator value="pageDTO.list" var="role" status="s">
			<tr class="td_title_2">
				<td>${role.roleName}</td>
				<td><s:if test='#role.status==1'>
		  			启用
		 	        </s:if> <s:else>
		 	                    禁用
			        </s:else></td>
				<td>${role.createby}</td>
				<td>${role.createdate}</td> 
				<td>${role.updateby}</td>
				
				<s:if test='%{flag=="query" || flag=="queryBySearch"}'>
				<td><input type="button" value="查  看" class="button_class"
					onclick="javascript:QueryRole.details('${role.roleCode}');" /></td>
				</s:if>
				<s:if test='%{flag=="modify" || flag=="modifyBySearch"}'>
					<td><input type="button" value="修  改" class="button_class"
						onclick="javascript:ModifyRole.modify('${role.roleCode}');" /></td>
				</s:if>
				<s:if test='%{flag=="delete" || flag=="deleteBySearch"}'>
					<td><input type="button" value="删  除" class="button_class"
						onclick="javascript:DeleteRole.deleteRole('${role.roleCode}');" /></td>
				</s:if>
				<s:if test='%{flag=="menu" || flag=="menuBySearch"}'>
					<td><input type="button" value="管理角色" class="button_class"
						onclick="javascript:RoleMenu.manageRoleMenu('${role.roleCode}');" /></td>
				</s:if>
			</tr>
		</s:iterator>
	</table>
	
	<s:if test='%{flag=="query"}'>
	<table align="center" width="99%" border="0" align="center" >
		<tr>
			<td align="center">
				共<font color="#CC3333"><s:property value="pageDTO.totalRecords" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;每页<font color="#CC3333">
				<s:property value="pageDTO.pageSize" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;第<font color="#CC3333"><s:property value="pageDTO.pageNo" />/<s:property value="pageDTO.getTotalPages()" /></font>页 
				<s:if test="pageDTO.pageNo==1&&pageDTO.getTotalPages()==1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:if> 
				<s:elseif test="pageDTO.pageNo==1&&pageDTO.getTotalPages()!=1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.query('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:elseif>
				<s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.query(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.query('${pageNo-1}')">上一页</a>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:elseif>
				<s:else>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.query(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.query('${pageNo-1}')">上一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.query('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
				 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳转" onclick="QueryRole.query($('#pageNoPro').val())"/>
			</td>
		</tr>
	</table>
	</s:if>
	
	<s:if test='%{flag=="queryBySearch"}'>
	<table align="center" width="99%" border="0" align="center" >
		<tr>
			<td align="center">
				共<font color="#CC3333"><s:property value="pageDTO.totalRecords" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;每页<font color="#CC3333">
				<s:property value="pageDTO.pageSize" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;第<font color="#CC3333"><s:property value="pageDTO.pageNo" />/<s:property value="pageDTO.getTotalPages()" /></font>页 
				<s:if test="pageDTO.pageNo==1&&pageDTO.getTotalPages()==1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:if> 
				<s:elseif test="pageDTO.pageNo==1&&pageDTO.getTotalPages()!=1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.queryBySearch('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.queryBySearch('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:elseif>
				<s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.queryBySearch(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.queryBySearch('${pageNo-1}')">上一页</a>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:elseif>
				<s:else>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.queryBySearch(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.queryBySearch('${pageNo-1}')">上一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.queryBySearch('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="QueryRole.queryBySearch('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
				 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳转" onclick="QueryRole.queryBySearch($('#pageNoPro').val())"/>
			</td>
		</tr>
	</table>
	</s:if>
	
	<s:if test='%{flag=="modify"}'>
	<table align="center" width="99%" border="0" align="center" >
		<tr>
			<td align="center">
				共<font color="#CC3333"><s:property value="pageDTO.totalRecords" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;每页<font color="#CC3333">
				<s:property value="pageDTO.pageSize" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;第<font color="#CC3333"><s:property value="pageDTO.pageNo" />/<s:property value="pageDTO.getTotalPages()" /></font>页 
				<s:if test="pageDTO.pageNo==1&&pageDTO.getTotalPages()==1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:if> 
				<s:elseif test="pageDTO.pageNo==1&&pageDTO.getTotalPages()!=1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.query('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:elseif>
				<s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.query(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.query('${pageNo-1}')">上一页</a>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:elseif>
				<s:else>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.query(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.query('${pageNo-1}')">上一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.query('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
				 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳转" onclick="ModifyRole.query($('#pageNoPro').val())"/>
			</td>
		</tr>
	</table>
	</s:if>
	
	<s:if test='%{flag=="modifyBySearch"}'>
	<table align="center" width="99%" border="0" align="center" >
		<tr>
			<td align="center">
				共<font color="#CC3333"><s:property value="pageDTO.totalRecords" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;每页<font color="#CC3333">
				<s:property value="pageDTO.pageSize" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;第<font color="#CC3333"><s:property value="pageDTO.pageNo" />/<s:property value="pageDTO.getTotalPages()" /></font>页 
				<s:if test="pageDTO.pageNo==1&&pageDTO.getTotalPages()==1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:if> 
				<s:elseif test="pageDTO.pageNo==1&&pageDTO.getTotalPages()!=1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.modifyBySearch('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.modifyBySearch('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:elseif>
				<s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.modifyBySearch(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.modifyBySearch('${pageNo-1}')">上一页</a>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:elseif>
				<s:else>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.modifyBySearch(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.modifyBySearch('${pageNo-1}')">上一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.modifyBySearch('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="ModifyRole.modifyBySearch('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
				 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳转" onclick="ModifyRole.modifyBySearch($('#pageNoPro').val())"/>
			</td>
		</tr>
	</table>
	</s:if>
	
	<s:if test='%{flag=="delete"}'>
	<table align="center" width="99%" border="0" align="center" >
		<tr>
			<td align="center">
				共<font color="#CC3333"><s:property value="pageDTO.totalRecords" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;每页<font color="#CC3333">
				<s:property value="pageDTO.pageSize" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;第<font color="#CC3333"><s:property value="pageDTO.pageNo" />/<s:property value="pageDTO.getTotalPages()" /></font>页 
				<s:if test="pageDTO.pageNo==1&&pageDTO.getTotalPages()==1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:if> 
				<s:elseif test="pageDTO.pageNo==1&&pageDTO.getTotalPages()!=1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.query('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:elseif>
				<s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.query(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.query('${pageNo-1}')">上一页</a>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:elseif>
				<s:else>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.query(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.query('${pageNo-1}')">上一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.query('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
				 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳转" onclick="DeleteRole.query($('#pageNoPro').val())"/>
			</td>
		</tr>
	</table>
	</s:if>

	<s:if test='%{flag=="deleteBySearch"}'>
	<table align="center" width="99%" border="0" align="center" >
		<tr>
			<td align="center">
				共<font color="#CC3333"><s:property value="pageDTO.totalRecords" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;每页<font color="#CC3333">
				<s:property value="pageDTO.pageSize" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;第<font color="#CC3333"><s:property value="pageDTO.pageNo" />/<s:property value="pageDTO.getTotalPages()" /></font>页 
				<s:if test="pageDTO.pageNo==1&&pageDTO.getTotalPages()==1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:if> 
				<s:elseif test="pageDTO.pageNo==1&&pageDTO.getTotalPages()!=1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.deleteBySearch('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.deleteBySearch('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:elseif>
				<s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.deleteBySearch(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.deleteBySearch('${pageNo-1}')">上一页</a>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:elseif>
				<s:else>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.deleteBySearch(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.deleteBySearch('${pageNo-1}')">上一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.deleteBySearch('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="DeleteRole.deleteBySearch('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
				 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳转" onclick="DeleteRole.deleteBySearch($('#pageNoPro').val())"/>
			</td>
		</tr>
	</table>
	</s:if>
	
		<s:if test='%{flag=="menu"}'>
	<table align="center" width="99%" border="0" align="center" >
		<tr>
			<td align="center">
				共<font color="#CC3333"><s:property value="pageDTO.totalRecords" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;每页<font color="#CC3333">
				<s:property value="pageDTO.pageSize" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;第<font color="#CC3333"><s:property value="pageDTO.pageNo" />/<s:property value="pageDTO.getTotalPages()" /></font>页 
				<s:if test="pageDTO.pageNo==1&&pageDTO.getTotalPages()==1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:if> 
				<s:elseif test="pageDTO.pageNo==1&&pageDTO.getTotalPages()!=1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.query('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:elseif>
				<s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.query(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.query('${pageNo-1}')">上一页</a>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:elseif>
				<s:else>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.query(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.query('${pageNo-1}')">上一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.query('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.query('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
				 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳转" onclick="RoleMenu.query($('#pageNoPro').val())"/>
			</td>
		</tr>
	</table>
	</s:if>

	<s:if test='%{flag=="menuBySearch"}'>
	<table align="center" width="99%" border="0" align="center" >
		<tr>
			<td align="center">
				共<font color="#CC3333"><s:property value="pageDTO.totalRecords" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;每页<font color="#CC3333">
				<s:property value="pageDTO.pageSize" /></font>条&nbsp;&nbsp;&nbsp;&nbsp;第<font color="#CC3333"><s:property value="pageDTO.pageNo" />/<s:property value="pageDTO.getTotalPages()" /></font>页 
				<s:if test="pageDTO.pageNo==1&&pageDTO.getTotalPages()==1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:if> 
				<s:elseif test="pageDTO.pageNo==1&&pageDTO.getTotalPages()!=1">
					<font color="#999999"> 首页</font>
					<font color="#999999"> 上一页</font>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.menuBySearch('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.menuBySearch('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:elseif>
				<s:elseif test="pageDTO.pageNo==pageDTO.getBottomPageNo()">
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.menuBySearch(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.menuBySearch('${pageNo-1}')">上一页</a>
					<font color="#999999">下一页</font>
					<font color="#999999">尾页</font>
				</s:elseif>
				<s:else>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.menuBySearch(1)">首页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.menuBySearch('${pageNo-1}')">上一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.menuBySearch('${pageNo+1}')">下一页</a>
					<a style="cursor:hand" href="javascript:void(0);" onclick="RoleMenu.menuBySearch('<s:property value="pageDTO.getTotalPages()" />')">尾页</a>
				</s:else>&nbsp;&nbsp;&nbsp;&nbsp;转到 <input type="text" name="pageNoPro" id="pageNoPro" size="2" />页
				 &nbsp;&nbsp;&nbsp;&nbsp;<input type="button" value="跳转" onclick="RoleMenu.menuBySearch($('#pageNoPro').val())"/>
			</td>
		</tr>
	</table>
	</s:if>