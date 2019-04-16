var importOrCheckResult = {	
	initPage:function() {
	    var importResult= $("#importResult").val();
		if(importResult=='true'){
			
			/**
			* @description 导入成功，返回主页面事件
			*/
			$("#successCommitBut").click( function() {			
					url=$.taHash.get("ctx")+"/insucard/insuranceCardImport.do";	
				  // common.formSubmit("importOrCheckResultForm", url);			
					$('#importOrCheckResultForm').attr("action",url);			
					$('#importOrCheckResultForm').submit();
			});
			   
		}else{
			/**
			* @description 导入失败，重新导入事件			
			*/	
			$("#submitAgainBut").click( function() {
				history.back(-1);
			});
			
			/**
			* @description 导入失败，返回主页面事件			
			*/
			$("#goToMaintBut").click( function() {			
					url=$.taHash.get("ctx")+"/insucard/insuranceCardImport.do";	
				   //common.formSubmit("importOrCheckResultForm", url);			
					$('#importOrCheckResultForm').attr("action",url);			
					$('#importOrCheckResultForm').submit();
			});
	    };
	}
}