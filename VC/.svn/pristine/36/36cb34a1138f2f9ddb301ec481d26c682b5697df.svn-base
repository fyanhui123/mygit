<% 
/*
 * 单证库存统计查询
***************************************
 * 程序名：DocNumAccount.jsp
 * 建立日期：2015-2-7
 * 作者：zhxiao
 * 模块：外围系统对账
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

<script type="text/javascript" src="${ctx}/pages/account/js/DocNumAccount.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		common.initHtml("DocNumAccount");
		docNumAccount.initPage();
	});
</script>
</head>
<body>
<div class="tab-center" id="accountPage">
	<table style="width: 100%">
		<tr>
			<td>
			<form id="accountForm" name="accountForm" action="#" method="post" enctype="multipart/form-data">
				<table id="mainTable" style="width: 100%">
					<tr>
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;外围银保通手工对账界面
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
									<td width="12%" >
										对账日期
									</td>
									<td width="38%" >
										<input type="text" id = "accountDate" name="accountDate" class="Wdate taDatePicker" onclick="WdatePicker()"/> 
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
						<td colspan="4" align="center">
							<button id="importFile" buttonId="b11" class="taButton" type="button">
								文件导入
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="accountFile" buttonId="b12" class="taButton" type="button">
								人工对账
							</button>
						</td>
					</tr>
				</table>
				</form>
			</td>
		</tr>
	</table>
</div>
</body>
</html>