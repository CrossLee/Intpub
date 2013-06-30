<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>网站后台管理平台</title>
<link rel="stylesheet" href="css/common.css" type="text/css" />
<link rel="stylesheet" href="css/jquery-ui-1.8.16.custom.css"/>
<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<script type="text/javascript"  src=js/menuBar.js></script>
<script type="text/javascript"  src=js/common.js></script>
<script type="text/javascript"  src="js/fckeditor/fckeditor.js" ></script>
<script src="js/jquery.ui.core.js" type="text/javascript"></script>
<script src="js/jquery.ui.widget.js" type="text/javascript"></script>
<script src="js/jquery.ui.datepicker.js" type="text/javascript" ></script>
<script type="text/javascript">
	var preClassNameList = ""; 
	var menubar=new menulook();
  	var t;
  	var preClassName="";
	var profiles=new Array();
	var LeftFrame = new function(){
		this.list_sub_detail=function(Id, item){
			if(preClassNameList != "") 
	  		{ 
				LeftFrame.getObject(preClassNameList).className = "left_back"; 
	  		} 
	  		if(LeftFrame.getObject(Id).className == "left_back") 
	  		{ 
	  			LeftFrame.getObject(Id).className = "left_back_onclick"; 
	  			menubar.getbyitem(item); 
	  			preClassNameList = Id;
	  		} 
		};
		
		 //获取对象属性兼容方法
	    this.getObject=function(objectId){
	    	 if(document.getElementById && document.getElementById(objectId)) {
	    			// W3C DOM
	    			return document.getElementById(objectId);
	    	 } else if (document.all && document.all(objectId)) {
	    			// MSIE 4 DOM
	    			return document.all(objectId);
	    	 } else if (document.layers && document.layers[objectId]) {
	    			// NN 4 DOM.. note: this won't find nested layers
	    			return document.layers[objectId];
	    	 } else {
	    			return false;
	    	 }
	    };
		this.changeframe=function(item, sortname, src){
			
			if(item != "" && sortname != "") 
	  		{ 
	  			//window.top.frames['mainFrame'].LeftFrame.getObject('show_text').innerHTML = sortname + "  <img src=images/slide.gif broder=0 />  " + item 
	  		} 
	  		if(src != "") 
	  		{ 
	  			//window.top.frames['manFrame'].location = src;
	  			//alert("/Intpub/userspace/"+src);
	  			
	  			$.ajax({
					type:"POST",
					url:"/Intpub/"+src,
					dataType:"HTML",
					success:function(data){
	  					$("#mainDiv").html("");
						$("#mainDiv").html(data);
					},
					error:function(){
						alert("request error");
					}
				});
	  		} 
		};

		this.hideorshow=function(divid){
			subsortid = "sub_sort_" + divid.substring(11); 
	  		if(LeftFrame.getObject(divid).style.display == "none") 
	  		{ 
	  			LeftFrame.getObject(divid).style.display = "block"; 
	  			LeftFrame.getObject(subsortid).className = "list_tilte";
	  		}else{ 
	  			LeftFrame.getObject(divid).style.display = "none"; 
				LeftFrame.getObject(subsortid).className = "list_tilte_onclick"; 
	  		} 
		};	

		this.initinav=function(sortname){
			
			menubar.getdefaultnav(sortname); 
	   		menubar.getbytitle(sortname); 
		};		
		this.list_sub_nav=function(Id,sortname){
	    	 if(preClassName != ""){
	    		 LeftFrame.getObject(preClassName).className="bg_image";
	    	   }
	    	   if(LeftFrame.getObject(Id).className == "bg_image"){
	    		  LeftFrame.getObject(Id).className="bg_image_onclick";
	    	      preClassName = Id;
	    	      LeftFrame.showInnerText(Id);
	    		  menubar.getbytitle(sortname);
	    		  menubar.getdefaultnav(sortname);
	    	   }
	    };  
	    this.showInnerText=function(Id){ 
	    	var i=Id.substring(8);
	    	
	    	var showText = "对不起没有设置描述信息！";
	    	if(profiles[i]!=""){
	    		showText=profiles[i];
	    	}	
	    	LeftFrame.getObject('show_text').innerHTML =showText ;
	    };

	    this.closeWindow=function(){
		    window.location.href="/Intpub/login.jsp";
	    };    
	}; 


	
  </script>
