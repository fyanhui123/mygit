<%
	/*
	 * 激活卡导入
	 ***************************************
	 * 程序名：InsuranceCardImport.jsp
	 * 建立日期：2013-06-14
	 * 作者：whj
	 * 模块：激活卡管理-激活卡导入页面
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
<script type="text/javascript" src="${ctx}/pages/insucard/js/InsuranceCardImport.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	 insuranceCardImport.initPage();
 }); 
</script>
</head>
<body>
<div  id="insuCardImport" class="tab-center">  
	<table style="width: 100%">
		<tr>
			<td>
			 <form action="/visa/insucard/insuranceCardupLoad.do" id="insuCardimportForm"  method="post" enctype="multipart/form-data">					
				<table width="100%" id="mainTable" style="width: 100%">
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
							
							  <table class="inputTable3col" style="width: 60%">
								<tr>
									<td width="10%"  class="inputTitle3col" align="left" >
										<s:text name="store.insucard.docVersionInfo" />
									</td>
									<td  width="38%" class="inputBox3col"  align="left">								    
										<input type="hidden" id="importDtoDocVerCode" name="docVerCode"  value="${docVersionInfoDto.docVerCode}"/> 
										<input id="importDtoDocVerName"  value="${docVersionInfoDto.docVerName}"  readOnly style="background-color: lightgray"/>
									 </td>
								
									<td width="10%" class="inputTitle3col" align="left">
										<s:text name="store.insucard.importFile" />
									</td>
									<td width="38%" class="inputBox3col"  align="left">
									    <input type="file" name="file" class="required" /><!--
									    <input type="button" class="taButton" id="checkBut" value="校验" />
									--></td>
								</tr>
							</table>							
						</td>
					</tr>
					<tr>
					 <td>&nbsp;
					 </td>
					</tr>			
				    <tr>
					 <td width="60%" class="inputBox3col"  align="left">
					   <div style="padding-left: 20%;">
					     <input type="button"  class="taButton" id="commitImportBut"  value="<s:text name="common.submit.submit" />"/>
					     &nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;
						 <input type="button" id="backToMainBut" value="<s:text name="common.submit.back" />" />
					    </div>
					  </td>
					</tr>	
					
					<tr>
					  <td>&nbsp;
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

