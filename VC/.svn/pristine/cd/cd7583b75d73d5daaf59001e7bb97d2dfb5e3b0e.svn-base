
//node 为节点对象
function Node(id, pid, name, url, title, target, icon, iconOpen, open) {

	//当前节点id
	this.id = id;
	//父节点id
	this.pid = pid;
	//当前节点的名字
	this.name = name;
	//点击节点时对应的超链接
	this.url = url;
	//鼠标移动到节点时对应的提示信息
	this.title = title;
	//设置打开连接方式
	this.target = target;
	//节点关闭时候的图标
	this.icon = icon;
	//节点打开时候的图标
	this.iconOpen = iconOpen;
	//节点是否默认打开
	this._io = open || false;

	this._is = false;

	//是否是同一级别节点中最后一个节点的标志
	this._ls = false;

	//当前节点是否有子节点的标志
	this._hc = false;
	//当前节点在所有节点数组(dtree 对象中的aNodes[])中的下标
	this._ai = 0;
	//父节点对象
	this._p;

};
 

function dTree(objName) {
	//配置项
	this.config = {
		//string 默认true 所有节点打开方式 html 元素 target属性
		target					: null,	
		//如果当前节点有子节点，设置url为空，点击时，只展示不进行超链接
		folderLinks			: false,
		//节点是否高亮现实
		useSelection		: true,
		//是否使用cookies 记录用户选择
		useCookies			: false,
		//是否使用线
		useLines				: false,
		//是否显示图片
		useIcons				: true,
		//是否在浏览器状态栏现实url
		useStatusText		: false,
		//同时只有一个节点被展开
		closeSameLevel	: false,
		inOrder					: false,
		webroot					:  '/'+(window.location.pathname.substring(1) == "" ? "" : window.location.pathname.substring(1).substring(0, window.location.pathname.substring(1).indexOf('/')))
	}

	this.icon = {
			root				: this.config.webroot+'/style/default/css/dtree/images/base.gif',
			folder			: this.config.webroot+'/style/default/css/dtree/images/folder.gif',
			folderOpen	: this.config.webroot+'/style/default/css/dtree/images/folderopen.gif',
			node				: this.config.webroot+'/style/default/css/dtree/images/page.gif',
			empty				: this.config.webroot+'/style/default/css/dtree/images/empty.gif',
			line				: this.config.webroot+'/style/default/css/dtree/images/line.gif',
			join				: this.config.webroot+'/style/default/css/dtree/images/join.gif',
			joinBottom	: this.config.webroot+'/style/default/css/dtree/images/joinbottom.gif',
			plus				: this.config.webroot+'/style/default/css/dtree/images/plus.gif',
			plusBottom	: this.config.webroot+'/style/default/css/dtree/images/plusbottom.gif',
			minus				: this.config.webroot+'/style/default/css/dtree/images/minus.gif',
			minusBottom	: this.config.webroot+'/style/default/css/dtree/images/minusbottom.gif',
			nlPlus			: this.config.webroot+'/style/default/css/dtree/images/nolines_plus.gif',
			nlMinus			: this.config.webroot+'/style/default/css/dtree/images/nolines_minus.gif'
	};

	this.obj = objName;

	//所有节点数组
	this.aNodes = [];
	//当前节点同级节点集合
	this.aIndent = [];

	this.root = new Node(-1);

	this.selectedNode = null;

	this.selectedFound = false;

	this.completed = false;

};

dTree.prototype.add = function(id, pid, name, url, title, target, icon, iconOpen, open) {
	this.aNodes[this.aNodes.length] = new Node(id, pid, name, url, title, target, icon, iconOpen, open);
};
dTree.prototype.openAll = function() {
	this.oAll(true);
};
dTree.prototype.closeAll = function() {
	this.oAll(false);
};

dTree.prototype.toString = function() {

	var str = '<div class="dtree">\n';

	if (document.getElementById) {

		//如果配置记录用户选择
		if (this.config.useCookies) 
		{
			//获取cookis信息设定当前节点为用户选择过的
			this.selectedNode = this.getSelected();
		}
		str += this.addNode(this.root);

	} else {
		str += 'Browser not supported.';
	}
	
	str += '</div>';

	if (!this.selectedFound) {
		//如果cookis获取不到用户选择，设置当前节点为用户没有选择
		this.selectedNode = null;
	}

	//当前节点是否配置完毕标志
	this.completed = true;

	return str;

};



