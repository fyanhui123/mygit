<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/visausage/js/CancelApprove.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	 cancelApprove.initQueryPage();
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
						<td style="width: 100%">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="common.form.entryQuertyCondition" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trbCancelApproveQuery">
						<td>
							<form action="#" id="cancelApproveQueryForm" name="cancelApproveQueryForm">
							<table style="width: 100%">
								<tr>
									<td width="12%"  align="left">
										<s:text name="usage.cancelCode" />
									</td>
									<td width="38%" >
										<input id="cancelCode" name="cancelCode" /> 
									</td>

									<td width="12%"  align="left">
										<s:text name="usage.cancelStatus" />
									</td>
									<td width="38%" >
										<select id="cancelStatusSelect" class="selectStyle" style="width: 155px">
										</select>
									</td>
								</tr>
								<tr>
									<td width="12%"  align="left">
										<s:text name="usage.cancelType" />
									</td>
									<td width="38%" >
										<select id="cancelTypeSelect" class="selectStyle" style="width: 155px">
										</select>
									</td>
									<td width="12%"  align="left">
									</td>
									<td width="38%" >
									</td>
								</tr>								
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="right">
										<input id="queryCanceApprove" type="button" class="taButton" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="resetCancelApprove" type="reset" onclick="cancelApprove.clickReset('cancelApproveGrid')" class="taButton" value="<s:text name="common.submit.rest" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelApproveBtn" type="button" class="taButton" value='<s:text name="usage.common.approve" />' />
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
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp; <s:text name="usage.cancelList" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trcCancelApprove">
						<td>
							<table class="pagerTable" style="width: 100%">
								<tr>
									<td width="100%">
										<table id="cancelApproveGrid" class="inputTable3col"></table>
										<div id="cancelApproveGridPager"></div>
										<input id="id" name="id" value="" type="hidden">												
									</td>
								</tr>
							</table>
						</td>
					</tr>					
				</table>
			</td>
		</tr>
	</table>
</div>
<%@ include file="CancelApproveEdit.jsp"%>
<%@ include file="CancelApproveReadonly.jsp"%>
</body>
</html>