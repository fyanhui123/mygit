<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/VcDocType.js"></script>
  <div id="toAddDocType" style="width: 100%">
	<table style="width: 100%">
		<tr>
		  <td>
		   <form action="/visa/baseinfo/saveDocType.do?ajax=true&" id="docTypeForm"  method="post">
		     <input type="hidden" id="actionType" name="actionType" value="${actionType}">
		     <input type="hidden"  name="vcDocTypeDto.idVcDocType" value="${vcDocTypeDto.idVcDocType}">
			   <table width="100%" id="mainTable" style="width: 100%">				     
				    <tr onclick="common.hideTR('tra');">
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<s:if test="actionType=='insert'"><s:text name="visaBase.title.docTypeAdd" /></s:if>
								<s:elseif test="actionType=='update'"><s:text name="visaBase.title.docTypeModify" /></s:elseif>
								<s:else><s:text name="visaBase.title.docTypeView" /></s:else>									
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
						 <div style="padding-left: 4%;">						
							 <table class="inputTable3col">							   
							 	<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docType.docTypeCode" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="vcDocTypeDtoDocTypeCode" name="vcDocTypeDto.docTypeCode" value="${vcDocTypeDto.docTypeCode}" type="text" maxlength="20" class="required nochinese noBlankSpace" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docType.docTypeName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="vcDocTypeDtoDocTypeName" name="vcDocTypeDto.docTypeName" value="${vcDocTypeDto.docTypeName}" type="text" maxlength="20" class="required noBlankSpace"/>
									</td>
								</tr>
								<tr >
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docType.docTypeType" />
									</td>
									<td width="38%" class="inputBox3col_text" colspan="3">	
									    <input type="hidden" id="hide_vcDocTypeDtoDocType"  value="${vcDocTypeDto.docType}">									
										<select name="vcDocTypeDto.docType" id="vcDocTypeDtoDocType" value="${vcDocTypeDto.docType}" class="selectStyle required" style="width: 155px">
									   </select>
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
								<tr>								
								    <td  colspan="4">
								      <div style="padding-left: 35%;">										
										<input type="button"  id="saveDocTypeBut" value="<s:text name="common.submit.save" />" />
										&nbsp;&nbsp;
										<input type="button" id="backToDocTypeMainBut" value="<s:text name="common.submit.back" />" />
									  </div>
									</td>
								</tr>
							</table>
					      </div>	
						</td>
					</tr>
				</table>
			</form>
		</td>
	  </tr>
	</table>
  </div> 
  <script type="text/javascript"> 
		vcDocType.initEditPage();
</script>

