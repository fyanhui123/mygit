/**
 * @fileOverview demo js文件
 * @author 廉超
 * @date 2013-02-25
 * @version 1.0
 */
var contract = {
		
	loadGridUrl : "/queryContractsByPages.do?ajax=true",
		
	/**
	 * @description 所属机构数据（为json对象）
	 * @param
	 * @return
	 */
	codeLabelData : [ 
 		{value:"SH",lable:"上海"},
 		{value:"BJ",lable:"北京"},
 		{value:"JS",lable:"江苏"},
 		{value:"GD",lable:"广东"}
 	],
 	
	initPage: function(){
	
		/**
		 * @description 所属机构数据（为json对象）
		 * @param source 如果source为URL，则返回结果为json对象
		 * @return
		 */
		$( "#deptCode" ).taAutoComplete({
			source: contract.codeLabelData,
			minLength: 0
		});
		
		/**
		 * @description 初始化dialog
		 * @param modal 是否模式层
		 * @param height 模式层高度(像素)
		 * @param width 模式层宽度
		 * @param autoOpen 是否默认打开模式层
		 * @return
		 */
		$("#editPage").dialog({
			modal: true,
			height:450,
			width:950,
			autoOpen: false

		});
		
		/**
		 * @description 添加按钮事件
		 * @param addContact 添加按钮ID
		 * @return
		 */
		$("#addContact").click( function() {
			common.enableForm();
			// 清空表单数据
			$("form input[type='text']").attr("value","");
			// 调用dialog组件
			$('#editPage').dialog('open');
		});
		
		/**
		 * @description 查询按钮事件
		 * @param queryResource 查询按钮ID
		 * @return
		 */
		$("#queryContract").click( function() {
			// 执行查询方法
			contract.gridReload();
		});
		
		/**
		 * @description 修改按钮事件
		 * @param modifyContact 修改按钮ID
		 * @return
		 */
		$("#modifyContact").click( function() {
			// 获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				// 判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行修改");
				}else{
					// ajax获取要修改的数据
					common.ajaxGetData("editPage", $.taHash.get("ctx")+"/subfunc/editPrinting.do?ajax=true&id="+id+"&action=loading",null);
					// 调用dialog组件
					$('#editPage').dialog('open');
				}
			}else{
				alert("请选择一条记录进行修改");
			}
			
		});
		
		/**
		 * @description 初始化表格数据
		 * @param contractGrid 表格ID
		 * @param ontractGridPager 分页标签Id
		 * @return
		 */
		$("#contractGrid").jqGrid({
			url : $.taHash.get("ctx")+ contract.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['ID','印刷厂代码', '印刷厂名称', '工商代码', '联系人', '联系电话', 'email', '合同号', '合同名','合同期限'],
			colModel : [
			           {name : 'id', index : 'id', width : 50, resizable : false, align : 'center'},
			           {name : 'printing.printingCode', index : 'printing.printingCode', autowidth : true, resizable : true, align : 'center'}, 
			           {name : 'printing.printingName', index : 'printing.printingName', autowidth : true, resizable : true, align : 'center' }, 
			           {name : 'printing.taxNo', index : 'printing.taxNo', autowidth : true, resizable : true, align : 'center'}, 
			           {name : 'printing.contact', index : 'printing.contact', width : 80, resizable : true, align : 'center'}, 
			           {name : 'printing.tel', index : 'printing.tel', autowidth : true, resizable : true, align : 'center'}, 
			           {name : 'printing.email', index : 'printing.email', autowidth : true, resizable : true, align : 'center'},
			           {name : 'contractNo', index : 'contractNo', autowidth : true, resizable : true, align : 'center',formatter: "currency", formatoptions: {thousandsSeparator:",",decimalSeparator:".", prefix:"¥"}},
			           {name : 'contractName', index : 'contractName', autowidth : true, resizable : true, align : 'center'},
			           {name : 'contractDate', index : 'contractDate', autowidth : true, resizable : true, align : 'center',formatter:"date",formatoptions: {srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}}
					],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 20, 30, 40, 50 ],
			pager : '#contractGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "contractList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "id"
		     },
			multiselect: true,
			/**
			 * @description 给ID添加链接
			 * @param contractGrid 表格Id
			 * @param id 表格Id
			 * @remark 其他参数不变，为规范写法，添加连接方式可以自行定义，这里以<a> 标签为例
			 * @return
			 */
			gridComplete: function(){ 
				ids = $("#contractGrid").jqGrid('getDataIDs');				
				for(var i=0;i < ids.length;i++){ 
					rowArray = $("#contractGrid").jqGrid('getRowData',ids[i]);	
					var href = '#';
					editBtn = '<a href=\"'+href+ '\">'+rowArray.id+'</a>'; 
					$("#contractGrid").jqGrid('setCell', ids[i], 'id', editBtn);
				}
				
			},　　　
			/**
			 * @description 获取选中的值
			 * @param contractGrid 表格ID
			 * @return
			 */
			onSelectRow: function(aRowids, status){
				var selectValue = common.selectRowValue("contractGrid");
				if(status){
					$("#id").val(selectValue);
				}else{
					$("#id").val("");
				}
				
			},
	
			/**
			 * @description 全选触发时候获取所有选中的Id值
			 * @param contractGrid 表格ID
			 * @return
			 */
			onSelectAll: function(aRowids, status){
				var selectValues = $("#contractGrid").jqGrid('getDataIDs');	
				if(status){
					$("#id").val(selectValues);
				}else{
					$("#id").val("");
				}
			}
			
			
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
		var gridReloadUrl = $.taHash.get("ctx")+ contract.loadGridUrl;
		
		jQuery("#contractGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData:{
				contractName : $("#contractName").val(),
				contractStartDate: $("#contractStartDate").val(),
				contractEndDate : $("#contractEndDate").val()
			},
			datatype : "json",
			page : 1
		});
		jQuery("#contractGrid").trigger("reloadGrid");
	},
	
	/**
	 * @description 初始化编辑页面
	 * @param
	 * @return
	 */
	initEditPage : function(){

		// 所属机构
		$("#deptCode").taAutoComplete({
			source: contract.codeLabelData,
			minLength: 0
		});
		
		/**
		 * @description 初始化表单验证组建
		 * @param saveBut 保存按钮ID
		 * @return
		 */
		$("#contractForm").validate();
		
		/**
		 * @description 保存按钮事件
		 * @param saveBut 保存按钮ID
		 * @return
		 */
		$("#saveBut").click( function() {
			// 判断表单验证是否通过
			if($("#contractForm").validate().form()){
				common.formSubmit("contractForm", $.taHash.get("ctx")+"/subfunc/savePrinting.do?action=savePrinting");
				contract.gridReload();
			}else{
				return false;
			}

		});
		
		/**
		 * @description 禁用按钮事件
		 * @param addContact 禁用按钮ID
		 * @return
		 */
		$("#disbBut").click( function() {
			common.disabledForm();
		});
		
	}
}