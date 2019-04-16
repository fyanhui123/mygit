var lostApprove = {
	initPage : function() {
	
		$("#approveBackBtn").click(function() {
			$('#lostApprovePage').dialog('close');
		});
		
		//同意
		$("#approvePassBtn").click(function() {
			approve("4","approvePassBtn");			
		});
		
		//退回
		$("#approveRebutBtn").click(function() {
			var approveOpp = $("#approveOpp").val().replace(/^\s+|\s*$/g,"");
			if(approveOpp.length==0){
				alert("请输入审批意见");
				$("#approveOpp").val("");
				return;
			}
			approve("3","approveRebutBtn");
		});
		
		function approve(actionType,butId){
			//禁用按钮
			//$("#"+butId).attr("disabled",true);
			$("#approvePassBtn").attr("disabled",true);
			$("#approveRebutBtn").attr("disabled",true);
			
			// 遗失处理状态（0删除/1新建/2待审批/3审批退回/4审批通过）
			var approveOpp = $("#approveOpp").text();
			// var params = "&approveOpp="+approveOpp+"&actionType="+actionType;
			var params = $("#lostApproveForm").serialize();
			params += "&actionType="+actionType;
			var action = $.taHash.get("ctx")+ "/newinvoiceJson/executeApprove.do?ajax=true";
			common.ajaxSubmitDfCallBack(
					function(data){
						alert(data);
						//取消禁用
						//$("#"+butId).removeAttr("disabled");
						$("#approvePassBtn").removeAttr("disabled");
						$("#approveRebutBtn").removeAttr("disabled");
						if(data == "操作成功"){
							$('#lostApprovePage').dialog('close');
							lostApproveQuery.gridReload();
						}
					}, action, params);
		}
	}
}