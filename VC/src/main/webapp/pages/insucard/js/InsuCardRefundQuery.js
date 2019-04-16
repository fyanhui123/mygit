var insuCardRefundQuery = {
		
	loadGridUrl : "/insucardJson/queryInsuCardSales.do?ajax=true&oprType=R",
	
	
	//20退卡删除/ 21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)
	salesRecordStatus : [{
  		"label" : "退卡删除",
		 "value" : "20"
	 },{
		 "label" : "退卡新增",
		 "value" : "21"
	},{
		 "label" : "等待退费",
		 "value" : "22"
	},{
		 "label" : "退费成功",
		 "value" : "23"
	},{
		 "label" : "退费失败(账户原因)",
		 "value" : "24"
	},{
		 "label" : "退费失败(非账户原因)",
		 "value" : "25"
	}],
	
	
	//新增、修改保存成功提示信息
	saveSuccessAlert:function(flag,msg){
	  if(flag == 'savaSuccess'){
		alert(msg);
		insuCardRefundQuery.gridReload();
	  }
    },
	
	initPage : function() {
	
		$("#insuCardRefundViewPage").dialog({
			modal: true,
			height:650,
			width:900,
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
			data : insuCardRefundQuery.salesRecordStatus
		});
		
		/**
		 * 退卡人自动完成
		 */
		$("#quetyDtoSellerCode").taAutoComplete( {
					source : "/visa/baseinfoJson/getTakerJson.do?type=auto",
					minLength : 0,
					height : 210,
					width : 220
				});
		
		//机构树
		$("#queryDtoSaleOrgCode").dropDownDivList({
		   source: "/visa/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=queryDtoSaleOrgCode&tagNameId=queryDtoSaleOrgCodeName"
		});

		
		/**
		 * @description 查询按钮事件
		 *     查询按钮ID
		 * @return
		 */
		$("#queryInsuCardRefundBut").click(function() {
			insuCardRefundQuery.gridReload();
		});
		
		/**
		 * @description 新增按钮事件
		 *
		 * @return
		 */
		$("#addInsuCardRefundBut").click(function() {
			url= $.taHash.get("ctx") + "/insucard/initInsuCardRefundAdd.do";
			//common.formSubmit ("queryForm",url);
			queryForm.action=url;
			queryForm.submit();
		});
		
		
		
		/**
		 * @description修改按钮事件
		 *
		 * @return
		 */
		$("#editInsuCardRefundBut").click(function() {
			// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if ("" != id) {
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if (patt.test(id)) {
					alert("只能选择一条记录！");
				} else {
					var status = common.selectRowShowCollValue("insuCardRefundGrid","salesRecordStatus",id);
					// 退卡记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功）
					if(status!="21"  ){
						alert("不是新建的记录，不能修改！");
						return;
					}
					url= $.taHash.get("ctx") + "/insucard/initInsuCardRefundUpdate.do?idVcInsuCardSalesRecord="+id;
					queryForm.action=url;
					queryForm.submit();
				}
			} else {
				alert("请选择一条记录！");
			}			
		});
		
		/**
		 * @description 退费按钮事件
		 * @return
		 */
		$("#refundBut").click(function() {
			// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if ("" != id) {
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if (patt.test(id)) {
					alert("只能选择一条记录！");
				} else {
					var status = common.selectRowShowCollValue("insuCardRefundGrid","salesRecordStatus",id);
					// 退卡记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)）
					if(status!="21" && status!="24"){
						alert("不是【退卡新增、退费失败(账户原因)】的记录，不能进行退费操作！");
						return;
					}
				   var action = $.taHash.get("ctx") + "/insucardJson/doRefund.do?ajax=true&idVcInsuCardSalesRecord="+id;
			       if (confirm("确定提交?")) {
				        common.ajaxSubmitDfCallBack(
				        	function(date){
				        	  alert(date);
				        	  insuCardRefundQuery.gridReload();
				            },
						action, "");
			        }
				}
			} else {
				alert("请选择一条记录！");
			}
		});
		
		/**
		 * @description 账户修改按钮事件
		 * @return
		 */
		$("#modifyAccountBut").click(function() {
			// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if ("" != id) {
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if (patt.test(id)) {
					alert("只能选择一条记录！");
				} else {
					var status = common.selectRowShowCollValue("insuCardRefundGrid","salesRecordStatus",id);
					// 退卡记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)）
					if(status!="22" && status!="24"){
						alert("不是【退卡失败（账户原因）、等待退费】的记录，不能进行账户修改同步操作！");
						return;
					}
					url=$.taHash.get("ctx") + "/insucard/initAccountUpdate.do?idVcInsuCardSalesRecord="+id;
					queryForm.action=url;
					queryForm.submit();
				}
			} else {
				alert("请选择一条记录！");
			}
		});
		
		
		/**
		 * @description 删除按钮事件
		 * @return
		 */
		$("#deleteInsuCardRefundBut").click(function() {
			// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if ("" != id) {
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if (patt.test(id)) {
					alert("只能选择一条记录！");
				} else {
					var status = common.selectRowShowCollValue("insuCardRefundGrid","salesRecordStatus",id);
					// 退卡记录状态（20退卡删除/21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)）
					if(status!="21" && status!="25"){
						alert("不是【退卡新增、退费失败(非账户原因)】的记录，不能删除！");
						return;
					}
				   var action = $.taHash.get("ctx") + "/insucardJson/deleteRecord.do?ajax=true&idVcInsuCardSalesRecord="+id;
			       if (confirm("确定删除?")) {
				        common.ajaxSubmitDfCallBack(
				        	function(date){
				        	  alert(date);
				        	  insuCardRefundQuery.gridReload();
				            },
						action, "");
			        }
				}
			} else {
				alert("请选择一条记录！");
			}
		});



		$("#insuCardRefundGrid").jqGrid({
					//url : $.taHash.get("ctx") + insuCardRefundQuery.loadGridUrl,
			        url:"",
					mtype : "POST",
					datatype : "local",
					autowidth : true,
					height : 'auto',
					colNames : [ 'ID','退卡单号', '退卡人', '退卡人所属机构', '退卡日期', '退费总额','状态','salesRecordStatus' ],
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
					pager : '#insuCardRefundGridPager',
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
								.selectRowValue("insuCardRefundGrid");
						$("#id").val(selectValue);
					},
					
					onSelectAll: function(id){
						ids = $("#insuCardRefundGrid").jqGrid('getDataIDs');	
						$("#id").val(ids);
					},
					
					gridComplete: function(){
					    var ids = $("#insuCardRefundGrid").jqGrid('getDataIDs');
					    for(var i=0;i < ids.length;i++){
					     var rowArray = $("#insuCardRefundGrid").jqGrid('getRowData',ids[i]);
					     var editBtn = "<a href='#' onclick='insuCardRefundQuery.refundRecordView("+rowArray.idVcInsuCardSalesRecord+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.salesRecordCode+"</a>";
					     $("#insuCardRefundGrid").jqGrid('setCell', ids[i], 'salesRecordCode', editBtn);
					    }
				   }
				});
		
		// 格式化状态标志
		// 21退卡新增/22等待退费/23退费成功/24退费失败(账户原因)/25退费失败(非账户原因)
		function myStatusFormatter(cellvalue, options, rowObject) {
			if (cellvalue == '20') {
				return '退卡删除';
			} else if (cellvalue == '21') {
				return '退卡新增';
			} else if (cellvalue == '22') {
				return '等待退费';
			} else if (cellvalue == '23') {
				return '退费成功';
			}else if (cellvalue == '24') {
				return '退费失败(账户原因)';
			}else if (cellvalue == '25') {
				return '退费失败(非账户原因)';
			}
			
		}
	},
	
	refundRecordView:function(id) {
		//ajax获取要修改的数据
		common.ajaxGetData("insuCardRefundViewPage", $.taHash.get("ctx")+"/insucard/viewInsuCardRefund.do?idVcInsuCardSalesRecord="+id+"&ajax=true",null);
		//调用dialog组件
		setTimeout(function(){
			$('#insuCardRefundViewPage').dialog('open');
		},300);
	},

	gridReload : function() {
		$("#id").val("");
		var queryParam=$("#queryForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+ insuCardRefundQuery.loadGridUrl+"&"+queryParam;
		jQuery("#insuCardRefundGrid").jqGrid('setGridParam', {
			datatype : "json",
			postData:{
			},
			url : gridReloadUrl,
			page : 1
		});
		jQuery("#insuCardRefundGrid").trigger("reloadGrid");
	},

	callBack : function(data) {
		alert(data);
		insuCardRefundQuery.gridReload();
	}
}
