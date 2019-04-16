<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>

<div id = "relAreaPage" style="width: 100%">	
	<input type="hidden" id="docVersioninfoId_toRelArea">	
	<table width="100%"  style="width: 100%">
		<tr >
           <td height="33" >
			<table cellpadding="0" cellspacing="0" border="0" width="100%">
				<tr>
					<td class="f-black" height="28" valign="top">
						<img src="${ctx}/style/default/images/point4.gif" class="bottom">
						&nbsp;地区关联
					</td>
					<td align="right" valign="top"  nowrap="nowrap">
						<input type="button" id=btnSubmitRelArea
								value="<s:text name="common.submit.save" />" />
					</td>
				</tr>				
				<tr>
					<td class="pageDevide" colspan="2"></td>
				</tr>
			</table>
           </td>
		</tr>
		<tr>
		 <td >
		  <div style="padding-left: 10%;">		   
			[<a id="collapseAllBtn" href="#" title="全部折叠" style="color:red;text-decoration :underline;">折叠</a>			
			/<a id="expandAllBtn" href="#" title="全部展开" style="color:red;text-decoration :underline;">展开</a>]
		  </div>
		 </td>
		</tr>
		<tr >
		 <td>
             <div id="relAreaZTree"  class="ztree" style="padding-left: 10%;"></div>
		  </td>
		</tr>
	</table>
</div>

<script type="text/javascript">
	relAreaPage.initRelAreaPage();
</script>

