<% 
/*
 * 采购下单界面
***************************************
 * 程序名：PurchaseOrderList.jsp
 * 建立日期：2013-04-03
 * 作者：chy
 * 模块：征订管理-采购下单
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

<script type="text/javascript" src="${ctx}/pages/order/js/PurchaseOrderList.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		purchaseOrder.initPage()
	});
</script>

</head>
<body>
<div class="tab-center" id="purchaseOrderListPage">
<form id="form" name="form" action="#" method="post" enctype="multipart/form-data">
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
										<s:text name="order.purchase.printery" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<select  id="printeryCode" name="printeryCode" class="selectStyle" >
										</select>		
									</td>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="order.purchase.purchaseNo" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<INPUT id="purchaseCode" name="purchaseCode" type="text" class="ui-tianan-input-text">
									</td>
									<!-- 
									<td width="12%"  class="inputTitle3col">
										<s:text name="order.orderLaunch.status" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<select id="" name="flag" class="ui-tianan-input-text">
											<option value="">全部</option>
											<option value="0">新建</option>
											<option value="1">已下单</option>
											<option value="2">已收货</option>
											<option value="3">取消收货</option>
										</select>
									</td>
									 -->
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
									<button id="queryPurchaseOrder" buttonId="b01" class="taButton" type="button">
										<s:text name="common.submit.query" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="reset" buttonId="b02" class="taButton" type="button">
										<s:text name="common.submit.rest" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="purchaseOrder" buttonId="b03" class="taButton" type="button">
										<s:text name="order.purchase.purchaseOrder" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="exportPurchaseOrder" buttonId="b04" class="taButton" type="button">
									    <s:text name="common.submit.exportExcel" />
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
										<table id="purchaseOrderGrid" class="inputTable3col" ></table>
										<div id="purchaseOrderGridPager"></div>
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
</form>
</div>
</body>
</html>

