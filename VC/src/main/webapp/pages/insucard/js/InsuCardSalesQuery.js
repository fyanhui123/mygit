var insuCardSalesQuery = {
		
	loadGridUrl : "/insucardJson/queryInsuCardSales.do?ajax=true&oprType=P",
	
	//0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败
	salesRecordStatus : [{
  		"label" : "删除",
		 "value" : "0"
	 },{
		 "label" : "新建",
		 "value" : "1"
	},{
		 "label" : "等待缴费",
		 "value" : "2"
	},{
		 "label" : "缴费退回",
		 "value" : "3"
	},{
		 "label" : "已缴费成功",
		 "value" : "4"
	},{
		 "label" : "缴费失败",
		 "value" : "5"
	},{
		 "label" : "销售撤销",
		 "value" : "6"
	},{
		 "label" : "撤销失败",
		 "value" : "7"
	} ],
	
	//新增、修改保存成功提示信息
	saveSuccessAlert:function(flag,msg){
	  if(flag == 'savaSuccess'){
		alert(msg);
		insuCardSalesQuery.gridReload();
	  }
    },
	
	
	initPage : function() {
		$("#insuCardSalesViewPage").dialog({
			modal: true,
			height:550,
			width:1000,
			autoOpen: false
		});
		/**
		 * 单证类型自动完成(只查激活卡)
		 */
		$("#queryDtoDocVerCode").taAutoComplete( {
				source : $.taHash.get("ctx")+ "/baseinfoJson/getJSONUserSend.do?trigger=no&docTypeCode=JK&style=auto",
				minLength : 0,
				height : 200,
				width : 220
		});
		
		$("#queryDtoSalesRecordStatus").dropDownList({
			data : insuCardSalesQuery.salesRecordStatus
		});
		
		/**
		 * 使用人自动完成
		 */
		$("#quetyDtoSellerCode").taAutoComplete( {
					source : "/visa/baseinfoJson/getTakerJson.do?type=auto",
					minLength : 0,
					height : 210,
					width : 220
				});
		
		//使用人机构树
		$("#queryDtoSaleOrgCode").dropDownDivList({
		   source: "/visa/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=queryDtoSaleOrgCode&tagNameId=queryDtoSaleOrgCodeName"
		});

		
		/**
		 * @description 查询按钮事件
		 *     查询按钮ID
		 * @return
		 */
		$("#queryInsuCardSalesBut").click(function() {
			insuCardSalesQuery.gridReload();
		});
		
		/**
		 * @description 新增按钮事件
		 *
		 * @return
		 */
		$("#addInsuCardSalesBut").click(function() {
			url= $.taHash.get("ctx") + "/insucard/initInsuCardSalesAdd.do";
			//common.formSubmit ("queryForm",url);
			queryForm.action=url;
			queryForm.submit();
		});
		
		
		/**
		 * @description修改按钮事件
		 *
		 * @return
		 */
		$("#editInsuCardSalesBut").click(function() {
			// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if ("" != id) {
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if (patt.test(id)) {
					alert("只能选择一条记录！");
				} else {
					var status = common.selectRowShowCollValue("insuCardSalesGrid","salesRecordStatus",id);
					// 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败/8等待退费）
					if(status!="1" && status!="6" ){
						alert("不是新建和销售撤销的记录，不能修改！");
						return;
					}
					url= $.taHash.get("ctx") + "/insucard/initInsuCardSalesUpdate.do?idVcInsuCardSalesRecord="+id;
					queryForm.action=url;
					queryForm.submit();
				}
			} else {
				alert("请选择一条记录！");
			}			
		});
		
		 
		/**
		 * @description 提交按钮事件
		 * @return
		 */
		$("#submitInsuCardSalesBut").click(function() {
			// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if ("" != id) {
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if (patt.test(id)) {
					alert("只能选择一条记录！");
				} else {
					var status = common.selectRowShowCollValue("insuCardSalesGrid","salesRecordStatus",id);
					// 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败/8等待退费）
					if(status!="1" && status!="6"){
						alert("不是新建和销售撤销的记录，不能提交！");
						return;
					}
				   var action = $.taHash.get("ctx") + "/insucardJson/submitInsuCardSales.do?ajax=true&idVcInsuCardSalesRecord="+id;
			       if (confirm("确定提交?")) {
				        common.ajaxSubmitDfCallBack(
				        	function(date){
				        	  alert(date);
				        	  insuCardSalesQuery.gridReload();
				            },
						action, "");
			        }
				}
			} else {
				alert("请选择一条记录！");
			}
		});
		
		/**
		 * @description 缴费撤销按钮事件
		 * @return
		 */
		$("#cancelPayFeeBut").click(function() {
			// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if ("" != id) {
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if (patt.test(id)) {
					alert("只能选择一条记录！");
				} else {
					var status = common.selectRowShowCollValue("insuCardSalesGrid","salesRecordStatus",id);					
					// 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败/8等待退费）
					if(status!="2"){
						alert("不是等待缴费的记录，不能进行此操作！");
						return;
					}
				   var action = $.taHash.get("ctx") + "/insucardJson/doCancelPayFee.do?ajax=true&idVcInsuCardSalesRecord="+id;
			       if (confirm("确定提交?")) {
				        common.ajaxSubmitDfCallBack(
				        	function(date){
				        	  alert(date);
				        	  insuCardSalesQuery.gridReload();
				            },
						action, "");
			        }
				}
			} else {
				alert("请选择一条记录！");
			}
		});
		
		/**
		 * @description 删除按钮事件
		 * @return
		 */
		$("#deleteInsuCardSalesBut").click(function() {
			// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if ("" != id) {
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if (patt.test(id)) {
					alert("只能选择一条记录！");
				} else {
					var status = common.selectRowShowCollValue("insuCardSalesGrid","salesRecordStatus",id);					
					// 销售记录状态（0删除/1新建/2等待缴费/3缴费退回/4已缴费成功/5缴费失败/6销售撤销/7撤销失败）
					if(status!="1" && status!="6"){
						alert("不是【新建、销售撤销】的记录，不能删除！");
						return;
					}
				   var action = $.taHash.get("ctx") + "/insucardJson/deleteRecord.do?ajax=true&idVcInsuCardSalesRecord="+id;
			       if (confirm("确定删除?")) {
				        common.ajaxSubmitDfCallBack(
				        	function(date){
				        	  alert(date);
				        	  insuCardSalesQuery.gridReload();
				            },
						action, "");
			        }
				}
			} else {
				alert("请选择一条记录！");
			}
		});
		


		$("#insuCardSalesGrid").jqGrid({
					//url : $.taHash.get("ctx") + insuCardSalesQuery.loadGridUrl,
			        url:"",
					mtype : "POST",
					datatype : "local",
					autowidth : true,
					height : 'auto',
					colNames : [ 'ID','销售单号', '销售人', '销售人所属机构', '销售日期', '销售总额','缴费截止日期 ','状态','salesRecordStatus' ],
					colModel : [{
						name : 'idVcInsuCardSalesRecord',
						index : 'idVcInsuCardSalesRecord',
						hidedlg : true,
						hidden : true
					},{
						name : 'salesRecordCode',
						index : 'salesRecordCode',
						resizable : true,
						align : 'center'
					}, {
						name : 'sellerName',
						index : 'sellerName',
						resizable : true,
						align : 'left'
					}, {
						name : 'saleOrgName',
						index : 'saleOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'saleDate',
						index : 'saleDate',
						autowidth : true,
						resizable : true,
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},
						align : 'left'
					}, {
						name : 'sumFee',
						index : 'sumFee',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'payEndDate',
						index : 'payEndDate',
						autowidth : true,
						resizable : true,
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},
						align : 'left'
					},{
						name : 'salesRecordStatus',
						index : 'salesRecordStatusName',
						autowidth : true,
						resizable : true,
						formatter : myStatusFormatter,
						align : 'left'
					},{
						name : 'salesRecordStatus',
						index : 'salesRecordStatus',
						hidden : true
					}],
					shrinkToFit : true,
					rowNum : 10,
					rowList : [ 10, 30, 50 ],
					pager : '#insuCardSalesGridPager',
					viewrecords : true,
					jsonReader : {
						root : "vcInsuCardSalesRecordList",
						repeatitems : false,
						page : 'page',
						total : 'total',
						records : 'records',
						id : "idVcInsuCardSalesRecord"
					},
					multiselect: true,
					/**
					 * @description 获取选中的值
					 * @param
					 * @return
					 */
					onSelectRow : function(id) {
						var selectValue = common
								.selectRowValue("insuCardSalesGrid");
						$("#id").val(selectValue);
					},
					
					onSelectAll: function(id){
						ids = $("#insuCardSalesGrid").jqGrid('getDataIDs');	
						$("#id").val(ids);
					},
					
					gridComplete: function(){
					    var ids = $("#insuCardSalesGrid").jqGrid('getDataIDs');
					    for(var i=0;i < ids.length;i++){
					     var rowArray = $("#insuCardSalesGrid").jqGrid('getRowData',ids[i]);
					     var editBtn = "<a href='#' onclick='insuCardSalesQuery.salesRecordView("+rowArray.idVcInsuCardSalesRecord+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.salesRecordCode+"</a>";
					     $("#insuCardSalesGrid").jqGrid('setCell', ids[i], 'salesRecordCode', editBtn);
					    }
				   }
				});
		
		// 格式化状态标志
		function myStatusFormatter(cellvalue, options, rowObject) {
			if (cellvalue == '0') {
				return '删除';
			} else if (cellvalue == '1') {
				return '新建';
			} else if (cellvalue == '2') {
				return '等待缴费';
			} else if (cellvalue == '3') {
				return '缴费退回';
			} else if (cellvalue == '4') {
				return '已缴费成功';
			} else if (cellvalue == '5') {
				return '缴费失败';
			} else if (cellvalue == '6') {
				return '销售撤销';
			} else if (cellvalue == '7') {
				return '撤销失败';
			} 
		}
	},
	
	salesRecordView:function(id) {
		//ajax获取要修改的数据
		common.ajaxGetData("insuCardSalesViewPage", $.taHash.get("ctx")+"/insucard/viewInsuCardSales.do?idVcInsuCardSalesRecord="+id+"&ajax=true",null);
		//调用dialog组件
		setTimeout(function(){
			$('#insuCardSalesViewPage').dialog('open');
		},300);
	},

	gridReload : function() {
		$("#id").val("");
		var queryParam=$("#queryForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+ insuCardSalesQuery.loadGridUrl+"&"+queryParam;
		jQuery("#insuCardSalesGrid").jqGrid('setGridParam', {
			datatype : "json",
			postData:{
			},
			url : gridReloadUrl,
			page : 1
		});
		jQuery("#insuCardSalesGrid").trigger("reloadGrid");
	},

	callBack : function(data) {
		alert(data);
		insuCardSalesQuery.gridReload();
	}
}
