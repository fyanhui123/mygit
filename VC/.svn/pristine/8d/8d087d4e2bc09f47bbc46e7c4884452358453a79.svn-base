var lostVerificationInquiry = {
	loadGridUrl : "/inquiryJson/queryLostVerification.do?ajax=true",
	initPage : function() {
	
		/**
		 * 导出按钮事件
		 */
		$("#exportLostInquiry").click(function() {
			$("#exportLostInquiry").attr("disabled",true);
			var formStr = $("#subForm").serialize();
			var url = $.taHash.get("ctx")+ "/inquiryJson/exportLostVerification.do?ajax=true&"+formStr;
			common.ajaxSubmitDfCallBack(lostVerificationInquiry.exportHref, url, null);
		});
		
		$("#companyPage").dialog( {
			modal : true,
			height : 240,
			width : 550,
			autoOpen : false
		});
		/*//双击显示机构树
		$("#companyCode").dblclick(function (){
			$('#companyPage').dialog('open');
		});*/
		
		//双击显示机构树
		$("#companyCode").dropDownDivList({
		   source: "/visa/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=companyCode&tagNameId=companyCodeName"
		});
		

		// 单证种类(大类)
		$("#docVersionInfo").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=DocumentType&actionType=query"
		});
	
		// 单证种类(大类)
		$("#docVersionInfo").change(function() {
			var select = $("#docVersionInfo").val();
			//单证种类
			$( "#lostVerificationInquiryDocTypeList" ).taAutoComplete({
				source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=auto&docType="+select,
				minLength: 0,
				height: 200,
				width:220
			});
		});

		$( "#lostVerificationInquiryDocTypeList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=auto",
			minLength: 0,
			height: 200,
			width:220
		});
		
		/*
		 * $( "#lostVerificationInquiryDocTypeList" ).taAutoComplete({ source:
		 * $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=auto",
		 * minLength: 0, height: 200, width:220 });
		 */
		/*$("#lostVerificationInquiryDocTypeList").dropDownList({
			dataField : 'value',
			dataType : 'json',
			url : $.taHash.get("ctx")
					+ "/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=select"
		});*/


		// 单证类型代码
		$("#lostVerificationInquiryDocVerCode").dropDownDivList({		
			source:"/visa/baseinfo/initdocVerForSelector.do?ajax=true&code=lostVerificationInquiryDocVerCode&name=lostVerificationInquiryDocVerName&docTypeCode=lostVerificationInquiryDocTypeList"
		});
		
		// 单证险类
		$("#lostVerificationInquiryInsuTypeList").dropDownList({
			dataField : 'value',
			dataType : 'json',
			url : $.taHash.get("ctx")
					+ "/baseinfoJson/getJSONInsuTypeList.do?ajax=true"
		});

		 $("#lostVerificationInquiryInsuTypeList").change( function() {
			 var select = $("#lostVerificationInquiryInsuTypeList").val();
			 $("#lostVerificationInquiryInsuKindList").dropDownList({
				url : $.taHash.get("ctx")
				+ "/baseinfoJson/initInsuKindListWithCondition.do?ajax=true&insuTypeCode="+select
			 });
		 });
		

		// 单证险种
		$("#lostVerificationInquiryInsuKindList").dropDownList(
				{
					trigger : '#inStoreInquiryDtoInsuTypeList',
					event : 'change',
					dataField : 'name',
					dataType : 'json',
					url : $.taHash.get("ctx")
							+ "/baseinfoJson/getJSONInsuKindList.do?ajax=true"
				// url:
				// $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true&insuTypeCode="+$("#inStoreInquiryDtoInsuTypeList").val()
				});
		
		$("#lostStatus").dropDownList( {
			url : "/visa/baseinfoJson/initDownList.do?codeType=ApplyStatus&actionType=query"
		});
		

		/**
		 * @description 查询按钮事件
		 * @param queryInStoreInquiry
		 *            查询按钮ID
		 * @return
		 */
		$("#querylostInquiry").click(function() {
			lostVerificationInquiry.gridReload();
		});

		$("#resetBtn").click(function() {
			common.clearGridData('lostVerificationInquiryGrid');
		});
		
		$("#lostVerificationInquiryGrid").jqGrid(
				{
					url : $.taHash.get("ctx")
							+ lostVerificationInquiry.loadGridUrl,
					mtype : "POST",
					datatype : "local",
					autowidth : true,
					height : 'auto',
					colNames : [ '遗失单号', '单证类型名称', '单证类型代码', '印刷批次', '起始流水号',
							'终止流水号', '遗失数量', '机构名称', '遗失时间' ],
					colModel : [ {
						name : 'lostCode',
						index : 'lostCode',
						width: 220,
						resizable : true,
						align : 'center'
					// hidden : true
							}, {
								name : 'docVerCode',
								index : 'docVerCode',
								resizable : true,
								align : 'left'
							}, {
								name : 'docVerName',
								index : 'docVerName',
								resizable : true,
								align : 'left'
							}, {
								name : 'pressBatchNo',
								index : 'pressBatchNo',
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
							}, {
								name : 'lostNum',
								index : 'lostNum',
								autowidth : true,
								resizable : true,
								align : 'center'
							}, {
								name : 'lostOrgCode',
								index : 'lostOrgCode',
								autowidth : true,
								resizable : true,
								align : 'left'
							}, {
								name : 'lostTime',
								index : 'lostTime',
								width : 200,
								resizable : true,
								formatter : "date",
								formatoptions : {
									srcformat : 'Y-m-d H:i:s',
									newformat : 'Y-m-d H:i'
								},
								align : 'left'
							} ],
					shrinkToFit : true,
					rowNum : 10,
					rowList : [ 10, 30, 50 ],
					pager : '#lostVerificationInquiryGridPager',
					viewrecords : true,
					jsonReader : {
						root : "lostVos",
						repeatitems : false,
						page : 'page',
						total : 'total',
						records : 'records',
						id : "lostCode"
					},
					multiselect : false,

					/**
					 * @description 获取选中的值
					 * @param
					 * @return
					 */
					onSelectRow : function(id) {
						var selectValue = common
								.selectRowValue("inStoreInquiryGrid");
						$("#id").val(selectValue);
					}

				});

		// 格式化状态标志
		function myFlagFormatter(cellvalue, options, rowObject) {
			if (cellvalue == '1') {
				return '新建';
			} else if (cellvalue == '4') {
				return '审批通过';
			} else if (cellvalue == '3') {
				return '审批退回';
			} else if (cellvalue == '2') {
				return '等待审批';
			} else if (cellvalue == '0') {
				return '删除';
			}
		}
		;

	},

	gridReload : function() {
		var tmpStr = $("#subForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")
				+ lostVerificationInquiry.loadGridUrl + "&" + tmpStr;
		jQuery("#lostVerificationInquiryGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData : {},
			page : 1
		});

		jQuery("#lostVerificationInquiryGrid").trigger("reloadGrid");
	},
	
	exportHref : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#exportLostInquiry").removeAttr("disabled");
			return false;
		}
		var filePath=data.substring(0,data.indexOf("=="));
		var fileName=data.substring(data.indexOf("==")+2, data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#exportLostInquiry").removeAttr("disabled");
	}
}
