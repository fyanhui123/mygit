<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/visausage/js/CancelUsage.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	 cancelUsage.initQueryPage();
 }); 
</script>
</head>
<body>
<div class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('trbCancelUsage','hideImg1')">
						<td>
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
					<tr id="trbCancelUsage">
						<td>
							<form action="#" id="cancelUsageQueryForm">
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td width="12%" class="inputTitle3col">
										<s:text name="usage.cancelCode" />
									</td>
									<td width="38%" class="inputBox3col">
										<input id="cancelCode" name="cancelCode" /> 
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="usage.cancelStatus" />
									</td>
									<td width="38%" class="inputBox3col">
										<select id="cancelStatusSelect" class="selectStyle" style="width: 155px">								
										</select>
									</td>
								</tr>
								<tr>
									<td width="12%" class="inputTitle3col">
										<s:text name="usage.cancelTime" />
									</td>
									<td width="38%" class="inputBox3col">
										<input id="initStartDate" value="<fmt:formatDate value='${initStartDate}' type='date' pattern='yyyy-MM-dd' />" name="initStartDate" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
										<s:text name="usage.time.to" />
										<input id="initEndDate" value="<fmt:formatDate value='${initEndDate}' type='date' pattern='yyyy-MM-dd' />" name="initEndDate" type="text"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									</td>
									<td></td>
									<td></td>
								</tr>
								<tr>
									<td class="inputTitle3col_btn" colspan="6" align="right">
										<input id="queryCancelUsage" type="button" class="taButton" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="resetCancelUsage" type="reset" onclick="cancelUsage.clearGridData('cancelUsageGrid')" class="taButton" value="<s:text name="common.submit.rest" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelUsageNewBtn" type="button" class="taButton" value='<s:text name="usage.common.add" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelUsageUptBtn" type="button" class="taButton" value='<s:text name="usage.common.update" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelUsageSubmitBtn" type="button" class="taButton" value='<s:text name="usage.common.submit" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelUsageDelBtn" type="button" class="taButton" value='<s:text name="usage.common.delete" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelUsageViewBtn" type="hidden" class="taButton" value='<s:text name="usage.common.view" />' />			   							
									</td>
								</tr>
							</table>
							</form>
						</td>
					</tr>
					<tr onclick="common.hideTR('trcCancelUsage','hideImg2');">
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">&nbsp;<s:text name="usage.cancelList" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trcCancelUsage">
						<td>
							<table class="pagerTable" style="width: 100%">
								<tr>
									<td width="100%">
										<table id="cancelUsageGrid" class="inputTable3col"></table>
										<div id="cancelUsageGridPager"></div>
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
<%@ include file="CancelUsageNew.jsp"%>
<%@ include file="CancelUsageEdit.jsp"%>
<%@ include file="CancelUsageReadonly.jsp"%>
</body>
</html>

