<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/menuDef.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/commonData.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		$('#divLayout').height($(document).height()-$('#upDiv').height()-140);
		$('#divLayout').layout({
			west_size:200,
			west__resizable:false,
			closable:false,	// pane can open & close
			resizable:false,	// when open, pane can be resized 
			slidable:true	//
		});
		
		menuDef.initAddPage();
	});
</script>
</head>
<body>	
	<div id="upDiv">
		<%--<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td height="28" valign="center">
					<img src="${ctx}/style/default/images/point4.gif" class="bottom">
					&nbsp;请选择菜单所属系统
				</td>
			</tr>
			<tr>
				<td class="pageDevide" ></td>
		    </tr>
		</table>
		
		<div id="divSystem" style="overflow:auto;">
			<table class="pagerTable">
				<tr>
					<td>
						<table id="systemDefGrid"></table>
						<div id="systemDefGridPager"></div>
					</td>
			 	</tr>
		    </table>
		</div>	--%>	
	</div>
	
	<table cellpadding="0" cellspacing="0" border="0" width="100%" style="margin-top:15px;">
		<tr>
			<td height="26" valign="center">
				<img src="${ctx}/style/default/images/point4.gif" class="bottom">
				&nbsp;<s:text name="新增菜单信息录入"/>
			</td>
			<td  align="right" valign="top" nowrap="nowrap">
				<input type="button" id="saveBut" class="taButton" value="<s:text name="common.submit.save" />"/>
				&nbsp;&nbsp;&nbsp;&nbsp;
				<input type="reset" id="reset" class="taButton" value="<s:text name="common.submit.rest" />"/>		   										   							
			</td>
		</tr>
		<tr>
			<td class="pageDevide" colspan="2">&nbsp;</td>
		</tr>
	</table>
	
	<div id="divLayout">		
		<div class="ui-layout-west" style="overflow:auto;">
			&nbsp;&nbsp;&nbsp;&nbsp;<div id="menuTree" style="overflow:auto;"></div>
		</div>
		
		<div class="ui-layout-center">		
			<form id="menuForm" name="menuForm" action="#" method="post" enctype="multipart/form-data">			
				<table class="inputTable3col" cellspacing="10" align="center" width="100%">
					<tr class="tableTr">
						<td width="12%" class="inputTitle3col" align="right">
							菜单URL
						</td>
						<td width="38%" class="inputBox3col_text">
							<input id="txtMenuUrl" value="${menuDef.menuUrl}" name="menuDef.menuUrl" type="text" />
						</td>
						<td width="12%" class="inputTitle3col" align="right">
							上级菜单
						</td>
						<td width="38%" class="inputBox3col_text">
							<input id="hidParentMenuId" value="-1" name="menuDef.parentId" type="hidden" />
							<input id="parentMenuName" disabled="disabled" readonly="readonly" type="text" class="readonlyDemo"/>
						</td>
					</tr>
					<tr class="tableTr">
						<td width="12%" class="inputTitle3col" align="right">
							 菜单名称							
						</td>
						<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
							<input id="txtMenuName" value="${menuDef.menuName}" name="menuDef.menuName" type="text" class="required" />
						</td>
						<td width="12%" class="inputTitle3col" align="right">
							菜单序号
						</td>
						<td width="38%" class="inputBox3col_text">
							<input id="txtMenuIndex" value="${menuDef.menuIndex}" name="menuDef.menuIndex" type="text" class="required number" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
</body>
</html>

