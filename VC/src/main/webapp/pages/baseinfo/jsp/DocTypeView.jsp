<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
  <div id="toAddDocTypeView">
	<table style="width: 100%">
		<tr>
		  <td id="mainTableLeftTd_view">
		   <form action="/visa/baseinfo/saveDocType.do?ajax=true&" id="viewForm"  method="post">		    
		     <input type="hidden"  name="vcDocTypeDto.idVcDocType" value="${vcDocTypeDto.idVcDocType}">
			   <table style="width: 100%">				     
				    <tr >
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp; <s:text name="visaBase.title.docTypeView" />									
							</td>
						   </tr>
						   <tr>
								<td class="pageDevide"></td>
							</tr>
						</table>
					   </td>
					</tr>
					<tr id="tra_view">
						<td>
							<div style="padding-left: 4%;">
							 <table class="inputTable3col">
							   
							 	<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docType.docTypeCode" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="viewDtoDocTypeCode" name="vcDocTypeDto.docTypeCode" value="${vcDocTypeDto.docTypeCode}" type="text" maxlength="20"  />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docType.docTypeName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="viewDtoDocTypeName" name="vcDocTypeDto.docTypeName" value="${vcDocTypeDto.docTypeName}" type="text" maxlength="20" />
									</td>
								</tr>
								<tr >
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docType.docTypeType" />
									</td>
									<td width="38%" class="inputBox3col_text" colspan="3">	
									    <input type="hidden" id="hide_viewDtoDocType"  value="${vcDocTypeDto.docType}">									
										<select name="vcDocTypeDto.docType" id="viewDtoDocType" class="selectStyle" style="width: 155px">
									   </select>
									</td>
									
								</tr>
								
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="createUserCode" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input  value="${vcDocTypeDto.createdUser}" type="text" disabled="disabled" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="createTime" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input  value="<fmt:formatDate value='${vcDocTypeDto.dateCreated}' pattern='yyyy-MM-dd HH:mm'/>"  type="text" disabled="disabled" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="modifyUserCode" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input  value="${vcDocTypeDto.updatedUser}" type="text" disabled="disabled"  />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="modifyTime" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input  value="<fmt:formatDate value='${vcDocTypeDto.dateUpdated}' pattern='yyyy-MM-dd HH:mm'/>"  type="text" disabled="disabled" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
									</td>
								</tr>	
								 
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
								<tr>								
								    <td  colspan="4">
								     <div style="padding-left: 45%;">
										&nbsp;
										<input type="button" id="fromViewBackToDocTypeMainBut" value="<s:text name="common.submit.back" />" />
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
		vcDocTypeView.initViewPage();
</script>

