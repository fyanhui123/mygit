<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
		<title>天安保险核心系统</title>
		<link rel="Shortcut Icon" href="${ctx}/style/${currentSkin}/images/favicon.ico">
		<link rel="Bookmark" href="${ctx}/style/${currentSkin}/images/favicon.ico">
		<link href="${ctx}/style/${currentSkin}/css/ui/ui.layout.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/style/${currentSkin}/css/ui/ui.jqgrid.css" rel="stylesheet" type="text/css" />
		<link href="${ctx}/style/${currentSkin}/css/ui/jquery-ui.css" rel="stylesheet" type="text/css" />
	    <link href="${ctx}/style/${currentSkin}/css/styles.css" rel="stylesheet" type="text/css" />
	    <link href="${ctx}/style/${currentSkin}/css/validateStyle.css" rel="stylesheet" type="text/css" />
	    <link href="${ctx}/style/${currentSkin}/css/top.css" rel="stylesheet" type="text/css" />
	    <link href="${ctx}/style/${currentSkin}/css/taMenus.css" rel="stylesheet" type="text/css" />
	    <!-- add by chy,用于dtree begin -->
	    <link href="${ctx}/style/${currentSkin}/css/dtree/dtree.css" rel="stylesheet" type="text/css" />	    	    
	    <!-- add by chy,用于dtree end -->	    
	    <!-- add by whj,用于ztree begin -->
        <link rel="stylesheet" href="${ctx}/widgets/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<!-- add by whj,用于ztree end -->
	    
		<!-- 加载js类库 -->
		<script type="text/javascript" src="${ctx}/widgets/jquery/jquery.js" ></script>
		<script type="text/javascript" src="${ctx}/widgets/jquery/jquery.ui.js" ></script>
		<script type="text/javascript" src="${ctx}/widgets/jquery/jquery.layout.js" ></script>
		<script type="text/javascript" src="${ctx}/widgets/jquery/grid.locale-cn.js" ></script>
		<script type="text/javascript" src="${ctx}/widgets/jquery/jquery.jqGrid.js" ></script>
		<script type="text/javascript" src="${ctx}/widgets/jquery/jquery.jstree.js"></script>
	    <script type="text/javascript" src="${ctx}/widgets/jquery/jquery.blockUI.js" ></script>
	    <script type="text/javascript" src="${ctx}/widgets/jquery/jquery.validate.js" ></script>
	  	<script type="text/javascript" src="${ctx}/widgets/jquery/jquery.bgiframe.js"></script>
	    <script type="text/javascript" src="${ctx}/widgets/My97DatePicker/WdatePicker.js"></script>
	    <!-- 自定义js类库 -->
	    <script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.js"></script>  
	    <script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.tree.js"></script>
	   	<script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.hash.js"></script>
	   	<script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.taValidator.js"></script>
	   	<script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.validate.method.js"></script>
	   	<script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.validate.message.zh.js"></script>
		<script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.mulLines.js"></script>
		<script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.taBlockUI.js"></script>
		<script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.checkSelect.js"></script>
		<script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.taMenu.js"></script>
		<script type="text/javascript" src="${ctx}/widgets/taLib/jquery.taComponent.menuWidget.js"></script>
		<script type="text/javascript" src="${ctx}/widgets/taLib/scroll.js"></script>
	    <!-- 用于系统公共方法 -->
	    <script type="text/javascript" src="${ctx}/widgets/taLib/common.js"></script>
	    <!-- add by chy,用于dtree begin -->
	    <script type="text/javascript" src="${ctx}/widgets/dtree/dtree.js"></script>
	    <script type="text/javascript" src="${ctx}/widgets/dtree/dtreeNew.js"></script>
	    <script type="text/javascript" src="${ctx}/widgets/dtree/dtreeUtil.js"></script>	    
	    <!-- add by chy,用于dtree end -->	    
	     <!-- add by whj,用于ztree begin -->
        <script type="text/javascript" src="${ctx}/widgets/zTree/js/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${ctx}/widgets/zTree/js/jquery.ztree.excheck-3.5.js"></script>
		<!-- add by whj,用于ztree end -->
		
		<script type="text/javascript">
			//在系统加载完毕后执行
			$(document).ready(function () {
		        $.taHash.set("ctx","${ctx}");
		        common.ctx="${ctx}";
		        /* document.onkeydown = function(e){
		            e = window.event || e;
		            var keycode = e.keyCode || e.which;
		            if((event.ctrlKey && event.keyCode==82) || (event.ctrlKey && event.keyCode==78) || (event.shiftKey && event.keyCode==121) || keycode >= 112 && keycode <= 123){
		                if(window.event){// ie
		                    try{e.keyCode = 0;}catch(e){}
		                    e.returnValue = false;
		                }else{// ff
		                    e.preventDefault();
		                    e.returnValue = false;
		                }
		            }
		        } 
		        
		        document.oncontextmenu= function(e){
		            e = window.event || e;
		            if(window.event){
		                try{e.keyCode = 0;}catch(e){}
		                e.returnValue = false;
		            }else{// ff
		                e.preventDefault();
		                e.returnValue = false;
		            }
		        } */
			});
			
			$(function(){
				common.reFreshWindow();
			
			});
		</script>
		
		<decorator:head />
		<script type="text/javascript">
			$(document).ready(function () {
			   common.initMessage(${messageInfo});
			});
		</script>
	</head>

	<body marginwidth="0" marginheight="0" topmargin="0" leftmargin="0" style="overflow: auto">
		<decorator:body />
		<!-- 页面公共隐藏域 -->
		<%@ include file="/common/commonFormHeader.jsp"%>
		<!-- message的弹出层 start -->
		<%@ include file="messageDialog.jsp"%>
		<!-- message的弹出层 end -->

	</body>

</html>
