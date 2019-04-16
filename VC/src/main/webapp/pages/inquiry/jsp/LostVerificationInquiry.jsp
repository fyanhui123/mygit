
<% 
/*
 * 印刷入库统计查询
***************************************
 * 程序名：LostVerificationInquiry.jsp
 * 建立日期：2013-4-25
 * 作者：Leo
 * 模块：遗失核销查询统计
 * 
***************************************
 */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
		<script type="text/javascript"
			src="${ctx}/pages/inquiry/js/LostVerificationInquiry.js"></script>
		<script type="text/javascript">
	$(document).ready(function () {
	    common.initHtml("LostVerificationInquiry");
		lostVerificationInquiry.initPage();
	});
</script>
	</head>
	<body>
		<div class="tab-center" id="docInStoreStatListPage">
			<table style="width: 100%">
				<tr>
					<td>
						<form id="subForm">
							<table id="mainTable" style="width: 100%">
								<tr>
									<td height="33">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr>
												<td class="f-black" height="20" valign="top">
													<img src="${ctx}/style/default/images/point4.gif"
														class="bottom">
													&nbsp; 遗失核销统计查询条件
												</td>
											</tr>
											<tr>
												<td class="pageDevide"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="trb">
									<td>
										<table width="100%" class="inputTable3col">
										    <tr>
												<td width="12%" class="inputTitle3col">
													<!--单证险类-->
													<s:text name="vcdoc.insuKind.insuType"></s:text>
												</td>
												<td width="25%" class="inputBox3col">
													<select id="lostVerificationInquiryInsuTypeList"
														name="lostVo.insuType" class="selectStyle" style="width: 155px">
													</select>
												</td>
												<td width="12%" class="inputTitle3col">
													<!--归属险种-->
													单证归属险种
												</td>
												<td width="38%" class="inputBox3col_text" nowrap="nowrap">
													<select  id="lostVerificationInquiryInsuKindList"
														name="lostVo.insuKind" class="selectStyle" style="width: 155px">
													</select>
												</td>
											</tr>
											<tr>
												<td width="12%" class="inputTitle3col">
													<!--单证类型-->
													单证种类
												</td>
												<td width="38%" class="inputBox3col_text" nowrap="nowrap">
													<select id="docVersionInfo" class="selectStyle" style="width: 155px"
														name="lostVo.docType">
														<%-- <option value="">
															-请选择-
														</option>
														<option value="0">
															单证
														</option>
														<option value="1">
															发票
														</option>--%>
													</select>
												</td>
												<td width="12%" class="inputTitle3col">
													<!--单证种类-->
													<s:text name="visaBase.docVersionInfo.docType" />
												</td>
												<td width="38%" class="inputBox3col_text" nowrap="nowrap">
													<input id="lostVerificationInquiryDocTypeList"  name="lostVo.docTypeCode" type="text"  />
                                         			<input id="lostVerificationInquiryDocTypeListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
												</td>
											</tr>
											
											<tr>
												<td width="12%" class="inputTitle3col">
													单证类型代码
												</td>
												<td width="38%" class="inputBox3col_text">
													<input id="lostVerificationInquiryDocVerCode"  name="lostVo.strDocVerCodes"  type="text" class="comboBox-select" />
                                      				<input id="lostVerificationInquiryDocVerName" type="text" readonly="readonly" />
												</td>

												<td width="12%" class="inputTitle3col">
													遗失单号
												</td>
												<td class="inputBox3col_text">
													<input type="text" id="lostCode" name="lostVo.lostCode" />
												</td>
											</tr>
											<tr>
												<td width="12%" class="inputTitle3col">
													<s:text name="inquiry.inStoryApply.applyTime" />
												</td>
												<td colspan="3" class="inputBox3col_text">
													<input type="text" id="applyStartDate"
														name="lostVo.applyStartDate"
														value="<fmt:formatDate value="${applyStartDate}" pattern="yyyy-MM-dd"/>"
														class="Wdate taDatePicker" onclick="WdatePicker()" />
													至
													<input type="text" id="applyEndDate"
														name="lostVo.applyEndDate"
														value="<fmt:formatDate value="${applyEndDate}" pattern="yyyy-MM-dd"/>"
														class="Wdate taDatePicker" onclick="WdatePicker()" />
												</td>
											</tr>

											<tr>
												<td width="12%" class="inputTitle3col">
													<s:text name="inquiry.inStoryApply.applyOrgName" />
												</td>
												<td width="38%" class="inputBox3col_text" nowrap="nowrap">
													<!--<input id="companyCode" 
														name="lostVo.applyOrgCode" type="text"
														class="ui-tianan-input-text">
												-->
												   <input id="companyCode" name="lostVo.lostOrgCode" class="ui-autocomplete-input" type="text" /> 
							                       <input id="companyCodeName" style="width: 100px;" type="text" readonly="readonly" /> 
										       </td>
												
												
												<td width="12%" class="inputTitle3col">
													处理状态
												</td>
												<td width="38%" class="inputBox3col_text" nowrap="nowrap">
													<select id="lostStatus" name="lostVo.lostStatus"
														class="selectStyle" style="width: 155px"></select>
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
												<td height="28" valign="center">
													<img src="${ctx}/style/default/images/point4.gif"
														class="bottom">
													&nbsp;
													<s:text name="inquiry.inStoryApply.queryList" />
												</td>
												<td align="right" valign="top" nowrap="nowrap">
													<input id="querylostInquiry" type="button" class="taButton"
														value="<s:text name="common.submit.query" />" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="reset" class="taButton" id="resetBtn"
														value="<s:text name="common.submit.rest" />" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input id="exportLostInquiry" type="button" class="taButton" 
														value="<s:text name="common.submit.exportExcel" />" />
												</td>
											</tr>
											<tr>
												<td class="pageDevide" colspan="2"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="trc">
									<td>
										<table class="pagerTable">
											<tr>
												<td>
													<table id="lostVerificationInquiryGrid"
														class="inputTable3col"></table>
													<div id="lostVerificationInquiryGridPager"></div>
													<input id="id" name="id" value="" type="hidden">
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
	</body>
</html>