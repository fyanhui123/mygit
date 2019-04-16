var docStorageInOut = {
	loadGridUrl : "/storeInOutJson/queryStorageInOutByPages.do?ajax=true",
	
	//操作成功提示信息
	alertResultMsg:function(actionType,msg){
		if(actionType == 'saveSuccess'){
			alert(msg);
		}
	},
	
	operateFlag : [{
  		"label" : "出库",
		 "value" : "O"
	 },{
		 "label" : "出库后入库",
		 "value" : "I"
	} ],
	
	initPage : function() {
		/**
		 * 单证类型自动完成
		 */
		$("#docVerCode").taAutoComplete({
			source : $.taHash.get("ctx")+ "/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no",
			minLength : 0,
			height : 200,
			width : 220
		});
		
		$("#vcStorageInOutOperateFlag").dropDownList( {
			data : docStorageInOut.operateFlag
		});
		
		/**
		 * @description 查询按钮事件
		 * @param queryInStore
		 *            查询按钮ID
		 * @return
		 */
		$("#queryStorageInOut").click(function() {
			docStorageInOut.gridReload();
		});
		
		/**
		 * @description 出库事件
		 * @param queryResource
		 *            查询按钮ID
		 * @return
		 */
		$("#storageOut").click(function() {				
			common.formSubmit("queryForm",$.taHash.get("ctx")+ "/storeInOut/initStorageInOutAdd.do?operateType=O&random="+Math.random());
		});
		$("#storageIn").click(function() {
			common.formSubmit("queryForm",$.taHash.get("ctx")+ "/storeInOut/initStorageInOutAdd.do?operateType=I&random="+Math.random());
		});

		$("#docStorageInOutGrid").jqGrid({
					url : $.taHash.get("ctx") + docStorageInOut.loadGridUrl,
					mtype : "POST",
					datatype : "local",
					autowidth : true,
					height : 'auto',
					colNames : ['单证类型', '单证类型名称','印刷批次', '起始号', '终止号', '数量','操作时间','操作人','出库/入库' ],
					colModel : [{
						name : 'docVerCode',
						index : 'docVerCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'docVerName',
						index : 'docVerName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
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
					}, {
						name : 'endNum',
						index : 'endNum',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'docNum',
						index : 'docNum',
						autowidth : true,
						resizable : true,
						align : 'center'
					},{
						name : 'operateTime',
						index : 'operateTime',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i:s'}
					},{
						name : 'oprName',
						index : 'oprName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'operateFlag',
						index : 'operateFlag',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:function(value,row,index){
					         if(value=='O'){
					        	 return "出库";
					         }else{
					        	 return "入库"; 
					         }
						 }	
					} ],
					shrinkToFit : true,
					rowNum : 10,
					rowList : [ 10, 30, 50 ],
					pager : '#docStorageInOutGridPager',
					viewrecords : true,
					jsonReader : {
						root : "vcStorageInOutList",
						repeatitems : false,
						page : 'page',
						total : 'total',
						records : 'records',
						id : "idVcStorageInOutDet"
					},

					/**
					 * @description 获取选中的值
					 * @param
					 * @return
					 */
					onSelectRow : function(id) {
						var selectValue = common
								.selectRowValue("docStorageInOutGrid");
						$("#id").val(selectValue);
					},
					
					onSelectAll: function(id){
						ids = $("#docStorageInOutGrid").jqGrid('getDataIDs');	
						$("#id").val(ids);
					}


				});
	},

	gridReload : function() {
		var queryParam=$("#queryForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+ docStorageInOut.loadGridUrl+"&"+queryParam;
		jQuery("#docStorageInOutGrid").jqGrid('setGridParam', {
			datatype : "json",
			postData:{},
			url : gridReloadUrl,
			page : 1
		});
		jQuery("#docStorageInOutGrid").trigger("reloadGrid");
	}
}
