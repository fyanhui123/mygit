<% 
/*
 * 使用人单证权限控制
***************************************
 * 程序名：DocMngRule.jsp
 * 建立日期：2013-4-22
 * 作者：wanghuajian
 * 模块：使用人单证权限控制
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">

<script type="text/javascript" src="${ctx}/pages/baseinfo/js/DocMngRule.js"></script>
<script type="text/javascript">
	$(document).ready(function () {
	    common.initHtml("DocMngRule");
		docMngRule.initPage();
	});
</script>
</head>
<body>
<div class="tab-center" id="docPowerLimitListPage">
	<table style="width: 100%">
		<tr>
			<td>
			<form id="mainForm">
				<table id="mainTable" style="width: 100%">
					<tr>
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;单证管理控制
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
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docType" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									     <select id="docQueryDtoDocTypeList" name="docQueryDto.docTypeCode" class="selectStyle"  style="width: 155px">
										 </select>
									     <!--  <input id="docQueryDtoDocTypeList" name="docQueryDto.docTypeCode" type="text" class="ui-autocomplete-input" />
                                         <input id="docQueryDtoDocTypeListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
                                         -->
                                    </td>
									<td width="12%" class="inputTitle3col">
										<s:text name="inquiry.inStoryApply.docVersion" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									     <select id="docQueryDtoDocVersionInfoList" name="docQueryDto.docVerCode" class="selectStyle" style="width: 155px">
										</select>										
									</td>
								</tr>
								<tr>
								    <td width="12%" class="inputTitle3col" >
										<s:text name="vcdoc.insuKind.insuType"></s:text>
									</td>
									<td width="25%" class="inputBox3col">
										<select  id="docQueryDtoInsuTypeList" name="docQueryDto.insuType" class="selectStyle" style="width: 155px">
										</select>											
									</td>	
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.insuKind" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
									    <select id="docQueryDtoInsuKindList" name="docQueryDto.insuKind" class="selectStyle" style="width: 155px">
										</select>
									</td>					
								</tr>
								<tr>
								 <td>&nbsp;</td>
								</tr>
								<tr>
									<td width="12%">
										管理控制类型
									</td>
									<td width="38%">
										<input id="orgType" value="0" name="vcDocMngRuleDto.mngType" type="radio" 
											onclick = "changeType(this.value)"	class="ui-tianan-input-text" checked>&nbsp;&nbsp;机构&nbsp;&nbsp;&nbsp;&nbsp;
										<input id="takerType" value="1"  name="vcDocMngRuleDto.mngType" type="radio" 
											onclick = "changeType(this.value)" class="ui-tianan-input-text">&nbsp;&nbsp;使用人	
									</td>									
								</tr>
								<input id="vcDocMngRuleDtoMngObjectCode" name="vcDocMngRuleDto.mngObjectCode" type="hidden" >
								<tr id = "typeOrgCode" >
								   
									<td width="12%"  >
										机构
									</td>
									<td width="38%" >
									     <input id="vcDocMngRuleDtoOrgCode" name="vcDocMngRuleDto.orgCode" class="ui-autocomplete-input required" type="text" />
						                 <input id="vcDocMngRuleDtoOrgCodeName" style="width: 100px;" type="text" readonly="readonly" />
										
										<%-- <input id="vcDocMngRuleDtoOrgCode" name="vcDocMngRuleDto.orgCode" type="text" class="ui-tianan-input-text required"  >	--%>									
									</td>
								</tr>
								<tr  id = "typeTakerCode" style="display:none">
									<td width="12%">
										使用人
									</td>
									<td width="38%" >
									    <input id="vcDocMngRuleDtoTakerCode" name="vcDocMngRuleDto.takerCode" type="text" class="ui-autocomplete-input required">
										<input id="vcDocMngRuleDtoTakerCodeName"   type="text"  class="ui-widget-content ui-autocomplete-label" />
									</td>
								</tr>		
								<!--<tr>
									<td width="12%" class="inputTitle3col">
										<s:text name="inquiry.docDetailInquiry.orgName" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
										<input id="vcDocMngRuleDtoOrgCode" name="vcDocMngRuleDto.orgCode" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="inquiry.docDetailInquiry.oprName" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									    <input id="dvcDocMngRuleDtoOprCode" name="vcDocMngRuleDto.oprCode" type="text" class="ui-tianan-input-text">									    								
									</td>								
								</tr> -->
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
									&nbsp;设置结果列表
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<input id="queryDoc" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input id="resetBut" type="reset" class="taButton" value="<s:text name="common.submit.rest" /> " onclick=""/>
		   							&nbsp;&nbsp;&nbsp;&nbsp;
		   							<input id="saveConfigBut" type="button" class="taButton" value="<s:text name="common.submit.submit" /> " />
		   							&nbsp;&nbsp;&nbsp;&nbsp;
		   							
								</td>
							</tr>
							<tr>
								<td class="pageDevide" colspan="2"></td>
							</tr>
						</table>
		              </td>
					</tr>
				    <%--多行输入与显示 --%>
					<tr id="trh">
					  <td style="width: 98%">
					    <table cellpadding="0" cellspacing="0" border="0" width="98%">
					      <tr>
					        <td>
						      <div id="docMngListDiv">
						        <div id="docMngList"></div>
						      </div>
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