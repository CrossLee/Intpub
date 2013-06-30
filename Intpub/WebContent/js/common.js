var winWidth = 0;
var winHeight = 0;

// 获取浏览器宽高
function findDimensions() {
	if (document.body.scrollWidth > document.body.clientWidth) {
		winWidth = document.body.scrollWidth;
	} else {
		winWidth = document.body.clientWidth;
	}

	if (document.body.scrollHeight > document.body.clientHeight) {
		winHeight = document.body.scrollHeight;
	} else {
		winHeight = document.body.clientHeight;
	}
}

/**
 * @param newDivID
 *            test
 * @param width
 *            300
 * @param height
 *            400
 * @param contentIndex
 *            content5
 * @return
 */

var DEFAULT_WIDTH = "400";
var DEFAULT_HEIGHT = "300";
var DEFAULT_PENDERID = "indexBody";

function popupAlert(newDivID, width, height, penderId) {

	width = (width == null) ? DEFAULT_WIDTH : width;
	height = (height == null) ? DEFAULT_HEIGHT : height;
	penderId = (penderId == null) ? DEFAULT_PENDERID : penderId;

	findDimensions();
	//window.onresize = findDimensions;

	var newMaskID = "mask"; // 遮罩层id
	var newMaskWidth = winWidth;// 遮罩层宽度
	var newMaskHeight = winHeight;// 遮罩层高度

	// mask遮罩层
	var newMask = document.createElement("div");// 创建遮罩层
	newMask.id = newMaskID;// 设置遮罩层id
	newMask.style.position = "absolute";// 遮罩层位置
	newMask.style.zIndex = "100";// 遮罩层zIndex
	newMask.style.width = newMaskWidth + "px";// 设置遮罩层宽度
	newMask.style.height = newMaskHeight + "px";// 设置遮罩层高度
	newMask.style.top = "0px";// 设置遮罩层于上边距离
	newMask.style.left = "0px";// 设置遮罩层左边距离
	newMask.style.background = "black";// #33393C//遮罩层背景色
	newMask.style.filter = "alpha(opacity=40)";// 遮罩层透明度IE
	newMask.style.opacity = "0.40";// 遮罩层透明度FF
	document.getElementById(penderId).appendChild(newMask);// 遮罩层添加到DOM中

	// 新弹出层
	var newDivWidth = width;// 新弹出层宽度
	var newDivHeight = height;// 新弹出层高度
	var newDivtop = (screen.height / 2 - newDivHeight / 2) - 80;// 新弹出层距离上边距离
	var newDivleft = (screen.width / 2 - newDivWidth / 2);// 新弹出层距离左边距离

	document.getElementById(newDivID).style.position = "absolute";// 新弹出层位置
	document.getElementById(newDivID).style.zIndex = "1000";// 新弹出层zIndex
	document.getElementById(newDivID).style.width = newDivWidth + "px";// 新弹出层宽度
	document.getElementById(newDivID).style.height = newDivHeight + "px";// 新弹出层高度
	document.getElementById(newDivID).style.top = newDivtop + "px";// 新弹出层距离上边距离
	document.getElementById(newDivID).style.left = newDivleft + "px";// 新弹出层距离左边距离
	document.getElementById(newDivID).style.background = "#EFEFEF";// 新弹出层背景色
	document.getElementById(newDivID).style.border = "1px solid #860001";// /新弹出层边框样式
	document.getElementById(newDivID).style.padding = "5px";// 新弹出层
	//document.getElementById(newDivID).style.display = "";
	$('#'+newDivID).removeClass('hiddenDiv');

	document.getElementById(penderId).appendChild(
			document.getElementById(newDivID));// 新弹出层添加到DOM中

	// 弹出层滚动居中
	function newDivCenter() {
		document.getElementById(newDivID).style.top = (document.body.scrollTop
				+ document.body.clientHeight / 2 - newDivHeight / 2)
				+ "px";
		document.getElementById(newDivID).style.left = (document.body.scrollLeft
				+ document.body.clientWidth / 2 - newDivWidth / 2)
				+ "px";
	}
	// 处理滚动事件，使弹出层始终居中
	if (document.all) {
		if (newDivID != null) {
			// window.attachEvent("onscroll", newDivCenter);
		}
	} else {
		if (newDivID != null) {
			// window.addEventListener('scroll', newDivCenter, false);
		}
	}

	// 关闭新图层和mask遮罩层
	var newA = document.createElement("span");
	newA.href = "#";
	newA.id = newDivID + "_close";
	newA.style.position = "absolute";// span位置
	newA.style.right = 10 + "px";
	newA.style.top = 10 + "px";
	newA.style.cursor = "pointer";
	newA.style.color = "#AB8617";
	newA.innerHTML = "关闭";

	// 处理关闭事件
	newA.onclick = function() {
		if (document.all) {
			window.detachEvent("onscroll", newDivCenter);
		} else {
			window.removeEventListener('scroll', newDivCenter, false);
		}
		document.getElementById(penderId).removeChild(newMask);// 移除遮罩层
		// document.getElementById(penderId).removeChild(newDivID);// 移除
		document.getElementById(newDivID).removeChild(newA);// 移除Close
		document.getElementById(newDivID).style.display = "none";
		return false;
	};

	document.getElementById(newDivID).appendChild(newA);// 添加关闭span
}

