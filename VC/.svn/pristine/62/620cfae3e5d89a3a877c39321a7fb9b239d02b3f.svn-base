<%
	/*
	 * 激活卡使用人发放查询页面
	 ***************************************
	 * 程序名：InsuCardRefundQuery.jsp
	 * 建立日期：2013-06-25
	 * 作者：whj
	 * 模块：激活卡管理-激活卡使用人发放查询
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
<head>
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/insucard/js/InsuCardRefundQuery.js"></script>
<script type="text/javascript" src="${ctx}/pages/insucard/js/InsuCardRefundView.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
     common.initHtml("InsuCardRefundQuery");
	 insuCardRefundQuery.initPage();
	 insuCardRefundQuery.saveSuccessAlert("${actionType}","${resultMsg}");
 }); 
</script>
</head>
<body>
	<div class="tab-center" id="insuCardSalesListPage">
		<table style="width: 100%">
			<tr>
				<td>
					<form action="#" id="queryForm" name="queryForm"  method="post" enctype="multipart/form-data">
					  <input type="hidden" id="actionType" name="actionType" value="${actionType }" />
					  <input type="hidden" id="oprType" name="queryDto.oprType" value="R" />
						<table id="mainTable" style="width: 100%">
							<tr>
							  <td height="33">
									<table cellpadding="0" cellspacing="0" border="0" width="100%">
										<tr>
											<td height="20" valign="top">
												<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;
												<s:text name="common.form.entryQuertyCondition" />
											</td>
										</tr>
										<tr>
											<td class="pageDevide" colspan="2"></td>
										</tr>								
									 </table>
								</td>
							</tr>
							<tr>
								<td>
									<table width="100%" class="inputTable3col">
										<tr>
											<td width="12%" class="inputTitle3col">
											  退卡记录号
												<%--s:text name="visaBase.docVersionInfo.docVerName" /--%>								
											</td>
											<td width="38%" class="inputBox3col_text" nowrap="nowrap">
												<input id="queryDtoSalesRecordCode" name="queryDto.salesRecordCode" type="text" class="ui-tianan-input-text">
											</td>
											<td width="12%" class="inputTitle3col">
												退卡记录状态							
											</td>
											<td width="38%" class="inputBox3col_text" nowrap="nowrap" >
											    <select  id="queryDtoSalesRecordStatus" name="queryDto.salesRecordStatus"  class="selectStyle" style="width: 155px">
												</select>
											</td>		
										</tr>
										<tr>
											<td width="12%" class="inputTitle3col">
											          退卡申请人
												<!--销售人代码-->
											</td>
											<td width="38%" class="inputBox3col_text">
												<input id="quetyDtoSellerCode"   name="queryDto.sellerCode" class="ui-autocomplete-input" type="text"/>
												<input id="quetyDtoSellerCodeName" type="text" class="ui-widget-content ui-autocomplete-label" />
											</td>
											<td width="12%" class="inputTitle3col">
												 退卡申请人机构
											</td>
											<td width="38%" class="inputBox3col_text">
												<input id="queryDtoSaleOrgCode"  name="queryDto.saleOrgCode" type="text" class="ui-autocomplete-input" />
												<input id="queryDtoSaleOrgCodeName" type="text" class="ui-widget-content ui-autocomplete-label" />
											</td>
										</tr>
										<tr>
											<td width="12%" class="inputTitle3col">
												单证类型
											</td>
											<td class="inputBox3col_text">
												<input id="queryDtoDocVerCode" name="queryDto.docVerCode"  class="ui-autocomplete-input" type="text" />
												<input id="queryDtoDocVerCodeName" type="text" class="ui-widget-content ui-autocomplete-label" />

											</td>
											<td width="12%" class="inputTitle3col">
												印刷批次
											</td>
											<td width="38%" class="inputBox3col_text">
												<input id="queryDtoPressBatchNo" name="queryDto.pressBatchNo" type="text" class="ui-tianan-input-text">
											</td>
										</tr>
										<tr>											
											<td width="12%" class="inputTitle3col">
												退卡日期
											</td>
											<td class="inputBox3col_text">
												<input type="text" id="queryDtoSaleDateFrom" name="queryDto.saleStartDate"
													value="<fmt:formatDate value="${queryDto.saleStartDate }" pattern="yyyy-MM-dd"/>"
													class="Wdate taDatePicker" onclick="WdatePicker()" />
												至
												<input type="text" id="queryDtoSaleDateTo" name="queryDto.saleEndDate"
													value="<fmt:formatDate value="${queryDto.saleEndDate }" pattern="yyyy-MM-dd"/>"
													class="Wdate taDatePicker" onclick="WdatePicker()" />
											</td>
										</tr>
										<tr>
											<td colspan="4">
												&nbsp;
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
							<tr>
								<td height="33">
									<table cellpadding="0" cellspacing="0" border="0" width="100%">
										<tr>
											<td height="28" valign="top">
												<img src="${ctx}/style/default/images/point4.gif"
													class="bottom">
												&nbsp;
												<!--使用人单证发放列表-->	
												<s:text name="store.docProvideList" />
											</td>
											<td align="right" valign="top" nowrap="nowrap"> 
											   <input id="queryInsuCardRefundBut" type="button" class="taButton" value="<s:text name="common.submit.query" />" />
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input type="reset" id="restBtn" class="taButton" value="<s:text name="common.submit.rest" /> "
													onclick="common.clearGridData('insuCardRefundGrid');" />
												&nbsp;&nbsp;&nbsp;&nbsp;
												<input id="addInsuCardRefundBut" class="taButton" type="button" value="<s:text name="common.submit.add" />" />
												 &nbsp;&nbsp;&nbsp;&nbsp;
												 <input id="editInsuCardRefundBut" class="taButton" type="button" value="<s:text name="common.submit.modify" />" />
												 &nbsp;&nbsp;&nbsp;&nbsp;
												 <input id="refundBut" class="taButton" type="button" value="<s:text name="common.submit.submit" />" />
												 &nbsp;&nbsp;&nbsp;&nbsp;
												 <input id="modifyAccountBut" class="taButton" type="button" value="<s:text name="common.submit.modifyBankAccount" />" />
												 &nbsp;&nbsp;&nbsp;&nbsp;
												 <input id="deleteInsuCardRefundBut" class="taButton" type="button" value='<s:text name="common.submit.delete"/>' />
												 &nbsp;&nbsp;&nbsp;&nbsp;
					                        </td>
										</tr>
										<tr>
											<td class="pageDevide" colspan="2"></td>
										</tr>
									</table>
								</td>
							</tr>
							<tr id="trc_Q">
								<td>
									<table class="pagerTable">
										<tr>
											<td>
												<table id="insuCardRefundGrid" class="inputTable3col"></table>
												<div id="insuCardRefundGridPager"></div>
												<input id="id" name="id" value="" type="hidden">
											</td>
										</tr>
									</table>
								</td>
							</tr>
							<tr>
								<td>
									&nbsp;
								</td>
							</tr>
						</table>
					</form>
				</td>
			</tr>
		</table>
	</div>
<%@ include file="InsuCardRefundView.jsp"%>
</body>
</html>