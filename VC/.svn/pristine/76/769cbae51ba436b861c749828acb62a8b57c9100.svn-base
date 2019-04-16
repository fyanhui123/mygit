<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

  <div id="toViewPubCodeType">
	<table width="100%" style="width: 100%">
		<tr>
		  <td id="mainTableLeftTd_view">
		   <form  id="viewForm"  method="post">
		        <input type="hidden" name="actionType" value="${actionType}">
		        <input type="hidden" name="vcPubCodeType.idVcPubCodeType" value="${vcPubCodeType.idVcPubCodeType}">		                
				<table width="100%" id="viewTable" style="width: 100%">
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
								&nbsp;<s:text name="visaBase.title.PubCodeView" />								
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
							 <table >							   
							 	<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.PubCode.codeType" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
										<input  name="vcPubCodeType.codeType" value="${vcPubCodeType.codeType}" type="text" maxlength="20" disabled />
									</td>
								</tr>
								<tr>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.PubCode.typeDesc" />
									</td>
									<td width="38%" class="inputBox3col_text" colspan="3">
									  <textarea  name="vcPubCodeType.codeTypeDesc"  rows="5" style="width: 90%" disabled>${vcPubCodeType.codeTypeDesc}</textarea> 									
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
										<input type="button" id="fromViewBackToPubCodeTypeMainBut" value="<s:text name="common.submit.back" />" />
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
					
					<tr>
						<td>
							<table width="100%" border="0">
								<thead>
									<tr>
										<th width="25%"><s:text name="visaBase.PubCode.codeCode" /></th>
										<th width="35%"><s:text name="visaBase.PubCode.codeCName" /></th>
										<th width="20%"><s:text name="visaBase.PubCode.codeEName" /></th>										
									</tr>
								</thead>
								<tbody>
									<s:iterator id="vcPubCodeItem" value="vcPubCodeList">
										<tr>
											<td align="center"><s:property value="#vcPubCodeItem.codeCode"/></td>
											<td align="center"><s:property value="#vcPubCodeItem.codeCName"/></td>
											<td align="center"><s:property value="#vcPubCodeItem.codeEName"/></td>											
										</tr>
									</s:iterator>
								</tbody>
							</table>
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
		pubCodeTypeView.initViewPage();
	});
</script>
