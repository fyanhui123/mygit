<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<div id="top">
<div class="getlogo"></div>
<div class="getinfo"><span class="f_red">${userInfo.currentDate}</span> | IP: <span class="f_red">${userInfo.sysAddr}</span><br /><span class="f_black">${userInfo.comName}　${userInfo.userName}</span> | <a href="${ctx}/index/logout.do">用户注销</a> | <a href="javascript:void(0)" onclick="document.getElementById('switchSys').style.display = 'block'">切换系统</a></div>
</div>
<div id="switchSys" onclick="this.style.display='none'">
</div>
<script type="text/javascript">
document.getElementById('switchSys').onclick= function(){
	this.style.display='none';
}
document.getElementById('switchSys').onmouseout= function(e){ 
	if(!e) e = window.event;
	var reltg = e.relatedTarget ? e.relatedTarget : e.toElement;
	while( reltg && reltg != this ) reltg = reltg.parentNode;
	if( reltg != this ){this.style.display='none';}
	}
</script>
