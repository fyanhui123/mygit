
<%
	/*
	 * 使用出入库操作
	 ***************************************
	 * 程序名：DocStorageInOutAdd.jsp
	 * 建立日期：2014-4-14
	 * 作者：whj
	 * 模块：库存管理-单证出入库
	 * 备注：
	 * -----------------------------------------
	 * 修改历史
	 * 序号 日期 修改人 修改原因
	 * 
	 ***************************************
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">		
		<script type="text/javascript" src="${ctx}/pages/store/js/DocStorageInOutAdd.js"></script>
		<script type="text/javascript" src="${ctx}/pages/store/js/ValidStorage.js"></script>
		<script type="text/javascript">
			$(document).ready(function () {
				docStorageInOutAdd.initInOutPage();
				docStorageInOutAdd.alertResultMsg("${actionType}","${resultMsg}");
			});
		</script>
	</head>
<body>
<div id="docStorageInOutAddPage" style="width: 100%">
	<form id="addForm" name="addForm" action="#" method="post" enctype="multipart/form-data">
	<input type="hidden" id="operateType" name="operateType" value="${operateType}">
	<input type="hidden" id="jsonStr" name="jsonStr">
	<textarea id="edit_hiddenDet" name="hiddenDet" type="text"  style="display: none;" rows="4">${jsonStr}</textarea>
		<table id="mainTable" style="width: 100%">
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<c:if test="${operateType=='I'}"><s:text name="storageInOut.title.in" /> </c:if>
								<c:if test="${operateType=='O'}"><s:text name="storageInOut.title.out" /> </c:if>
							</td>
						</tr>
						<tr>
							<td class="pageDevide"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="trd">
				<td>
					<table width="100%" class="inputTable3col">						
						<tr>
							<td width="12%" class="inputTitle3col">
								<!--操作人 -->
								<s:text name="storageInOut.opr" />
							</td>
							<td width="38%" class="inputBox3col_text">
								<input type="hidden"  name="vcStorageInOut.oprCode"  value="${vcStorageInOut.oprCode}"  type="text" class="ui-tianan-input-text" />
								<input id="vcStorageInOut.oprName" type="text" class="ui-tianan-input-text"   value="${vcStorageInOut.oprName}" 
								style="background-color:lightgray;" readonly="readonly" />
							</td>
							<td width="12%" class="inputTitle3col">
								<!--机构 -->
								<s:text name="storageInOut.org" />
							</td>
							<td>
								<input type="hidden"  name="vcStorageInOut.orgCode"  value="${vcStorageInOut.orgCode}"  type="text" class="ui-tianan-input-text" />
								<input name="vcStorageInOut.orgName" value="${vcStorageInOut.orgName}" type="text" class="ui-tianan-input-text" 
								  style="background-color:lightgray;" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td width="12%" class="inputTitle3col">
								<!--操作人 -->
								<s:text name="storageInOut.operateTime" />
							</td>
							<td width="38%" class="inputBox3col_text">	
							<input type="text"  name="vcStorageInOut.operateTime" value="<fmt:formatDate value="${vcStorageInOut.operateTime }" pattern="yyyy-MM-dd H:m:s"/>"
								class="ui-tianan-input-text" style="background-color:lightgray;" readonly="readonly" />
							</td>
						</tr>
						<tr>
							<td class="inputTitle3col" align="left"><s:text name="storageInOut.inOutReason" /></td>
							<td class="inputBox3col_text" nowrap="nowrap" colspan="3">
								<textarea id="vcStorageInOutInOutReason" name="vcStorageInOut.inOutReason" class="required" maxlength="100" style="width: 60%">${vcStorageInOut.inOutReason }</textarea>
							</td>
						</tr>
						<tr>
							<td colspan="4">
								&nbsp;
							</td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<!--单证列表 -->
								<s:text name="store.docVerList" />
							</td>
						</tr>
						<tr>
							<td class="pageDevide"></td>
						</tr>
					</table>
				</td>
			</tr>

			<tr id="tre">
				<td>
					<div style="width: 98%">
						<span id="docInOutDetinput" style="width: 100%"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td style="padding-left: 30px;" colspan="4" align="center">
					<input type="button" id="saveBut" class="taButton" 
					    <c:if test="${operateType=='I'}">value=<s:text name="storageInOut.button.in" /></c:if>
					    <c:if test="${operateType=='O'}">value=<s:text name="storageInOut.button.out" /> </c:if>
					 />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="goBackBtn" class="taButton" value="<s:text name="store.common.cancel" />" />
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="ValidStorageQuery.jsp"%>
<form action="#" id="backMainForm"  method="post" enctype="multipart/form-data">
</form>
</body>
