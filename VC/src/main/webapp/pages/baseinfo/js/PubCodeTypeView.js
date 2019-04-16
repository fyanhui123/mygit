var pubCodeTypeView = {
  
	initViewPage : function(){		
	
			/*$("#pubCodeList").mulLineWidget({
				hiddenPlus: '1',
				hiddenSubtraction: '1',
			    columnData: pubCodeType.pubCodeArry,
				exeFuction: pubCodeType.resetVal ,					
				initDataUrl: $.taHash.get("ctx")+"/baseinfoJson/queryJsonVcPubCodeListByTypeId.do?ajax=true&idVcPubCodeType="+$("#currentIdVcPubCodeType").val()
		     });*/
			//$("#pubCodeList").attr('disabled','disabled');
			// $("#pubCodeTypeForm :input").attr('disabled','disabled');			
			 //$("#backToPubCodeTypeMainBut").removeAttr("disabled");
		

		
	
		
		/**
		* @description 返回查询页面事件
		* @param  backBut 返回按钮ID
		* @return 
		*/
		$("#fromViewBackToPubCodeTypeMainBut").click( function() {
			$("#toViewPubCodeType").dialog('close');
		});
	}
}