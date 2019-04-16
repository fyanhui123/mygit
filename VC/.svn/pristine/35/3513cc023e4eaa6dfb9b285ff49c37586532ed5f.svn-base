<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
	<script type="text/javascript" src="${ctx}/pages/common/js/companyZTree.js"></script>
    
     <script type="text/javascript">
		$(document).ready(function () {
		   companyZTree.initZTree();
		});
    </script>
    <style type="text/css">
		.ztree li span.button.icon_1_ico_open, .ztree li span.button.icon_1_ico_close{margin-right:2px; background: url(${ctx}/style/default/css/dtree/images/base.gif) no-repeat scroll 0 0 transparent; vertical-align:top; *vertical-align:middle}
	</style>
</head>
<body>
    <input type="hidden" name="tagCodeId" id="ztree_tagCodeId" value="${tagCodeId}"/>
	<input type="hidden" name="tagNameId" id="ztree_tagNameId" value="${tagNameId}"/>
	<input type="hidden" name="minLevel" id="ztree_minLevel" value="${minLevel}"/>
	<input type="hidden" name="singleSelect" id="ztree_singleSelect" value="${singleSelect}"/>
	<input type="hidden" name="root" id="ztree_root" value="${root}"/>
	<input type="hidden" name="onlyOrgFlag" id="ztree_onlyOrgFlag" value="${onlyOrgFlag}"/>
	<!--modify by zhxiao3 20141113:VC-135单证库存查询优化 begin  -->
	<input type="hidden" name="pageTableName" id="ztree_pageTableName" value="${pageTableName}"/>
	<!--modify by zhxiao3 20141113:VC-135单证库存查询优化 end  -->
	<div id="divTreeDemo" class="zTreeDemoBackground left" style="background:#F6F6F6;z-index:9999;position:absolute;">
	  <ul id="treeDemo" class="ztree"></ul>
	</div>
</body>
</html>