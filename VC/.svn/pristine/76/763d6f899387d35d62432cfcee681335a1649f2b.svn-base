var servantDoc = {
	loadGridUrl : "/servantDocManageJson/queryServantDoc.do?ajax=true",
	initPage : function() {

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
		 * 使用人自动完成
		 */
		$("#takerCode").taAutoComplete(
				{
					source : "/visa/baseinfoJson/getJsonVcTaker.do?ajax=true",
					minLength : 0,
					height : 210,
					width : 220
				});
		
		//双击显示机构树
		$("#acceptOrgCode").dropDownDivList({
		   source: "/visa/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=acceptOrgCode&tagNameId=acceptOrgName"
		});

		
		/**
		 * @description 查询按钮事件
		 * @param queryInStore
		 *            查询按钮ID
		 * @return
		 */
		$("#queryDocIssued").click(function() {
			servantDoc.gridReload();
		});
		
		/**
		 * @description 新增按钮事件
		 * @param queryResource
		 *            查询按钮ID
		 * @return
		 */
		$("#addDocIssued").click(function() {
			// ajax获取要修改的数据
				// common.ajaxGetData("docIssuedAddPage", $.taHash.get("ctx")
				// + "/store/initAddDocInStore.do?ajax=true", null);
				// 调用dialog组件
				var acrionType = $("#actionType").val();
				if(acrionType == "P"){
					common.ajaxGetData("docIssuedAddPage", $.taHash.get("ctx")
							+ "/servantDocManage/initServantDocIssuedAdd.do?ajax=true", null);
					$('#docIssuedAddPage').dialog('open');
				}else if(acrionType == "R"){
					common.ajaxGetData("docRecoveryAddPage", $.taHash.get("ctx")
							+ "/servantDocManage/initServantDocRecoveryAdd.do?ajax=true", null);
					$('#docRecoveryAddPage').dialog('open');
				}
				
			});

		$("#serDocIssuedGatherGrid").jqGrid(
				{
					url : $.taHash.get("ctx") + servantDoc.loadGridUrl,
					mtype : "POST",
					datatype : "local",
					autowidth : true,
					height : 'auto',
					colNames : [ 'id', '使用人', '使用人所属机构', '单证类型代码', '单证类型名称',
							'起始流水号', '终止流水号', '数量' ],
					colModel : [ {
						name : 'id',
						index : 'vcDocTakerIo.id',
						resizable : true,
						align : 'center',
						hidden : true
					}, {
						name : 'takerName',
						index : 'vcDocTakerIo.takerName',
						resizable : true,
						align : 'left'
					}, {
						name : 'acceptOrgName',
						index : 'vcDocTakerIo.acceptOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'docVerCode',
						index : 'vcDocTakerIo.docVerCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'docVerName',
						index : 'vcDocTakerIo.docVerName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'startNum',
						index : 'vcDocTakerIo.startNum',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'endNum',
						index : 'vcDocTakerIo.endNum',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'docNum',
						index : 'vcDocTakerIo.docNum',
						autowidth : true,
						resizable : true,
						align : 'center'
					} ],
					shrinkToFit : true,
					rowNum : 10,
					rowList : [ 10, 30, 50 ],
					pager : '#serDocIssuedGatherGridPager',
					viewrecords : true,
					jsonReader : {
						root : "vcDocTakerIos",
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
				docVerName :$("#docVerCodeName").val(),
				takerCode : $("#takerCode").val(),
				takerName : $("#takerCodeName").val(),
				acceptOrgCode : $("#acceptOrgCode").val(),
				acceptOrgName : $("#acceptOrgName").val(),
				provideStartDate : $("#provideStartDate").val(),
				provideEndDate : $("#provideEndDate").val(),
				actionType: $("#actionType").val()
			},
			url : gridReloadUrl,
			page : 1
		});
		jQuery("#serDocIssuedGatherGrid").trigger("reloadGrid");
	},

	callBack : function(data) {
		alert(data);
		servantDoc.gridReload();
	}
}
