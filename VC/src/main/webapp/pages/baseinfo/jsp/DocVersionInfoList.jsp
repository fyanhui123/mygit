<%
	/*
	 * 单证类型查询主页面
	 ***************************************
	 * 程序名：DocVersionInfoList.jsp
	 * 建立日期：2013-06-14
	 * 作者：whj
	 * 模块：基础信息维护-单证类型查询
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
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/DocVersionInfo.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/DocVersionInfoView.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/RelInsuKindPage.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/RelAreaPage.js"></script>
<script type="text/javascript">  
	$(document).ready(function () {
	    common.initHtml("DocVersionInfoList");
		docVersionInfo.initPage();	
		docVersionInfo.alertMsg("${actionType}","${resultMsg}");	
	});	
</script>
</head>
<body>
  <div id="docVersionInfoMain">
	<table width="100%" style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
			<form  id="queryForm" action="#" method="post" enctype="multipart/form-data" target="_self">
			 <input type="hidden"  id="docVersionTypeFlag" value="${docVersionTypeFlag}"><!--
			  <input type="hidden"  id="docType" name="queryDto.docType" value="${docType}">
				--><table width="100%" style="width: 100%">					
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
								<td class="pageDevide"></td>
							</tr>
						</table>
		               </td>						
					</tr>
					<tr>
						<td>
							<table width="100%" class="inputTable3col">
							   <tr >
							        <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docType.docTypeType" />
									</td>
									<td width="38%" class="inputBox3col_text" >										
									   <select  id="queryDtoDocType"  name="queryDto.docType" class="selectStyle" style="width: 155px" >
									   </select>
									</td>
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docType" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									     <select  id="queryDtoDocTypeList" name="queryDto.idVcDocType" class="selectStyle" style="width: 155px">
										</select>										
									</td>
								</tr>								
								<tr id="relInsuKindCondition">
									<td width="12%" class="inputTitle3col" >
										<s:text name="vcdoc.insuKind.insuType"></s:text>
									</td>
									<td width="25%" class="inputBox3col">
										<select id="queryDtoInsuTypeList" name="queryDto.insuTypeCode" class="selectStyle" style="width: 155px">
										</select>											
									</td>	
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.insuKind" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
									   <select  id="queryDtoInsuKindList" name="queryDto.insuKind"  class="selectStyle" style="width: 155px">
										</select>
									</td>								
								</tr>
								<tr >
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docVerCode" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="queryDtoDocVerCode" name="queryDto.docVerCode" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docVerName" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="queryDtoDocVerName" name="queryDto.docVerName" type="text" class="ui-tianan-input-text">
									</td>									
								</tr>								
								<tr >
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.area" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input id="queryDtoArea" class="ui-autocomplete-input"  name="queryDto.orgCode" type="text" />
             							<input id="queryDtoAreaName" style="width: 100px;" value="${codeLabel}" type="text" readonly="readonly" />
									</td>
									<td width="12%" class="inputTitle3col" >
										<s:text name="visaBase.docVersionInfo.status" />								
									</td>
									<td width="38%" class="inputBox3col_text" colspan="3">										
										<select id="queryDtoStatus" name="queryDto.status" class="selectStyle" style="width: 155px">
										</select>
									</td>										
								</tr>
                                <tr >								   
									<td width="12%" class="inputTitle3col" >
										<s:text name="visaBase.docVersionInfo.isOrder" />								
									</td>
									<td width="38%" class="inputBox3col_text" colspan="3">										
										<select id="queryDtoIsOrder" name="queryDto.isOrder" class="selectStyle" style="width: 155px">
										</select>
									</td>										
								</tr>																	
							</table>
						</td>
					</tr>
					<tr>
					  <td >
					    &nbsp;
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
									<input type="reset"  class="taButton" id="restBut" value="<s:text name="common.submit.rest" />" onclick="common.clearGridData('docVersionInfoGrid');" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button"  class="taButton" id="addDocVersionInfoBut" value="<s:text name="common.submit.add" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;									
									 <input type="button" class="taButton" id="updateDocVersionInfoBut" value="<s:text name="common.submit.alter" />" />
								     &nbsp;&nbsp;&nbsp;&nbsp;
								      <input type="button" class="taButton" id="viewDocVersionInfoBut" value="<s:text name="common.submit.view" />" />
								     &nbsp;&nbsp;&nbsp;&nbsp;
								     <input type="button" class="taButton" id="makeUseBut" value="<s:text name="visaBase.buttion.makeUseBut" />" />							    
								      &nbsp;&nbsp;&nbsp;&nbsp;								   
									  <!--	<input type="button" class="taButton" id="relInsuKindBut" value="<s:text name="visaBase.buttion.relInsuKindBut" />" />
									  &nbsp;&nbsp;&nbsp;&nbsp;
									 <input type="button" class="taButton"  id="relAreaBut" value="<s:text name="visaBase.buttion.relAreaBut" />" />
									  &nbsp;&nbsp;&nbsp;&nbsp;
									  -->
									   <input type="button" class="taButton" id="relInsuKindBut_new" value="<s:text name="visaBase.buttion.relInsuKindBut" />" />
									  &nbsp;&nbsp;&nbsp;&nbsp;
									  <input type="button" class="taButton"  id="relAreaBut_new" value="<s:text name="visaBase.buttion.relAreaBut" />" />
									  &nbsp;&nbsp;&nbsp;&nbsp;
								</td>
							</tr>
							<tr>
								<td class="pageDevide" colspan="2">&nbsp;</td>
							</tr>
						</table>
		              </td>
					</tr>	
									
					<tr id="trc">
					  <td>
					    <table class="pagerTable">
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
					  </td>
					</tr>
					
				</table>
				</form>
			</td>
		</tr>
	</table>
  </div>
    <%--@ include file="DocVersionInfoEdit.jsp"--%>
    <%@ include file="DocVersionInfoView.jsp"%>
    <%--<%@ include file="DocVersionInfoInsuKindSelect.jsp"%>   
    <%@ include file="DocVersionInfoAreaSelect.jsp"%>--%>
    <%@ include file="RelInsuKindPage.jsp"%>
    <%@ include file="RelAreaPage.jsp"%>
    
</body>
</html>

