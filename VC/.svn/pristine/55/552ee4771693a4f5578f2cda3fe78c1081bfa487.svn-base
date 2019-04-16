
<%
	/*
	 * 遗失申请添加/修改页面
	 ***************************************
	 * 程序名：LostApplyEdit.jsp
	 * 建立日期：2013-4-10
	 * 作者：Leo
	 * 模块：单证使用-遗失申请-添加页面
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
		<script type="text/javascript" src="${ctx}/pages/visausage/js/LostApplyEdit.js"></script>
		<script type="text/javascript" src="${ctx}/pages/store/js/ValidStorage.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				lostApplyEdit.initPage();
				lostApplyEdit.alertErrorMsg("${hasError}","${resultMsg}");	
			});
		</script>
	</head>
	<body>
<div id="lostApplyEditPage" style="width: 100%">
	<form id="lostEditForm" name="editForm" action="${ctx }/lostApply/saveLostApply.do" method="post"
		enctype="multipart/form-data">
		<table id="mainTable" style="width: 100%">
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<!--遗失申请录入-->
								<s:text name="usage.lostApplyInput" />
							</td>
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
							<td class="inputTitle3col" valign="top">
								<!--遗失单号-->
								<s:text name="usage.lostCode" />
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="${vcLost.lostCode}" style="background-color: lightgray" />
								<input type="hidden" id="vcLostId" name="id" value="${vcLost.id }" />
								<input type="hidden" name="actionType" id="actionType" value="${actionType }" />
							</td>
							<td>
								<!--遗失证明材料-->
								<s:text name="usage.lostCertificate" />
							</td>
							<td>
								<input type="file" name="file" <c:if test="${actionType == 'add'}">class="required" </c:if> />
								<c:if test="${actionType == 'modify'}">
									<a style="color: blue"
										href="${ctx}/common/downloadById.do?id=${vcLost.id }&type=lost">查看</a>
								</c:if>
							</td>
						</tr>
						<tr></tr>
						<tr>
							<td class="inputTitle3col" valign="top">
								<!--申请机构-->
								<s:text name="usage.lostApplyOrg" />
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="${vcLost.lostOrgName}"
									style="background-color: lightgray" />
							</td>

							<td class="inputTitle3col" valign="top">
								<!--申请人-->
								<s:text name="usage.lostApplyOpr" />
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="${vcLost.lostOprName}"
									style="background-color: lightgray" />
							</td>
							<td>
								<!--申请时间-->
								<s:text name="usage.lostApplyTime" />
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="<fmt:formatDate value="${vcLost.lostTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
									style="background-color: lightgray" />
							</td>
						</tr>
						<tr></tr>
						<tr>
							<td class="inputTitle3col" valign="top">
								<s:text name="usage.lostDate"/>
							</td>
							<td>
								<input type="text" id="lostDate" name="lostDate" 
									value="<fmt:formatDate value="${vcLost.lostDate}" pattern="yyyy-MM-dd"/>"
									class="Wdate taDatePicker required" onclick="WdatePicker()" />
							</td>
							<td>
								<s:text name="usage.reportLostDate"/>
							</td>
							<td>
								<input type="text" id="reportLostDate" name="reportLostDate" 
									value="<fmt:formatDate value="${vcLost.reportLostDate}" pattern="yyyy-MM-dd"/>"
									class="Wdate taDatePicker required" onclick="WdatePicker()" />
							</td>
						</tr>
						<tr></tr>
						<tr>
							<td class="inputTitle3col" valign="top">
								<!--申请原因-->
								<s:text name="usage.lostApplyReason" />
							</td>
							<td colspan="3" class="inputBox3col_text">
								<textarea id="lostReason" name="lostReason" rows="3" cols="80" class="required">${vcLost.lostReason }</textarea>
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
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<!--遗失申请详细信息-->
								<s:text name="usage.lostApplyDetails" />
							</td>
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
						<div id="lostApplyEditList"></div>
					</div>
					<textarea id="edit_hiddenLostDet" style="display: none;" rows="4">${jsonStr }</textarea>
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
									<s:text name="usage.lostApproveDetails" />
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
				<td colspan="4" align="center">
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
