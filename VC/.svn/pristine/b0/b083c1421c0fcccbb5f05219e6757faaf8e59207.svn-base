<% 
/*
 * 发票导出查询界面
***************************************
 * 程序名：InvoiceExportList.jsp
 * 建立日期：2013-6-6
 * 作者：chy
 * 模块：发票导出
 * 备注：
 * -----------------------------------------
 * 修改历史
 * 序号 日期 修改人 修改原因
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/invoice/js/InvoiceExportList.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		invoiceExport.initPage();
	});
</script>

</head>
<body>
<div class="tab-center" id="orderListPage">
	<table style="width: 100%">
		<tr>
			<td>
				<table id="mainTable" style="width: 100%">
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
					<tr>
						<td>
							<form id="invoiceExportForm" name="invoiceExportForm" action="#" method="post" enctype="multipart/form-data">
							<table width="100%" class="inputTable3col">
								<!--
								<tr>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.insuType" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<select id="insuTypeList" name="invoiceExportVo.insuTypeId" class="selectStyle">
										</select>
									</td>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.insuKind" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<select id="insuKindList" name="invoiceExportVo.insuKindCode" class="selectStyle">
										</select>
									</td>
								</tr>
								-->
								<tr>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.policyNo" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input id="policyNo" name="invoiceExportVo.policyNo" type="text" class="ui-tianan-input-text" />
									</td>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.endorseNo" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input id="endorseNo" name="invoiceExportVo.endorseNo" type="text" class="ui-tianan-input-text" />
									</td>
								</tr>
								<tr>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.insuredName" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input id="insuredName" name="invoiceExportVo.insuredName" type="text" class="ui-tianan-input-text" />
									</td>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.payerName" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input id="payerName" name="invoiceExportVo.payerName" type="text" class="ui-tianan-input-text" />
									</td>
								</tr>
								<tr>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.printDateStart" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input type="text" id = "printDateStart" name="invoiceExportVo.printDateStart" class="Wdate taDatePicker" onclick="WdatePicker()" onchange="invoiceExport.checkDate()"/>
									</td>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.printDateEnd" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input type="text" id = "printDateEnd" name="invoiceExportVo.printDateEnd" class="Wdate taDatePicker" onclick="WdatePicker()" onchange="invoiceExport.checkDate()"/>
									</td>
								</tr>
								<tr>
									<!-- 
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.inputCompany" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<select id="inputCompany" name="invoiceExportVo.inputCompany" class="selectStyle">
										</select>
									</td>
									-->
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.invoiceStatus" />
									</td>
									<td width="38%"  class="inputBox3col_text">
										<select id="invoiceStatus" name="invoiceExportVo.invoiceStatus" class="selectStyle">
										</select>
									</td>
									 <td width="12%"  class="inputTitle3col">
										<s:text name="invoice.invoiceExport.invoiceCode" />
									</td>
									<td width="38%"  class="inputBox3col_text">
										<input id="invoiceCode" name="invoiceExportVo.invoiceCode" type="text" class="ui-tianan-input-text" />
									</td>
								</tr>
								<tr>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="invoice.invoiceExport.inputCompany" />
									</td>
									<td colspan="3"  class="inputBox3col_text" >
										<input id="inputCompany" name="invoiceExportVo.inputCompany" class="ui-autocomplete-input" type="text" />
										<input id="inputCompanyName" style="width: 100px;" type="text" readonly="readonly" />
									</td>
								</tr>
							</table>
							</form>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="order.orderLaunch.orderList" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id="queryInvoiceExport" buttonId="b01" class="taButton" type="button">
										<s:text name="common.submit.query" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="restBtn" buttonId="b02" class="taButton" type="button">
										<s:text name="common.submit.rest" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="exportInvoice" buttonId="b03" class="taButton" type="button">
										<s:text name="invoice.invoiceExport.export" />
									</button>
								</td>
							</tr>
							<tr>
								<td class="pageDevide" colspan="2"></td>
							</tr>
						</table>
		              </td>
					</tr>
					<tr>
						<td>
							<table class="pagerTable">
								<tr>
									<td>
										<table id="invoiceExportGrid" class="inputTable3col" ></table>
										<div id="invoiceExportGridPager"></div>
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
</form>
</div>
</body>
</html>

