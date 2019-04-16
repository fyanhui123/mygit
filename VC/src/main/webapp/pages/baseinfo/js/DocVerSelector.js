var docVerSelector = {
	loadGridUrl : "/baseinfoJson/queryDocVerForSelector.do?ajax=true",
	initPage : function() {
		
		/**
		 * @description 查询按钮事件
		 * @param queryInStore
		 *            查询按钮ID
		 * @return
		 */
		$("#queryBut").click(function() {
			docVerSelector.gridReload();
		});


		$("#docVerGrid").jqGrid( {
			url : $.taHash.get("ctx") + docVerSelector.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',
			colNames : [  '单证类型代码', '单证类型名称' ],
			colModel : [ {
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
			} ],
			shrinkToFit : true,
			rowNum : 5,
			pager : '#docVerGridPage',
			viewrecords : true,
			jsonReader : {
				root : "docVers",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "id"
			},
			multiselect : true,
			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id,status) {
				var selectValue = common.selectRowShowCollValue(
						"docVerGrid", "docVerCode", id);
				var selectName = common.selectRowShowCollValue(
						"docVerGrid", "docVerName", id);
				var code =  $("#code").val();
				var name =  $("#name").val();
				
				$("#"+code).val(selectValue);
				$("#"+name).val(selectName);
			}
		});
	},
	
	gridReload : function() {
		var gridReloadUrl = $.taHash.get("ctx") + docVerSelector.loadGridUrl;
		jQuery("#docVerGrid").jqGrid('setGridParam', {
			datatype : "json",
			postData : {
			docVerCode : $("#docVerCode").val(),
			docVerName : $("#docVerName").val(),
			docTypeCode : $("#"+$("#docTypeCode").val()).val()
			},
			url : gridReloadUrl,
			page : 1
		});

		jQuery("#docVerGrid").trigger("reloadGrid");
	}
}
