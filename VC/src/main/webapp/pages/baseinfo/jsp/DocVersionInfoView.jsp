<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

  <div id="toAddDocVersionInfoView">
	<table style="width: 100%">
		<tr>
		  <td id="mainTableLeftTd_View">
		   <form  id="viewForm"  method="post">		      
		        <input type="hidden"  name=docVersionTypeFlag value="${docVersionTypeFlag}">	
		        <input type="hidden"  id="viewDocType" name="docType"  value="${docType}">	       
		        <input type="hidden" name="docVersionInfoDto.idVcDocVersionInfo" value="${docVersionInfoDto.idVcDocVersionInfo}">
				<table  style="width: 100%">					
					<tr >
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
						    <td class="f-black" height="28" valign="center">
							  <img src="${ctx}/style/default/images/point4.gif" class="bottom">
							  &nbsp;
								<s:text name="visaBase.title.docVersionInfoView" />
							</td>
						 </tr>
						 <tr>
						    <td class="pageDevide" colspan="2">&nbsp;</td>
					     </tr>
						   
						  </table>
					  </td>						
					</tr>
					<tr>
						<td>
						 <div style="padding-left: 4%;">							
							 <table >							   
							 	<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docVerCode" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="viewDtoDocVerCode" name="docVersionInfoDto.docVerCode" value="${docVersionInfoDto.docVerCode}" type="text" maxlength="50"  disabled/>
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docVerName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="viewDtoDocVerName" name="docVersionInfoDto.docVerName" value="${docVersionInfoDto.docVerName}" type="text" maxlength="100"  disabled/>
									</td>
								</tr>
								<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docCountCode" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									    <input type="hidden"  id="hide_viewDtoDocCountCode" value="${docVersionInfoDto.docCountCode}">
									     <select style="width: 100px" id="viewDtoDocCountCode" name="docVersionInfoDto.docCountCode" class="selectStyle" style="width: 155px" disabled>
										</select>										
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docFilePath" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <a style="color: blue"
												href="${ctx}/common/downloadById.do?id=${docVersionInfoDto.idVcDocVersionInfo }&type=docVersionInfo">查看</a>									  									
									</td>
								</tr>
								<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docType" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									      <input type="hidden"  id="hide_viewDtoIdVcDocTyp" value="${docVersionInfoDto.idVcDocType}">
									     <select style="width: 100px" id="viewDtoIdVcDocTyp" name="docVersionInfoDto.idVcDocType" class="selectStyle" style="width: 155px" disabled>
										</select>										
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.value" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="viewDtoValue" name="docVersionInfoDto.value" value="${docVersionInfoDto.value}" type="text" maxlength="14" class="number" disabled/>
									</td>
								</tr>
								<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="belongOrg" />							
									</td>
									<td width="38%" class="inputBox3col_text" >
									<input id="viewDtoOrgCode" name="docVersionInfoDto.orgCode" value="${docVersionInfoDto.orgName}" type="text" disabled />
									     										
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="inputUserCode" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input id="viewDtoCreateUserCode" name="docVersionInfoDto.createUserCode" value="${docVersionInfoDto.createdUser}" type="text" disabled />
									</td>
								</tr>
								<tr>
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.status" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input type="hidden"  id="hide_viewDtoStatus" value="${docVersionInfoDto.status}">
										<select style="width: 100px"  id="viewDtoStatus" name="docVersionInfoDto.status" class="selectStyle" style="width: 155px" disabled>
										</select>
									</td>
								   <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.isAutoGenNo" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input type="hidden"  id="hide_viewDtoIsAutoGenNo" value="${docVersionInfoDto.isAutoGenNo}">
										<select style="width: 100px"  id="viewDtoIsAutoGenNo" name="docVersionInfoDto.isAutoGenNo"  class="selectStyle" style="width: 155px" disabled>
										</select>
									</td>
								</tr>
								<tr>
								   <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.isOrder" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input type="hidden"  id="hide_viewDtoIsOrder" value="${docVersionInfoDto.isOrder}">
										<select style="width: 100px"  id="viewDtoIsOrder" name="docVersionInfoDto.isOrder"  class="selectStyle" style="width: 155px" disabled>
										</select>
									</td>
								</tr>											
								
							
								<tr >							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="createUserCode" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input  value="${docVersionInfoDto.createdUser}" type="text" disabled="disabled" />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="createTime" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input   value="<fmt:formatDate value='${docVersionInfoDto.dateCreated}' pattern='yyyy-MM-dd HH:mm'/>"  type="text" disabled="disabled" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
									</td>
								</tr>
								<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="modifyUserCode" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input  value="${docVersionInfoDto.updatedUser}" type="text" disabled="disabled"  />
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="modifyTime" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input  value="<fmt:formatDate value='${docVersionInfoDto.dateUpdated}' pattern='yyyy-MM-dd HH:mm'/>"  type="text" disabled="disabled" class="Wdate" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm'})"/>
									</td>
								</tr>
							</table>
							</div>
						</td>
					</tr>
					
					
					<tr>
						<td height="20px">
							&nbsp;
						</td>
					</tr>
					<tr>
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp; 单证印刷流水号规则									
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
										<th width="25%">元素顺序</th>
										<th width="35%">元素项</th>
										<th width="25%">元素值</th>
										<th width="25%">元素长度</th>
																			
									</tr>
								</thead>
								<tbody>
									<s:iterator id="viewDocPrtNoRuleItem" value="docPrtNoRuleList">
										<tr>
											<td align="center"><s:property value="#viewDocPrtNoRuleItem.sortNum"/></td>
											<td align="center"><s:property value="#viewDocPrtNoRuleItem.itemTypeCode"/></td>
											<td align="center"><s:property value="#viewDocPrtNoRuleItem.itemValue"/></td>	
											<td align="center"><s:property value="#viewDocPrtNoRuleItem.itemLength"/></td>										
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
					
					<tr>
						<td height="20px">
							&nbsp;
						</td>
					</tr>
					<tr>
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp; 单证险种关联信息									
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
										<th width="25%">险种代码</th>
										<th width="35%">险种名称</th>
										<th width="25%">所属险类</th>	
									</tr>
								</thead>
								<tbody>
									<s:iterator id="viewrelInsuKindItem" value="docInsuKindVoList">
										<tr>
											<td align="center"><s:property value="#viewrelInsuKindItem.insuKindCode"/></td>
											<td align="center"><s:property value="#viewrelInsuKindItem.insuKindName"/></td>
											<td align="center"><s:property value="#viewrelInsuKindItem.insuTypeName"/></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>	
					
					<tr>
						<td height="20px">
							&nbsp;
						</td>
					</tr>
					<tr>
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp; 单证地区关联信息									
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
										<th width="25%">机构代码</th>
										<th width="35%">机构名称</th>											
									</tr>
								</thead>
								<tbody>
									<s:iterator id="viewVcLevelListItem" value="vcLevelList">
										<tr>
											<td align="center"><s:property value="#viewVcLevelListItem.unitCode"/></td>
											<td align="center"><s:property value="#viewVcLevelListItem.unitName"/></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
					
					
					<tr>
						<td height="20px">
							&nbsp;
						</td>
					</tr>
					<tr>
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp; 单证管控规则									
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
							<table width="100%" border="0" cellspacing="0" >
								<thead>
									<tr>									
										<th width="20%">机构级别</th>
										<th width="20%">描述</th>
										<th width="25%">库存上限</th>
										<th width="25%">最长库存时间（天）</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator id="mngItem" value="docVersionInfoMngList">
										<tr>
											<td align="center"><s:property value="#mngItem.levelNo"/></td>
											<td align="center"><s:property value="#mngItem.levelRemark"/></td>											
											<td align="center"><s:property value="#mngItem.maxStore"/></td>
											<td align="center"><s:property value="#mngItem.maxStoreTime"/></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>													
					
					<tr>
						<td>
							&nbsp;
						</td>
					</tr>
					<tr>								
					    <td>
					     <div style="padding-left: 45%;">
							&nbsp; 
							<input type="button" class="taButton" id="fromViewBackToDocVersionInfoMainBut" value="<s:text name="common.submit.back" />" />
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
		docVersionInfoView.initViewPage();
	});		
</script>


