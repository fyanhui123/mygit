var vcDocTypeView = {
	
	initViewPage : function(){
		
		$("#viewDtoDocType").dropDownList( {
			 url: "/visa/baseinfoJson/initDownList.do?codeType=DocumentType&actionType=query"
		});
		
	
	//设置默认值
	 setTimeout(function(){	
	      $("#viewDtoDocType").dropDownList("selected",$("#hide_viewDtoDocType").val()); 
	 },250);
	 $("#viewDtoDocTypeCode").attr('disabled','disabled');	
	 $("#viewDtoDocType").attr('disabled','disabled');
	 $("#viewDtoDocTypeName").attr('disabled','disabled');

		
		/**
		* @description 返回查询页面事件
		* @param  backBut 返回按钮ID
		* @return 
		*/
		$("#fromViewBackToDocTypeMainBut").click( function() {			
			$("#toAddDocTypeView").dialog('close');
		});
	}

}