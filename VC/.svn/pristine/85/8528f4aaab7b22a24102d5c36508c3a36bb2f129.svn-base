var invoiceBuy = {
		
	loadGridUrl : "/invoiceJson/queryInvoiceBuyPageList.do?ajax=true",
	
	/**
	 * 初始化方法
	 */
	initPage : function(){
	
		/**
		 * 查询按钮事件
		 */
		$("#queryInvoiceBuyBtn").click( function() {
			invoiceBuy.gridReload();
		});
		
		$("#invoiceBuyGrid").jqGrid({
			url : $.taHash.get("ctx")+ invoiceBuy.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['序号', '领购日期', '状态', '发票代码', '数量', '发票起号', '发票止号', '当前正在使用', '剩余数量', '单张开具限额（万）', '所属行业', '电子码'],
			colModel : [{
						name : 'orderNo',
						index : 'orderNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'buyDate',
						index : 'buyDate',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d'}
					},{
						name : 'storeStatus',
						index : 'storeStatus',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'invoiceCode',
						index : 'invoiceCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'amount',
						index : 'amount',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'startNum',
						index : 'startNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'endNum',
						index : 'endNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'nowUse',
						index : 'nowUse',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'leftAmount',
						index : 'leftAmount',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'moneyLimit',
						index : 'moneyLimit',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'belongField',
						index : 'belongField',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'electronicCode',
						index : 'electronicCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#invoiceBuyGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcInvoiceBuyHljList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records'
		     }
			//multiselect: true,
			
		});
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#id").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ invoiceBuy.loadGridUrl;
		jQuery("#invoiceBuyGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				startDate : $("#startDate").val(),
				endDate : $("#endDate").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#invoiceBuyGrid").trigger("reloadGrid");
	}
}