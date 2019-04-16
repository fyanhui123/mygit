var destroy = {
	loadGridUrl : "/invoiceDestroyJson/queryDestroy.do?ajax=true",
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
		$("#destroyEditPage").dialog( {
			modal : true,
			height : 550,
			width : 950,
			autoOpen : false
		});
		
		$("#destroyApprovePage").dialog( {
			modal : true,
			height : 550,
			width : 950,
			autoOpen : false
		});

		
		$("#destroyStatus").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=DestroyStatus"
		});
		/**
		 * @description 查询按钮事件
		 * @param 
		 *            查询按钮ID
		 * @return
		 */
		$("#queryDestroy").click(function() {
			destroy.gridReload();
		});

		/**
		 * @description 新增按钮事件
		 * @param queryResource
		 *            查询按钮ID
		 * @return
		 */
		$("#addDestroy").click(function() {
			// ajax获取要修改的数据
				/*common.ajaxGetData("destroyEditPage", $.taHash.get("ctx")
						+ "/destroy/initDestroyAdd.do?ajax=true", null);
				// 调用dialog组件
				$('#destroyEditPage').dialog('open');*/
				common.localHref($.taHash.get("ctx")
						+ "/invoiceDestroy/initDestroyAdd.do");
			});


		/**
		 * 修改按钮事件
		 */
		$("#modifyDestroy").click(function() {
			// 获取选中行的id
				var id = $("#id").attr("value");
				var patt = new RegExp(',');//
				if ("" != id) {
					// 判断是否选中多行（字符串存在返回true否则返回false）
					if (patt.test(id)) {
						alert("只能选择一条记录！");
					} else {
						var selectGroupName = common.selectRowShowCollValue("destroyGatherGrid","destroyStatus",id);
						if(selectGroupName=="删除"){
							alert("已删除的申请不能修改！");
							return;
						}else if(selectGroupName=="等待审批"){
							alert("待审批的申请不能修改！");
							return;
						}else if(selectGroupName=="审批通过"){
							alert("审批通过的申请不能修改！");
							return;
						}
						common.localHref($.taHash.get("ctx")
								+ "/invoiceDestroy/initDestroyEdit.do?actionType=modify&id="+id);
					}
				} else {
					alert("请选择一条记录！");
				}
			});
		/**
		 * 删除按钮事件
		 */
		$("#deleteDestroy").click(function() {
			// 获取选中行的id
				var id = $("#id").attr("value");
				if ("" != id) {
					var param = {
						"id" : id
					};
					var action = $.taHash.get("ctx")
							+ "/invoiceDestroyJson/deleteDestroy.do?ajax=true";
					if (confirm("确定删除?")) {
						common.ajaxSubmitDfCallBack(destroy.callBack,
								action, param);
					}
				} else {
					alert("请选择需要删除的记录！");
				}
			});

		/**
		 * 提交按钮事件
		 */
		$("#submitDestroy").click(function() {
			// 获取选中行的id
				var id = $("#id").attr("value");
				if ("" != id) {
					var param = {
						"id" : id
					};
					var action = $.taHash.get("ctx")
							+ "/invoiceDestroyJson/submitDestroy.do?ajax=true";
					if (confirm("确定提交?")) {
						common.ajaxSubmitDfCallBack(destroy.callBack,
								action, param);
					}
				} else {
					alert("请选择需要提交的记录！");
				}
			});

		$("#destroyGatherGrid").jqGrid( {
			url : $.taHash.get("ctx") + destroy.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',
			colNames : [ 'id', '销毁单号', '申请机构', '申请人', '申请时间', '申请单状态' ],
			colModel : [ {
				name : 'id',
				index : 'vcDestroy.id',
				width : 2,
				resizable : true,
				align : 'center',
				hidden : true
			}, {
				name : 'destroyCode',
				index : 'vcDestroy.destroyCode',
				autowidth : true,
				resizable : true,
				align : 'left'
			}, {
				name : 'destroyOrgName',
				index : 'vcDestroy.destroyOrgName',
				autowidth : true,
				resizable : true,
				align : 'left'
			}, {
				name : 'destroyOprName',
				index : 'vcDestroy.destroyOprName',
				autowidth : true,
				resizable : true,
				align : 'left'
			}, {
				name : 'destroyAppTime',
				index : 'vcDestroy.destroyAppTime',
				autowidth : true,
				formatter:"date",
				formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},
				resizable : true,
				align : 'left'
			}, {
				name : 'destroyStatus',
				index : 'vcDestroy.destroyStatus',
				autowidth : true,
				resizable : true,
				formatter : myFlagFormatter,
				align : 'left'
			} ],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#destroyGatherGridPager',
			viewrecords : true,
			jsonReader : {
				root : "vcDestroys",
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
		    ids = $("#destroyGatherGrid").jqGrid('getDataIDs');
		    for(var i=0;i < ids.length;i++){
		     var rowArray = $("#destroyGatherGrid").jqGrid('getRowData',ids[i]);
		     var editBtn = "<a href='#' onclick='destroy.viewDestroy("+rowArray.id+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.destroyCode+"</a>";
		     $("#destroyGatherGrid").jqGrid('setCell', ids[i], 'destroyCode', editBtn);
		    }
		   },
			   
			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				var selectValue = common.selectRowValue("destroyGatherGrid");
				$("#id").val(selectValue);
			},
			
			onSelectAll: function(id, status){
				var ids = $("#destroyGatherGrid").jqGrid('getDataIDs');	
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
				return '等待审批';
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
	 viewDestroy : function(id){
		var action = $.taHash.get("ctx")+ "/invoiceDestroy/initDestroyEdit.do?ajax=true&actionType=view&id=" + id;
		common.ajaxGetData("destroyApprovePage", action, null);
		// 调用dialog组件
		$('#destroyApprovePage').dialog('open');
	 },
	 

	alertMsg:function(flag){
		if(flag == 'success'){
			alert("操作成功");
			setTimeout(function(){
				destroy.gridReload();
			},300);
		}
	},
	
	gridReload : function() {
		$("#id").val("");
		var gridReloadUrl = $.taHash.get("ctx") + destroy.loadGridUrl;
		jQuery("#destroyGatherGrid").jqGrid('setGridParam', {
			datatype : "json",
			postData : {
				applyStartDate : $("#applyStartDate").val(),
				applyEndDate : $("#applyEndDate").val(),
				destroyStatus :$("#destroyStatus").val(),
				destroyCode :$("#destroyCode").val()
			},
			url : gridReloadUrl,
			page : 1
		});
		jQuery("#destroyGatherGrid").trigger("reloadGrid");
	},

	callBack : function(data) {
		alert(data);
		destroy.gridReload();
	}
}
