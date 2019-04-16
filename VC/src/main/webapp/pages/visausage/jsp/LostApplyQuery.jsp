
<%
	/*
	 * 遗失申请查询
	 ***************************************
	 * 程序名：LostApplyQuery.jsp
	 * 建立日期：2013-4-18
	 * 作者：Leo
	 * 模块：单证使用-遗失申请
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
			src="${ctx}/pages/visausage/js/LostApplyQuery.js"></script>
		<script type="text/javascript"
			src="${ctx}/pages/visausage/js/LostApplyEdit.js"></script>
		<script type="text/javascript"
			src="${ctx}/pages/visausage/js/LostApplyView.js"></script>


		<script type="text/javascript">
			$(document).ready(function () {
			 	common.initHtml("LostApplyQuery");
				lostApply.initPage();
				 lostApply.alertMsg("${actionType}","${resultMsg}");
			});
		</script>
	</head>
	<body>
		<div class="tab-center" id="lostApplyListPage">
			<table style="width: 100%">
			<form>
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
												<!--遗失单号-->
												<s:text name="usage.lostCode" />
											</td>
											<td colspan="3" class="inputBox3col_text">
												<input type="text" id="lostCode" name="lostCode" />
											</td>
											<td width="12%" class="inputTitle3col">
												<!--申请单状态-->
												<s:text name="usage.lostStatus" />
											</td>
											<td width="38%" class="inputBox3col_text">
												<select id="lostStatus" name="lostStatus"
													class="ui-tianan-input-text">
												</select>
											</td>
										</tr>
										<tr>
											<td width="12%" class="inputTitle3col">
												<!--申请日期-->
												<s:text name="usage.lostApplyTime" />
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
												<!--遗失申请列表-->
												<s:text name="usage.lostApplyList" />
											</td>
											<td align="right" valign="top" nowrap="nowrap">
												<input id="queryLostApply" type="button" class="taButton"
													value="<s:text name="common.submit.query" />" />
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="reset" class="taButton" id="restBtn" onclick="common.clearGridData('lostApplyGatherGrid');"
													value="<s:text name="common.submit.rest" />"/>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<button id="addLostApply" type="button" buttonId="b04" class="taButton">
													<s:text name="common.submit.add" />
												</button>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<button id="modifyLostApply" type="button"  buttonId="b06" class="taButton">
													<s:text name="common.submit.alter" />
												</button>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<button id="submitLostApply" type="button"  buttonId="b08" class="taButton">
													<s:text name="common.submit.submit" />
												</button>
												&nbsp;&nbsp;&nbsp;&nbsp;
												<button id="deleteLostApply" type="button"  buttonId="b07" class="taButton">
													<s:text name="common.submit.delete" />
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
												<table id="lostApplyGatherGrid" class="inputTable3col"></table>
												<div id="lostApplyGatherGridPager"></div>
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
				</form>
			</table>
		</div>
		<%@include file="LostApplyView.jsp"%>
	</body>
</html>