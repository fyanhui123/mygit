var insuKind = {
		
	
	gridReload: function () {
		
		var gridReloadUrl = $.taHash.get("ctx")+"/baseinfoJson/queryInsuKindListByPages.do?ajax=true";
		
		jQuery("#insuKindGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型
			postData : {
				insuTypeId : $("#insuTypeList").val(),
				insuKindCode : $("#insuKindCode").val(),
				insuKindName : $("#insuKindName").val()
			},	
			page : 1
		});
		
		jQuery("#insuKindGrid").trigger("reloadGrid");
		
	},
	
	initQueryPage: function(){
		
		$("#editPageInsuKind").css("display","none");
		
		$("#insuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true&valueType=serialId&random="+ Math.random()
		}); 

		
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#editPageInsuKind").dialog({
			modal: true,
			height:450,
			width:950,
			autoOpen: false
		});
		
		/**
		 * 
		 */
		$("#queryInsuKind").click( function() {
			insuKind.gridReload();
		});
		
		/**
		 * 
		 */
		$("#newInsuKind").click(function() {
//			alert("insuKind");
			
			//调用dialog组件
			$("#singliDropDownListInsuType").removeAttr("disabled");
			$("#insuKind_insuKindCode").removeAttr("readOnly");
			$("#insuKind_insuKindCode").removeAttr("disabled");

			
			$("#editPageInsuKind input[type='text']").each(function() {
				$(this).val("");
			});
			
			$("#insuKind_id").val("");
			$("#singliDropDownListInsuType").val("");
			
			$('#editPageInsuKind').dialog('open');
		});
		
		/**
		 * 
		*/
		$("#updateInsuKind").click( function() {
			
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行修改");
				}else{
					//ajax获取要修改的数据
					common.ajaxGetData("editPageInsuKind", $.taHash.get("ctx")+"/baseinfo/viewEditInsuKind.do?id="+id+"&ajax=true&actionType=update",null);
					
      				setTimeout(function(){
	     				$('#editPageInsuKind').dialog('open');
      				},500);
      				
				}
			}else{
				alert("请选择一条记录进行修改");
			}
			
		});		
		
		$("#deleteInsuKind").click(function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			var action = $.taHash.get("ctx")+"/baseinfoJson/deleteInsuKindList.do?ajax=true";
			if("" != id){
				//ajax获取要修改的数据
				if(confirm("确定删除已选记录?")) {
					var param = {"deleteList":id};
					common.ajaxSubmitDfCallBack(insuKind.deleteOk, action, param);				
				}
			}else{
				alert("请选择记录进行删除");
			}			
		});
		
		$("#insuKindGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',
			colNames : ['ID', '单证险类', '险种代码', '险种名称'],
			colModel : [{
						name : 'idVcDocInsuKind',
						index : 'idVcDocInsuKind',
						width : 50,
						resizable : true,
						align : 'center'
					},{
						name : 'insuType.insuTypeName',
						index : 'insuType.insuTypeName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'insuKindCode',
						index : 'insuKindCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'insuKindName',
						index : 'insuKindName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#insuKindGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "insuKindList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "idVcDocInsuKind"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("insuKindGrid");
				$("#id").val(selectValue);
			}
		});
		
		
		
	},
	
	initEditPage : function(){
		
		$("#singliDropDownListInsuType").dropDownList({ 
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true&valueType=serialId&random="+ Math.random()
		});
		setTimeout(function(){
			if($("#actionType").val() == "update"){
				  $("#insuKind_insuKindCode").attr("readOnly",true);
				  $("#insuKind_insuKindCode").attr("disabled","disabled");
				  $("#singliDropDownListInsuType").dropDownList("disable",$("#insuKind_insuType_idVcDocInsuType").val());
				}
		},200);
		
		
		
		
		$("#insuKindForm").validate();
		
		/**
		* @description 添加按钮事件
		* @param  saveBut 添加按钮ID
		* @return 
		*/
		$("#saveButInsuKind").click(function() {
			
			var valid = $("#insuKindForm").validate().form();
			if(valid) {
				$("#saveButInsuKind").attr("disabled",true);
				var id = $("#id").val();
				
				var action = $.taHash.get("ctx")+"/baseinfoJson/saveInsuKind.do?ajax=true";
				var param=$("#insuKindForm").serialize();
				common.ajaxSubmitDfCallBack(insuKind.saveOk, action, param);	
			} else {
				return;
			}			

		});
		
		/**
		* @description 返回按钮事件
		* @param  backBut 返回按钮ID
		* @return 
		*/
		$("#backButInsuKind").click(function() {
			$('#editPageInsuKind').dialog('close');
		});
	},
	
	saveOk:function(data) {
		 alert(data);
		 $("#saveButInsuKind").removeAttr("disabled");
		 var index = data.toString().indexOf('成功');
		 if(index!=-1) {
			 $("#editPageInsuKind").dialog('close');
			 insuKind.gridReload();
		 }
	},
	
	deleteOk:function(data) {
		 alert(data);
		 insuKind.gridReload();
	}
}