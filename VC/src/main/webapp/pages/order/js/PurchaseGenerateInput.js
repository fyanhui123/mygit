var purchaseGenInput = {
		
	loadGridUrl : "/orderJson/queryPurchaseGenInput.do?ajax=true",
	
	generateGridUrl : "/orderJson/generatePruchase.do?ajax=true",
	
	initPage: function(){
		
		/**
		* @description 查询按钮事件
		* @param  queryOrderGather 查询按钮ID
		* @return 
		*/
		$("#queryPurGenInput").click( function() {
			purchaseGenInput.gridReload();
		});
		
		/**
		* @description 重置按钮事件
		* @param  resetInput 重置按钮ID
		* @return 
		*/
		$("#resetInput").click( function() {
			//清空查询条件
			$("#applyStartDateInput").val("");
			$("#applyEndDateInput").val("");
			//清空选中的id
			$("#inputId").val("");
			//清空表格数据
			$("#purchaseInputGrid").clearGridData();
		});
		
		/**
		* @description 生成采购单按钮事件
		* @return 
		*/
		$("#genPurchaseBtn").click( function() {
			//记录选择的订单号
			var selectedIds = $("#inputId").val();
			if(selectedIds==''){
				alert("请选择需要生成采购单的订单！");
				return false;
			}
			$("#selectedId").val(selectedIds);
			purchaseGenInput.generateGrid();
		});
		
		/**
		* @description 取消按钮事件
		* @param  gatherCancelBtn 汇总按钮ID
		* @return 
		*/
		$("#cancelBtn").click( function() {
			$('#purchaseGenInputPage').dialog('close');
		});
		
		$("#purchaseInputGrid").jqGrid({
			url : $.taHash.get("ctx")+ purchaseGenInput.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '125px',
			colNames : ['id','申请单号','申请机构', '申请人', '申请时间', '申请状态'],
			colModel : [{
						name : 'orderId',
						index : 'orderId',
						hidden : true
					},{
						name : 'orderCode',
						index : 'orderCode',						
						width:180,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'orgName',
						index : 'orgName',						
						width:240,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'createUserName',
						index : 'createUserName',						
						width:100,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'createDate',
						index : 'createDate',						
						width:160,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}
					},{
						name : 'flag',
						index : 'flag',						
						width:80,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#purchaseInputGridPager',
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
				ids = $("#purchaseInputGrid").jqGrid('getDataIDs');
				for(var i=0;i < ids.length;i++){
					var rowArray = $("#purchaseInputGrid").jqGrid('getRowData',ids[i]);
					var editBtn = "<a href='#' onclick='purchaseGenInput.viewOrderLaunch("+rowArray.orderId+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.orderCode+"</a>";
					$("#purchaseInputGrid").jqGrid('setCell', ids[i], 'orderCode', editBtn);
				}
			},
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("purchaseInputGrid");
				$("#inputId").val(selectValue);
			},
			
			/**
			* @description 全选触发时候获取所有选中的Id值
			* @return 
			*/
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#inputId").val("");
				}else{//全部选中时
					var ids = $("#purchaseInputGrid").jqGrid('getDataIDs');
					$("#inputId").val(ids);
				}
			}
		});
		
		/**
		 * 生成的采购单列表
		 */
		$("#genListGrid").jqGrid({
			url : $.taHash.get("ctx")+ purchaseGenInput.generateGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',
			colNames : ['id','采购单号','申请机构', '单证类型名称', '单证类型代码', '印刷数量（份）','起始流水号','结束流水号'],
			colModel : [{
						name : 'purchaseId',
						index : 'purchaseId',
						hidden : true
					},{
						name : 'purchaseCode',
						index : 'purchaseCode',
						width : 100,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'orgName',
						index : 'orgName',
						width : 150,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'versionName',
						index : 'versionName',
						width : 150,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'versionCode',
						index : 'versionCode',
						width : 110,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'applyPrintNum',
						index : 'applyPrintNum',
						width : 110,
						resizable : true,
						sortable : false,
						align : 'center'
					},{
						name : 'startSerialNo',
						index : 'startSerialNo',
						width : 120,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'endSerialNo',
						index : 'endSerialNo',
						width : 120,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10000,
			viewrecords : true,
			jsonReader: {
			 	root: "vcPurchaseList",
			    repeatitems : false
			 }
		});
	},
	
	/**
	 * 浏览订单申请
	 */
	viewOrderLaunch : function(id){
		//ajax获取要修改的数据
		common.ajaxGetData("orderViewPage", $.taHash.get("ctx")+"/orderManager/viewOrderLaunch.do?ajax=true&id="+id+"&random="+Math.random(),null);
		//调用dialog组件
		$('#orderViewPage').dialog('open');
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#inputId").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ purchaseGenInput.loadGridUrl;
		jQuery("#purchaseInputGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				applyStartDate : $("#applyStartDateInput").val(),
				applyEndDate : $("#applyEndDateInput").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#purchaseInputGrid").trigger("reloadGrid");
	},
	
	/**
	* @description 生成采购单信息
	* @param  
	* @return 
	*/
	generateGrid : function() {
		var gridUrl = $.taHash.get("ctx")+ purchaseGenInput.generateGridUrl;
		jQuery("#genListGrid").jqGrid('setGridParam', {
			url : gridUrl,
			postData : {
				//获取选中的ID
				orderId : $("#inputId").val()
			},
			datatype : "json"
		});
		jQuery("#genListGrid").trigger("reloadGrid");
		//清空查询列表
		jQuery("#resetInput").trigger("click");
	},
	
	/**
	 * 开始时间不能小于结束时间
	 */
	checkDate : function() {
		var startTime=$("#applyStartDateInput").val();
	    var endTime=$("#applyEndDateInput").val();
	    if(startTime=="" || endTime==""){
	    	return true;
	    }
	    var start=new Date(startTime.replace("-", "/").replace("-", "/"));
	    var end=new Date(endTime.replace("-", "/").replace("-", "/"));
	    if(end<start){
	    	alert("开始时间不能小于结束时间！");
	    	$("#applyStartDateInput").val("");
	    	$("#applyEndDateInput").val("");
	        return false;
	    }
	    return true;
	}
}