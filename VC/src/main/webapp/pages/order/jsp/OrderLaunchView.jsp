<% 
/*
 * 订单申请浏览界面
***************************************
 * 程序名：OrderLaunchView.jsp
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
<div id = "orderViewPage" >
	<table width="100%" >
		<tr>
			<td>
			<form id="viewForm" name="viewForm" action="#" method="post" enctype="multipart/form-data">
				<table width="100%" id="mainTable" style="width: 100%">
					<%--基本信息模块 --%>
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="order.orderLaunch.baseInfo" />
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
								<tr >
									<td width="12%" >
										<s:text name="order.orderLaunch.orderId" />
									</td>
									<td width="38%" nowrap="nowrap">
										<input value="${orderLaunchVo.orderCode}" name="orderLaunchVo.orderCode" type="text" readonly="readonly">
									</td>
									<td width="12%" >
										<s:text name="order.orderLaunch.applyOrg" />
									</td>
									<td width="38%">
										<input value="${orderLaunchVo.orgName}" name="orderLaunchVo.orgName"  type="text"  readonly="readonly"/>
									</td>
								</tr>
								<tr >
									<td width="12%" >
										<s:text name="order.orderLaunch.applyer" />
									</td>
									<td width="38%">
										<input value="${orderLaunchVo.createUserName}" name="orderLaunchVo.createUserName" type="text"  readonly="readonly"/>
									</td>
									<td width="12%" >
										<s:text name="order.orderLaunch.applyTime" />
									</td>
									<td width="38%">
										<input value="<fmt:formatDate value='${orderLaunchVo.createDate}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="orderLaunchVo.createDate"  type="text"  readonly="readonly"/>
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
					<%--详细信息模块 --%>
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="order.orderLaunch.detInfo" />
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
							<table width="100%" border="0">
								<thead>
									<tr>
										<th width="25%"><s:text name="order.orderLaunchDet.verCode" /></th>
										<th width="35%"><s:text name="order.orderLaunchDet.verName" /></th>
										<th width="20%"><s:text name="order.orderLaunchDet.orderCount" /></th>
										<th width="20%"><s:text name="order.orderLaunchDet.allStore" /></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator id="orderLaunchDetVo" value="orderLaunchDetVos">
										<tr>
											<td align="center"><s:property value="#orderLaunchDetVo.versionCode"/></td>
											<td align="center"><s:property value="#orderLaunchDetVo.versionName"/></td>
											<td align="center"><s:property value="#orderLaunchDetVo.applyPrintNum"/></td>
											<td align="center"><s:property value="#orderLaunchDetVo.allStore"/></td>
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
							<button id="viewBackBtn" buttonId="b10" class="taButton" type="button">
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
			orderLaunchView.initPage();
	});
</script>