<style type="text/css">
html,body{
overflow:hidden;
height:100%;
width:100%;
margin:0;
padding:0;
font:14px/1.8 Georgia, Arial, Simsun;
}

#top{
    position:absolute;
    top:0;
    left:0;
    width:100%;
    height:60px;
}
#foot{
	position:absolute;
	bottom:0;
	left:0;
	width:100%;
	height:49px;
}
#side{
    margin: 0;
    padding: 0;
	position:absolute;
	top:0;
	left:0;
	bottom:0;
	right:0;
	overflow:hidden;
	width:16%;
	height:100%;
}
#bd{
	position:absolute;
	top:70px;
	right:0;
	 margin: 0;
    padding: 0;
	bottom:52px;
	left:0;
	width:100%;
	overflow:hidden;
	_height:100%;
	}

#main{
	position:absolute;
	_position:static;
	top:0;
	right:0;
	bottom:0;
	left:210px;
	overflow:hidden;
	_overflow:hidden;
	_height:100%;
	width:83%;
	_margin-left:210px;
}
#mainDiv {
	width:100%;
	float:left;
}
/* 与布局无关，一些说明性内容样式 */
.copyright{font-weight:bold;text-align:center;}

</style>
</head>

<body id="indexBody">
  
   <div id="top">
      <div class="logo"><img src="images/man_logo.jpg" alt="网站后台管理平台" /></div>
	   
   </div>
   <div id="bd">
   //菜单开始
          <div id="side">
                                  <s:iterator id="menu" value="#session.menuList" status="m">
        <s:if test="#m.isFirst()">
          <script  type="text/javascript">
          	var firstMenu='${menu.menuName}'; //存放第一个菜单
          </script>
        </s:if>
      	<s:if test='#menu.depth==1'>
        	<script  type="text/javascript"> //存放一级菜单代码
		      var tempCode="${menu.menuCode}";
		      var tempName="${menu.menuName}";
			</script>
	 	 </s:if>
		<s:else>
		    <script  type="text/javascript">
		      if("${menu.parentCode}"==tempCode){
		    	  t=menubar.addtitle('${menu.menuName}',tempName,1);
		      }else{
		    	  menubar.additem('${menu.menuName}',t,'${menu.menuURL}');
		      }    
		    </script>
		</s:else>
  	</s:iterator>
  
	    <div id="left_content">
     	<div id="user_info">
     	           欢迎您，<strong>${userDTO.userName}</strong><br />
     	           [系统管理员，<a style="cursor:hand" href="javascript:void()" onclick="LeftFrame.closeWindow();">退出</a>]
     	</div>
	 	<div id="main_nav">
		<div id="left_main_nav"></div>
		<div id="right_main_nav"></div>
	 	</div>
	</div>
	<script   type="text/javascript">
	LeftFrame.initinav(firstMenu);
	</script>
          </div>
          
      //菜单结束
          <div id="main">
             <div class="maintop">
                 <div id="nav">
    				<ul>
     					<s:iterator  id="menu" value="#session.menuList" status="a">
     						<script  type="text/javascript">  profiles["${a.index+1}"]="${menu.profile}"; </script>
      						   <s:if test = '#menu.parentCode== "0"'> 
      							<s:if test="#a.isFirst()">
         						 <li id="man_nav_${menu.menuCode}" onclick="LeftFrame.list_sub_nav(id,'${menu.menuName}')"  class="bg_image_onclick">${menu.menuName}</li>
         					<script  type="text/javascript"> preClassName="man_nav_${menu.menuCode}";</script>
       							</s:if> 
      							 <s:else>
         							<li id="man_nav_${menu.menuCode}" onclick="LeftFrame.list_sub_nav(id,'${menu.menuName}')"  class="bg_image">${menu.menuName}</li>
       							</s:else>
      							</s:if>
     					</s:iterator>
    				</ul>
				</div>
				<div id="sub_info">
					&nbsp;&nbsp;<img src="images/hi.gif" />&nbsp;<span id="show_text">欢迎使用**后台管理系统!</span>
				</div>
            </div>
             
             
             <div id="mainDiv">
                
             </div>
           
   </div>
  </div>
  <div id="foot">
    <address class="copyright">Copyright &copy; 2012-2010 www.withiter.com</address>
  </div>
</body>
</html>