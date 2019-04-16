var purchaseGenerate = {
	
	loadGridUrl : "/orderJson/queryPurchase.do?ajax=true",
		
	initPage: function(){
	
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#purchaseGenInputPage").dialog({
			modal: true,
			height:470,
			width:950,
			autoOpen: false
		});
		
		$("#orderViewPage").dialog({
			modal: true,
			height:400,
			width:850,
			autoOpen: false
		});
		
		/**
		* @description 查询按钮事件
		* @param  queryPurchaseGen 查询按钮ID
		* @return 
		*/
		$("#queryPurchaseGen").click( function() {
			purchaseGenerate.gridReload();
		});
		
		/**
		* @description 重置按钮事件
		* @param  reset 重置按钮ID
		* @return 
		*/
		$("#reset").click( function() {
			//清空查询条件
			$("#purchaseCode").val("");
			$("#flag").val("");
			//清空选中的id
			$("#id").val("");
			//清空表格数据
			$("#purchaseGenGrid").clearGridData();
		});
		
		/**
		 * 新增按钮事件
		 */
		$("#addPurchase").click(function(){
			//$("#purchaseInputGrid").clearGridData();
			//清空订单查询列表
			$("#resetInput").trigger("click");
			//清空查询采购单列表
			$("#genListGrid").clearGridData();
			
			$('#purchaseGenInputPage').dialog('open');
		});
		
		$("#purchaseGenGrid").jqGrid({
			url : $.taHash.get("ctx")+ purchaseGenerate.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','采购单号','申请机构', '单证类型名称', '单证类型代码', '申印数量（份）','起始流水号','结束流水号','状态'],
			colModel : [{
						name : 'purchaseId',
						index : 'purchaseId',
						hidden : true
					},{
						name : 'purchaseCode',
						index : 'purchaseCode',
						//autowidth : true,
						width : '200px',
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
						//autowidth : true,
						width : '180px',
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'endSerialNo',
						index : 'endSerialNo',
						//autowidth : true,
						width : '180px',
						resizable : true,
						sortable : false,
						align : 'left'
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
			pager : '#purchaseGenGridPager',
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
				var selectValue = common.selectRowValue("purchaseGenGrid");
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
					var ids = $("#purchaseGenGrid").jqGrid('getDataIDs');
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
		var gridReloadUrl = $.taHash.get("ctx")+ purchaseGenerate.loadGridUrl;
		jQuery("#purchaseGenGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				purchaseCode : $("#purchaseCode").val(),
				flag : $("#flag").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#purchaseGenGrid").trigger("reloadGrid");
	}
}