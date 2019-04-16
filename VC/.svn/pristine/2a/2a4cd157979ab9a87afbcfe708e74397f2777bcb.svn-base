<% 
/*
 * 订单汇总新增界面
***************************************
 * 程序名：OrderGatherInput.jsp
 * 建立日期：2013-3-25
 * 作者：chy
 * 模块：征订管理-订单汇总
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
<div id="gatherInputPage">
	<table style="width: 100%">
		<tr>
			<td>
			<form id="gatherInputForm" name="gatherInputForm" action="#" method="post" enctype="multipart/form-data">
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
									<td width="10%"  class="inputTitle3col">
										<s:text name="order.orderLaunch.orderId" />
									</td>
									<td width="40%"  class="inputBox3col_text" >
										<INPUT id="orderCodeInput" name="orderCodeInput" type="text" class="ui-tianan-input-text">
									</td>
									<td width="10%"  class="inputTitle3col">
										<s:text name="order.orderLaunch.applyTime" />
									</td>
									<td width="40%"  class="inputBox3col_text"  nowrap="nowrap">
										<input type="text" id = "applyStartDateInput" name="applyStartDateInput" class="Wdate taDatePicker" onclick="WdatePicker()" onchange="orderGatherInput.checkDate()"/> 至
										<input type="text" id= "applyEndDateInput" name="applyEndDateInput" class="Wdate taDatePicker"  onclick="WdatePicker()" onchange="orderGatherInput.checkDate()"/>
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
									&nbsp;<s:text name="order.orderLaunch.orderList" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id="queryOrderGatherInput" buttonId="b08" class="taButton" type="button">
										<s:text name="common.submit.query" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="gatherInputReset" buttonId="b09" class="taButton" type="button">
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
										<table id="gatherInputGrid" class="inputTable3col" ></table>
										<div id="gatherInputGridPager"></div>
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
						<button id="orderGatherBtn" buttonId="b10" class="taButton" type="button">
								<s:text name="order.gather.orderGather" />
						</button>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					
					<%--汇总结果 --%>
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="20" valign="top">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="order.gather.gatherInfo" />
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
							<div id="gatherInfoDiv" style="width: 98%">
								<span id='gatherInfoSpan'></span>
							</div>
							<%--进行汇总的订单号 --%>
							<input type="hidden" id="selectedId" name="selectedId" value=""/>
							<%--
							<table class="pagerTable">
								<tr>
									<td>
										<table id="gatherInfoGrid" class="inputTable3col" ></table>
										<input type="hidden" id="selectedId" name="selectedId" value=""/>
									</td>
								</tr>
							</table>
							 --%>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<tr>
						<td colspan="4" align="center">
							<button id="gatherSaveBtn" buttonId="b11" class="taButton" type="button">
								<s:text name="common.submit.save" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="gatherSubmitBtn" buttonId="b12" class="taButton" type="button">
								<s:text name="common.submit.submit" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="gatherCancelBtn" buttonId="b13" class="taButton" type="button">
								<s:text name="common.submit.cancel" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
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
		orderGatherInput.initPage();
	});
</script>