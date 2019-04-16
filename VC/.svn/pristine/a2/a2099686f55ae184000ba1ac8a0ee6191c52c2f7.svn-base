<% 
/*
 * 采购单新增界面
***************************************
 * 程序名：PurchaseGenerateInput.jsp
 * 建立日期：2013-4-2
 * 作者：chy
 * 模块：征订管理-生成采购单
 * 备注：
 * -----------------------------------------
 * 修改历史
 * 序号 日期 修改人 修改原因
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id="purchaseGenInputPage">
	<table style="width: 100%">
		<tr>
			<td>
			<form id="purchaseGenInputForm" name="purchaseGenInputForm" action="#" method="post" enctype="multipart/form-data">
				<table id="mainTable" style="width: 100%">
					<%--查询条件 --%>
					<tr>
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="common.form.entryQuertyCondition" />
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
							<table class="inputTable3col">
								<tr>
									<td width="12%"  class="inputTitle3col">
										<s:text name="order.orderLaunch.applyTime" />
									</td>
									<td colspan="3" class="inputBox3col_text" >
										<input type="text" id = "applyStartDateInput" name="applyStartDateInput" class="Wdate taDatePicker" onclick="WdatePicker()" onchange="purchaseGenInput.checkDate()"/> 至
										<input type="text" id= "applyEndDateInput" name="applyEndDateInput" class="Wdate taDatePicker"  onclick="WdatePicker()" onchange="purchaseGenInput.checkDate()"/>
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
					<%--查询列表 --%>
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="common.submit.result" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id="queryPurGenInput" buttonId="b04" class="taButton" type="button">
										<s:text name="common.submit.query" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="resetInput" buttonId="b05" class="taButton" type="button">
										<s:text name="common.submit.rest" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
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
							<table class="pagerTable">
								<tr>
									<td>
										<table id="purchaseInputGrid" class="inputTable3col" ></table>
										<div id="purchaseInputGridPager"></div>
										<input id="inputId" name="inputId" value="" type="hidden">
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
						<td>
							<button id="genPurchaseBtn" buttonId="b06" class="taButton" type="button">
								<s:text name="order.purchase.genPurchase" />
							</button>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					
					<%--生成的采购单列表 --%>
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="20" valign="top">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="order.purchase.purchaseInfo" />
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
							<table class="pagerTable" width="100%">
								<tr>
									<td>
										<table id="genListGrid" class="inputTable3col" ></table>
										<input type="hidden" id="selectedId" name="selectedId" value=""/>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<button id="cancelBtn" buttonId="b07" class="taButton" type="button">
								<s:text name="common.submit.back" />
							</button>
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
		purchaseGenInput.initPage();
	});
</script>