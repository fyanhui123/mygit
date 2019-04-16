var destroyApprove = {
	initPage : function() {
	
		$("#cancelBtn").click(function() {
			$('#destroyApprovePage').dialog('close');
		});
		
		//同意
		$("#destroyAppPass").click(function() {
			destroyApprove("4","destroyAppPass");			
		});
		
		//退回
		$("#destroyAppRebut").click(function() {
			var approveOpp = $("#destroyApproveOpp").text().replace(/^\s+|\s*$/g,"");
			if(approveOpp.length==0){
				alert("请输入审批意见");
				$("#approveOpp").val("");
				return;
			}
			destroyApprove("3","destroyAppRebut");
		});
		
		function destroyApprove(actionType,butId){
			//$("#"+butId).attr("disabled",true);
			$("#destroyAppRebut").attr("disabled",true);
			$("#destroyAppPass").attr("disabled",true);
			
			// actionType -- 遗失处理状态（0删除/1新建/2待审批/3审批退回/4审批通过）
			// var params = "&approveOpp="+approveOpp+"&actionType="+actionType;
			var params = $("#destroyApproveForm").serialize();
			params += "&actionType="+actionType;
			var action = $.taHash.get("ctx")+ "/newDestroyJson/approveDestroy.do?ajax=true";
			common.ajaxSubmitDfCallBack(
					function callBack(data){
				         alert(data);
				         //$("#"+butId).removeAttr("disabled");
				         $("#destroyAppRebut").removeAttr("disabled");
				         $("#destroyAppPass").removeAttr("disabled");
				         if(data == "操作成功"){
					       $('#destroyApprovePage').dialog('close');
					       destroyApproveQuery.gridReload();
				         }
			         } ,
			         action,
			         params);
		}
		/*function callBack(data){
			alert(data);
			if(data == "操作成功"){
				$('#destroyApprovePage').dialog('close');
				destroyApproveQuery.gridReload();
			}
		}*/
	}
}