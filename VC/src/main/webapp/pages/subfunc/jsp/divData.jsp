<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/subfunc/js/divData.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		divData.initPage();
	});
</script>
</head>
<body>
<div class="checkSelectDiv" style="height: 200px;">
	合同名&nbsp;<input id="contractName" name="contractName" type="text">
	
	<input id="queryBut" type="button" class="button" value="查 询" />
	
	<table id="grid_address1"></table>
	<div id="gridPager_address1"></div>
	<input id="divId" type="hidden" value ="address1">
</div>
					
</body>
</html>

