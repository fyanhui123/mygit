var selectDocVersion = {
		
	loadGridUrl : "/platformJson/queryInvoiceDocVersion.do?ajax=true",
	
	initPage: function(){
	
	
		// 单证种类(财务类)
		$( "#selectDocVersionDocTypeList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&style=auto&docType=1",
			minLength: 0,
			height: 200,
			width:220
		});
		
		 //机构选择
		$("#selectDocVersionOrgCode").dropDownDivList({		
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&onlyOrgFlag=1&singleSelect=true&tagCodeId=selectDocVersionOrgCode&tagNameId=selectDocVersionOrgName"
		});
		
		//单证类型
		$( "#selectDocVersionDocVersionInfoList" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&valueType=code&style=auto",
			minLength: 0,
			height: 200,
			width:220
		});
		
		/**
		* @description 查询按钮事件
		* @param  queryOrderGather 查询按钮ID
		* @return 
		*/
		$("#queryDocVersion").click( function() {
			selectDocVersion.gridReload();
		});
		
		/**
		* @description 重置按钮事件
		* @param  resetInput 重置按钮ID
		* @return 
		*/
		$("#resetInput").click( function() {
			//清空查询条件
			$("#selectDocVersionDocTypeList").val("");
			$("#selectDocVersionDocTypeListName").val("");
			$("#selectDocVersionOrgCode").val("");
			$("#selectDocVersionOrgName").val("");
			$("#selectDocVersionDocVersionInfoList").val("");
			$("#selectDocVersionDocVersionInfoListName").val("");
			//清空选中的id
			$("#inputId").val("");
			//清空表格数据
			$("#selectDocVersionGrid").clearGridData();
		});
		
		
		/**   
		* @description  单证类型直接入库按钮事件
		* @return 
		*/
		$("#docVersionInsert").click(function(){
		         var  purchaseId=$("#invoiceShortCodeId").val();
		         var  docVerCode=$("#inputId").val();
		         var orgCode = $("#invoicePurchaseOrgCode").val();
		         
				 selectDocVersion.inStoreConfirm(purchaseId,docVerCode,orgCode);
		 });
			
		
		/**
		* @description 取消按钮事件
		* @param  gatherCancelBtn 汇总按钮ID
		* @return 
		*/
		$("#cancelBtn").click( function() {
			$('#invoiceSelectDocVersion').dialog('close');
		});
		
		$("#selectDocVersionGrid").jqGrid({
			url : $.taHash.get("ctx")+ selectDocVersion.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			width : 830,
			height : 'auto',
			colNames : ['单证版本信息流水','单证类型代码','单证类型名称', '适用地区'],
			colModel : [{
						name : 'idVcDocVersionInfo',
						index : 'idVcDocVersionInfo',
						hidden : true
					},{
						name : 'docVerCode',
						index : 'docVerCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'docVerName',
						index : 'docVerName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'orgName',
						index : 'orgName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#selectDocVersionGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "invoiceDocVersionDTOList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "docVerCode"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var docVerCodeValue = common.selectRowValue("selectDocVersionGrid");
				$("#inputId").val(docVerCodeValue);
			},
			
			/**
			* @description 全选触发时候获取所有选中的Id值
			* @return 
			*/
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#inputId").val("");
				}else{//全部选中时
					var ids = $("#selectDocVersionGrid").jqGrid('getDataIDs');
					$("#inputId").val(ids);
				}
			}
		});
		
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#inputId").val("");
		var tmpStr=$("#invoiceSelectDocVersionForm").serialize();
		var orgCode = $("#invoicePurchaseOrgCode").val();
		var gridReloadUrl = $.taHash.get("ctx")+ selectDocVersion.loadGridUrl + "&purchaseOrg=" + orgCode +"&"+tmpStr;
		jQuery("#selectDocVersionGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {},
			datatype : "json",
			page : 1
		});
		jQuery("#selectDocVersionGrid").trigger("reloadGrid");
	},
	inStoreConfirm:function(purchaseId,docVerCode,orgCode) {

        $("#docVersionInsert").attr("disabled",true);
		var params = {
           purchaseId : purchaseId,
           docVerCode: docVerCode,
           purchaseOrg : orgCode
        };

		$.ajax({   
             type:'post',   
            url: $.taHash.get("ctx")+"/platformJson/inStoreInvoiceDocVersion.do?ajax=true",   
             dataType:'json',   
             data:params,
             success:function(msg){   
                alert("入库成功，入库申请单号为"+msg+"!");               //提示申请入库单号
                
               $("#docVersionInsert").removeAttr("disabled");
               if(msg!=null) {
	         	   $('#invoiceSelectDocVersion').dialog('close');   //关闭单证类型新增界面
                  invoicePurchase.purchaseGridReload();  //刷新领购信息
               }
         	 }
         }); 	
	}
	
	
}