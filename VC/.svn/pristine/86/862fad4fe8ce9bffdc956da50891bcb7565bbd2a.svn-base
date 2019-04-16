var docDetailInquiry = {
	loadGridUrl : "/inquiryJson/queryDocDetailInquiryListByPages.do?ajax=true",
	
	initPage : function() {	
	
	  //必填验证   add by whj 20131126  
	   $("#subForm").validate();
	
		/**
		 * 导出按钮事件
		 */
		$("#exportDocDetailInquiry").click(function() {
			//必填校验   add by whj 20131126			
			if(!$("#subForm").validate().form()){
				return false;
			}
			//导出时时间不得大于一个月
			var t1=$("#operateStartDate").val();
			var t2=$("#operateEndDate").val();			
			var d1=new Date(t1.replace(/-/g,"/"));
			var d2=new Date(t2.replace(/-/g,"/"));
			var day= (d2.getTime() - d1.getTime())/(24 * 60 * 60 * 1000)+1; 
			if(day>31){
				alert("统计时间段不能超过31天");
				return false;
			}
			

			$("#exportDocDetailInquiry").attr("disabled",true);
			var formStr = $("#subForm").serialize();
			var url = $.taHash.get("ctx")+ "/inquiryJson/exportDocDetailInquiry.do?ajax=true&"+formStr;
			common.ajaxSubmitDfCallBack(docDetailInquiry.exportHref, url, null);
		});
		
	   //机构选择
		$("#docDetailInquiryDtoOrgCode").dropDownDivList({		
			//source:$.taHash.get("ctx")+"/pages/inquiry/jsp/companyTree3.jsp?ajax=true"
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=docDetailInquiryDtoOrgCode&tagNameId=docDetailInquiryDtoOrgCodeName"
		});
		
		/**
		 * 操作人自动完成组建
		 */
		$("#docDetailInquiryDtoOprCode").taAutoComplete({
			source : "/visa/baseinfoJson/getJsonVcTaker.do?ajax=true",
			minLength : 0,
			height : 210,
			width : 220
		});
		
		
		/*//单证种类
		$("#docDetailInquiryDtoDocTypeList").dropDownList({
			dataField:'value',
			dataType:'json',		  
			url:  $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&trigger=no"
		}); */
		//单证种类
		$( "#docDetailInquiryDtoDocTypeList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=auto",
			minLength: 0,
			height: 200,
			width:220
		});
		
				
		/*//单证类型
		$("#docDetailInquiryDtoDocVersionInfoList").dropDownList({
			trigger: '#docDetailInquiryDtoDocTypeList',    
			event: 'change',
			dataField:'name',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code"
		});*/
		//单证类型
		$( "#docDetailInquiryDtoDocVersionInfoList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto",
			minLength: 0,
			height: 200,
			width:220
		});
		
		//单证种类作为单证类型的查询条件
		$("#docDetailInquiryDtoDocTypeList").change( function(){
			var docType = $("#docDetailInquiryDtoDocTypeList").val();
			//单证类型
			$("#docDetailInquiryDtoDocVersionInfoList").taAutoComplete({
				source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto&docTypeCode="+docType,
				minLength: 0,
				height: 200,
				width:220
			});
		});
		
		
		//单证险类
		$("#docDetailInquiryDtoInsuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		   // url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true&valueType=serialId"
			 url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true"
		}); 
		
		/*$("#docDetailInquiryDtoInsuTypeList").change( function() {
			 var select = $("#docDetailInquiryDtoInsuTypeList").val();			
			 setTimeout(function(){
				 $("#docDetailInquiryDtoInsuKindList").dropDownList({
					//url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true&insuTypeId="+select
					url : $.taHash.get("ctx")+ "/baseinfoJson/initInsuKindListWithCondition.do?ajax=true&insuTypeCode="+select
				 });
			 },20);
		});*/
		
		//单证险种
		$("#docDetailInquiryDtoInsuKindList").dropDownList({
			trigger: '#docDetailInquiryDtoInsuTypeList',    
			event: 'change',
			dataField:'name',
			dataType:'json',
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true"
		    //url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true&insuTypeCode="+$("#docDetailInquiryDtoInsuTypeList").val()
		});

		$("#docDetailInquiryDtoIsContainSubOrg").change( function() {		
		  if($(this).attr("checked")=="checked"){
			  $(this).val("1"); 
		  }else{
			  $(this).val("0");   
		  }		 
	    });
		
		/**
		 * @description 查询按钮事件
		 * @param queryDocDetailInquiry
		 *            查询按钮ID
		 * @return
		 */
		$("#queryDocDetailInquiry").click(function() {
			docDetailInquiry.gridReload();
		});

		$("#docDetailInquiryGrid").jqGrid( {
			url : $.taHash.get("ctx")+docDetailInquiry.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',	
			colNames : ['ID',  '单证类型代码','单证类型名称','单证种类', '起始流水','终止流水','所属机构', '操作人','所属人', '操作时间','业务号', '单证状态' ],
			colModel : [ {
				name : 'id',
				 index : 'id',
				 hidedlg : true,
				 hidden : true
			},{
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
				name : 'orgName',
				index : 'orgName',
				width : 200,
				resizable : true,				
				align : 'left'
			},{
				name : 'oprName',
				index : 'oprName',
				autowidth : true,				
				resizable : true,
				align : 'left'
			},{
				name : 'ownerName',
				index : 'ownerName',
				autowidth : true,				
				resizable : true,
				align : 'left'
			}, {
				name : 'operateTime',
				index : 'operateTime',
				width : 200,
				resizable : true,	
				formatter:"date",
				formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d'},	
				align : 'left'
			},{
				name : 'businessNo',
				index : 'businessNo',
				width : 200,
				resizable : true,				
				align : 'center'
			},{
				name : 'status',
				index : 'status',
				width : 200,
				resizable : true,
				align : 'left'
			}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#docDetailInquiryGridPager',
			viewrecords : true,
			jsonReader : {
				root : "docDetailInquiryDtoList",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "docInStoreDetId"
			},
			multiselect : false,

			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				var selectValue = common.selectRowValue("docDetailInquiryGrid");
				$("#id").val(selectValue);
			}

		});
		
	},

	gridReload : function() {
		//必填校验   add by whj 20131126			
		if(!$("#subForm").validate().form()){
			return false;
		}
		
		var tmpStr = $("#subForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+docDetailInquiry.loadGridUrl+"&"+tmpStr;
		jQuery("#docDetailInquiryGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{},
			page : 1
		});

		jQuery("#docDetailInquiryGrid").trigger("reloadGrid");
	},
	
	exportHref : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#exportDocDetailInquiry").removeAttr("disabled");
			return false;
		}
		var filePath=data.substring(0,data.indexOf("=="));
		var fileName=data.substring(data.indexOf("==")+2, data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#exportDocDetailInquiry").removeAttr("disabled");
	}
}
