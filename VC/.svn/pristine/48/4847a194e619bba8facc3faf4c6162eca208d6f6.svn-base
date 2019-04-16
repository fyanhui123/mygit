<% 
/*
 * 订单审批界面
***************************************
 * 程序名：PurchaseApproveEdit.jsp
 * 建立日期：2013-07-03
 * 作者：chy
 * 模块：征订管理-收货确认审批
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
<div id="appEditPage" >
	<table width="100%" style="width: 100%">
		<tr>
			<td>
			<form id="appEditForm" name="appEditForm" action="#" method="post" enctype="multipart/form-data">
				<table width="100%" id="mainTable" style="width: 100%">
					<%--基本信息模块 --%>
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="order.purchase.purchaseInfo" />
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
									<td width="12%">
										<s:text name="order.purchase.purchaseNo" />
									</td>
									<td width="38%" nowrap="nowrap">
										<input value="${vcPurchaseVo.purchaseId}" name="vcPurchaseVo.purchaseId" type="hidden" readonly="readonly">
										<input value="${vcPurchaseVo.purchaseCode}" name="vcPurchaseVo.purchaseCode" type="text" readonly="readonly">
									</td>
									<td width="12%">
										<s:text name="order.orderLaunch.applyOrg" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.orgName}" name="vcPurchaseVo.orgName"  type="text"  readonly="readonly"/>
									</td>
								</tr>
								<tr >
									<td width="12%">
										<s:text name="order.purchase.verCode" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.versionCode}" name="vcPurchaseVo.versionCode" type="text"  readonly="readonly"/>
									</td>
									<td width="12%">
										<s:text name="order.purchase.verName" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.versionName}" name="vcPurchaseVo.versionName" type="text"  readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td width="12%">
										<s:text name="order.purchase.startNum" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.startSerialNo}" name="vcPurchaseVo.startSerialNo" type="text"  readonly="readonly"/>
									</td>
									<td width="12%">
										<s:text name="order.purchase.receivedStartNum" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.receivedStartNum}" name="vcPurchaseVo.receivedStartNum" type="text"  readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td width="12%">
										<s:text name="order.purchase.endNum" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.endSerialNo}" name="vcPurchaseVo.endSerialNo" type="text"  readonly="readonly"/>
									</td>
									<td width="12%">
										<s:text name="order.purchase.receivedEndNum" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.receivedEndNum}" name="vcPurchaseVo.receivedEndNum" type="text"  readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td width="12%">
										<s:text name="order.purchase.applyNum" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.applyPrintNum}" name="vcPurchaseVo.applyPrintNum" type="text"  readonly="readonly"/>
									</td>
									<td width="12%">
										<s:text name="order.purchase.receivedNum" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.receivedQuantity}" name="vcPurchaseVo.receivedQuantity" type="text"  readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td width="12%">
										<s:text name="order.purchase.unitPrice" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.unitPrice}" name="vcPurchaseVo.unitPrice" type="text"  readonly="readonly"/>
									</td>
									<td width="12%">
										<s:text name="order.purchase.totalAmount" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.totalAmount}" name="vcPurchaseVo.totalAmount" type="text"  readonly="readonly"/>
									</td>
								</tr>
								<tr>
									<td width="12%">
										<s:text name="order.purchase.receivedOpr" />
									</td>
									<td width="38%">
										<input value="${vcPurchaseVo.receivedOpr}" name="vcPurchaseVo.receivedOpr" type="text"  readonly="readonly"/>
									</td>
									<td width="12%">
										<s:text name="order.purchase.receivedTime" />
									</td>
									<td width="38%">
										<input value="<fmt:formatDate value='${vcPurchaseVo.receivedTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="vcPurchaseVo.receivedTime"  type="text"  readonly="readonly"/>
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
										<th><s:text name="order.approve.checkOrgId" /></th>
										<th><s:text name="order.approve.checkOprId" /></th>
										<th><s:text name="order.approve.checkTime" /></th>
										<th><s:text name="order.approve.checkStatus" /></th>
										<th><s:text name="order.approve.checkExpl" /></th>
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
					<%--审批意见 --%>
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="order.approve.checkExpl" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td align="center">
							<textarea name="checkExpl"   rows="5"  class="required" style="width: 90%" maxByteLen="200"></textarea> 
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<button id="appPassBtn" buttonId="b04" class="taButton" type="button">
								<s:text name="order.approve.pass" />
							</button>
   							&nbsp;&nbsp;&nbsp;&nbsp;
   							<button id="appUnPassBtn" buttonId="b05" class="taButton" type="button">
								<s:text name="order.approve.unPass" />
							</button>
   							&nbsp;&nbsp;&nbsp;&nbsp;
   							<button id="appBackBtn" buttonId="b06" class="taButton" type="button">
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
		purchaseAppEdit.initPage();
	});
</script>

