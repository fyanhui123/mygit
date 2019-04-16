<% 
/*
 * 采购下单界面
***************************************
 * 程序名：PurchaseReceiptList.jsp
 * 建立日期：2013-04-08
 * 作者：chy
 * 模块：征订管理-收货确认
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

<script type="text/javascript" src="${ctx}/pages/order/js/PurchaseReceiptList.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		purchaseReceipt.initPage();
	});
</script>

</head>
<body>
<div class="tab-center" id="purchaseReceiptListPage">
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
							<table width="100%" class="inputTable3col">
								<tr>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="order.orderLaunch.applyTime" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input type="text" id = "applyStartDate" name="applyStartDate" 
											value="<fmt:formatDate value="${applyStartDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker" onclick="WdatePicker()" onchange="purchaseReceipt.checkDate()"/> 至
										<input type="text" id= "applyEndDate" name="applyEndDate" 
											value="<fmt:formatDate value="${applyEndDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker"  onclick="WdatePicker()" onchange="purchaseReceipt.checkDate()"/>
									</td>
									<td width="12%"  class="inputTitle3col">
										<s:text name="order.orderLaunch.status" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<select id="flag" name="flag" class="selectStyle">
											<option value="">全部</option>
											<option value="0">新建</option>
											<option value="1">已下单</option>
											<option value="2">已收货</option>
											<option value="3">取消收货</option>
											<%--<option value="4">待审批</option>
											--%><option value="5">审批退回</option>
											<%--<option value="6">审批通过</option>
										--%></select>
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
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="order.purchase.purchaseList" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id="queryPurchaseReceipt" buttonId="b01" class="taButton" type="button">
										<s:text name="common.submit.query" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="reset" buttonId="b02" class="taButton" type="button">
										<s:text name="common.submit.rest" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="receiptConfirm" buttonId="b03" class="taButton" type="button">
										<s:text name="order.purchase.receiptConfirm" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									
									<button id="receiptCancel" buttonId="b04" class="taButton" type="button">
										<s:text name="取消收货" />
									</button>
									<%--<button id="submitApprove" buttonId="b05" class="taButton" type="button">
										<s:text name="order.purchase.submitApprove" />
									</button>
								--%></td>
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
										<table id="purchaseReceiptGrid" class="inputTable3col" ></table>
										<div id="purchaseReceiptGridPager"></div>
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
</body>
</html>

