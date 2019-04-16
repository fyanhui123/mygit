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

<script type="text/javascript" src="${ctx}/pages/inquiry/js/StorageInquiry.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		common.initHtml("StorageInquiry");
		storageInquiry.initPage();
	});
</script>
</head>
<body>
<div class="tab-center" id="storageInquiryListPage">
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
										&nbsp;<s:text name="inquiry.storageInquiry.inputQueryCondition"/>
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
										<select id="storageInquiryVoInsuTypeList" name="storageInquiryVo.insuTypeCode" class="selectStyle" style="width: 155px">
										</select>
									</td>
									
									<td width="12%" >
										<s:text name="inquiry.insuKind"/>
									</td>
									<td width="38%">
										<select id="storageInquiryVoInsuKindList" name="storageInquiryVo.insuKindCode" class="selectStyle" style="width: 155px">
										</select>
									</td>
								</tr>
								<tr>
									<td width="12%" >
										<s:text name="inquiry.docType"/>
									</td>
									<td width="38%">
									 	<input id="storageInquiryVoDocTypeList" name="storageInquiryVo.docTypeCode" type="text" class="ui-autocomplete-input" />
                                         <input id="storageInquiryVoDocTypeListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
									</td>
									<td width="12%" >
										<s:text name="inquiry.docVersion"/>
									</td>
									<td width="38%">
										<input id="storageInquiryVoDocVersionInfoList" name="storageInquiryVo.docVerCode" type="text" class="ui-autocomplete-input" />
                                    	<input id="storageInquiryVoDocVersionInfoListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td width="12%">
										<s:text name="inquiry.storage.takerDocType"/>
									</td>
									<td width="38%">
										<input id="takerDocType" value = "0" name="storageInquiryVo.takerDocType" type="radio" 
											onclick = "changeTakerDocCode(this.value)"	class="ui-tianan-input-text" checked>&nbsp;&nbsp;机构&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="takerDocType" value = "1" name="storageInquiryVo.takerDocType" type="radio" 
											onclick = "changeTakerDocCode(this.value)" class="ui-tianan-input-text">&nbsp;&nbsp;使用人	
									</td>
									<td width="12%" >
										<s:text name="inquiry.status"/>
									</td>
									<td width="38%" >
										<select class="selectStyle" id="docStatusList" name="storageInquiryVo.docStatus">
										</select>
									</td>
								</tr>
								<tr id = "typeOrgCode" >
									<td width="12%"  >
										<s:text name="inquiry.storage.orgCode"/>
									</td>
									<td width="38%" >
										<input id="storageInquiryVoOrgCode" name="storageInquiryVo.orgCode" class="ui-autocomplete-input required" type="text" /> 
										<input id="storageInquiryVoOrgCodeName" style="width: 100px;" type="text" readonly="readonly" />
										<%--<INPUT id="orgCode" name="storageInquiryVo.orgCode" type="text" class="ui-tianan-input-text"  >
										--%><input id="storageInquiryVoIsContainSubOrg" name="storageInquiryVo.isContainSubOrg" type="checkbox" value="">包含下级
									</td>
								</tr>
								<!-- 
								<tr  id = "typeTakerCode" style="display:none">
									<td width="12%">
										<s:text name="inquiry.storage.takerCode"/>
									</td>
									<td width="38%" >
										<INPUT id="takerCode" name="storageInquiryVo.takerCode" type="text" class="ui-tianan-input-text" >
									</td>
								</tr>
								 -->
								<tr  id = "typeTakerCode" style="display:none">
									<td width="12%">
										<s:text name="inquiry.storage.takerCode"/>
									</td>
									<td width="38%" >
										<input id="takerCode"    name="storageInquiryVo.takerCode"   type="text"	class="ui-autocomplete-input" />
										<input id="takerCodeName"   type="text"  class="ui-widget-content ui-autocomplete-label" />
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
									&nbsp;<s:text name="inquiry.storageInquiry.queryList"/>
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<input id="queryStorageInquiry" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " onclick="common.clearGridData('storageInquiryGrid');"/>
		   							&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input id="exportStorageInquiry" type="button" class="taButton" value="<s:text name="common.submit.exportExcel" />" />
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
										<table id="storageInquiryGrid" class="inputTable3col" ></table>
										<div id="storageInquiryGridPager"></div>
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