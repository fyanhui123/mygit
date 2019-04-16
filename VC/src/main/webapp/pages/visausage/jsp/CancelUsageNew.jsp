<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="newPageCancelUsage" class="tab-center">
	<table style="width: 100%">
		<tr>
			<form id="cancelUsageNew" name="cancelUsageNew" action="#" method="post">			
			<td id="mainTableLeftTd">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('trbCancelUsageEdit','hideImg1')">
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
					<tr id="trbCancelUsageEdit">
						<td>
							<table class="inputTable3col" style="width: 100%" >
								<tr>
									<td>
										<table style="width: 100%">
											<tr>
												<td class="inputTitle3col" width="15%"><s:text name="usage.cancelCode" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap" width="38%">
													<input id="cancelCode" value="${cancel.cancelCode }" name="cancelCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" width="15%"><s:text name="usage.cancelOrgName" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap" width="38%">
													<input id="cancelOrgName" value="${cancel.cancelOrgName }" name="cancelOrgName" type="text" disabled="disabled" style="width: 200px" />
												</td>
											</tr>	
											<tr>
												<td class="inputTitle3col" width="15%"><s:text name="usage.cancelOprName" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap" width="38%">
													<input id="cancelOprName" value="${cancel.cancelOprName }" name="cancelOprName" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" width="15%"><s:text name="usage.cancelTime" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap" width="38%">
													<input id="new_cancelTime" value="<fmt:formatDate value='${cancel.cancelTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="cancelTime" type="text" disabled="disabled" />
												</td>												
											</tr>
											<tr>
												<td class="inputTitle3col"><s:text name="usage.cancelReason" /></td>
												<td class="inputBox3col_text" nowrap="nowrap" colspan="3">
													<textarea id="new_cancelReason" name="new_cancelReason" style="width: 60%" class="required">${cancel.cancelReason }</textarea>
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
										<div id="SpanDivCancelUsageNew">
											<table id="DocListCancelUsageNew"></table>
										</div>
									</td>
								</tr>								
								<tr>
									<td class="inputTitle3col_btn" colspan="4"  align="center">
										<input type="button" class="taButton" id="saveButCancelUsageNew" value='<s:text name="usage.common.save" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="submitButCancelUsageNew" value='<s:text name="usage.common.submit" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="cancelButCancelUsageNew" value='<s:text name="usage.common.cancel" />' />&nbsp;&nbsp;&nbsp;&nbsp;
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
	 cancelUsage.initNewPage();
 }); 
</script>
