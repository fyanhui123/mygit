var docInStoreInquiry = {
	loadGridUrl : "/inquiryJson/queryDocInStoreInquiryListByPages.do?ajax=true",
    	
	
		initPage : function() {
		/**
		 * 导出按钮事件
		 */
		$("#exportInStoreInquiry").click(function() {
			$("#exportInStoreInquiry").attr("disabled",true);
			var formStr = $("#subForm").serialize();
			var url = $.taHash.get("ctx")+ "/inquiryJson/exportDocInStoreInquiry.do?ajax=true&"+formStr;
			common.ajaxSubmitDfCallBack(docInStoreInquiry.exportHref, url, null);
		});
		
	    //机构选择
		$("#inStoreInquiryDtoApplyOrgCode").dropDownDivList({		
			//source:$.taHash.get("ctx")+"/pages/inquiry/jsp/companyTree3.jsp?ajax=true"
			//source:"/visa/pages/inquiry/jsp/companyTree3.jsp?ajax=true"
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&minLevel=2&tagCodeId=inStoreInquiryDtoApplyOrgCode&tagNameId=inStoreInquiryDtoApplyOrgCodeName"
		});
		
		/**
		 * 申请人自动完成组建
		 */
		$("#inStoreInquiryDtoApplyOprCode").taAutoComplete({
			source : "/visa/baseinfoJson/getJsonVcTaker.do?ajax=true",
			minLength : 0,
			height : 210,
			width : 220
		});
		
		//申请人
		/*$("#inStoreInquiryDtoApplyOprCode").dropDownDivList({		
			source:"/visa/vclevel/initUsersForSelector.do?ajax=true&code=inStoreInquiryDtoApplyOprCode"
		});*/
			
		//单证种类
		$( "#inStoreInquiryDtoDocTypeList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=auto",
			minLength: 0,
			height: 200,
			width:220
		});
		/*$("#inStoreInquiryDtoDocTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=select"
		}); */
		
		
		//单证类型
		/*$( "#inStoreInquiryDtoDocVersionInfoList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto",
			minLength: 1,
			height: 200,
			width:220
		});*/
		
		//单证类型
		$("#inStoreInquiryDtoDocVersionInfoList").dropDownDivList({		
			source:"/visa/baseinfo/initdocVerForSelector.do?ajax=true&code=inStoreInquiryDtoDocVersionInfoList&name=inStoreInquiryDtoDocVersionInfoListName&docTypeCode=inStoreInquiryDtoDocTypeList"
		});

		/*$("#inStoreInquiryDtoDocVersionInfoList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true"
		}); 
		*/		
		//单证险类
		$("#inStoreInquiryDtoInsuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true"
		}); 
		
     /* $("#inStoreInquiryDtoInsuTypeList").change( function() {		 			 var select = $("#inStoreInquiryDtoInsuTypeList").val();	
			 setTimeout(function(){
				 $("#inStoreInquiryDtoInsuKindList").dropDownList({
					 url : $.taHash.get("ctx")+ "/baseinfoJson/initInsuKindListWithCondition.do?ajax=true&insuTypeCode="+select					
				 });
			 },100);
		});*/
		
		//单证险	种
		$("#inStoreInquiryDtoInsuKindList").dropDownList({
			trigger: '#inStoreInquiryDtoInsuTypeList',    
			event: 'change',
			dataField:'name',
			dataType:'json',
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true"
		    //url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true&insuTypeCode="+$("#inStoreInquiryDtoInsuTypeList").val()
		});
		 
		$("#inStoreStatus").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=InStoreStatus&actionType=query"
		}); 
		
		$("#inStoreInquiryDtoIsContainSubOrg").change( function() {		
		  if($(this).attr("checked")=="checked"){
			  $(this).val("1"); 
		  }else{
			  $(this).val("0");   
		  }		 
	    });
		
		
		
		
		/**
		 * @description 查询按钮事件
		 * @param queryInStoreInquiry
		 *            查询按钮ID
		 * @return
		 */
		$("#queryInStoreInquiry").click(function() {			
			docInStoreInquiry.gridReload();
		});

		$("#inStoreInquiryGrid").jqGrid( {
			url : $.taHash.get("ctx")+docInStoreInquiry.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',	
			colNames : [ '单证类型代码','单证类型名称','单证种类', '申请单号', '起始流水','终止流水','印刷批次','数量','费用','申请机构', '申请人', '申请时间', '申请单状态','入库时间' ],
			colModel : [ {
				name : 'docVerCode',
				index : 'docVerCode',
				width : 200,
				resizable : true,
				align : 'left'
				//hidden : true
			}, {
				name : 'docVerName',
				index : 'docVerName',
				width : 200,
				resizable : true,
				align : 'left'
			}, {
				name : 'docTypeName',
				index : 'docTypeName',
				width : 200,
				resizable : true,
				align : 'left'
			}, {
				name : 'inStoreAppCode',
				index : 'inStoreAppCode',
				width : 200,
				resizable : true,
				align : 'left'
			}, {
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
			},{
				name : 'pressBatchNo',
				index : 'pressBatchNo',
				autowidth : true,				
				resizable : true,
				align : 'left'
			},{
				name : 'totalAmount',
				index : 'totalAmount',
				autowidth : true,				
				resizable : true,
				align : 'center'
			},{
				name : 'printingFee',
				index : 'printingFee',
				autowidth : true,				
				resizable : true,
				align : 'right'
			},{
				name : 'applyOrgName',
				index : 'applyOrgName',
				width : 200,
				resizable : true,				
				align : 'center'
			},{
				name : 'applyOprName',
				index : 'applyOprName',
				autowidth : true,				
				resizable : true,
				align : 'left'
			}, {
				name : 'applyTime',
				index : 'applyTime',
				width : 200,
				resizable : true,	
				formatter:"date",
				formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i'},		
				align : 'left'
			},{
				name : 'inStoreStatus',
				index : 'inStoreStatus',
				width : 200,
				resizable : true,
				formatter : myFlagFormatter,
				align : 'left'
			}, {
				name : 'inStoreTime',
				index : 'inStoreTime',
				width : 200,
				resizable : true,	
				formatter:"date",
				formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i'},		
				align : 'left'
			} ],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#inStoreInquiryGridPager',
			viewrecords : true,
			jsonReader : {
				root : "inStoreInquiryDtoList",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "inStoreAppCode"
			},
			multiselect : false,

			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				var selectValue = common.selectRowValue("inStoreInquiryGrid");
				$("#id").val(selectValue);
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
		};

	},

	gridReload : function() {
		var tmpStr = $("#subForm").serialize();	
		var gridReloadUrl = $.taHash.get("ctx")+docInStoreInquiry.loadGridUrl+"&"+tmpStr;
		jQuery("#inStoreInquiryGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{},
			page : 1
		});

		jQuery("#inStoreInquiryGrid").trigger("reloadGrid");
	},
	
	exportHref : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#exportInStoreInquiry").removeAttr("disabled");
			return false;
		}
		var filePath=data.substring(0,data.indexOf("=="));
		var fileName=data.substring(data.indexOf("==")+2, data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#exportInStoreInquiry").removeAttr("disabled");
	}
}
