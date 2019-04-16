<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/visausage/js/CancelS.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/ValidStorage.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	 cancelS.initQueryPage();
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
					<tr id="trbCancelS">
						<td>
							<table style="width: 100%">
								<tr>
									<td width="12%">
										<s:text name="usage.cancelCode" />
									</td>
									<td width="38%" >
										<input id="cancelCode" name="cancelCode" /> 
									</td>
									<td width="12%">
										<s:text name="usage.cancelStatus" />
									</td>
									<td width="38%" >
										<select id="cancelStatusSelect" class="selectStyle" style="width: 155px">								
										</select>
									</td>
								</tr>
								<tr>
									<td width="12%">
										<s:text name="usage.cancelTime" />
									</td>
									<td width="38%">
										<input id="initStartDate" value="<fmt:formatDate value='${initStartDate}' type='date' pattern='yyyy-MM-dd' />" name="initStartDate" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
										<s:text name="usage.time.to" />
										<input id="initEndDate" value="<fmt:formatDate value='${initEndDate}' type='date' pattern='yyyy-MM-dd' />" name="initEndDate" type="text"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									</td>
									<td width="12%"></td>
									<td width="38%"></td>
								</tr>
								<tr>
									<td class="inputTitle3col_btn" colspan="6" align="right">
										<input id="queryCancelS" type="button" class="taButton" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="resetCancelS" type="reset" onclick="cancelS.clearGridData('cancelSGrid')" class="taButton" value="<s:text name="common.submit.rest" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelSNewBtn" type="button" class="taButton" value='<s:text name="usage.common.add" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelSUptBtn" type="button" class="taButton" value='<s:text name="usage.common.update" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelSSubmitBtn" type="button" class="taButton" value='<s:text name="usage.common.submit" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelDelBtn" type="button" class="taButton" value='<s:text name="usage.common.delete" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="cancelSViewBtn" type="hidden" class="taButton" value='<s:text name="usage.common.view" />' />									
									</td>
								</tr>
							</table>
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
					<tr id="trcCancelS">
						<td>
							<table class="pagerTable" style="width: 100%">
								<tr>
									<td width="100%">
										<table id="cancelSGrid" class="inputTable3col"></table>
										<div id="cancelSGridPager"></div>
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
<%@ include file="CancelSNew.jsp"%>
<%@ include file="CancelSEdit.jsp"%>
<%@ include file="CancelSReadonly.jsp"%>
<%@ include file="/pages/store/jsp/ValidStorageQuery.jsp"%>
</body>
</html>