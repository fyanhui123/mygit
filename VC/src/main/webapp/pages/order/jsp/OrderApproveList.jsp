<% 
/*
 * 订单审批查询界面
***************************************
 * 程序名：OrderApproveList.jsp
 * 建立日期：2013-3-22
 * 作者：chy
 * 模块：征订管理-订单审批
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

<script type="text/javascript" src="${ctx}/pages/order/js/OrderApproveList.js"></script>
<script type="text/javascript" src="${ctx}/pages/order/js/OrderApproveEdit.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		orderApprove.initPage();
	});
</script>

</head>
<body>
<div class="tab-center">
	<table style="width: 100%">
		<tr>
			<td>
			   <input type="hidden" id="curComCode" value="${curComCode}">
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
							<table>
								<tr>
									<td width="12%" >
										<s:text name="order.orderLaunch.orderId" />
									</td>
									<td width="38%" >
										<INPUT id="orderCode" name="orderCode" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%" >
										<s:text name="order.orderLaunch.applyTime" />
									</td>
									<td width="38%" >
										<input type="text" id= "applyStartDate" name="applyStartDate" 
											value="<fmt:formatDate value="${applyStartDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker" onclick="WdatePicker()" onchange="orderApprove.checkDate()"/> 至
										<input type="text" id="applyEndDate" name="applyEndDate" 
											value="<fmt:formatDate value="${applyEndDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker"  onclick="WdatePicker()" onchange="orderApprove.checkDate()"/>
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
									<button id="queryOrderApprove" buttonId="b01" class="taButton" type="button">
										<s:text name="common.submit.query" />
									</button>
		   							&nbsp;&nbsp;&nbsp;&nbsp;
		   							<button id="reset" buttonId="b02" class="taButton" type="button">
										<s:text name="common.submit.rest" />
									</button>
		   							&nbsp;&nbsp;&nbsp;&nbsp;
		   							<button id="approveBtn" buttonId="b03" class="taButton" type="button">
										<s:text name="order.approve.approve" />
									</button>
									<c:if test="${curComCode=='01'}">
									&nbsp;&nbsp;&nbsp;&nbsp;
		   							<button id="approvedBackBtn" buttonId="b04" class="taButton" type="button">
										<s:text name="order.approve.approvedBack" />
									</button>
									</c:if>
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
										<table id="orderApproveGrid" class="inputTable3col" ></table>
										<div id="orderApproveGridPager"></div>
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
<%@include file="OrderApproveEdit.jsp"%>
</body>
</html>