// Creates the tree structure

dTree.prototype.addNode = function(pNode) {

	var str = '';

	var n=0;
	if (this.config.inOrder) {
		n = pNode._ai;
	}

	//遍历所有节点
	for (n; n<this.aNodes.length; n++) {
		//如果循环发现当前循环到的节点的父ID 和 传入节点的节点ID相同
		//当前循环节点为传入节点的子节点
		if (this.aNodes[n].pid == pNode.id) {

			var cn = this.aNodes[n];
			//设置当前节点的父节点为传入节点
			cn._p = pNode;
			//设置当前循环节点在整个节点数组中的下标
			cn._ai = n;

			//判断当前节点是否有子节点，并且是不是最后一个节点
			this.setCS(cn);

			//设置当前节点打开超链接时的默认行为
			if (!cn.target && this.config.target){
				cn.target = this.config.target;
			}
			//如果当前节点有子节点，并且选择的COOKIES记录用户选择，设置当前节点是否打开
			if (cn._hc && !cn._io && this.config.useCookies){
				cn._io = this.isOpen(cn.id);
			}

			//如果当前节点有自己点，并且folderLinks 配置为false，点击节点时只打开节点，不超链接
			if (!this.config.folderLinks && cn._hc) {
				cn.url = null;
			}

			if (this.config.useSelection && cn.id == this.selectedNode && !this.selectedFound) {
					cn._is = true;
					this.selectedNode = n;
					this.selectedFound = true;
			}

			str += this.node(cn, n);

			if (cn._ls){
				break;
			}
		}
	}

	return str;

};  

dTree.prototype.node = function(node, nodeId) {

	//添加class，并且根据节点的层级添加缩进（虚线或者空图片）,如果有子节点，添加加减号图片
	var str=''
	if (this.root.id != node.pid) {
				str = '<div class="dTreeNode">' + this.indent(node, nodeId); 
			//如果配置显示图片每个节点前的自定义图片
			if (this.config.useIcons) {
				//如果定义了 节点关闭图片
				if (!node.icon) {
					node.icon = (this.root.id == node.pid) ? this.icon.root : ((node._hc) ? this.icon.folder : this.icon.node);
				}
				//如果定义了 节点打开图片
				if (!node.iconOpen){
					node.iconOpen = (node._hc) ? this.icon.folderOpen : this.icon.node;
				}
		
				if (this.root.id == node.pid) {
					node.icon = this.icon.root;
					node.iconOpen = this.icon.root;
				}
		
				str += '<img id="i' + this.obj + nodeId + '" src="' + ((node._io) ? node.iconOpen : node.icon) + '" alt="" />';
		
			}
		
			//如果有url代表是末级节点
			if (node.url) {
				str += '<a id="s' + this.obj + nodeId + '" class="' + ((this.config.useSelection) ? ((node._is ? 'nodeSel' : 'node')) : 'node') + '" href="' + node.url + '"';
		
				if (node.title){
					str += ' title="' + node.title + '"';
				}
		
				if (node.target) {
					str += ' target="' + node.target + '"';
				}
		
				if (this.config.useStatusText){
					str += ' onmouseover="window.status=\'' + node.name + '\';return true;" onmouseout="window.status=\'\';return true;" ';
				}
		
				if (this.config.useSelection && ((node._hc && this.config.folderLinks) || !node._hc))
		
					str += ' onclick="javascript: ' + this.obj + '.s(' + nodeId + ');"';
		
				str += '>';
		
			}else if ((!this.config.folderLinks || !node.url) && node._hc && node.pid != this.root.id){
				str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');" class="node">';
			}
			
			str += node.name;
		
			if (node.url || ((!this.config.folderLinks || !node.url) && node._hc)){
				str += '</a>';
			}
		
			str += '</div>';
	}

	if (node._hc) {
		str += '<div id="d' + this.obj + nodeId + '" class="clip" style="display:' + ((this.root.id == node.pid || node._io) ? 'block' : 'none') + ';">';
		str += this.addNode(node);
		str += '</div>';
	}

	this.aIndent.pop();

	return str;

};



