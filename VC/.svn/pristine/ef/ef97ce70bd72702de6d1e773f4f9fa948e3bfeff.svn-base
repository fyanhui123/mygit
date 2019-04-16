<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
<script type="text/javascript"
	src="${ctx}/pages/inquiry/js/CompanyTree.js"></script>
<script type="text/javascript">
	$(document).ready(function () {	
		companyTree.initPage();
	});
</script>
</head>
<body>

<div id="companyTreeDiv" title="机构" style="width: 100%">
<c:if test="${singleSelect!='true'}">
  <input id="orgSelectConfirmBut" type="button"  class="taButton" value="<s:text name="common.submit.confirm" />" />							
</c:if>
<div id="companyTree" class="checkSelectDiv" style="height: auto; backgroundColor:#84C1FF;z-index: 100;position:absolute">
</div>
<input type="hidden" name="tagCodeId" id="tagCodeId" value="${tagCodeId}"/>
<input type="hidden" name="tagNameId" id="tagNameId" value="${tagNameId}"/>
<input type="hidden" name="singleSelect" id="singleSelect" value="${singleSelect}"/>
</div>
</body>
</html>

