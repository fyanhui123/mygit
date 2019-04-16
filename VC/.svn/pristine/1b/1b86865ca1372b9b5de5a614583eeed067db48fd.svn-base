var availableDocInquiry = {

	loadGridUrl : "/storageInquiryJson/queryAvailableDocInquiryListByPages.do?ajax=true",
		
	initPage : function() {	
		
		/**
		 * @description 查询按钮事件
		 * @param queryAvailableDocInquiry
		 *            查询按钮ID
		 * @return
		 */
		$("#queryAvailableDocInquiry").click(function() {
			availableDocInquiry.gridReload();
		});
		
		//单证种类
		$( "#availableDocInquiryVoDocTypeList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=auto",
			minLength: 0,
			height: 200,
			width:220
		});
		//单证类型
		$( "#availableDocInquiryVoDocVersionInfoList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto&isCurOrUpperOrgDoc=yes",
			minLength: 0,
			height: 200,
			width:220
		});
		
		//单证种类作为单证类型的查询条件
		$("#availableDocInquiryVoDocTypeList").change( function(){
			var docType = $("#availableDocInquiryVoDocTypeList").val();
			//单证类型
			$("#availableDocInquiryVoDocVersionInfoList").taAutoComplete({
				source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto&isCurOrUpperOrgDoc=yes&docTypeCode="+docType,
				minLength: 0,
				height: 200,
				width:220
			});
		});
		//单证险类
		$("#availableDocInquiryVoInsuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true"
		}); 
		//单证险类作为单证险种的查询条件
		$("#availableDocInquiryVoInsuTypeList").change( function() {		 
			var select = $("#availableDocInquiryVoInsuTypeList").val();	
			setTimeout(function(){
				$("#availableDocInquiryVoInsuKindList").dropDownList({
					url : $.taHash.get("ctx")+ "/baseinfoJson/initInsuKindListWithCondition.do?ajax=true&insuTypeCode="+select					
				});
			},100);
		});
		//单证险种
		$("#availableDocInquiryVoInsuKindList").dropDownList({
			trigger: '#storageInquiryVoInsuTypeList',    
			event: 'change',
			dataField:'name',
			dataType:'json',
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true"
		});
		
		
		$("#availableDocInquiryGrid").jqGrid( {
			url : availableDocInquiry.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : '230px',	
			colNames : ['ID', '单证类型代码', '单证类型名称','所属机构','机构/使用人','起始号码','终止号码','库存数量','印刷批次','入库时间','使用截止期','状态'],
			colModel : [{name : 'id',
						 index : 'id',
						 hidedlg : true,
						 hidden : true
					},{
						name : 'docVerCode',
						index : 'docVerCode',
						width : '12%',
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'docVerName',
						index : 'docVerName',
						width : '12%',
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'orgCode',
						index : 'orgCode',
						width : '22%',
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'docTakerCode',
						index : 'docTakerCode',
						width : '12%',
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'startNum',
						index : 'startNum',
						width : '7%',
						resizable : true,
						sortable : false,
						align : 'left'

					},{
						name : 'endNum',
						index : 'endNum',
						width : '7%',
						resizable : true,
						sortable : false,
						align : 'left'

					},{
						name : 'docNum',
						index : 'docNum',
						width : '6%',
						resizable : true,
						sortable : false,
						align : 'left'

					},{
						name : 'pressBatchNo',
						index : 'pressBatchNo',
						width : '8%',
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'inStoreTime',
						index : 'inStoreTime',
						width : '8%',
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d'}
					},{
						name : 'deadLine',
						index : 'deadLine',
						width : '8%',
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d'}
					},{
						name : 'docStatus',
						index : 'docStatus',
						width : '6%',
						resizable : true,
						sortable : false,
						formatter : myFlagFormatter,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#availableDocInquiryGridPager',
			viewrecords : true,
			jsonReader : {
				root : "availableDocInquiryVoList",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "id"
			},
			multiselect : false,

			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				var selectValue = common.selectRowValue("availableDocInquiryGrid");
				$("#id").val(selectValue);
			}

		});
		
		// 格式化状态标志
		function myFlagFormatter(cellvalue, options, rowObject) {
			if (cellvalue == 'A') {
				return '可使用';
			} 
		};
		
	},
	
	gridReload : function() {
		var tmpStr = $("#subForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+availableDocInquiry.loadGridUrl+"&"+tmpStr;
		jQuery("#availableDocInquiryGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData : {},
			page : 1
		});
		jQuery("#availableDocInquiryGrid").trigger("reloadGrid");
	}
		

}