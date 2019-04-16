var docInStoreApprove = {

	initPage : function() {
		/**
		 * 返回按钮事件
		 */
		$("#inStoreAppBackBtn").click(function() {
			$('#docInstoreApprovePage').dialog('close');
		});

		/**
		 * 同意按钮事件  0提交/1审批退回/2审批同意
		 */
		$("#inStoreAppPassBtn").click(function() {
		
		                    $("#inStoreAppPassBtn").attr("disabled",true);
		                    $("#inStoreAppRebutBtn").attr("disabled",true);
							var formStr = $("#docInStoreAppForm").serialize();
							var action = $.taHash.get("ctx")
									+ "/storeJson/approveDocInStore.do?ajax=true&checkStatus=2";
							common.ajaxSubmitDfCallBack(
											docInStoreApprove.callBack, action,
											formStr);
						});

		/**
		 * 退回按钮事件 0提交/1审批退回/2审批同意
		 */
		$("#inStoreAppRebutBtn").click(function() {
		//	var approveOpp =  $("#approveOpp").text().replace(/^\s+|\s*$/g,"");
			var approveOpp =  $.trim($("#approveOpp").val());  
			$("#inStoreAppPassBtn").attr("disabled",true);
		    $("#inStoreAppRebutBtn").attr("disabled",true);
			if(approveOpp.length==0){
				alert("请输入审批意见");
				$("#inStoreAppPassBtn").removeAttr("disabled");
		        $("#inStoreAppRebutBtn").removeAttr("disabled");
				$("#approveOpp").val("");
				return;
			}
					      
			var formStr = $("#docInStoreAppForm").serialize();
			var action = $.taHash.get("ctx")
					+ "/storeJson/approveDocInStore.do?ajax=true&checkStatus=1";
			common.ajaxSubmitDfCallBack(
							docInStoreApprove.callBack, action,
							formStr);
						});
	},

	callBack : function(data) {
		alert(data);
		if(data=="操作成功"){
		        $("#inStoreAppPassBtn").removeAttr("disabled");
		        $("#inStoreAppRebutBtn").removeAttr("disabled");
			 $("#docInstoreApprovePage").dialog('close');
			docInStore.gridReload();
		}
	}

}