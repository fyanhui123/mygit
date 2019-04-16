<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="readOnlyPageCancelApprove" class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('trbCancelApproveReadonly','hideImg1')">
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="usage.common.basicinfo" />
									</td>
								</tr>
								<tr>
									<td height="13" background="${ctx}/style/default/images/devide-main.gif"></td>
									<td height="13" width="13" background="${ctx}/style/default/images/devide-r.gif"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trbCancelApproveReadonly">
						<td>
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td>
										<table style="width: 100%">
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelCode" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="readOnly_idVcCancel" value="${cancel.idVcCancel }" name="idVcCancel" type="hidden" />
													<input id="readOnly_cancelCode" value="${cancel.cancelCode }" name="cancelCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelOrgName" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="readOnly_cancelOrgName" value="${cancel.cancelOrgName }" name="cancelOrgName" type="text" disabled="disabled" style="width: 200px" />
												</td>
											</tr>	
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelOprName" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="readOnly_cancelOprName" value="${cancel.cancelOprName }" name="cancelOprName" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelTime" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="readOnly_cancelTime" value="<fmt:formatDate value='${cancel.cancelTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="cancelTime" type="text" disabled="disabled" />
												</td>												
											</tr>																					
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelReason" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap" colspan="3">
													<textarea id="readOnly_cancelReason" name="cancelReason" disabled="disabled" style="width: 100%" class="">${cancel.cancelReason }</textarea>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										<table cellpadding="0" cellspacing="0" border="0" style="width: 100%">
											<tr>
												<td class="f-black" height="28" valign="center">
													<img src="${ctx}/style/default/images/point4.gif" class="bottom">
													&nbsp;<s:text name="usage.common.detailinfo" />
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
										<hr/>
										<div id="SpanDivCancelSReadonly">
											<textarea id="readOnly_hiddenDet" name="hiddenDet" type="text"  style="display: none;" rows="4">${jsonStr}</textarea>										
											<table id="DocListCancelSReadonly"></table>
										</div>
									</td>
								</tr>	
								
								<tr>
									<td colspan="4">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr>
												<td height="20" valign="top">
													<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="store.recycle.approveHistory" />
												</td>
											</tr>
											<tr>
												<td class="pageDevide"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
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
												<s:iterator id="vcApprove" value="approveList">
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
									<td class="inputTitle3col_btn" colspan="4" align="center">
										<input type="button" id="cancelButCancelSReadoly" value='<s:text name="usage.common.back" />' />&nbsp;&nbsp;&nbsp;&nbsp;
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

<script type="text/javascript">
 $(document).ready(function () {
	 cancelApprove.initReadonlyPage();
 }); 
</script>
