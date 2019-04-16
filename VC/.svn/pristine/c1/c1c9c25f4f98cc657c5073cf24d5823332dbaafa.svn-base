<% 
/*
 * 订单明细界面
***************************************
 * 程序名：OrderGatherSubView.jsp
 * 建立日期：2013-3-27
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
<div class="tab-center" id="gatherSubViewPage">
	<table style="width: 100%">
		<tr>
			<td>
			<form id="gatherInputForm" name="gatherInputForm" action="#" method="post" enctype="multipart/form-data">
				<table id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('trc');">
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="order.gather.orderSub" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="trc">
						<td>
							<table width="100%">
								<thead>
									<tr>
										<th><s:text name="order.orderLaunch.orderId" /></th>
										<th><s:text name="order.orderLaunch.applyOrg" /></th>
										<th><s:text name="order.orderLaunch.applyer" /></th>
										<th><s:text name="order.orderLaunch.applyTime" /></th>
										<th><s:text name="order.orderLaunch.status" /></th>
									</tr>
								</thead>
								<tbody>
									<s:iterator id="vcOrderLaunchVo" value="orderLaunchList">
										<tr>
											<td align="center"><s:property value="#vcOrderLaunchVo.orderCode"/></td>
											<td align="center"><s:property value="#vcOrderLaunchVo.orgName"/></td>
											<td align="center"><s:property value="#vcOrderLaunchVo.createUserName"/></td>
											<td align="center"><s:date name="#vcOrderLaunchVo.createDate" format="yyyy-MM-dd HH:mm:ss"/> </td>
											<td align="center"><s:property value="#vcOrderLaunchVo.flag"/></td>
										</tr>
									</s:iterator>
								</tbody>
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
							<input type="button"  id="gatherSubViewBackBtn"  class="taButton"  value="<s:text name="common.submit.back" />"/>
						</td>
					</tr>
				</table>
			</form>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
	$("#gatherSubViewBackBtn").click(function(){
		$("#gatherSubViewPage").dialog("close");
	});
</script>