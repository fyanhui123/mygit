var insuranceCardImport = {	
	initPage:function() {	
	
	$("#commitImportBut").validate();	
	  
	/**
	* @description 返回查询页面事件
	* @param  backBut 返回按钮ID
	* @return 
	*/
	$("#backToMainBut").click( function() {			
		//$("#insuCardImport").dialog('close');
		history.back(-1);
	});
	
	/**
	* @description 返回查询页面事件
	* @param  backBut 返回按钮ID
	* @return 
	*/
	$("#commitImportBut").click( function() {
		 //application/vnd.ms-excel
		if($("#insuCardimportForm").validate().form()){
			
			url=$.taHash.get("ctx")+"/insucard/insuranceCardupLoad.do";	
			
		   //common.formSubmit("insuCardimportForm", url);			
			//$('#insuCardimportForm').attr("action",url);	
			$.taBlockUI.message = "处理中，请等待。。。。";
			$('#insuCardimportForm').submit();	
			$.taBlockUI.active();
			//setTimeout(function(){$.taBlockUI.inactive();}, 3000);			
		    //$("#insuCardImport").dialog('close');
					
		}else{			
			 return false;			
		}
			
	});
	
	}
}