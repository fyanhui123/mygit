<% 
/*
 * 领用存报表生成界面
***************************************
 * 程序名：InvoiceReportGenerate.jsp
 * 建立日期：2013-10-30
 * 作者：chy
 * 模块：发票管理
 * 备注：
 * -----------------------------------------
 * 修改历史
 * 序号 日期 修改人 修改原因
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ page import="com.tapi.tcs.vc.invoice.vo.VcInvoiceReportDetHljVo" %>
<%@ include file="/common/taglib.jspf"%>
<%
		String actionType = (String)request.getAttribute("actionType");
		//如果是查询后进来的页面，则把信息放到session中，供保存时使用
		if("query".equals(actionType)){
			List<VcInvoiceReportDetHljVo> lastLeftArr = (List<VcInvoiceReportDetHljVo>)request.getAttribute("lastLeftList");
			List<VcInvoiceReportDetHljVo> lastBuyArr = (List<VcInvoiceReportDetHljVo>)request.getAttribute("lastBuyList");
			List<VcInvoiceReportDetHljVo> nextUseArr = (List<VcInvoiceReportDetHljVo>)request.getAttribute("nextUseList");
			List<VcInvoiceReportDetHljVo> nextLeftArr = (List<VcInvoiceReportDetHljVo>)request.getAttribute("nextLeftList");
			session.setAttribute("lastLeftList",lastLeftArr);
			session.setAttribute("lastBuyList",lastBuyArr);
			session.setAttribute("nextUseList",nextUseArr);
			session.setAttribute("nextLeftList",nextLeftArr);
		}
	%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/invoice/js/InvoiceReportGenerate.js"></script>
</head>
<body>
<div id="invoiceReportGenerate" class="tab-center">
<form id="editForm" name="editForm" action="${ctx}/invoice/queryReportBeforeGererate.do" method="post" enctype="multipart/form-data">
<input name="reportId" value="${reportId}" type="hidden"/>
<input name="actionType" id="actionType" value="${actionType}" type="hidden"/>
<input id="message" value="${message}" type="hidden"/>
<table style="width: 100%">
<tr>
	<td>
	<table id="mainTable" style="width: 100%">
		<tr>
			<td height="33">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td class="f-black" height="20" valign="top">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;领用存报表生成
						</td>
					</tr>
					<tr>
						<td class="pageDevide"></td>
					</tr>
					<tr>
						<td>
							<table width="100%" class="inputTable3col">
								<tr>
									<td width="12%"  class="inputTitle3col" >
										日期起
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input type="text" id="startDate" name="startDate" readonly="readonly"
											value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker" />
									</td>
									<td width="12%"  class="inputTitle3col" >
										日期止
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input type="text" id="endDate" name="endDate"  readonly="readonly"
											value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker"  />
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
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;前期结存
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id=queryReportBtn buttonId="b01" class="taButton" type="button" onclick="submitQuery();">
										查询
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="generateReportBtn" buttonId="b02" class="taButton" type="button" onclick="submitGenerate();" >
										报表生成
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="exportReportBtn" buttonId="b03" class="taButton" type="button" onclick="exportFile();">
										导出Excel
									</button>
									<c:if test="${actionType=='reportDetail'}">
										&nbsp;&nbsp;&nbsp;&nbsp;
										<button id="returnBtn" buttonId="b04" class="taButton" type="button" onclick="returnBack();">
											返回
										</button>
									</c:if>
								</td>
							</tr>
							<tr>
								<td class="pageDevide" colspan="2"></td>
							</tr>
						</table>
		              </td>
					</tr>
					<%--前期结存 --%>
					<tr>
						<td>
						<div class="ui-jqgrid">
							<table border="0" cellSpacing="0" cellPadding="0" width="100%" style="font-size: 13px">
								<tr>
									<th class="mulLine-state-default" width="10%"><b>序号</b></th>
									<th class="mulLine-state-default" width="13%"><b>发票代码</b></th>
									<th class="mulLine-state-default" width="13%"><b>份数</b></th>
									<th class="mulLine-state-default" width="13%"><b>发票号起</b></th>
									<th class="mulLine-state-default" width="13%"><b>发票号止</b></th>
									<th class="mulLine-state-default" width="13%"><b>领购日期</b></th>
									<th class="mulLine-state-default" width="13%"><b>开具金额</b></th>
									
								</tr>
								<s:iterator id="lastLeftVo" value="lastLeftList">
									<tr>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastLeftVo.order"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastLeftVo.invoiceCode"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastLeftVo.totalNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastLeftVo.startNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastLeftVo.endNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:date name="#lastLeftVo.buyDate" format="yyyy/MM/dd"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastLeftVo.amount"/></span></td>
										
									</tr>
								</s:iterator>
							</table>
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<%--上期领购 --%>
					<tr>
						<td class="f-black" height="20" valign="top">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;上期领购
						</td>
					</tr>
					<tr>
						<td class="pageDevide"></td>
					</tr>
					<tr>
						<td>
						<div class="ui-jqgrid">
							<table border="0" cellSpacing="0" cellPadding="0" width="100%" style="font-size: 13px">
								<tr>
									<th class="mulLine-state-default" width="10%"><b>序号</b></th>
									<th class="mulLine-state-default" width="15%"><b>发票代码</b></th>
									<th class="mulLine-state-default" width="15%"><b>份数</b></th>
									<th class="mulLine-state-default" width="15%"><b>发票号起</b></th>
									<th class="mulLine-state-default" width="15%"><b>发票号止</b></th>
									<th class="mulLine-state-default" width="15%"><b>领购日期</b></th>
									<th class="mulLine-state-default" width="15%"><b>开具金额</b></th>
								</tr>
								<s:iterator id="lastBuyVo" value="lastBuyList">
									<tr>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastBuyVo.order"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastBuyVo.invoiceCode"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastBuyVo.totalNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastBuyVo.startNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastBuyVo.endNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:date name="#lastBuyVo.buyDate" format="yyyy/MM/dd"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#lastBuyVo.amount"/></span></td>
									</tr>
								</s:iterator>
							</table>
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<%-- 本期使用 --%>
					<tr>
						<td class="f-black" height="20" valign="top">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;本期使用
						</td>
					</tr>
					<tr>
						<td class="pageDevide"></td>
					</tr>
					<tr>
						<td>
						<div class="ui-jqgrid">
							<table border="0" cellSpacing="0" cellPadding="0" width="100%" style="font-size: 13px">
								<tr>
									<th class="mulLine-state-default" width="5%"><b>序号</b></th>
									<th class="mulLine-state-default" width="5%"><b>发票代码</b></th>
									<th class="mulLine-state-default" width="5%"><b>开具份数</b></th>
									<th class="mulLine-state-default" width="5%"><b>作废份数</b></th>
									<th class="mulLine-state-default" width="10%"><b>发票号起</b></th>
									<th class="mulLine-state-default" width="10%"><b>发票号止</b></th>
									<th class="mulLine-state-default" width="10%"><b>日期起</b></th>
									<th class="mulLine-state-default" width="10%"><b>日期止</b></th>
									<th class="mulLine-state-default" width="30%"><b>机构信息</b></th>
									<th class="mulLine-state-default" width="10%"><b>开具金额</b></th>
								</tr>
								<s:iterator id="nextUseVo" value="nextUseList">
									<tr>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextUseVo.order"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextUseVo.invoiceCode"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextUseVo.printNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextUseVo.cancelNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextUseVo.startNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextUseVo.endNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:date name="#nextUseVo.startDate" format="yyyy/MM/dd"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:date name="#nextUseVo.endDate" format="yyyy/MM/dd"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextUseVo.orgName"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextUseVo.amount"/></span></td>
									</tr>
								</s:iterator>
							</table>
						</div>
						</td>
					</tr>
					<tr>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					<%--本期结存 --%>
					<tr>
						<td class="f-black" height="20" valign="top">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;本期结存
						</td>
					</tr>
					<tr>
						<td class="pageDevide"></td>
					</tr>
					<tr>
						<td>
						<div class="ui-jqgrid">
							<table border="0" cellSpacing="0" cellPadding="0" width="100%" style="font-size: 13px">
								<tr>
									<th class="mulLine-state-default" width="10%"><b>序号</b></th>
									<th class="mulLine-state-default" width="15%"><b>发票代码</b></th>
									<th class="mulLine-state-default" width="15%"><b>份数</b></th>
									<th class="mulLine-state-default" width="15%"><b>发票号起</b></th>
									<th class="mulLine-state-default" width="15%"><b>发票号止</b></th>
									<th class="mulLine-state-default" width="15%"><b>领购日期</b></th>
									<th class="mulLine-state-default" width="15%"><b>开具金额</b></th>
								</tr>
								<s:iterator id="nextLeftVo" value="nextLeftList">
									<tr>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextLeftVo.order"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextLeftVo.invoiceCode"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextLeftVo.totalNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextLeftVo.startNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextLeftVo.endNum"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:date name="#nextLeftVo.buyDate" format="yyyy/MM/dd"/></span></td>
										<td class="mulLineWidgetLeft"><span style='text-align:left;display:block;'>
											<s:property value="#nextLeftVo.amount"/></span></td>
									</tr>
								</s:iterator>
							</table>
						</div>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</form>
</div>
<script type="text/javascript">
	init();
</script>
</body>
</html>
