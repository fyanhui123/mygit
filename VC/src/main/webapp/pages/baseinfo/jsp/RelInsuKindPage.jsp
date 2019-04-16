<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id = "relInsuKindPage" style="width: 100%">	
	<div id="relInsuKindTabs" >
			<table width="100%" style="width: 100%">
				<tr>
					<td id="">
					<form id="userPower1" name="userPower1" action="#" method="post" enctype="multipart/form-data">
					<input type="hidden" id="docVersioninfoId_toRel">
					<table width="100%"  style="width: 100%">
						<tr >
			              <td height="33">
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="28" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;险种关联
									</td>
									<td align="right" valign="top"  nowrap="nowrap">
										<input type="button" id="btnSubmitRelInsuKind"
												value="<s:text name="common.submit.save" />" />
									</td>
								</tr>
								<tr>
									<td class="pageDevide" colspan="2"></td>
								</tr>
							</table>
			              </td>
						</tr>
						<tr id="tr_query">
						  <td>
							<table width="50%">
							  <tr>
								 <td  class="inputTitle3col" >
									<s:text name="vcdoc.insuKind.insuType"></s:text>
								 </td>
								 <td  class="inputBox3col">
									<select id="insuTypeCondition" name="insuTypeCondition" class="selectStyle" style="width: 155px">
								    </select>											
							     </td>
							  </tr>
							</table>
						  </td>	
						</tr>
						<tr id="trb">
							<td>
								<table width="100%">
									<tr>
										<td>
											<div style="float:left;">
												未关联险种：<br/>
												<select id="noRelInsuKind" multiple="multiple"  style="width: 270px; height: 260px;"></select>
											</div>
											<div style="float:left;padding-left:35px;padding-top:100px;">
												<span id="leftToRight" class="ui-icon ui-icon-arrowthick-1-e" style="cursor: hand;"></span>
												<br/><br/><br/><br/>
												<span id="rightToLeft" class="ui-icon ui-icon-arrowthick-1-w" style="cursor: hand;"></span>
											</div> 
											<div style="float:right">
												已关联险种：<br/>
												<select id="haveRelInsuKind" name="roleId" multiple="multiple"  style="width: 270px; height: 260px;"></select>
											</div>
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
</div>

<script type="text/javascript">
	relInsuKindPage.initRelInsuKindPage();
</script>

