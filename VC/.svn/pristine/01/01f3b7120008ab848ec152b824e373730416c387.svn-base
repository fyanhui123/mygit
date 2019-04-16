function init(){
	var actionType = $("#actionType").val();
	var message = $("#message").val();
	if(message && message!=''){
		alert(message);
	}
	if('before' == actionType){
		$("#generateReportBtn").attr("disabled",true);
		$("#exportReportBtn").attr("disabled",true);
	}else if('query' == actionType){
		$("#exportReportBtn").attr("disabled",true);
	}else if('generate' == actionType){
		$("#queryReportBtn").attr("disabled",true);
		$("#generateReportBtn").attr("disabled",true);
	}else if('reportDetail' == actionType){
		$("#startDate").attr("disabled",true);
		$("#endDate").attr("disabled",true);
		$("#queryReportBtn").attr("disabled",true);
		$("#generateReportBtn").attr("disabled",true);
	}
}

function submitQuery() {
	$("#queryReportBtn").attr("disabled",true);
	$("#generateReportBtn").attr("disabled",true);
	$("#exportReportBtn").attr("disabled",true);
	var url = $.taHash.get("ctx")+"/invoice/queryReportBeforeGererate.do";
	document.getElementById("editForm").action=url;
	document.getElementById("editForm").submit();
}

function submitGenerate() {
	$("#queryReportBtn").attr("disabled",true);
	$("#generateReportBtn").attr("disabled",true);
	$("#exportReportBtn").attr("disabled",true);
	var url = $.taHash.get("ctx")+"/invoice/generateReport.do";
	document.getElementById("editForm").action=url;
	document.getElementById("editForm").submit();
}

function exportFile() {
	$("#queryReportBtn").attr("disabled",true);
	$("#generateReportBtn").attr("disabled",true);
	$("#exportReportBtn").attr("disabled",true);
	var formStr = $("#editForm").serialize();
	var url = $.taHash.get("ctx")+ "/invoiceJson/exportReport.do?ajax=true&"+formStr;
	common.ajaxSubmitDfCallBack(exportHref, url, null);
}

function exportHref(data) {
	if(!data || data==""){
		alert("生成文件失败！");
		$("#exportReportBtn").removeAttr("disabled");
		return false;
	}
	var filePath=data.substring(0,data.indexOf("=="));
	var fileName=data.substring(data.indexOf("==")+2, data.length);
	var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
	url = encodeURI(url, "UTF-8");
	window.location.href=url;
	$("#exportReportBtn").removeAttr("disabled");
}

function returnBack(){
	window.history.back();
}
