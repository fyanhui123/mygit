<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id="editPageProvideConfirm" class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('trbProvideIntegratedEdit','hideImg1')">
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
					<tr id="trbProvideIntegratedEdit">
						<td>
							<table class="inputTable3col" >
								<tr>
									<td>
										<table>
											<tr>
												<td class="inputTitle3col" align="right">单证类型代码</td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="vcProvide_idVcProvide" value="${vcProvide.idVcProvide }"  type="hidden" />
													<input id="applyCode" value="${vcProvide.applyCode }" name="applyCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="right">单证类型代码名称</td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="provideCode" value="${vcProvide.provideCode }" name="provideCode" type="text" disabled="disabled" />
												</td>
											</tr>										
											<tr>
												<td class="inputTitle3col" align="right">申证种类</td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyOrgCode" value="${vcProvide.applyOrgCode }" name="applyOrgCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="right"></td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="right">发放人类型</td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyOprCode" value="${vcProvide.applyOprCode }" name="applyOprCode" type="text" disabled="disabled" />
												</td>
												<td class="inputTitle3col" align="right">发放人名称</td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyOprName" value="${vcProvide.applyOprName }" name="applyOprName" type="text" disabled="disabled"/>
												</td>
											</tr>											
											<tr>
												<td class="inputTitle3col" align="right">申领人类型</td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyTime" value="${vcProvide.applyTime }" name="applyTime" type="text" disabled="disabled" />
												</td>											
												<td class="inputTitle3col" align="right">申领人名称</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="right">发放审批人名称</td>
												<td class="inputBox3col_text" nowrap="nowrap">
													<input id="applyTime" value="${vcProvide.applyTime }" name="applyTime" type="text" disabled="disabled" />
												</td>											
												<td class="inputTitle3col" align="right">下发性质</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="right">经办人名称</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>											
												<td class="inputTitle3col" align="right"></td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="right">起始流水号</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>											
												<td class="inputTitle3col" align="right">终止流水号</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="right">发放数量</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>											
												<td class="inputTitle3col" align="right"></td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="right">是否结算</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>											
												<td class="inputTitle3col" align="right">结算金额</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>
											</tr>
											<tr>
												<td class="inputTitle3col" align="right">发放日期</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>											
												<td class="inputTitle3col" align="right">操作员代码</td>
												<td class="inputBox3col_text" nowrap="nowrap">
												</td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						</td>
					</tr>					
				</table>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
 $(document).ready(function () {
	 provideConfirm.initEditPage();
 }); 
</script>
