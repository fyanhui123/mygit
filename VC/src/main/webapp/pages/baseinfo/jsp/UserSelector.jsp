<% 
/*
 * 权限机构设置界面
***************************************
 * 程序名：VcLevelSet.jsp
 * 建立日期：2013-05-03
 * 作者：chy
 * 模块：权限机构设置
 * 备注：
 * -----------------------------------------
 * 修改历史
 * 序号 日期 修改人 修改原因
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/UserSelector.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		userSelector.initPage();
	});
</script>
</head>
<body>
<div style="background: #f6f6f6;z-index: 100;position:absolute;">
	用户代码&nbsp;<input id="userCode" type="text">用户代码<input id="userName" type="text">
	
	<input id="queryBut" type="button" class="button" value="查 询" />
	
	<table id="userGrid"></table>
	<div id="userGridPage"></div>
	<input id="code" type="hidden" value ="${code }">
	<input id="name" type="hidden" value ="${name }">
</div>
					
</body>
</html>
