var docInStore = {
	loadGridUrl : "/storeJson/queryDocInStore.do?ajax=true",
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
		$("#docInStoreEditPage").dialog( {
			modal : true,
			height : 550,
			width : 950,
			autoOpen : false
		});

		$("#docInstoreViewPage").dialog( {
			modal : true,
			height : 550,
			width : 980,
			autoOpen : false
		});
		
		$("#inStoreStatus").dropDownList({ 
			 url: "/visa/baseinfoJson/initDownList.do?codeType=InStoreStatus&actionType=query"
		});  
		
		/**
		 * @description 查询按钮事件
		 * @param queryInStore
		 *            查询按钮ID
		 * @return
		 */
		$("#queryInStore").click(function() {
			docInStore.gridReload();
		});

		/**
		 * @description 新增按钮事件
		 * @param queryResource
		 *            查询按钮ID
		 * @return
		 */
		$("#addDocInStore").click(function() {
			// ajax获取要修改的数据
				// common.ajaxGetData("docInstoreViewPage",
				// $.taHash.get("ctx")+"/store/viewDocInStore.do?ajax=true",null);
				//common.ajaxGetData("docInStoreEditPage", $.taHash.get("ctx")
				//		+ "/store/initAddDocInStore.do?ajax=true", null);
				// 调用dialog组件
				//$('#docInStoreEditPage').dialog('open');
				common.localHref($.taHash.get("ctx")
					+ "/store/initAddDocInStore.do?actionType=add");
				});

		/**
		 * 修改按钮事件
		 */
		$("#modifyDocInStore").click(function() {
		// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if ("" != id) {
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if (patt.test(id)) {
					alert("只能选择一条记录！");
				} else {
					var selectGroupName = common.selectRowShowCollValue("inStoreGatherGrid","inStoreStatus",id);
					if(selectGroupName=="删除"){
						alert("已删除的申请不能修改！");
						return;
					}else if(selectGroupName=="等待审批"){
						alert("等待审批的申请不能修改！");
						return;
					}else if(selectGroupName=="审批通过"){
						alert("审批通过的申请不能修改！");
						return;
					}
					/*// ajax获取要修改的数据
					common.ajaxGetData("docInStoreEditPage",
									$.taHash.get("ctx")
											+ "/store/viewDocInStore.do?ajax=true&actionType=modify&id="
											+ id, null);
					// 调用dialog组件
					$('#docInStoreEditPage').dialog('open');*/
					
					common.localHref($.taHash.get("ctx")
							+ "/store/viewDocInStore.do?actionType=modify&id="+id);
				}
			} else {
				alert("请选择一条记录！");
			}

			});

		/**
		 * 删除按钮事件
		 */
		$("#deleteDocInStore").click(function() {
			// 获取选中行的id
				var id = $("#id").attr("value");
				if ("" != id) {
					var param = {
						"id" : id
					};
					var action = $.taHash.get("ctx")
							+ "/storeJson/deleteDocInStore.do?ajax=true";
					if (confirm("确定删除?")) {
						common.ajaxSubmitDfCallBack(docInStore.callBack,
								action, param);
					}
				} else {
					alert("请选择需要删除的记录！");
				}
			});

		/**
		 * 提交按钮事件
		 */
		$("#submitDocInStore").click(function() {
			// 获取选中行的id
				var id = $("#id").attr("value");
				if ("" != id) {
					var param = {
						"id" : id
					};
					var action = $.taHash.get("ctx")
							+ "/storeJson/submitDocInStore.do?ajax=true";
					if (confirm("确定提交?")) {
						common.ajaxSubmitDfCallBack(docInStore.callBack,
								action, param);
					}
				} else {
					alert("请选择需要提交的记录！");
				}
			});

		$("#inStoreGatherGrid").jqGrid( {
			url : $.taHash.get("ctx") +docInStore.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',
			colNames : [ 'id', '印刷入库单号', '申请机构', '申请人', '申请时间', '申请单状态' ],
			colModel : [ {
				name : 'id',
				index : 'vcDocInStore.id',
				width : 2,
				resizable : true,
				align : 'center',
				hidden : true
			}, {
				name : 'inStoreAppCode',
				index : 'vcDocInStore.inStoreAppCode',
				autowidth : true,
				resizable : true,
				align : 'left'
			}, {
				name : 'applyOrgName',
				index : 'vcDocInStore.applyOrgName',
				autowidth : true,
				resizable : true,
				align : 'left'
			}, {
				name : 'applyOprName',
				index : 'vcDocInStore.applyOprName',
				autowidth : true,
				resizable : true,
				align : 'left'
			}, {
				name : 'applyTime',
				index : 'vcDocInStore.applyTime',
				autowidth : true,
				resizable : true,
				align : 'left',
				formatter:"date",
				formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}
			}, {
				name : 'inStoreStatus',
				index : 'vcDocInStore.inStoreStatus',
				autowidth : true,
				resizable : true,
				formatter : myFlagFormatter,
				align : 'left',
			} ],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#inStoreGatherGridPager',
			viewrecords : true,
			jsonReader : {
				root : "vcDocInStores",
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
		    ids = $("#inStoreGatherGrid").jqGrid('getDataIDs');
		    for(var i=0;i < ids.length;i++){
		     var rowArray = $("#inStoreGatherGrid").jqGrid('getRowData',ids[i]);
		     var editBtn = "<a href='#' onclick='docInStore.viewInStore("+rowArray.id+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.inStoreAppCode+"</a>";
		     $("#inStoreGatherGrid").jqGrid('setCell', ids[i], 'inStoreAppCode', editBtn);
		    }
		   },
			   
			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id,status) {
				var selectValue = common.selectRowValue("inStoreGatherGrid");
				$("#id").val(selectValue);
			},
			
			onSelectAll: function(id,status){
				var ids = $("#inStoreGatherGrid").jqGrid('getDataIDs');	
				if(status){
					$("#id").val(ids);
				}else{
					$("#id").val("");
				}
			}
		});

		// 格式化状态标志
		function myFlagFormatter(cellvalue, options, rowObject) {
			//入库申请单状态（0删除/1新建/2待审批/3审批退回/4审批通过）
		    if (cellvalue == '4') {
				return '审批通过';
			} else if (cellvalue == '3') {
				return '审批退回';
			} else if (cellvalue == '2') {
				return '待审批';
			}else if (cellvalue == '1') {
				return '新建';
			} else if (cellvalue == '0') {
				return '删除';
			}
		}
		;

	},
	
	/**
	  * 浏览
	  */
	 viewInStore : function(id){
	  //ajax获取要修改的数据
		   common.ajaxGetData("docInstoreViewPage", $.taHash
					.get("ctx")
					+ "/store/viewDocInStore.do?ajax=true&id=" + id, null);
			// 调用dialog组件
			$('#docInstoreViewPage').dialog('open');
	 },
	
	alertMsg:function(msg){
		if(msg!=""){
			msg = msg.replace(/_/g,"\n");
			alert(msg);
			if(msg == '操作成功'){
				docInStore.gridReload();
			}
		}
		
	},

	gridReload : function() {
		var gridReloadUrl = $.taHash.get("ctx") + docInStore.loadGridUrl;
		jQuery("#inStoreGatherGrid").jqGrid('setGridParam', {
			datatype : "json",
			postData : {
			inStoreAppCode : $("#inStoreAppCode").val(),
			inStoreStatus : $("#inStoreStatus").val(),
			applyStartDate : $("#applyStartDate").val(),
			applyEndDate : $("#applyEndDate").val()
			},
			url : gridReloadUrl,
			page : 1
		});

		jQuery("#inStoreGatherGrid").trigger("reloadGrid");
	},

	callBack : function(data) {
		alert(data);
		docInStore.gridReload();
	}
}
