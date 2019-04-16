var applyInquiry = {
	loadGridUrl : "/applyInquiryJson/queryApplyInquiryListByPages.do?ajax=true",
	
	//申领单处理状态（0删除/1新建/2等待发放/3发放退回/4等待确认/6已确认/5确认退回）
	/**
	 * 下拉框的数据
	 */
	applyStatusData: [ 
	    {"label" : "已删除","value" : "0"},
	    {"label" : "新建","value" : "1"},
	    {"label" : "等待发放","value" : "2"},
	    {"label" : "发放退回","value" : "3"},
	    {"label" : "等待确认","value" : "4"},
	    {"label" : "确认退回","value" : "5"},
	    {"label" : "已确认","value" : "6"}
	],
		
	initPage : function() {	
		/**
		 * 导出按钮事件
		 */
		$("#exportApplyInquiry").click(function() {
			//mddify by zhxiao3 VC-132:申请时间增加比例校验 begin
			if(!$("#subForm").validate().form()){
				return false;
			}
			var t1=$("#applyStartDate").val();
			var t2=$("#applyEndDate").val();			
			var d1=new Date(t1.replace(/-/g,"/"));
			var d2=new Date(t2.replace(/-/g,"/"));
			var day= (d2.getTime() - d1.getTime())/(24 * 60 * 60 * 1000)+1; 
			if(day>93){
				$("#applyStartDate").focus();
				alert("统计时间段不能超过3个月（92天）！");
				return false;
			}
			//mddify by zhxiao3 VC-132:申请时间增加比例校验 end
			$("#exportApplyInquiry").attr("disabled",true);
			var formStr = $("#subForm").serialize();
			var url = $.taHash.get("ctx")+ "/applyInquiryJson/exportApplyInquiryList.do?ajax=true&"+formStr;
			common.ajaxSubmitDfCallBack(applyInquiry.exportHref, url, null);
		});
		
		//单证种类
		$( "#applyInquiryVoDocTypeList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=auto",
			minLength: 0,
			height: 200,
			width:220
		});
		//单证类型
		$( "#applyInquiryVoDocVersionInfoList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto",
			minLength: 0,
			height: 200,
			width:220
		});
		//modify by zhxiao3 ITEA-9215 单证类型不会根据单证种类筛选 begin
		//单证种类作为单证类型的查询条件
		$("#applyInquiryVoDocTypeList").change( function(){
			var docType = $("#applyInquiryVoDocTypeList").val();
			//单证类型
			$("#applyInquiryVoDocVersionInfoList").taAutoComplete({
				source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto&isCurOrUpperOrgDoc=yes&docTypeCode="+docType,
				minLength: 0,
				height: 200,
				width:220
			});
		});
		//modify by zhxiao3 ITEA-9215 单证类型不会根据单证种类筛选 end
		
		//单证险类
		$("#applyInquiryVoInsuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true"
		}); 
		$("#applyInquiryVoInsuTypeList").change( function() {		 
			var select = $("#applyInquiryVoInsuTypeList").val();	
			setTimeout(function(){
				$("#applyInquiryVoInsuKindList").dropDownList({
					url : $.taHash.get("ctx")+ "/baseinfoJson/initInsuKindListWithCondition.do?ajax=true&insuTypeCode="+select					
				});
			},100);
		});
		//单证险种
		$("#applyInquiryVoInsuKindList").dropDownList({
			trigger: '#applyInquiryVoInsuTypeList',    
			event: 'change',
			dataField:'name',
			dataType:'json',
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true"
		});

	    //机构选择
		$("#applyInquiryVoApplyOrgCode").dropDownDivList({		
			//source:$.taHash.get("ctx")+"/pages/inquiry/jsp/companyTree3.jsp?ajax=true"
			//source:"/visa/pages/inquiry/jsp/companyTree3.jsp?ajax=true"
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=applyInquiryVoApplyOrgCode&tagNameId=applyInquiryVoApplyOrgCodeName"
		});
		
		//包含下级
		$("#applyInquiryVoIsContainSubOrg").change( function() {		
			  if($(this).attr("checked")=="checked"){
				  $(this).val("1"); 
			  }else{
				  $(this).val("0");   
			  }		 
		    });
		
		/**
		 * @description 查询按钮事件
		 * @param queryApplyInquiry
		 *            查询按钮ID
		 * @return
		 */
		$("#queryApplyInquiry").click(function() {
			//mddify by zhxiao3 VC-132:申请时间增加比例校验 begin
			if(!$("#subForm").validate().form()){
				return false;
			}
			var t1=$("#applyStartDate").val();
			var t2=$("#applyEndDate").val();			
			var d1=new Date(t1.replace(/-/g,"/"));
			var d2=new Date(t2.replace(/-/g,"/"));
			var day= (d2.getTime() - d1.getTime())/(24 * 60 * 60 * 1000)+1; 
			if(day>93){
				$("#applyStartDate").focus();
				alert("统计时间段不能超过3个月（92天）！");
				return false;
			}
			//mddify by zhxiao3 VC-132:申请时间增加比例校验 end
			applyInquiry.gridReload();
		});
		
		$("#applyStatus").dropDownList( {
			data : applyInquiry.applyStatusData
		});
		
		$("#applyInquiryGrid").jqGrid( {
			url : applyInquiry.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',	
			colNames : ['ID', '申领单号', '单证类型代码', '单证类型名称', '申请数量', '申请人', '申领机构', '申请时间','处理状态'],
			colModel : [{name : 'id',
				 		index : 'id',
				 		hidedlg : true,
				 		hidden : true
					},{
						name : 'applyCode',
						index : 'applyCode',
						width : 200,
						resizable : true,
						align : 'left'
					},{
						name : 'docVerCode',
						index : 'docVerCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'docVerName',
						index : 'docVerName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyNum',
						index : 'applyNum',
						autowidth : true,
						resizable : true,
						align : 'center'
					},{
						name : 'applyOprCode',
						index : 'applyOprCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyOrgCode',
						index : 'applyOrgCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyTime',
						index : 'applyTime',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i'}
					},{
						name : 'applyStatus',
						index : 'applyStatus',
						autowidth : true,
						resizable : true,
						formatter : myFlagFormatter,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#applyInquiryGridPager',
			viewrecords : true,
			jsonReader : {
				root : "applyInquiryVoList",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "applyCode"
			},
			multiselect : false,

			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				var selectValue = common.selectRowValue("applyInquiryGrid");
				$("#id").val(selectValue);
			}

		});

		// 格式化状态标志
		function myFlagFormatter(cellvalue, options, rowObject) {
			if (cellvalue == '1') {
				return '新建';
			} else if (cellvalue == '2') {
				return '等待发放';
			} else if (cellvalue == '3') {
				return '发放退回';
			} else if (cellvalue == '4') {
				return '等待确认';
			} else if (cellvalue == '5') {
				return '确认退回';
			} else if (cellvalue == '6') {
				return '已确认';
			} else if (cellvalue == '0') {
				return '已删除';
			}
		}
		;

	},

	
	gridReload : function() {
		var tmpStr = $("#subForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+applyInquiry.loadGridUrl+"&"+tmpStr;
		jQuery("#applyInquiryGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData : {},
			page : 1
		});
		jQuery("#applyInquiryGrid").trigger("reloadGrid");
	},
	
	exportHref : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#exportApplyInquiry").removeAttr("disabled");
			return false;
		}
		var filePath=data.substring(0,data.indexOf("=="));
		var fileName=data.substring(data.indexOf("==")+2, data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#exportApplyInquiry").removeAttr("disabled");
	}
}
