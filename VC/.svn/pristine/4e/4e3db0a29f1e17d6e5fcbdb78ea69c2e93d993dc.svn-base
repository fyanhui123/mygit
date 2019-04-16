<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id="readOnlyPageRecycle" class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
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
					<tr id="trbRecycleReadOnly">
						<td>
							<table class="inputTable3col" style="width: 100%">
								<tr>
									<td>
										<table style="width: 100%">
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.recycleCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="recycleCode" value="${vcRecycle.recycleCode }" name="recycleCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="store.recycleTime" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="recycleTime" value="<fmt:formatDate value='${vcRecycle.recycleTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="recycleTime" type="text" disabled="disabled" />
												</td>
											</tr>											
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.recycleOprCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="recycleOprCode" value="${vcRecycle.recycleOprCode }" name="recycleOprCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="store.recycleOprName" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="recycleOprName" value="${vcRecycle.recycleOprName }" name="recycleOprName" type="text" disabled="disabled" />
												</td>
											</tr>										
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.recycleOrgCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="recycleOrgCode" value="${vcRecycle.recycleOrgCode }" name="recycleOrgCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="store.recycleOrgName" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="recycleOrgName" value="${vcRecycle.recycleOrgName }" name="recycleOrgName" type="text" disabled="disabled" />
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.recycle.confirmOrgCode" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="confirmOrgCode" value="${vcRecycle.confirmOrgCode }" name="confirmOrgCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="store.recycle.confirmOrgName" /> </td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="confirmOrgName" value="${vcRecycle.confirmOrgName }" name="confirmOrgName" type="text" disabled="disabled" />
												</td>
											</tr>	
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.recycle.confirmTime" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="confirmOrgCode" value="<fmt:formatDate value='${vcRecycle.confirmTime}' type='date' pattern='yyyy-MM-dd HH:mm:ss' />" name="confirmOrgCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="left"><s:text name="store.recycleStatus" /></td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<!-- <input id="recycleStatusZh" value="${vcRecycle.recycleStatusZh }" name="recycleStatusZh" type="text" disabled="disabled" />  -->
													<c:forEach  items="${vcRecycle.recycleStatus}"  var="recycleStatus">
													<c:if  test="${recycleStatus == 4}">
													  <input id="recycleStatusZh" value="审批通过" name="recycleStatusZh" type="text" disabled="disabled" />
												   	</c:if>
													
													 <c:if test="${recycleStatus == 0}">
													  <input id="recycleStatusZh" value="删除" name="recycleStatusZh" type="text" disabled="disabled" />
													 </c:if>
													 
													 <c:if test="${recycleStatus == 1}">
													  <input id="recycleStatusZh" value="新建" name="recycleStatusZh" type="text" disabled="disabled" />
													 </c:if>
													 
													 <c:if test="${recycleStatus == 2}">
													  <input id="recycleStatusZh" value="待审批" name="recycleStatusZh" type="text" disabled="disabled" />
													 </c:if>
													 
													 <c:if test="${recycleStatus == 3}">
													  <input id="recycleStatusZh" value="审批退回" name="recycleStatusZh" type="text" disabled="disabled" />
													 </c:if>
													</c:forEach>
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="left"><s:text name="store.recycleReason" /></td>
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
										<div id="SpanDivRecycleReadOnly">
											<textarea id="readOnlyRecycle_hiddenDet" name="readOnlyRecycle_hiddenDet" type="text"  style="display: none;" rows="4">${jsonStr }</textarea>
											<span id="DocListRecycleReadOnly"></span>
										</div>
									</td>
								</tr>	
								<tr>
									<td>
									</td>
								</tr>									
								<tr>
									<td>
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
									<td>
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
									<td class="inputTitle3col_btn" colspan="4" align="center">
										<input type="button" id="backButRecycleReadOnly" value='<s:text name="store.common.back" />' />&nbsp;&nbsp;&nbsp;&nbsp;
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
	 recycle.initReadonlyPage();
 }); 
</script>