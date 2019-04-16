<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id = "editPage">
	<table width="100%" style="width: 100%">
		<tr>
			<td>
			<form id="contractForm" name="contractForm" action="#" method="post" enctype="multipart/form-data">
				<table width="100%" id="mainTable" style="width: 100%">
					<tr onclick="common.hideTR('tra');">
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="28" valign="center">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="subfunc.contract.entryContract" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2">&nbsp;</td>
								</tr>
							</table>
						</td>
					</tr>
					<tr id="tra">
						<td>
							<table style="width: 100%">
								<tr >
									<td width="12%" >
										<s:text name="subfunc.contract.printingCode" />
										<input value="${contract.id}" name="contract.id" type="hidden"/>
										<input value="${contract.printing.id}" name="contract.printing.id" type="hidden" />
									</td>
									<td width="38%" nowrap="nowrap">
										<input value="${contract.printing.printingCode}" name="contract.printing.printingCode" type="text" minlength="6" maxlength="20" class="required">
									</td>
									<td width="12%" >
										<s:text name="contract.printing.printingName" />
									</td>
									<td width="38%">
										<input name="contract.printing.printingName" value="${contract.printing.printingName}" type="text" class="required"/>
									</td>
								</tr>
								<tr >
									<td width="12%" >
										<s:text name="contract.printing.contact" />
									</td>
									<td width="38%">
										<input value="${contract.printing.contact}" name="contract.printing.contact" type="text" class="required"/>
									</td>
									<td width="12%" >
										<s:text name="contract.printing.tel" />
									</td>
									<td width="38%">
										<input name="contract.printing.tel" value="${contract.printing.tel}" type="text"  class="phone">
									</td>
								</tr>
								<tr >
									<td width="12%" >
										<s:text name="contract.printing.fax" />
									</td>
									<td width="38%">
										<input value="${contract.printing.fax}" name="contract.printing.fax" type="text" />
									</td>
									<td width="12%" >
										<s:text name="contract.printing.email" />
									</td>
									<td width="38%">
										<input name="contract.printing.email" value="${contract.printing.email}" type="text" class="required email"/>
									</td>
								</tr>
								<tr >
									<td width="12%" >
										<s:text name="contract.printing.taxNo" />
									</td>
									<td width="38%">
										<input value="${contract.printing.taxNo}" name="contract.printing.taxNo" type="text" />
									</td>
									<td width="12%" >
										 <s:text name="contract.printing.address" />
									</td>
									<td width="38%">
										<input name="contract.printing.address" value="${contract.printing.address}" type="text" />
									</td>
								</tr>
								
								<tr >
									<td width="12%" >
										<s:text name="contract.printing.bankCode" />
									</td>
									<td width="38%">
										<input value="${contract.printing.bankCode}" name="contract.printing.bankCode" type="text" />
									</td>
									<td width="12%" >
										<s:text name="contract.printing.bankName" />
									</td>
									<td width="38%">
										<input name="contract.printing.bankName" value="${contract.printing.bankName}" type="text" />
									</td>
								</tr>
								
								<tr >
									<td width="12%" >
										<s:text name="contract.printing.bankAccountNo" />
									</td>
									<td width="38%">
										<input value="${contract.printing.bankAccountNo}" name="contract.printing.bankAccountNo" type="text"  class="creditcard"  minlength="15" maxlength="20"/>
									</td>
									<td width="12%" >
										<s:text name="contract.printing.bankAccountName" />
									</td>
									<td width="38%">
										<input name="contract.printing.bankAccountName" value="${contract.printing.bankAccountName}" type="text" />
									</td>
								</tr>
								
								<tr >
									<td width="12%" >
										<s:text name="subfunc.contract.contractNo" />
									</td>
									<td width="38%">
										<input value="${contract.contractNo}" name="contract.contractNo" type="text" />
									</td>
									<td width="12%" >
										<s:text name="subfunc.contract.contractName" />
									</td>
									<td width="38%">
										<input name="contract.contractName" value="${contract.contractName}" type="text" />
									</td>
								</tr>
								<tr >
									<td width="12%" >
										<s:text name="subfunc.contract.contractDate" />
									</td>
									<td width="38%">
										<input name="contract.contractDate" value="${contract.contractDate}" type="text" class="Wdate" onclick="WdatePicker()"/>
									</td>
									<td width="12%" >
										<s:text name="subfunc.contract.deptCode" />
									</td>
									<td width="38%">
										<input id="deptCode" class="deptCode ui-autocomplete-input" value="${contract.deptCode}" name="contract.deptCode" type="text" />
             								<input class="codeLabel ui-widget-content ui-autocomplete-label" value="${codeLabel}" type="text" readonly="readonly" />
										
									</td>
								</tr>
								<tr>
									<td width="12%" >
										<s:text name="subfunc.contract.contractCopy" />
									</td>
									<td width="38%" colspan="3">
										<input class="contractCopy" value="" name="contractCopy" type="file" />
									</td>
								</tr>
								<tr>
									<td colspan="4">
										&nbsp;
									</td>
								</tr>
								<tr>
									<td style="padding-left: 30px;"  colspan="4">
										&nbsp;
										<input type="button" id="saveBut" class="taButton" value="<s:text name="common.submit.save" />"/>
										<input type="button" id="disbBut" class="taButton" value="禁用"/>
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
<script type="text/javascript">
	contract.initEditPage();
</script>
