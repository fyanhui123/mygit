<% 
/*
 * 领用存报表查询界面
***************************************
 * 程序名：InvoiceReportInquiry.jsp
 * 建立日期：2013-11-02
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
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/invoice/js/InvoiceReportInquiry.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		invoiceReport.initPage();
	});
</script>
</head>
<body>
<div id="invoiceReportInquiry" class="tab-center">
<form id="editForm" name="editForm" action="#" method="post" enctype="multipart/form-data">
<input name="reportId" id="reportId" type="hidden"/>
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
							&nbsp;领用存报表查询
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
										<input type="text" id="startDate" name="startDate" 
											value="<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker"  onclick="WdatePicker()"/>
									</td>
									<td width="12%"  class="inputTitle3col" >
										日期止
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<input type="text" id="endDate" name="endDate" 
											value="<fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>"
											class="Wdate taDatePicker" onclick="WdatePicker()" />
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
									&nbsp;领用存报表列表
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id=queryReportBtn buttonId="b01" class="taButton" type="button">
										查询
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="reportDetailBtn" buttonId="b02" class="taButton" type="button" >
										报表明细
									</button>
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
							&nbsp;
						</td>
					</tr>
					<tr>
						<td>
							<table class="pagerTable">
								<tr>
									<td>
										<table id="invoiceReportGrid" class="inputTable3col" ></table>
										<div id="invoiceReportGridPager"></div>
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
			</td>
		</tr>
	</table>
	</td>
	</tr>
</table>
</form>
</div>
</body>
</html>
