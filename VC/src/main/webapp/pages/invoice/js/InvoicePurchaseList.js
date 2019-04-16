var invoicePurchase = {
        loadGridUrl : "/platformJson/queryInvoicePurchaseBaseInfo.do?ajax=true",
    	initPage : function(){	
    	
    	
    	 //机构选择
		$("#invoicePurchaseOrgCode").dropDownDivList({		
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=invoicePurchaseOrgCode&tagNameId=invoicePurchaseOrgName"
		});
		
		//发票平台导入
		$("#plantFormImport").click(function(){
		    var  orgCode=$("#invoicePurchaseOrgCode").val();
		    if(orgCode==""){
		      alert("请选择机构！");
		      return false;
		    }else{
		       invoicePurchase.gridReload();
		    }
		});
		
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#invoiceSelectDocVersion").dialog({
			modal: true,
			height:550,
			width:950,
			autoOpen: false
		});
		
		
		/**
		 * 新增按钮事件
		 */
		$("#inStoreInvoicePurchase").click(function(){
		    var  invoiceShortCodeId=$("#invoiceShortCodeId").val();
		    //var status=common.selectRowShowCollValue("invoicePurchasePurchaseGrid","inStoreFlag",invoiceShortCodeId);
		    //var purchaseType=common.selectRowShowCollValue("invoicePurchasePurchaseGrid","purchaseType",invoiceShortCodeId);
		    var rowArray = $("#invoicePurchasePurchaseGrid").jqGrid('getRowData', invoiceShortCodeId);
		    var status = rowArray.inStoreFlag;
		    var purchaseType = rowArray.purchaseType;
		    var patt = new RegExp(',');
			if("" != invoiceShortCodeId){
			    if(patt.test(invoiceShortCodeId)){
				    alert("只能选择一条记录！");
				}else{
				     if(status =='1' ||  purchaseType !='04'){
				        alert("必须是未导入库存记录且是纳税人领购！");
				     }else{
					     //重置新增界面
						$("#resetInput").trigger("click");
						//弹出新增页面
						$('#invoiceSelectDocVersion').dialog('open');
					}
				}
			}else{
			  alert("请选择一条领购信息！");
			}
			
		});
		
		
    	// 纳税人基本信息
    	   $("#invoicePurchaseBaseinfoGrid").jqGrid( {
			url : invoicePurchase.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',	
			colNames : ['纳税人电脑编码', '纳税人识别号','纳税人名称','主管税务机构代码','主管税务机构名称','纳税人状态','是否委托代征','所属地市'],
			colModel : [
					{
						name : 'computerNo',
						index : 'computerNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'taxPayerId',
						index : 'taxPayerId',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'taxPayerName',
						index : 'taxPayerName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'taxOrgCode',
						index : 'taxOrgCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'taxOrgName',
						index : 'taxOrgName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'taxPayerStatus',
						index : 'taxPayerStatus',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'collectFlag',
						index : 'collectFlag',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'belongCity', 
						index : 'belongCity',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 5,
			rowList : [5, 10, 20],
			pager : '#invoicePurchaseBaseinfoGridPager',
			viewrecords : true,
			jsonReader : {
				root : "basaicInfoDTOList",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
			    id : "computerNo"
			},
			//multiselect : true,

			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				 //var selectValue = common.selectRowValue("invoicePurchaseBaseinfoGrid");
				 var selectValue = $("#invoicePurchaseBaseinfoGrid").jqGrid('getGridParam','selrow');
				 $("#id").val(selectValue);
				 if(selectValue==""){
				  return false;
				 }
				 invoicePurchase.purchaseGridReload(selectValue);
			}
          });
          
          
          
         // 纳税人领购信息
          $("#invoicePurchasePurchaseGrid").jqGrid( {
			url : invoicePurchase.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',	
			colNames : [ '纳税人领购信息流水','发票简码代码','发票领购序号','发票代码','发票名称','起始号码','终止号码','份数','每月份数','本数','领购日期','领购类型代码','领购类型','系统内状态代码','系统内状态'],
			colModel : [{
						name : 'id',
						index : 'id',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						hidden : true
					},{
						name : 'invoiceShortCode',
						index : 'invoiceShortCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						hidden : true
					},{
						name : 'serialNo',
						index : 'serialNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'invoiceCode',
						index : 'invoiceCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'invoiceName',
						index : 'invoiceName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'startNum',
						index : 'startNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'endNum',
						index : 'endNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'copyNum',
						index : 'copyNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'unitNum',
						index : 'unitNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'pileNum',
						index : 'pileNum',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'purchaseDate',
						index : 'purchaseDate',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
			           	formatoptions:{srcformat:'Y-m-d', newformat:'Y-m-d'} 	
					},{
						name : 'purchaseType',
						index : 'purchaseType',
						autowidth : true,
						resizable : true,
						sortable : false,
					//	formatter : purchaseTypeFormatter,
						align : 'left',
						hidden : true
					},{
						name : 'purchaseTypeName',   //显示领购类型名称
						index : 'purchaseTypeName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'inStoreFlag',
						index : 'inStoreFlag',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						hidden : true
					},{
						name : 'inStoreFlagName',  //显示是否已入库名称
						index : 'inStoreFlagName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [5, 10, 20],
			pager : '#invoicePurchasePurchaseGridPager',
			viewrecords : true,
			jsonReader : {
				root : "invoicePurchaseDTOList",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "id"
			}, 
			//multiselect : true,
			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function() {
				//var valueId = common.selectRowValue("invoicePurchasePurchaseGrid");
				var valueId = $("#invoicePurchasePurchaseGrid").jqGrid('getGridParam','selrow');
				$("#invoiceShortCodeId").val(valueId);   
				//取流水号id  1. 转换发票简码代码触发纳税人授权信息   2.流水号获取明细进行入库
				var patt = new RegExp(',');
				if("" != valueId){
				      if(patt.test(valueId)){
					    alert("只能选择一条记录！");
					    return false;
					 }else{
					    invoicePurchase.purchaseImpowerGridReload(valueId);
					 }
				}/*else{
				   alert("请选择一条领购信息！");
		
			}*/
			}
			
          });
          
          // 纳税人授权信息
        $("#invoicePurchaseImpowerGrid").jqGrid( {
			url : invoicePurchase.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',	
			colNames : ['纳税人授权信息ID', '发票简码', '发票名称','开具方式','负数开票授信','简易验旧授信','单份开票开具限额','启用时间','使用时间'],
			colModel : [{name : 'id',
						 index : 'id',
						 hidedlg : true,
						 hidden : true
					},{
						name : 'invoiceShortCode',
						index : 'invoiceShortCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'invoiceName',
						index : 'invoiceName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'printTypeCode',
						index : 'printTypeCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'negativeFlag',
						index : 'negativeFlag',
						autowidth : true,
						resizable : true,
						sortable : false,
						formatter : negativeFlagFormatter,
						align : 'left'
					},{
						name : 'checkOldFlag',
						index : 'checkOldFlag',
						autowidth : true,
						resizable : true,
						formatter : checkOldFlagFormatter,
						sortable : false,
						align : 'left'

					},{
						name : 'limitAmount',
						index : 'limitAmount',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'startDate',
						index : 'startDate',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
			           	formatoptions:{srcformat:'Y-m-d', newformat:'Y-m-d'}	
					},{
						name : 'endDate',
						index : 'endDate',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left',
						formatter:"date",
				        formatoptions:{srcformat:'Y-m-d', newformat:'Y-m-d'}	
					}],
			shrinkToFit : true,
			rowNum : 5,
			rowList : [5, 10, 20],
			pager : '#invoicePurchaseImpowerGridPager',
			viewrecords : true,
			jsonReader : {
				root : "vcTaxAuthDTOList",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "id"
			}, 
			//multiselect : true,
			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				//var ordId = common.selectRowValue("invoicePurchaseImpowerGrid");
				var ordId = $("#invoicePurchaseImpowerGrid").jqGrid('getGridParam','selrow');
				$("#id").val(ordId);
				if(ordId==""){
				  return false;
				 }
				 invoicePurchase.invoicePurchaseImpowerDetGridReload(ordId);
			}
			
          });
          
          
           // 纳税人授权明细 
          $("#invoicePurchaseImpowerDetGrid").jqGrid( {
			url : invoicePurchase.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',	
			colNames : ['ID', '适用行业代码', '适用行业名称','适用项目代码','适用项目名称','套打样式代码','套打样式名称'],
			colModel : [{name : 'id',
						 index : 'id',
						 hidedlg : true,
						 hidden : true
					},{
						name : 'industryCode',
						index : 'industryCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'industryName',
						index : 'industryName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'itemCode',
						index : 'itemCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'itemName',
						index : 'itemName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'printTemplateCode',
						index : 'printTemplateCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'printTemplateName',
						index : 'printTemplateName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 5,
			rowList : [5, 10, 20],
			pager : '#invoicePurchaseImpowerDetGridPager',
			viewrecords : true,
			jsonReader : {
				root : "vcTaxAuthDetailDTOList",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "id"
			}, 
			//multiselect : true,
			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				//var selectValue = common.selectRowValue("invoicePurchaseImpowerDetGrid");
				var selectValue = $("#invoicePurchaseImpowerDetGrid").jqGrid('getGridParam','selrow');
				$("#id").val(selectValue);
			}
			
          });
          
          
          // 格式化负数发票授信
		  function   negativeFlagFormatter(cellvalue, options, rowObject) {
				if (cellvalue == 'Y') {
					return '可开具负数';
				}else if (cellvalue == 'N') {
					return '不可开具负数';
				}
	       };
		 
		  
          // 格式化简易验旧授信
		  function   checkOldFlagFormatter(cellvalue, options, rowObject) {
				if (cellvalue == 'Y') {
					return '简易验旧';
				}else if (cellvalue == 'N') {
					return '非简易验旧';
				}
	       };
	       
	       
          
    	},
    	
    	gridReload : function() {
		var orgCode = $("#invoicePurchaseOrgCode").val();
		var gridReloadUrl = $.taHash.get("ctx")+invoicePurchase.loadGridUrl+"&orgCode="+orgCode;
		jQuery("#invoicePurchaseBaseinfoGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData : {},
			page : 1
		});
		jQuery("#invoicePurchaseBaseinfoGrid").trigger("reloadGrid");
	  },
	 purchaseGridReload : function(selectValue) {
     var purchaseGridReloadUrl = $.taHash.get("ctx")+"/platformJson/queryInvoicePurchasePurchase.do?ajax=true&companyN0="+selectValue;
		jQuery("#invoicePurchasePurchaseGrid").jqGrid('setGridParam', {
			url : purchaseGridReloadUrl,
			datatype : "json",
			postData : {},
			page : 1
		});
		jQuery("#invoicePurchasePurchaseGrid").trigger("reloadGrid");
	  },
	   purchaseImpowerGridReload : function(valueId) {
		var purchaseImpowergridReloadUrl =$.taHash.get("ctx")+"/platformJson/queryInvoicePurchaseImpower.do?ajax=true&invoiceShortCode="+valueId;
		jQuery("#invoicePurchaseImpowerGrid").jqGrid('setGridParam', {
			url : purchaseImpowergridReloadUrl,
			datatype : "json",
			postData : {},
			page : 1
		});
		jQuery("#invoicePurchaseImpowerGrid").trigger("reloadGrid");
	  },
	   invoicePurchaseImpowerDetGridReload : function(ordId) {
		var invoicePurchaseImpowerDetGridReloadUrl = $.taHash.get("ctx")+"/platformJson/queryInvoicePurchaseImpowerDet.do?ajax=true&ordId="+ordId;
		jQuery("#invoicePurchaseImpowerDetGrid").jqGrid('setGridParam', {
			url : invoicePurchaseImpowerDetGridReloadUrl,
			datatype : "json",
			postData : {},
			page : 1
		});
		jQuery("#invoicePurchaseImpowerDetGrid").trigger("reloadGrid");
	  }
	  
}