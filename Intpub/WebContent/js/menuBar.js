function menulook() {
	this.titlelist = new Array();
	this.itemlist = new Array();
	this.addtitle = addtitle;
	this.additem = additem;
	this.getbytitle = getbytitle;
	this.getbyitem = getbyitem;
	this.getdefaultnav = getdefaultnav
}
function theitem(intitle, insort, inkey, inisdefault) {
	this.sortname = insort;
	this.key = inkey;
	this.title = intitle;
	this.isdefault = inisdefault
}
function addtitle(intitle, sortname, inisdefault) {
	menubar.itemlist[menubar.titlelist.length] = new Array();
	menubar.titlelist[menubar.titlelist.length] = new theitem(intitle,
			sortname, 0, inisdefault);
	return (menubar.titlelist.length - 1)
}
function additem(intitle, parentid, inkey) {
	if (parentid >= 0 && parentid <= menubar.titlelist.length) {
		insort = "item_" + parentid;
		menubar.itemlist[parentid][menubar.itemlist[parentid].length] = new theitem(
				intitle, insort, inkey, 0);
		return (menubar.itemlist[parentid].length - 1)
	} else
		additem = -1
}
function getdefaultnav(sortname) {
	var output = "";
	for (i = 0; i < menubar.titlelist.length; i++) {
		if (menubar.titlelist[i].isdefault == 1
				&& menubar.titlelist[i].sortname == sortname) {
			output += "<div class=list_tilte id=sub_sort_" + i
					+ " onclick=\"LeftFrame.hideorshow('sub_detail_" + i
					+ "')\">";
			output += "<span>" + menubar.titlelist[i].title + "</span>";
			output += "</div>";
			output += "<div class=list_detail id=sub_detail_" + i + "><ul>";
			for (j = 0; j < menubar.itemlist[i].length; j++) {
				output += "<li id=" + menubar.itemlist[i][j].sortname + j
						+ " onclick=\"LeftFrame.changeframe('"
						+ menubar.itemlist[i][j].title + "', '"
						+ menubar.titlelist[i].title + "', '"
						+ menubar.itemlist[i][j].key + "')\"><a href='javascript:void(0);'>"
						+ menubar.itemlist[i][j].title + "</a></li>"
			}
			output += "</ul></div>"
		}
	}
	LeftFrame.getObject('right_main_nav').innerHTML = output
}
function getbytitle(sortname) {
	var output = "<ul>";
	for (i = 0; i < menubar.titlelist.length; i++) {
		if (menubar.titlelist[i].sortname == sortname) {
			output += "<li id=left_nav_" + i
					+ " onclick=\"LeftFrame.list_sub_detail(id, '"
					+ menubar.titlelist[i].title + "')\" class=left_back>"
					+ menubar.titlelist[i].title + "</li>"
		}
	}
	output += "</ul>";
	LeftFrame.getObject('left_main_nav').innerHTML = output
}
function getbyitem(item) {
	var output = "";
	for (i = 0; i < menubar.titlelist.length; i++) {
		if (menubar.titlelist[i].title == item) {
			output = "<div class=list_tilte id=sub_sort_" + i
					+ " onclick=\"LeftFrame.hideorshow('sub_detail_" + i
					+ "')\">";
			output += "<span>" + menubar.titlelist[i].title + "</span>";
			output += "</div>";
			output += "<div class=list_detail id=sub_detail_" + i
					+ " style='display:block;'><ul>";
			for (j = 0; j < menubar.itemlist[i].length; j++) {
				output += "<li id=" + menubar.itemlist[i][j].sortname + "_" + j
						+ " onclick=\"LeftFrame.changeframe('"
						+ menubar.itemlist[i][j].title + "', '"
						+ menubar.titlelist[i].title + "', '"
						+ menubar.itemlist[i][j].key + "')\"><a href='javascript:void(0);'>"
						+ menubar.itemlist[i][j].title + "</a></li>"
			}
			output += "</ul></div>"
		}
	}
	LeftFrame.getObject('right_main_nav').innerHTML = output
}