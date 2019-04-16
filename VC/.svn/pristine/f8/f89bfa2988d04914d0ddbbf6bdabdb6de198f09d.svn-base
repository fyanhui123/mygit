<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/VcRoleEdit.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		distributeResource.initPage();
	});
</script>
</head>
<body style="margin:0 0;">
	<div  style="overflow:auto;" class="ui-layout-west" >
		<form name="fmRoleDefQuery" action="" id="form1">
		<input id="roleNameOfSearch" name="roleName" type="text" style="width:160px;"  class="ui-tianan-input-text"/>&nbsp;&nbsp;&nbsp;&nbsp;<input id="btnQueryRole" style="margin-bottom:7px;" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
		<table class="pagerTable">
			<tr>
				<td>
					<table id="roleDefGrid"></table>
					<div id="roleDefGridPager"></div>
				</td>
			</tr>
		</table>
		</form>
	</div>
	<div id="rightDiv" class="ui-layout-center" >
		<div id="tabs">
			<ul id="ulHead">
				<li><a href="#tabs-1">菜单权限</a></li>
			</ul>
			<div  id="tabs-1">
					<div class="ui-layout-north">
					<table style="width:100%">
								<tr >
									<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;菜单列表
									</td>
									<td width="38%" align="right"  class="inputBox3col">
										<input  type="button" id="distributeSaveBut" value="<s:text name="common.submit.save" />" />
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>	
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>
					</table>
					</div>
					<div style="overflow:auto;"  id="menuTree" class="ui-layout-center" ></div>
			</div>
			<div id="tabs-2">
				
			</div>
			<div id="tabs-3">
			</div>
		</div>
	</div>
</body>
</html>

