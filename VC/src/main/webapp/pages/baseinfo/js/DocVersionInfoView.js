var docVersionInfoView = {
		
	
	initViewPage : function(){	
		$("#viewDtoIdVcDocTyp").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&trigger=no"
		}); 
		
		//状态
		$("#viewDtoStatus").dropDownList( {
			data : docVersionInfo.statusData
		});
		
		//是否征订
		$("#viewDtoIsOrder").dropDownList( {
			data : docVersionInfo.isOrderData
		});
		
		//自动流水生成标志
		$("#viewDtoIsAutoGenNo").dropDownList( {
			data : docVersionInfo.isAutoGenNoData
		});	
		
		//联次
		$("#viewDtoDocCountCode").dropDownList( {
			 url: "/visa/baseinfoJson/initDownList.do?codeType=DocCountCode&actionType=query"			
		});
		
		
		//设置默认值
		setTimeout(function(){
			$("#viewDtoIdVcDocTyp").dropDownList("selected",$("#hide_viewDtoIdVcDocTyp").val()); 
			$("#viewDtoIsAutoGenNo").dropDownList("selected",$("#hide_viewDtoIsAutoGenNo").val());
			$("#viewDtoDocCountCode").dropDownList("selected",$("#hide_viewDtoDocCountCode").val());
			$("#viewDtoIsOrder").dropDownList("selected",$("#hide_viewDtoIsOrder").val());
			$("#viewDtoStatus").dropDownList("selected",$("#hide_viewDtoStatus").val());
		},300);
		
		$("#viewForm :input").attr('readOnly','readOnly');	
		 //$("#backToDocVersionInfoMainBut").attr('disabled','enabled');	
		 $("#fromViewBackToDocVersionInfoMainBut").removeAttr("disabled");
		
		/**
		* @description 返回查询页面事件
		* @param  backBut 返回按钮ID
		* @return 
		*/
		$("#fromViewBackToDocVersionInfoMainBut").click( function() {
			$("#toAddDocVersionInfoView").dialog('close');
		});
	}
	
	

}