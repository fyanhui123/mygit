<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/InsuKind.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	insuKind.initQueryPage();
});
</script>
</head>
<body>
<div class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr>
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="common.form.entryQuertyCondition" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>	
							</table>
						</td>
					</tr>
					<tr id="trbInsuKind">
						<td>
							<form action="#" id="insuKindQueryForm" name="insuKindQueryForm">
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td width="12%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuKind.insuType"></s:text>
									</td>
									<td width="25%" class="inputBox3col">
										<select style="width: 100px" id="insuTypeList" name="insuTypeId">
										</select>											
									</td>
									<td width="12%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuKind.insuKindCode"></s:text>
									</td>
									<td width="25%" class="inputBox3col">
										<INPUT id="insuKindCode" name="insuKindCode" type="text" class="ui-tianan-input-text">
									</td>									
									<td width="12%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuKind.insuKindName"></s:text>
									</td>
									<td width="38%" class="inputBox3col">
										<input type="text" id = "insuKindName" name="insuKindName" />
									</td>
									
								</tr>
								<tr>
									<td class="inputTitle3col_btn" colspan="6" align="right">
										<input id="queryInsuKind" type="button" class="taButton" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="resetInsuKind" type="reset" class="taButton" onclick="common.clearGridData('insuKindGrid')" value="<s:text name="common.submit.rest" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="newInsuKind" type="button" class="taButton" value="<s:text name="common.submit.add" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="updateInsuKind" type="button" class="taButton" value="<s:text name="common.submit.alter" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="deleteInsuKind" type="button" class="taButton" value="<s:text name="common.submit.delete" /> " />									
									</td>
								</tr>
							</table>
							</form>
						</td>
					</tr>
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black"  height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="vcdoc.insuKind.list"></s:text>
									</td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="trcInsuKind">
						<td>
							<table class="pagerTable">
								<tr>
									<td>
										<table id="insuKindGrid" class="inputTable3col" ></table>
										<div id="insuKindGridPager"></div>
										<input id="id" name="id" value="" type="hidden">
									</td>
								</tr>
								<tr>
									<td width="100%" colspan="4">
										
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
<%@ include file="InsuKindEdit.jsp"%>
</body>
</html>

