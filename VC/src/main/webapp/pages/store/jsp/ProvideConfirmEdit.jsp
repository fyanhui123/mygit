<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id="editPageProvideConfirm" class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('trbProvideConfirmEdit','hideImg1')">
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
					<tr id="trbProvideConfirmEdit">
						<td>
							<table class="inputTable3col" >
								<tr>
									<td>
										<table>
											<tr >
												<td width="12%" align="left">
													<s:text name="store.applyCode" />
												</td>
												<td width="24%">
													<input id="applyCode" value="${vcApply.applyCode }" name="applyCode" type="text"  disabled="disabled" style="width: 90%"/>
												</td>
												<td width="12%" align="left"><s:text name="store.applyTime" /></td>
												<td width="24%">
													<input id="applyTime" value="<fmt:formatDate value='${vcApply.applyTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="applyTime" type="text" disabled="disabled"  style="width: 90%" />
												</td>
											</tr>											
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.applyOrgCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="vcApply_id" value="${vcApply.id }"  type="hidden" />
													<input id="applyOrgCode" value="${vcApply.applyOrgCode }" name="applyOrgCode" type="text" disabled="disabled"  style="width: 90%" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="store.applyOrgName" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyOrgName" value="${vcApply.applyOrgName }" name="applyOrgName" type="text" disabled="disabled"  style="width: 90%" />
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.applyOprCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyOprCode" value="${vcApply.applyOprCode }" name="applyOprCode" type="text" disabled="disabled"  style="width: 90%" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="store.applyOprName" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyOprName" value="${vcApply.applyOprName }" name="applyOprName" type="text" disabled="disabled"  style="width: 90%"/>
												</td>
											</tr>											
											<tr>
												<td width="12%" align="left">
													<s:text name="store.provideOrgCode" />
												</td>
												<td width="24%">
													<input id="provideOrgCode" value="${vcApply.provideOrgCode }" name="provideOrgCode" type="text" disabled="disabled"  style="width: 90%"/>
												</td>
												<td width="12%" align="left" >
													<s:text name="store.provideOrgName" />
												</td>
												<td width="24%">
													<input id="provideOrgName" value="${vcApply.provideOrgName }" name="provideOrgName" type="text" disabled="disabled"  style="width: 90%"/>
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.applyReason" /></td>
												<td colspan="3" class="inputBox3col_text" nowrap="nowrap">
													<textarea id="applyReason" value="" name="applyReason" type="text" disabled="disabled" style="width: 60%" rows="4">${vcApply.applyReason }</textarea>
												</td>
											</tr>	
										</table>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
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
										<div id="SpanDivProvideConfirm">
											<textarea id="editProvideConfirm_hiddenDet" name="editProvideConfirm_hiddenDet" type="text"  style="display: none;" rows="4">${json}</textarea>
											<table id="DocListProvideConfirm"></table>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr>
												<td class="f-black" height="28" valign="center">
													<img src="${ctx}/style/default/images/point4.gif" class="bottom">
													&nbsp;<s:text name="store.confirmIdea" />
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
											<tr align="left">
												<td width="12%" align="left"><s:text name="store.confirmIdea" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<textarea id="backReason" style="width: 60%" rows="4" ></textarea>
												</td>
											</tr>
										</table>
									</td>
								</tr>								
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="center">
										<input type="button" id="submitButProvideConfirmEdit" value='<s:text name="store.common.agree" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" id="backButProvideConfirmEdit" value='<s:text name="store.common.disagree" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" id="cancelButProvideConfirmEdit" value='<s:text name="store.common.cancel" />' />
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
	 provideConfirm.initEditPage();
 }); 
</script>
