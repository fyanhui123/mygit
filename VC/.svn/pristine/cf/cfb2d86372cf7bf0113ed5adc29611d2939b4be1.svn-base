<%
	/*
	 * 激活卡导入查询主页面
	 ***************************************
	 * 程序名：InsuranceCardQuery.jsp
	 * 建立日期：2013-06-14
	 * 作者：whj
	 * 模块：激活卡管理-激活卡导入
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
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/insucard/js/InsuranceCardQuery.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
     common.initHtml("InsuranceCardQuery");
	 insuranceCardQuery.initPage();
 }); 
</script>
</head>
<body>
<div class="tab-center">
	<table style="width: 100%">
		<tr>
			<td>
			<form action="#" id="queryForm" name="queryForm"  method="post" enctype="multipart/form-data">
				<table style="width: 100%">
					<tr >
						<td>						
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="store.insucard.title.cardImport" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr >
						<td>
							  <table class="inputTable3col" style="width: 100%">													
								<tr id="relInsuKindCondition">
									<td width="12%" class="inputTitle3col" >
										<s:text name="vcdoc.insuKind.insuType"></s:text>
									</td>
									<td width="25%" class="inputBox3col">
										<select id="queryDtoInsuTypeList" name="queryDto.insuTypeCode" class="selectStyle" style="width: 155px">
										</select>											
									</td>	
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.insuKind" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
									   <select  id="queryDtoInsuKindList" name="queryDto.insuKind"  class="selectStyle" style="width: 155px">
										</select>
									</td>								
								</tr>
								<tr >
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docVerCode" />
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="queryDtoDocVerCode" name="queryDto.docVerCode" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docVerName" />								
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="queryDtoDocVerName" name="queryDto.docVerName" type="text" class="ui-tianan-input-text">
									</td>									
								</tr>	
														
								<tr >
								    <td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.area" />
									</td>
									<td width="38%" class="inputBox3col_text">
									    <input id="queryDtoArea" class="ui-autocomplete-input"  name="queryDto.orgCode" type="text" />
             							<input id="queryDtoAreaName" style="width: 100px;" value="${codeLabel}" type="text" readonly="readonly" />
									</td>					
								</tr>								
							</table>
						</td>
					</tr>
					<tr>
					  <td >
					    &nbsp;
					  </td>
					</tr>
					<tr >
						<td height="33">						
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td class="f-black" height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="visaBase.list.docVersionInfo" />
								</td>
								<td  align="right" valign="top" nowrap="nowrap">								   
									<input type="button"  class="taButton" id="queryInsuranceCardBut"  value="<s:text name="common.submit.query" />"/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset"  class="taButton" id="restBut" value="<s:text name="common.submit.rest" />" onclick="common.clearGridData('insuranceCardGrid');" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button"  class="taButton" id="importInsuranceCardBut" value="<s:text name="store.insucard.buttion.cardBatchImport" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button"  class="taButton" id="viewBut" value="<s:text name="store.insucard.buttion.viewCardStorage" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
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
					    <table class="pagerTable">
					      <tr>					
							<td>
								<table class="pagerTable">
									<tr>
										<td>
											<table id="insuranceCardGrid" class="inputTable3col" ></table>
											<div id="insuranceCardGridPager"></div>
											<input id="selectIds" name="selectIds"  type="hidden">											
										</td>
									</tr>
								</table>
							  </td>
							</tr>
						</table>
					  </td>
					</tr>
				</table>
				</form>
			</td>
		</tr>
	</table>
</div>
 <%--@ include file="InsuranceCardImport.jsp"--%>
</body>
</html>

