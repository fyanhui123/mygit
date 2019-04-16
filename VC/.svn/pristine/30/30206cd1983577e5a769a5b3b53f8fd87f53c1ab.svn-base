<% 
/*
 * 单证明细查询统计
***************************************
 * 程序名：DoDetailInquiry.jsp
 * 建立日期：2013-4-18
 * 作者：wanghuajian
 * 模块：单证明细查询统计
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

<script type="text/javascript" src="${ctx}/pages/inquiry/js/DocDetailInquiry.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
	    common.initHtml("DocDetailInquiry");
		docDetailInquiry.initPage();
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
										&nbsp;<s:text name="inquiry.docDetailInquiry.inputQueryCondition"/>
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
										<select id="docDetailInquiryDtoInsuTypeList" name="docDetailInquiryDto.insuType"  class="selectStyle" style="width: 155px">
										</select>											
									</td>	
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.insuKind" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
									    <select  id="docDetailInquiryDtoInsuKindList" name="docDetailInquiryDto.insuKind"  class="selectStyle" style="width: 155px">
										</select>
										<%--<input id="docDetailInquiryDtoInsuKindList" name="docDetailInquiryDto.insuKind" type="text" class="ui-tianan-input-text"> --%>
									</td>																
								</tr>						
							    <tr >
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docType" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									      <input id="docDetailInquiryDtoDocTypeList" name="docDetailInquiryDto.docTypeCode" type="text" class="required ui-autocomplete-input" />
                                          <input id="docDetailInquiryDtoDocTypeListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
                                    </td>
									<td width="12%" class="inputTitle3col">
										<s:text name="inquiry.inStoryApply.docVersion" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									    <input id="docDetailInquiryDtoDocVersionInfoList" name="docDetailInquiryDto.docVerCode" type="text" class="ui-autocomplete-input" />
                                    	<input id="docDetailInquiryDtoDocVersionInfoListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
                                    </td>										
								</tr>
								
								<tr>
									<td width="12%" class="inputTitle3col" >
										<s:text name="inquiry.docDetailInquiry.starNum" />
									</td>
									<td  class="inputBox3col_text" >
									 <input id="docDetailInquiryDtoStartNum" name="docDetailInquiryDto.startNum" type="text" class="required ui-tianan-input-text">	
									</td>
									<td width="12%"  class="inputTitle3col">
										<s:text name="inquiry.docDetailInquiry.endNum" />
									</td>
									<td width="38%"  class="inputBox3col_text" >
									 <input id="docDetailInquiryDtoEndNum" name="docDetailInquiryDto.endNum" type="text" class="required ui-tianan-input-text">	
										
									</td>
								</tr>								
								<tr>
									<td width="12%" class="inputTitle3col">
										<s:text name="inquiry.docDetailInquiry.orgName" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
									   <input id="docDetailInquiryDtoOrgCode" name="docDetailInquiryDto.orgCode" class="ui-autocomplete-input" type="text" />
						               <input id="docDetailInquiryDtoOrgCodeName" style="width: 100px;" type="text" readonly="readonly" />
										<!--<input id="docDetailInquiryDtoOrgCode" name="docDetailInquiryDto.orgCode" type="text" class="ui-tianan-input-text">
										--><input id="docDetailInquiryDtoIsContainSubOrg" name="docDetailInquiryDto.isContainSubOrg" type="checkbox" value="">包含下级
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="inquiry.docDetailInquiry.oprName" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									    <input id="docDetailInquiryDtoOprCode" name="docDetailInquiryDto.oprCode" type="text" class="ui-autocomplete-input">
										<input id="docDetailInquiryDtoOprCodeName"   type="text"  class="ui-widget-content ui-autocomplete-label" />
									</td>								
								</tr>
								<tr>
								  <td width="12%" class="inputTitle3col">
										<s:text name="inquiry.docDetailInquiry.businessNo" />							
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									    <input id="docDetailInquiryDtoBuhinessNo" name="docDetailInquiryDto.buhinessNo" type="text" class="ui-tianan-input-text">									    								
									</td>	
									<td width="12%" class="inputTitle3col" >
										<s:text name="inquiry.docDetailInquiry.inquiryTime" />
									</td>
									<td  class="inputBox3col_text" >
										<input type="text"  id="operateStartDate" name="docDetailInquiryDto.operateStartDate"
										value="<fmt:formatDate value="${applyStartDate}" pattern="yyyy-MM-dd"/>" class="Wdate taDatePicker required" onclick="WdatePicker()"/> 
										至
										<input type="text"  id="operateEndDate" name="docDetailInquiryDto.operateEndDate" 
										value="<fmt:formatDate value="${applyEndDate}" pattern="yyyy-MM-dd"/>" class="Wdate taDatePicker required"  onclick="WdatePicker()" />
									</td>
								</tr>								
								
								<tr>
								   <td width="12%" class="inputTitle3col" >
										<s:text name="inquiry.docDetailInquiry.status" />								
									</td>
									<td width="88%" class="inputBox3col_text" nowrap="nowrap" colspan=3>
									  <table>
									    <s:iterator  id="docStatusItem" status="status"  value="docStatusList">
										    <s:if test="#status.index%9==0"><tr></s:if>
											  <td>
											     <input id="docDetailInquiryDtoStatus" name="docDetailInquiryDto.arrStatus" type="checkbox" value="<s:property value="#docStatusItem.codeCode"/>">
											     <s:property value="#docStatusItem.codeCName"/>&nbsp;&nbsp;&nbsp;
											  </td>
											<s:if test="#status.index%9==8 || #status.last"></tr></s:if>
							           </s:iterator>
							         </table>
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
									&nbsp;<s:text name="inquiry.docDetailInquiry.queryList" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<input id="queryDocDetailInquiry" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " onclick="common.clearGridData('docDetailInquiryGrid');"/>
		   							&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input id="exportDocDetailInquiry" type="button" class="taButton" value="<s:text name="common.submit.exportExcel" />" />
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
										<table id="docDetailInquiryGrid" class="inputTable3col" ></table>
										<div id="docDetailInquiryGridPager"></div>
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