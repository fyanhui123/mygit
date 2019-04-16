<% 
/*
 * 岗位维护新增/修改
***************************************
 * 程序名：RoleEdit.jsp
 * 建立日期：2013-5-8
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
<div id="roleOrgChangePage">
	<form id="roleOrgChangeForm" name="roleOrgChangeForm" action="#" method="post" >
		<table id="roleOrgChangeMainTable" style="width: 100%">
			<tr>
				<td height="33">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td  height="28" valign="center">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;岗位人员机构变更
						</td>
						<td align="right" valign="top" nowrap="nowrap">
							<button id="saveRoleOrgChange" buttonId="b52" class="taButton" type="button">
								<s:text name="common.submit.save" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="roleOrgChangeReturn" buttonId="b53" class="taButton" type="button">
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
								人员代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input name="vcLevel.id"  value="${vcLevel.id}" type="hidden"/>							
								<input id="changeOrgRoleCode" name="vcLevel.unitCode"  value="${vcLevel.unitCode}"  type="text" class="ui-autocomplete-input"  readonly="readonly" style="width: 155px;background-color: lightgray;" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								人员名称
							</td>
							<td width="38%"  class="inputBox3col_text" >								
								<input id="changeOrgRoleCodeName"  name="vcLevel.unitName"  value="${vcLevel.unitName}" type="text" class="ui-widget-content ui-autocomplete-label"  style="width: 155px;background-color: lightgray;" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								当前上级机构代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input type="hidden" id="oldRoleParentOrgId" name="vcLevel.parentOrgId"/>
								<input id="oldRoleUpperCode" name="roleOrgCode"  value="${vcLevel.parentOrgCode}"  type="text" class="ui-tianan-input-text" style="background-color: lightgray;"  readonly="readonly" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								当前上级机构名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="oldRoleUpperName" name="roleOrgName"  value="${vcLevel.parentOrgName}"  type="text" class="ui-tianan-input-text" style="background-color: lightgray;" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								新上级机构代码
							</td>
							<td width="38%"  class="inputBox3col_text" >								
								<input id="newRoleUpperCode" name="vcLevel.newParentOrgCode" type="text" class="ui-autocomplete-input required" style="width: 155px;"/>
							</td>
							<td width="12%"  class="inputTitle3col" >
								新上级机构名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="newRoleUpperCodeName" name="vcLevel.newParentOrgName" type="text" class="ui-widget-content ui-autocomplete-label required" style="width: 155px;" />
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
		orgChangeEdit.initRoleOrgChangePage();
	});
</script>

