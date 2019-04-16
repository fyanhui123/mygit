var invoiceExport = {
		
	loadGridUrl : "/invoiceJson/queryInvoiceExport.do?ajax=true",
	
	/**
	 * 初始化函数
	 */
	initPage : function () {
	
		//初始化单证险类下拉框
		/*$("#insuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/invoiceJson/getJSONInsuTypeList.do?ajax=true"
		});*/
		
		//初始化单证险种下拉框
		/*$("#insuKindList").dropDownList({
			trigger: '#insuTypeList',    
			event: 'change',
			dataField:'name',
			url: $.taHash.get("ctx")+"/invoiceJson/getJSONInsuKindList.do?ajax=true",
			dataType:'json'
		});*/
		
		//初始化签单机构下拉框
		/*$("#inputCompany").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/invoiceJson/getJSONCompanyList.do?ajax=true"
		});*/
		
		//初始化发票状态下拉框
		$("#invoiceStatus").dropDownList({
			data: [{"label":"已导出","value":"1"},{"label":"未导出","value":"0"}] 
		});
		
		 // 机构选择
		$("#inputCompany").dropDownDivList({
			source : $.taHash.get("ctx")
					+ "/common/initQueryOrgZTree.do?ajax=true&minLevel=3&singleSelect=true&tagCodeId=inputCompany&tagNameId=inputCompanyName"
		});
		
		/**
		* @description 查询按钮事件
		* @param  queryInvoiceExport 查询按钮ID
		* @return 
		*/
		$("#queryInvoiceExport").click( function() {
			invoiceExport.gridReload();
		});
		
		/**
		* @description 重置按钮事件
		* @param  restBtn 重置按钮ID
		* @return 
		*/
		$("#restBtn").click( function() {
			//清空查询条件
			$("input[type='text']").val("");
			$("select").val("");
			//清空表格数据
			$("#invoiceExportGrid").clearGridData();
		});
		
		/**
		* @description 导出按钮事件
		* @param  exportInvoice 导出按钮ID
		* @return 
		*/
		/*$("#exportInvoice").click( function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			if("" != id){
				var formStr = $("#invoiceExportForm").serialize();
				var url = $.taHash.get("ctx")+ "/invoiceJson/generateFile.do?ajax=true&"+formStr;
				common.ajaxSubmitDfCallBack(invoiceExport.fileExport, url, {ids : id});
			}else{
				alert("请选择需要导出的数据！");
			}
		});*/
		$("#exportInvoice").click( function() {
			$("#exportInvoice").attr("disabled",true);
			//校验打印日期不能为空 begin
			var printDateStart = $("#printDateStart").val();
			var printDateEnd = $("#printDateEnd").val();
			if(printDateStart=="" || printDateEnd=="" || !printDateStart || !printDateEnd){
				alert("打印日期不能为空！");
				//重置查询条件，清空表格数据
				$("#restBtn").trigger("click");
				$("#exportInvoice").removeAttr("disabled");
				return false;
			}
			//校验打印日期不能为空 end
			var formStr = $("#invoiceExportForm").serialize();
			var total = $("#invoiceExportGrid").jqGrid('getGridParam','records');
			var url = $.taHash.get("ctx")+ "/invoiceJson/generateFile.do?ajax=true&"+formStr+"&total="+total;
			common.ajaxSubmitDfCallBack(invoiceExport.fileExport, url, null);
			/*setTimeout(function(){
				$("#exportInvoice").removeAttr("disabled");
			}, 2000);*/
		});
		
		/**
		 * 初始化表格
		 */
		$("#invoiceExportGrid").jqGrid({
			url : $.taHash.get("ctx")+ invoiceExport.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','发票代码','发票号码', '付款方单位名称', '合计金额', '开票人', '开票日期', '批单号', '保单号', '发票状态'],
			colModel : [{
						name : 'id',
						index : 'id',
						hidden : true
					},{
						name : 'invoiceCode',
						index : 'invoiceCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'invoiceNo',
						index : 'invoiceNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'payerName',
						index : 'payerName',
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
						name : 'billerName',
						index : 'billerName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'printDate',
						index : 'printDate',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d',newformat:'Y-m-d'}
					},{
						name : 'endorseNo',
						index : 'endorseNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'policyNo',
						index : 'policyNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'isUploadPlat',
						index : 'isUploadPlat',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:function(value,row,index){
					         if(value=='1'){
					        	 return "已导出";
					         }else{
					        	 return "未导出"; 
					         }
					 	}	
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#invoiceExportGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcInvoicePrintList",
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
				var selectValue = common.selectRowValue("invoiceExportGrid");
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
					var ids = $("#invoiceExportGrid").jqGrid('getDataIDs');
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
		//校验打印日期不能为空 begin
		var printDateStart = $("#printDateStart").val();
		var printDateEnd = $("#printDateEnd").val();
		if(printDateStart=="" || printDateEnd=="" || !printDateStart || !printDateEnd){
			alert("打印日期不能为空！");
			//重置查询条件，清空表格数据
			$("#restBtn").trigger("click");
			return false;
		}
		//校验打印日期不能为空 end
		$("#id").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ invoiceExport.loadGridUrl;
		var formStr = $("#invoiceExportForm").serialize();
		gridReloadUrl += gridReloadUrl + "&" + formStr;
		jQuery("#invoiceExportGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
			},
			datatype : "json",
			page : 1
		});
		jQuery("#invoiceExportGrid").trigger("reloadGrid");
	},
	
	/**
	 * 导出
	 */
	fileExport : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#exportInvoice").removeAttr("disabled");
			return false;
		}
		var filePath=data.substring(0,data.indexOf("=="));
		var fileName=data.substring(data.indexOf("==")+2, data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#exportInvoice").removeAttr("disabled");
	},

	/**
	 * 开始时间不能小于结束时间
	 */
	checkDate : function() {
		var startTime=$("#printDateStart").val();
	    var endTime=$("#printDateEnd").val();
	    if(startTime=="" || endTime==""){
	    	return true;
	    }
	    var start=new Date(startTime.replace("-", "/").replace("-", "/"));
	    var end=new Date(endTime.replace("-", "/").replace("-", "/"));
	    if(end<start){
	    	alert("起始日期不能小于终止日期！");
	    	$("#printDateStart").val("");
	    	$("#printDateEnd").val("");
	        return false;
	    }
	    return true;
	}
}