
<%
	/*
	 * 销毁审批页面/查看详情
	 ***************************************
	 * 程序名：DestroyApprove.jsp
	 * 建立日期：2013-4-18
	 * 作者：Leo
	 * 模块：单证使用-销毁审批(审批/浏览)/销毁审批(浏览)
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

<div id="destroyApprovePage" style="width: 100%">
	<form id="destroyApproveForm" name="approveForm" action="#"
		method="post" enctype="multipart/form-data">
		<table id="mainTable" style="width: 100%">
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								缴销申请基本信息
								<%--<s:text name="usage.destroyApplyBaseInfo" />
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
							<td class="inputTitle3col" valign="top">
								<!--销毁单号-->
								缴销单号
								<%--<s:text name="usage.destroyCode" />
							--%></td>
							<td>
								<input type="text" readonly="readonly"
									value="${vcDestroy.destroyCode}" size="22"
									style="background-color: lightgray" />
								<input type="hidden" name="id" value="${vcDestroy.id}" />
							</td>
							<td>
								缴销证明材料
								<%--<s:text name="usage.destroyCertificate" />	
							--%></td>
							<td>
								<a style="color: blue"
									href="${ctx}/common/downloadById.do?id=${vcDestroy.id }&type=destroy"><s:text name="usage.common.see" /></a>
							</td>

							<td>
								<!--申请单状态-->
								<s:text name="usage.destroyStatus" />
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="${vcDestroy.destroyStatus}"
									style="background-color: lightgray" />
							</td>

						</tr>
						<tr></tr>
						<tr>
							<td class="inputTitle3col" valign="top">
								<!--申请机构-->
								<s:text name="usage.destroyApplyOrg" />	
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="${vcDestroy.destroyOrgName}"
									style="background-color: lightgray" />
							</td>

							<td class="inputTitle3col" valign="top">
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

						<tr>
							<td class="inputTitle3col" valign="top">
								<!--修改人-->
								<s:text name="usage.destroyApplyModifyOpr" />
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="${vcDestroy.modifyOprCode}"
									style="background-color: lightgray" />
							</td>

							<td class="inputTitle3col" valign="top">
								<!--修改时间-->
								<s:text name="usage.destroyApplyModifyTime" />
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="<fmt:formatDate value="${vcDestroy.modifyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
									style="background-color: lightgray" />
							</td>
							<td class="inputTitle3col" valign="top">
								<!--销毁类型-->
								缴销类型
								<%--<s:text name="usage.destroyType" />
							--%></td>
							<td>
								<input type="text" readonly="readonly"
									value="${vcDestroy.destroyType}"
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
									value="${vcDestroy.approveOprCode}"
									style="background-color: lightgray" />
							</td>

							<td class="inputTitle3col" valign="top">
								<!--审批机构-->
								<s:text name="usage.approveOrg" />
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="${vcDestroy.approveOrgCode}"
									style="background-color: lightgray" />
							</td>
							<td>
								<!--审批时间-->
								<s:text name="usage.approveTime" />
							</td>
							<td>
								<input type="text" readonly="readonly"
									value="<fmt:formatDate value="${vcDestroy.approveTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
									style="background-color: lightgray" />
							</td>
						</tr>


						<tr></tr>
						<tr>
							<td class="inputTitle3col" valign="top">
								<!--申请原因-->
								<s:text name="usage.destroyApplyReason" />
							</td>
							<td colspan="3" class="inputBox3col_text">
								<textarea readonly="readonly" id="applyReason" rows="3" style="background-color: lightgray"
									cols="80">${vcDestroy.destroyReason }</textarea>
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
									<!--销毁数量-->
									<s:text name="usage.destroyNum" />
								</th>
							</tr>
						</thead>
						<tbody>
							<s:iterator id="vcDestroyDet" value="vcDestroyDet">
								<tr>
									<td align="center">
										<s:property value="#vcDestroyDet.docVerCode" />
									</td>
									<td align="center">
										<s:property value="#vcDestroyDet.docVerName" />
									</td>
									<td align="center">
										<s:property value="#vcDestroyDet.pressBatchNo" />
									</td>
									<td align="center">
										<s:property value="#vcDestroyDet.startNum" />
									</td>
									<td align="center">
										<s:property value="#vcDestroyDet.endNum" />
									</td>
									<td align="center">
										<s:property value="#vcDestroyDet.destroyNum" />
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
						<textarea name="approveOpp" rows="5" id="destroyApproveOpp"
							style="width: 90%"></textarea>
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
				<td align="center" colspan="4">
					<c:if test="${actionType == 'approve'}">
						<input type="button" id="destroyAppPass" class="taButton"
							value="<s:text name="usage.common.agree" />" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="destroyAppRebut" class="taButton"
							value="<s:text name="usage.common.disagree" />" />
							&nbsp;&nbsp;&nbsp;&nbsp;
					</c:if>
					<input type="button" id="cancelBtn" class="taButton" value="<s:text name="usage.common.cancel" />" />

				</td>
			</tr>
		</table>
	</form>
</div>

<script type="text/javascript">
	$(document).ready(function() {
		destroyApprove.initPage();
	});
</script>