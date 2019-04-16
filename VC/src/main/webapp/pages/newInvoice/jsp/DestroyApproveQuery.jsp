<%
	/*
	 * 销毁审批查询
	 ***************************************
	 * 程序名：DestroyApproveQuery.jsp
	 * 建立日期：2013-4-18
	 * 作者：Leo
	 * 模块：单证使用-销毁审批 
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
			src="${ctx}/pages/newInvoice/js/DestroyApproveQuery.js"></script>
		<script type="text/javascript"
			src="${ctx}/pages/newInvoice/js/DestroyApprove.js"></script>

		<script type="text/javascript">
			$(document).ready(function () {
			 	common.initHtml("DestroyApproveQuery");
				destroyApproveQuery.initPage();
			});
		</script>
	</head>
	<body>
		<div class="tab-center" id="destroyListPage">
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
											<td class="pageDevide"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="trb">
								<td>
									<table width="100%" class="inputTable3col">
										<tr>
											<td width="12%" class="inputTitle3col">
												<!--销毁单号-->
												<s:text name="usage.destroyCode" />
											</td>
											<td colspan="3" class="inputBox3col_text">
												<input type="text" id="destroyCode" name="destroyCode" />
											</td>
											<td width="12%" class="inputTitle3col">
												<!--申请单状态-->
												<s:text name="usage.destroyStatus" />
											</td>
											<td width="38%" class="inputBox3col_text">
												<select id="destroyStatus" name="destroyStatus"
													class="selectStyle" style="width: 155px">
												</select>
											</td>
										</tr>
										<tr>
											<td width="12%" class="inputTitle3col">
												<!--申请日期-->
												<s:text name="usage.destroyTime" />
											</td>
											<td colspan="3" class="inputBox3col_text">
												<input type="text" id="applyStartDate" name="applyStartDate"
													value="<fmt:formatDate value="${applyStartDate }" pattern="yyyy-MM-dd"/>"
													class="Wdate taDatePicker" onclick="WdatePicker()" />
												<s:text name="usage.time.to" />
												<input type="text" id="applyEndDate" name="applyEndDate"
													value="<fmt:formatDate value="${applyEndDate }" pattern="yyyy-MM-dd"/>"
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
											<td height="28" valign="center">
												<img src="${ctx}/style/default/images/point4.gif"
													class="bottom">
												&nbsp;
												<!--销毁申请列表-->
												<s:text name="usage.destroyList" />
											</td>
											<td align="right" valign="top" nowrap="nowrap">
												<input id="queryDestroy" type="button" class="taButton"
													value="<s:text name="common.submit.query" />" />
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="reset" class="taButton" id="restBtn"
													value="<s:text name="common.submit.rest" /> " onclick="common.clearGridData('destroyGatherGrid');"/>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<button id="approveDestroy" buttonId="b04" class="taButton" type="button" >
													<!--审批-->
													<s:text name="usage.approve" />
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
												<table id="destroyGatherGrid" class="inputTable3col"></table>
												<div id="destroyGatherGridPager"></div>
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
		<jsp:include page="DestroyApprove.jsp"></jsp:include>
	</body>
</html>