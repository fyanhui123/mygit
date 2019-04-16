var orderAppEdit = {
	
	initPage: function(){
	
		/**
		 * 初始化表单验证组件
		 */
		$("#appEditForm").validate();
		
		/**
		 * 返回按钮事件
		 */
		$("#appBackBtn").click(function(){
			$('#appEditPage').dialog('close');
		});
		
		/**
		 * 同意按钮事件
		 */
		$("#appPassBtn").click(function(){
			if($("#appEditForm").validate().form()){
				$("#appPassBtn").attr("disabled",true);
				$("#appUnPassBtn").attr("disabled",true);
				var formStr = $("#appEditForm").serialize();
				common.ajaxSubmitDfCallBack(orderAppEdit.callBackReload, $.taHash.get("ctx")+"/orderManager/approvePass.do?ajax=true&checkStatus=2", formStr);
			}else{
				return false;
			}
		});
		
		/**
		 * 退回按钮事件
		 */
		$("#appUnPassBtn").click(function(){
			if($("#appEditForm").validate().form()){
				$("#appPassBtn").attr("disabled",true);
				$("#appUnPassBtn").attr("disabled",true);
				var formStr = $("#appEditForm").serialize();
				common.ajaxSubmitDfCallBack(orderAppEdit.callBackReload, $.taHash.get("ctx")+"/orderManager/approvePass.do?ajax=true&checkStatus=1",formStr);
			}else{
				return false;
			}
		});
	},
	
	callBackReload : function(){
		alert("操作成功！");
		$('#appEditPage').dialog('close');
		$("#appPassBtn").removeAttr("disabled");
		$("#appUnPassBtn").removeAttr("disabled");
		orderApprove.gridReload();
	}
}