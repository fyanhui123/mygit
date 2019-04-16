<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/PubCodeType.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/PubCodeTypeView.js"></script>
<script type="text/javascript">  
	$(document).ready(function () {
	common.initHtml("PubCodeTypeList");
		pubCodeType.initPage();		
	});	
</script>
</head>
<body>
  <div id="pubCodeTypeMain">
	<table width="100%" style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
			<form id="queryForm" >
				<table width="100%" id="mainTable" style="width: 100%">					
					<tr>
					  <td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="20" valign="top">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="order.orderLaunch.orderId" />
								</td>
							</tr>
							<tr>
								<td class="pageDevide"></td>
							</tr>
						</table>
		               </td>						
					</tr>
					<tr id="tra">
						<td>
							<table width="100%" class="inputTable3col">
								<tr >
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.PubCode.codeType" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="queryDtoCodeType" name="queryDto.codeType" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.PubCode.typeDesc" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="queryDtoCodeTypeDesc" name="queryDto.codeTypeDesc" type="text" class="ui-tianan-input-text">
									</td>
								</tr>
								<tr >
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.PubCode.codeCode" />							
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="queryDtoCodeCode" name="queryDto.codeCode" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.PubCode.codeCName" />	
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="queryDtoCodeCName" name="queryDto.codeCName" type="text" class="ui-tianan-input-text">
									</td>
								</tr>
								<tr >
								     <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.PubCode.codeEName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="queryDtoCodeEName" name="queryDto.codeEName" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%" class="inputTitle3col" >
									<s:text name="visaBase.PubCode.status" />							
									</td>
									<td width="38%" class="inputBox3col_text" colspan="3">										
										<select id="queryDtoStatus" name="queryDto.status"  class="selectStyle" style="width: 155px">
										</select>
									</td>										
								</tr>															
							</table>
						</td>
					</tr>
					<tr>
					  <td colspan="4">
					    &nbsp;
					  </td>
					</tr>
					<tr >
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="visaBase.list.PubCode" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">										
									<input type="button" class="taButton" id="queryPubCodeTypeBut"  value="<s:text name="common.submit.query" />"/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" class="taButton" value="<s:text name="common.submit.rest" />" onclick="common.clearGridData('pubCodeTypeGrid');" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="taButton" id="addPubCodeTypeBut" value="<s:text name="common.submit.add" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									 <input type="button" id="updatePubCodeTypeBut" value="<s:text name="common.submit.alter" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="taButton" id="viewPubCodeTypeBut" value="<s:text name="common.submit.view" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" id="deleteOrUnDeleteVcPubCodeTypesBut" value="<s:text name="visaBase.PubCode.deleteOrUndelete" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
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
								<table class="pagerTable">
									<tr>
										<td>
											<table id="pubCodeTypeGrid" class="inputTable3col" ></table>
											<div id="pubCodeTypeGridPager"></div>
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
    <%@ include file="PubCodeTypeEdit.jsp"%>    
    <%@ include file="PubCodeTypeView.jsp"%> 
</body>
</html>

