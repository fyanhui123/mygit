
<%
	/*
	 * 使用人单证回收添加
	 ***************************************
	 * 程序名：ServantDocumentIssuedAdd.jsp
	 * 建立日期：2013-4-8
	 * 作者：Leo
	 * 模块：库存管理-使用人单证发放
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

<div id="docRecoveryAddPage" style="width: 100%">
	<form id="addForm" name="addForm" action="#" method="post"
		enctype="multipart/form-data">
		<table id="mainTable" style="width: 100%">
			<tr>
				<td>
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<!--使用人单证回收录入-->
								<s:text name="store.docRecoveryInput" />
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
					<table width="100%" class="inputTable3col">
						<tr>
							<td width="12%" class="inputTitle3col">
								<!--使用人 -->
								<s:text name="store.taker" />
							</td>
							<td width="38%" class="inputBox3col_text">
								<input id="editTakerCode" name="takerCode" type="text"
									class="ui-autocomplete-input" />
								<input id="editTakerCodeName" type="text"
									class="ui-widget-content ui-autocomplete-label"
									readonly="readonly" />
							</td>
							<td width="12%" class="inputTitle3col">
								<!--使用人机构 -->
								<s:text name="store.takerOrg" />
							</td>
							<td>
								<input id="takerOrgName" type="text" readonly="readonly"/>
								<input id="takerOrgCode" name="acceptOrgCode" type="hidden" />
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
					<table cellpadding="0" cellspacing="0" border="0" width="100%">
						<tr>
							<td class="f-black" height="20" valign="top">
								<img src="${ctx}/style/default/images/point4.gif" class="bottom">
								&nbsp;
								<!--单证列表 -->
								<s:text name="store.docVerList" />
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
						<span id="docRecoveryInput" style="width: 100%"></span>
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
					<input type="button" id="saveBut" class="taButton"
						value="<s:text name="common.submit.recover" />" />
					&nbsp;&nbsp;&nbsp;&nbsp;
					<input type="button" id="saveBackBtn" class="taButton" value="<s:text name="store.common.cancel" />" />
				</td>
			</tr>
		</table>
	</form>
</div>
<script type="text/javascript">
	mulLineRecovery.initPageR();
</script>