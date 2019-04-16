<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
   
   <!-- 自定义js类库 -->
<script type="text/javascript" src="${ctx}/pages/subfunc/js/jsTreeDemo.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		jsTree.initPage();
	});
</script>
</head>
<body>
<table id="treeGrid"></table>
<div >
    <div>
      <h4>树形控件</h4>
   	</div>
    <div id="treeDiv1" class="treeDiv">
        <ul>
            <li id="0"><a href="#" class="treeNodeParent" >上海分公司</a>
                <ul>
                    <li class="jstree-checked" id="1"><a href="#" >陆家嘴支公司</a></li>
                    <li id="2"><a href="#" id="2">金桥支公司</a></li>
                </ul>
            </li>
            <li id="3"><a href="#" class="treeNodeParent">北京分公司</a>
                <ul>
                    <li id="4"><a href="${ctx}/subfunc/viewListPage.do?action=viewListPage">长安街支公司</a></li>
                </ul>
            </li>
        </ul>
    </div>
</div>
<br>
<div id = "treeDiv" class="treeDiv"></div>
<br>
<div id = "mainDiv"></div>
<input id="textDiv" type = "text">
<br>
<input type="button" id="getBut" class="taButton" value="取值"/>
</body>
</html>

