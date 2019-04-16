
<%
    /*
     * 收付费银行账户信息页面
     ***************************************
     * 程序名：PayFeeAccountViewPage.jsp
     * 建立日期：2013-08-05
     * 作者：whj
     * 模块：激活卡管理-收付费银行账户信息页面
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
<script type="text/javascript" src="${ctx}/pages/insucard/js/PayFeeAccountPage.js"></script>

<div id="PayFeeAccountViewPage" style="width: 100%">
<table style="width: 100%">
	<tr>
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
			<!--  <tr>
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
			</tr>-->
			<tr>
				<td width="12%" class="inputTitle3col">银行卡折类型</td>
				<td>
				  <input type="hidden"  id="bankInfoDtoCardType_hidden" value="${bankInfoDto.cardType}">
				  <select id="bankInfoDtoCardType" name="bankInfoDto.cardType"
					class="selectStyle" style="width: 155px" disabled>
					<option value="1">折</option>
					<option value="2">借记卡</option>
					<option value="3">贷记卡</option>
					<option value="4">准贷记卡</option>
				  </select>
			   </td>
               
			</tr>
			<tr>
			   <td width="12%" class="inputTitle3col">银行账号</td>
			    <td><input id="bankInfoDtoPayeeBankAccount"
					name="bankInfoDto.payeeBankAccount" value="${bankInfoDto.payeeBankAccount}"  type="text" disabled/></td>
				<td width="12%" class="inputTitle3col">账户名称</td>
				<td><input id="bankInfoDtoPayeeBankAccountName" name="bankInfoDto.payeeBankAccountName"  
				     value="${bankInfoDto.payeeBankAccountName}" type="text"  disabled/>
			    </td>
			</tr>
			<tr>
			 <td width="12%" class="inputTitle3col">银行</td>
				<td width="38%" class="inputBox3col_text">
				<input id="bankInfoDtoPayeeBankCode" name="bankInfoDto.payeeBankCode" value="${bankInfoDto.payeeBankCode}" type="text" 
				 class="ui-autocomplete-input" disabled /> 
				<input id="bankInfoDtoPayeeBankCodeName" name="bankInfoDto.payeeBankName" value="${bankInfoDto.payeeBankName}"
				  type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" disabled/>
			 </td>
			 <td width="12%" class="inputTitle3col">收款方区域编码</td>
				<td width="38%" class="inputBox3col_text">
				<input id="bankInfoDtoPayeeAreaCode" name="bankInfoDto.payeeAreaCode"  value="${bankInfoDto.payeeAreaCode}" type="text" 
				     class="ui-autocomplete-input" disabled/> 
				<input id="bankInfoDtoPayeeAreaCodeName" name="bankInfoDto.payeeAreaName" value="${bankInfoDto.payeeAreaName}" 
				   type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" disabled/>
			</td>
			</tr>
			 <tr>
			   <td width="12%" class="inputTitle3col">开户支行（联行号）</td>
			   <td>
			    <input id="bankInfoDtoPayeeBankLocationCode"  name="bankInfoDto.payeeBankLocationCode" 
			         value="${bankInfoDto.payeeBankLocationCode}"  type="text" class="ui-autocomplete-input" disabled/> 
				 <input id="bankInfoDtoPayeeBankLocationCodeName"  name="bankInfoDto.payeeBankLocationName"  value="${bankInfoDto.payeeBankLocationName}" 
				     type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly"  disabled/>
			   </td>
			   <!--  <td width="12%" class="inputTitle3col">收款方联行号编码</td>
				<td width="38%" class="inputBox3col_text">
				<input id="bankInfoDtoCnapsCode" name="bankInfoDto.cnapsCode" value="999999999999" 
				   type="text" class="ui-autocomplete-input" disabled/> 
				<input id="bankInfoDtoCnapsCodeName" name="bankInfoDto.cnapsName" 
				   type="text" class="ui-widget-content ui-autocomplete-label" readonly="readonly" disabled/>
			  </td>-->
			</tr>
			<tr>
			 <td width="12%" class="inputTitle3col">加急标志 </td>
			   <td>
			      <input type="hidden"  id="bankInfoDtoFastFlag_hidden" value="${bankInfoDto.fastFlag}">
				  <select id="bankInfoDtoFastFlag" name="bankInfoDto.fastFlag" class="selectStyle" style="width: 155px" disabled>
					<option value="0">否</option>
					<option value="1">是</option>
				  </select>
			   </td>
			    <td width="12%" class="inputTitle3col">公/私标志</td>
			   <td>
			      <input type="hidden"  id="bankInfoDtoCorpInd_hidden" value="${bankInfoDto.corpInd}">
				  <select id="bankInfoDtoCorpInd" name="bankInfoDto.corpInd" class="selectStyle" style="width: 155px" disabled>
					<option value="0">法人</option>
					<option value="1">私人</option>
				  </select>
			</tr>
			 <tr>
			 <td width="12%" class="inputTitle3col">短信通知</td>
			 <td width="38%" class="inputBox3col_text">
			     <input type="hidden"  id="bankInfoDtoSmsFlag_hidden" value="${bankInfoDto.smsFlag}">
				  <select id="bankInfoDtoSmsFlag" name="bankInfoDto.smsFlag" class="selectStyle" style="width: 155px" disabled>
					<option value="0">否</option>
					<option value="1">是</option>
				  </select>
			  </td>
			  <td width="12%" class="inputTitle3col">手机号码</td>
			  <td width="38%" class="inputBox3col_text">
			   <input id="bankInfoDtoCellPhone" name="bankInfoDto.cellPhone" value="${bankInfoDto.cellPhone}" type="text" disabled/> 
			  </td>
			</tr>
			<tr>		
			 <td width="12%" class="inputTitle3col">邮件通知 </td>
			 <td width="38%" class="inputBox3col_text">
			     <input type="hidden"  id="bankInfoDtoMailFlag_hidden" value="${bankInfoDto.mailFlag}">
				  <select id="bankInfoDtoMailFlag" name="bankInfoDto.mailFlag" class="selectStyle" style="width: 155px" disabled>
					<option value="0">否</option>
					<option value="1">是</option>
				  </select>
			  </td>
			  <td width="12%" class="inputTitle3col">邮件地址</td>
			  <td width="38%" class="inputBox3col_text">
			   <input id="bankInfoDtoMailAddr" name="bankInfoDto.mailAddr" value="${bankInfoDto.mailAddr}" type="text" disabled/> 
			  </td>
			</tr>			
			<tr>	
              <td width="12%" class="inputTitle3col">证件类型 </td>
               <td>
                  <input type="hidden"  id="bankInfoDtoCertType_hidden" value="${bankInfoDto.certType}">
				  <select id="bankInfoDtoCertType" name="bankInfoDto.certType"
					class="selectStyle" style="width: 155px" disabled>
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
			   <td><input id="bankInfoDtoCertNumber"  name="bankInfoDto.certNumber" value="${bankInfoDto.certNumber}" type="text" disabled/></td>
			 </tr>
             <tr>
				<td width="12%" class="inputTitle3col">
					用途
				</td>
				<td width="38%" class="inputBox3col_text" colspan="3">
				  <textarea  name="bankInfoDto.purpose"  rows="3" style="width: 50%"  class="required"  disabled>${bankInfoDto.purpose}</textarea> 									
				</td>
			</tr>
			<tr>
				<td width="12%" class="inputTitle3col">
					备注 
				</td>
				<td width="38%" class="inputBox3col_text" colspan="3">
				  <textarea  name="bankInfoDto.memo"  rows="3" style="width: 50%"  class="required"  disabled>${bankInfoDto.memo}</textarea> 									
				</td>
			</tr>
			<tr>
				<td colspan="4">&nbsp;</td>
			</tr>
		</table>
		</td>
	</tr>
	<tr>
		<td colspan="4">&nbsp;</td>
	</tr>
	
</table>
</div>
<script type="text/javascript">
 $(document).ready(function () {
	payFeeAccount.initPage();
 }); 
</script>
