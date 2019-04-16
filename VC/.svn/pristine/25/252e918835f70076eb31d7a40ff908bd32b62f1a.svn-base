var purchaseReceipt = {
		
	//用于存放选择的id数组
	idList : [],
	loadGridUrl : "/orderJson/queryPurchaseReceipt.do?ajax=true",
	
	initPage: function(){
	
		/**
		 * 查询按钮事件
		 */
		$("#queryPurchaseReceipt").click(function(){
			purchaseReceipt.gridReload();
		});
		
		/**
		* @description 重置按钮事件
		* @param  restBtn 重置按钮ID
		* @return 
		*/
		$("#reset").click( function() {
			//清空查询条件
			$("#flag").val("");
			$("#applyStartDate").val("");
			$("#applyEndDate").val("");
			//清空选中的id
			$("#id").val("");
			//清空表格数据
			$("#purchaseReceiptGrid").clearGridData();
		});
		
		/**
		 * 确认收货按钮事件
		 */
		$("#receiptConfirm").click(function(){
			var id = $("#id").attr("value");
			if("" != id){
				$("#receiptConfirm").attr("disabled",true);
				var action =  $.taHash.get("ctx") + "/orderJson/checkPurchase.do?ajax=true";
				var param = {id : id, checkflag : "receiptConfirm"};
				//校验采购单状态
				common.ajaxSubmitDfCallBack(purchaseReceipt.receiptConfirm, action, param);
			}else{
				alert("请选择采购单！");
			}
		});
		
		/**
		 * 取消收货按钮事件
		 */
		$("#receiptCancel").click(function(){
			var id = $("#id").attr("value");
			if("" != id){
				var action =  $.taHash.get("ctx") + "/orderJson/checkPurchase.do?ajax=true";
				var param = {id : id, checkflag : "receiptCancel"};
				//校验采购单状态
				common.ajaxSubmitDfCallBack(purchaseReceipt.receiptCancel, action, param);
			}else{
				alert("请选择采购单！");
			}
		});
		
		/**
		 * 提交审批按钮事件
		 */
		$("#submitApprove").click(function(){
			var id = $("#id").attr("value");
			if("" != id){
				var action =  $.taHash.get("ctx") + "/orderJson/checkPurchase.do?ajax=true";
				var param = {id : id, checkflag : "submitApprove"};
				//校验采购单状态
				common.ajaxSubmitDfCallBack(purchaseReceipt.submitApprove, action, param);
			}else{
				alert("请选择采购单！");
			}
		});
	
		$("#purchaseReceiptGrid").jqGrid({
			url : $.taHash.get("ctx")+ purchaseReceipt.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			cellEdit : true,
			cellsubmit : 'clientArray',
			colNames : ['id','采购单号','单证类型名称', '单证类型代码', '申印数量','起始流水号','结束流水号','单价','总金额','印刷批次','收货起始流水号','收货结束流水号','收货数量','采购单状态'],
			colModel : [{
						name : 'purchaseId',
						index : 'purchaseId',
						hidden : true
					},{
						name : 'purchaseCode',
						index : 'purchaseCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'versionName',
						index : 'versionName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'versionCode',
						index : 'versionCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'applyPrintNum',
						index : 'applyPrintNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'center'
					},{
						name : 'startSerialNo',
						index : 'startSerialNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'endSerialNo',
						index : 'endSerialNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'unitPrice',
						index : 'unitPrice',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'right'
					},{
						name : 'totalAmount',
						index : 'totalAmount',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'right'
					},{
						name : 'pressBatchNo',
						index : 'pressBatchNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'center'
					},{
						name : 'receivedStartNum',
						index : 'receivedStartNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						editable : true,
						editrules: {required:true},
						align : 'left'
					},{
						name : 'receivedEndNum',
						index : 'receivedEndNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						editable : true,
						editrules: {required:true},
						align : 'left'
					},{
						name : 'receivedQuantity',
						index : 'receivedQuantity',
						autowidth : true,
						resizable : true,
						sortable : false,
						editable : true,
						editrules: {required:true,integer:true},
						align : 'center'
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
			pager : '#purchaseReceiptGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcPurchaseList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "purchaseId"
		     },
			multiselect: true,
			
			/**
			 * 根据输入的收货起始/结束流水号，自动计算收货数量
			 */
			afterSaveCell : function(rowid,cellname,value,iRow,iCol){
		    	if(cellname=="receivedStartNum" || cellname=="receivedEndNum"){
			    	 //alert(">>rowid="+rowid+">>cellname="+cellname+">>value="+value+">>iRow="+iRow+">>>iCol="+iCol);
			    	 var receiptStartNum = jQuery("#purchaseReceiptGrid").getCell(rowid,"receivedStartNum");
			    	 var receiptEndNum = jQuery("#purchaseReceiptGrid").getCell(rowid,"receivedEndNum");
			    	 //alert("receiptStartNum="+receiptStartNum+",receiptEndNum="+receiptEndNum);
			    	 if(""!=receiptStartNum && ""!=receiptEndNum){
			    		 var start = receiptStartNum.substring(receiptStartNum.length-8, receiptStartNum.length);
			    		 var end = receiptEndNum.substring(receiptEndNum.length-8, receiptEndNum.length);
			    		 var quantity = parseInt(end) - parseInt(start) + 1;
			    		 jQuery("#purchaseReceiptGrid").setCell(rowid,"receivedQuantity",quantity);
			    	 }else{
			    		 jQuery("#purchaseReceiptGrid").setCell(rowid,"receivedQuantity","10");
			    	 }
		    	}
		     },
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("purchaseReceiptGrid");
				purchaseReceipt.idList=selectValue;
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
					var ids = $("#purchaseReceiptGrid").jqGrid('getDataIDs');
					purchaseReceipt.idList=ids;
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
		var gridReloadUrl = $.taHash.get("ctx")+ purchaseReceipt.loadGridUrl;
		jQuery("#purchaseReceiptGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				applyStartDate : $("#applyStartDate").val(),
				applyEndDate : $("#applyEndDate").val(),
				flag : $("#flag").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#purchaseReceiptGrid").trigger("reloadGrid");
	},
	
	/**
	 * 确认收货
	 */
	receiptConfirm : function(data){
		//1-校验成功，0-校验失败
		if(data[0]=='1'){
			//确认收货
			//var id = $("#id").attr("value");
			//common.ajaxSubmitDfCallBack(purchaseReceipt.callBackReload, $.taHash.get("ctx")+ "/orderManager/executeReceiptConfirm.do?ajax=true", {id : id});
			var formData="";
			var rowDataArray = new Array();
			$.each(purchaseReceipt.idList,function(index,i){
				var rowData = $("#purchaseReceiptGrid").jqGrid("getRowData",i);
				rowDataArray.push(rowData);
			});
			formData = JSON.stringify(rowDataArray);
			common.ajaxSubmitDfCallBack(purchaseReceipt.saveCallBack, $.taHash.get("ctx")+ "/orderManager/executeReceiptConfirm.do?ajax=true", {jsonData : formData});
		}else{
			alert(data[1]);
			$("#receiptConfirm").removeAttr("disabled");
		}
	},
	
	/**
	 * 取消收货
	 */
	receiptCancel : function(data){
		//1-校验成功，0-校验失败
		if(data[0]=='1'){
			var id = $("#id").attr("value");
			//确认收货
			common.ajaxSubmitDfCallBack(purchaseReceipt.callBackReload, $.taHash.get("ctx")+ "/orderManager/executeReceiptCancel.do?ajax=true", {id : id});
		}else{
			alert(data[1]);
		}
	},
	
	/**
	 * 提交动作
	 */
	submitApprove : function(data){
		//1-校验成功，0-校验失败
		if(data[0]=='1'){
			var id = $("#id").attr("value");
			common.ajaxSubmitDfCallBack(purchaseReceipt.callBackReload, $.taHash.get("ctx")+ "/orderManager/receivedSubmit.do?ajax=true", {id : id});
		}else{
			alert(data[1]);
		}
	},
	
	/**
	 * 确认收货回调函数
	 */
	saveCallBack : function(){
		alert("操作成功！");
		$("#receiptConfirm").removeAttr("disabled");
		purchaseReceipt.gridReload();
	},
	
	/**
	 * 确认/取消收货回调函数
	 */
	callBackReload : function(){
		alert("操作成功！");
		purchaseReceipt.gridReload();
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