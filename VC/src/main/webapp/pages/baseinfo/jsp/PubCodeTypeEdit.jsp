<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

  <div id="toAddPubCodeType">
	<table width="100%" style="width: 100%">
		<tr>
		  <td id="mainTableLeftTd_">
		   <form  id="pubCodeTypeForm"  method="post">
		        <input type="hidden" id="actionType" name="actionType" value="${actionType}">
		        <input type="hidden" id="currentIdVcPubCodeType" name="vcPubCodeType.idVcPubCodeType" value="${vcPubCodeType.idVcPubCodeType}">		                
				<table width="100%" id="mainTable" style="width: 100%">
					<tr>
						<td height="40px">
							&nbsp;
						</td>
					</tr>
					<tr >
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<s:if test="actionType=='insert'"><s:text name="visaBase.title.PubCodeAdd" /></s:if>
								<s:elseif test="actionType=='update'"><s:text name="visaBase.title.PubCodeModify" /></s:elseif>
								<s:else><s:text name="visaBase.title.PubCodeView" /></s:else>									
							 </td>
						     </tr>
						    <tr>
								<td class="pageDevide"></td>
							</tr>
						  </table>
					  </td>						
					</tr>
					<tr id="tre">
						<td>							
							 <table >							   
							 	<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.PubCode.codeType" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
										<input id="vcPubCodeTypeCodeType" name="vcPubCodeType.codeType" value="${vcPubCodeType.codeType}" type="text" maxlength="20" class="required noBlankSpace" />
									</td>
								</tr>
								<tr>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.PubCode.typeDesc" />
									</td>
									<td width="38%" class="inputBox3col_text" colspan="3">
									  <textarea  name="vcPubCodeType.codeTypeDesc"  rows="5" style="width: 90%"  class="required"  maxlength="50">${vcPubCodeType.codeTypeDesc}</textarea> 									
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
								<tr>								
								    <td style="padding-left: 28%;"  colspan="4">
										&nbsp;
										<s:if test="actionType!='view'">
										<input type="button"  id="savePubCodeTypeBut"  value="<s:text name="common.submit.save" />" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										</s:if>
										<input type="button" id="backToPubCodeTypeMainBut" value="<s:text name="common.submit.back" />" />
									</td>
								</tr>
							</table>
					
						</td>
					</tr>
					
					
					<tr>
						<td height="40px">
							&nbsp;
						</td>
					</tr>
					<tr >
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp; <s:text name="visaBase.PubCode.pubCode" />
							 </td>
						     </tr>
						     <tr>
								<td class="pageDevide"></td>
							 </tr>
						  </table>
					  </td>						
					</tr>
					<!--<tr id="trh">					 
						<td style="width: 98%">
						      <div id="pubCodeListDiv">
						        <span id="pubCodeList"></span>
						      </div>
						</td>
					</tr>
					-->
					<tr id="trh">
						<td style="width: 98%">
						      <div id="pubCodeListDiv">
						        <span id="pubCodeList"></span>
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
   $(document).ready(function () {
		pubCodeType.initEditPage();
	});
</script>
