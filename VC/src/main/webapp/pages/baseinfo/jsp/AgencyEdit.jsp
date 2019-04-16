<% 
/*
 * 中介机构维护新增/修改
***************************************
 * 程序名：AgencyEdit.jsp
 * 建立日期：2013-6-19
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
<div id="agencyEditPage">
	<form id="agencyForm" name="agencyForm" action="#" method="post" enctype="multipart/form-data">
		<table id="mainTable" style="width: 100%">
			<tr>
				<td height="33">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td  height="28" valign="center">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;中介机构信息录入
						</td>
						<td align="right" valign="top" nowrap="nowrap">
							<button id="saveAgency" buttonId="b17" class="taButton" type="button">
								<s:text name="common.submit.save" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="agencyReturn" buttonId="b18" class="taButton" type="button">
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
					<input type="hidden" id="agencyEditType" name="editType"/>
					<table width="100%" class="inputTable3col">
						<tr>
							<td width="12%"  class="inputTitle3col" >
								中介机构代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input name="vcAgencyOrg.id"  value="${vcAgencyOrg.id}" type="hidden"/>
								<input id="agencyCode" name="vcAgencyOrg.agencyOrgCode"  value="${vcAgencyOrg.agencyOrgCode}" maxlength="20"  type="text" class="ui-tianan-input-text  required alphanumeric" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								中介机构名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="agencyName" name="vcAgencyOrg.agencyOrgName" value="${vcAgencyOrg.agencyOrgName}" maxlength="100"  type="text" class="ui-tianan-input-text required" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								归属机构代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="agencyComCode" name="vcAgencyOrg.orgCode" type="text" class="ui-tianan-input-text required" readonly="readonly" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								归属机构名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="agencyComName" name="agencyComName" type="text" class="ui-tianan-input-text required" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								销售渠道大类
							</td>
							<td width="38%"  class="inputBox3col_text">
								<input id="businessCodeDropDownList"  value="${vcAgencyOrg.businessCode}" type="hidden"/>
								<select name="vcAgencyOrg.businessCode" id="businessCode"  class="selectStyle required" >
								</select>
							</td>
							<td width="12%"  class="inputTitle3col" >
								销售渠道小类
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="businessDetailCodeDropDownList"  value="${vcAgencyOrg.businessDetailCode}" type="hidden"/>
								<select name="vcAgencyOrg.businessDetailCode" id="businessDetailCode"  class="selectStyle required" >
								</select>
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								是否销售网点
							</td>
							<td width="38%"  class="inputBox3col_text">
								<select name="vcAgencyOrg.isSalesNet" id="isSalesNet"  class="selectStyle required" >
									<option value="0">否</option>
									<c:if test="${vcAgencyOrg.isSalesNet=='1'}">
										<option value="1" selected="selected">是</option>
									</c:if>
									<c:if test="${vcAgencyOrg.isSalesNet==null || vcAgencyOrg.isSalesNet=='0'}">
										<option value="1">是</option>
									</c:if>
								</select>
							</td>
							<td width="12%"  class="inputTitle3col" >
								显示序号
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="agencyDisplayNo" name="vcAgencyOrg.displayNo" value="${vcAgencyOrg.displayNo}" type="text" class="ui-tianan-input-text required digits" maxlength="9" />
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
		agencyEdit.initPage();
	});
</script>

