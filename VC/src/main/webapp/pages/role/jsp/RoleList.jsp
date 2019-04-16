<% 
/*
 * 单证角色查询统计
***************************************
 * 程序名：rolelsit.jsp
 * 建立日期：2016-5-31
 * 作者：fyanhui
 * 模块：单证角色查询统计
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

<script type="text/javascript" src="${ctx}/pages/role/js/RoleListQuiry.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
	    common.initHtml("roleListInquiry");
	    	roleListInquiry.initPage();
	});
</script>
</head>
<body>
<div class="tab-center" id="docInStoreStatListPage">
	<table style="width: 100%">
		<tr>
			<td>
			<form id="subForm">
				<table id="mainTable" style="width: 100%">
					<tr>
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<%--<s:text name="inquiry.inStoryApply.inputQueryCondition"/>
									--%>单证管理员清单查询</td>
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
							<td width="12%" class="inputTitle3col">
									所在机构
									</td>
									<td width="30%" class="inputBox3col_text" nowrap="nowrap" >
									<input id="roleListDtoOrgCode" name="roleListVo.companyCode" class="ui-autocomplete-input required" type="text" /> 
									<input id="roleListDtoOrgCodeName" style="width: 100px;" type="text" readonly="readonly" />
									<input id="roleListIsContainSubOrg" name="roleListVo.underCode" type="checkbox" value="">包含下级
									</td>
									<td width="12%"  class="inputTitle3col" style="visibility: hidden">
									单证角色
									</td>
									<td width="38%"  class="inputBox3col_text" style="visibility: hidden">
										<select id="usertype" name="roleListVo.userType"  class="selectStyle" style="width: 155px">
												<option value="0">单证管理员</option>
										</select>
									</td>
									<td width="12%" >
										状态
									</td>
									<td width="38%" >
										<select class="selectStyle" id="validKind" name="roleListVo.validKind">
											    <option value="2">请选择</option>
											    <option value="0">无效</option>
												<option value='1'>有效</option>
										</select>
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
					<tr >
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">单证管理员清单查询条件
									&nbsp;<%--<s:text name="inquiry.inStoryApply.queryList" />
								--%></td>
								<td align="right" valign="top" nowrap="nowrap">
									<input id="rolelistquerys" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input type="reset" class="taButton" value="<s:text name="common.submit.rest" />" onclick="common.clearGridData('rolelistquery');"/>
		   							&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input id="exportRoleListInquiry" type="button" class="taButton" value="<s:text name="common.submit.exportExcel" />" />
								</td>
							</tr>
							<tr>
								<td class="pageDevide" colspan="2"></td>
							</tr>
						</table>
		              </td>
					</tr>
					<tr id="trc">
						<td>
							<table class="pagerTable">
								<tr>
									<td>
										<table id="rolelistquery" class="inputTable3col" ></table>
										<div id="inStoreInquiryGridPager"></div>
										<input id="id" name="id" value="" type="hidden">
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
</body>
</html>