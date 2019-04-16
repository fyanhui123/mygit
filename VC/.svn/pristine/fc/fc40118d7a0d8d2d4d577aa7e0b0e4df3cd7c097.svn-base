var orderGatherInput = {
	
	loadGridUrl : "/orderJson/queryOrderGatherInput.do?ajax=true",
	
	gatherGridUrl : "/orderJson/orderGather.do?ajax=true",
	
	lineDataArry : [
	                {title:"单证类型名称",fieldId:"versionName",fieldName:"versionName",type:"4",width:"180px",disabled:"disabled"},
	                {title:"单证类型代码",fieldId:"versionCode",fieldName:"versionCode",type:"4",width:"150px",disabled:"disabled"},
	                {title:"汇总数量（份）",fieldId:"orderCount",fieldName:"orderCount",type:"4",width:"150px",disabled:"disabled"},
	                {title:"本级有效库存",fieldId:"allStore",fieldName:"allStore",type:"4",width:"150px",disabled:"disabled"},
	                {title:"申印数量（份）",fieldId:"applyPrintNum",fieldName:"applyPrintNum",type:"4",width:"150px",fieldClass:"required digits",maxlength:8}
	],
	
	initPage: function(){
		
		/**
		 * 初始化表单验证组件
		 */
		$("#gatherInputForm").validate();
	
		/**
		* @description 查询按钮事件
		* @param  queryOrderGather 查询按钮ID
		* @return 
		*/
		$("#queryOrderGatherInput").click( function() {
			orderGatherInput.gridReload();
		});
		
		/**
		* @description 重置按钮事件
		* @param  restBtn 重置按钮ID
		* @return 
		*/
		$("#gatherInputReset").click( function() {
			//清空查询条件
			$("#orderCodeInput").val("");
			$("#applyStartDateInput").val("");
			$("#applyEndDateInput").val("");
			//清空选中的id
			$("#inputId").val("");
			//清空表格数据
			$("#gatherInputGrid").clearGridData();
		});
		
		/**
		* @description 汇总按钮事件
		* @param  orderGatherBtn 汇总按钮ID
		* @return 
		*/
		$("#orderGatherBtn").click( function() {
			//记录选择的订单号
			var selectedIds = $("#inputId").val();
			if(selectedIds==''){
				alert("请选择需要汇总的订单！");
				return false;
			}
			$("#selectedId").val(selectedIds);
			//orderGatherInput.generGrid();
			
			//重置多行输入域，为了防止出现多个多行输入域控件
			$("#gatherInfoDiv").html("<span id='gatherInfoSpan'></span>");
			//初始化多行输入域
			$("#gatherInfoSpan").mulLineWidget({
				hiddenPlus : 1,
				hiddenSubtraction : 1,
				columnData: orderGatherInput.lineDataArry,
				initDataUrl : $.taHash.get("ctx")+ "/orderJson/orderGather.do?ajax=true&orderId="+selectedIds
			});
		});
		
		/**
		* @description 保存按钮事件
		* @param  gatherSaveBtn 汇总按钮ID
		* @return 
		*/
		$("#gatherSaveBtn").click( function() {
			if($("#gatherInputForm").validate().form()){
				$("#gatherSaveBtn").attr("disabled",true);
				$("#gatherSubmitBtn").attr("disabled",true);
				var selectedIds = $("#selectedId").val();
				var jsonArr = $("#gatherInfoSpan").mulLineWidget.getData();
				if(jsonArr && jsonArr.length>0){
					var jsonData = JSON.stringify(jsonArr);
					common.ajaxSubmitDfCallBack(orderGatherInput.callBackReload, $.taHash.get("ctx")+"/orderJson/saveOrderGather.do?ajax=true", {jsonData:jsonData,orderId:selectedIds});
				}else{
					alert("请先进行订单汇总！"); 
					$("#gatherSaveBtn").removeAttr("disabled");
					$("#gatherSubmitBtn").removeAttr("disabled");
					return false;
				}
			}else{
				return false;
			}
		});
		
		/**
		 * 提交按钮事件
		 */
		$("#gatherSubmitBtn").click( function() {
			if($("#gatherInputForm").validate().form()){
				$("#gatherSaveBtn").attr("disabled",true);
				$("#gatherSubmitBtn").attr("disabled",true);
				var selectedIds = $("#selectedId").val();
				var jsonArr = $("#gatherInfoSpan").mulLineWidget.getData();
				if(jsonArr && jsonArr.length>0){
					var jsonData = JSON.stringify(jsonArr);
					common.ajaxSubmitDfCallBack(orderGatherInput.callBackReload, 
							$.taHash.get("ctx")+"/orderJson/saveOrderGather.do?ajax=true", {jsonData:jsonData,orderId:selectedIds,bizType:'submit'});
				}else{
					alert("请先进行订单汇总！");
					$("#gatherSaveBtn").removeAttr("disabled");
					$("#gatherSubmitBtn").removeAttr("disabled");
					return false;
				}
			}else{
				return false;
			}
		});
		
		/**
		* @description 取消按钮事件
		* @param  gatherCancelBtn 汇总按钮ID
		* @return 
		*/
		$("#gatherCancelBtn").click( function() {
			$('#gatherInputPage').dialog('close');
		});
		
		$("#gatherInputGrid").jqGrid({
			url : $.taHash.get("ctx")+ orderGatherInput.loadGridUrl,
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
						index : 'createDate',
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
			rowNum : 10000,
			pager : '#gatherInputGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "orderLaunchList",
		        repeatitems : false,
		        total : "1",
				id: "orderId"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("gatherInputGrid");
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
					var ids = $("#gatherInputGrid").jqGrid('getDataIDs');
					$("#inputId").val(ids);
				}
			}
		});
		
		/**
		 * 生成汇总列表
		 */
		/*$("#gatherInfoGrid").jqGrid({
			url : $.taHash.get("ctx")+ orderGatherInput.gatherGridUrl,
			mtype: "POST", 
			datatype : "json",
			autowidth : true,
			height: 'auto',
			cellEdit : true,
			colNames : ['单证类型名称','单证类型代码', '汇总数量（份）', '本级有效库存', '申印数量（份）'],
			colModel : [{
						name : 'versionName',
						index : 'versionName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'center'
					},{
						name : 'versionCode',
						index : 'vcOrderLaunchDet.versionCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'center'
					},{
						name : 'orderCount',
						index : 'vcOrderLaunchDet.orderCount',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'center'
					},{
						name : 'allStore',
						index : 'vcOrderLaunchDet.allStore',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'center'
					},{
						name : 'applyPrintNum',
						index : 'vcOrderLaunchDet.applyPrintNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						editable : true,
						editrules : {required:true,number:true,minValue:0,maxValue:999},
						align : 'center'
					}],
			shrinkToFit : true,
			viewrecords : true,
			jsonReader: {
			 	root: "vcOrderLaunchDet",
			    repeatitems : false,
			 }
		});*/
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#inputId").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ orderGatherInput.loadGridUrl;
		jQuery("#gatherInputGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				orderCode : $("#orderCodeInput").val(),
				applyStartDate : $("#applyStartDateInput").val(),
				applyEndDate : $("#applyEndDateInput").val()
			},
			datatype : "json"
		});
		jQuery("#gatherInputGrid").trigger("reloadGrid");
	},
	
	/**
	 * 保存的回调函数
	 */
	callBackReload : function(data){
		alert(data);
		$('#gatherInputPage').dialog('close');
		$("#gatherSaveBtn").removeAttr("disabled");
		$("#gatherSubmitBtn").removeAttr("disabled");
		orderGather.gridReload();
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
		
	/**
	* @description 生成订单汇总信息
	* @param  
	* @return 
	*/
	/*generGrid : function() {
		var gridUrl = $.taHash.get("ctx")+ orderGatherInput.gatherGridUrl;
		jQuery("#gatherInfoGrid").jqGrid('setPostData',{
			//获取选中的ID
			orderId : $("#inputId").val(),
		});
		jQuery("#gatherInfoGrid").jqGrid('setGridParam', {
			url : gridUrl
		});
		jQuery("#gatherInfoGrid").trigger("reloadGrid");
	}*/
}