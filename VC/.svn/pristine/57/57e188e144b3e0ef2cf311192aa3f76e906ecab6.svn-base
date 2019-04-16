
<%
	/*
	 * 使用人单证发放查询页面
	 ***************************************
	 * 程序名：ServantDocumentIssuedQuery.jsp
	 * 建立日期：2013-4-7
	 * 作者：Leo
	 * 模块：库存管理-使用人单证发放
	 * 备注：
	 * -----------------------------------------
	 * 修改历史
	 * 序号 日期 修改人 修改原因
	 * 
	 ***************************************
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
		<script type="text/javascript"
			src="${ctx}/pages/store/js/ServantDocument.js"></script>
		<script type="text/javascript"
			src="${ctx}/pages/store/js/ServantDocumentIssuedAdd.js"></script>
		<script type="text/javascript"
			src="${ctx}/pages/store/js/ValidStorage.js"></script>
		<script type="text/javascript">
	$(document).ready(function () {
		servantDoc.initPage();
	});
</script>
	</head>
	<body>
		<div class="tab-center" id="docIssuedListPage">
			<table style="width: 100%">
				<tr>
					<td>
						<form>
							<table id="mainTable" style="width: 100%">
								<tr>
									<td height="33">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr>
												<td class="f-black" height="20" valign="top">
													<img src="${ctx}/style/default/images/point4.gif"
														class="bottom">
													&nbsp;
													<s:text name="common.form.entryQuertyCondition" />
												</td>
											</tr>
											<tr>
												<td height="13"
													background="${ctx}/style/default/images/devide-main.gif"></td>
												<td height="13" width="13"
													background="${ctx}/style/default/images/devide-r.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="trb">
									<td>
										<table width="100%" class="inputTable3col">
											<tr>
												<td width="12%" class="inputTitle3col">
													<!--单证类型-->
													<s:text name="store.docVer" />
												</td>
												<td class="inputBox3col_text">
													<input id="docVerCode" type="text"
														class="ui-autocomplete-input" />
													<input id="docVerCodeName" type="text"
														class="ui-widget-content ui-autocomplete-label" />

												</td>
												<td width="12%" class="inputTitle3col">
													<!--使用人-->
													<s:text name="store.taker" />
												</td>
												<td width="38%" class="inputBox3col_text">
													<input id="takerCode" type="text"
														class="ui-autocomplete-input" />
													<input id="takerCodeName" type="text"
														class="ui-widget-content ui-autocomplete-label" />
												</td>
											</tr>
											<tr>
												<td width="12%" class="inputTitle3col">
													<!--使用人所属机构-->
													<s:text name="store.takerOrg" />
												</td>
												<td width="38%" class="inputBox3col_text">
													<input id="acceptOrgCode" type="text"
														class="ui-autocomplete-input" />
													<input id="acceptOrgName" type="text"
														class="ui-widget-content ui-autocomplete-label" />
												</td>
												<td width="12%" class="inputTitle3col">
													<!--发放时间-->
													<s:text name="store.provideTime" />
												</td>
												<td colspan="3" class="inputBox3col_text">
													<input type="text" id="provideStartDate"
														name="provideStartDate"
														value="<fmt:formatDate value="${provideStartDate }" pattern="yyyy-MM-dd"/>"
														class="Wdate taDatePicker" onclick="WdatePicker()" />
													<s:text name="store.time.to" />
													<input type="text" id="provideEndDate"
														name="provideEndDate"
														value="<fmt:formatDate value="${provideEndDate }" pattern="yyyy-MM-dd"/>"
														class="Wdate taDatePicker" onclick="WdatePicker()" />
												</td>
											</tr>
											<tr>
												<td colspan="4">
													&nbsp;
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="33">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr>
												<td height="28" valign="top">
													<img src="${ctx}/style/default/images/point4.gif"
														class="bottom">
													&nbsp;
													<!--使用人单证发放列表-->
													<s:text name="store.docProvideList" />
												</td>
												<td align="right" valign="top" nowrap="nowrap">
													<input id="queryDocIssued" type="button" class="taButton"
														value="<s:text name="common.submit.query" />" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="reset" id="restBtn" class="taButton"
														value="<s:text name="common.submit.rest" /> "
														onclick="common.clearGridData('serDocIssuedGatherGrid');" />
													<input type="hidden" id="actionType" name="actionType"
														value="${actionType }" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<button id="addDocIssued" buttonId="b04" class="taButton"
														type="button">
														<s:text name="common.submit.send" />
													</button>
												</td>
											</tr>
											<tr>
												<td class="pageDevide" colspan="2"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="trc">
									<td>
										<table class="pagerTable">
											<tr>
												<td>
													<table id="serDocIssuedGatherGrid" class="inputTable3col"></table>
													<div id="serDocIssuedGatherGridPager"></div>
													<input id="id" name="id" value="" type="hidden">
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td>
										&nbsp;
									</td>
								</tr>
							</table>
						</form>
					</td>
				</tr>
			</table>
		</div>
		<jsp:include page="ServantDocumentIssuedAdd.jsp"></jsp:include>
		<%@ include file="ValidStorageQuery.jsp"%>
	</body>
</html>