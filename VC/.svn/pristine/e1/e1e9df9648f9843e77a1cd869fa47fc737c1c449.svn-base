<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/menuDef.js"></script>
<script type="text/javascript" src="${ctx}/pages/baseinfo/js/commonData.js"></script>
<div id = "editPage" style="width: 100%">
<form id="menuDef" name="menuDef" action="#" method="post" enctype="multipart/form-data">
	<table width="100%" style="width: 100%">
		<tr>
			<td>
			
				<table width="100%" style="width: 100%">
					<tr>
						<td>
							<table cellpadding="0" cellspacing="0" border="0" width="100%">
								<tr>
									<td class="f-black" height="20" valign="top">
										<img src="${ctx}/style/default/images/point4.gif" class="bottom">
										&nbsp;<s:text name="修改菜单信息录入"/>
									</td>
									<td align="right" valign="top"  nowrap="nowrap">
										<input type="button" id="saveBut" value="<s:text name="common.submit.save" />"/>
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
							<table style="line-height: 35px;">
								<tr >
									<td width="15%" align="right">
										 菜单名称：
									</td>
									<td width="20%" >
										<input value="${menuDef.menuName}" name="menuDef.menuName" type="text" class="required">
									</td>
									<td width="15%" align="right">
										菜单序号：
									</td>
									<td>
										<input name="menuDef.menuIndex" value="${menuDef.menuIndex}" type="text" class="required number">
									</td>
								</tr>
								<tr>
									<td  align="right">
										菜单URL：
									</td>
									<td>
										<input value="${menuDef.menuUrl}" name="menuDef.menuUrl" type="text" />
									</td>
								</tr>
								<tr style="display:none;">
									<td>
										<input type="hidden" value="${menuDef.menuId}" name="menuDef.menuId"  />
										<input type="hidden" value="${menuDef.parentId}" name="menuDef.parentId"  />
										<input type="hidden" value="${menuDef.validFlag}" name="menuDef.validFlag" />
										<input type="hidden" value="${menuDef.systemCode}" name="menuDef.systemDef.systemCode"  />
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
</div>

<script type="text/javascript">
	$(document).ready(function () {
		menuDef.initEditPage();
	});
</script>
