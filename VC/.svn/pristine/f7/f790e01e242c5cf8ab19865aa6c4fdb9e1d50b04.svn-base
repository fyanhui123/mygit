<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/subfunc/js/mulLine.js"></script>
<style type="text/css">
	.ui-jqgrid-labels {
		height: 20px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function () {
		mulLine.initRiskPlan();
	});
</script>
</head>
<body>
<div>
<h3>
	多行输入组件
</h3>
</div>
<div>
	<span id="RiskPlan"></span>
</div>
<br/><br/><br/><h3>
		单元格合并
	</h3>
<div class="ui-jqgrid ui-widget ui-widget-content ui-corner-all" style="display:inline-block; clear:both;">
	<table id ='tabledivRiskPlan' border="0" cellpadding="0"  cellspacing="0">
		<tr >
			<td class='mulinetitle'>序号</td>
			<td class='mulinetitle'>性别</td>
			<td class='mulinetitle' colspan="2" >
				<table border="0" cellSpacing="0" cellPadding="0" width="100%">
					<tr >
						<td colspan="2" align='center' class="mergeTd-top">
							机构
						</td>
					</tr>
					<tr class = 'ui-jqgrid-labels'>
						<TD  class="mergeTd-left">总部</TD>
						<TD >分中心</TD>
					</tr>

				</table>
			</td>
			<TD  class='mulinetitle'>投保金额</TD>
			<TD  class='mulinetitle'>密码</TD>
			<TD  class='mulinetitle' >操作</TD>
		</tr>
	</table>
</div>
<div>
	<span id="divRiskPlan"></span>
</div>

</body>
</html>

