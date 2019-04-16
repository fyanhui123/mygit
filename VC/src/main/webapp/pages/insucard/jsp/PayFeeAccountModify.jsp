<%
	/*
	 * 账户修改同步页面
	 ***************************************
	 * 程序名：PayFeeAccountModify.jsp
	 * 建立日期：2013-07-22
	 * 作者：whj
	 * 模块：激活卡管理-账户修改同步页面
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
<script type="text/javascript" src="${ctx}/pages/insucard/js/PayFeeAccountModify.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	accountModify.initPage();
 }); 
</script>
</head>
<body>
<div id="payFeeAccountModifyPage" style="width: 100%">
	<form id="accountModifyForm" name="accountModifyForm" action="#" method="post" enctype="multipart/form-data">
	    <input type="hidden" id="actionType" name="actionType" value="${actionType}"/>
	   
		
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="f-black" height="20" valign="top"><img
						src="${ctx}/style/default/images/point4.gif" class="bottom">
					&nbsp; 退卡记录信息</td>
				</tr>
				<tr>
					<td class="pageDevide"></td>
				</tr>
			</table>
		
			<table width="100%" class="inputTable3col">
			  <tr>
				<td width="12%" class="inputTitle3col">
					退卡记录号
				 </td>
				 <td width="38%" class="inputBox3col_text">
					 <input id="idVcInsuCardSalesRecord" value="${insuCardSaleRecordVo.idVcInsuCardSalesRecord}" type="hidden"  />
		             <input  value="${insuCardSaleRecordVo.salesRecordCode}" type="text"   disabled />
				 </td>
			   </tr>
			   <tr>
				 <td width="12%" class="inputTitle3col">
					退卡人
				 </td>
				 <td width="38%" class="inputBox3col_text">
					<input  value="${insuCardSaleRecordVo.sellerName}" type="text" class=""  disabled />
				 </td>
				 <td width="12%" class="inputTitle3col">
					退卡人机构 
				 </td>
				 <td>
					<input value="${insuCardSaleRecordVo.saleOrgName}" type="text" disabled/>
				</td>
			 </tr>
		 </table>
		<%@ include file="PayFeeAccountPage.jsp"%>
		<table  style="width: 100%">
			<tr>
				<td colspan="4">
					&nbsp;
				</td>
			</tr>
			<tr>
				<td style="padding-left: 30px;" colspan="4" align="center">
					<input type="button" class="taButton"  id="submitModifyBut" value='<s:text name="common.submit.submit" />'/>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="cancelModifyBut" class="taButton" value="<s:text name="common.submit.cancel" />" />
				</td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
