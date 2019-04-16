<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id="newPageApply" class="tab-center">
	<table style="width: 100%">
		<tr>
			<td>
			<form id="applyNew" name="applyNew" action="#" method="post">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('tra');">
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="28" valign="center">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="store.common.basicinfo" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="tra">
						<td>
							<table style="width: 100%">
								<tr>
									<td width="12%" align="left"><s:text name="store.applyCode" /></td>
									<td width="24%"><input type="text" disabled="disabled" style="width:90%"/> </td>
									<td width="12%" align="left"><s:text name="store.applyTime" /></td>
									<td width="24%">
										<input id="new_applyTime" value="<fmt:formatDate value='${vcApply.applyTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="applyTime" type="text" disabled="disabled"  style="width:90%" />
									</td>	
								</tr>
								<tr >
									<td width="12%" align="left">
										<s:text name="store.applyOrgCode" />
									</td>
									<td width="24%">
										<input id="new_applyOrgCode" value="${vcApply.applyOrgCode }" name="applyOrgCode" type="text"  disabled="disabled" style="width:90%"/>
									</td>
									<td width="12%" align="left" >
										<s:text name="store.applyOrgName" />
									</td>
									<td width="24%">
										<input id="new_applyOrgName" value="${vcApply.applyOrgName }" name="applyOrgName" type="text" disabled="disabled" style="width:90%"/>
									</td>
								</tr>
								<tr>
									<td width="12%" align="left" ><s:text name="store.applyOprCode" /></td>
									<td width="24%">
										<input id="new_applyOprCode" value="${vcApply.applyOprCode }" name="applyOprCode" type="text"  disabled="disabled" style="width:90%"/>
									</td>
									<td width="12%" align="left" ><s:text name="store.applyOprName" /></td>
									<td width="24%">
										<input id="new_applyOprName" value="${vcApply.applyOprName }" name="applyOprName" type="text" disabled="disabled" style="width:90%"/>
									</td>
								</tr>											
								<tr>
									<td width="12%" align="left">
										<s:text name="store.provideOrgCode" />
									</td>
									<td width="24%">
										<input id="new_provideOrgCode" value="${vcApply.provideOrgCode }" name="provideOrgCode" type="text"  disabled="disabled" style="width:90%"/>
									</td>
									<td width="12%" align="left" >
										<s:text name="store.provideOrgName" />
									</td>
									<td width="24%">
										<input id="new_provideOrgName" value="${vcApply.provideOrgName }" name="provideOrgName" type="text" disabled="disabled" style="width:90%"/>
									</td>
								</tr>																
								<tr>
									<td width="12%" align="left"><s:text name="store.applyReason" /></td>
									<td colspan="3" width="24%">
										<textarea id="new_applyReason" value="" name="applyReason" type="text" class="required" style="width: 60%" rows="4">${vcApply.applyReason }</textarea>
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
									<td colspan="3">
										<div id="SpanDivApplyNew">
											<table id="DocListApplyNew"></table>
										</div>
									</td>
									<td>&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td style="padding-left: 30px;"  colspan="4" align="center">
										<input type="button" id="saveButApplyNew" class="taButton" value='<s:text name="store.common.save" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" id="submitButApplyNew" class="taButton" value='<s:text name="store.common.submit" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" id="backButApplyNew" class="taButton" value='<s:text name="store.common.cancel" />' />
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
	apply.initApplyNew();
</script>

