<%
	/*
	 * 激活卡销售登记新增页面
	 ***************************************
	 * 程序名：InsuCardSalesAdd.jsp
	 * 建立日期：2013-07-01
	 * 作者：whj
	 * 模块：激活卡管理-激活卡销售登记新增页面
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
<script type="text/javascript" src="${ctx}/pages/insucard/js/InsuCardSalesAdd.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/ValidStorage.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	insuCardSalesAdd.initPage();
	$("#businessMode").trigger("change");
 }); 
</script>
</head>
<body>
<div id="insuCardSalesAddPage" style="width: 100%">
	<form id="insuCardSalesAddForm" name="insuCardSalesAddForm" action="#" method="post" enctype="multipart/form-data">
	    <input type="hidden" id="actionType" name="actionType" value="${actionType}"/>
	    <textarea id="detListJsonId" style="display: none;" rows="4">${detListJson}</textarea>
	    <%--
	    <input id="insuCardSaleRecordVoSellerCode" name="insuCardSaleRecordVo.sellerCode" 
		     value="${insuCardSaleRecordVo.sellerCode}" type="hidden"/>
		<input id="insuCardSaleRecordVoSellerCodeName"  name="insuCardSaleRecordVo.sellerName"
		    value="${insuCardSaleRecordVo.sellerName}" type="hidden" />
		<input id="insuCardSaleRecordVoSaleOrgCode" name="insuCardSaleRecordVo.saleOrgCode" 
		    value="${insuCardSaleRecordVo.saleOrgCode}" type="hidden" />   
	    <input id="insuCardSaleRecordVoSaleOrgCodeName"  name="insuCardSaleRecordVo.saleOrgName"
			value="${insuCardSaleRecordVo.saleOrgName}"  type="hidden"/>
		 --%>
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
					<table id="sellerSelectTab" width="100%" class="inputTable3col">
						<%--
						<tr>
							<td width="12%" class="inputTitle3col">
								销售人
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="insuCardSaleRecordVoSellerCode" name="insuCardSaleRecordVo.sellerCode" 
								     value="${insuCardSaleRecordVo.sellerCode}" type="text"  class="ui-autocomplete-input" />
								<input id="insuCardSaleRecordVoSellerCodeName"  name="insuCardSaleRecordVo.sellerName"
								    value="${insuCardSaleRecordVo.sellerName}" type="text" 
								    class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
							</td>
							<td width="12%" class="inputTitle3col">
								销售人机构 
							</td>
							<td>
								<input id="insuCardSaleRecordVoSaleOrgCodeName"  name="insuCardSaleRecordVo.saleOrgName"
								value="${insuCardSaleRecordVo.saleOrgName}"   type="text" readonly="readonly"/>
								<input id="insuCardSaleRecordVoSaleOrgCode" name="insuCardSaleRecordVo.saleOrgCode" 
								    value="${insuCardSaleRecordVo.saleOrgCode}" type="hidden" />   
							</td>
						</tr>
						 --%>
						 <%--业务方式 --%>
						 <%--<tr>
						 	<td width="12%" class="inputTitle3col">
								业务方式
							</td>
							<td colspan="3" class="inputBox3col_text">
								<select id="businessType" class="selectStyle" style="width: 155px">
									<option value="1">1-直接业务</option>
									<option value="2">2-间接业务</option>
								</select>
							</td>
						 </tr> --%>
						 <%--直接业务 --%>
						 <%--<tr id="typeOneTr" style="display: none;">
							<td width="12%" class="inputTitle3col">
								销售人
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="typeOneSellerCode" value="${insuCardSaleRecordVo.sellerCode}" 
									type="text"  class="ui-autocomplete-input" />
								<input id="typeOneSellerCodeName" value="${insuCardSaleRecordVo.sellerName}" 
									type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
							</td>
							<td width="12%" class="inputTitle3col">
								销售人机构 
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="typeOneSaleOrgCodeName"  value="${insuCardSaleRecordVo.saleOrgName}"
									type="text" readonly="readonly"/>
								<input id="typeOneSaleOrgCode" value="${insuCardSaleRecordVo.saleOrgCode}" 
									type="hidden" />   
							</td>
						</tr> --%>
						<%--间接业务 --%>
						<%--<tr id="typeTwoTr" style="display: none;">
							<td colspan="4">
							<table width="100%" class="inputTable3col">
								<tr>
									<td width="12%" class="inputTitle3col">
										所属机构
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="typeTwoComCode"  type="text" class="ui-autocomplete-input" />
										<input id="typeTwoComCodeName"  type="text" 
											class="ui-widget-content ui-autocomplete-label" style="width: 100px;" readonly="readonly" />
									</td>
									<td width="12%" class="inputTitle3col">
										所属团队
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="typeTwoTeamCode" type="text"  class="ui-autocomplete-input" />
										<input id="typeTwoTeamCodeName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
									</td>
								</tr>
								<tr>
									<td width="12%" class="inputTitle3col">
										销售人
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="typeTwoSellerCode" value="${insuCardSaleRecordVo.sellerCode}" 
											type="text"  class="ui-autocomplete-input" />
										<input id="typeTwoSellerCodeName" value="${insuCardSaleRecordVo.sellerName}" 
											type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
									</td>
									<td width="12%" class="inputTitle3col">
										销售人机构
									</td>
									<td width="38%" class="inputBox3col_text">
										<input id="typeTwoSaleOrgCodeName"  value="${insuCardSaleRecordVo.saleOrgName}"
											type="text" readonly="readonly"/>
										<input id="typeTwoSaleOrgCode" value="${insuCardSaleRecordVo.saleOrgCode}" 
											type="hidden" />   
									</td>
								</tr>
							</table>
							</td>
						</tr>--%>
						<%--业务方式 --%>
						<tr>
						 	<td width="12%" class="inputTitle3col">
								业务方式
							</td>
							<td width="38%" class="inputBox3col_text">
								<select id="businessMode" name="insuCardSaleRecordVo.businessMode" class="selectStyle" style="width: 155px">
									<option value="1">1-直接业务</option>
									<option value="2">2-间接业务</option>
								</select>
							</td>
							<td width="12%" class="inputTitle3col">
								渠道类型
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="channelDetailCode" name="insuCardSaleRecordVo.channelDetailCode" 
									value="${insuCardSaleRecordVo.channelDetailCode}" type="text"  class="ui-autocomplete-input required" disabled = "true"/>
								<input id="channelDetailCodeName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
							</td>
						</tr>
						<tr>
						 	<td width="12%" class="inputTitle3col">
								销售机构
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="saleOrgCode" name="insuCardSaleRecordVo.saleOrgCode" 
									value="${insuCardSaleRecordVo.saleOrgCode}" type="text"  class="ui-autocomplete-input required" />
								<input id="saleOrgCodeName" name="insuCardSaleRecordVo.saleOrgName" 
									value="${insuCardSaleRecordVo.saleOrgName}" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
							</td>
							<td width="12%" class="inputTitle3col">
								业务来源
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="businessSource" name="insuCardSaleRecordVo.businessSource" 
									value="${insuCardSaleRecordVo.businessSource}" type="text"  class="ui-autocomplete-input required" />
								<input id="businessSourceName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
							</td>
						</tr>
						<tr id="agencyTr" style="display: none;">
						 	<td width="12%" class="inputTitle3col">
								中介人协议号
							</td>
							<td colspan="3" class="inputBox3col_text">
								<input id="agreementNo" name="insuCardSaleRecordVo.agreementNo" style="width: 170px"
									value="${insuCardSaleRecordVo.agreementNo}" type="text"  class="ui-autocomplete-input" />
							</td>
						</tr>
						<tr>
						 	<td width="12%" class="inputTitle3col">
								团队代码
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="teamCode" name="insuCardSaleRecordVo.teamCode" style="width: 170px"
									value="${insuCardSaleRecordVo.teamCode}" type="text"  class="ui-autocomplete-input" />
							</td>
							<td width="12%" class="inputTitle3col">
								销售人
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="insuCardSaleRecordVoSellerCode" name="insuCardSaleRecordVo.sellerCode" 
								     value="${insuCardSaleRecordVo.sellerCode}" type="text"  class="ui-autocomplete-input required" />
								<input id="insuCardSaleRecordVoSellerCodeName"  name="insuCardSaleRecordVo.sellerName"
								    value="${insuCardSaleRecordVo.sellerName}" type="text" 
								    class="ui-widget-content ui-autocomplete-label required" readonly="readonly" />
							</td>
						</tr>
						
						<!--MODIFY BY ZHXIAO3 VC-121激活卡激活卡未保存跟单业务员 BEGIN-->
						<tr>
							<td>
								<div style="display: none;">
									<input id="handlerCode" name="insuCardSaleRecordVo.handlerCode" />
									<input id="handlerName" name="insuCardSaleRecordVo.handlerName" />
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
