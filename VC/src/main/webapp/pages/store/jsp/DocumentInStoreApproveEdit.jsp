
<%
	/*
	 * 印刷入库浏览界面
	 ***************************************
	 * 程序名：DocumentInStoreApproveEdit.jsp
	 * 建立日期：2013-3-29
	 * 作者：Leo
	 * 模块：库存管理-印刷入库审批
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

<div id="docInstoreApprovePage" style="width: 100%">
	<table width="100%" style="width: 100%">
		<tr>
			<td>
				<form id="docInStoreAppForm" name="viewForm" action="#"
					method="post" enctype="multipart/form-data">
					<table width="100%" id="mainTable" style="width: 100%">
						<%--基本信息模块 --%>
						<tr onclick="common.hideTR('tra');">
							<td>
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td class="f-black" height="20" valign="top">
											<img src="${ctx}/style/default/images/point4.gif"
												class="bottom">
											&nbsp;
											<!--基本信息-->
											<s:text name="store.common.basicinfo" />
										</td>
									</tr>
									<tr>
										<td class="pageDevide"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr id="tra">
							<td>
								<table width="100%">
									<tr>
										<td>
											<!--印刷入库单号-->
											<s:text name="store.inStoreAppCode" />
										</td>
										<td nowrap="nowrap">
											<input value="${vcDocInStore.inStoreAppCode}"
												name="vcDocInStore.inStoreAppCode" type="text"
												readonly="readonly" size="22" style="background-color: lightgray">
											<input type="hidden" name="id" value="${vcDocInStore.id}" />
										</td>
										<td width="12%">
											<!--申请单状态-->
											<s:text name="store.inStoreStatus" />
										</td>
										<td nowrap="nowrap">
											<input value="${vcDocInStore.inStoreStatus }" type="text"
												readonly="readonly" style="background-color: lightgray">
										</td>
									</tr>
									<tr>
										<td>
											<!--申请机构-->
											<s:text name="store.applyOrg" />
										</td>
										<td>
											<input value="${vcDocInStore.applyOrgName}" type="text"
												readonly="readonly" style="background-color: lightgray" />
										</td>
										<td>
											<!--申请人-->
											<s:text name="store.applyOpr" />
										</td>
										<td>
											<input value="${vcDocInStore.applyOprName}" type="text"
												readonly="readonly" style="background-color: lightgray" />
										</td>
										<td>
											<!--申请时间-->
											<s:text name="store.inStoreApplyTime" />
										</td>
										<td>
											<input value="<fmt:formatDate value="${vcDocInStore.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text"
												readonly="readonly" style="background-color: lightgray" />
										</td>
									</tr>

									<tr>
										<td>
											<!--审批机构-->
											<s:text name="store.approveOrg" />
										</td>
										<td>
											<input value="${vcDocInStore.checkOrgName}" type="text"
												readonly="readonly" style="background-color: lightgray" />
										</td>
										<td>
											<!--审批人-->
											<s:text name="store.approveOpr" />
										</td>
										<td>
											<input value="${vcDocInStore.checkOprName}" type="text"
												readonly="readonly" style="background-color: lightgray" />
										</td>
										<td>
											<!--审批时间-->
											<s:text name="store.approveTime" />
										</td>
										<td>
											<input value="<fmt:formatDate value="${vcDocInStore.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text"
												readonly="readonly"  style="background-color: lightgray" />
										</td>
									</tr>

									<tr>
										<td>
											
											<!--入库登记表-->
										<s:text name="store.inStoreRegForm" />
										</td>
										<td>
											<a style="color: blue"
												href="${ctx}/common/downloadById.do?id=${vcDocInStore.id }&type=docInstore">
												<s:text name="store.common.see" /></a>
										</td>
										<td>
											<!--修改人-->
											<s:text name="store.modifyOpr" />
										</td>
										<td>
											<input value="${vcDocInStore.modifyOprName}" type="text"
												readonly="readonly" style="background-color: lightgray" />
										</td>
										<td>
											<!--修改时间-->
											<s:text name="store.modifyTime" />
										</td>
										<td>
											<input value="<fmt:formatDate value="${vcDocInStore.modifyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>" type="text"
												readonly="readonly" style="background-color: lightgray" />
										</td>
									</tr>
									<%--add by chy 20131029 增加黑龙江地税的处理 begin --%>
									<c:if test="${'1'==hljBuyFlag}">
										<tr id="hljBuyTr">
											<td>
												领购日期
											</td>
											<td>
												<input type="text" value="<fmt:formatDate value="${vcDocInStore.buyDate}" pattern="yyyy-MM-dd"/>"
													readonly="readonly" style="background-color: lightgray"/>
											</td>
											<td>
												单证开具限额（万）
											</td>
											<td>
												<input type="text" value="${vcDocInStore.moneyLimit}"
													readonly="readonly" style="background-color: lightgray"/>
											</td>
											<td>
												电子码
											</td>
											<td>
												<input type="text" value="${vcDocInStore.electronicCode}"
													readonly="readonly" style="background-color: lightgray" />
											</td>
										</tr>
										<tr></tr>
									</c:if>
									<%--add by chy 20131029 增加黑龙江地税的处理 end --%>
									<tr>
										<td class="inputTitle3col" valign="top">
											<!--申请原因-->
											<s:text name="store.inStoreApplyReason" />
										</td>
										<td colspan="3" class="inputBox3col_text">
											<textarea readonly="readonly" rows="3" cols="80" style="background-color: lightgray">${vcDocInStore.applyReason }</textarea>
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
						<tr onclick=common.hideTR('trb');;
>
							<td>
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td class="f-black" height="20" valign="top">
											<img src="${ctx}/style/default/images/point4.gif"
												class="bottom">
											&nbsp;
											<!--详细信息-->
											<s:text name="store.docDetails" />
										</td>
									</tr>
									<tr>
										<td class="pageDevide"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr id="trb">
							<td>
								<table width="100%" border="0">
									<thead>
										<tr>
											<th width="10%">
												<!--单证类型代码-->
												<s:text name="store.docVerCode" />
											</th>
											<th width="10%">
												<!--单证类型名称-->
												<s:text name="store.docVerName" />
											</th>
											<th width="5%">
												<!--印刷批次-->
												<s:text name="store.pressBatchNo" />
											</th>
											<th width="12%">
												<!--起始流水号-->
												<s:text name="store.docStartNum" />
											</th>
											<th width="13%">
												<!--终止流水号-->
												<s:text name="store.docEndNum" />
											</th>
											<th width="10%">
												<!--入库数量-->
												<s:text name="store.docInStoreNum" />
											</th>
											<th width="10%">
												<!--印刷厂-->
												<s:text name="store.printery" />
											</th>
											<th width="10%">
												<!--印刷单价-->
												<s:text name="store.docUnitPrice" />
											</th>
											<th width="10%">
												<!--印刷费用-->
												<s:text name="store.printingFee" />
											</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator id="vcDocInStoreDet" value="vcDocInStoreDet">
											<tr>
												<td align="center">
													<s:property value="#vcDocInStoreDet.docVerCode" />
												</td>
												<td align="center">
													<s:property value="#vcDocInStoreDet.docVerName" />
												</td>
												<td align="center">
													<s:property value="#vcDocInStoreDet.pressBatchNo" />
												</td>
												<td align="center">
													<s:property value="#vcDocInStoreDet.startNum" />
												</td>
												<td align="center">
													<s:property value="#vcDocInStoreDet.endNum" />
												</td>
												<td align="center">
													<s:property value="#vcDocInStoreDet.totalAmount" />
												</td>
												<td align="center">
													<s:property value="#vcDocInStoreDet.printeryCode" />
												</td>
												<td align="center">
													<s:property value="#vcDocInStoreDet.unitPrice" />
												</td>
												<td align="center">
													<s:property value="#vcDocInStoreDet.printingFee" />
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
											<img src="${ctx}/style/default/images/point4.gif"
												class="bottom">
											&nbsp;
											<!--历史审批信息-->
											<s:text name="store.inStore.approveDetails" />
										</td>
									</tr>
									<tr>
										<td class="pageDevide"></td>
									</tr>
								</table>
							</td>
						</tr>
						<tr id="trc">
							<td>
								<table width="100%">
									<thead>
										<tr>
											<th>
												<s:text name="store.approveOrg" />
											</th>
											<th>
												<s:text name="store.approveOpr" />
											</th>
											<th>
												<s:text name="store.approveTime" />
											</th>
											<th>
												<s:text name="store.approveStatus" />
											</th>
											<th>
												<s:text name="store.approveExpl" />
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
													<fmt:formatDate value="${vcApprove_.checkTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
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
						<tr>
							<td colspan="4">
								&nbsp;
							</td>
						</tr>
						<c:if test="${actionType != 'view'}">
							<%--审批意见 --%>
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
						<tr align="center">
							<td align="center">
								<c:if test="${actionType != 'view'}">
									<input type="button" id="inStoreAppPassBtn" class="taButton"
										value="<s:text name="store.common.agree" />" />
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="inStoreAppRebutBtn" class="taButton"
										value="<s:text name="store.common.disagree" />" />
								</c:if>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="inStoreAppBackBtn" class="taButton"
									value="<s:text name="common.submit.back" />" />
							</td>
						</tr>
					</table>
				</form>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
	docInStoreApprove.initPage();
</script>