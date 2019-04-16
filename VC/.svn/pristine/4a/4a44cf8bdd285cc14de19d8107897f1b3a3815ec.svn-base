
<%
	/*
	 * 遗失申请查看详情页面
	 ***************************************
	 * 程序名：LostApprove.jsp
	 * 建立日期：2013-4-15
	 * 作者：Leo
	 * 模块：单证使用-遗失申请审批
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

<div id="lostApprovePage" style="width: 100%">
	<table id="mainTable" style="width: 100%">
		<form id="lostApproveForm" name="viewForm" action="#" method="post"
			enctype="multipart/form-data">
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<!--遗失申请基本信息-->
							<s:text name="usage.lostApplyBaseInfo" />
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
						<td class="inputTitle3col" valign="top">
							<!--遗失单号-->
							<s:text name="usage.lostCode" />
						</td>
						<td>
							<input type="text" readonly="readonly" value="${vcLost.lostCode}"
								style="background-color: lightgray" size="22" />
							<input type="hidden"  name="id" value="${vcLost.id}"/>
						</td>
						<td>
							<!--遗失证明材料-->
							<s:text name="usage.lostCertificate" />
						</td>
						<td>
							<a style="color: blue"
								href="${ctx}/common/downloadById.do?id=${vcLost.id }&type=lost"><s:text name="usage.common.see" /></a>
						</td>
					</tr>
					<tr></tr>
					<tr>
						<td class="inputTitle3col" valign="top">
							<s:text name="usage.lostDate" />
						</td>
						<td>
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${vcLost.lostDate}" pattern="yyyy-MM-dd"/>"
								style="background-color: lightgray" />
						</td>
						<td class="inputTitle3col" valign="top">
							<s:text name="usage.reportLostDate" />
						</td>
						<td>
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${vcLost.reportLostDate}" pattern="yyyy-MM-dd"/>"
								style="background-color: lightgray" />
						</td>
					</tr>
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
					
					<tr>

						<td class="inputTitle3col" valign="top">
							<!--修改人-->
							<s:text name="usage.lostApplyModifyOpr" />
						</td>
						<td>
							<input type="text" readonly="readonly"
								value="${vcLost.modifyOprCode}"
								style="background-color: lightgray" />
						</td>
						<td>
							<!--修改时间-->
							<s:text name="usage.lostApplyModifyTime" />
						</td>
						<td>
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${vcLost.modifyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								style="background-color: lightgray" />
						</td>
					</tr>
					
					<tr>
						<td class="inputTitle3col" valign="top">
							<!--审批人-->
							<s:text name="usage.approveOpr" />
						</td>
						<td>
							<input type="text" readonly="readonly"
								value="${vcLost.approveOprCode}"
								style="background-color: lightgray" />
						</td>
						
						<td class="inputTitle3col" valign="top">
							<!--审批机构-->
							<s:text name="usage.approveOrg" />
						</td>
						<td>
							<input type="text" readonly="readonly"
								value="${vcLost.approveOrgCode}"
								style="background-color: lightgray" />
						</td>
						
						<td>
							<!--审批时间-->
							<s:text name="usage.approveTime" />
						</td>
						<td>
							<input type="text" readonly="readonly"
								value="<fmt:formatDate value="${vcLost.approveTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
								style="background-color: lightgray" />
						</td>
					</tr>
					
					<tr></tr>
					<tr>
						<td class="inputTitle3col" valign="top">
							<!--申请原因-->
							<s:text name="usage.lostApplyReason" />
						</td>
						<td colspan="3" class="inputBox3col_text">
							<textarea readonly="readonly" id="applyReason" name="applyReason" style="background-color: lightgray"
								rows="3" cols="80">${vcLost.lostReason }</textarea>
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

			<%--详细信息模块 --%>
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

		<tr id="trg">
			<td>
				<table width="100%" border="0">
					<thead>
						<tr>
							<th width="10%">
								<!--单证类型代码-->
								<s:text name="usage.docVerCode" />
							</th>
							<th width="10%">
								<!--单证类型名称-->
								<s:text name="usage.docVerName" />
							</th>
							<th width="5%">
								<!--印刷批次-->
								<s:text name="usage.pressBatchNo" />
							</th>
							<th width="12%">
								<!--起始流水号-->
								<s:text name="usage.startNum" />
							</th>
							<th width="13%">
								<!--终止流水号-->
								<s:text name="usage.endNum" />
							</th>
							<th width="10%">
								<!--遗失数量-->
								<s:text name="usage.lostNum" />
							</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator id="vcLostDet" value="vcLostDet">
							<tr>
								<td align="center">
									<s:property value="#vcLostDet.docVerCode" />
								</td>
								<td align="center">
									<s:property value="#vcLostDet.docVerName" />
								</td>
								<td align="center">
									<s:property value="#vcLostDet.pressBatchNo" />
								</td>
								<td align="center">
									<s:property value="#vcLostDet.startNum" />
								</td>
								<td align="center">
									<s:property value="#vcLostDet.endNum" />
								</td>
								<td align="center">
									<s:property value="#vcLostDet.lostNum" />
								</td>
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
			<%--审批意见 --%>
			<c:if test="${actionType=='approve'}">
				<tr>
					<td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td class="f-black" height="20" valign="top">
									<img src="${ctx}/style/default/images/point4.gif"
										class="bottom">
									&nbsp;
									<s:text name="order.approve.checkExpl" />
								</td>
							</tr>
							<tr>
								<td class="pageDevide"></td>
							</tr>
						</table>
					</td>
				</tr>
				<tr id="trd">
					<td align="center">
						<textarea name="approveOpp" id="approveOpp" rows="5"
							class="required" style="width: 90%"></textarea>
					</td>
				</tr>
				<tr>
					<td colspan="4">
						&nbsp;
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
					<c:if test="${actionType=='approve'}">
							&nbsp;&nbsp;
							<input type="button" id="approvePassBtn" class="taButton"
							value="<s:text name="usage.common.agree" />" />
							&nbsp;&nbsp;
							<input type="button" id="approveRebutBtn" class="taButton"
							value="<s:text name="usage.common.disagree" />" />
					</c:if>
					&nbsp;&nbsp;
					<input type="button" id="approveBackBtn" class="taButton"
						value="<s:text name="usage.common.cancel" />" />
				</td>
			</tr>

		</form>
	</table>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		lostApprove.initPage();
	});
</script>