// 添加线或者空格保持缩进层级
dTree.prototype.indent = function(node, nodeId) {

	var str = '';

	//如果当前节点不是root节点的直接子节点
	if (this.root.id != node.pid) {

		//根据层级添加虚线或者空图片
		for (var n=0; n<this.aIndent.length; n++){
			str += '<img src="' + ( (this.aIndent[n] == 1 && this.config.useLines) ? this.icon.line : this.icon.empty ) + '" alt="" />';
		}

		(node._ls) ? this.aIndent.push(0) : this.aIndent.push(1);
		//如果 有子节点
		if (node._hc) {
			//添加点击打开事件加减号点击的动作
			str += '<a href="javascript: ' + this.obj + '.o(' + nodeId + ');"><img border="0" id="j' + this.obj + nodeId + '" src="'; 
			//添加加减号图片
			if (!this.config.useLines){
				str += (node._io) ? this.icon.nlMinus : this.icon.nlPlus;
			}else{
				str += ( (node._io) ? ((node._ls && this.config.useLines) ? this.icon.minusBottom : this.icon.minus) : ((node._ls && this.config.useLines) ? this.icon.plusBottom : this.icon.plus ) );
			}

			str += '" alt="" /></a>';

		} else{
			//如果没有子节点就不需要加减号
			str += '<img border="0" src="' + ( (this.config.useLines) ? ((node._ls) ? this.icon.joinBottom : this.icon.join ) : this.icon.empty) + '" alt="" />';
		}

	}

	return str;

};
 
//检查节点的所有子节点
dTree.prototype.setCS = function(node) { 
	var lastId; 
	for (var n=0; n<this.aNodes.length; n++) {
		//遍历所有节点判断传入节点的是否有子节点
		if (this.aNodes[n].pid == node.id) {
			node._hc = true;
		}
		//判断是否是同一级节点中的最后一个节点
		if (this.aNodes[n].pid == node.pid){
			lastId = this.aNodes[n].id;
		}

	} 
	if (lastId==node.id) node._ls = true;

};



// Returns the selected node

dTree.prototype.getSelected = function() {

	var sn = this.getCookie('cs' + this.obj);

	return (sn) ? sn : null;

};



// Highlights the selected node

dTree.prototype.s = function(id) {

	if (!this.config.useSelection) return;

	var cn = this.aNodes[id];

	if (cn._hc && !this.config.folderLinks) return;

	if (this.selectedNode != id) {

		if (this.selectedNode || this.selectedNode==0) {

			eOld = document.getElementById("s" + this.obj + this.selectedNode);

			eOld.className = "node";

		}

		eNew = document.getElementById("s" + this.obj + id);

		eNew.className = "nodeSel";

		this.selectedNode = id;

		if (this.config.useCookies) this.setCookie('cs' + this.obj, cn.id);

	}

};



// Toggle Open or close

dTree.prototype.o = function(id) {

	var cn = this.aNodes[id];

	this.nodeStatus(!cn._io, id, cn._ls);

	cn._io = !cn._io;

	if (this.config.closeSameLevel) this.closeLevel(cn);

	if (this.config.useCookies) this.updateCookie();

};



// Open or close all nodes

dTree.prototype.oAll = function(status) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._hc && this.aNodes[n].pid != this.root.id) {

			this.nodeStatus(status, n, this.aNodes[n]._ls)

			this.aNodes[n]._io = status;

		}

	}

	if (this.config.useCookies) this.updateCookie();

};



// Opens the tree to a specific node

dTree.prototype.openTo = function(nId, bSelect, bFirst) {

	if (!bFirst) {

		for (var n=0; n<this.aNodes.length; n++) {

			if (this.aNodes[n].id == nId) {

				nId=n;

				break;

			}

		}

	}

	var cn=this.aNodes[nId];

	if (cn.pid==this.root.id || !cn._p) return;

	cn._io = true;

	cn._is = bSelect;

	if (this.completed && cn._hc) this.nodeStatus(true, cn._ai, cn._ls);

	if (this.completed && bSelect) this.s(cn._ai);

	else if (bSelect) this._sn=cn._ai;

	this.openTo(cn._p._ai, false, true);

};



