<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="editPageCancelApprove" class="tab-center">
	<table style="width: 100%">
		<tr>
			<form id="cancelApproveEdit" name="cancelApproveEdit" action="#" method="post">		
			<td id="mainTableLeftTd_editPageCancelS">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="usage.common.basicinfo" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trbCancelApproveEdit">
						<td>
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td>
										<table style="width: 100%">
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="idVcCancel" value="${cancel.idVcCancel }" name="idVcCancel" type="hidden" />
													<input id="cancelCode" value="${cancel.cancelCode }" name="cancelCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelOrgName" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="cancelOrgName" value="${cancel.cancelOrgName }" name="cancelOrgName" type="text" disabled="disabled" style="width: 200px" />
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelOprName" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="cancelOprName" value="${cancel.cancelOprName }" name="cancelOprName" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelTime" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="cancelTime" value="<fmt:formatDate value='${cancel.cancelTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="cancelTime" type="text" disabled="disabled" />
												</td>												
											</tr>										
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="usage.cancelReason" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap" colspan="3">
													<textarea id="cancelReason" name="cancelReason" disabled="disabled" style="width: 60%" class="required">${cancel.cancelReason }</textarea>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
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
										<div id="SpanDivCancelApproveEdit">
											<textarea id="edit_hiddenDet" name="hiddenDet" type="text"  style="display: none;" rows="4">${jsonStr }</textarea>
											<table id="DocListCancelApproveEdit"></table>
										</div>
									</td>
								</tr>	
								<tr>
									<td colspan="4">
										<table cellpadding="0" cellspacing="0" border="0" style="width: 100%">
											<tr>
												<td class="f-black" height="28" valign="center">
													<img src="${ctx}/style/default/images/point4.gif" class="bottom">
													&nbsp;<s:text name="usage.cancelApproveRemark" />
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
										<table style="width: 100%">
											<tr>
												<td class="inputTitle3col" align="left" width="80px"><s:text name="usage.cancelApproveRemark" /></td>
												<td class="inputBox3col_text" nowrap="nowrap" colspan="7">
													<textarea id="cancelApproveRemark" name="cancelApproveRemark" style="width: 60%"></textarea>
												</td>											
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="center">
										<input type="button" class="taButton" id="agreeButCancelApproveEdit" value='<s:text name="usage.common.agree" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="disagreeButCancelApproveEdit" value='<s:text name="usage.common.disagree" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="cancelButCancelApproveEdit" value='<s:text name="usage.common.cancel" />' />&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>					
				</table>
			</td>
			</form>
		</tr>
	</table>
</div>

<script type="text/javascript">
 $(document).ready(function () {
	 cancelApprove.initEditPage();
 }); 
</script>
