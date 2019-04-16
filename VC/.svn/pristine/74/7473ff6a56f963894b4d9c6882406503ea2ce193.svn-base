var companyEdit = {
		
	initPage : function(){
		
		/**
		 * 初始化表单验证组件
		 */
		$("#companyForm").validate();
		
		/**
		 * 保存按钮事件
		 */
		$("#saveCompany").click( function(){
			if($("#companyForm").validate().form()){
				$("#saveCompany").attr("disabled",true);
				var formStr = $("#companyForm").serialize();
				common.ajaxSubmitDfCallBack(companyEdit.saveCallback, 
						 $.taHash.get("ctx")+"/vclevelJson/saveCompany.do?ajax=true", 
						 formStr);
			}else{
				return false;
			}
		});
		
		/**
		 * 返回按钮事件
		 */
		$("#comReturn").click( function(){
			$('#companyEditPage').dialog('close');
		});
		
		/**
		 * 机构类型事件
		 */
//		$("#comType").change(function() {
//			$("#comCode").val("");
//			$("#comCodeName").val("");
//			
//			var comType = $("#comType").val();
//			var upperComCode = $("#comUpperCode").val();
//			/**
//			 * 初始化自动完成组件
//			 */
//			$( "#comCode" ).taAutoComplete({
//				source: $.taHash.get("ctx")+"/vclevelJson/getJSONCompanyList.do?ajax=true&comType="+comType+"&upperComCode="+upperComCode,
//				minLength: 0,
//				height: 200,
//				width:220
//			});
//		});
	},
	
	
	/**
	 * 保存的回调函数
	 */
	saveCallback : function(data){
		if("0"==data){
			alert("机构已存在，不能重复！");
			$("#saveCompany").removeAttr("disabled");
		}else if("1"==data){
			alert("保存成功！");
			//关闭dialog
			$('#companyEditPage').dialog('close');
			$("#saveCompany").removeAttr("disabled");
			//重新加载机构数
			vcLevelSet.reloadCompanyTree();
		}
	}
}