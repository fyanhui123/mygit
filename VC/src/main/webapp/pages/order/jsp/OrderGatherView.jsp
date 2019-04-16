<% 
/*
 * 订单申请浏览界面
***************************************
 * 程序名：OrderGatherView.jsp
 * 建立日期：2013-3-27
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
<div id="gatherViewPage">
	<table style="width: 100%">
		<tr>
			<td>
			<form id="gatherViewForm" name="gatherViewForm" action="#" method="post" enctype="multipart/form-data">
				<table width="100%" id="mainTable" style="width: 100%">
					<%--详细信息模块 --%>
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="order.gather.printApplyInfo" />
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
								<tr><td><input type="hidden" name="gatherOrderId" id="gatherOrderId" value="${orderLaunchVo.orderId}"/></td></tr>
							</table>
							<table width="100%" border="0">
								<thead>
									<tr>
										<th width="20%"><s:text name="order.orderLaunchDet.verCode" /></th>
										<th width="20%"><s:text name="order.orderLaunchDet.verName" /></th>
										<th width="20%"><s:text name="order.orderLaunchDet.orderCount" /></th>
										<th width="20%"><s:text name="order.orderLaunchDet.allStore" /></th>
										<th width="20%"><s:text name="order.orderLaunchDet.applyPrintNum" /></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator id="orderLaunchDetVo" value="orderLaunchDetVos">
										<tr>
											<td align="center"><s:property value="#orderLaunchDetVo.versionCode"/></td>
											<td align="center"><s:property value="#orderLaunchDetVo.versionName"/></td>
											<td align="center"><s:property value="#orderLaunchDetVo.OrderCount"/></td>
											<td align="center"><s:property value="#orderLaunchDetVo.allStore"/></td>
											<td align="center"><s:property value="#orderLaunchDetVo.applyPrintNum"/></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
						<input id="viewOrderSubBtn" type="button" class="taButton" value="<s:text name="order.gather.viewOrderSub" />" />&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<%--审批信息模块 --%>
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="order.orderLaunch.apprInfo" />
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
							<table width="100%">
								<thead>
									<tr>
										<th width="20%"><s:text name="order.approve.checkOrgId" /></th>
										<th width="18%"><s:text name="order.approve.checkOprId" /></th>
										<th width="18%"><s:text name="order.approve.checkTime" /></th>
										<th width="16%"><s:text name="order.approve.checkStatus" /></th>
										<th width="28%"><s:text name="order.approve.checkExpl" /></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator id="vcApprove" value="vcApprove">
										<tr>
											<td align="center"><s:property value="#vcApprove.checkOrgId"/></td>
											<td align="center"><s:property value="#vcApprove.checkOprId"/></td>
											<td align="center"><s:date name="#vcApprove.checkTime" format="yyyy-MM-dd HH:mm:ss"/> </td>
											<td align="center"><s:property value="#vcApprove.checkStatus"/></td>
											<td align="center"><s:property value="#vcApprove.checkExpl"/></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<button id="gatherViewBackBtn" buttonId="b10" class="taButton" type="button">
								<s:text name="common.submit.back" />
							</button>
						</td>
					</tr>
				</table>
				</form>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
	$(document).ready(function () {
		orderGatherView.initPage();
	});
</script>

