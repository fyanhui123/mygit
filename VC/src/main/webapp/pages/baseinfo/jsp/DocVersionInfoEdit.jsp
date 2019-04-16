<%
	/*
	 * 单证类型新增修改叶
	 ***************************************
	 * 程序名：DocVersionInfoEdit.jsp
	 * 建立日期：2013-06-14
	 * 作者：whj
	 * 模块：基础信息维护-单证类型新增修改
	 * 备注：
	 * -----------------------------------------
	 * 修改历史
	 * 序号 日期 修改人 修改原因
	 * 
	 ***************************************
	 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/DocVersionInfoEdit.js"></script>
<script type="text/javascript"> 
	$(document).ready(function () {
		docVersionInfoEdit.initEditPage();
		docVersionInfoEdit.alertErrorMsg("${hasError}","${resultMsg}");
	});		
</script>
</head>
<body>
  <div id="toAddDocVersionInfo" style="width: 100%">
	<table  style="width: 100%">
		<tr>
		  <td id="mainTableLeftTd_">
		   <form action="#" id="docVersionInfoForm"  method="post" enctype="multipart/form-data">
		        <input type="hidden" id="actionType" name="actionType" value="${actionType}">
		        <input type="hidden"  name=docVersionTypeFlag value="${docVersionTypeFlag}">
		        <input type="hidden"  id="docType" name="docType"  value="${docType}">
		        <input type="hidden"  id="currentIdVcDocVersionInfo" name="docVersionInfoDto.idVcDocVersionInfo" value="${docVersionInfoDto.idVcDocVersionInfo}">
				<input type="hidden" id="jsonMngDate"  name="jsonMngDate">				
				<table width="100%" id="mainTable" style="width: 100%">					
					<tr >
					  <td>
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
						  <tr>
						    <td class="f-black" height="28" valign="center">
							  <img src="${ctx}/style/default/images/point4.gif" class="bottom">
							  &nbsp;
                                <s:if test="actionType=='insert'"><s:text name="visaBase.title.docVersionInfoAdd" /></s:if>
								<s:else><s:text name="visaBase.title.docVersionInfoModify" /></s:else>
							</td>
						 </tr>
						 <tr>
						    <td class="pageDevide">&nbsp;</td>
					     </tr>
						   
						  </table>
					  </td>						
					</tr>
					<tr id="tre">
						<td>
						  <div style="padding-left: 4%;">							
							 <table width="100%">							   
							 	<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docVerCode" />										
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="docVersionInfoDtoDocVerCode" name="docVersionInfoDto.docVerCode" value="${docVersionInfoDto.docVerCode}" type="text" maxlength="50" class="required nochinese"  style="width: 200px"/>
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docVerName" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="docVersionInfoDtoDocVerName" name="docVersionInfoDto.docVerName" value="${docVersionInfoDto.docVerName}" type="text" maxlength="100" class="required"  style="width: 200px"/>
									</td>
								</tr>
								<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docCountCode" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									    <input type="hidden"  id="hide_docVersionInfoDtoDocCountCode" value="${docVersionInfoDto.docCountCode}">
									     <select  id="docVersionInfoDtoDocCountCode" name="docVersionInfoDto.docCountCode" class="selectStyle" style="width: 205px">
										</select>										
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docFilePath" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input name="file" type="file" accept="image/jpeg, image/png, image/x-png,image/pjpeg"  style="width: 205px"
									    <c:if test="${actionType == 'insert'}">class="required" </c:if>
									    />
										<c:if test="${actionType == 'update'}">
											<a style="color: blue"
												href="${ctx}/common/downloadById.do?id=${docVersionInfoDto.idVcDocVersionInfo }&type=docVersionInfo">查看</a>
										</c:if>									  									
									</td>
								</tr>
								<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docType" />									
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
									      <input type="hidden"  id="hide_docVersionInfoDtoIdVcDocTyp" value="${docVersionInfoDto.idVcDocType}">
									     <select id="docVersionInfoDtoIdVcDocTyp" name="docVersionInfoDto.idVcDocType" class="selectStyle required"  style="width: 205px">
										</select>										
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.value" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="docVersionInfoDtoValue" name="docVersionInfoDto.value" value="${docVersionInfoDto.value}" type="text" maxlength="14" class="number"  style="width: 200px"/>
									</td>
								</tr>
								<tr>							 	 						
									<td width="12%" class="inputTitle3col">
										<s:text name="belongOrg" />							
									</td>
									<td width="38%" class="inputBox3col_text" >
									  <input id="docVersionInfoDtoOrgCode" name="docVersionInfoDto.orgCode" value="${docVersionInfoDto.orgName}" type="text" disabled class="required"  style="width: 200px"/>
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="inputUserCode" />
									</td>
									<td width="38%" class="inputBox3col_text">
									   <input id="docVersionInfoDtoCreateUserCode" name="docVersionInfoDto.createdBy" value="${docVersionInfoDto.createdUser}" type="text" disabled class="required"  style="width: 200px"/>
									</td>
								</tr>
								<tr>
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.status" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input type="hidden"  id="hide_docVersionInfoDtoStatus" value="${docVersionInfoDto.status}">
										<select   id="docVersionInfoDtoStatus" name="docVersionInfoDto.status" disabled class="selectStyle" style="width: 205px">
										</select>
									</td>
								   <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.isAutoGenNo" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input type="hidden"  id="hide_docVersionInfoDtoIsAutoGenNo" value="${docVersionInfoDto.isAutoGenNo}">
										<select  id="docVersionInfoDtoIsAutoGenNo" name="docVersionInfoDto.isAutoGenNo" class="selectStyle required" style="width: 205px">
										</select>
									</td>
								</tr>
								<tr>
								   <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.isOrder" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input type="hidden"  id="hide_docVersionInfoDtoIsOrder" value="${docVersionInfoDto.isOrder}">
										<select  id="docVersionInfoDtoIsOrder" name="docVersionInfoDto.isOrder" class="selectStyle required" style="width: 205px">
										</select>
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
						 <table cellpadding="0" cellspacing="0" border="0" width="80%">
						   <tr>	
						     <td>					
							   <div id="docPrtNoRuleList"></div>
							  </td>
						    </tr>
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
							<table width="80%" border="0" cellSpacing="0" cellPadding="0" >
								<thead>
									<tr>									
										<th width="20%" class='mulLine-state-default'>机构级别</th>
										<th width="20%" class='mulLine-state-default'>描述</th>
										<th width="25%" class='mulLine-state-default'>库存上限</th>
										<th width="25%" class='mulLine-state-default'>最长库存时间（天）</th>
									</tr>
								</thead>
								<tbody >
									<s:iterator id="mngItem" value="docVersionInfoMngList">
										<tr>
											<td align="center" class='mulLine-state-default'><s:property value="#mngItem.levelNo"/></td>
											<td align="center" class='mulLine-state-default'><s:property value="#mngItem.levelRemark"/></td>
											<td class='mulLine-state-default'><input id=<s:property value="#mngItem.tagName1"/>  value=<s:property value="#mngItem.maxStore"/>   minNum="1"  maxNum=<s:property value="#mngItem.maxStoreC"/> type="text"  class="required"/></td>
											<td class='mulLine-state-default'><input id=<s:property value="#mngItem.tagName2"/>  value=<s:property value="#mngItem.maxStoreTime"/>  minNum="1"  maxNum=<s:property value="#mngItem.maxStoreTimeC"/> type="text"  class="required"/></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</td>
					</tr>					
					
					<tr>
						<td >
							&nbsp;
						</td>
					</tr>
					<tr>							
					    <td >
					    <div style="padding-left: 35%;" >
							&nbsp;
							<input type="button" class="taButton"  id="saveDocVersionInfoBut"  value="<s:text name="common.submit.save" />" />
							&nbsp;&nbsp;&nbsp;&nbsp;
							<input type="button" class="taButton" id="backToDocVersionInfoMainBut" value="<s:text name="common.submit.back" />" />
						</div>
						</td>
					</tr>
				</table>
			</form>
		</td>
	  </tr>
	</table>
  </div>
  <form action="#" id="backMainForm"  method="post" enctype="multipart/form-data">
  </form>
  </body>
</html>