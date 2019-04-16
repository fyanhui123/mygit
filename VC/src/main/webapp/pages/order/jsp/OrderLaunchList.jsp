<% 
/*
 * 订单申请查询界面
***************************************
 * 程序名：OrderLaunchList.jsp
 * 建立日期：2013-3-18
 * 作者：chy
 * 模块：征订管理-订单申请
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
<script type="text/javascript" src="${ctx}/pages/order/js/OrderLaunchList.js"></script>
<script type="text/javascript" src="${ctx}/pages/order/js/OrderLaunchEdit.js"></script>
<script type="text/javascript" src="${ctx}/pages/order/js/OrderLaunchView.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		common.initHtml("OrderLaunchList");
		orderLaunch.initPage();
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
							<table width="100%" class="inputTable3col">
								<tr>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="order.orderLaunch.orderId" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<INPUT id="orderCode" name="orderCode" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="order.orderLaunch.status" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<select id="flag" name="flag" class="selectStyle">
											<option value="">全部</option>
											<option value="0">新建</option>
											<option value="1">审批通过</option>
											<option value="2">审批退回</option>
											<option value="9">待审批</option>
											<option value="3">已生成采购单</option>
										</select>
									</td>
								</tr>
								<tr>
									<td width="12%"  class="inputTitle3col" >
										<s:text name="order.orderLaunch.applyTime" />
									</td>
									<td colspan="3"  class="inputBox3col_text" >
										<input type="text" id = "applyStartDate" name="applyStartDate" 
											value="<fmt:formatDate value="${applyStartDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker" onclick="WdatePicker()" onchange="orderLaunch.checkDate()"/> 至
										<input type="text" id= "applyEndDate" name="applyEndDate" 
											value="<fmt:formatDate value="${applyEndDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker"  onclick="WdatePicker()" onchange="orderLaunch.checkDate()"/>
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
									&nbsp;<s:text name="order.orderLaunch.orderList" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id="queryOrderLaunch" buttonId="b01" class="taButton" type="button">
										<s:text name="common.submit.query" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="restBtn" buttonId="b02" class="taButton" type="button">
										<s:text name="common.submit.rest" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="addOrderLaunch" buttonId="b03" class="taButton" type="button">
										<s:text name="common.submit.add" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<!-- 
									<button id="viewOrderLaunch" buttonId="b04" class="taButton" type="button">
										<s:text name="common.submit.view" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									 -->
									<button id="modifyOrderLaunch" buttonId="b05" class="taButton" type="button">
										<s:text name="common.submit.alter" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="deleteOrderLaunch" buttonId="b06" class="taButton" type="button">
										<s:text name="common.submit.delete" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="submitOrderLaunch" buttonId="b07" class="taButton" type="button">
										<s:text name="common.submit.submit" />
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
										<table id="orderLaunchGrid" class="inputTable3col" ></table>
										<div id="orderLaunchGridPager"></div>
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
</div>
<%@include file="OrderLaunchEdit.jsp" %>
<%@include file="OrderLaunchView.jsp" %>
</body>
</html>

