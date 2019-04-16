<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="editPageInsuKind" class="tab-center">
	<table width="100%" style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
			<form id="insuKindForm" action="#" method="post">
			 <input type="hidden" id="actionType" name="actionType" value="${actionType}">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr>
						<td height="40px">
							&nbsp;
						</td>
					</tr>
					<tr onclick="common.hideTR('traInsuKind','hideImg1');">
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="vcdoc.insuKind.addTitle" />
									</td>
								</tr>
								<tr>
									<td height="13" background="${ctx}/style/default/images/devide-main.gif"></td>
									<td height="13" width="13" background="${ctx}/style/default/images/devide-r.gif"></td>
								</tr>	
							</table>
						</td>
					</tr>
					<tr id="traInsuKind">
						<td>
							<table class="inputTable3col" style="width: 100%">
								<tr class="tableTr">
									<td width="15%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuKind.insuType" />
									</td>
									<td width="15%" class="inputBox3col_text" nowrap="nowrap">
										<input id="insuKind_insuType_idVcDocInsuType" type="hidden" value="${insuKind.insuType.idVcDocInsuType}">
										<select id="singliDropDownListInsuType" name="insuKind.insuType.idVcDocInsuType" class="required">
										</select>
									</td>	    							
									<td width="15%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuKind.insuKindCode" />
									</td>
									<td width="15%" class="inputBox3col_text" nowrap="nowrap">  
										<input type="hidden" id="insuKind_id" name="insuKind.idVcDocInsuKind" value="${insuKind.idVcDocInsuKind}" />
										<input id="insuKind_insuKindCode" value="${insuKind.insuKindCode}" name="insuKind.insuKindCode" maxlength="20"  class="required alphanumeric"  type="text"  />
									</td>
									<td width="15%" class="inputTitle3col" align="right">
										<s:text name="vcdoc.insuKind.insuKindName" />
									</td>
									<td width="30%" class="inputBox3col_text">
										<input value="${insuKind.insuKindName}" name="insuKind.insuKindName"  type="text" class="required" />
									</td>
								</tr>
								<tr>
									<td style="padding-left: 30px;"  colspan="6" align="center">
										&nbsp;
										<input type="button" class="taButton" id="saveButInsuKind" value="确 定"/>&nbsp;&nbsp;&nbsp;&nbsp;
										<input type="button" class="taButton" id="backButInsuKind" value="返 回 " />
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
	insuKind.initEditPage();
});
</script>
