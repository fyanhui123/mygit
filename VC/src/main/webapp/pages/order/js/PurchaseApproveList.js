var purchaseApprove = {
		
	loadGridUrl : "/orderJson/queryPurchaseApprove.do?ajax=true",
		
	initPage: function(){
	
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#appEditPage").dialog({
			modal: true,
			height:450,
			width:950,
			autoOpen: false
		});
		
		/**
		* 查询按钮事件
		*/
		$("#queryPurchaseApprove").click( function() {
			purchaseApprove.gridReload();
		});
		
		/**
		* @description 重置按钮事件
		* @param  restBtn 重置按钮ID
		* @return 
		*/
		$("#reset").click( function() {
			//清空查询条件
			$("#purchaseCode").val("");
			$("#applyStartDate").val("");
			$("#applyEndDate").val("");
			$("#flag").val("");
			//清空选择的id
			$("#id").val("");
			//清空表格数据
			$("#purchaseApproveGrid").clearGridData()
		});
		
		/**
		* 审批按钮事件
		*/
		$("#approveBtn").click( function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					var action =  $.taHash.get("ctx") + "/orderJson/checkPurchase.do?ajax=true";
					var param = {id : id, checkflag : "purchaseAppEdit"};
					//校验采购单状态
					common.ajaxSubmitDfCallBack(purchaseApprove.purchaseAppEdit, action, param);
				}
			}else{
				alert("请选择一条记录！");
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
				common.ajaxSubmitDfCallBack(purchaseApprove.receiptCancel, action, param);
			}else{
				alert("请选择采购单！");
			}
		});
		
		$("#purchaseApproveGrid").jqGrid({
			url : $.taHash.get("ctx")+ purchaseApprove.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','采购单号','申请机构', '单证类型',/* '单证类型代码',*/ '收货起始流水号','收货结束流水号','收货数量','收货人','收货时间','采购单状态'],
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
						name : 'orgName',
						index : 'orgName',
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
					},/*{
						name : 'versionCode',
						index : 'versionCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},*/{
						name : 'receivedStartNum',
						index : 'receivedStartNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'receivedEndNum',
						index : 'receivedEndNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'receivedQuantity',
						index : 'receivedQuantity',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'receivedOpr',
						index : 'receivedOpr',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'receivedTime',
						index : 'receivedTime',
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
			pager : '#purchaseApproveGridPager',
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
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("purchaseApproveGrid");
				$("#id").val(selectValue);
			},
			
			/**
			* @description 全选触发时获取所有选中的Id值
			* @return 
			*/
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#id").val("");
				}else{//全部选中时
					var ids = $("#purchaseApproveGrid").jqGrid('getDataIDs');
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
		var gridReloadUrl = $.taHash.get("ctx")+ purchaseApprove.loadGridUrl;
		jQuery("#purchaseApproveGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				purchaseCode : $("#purchaseCode").val(),
				flag : $("#flag").val(),
				applyStartDate : $("#applyStartDate").val(),
				applyEndDate : $("#applyEndDate").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#purchaseApproveGrid").trigger("reloadGrid");
	},
	
	/**
	 * 审批
	 */
	purchaseAppEdit : function(data){
		//1-校验成功，0-校验失败
		if(data[0]=='1'){
			var id = $("#id").attr("value");
			//ajax获取要修改的数据
			common.ajaxGetData("appEditPage", $.taHash.get("ctx")+"/orderManager/editPurchaseApprove.do?ajax=true&id="+id,null);
			//调用dialog组件
			$('#appEditPage').dialog('open');
		}else{
			alert(data[1]);
			return false;
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
			common.ajaxSubmitDfCallBack(purchaseApprove.callBackReload, $.taHash.get("ctx")+ "/orderManager/executeReceiptCancel.do?ajax=true", {id : id});
		}else{
			alert(data[1]);
		}
	},
	
	/**
	 * 取消收货回调函数
	 */
	callBackReload : function(){
		alert("操作成功！");
		purchaseApprove.gridReload();
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