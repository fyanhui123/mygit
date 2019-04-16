<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

  <div id="toInsuKindSelect">
	<table width="100%" style="width: 100%">
		<tr>
		  <td id="mainTableLeftTd_">
		 	    <input type="hidden" id="selectVcDocVersionInfoIds" name="selectVcDocVersionInfoIds" value="${selectVcDocVersionInfoIds}">  
				<table width="100%" id="mainTable" style="width: 100%">
					<tr>
						<td height="40px">
							&nbsp;
						</td>
					</tr>
					
					<tr>
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;<s:text name="visaBase.docVersionInfo" />							
							 </td>
						     </tr>
						     <tr>
								<td class="pageDevide"></td>
							</tr>
						  </table>
					  </td>						
					</tr>
					<tr id="trk_1">
						<td>
							<table width="100%" border="0">
								<thead>
									<tr>
										<th width="25%"><s:text name="visaBase.docVersionInfo.docVerCode" /></th>
										<th width="35%"><s:text name="visaBase.docVersionInfo.docVerName" /></th>
										<th width="20%"><s:text name="belongOrg" /></th>										
									</tr>
								</thead>
								<tbody>
									<s:iterator id="resultItem1" value="resultList">
										<tr>
											<td align="center"><s:property value="#resultItem1.docVerCode"/></td>
											<td align="center"><s:property value="#resultItem1.docVerName"/></td>
											<td align="center"><s:property value="#resultItem1.orgCode"/></td>											
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
					
					<tr >
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					
					
					<%--star根据下拉框的值动态修改 --%>
				<%--
				  <tr onclick="common.hideTR('trk_2');">
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;单证归属险种选择		
							 </td>
						    </tr>
						    <tr>
								<td height="13" background="${ctx}/style/default/images/devide-main.gif"></td>
								<td height="13" width="13" background="${ctx}/style/default/images/devide-r.gif"></td>
						   </tr>
						 </table>
					  </td>						
					</tr>
					 
					<tr id="trk_2">
						<td>							
							 <table >							   
							 	<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="vcdoc.insuKind.insuType" />
									</td>
									<td width="25%" class="inputBox3col" colspan="3">
										<select style="width: 100px" id="dialogInsuTypeSelect" name="dialogInsuTypeSelect">
										</select>											
									</td>
								</tr>
							</table>					
						</td>
					</tr>
					--%>
					<tr>
					  <td>
						<table  border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;险种列表
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
						<table  border="0" width="100%">
						   <tr>							 	 						
								<td width="12%" >
									<s:text name="vcdoc.insuKind.insuType" />
								</td>
								<td class="inputBox3col_text" colspan="3">
									<select style="width: 100px" id="dialogInsuTypeSelect" name="dialogInsuTypeSelect">
									</select>											
								</td>
							</tr>
						</table>
					 </td>
				    </tr>
					
					<tr  id="trk_4">
				      <td>					    
							<table class="pagerTable">
								<tr>
									<td>
										<table id="refInsuKindGrid" class="inputTable3col" ></table>
										<%-- <div id="refInsuKindGridPager"></div>--%>
										<input id="selectRelInsuKindCodes" name="selectRelInsuKindCodes"  type="hidden">
										
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<%-- 
					<tr  id="trk_4">
					    <td>
							<table class="pagerTable">								
							    <s:iterator  id="item" status="status"  value="resultList">
										    <s:if test="#status.modules(4)==0"><tr></s:if>
											<td width="20%"> 
								                  <input type="checkbox" id="mm" name="mm" 
								                  value="<s:property value="#item.docVerCode"/>" checked ><s:property value="#item.docVerName"/></input>
							                  
							               </td> 
							               <s:if test="#status.modules(4)==0"></tr></s:if>
							      </s:iterator>
							</table>
						  </td>
					</tr>
					--%>
					<%--end根据下拉框的值动态修改 --%>				
					<%-- 
					<tr onclick="common.hideTR('trk_3');">
					  <td>
						<table  border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;<s:text name="visaBase.title.relInsuKind" />
							 </td>
						    </tr>
						    <tr>
								<td height="13" background="${ctx}/style/default/images/devide-main.gif"></td>
								<td height="13" width="13" background="${ctx}/style/default/images/devide-r.gif"></td>
						   </tr>
						  </table>
					  </td>						
					</tr>
					<tr  id="trk_3">
					    <td width="100%">							
							 <span id="addInsuKindList"></span>
						</td>
						<td colspan="4">
							&nbsp;
						</td>
					</tr>
					--%>
					
					<tr>					   
						<td colspan="4">
							&nbsp; 
						</td>
					</tr>
					<tr>								
					    <td style="padding-left: 28%;"  colspan="4">
							&nbsp;							
							<input type="button"  class="taButton"  id="insuKindSelectconfirmBut"  value="<s:text name="common.submit.confirm" />" />
							&nbsp;&nbsp;&nbsp;&nbsp;							
							<input type="button" class="taButton"  id="backToDocVersionInfoAddBut" value="<s:text name="common.submit.back" />" />
						</td>
					</tr>
				</table>
		</td>
	  </tr>
	</table>
  </div> 
<script type="text/javascript"> 
  	$(document).ready(function () {
		docVersionInfo.initInsuKindSelectPage();
	});
</script>
