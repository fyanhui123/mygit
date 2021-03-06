var docInStore = {
	loadGridUrl : "/storeJson/queryDocInStoreApprove.do?ajax=true",
	initPage: function(){
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#docInstoreApprovePage").dialog({
			modal: true,
			height:620,
			width:1000,
			autoOpen: false
		});
		
		/*
		$("#inStoreStatus").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=ApplyApproveStatus"
		});  
		*/
		
	
		/**
		* @description 查询按钮事件
		* @param  queryInStore 查询按钮ID
		* @return 
		*/
		$("#queryInStore").click( function() {
			docInStore.gridReload();
		});
		
		/**
		 * 审批按钮事件
		 */
		$("#approveDocInStore").click(function(){
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					var selectGroupName = common.selectRowShowCollValue("inStoreApproveGrid","inStoreStatus",id);
					if(selectGroupName=="审批通过"){
						alert("已审批通过的申请不能再次审批！");
						return;
					}else if(selectGroupName=="审批退回"){
						alert("已审批退回的申请不能再次审批！");
						return;
					}
					//ajax获取要修改的数据
					common.ajaxGetData("docInstoreApprovePage", $.taHash.get("ctx")+"/store/initDocInStoreApprove.do?ajax=true&id="+id,null);
					//调用dialog组件
					$('#docInstoreApprovePage').dialog('open');
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		$("#inStoreApproveGrid").jqGrid({
			url : $.taHash.get("ctx") + docInStore.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',
			colNames : ['id','印刷入库单号','申请机构', '申请人', '申请时间', '申请单状态'],
			colModel :[{
						name : 'id',
						index : 'vcDocInStore.id',
						resizable : true,
						align : 'center',
						hidden: true
					},{
						name : 'inStoreAppCode',
						index : 'vcDocInStore.inStoreAppCode',
						resizable : true,
						align : 'left'
					},{
						name : 'applyOrgName',
						index : 'vcDocInStore.applyOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyOprName',
						index : 'vcDocInStore.applyOprName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyTime',
						index : 'vcDocInStore.applyTime',
						autowidth : true,
						datefmt : "yyyy-mm-dd",
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}
					},{
						name : 'inStoreStatus',
						index : 'vcDocInStore.inStoreStatus',
						autowidth : true,
						resizable : true,
						formatter : myFlagFormatter,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#inStoreApproveGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcDocInStores",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "id"
		     },
			multiselect: true,
			
			/**
		    * 添加链接
		    */
		   gridComplete: function(){
		    ids = $("#inStoreApproveGrid").jqGrid('getDataIDs');
		    for(var i=0;i < ids.length;i++){
		     var rowArray = $("#inStoreApproveGrid").jqGrid('getRowData',ids[i]);
		     var editBtn = "<a href='#' onclick='docInStore.viewInStore("+rowArray.id+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.inStoreAppCode+"</a>";
		     $("#inStoreApproveGrid").jqGrid('setCell', ids[i], 'inStoreAppCode', editBtn);
		    }
		   },
			   
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("inStoreApproveGrid");
				$("#id").val(selectValue);
			},

			onSelectAll: function(id,status){
				var ids = $("#inStoreApproveGrid").jqGrid('getDataIDs');	
				if(status){
					$("#id").val(ids);
				}else{
					$("#id").val("");
				}
			}
		     
		});
			
		//格式化状态标志
		function myFlagFormatter (cellvalue, options, rowObject) {
			if(cellvalue=='1'){
				return '新建';
			}else if(cellvalue=='4'){
				return '审批通过';
			}else if(cellvalue=='3'){
				return '审批退回';
			}else if(cellvalue=='2'){
				return '等待审批';
			}
		};
	},

	/**
	  * 浏览
	  */
	 viewInStore : function(id){
	  //ajax获取要修改的数据
		//ajax获取要修改的数据
		common.ajaxGetData("docInstoreApprovePage", $.taHash.get("ctx")+"/store/initDocInStoreApprove.do?ajax=true&id="+id+"&actionType=view",null);
		//调用dialog组件
		$('#docInstoreApprovePage').dialog('open');
	 },
	 
	gridReload: function () {
			var gridReloadUrl = $.taHash.get("ctx") + docInStore.loadGridUrl;
			jQuery("#inStoreApproveGrid").jqGrid('setGridParam', {
				datatype : "json",
				postData:{
					inStoreAppCode: $("#inStoreAppCode").val(),
					inStoreStatus : $("#inStoreStatusFlag").val(),
					applyStartDate : $("#applyStartDate").val(),
					applyEndDate : $("#applyEndDate").val()
					
				},
				url : gridReloadUrl,
				page : 1
			});
			
			jQuery("#inStoreApproveGrid").trigger("reloadGrid");
			
		},
}



