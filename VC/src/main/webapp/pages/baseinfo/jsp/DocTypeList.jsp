<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/VcDocType.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/VcDocTypeView.js"></script>
<script type="text/javascript">  
	$(document).ready(function () {
	    common.initHtml("DocTypeList");
		vcDocType.initPage();		
	});	
</script>
</head>
<body>
  <div id="docTypeMain">
	<table  style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
			<form id="queryForm" action="#" method="post" enctype="multipart/form-data" target="_parent">
				<table id="mainTable" style="width: 100%">					
					<tr>
					   <td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="visaBase.title.docTypeQueryCondition" />
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
										<s:text name="visaBase.docType.docTypeCode" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="docTypeCode" name="docTypeCode" type="text" class="ui-tianan-input-text ">
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docType.docTypeName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="docTypeName" name="docTypeName" type="text" class="ui-tianan-input-text">
									</td>
								</tr>
								<tr >
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docType.docTypeType" />
									</td>
									<td width="38%" class="inputBox3col_text" >										
									   <select name="docType" id="docType"  class="selectStyle" style="width: 155px" >
									   </select>
									</td>
									
									<td width="12%" class="inputTitle3col" >
										<s:text name="baseInfo.status" />								
									</td>
									<td width="38%" class="inputBox3col_text" colspan="3">										
										<select id="status" name="status"  class="selectStyle" style="width: 155px" >
										</select>
								    </td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr >
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="visaBase.list.docType" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">									
		   							<input type="button" class="taButton" id="queryBut"  value="<s:text name="common.submit.query" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" class="taButton"  value="<s:text name="common.submit.rest" />" onclick="common.clearGridData('docTypeGrid');"/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="taButton" id="addDocTypeBut" value="<s:text name="common.submit.add" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" id="updateDocTypeBut" class="taButton" value="<s:text name="common.submit.alter" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" id="viewDocTypeBut"  class="taButton" value="<s:text name="common.submit.view" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;									
									<input type="button" id="deleteDocTypeBut" value="<s:text name="common.submit.deleteOrUnDelete" />" />
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
						  <tr >
							<td>
								<table class="pagerTable">
									<tr>
										<td>
											<table id="docTypeGrid" class="inputTable3col" ></table>
											<div id="docTypeGridPager"></div>
											<input id="selectIds" name="selectIds"  type="hidden">
											
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<!--<tr>
							<td colspan="4">
								&nbsp;
							</td>
						</tr>
						<tr >
							<td style="padding-left: 4%;" >
							    <input type="button" id="updateDocTypeBut" value="<s:text name="common.submit.alter" />" />
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="button" id="deleteDocTypeBut" value="<s:text name="common.submit.delete" />" />
							</td>
						</tr>
					--></table>
				   </td>
				  </tr>
					
				</table>
			  </form>
			</td>
		</tr>
	</table>
  </div>
    <%@ include file="DocTypeEdit.jsp"%>
    <%@ include file="DocTypeView.jsp"%>
</body>
</html>

