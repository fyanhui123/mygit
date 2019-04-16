<% 
/*
 * 使用人维护新增/修改
***************************************
 * 程序名：TakerEdit.jsp
 * 建立日期：2013-6-21
 * 作者：chy
 * 模块：权限机构设置
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
<div id="takerOrgChangePage">
	<form id="takerOrgChangeForm" name="takerOrgChangeForm" action="#" method="post" enctype="multipart/form-data">
		<table id="takerOrgChangeMainTable" style="width: 100%">
			<tr>
				<td height="33">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td  height="28" valign="center">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;使用人机构变更
						</td>
						<td align="right" valign="top" nowrap="nowrap">
							<button id="saveTakerOrgChange" buttonId="b62" class="taButton" type="button">
								<s:text name="common.submit.save" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="takerOrgChangeReturn" buttonId="b63" class="taButton" type="button">
								<s:text name="common.submit.back" />
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
					<table width="100%" class="inputTable3col">
						<tr>
							<td width="12%"  class="inputTitle3col" >
								使用人代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input name="vcTaker.id"  value="${vcTaker.id}" type="hidden"/>
								<input id="changeOrgtakerCode" name="vcTaker.takerCode"  value="${vcTaker.takerCode}" maxlength="100"  type="text" class="ui-autocomplete-input"  readonly="readonly" style="width: 159px;background-color: lightgray;" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								使用人名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="changePrgTakerCodeName" name="vcTaker.takerName" value="${vcTaker.takerName}" maxlength="200"  type="text" class="ui-widget-content ui-autocomplete-label"  style="width: 159px;background-color: lightgray;" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								当前归属机构代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="oldTakerComCode" name="vcTaker.orgCode" value="${vcTaker.orgCode}" type="text" class="ui-tianan-input-text" style="background-color: lightgray;" readonly="readonly" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								当前归属机构名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="oldTakerComName" name="takerComName" value="${vcTaker.orgName}" type="text" class="ui-tianan-input-text" style="background-color: lightgray;" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								新归属机构代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="newTakerComCode" name="vcTaker.newOrgCode" type="text" class="ui-autocomplete-input required" style="width: 155px;" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								新归属机构名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="newTakerComCodeName" name="takerComName" type="text" class="ui-widget-content ui-autocomplete-label required" readonly="readonly"  style="width: 155px;"/>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	$(document).ready(function () {
		orgChangeEdit.initTakerOrgChangePage();
	});
</script>

