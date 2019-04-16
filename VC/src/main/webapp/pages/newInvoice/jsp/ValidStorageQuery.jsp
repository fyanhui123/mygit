<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id="validStorageDiv" class="tab-center">
	<table style="width: 100%">
		<tr>
			<td id="mainTableLeftTd">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr>
						<td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="store.validStorageList" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide"></td>
								</tr>
								<tr>
									<td>
										<div style="display: none;">
											<input type="hidden" id="validStorageQuery_docVerCode" />
											<input type="hidden" id="validOrg" />
											<input type="hidden" id="validDocStatus" />
											<input type="hidden" id="validQueryType" />
											<input type="hidden" id="rowIndex"/>
											<%--add by zhxiao3 VC-97 begin --%>
											<input type="hidden" id="validDeadline"/>
											<%--add by zhxiao3 VC-97 end --%>
											<%--add by chy 20130916 --%>
											<input type="hidden" id="tableName"/>
											<input type="button" id="hiddenQueryBtn">
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="trcApply">
						<td>
							<table class="pagerTable" style="width: 100%">
								<tr>
									<td width="100%">
										<table id="validStorageGrid" class="inputTable3col"></table>
										<div id="validStorageGridPager"></div>
										<input id="validStorageId" name="validStorageId" value="" type="hidden">												
									</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr>
						<td>
							&nbsp;&nbsp;&nbsp;&nbsp;
						</td>
					</tr>
					<tr>
						<td align="center">
							<button id="selectStorageRec" buttonId="b20" class="taButton" type="button">
								<s:text name="common.submit.select" />
							</button>
						</td>
					</tr>
				</table>
			</td>
		</tr>
	</table>
</div>
<script type="text/javascript">
$(document).ready(function () {
	validStorage.initQueryPage();
}); 
</script>
