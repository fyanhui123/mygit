<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/Printery.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/PrinteryView.js"></script>
<script type="text/javascript">  
	$(document).ready(function () {
	     common.initHtml("PrinteryList");
		 printery.initPage();
		 printery.alertMsg("${actionType}","${resultMsg}");		
	});	
</script>
</head>
<body>
  <div id="printeryMain" class="tab-center">
	<table width="100%" style="width: 100%">
		<tr>
			<td >
			<form id="printingForm" action="#" method="post" enctype="multipart/form-data" >
				<table width="100%" id="mainTable" style="width: 100%">					
					<tr>
					  <td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td  height="20" valign="top">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="visaBase.title.printeryQueryCondition" />
								</td>
							</tr>
							<tr>
								<td class="pageDevide"></td>
							</tr>
						</table>
		               </td>						
					</tr>
					<tr id="tra">
						<td>
							<table width="100%" class="inputTable3col">
								<tr >
									<td width="12%" >
										<s:text name="visaBase.printery.printeryCode" />										
									</td>
									<td width="38%" >
										<input id="printeryCode" name="printeryCode" type="text" class="ui-tianan-input-text">
									</td>
									<td width="12%" >
										<s:text name="visaBase.printery.printeryName" />
									</td>
									<td width="38%" >
										<input id="printeryName" name="printeryName" type="text" class="ui-tianan-input-text">
									</td>
								</tr>
								<tr >
									<td width="12%"  >
									状态								
									</td>
									<td width="38%" >										
										<select id="status" name="status" class="selectStyle" style="width: 155px">
										</select>
									</td>										
								</tr>															
							</table>
						</td>
					</tr>
					<tr>
					  <td >
					    &nbsp;
					  </td>
					</tr>
					<tr >
						<td height="33">
						<table cellpadding="0" cellspacing="0" border="0" width="100%">
							<tr>
								<td height="28" valign="center">
									<img src="${ctx}/style/default/images/point4.gif" class="bottom">
									&nbsp;<s:text name="visaBase.list.printery" />
								</td>
								<td align="right" valign="top" nowrap="nowrap">										
									<input type="button" class="taButton" id="queryPrinteryBut"  value="<s:text name="common.submit.query" />"/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" class="taButton" value="<s:text name="common.submit.rest" />"  onclick="common.clearGridData('printeryGrid');"/>
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" class="taButton" id="addPrinteryBut" value="<s:text name="common.submit.add" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									 <input type="button" id="updatePrinteryBut" value="<s:text name="common.submit.alter" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" id="viewPrinteryBut"  class="taButton" value="<s:text name="common.submit.view" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="button" id="deleteOrUnDeletePrinteryBut" value="<s:text name="common.submit.deleteOrUnDelete" />" />
									&nbsp;&nbsp;&nbsp;&nbsp;
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
								<table class="pagerTable">
									<tr>
										<td>
											<table id="printeryGrid" class="inputTable3col" ></table>
											<div id="printeryGridPager"></div>
											<input id="selectIds" name="selectIds"  type="hidden">
											
										</td>
									</tr>
								</table>
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
    <%--@ include file="PrinteryEdit.jsp"--%>  
    <%@ include file="PrinteryView.jsp"%>   
</body>
</html>

