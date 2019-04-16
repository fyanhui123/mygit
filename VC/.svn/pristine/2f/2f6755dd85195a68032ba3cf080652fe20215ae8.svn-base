<%
	/*
	 * 使用出入库查询页面
	 ***************************************
	 * 程序名：DocStorageInOutQuery.jsp
	 * 建立日期：2014-4-14
	 * 作者：whj
	 * 模块：库存管理-使用出入库查询
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
	<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
		<script type="text/javascript" src="${ctx}/pages/store/js/DocStorageInOut.js"></script>		
		<script type="text/javascript">
			$(document).ready(function () {
				docStorageInOut.initPage();
				docStorageInOut.alertResultMsg("${actionType}","${resultMsg}");
			});
		</script>
	</head>
	<body>
		<div class="tab-center" id="docStorageInOutListPage">
			<table style="width: 100%">
				<tr>
					<td>
						<form id="queryForm" name="queryForm" action="#" method="post" enctype="multipart/form-data">
							<table id="mainTable" style="width: 100%">
								<tr>
									<td height="33">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr>
												<td class="f-black" height="20" valign="top">
													<img src="${ctx}/style/default/images/point4.gif"
														class="bottom">
													&nbsp;
													<s:text name="common.form.entryQuertyCondition" />
												</td>
											</tr>
											<tr>
												<td height="13"
													background="${ctx}/style/default/images/devide-main.gif"></td>
												<td height="13" width="13"
													background="${ctx}/style/default/images/devide-r.gif"></td>
											</tr>
										</table>
									</td>
								</tr>
								<tr id="trb">
									<td>
										<table width="100%" class="inputTable3col">
											<tr>
												<td width="12%" class="inputTitle3col">
													<!--单证类型-->
													<s:text name="store.docVer" />
												</td>
												<td class="inputBox3col_text">
													<input id="docVerCode" name="vcStorageInOut.docVerCode" type="text" class="ui-autocomplete-input" />
													<input id="docVerCodeName" type="text" class="ui-widget-content ui-autocomplete-label" />
												</td>
												<td width="12%" class="inputTitle3col">
													<!--起始流水-->
													<s:text name="storageInOut.startNum" />
												</td>
												<td width="38%" class="inputBox3col_text">
													<input name="vcStorageInOut.startNumFrom" type="text" class="ui-tianan-input-text" />
													<s:text name="store.time.to" />
													<input name="vcStorageInOut.startNumTo" type="text" class="ui-tianan-input-text" />
												</td>
											</tr>
											<tr>												
												<td width="12%" class="inputTitle3col">
													<!--操作时间-->
													<s:text name="storageInOut.operateTime" />
												</td>
												<td width="38%" class="inputBox3col_text">
													<input type="text"  name="vcStorageInOut.startOperateTime"
														value="<fmt:formatDate value="${vcStorageInOut.startOperateTime }" pattern="yyyy-MM-dd"/>"
														class="Wdate taDatePicker" onclick="WdatePicker()" />
													<s:text name="store.time.to" />
													<input type="text"  name="vcStorageInOut.endOperateTime"
														value="<fmt:formatDate value="${vcStorageInOut.endOperateTime }" pattern="yyyy-MM-dd"/>"
														class="Wdate taDatePicker" onclick="WdatePicker()" />
												</td>
												<td width="12%" class="inputTitle3col" >
													出入库								
												</td>
												<td width="38%" class="inputBox3col_text">										
													<select id="vcStorageInOutOperateFlag" name="vcStorageInOut.operateFlag" class="selectStyle" style="width: 155px">
													</select>
												</td>	
											</tr>
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
										&nbsp;
									</td>
								</tr>
								<tr>
									<td height="33">
										<table cellpadding="0" cellspacing="0" border="0" width="100%">
											<tr>
												<td height="28" valign="top">
													<img src="${ctx}/style/default/images/point4.gif"
														class="bottom">
													&nbsp;
													<!--单证出入库列表-->
													<s:text name="storageInOut.title.resultList" />
												</td>
												<td align="right" valign="top" nowrap="nowrap">
													<input id="queryStorageInOut" type="button" class="taButton"
														value="<s:text name="common.submit.query" />" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<input type="reset" id="restBtn" class="taButton"
														value="<s:text name="common.submit.rest" /> "
														onclick="common.clearGridData('docStorageInOutGrid');" />
													&nbsp;&nbsp;&nbsp;&nbsp;
													<button id="storageOut" buttonId="b03" class="taButton"
														type="button">
														<s:text name="storageInOut.button.out" />
													</button>
													&nbsp;&nbsp;&nbsp;&nbsp;
													<button id="storageIn" buttonId="b04" class="taButton"
														type="button">
														<s:text name="storageInOut.button.inAfterOut" />
													</button>
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
													<table id="docStorageInOutGrid" class="inputTable3col"></table>
													<div id="docStorageInOutGridPager"></div>
													<input id="id" name="id" value="" type="hidden">
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
							</table>
						</form>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>