<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Info User</title>
<script language="JavaScript">

	var InfoUser = new function(){
		
		this.query = function(pageno){

		    if(!((/^(\+|-)?\d+$/.test( pageno ))|| pageno<0)){  
		    	alert("页数中请输入正整数！");  
		        return; 
		    } 
			var partCode=$("#partCode").val();
			var date=$("#date").val();
			$.ajax({
				type:"POST",
				url:"/Intpub/infospace/queryInfo.action",
				data:"flag=query&pageNo="+pageno+"&status=1&type=upload&date="+date+"&partCode="+partCode,
				dataType:"HTML",
				success:function(data){
					$("#infoDiv").html(data);
				},
				error:function(){
					alert("request error");
				}
			});
		};
		this.add = function(){
			$.ajax({
				type:"POST",
				url:"/Intpub/infospace/loadInfo.action?page=upload_add",
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
		this.look = function(oid){
			$.ajax({
				type:"POST",
				url:"/Intpub/infospace/queryInfo.action?flag=select&infoId="+oid,
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
		this.updateByUpload = function(oid){
			$.ajax({
				type:"POST",
				url:"/Intpub/infospace/queryInfo.action?flag=updateByUpload&infoId="+oid,
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
		this.dels = function(oid){
			if(window.confirm("确定要删除该信息吗?")){
				$.ajax({
					type:"POST",
					url:"/Intpub/infospace/deleteInfo.action",
					data:"type=upload&infoId="+oid+"&date="+$("#date").val(),
					dataType:"HTML",
					success:function(data){
					        alert("删除成功!");
							$("#infoDiv").html(data);
					},
					error:function(){
						alert("删除失败!");
					}
				});
			}
		};
		this.clearText=function(){
			$("#partCode").val("");
			$( "#date" ).datepicker('setDate' , -30) ;
		};	
	};
	$(document).ready(function(){
		$( "#date" ).datepicker();
		$( "#date" ).datepicker('setDate' , -30) ;
		InfoUser.query(1);
	});
</script>

</head>
<body>

<div id="man_zone">
  <table width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
    <tr>
      <td width="16%" class="left_title_1"><span class="left-title">创建时间</span></td>
      <td width="19%" ><input id="date" name="date" type="text" value="" readonly="readonly" /></td>
        <td width="16%"  class="left_title_1"  align="center"><span  style="align:'center'">栏目名称</span></td>
        <td width="19%" ><select id="partCode">
            <option value="">--请选择--</option>
       		<c:forEach items="${partMap}" var="obj">
             	 <option value="${obj.key}">${obj.value}</option>
        	</c:forEach>
        </select>
       </td>
       <td width="26%">&nbsp;&nbsp;&nbsp;&nbsp;<input name="sumbit" class="button_class" value="查  询" type="button" onclick="InfoUser.query(1)" />&nbsp;&nbsp;&nbsp;&nbsp;
       <input name="reset" value="重  置" class="button_class" type="button" onclick="InfoUser.clearText()"/>
      </td>
    </tr>
  </table>
  <br/>
  <div id="infoDiv">
  
  </div>
   <table border="0" width="99%" border="0" align="center"  cellpadding="3" cellspacing="1" class="table_style">
      <tr><td align="center" colspan="4">
            &nbsp;&nbsp;&nbsp;&nbsp;<input name="button" class="button_class" value="添  加" type="button"  onclick="InfoUser.add()" />
          </td>
      </tr>
  </table>
</div>
</body>
</html>