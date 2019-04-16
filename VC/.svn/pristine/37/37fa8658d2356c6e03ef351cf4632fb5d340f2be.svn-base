/**
 * @deivData 页面 js文件
 * @author 廉超
 * @date 2013-03-15
 * @version 1.0
 */
var divData = {

	loadGridUrl : "/queryContractsByPages.do?ajax=true",

	initPage : function() {

		/**
		 * @description 查询按钮事件
		 * @param queryResource
		 *            查询按钮ID
		 * @return
		 */
		$("#queryBut").click(function() {
			// 执行查询方法
			divData.gridReload();
		});
		/**
		 * @description 查询按钮事件
		 * @param grid_address1
		 *            表格ID
		 * @return
		 */
		$("#grid_address1").jqGrid(
				{
					url : $.taHash.get("ctx") + divData.loadGridUrl,
					mtype : "POST",
					datatype : "json",
					autowidth : true,
					height : 'auto',
					colNames : [ 'ID', '合同号', '合同名' ],
					colModel : [ {
						name : 'id',
						index : 'id',
						width : 50,
						resizable : false,
						align : 'center'
					}, {
						name : 'contractNo',
						index : 'contractNo',
						autowidth : true,
						resizable : true,
						align : 'center'
					}, {
						name : 'contractName',
						index : 'contractName',
						autowidth : true,
						resizable : true,
						align : 'center'
					} ],
					shrinkToFit : true,
					rowNum : 5,
					pager : '#gridPager_address1',
					viewrecords : true,
					pagerpos : 'left',
					recordpos : 'center',
					jsonReader : {
						root : "contractList",
						repeatitems : false,
						page : 'page',
						total : 'total',
						records : 'records',
						id : "contractNo"
					},
					multiselect : true,

					onSelectRow : function(id) {
						// 获取要回填的值
						var selectValue = common.selectRowShowCollValue(
								"grid_address1", "contractNo", id);
						// divId中的value为要回填的字段Id,也可直接用 ID.val(selectValue)赋值。
						var seft = $("#divId").val();
						$("#" + seft).dropDownDivList("default", selectValue);

					},
					multiselect : true

				});

	},

	/**
	 * @description 根据条件查询信息
	 * @param contractGrid表格ID
	 * @param contractName查询条件
	 * @param contractStartDate查询条件
	 * @param contractEndDate查询条件
	 * @return
	 */
	gridReload : function() {
		var gridReloadUrl = $.taHash.get("ctx") + divData.loadGridUrl;
		jQuery("#grid_address1").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				contractName : $("#contractName").val()
			},
			page : 1
		});
		jQuery("#grid_address1").trigger("reloadGrid");
	}

}