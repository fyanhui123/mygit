<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="viewPageApply" class="tab-center" >
	<table width="100%" style="width: 100%">
		<tr>
			<td>
			<form id="applyView" name="applyView" action="#" method="post">
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
									<td width="12%" align="right"><s:text name="store.applyCode" /></td>
									<td width="24%">
										<input id="view_applyCode" value="${vcApply.applyCode }" name="applyCode" type="text"  disabled="disabled"/> 
									</td>
									<td width="12%" align="right"><s:text name="store.applyTime" /></td>
									<td width="24%">
										<input id="view_applyTime" value="<fmt:formatDate value='${vcApply.applyTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="applyTime" type="text" disabled="disabled"  />
									</td>	
								</tr>
								<tr >
									<td width="12%" align="right">
										<s:text name="store.applyOrgCode" />
									</td>
									<td width="24%">
										<input id="view_applyOrgCode" value="${vcApply.applyOrgCode }" name="applyOrgCode" type="text"  disabled="disabled"/>
									</td>
									<td width="12%" align="right" >
										<s:text name="store.applyOrgName" />
									</td>
									<td width="24%">
										<input id="view_applyOrgName" value="${vcApply.applyOrgName }" name="applyOrgName" type="text" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td width="12%" align="right" ><s:text name="store.applyOprCode" /></td>
									<td width="24%">
										<input id="view_applyOprCode" value="${vcApply.applyOprCode }" name="applyOprCode" type="text"  disabled="disabled" />
									</td>
									<td width="12%" align="right" ><s:text name="store.applyOprName" /></td>
									<td width="24%">
										<input id="view_applyOprName" value="${vcApply.applyOprName }" name="applyOprName" type="text" disabled="disabled"/>
									</td>
								</tr>											
								<tr >
									<td width="12%" align="right">
										<s:text name="store.provideOrgCode" />
									</td>
									<td width="24%">
										<input id="view_provideOrgCode" value="${vcApply.provideOrgCode }" name="provideOrgCode" type="text"  disabled="disabled"/>
									</td>
									<td width="12%" align="right" >
										<s:text name="store.provideOrgName" />
									</td>
									<td width="24%">
										<input id="view_provideOrgName" value="${vcApply.provideOrgName }" name="provideOrgName" type="text" disabled="disabled"/>
									</td>
								</tr>
								<tr>
									<td width="12%" align="right"><s:text name="store.applyReason" /></td>
									<td colspan="3" width="24%">
										<textarea id="view_applyReason" value="" name="applyReason" type="text"  style="width: 60%" rows="4" disabled="disabled">${vcApply.applyReason }</textarea>
										<textarea id="view_hiddenDet" name="hiddenDet" type="text"  style="display: none;" rows="4">${jsonStr }</textarea>
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
									<td colspan="4">
										<div id="SpanDivApplyView">
											<table id="DocListApplyView"></table>
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
									<td style="padding-left: 30px;"  colspan="4" align="center">
										<input type="button" id="backButApplyView" class="taButton" value='<s:text name="store.common.back" />' />
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
	apply.initApplyView();
</script>

