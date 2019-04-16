<% 
/*
 * 单证申领统计查询
***************************************
 * 程序名：ApplyInquiry.jsp
 * 建立日期：2013-4-11
 * 作者：zhxiao
 * 模块：统计查询-单证申领查询统计
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

<script type="text/javascript" src="${ctx}/pages/inquiry/js/ApplyInquiry.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
		common.initHtml("ApplyInquiry");
		applyInquiry.initPage();
	});
</script>
</head>
<body>
<div class="tab-center" id="applyInquiryListPage">
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
										&nbsp;<s:text name="inquiry.Apply.inputQueryCondition"/>
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
										<select id="applyInquiryVoInsuTypeList" name="applyInquiryVo.insuTypeCode" class="selectStyle" style="width: 155px">
										</select>
									</td>
									<td width="12%" >
										<s:text name="inquiry.insuKind"/>
									</td>
									<td width="38%">
										<select id="applyInquiryVoInsuKindList" name="applyInquiryVo.insuKindCode" class="selectStyle" style="width: 155px">
										</select>
									</td>
									
								</tr>
								<tr>
									<td width="12%" >
										<s:text name="inquiry.docType"/>
									</td>
									<td width="38%">
										<input id="applyInquiryVoDocTypeList" name="applyInquiryVo.docTypeCode" type="text" class="ui-autocomplete-input" />
                                         <input id="applyInquiryVoDocTypeListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
									
										<!--<select id="applyInquiryVoDocTypeList" name="applyInquiryVo.docTypeCode" class="selectStyle" style="width: 155px">
										</select>
									--></td>

									<td width="12%" >
										<s:text name="inquiry.docVersion"/>
									</td>
									<td width="38%">
										<input id="applyInquiryVoDocVersionInfoList" name="applyInquiryVo.docVerCode" type="text" class="ui-autocomplete-input" />
                                    	<input id="applyInquiryVoDocVersionInfoListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
                                    	
										<!--<select id="applyInquiryVoDocVersionInfoList" name="applyInquiryVo.docVerCode" class="selectStyle" style="width: 155px" >
										</select>
									--></td>
								</tr>
							 	<tr>
									<td width="12%" >
										<s:text name="inquiry.Apply.applyCode"/>
									</td>
									<td width="38%">
										<input id="applyCode" name="applyInquiryVo.applyCode" type="text" class="ui-tianan-input-text"> 
									</td>
									<td width="12%">
										<s:text name="inquiry.Apply.appOprName"/>
									</td>
									<td width="38%">
									    <input id="applyOprCode" name="applyInquiryVo.applyOprCode" type="text" class="ui-autocomplete-input">
										<input id="applyOprCodeName"   type="text"  class="ui-widget-content ui-autocomplete-label" />
									</td>
								</tr>
								<tr>
									<td width="12%" >
										<s:text name="inquiry.Apply.appOrgCode"/>
									</td>
									<td width="38%" >
										<input id="applyInquiryVoApplyOrgCode" name="applyInquiryVo.applyOrgCode" class="ui-autocomplete-input" type="text" /> 
										<input id="applyInquiryVoApplyOrgCodeName" style="width: 100px;" type="text" readonly="readonly" />
										<!--<INPUT id="applyOrgCode" name="applyInquiryVo.applyOrgCode" type="text" class="ui-tianan-input-text">
										-->
										<input id="applyInquiryVoIsContainSubOrg" name="applyInquiryVo.isContainSubOrg" type="checkbox" value="">包含下级
									</td>
									<td width="12%" >
										<s:text name="inquiry.Apply.applyStatus"/>
									</td>
									<td width="38%" >
										<select id="applyStatus" name="applyInquiryVo.applyStatus" class="ui-tianan-input-text">
										</select>
									</td>
						
								</tr>
								<tr>
									<td width="12%" >
										<s:text name="inquiry.Apply.applyTime"/>
									</td>
									<td width="38%" >
										<input type="text" id = "applyStartDate" name="applyInquiryVo.applyStartDate" 
										value="<fmt:formatDate value="${applyStartDate}" pattern="yyyy-MM-dd"/>" class="Wdate taDatePicker required" onclick="WdatePicker()"/> 至
										<input type="text" id = "applyEndDate" name="applyInquiryVo.applyEndDate" 
										value="<fmt:formatDate value="${applyEndDate}" pattern="yyyy-MM-dd"/>" class="Wdate taDatePicker required"  onclick="WdatePicker()" />
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
									&nbsp;<s:text name="inquiry.Apply.queryList"/>
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<input id="queryApplyInquiry" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " onclick="common.clearGridData('applyInquiryGrid');"/>
		   							&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input id="exportApplyInquiry" type="button" class="taButton" value="<s:text name="common.submit.exportExcel" />" />
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
										<table id="applyInquiryGrid" class="inputTable3col" ></table>
										<div id="applyInquiryGridPager"></div>
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