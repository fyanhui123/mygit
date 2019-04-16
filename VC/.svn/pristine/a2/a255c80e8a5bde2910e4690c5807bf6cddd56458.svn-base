var orderGatherEdit = {
		
	initPage : function() {
		/**
		 * 初始化表单验证组件
		 */
		$("#gatherEditForm").validate();
		
		/**
		 * 返回按钮添加事件
		 */
		$("#gatherEditBackBtn").click(function(){
			$('#gatherEditPage').dialog('close');
		});
		
		/**
		 * 保存按钮添加事件
		 */
		$("#gatherEditSaveBtn").click(function(){
			if($("#gatherEditForm").validate().form()){
				$("#gatherEditSaveBtn").attr("disabled",true);
				$("#gatherEditSubBtn").attr("disabled",true);
				var formStr = $("#gatherEditForm").serialize();
				var orderId = $("#orderId").val();
				var detIdArr = $("input[name='detId']");
				var applyPrintNumArr = $("input[name='applyPrintNum']");
				formStr = "orderId="+orderId;
				for(i=0;i<detIdArr.length;i++){
					formStr += "&orderLaunchDetVos["+i+"].id="+detIdArr[i].value;
					formStr += "&orderLaunchDetVos["+i+"].applyPrintNum="+applyPrintNumArr[i].value;
				}
				common.ajaxSubmitDfCallBack(orderGatherEdit.saveCallback, $.taHash.get("ctx")+"/orderManager/updateOrderGather.do?ajax=true", formStr);
			}else{
				return false;
			}
		});
		
		/**
		 * 提交按钮添加事件
		 */
		$("#gatherEditSubBtn").click(function(){
			if($("#gatherEditForm").validate().form()){
				$("#gatherEditSaveBtn").attr("disabled",true);
				$("#gatherEditSubBtn").attr("disabled",true);
				var formStr = $("#gatherEditForm").serialize();
				var orderId = $("#orderId").val();
				var detIdArr = $("input[name='detId']");
				var applyPrintNumArr = $("input[name='applyPrintNum']");
				formStr = "orderId="+orderId;
				for(i=0;i<detIdArr.length;i++){
					formStr += "&orderLaunchDetVos["+i+"].id="+detIdArr[i].value;
					formStr += "&orderLaunchDetVos["+i+"].applyPrintNum="+applyPrintNumArr[i].value;
				}
				common.ajaxSubmitDfCallBack(orderGatherEdit.saveCallback, $.taHash.get("ctx")+"/orderManager/updateOrderGather.do?ajax=true&bizType=submit", formStr);
			}else{
				return false;
			}
		});
	},
	
	saveCallback : function(data){
		alert("操作成功！");
		$('#gatherEditPage').dialog('close');
		$("#gatherEditSaveBtn").removeAttr("disabled");
		$("#gatherEditSubBtn").removeAttr("disabled");
		orderGather.gridReload();
	}
}