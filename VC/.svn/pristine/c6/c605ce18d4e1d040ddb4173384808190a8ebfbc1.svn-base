var servantDoc = {
	loadGridUrl : "/findVcDocTakerioJson/findVcDocTakerio.do?ajax=true",
	initPage : function() {
		$("#docTakerioExplore").click(function() {
			var	docVerCode = $("#docVerCode").val();
			var	docNum =$("#docNum").val();
			$("#docTakerioExplore").attr("disabled",true);
			var formStr = $("#subForm").serialize();
			var url = $.taHash.get("ctx")+ "/findVcDocTakerioJson/exportDocTakerio.do?ajax=true&"+formStr+"&docVerCode="+docVerCode+"&docNum="+docNum;
			common.ajaxSubmitDfCallBack(servantDoc.exportHref, url, null);
		});
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
		$("#docIssuedAddPage").dialog( {
			modal : true,
			height : 550,
			width : 1000,
			autoOpen : false
		});
		
		$("#docRecoveryAddPage").dialog( {
			modal : true,
			height : 550,
			width : 950,
			autoOpen : false
		});

		$("#validStorageDiv").dialog({
			modal: true,
			height:430,
			width:1000,
			autoOpen: false
		});
		
		$("#validAvailableDiv").dialog({
			modal: true,
			height:430,
			width:1000,
			autoOpen: false
		});
		
		/**
		 * 单证类型自动完成
		 */
		$("#docVerCode").taAutoComplete(
				{
					source : $.taHash.get("ctx")+ "/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no",
					minLength : 0,
					height : 200,
					width : 220
				});
		

		
		/**
		 * @description 查询按钮事件
		 * @param queryInStore
		 * 查询按钮ID
		 * @return
		 */
		$("#queryDocTakerio").click(function() {
		var	docVerCode = $("#docVerCode").val();
		var	docNum =$("#docNum").val();
		
		if(null==docVerCode ||docVerCode==""){
			
			alert("单证类型不能为空！");
			$("#docVerCode").focus();
			return false;
		}
		if(null==docNum||docNum==""){
			alert("单证流水号不能为空！");
			$("#docNum").focus();
			return false;
		}
			servantDoc.gridReload();
		});
		
		$("#serDocIssuedGatherGrid").jqGrid(
				{
					url : $.taHash.get("ctx") + servantDoc.loadGridUrl,
					mtype : "POST",
					datatype : "local",
					autowidth : true,
					height : 'auto',
					colNames : [ 'id', '单证类型代码', '单证类型名称', '印刷号', '起始流水号',
							'终止流水号', '数量', '发放/回收类型','操作员','操作时间','使用人','使用人所属机构','发放机构' ],
					colModel : [ {
						name : 'id',
						index : 'vcDocTakerIo.id',
						resizable : true,
						align : 'center',
						hidden : true
					}, {
						name : 'docVerCode',
						index : 'vcDocTakerIo.docVerCode',
						resizable : true,
						align : 'left',
						sortable :true
					}, {
						name : 'docVerName',
						index : 'vcDocTakerIo.docVerName',
						autowidth : true,
						resizable : true,
						align : 'left',
						sortable :true
					}, {
						name : 'pressBatchNo',
						index : 'vcDocTakerIo.pressBatchNo',
						autowidth : true,
						resizable : true,
						align : 'left',
						sortable :true
					}, {
						name : 'startNum',
						index : 'vcDocTakerIo.startNum',
						autowidth : true,
						resizable : true,
						align : 'left',
						sortable :true
					}, {
						name : 'endNum',
						index : 'vcDocTakerIo.endNum',
						autowidth : true,
						resizable : true,
						align : 'left',
						sortable :true
					}, {
						name : 'docNum',
						index : 'vcDocTakerIo.docNum',
						autowidth : true,
						resizable : true,
						align : 'left',
						sortable :true
					}, {
						name : 'oprType',
						index : 'vcDocTakerIo.oprType',
						autowidth : true,
						resizable : true,
						align : 'center',
						sortable :true
					},{
						name : 'oprCode',
						index : 'vcDocTakerIo.oprCode',
						autowidth : true,
						resizable : true,
						align : 'center',
						sortable :true
					},
					{
						name : 'oprTime',
						index : 'vcDocTakerIo.oprTime',
						autowidth : true,
						resizable : true,
						align : 'center',
						sortable :true,
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i:s'}
					},{
						name : 'takerCode',
						index : 'vcDocTakerIo.takerCode',
						autowidth : true,
						resizable : true,
						align : 'center',
						sortable :true
					},{
						name : 'acceptOrgCode',
						index : 'vcDocTakerIo.acceptOrgCode',
						autowidth : true,
						resizable : true,
						align : 'center',
						sortable :true
					},{
						name : 'provideOrgCode',
						index : 'vcDocTakerIo.provideOrgCode',
						autowidth : true,
						resizable : true,
						align : 'center',
						sortable :true
					}],
					shrinkToFit : true,
					rowNum : 10,
					rowList : [ 10, 30, 50 ],
					pager : '#serDocIssuedGatherGridPager',
					viewrecords : true,
					jsonReader : {
						root : "listVcDocTakerIo",
						repeatitems : false,
						page : 'page',
						total : 'total',
						records : 'records',
						id : "id"
					},

					/**
					 * @description 获取选中的值
					 * @param
					 * @return
					 */
					onSelectRow : function(id) {
						var selectValue = common
								.selectRowValue("serDocIssuedGatherGrid");
						$("#id").val(selectValue);
					},
					
					onSelectAll: function(id){
						ids = $("#serDocIssuedGatherGrid").jqGrid('getDataIDs');	
						$("#id").val(ids);
					}


				});
		
	},

	gridReload : function() {
		var gridReloadUrl = $.taHash.get("ctx")+ servantDoc.loadGridUrl;
		jQuery("#serDocIssuedGatherGrid").jqGrid('setGridParam', {
			datatype : "json",
			postData:{
				docVerCode : $("#docVerCode").val(),
				docNum :$("#docNum").val()
			},
			url : gridReloadUrl,
			page : 1
		});
		jQuery("#serDocIssuedGatherGrid").trigger("reloadGrid");
	},
	callBack : function(data) {
		alert(data);
		servantDoc.gridReload();
	},
	exportHref : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#docTakerioExplore").removeAttr("disabled");
			return false;
		}
		var num=data.indexOf("==");
		var filePath =data.substr(0,num);
		var fileName =data.substr(data.indexOf("==")+2,data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#docTakerioExplore").removeAttr("disabled");
	}
}
