<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/store/js/StatusValues.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/Provide.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/Apply.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/ValidStorage.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	 provide.initQueryPage();
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
									<td height="13" background="${ctx}/style/default/images/devide-main.gif"></td>
									<td height="13" width="13" background="${ctx}/style/default/images/devide-r.gif"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trbProvide">
						<td>
							<form action="#" id="provideQueryForm" name="provideQueryForm">
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td width="12%" class="inputTitle3col" align="right">
										<s:text name="store.applyCode" />
									</td>
									<td width="38%" class="inputBox3col">
										<input id="applyCode" name="applyCode" /> 
									</td>
									<td width="12%" class="inputTitle3col" align="right">
										<s:text name="store.applyStatus" />
									</td>
									<td width="38%" class="inputBox3col">
										<select id="applyStatusDropDownList">
										</select>
									</td>
								</tr>
								<tr>
									<td width="12%" class="inputTitle3col" align="right">
										<s:text name="store.applyTime" />
									</td>
									<td width="38%" class="inputBox3col">
										<input id="initStartDate" value="<fmt:formatDate value='${initStartDate}' type='date' pattern='yyyy-MM-dd' />" name="initStartDate" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})" />
										<s:text name="store.time.to" />
										<input id="initEndDate" value="<fmt:formatDate value='${initEndDate}' type='date' pattern='yyyy-MM-dd' />" name="initEndDate" type="text"  class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									</td>
									<td width="12%" class="inputTitle3col" align="right">
									</td>
									<td width="38%" class="inputBox3col">
									</td>
								</tr>	
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="right">
										<input id="queryProvide" type="button" class="taButton" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="resetProvide" type="reset" onclick="provide.clearGridData('provideGrid')"  class="taButton" value="<s:text name="common.submit.rest" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="provideBtn" type="button" class="taButton" value='<s:text name="store.provide" />' />
									</td>
								</tr>
							</table>
							</form>
						</td>
					</tr>
					<tr onclick="common.hideTR('trcProvide','hideImg2');">
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="store.applyList" />
									</td>
								</tr>
								<tr>
									<td height="13" background="${ctx}/style/default/images/devide-main.gif"></td>
									<td height="13" width="13" background="${ctx}/style/default/images/devide-r.gif"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trcProvide">
						<td>
							<table class="pagerTable" style="width: 100%">
								<tr>
									<td width="100%">
										<table id="provideGrid" class="inputTable3col"></table>
										<div id="provideGridPager"></div>
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
<%@ include file="ProvideEdit.jsp"%>
<%@ include file="ApplyView.jsp"%>
<%@ include file="ValidStorageQuery.jsp"%>
</body>
</html>

