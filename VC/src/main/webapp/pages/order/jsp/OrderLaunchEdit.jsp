<% 
/*
 * 订单申请新增/修改界面
***************************************
 * 程序名：OrderLaunchEdit.jsp
 * 建立日期：2013-3-18
 * 作者：chy
 * 模块：征订管理-订单申请
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
<div id="orderEditPage">
		<form id="editForm" name="editForm" action="#" method="post" enctype="multipart/form-data">
				<table id="mainTable" style="width: 100%">
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="order.orderLaunch.input" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
				<input type="hidden" id="editId" name="editId" value="" />
				<input type="hidden" id="editType" name="editType" value="" />
				<div id="orderInputDiv"  style="width: 98%">
					<span id='orderInput'></span>
				</div>
				<table style="width: 100%">
					<tr>
						<td align="center" colspan="4">
							<button id="saveBut" buttonId="b08" class="taButton" type="button">
								<s:text name="common.submit.save" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="orderSubmitBtn" buttonId="b09" class="taButton" type="button">
								<s:text name="common.submit.submit" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="orderLuanchCancelBtn" buttonId="b10" class="taButton" type="button">
								<s:text name="common.submit.cancel" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
				</table>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function () {
		orderLaunchEdit.initOrderInput();
	});
</script>

