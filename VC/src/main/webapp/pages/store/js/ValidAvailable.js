var validAvailable = {
	/**
	 * 加载jqGrid数据
	 */
	gridReload: function () {
		var gridReloadUrl = common.ctx+"/applyJson/queryAvailableListByPages.do?ajax=true";
		var QueryCondition = new Object();
		QueryCondition.docVerCode = $("#validStorageQuery_docVerCode").val();
		QueryCondition.validOrg = $("#validOrg").val();
		QueryCondition.takerCode= $("#takerCode").val();
		
		jQuery("#validStorageGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型 
			postData : {
				jsonStr : JSON.stringify(QueryCondition)
			},
			page : 1
		});
			
		jQuery("#validStorageGrid").trigger("reloadGrid");
		$("#validStorageId").val("");
	},
	
	/**
	 * 初始化查询页面
	 */
	initQueryPage: function(){
		$("#hiddenQueryBtn").click(function() {
			validAvailable.gridReload();
		});
		
		/**
		 * 选择按钮
		 */
		$("#selectStorageRec").click(function() {
			var id = $("#validStorageId").attr("value");
			//MODIFY BY zhxiao VC-112 使用人单证批量发放、回收 BEGIN
			var rowIndex = $("#rowIndex").val();
			var docVerCode = $("#validStorageQuery_docVerCode").val();
			var tableName = $("#tableName").val();
			var patt = new RegExp(',');//
			if("" != id){
				var curRowIndex= rowIndex;
				//获取选择行的数据,可同时选取多行
			    var arrIds=id.split(",");	
			    for (var i=0;i<arrIds.length;i++){
					//获取选择行的数据
					var rowDatas = $("#validStorageGrid").jqGrid('getRowData', arrIds[i]);
					var DocVerName = rowDatas["docVerName"]; 
					var pressBatchNo = rowDatas["pressBatchNo"];
					var startNum = rowDatas["startNum"];
					var endNum = rowDatas["endNum"];
					var docNum = rowDatas["docNum"];
					
					//获取使用人发放界面多行录入域的值
					if(curRowIndex!=rowIndex){								
						$("#docRecoveryInput").mulLineWidget.resetVal(rowIndex, "docVerCode", docVerCode);
						$("#docVerCode__"+rowIndex+"Name").val(DocVerName);
					}
					$("#docRecoveryInput").mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
					$("#docRecoveryInput").mulLineWidget.resetVal(rowIndex, "startNum", startNum);
					$("#docRecoveryInput").mulLineWidget.resetVal(rowIndex, "endNum", endNum);
					$("#docRecoveryInput").mulLineWidget.resetVal(rowIndex, "docNum", docNum);
					
					if(i < arrIds.length-1){
						//新增一行
						$("#addOneLine"+tableName).click();
						if(i==0){
							//第一行新增后查询记录总数
							//当前数据数目【除当前选择单证类型的那条数据外，其他全是在最后新增记录行】
							var jsonArr = $("#docRecoveryInput").mulLineWidget.getData();
							rowIndex=jsonArr.length;  //指向最后一行							   
						}else{
							//最后一行
							rowIndex++;
						}
					}
					$('#validAvailableDiv').dialog('close');
			    }
			    /*if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					var rowIndex = $("#rowIndex").val();
					//获取选择行的数据
					var rowDatas = $("#validStorageGrid").jqGrid('getRowData', id);
					var pressBatchNo = rowDatas["pressBatchNo"];
					var startNum = rowDatas["startNum"];
					var endNum = rowDatas["endNum"];
					var docNum = rowDatas["docNum"];
					//获取使用人发放界面多行录入域的值
					$("#docRecoveryInput").mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
					$("#docRecoveryInput").mulLineWidget.resetVal(rowIndex, "startNum", startNum);
					$("#docRecoveryInput").mulLineWidget.resetVal(rowIndex, "endNum", endNum);
					$("#docRecoveryInput").mulLineWidget.resetVal(rowIndex, "docNum", docNum);
					$('#validAvailableDiv').dialog('close');
				}*/
				//MODIFY BY zhxiao VC-112 使用人单证批量发放、回收 BEGIN
			}else{
				alert("请选择一条记录！");
			}
		});
		
		$("#validStorageGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['ID', '单证类型', '印刷批次', '起始流水号','终止流水号', '单证数量','使用截止期', '单证状态', '所属机构'],
			colModel : [{
						name : 'id',
						index : 'id',
						hidedlg : true,
						hidden : true
					},{
						name : 'docVerName',
						index : 'docVerName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'pressBatchNo',
						index : 'pressBatchNo',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'startNum',
						index : 'startNum',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'endNum',
						index : 'endNum',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'docNum',
						index : 'docNum',
						autowidth : true,
						resizable : true,
						align : 'center'
					},{
						name : 'deadline',
						index : 'deadline',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d'}
					},{
						name : 'docStatusZh',
						index : 'docStatusZh',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'orgName',
						index : 'orgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#validStorageGridPager',
			viewrecords : true,
			rownumbers: true,
			jsonReader: {
		     	root: "resultList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "id"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("validStorageGrid");
				$("#validStorageId").val(selectValue);
			},
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#validStorageId").val("");
				}else{//全部选中时
					var ids = $("#validStorageGrid").jqGrid('getDataIDs');
					$("#validStorageId").val(ids);
				}
			},
			onPaging:function(pgButton) {
				$("#validStorageId").val("");
			}
		});
	}
}