<% 
/*
 * 权限机构设置界面
***************************************
 * 程序名：VcLevelSet1.jsp
 * 建立日期：2013-05-03
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
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

<script type="text/javascript" src="${ctx}/pages/baseinfo/js/VcLevelSet1.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/CompanyEdit.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/RoleEdit.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/AgencyEdit.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/TakerEdit.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/OrgChangeEdit.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		//$.fn.zTree.init($("#companyTree"), setting, ${jsonResultList});
		vcLevelSet.initPage();
	});
</script>
</head>
<body style="margin:0 0;">
	<div  style="overflow:auto;" class="ui-layout-west" >
	  <div id="companyTree" class="ztree"></div>
	</div>
	<div id="rightDiv" class="ui-layout-center" >
		<div id="tabs">
			<ul id="ulHead">
				<li><a href="#tabs-1">机构维护</a></li>
				<li><a href="#tabs-2">岗位维护</a></li>
				<li><a href="#tabs-3">中介机构维护</a></li>
				<li><a href="#tabs-4">使用人维护</a></li>
			</ul>
			<%--机构维护标签页 --%>
			<div  id="tabs-1">
					<div class="ui-layout-north">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td  height="28" valign="center">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;下属机构列表
							</td>
							<td align="right" valign="top" nowrap="nowrap">
								<button id="addCompany" buttonId="b01" class="taButton" type="button">
									<s:text name="common.submit.add" />
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="modifyCompany" buttonId="b02" class="taButton" type="button">
									<s:text name="common.submit.alter" />
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="deleteCompany" buttonId="b03" class="taButton" type="button">
									<s:text name="common.submit.deleteOrUnDelete" />
								</button>								
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td class="pageDevide" colspan="2"></td>
						</tr>
					</table>
					</div>
					<div style="overflow:auto;"  id="companyList" class="ui-layout-center" >
						<table id="companyGrid" class="inputTable3col" ></table>
						<div id="companyGridPager"></div>
						<input id="companyId" name="companyId" value="" type="hidden">
					</div>
			</div>
			<%--岗位维护标签页 --%>
			<div id="tabs-2">
				<div class="ui-layout-north">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td  height="28" valign="center">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;岗位列表
							</td>
							<td align="right" valign="top" nowrap="nowrap">
								<button id="addRole" buttonId="b04" class="taButton" type="button">
									<s:text name="common.submit.add" />
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="modifyRole" buttonId="b05" class="taButton" type="button">
									<s:text name="common.submit.alter" />
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="deleteRole" buttonId="b06" class="taButton" type="button">
									<s:text name="common.submit.deleteOrUnDelete" />
								</button>								
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="changeRoleOrg" buttonId="b25_1" class="taButton" type="button">
									变更机构
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td class="pageDevide" colspan="2"></td>
						</tr>
					</table>
					</div>
					<div style="overflow:auto;"  id="roleList" class="ui-layout-center" >
						<table id="roleGrid" class="inputTable3col" ></table>
						<div id="roleGridPager"></div>
						<input id="roleId" name="roleId" value="" type="hidden">
					</div>
			</div>
			<%--中介机构维护标签页 --%>
			<div id="tabs-3">
				<div class="ui-layout-north">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td  height="28" valign="center">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;中介机构列表
							</td>
							<td align="right" valign="top" nowrap="nowrap">
								<button id="addAgency" buttonId="b14" class="taButton" type="button">
									<s:text name="common.submit.add" />
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="modifyAgency" buttonId="b15" class="taButton" type="button">
									<s:text name="common.submit.alter" />
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="deleteAgency" buttonId="b16" class="taButton" type="button">
									<s:text name="common.submit.deleteOrUnDelete" />
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td class="pageDevide" colspan="2"></td>
						</tr>
					</table>
					</div>
					<div style="overflow:auto;"  id="roleList" class="ui-layout-center" >
						<table id="agencyGrid" class="inputTable3col"  style="width: 100%"></table>
						<div id="agencyGridPager"></div>
						<input id="agencyId" name="agencyId" value="" type="hidden">
					</div>
			</div>
			<%--使用人维护标签页 --%>
			<div id="tabs-4">
				<div class="ui-layout-north">
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td  height="28" valign="center">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;使用人列表
							</td>
							<td align="right" valign="top" nowrap="nowrap">
								<button id="addTaker" buttonId="b19" class="taButton" type="button">
									<s:text name="common.submit.add" />
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="modifyTaker" buttonId="b20" class="taButton" type="button">
									<s:text name="common.submit.alter" />
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="deleteTaker" buttonId="b21" class="taButton" type="button">
									<s:text name="common.submit.deleteOrUnDelete" />
								</button>								
								&nbsp;&nbsp;&nbsp;&nbsp;
								<button id="changeTakerOrg" buttonId="b25_2" class="taButton" type="button">
									变更机构
								</button>
								&nbsp;&nbsp;&nbsp;&nbsp;
							</td>
						</tr>
						<tr>
							<td class="pageDevide" colspan="2"></td>
						</tr>
					</table>
					</div>
					<div style="overflow:auto;"  id="companyList" class="ui-layout-center" >
						<table id="takerGrid" class="inputTable3col" ></table>
						<div id="takerGridPager"></div>
						<input id="takerId" name="takerId" value="" type="hidden">
					</div>
			</div>
		</div>
	</div>
<%@include file="CompanyEdit.jsp" %>
<%@include file="RoleEdit.jsp" %>
<%@include file="AgencyEdit.jsp" %>
<%@include file="TakerEdit.jsp" %>
<%@include file="RoleOrgChange.jsp" %>
<%@include file="TakerOrgChange.jsp" %>
</body>
</html>