function popupAlertWithoutClose(newDivID, width, height, penderId) {
	
	width = (width == null) ? DEFAULT_WIDTH : width;
	height = (height == null) ? DEFAULT_WIDTH : height;
	penderId = (penderId == null) ? DEFAULT_PENDERID : penderId;
	
	findDimensions(); // 调用函数，获取数值
	window.onresize = findDimensions;

	var newMaskID = "mask"; // 遮罩层id
	var newMaskWidth = winWidth;// 遮罩层宽度
	var newMaskHeight = winHeight;// 遮罩层高度

	// mask遮罩层
	var newMask = document.createElement("div");// 创建遮罩层
	newMask.id = newMaskID;// 设置遮罩层id
	newMask.style.position = "absolute";// 遮罩层位置
	newMask.style.zIndex = "100";// 遮罩层zIndex
	newMask.style.width = newMaskWidth + "px";// 设置遮罩层宽度
	newMask.style.height = newMaskHeight + "px";// 设置遮罩层高度
	newMask.style.top = "0px";// 设置遮罩层于上边距离
	newMask.style.left = "0px";// 设置遮罩层左边距离
	newMask.style.background = "black";// #33393C//遮罩层背景色
	newMask.style.filter = "alpha(opacity=40)";// 遮罩层透明度IE
	newMask.style.opacity = "0.40";// 遮罩层透明度FF
	document.getElementById(penderId).appendChild(newMask);// 遮罩层添加到DOM中

	// 新弹出层
	var newDivWidth = width;// 新弹出层宽度
	var newDivHeight = height;// 新弹出层高度
	var newDivtop = (screen.height / 2 - newDivHeight / 2) - 80;// 新弹出层距离上边距离
	var newDivleft = (screen.width / 2 - newDivWidth / 2);// 新弹出层距离左边距离

	document.getElementById(newDivID).style.position = "absolute";// 新弹出层位置
	document.getElementById(newDivID).style.zIndex = "1000";// 新弹出层zIndex
	document.getElementById(newDivID).style.width = newDivWidth + "px";// 新弹出层宽度
	document.getElementById(newDivID).style.height = newDivHeight + "px";// 新弹出层高度
	document.getElementById(newDivID).style.top = newDivtop + "px";// 新弹出层距离上边距离
	document.getElementById(newDivID).style.left = newDivleft + "px";// 新弹出层距离左边距离
	document.getElementById(newDivID).style.background = "#EFEFEF";// 新弹出层背景色
	document.getElementById(newDivID).style.border = "1px solid #860001";// /新弹出层边框样式
	document.getElementById(newDivID).style.padding = "5px";// 新弹出层
	document.getElementById(newDivID).style.display = "";

	document.getElementById(penderId).appendChild(
			document.getElementById(newDivID));// 新弹出层添加到DOM中

	// 弹出层滚动居中
	function newDivCenter() {
		document.getElementById(newDivID).style.top = (document.body.scrollTop
				+ document.body.clientHeight / 2 - newDivHeight / 2)
				+ "px";
		document.getElementById(newDivID).style.left = (document.body.scrollLeft
				+ document.body.clientWidth / 2 - newDivWidth / 2)
				+ "px";
	}
	// 处理滚动事件，使弹出层始终居中
	if (document.all) {
		if (newDivID != null) {
			// window.attachEvent("onscroll", newDivCenter);
		}
	} else {
		if (newDivID != null) {
			// window.addEventListener('scroll', newDivCenter, false);
		}
	}

	// 关闭新图层和mask遮罩层
	var newA = document.createElement("span");
	newA.href = "#";
	newA.id = newDivID + "_close";
	newA.style.position = "absolute";// span位置
	newA.style.right = 10 + "px";
	newA.style.top = 10 + "px";
	newA.style.cursor = "pointer";
	newA.style.display = "none";
	newA.style.color = "#AB8617";
	newA.innerHTML = "关闭";

	// 处理关闭事件
	newA.onclick = function() {
		if (document.all) {
			window.detachEvent("onscroll", newDivCenter);
		} else {
			window.removeEventListener('scroll', newDivCenter, false);
		}
		document.getElementById(penderId).removeChild(newMask);// 移除遮罩层
		document.getElementById(newDivID).removeChild(newA);// 移除Close
		document.getElementById(newDivID).style.display = "none";
		return false;
	};

	document.getElementById(newDivID).appendChild(newA);// 添加关闭span
}

