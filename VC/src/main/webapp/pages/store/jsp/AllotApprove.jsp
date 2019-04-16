<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="editPageProvide" class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<form id="provideEdit" name="provideEdit" action="#" method="post">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr>
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
					<tr id="trbProvideEdit">
						<td>
							<table class="inputTable3col" >
								<tr>
									<td>
										<table style="width: 100%">
											<tr >
												<td width="10%" align="left">
													调拨单号
												</td>
												<td width="24%">
													<input id="applyCode" value="${vcAllot.allotCode }" name="applyCode" type="text"  disabled="disabled" style="width:90%"/>
												</td>
												<td width="10%" align="left">调拨时间</td>
												<td width="24%">
													<input id="applyTime" value="<fmt:formatDate value='${vcAllot.allotTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="applyTime" type="text" disabled="disabled" style="width:90%" />
												</td>
											</tr>											
											<tr>
												<td class="inputTitle3col" align="left">调拨机构名称</td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="vcApply_id" value="${vcAllot.id }"  type="hidden" />
													<input id="applyOrgCode" value="${vcAllot.allotOrgCode }" name="applyOrgCode" type="text" disabled="disabled" style="width:90%" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="store.applyOrgName" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyOrgName" value="${vcAllot.allotOrgName }" name="applyOrgName" type="text" disabled="disabled" style="width:90%" />
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.applyOprCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyOprCode" value="${vcAllot.allotOprCode }" name="applyOprCode" type="text" disabled="disabled" style="width:90%" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="store.applyOprName" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyOprName" value="${vcAllot.allotOprName }" name="applyOprName" type="text" disabled="disabled" style="width:90%"/>
												</td>
											</tr>											
											<tr>
												<td width="10%" align="left">
													<s:text name="store.provideOrgCode" />
												</td>
												<td width="24%">
													<input id="provideOrgCode" value="${vcAllot.provideOrgCode }" name="provideOrgCode" type="text" disabled="disabled" style="width:90%"/>
												</td>
												<td width="10%" align="left" >
													<s:text name="store.provideOrgName" />
												</td>
												<td width="24%">
													<input id="provideOrgName" value="${vcAllot.provideOrgName }" name="provideOrgName" type="text" disabled="disabled" style="width:90%"/>
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.applyReason" /></td>
												<td colspan="3" class="inputBox3col_text" nowrap="nowrap">
													<textarea id="applyReason" value="" name="applyReason"   type="text" style="width: 60%" rows="4">${vcAllot.allotReason }</textarea>
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
										<div id="SpanDivProvide">
											<textarea id="editProvide_hiddenDet" name="editProvide_hiddenDet" type="text"  style="display: none;" rows="4">${jsonStr}</textarea>
											<table id="DocListProvide"></table>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr>
												<td class="f-black" height="28" valign="center">
													<img src="${ctx}/style/default/images/point4.gif" class="bottom">
													&nbsp;<s:text name="store.provideIdea" />
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
												<td width="12%" class="inputTitle3col" align="left"><s:text name="store.provideIdea" /> </td>
												<td>
													<textarea id="backReason" style="width: 60%" rows="4"></textarea>
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="center">
										<input type="button" class="taButton" id="saveButProvideEdit" value='<s:text name="store.common.agree" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="submitButProvideEdit" value='<s:text name="store.common.disagree" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="backButProvideEdit" value='<s:text name="store.common.cancel" />' />
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
	 provide.initEditPage();
 }); 
</script>