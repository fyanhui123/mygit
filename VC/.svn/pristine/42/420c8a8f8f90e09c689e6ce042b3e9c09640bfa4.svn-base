
<%
	/*
	 * 遗失销毁添加/修改页面
	 ***************************************
	 * 程序名：DestroyEdit.jsp
	 * 建立日期：2013-4-18
	 * 作者：Leo
	 * 模块：单证使用-遗失销毁-添加页面
	 * 备注：
	 * -----------------------------------------
	 * 修改历史
	 * 序号 日期 修改人 修改原因
	 * 
	 ***************************************
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
		<script type="text/javascript" src="${ctx}/pages/newInvoice/js/CancellationEdit.js"></script>
		<script type="text/javascript" src="${ctx}/pages/store/js/ValidStorage.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				destroyEdit.initPage();
			});
		</script>
	</head>
	<body>

		<div id="destroyEditPage" style="width: 100%">
			<form id="destroyEditForm" action="${ctx}/newDestroy/saveDestroy.do"
				method="post" enctype="multipart/form-data">
				<table id="mainTable" style="width: 100%">
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif"
											class="bottom">
										&nbsp;
										<!--销毁申请录入-->
										缴销申请录入
										<%--<s:text name="usage.destroyApplyInput" />
									--%></td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="trd">
						<td>
							<table width="100%" class="inputTable3col">
								<tr>
									<td>
										<!--销毁单号-->
										缴销单号
										<%--<s:text name="usage.destroyCode" />
									--%></td>
									<td>
										<input type="text" readonly="readonly"
											value="${vcDestroy.destroyCode}"
											style="background-color: lightgray" />
										<input type="hidden" name="id" id="vcDestroyId"
											value="${vcDestroy.id }" />
										<input type="hidden" name="actionType" id="actionType"
											value="${actionType }" />
									</td>

									<td>
										<!--缴销类型-->
										缴销类型
										<%--<s:text name="usage.destroyType" />	
									--%></td>
									<td>
										<select name="destroyType" class="ui-tianan-input-text"
											id="destroyType_ajax""></select>
										<input type="hidden" id="destroyTypeValue" value="${vcDestroy.destroyType }" />
									</td>

									<td>
										<!--销毁证明材料-->
										缴销证明材料
										<%--<s:text name="usage.destroyCertificate" />	
									--%></td>
									<td>
										<input type="file" name="file"
											<c:if test="${actionType == 'add'}">class="required" </c:if> />
										<c:if test="${actionType == 'modify'}">
											<a style="color: blue"
												href="${ctx}/common/downloadById.do?id=${vcDestroy.id }&type=destroy"><s:text name="usage.common.see" /></a>
										</c:if>
									</td>


								</tr>
								<tr></tr>
								<tr>
									<td>
										<!--申请机构-->
										<s:text name="usage.destroyApplyOrg" />	
									</td>
									<td>
										<input type="text" readonly="readonly"
											value="${vcDestroy.destroyOrgName}"
											style="background-color: lightgray" />
									</td>

									<td>
										<!--申请人-->
										<s:text name="usage.destroyApplyOpr" />	
									</td>
									<td>
										<input type="text" readonly="readonly"
											value="${vcDestroy.destroyOprName}"
											style="background-color: lightgray" />
									</td>
									<td>
										<!--申请时间-->
										<s:text name="usage.destroyApplyTime" />	
									</td>
									<td>
										<input type="text" readonly="readonly"
											value="<fmt:formatDate value="${vcDestroy.destroyAppTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
											style="background-color: lightgray" />
									</td>
								</tr>
								<tr></tr>
								<tr>
									<td>
										<!--申请原因-->
										<s:text name="usage.destroyApplyReason" />
									</td>
									<td colspan="4" class="inputBox3col_text">
										<textarea name="destroyReason" rows="3" cols="80"
											class="required">${vcDestroy.destroyReason }</textarea>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif"
											class="bottom">
										&nbsp;
										<!--销毁申请详细信息-->
										缴销申请详细信息
										<%--<s:text name="usage.destroyApplyDetails" />
									--%></td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>
							</table>
						</td>
					</tr>

					<tr id="tre">
						<td>
							<div style="width: 98%">
								<div id="destroyEditList"></div>
							</div>
							<textarea id="edit_hiddenDestroy" style="display: none;" rows="4">${jsonStr }</textarea>
							<textarea id="jsonStr" name='jsonStr' style="display: none;" ></textarea>
						</td>
					</tr>
					<c:if test="${actionType=='modify'}">
						<%--审批信息模块 --%>
						<tr>
							<td>
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td class="f-black" height="20" valign="top">
											<img src="${ctx}/style/default/images/point4.gif"
												class="bottom">
											&nbsp;
											<!--历史审批信息-->
											<s:text name="usage.destroyApproveDetails" />
										</td>
									</tr>
									<tr>
										<td class="pageDevide"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr id="tri">
							<td>
								<table width="100%">
									<thead>
										<tr>
											<th>
												<s:text name="usage.approveOrg" />
											</th>
											<th>
												<s:text name="usage.approveOpr" />
											</th>
											<th>
												<s:text name="usage.approveTime" />
											</th>
											<th>
												<s:text name="usage.approveStatus" />
											</th>
											<th>
												<s:text name="usage.approveExpl" />
											</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach items="${vcApprove}" var="vcApprove_">
											<tr>
												<td align="center">
													${vcApprove_.checkOrgId}
												</td>
												<td align="center">
													${vcApprove_.checkOprId}
												</td>
												<td align="center">
													<fmt:formatDate value="${vcApprove_.checkTime}"
														pattern="yyyy-MM-dd HH:mm:ss" />
												</td>
												<td align="center">
													${vcApprove_.checkStatus}
												</td>
												<td align="center">
													${vcApprove_.checkExpl}
												</td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</td>
						</tr>
					</c:if>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center" colspan="4">
							<input type="button" id="saveBut" class="taButton"
								value="<s:text name="common.submit.save" />" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="submitBut" class="taButton" value="<s:text name="usage.common.submit" />" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="saveBackBtn" class="taButton" value="<s:text name="usage.common.cancel" />" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		<%@ include file="/pages/store/jsp/ValidStorageQuery.jsp"%>
	</body>
</html>
