var cancelApprove = {
	
	lineDataArry : [ 
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName", width:"180px",fieldClass:"required",type:"2",
					dataFormat:[{source:"/visa/baseinfoJson/queryVcDocVerLabels.do?ajax=true",height:"210px",minLength:0}]},
		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",fieldClass:"required",type:"4"},
		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",fieldClass:"required",type:"4"},
		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",fieldClass:"required",type:"4"},
		{title:"作废数量",fieldName:"cancelNum",fieldId:"cancelNum",width:"60px",fieldClass:"required",type:"4"},
		{title:"单证状态",fieldName:"docStatus",fieldId:"docStatus",width:"60px",fieldClass:"required",type:"3",
			dataFormat:[{selectedValue:"S1", data:[{"value":"S1","label":"S3-印刷入库"},{"value":"S2","label":"S2-申领入库"},{"value":"S3","label":"S3-回收入库"}
			,{"value":"U1","label":"U1-已使用"},{"value":"U2","label":"U2-手工已使用登记"}]}]
		}
	],
	
	lineDataArryDisabled : [
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName", width:"180px",fieldClass:"required",type:"2",
					dataFormat:[{source:"/visa/baseinfoJson/queryVcDocVerLabels.do?ajax=true",height:"210px",minLength:0}],disabled:"disabled"},
		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"作废数量",fieldName:"cancelNum",fieldId:"cancelNum",width:"60px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"单证状态",fieldName:"docStatus",fieldId:"docStatus",width:"60px",fieldClass:"required",type:"3",disabled:"disabled",
			dataFormat:[{selectedValue:"S1", data:[{"value":"S1","label":"S1-印刷入库"},{"value":"S2","label":"S2-申领/调拨入库"},{"value":"S3","label":"S3-回收入库"}
			,{"value":"U1","label":"U1-已使用"},{"value":"U2","label":"U2-手工已使用登记"}]}]
		}
	],	
	
	gridReload: function () {
	    $("#id").val("");
		var gridReloadUrl = $.taHash.get("ctx")+"/visaUsageJson/queryCancelListByPages.do?ajax=true";

		var QueryCondition = new Object();
		QueryCondition.cancelCode = $("#cancelCode").val();
		QueryCondition.startDate = $("#initStartDate").val();
		QueryCondition.endDate = $("#initEndDate").val();
		QueryCondition.cancelStatus = $("#cancelStatusSelect").val();
		QueryCondition.cancelType = $("#cancelTypeSelect").val();
		
		jQuery("#cancelApproveGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型 
			postData : {
				jsonStr : JSON.stringify(QueryCondition)
			},				
			page : 1
		});
		
		jQuery("#cancelApproveGrid").trigger("reloadGrid");
	},	
	
	initEditPage:function() {
		$("#cancelApproveEdit").validate();
		var initJson = $("#edit_hiddenDet").html();
		
		$("#DocListCancelApproveEdit").mulLineWidget({
			hiddenPlus: '1',		
			hiddenSubtraction: '1',
			columnData: cancelApprove.lineDataArryDisabled,
			initJsonDate: initJson
		});
		
		$("#agreeButCancelApproveEdit").click(function() {
			
			var vcCancel = cancelApprove.getPageCancel();
			vcCancel.cancelApprove = "2";
			cancelApprove.ajaxSaveCancelApprove(vcCancel,"agreeButCancelApproveEdit");
			
		});
		
		$("#disagreeButCancelApproveEdit").click(function() {
			var rs = $("#cancelApproveRemark").html();
			if(rs!=null && rs!="") {
				var vcCancel = cancelApprove.getPageCancel();
				vcCancel.cancelApprove = "1";
				cancelApprove.ajaxSaveCancelApprove(vcCancel,"disagreeButCancelApproveEdit");
			} else {
				alert("审批意见不可以为空");
				return;
			}
		});	
		
		$("#cancelButCancelApproveEdit").click(function() {
			$('#editPageCancelApprove').dialog('close');
		});
	},
	
	initReadonlyPage:function() {
		var initJson = $("#readOnly_hiddenDet").html();
		
		$("#DocListCancelSReadonly").mulLineWidget({
			hiddenPlus: '1',		
			hiddenSubtraction: '1',
			columnData: cancelApprove.lineDataArryDisabled,
			initJsonDate: initJson
		});
		
		$("#cancelButCancelSReadoly").click(function() {
			$('#readOnlyPageCancelApprove').dialog('close');
		});
	},	
	
	cancelQueryEnableStatus : [
		{"label":"已删除", "value":"0"},
		{"label":"新建", "value":"1"},
		{"label":"待审批", "value":"2"},
		{"label":"审批退回", "value":"3"},
		{"label":"审批通过", "value":"4"}
	],
	
	cancelQueryEnableType : [
		{"label":"库存作废", "value":"S"},
		{"label":"已使用作废", "value":"U"}
	],
	
	initQueryPage:function() {
		$("#editPageCancelApprove").css("display","none");
		$("#readOnlyPageCancelApprove").css("display","none");
		
		$("#cancelStatusSelect").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getPubCode.do?ajax=true&codeType=CancelStatus&notContainCodeCode=0,1"	//不包含删除、新建			
//			data:cancelApprove.cancelQueryEnableStatus
		});
		
		$("#cancelTypeSelect").dropDownList({
			//data:cancelApprove.cancelQueryEnableType
			 url: "/visa/baseinfoJson/initDownList.do?codeType=CancelType&actionType=query"
		});
		
		$("#editPageCancelApprove").dialog({
			modal: true,
			height:550,
			width:1000,
			autoOpen: false
		});
		
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#readOnlyPageCancelApprove").dialog({
			modal: true,
			height:550,
			width:1000,
			autoOpen: false
		});
		
		/**
		 * 
		 */
		$("#queryCanceApprove").click( function() {
			cancelApprove.gridReload();
		});
		
		$("#cancelApproveBtn").click(function() {
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行审批");
				} else {
					var rowDatas = $("#cancelApproveGrid").jqGrid('getRowData', id); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var rowStatus = rowDatas["cancelStatus"];
					var rowCancelCode = rowDatas["cancelCode"];
					if((2!=rowStatus)) {
						alert("所选作废单号非[待审批]状态,不可以进行审批操作");
						return;
					}	
					
					$("#readOnlyPageCancelApprove").empty();
					//ajax获取要修改的数据
					common.ajaxGetData("editPageCancelApprove", $.taHash.get("ctx")+"/visaUsage/viewCancelApproveEdit.do?id="+id+"&ajax=true",null);
					$('#editPageCancelApprove').dialog('open');
				}
			}else{
				alert("请选择一条记录进行审批");
			}
		});
		
		$("#cancelApproveGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '210px',
			colNames : ['ID','作废单号','申请机构','申请人','申请时间','作废类型','申请单状态','cancelStatus'],
			colModel : [{
						name : 'idVcCancel',
						index : 'idVcCancel',
						hidedlg : true,
						hidden : true
					},{
						name : 'cancelCode',
						index : 'cancelCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'cancelOrgName',
						index : 'cancelOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					} , {
						name : 'cancelOprName',
						index : 'cancelOprName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'cancelTime',
						index : 'cancelTime',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i:s'}
					},  {
						name : 'cancelTypeZh',
						index : 'cancelTypeZh',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'cancelStatusZh',
						index : 'cancelStatusZh',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'cancelStatus',
						index : 'cancelStatus',
						hidedlg : true,
						hidden : true
					} ],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#cancelApproveGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "resultList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "idVcCancel"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("cancelApproveGrid");
				$("#id").val(selectValue);
			},
			
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#id").val("");
				}else{//全部选中时
					var ids = $("#cancelApproveGrid").jqGrid('getDataIDs');
					$("#id").val(ids);
				}
			},
			onPaging:function(pgButton) {
				$("#id").val("");
			},
			gridComplete: function(){
			    var ids = $("#cancelApproveGrid").jqGrid('getDataIDs');
			    for(var i=0;i < ids.length;i++){
			     var rowArray = $("#cancelApproveGrid").jqGrid('getRowData',ids[i]);
			     var editBtn = "<a href='#' onclick='cancelApprove.hrefView("+rowArray.idVcCancel+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.cancelCode+"</a>";
			     $("#cancelApproveGrid").jqGrid('setCell', ids[i], 'cancelCode', editBtn);
			    }
		   }
		});
		
	},
	
	validatePageCancelError:function() {
		return isValidateError;
	},
	
	getPageCancel:function() {
		
		var vcCancel = new Object();
		
		vcCancel.idVcCancel=$("#idVcCancel").val();
		vcCancel.cancelApproveRemark=$("#cancelApproveRemark").html();
		return vcCancel;
	},

	ajaxSaveCancelApprove:function(vcCancel,butId) {
		//$("#"+butId).attr("disabled",true);
		$("#agreeButCancelApproveEdit").attr("disabled",true);
		$("#disagreeButCancelApproveEdit").attr("disabled",true);
		var str = JSON.stringify(vcCancel);	
		
		var params = {
           jsonStr : str
        };
        
		$.ajax({   
             type:'post',   
             url: $.taHash.get("ctx")+"/visaUsageJson/saveCancelApprove.do?ajax=true",
             dataType:'json',   
             data:params,
             success:function(msg){
              alert(msg);
              //$("#"+butId).removeAttr("disabled");
              $("#agreeButCancelApproveEdit").removeAttr("disabled");
      		  $("#disagreeButCancelApproveEdit").removeAttr("disabled");
              if("审批操作成功"==msg) {
	              cancelApprove.gridReload();   
	              $('#editPageCancelApprove').dialog('close');
              }
         	 }
         }); 	
	},
	deleteOk:function(data) {
		 alert(data);
		 cancelApprove.gridReload();
	},
	clickReset:function(gridId) {
		$("#"+gridId).clearGridData(); 
		$("#id").val("");
	},
	hrefView:function(id) {
		$("#editPageCancelApprove").empty();
		common.ajaxGetData("readOnlyPageCancelApprove", $.taHash.get("ctx")+"/visaUsage/viewCancelApproveReadonly.do?id="+id+"&ajax=true",null);
		$('#readOnlyPageCancelApprove').dialog('open');
	}
}