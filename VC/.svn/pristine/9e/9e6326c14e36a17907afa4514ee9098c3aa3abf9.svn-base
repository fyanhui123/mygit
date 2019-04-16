<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id="newPageAllot" class="tab-center">
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
										&nbsp;基本信息
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
									<td width="12%" align="left">调拨单号</td>
									<td width="24%"><input type="text" disabled="disabled" style="width:90%"/> </td>
									<td width="12%" align="left">调拨申请时间</td>
									<td width="24%">
										<input id="new_allotTime" value="<fmt:formatDate value='${vcAllot.allotTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="allotTime" type="text" disabled="disabled"  style="width:90%" />
									</td>	
								</tr>
								<tr >
									<td width="12%" align="left">
										调入机构代码
									</td>
									<td width="24%">
										<input id="new_allotOrgCode" value="${vcAllot.allotOrgCode }" name="allotOrgCode" type="text"  disabled="disabled" style="width:90%"/>
									</td>
									<td width="12%" align="left" >
										调入机构名称
									</td>
									<td width="24%">
										<input id="new_allotOrgName" value="${vcAllot.allotOrgName }" name="applyOrgName" type="text" disabled="disabled" style="width:90%"/>
									</td>
								</tr>
								<tr>
									<td width="12%" align="left" >调拨操作人代码</td>
									<td width="24%">
										<input id="new_allotOprCode" value="${vcAllot.allotOprCode }" name="applyOprCode" type="text"  disabled="disabled" style="width:90%"/>
									</td>
									<td width="12%" align="left" >调拨操作人名称</td>
									<td width="24%">
										<input id="new_allotOprName" value="${vcAllot.allotOprName }" name="" type="text" disabled="disabled" style="width:90%"/>
									</td>
								</tr>
								<tr>
									<td width="12%" class="inputTitle3col">
									调出机构名称
									</td>
									<td width="24%" class="inputBox3col_text" nowrap="nowrap" >
									<input id="allotListDtoOrgCode" name="allotListVocompanyCode" class="ui-autocomplete-input required" type="text" /> 
									<input id="allotInquiryVoOrgCodeName" style="width: 100px;" type="text" readonly="readonly" />
									</td>
								</tr>												
																							
								<tr>
									<td width="12%" align="left">调拨原因</td>
									<td colspan="3" width="24%">
										<textarea id="new_allotReason" value="" name="allotReason" type="text" class="required" style="width: 60%" rows="4">${vcAllot.allotReason }</textarea>
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
										<div id="SpanDivApplyNew">
											<table id="DocListAllotNew"></table>
										</div>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td style="padding-left: 30px;"  colspan="4" align="center">
										<input type="button" id="saveButAllotNew" class="taButton" value='<s:text name="store.common.save" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" id="submitButAllotNew" class="taButton" value='<s:text name="store.common.submit" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
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

