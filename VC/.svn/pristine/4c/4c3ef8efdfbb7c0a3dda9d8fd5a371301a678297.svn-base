<% 
/*
 * 订单汇总查询界面
***************************************
 * 程序名：OrderGatherList.jsp
 * 建立日期：2013-3-25
 * 作者：chy
 * 模块：征订管理-订单汇总
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

<script type="text/javascript" src="${ctx}/pages/order/js/OrderGatherList.js"></script>
<script type="text/javascript" src="${ctx}/pages/order/js/OrderGatherInput.js"></script>
<script type="text/javascript" src="${ctx}/pages/order/js/OrderGatherView.js"></script>
<script type="text/javascript" src="${ctx}/pages/order/js/OrderGatherEdit.js"></script>

<script type="text/javascript">
	$(document).ready(function () {
		orderGather.initPage();
	});
</script>

</head>
<body>
<div class="tab-center" id="gatherListPage">
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
									<td width="12%"  class="inputTitle3col">
										<s:text name="order.orderLaunch.orderId" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<INPUT id="orderCode" name="orderCode" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%"  class="inputTitle3col">
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
									<td width="12%" class="inputTitle3col" >
										<s:text name="order.orderLaunch.applyTime" />
									</td>
									<td colspan="3"  class="inputBox3col_text" >
										<input type="text"  id="applyStartDate" name="applyStartDate" 
											value="<fmt:formatDate value="${applyStartDate}" pattern="yyyy-MM-dd"/>" 
											class="Wdate taDatePicker" onclick="WdatePicker()" onchange="orderGather.checkDate()"/> 至
										<input type="text"  id="applyEndDate" name="applyEndDate" 
											value="<fmt:formatDate value="${applyEndDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker"  onclick="WdatePicker()" onchange="orderGather.checkDate()"/>
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
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="order.orderLaunch.orderList" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id="queryOrderGather" buttonId="b01" class="taButton" type="button">
										<s:text name="common.submit.query" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="reset" buttonId="b02" class="taButton" type="button">
										<s:text name="common.submit.rest" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="addOrderGather" buttonId="b03" class="taButton" type="button">
										<s:text name="common.submit.add" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<!-- 
									<button id="viewOrderGather" buttonId="b04" class="taButton" type="button">
										<s:text name="common.submit.view" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									 -->
									<button id="modifyOrderGather" buttonId="b05" class="taButton" type="button">
										<s:text name="common.submit.alter" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="deleteOrderGather" buttonId="b06" class="taButton" type="button">
										<s:text name="common.submit.delete" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="submitOrderGather" buttonId="b07" class="taButton" type="button">
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
										<table id="orderGatherGrid" class="inputTable3col" ></table>
										<div id="orderGatherGridPager"></div>
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
<%@include file="OrderGatherInput.jsp" %>
<%@include file="OrderGatherEdit.jsp" %>
<%@include file="OrderGatherView.jsp" %>
<%@include file="OrderGatherSubView.jsp" %>
</body>
</html>

