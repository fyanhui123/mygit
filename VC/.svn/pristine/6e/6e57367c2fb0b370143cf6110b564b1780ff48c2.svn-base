<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="editPageCancelUsage" class="tab-center">
	<table style="width: 100%">
		<tr>
			<form id="cancelUsageEdit" name="cancelUsageEdit" action="#" method="post">		
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
							<table class="inputTable3col" >
								<tr>
									<td>
										<table>
											<tr>
												<td class="inputTitle3col" width="15%"><s:text name="usage.cancelCode" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap" width="38%">
													<input id="idVcCancel" value="${cancel.idVcCancel }" name="idVcCancel" type="hidden" />
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
													<input id="edit_cancelTime" value="<fmt:formatDate value='${cancel.cancelTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="cancelTime" type="text" disabled="disabled" />
												</td>												
											</tr>																				
											<tr>
												<td class="inputTitle3col" width="15%"><s:text name="usage.cancelReason" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap" colspan="3">
													<textarea id="edit_cancelReason" name="cancelReason" style="width: 60%" class="required">${cancel.cancelReason }</textarea>
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
										<div id="SpanDivCancelUsageEdit">
											<textarea id="edit_hiddenDet" name="hiddenDet" type="text"  style="display: none;" rows="4">${jsonStr }</textarea>
											<table id="DocListCancelUsageEdit"></table>
										</div>
									</td>
								</tr>	
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="center">
										<input type="button" class="taButton" id="saveButCancelUsageEdit" value="暂 存"/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="submitButCancelUsageEdit" value="提 交"/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="cancelButCancelUsageEdit" value="取 消" />&nbsp;&nbsp;&nbsp;&nbsp;
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
	 cancelUsage.initEditPage();
 }); 
</script>
