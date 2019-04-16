<%@ page language="java" import="java.util.*" pageEncoding="utf-8"
	isELIgnored="false"%>
<%@ include file="/common/taglib.jspf"%>
<div id="storagePage"  style="width: 100%">
	<div style="width: 98%">
		<span id="storageInfo" style="width: 100%"></span>
	</div>
</div>
<script>
	var storageInfo = {
		 dataArry: [
		{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldClass:"required",type:"2",disabled:true,
			dataFormat:[{source:"/visa/baseinfoJson/queryVcDocVerLabels.do"
				, height:'100px', width:'300px',minLength:0}]},
		{title:"使用人",fieldId:"user",fieldName:"user",type:"4",disabled:true},											
		{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",fieldClass:"required",type:"4",disabled:true},
		{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",fieldClass:"required",type:"4",disabled:true},
		{title:"库存量",fieldId:"docNum",fieldName:"docNum",type:"4",disabled:true},
		{title:"使用截止期",fieldId:"deadline",fieldName:"deadline",type:"4",disabled:true},
		{title:"单证状态",fieldId:"docStatus",fieldName:"docStatus",fieldLableName:"docStatusName",fieldClass:"required",type:"2",disabled:true,
			dataFormat:[{source:[{value:"S1",lable:"印刷入库"},
			{value:"S2",lable:"申领入库"},
			{value:"S3",lable:"回收入库"},
			{value:"A",lable:"可使用"}],minLength:0}]}
		],
	}
</script>