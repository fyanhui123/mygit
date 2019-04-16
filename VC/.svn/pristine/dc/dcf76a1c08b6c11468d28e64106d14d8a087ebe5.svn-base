<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/store/js/StatusValues.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/Recycle.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/ValidStorage.js"></script>

<script type="text/javascript">
 $(document).ready(function () {
	 recycle.initQueryPage();
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
					<tr id="trbRecycle">
						<td>
							<form action="#" id="recycleQueryForm" name="recycleQueryForm">
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td width="15%" class="inputTitle3col" align="left">
										<s:text name="store.recycleCode" />
									</td>
									<td width="38%" class="inputBox3col">
										<input id="recycleCode" name="recycleCode" /> 
									</td>
									<td width="15%" class="inputTitle3col" align="left">
										<s:text name="store.recycleStatus" />
									</td>
									<td width="38%" class="inputBox3col">
										<select id="recycleStatusSelect">
										</select>
									</td>
								</tr>
								<tr>
									<td width="15%" class="inputTitle3col" align="left">
										<s:text name="store.recycleTime" />
									</td>
									<td width="38%" class="inputBox3col" colspan="3">
										<input id="initStartDate" value="<fmt:formatDate value='${initStartDate}' type='date' pattern='yyyy-MM-dd' />" name="initStartDate" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
										<s:text name="store.time.to" />
										<input id="initEndDate" value="<fmt:formatDate value='${initEndDate}' type='date' pattern='yyyy-MM-dd' />" name="initEndDate" type="text"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									</td>
								</tr>
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="right">
										<input id="queryRecycle" type="button" class="taButton" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="resetRecycle" type="reset" class="taButton" onclick="recycle.clearGridData('recycleGrid');" value="<s:text name="common.submit.rest" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="recycleNewBtn" type="button" class="taButton" value='<s:text name="store.common.add" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="recycleUptBtn" type="button" class="taButton" value='<s:text name="store.common.update" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="recycleSubmitBtn" type="button" class="taButton" value='<s:text name="store.common.submit" />' />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="recycleDelBtn" type="button" class="taButton" value='<s:text name="store.common.delete" />' />&nbsp;&nbsp;&nbsp;&nbsp;			   		
										<input id="recycleViewBtn" type="hidden" class="taButton" value='<s:text name="store.common.view" />' />						
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
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp; <s:text name="store.recycleList" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trcRecycle">
						<td>
							<table class="pagerTable" style="width: 100%">
								<tr>
									<td width="100%">
										<table id="recycleGrid" class="inputTable3col"></table>
										<div id="recycleGridPager"></div>
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
<%@ include file="RecycleNew.jsp"%>
<%@ include file="RecycleEdit.jsp"%>
<%@ include file="RecycleReadonly.jsp"%>
<%@ include file="ValidStorageQuery.jsp"%>
</body>
</html>

