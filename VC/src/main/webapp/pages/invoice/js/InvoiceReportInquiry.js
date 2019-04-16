var invoiceReport = {
	
	loadGridUrl : "/invoiceJson/queryReportPageList.do?ajax=true",
	
	/**
	 * 初始化方法
	 */
	initPage : function(){
		
		/**
		 * 查询按钮事件
		 */
		$("#queryReportBtn").click( function() {
			invoiceReport.gridReload();
		});
		
		/**
		 * 报表明细按钮事件
		 */
		$("#reportDetailBtn").click(function(){
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					var url = $.taHash.get("ctx")+"/invoice/queryReportDetail.do";
					$("#reportId").val(id);
					document.getElementById("editForm").action=url;
					document.getElementById("editForm").submit();
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		$("#invoiceReportGrid").jqGrid({
			url : $.taHash.get("ctx")+ invoiceReport.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id', '管理编码', '日期起', '日期止', '报表日期'],
			colModel : [{
						name : 'id',
						index : 'id',
						hidden : true
					},{
						name : 'manageCode',
						index : 'manageCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'startDate',
						index : 'startDate',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d'}
					},{
						name : 'endDate',
						index : 'endDate',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d'}
					},{
						name : 'reportDate',
						index : 'reportDate',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d'}
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#invoiceReportGridPager',
			viewrecords : true,
			rownumbers: true,
			jsonReader: {
		     	root: "invoiceReportList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "id"
		     },
			//multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = $("#invoiceReportGrid").jqGrid('getGridParam','selrow');
				$("#id").val(selectValue);
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
		var gridReloadUrl = $.taHash.get("ctx")+ invoiceReport.loadGridUrl;
		jQuery("#invoiceReportGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				startDate : $("#startDate").val(),
				endDate : $("#endDate").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#invoiceReportGrid").trigger("reloadGrid");
	}
}