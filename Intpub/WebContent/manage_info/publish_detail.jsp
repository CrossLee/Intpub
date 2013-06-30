<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息内容</title>
	<link rel="shortcut icon" href="../fckeditor.gif" type="image/x-icon" />
<script type="text/javascript">

var oFCKeditor = new FCKeditor('content') ;  //content与textarea的property或name属性对应
oFCKeditor.BasePath = "<%=request.getContextPath()%>/js/fckeditor/" ;
//写明FCKeditor文件夹在项目中的路径
oFCKeditor.Width=900;
oFCKeditor.Height = 400;
oFCKeditor.ToolbarSet = "Basic" ;//用默认工具栏
oFCKeditor.ReplaceTextarea();
$(document).ready(function(){
	
	
});
function FCKeditor_OnComplete(editorInstance) {    
	var oEditor = FCKeditorAPI.GetInstance("content");
    oEditor.SetHTML("${info.content}",true);
}   

var PublishDetail = new function(){
	this.goBack = function(){
			$.ajax({
				type:"POST",
				url:"/Intpub/infospace/queryInfo.action?flag=publish",
				dataType:"HTML",
				success:function(data){
  					$("#mainDiv").html("");
  					
					$("#mainDiv").html(data);
				},
				error:function(){
					alert("request error");
				}
			});
			
	};
	this.upload = function(){
	
		var oEditor = FCKeditorAPI.GetInstance("content");
		var content=oEditor.GetXHTML(false); 
		
		var title=$("#title").val();
		var infoId=$("#infoId").val();
    	$.ajax({
		type:"POST",
		url:"/Intpub/infospace/updateInfo.action",
		dataType:"JSON",
		data:"flag=updateByUpload&content="+content+"&title="+title+"&infoId="+infoId+"&status=2&partCode="+$("#code").val(),
		success:function(data){
    		if(data.loginState == 'addSuccess'){
				alert("发布信息成功!");
				$.ajax({
					type:"POST",
					url:"/Intpub/infospace/queryInfo.action?flag=publish",
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
				alert("发布信息失败!");
			}	
		},
		error:function(){
			alert("request error");
		}
	});
	};
};
$(document).ready(function(){
	$( "#code" ).val("${info.code}");
});
</script>

</head>
<body>
<form name="form1" action="">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
		  <tr>
		    <td width="14%" height="20" class="left_title_1">标题：</td>
		    <td width="20%"  class="left_title_2"><input type="text" name="title" id="title" value="${info.title}"/></td>
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
		    <td colspan="4" align="center" >
		    <s:if test='%{flag=="updateByPublish"}'>
		    		<input class="button_class" type="button" value="发  布"  onclick="PublishDetail.upload()"/>
		    </s:if>
		    <input class="button_class" type="button" value="返  回"  onclick="PublishDetail.goBack(1)"/></td>
		  </tr>
		</table>
		<input type="hidden" value="${info.infoId}" name="infoId" id="infoId" />
</form>
</body>

</html>