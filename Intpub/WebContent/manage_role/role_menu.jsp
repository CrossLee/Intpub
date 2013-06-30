<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<script type="text/javascript">

	var RoleMenu = new function(){
		this.manageRoleMenu = function(roleCode){
			$.ajax({
				type:"POST",
				url:"/Intpub/rolespace/queryMenuRoleAction.action",
				data:"flag=queryMenu&roleCode="+roleCode,
				dataType:"HTML",
				success:function(data){
					//$("#rolePageDiv").html(data); 
                    alertWin("Role Details",data,538,450);
					
				},
				error:function(){
					alert("request error");
				}
			});
		};


		// paging
		this.query = function(pageno){
		    if(!((/^(\+|-)?\d+$/.test( pageno ))|| pageno<0)){
		    	alert("页数中请输入正整数！");
		        return;
		    }
			
			//var rolename=$("#rolename").val();
			//var status=$("#status").val();
			$.ajax({
				type:"POST",
				url:"/Intpub/rolespace/queryRoleAction.action",
				//data:"flag=query&pageNo="+pageno+"&rolename="+rolename+"&status="+status,
				data:"flag=menu&pageNo="+pageno,
				dataType:"HTML",
				success:function(data){
					$("#rolePageDiv").html(data);
				},
				error:function(){
					alert("request error");
				}
			});
		};

		// search paging
		this.menuBySearch = function(pageno){
		    if(!((/^(\+|-)?\d+$/.test( pageno ))|| pageno<0)){
		    	alert("页数中请输入正整数！");
		        return;
		    }
			
			var rolename=$("#rolename").val();
			var status=$("#status").val();
			$.ajax({
				type:"POST",
				url:"/Intpub/rolespace/queryRoleAction.action",
				data:"flag=menuBySearch&pageNo="+pageno+"&roleName="+rolename+"&status="+status,
				dataType:"HTML",
				success:function(data){
					$("#rolePageDiv").html(data);
				},
				error:function(){
					alert("request error");
				}
			});
		};

		this.saveRoleMenu = function(){

			$("#select2 option").attr("selected", true);
			
			var values = $('#select2').val();
			var roleCodeID = $('#roleCodeID').val();
			//alert(values);
			//var afterDelDot = deleDot(values);
			//alert(roleCodeID);
			//alert(afterDelDot);
			$.ajaxSettings.traditional=true;
			$.ajax({
				type:"POST",
				url:"/Intpub/rolespace/saveMenuRoleAction.action",
				data:{"menuString":values,"roleCode":roleCodeID},
				dataType:"JSON",
				success:function(data){
					//alert(data.manageMenus);
					if(data.manageMenus == "true"){
						alert("更新成功");
						closeWin();
					}else{
						alert("更新失败");
					}
				},
				error:function(){
					alert("request error");
				}
			});
		};
	};

	$(document).ready(function(){
		RoleMenu.query(1);
	});

	//移到右边
	function addClick(){
		$('#select1 option:selected').remove().appendTo('#select2');
	}
	//移到左边
	function removeClick(){
		$('#select2 option:selected').remove().appendTo('#select1');
	}
	//双击选项
	function select1Dbclick(){
		$("option:selected", this).remove().appendTo('#select2');
	}
	//双击选项
	function select2Dbclick(){
		$("option:selected", this).remove().appendTo('#select1');
	}
</script>
<div id="man_zone">
	<form>
		<table width="99%" border="0" align="center" cellpadding="3"
			cellspacing="1" class="table_style">
			<tr>
				<td width="14%" class="left_title_1"><span class="left-title">查询关键字</span></td>
				<td width="20%"><input id="rolename" name="rolename" id="rolename"
					type="text" value="" /></td>
				<td width="14%" class="left_title_1">是否启用</td>
				<td width="16%"><select id="status" name="user.status"
					style="width: 90px">
					<option value="0">-请选择-</option>
					<option value="1">是</option>
					<option value="2">否</option>
				</select></td>
				<td width="32%">&nbsp;&nbsp;&nbsp;&nbsp;<input name="sumbit"
					class="button_class" value="查  询" type="button"
					onclick=RoleMenu.menuBySearch(1);
		/>&nbsp;&nbsp;&nbsp;&nbsp; <input
					name="reset" class="button_class" value="重  置" type="reset" /></td>
			</tr>
		</table>
	</form>
	<br />
	
	<div id="rolePageDiv">
	</div>
</div>