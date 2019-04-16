<% 
/*
 * 单证明细查询统计[新]
***************************************
 * 程序名：DoDetailInquiryNew.jsp
 * 建立日期：2014-03-17
 * 作者：wanghuajian
 * 模块：单证明细查询统计[新]
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/inquiry/js/DocDetailInquiryNew.js"></script>
<script type="text/javascript">  
	$(document).ready(function () {
	    common.initHtml("DocDetailInquiryNew");
		docDetailInquiryNew.initPage();
	}); 
</script>
</head>
<body>
  <div id="docDetailInquiryNew" style="display:">
	   <%--单证类型选择 --%>
       <div id="tabs-1">
			<table width="100%" style="width: 100%">
				<tr>
					<td id="mainTableLeftTd">
					<form  id="docQueryForm" action="#" method="post" enctype="multipart/form-data">					
					   <table width="100%" style="width: 100%">					
							<tr>
							  <td height="33">
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td  height="20" valign="top">
											<img src="${ctx}/style/default/images/point4.gif" class="bottom">
											&nbsp;<s:text name="visaBase.title.docVersionInfoQueryCondition" />
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
									<table width="100%" class="inputTable3col">
									   <tr >
										    <td width="12%" class="inputTitle3col">
												<s:text name="visaBase.docVersionInfo.docType" />									
											</td>
											<td width="38%" class="inputBox3col_text" nowrap="nowrap">
											     <select  id="queryDtoDocTypeList" name="queryDto.idVcDocType" class="required selectStyle" style="width: 220px">
												</select>										
											</td>
											<td width="12%" class="inputTitle3col" >
												<s:text name="visaBase.docVersionInfo.status" />								
											</td>
											<td width="38%" class="inputBox3col_text">										
												<select id="queryDtoStatus" name="queryDto.status" class="required selectStyle" style="width: 220px">
												</select>
											</td>		
										</tr>								
										<tr id="relInsuKindCondition">
											<td width="12%" class="inputTitle3col" >
												<s:text name="visaBase.riskClass"></s:text>
											</td>
											<td width="25%" class="inputBox3col">
												<select id="queryDtoInsuTypeList" name="queryDto.insuTypeCode" class="selectStyle" style="width: 220px">
												</select>											
											</td>	
											<td width="12%" class="inputTitle3col">
												<s:text name="visaBase.risk" />								
											</td>
											<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
											   <select  id="queryDtoInsuKindList" name="queryDto.insuKind"  class="selectStyle" style="width: 220px">
												</select>
											</td>								
										</tr>
										<tr >
										    <td width="12%" class="inputTitle3col">
												<s:text name="visaBase.docVersionInfo.docVerCode" />
											</td>
											<td width="38%" class="inputBox3col_text">
												<input id="queryDtoDocVerCode" name="queryDto.docVerCode" type="text" class="ui-tianan-input-text" style="width: 215px">
											</td>
											<td width="12%" class="inputTitle3col">
												<s:text name="visaBase.docVersionInfo.docVerName" />								
											</td>
											<td width="38%" class="inputBox3col_text" nowrap="nowrap">
												<input id="queryDtoDocVerName" name="queryDto.docVerName" type="text" class="ui-tianan-input-text" style="width: 215px">
											</td>									
										</tr>											
									</table>
								</td>
							</tr>							
							<tr >
								<td height="33">						
								<table cellpadding="0" cellspacing="0" border="0" width="100%">
									<tr>
										<td class="f-black" height="28" valign="center">
											<img src="${ctx}/style/default/images/point4.gif" class="bottom">
											&nbsp;<s:text name="visaBase.list.docVersionInfo" />
										</td>
										<td  align="right" valign="top" nowrap="nowrap">								   
											<input type="button"  class="taButton" id="queryDocVersionInfoBut"  value="<s:text name="common.submit.query" />"/>
											&nbsp;&nbsp;&nbsp;&nbsp;
											<input type="reset"  class="taButton" id="docQueryRestBut" value="<s:text name="common.submit.rest" />"  />
										</td>
									</tr>
									<tr>
										<td class="pageDevide" colspan="2">&nbsp;</td>
									</tr>
								</table>
				              </td>
							</tr>											
							<tr>
							  <td>							   
								<table class="pagerTable">
									<tr>
										<td>
											<table id="docVersionInfoGrid" class="inputTable3col" ></table>
											<div id="docVersionInfoGridPager"></div>
											<input id="selectIds" name="selectIds"  type="hidden">											
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
		 <%--单证状态查询 --%>
		<div id="tabs-2">
		  <table width="100%" style="width: 100%">
			<tr>
				<td>
					<form id="subForm" action="#" method="post" enctype="multipart/form-data" target="_self">
						<table id="mainQueryTable" style="width: 100%">
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
										   <td class="pageDevide" colspan="4"></td>
									    </tr>
									</table>
								</td>
							</tr>
							  
							<tr>
								<td >
									<table width="100%" class="inputTable3col">	
										<tr>
											<td width="12%" class="inputTitle3col" >
												<s:text name="inquiry.docDetailInquiry.starNum" />
											</td>
											<td  width="38%" class="inputBox3col_text" >
											 <input id="docDetailInquiryDtoStartNum" name="docDetailInquiryDto.startNum" type="text" class="ui-tianan-input-text" style="width: 215px">	
											</td>
											<td width="12%"  class="inputTitle3col">
												<s:text name="inquiry.docDetailInquiry.endNum" />
											</td>
											<td width="38%"  class="inputBox3col_text" >
											 <input id="docDetailInquiryDtoEndNum" name="docDetailInquiryDto.endNum" type="text" class="ui-tianan-input-text" style="width: 215px">	
												
											</td>
										</tr>								
										<tr>
											<td width="12%" class="inputTitle3col">
												<s:text name="inquiry.docDetailInquiry.orgName" />								
											</td>
											<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
											   <input id="docDetailInquiryDtoOrgCode" name="docDetailInquiryDto.orgCode" value="${ docDetailInquiryDto.orgCode}" class="ui-autocomplete-input" type="text" />
								               <input id="docDetailInquiryDtoOrgCodeName"  value="${docDetailInquiryDto.orgName}" style="width: 200px;" type="text" readonly="readonly" />
												<input id="docDetailInquiryDtoIsContainSubOrg" name="docDetailInquiryDto.isContainSubOrg" type="checkbox" value="1" checked="checked">包含下级
											</td>
											<td width="12%" class="inputTitle3col">
												<s:text name="inquiry.docDetailInquiry.oprName" />									
											</td>
											<td width="38%" class="inputBox3col_text" nowrap="nowrap">
											    <input id="docDetailInquiryDtoOprCode" name="docDetailInquiryDto.oprCode" type="text" class="ui-autocomplete-input">
												<input id="docDetailInquiryDtoOprCodeName"   type="text"  class="ui-widget-content ui-autocomplete-label" style="width: 155px"/>
											</td>								
										</tr>
										<tr>
										   <td width="12%" class="inputTitle3col">
												<s:text name="inquiry.docDetailInquiry.businessNo" />							
											</td>
											<td width="38%" class="inputBox3col_text" nowrap="nowrap">
											    <input id="docDetailInquiryDtoBusinessNo" name="docDetailInquiryDto.businessNo" type="text" class="ui-tianan-input-text" style="width: 215px">									    								
											</td>	
											<td width="12%" class="inputTitle3col" >
												<s:text name="inquiry.docDetailInquiry.inquiryTime" />
											</td>
											<td  width="38%" class="inputBox3col_text">
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
											<td width="88%" class="inputBox3col_text" nowrap="nowrap" colspan="3">
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
						       <td  colspan="4">
							      <div style="padding-left: 45%;">										
									<input id="queryDocDetailInquiry" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " onclick="common.clearGridData('docDetailInquiryGrid');"/>
		   					      </div>
								</td>
							</tr>							
						   
							
						</table>
					  </form>
					</td>
				</tr>
			</table>
	    </div>
  </div>
  <div id="docDetailInquiryResult" style="width: 100%;display:none">
     <table width="100%" style="width: 100%">
		<tr>
			<td>
				<table class="pagerTable">
					<tr>
						<td>
							<table id="docDetailInquiryGrid" class="inputTable3col" ></table>
							<div id="docDetailInquiryGridPager"></div>
							<input id="docStatusId" name="docStatusId" value="" type="hidden">
						</td>
					</tr>
				</table>
			</td>
		</tr>
        <tr>								
	        <td>
		      <div style="padding-left: 45%;">										
				<input id="goBackBut" type="button" class="taButton" value="返回" />
				<input id="explore" type="button" class="taButton" value="导出" />
	          </div>
			</td>
		</tr>
	</table>	
  </div>
  
   <div id="docDetailListResult" style="width: 100%;display:none">
     <table width="100%" style="width: 100%">
		<tr>
			<td>
				<table class="pagerTable">
					<tr>
						<td>
							<table id="docDetailListGrid" class="inputTable3col" ></table>
							<div id="docDetailListGridPager"></div>
							<input id="newDocStatusId" name="newDocStatusId" value="" type="hidden">
						</td>
					</tr>
				</table>
			</td>
		</tr>
        <tr>								
	        <td>
		      <div style="padding-left: 45%;">										
				<input id="goBackBut1" type="button" class="taButton" value="返回" />
	          </div>
			</td>
		</tr>
	</table>	
  </div>
</body>
</html>