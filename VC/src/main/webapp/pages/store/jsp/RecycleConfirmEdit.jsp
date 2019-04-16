<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="editPageRecycleConfirm" class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<form id="recycleConfirmEdit" name="recycleConfirmEdit" action="#" method="post">	
				<table width="100%" id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('trbRecycleConfirmEdit','hideImg1')">
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="store.common.basicinfo" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trbRecycleConfirmEdit">
						<td>
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td>
										<table style="width: 100%">
											<tr>
												<td class="inputTitle3col" align="right"><s:text name="store.recycleOprCode" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="vcRecycle_idVcRecycle" value="${vcRecycle.idVcRecycle }"  type="hidden" />
													<input id="recycleOprCode" value="${vcRecycle.recycleOprCode }" name="recycleOprCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="right"><s:text name="store.recycleOprName" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="recycleOprName" value="${vcRecycle.recycleOprName }" name="recycleOprName" type="text" disabled="disabled" />
												</td>
											</tr>										
											<tr>
												<td class="inputTitle3col" align="right"><s:text name="store.recycleOrgCode" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="recycleOrgCode" value="${vcRecycle.recycleOrgCode }" name="recycleOrgCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="right"><s:text name="store.recycleOrgName" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="recycleOrgName" value="${vcRecycle.recycleOrgName }" name="recycleOrgName" type="text" disabled="disabled" />
												</td>
											</tr>											
											<tr>
												<td class="inputTitle3col" align="right"><s:text name="store.recycleReason" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap" colspan="3">
													<textarea id="recycleReason" name="recycleReason" style="width: 60%" disabled="disabled">${vcRecycle.recycleReason }</textarea>
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
													&nbsp;<s:text name="store.common.detailinfo" />
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
										<div id="SpanDivRecycleConfirm">
											<textarea id="editRecycleConfirm_hiddenDet" name="editRecycleConfirm_hiddenDet" type="text"  style="display: none;" rows="4">${jsonStr }</textarea>
											<table id="DocListRecycleConfirm"></table>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table cellpadding="0" cellspacing="0" border="0" style="width: 100%">
											<tr>
												<td class="f-black" height="28" valign="center">
													<img src="${ctx}/style/default/images/point4.gif" class="bottom">
													&nbsp;<s:text name="store.recycle.approveIdea" />
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
										<table>
											<tr>
												<td class="inputTitle3col" align="right"><s:text name="store.recycle.approveIdea" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<textarea id="disagreeReason" name="disagreeReason" style="width: 500px" rows="5"></textarea>
												</td>											
											</tr>
										</table>
									</td>
								</tr>															
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="center">
										<input type="button" class="taButton" id="agreeButRecycleConfirmEdit" value='<s:text name="store.common.agree" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="disagreeButRecycleConfirmEdit" value='<s:text name="store.common.disagree" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="cancelButRecycleConfirmEdit" value='<s:text name="store.common.cancel" />' />&nbsp;&nbsp;&nbsp;&nbsp;
									</td>
								</tr>
							</table>
						</td>
					</tr>					
				</table>
				</form>
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">
 $(document).ready(function () {
	 recycleConfirm.initEditPage();
 }); 
</script>