var lostApproveQuery = {
	loadGridUrl : "/newinvoiceJson/queryLostApprove.do",
	initPage : function() {
		/** 
		 * @description 初始化dialog
		 * @param modal
		 *            是否模式层
		 * @param height
		 *            模式层高度(像素)
		 * @param width
		 *            模式层宽度
		 * @param autoOpen
		 *            是否默认打开模式层
		 * @return
		 */
		$("#lostApprovePage").dialog( {
			modal : true,
			height : 600,
			width : 950,
			autoOpen : false
		});
		
		
		$("#lostStatus").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=ApplyApproveStatus&actionType=query"
		});
		
		/**
		 * @description 查询按钮事件
		 * @param lostApply
		 *            查询按钮ID
		 * @return
		 */
		$("#queryLostApprove").click(function() {
			lostApproveQuery.gridReload();
		});

		/**
		 * 审批按钮事件
		 */
		$("#lostApprove").click(function() {
			// 获取选中行的id
				var id = $("#id").attr("value");
				var patt = new RegExp(',');//
				if ("" != id) {
					// 判断是否选中多行（字符串存在返回true否则返回false）
					if (patt.test(id)) {
						alert("只能选择一条记录！");
					} else {
						var selectGroupName = common.selectRowShowCollValue("lostApplyGatherGrid","lostStatus",id);
						if(selectGroupName=="审批退回"){
							alert("审批退回的申请不能再次审批！");
							return;
						}else if(selectGroupName=="审批通过"){
							alert("审批通过的申请不能再次审批！");
							return;
						}
						// ajax获取要修改的数据
						var action = $.taHash.get("ctx")+ "/newinvoice/viewLostApply.do?ajax=true&actionType=approve&id=" + id;
						common.ajaxGetData("lostApprovePage", action , null);
						// 调用dialog组件
						$('#lostApprovePage').dialog('open');
					}
				} else {
					alert("请选择一条记录！");
				}
			});

		
		$("#lostApplyGatherGrid").jqGrid( {
			url : $.taHash.get("ctx") + lostApproveQuery.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',
			colNames : [ 'id', '遗失单号', '申请机构', '申请人', '申请时间', '申请单状态' ],
			colModel : [ {
				name : 'id',
				index : 'vcLost.id',
				width : 2,
				resizable : true,
				align : 'center',
				hidden : true
			}, {
				name : 'lostCode',
				index : 'vcLost.lostCode',
				autowidth : true,
				resizable : true,
				align : 'left'
			}, {
				name : 'lostOrgName',
				index : 'vcLost.lostOrgName',
				autowidth : true,
				resizable : true,
				align : 'left'
			}, {
				name : 'lostOprName',
				index : 'vcLost.lostOprName',
				autowidth : true,
				resizable : true,
				align : 'left'
			}, {
				name : 'lostTime',
				index : 'vcLost.lostTime',
				autowidth : true,
				formatter:"date",
				formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},
				resizable : true,
				align : 'left'
			}, {
				name : 'lostStatus',
				index : 'vcLost.lostStatus',
				autowidth : true,
				resizable : true,
				formatter : myFlagFormatter,
				align : 'left'
			} ],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#lostApplyGatherGridPager',
			viewrecords : true,
			jsonReader : {
				root : "vcLosts",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "id"
			},
			multiselect : true,

			/**
		    * 添加链接
		    */
		   gridComplete: function(){
		    ids = $("#lostApplyGatherGrid").jqGrid('getDataIDs');
		    for(var i=0;i < ids.length;i++){
		     var rowArray = $("#lostApplyGatherGrid").jqGrid('getRowData',ids[i]);
		     var editBtn = "<a href='#' onclick='lostApproveQuery.viewLostApprove("+rowArray.id+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.lostCode+"</a>";
		     $("#lostApplyGatherGrid").jqGrid('setCell', ids[i], 'lostCode', editBtn);
		    }
		   },
			   
			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				var selectValue = common.selectRowValue("lostApplyGatherGrid");
				$("#id").val(selectValue);
			},
			
			onSelectAll: function(id, status){
				var ids = $("#lostApplyGatherGrid").jqGrid('getDataIDs');	
				if(status){
					$("#id").val(ids);
				}else{
					$("#id").val("");
				}
			}
		});

		// 格式化状态标志
		function myFlagFormatter(cellvalue, options, rowObject) {
			if (cellvalue == '1') {
				return '新建';
			} else if (cellvalue == '2') {
				return '待审批';
			} else if (cellvalue == '3') {
				return '审批退回';
			} else if (cellvalue == '4') {
				return '审批通过';
			} else if (cellvalue == '0') {
				return '删除';
			}
		};
	},

	/**
	  * 浏览
	  */
	 viewLostApprove : function(id){
		var action = $.taHash.get("ctx")+ "/lostApply/viewLostApply.do?ajax=true&actionType=approveView&id=" + id;
		common.ajaxGetData("lostApprovePage", action , null);
		// 调用dialog组件
		$('#lostApprovePage').dialog('open');
	 },
	 
	gridReload : function() {
		$("#id").val("");
		var gridReloadUrl = $.taHash.get("ctx") + lostApproveQuery.loadGridUrl;
		jQuery("#lostApplyGatherGrid").jqGrid('setGridParam', {
			datatype : "json",
			postData : {
				lostCode : $("#lostCode").val(),
				lostStatus : $("#lostStatus").val(),
				applyStartDate : $("#applyStartDate").val(),
				applyEndDate : $("#applyEndDate").val()
			},
			url : gridReloadUrl,
			page : 1
		});
		jQuery("#lostApplyGatherGrid").trigger("reloadGrid");
	},

	callBack : function(data) {
		alert(data);
		lostApproveQuery.gridReload();
	}
}
