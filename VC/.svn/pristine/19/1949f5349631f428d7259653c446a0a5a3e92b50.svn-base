<% 
/*
 * 印刷入库查询
***************************************
 * 程序名：DocumentInStoreQuery.jsp
 * 建立日期：2013-3-28
 * 作者：Leo
 * 模块：印刷入库查询
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

<script type="text/javascript" src="${ctx}/pages/store/js/DocumentInStoreApplyQuery.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/DocumentInStoreApplyView.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		common.initHtml("DocumentInStoreQuery");
		docInStore.initPage();
	});
</script>
</head>
<body>
<div class="tab-center" id="docInStoreListPage">
	<table style="width: 100%">
		<tr>
			<td>
			<form>
				<table id="mainTable" style="width: 100%">
					<tr>
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="common.form.entryQuertyCondition" />
									</td>
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
									<td width="12%" class="inputTitle3col" >
										<!--印刷入库单号-->
										<s:text name="store.inStoreAppCode" />
									</td>
									<td colspan="3"  class="inputBox3col_text" >
										<input type="text"  id="inStoreAppCode" name="inStoreAppCode"  maxlength="20"/>
									</td>
									<td width="12%"  class="inputTitle3col">
										<!--申请单状态-->
										<s:text name="store.inStoreStatus" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<select id="inStoreStatus" name="inStoreStatus" class="selectStyle" style="width: 155px">
										</select>
									</td>
								</tr>
								<tr>
									<td width="12%" class="inputTitle3col" >
										<!--申请时间-->
										<s:text name="store.inStoreApplyTime" />
									</td>
									<td colspan="3"  class="inputBox3col_text" >
										<input type="text"  id="applyStartDate" name="applyStartDate" 
											value="<fmt:formatDate value="${applyStartDate }" pattern="yyyy-MM-dd"/>"  class="Wdate taDatePicker" onclick="WdatePicker()"/> 
											<s:text name="store.time.to" />
										<input type="text"  id="applyEndDate" name="applyEndDate" 
											value="<fmt:formatDate value="${applyEndDate }" pattern="yyyy-MM-dd"/>" class="Wdate taDatePicker"  onclick="WdatePicker()" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td colspan="4">
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
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="middle">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;
									<!--印刷入库申请列表-->
									<s:text name="store.inStoreApplyList" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<input id="queryInStore" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
			   						<!--<input type="reset" id="restBtn" class="taButton" value="<s:text name="common.submit.rest" /> " 
			   							onclick="common.clearGridData('inStoreGatherGrid')"/>
			   						 --><input type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " 
			   						 onclick="common.clearGridData('inStoreGatherGrid');"/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="addDocInStore" buttonId="b04"  type="button" class="taButton">
										<s:text name="common.submit.add" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="modifyDocInStore" buttonId="b06" type="button"  class="taButton">
										<s:text name="common.submit.alter" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="submitDocInStore" buttonId="b08" type="button"  class="taButton">
										<s:text name="common.submit.submit" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="deleteDocInStore" buttonId="b07" type="button" class="taButton">
										<s:text name="common.submit.delete" />
									</button>
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
							<table class="pagerTable">
								<tr>
									<td>
										<table id="inStoreGatherGrid" class="inputTable3col" ></table>
										<div id="inStoreGatherGridPager"></div>
										<input id="id" name="id" value="" type="hidden">
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
				</table>
				</form>
			</td>
		</tr>
	</table>
</div>
<%@include file="DocumentInStoreApplyView.jsp" %>
</body>
</html>