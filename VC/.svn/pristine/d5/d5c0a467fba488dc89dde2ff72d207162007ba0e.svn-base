<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

  <div id="toAreaSelect">
	<table width="100%" style="width: 100%">
		<tr>
		  <td>
		 	    <input type="hidden" id="selectVcDocVersionInfoIds" name="selectVcDocVersionInfoIds" value="${selectVcDocVersionInfoIds}">  
				<table width="100%" id="areaRelMainTable" style="width: 100%">
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
								&nbsp; <s:text name="visaBase.docVersionInfo" />						
							 </td>
						     </tr>
						     <tr>
								<td class="pageDevide" ></td>
							</tr>
						  </table>
					  </td>						
					</tr>
					<tr>
						<td>
							<table width="100%" border="0">
								<thead>
									<tr>
										<th width="35%"><s:text name="visaBase.docVersionInfo.docVerCode" /></th>
										<th width="35%"><s:text name="visaBase.docVersionInfo.docVerName" /></th>
										<th width="30%"><s:text name="belongOrg" /></th>										
									</tr>
								</thead>
								<tbody>
									<s:iterator id="resultItem" value="resultList">
										<tr>
											<td align="center"><s:property value="#resultItem.docVerCode"/></td>
											<td align="center"><s:property value="#resultItem.docVerName"/></td>
											<td align="center"><s:property value="#resultItem.orgCode"/></td>											
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>
					
					<tr >
						<td >
							&nbsp;
						</td>
					</tr>
				
					<tr>
					  <td>
						<table  border="0" width="100%">
						  <tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;<s:text name="visaBase.title.relArea" />
							 </td>
						    </tr>
						    <tr>
								<td class="pageDevide"  colspan='2'></td>
							</tr>
						  </table>
					  </td>						
					</tr>
					<tr style="width: 100%">
						<td style="padding-left: 4%;">
						  <table>
							<tr>
							   <td class="inputTable3col">机构 </td>
							   <td class="inputBox3col_text" nowrap="nowrap" >	
						          <input id="relAreaOrgCode" name="relAreaOrgCode" class="ui-autocomplete-input" type="text" />
							      <input id="relAreaOrgCodeName"  type="text" readonly="readonly" />
							    </td>
						     </tr>
					      </table>
					   </td>
					</tr>
					
					<tr>								
					    <td style="padding-left: 40%;">
							&nbsp;
							<input type="button"  id="areaSelectconfirmBut"  value="确定" />
							&nbsp;&nbsp;							
							<input type="button" id="fromAreaBackBut" value="<s:text name="common.submit.back" />" />
						</td>
					</tr>
				</table>
		</td>
	  </tr>
	</table>
  </div> 
  <script type="text/javascript"> 
		docVersionInfo.initAreaSelectPage();
</script>

