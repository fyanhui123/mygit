<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<!--引入jsp配对的js文件 -->
<script type="text/javascript" src="${ctx}/pages/store/js/StatusValues.js"></script>
<script type="text/javascript" src="${ctx}/pages/store/js/ProvideConfirm.js"></script>

<script type="text/javascript">
 $(document).ready(function () {
	 provideConfirm.initQueryPage();
 }); 
</script>
</head>
<body>
<div class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('trbProvideIntegrated','hideImg1')">
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp;<s:text name="common.form.entryQuertyCondition" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trbProvideIntegrated">
						<td>
							<table class="inputTable3col" >
								<tr>
									<td width="20%" class="inputTitle3col" align="right">
										发放单号
									</td>
									<td width="400px" class="inputBox3col">
										<input id="provideCode" name="provideCode" /> 
									</td>
									<td width="20%" class="inputTitle3col" align="right">
										单证类型代码
									</td>
									<td width="400px" class="inputBox3col">
										<input id="docVerCode" name="docVerCode" />
									</td>
								</tr>
								<tr>
									<td width="20%" class="inputTitle3col" align="right">
										发放机构
									</td>
									<td width="400px" class="inputBox3col">
										<input id="provideOrg" name="provideOrg" /> 
									</td>
									<td width="20%" class="inputTitle3col" align="right">
										发放审批人
									</td>
									<td width="400px" class="inputBox3col">
										<input id="provideApprove" name="provideApprove" />
									</td>
								</tr>
								<tr>
									<td width="20%" class="inputTitle3col" align="right">
										申领人类型
									</td>
									<td width="400px" class="inputBox3col">
										<select>
											<option>部门</option>
											<option>个人</option>
										</select>										
									</td>
									<td width="20%" class="inputTitle3col" align="right">
										申领人代码
									</td>
									<td width="400px" class="inputBox3col">
										<input id="applyOpr" name="applyOpr" />
									</td>
								</tr>	
								<tr>
									<td width="20%" class="inputTitle3col" align="right">
										起始流水号
									</td>
									<td width="400px" class="inputBox3col">
										<input type="text" id="startNum" name="startNum" />										
									</td>
									<td width="20%" class="inputTitle3col" align="right">
										结束流水号
									</td>
									<td width="400px" class="inputBox3col">
										<input type="text" id="endNum" name="endNum" />
									</td>
								</tr>
								<tr>
									<td width="20%" class="inputTitle3col" align="right">
										发放数量
									</td>
									<td width="400px" class="inputBox3col">
										（&nbsp;<input style="width: 50px" type="text" id="startCount" name="startCount" />&nbsp;，&nbsp;<input style="width: 50px" type="text" id="endCount" name="endCount" />&nbsp;）										
									</td>
									<td width="20%" class="inputTitle3col" align="right">
										发放日期
									</td>
									<td width="400px" class="inputBox3col">
										<input type="text" id="startDate" name="startDate" />至<input type="text" id="endDate" name="endDate" />
									</td>
								</tr>
								<tr>
									<td width="20%" class="inputTitle3col" align="right">
										是否结算
									</td>
									<td width="400px" class="inputBox3col">
										<select>
											<option>是</option>
											<option>否</option>
										</select>										
									</td>
									<td width="20%" class="inputTitle3col" align="right">
										结算金额
									</td>
									<td width="400px" class="inputBox3col">
										（&nbsp;<input style="width: 50px" type="text" id="startSum" name="startSum" />&nbsp;，&nbsp;<input style="width: 50px" type="text" id="endSum" name="endSum" />&nbsp;）
									</td>
								</tr>
								<tr>
									<td class="inputTitle3col_btn" colspan="4">
										<input id="queryProvideConfirm" type="button" class="button" value="<s:text name="common.submit.query" />" />&nbsp;&nbsp;&nbsp;&nbsp;
			   							<input id="resetProvideConfirm" type="reset" class="button" value="<s:text name="common.submit.rest" /> " />
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr onclick="common.hideTR('trcProvideConfirm','hideImg2');">
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom"> &nbsp; 已发放列表
									</td>
								</tr>
								<tr>
									<td height="13" background="${ctx}/style/default/images/devide-main.gif"></td>
									<td height="13" width="13" background="${ctx}/style/default/images/devide-r.gif"></td>
								</tr>								
							</table>
						</td>
					</tr>
					<tr id="trcProvideConfirm">
						<td>
							<table class="pagerTable">
								<tr>
									<td>
										<table class="pagerTable" style="width: 100%">
											<tr>
												<td width="100%">
													<table id="provideConfirmGrid" class="inputTable3col"></table>
													<div id="provideConfirmGridPager"></div>
													<input id="id" name="id" value="" type="hidden">												
												</td>
											</tr>
										</table>
									</td>
								</tr>
								<tr>
									<td width="100%">
										<input id="provideConfirmUptBtn" type="button" class="button" value="确 认" />
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
			</td>
		</tr>
	</table>
</div>
<%@ include file="ProvideIntegratedQueryDET.jsp"%>
</body>
</html>

