var insuType = {
	
	gridReload: function () {
		
		var gridReloadUrl =  $.taHash.get("ctx")+"/baseinfoJson/queryInsuTypeListByPages.do?ajax=true";
		
		jQuery("#insuTypeGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型
			postData : {
				insuTypeCode : ($("#insuTypeCode").val()).replace(/(^\s*)|(\s*$)/g, ""),
				insuTypeName : ($("#insuTypeName").val()).replace(/(^\s*)|(\s*$)/g, "")
			},		
			page : 1
		});
		
		jQuery("#insuTypeGrid").trigger("reloadGrid");
		
	},
	
	initQueryPage: function(){
		
		$("#editPageInsuType").css("display","none");
		
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#editPageInsuType").dialog({
			modal: true,
			height:450,
			width:950,
			autoOpen: false

		});
		/**
		 * 
		 */
		$("#queryInsuType").click( function() {
			insuType.gridReload();
		});
		
		/**
		 * 
		 */
		$("#newInsuType").click(function() {
			//调用dialog组件
			$("#insuType_insuTypeCode").removeAttr("readOnly");
			$("#insuType_insuTypeCode").removeAttr("disabled");
			$("#editPageInsuType input[type='text']").each(function() {
				$(this).val("");
			});
			
			$("#insuType_id").val("");
			
			$('#editPageInsuType').dialog('open');

		});
		
		/**
		 * 
		*/
		$("#updateInsuType").click( function() {
			
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行修改");
				}else{
					//ajax获取要修改的数据
					common.ajaxGetData("editPageInsuType", $.taHash.get("ctx")+"/baseinfo/viewEditInsuType.do?id="+id+"&ajax=true",null);
					//调用dialog组件
					$.taHash.set("action","modify");
					$('#editPageInsuType').dialog('open');
				}
			}else{
				alert("请选择一条记录进行修改");
			}
			
		});		
		
		$("#deleteInsuType").click(function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			var action = $.taHash.get("ctx")+"/baseinfoJson/deleteInsuTypeList.do?ajax=true";
			if("" != id){
				//ajax获取要修改的数据
				if(confirm("确定删除已选记录?")) {
					var param = {"deleteList":id};
					common.ajaxSubmitDfCallBack(insuType.deleteOk, action, param);			
				}				
			}else{
				alert("请选择记录进行删除");
			}			
		});
		
		$("#insuTypeGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',
			colNames : ['ID','险类代码', '险类名称'],
			colModel : [{
						name : 'idVcDocInsuType',
						index : 'idVcDocInsuType',
						width : 50,
						resizable : true,
						align : 'center'
					},{
						name : 'insuTypeCode',
						index : 'insuTypeCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'insuTypeName',
						index : 'insuTypeName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#insuTypeGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "insuTypeList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "idVcDocInsuType"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("insuTypeGrid");
				$("#id").val(selectValue);
			}
		});
		
		
		
	},
	
	initEditPage : function(){
		//获取选中行的id
		$("#insuType_insuTypeCode").attr("readOnly",true);		
		$("#insuType_insuTypeCode").attr("disabled","disabled");
		
		$("#insuTypeForm").validate();
		
		/**
		* @description 添加按钮事件
		* @param  saveBut 添加按钮ID
		* @return 
		*/
		$("#saveButInsuType").click(function() {
			var valid = $("#insuTypeForm").validate().form();
			
			if(valid) {
				$("#saveButInsuType").attr("disabled",true);
				var id = $("#id").val();
				
				var action = $.taHash.get("ctx")+"/baseinfoJson/saveInsuType.do?ajax=true";
				var param=$("#insuTypeForm").serialize();
				
				common.ajaxSubmitDfCallBack(insuType.saveOk, action, param);	
			} else {
				return;
			}
			
		});
		
		/**
		* @description 返回按钮事件
		* @param  backBut 返回按钮ID
		* @return 
		*/
		$("#backButInsuType").click(function() {
			$('#editPageInsuType').dialog('close');
		});
	},
	
	saveOk:function(data) {
		 alert(data);
		 $("#saveButInsuType").removeAttr("disabled");
		 var index = data.toString().indexOf('成功');
		 if(index!=-1) {
			 $("#editPageInsuType").dialog('close');
			 insuType.gridReload();		 	
		 }
	},
	
	deleteOk:function(data) {
		 alert(data);
		 insuType.gridReload();
	}
}