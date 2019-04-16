<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/subfunc/js/widgets.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		widgets.initPage();
	});
</script>
</head>
<body>
	<table style="width: 100%;">
		<tr>
			<td>
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td class="f-black" height="28" valign="center">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;组件demo
						</td>
						<td  align="right">
							<input type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " />&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="saveBt" buttonId="b04" class="taButton">
								<s:text name="common.submit.save" />
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
		<tr id="tra">
			<td>
				<table>
					<tr>
						<td width="12%">
							下拉选择组件
						</td>
						<td width="38%">
							<INPUT id="address1" type="text" class="comboBox-select">							
						</td>
						<td width="12%" >
							&nbsp;
						</td>
						<td width="38%" >
							&nbsp;
						</td>
					</tr>
					<tr>
						<td width="12%">
							日期(yyyy-MM-dd)
						</td>
						<td width="38%" >
							<input name="contract.contractDate" value="${contract.contractDate}" type="text" class="Wdate" onclick="WdatePicker()"/>
						</td>
						<td width="12%" >
							日期(yyyy-MM-dd HH:mm:ss)
						</td>
						<td width="38%" >
							<input name="contract.contractDate" value="${contract.contractDate}" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"/>
						</td>
						
					</tr>
					<tr>
						<td width="12%" >
							自动完成(本地数据)
						</td>
						<td width="38%" >
							<input id="deptCode" class="deptCode ui-autocomplete-input" value="${contract.deptCode}" name="contract.deptCode" type="text" />
          					<input id="deptCodeName" class="codeLabel ui-widget-content ui-autocomplete-label" value="${codeLabel}" type="text" readonly="readonly" />
							
						</td>
						<td width="12%" >
							自动完成(ajax数据)
						</td>
						<td width="38%" >
							<input id="InsuredType" class="InsuredType ui-autocomplete-input" value="" name="InsuredType" type="text" />
          					<input id="InsuredTypeName" class="typeLabel ui-widget-content ui-autocomplete-label" value="${typeLabel}" type="text" readonly="readonly" />
						</td>
					</tr>
					<tr>
						<td width="12%" >
							下拉框
						</td>
						<td width="38%" >
							<select class="selectStyle" id="singliDropDownList"></select>
						</td>
						<td width="12%" >
							
						</td>
						<td width="38%" >
							
						</td>
					</tr>
					<tr>
						<td width="12%" >
							自动完成(本地数据)
						</td>
						<td width="38%" >
							<input id="userName" class="userName ui-autocomplete-input" value="" name="userName" type="text" /> 
						</td>
					</tr>
					<tr>
						<td width="12%" >
							自动完成(ajax数据)
						</td>
						<td width="38%" >
							<input id="RiskType" class="userName ui-autocomplete-input" value="" name="userCode" type="text" /> 
						</td>
						<td width="12%" >
							&nbsp;
						</td>
						<td width="38%" >
							&nbsp;
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>		
</body>
</html>

