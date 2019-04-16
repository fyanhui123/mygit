
<%
	/*
	 * 印刷入库添加/修改页面
	 ***************************************
	 * 程序名：DocumentInStoreEdit.jsp
	 * 建立日期：2013-3-28
	 * 作者：Leo
	 * 模块：印刷入库
	 * 备注：
	 * -----------------------------------------
	 * 修改历史
	 * 序号 日期 修改人 修改原因
	 * 
	 *****************************Z**********
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
		<script type="text/javascript"
			src="${ctx}/pages/store/js/DocumentInStoreApplyEdit.js"></script>
		<script type="text/javascript">
			$(document).ready(function() {
				mulLine.initPageEdit();
			});
		</script>
	</head>
	<body>

		<div id="docInStoreEditPage" style="width: 100%">
			<form id="editForm" name="editForm"
				action="${ctx}/store/saveDocInStore.do" method="post"
				enctype="multipart/form-data">
				<table id="mainTable" style="width: 100%">
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif"
											class="bottom">
										&nbsp;
										<!--入库申请录入-->
										<s:text name="store.inStoreApplyInput" />
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
										<!--印刷入库单号-->
										<s:text name="store.inStoreAppCode" />
									</td>
									<td>
										<input type="text" readonly="readonly"
											value="${vcDocInStore.inStoreAppCode}"
											style="background-color: lightgray" />
										<input type="hidden" name="id" value="${vcDocInStore.id }" />
										<input type="hidden" id="actionType" name="actionType"
											value="${actionType }" />
										<%--add by chy 20131029 增加黑龙江地区标志 --%>
										<input id="hljBuyFlag" name="hljBuyFlag" value="${hljBuyFlag}" type="hidden"/>
									</td>
									<td>
										<!--入库登记表-->
										<s:text name="store.inStoreRegForm" />
									</td>
									<td>
											 <c:if test="${actionType == 'add'}">
											     <input name="file" type="file"  class="required"   />
											     <a  href="#" style="color: red">*</a>
											 </c:if>
										

										   <c:if test="${actionType == 'modify'}">
										       <input name="file" type="file" />
										    	<a style="color: blue"
												href="${ctx}/common/downloadById.do?id=${vcDocInStore.id }&type=docInstore"><s:text name="store.common.see" /></a>
										   </c:if>
									</td>
								</tr>
								<tr></tr>
								<tr>
									<td>
										<!--申请机构-->
										<s:text name="store.applyOrg" />
									</td>
									<td>
										<input type="text" readonly="readonly" class="required"
											value="${vcDocInStore.applyOrgName }"
											style="background-color: lightgray" />
									</td>
									<td>
										<!--申请人-->
										<s:text name="store.applyOpr" />
									</td>
									<td>
										<input type="text" readonly="readonly" class="required"
											value="${vcDocInStore.applyOprName }"
											style="background-color: lightgray" />
									</td>
									<td>
										<!--申请时间-->
										<s:text name="store.inStoreApplyTime" />
									</td>
									<td>
										<input type="text" readonly="readonly" class="required"
											value="<fmt:formatDate value="${vcDocInStore.applyTime }" pattern="yyyy-MM-dd HH:mm:ss"/>"
											style="background-color: lightgray" />

									</td>
								</tr>
								<tr></tr>
								<%--add by chy 20131029 增加黑龙江地税的处理 begin --%>
								<c:if test="${'1'==hljBuyFlag}">
									<tr id="hljBuyTr">
										<td>
											领购日期
										</td>
										<td>
											<input type="text" id="buyDate" name="buyDate" 
												value="<fmt:formatDate value="${vcDocInStore.buyDate}" pattern="yyyy-MM-dd"/>"
												class="Wdate taDatePicker required" onclick="WdatePicker()" />
										</td>
										<td>
											单证开具限额（万）
										</td>
										<td>
											<input type="text" id="moneyLimit" name="moneyLimit" 
												value="${vcDocInStore.moneyLimit}" class="required number"/>
										</td>
										<td>
											电子码
										</td>
										<td>
											<input type="text" id="electronicCode" name="electronicCode" 
												value="${vcDocInStore.electronicCode}" class="required"/>
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
										<textarea id="applyReason" name="applyReason" rows="3"
											class="required" cols="80">${vcDocInStore.applyReason }</textarea>
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
										<!--入库详细信息-->
										<s:text name="store.inStoreDetails" />
										<textarea rows="" cols="" id="jsonStr" name="jsonStr1"
											style="display: none"></textarea>
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
							<!--
					<div style="width: 98%">
						<div id="inStoreInput" ></div>
					</div>
					-->
							<span id="inStoreInput"></span>
							<textarea id="edit_hiddenInStoreDet" style="display: none;"
								rows="4">${jsonStr }</textarea>
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
											<s:text name="store.inStore.approveDetails" />
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
						<td style="padding-left: 30px;" colspan="4" align="center">
							<input type="button" id="saveBut" class="taButton"
								value="<s:text name="common.submit.save" />" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="submitBut" class="taButton" value="<s:text name="store.common.submit" />" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" id="saveBackBtn" class="taButton" value="<s:text name="store.common.cancel" />" />
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>
