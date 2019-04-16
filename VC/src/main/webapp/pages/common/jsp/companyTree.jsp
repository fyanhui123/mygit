<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<div id="companyPage" style="width: 100%">
	<input type="button" id="companyButtom" value="确定"/>
	<div id="companyTree" style="">
		
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function() {
		companyTree.initPage();
	});
</script>
