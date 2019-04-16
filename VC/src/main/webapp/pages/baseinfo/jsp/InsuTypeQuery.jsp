<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/InsuType.js"></script>
<script type="text/javascript">
$(document).ready(function () {
	insuType.initQueryPage();
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
					<tr id="trbInsuType">
						<td>
							<form action="#" id="insuTypeQueryForm" name="insuTypeQueryForm">
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td width="20%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuType.insuTypeCode" />
									</td>
									<td width="38%" class="inputBox3col">
										<INPUT id="insuTypeCode" name="insuTypeCode" type="text" class="ui-tianan-input-text">
									</td>
									<td width="20%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuType.insuTypeName" />
									</td>
									<td width="38%" class="inputBox3col">
										<input type="text" id = "insuTypeName" name="insuTypeName" />
									</td>
								</tr>
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="right">
										<input id="queryInsuType" type="button" class="taButton" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="resetInsuType" type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " onclick="common.clearGridData('insuTypeGrid')" />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="newInsuType" type="button" class="taButton" value="<s:text name="common.submit.add" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="updateInsuType" type="button" class="taButton" value="<s:text name="common.submit.alter" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="deleteInsuType" type="button" class="taButton" value="<s:text name="common.submit.delete" /> " />									
									</td>
								</tr>
							</table>
							</form>
						</td>
					</tr>
					<tr>
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp; <s:text name="vcdoc.insuType.list"></s:text>
									</td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trcInsuType">
						<td>
							<table class="pagerTable" style="width: 100%">
								<tr>
									<td width="100%">
										<table id="insuTypeGrid" class="inputTable3col"></table>
										<div id="insuTypeGridPager"></div>
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
<%@ include file="InsuTypeEdit.jsp"%>
</body>
</html>

