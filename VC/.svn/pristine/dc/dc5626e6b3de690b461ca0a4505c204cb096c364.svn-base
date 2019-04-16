
<%
    /*
     * 激活卡退卡登记新增页面
     ***************************************
     * 程序名：InsuCardSalesAdd.jsp
     * 建立日期：2013-07-01
     * 作者：whj
     * 模块：激活卡管理-激活卡退卡登记新增页面
     * 备注：
     * -----------------------------------------
     * 修改历史
     * 序号 日期 修改人 修改原因
     * 
     ***************************************
     */
%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<!--引入jsp配对的js文件 -->
<script type="text/javascript"
	src="${ctx}/pages/insucard/js/InsuCardRefundAdd.js"></script>
<script type="text/javascript"
	src="${ctx}/pages/store/js/ValidStorage.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	insuCardRefundAdd.initPage();
	$("#businessMode").trigger("change");
 }); 
</script>
</head>
<body>
<div id="insuCardRefundAddPage" style="width: 100%">
<form id="insuCardRefundAddForm" name="insuCardRefundAddForm" action="#"
	method="post" enctype="multipart/form-data"><input type="hidden"
	id="actionType" name="actionType" value="${actionType}" />
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
<table style="width: 100%">
	<tr>
		<td>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td class="f-black" height="20" valign="top"><img
					src="${ctx}/style/default/images/point4.gif" class="bottom">
				&nbsp; 激活卡退卡人</td>
			</tr>
			<tr>
				<td class="pageDevide"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table id="sellerSelectTab" width="100%" class="inputTable3col">
			<%--
			<tr>
				<td width="12%" class="inputTitle3col">退卡人</td>
				<td width="38%" class="inputBox3col_text">
				  <input id="insuCardSaleRecordVoSellerCode" name="insuCardSaleRecordVo.sellerCode" 
				      value="${insuCardSaleRecordVo.sellerCode}" type="text" class="ui-autocomplete-input  required" /> 
				  <input id="insuCardSaleRecordVoSellerCodeName" name="insuCardSaleRecordVo.sellerName" 
				       value="${insuCardSaleRecordVo.sellerName}"  type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
				</td>
				<td width="12%" class="inputTitle3col">退卡人机构</td>
				<td> 
				   <input id="insuCardSaleRecordVoSaleOrgCodeName" name="insuCardSaleRecordVo.saleOrgName" 
				       value="${insuCardSaleRecordVo.saleOrgName}"  type="text" readonly="readonly" /> 
					<input id="insuCardSaleRecordVoSaleOrgCode" name="insuCardSaleRecordVo.saleOrgCode" 
					   value="${insuCardSaleRecordVo.saleOrgCode}"  type="hidden" />
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
			 </tr>--%>
			 <%--直接业务 --%>
			 <%--<tr id="typeOneTr" style="display: none;">
				<td width="12%" class="inputTitle3col">
					退卡人
				</td>
				<td width="38%" class="inputBox3col_text">
					<input id="typeOneSellerCode" value="${insuCardSaleRecordVo.sellerCode}" 
						type="text"  class="ui-autocomplete-input" />
					<input id="typeOneSellerCodeName" value="${insuCardSaleRecordVo.sellerName}" 
						type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
				</td>
				<td width="12%" class="inputTitle3col">
					退卡人机构 
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
							<input id="typeTwoComCode" type="text" class="ui-autocomplete-input" />
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
							退卡人
						</td>
						<td width="38%" class="inputBox3col_text">
							<input id="typeTwoSellerCode" value="${insuCardSaleRecordVo.sellerCode}" 
								type="text"  class="ui-autocomplete-input" />
							<input id="typeTwoSellerCodeName" value="${insuCardSaleRecordVo.sellerName}" 
								type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
						</td>
						<td width="12%" class="inputTitle3col">
							退卡人机构
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
			</tr> --%>
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
						value="${insuCardSaleRecordVo.channelDetailCode}" type="text"  class="ui-autocomplete-input required" />
					<input id="channelDetailCodeName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
				</td>
			</tr>
			<tr>
			 	<td width="12%" class="inputTitle3col">
					退卡机构
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
						value="${insuCardSaleRecordVo.teamCode}" type="text"  class="ui-autocomplete-input required" />
				</td>
				<td width="12%" class="inputTitle3col">
					退卡人
				</td>
				<td width="38%" class="inputBox3col_text">
					<input id="insuCardSaleRecordVoSellerCode" name="insuCardSaleRecordVo.sellerCode" 
					     value="${insuCardSaleRecordVo.sellerCode}" type="text"  class="ui-autocomplete-input required" />
					<input id="insuCardSaleRecordVoSellerCodeName"  name="insuCardSaleRecordVo.sellerName"
					    value="${insuCardSaleRecordVo.sellerName}" type="text" 
					    class="ui-widget-content ui-autocomplete-label required" readonly="readonly" />
				</td>
			</tr>
			<tr>
			<td width="12%" class="inputTitle3col">退卡人类型</td>
				<td><select id="bankInfoDtoPayeeType" name="bankInfoDto.payeeType"
					class="selectStyle" style="width: 155px" >
					<option value="0">0-投保人</option>
					<option value="1" selected>1-业务员</option>
					<option value="2">2-代理人</option>
					<option value="3">3-再保人</option>
				</select></td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	</table>
	
	<!--  <tr>
		<td>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td class="f-black" height="20" valign="top"><img
					src="${ctx}/style/default/images/point4.gif" class="bottom">
				&nbsp; 银行账户信息</td>
			</tr>
			<tr>
				<td class="pageDevide"></td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td>
		<table width="100%" class="inputTable3col">
			<tr>
				<td width="12%" class="inputTitle3col">收款人</td>
				<td width="38%" class="inputBox3col_text">
				<input id="bankInfoDtoPayeeCode" name="bankInfoDto.payeeCode" type="text" class="ui-autocomplete-input" />
				 <input id="bankInfoDtoPayeeCodeName" name="bankInfoDto.payeeName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
				</td>
				<td width="12%" class="inputTitle3col">收款人类型</td>
				<td><select id="bankInfoDtoPayeeType" name="bankInfoDto.payeeType"
					class="selectStyle" style="width: 155px">
					<option value="0">0-投保人</option>
					<option value="1">1-业务员</option>
					<option value="2">2-代理人</option>
					<option value="3">3-再保人</option>
				</select></td>
			</tr>
			<tr>
				<td width="12%" class="inputTitle3col">收款方银行卡折类型</td>
				<td>
				  <select id="bankInfoDtoCardType" name="bankInfoDto.cardType"
					class="selectStyle" style="width: 155px">
					<option value="1">折</option>
					<option value="2">借记卡</option>
					<option value="3">贷记卡</option>
					<option value="4">准贷记卡</option>
				  </select>
			   </td>
               <td width="12%" class="inputTitle3col">开户银行名称</td>
			   <td><input id="bankInfoDtoPayeeBankLocation"  name="bankInfoDto.payeeBankLocation" type="text" /></td>
			</tr>
			<tr>
			<td width="12%" class="inputTitle3col">收款方银行账号</td>
				<td><input id="bankInfoDtoPayeeBankAccount"
					name="bankInfoDto.payeeBankAccount" type="text"/></td>
				<td width="12%" class="inputTitle3col">收款方账户名称</td>
				<td><input id="bankInfoDtoPayeeBankAccountName" name="bankInfoDto.payeeBankAccountName" type="text" /></td>
			</tr>
			<tr>
			<td width="12%" class="inputTitle3col">收款方区域编码</td>
				<td width="38%" class="inputBox3col_text">
				<input id="bankInfoDtoPayeeAreaCode" name="bankInfoDto.payeeAreaCode" type="text" class="ui-autocomplete-input" /> 
				<input id="bankInfoDtoPayeeAreaCodeName" name="bankInfoDto.payeeAreaName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
			</td>
			<td width="12%" class="inputTitle3col">收款方银行编码</td>
				<td width="38%" class="inputBox3col_text">
				<input id="bankInfoDtoPayeeAreaCode" name="bankInfoDto.payeeBankCode" type="text" class="ui-autocomplete-input" /> 
				<input id="bankInfoDtoPayeeBankCodeName" name="bankInfoDto.payeeBankName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
			</td>
			
			</tr>
			<tr>
			 <td width="12%" class="inputTitle3col">收款方联行号编码</td>
				<td width="38%" class="inputBox3col_text">
				<input id="bankInfoDtoCnapsCode" name="bankInfoDto.cnapsCode" type="text" class="ui-autocomplete-input" /> 
				<input id="bankInfoDtoCnapsCodeName" name="bankInfoDto.cnapsName" type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" />
			  </td>
			</tr>
			<tr>
			 <td width="12%" class="inputTitle3col">加急标志 </td>
			   <td>
				  <select id="bankInfoDtoFastFlag" name="bankInfoDto.fastFlag" class="selectStyle" style="width: 155px">
					<option value="0">否</option>
					<option value="1">是</option>
				  </select>
			   </td>
			    <td width="12%" class="inputTitle3col">公/私标志</td>
			   <td>
				  <select id="bankInfoDtoCorpInd" name="bankInfoDto.corpInd" class="selectStyle" style="width: 155px">
					<option value="0">否</option>
					<option value="1">是</option>
				  </select>
			</tr>
			 <tr>
			 <td width="12%" class="inputTitle3col">短信通知</td>
			 <td width="38%" class="inputBox3col_text">
				  <select id="bankInfoDtoSmsFlag" name="bankInfoDto.smsFlag" class="selectStyle" style="width: 155px">
					<option value="0">否</option>
					<option value="1">是</option>
				  </select>
			  </td>
			  <td width="12%" class="inputTitle3col">手机号码</td>
			  <td width="38%" class="inputBox3col_text">
			   <input id="bankInfoDtoCellPhone" name="bankInfoDto.cellPhone" type="text"/> 
			  </td>
			</tr>
			<tr>		
			 <td width="12%" class="inputTitle3col">邮件通知 </td>
			 <td width="38%" class="inputBox3col_text">
				  <select id="bankInfoDtoMailFlag" name="bankInfoDto.mailFlag" class="selectStyle" style="width: 155px">
					<option value="0">否</option>
					<option value="1">是</option>
				  </select>
			  </td>
			  <td width="12%" class="inputTitle3col">邮件地址</td>
			  <td width="38%" class="inputBox3col_text">
			   <input id="bankInfoDtoMailAddr" name="bankInfoDto.mailAddr" type="text"/> 
			  </td>
			</tr>
			<%-- <tr>
			 <td width="12%" class="inputTitle3col">短信通知</td>
				<td width="38%" class="inputBox3col_text">
				<input id="bankInfoDtoSMSFlag" name="bankInfoDto.SMSFlag" type="checkbox" value="1" />是&nbsp;
				<input name="bankInfoDto.SMSFlag" type="checkbox" value="0" />否&nbsp;
			  </td>
			  <td width="12%" class="inputTitle3col">手机号码</td>
			  <td width="38%" class="inputBox3col_text">
			   <input id="bankInfoDtoCellPhone" name="bankInfoDto.cellPhone" type="text"/> 
			  </td>
			</tr>
			<tr>		
			 <td width="12%" class="inputTitle3col">邮件通知 </td>
				<td width="38%" class="inputBox3col_text">
				<input id="bankInfoDtoMailFlag" name="bankInfoDto.mailFlag" type="checkbox" value="1" >是&nbsp;
				<input id="bankInfoDtoMailFlag" name="bankInfoDto.mailFlag" type="checkbox"  value="0" >否&nbsp;
			  </td>
			  <td width="12%" class="inputTitle3col">邮件地址</td>
			  <td width="38%" class="inputBox3col_text">
			   <input id="bankInfoDtoMailAddr" name="bankInfoDto.mailAddr" type="text"/> 
			  </td>
			</tr> --%>
			<tr>	
              <td width="12%" class="inputTitle3col">证件类型 </td>
               <td>
				  <select id="bankInfoDtoCertType" name="bankInfoDto.certType"
					class="selectStyle" style="width: 155px">
					 <option value="0">身份证</option>
					 <option value="1">户口簿</option>
					 <option value="2">护照  </option>
					 <option value="3">军官证</option>
					 <option value="4">士兵证</option>
					 <option value="5">港澳居民来往内地通行证 </option>
					 <option value="6">台湾同胞来往内地通行证 </option>
					 <option value="7">临时身份证</option>
					 <option value="8">外国人居留证</option>
					 <option value="9">警官证</option>
					 <option value="99">其他证件</option>
				  </select>
			   </td>
               <td width="12%" class="inputTitle3col">证件号码</td>
			   <td><input id="bankInfoDtoCertNumber"  name="bankInfoDto.certNumber" type="text" /></td>
			 </tr>
             <tr>
				<td width="12%" class="inputTitle3col">
					用途
				</td>
				<td width="38%" class="inputBox3col_text" colspan="3">
				  <textarea  name="bankInfoDto.purpose"  rows="5" style="width: 90%"  class="required"  maxlength="500"></textarea> 									
				</td>
			</tr>
			<tr>
				<td width="12%" class="inputTitle3col">
					备注 
				</td>
				<td width="38%" class="inputBox3col_text" colspan="3">
				  <textarea  name="bankInfoDto.memo"  rows="5" style="width: 90%"  class="required"  maxlength="500"></textarea> 									
				</td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	-->
	<%@ include file="PayFeeAccountPage.jsp"%>
	<table style="width: 100%">
	<tr>
		<td>
		<table cellpadding="0" cellspacing="0" border="0" width="100%">
			<tr>
				<td class="f-black" height="20" valign="top"><img
					src="${ctx}/style/default/images/point4.gif" class="bottom">
				&nbsp; 退卡列表</td>
			</tr>
			<tr>
				<td class="pageDevide"></td>
			</tr>
		</table>
		</td>
	</tr>

	<tr >
		<td>
		<div style="width: 98%"><span id="insuCardDetList"
			style="width: 100%"></span></div>
		</td>
	</tr>

	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	<tr>
	  <td style="padding-left: 30px;" colspan="4" align="center">
		<input type="button" id="saveBut" class="taButton" value="<s:text name="common.submit.save" />" />
		&nbsp;&nbsp;&nbsp;&nbsp; 
		<input type="button" class="taButton" id="submitBut" value='<s:text name="common.submit.submit" />' />
		&nbsp;&nbsp;&nbsp;&nbsp; 
		<input type="button" id="cancelBtn" class="taButton" value="<s:text name="common.submit.cancel" />" />
	  </td>
	</tr>
</table>
</form>
</div>
</body>
</html>
