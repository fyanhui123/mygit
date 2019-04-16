<% 
/*
 * 印刷入库统计查询
***************************************
 * 程序名：DoInStoreApplyStat.jsp
 * 建立日期：2013-4-7
 * 作者：wanghuajian
 * 模块：印刷入库查询统计
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

<script type="text/javascript" src="${ctx}/pages/inquiry/js/DocInStoreApplyInquiry.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
	    common.initHtml("DocInStoreApplyInquiry");
		docInStoreInquiry.initPage();
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
										&nbsp;<s:text name="inquiry.inStoryApply.inputQueryCondition"/>
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
										<s:text name="vcdoc.insuKind.insuType"></s:text>
									</td>
									<td width="25%" class="inputBox3col">
										<select id="inStoreInquiryDtoInsuTypeList" name="inStoreInquiryDto.insuType"  class="selectStyle" style="width: 155px">
										</select>											
									</td>	
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.insuKind" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
									    <select  id="inStoreInquiryDtoInsuKindList" name="inStoreInquiryDto.insuKind"   class="selectStyle" style="width: 155px">
										</select>
										<%--<input id="inStoreInquiryDtoInsuKindList" name="inStoreInquiryDto.insuKind" type="text" class="ui-tianan-input-text"> --%>
									</td>					
								</tr>
							    <tr >
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docType" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									     <input id="inStoreInquiryDtoDocTypeList" name="inStoreInquiryDto.docTypeCode" type="text"  />
                                         <input id="inStoreInquiryDtoDocTypeListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
									     <!--<select style="width: 100px" id="inStoreInquiryDtoDocTypeList" name="inStoreInquiryDto.docTypeCode">
										</select>										
									-->
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="inquiry.inStoryApply.docVersion" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									      <input id="inStoreInquiryDtoDocVersionInfoList" name="inStoreInquiryDto.docVerCode" type="text" class="comboBox-select" />
                                         <input id="inStoreInquiryDtoDocVersionInfoListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" /><!--
									     <select style="width: 100px" id="inStoreInquiryDtoDocVersionInfoList" name="inStoreInquiryDto.docVerCode">
										</select>										
									   -->
									</td>
								</tr>
								
								<tr>
									<td width="12%" class="inputTitle3col" >
										<s:text name="inquiry.inStoryApply.inStoreAppCode" />
									</td>
									<td  class="inputBox3col_text" >
										<input type="text"  id="inStoreAppCode" name="inStoreInquiryDto.inStoreAppCode" />
									</td>
									<td width="12%"  class="inputTitle3col">
										<s:text name="inquiry.inStoryApply.inStoreStatus" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
										<select id="inStoreStatus" name="inStoreInquiryDto.inStoreStatus"  class="selectStyle" style="width: 155px">
										</select>
									</td>
								</tr>								
								<tr>
									<td width="12%" class="inputTitle3col">
										<s:text name="inquiry.inStoryApply.applyOrgName" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
									<input id="inStoreInquiryDtoApplyOrgCode" name="inStoreInquiryDto.applyOrgCode" class="ui-autocomplete-input required" type="text" /> 
									<input id="inStoreInquiryDtoApplyOrgCodeName" style="width: 100px;" type="text" readonly="readonly" /> 
										<!--<input id="inStoreInquiryDtoApplyOrgCode" name="inStoreInquiryDto.applyOrgCode" type="text" class="ui-tianan-input-text">
										--><input id="inStoreInquiryDtoIsContainSubOrg" name="inStoreInquiryDto.isContainSubOrg" type="checkbox" value="">包含下级
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="inquiry.inStoryApply.applyOprName" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									    <input id="inStoreInquiryDtoApplyOprCode" name="inStoreInquiryDto.applyOprCode" type="text" class="ui-autocomplete-input">
										<input id="inStoreInquiryDtoApplyOprCodeName"   type="text"  class="ui-widget-content ui-autocomplete-label" />
									</td>								
								</tr>
								<tr>
									<td width="12%" class="inputTitle3col" >
										<s:text name="inquiry.inStoryApply.applyTime" />
									</td>
									<td colspan="3"  class="inputBox3col_text" >
										<input type="text"  id="applyStartDate" name="inStoreInquiryDto.applyStartDate"
										value="<fmt:formatDate value="${applyStartDate}" pattern="yyyy-MM-dd"/>" class="Wdate taDatePicker" onclick="WdatePicker()"/> 
										至
										<input type="text"  id="applyEndDate" name="inStoreInquiryDto.applyEndDate" 
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
					<tr >
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="inquiry.inStoryApply.queryList" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<input id="queryInStoreInquiry" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input type="reset" class="taButton" value="<s:text name="common.submit.rest" />" onclick="common.clearGridData('inStoreInquiryGrid');"/>
		   							&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input id="exportInStoreInquiry" type="button" class="taButton" value="<s:text name="common.submit.exportExcel" />" />
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
										<table id="inStoreInquiryGrid" class="inputTable3col" ></table>
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