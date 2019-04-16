<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

  <div id="toAddPrinteryView">
	<table width="100%" style="width: 100%">
		<tr>
		  <td id="mainTableLeftTd_view">
		   <form  id="viewForm"  method="post">		       
		        <input type="hidden" name="printeryDto.idVcPrintery" value="${printeryDto.idVcPrintery}">		                
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
								&nbsp; <s:text name="visaBase.title.printeryView" />								
							 </td>
						     </tr>
						    <tr>
								<td class="pageDevide"></td>
							</tr>
						  </table>
					  </td>						
					</tr>
					<tr id="tre_view">
						<td> 
						  <div style="padding-left: 4%;">							
							 <table >							   
							 	<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.printeryCode" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="viewDtoPrinteryCode" name="printeryDto.printeryCode" value="${printeryDto.printeryCode}" type="text" maxlength="20"   disabled="disabled"/>
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.printeryName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="viewDtoPrinteryName" name="printeryDto.printeryName" value="${printeryDto.printeryName}" type="text" maxlength="20"  disabled="disabled"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
											<s:text name="visaBase.printery.businCode" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="viewDtoBusinCode" name="printeryDto.businCode" value="${printeryDto.businCode}" type="text" maxlength="50"   disabled="disabled"/>
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.corresponder" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="viewDtoCorresponder" name="printeryDto.corresponder" value="${printeryDto.corresponder}" type="text" maxlength="100"  disabled="disabled"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.address" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="viewDtoAddress" name="printeryDto.address" value="${printeryDto.address}" type="text" maxlength="200"  disabled="disabled" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.tel" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="viewDtoTel" name="printeryDto.tel" value="${printeryDto.tel}" type="text" maxlength="20"  class="phone"  disabled="disabled"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
											<s:text name="visaBase.printery.fax" />							
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="viewDtoFax" name="printeryDto.fax" value="${printeryDto.fax}" type="text" maxlength="20"    disabled="disabled"/>
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.email" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="viewDtoEmail" name="printeryDto.email" value="${printeryDto.email}" type="text" maxlength="50" class="email"  disabled="disabled"/>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.bankName" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="viewDtoBank" name="printeryDto.bank"  value="${printeryDto.bank}" type="text" maxlength="20"  disabled="disabled" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.bankAccountName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="viewDtoAccountName" name="printeryDto.accountName" value="${printeryDto.accountName}" type="text"maxlength="100"   disabled="disabled" />
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.bankAccountNo" />							
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="viewDtoAccountCode" name="printeryDto.accountCode"  value="${printeryDto.accountCode}" type="text" maxlength="50"   disabled="disabled" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.contractTimeLimit" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input id="viewDtoContractTimeLimit" name="printeryDto.contractTimeLimit" disabled="disabled" value="<fmt:formatDate value='${printeryDto.contractTimeLimit}' pattern='yyyy-MM-dd'/>"  type="text" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
									</td>
								</tr>
								<tr>								
								   <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.postCode" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input id="viewDtoPostCode" name="printeryDto.postCode"  value="${printeryDto.postCode}" type="text" maxlength="50"  disabled="disabled"  />
								    </td>
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.printery.file" />								
									</td>
									<td  class="inputBox3col_text" nowrap="nowrap" colspan=3>
									   <a style="color: blue"
												href="${ctx}/common/downloadById.do?id=${printeryDto.idVcPrintery }&type=printery">查看</a>
									</td>
								</tr>
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="createUserCode" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input  value="${printeryDto.createdUser}" type="text" disabled="disabled" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="createTime" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input  value="<fmt:formatDate value='${printeryDto.dateCreated}' pattern='yyyy-MM-dd HH:mm'/>" type="text" disabled="disabled" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
									</td>
								</tr>
								<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="modifyUserCode" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input  value="${printeryDto.updatedUser}" type="text" disabled="disabled"  />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="modifyTime" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input   value="<fmt:formatDate value='${printeryDto.dateUpdated}' pattern='yyyy-MM-dd HH:mm'/>"  type="text" disabled="disabled" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
									</td>
								</tr>	
								
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
								<tr>								
								    <td  colspan="4">
								      <div style="padding-left: 28%;" >
										&nbsp;
										<input type="button" id="fromViewBackToPrinteryMainBut" value="<s:text name="common.submit.back" />" />
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
								&nbsp;承印单证类型								
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
										<th width="25%"><s:text name="order.orderLaunchDet.verCode" /></th>
										<th width="35%"><s:text name="order.orderLaunchDet.verName" /></th>
										<th width="20%">单价</th>										
									</tr>
								</thead>
								<tbody>
									<s:iterator id="printDocVersionItem" value="docVersionInfoQueryVoList">
										<tr>
											<td align="center"><s:property value="#printDocVersionItem.docVerCode"/></td>
											<td align="center"><s:property value="#printDocVersionItem.docVerName"/></td>
											<td align="center"><s:property value="#printDocVersionItem.unitPrice"/></td>											
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
		printeryView.initViewPage();
	});
</script>
