<%
	/*
	 * 激活卡销售登记修改页面
	 ***************************************
	 * 程序名：InsuCardSalesEdit.jsp
	 * 建立日期：2013-07-22
	 * 作者：whj
	 * 模块：激活卡管理-激活卡销售登记修改页面
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
<script type="text/javascript" src="${ctx}/pages/insucard/js/InsuCardSalesEdit.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/ValidStorage.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	insuCardSalesEdit.initPage();
 }); 
</script>
</head>
<body>
<div id="insuCardSalesEditPage" style="width: 100%">
	<form id="insuCardSalesEditForm" name="insuCardSalesEditForm" action="#" method="post" enctype="multipart/form-data">
	    <input type="hidden" id="actionType" name="actionType" value="${actionType}"/>
	    <textarea id="detListJsonId" style="display: none;" rows="4">${detListJson}</textarea>
		<table  style="width: 100%">
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								激活卡销售登记
							</td>
						</tr>
						<tr>
							<td class="pageDevide"></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr id="trd">
				<td>
					<table width="100%" class="inputTable3col">
						<%--
					    <tr>
							<td width="12%" class="inputTitle3col">
								销售记录号
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="idVcInsuCardSalesRecord" name="insuCardSaleRecordVo.idVcInsuCardSalesRecord" value="${insuCardSaleRecordVo.idVcInsuCardSalesRecord}" type="hidden"  />
								<input value="${insuCardSaleRecordVo.salesRecordCode}" type="text"  disabled />
								<input type="hidden"  name="insuCardSaleRecordVo.salesRecordCode" value="${insuCardSaleRecordVo.salesRecordCode}"/>
							</td>
						</tr>
						<tr>
							<td width="12%" class="inputTitle3col">
								销售人
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="insuCardSaleRecordVoSellerCode" name="insuCardSaleRecordVo.sellerCode" value="${insuCardSaleRecordVo.sellerCode}" type="hidden"  />
								<input  value="${insuCardSaleRecordVo.sellerName}" type="text"  disabled />
								<input type="hidden"  name="insuCardSaleRecordVo.sellerName" value="${insuCardSaleRecordVo.sellerName}"/>
							</td>
							<td width="12%" class="inputTitle3col">
								销售人机构 
							</td>
							<td>
								<input value="${insuCardSaleRecordVo.saleOrgName}" type="text" disabled/>
								<input type="hidden"  name="insuCardSaleRecordVo.saleOrgName" value="${insuCardSaleRecordVo.saleOrgName}"/>
								<input id="insuCardSaleRecordVoSaleOrgCode" name="insuCardSaleRecordVo.saleOrgCode" value="${insuCardSaleRecordVo.saleOrgCode}" type="hidden" />   
							</td>
						</tr>
						 --%>
						 <tr>
							<td width="12%" class="inputTitle3col">
								销售记录号
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="idVcInsuCardSalesRecord" name="insuCardSaleRecordVo.idVcInsuCardSalesRecord" value="${insuCardSaleRecordVo.idVcInsuCardSalesRecord}" type="hidden"  />
								<input value="${insuCardSaleRecordVo.salesRecordCode}" type="text"  disabled />
								<input type="hidden"  name="insuCardSaleRecordVo.salesRecordCode" value="${insuCardSaleRecordVo.salesRecordCode}"/>
							</td>
							<td width="12%" class="inputTitle3col">
								业务方式
							</td>
							<td width="38%" class="inputBox3col_text">
								<select id="businessMode"  class="selectStyle" style="width: 155px" disabled="disabled">
									<option value="1">1-直接业务</option>
									<option value="2">2-间接业务</option>
								</select>
								<input id="businessModeDropDownList" name="insuCardSaleRecordVo.businessMode" value="${insuCardSaleRecordVo.businessMode}" type="hidden"/>
							</td>
						</tr>
						<tr>
						 	<td width="12%" class="inputTitle3col">
								渠道类型
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="channelDetailCode" name="insuCardSaleRecordVo.channelDetailCode" readonly="readonly"
									value="${insuCardSaleRecordVo.channelDetailCode}" type="text"  class="ui-autocomplete-input" />
								<input id="channelDetailCodeName" name="insuCardSaleRecordVo.channelDetailCode" readonly="readonly"
									value="${insuCardSaleRecordVo.channelDetailName}" type="text" class="ui-widget-content ui-autocomplete-label"/>
							</td>
							<td width="12%" class="inputTitle3col">
								业务来源
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="businessSource" name="insuCardSaleRecordVo.businessSource" readonly="readonly"
									value="${insuCardSaleRecordVo.businessSource}" type="text"  class="ui-autocomplete-input" />
								<input id="businessSourceName" name="insuCardSaleRecordVo.businessSourceName" readonly="readonly"
									value="${insuCardSaleRecordVo.businessSourceName}" type="text" class="ui-widget-content ui-autocomplete-label"/>
							</td>
						</tr>
						<tr>
						 	<td width="12%" class="inputTitle3col">
								销售机构
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="saleOrgCode" name="insuCardSaleRecordVo.saleOrgCode" readonly="readonly"
									value="${insuCardSaleRecordVo.saleOrgCode}" type="text"  class="ui-autocomplete-input" />
								<input id="saleOrgCodeName" name="insuCardSaleRecordVo.saleOrgName" readonly="readonly"
									value="${insuCardSaleRecordVo.saleOrgName}" type="text" class="ui-widget-content ui-autocomplete-label"/>
							</td>
							<td width="12%" class="inputTitle3col">
								销售人
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="insuCardSaleRecordVoSellerCode" name="insuCardSaleRecordVo.sellerCode" readonly="readonly"
								     value="${insuCardSaleRecordVo.sellerCode}" type="text"  class="ui-autocomplete-input" />
								<input id="insuCardSaleRecordVoSellerCodeName"  name="insuCardSaleRecordVo.sellerName" readonly="readonly"
								    value="${insuCardSaleRecordVo.sellerName}" type="text" class="ui-widget-content ui-autocomplete-label" />
							</td>
						</tr>
						<tr>
						 	<td width="12%" class="inputTitle3col">
								团队代码
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="teamCode" name="insuCardSaleRecordVo.teamCode" style="width: 170px" readonly="readonly"
									value="${insuCardSaleRecordVo.teamCode}" type="text"  class="ui-autocomplete-input" />
							</td>
							<td id="agreementNoTD1" width="12%" class="inputTitle3col" style="display: none;">
								中介人协议号
							</td>
							<td id="agreementNoTD2" width="38%" class="inputBox3col_text" style="display: none;">
								<input id="agreementNo" name="insuCardSaleRecordVo.agreementNo" style="width: 170px" readonly="readonly"
									value="${insuCardSaleRecordVo.agreementNo}" type="text"  class="ui-autocomplete-input" />
							</td>
						</tr>
						
						<!--MODIFY BY ZHXIAO3 VC-121激活卡激活卡未保存跟单业务员 BEGIN-->
						<tr>
							<td>
								<div style="display: none;">
								<input id="handlerCode" name="insuCardSaleRecordVo.handlerCode" style="width: 170px" readonly="readonly"
									value="${insuCardSaleRecordVo.handlerCode}" type="text"  class="ui-autocomplete-input" />
								<input id="handlerName" name="insuCardSaleRecordVo.handlerName" style="width: 170px" readonly="readonly"
									value="${insuCardSaleRecordVo.handlerName}" type="text"  class="ui-autocomplete-input" />
								</div>
							</td>
						</tr> 
						<!--MODIFY BY ZHXIAO3 VC-121激活卡激活卡未保存跟单业务员 END-->
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
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								激活卡列表
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
					<div style="width: 98%">
						<span id="insuCardDetList" style="width: 100%"></span>
					</div>
				</td>
			</tr>

			<tr>
				<td colspan="4">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td style="padding-left: 30px;" colspan="4" align="center">
					<input type="button" id="saveBut" class="taButton" value="<s:text name="common.submit.save" />" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" class="taButton"  id="submitBut" value='<s:text name="common.submit.submit" />'/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="cancelBtn" class="taButton" value="<s:text name="common.submit.cancel" />" />
				</td>
			</tr>
		</table>
	</form>
</div>
<%@ include file="/pages/store/jsp/ValidStorageQuery.jsp"%>
</body>
</html>
