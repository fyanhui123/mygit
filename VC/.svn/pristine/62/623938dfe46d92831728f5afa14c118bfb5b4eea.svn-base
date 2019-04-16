var orderApprove = {
		
	loadGridUrl : "/orderJson/queryOrderApprove.do?ajax=true",
		
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
		$("#queryOrderApprove").click( function() {
			orderApprove.gridReload();
		});
		
		/**
		* @description 重置按钮事件
		* @param  restBtn 重置按钮ID
		* @return 
		*/
		$("#reset").click( function() {
			//清空查询条件
			$("#orderCode").val("");
			$("#applyStartDate").val("");
			$("#applyEndDate").val("");
			//清空选择的id
			$("#id").val("");
			//清空表格数据
			$("#orderApproveGrid").clearGridData()
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
					var status = common.selectRowShowCollValue("orderApproveGrid","flag",id);
					if(status=='待审批'){						
					   //ajax获取要修改的数据
					   common.ajaxGetData("appEditPage", $.taHash.get("ctx")+"/orderManager/editOrderApprove.do?ajax=true&id="+id,null);
					   //调用dialog组件					
					  $('#appEditPage').dialog('open');
					}else{
						alert("已审批，不能再进行此操作！");
					}
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		/**
		* 已审批回退按钮事件
		*/
		$("#approvedBackBtn").click( function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					var status = common.selectRowShowCollValue("orderApproveGrid","flag",id);
					if(status=='审批通过'){	
						//ajax获取要修改的数据
						common.ajaxGetData("appEditPage", $.taHash.get("ctx")+"/orderManager/editOrderApprove.do?ajax=true&approvedBackFlag=back&id="+id,null);
						//调用dialog组件					
						$('#appEditPage').dialog('open');
					}else{
						alert("未审批通过，不能进行此操作！");
					}
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		$("#orderApproveGrid").jqGrid({
			url : $.taHash.get("ctx")+ orderApprove.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','订单号','申请机构', '申请人', '申请时间', '订单状态'],
			colModel : [{
						name : 'orderId',
						index : 'orderId',
						hidden : true
					},{
						name : 'orderCode',
						index : 'orderCode',
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
						name : 'createUserName',
						index : 'createUserName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'createDate',
						index : 'createDate',
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
			pager : '#orderApproveGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "orderLaunchList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "orderId"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("orderApproveGrid");
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
					var ids = $("#orderApproveGrid").jqGrid('getDataIDs');
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
		var gridReloadUrl = $.taHash.get("ctx")+ orderApprove.loadGridUrl;
		jQuery("#orderApproveGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				orderCode : $("#orderCode").val(),
				applyStartDate : $("#applyStartDate").val(),
				applyEndDate : $("#applyEndDate").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#orderApproveGrid").trigger("reloadGrid");
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