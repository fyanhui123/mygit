var validStorage = {
	
	/**
	 * 加载jqGrid数据
	 */
	gridReload: function () {
		var gridReloadUrl = common.ctx+"/invoiceDestroyJson/queryValidStorageListByPages.do?ajax=true";
		var QueryCondition = new Object();
		QueryCondition.docVerCode = $("#validStorageQuery_docVerCode").val();
		QueryCondition.validOrg = $("#validOrg").val();
		QueryCondition.validDocStatus = $("#validDocStatus").val();
		QueryCondition.validQueryType = $("#validQueryType").val();
		
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
			validStorage.gridReload();
		});
		
		$("#selectStorageRec").click(function() {
			var id = $("#validStorageId").attr("value");
			var rowIndex = $("#rowIndex").val();
			var tableName = $("#tableName").val();
			var docVerCode = $("#validStorageQuery_docVerCode").val();
			var patt = new RegExp(',');//
			var deadLine = $("#validDeadline").val();
			
			if("" != id){
				//检查单证发放、使用人单证发放，选中号段是否已过使用截止期
				var isOverdue = validStorage.checkDeadLine(id,tableName);

				///销毁可以选择多条
				if(tableName!="destroyEditList" && tableName!="docIssuedInput" && isOverdue){
						if( patt.test(id)){
						    alert("只能选择一条记录！");
						}else{
						if(rowIndex && rowIndex!=""){
							//获取选择行的数据
							var rowDatas = $("#validStorageGrid").jqGrid('getRowData', id);
							var pressBatchNo = rowDatas["pressBatchNo"]; 
							var startNum = rowDatas["startNum"];
							var endNum = rowDatas["endNum"];
							var docNum = rowDatas["docNum"];
							var curStatus = rowDatas["docStatus"];
							//发放界面
							if(tableName=="DocListProvide"){
								//获取发放界面多行录入域的值
								var jsonArr = $("#DocListProvide").mulLineWidget.getData();
								//获取发放界面的申印数量
								var applyNum = jsonArr[rowIndex-1].applyNum;
								var int_applyNum = parseInt(applyNum, 10);
								var int_docNum = parseInt(docNum, 10);
								$("#DocListProvide").mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
								$("#DocListProvide").mulLineWidget.resetVal(rowIndex, "startNum", startNum);
								//1、如果库存数量小于等于申请数量，发放数量=库存数量
								if(int_docNum<=int_applyNum){
									$("#DocListProvide").mulLineWidget.resetVal(rowIndex, "endNum", endNum);
									$("#DocListProvide").mulLineWidget.resetVal(rowIndex, "provideNum", docNum);
								}else{
									//2、否则，发放数量=申请数量
									var int_startNum = parseInt(startNum, 10);
									var length = endNum.length;
									var int_endNum = int_startNum + int_applyNum - 1;
									int_endNum=common.formatNumber(int_endNum+"", length);
									//alert(int_startNum+","+int_endNum+","+int_applyNum);
									$("#DocListProvide").mulLineWidget.resetVal(rowIndex, "endNum", int_endNum);
									$("#DocListProvide").mulLineWidget.resetVal(rowIndex, "provideNum", applyNum);
								}
								$('#validStorageDiv').dialog('close');
							}
							/*//使用人发放界面
							else if(tableName=="docIssuedInput"){
								//获取使用人发放界面多行录入域的值
								var jsonArr = $("#docIssuedInput").mulLineWidget.getData();
								$("#docIssuedInput").mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
								$("#docIssuedInput").mulLineWidget.resetVal(rowIndex, "startNum", startNum);
								$("#docIssuedInput").mulLineWidget.resetVal(rowIndex, "endNum", endNum);
								$("#docIssuedInput").mulLineWidget.resetVal(rowIndex, "docNum", docNum);
								$('#validStorageDiv').dialog('close');
							}*/
							//回收申请界面
							else if(tableName=="DocListRecycleNew"){
								//获取回收申请界面多行录入域的值
								var jsonArr = $("#DocListRecycleNew").mulLineWidget.getData();
								$("#DocListRecycleNew").mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
								$("#DocListRecycleNew").mulLineWidget.resetVal(rowIndex, "startNum", startNum);
								$("#DocListRecycleNew").mulLineWidget.resetVal(rowIndex, "endNum", endNum);
								$("#DocListRecycleNew").mulLineWidget.resetVal(rowIndex, "recycleNum", docNum);
								$("#DocListRecycleNew").mulLineWidget.resetVal(rowIndex, "docStatus", curStatus);
								$('#validStorageDiv').dialog('close');
							}
							//激活卡销售-新增界面
							else if (tableName=="insuCardDetList") {
								$("#insuCardDetList").mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
								$("#insuCardDetList").mulLineWidget.resetVal(rowIndex, "startNum", startNum);
								$("#insuCardDetList").mulLineWidget.resetVal(rowIndex, "endNum", endNum);
								$("#insuCardDetList").mulLineWidget.resetVal(rowIndex, "saleNum", docNum);
								$('#validStorageDiv').dialog('close');
							}
							/*//单证销毁申请、修改编辑界面
							else if (tableName=="destroyEditList") {
								//获取单证销毁申请、修改界面多行录入域的值
								var jsonArr = $("#destroyEditList").mulLineWidget.getData();
								$("#destroyEditList").mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
								$("#destroyEditList").mulLineWidget.resetVal(rowIndex, "startNum", startNum);
								$("#destroyEditList").mulLineWidget.resetVal(rowIndex, "endNum", endNum);
								$("#destroyEditList").mulLineWidget.resetVal(rowIndex, "destroyNum", docNum);
								$("#destroyEditList").mulLineWidget.resetVal(rowIndex, "docStatus", curStatus);
								$("#addOneLine"+tableName).click();
								$('#validStorageDiv').dialog('close');
							}*/
							//单证库存作废申请、修改编辑界面
							else if (tableName=="DocListCancelS" || tableName=="DocListCancelSNew") {
								//获取单证库存作废申请、修改编辑界面多行录入域的值
								var tagIdName="#"+tableName;
								var jsonArr = $(tagIdName).mulLineWidget.getData();
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "startNum", startNum);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "endNum", endNum);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "cancelNum", docNum);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "docStatus", curStatus);
								$('#validStorageDiv').dialog('close');
							}
							//遗失申请新增修改编辑页
							else if (tableName=="lostApplyEditList") {
								//获取单证库遗失申请新增修改编辑界面多行录入域的值
								var tagIdName="#"+tableName;
								var jsonArr = $(tagIdName).mulLineWidget.getData();
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "startNum", startNum);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "endNum", endNum);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "lostNum", docNum);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "docStatus", curStatus);
								$('#validStorageDiv').dialog('close');
							}
							//单证出入库操作页
							else if (tableName=="docInOutDetinput") {
								//获取单证库遗失申请新增修改编辑界面多行录入域的值
								var tagIdName="#"+tableName;
								var jsonArr = $(tagIdName).mulLineWidget.getData();
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "pressBatchNo", pressBatchNo);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "startNum", startNum);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "endNum", endNum);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "docNum", docNum);
								$(tagIdName).mulLineWidget.resetVal(rowIndex, "docStatus", curStatus);
								$('#validStorageDiv').dialog('close');
							}
						}
					}
				}else if(isOverdue){
					   //MODIFY BY zhxiao VC-112 使用人单证批量发放、回收 BEGIN
					   var curRowIndex= rowIndex;
					   //获取选择行的数据,可同时选取多行
					    var arrIds=id.split(",");	
						for (var i=0;i<arrIds.length;i++){
							var curRowData = $("#validStorageGrid").jqGrid('getRowData', arrIds[i]);
							var curDocVerName = curRowData["docVerName"]; 
							var curPressBatchNo = curRowData["pressBatchNo"]; 
							var curStartNum = curRowData["startNum"];
							var curEndNum = curRowData["endNum"];
							var curDocNum = curRowData["docNum"];
							var curStatus = curRowData["docStatus"];

							if(tableName=="destroyEditList"){
								if(curRowIndex!=rowIndex){	

									$("#destroyEditList").mulLineWidget.resetVal(curRowIndex, "docVerCode", docVerCode);
									//$("#destroyEditList").mulLineWidget.resetVal(curRowIndex, "docVerCodeName", curDocVerName);
									$("#docVerCode__"+curRowIndex+"Name").val(curDocVerName);
								}
								$("#destroyEditList").mulLineWidget.resetVal(curRowIndex, "pressBatchNo", curPressBatchNo);
								$("#destroyEditList").mulLineWidget.resetVal(curRowIndex, "startNum", curStartNum);
								$("#destroyEditList").mulLineWidget.resetVal(curRowIndex, "endNum", curEndNum);
								$("#destroyEditList").mulLineWidget.resetVal(curRowIndex, "destroyNum", curDocNum);
								$("#destroyEditList").mulLineWidget.resetVal(curRowIndex, "docStatus", curStatus);
							}
							
							//使用人发放界面
							else if(tableName=="docIssuedInput"){
								//获取使用人发放界面多行录入域的值
								if(curRowIndex!=rowIndex){								
									$("#docIssuedInput").mulLineWidget.resetVal(curRowIndex, "docVerCode", docVerCode);
									$("#docVerCode__"+curRowIndex+"Name").val(curDocVerName);
								}
								var jsonArr = $("#docIssuedInput").mulLineWidget.getData();
								$("#docIssuedInput").mulLineWidget.resetVal(curRowIndex, "pressBatchNo", curPressBatchNo);
								$("#docIssuedInput").mulLineWidget.resetVal(curRowIndex, "startNum", curStartNum);
								$("#docIssuedInput").mulLineWidget.resetVal(curRowIndex, "endNum", curEndNum);
								$("#docIssuedInput").mulLineWidget.resetVal(curRowIndex, "docNum", curDocNum);
								$("#docIssuedInput").mulLineWidget.resetVal(curRowIndex, "deadline", deadLine);
							}
							
							if(i < arrIds.length-1){
							  //新增一行
							  $("#addOneLine"+tableName).click();
							  if(i==0){
								 //第一行新增后查询记录总数
							    //当前数据数目【除当前选择单证类型的那条数据外，其他全是在最后新增记录行】
							    var jsonArr = $("#"+tableName).mulLineWidget.getData();
							    curRowIndex=jsonArr.length;  //指向最后一行							   
							  }else{
							   //最后一行
							   curRowIndex++;
							  }
					        }
						}
						//MODIFY BY zhxiao VC-112 使用人单证批量发放、回收 END
						$('#validStorageDiv').dialog('close');
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		$("#validStorageGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			//autowidth : true,
			width : $(window).width()*0.73,		
			height: '230px',
			colNames : ['ID', '单证类型', '印刷批次', '起始流水号','终止流水号', '单证数量', '使用截止期', '单证状态', '所属机构','status'],
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
						width : 120,
						resizable : true,
						align : 'left'
					},{
						name : 'startNum',
						index : 'startNum',
						width : 100,
						resizable : true,
						align : 'left'
					},{
						name : 'endNum',
						index : 'endNum',
						width : 100,
						resizable : true,
						align : 'left'
					},{
						name : 'docNum',
						index : 'docNum',
						width : 80,
						resizable : true,
						align : 'center'
					},{
						name : 'deadline',
						index : 'deadline',
						width : 90,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d'}
					},{
						name : 'docStatusZh',
						index : 'docStatusZh',
						width : 100,
						resizable : true,
						align : 'left'
					},{
						name : 'orgName',
						index : 'orgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'docStatus',
						index : 'docStatus',
						hidden : true
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
		
		
	},
	

	/**
	* 使用截止期校验
	* 单证发放、使用人单证发放、激活卡销售
	*/
	checkDeadLine : function(id, tableName) {
		var isOverdue = true;
		if (tableName == "DocListProvide" || tableName == "docIssuedInput"
				|| tableName == "insuCardDetList") {
			var arrIds = id.split(",");
			for (var i = 0; i < arrIds.length; i++) {
				var curRowData = $("#validStorageGrid").jqGrid('getRowData',
						arrIds[i]);
				var curStartNum = curRowData["startNum"];
				var curEndNum = curRowData["endNum"];
				var curDocNum = curRowData["docNum"];
				var rowDeadline = curRowData["deadline"].replace(/-/g, "/");
				var date = new Date(rowDeadline);
				var now = new Date();
				var tomorrow = new Date(date.getTime() + 1000 * 60 * 60 * 24);
				if (tomorrow.getTime() < now.getTime()) {
					isOverdue = false;
					alert("选中号段[" + curStartNum + "-" + curEndNum
							+ "]已过使用截止期，无法发放！");
					break;
				}
			}
		}
		return isOverdue;
	}
		
}