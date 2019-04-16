<% 
/*
 * 单证库存统计查询
***************************************
 * 程序名：StorageInquiry.jsp
 * 建立日期：2013-4-16
 * 作者：zhxiao
 * 模块：统计查询-单证库存查询统计
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

<script type="text/javascript" src="${ctx}/pages/inquiry/js/AvailableDocInquiry.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		common.initHtml("AvailableDocInquiry");
		availableDocInquiry.initPage();
	});
</script>
</head>
<body>
<div class="tab-center" id="availableDocInquiryListPage">
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
										&nbsp;<s:text name="inquiry.availableDocInquiry.inputQueryCondition"/>
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
									<td width="12%">
										<s:text name="inquiry.insuType"/>
									</td>
									<td width="38%">
										<select id="availableDocInquiryVoInsuTypeList" name="storageInquiryVo.insuTypeCode" class="selectStyle" style="width: 155px">
										</select>
									</td>
									
									<td width="12%" >
										<s:text name="inquiry.insuKind"/>
									</td>
									<td width="38%">
										<select id="availableDocInquiryVoInsuKindList" name="storageInquiryVo.insuKindCode" class="selectStyle" style="width: 155px">
										</select>
									</td>
								</tr>
								<tr>
									<td width="12%" >
										<s:text name="inquiry.docType"/>
									</td>
									<td width="38%">
									 	<input id="availableDocInquiryVoDocTypeList" name="storageInquiryVo.docTypeCode" type="text" class="ui-autocomplete-input" />
                                         <input id="availableDocInquiryVoDocTypeListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
									</td>
									<td width="12%" >
										<s:text name="inquiry.docVersion"/>
									</td>
									<td width="38%">
										<input id="availableDocInquiryVoDocVersionInfoList" name="storageInquiryVo.docVerCode" type="text" class="ui-autocomplete-input" />
                                    	<input id="availableDocInquiryVoDocVersionInfoListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
									</td>
								</tr>
																								
								<tr>
									<td width="12%" >
										<s:text name="inquiry.storage.applyStartDate"/>
									</td>
									<td width="38%" >
										<input type="text" id = "applyStartDate" name="storageInquiryVo.applyStartDate" 
										value="<fmt:formatDate value="${applyStartDate}" pattern="yyyy-MM-dd"/>" class="Wdate taDatePicker" onclick="WdatePicker()"/> 至
										<input type="text" id = "applyEndDate" name="storageInquiryVo.applyEndDate" 
										value="<fmt:formatDate value="${applyEndDate}" pattern="yyyy-MM-dd"/>" class="Wdate taDatePicker"  onclick="WdatePicker()" />
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
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="inquiry.availableDocInquiry.queryList"/>
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<input id="queryAvailableDocInquiry" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " onclick="common.clearGridData('availableDocInquiryGrid');"/>
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
										<table id="availableDocInquiryGrid" class="inputTable3col" ></table>
										<div id="availableDocInquiryGridPager"></div>
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