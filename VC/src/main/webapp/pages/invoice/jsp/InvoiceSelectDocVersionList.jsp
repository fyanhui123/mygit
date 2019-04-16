<% 
/*
 *   直接入库查询单证类型
***************************************
 * 程序名：
 * 建立日期：2013-8-7
 * 作者：ljin
 * 模块：
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
<div id="invoiceSelectDocVersion">
	<table style="width: 100%">
		<tr>
			<td>
			<form id="invoiceSelectDocVersionForm" name="invoiceSelectDocVersionForm" action="#" method="post" enctype="multipart/form-data">
				<table id="mainTable" style="width: 100%">
					<%--查询条件 --%>
					<tr>
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="common.form.entryQuertyCondition" />
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
							<table class="inputTable3col" width="100%">
								<tr>
								    <!--docTypeCode-->
									<td width="12%" class="inputTitle3col">
										<s:text name="visaBase.docVersionInfo.docType" />
									</td>
									<td width="38%" class="inputBox3col_text" nowrap="nowrap">
										<input id="selectDocVersionDocTypeList"  name="invoiceDocVersionDTO.docTypeCode" type="text"  />
                                         <input id="selectDocVersionDocTypeListName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
									</td>
									<!-- orgCode -->
								<!-- 	
								    <td width="12%"  class="inputTitle3col" >
										<s:text name="inquiry.storage.orgCode"/>
									</td>
									<td width="38%"  class="inputBox3col_text"  nowrap="nowrap">
										<input id="selectDocVersionOrgCode" name="invoiceDocVersionDTO.orgCode" class="ui-autocomplete-input required" type="text" /> 
										<input id="selectDocVersionOrgName" style="width: 100px;" type="text" readonly="readonly" />
									</td>
								 -->
								    <!-- docVerCode -->
								    <td width="12%"  class="inputTitle3col">
										<s:text name="inquiry.Apply.docVerCode"/>
									</td>
									<td width="38%"  class="inputBox3col_text"  nowrap="nowrap">
										<input id="docVerCode" name="invoiceDocVersionDTO.docVerCode" type="text" />
									</td>
									
								</tr>
								<tr>
								    <!-- docVerName -->
								    <td width="12%"  class="inputTitle3col">
										<s:text name="inquiry.Apply.docVerName"/>
									</td>
									<td width="38%"  class="inputBox3col_text"  nowrap="nowrap">
										<input id="docVerName" name="invoiceDocVersionDTO.docVerName" type="text" />
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
					<%--查询列表 --%>
					<tr>
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="common.submit.result" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">
									<button id="queryDocVersion" buttonId="b04" class="taButton" type="button">
										<s:text name="common.submit.query" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="resetInput" buttonId="b05" class="taButton" type="button">
										<s:text name="common.submit.rest" />
									</button>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button id="docVersionInsert" buttonId="b06" class="taButton" type="button">
										<s:text name="invoice.invoicePurchase.inStore" />
									</button>
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
							<table class="pagerTable">
								<tr>
									<td>
										<table id="selectDocVersionGrid" class="inputTable3col" ></table>
										<div id="selectDocVersionGridPager"></div>
										<input id="inputId" name="inputId"  type="hidden">
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
<script type="text/javascript">
	$(document).ready(function () {
		selectDocVersion.initPage();
	});
</script>