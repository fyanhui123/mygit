<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/store/js/StatusValues.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/AllotStorages.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/AllotStorageNew.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/ValidStorage.js"></script>

<script type="text/javascript">
 $(document).ready(function () {
	 provideConfirm.initQueryPage();
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
					<tr id="trbProvideConfirm">
						<td>
							<form action="#" id="provideConfirmQueryForm">
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td width="12%" class="inputTitle3col" align="left">
										调拨单号
									</td>
									<td width="38%" class="inputBox3col">
										<input id="applyCode" name="applyCode" /> 
									</td>
									<td width="12%" class="inputTitle3col" align="left">
										调拨单状态
									</td>
									<td width="38%" class="inputBox3col">
										<select id="applyStatusDropDownList"></select>
									</td>
								</tr>
								<tr>
									<td width="12%" class="inputTitle3col" align="left">
										调拨时间
									</td>
									<td width="38%" class="inputBox3col">
										<input id="initStartDate" value="<fmt:formatDate value='${initStartDate}' type='date' pattern='yyyy-MM-dd' />" name="initStartDate" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
										<s:text name="store.time.to" />
										<input id="initEndDate" value="<fmt:formatDate value='${initEndDate}' type='date' pattern='yyyy-MM-dd' />" name="initEndDate" type="text"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									</td>
									<td width="20%" class="inputTitle3col" align="right">
									</td>
									<td width="38%" class="inputBox3col">
									</td>
								</tr>
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="right">
										<input id="queryProvideConfirm" type="button" class="taButton" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="resetProvideConfirm" type="reset" onclick="provideConfirm.clearGridData('provideConfirmGrid')" class="taButton" value="<s:text name="common.submit.rest" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="provideConfirmUptBtn" type="button" class="taButton" value='<s:text name="store.provide.confirm" />' />
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
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp; <s:text name="store.applyList" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trcProvideConfirm">
						<td>
							<table class="pagerTable" style="width: 100%">
								<tr>
									<td width="100%">
										<table id="provideConfirmGrid" class="inputTable3col"></table>
										<div id="provideConfirmGridPager"></div>
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
<%@ include file="ProvideConfirmEdit.jsp"%>
<%@ include file="AllotView.jsp"%>
<%@ include file="ValidStorageQuery.jsp"%>
</body>
</html>

