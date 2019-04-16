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
<div id="roleEditPage">
	<form id="roleForm" name="roleForm" action="#" method="post" >
		<table id="mainTable" style="width: 100%">
			<tr>
				<td height="33">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td  height="28" valign="center">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;岗位信息录入
						</td>
						<td align="right" valign="top" nowrap="nowrap">
							<button id="saveRole" buttonId="b12" class="taButton" type="button">
								<s:text name="common.submit.save" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="roleReturn" buttonId="b13" class="taButton" type="button">
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
					<input type="hidden" id="roleEditType" name="editType"/>
					<table width="100%" class="inputTable3col">
						<tr>
							<td width="12%"  class="inputTitle3col" >
								人员代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input name="vcLevel.id"  value="${vcLevel.id}" type="hidden"/>
								<!-- 
								<input id="roleCode" name="vcLevel.unitCode"  value="${vcLevel.unitCode}" type="text"  maxlength="30"  class="ui-tianan-input-text required alphanumeric" />
								-->
								<input id="roleCode" name="vcLevel.unitCode"  value="${vcLevel.unitCode}"  type="text" class="ui-autocomplete-input required"  style="width: 155px"/>
							</td>
							<td width="12%"  class="inputTitle3col" >
								人员名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<!-- 
								<input id="roleName" name="vcLevel.unitName" value="${vcLevel.unitName}" type="text" class="ui-tianan-input-text required" />
								-->
								<input id="roleCodeName"  name="vcLevel.unitName"  value="${vcLevel.unitName}" type="text" class="ui-widget-content ui-autocomplete-label required"  style="width: 155px" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								上级机构代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input type="hidden" id="roleParentOrgId" name="vcLevel.parentOrgId"/>
								<input id="roleUpperCode" name="roleUpperCode" type="text" class="ui-tianan-input-text required" readonly="readonly" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								上级机构名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="roleUpperName" name="roleUpperName" type="text" class="ui-tianan-input-text required" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								人员类型
							</td>
							<td width="38%"  class="inputBox3col_text">
								<select name="vcLevel.invoiceFlag" id="invoiceFlag"  class="selectStyle required" >
									<option value="0">单证管理员</option>
									<c:if test="${vcLevel.invoiceFlag=='1'}">
										<option value="1" selected="selected">发票管理员</option>
									</c:if>
									<c:if test="${vcLevel.invoiceFlag==null || vcLevel.comType=='0'}">
										<option value="1">发票管理员</option>
									</c:if>
								</select>
							</td>
							<td width="12%"  class="inputTitle3col" >
								显示序号
							</td>
							<td width="38%"  class="inputBox3col_text" colspan="3">
								<input id="roleDisplayNo" name="vcLevel.displayNo" value="${vcLevel.displayNo}" type="text"  class="ui-tianan-input-text required digits"  maxlength="9"/>
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
		roleEdit.initPage();
	});
</script>

