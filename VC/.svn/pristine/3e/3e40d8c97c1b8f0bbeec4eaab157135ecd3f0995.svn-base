var purchaseOrder = {
	
	//用于存放选择的id数组
	iIDs : [],
	loadGridUrl : "/orderJson/queryPurchaseOrder.do?ajax=true",
	
	initPage: function(){
	
		/**
		 * 查询按钮事件
		 */
		$("#queryPurchaseOrder").click(function(){
			purchaseOrder.gridReload();
		});
		
		/**
		 * 重置按钮事件
		 */
		$("#reset").click(function(){
			//清空查询条件
			$("#printeryCode").val("");
			$("#purchaseCode").val("");
			//清空选中的id
			$("#id").val("");
			//清空表格数据
			$("#purchaseOrderGrid").clearGridData();
		});
		
		/**
		 * 初始化印刷厂下拉框
		 */
		$("#printeryCode").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONPrinteryList.do?ajax=true"
		});
		
		/**
		 * 下单按钮事件
		 */
		$("#purchaseOrder").click(function(){
			//记录选择的订单号
			var selectedIds = $("#id").val();
			if(selectedIds==''){
				alert("请选择采购单！");
				return false;
			}else{
				var action = $.taHash.get("ctx")+"/orderManager/executePurchaseOrder.do?ajax=true";
				var jsonData;
				var formData = "";
				$.each(purchaseOrder.iIDs,function(index,id){
					var rowData = $("#purchaseOrderGrid").jqGrid("getRowData",id);
					//alert(rowData.purchaseNo+">"+rowData.printeryCode+">"+rowData.printeryName+">"+rowData.unitPrice+">"+rowData.totalAmount);
					formData += JSON.stringify(rowData) + ",";
				});
				if(formData!=""){
					jsonData = "["+formData.substring(0,formData.length-1)+"]";
				}
				common.ajaxSubmitDfCallBack(purchaseOrder.callBackReload, action, {jsonData:jsonData});
			}
		});
		
		/**
		 * 采购单导出
		 */
		$("#exportPurchaseOrder").click(function(){			
			//校验印刷厂不能为空 begin
			var printeryId = $("#printeryCode").val();
			if(printeryId == ""){			
				alert("请选择印刷厂！");
				//重置查询条件，清空表格数据
				$("#reset").trigger("click");
				return false;
			}	
			$("#exportPurchaseOrder").attr("disabled",true);
		    var url = $.taHash.get("ctx")+ "/orderJson/exportPurchaseOrder.do?ajax=true&printeryId="+ $("#printeryCode").val()+"&purchaseCode="+$("#purchaseCode").val();
		    common.ajaxSubmitDfCallBack(purchaseOrder.exportHref, url, null);
		    setTimeout(function(){
		    	$("#exportPurchaseOrder").removeAttr("disabled");
		    },5000);
		  });
		
		
		
	
		$("#purchaseOrderGrid").jqGrid({
			url : $.taHash.get("ctx")+ purchaseOrder.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','采购单号','申请机构', '单证类型名称', '单证类型代码', '申印数量（份）','起始流水号','结束流水号','printeryId','承印厂家','单价（元）','总金额（元）'],
			colModel : [{
						name : 'purchaseId',
						index : 'purchaseId',
						hidden : true
					},{
						name : 'purchaseCode',
						index : 'purchaseCode',
						//autowidth : true,
						width : '220px',
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
						name : 'printeryId',
						index : 'printeryId',
						hidden : true
					},{
						name : 'printeryName',
						index : 'printeryName',
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
						editable : false,
						align : 'right'
					},{
						name : 'totalAmount',
						index : 'totalAmount',
						autowidth : true,
						resizable : true,
						sortable : false,
						editable : false,
						align : 'right'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#purchaseOrderGridPager',
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
				var selectValue = common.selectRowValue("purchaseOrderGrid");
				purchaseOrder.iIDs = selectValue;
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
					var ids = $("#purchaseOrderGrid").jqGrid('getDataIDs');
					purchaseOrder.iIDs = ids;
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
		//校验印刷厂不能为空 begin
		var printeryCode = $("#printeryCode").val();
		if(printeryCode != ""){
		}else{
			alert("请选择印刷厂！");
			//重置查询条件，清空表格数据
			$("#reset").trigger("click");
			return false;
		}
		//校验印刷厂不能为空 end
		$("#id").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ purchaseOrder.loadGridUrl;
		jQuery("#purchaseOrderGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				printeryId : printeryCode,
				printeryName : $("#printeryCode").find("option:selected").text(),
				purchaseCode : $("#purchaseCode").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#purchaseOrderGrid").trigger("reloadGrid");
	},
	
	callBackReload : function(){
		alert("操作成功！");
		purchaseOrder.gridReload();
	},
	
	/**
	 * 采购单导出回调
	 */
	exportHref : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#exportPurchaseOrder").removeAttr("disabled");
			return false;
		}
		var filePath=data.substring(0,data.indexOf("=="));
		var fileName=data.substring(data.indexOf("==")+2, data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#exportPurchaseOrder").removeAttr("disabled");
	}
}