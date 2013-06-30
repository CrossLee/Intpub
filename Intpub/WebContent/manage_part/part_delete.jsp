<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script language="JavaScript">
		
		var contextPath = "<%=basePath%>";
	
		var PartDelete = new function(){
			this.queryParts = function(pageNo){
				$.ajax({
					type:"POST",
					url:"/Intpub/partspace/queryPartsByCondition.action?returnType=deleteList&pageNo="+pageNo,
					dataType:"HTML",
					data:$('#partDeleteForm').serialize(),
					success:function(data){
							$("#partList").html(data);
							$("#partDetail").html("");
						//}
					},
					error:function(){
						alert("request error");
					}
				});
			};
			this.deletePartByPartCode = function(partCode){
				$.ajax({
					type:"POST",
					url:"/Intpub/partspace/deletePartByPartCode.action?pageNo=1&selectedPartCode=" + partCode,
					dataType:"HTML",
					data:$('#partDeleteForm').serialize(),
					success:function(data){
						$("div").remove("#hideDiv");
						$("div").remove("#dispDiv");
						$("#partList").html(data);
						//}
					},
					error:function(){
						alert("request error");
					}
				});
			};
		};
		$(document).ready(function(){
			// get validate code
		});
	</script>
  </head>
  
  <body>
  	<div id="man_zone">
  	<form action="" id="partDeleteForm" name="partDeleteForm" method="post">
  		<table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
  			<tr>
  				<td class="left_title_1"><span class="left-title">名称</span></td>
  				<td><input type="text" id="partName" name="queryCondition.partName" /></td>
  				<td class="left_title_1"><span class="left-title">状态</span></td>
  				<td><s:select name="queryCondition.status" id="status" list="{1,2,3}"></s:select></td>
  				<td colspan="2"><input type="button" class="button_class" value="查询" onclick="javascript:PartDelete.queryParts(1);"/></td>
  			</tr>
  		</table>
  		<div id="partList">
  		
  		</div>
  		
  	</form>
  	</div>
  </body>
</html>
