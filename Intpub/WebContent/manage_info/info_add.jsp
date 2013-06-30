<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>信息上载</title>
	<meta content="text/html; charset=utf-8" http-equiv="content-type"/>
	<link href="../sample.css" rel="stylesheet" type="text/css" />
	<link rel="shortcut icon" href="../fckeditor.gif" type="image/x-icon" />
	<script type="text/javascript">

var oFCKeditor = new FCKeditor('content') ;  //content与textarea的property或name属性对应

oFCKeditor.BasePath = "<%=request.getContextPath()%>/js/fckeditor/" ;

//写明FCKeditor文件夹在项目中的路径

oFCKeditor.Width=900;

oFCKeditor.Height = 400;

oFCKeditor.ToolbarSet = "Basic" ;//用默认工具栏

oFCKeditor.ReplaceTextarea();
function FCKeditor_OnComplete(editorInstance) {    
	var oEditor = FCKeditorAPI.GetInstance("content");
	oEditor.SetHTML("");
}   
var AddInfo = new function(){
	this.add = function(){
		var oEditor = FCKeditorAPI.GetInstance("content");
		var content=oEditor.GetXHTML(false); 
		var title=$("#title").val();
		var code=$("#code").val();
		if(title==""){
			alert("请输入标题!");
			return;
		}
		if(code==""){
			alert("请选择栏目!");
			return;
		}		
		if(content==""){
			alert("请输入内容!");
			return;
		}	
    	$.ajax({
		type:"POST",
		url:"/Intpub/infospace/addInfo.action",
		dataType:"JSON",
		data:"content="+content+"&title="+title+"&partCode="+code,
		success:function(data){
    		if(data.loginState == 'addSuccess'){
				alert("保存信息成功!");
				$.ajax({
					type:"POST",
					url:"/Intpub/infospace/queryInfo.action?flag=upload",
					dataType:"HTML",
					success:function(data){
	  					$("#mainDiv").html("");
	  					
						$("#mainDiv").html(data);
					},
					error:function(){
						alert("request error");
					}
				});
			}else{
				alert("保存信息失败!");
			}	
		},
		error:function(){
			alert("request error");
		}
	});
	};
};
</script> 
</head>
	<body >
	<form name="form1" action="">
		<table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
		  <tr>
		    <td width="14%" class="left_title_1">标题：</td>
		    <td width="20%"  class="left_title_2"><input type="text" name="title" id="title" value=""/></td>
		    <td width="14%" class="left_title_1">栏目：</td>
		    <td  style="text-align:left"align="left" class="left_title_2"> 
		    <select id="code">
		    <option value="">--请选择--</option>
       		<c:forEach items="${partMap}" var="obj">
             	 <option value="${obj.key}">${obj.value}</option>
        	</c:forEach>
        </select></td>
		  </tr>
		  <tr>
		    <td colspan="4"><textarea name="content"  style="width:280px;height:200px"  id="content" ></textarea></td>
		  </tr>
		  <tr>
		    <td colspan="4" align="center"><input class="button_class" type="button" value="保  存"  onclick="AddInfo.add()"/></td>
		  </tr>
		</table>
	</form>
	</body>
</html>

