/**
* @deivData 页面 js文件
* @author 
* @date 2013-03-15
* @version 1.0
*/
var userSelector = {
	
	loadGridUrl : "/vclevelJson/queryUsersForSelector.do?ajax=true",
		

	initPage: function(){
		
		/**
		* @description 查询按钮事件
		* @param  queryResource 查询按钮ID
		* @return 
		*/
		$("#queryBut").click( function() {
			//执行查询方法
			userSelector.gridReload();
		});
		/**
		* @description 查询按钮事件
		* @param  userPage 表格ID
		* @return 
		*/
		$("#userGrid").jqGrid({
			url : $.taHash.get("ctx")+ userSelector.loadGridUrl,
			mtype: "POST", 
			datatype : "json",
			autowidth : true,
			height: 'auto',
			colNames : ['用户代码','用户名称', '机构'],
			colModel : [{name : 'userCode', index : 'userCode', autowidth:true, resizable : false, align : 'left'},
			            {name : 'userName', index : 'userName', autowidth : true, resizable : true, align : 'left'}, 
			            {name : 'orgName', index : 'orgName', autowidth : true,resizable : true, align : 'left'}
			            ],
				shrinkToFit : true,
				rowNum : 5,
				pager : '#userGridPage',
				viewrecords : true,
				jsonReader: {
			     	root: "users",
			        repeatitems : false,
			        page : 'page',
					total : 'total',
					records : 'records',
					id: "unitCode"
			     },
				multiselect: true,

			onSelectRow: function(id){
			   //获取要回填的值
			    	 var selectValue = common.selectRowShowCollValue(
								"userGrid", "userCode", id);
						var selectName = common.selectRowShowCollValue(
								"userGrid", "userName", id);
						var code =  $("#code").val();
						var name =  $("#name").val();
						
						$("#"+code).val(selectValue);
						$("#"+name).val(selectName);
			   
		    }, 
			multiselect: true

		});
		
	},
	/**
	* @description 根据条件查询信息
	* @param  contractGrid表格ID
	* @param  contractName查询条件
	* @param  contractStartDate查询条件
	* @param  contractEndDate查询条件
	* @return 
	*/
	gridReload : function() {
		var gridReloadUrl = $.taHash.get("ctx") + userSelector.loadGridUrl;
		jQuery("#userGrid").jqGrid('setGridParam', {
			datatype : "json",
			postData : {
			userCode : $("#userCode").val(),
			userName : $("#userName").val()
			},
			url : gridReloadUrl,
			page : 1
		});

		jQuery("#userGrid").trigger("reloadGrid");
	}
	
	
}
