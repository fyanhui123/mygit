var storageInquiry = {
	loadGridUrl : "/storageInquiryJson/queryStorageInquiryListByPages.do?ajax=true",
	storageStatusData: [ {"label" : "印刷入库","value" : "S1"},{"label" : "申领/调拨入库","value" : "S2"},{"label" : "回收入库","value" : "S3"},
	                   {"label" : "可使用","value" : "A"}],
		
	initPage : function() {	
		
		/**
		 * @description 查询按钮事件
		 * @param queryApplyquiry
		 *            查询按钮ID
		 * @return
		 */
		$("#queryStorageInquiry").click(function() {
			storageInquiry.gridReload();
		});
		
		/**
		 * 导出按钮事件
		 */
		$("#exportStorageInquiry").click(function() {
			var total = $("#storageInquiryGrid").jqGrid('getGridParam','records');
			$("#exportStorageInquiry").attr("disabled",true);
			var formStr = $("#subForm").serialize();
			var url = $.taHash.get("ctx")+ "/storageInquiryJson/exportStorageInquiry.do?ajax=true&"+formStr+"&total="+total;
			common.ajaxSubmitDfCallBack(storageInquiry.exportHref, url, null);
		});
		
		$("#docStatusList").dropDownList( {
			data : storageInquiry.storageStatusData
		});
		
		
		/**
		 * 使用人自动完成
		 */
		$("#takerCode").taAutoComplete(
				{
					source : "/visa/baseinfoJson/getJsonVcTaker.do?ajax=true",
					minLength : 0,
					height : 210,
					width : 220
				});
				
		
		//单证种类
		$( "#storageInquiryVoDocTypeList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=auto",
			minLength: 0,
			height: 200,
			width:220
		});

		//单证类型
		$( "#storageInquiryVoDocVersionInfoList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto&isCurOrUpperOrgDoc=yes",
			minLength: 0,
			height: 200,
			width:220
		});
		
		//单证种类作为单证类型的查询条件
		$("#storageInquiryVoDocTypeList").change( function(){
			var docType = $("#storageInquiryVoDocTypeList").val();
			//单证类型
			$("#storageInquiryVoDocVersionInfoList").taAutoComplete({
				source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto&isCurOrUpperOrgDoc=yes&docTypeCode="+docType,
				minLength: 0,
				height: 200,
				width:220
			});
		});
		
		//单证险类
		$("#storageInquiryVoInsuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true"
		}); 
		
		$("#storageInquiryVoInsuTypeList").change( function() {		 
			var select = $("#storageInquiryVoInsuTypeList").val();	
			setTimeout(function(){
				$("#storageInquiryVoInsuKindList").dropDownList({
					url : $.taHash.get("ctx")+ "/baseinfoJson/initInsuKindListWithCondition.do?ajax=true&insuTypeCode="+select					
				});
			},100);
		});
	      
		//单证险种
		$("#storageInquiryVoInsuKindList").dropDownList({
			trigger: '#storageInquiryVoInsuTypeList',    
			event: 'change',
			dataField:'name',
			dataType:'json',
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true"
		});
		
	    //机构选择
		$("#storageInquiryVoOrgCode").dropDownDivList({		
			//source:$.taHash.get("ctx")+"/pages/inquiry/jsp/companyTree3.jsp?ajax=true"
			//source:"/visa/pages/inquiry/jsp/companyTree3.jsp?ajax=true"
			//modify by zhxiao3 20141113:VC-135单证库存查询优化 begin
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=storageInquiryVoOrgCode&tagNameId=storageInquiryVoOrgCodeName&pageTableName=storageInquiryPage"
			//modify by zhxiao3 20141113:VC-135单证库存查询优化 end
		});
		
		//包含下级
		$("#storageInquiryVoIsContainSubOrg").change( function() {		
			  if($(this).attr("checked")=="checked"){
				  $(this).val("1"); 
			  }else{
				  $(this).val("0");   
			  }		 
		    });
		
		
		$("#storageInquiryGrid").jqGrid( {
			url : storageInquiry.loadGridUrl,
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
			pager : '#storageInquiryGridPager',
			viewrecords : true,
			jsonReader : {
				root : "storageInquiryVoList",
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
				var selectValue = common.selectRowValue("storageInquiryGrid");
				$("#id").val(selectValue);
			}

		});
		
		// 格式化状态标志
		function myFlagFormatter(cellvalue, options, rowObject) {
			if (cellvalue == 'S1') {
				return '印刷入库';
			} else if (cellvalue == 'S2') {
				return '申领/调拨入库';
			} else if (cellvalue == 'S3') {
				return '回收入库';
			} else if (cellvalue == 'A') {
				return '可使用';
			} 
		}
		;

	},

	
	gridReload : function() {
		var tmpStr = $("#subForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+storageInquiry.loadGridUrl+"&"+tmpStr;
		jQuery("#storageInquiryGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData : {},
			page : 1
		});
		jQuery("#storageInquiryGrid").trigger("reloadGrid");
	},
	
	exportHref : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#exportStorageInquiry").removeAttr("disabled");
			return false;
		}
		var filePath=data.substring(0,data.indexOf("=="));
		var fileName=data.substring(data.indexOf("==")+2, data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#exportStorageInquiry").removeAttr("disabled");
	}
		
}
function changeTakerDocCode(value){
	switch(value){
		case "0" :	
			$("#typeOrgCode").show();
			$("#typeTakerCode").hide();
		    $("#takerCode").val("");
		    $("#takerCodeName").val("");
			break;
		case "1" :  
			$("#typeOrgCode").hide();
			$("#typeTakerCode").show(); 
			$("#storageInquiryVoOrgCode").val("");
			$("#storageInquiryVoOrgCodeName").val("");
			$("#storageInquiryVoIsContainSubOrg").val("");
			break;
	}
}