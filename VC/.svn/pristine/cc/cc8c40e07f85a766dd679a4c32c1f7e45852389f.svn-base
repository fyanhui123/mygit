<%
	/*
	 * 激活卡数据导入结果叶
	 ***************************************
	 * 程序名：InsuranceCardImport.jsp
	 * 建立日期：2013-06-14
	 * 作者：whj
	 * 模块：激活卡管理-激活卡导入结果页面
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
<script type="text/javascript" src="${ctx}/pages/insucard/js/ImportOrCheckResult.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
  common.initHtml("InsuranceCardImport");
  importOrCheckResult.initPage();
 }); 
</script>
</head>
<body>
<div class="tab-center">  
	<table style="width: 100%">
		<tr>
			<td>
			 <form action="#" id="importOrCheckResultForm"  method="post" enctype="multipart/form-data">	
			  <input type="hidden" id="importResult" value="${checkedReturnVo.importResult}">				
				<table width="100%" style="width: 100%">
			         <tr >
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="store.insucard.title.fileImportResult" />
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
					    <table width="50%" cellpadding="1" cellspacing="1" border="1">
								<thead>								
									<tr>									
										<th width="10%"><s:text name="store.insucard.startCardNo" /></th>
										<th width="10%"><s:text name="store.insucard.endCardNo" /></th>
										<th width="5%"><s:text name="store.insucard.importNo" /></th>
										<th width="5%"><s:text name="store.insucard.badRecordNum" /></th>
										<th width="5%"><s:text name="store.insucard.importResult" /></th>
										<th width="20%"><s:text name="store.insucard.importFainReason" /></th>				
									</tr>
								</thead>
								<tbody>
									<s:iterator id="checkItem" value="checkedReturnVoList">
										<tr>
											<td align="center"><s:property value="#checkItem.startCardNo"/></td>
											<td align="center"><s:property value="#checkItem.endCardNo"/></td>
											<td align="center"><s:property value="#checkItem.importNum"/></td>
											<td align="center"><s:property value="#checkItem.badRecordNum"/></td>	
											<td align="center"><s:property value="#checkItem.importResultDesc"/></td>
											<td align="center"><s:property value="#checkItem.importResultMsg"/></td>										
										</tr>
									</s:iterator>
								</tbody>
							</table>
					    </td>
					  </tr>
					  <tr>
					  <td>&nbsp;
					  </td>
					  </tr>
					  <tr>
						 <td width="60%" class="inputBox3col"  align="left">
						   <div style="padding-left: 18%;">						  
						     <s:if test="checkedReturnVo.importResult==true">   
                                 <input type="button"  class="taButton" id="successCommitBut"  value="<s:text name="common.submit.confirm" />"/>  
		                       </s:if> 
		                        <s:else> 
		                         <input type="button" id="submitAgainBut" value="<s:text name="store.insucard.buttion.importOnceAgain" />" />
		                         &nbsp;	 &nbsp;	&nbsp;	&nbsp;	&nbsp;		                        
		                         <input type="button"  class="taButton" id="goToMaintBut" value="<s:text name="store.insucard.buttion.leave" />"/>  
		                       </s:else>  
						    </div>
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