// get current millins
function getCurrentMillis() {
	var date = new Date();
	var yy = date.getFullYear();
	var MM = date.getMonth() + 1;
	var dd = date.getDay();
	var hh = date.getHours();
	var mm = date.getMinutes();
	var ss = date.getSeconds();
	var sss = date.getMilliseconds();
	var result = Date.UTC(yy, MM, dd, hh, mm, ss, sss);
	return result;
}

// mouseover show div
function showTip(divid) {
	$('#' + divid).show("slow");
}
// mouseout hide div
function hidTip(divid) {
	$('#' + divid).hide("slow");
}

var winWidth = 0;
var winHeight = 0;
function getElementPos(elementId) {
	var ua = navigator.userAgent.toLowerCase();
	var isOpera = (ua.indexOf('opera') != -1);
	var isIE = (ua.indexOf('msie') != -1 && !isOpera); // not opera spoof
	var el = document.getElementById(elementId);
	if(el.parentNode === null || el.style.display == 'none') {
		return false;
	}
	var parent = null;
	var pos = [];    
	var box;    
	if(el.getBoundingClientRect)    //IE
	{         
		box = el.getBoundingClientRect();
		var scrollTop = Math.max(document.documentElement.scrollTop, document.body.scrollTop);
		var scrollLeft = Math.max(document.documentElement.scrollLeft, document.body.scrollLeft);
		return {x:box.left + scrollLeft, y:box.top + scrollTop};
	}else if(document.getBoxObjectFor)    // gecko   
	{
		box = document.getBoxObjectFor(el);
		var borderLeft = (el.style.borderLeftWidth)?parseInt(el.style.borderLeftWidth):0;
		var borderTop = (el.style.borderTopWidth)?parseInt(el.style.borderTopWidth):0;
		pos = [box.x - borderLeft, box.y - borderTop];
	} else    // safari & opera   
	{
		pos = [el.offsetLeft, el.offsetTop]; 
		parent = el.offsetParent;    
		if (parent != el) {
			while (parent) { 
				pos[0] += parent.offsetLeft;
				pos[1] += parent.offsetTop;
				parent = parent.offsetParent;
			} 
		}  
		if (ua.indexOf('opera') != -1 || ( ua.indexOf('safari') != -1 && el.style.position == 'absolute' )) {
			pos[0] -= document.body.offsetLeft;
			pos[1] -= document.body.offsetTop;        
		}   
	}             
	if (el.parentNode) {
		parent = el.parentNode;
	} else {
		parent = null;
	}
	while (parent && parent.tagName != 'BODY' && parent.tagName != 'HTML') { // account for any scrolled ancestors
		pos[0] -= parent.scrollLeft;
		pos[1] -= parent.scrollTop;
		if (parent.parentNode) {
			parent = parent.parentNode;
		} else {
			parent = null;
		}
	}
	return {x:pos[0], y:pos[1]};
}

//obtain the height and width of window 
function findDimensions() {
	if (document.body.scrollWidth > document.body.clientWidth) {
		winWidth = document.body.scrollWidth;
	} else {
		winWidth = document.body.clientWidth;
	}

	if (document.body.scrollHeight > document.body.clientHeight) {
		winHeight = document.body.scrollHeight;
	} else {
		winHeight = document.body.clientHeight;
	}
}

