var orderGather = {
		
	loadGridUrl : "/orderJson/queryOrderGather.do?ajax=true",
		
	initPage: function(){
	
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#gatherInputPage").dialog({
			modal: true,
			height:450,
			width:950,
			autoOpen: false
		});
		
		$("#gatherEditPage").dialog({
			modal: true,
			height:450,
			width:950,
			autoOpen: false
		});
		
		$("#gatherViewPage").dialog({
			modal: true,
			height:450,
			width:950,
			autoOpen: false
		});
		
		$("#gatherSubViewPage").dialog({
			modal: true,
			height:300,
			width:750,
			autoOpen: false
		});
		
		/**
		* @description 查询按钮事件
		* @param  queryOrderGather 查询按钮ID
		* @return 
		*/
		$("#queryOrderGather").click( function() {
			orderGather.gridReload();
		});
		
		/**
		* @description 重置按钮事件
		* @param  restBtn 重置按钮ID
		* @return 
		*/
		$("#reset").click( function() {
			//清空查询条件
			$("#orderCode").val("");
			$("#flag").val("");
			$("#applyStartDate").val("");
			$("#applyEndDate").val("");
			//清空选中的id
			$("#id").val("");
			//清空表格数据
			$("#orderGatherGrid").clearGridData();
		});
		
		/**
		 * 新增按钮事件
		 */
		$("#addOrderGather").click(function(){
			//重置多行输入域
			$("#gatherInfoDiv").html("<span id='gatherInfoSpan'></span>");
			//重置新增页面的查询结果
			$("#gatherInputReset").trigger("click");
			//弹出新增页面
			$('#gatherInputPage').dialog('open');
		});
		
		/**
		 * 修改按钮事件
		 */
		$("#modifyOrderGather").click(function(){
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					//校验订单状态
					$.post($.taHash.get("ctx")+"/orderManager/checkOrderLaunch.do?ajax=true",{id:id},function(data,testStuatus){
						if(data=="1"){
							//ajax获取要修改的数据
							common.ajaxGetData("gatherEditPage", $.taHash.get("ctx")+"/orderManager/preUpdateOrderGather.do?ajax=true&id="+id,null);
							//调用dialog组件
							$('#gatherEditPage').dialog('open');
						}else if(data=="0"){
							alert("请选择需要修改的订单！");
						}else if(data=="2"){
							alert("选择的订单不存在，请确认！");
						}/*else if(data=="3"){
							alert("已提交审批的订单不能修改！");
						}else if(data=="4"){
							alert("已审批通过的订单不能修改！");
						}else if(data=="5"){
							alert("采购中的订单不能修改！");
						}*/else if(data=="3" || data=="4" || data=="5"){
							alert("只能修改'新建'或'审批退回'状态的订单！");
						}
					});
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		/**
		 * 浏览按钮事件
		 */
		/*$("#viewOrderGather").click(function(){
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					//ajax获取要修改的数据
					common.ajaxGetData("gatherViewPage", $.taHash.get("ctx")+"/orderManager/viewOrderGather.do?ajax=true&id="+id,null);
					//调用dialog组件
					$('#gatherViewPage').dialog('open');
				}
			}else{
				alert("请选择一条记录！");
			}
			
		});*/
		
		/**
		 * 删除按钮事件
		 */
		$("#deleteOrderGather").click(function(){
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//校验订单状态
				$.post($.taHash.get("ctx")+"/orderManager/checkOrderLaunch.do?ajax=true",{id:id},function(data,testStuatus){
					if(data=="1"){
						//校验成功，可以删除
						/*$.post($.taHash.get("ctx")+"/orderManager/deleteOrderLaunch.do",{id:id},function(data,testStuatus){
							alert("删除成功！");
							//orderLaunch.gridReload();
						}*/
						if(confirm("确定删除?")){
							//common.ajaxSubmit("gatherListPage", $.taHash.get("ctx")+ "/orderManager/deleteOrderGather.do?ajax=true", {id : id});
							//orderGather.gridReload();
							common.ajaxSubmitDfCallBack(orderGather.callBackReload, $.taHash.get("ctx")+ "/orderManager/deleteOrderGather.do?ajax=true", {id : id});
						}
					}else if(data=="0"){
						alert("请选择需要删除的订单！");
					}else if(data=="2"){
						alert("选择的记录中有不存在的订单，请确认！");
					}/*else if(data=="3"){
						alert("已提交审批的订单不能删除！");
					}else if(data=="4"){
						alert("已审批通过的订单不能删除！");
					}else if(data=="5"){
						alert("采购中的订单不能删除！");
					}*/else if(data=="3" || data=="4" || data=="5"){
						alert("只能删除'新建'或'审批退回'状态的订单！");
					}
				});
			}else{
				alert("请选择需要删除的记录！");
			}
		});
		
		/**
		 * 提交按钮事件
		 */
		$("#submitOrderGather").click(function(){
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//校验订单状态
				$.post($.taHash.get("ctx")+"/orderManager/checkOrderLaunch.do?ajax=true",{id:id},function(data,testStuatus){
					if(data=="1"){
						//校验成功，可以提交
						//common.ajaxSubmit("gatherListPage", $.taHash.get("ctx")+ "/orderManager/submitOrderGather.do?ajax=true", {id : id});
						//orderGather.gridReload();
						common.ajaxSubmitDfCallBack(orderGather.callBackReload, $.taHash.get("ctx")+ "/orderManager/submitOrderGather.do?ajax=true", {id : id});
					}else if(data=="0"){
						alert("请选择需要提交的订单！");
					}else if(data=="2"){
						alert("选择的记录中有不存在的订单，请确认！");
					}/*else if(data=="3"){
						alert("已提交审批的订单不能重复提交！");
					}else if(data=="4"){
						alert("已审批通过的订单不能提交审批！");
					}else if(data=="5"){
						alert("采购中的订单不能提交审批！");
					}*/else if(data=="3" || data=="4" || data=="5"){
						alert("只能提交'新建'或'审批退回'状态的订单！");
					}
				});
			}else{
				alert("请选择需要提交的订单！");
			}
		});
		
		$("#orderGatherGrid").jqGrid({
			url : $.taHash.get("ctx")+ orderGather.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','订单号','申请机构', '申请人', '申请时间', '订单状态'],
			colModel : [{
						name : 'orderId',
						index : 'orderId',
						hidden : true
					},{
						name : 'orderCode',
						index : 'orderCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'orgName',
						index : 'orgName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'createUserName',
						index : 'createUserName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'createDate',
						index : 'orderLaunchVo.createDate',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}
					},{
						name : 'flag',
						index : 'flag',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#orderGatherGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "orderLaunchList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "orderId"
		     },
			multiselect: true,
			
			/**
			 * 给订单号添加链接
			 */
			gridComplete: function(){
				ids = $("#orderGatherGrid").jqGrid('getDataIDs');
				for(var i=0;i < ids.length;i++){
					var rowArray = $("#orderGatherGrid").jqGrid('getRowData',ids[i]);
					var editBtn = "<a href='#' onclick='orderGather.viewOrderGather("+rowArray.orderId+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.orderCode+"</a>";
					$("#orderGatherGrid").jqGrid('setCell', ids[i], 'orderCode', editBtn);
				}
			},
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("orderGatherGrid");
				$("#id").val(selectValue);
			},
			
			/**
			* @description 全选触发时候获取所有选中的Id值
			* @return 
			*/
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#id").val("");
				}else{//全部选中时
					var ids = $("#orderGatherGrid").jqGrid('getDataIDs');
					$("#id").val(ids);
				}
			}
		});
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#id").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ orderGather.loadGridUrl;
		jQuery("#orderGatherGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				orderCode : $("#orderCode").val(),
				flag : $("#flag").val(),
				applyStartDate : $("#applyStartDate").val(),
				applyEndDate : $("#applyEndDate").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#orderGatherGrid").trigger("reloadGrid");
	},
	
	/**
	 * 浏览汇总订单
	 */
	viewOrderGather : function(id){
		//ajax获取要修改的数据
		common.ajaxGetData("gatherViewPage", $.taHash.get("ctx")+"/orderManager/viewOrderGather.do?ajax=true&id="+id,null);
		//调用dialog组件
		$('#gatherViewPage').dialog('open');
	},
	
	/**
	 * 删除、提交回调函数
	 */
	callBackReload : function(){
		alert("操作成功！");
		orderGather.gridReload();
	},
	
	/**
	 * 开始时间不能小于结束时间
	 */
	checkDate : function() {
		var startTime=$("#applyStartDate").val();
	    var endTime=$("#applyEndDate").val();
	    if(startTime=="" || endTime==""){
	    	return true;
	    }
	    var start=new Date(startTime.replace("-", "/").replace("-", "/"));
	    var end=new Date(endTime.replace("-", "/").replace("-", "/"));
	    if(end<start){
	    	alert("开始时间不能小于结束时间！");
	    	$("#applyStartDate").val("");
	    	$("#applyEndDate").val("");
	        return false;
	    }
	    return true;
	}
}