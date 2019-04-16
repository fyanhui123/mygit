<% 
/*
 * 福建领购发票界面
***************************************
 * 程序名：InvoicePurchaseList.jsp
 * 建立日期：2013-8-1
 * 作者：chy
 * 模块：发票领用
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
<head>
<script type="text/javascript" src="${ctx}/pages/invoice/js/InvoicePurchaseList.js"></script>
<script type="text/javascript" src="${ctx}/pages/invoice/js/InvoiceSelectDocVersionList.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		common.initHtml("InvoicePurchaseList");
		invoicePurchase.initPage();
	});
</script>

</head>
<body>
<div class="tab-center">
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
							<form id="invoicePurchaseForm" name="invoicePurchaseForm" action="#" method="post" enctype="multipart/form-data">
							<table width="100%" class="inputTable3col">
								<tr>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="inquiry.storage.orgCode"/>
									</td>
									<td colspan="2" class="inputBox3col_text" >
										<input id="invoicePurchaseOrgCode" name="invoicePurchaseOrgCode" class="ui-autocomplete-input required" type="text" /> 
										<input id="invoicePurchaseOrgName" style="width: 100px;" type="text" readonly="readonly" />
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
					
					<!--baseinfo  start  -->
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="invoice.invoicePurchase.baseinfo"/>
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id="plantFormImport" buttonId="b01" class="taButton" type="button">
										<s:text name="invoice.invoicePurchase.plantFormImport" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="inStoreInvoicePurchase" buttonId="b02" class="taButton" type="button">
										<s:text name="invoice.invoicePurchase.inStore" />
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
										<table id="invoicePurchaseBaseinfoGrid" class="inputTable3col" ></table>
										<div id="invoicePurchaseBaseinfoGridPager"></div>
										<input id="id" name="id" type="hidden">
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<!--baseinfo  start  -->
					
					<!--purchase  start  -->
				 	<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="invoice.invoicePurchase.purchase"/>
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
										<table id="invoicePurchasePurchaseGrid" class="inputTable3col" ></table>
										<div id="invoicePurchasePurchaseGridPager"></div>
								       <input id="invoiceShortCodeId" name="invoiceShortCodeId" type="hidden"> 
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>  
					<!--purchase  end  -->
					
					<!--impower  start  -->
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="invoice.invoicePurchase.impower"/>
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
										<table id="invoicePurchaseImpowerGrid" class="inputTable3col" ></table>
										<div id="invoicePurchaseImpowerGridPager"></div>
								      <!--   <input id="id" name="id" value="" type="hidden">   -->
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>  
					<!--impower  end  -->
					
					
						<!--impowerDet  start  -->
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="invoice.invoicePurchase.impowerDet"/>
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
										<table id="invoicePurchaseImpowerDetGrid" class="inputTable3col" ></table>
										<div id="invoicePurchaseImpowerDetGridPager"></div>
								<!-- 	   <input id="id" name="id" value="" type="hidden">    -->
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>  
					<!--impowerDet  end  -->
					
					
				</table>
			</td>
		</tr>
	</table>
</form>
</div>
<%@include file="InvoiceSelectDocVersionList.jsp" %>
</body>
</html>