function alertWin(title, msg, w, h) {
	var titleheight = "22px"; // 提示窗口标题高度
	var bordercolor = "#666699"; // 提示窗口的边框颜色
	var titlecolor = "#FFFFFF"; // 提示窗口的标题颜色
	var titlebgcolor = "#666699"; // 提示窗口的标题背景色
	var bgcolor = "#FFFFFF"; // 提示内容的背景色

	var iWidth = document.documentElement.clientWidth;
	var iHeight = document.documentElement.clientHeight;
	var bgObj = document.createElement("div");
	bgObj.id="hideDiv";
	bgObj.style.cssText = "position:absolute;left:0px;top:0px;width:"
			+ Math.max(document.body.clientWidth, iWidth)
			+ "px;height:"
			+ Math.max(document.body.clientHeight, iHeight)
			+ "px;filter:Alpha(Opacity=30);opacity:0.3;background-color:#000000;z-index:101;";
	document.body.appendChild(bgObj);

	var msgObj = document.createElement("div");
	msgObj.id="dispDiv";
	msgObj.style.cssText = "position:absolute;font:11px '宋体';top:"
			+ (Math.max(document.body.clientHeight, iHeight) - h) / 2 + "px;left:" + (Math.max(document.body.clientWidth, iWidth) - w) / 2 + "px;width:"
			+ w + "px;height:" + h + "px;text-align:center;border:1px solid "
			+ bordercolor + ";background-color:" + bgcolor
			+ ";padding:1px;line-height:22px;z-index:102;";
	document.body.appendChild(msgObj);

	var table = document.createElement("table"); 
	msgObj.appendChild(table);
	table.style.cssText = "margin:0px;border:0px;padding:0px;";
	table.cellSpacing = 0;
	var tr = table.insertRow(-1);
	var titleBar = tr.insertCell(-1);
	titleBar.style.cssText = "width:100%;height:"
			+ titleheight
			+ "px;text-align:left;padding:3px;margin:0px;font:bold 13px '宋体';color:"
			+ titlecolor + ";border:1px solid " + bordercolor
			+ ";cursor:move;background-color:" + titlebgcolor;
	titleBar.style.paddingLeft = "10px";
	titleBar.innerHTML = title;
	var moveX = 0;
	var moveY = 0;
	var moveTop = 0;
	var moveLeft = 0;
	var moveable = false;
	var docMouseMoveEvent = document.onmousemove; 
	var docMouseUpEvent = document.onmouseup;
	titleBar.onmousedown = function() {
		var evt = getEvent();
		moveable = true;
		moveX = evt.clientX;
		moveY = evt.clientY;
		moveTop = parseInt(msgObj.style.top);
		moveLeft = parseInt(msgObj.style.left);

		document.onmousemove = function() {
			if (moveable) {
				var evt = getEvent();
				var x = moveLeft + evt.clientX - moveX; 
														
				var y = moveTop + evt.clientY - moveY;
				if (x > 0 && (x + w < iWidth) && y > 0 && (y + h < iHeight)) {
					msgObj.style.left = x + "px";
					msgObj.style.top = y + "px";
				}
			}
		};
		document.onmouseup = function() {
			if (moveable) {
				document.onmousemove = docMouseMoveEvent; 

				document.onmouseup = docMouseUpEvent;
				moveable = false;
				moveX = 0;
				moveY = 0;
				moveTop = 0;
				moveLeft = 0;
			}
		};
	};

	var closeBtn = tr.insertCell(-1);
	closeBtn.style.cssText = "cursor:pointer; padding:2px;background-color:"
			+ titlebgcolor;
	closeBtn.innerHTML = "<span style='font-size:15pt; color:" + titlecolor
			+ ";'>×</span>";
	closeBtn.onclick = function() {
		document.body.removeChild(bgObj);
		document.body.removeChild(msgObj);
	};
	var msgBox = table.insertRow(-1).insertCell(-1);
	msgBox.style.cssText = "font:10pt '宋体';";
	msgBox.colSpan = 2;
	msgBox.innerHTML = msg;

	// 获得事件Event对象，用于兼容IE和FireFox
	function getEvent() {
		return window.event || arguments.callee.caller.arguments[0];
	}
}

/** close alert window **/
function closeWin(){
	$("div").remove("#hideDiv"); 
	$("div").remove("#dispDiv");
}
