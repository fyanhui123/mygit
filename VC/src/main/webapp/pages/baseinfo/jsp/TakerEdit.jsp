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
<div id="takerEditPage">
	<form id="takerForm" name="takerForm" action="#" method="post" enctype="multipart/form-data">
		<table id="mainTable" style="width: 100%">
			<tr>
				<td height="33">
				<table cellpadding="0" cellspacing="0" border="0" width="100%">
					<tr>
						<td  height="28" valign="center">
							<img src="${ctx}/style/default/images/point4.gif" class="bottom">
							&nbsp;使用人信息录入
						</td>
						<td align="right" valign="top" nowrap="nowrap">
							<button id="saveTaker" buttonId="b22" class="taButton" type="button">
								<s:text name="common.submit.save" />
							</button>
							&nbsp;&nbsp;&nbsp;&nbsp;
							<button id="takerReturn" buttonId="b23" class="taButton" type="button">
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
					<input type="hidden" id="takerEditType" name="editType"/>
					<table width="100%" class="inputTable3col">
						<tr>
							<td width="12%"  class="inputTitle3col" >
								使用人代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input name="vcTaker.id"  value="${vcTaker.id}" type="hidden"/>
								<input id="takerCode" name="vcTaker.takerCode"  value="${vcTaker.takerCode}" maxlength="100"  type="text" class="ui-autocomplete-input required"  style="width: 159px" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								使用人名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="takerCodeName" name="vcTaker.takerName" value="${vcTaker.takerName}" maxlength="200"  type="text" class="ui-widget-content ui-autocomplete-label required"  style="width: 159px" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								归属机构代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="takerComCode" name="vcTaker.orgCode" type="text" class="ui-tianan-input-text required" readonly="readonly" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								归属机构名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="takerComName" name="takerComName" type="text" class="ui-tianan-input-text required" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								使用人类型
							</td>
							<td width="38%"  class="inputBox3col_text">
								<input id="takerTypeCodeDropDownList"  value="${vcTaker.takerTypeCode}" type="hidden"/>
								<select name="vcTaker.takerTypeCode" id="takerTypeCode"  class="selectStyle required" >
								</select>
							</td>
							<td width="12%"  class="inputTitle3col" >
								所属中介机构
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="agencyOrgCodeDropDownList"  value="${vcTaker.agencyOrgCode}" type="hidden"/>
								<select name="vcTaker.agencyOrgCode" id="takerAgencyOrgCode"  class="selectStyle" >
								</select>
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								银行账号
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="bankAccount" name="vcTaker.bankAccount" value="${vcTaker.bankAccount}" type="text" class="ui-tianan-input-text digits" maxlength="50" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								银行代码
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="bankCode" name="vcTaker.bankCode" value="${vcTaker.bankCode}" maxlength="30"  type="text" class="ui-tianan-input-text" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								账户名称
							</td>
							<td width="38%"  class="inputBox3col_text" >
								<input id="bankAccountName" name="vcTaker.bankAccountName"  value="${vcTaker.bankAccountName}" maxlength="50"  type="text" class="ui-tianan-input-text" />
							</td>
							<td width="12%"  class="inputTitle3col" >
								开户银行名称
							</td>
							<td width="38%" class="inputBox3col_text" >
								<input id="bankName" name="vcTaker.bankName"  value="${vcTaker.bankName}" maxlength="100"  type="text" class="ui-tianan-input-text" />
							</td>
						</tr>
						<tr>
							<td width="12%"  class="inputTitle3col" >
								银行卡折类型
							</td>
							<td colspan="3"  class="inputBox3col_text">
								<input id="passbookOrCardDropDownList"  value="${vcTaker.passbookOrCard}" type="hidden"/>
								<select name="vcTaker.passbookOrCard" id="passbookOrCard"  class="selectStyle" >
								</select>
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
		takerEdit.initPage();
	});
</script>

