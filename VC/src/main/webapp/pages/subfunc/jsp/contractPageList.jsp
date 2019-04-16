<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

<script type="text/javascript" src="${ctx}/pages/subfunc/js/contract.js"></script>
<script type="text/javascript" src="${ctx}/pages/subfunc/js/eidt.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		common.initHtml("JcontractPageList");
		contract.initPage();
	});
</script>

</head>
<body style="background-color:transparent">
<div class="tab-center" >
	<table style="width: 100%">
		<tr>
			<td>
				<table style="width: 100%">
					<tr onclick="common.hideTR('trb','hideImg1')">
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
							<table>
								<tr>
									<td width="12%">
										<s:text name="subfunc.contract.contractName" />
									</td>
									<td width="38%">
										<INPUT id="contractName" name="contractName" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%">
										<s:text name="subfunc.contract.contractDate" />
									</td>
									<td width="38%" nowrap="nowrap">
										<input type="text" id = "contractStartDate" name="contractStartDate" class="Wdate taDatePicker" onclick="WdatePicker()"/> 至
										<input type="text" id= "contractEndDate" name="contractEndDate" class="Wdate taDatePicker"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})" />
									</td>
									
								</tr>
							</table>
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
									&nbsp;<s:text name="subfunc.contract.contractList" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<input id="queryContract" type="button" class="taButton" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
		   							<button id="addContact" buttonId="b04" class="taButton">
										<s:text name="common.submit.add" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="modifyContact" class="taButton">
										<s:text name="common.submit.alter" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="modifyContact" class="taButton">
										<s:text name="common.submit.delete" />
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
										<table id="contractGrid"></table>
										<div id="contractGridPager"></div>
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
<%@ include file="contractEdit.jsp"%>
</body>
</html>

