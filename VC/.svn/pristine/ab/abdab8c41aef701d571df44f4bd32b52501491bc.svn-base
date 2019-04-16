<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/Printery.js"></script>
<script type="text/javascript">
   $(document).ready(function () {
	   common.initHtml("PrinteryEdit");
		printery.initEditPage();
		printery.alertErrorMsg("${hasError}","${resultMsg}");	
	});
</script>
</head>
<body>
  <div id="toAddPrintery">
	<table width="100%" style="width: 100%">
		<tr>
		  <td id="mainTableLeftTd_">
		   <form action="" id="printeryForm" name="printeryForm" target="_self" method="post" enctype="multipart/form-data">
		        <input type="hidden" id="actionType" name="actionType" value="${actionType}">
		        <input type="hidden" id="currentIdVcPrintery" name="printeryDto.idVcPrintery" value="${printeryDto.idVcPrintery}">		                
				<table width="100%" id="mainTable" style="width: 100%">
					
					<tr >
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<s:if test="actionType=='insert'"><s:text name="visaBase.title.printeryAdd" /></s:if>
								<s:elseif test="actionType=='update'"><s:text name="visaBase.title.printeryModify" /></s:elseif>
								<s:else><s:text name="visaBase.title.printeryView" /></s:else>									
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
						  <div style="padding-left: 4%;" >									
							 <table >							   
							 	<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.printeryCode" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="printeryDtoPrinteryCode" name="printeryDto.printeryCode" value="${printeryDto.printeryCode}" type="text" maxlength="20" class="required nochinese" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.printeryName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="printeryDtoPrinteryName" name="printeryDto.printeryName" value="${printeryDto.printeryName}" type="text" maxlength="100" class="required"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
											<s:text name="visaBase.printery.businCode" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="printeryDtoBusinCode" name="printeryDto.businCode" value="${printeryDto.businCode}" type="text" maxlength="50" class="required" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.corresponder" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="printeryDtoCorresponder" name="printeryDto.corresponder" value="${printeryDto.corresponder}" type="text" maxlength="100" class="required"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.address" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="printeryDtoAddress" name="printeryDto.address" value="${printeryDto.address}" type="text" maxlength="200" class="required" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.tel" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="printeryDtoTel" name="printeryDto.tel" value="${printeryDto.tel}" type="text" maxlength="20"  class="required phone"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
											<s:text name="visaBase.printery.fax" />							
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="printeryDtoFax" name="printeryDto.fax" value="${printeryDto.fax}" type="text" maxlength="20"  class="required" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.email" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="printeryDtoEmail" name="printeryDto.email" value="${printeryDto.email}" type="text" maxlength="50" class="required email"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.bankName" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="printeryDtoBank" name="printeryDto.bank"  value="${printeryDto.bank}" type="text" maxlength="20" class="required" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.bankAccountName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="printeryDtoAccountName" name="printeryDto.accountName" value="${printeryDto.accountName}" type="text"maxlength="100"   class="required"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.bankAccountNo" />							
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="printeryDtoAccountCode" name="printeryDto.accountCode"  value="${printeryDto.accountCode}" type="text" maxlength="50"  class="required" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.contractTimeLimit" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input id="printeryDtoContractTimeLimit" name="printeryDto.contractTimeLimit" value="<fmt:formatDate value='${printeryDto.contractTimeLimit}' pattern='yyyy-MM-dd'/>" type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd', minDate:new Date()})"/>
									</td>
								</tr>
								<tr>								    
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.postCode" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input id="printeryDtoPostCode" name="printeryDto.postCode"  value="${printeryDto.postCode}" type="text" maxlength="6"  class="required digits" />
								    </td>
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.file" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">									  
										<input name="file" type="file" accept="image/jpeg, image/png, image/x-png,image/pjpeg"/>
										<c:if test="${actionType == 'update'}">
											<a style="color: blue"
												href="${ctx}/common/downloadById.do?id=${printeryDto.idVcPrintery }&type=printery">查看</a>
										</c:if>
									</td>
								</tr>								
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
								<tr>								
								    <td  colspan="4">
									 <div style="padding-left: 32%;" >									
										<input type="button"  id="savePrinteryBut"  value="<s:text name="common.submit.save" />" />
										&nbsp;&nbsp;&nbsp;&nbsp;										
										<input type="button" id="backToPrinteryMainBut" value="<s:text name="common.submit.back" />" />
									 </div>
									</td>
								</tr>
							</table>
					     </div>
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
								&nbsp;
								<s:if test="actionType=='insert'">承印单证类型新增</s:if>
								<s:elseif test="actionType=='update'">承印单证类型</s:elseif>
								<s:else>承印单证类型</s:else>									
							 </td>
						     </tr>
						     <tr>
								<td class="pageDevide"></td>
							 </tr>
						  </table>
					  </td>						
					</tr>
					<tr id="trh">
					  <td style="width: 98%">
					    <table cellpadding="0" cellspacing="0" border="0" width="60%">
					      <tr>
					        <td>
						      <div id="printDocVersionListDiv">
						        <span id="printDocVersionList"></span>
						      </div>
						    </td>
						  </tr>
						 </table>
					  </td>
					</tr>
					<!--<tr id="trh">
					  <s:if test="actionType!='view'">
						<td style="width: 98%">
						      <div id="printDocVersionListDiv">
						        <span id="printDocVersionList"></span>
						      </div>							
							<%--  <span id="printDocVersionList"></span>--%>
						</td>
					 </s:if>
					 <s:else>
						<td>
							<table width="100%" border="0">
								<thead>
									<tr>
										<th width="25%"><s:text name="order.orderLaunchDet.verCode" /></th>
										<th width="35%"><s:text name="order.orderLaunchDet.verName" /></th>
										<th width="20%">单价</th>										
									</tr>
								</thead>
								<tbody>
									<s:iterator id="printDocVersionItem" value="printDocVersionList">
										<tr>
											<td align="center"><s:property value="#printDocVersionItem.docVerCode"/></td>
											<td align="center"><s:property value="#printDocVersionItem.docName"/></td>
											<td align="center"><s:property value="#printDocVersionItem.unitPrice"/></td>											
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					  </s:else>
					</tr>
				-->
				</table>
			</form>
			
		</td>
	  </tr>
	</table>
	<form action="" id="backMainForm" target="_self" method="post" enctype="multipart/form-data">
	</form>
  </div> 
  </body>
  </html>