// Closes all nodes on the same level as certain node

dTree.prototype.closeLevel = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.pid && this.aNodes[n].id != node.id && this.aNodes[n]._hc) {

			this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);

		}

	}

}



// Closes all children of a node

dTree.prototype.closeAllChildren = function(node) {

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n].pid == node.id && this.aNodes[n]._hc) {

			if (this.aNodes[n]._io) this.nodeStatus(false, n, this.aNodes[n]._ls);

			this.aNodes[n]._io = false;

			this.closeAllChildren(this.aNodes[n]);		

		}

	}

}



// Change the status of a node(open or closed)

dTree.prototype.nodeStatus = function(status, id, bottom) {

	eDiv	= document.getElementById('d' + this.obj + id);

	eJoin	= document.getElementById('j' + this.obj + id);

	if (this.config.useIcons) {

		eIcon	= document.getElementById('i' + this.obj + id);

		eIcon.src = (status) ? this.aNodes[id].iconOpen : this.aNodes[id].icon;

	}

	eJoin.src = (this.config.useLines)?

	((status)?((bottom)?this.icon.minusBottom:this.icon.minus):((bottom)?this.icon.plusBottom:this.icon.plus)):

	((status)?this.icon.nlMinus:this.icon.nlPlus);

	eDiv.style.display = (status) ? 'block': 'none';

};





// [Cookie] Clears a cookie

dTree.prototype.clearCookie = function() {

	var now = new Date();

	var yesterday = new Date(now.getTime() - 1000 * 60 * 60 * 24);

	this.setCookie('co'+this.obj, 'cookieValue', yesterday);

	this.setCookie('cs'+this.obj, 'cookieValue', yesterday);

};



// [Cookie] Sets value in a cookie

dTree.prototype.setCookie = function(cookieName, cookieValue, expires, path, domain, secure) {

	document.cookie =

		escape(cookieName) + '=' + escape(cookieValue)

		+ (expires ? '; expires=' + expires.toGMTString() : '')

		+ (path ? '; path=' + path : '')

		+ (domain ? '; domain=' + domain : '')

		+ (secure ? '; secure' : '');

};



// [Cookie] Gets a value from a cookie

dTree.prototype.getCookie = function(cookieName) {

	var cookieValue = '';

	var posName = document.cookie.indexOf(escape(cookieName) + '=');

	if (posName != -1) {

		var posValue = posName + (escape(cookieName) + '=').length;

		var endPos = document.cookie.indexOf(';', posValue);

		if (endPos != -1) cookieValue = unescape(document.cookie.substring(posValue, endPos));

		else cookieValue = unescape(document.cookie.substring(posValue));

	}

	return (cookieValue);

};



// [Cookie] Returns ids of open nodes as a string

dTree.prototype.updateCookie = function() {

	var str = '';

	for (var n=0; n<this.aNodes.length; n++) {

		if (this.aNodes[n]._io && this.aNodes[n].pid != this.root.id) {

			if (str) str += '.';

			str += this.aNodes[n].id;

		}

	}

	this.setCookie('co' + this.obj, str);

};



// [Cookie] Checks if a node id is in a cookie

dTree.prototype.isOpen = function(id) {

	var aOpen = this.getCookie('co' + this.obj).split('.');

	for (var n=0; n<aOpen.length; n++)

		if (aOpen[n] == id) return true;

	return false;

};



// If Push and pop is not implemented by the browser

if (!Array.prototype.push) {

	Array.prototype.push = function array_push() {

		for(var i=0;i<arguments.length;i++)

			this[this.length]=arguments[i];

		return this.length;

	}

};

if (!Array.prototype.pop) {

	Array.prototype.pop = function array_pop() {

		lastElement = this[this.length-1];

		this.length = Math.max(this.length-1,0);

		return lastElement;

	}

};