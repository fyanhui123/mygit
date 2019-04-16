<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id="newPageRecycle" class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<form id="recycleNew" name="recycleNew" action="#" method="post">			
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
					<tr id="trbRecycleNew">
						<td>
							<table style="width: 100%">
								<tr>
									<td>
										<table style="width: 100%">
											<tr>
												<td class="inputTitle3col"><s:text name="store.recycleCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="new_recycleCode" value="${vcRecycle.recycleCode }" name="recycleCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" ><s:text name="store.recycleTime" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="new_recycleTime" value="<fmt:formatDate value='${vcRecycle.recycleTime }' type='date' pattern='yyyy-MM-dd HH:mm:ss' />"  name="recycleTime" type="text" disabled="disabled" />
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col"><s:text name="store.recycleOprCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="new_recycleOprCode" value="${vcRecycle.recycleOprCode }" name="recycleOprCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col"><s:text name="store.recycleOprName" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="new_recycleOprName" value="${vcRecycle.recycleOprName }" name="recycleOprName" type="text" disabled="disabled" />
												</td>
											</tr>										
											<tr>
												<td class="inputTitle3col" ><s:text name="store.recycleOrgCode" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="new_recycleOrgCode" value="${vcRecycle.recycleOrgCode }" name="recycleOrgCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" ><s:text name="store.recycleOrgName" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="new_recycleOrgName" value="${vcRecycle.recycleOrgName }" name="recycleOrgName" type="text" disabled="disabled" />
												</td>
											</tr>										
											<tr>
												<td class="inputTitle3col" ><s:text name="store.recycleReason" /></td>
												<td class="inputBox3col_text" nowrap="nowrap" colspan="3">
													<textarea id="new_recycleReason" name="recycleReason" class="required" style="width: 60%"></textarea>
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
										<div id="SpanDivRecycleNew">
											<table id="DocListRecycleNew"></table>
										</div>
									</td>
								</tr>								
								<tr>
									<td class="inputTitle3col_btn" colspan="4" align="center">
										<input type="button" id="saveButRecycleNew" value='<s:text name="store.common.save" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" id="submitButRecycleNew" value='<s:text name="store.common.submit" />'/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" id="cancelButRecycleNew" value='<s:text name="store.common.cancel" />' />&nbsp;&nbsp;&nbsp;&nbsp;
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
	 recycle.initNewPage();
 }); 
</script>
