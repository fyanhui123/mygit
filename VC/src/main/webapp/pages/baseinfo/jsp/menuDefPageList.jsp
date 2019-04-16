<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/menuDef.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/commonData.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		menuDef.initPage();
	});
</script>

</head>
<body style="background-color:transparent">
<div class="tab-center">
	<table style="width: 100%">
		<tr>
			<td >
				<table width="100%" style="width: 100%">
					<tr>
		              <td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td class="f-black" height="20" valign="top">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="common.form.entryQuertyCondition" />
								</td>
							</tr>
							<tr>
								<td class="pageDevide"></td>
							</tr>
						</table>
		              </td>
					</tr>
					<tr id="trb">
						<td>
						<form id="form1">
							<table style="width:100%">
								<tr >
									<td width="12%" align="right" >
										菜单名称：
									</td>
									<td width="16%">
										<INPUT id="menuName" name="menuName" type="text" class="ui-tianan-input-text">
									</td>	
									<td width="12%" align="right" >
										是否有效：
									</td>
									<td width="38%" class="inputBox3col">
										<select id="validFlagOfSearch" style="width:150px;" class="comboBox-select"  ></select>
									</td>	
									
								</tr>
							</table>
						</form>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="查询结果列表"/>
								</td>
								<td align="right" valign="top" nowrap="nowrap">
										<input id="queryMenuDef" type="button" class="button" value="<s:text name="common.submit.query" />" />
										&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input type="reset" id="resetSearch" class="button" value="<s:text name="common.submit.rest" /> " />
			   							&nbsp;&nbsp;&nbsp;&nbsp;
			   							<button id="modifyMenuDef" buttonId="b05" class="ghButton">
										<s:text name="common.submit.alter" />
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="deleteMenuDef" buttonId="b06" class="ghButton">
											<s:text name="删除" />
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="restoreMenuDef" buttonId="b07" class="taButton">
											<s:text name="恢复" />
										</button>
										&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
							</tr>
							<tr>
								<td class="pageDevide" colspan="2"></td>
							</tr>
						</table>
		              </td>
					</tr>
					<tr id="trc">
						<td>
							<table class="pagerTable">
								<tr>
									<td>
										<table id="menuDefGrid"></table>
										<div id="menuDefGridPager"></div>
										<input id="id" name="id" value="" type="hidden">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
				
				</table>
			</td>
		</tr>
	</table>
</div>
<%@ include file="menuDefEditPage.jsp"%>
</body>
</html>

