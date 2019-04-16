<%
/***
      // modify by ljin   修改日期  2013-5-23 14.05
     //  险类代码校验： 不能输入中文、文本必填项
***/
 %>

<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="editPageInsuType" style="width: 100%">
	<table width="100%" style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
			<form id="insuTypeForm" action="#" method="post">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr>
						<td height="40px">
							&nbsp;
						</td>
					</tr>
					<tr onclick="common.hideTR('traInsuType','hideImg1');">
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="vcdoc.insuType.addTitle" />
									</td>
								</tr>
								<tr>
									<td height="13" background="${ctx}/style/default/images/devide-main.gif"></td>
									<td height="13" width="13" background="${ctx}/style/default/images/devide-r.gif"></td>
								</tr>	
							</table>
						</td>
					</tr>
					<tr id="traInsuType">
						<td>
							<table class="inputTable3col" style="width: 100%">
								<tr class="tableTr">
									<td width="25%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuType.insuTypeCode" />
									</td>
									<td width="25%" class="inputBox3col_text" nowrap="nowrap">  
										<input type="hidden" id="insuType_id" name="insuType.idVcDocInsuType" value="${insuType.idVcDocInsuType}" />
										<input id="insuType_insuTypeCode" value="${insuType.insuTypeCode}" name="insuType.insuTypeCode" maxlength="20"  class="required alphanumeric"  type="text"  />
									</td>
									<td width="25%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuType.insuTypeName" />
									</td>
									<td width="25%" class="inputBox3col_text">
										<input value="${insuType.insuTypeName}" name="insuType.insuTypeName"  type="text" class="required" />
									</td>
								</tr>
								<tr>
									<td style="padding-left: 30px;"  colspan="4" align="center">
										&nbsp;
										<input type="button" class="taButton" id="saveButInsuType" value="确 定"/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="backButInsuType" value="返 回 " />
									</td>
								</tr>
							</table>
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
	insuType.initEditPage();
});
</script>
