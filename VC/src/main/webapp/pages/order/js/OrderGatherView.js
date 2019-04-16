var orderGatherView = {
	
	initPage: function(){
	
		/**
		 * 返回按钮事件
		 */
		$("#gatherViewBackBtn").click(function(){
			$("#gatherViewPage").dialog("close");
		});
		
		/**
		 * 查看订单明细
		 */
		$("#viewOrderSubBtn").click(function(){
			var orderId = $("#gatherOrderId").val();
			//ajax获取要修改的数据
			common.ajaxGetData("gatherSubViewPage", $.taHash.get("ctx")+"/orderManager/viewSubOrder.do?ajax=true&orderId="+orderId,null);
			//调用dialog组件
			$('#gatherSubViewPage').dialog('open');
		});
	}
}