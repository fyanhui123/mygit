<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<DIV id="messageDialog" class="status-bar div" style="text-align: center; margin: 0; padding: 0; margin-bottom: 0px; margin-left: 0px; margin-right: 0px; left: 0px">
	<%-- <table width="100%" style="padding-bottom: 0px; margin: 0; padding: 0;">
		<tr id="divMessage" style="border: 1px solid #BBBBBB; display: none;">
			<td style="border: 1px solid #BBBBBB; padding-bottom: 0px; text-align: left">
				<br />
				<div id="errorShowArea" class="message">
					&nbsp;
					<strong>你好！</strong>欢迎登陆天安产险核心系统。
					<br />
				</div>
				<div id="errorDialogArea" title="错误信息" class="message">
				</div>
			</td>
		</tr>
		<tr>
			<td style="padding-bottom: 0px; margin: 0px; padding: 0px;">
				<input id="messageButton" type="image" src="${ctx}/style/${currentSkin}/images/layout.gif" onmouseover="$('#messageButton').attr('src','${ctx}/style/${currentSkin}/images/layout_light.gif')" onmouseout="$('#messageButton').attr('src','${ctx}/style/${currentSkin}/images/layout.gif')" onclick="common.divMessageUpAndDown()" style="margin: 0; padding: 0; top: 0px; bottom: 0px;"></input>
			</td>
		</tr>
	</table> --%>
	<div id="errorMsgArea" class="error" title="错误信息" style="color: red">
		<img src="${ctx}/style/${currentSkin}/images/01red.png" width="48" height="48" align="absmiddle" />
		<span style="margin-left: 10px;" id="errorMsgSpan"></span>
	</div>
	<div  id="promptMsgArea" title="提示信息" class="message">
		<img src="${ctx}/style/${currentSkin}/images/06.png" width="48" height="48" align="absmiddle" />
		<span style="margin-left: 10px;" id="promptMsgSpan"></span>
	</div>
</DIV>
<script type="text/javascript">	
$("#promptMsgArea").dialog({		
	modal: true,		
	autoOpen: false,		
	height: 150,
	buttons: {
		"关闭": function() {	
			$( this ).dialog( "close" );
		}			
	}
});

$("#errorMsgArea").dialog({		
	modal: true,		
	autoOpen: false,		
	height: 200,
	buttons: {
		"关闭": function() {	
			$( this ).dialog( "close" );
		}			
	}
});
</script>
