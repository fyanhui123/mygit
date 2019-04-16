var recycleConfirm = {
	
	lineDataArry : [ 
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName",width:"200px",fieldClass:"required",type:"2",
					dataFormat:[{source:"/visa/baseinfoJson/queryVcDocVerLabels.do?ajax=true", height:"210px", width:'300px',minLength:0}], disabled:"disabled"},
		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",fieldClass:"required",type:"4", disabled:"disabled"},
		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",type:"4", disabled:"disabled"},
		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",type:"4", disabled:"disabled"},
		{title:"回收数量",fieldName:"recycleNum",fieldId:"recycleNum",width:"50px",type:"4", disabled:"disabled"},
		{title:"单证状态",fieldName:"docStatus",fieldId:"docStatus",width:"60px",fieldClass:"required",type:"3",
			dataFormat:[{selectedValue:"S1", data:[{"value":"S2","label":"申领/调拨入库"},{"value":"S3","label":"回收入库"}]}],
			disabled:"disabled"
		}
		],
	
	gridReload: function () {
		var gridReloadUrl = $.taHash.get("ctx")+"/recycleJson/getAwaitConfrimRecycleList.do?ajax=true";

		var QueryCondition = new Object();
		QueryCondition.queryCode = $("#recycleCode").val();
		QueryCondition.startDate = $("#initStartDate").val();
		QueryCondition.endDate = $("#initEndDate").val();
		QueryCondition.queryStatus = $("#recycleStatusSelect").val();
		
		jQuery("#recycleConfirmGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型 
			postData : {
				jsonStr : JSON.stringify(QueryCondition)
			},			
			page : 1
		});
		
		jQuery("#recycleConfirmGrid").trigger("reloadGrid");
		$("#id").val("");
	},
	
	initEditPage:function() {
		$("#recycleConfirmEdit").validate();
		var initData = $("#editRecycleConfirm_hiddenDet").html();
//		setTimeout(function(){
		$("#DocListRecycleConfirm").mulLineWidget({
			hiddenPlus: '1',		
			hiddenSubtraction: '1',
			columnData: recycleConfirm.lineDataArry,
			initJsonDate: initData
		  });
//		 }, 500);		
		
		$("#agreeButRecycleConfirmEdit").click(function() {
			
			$("#agreeButRecycleConfirmEdit").attr("disabled",true);
			$("#disagreeButRecycleConfirmEdit").attr("disabled",true);
			var disagreeReason = $("#disagreeReason").val();
			if(disagreeReason==null||disagreeReason=='') {
				alert("请填写审批意见");
				$("#agreeButRecycleConfirmEdit").removeAttr("disabled");
				$("#disagreeButRecycleConfirmEdit").removeAttr("disabled");
				return false;
			}
				
			var vcRecycle = recycleConfirm.getPageRecycle();
			vcRecycle.recycleStatus=statusValues.VC_RECYCLE_STATUS_CONFIRMED;
			
			recycleConfirm.ajaxSaveRecycleConfirm(vcRecycle);
			
		});
		
		$("#disagreeButRecycleConfirmEdit").click(function() {
			var disagreeReason = $("#disagreeReason").val();
			
			$("#agreeButRecycleConfirmEdit").attr("disabled",true);
			$("#disagreeButRecycleConfirmEdit").attr("disabled",true);
			if(disagreeReason==null||disagreeReason=='') {
				alert("请填写审批意见");
				$("#agreeButRecycleConfirmEdit").removeAttr("disabled");
				$("#disagreeButRecycleConfirmEdit").removeAttr("disabled");
				return false;
			}
			
			 
						
			var vcRecycle = recycleConfirm.getPageRecycle();
			vcRecycle.recycleStatus=statusValues.VC_RECYCLE_STATUS_CONFIRMBACK;
			
			recycleConfirm.ajaxSaveRecycleConfirm(vcRecycle);
			
		});
		
		$("#cancelButRecycleConfirmEdit").click(function() {
			$('#editPageRecycleConfirm').dialog('close');
		});
		
		
	},

	recycleQueryEnableStatus : [
		{"label":"待审批", "value":"2"},
		{"label":"审批退回", "value":"3"},
		{"label":"审批通过", "value":"4"}
	],	
	
	initQueryPage:function() {
		$("#editPageRecycleConfirmConfirm").css("display","none");
		$("#readOnlyPageRecycle").css("display","none");
		
		$("#recycleStatusSelect").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getPubCode.do?ajax=true&codeType=RecycleStatus"				
//			data:recycleConfirm.recycleQueryEnableStatus
		});		
		
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#editPageRecycleConfirm").dialog({
			modal: true,
			height:450,
			width:950,
			autoOpen: false
		});
		
		$("#readOnlyPageRecycle").dialog({
			modal: true,
			height:550,
			width:950,
			autoOpen: false
		});		
		
		/**
		 * 
		 */
		$("#queryRecycleConfirm").click( function() {
			recycleConfirm.gridReload();
		});
		
		/**
		 * 发放按钮单击事件
		 */
		$("#recycleConfirmBtn").click(function() {
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行审批");
				} else {
					//根据id获取行数据,返回的是列表 
					var rowDatas = $("#recycleConfirmGrid").jqGrid('getRowData', id); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var row = rowDatas["recycleStatus"]; 
					var rowCode = rowDatas["recycleCode"];
					if('2'!=row) {
						alert("所选回收单号非[待审批]状态,不可以进行审批操作");
						return;
					}				
					
					$("#readOnlyPageRecycle").empty();
					
					//ajax获取要修改的数据
					common.ajaxGetData("editPageRecycleConfirm", $.taHash.get("ctx")+"/recycle/viewRecycleConfirmEdit.do?id="+id+"&ajax=true",null);
					
					setTimeout(function(){
						$('#editPageRecycleConfirm').dialog('open');
					},200);
				}
			}else{
				alert("请选择一条记录进行审批");
			}
		});
			
		$("#recycleConfirmGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',
			colNames : ['ID','回收单号','回收操作人','回收提交机构','回收提交时间','回收确认机构','回收确认时间','申请单状态','recycleStatus'],
			colModel : [{
						name : 'idVcRecycle',
						index : 'idVcRecycle',
						hidedlg : true,
						hidden : true
					},{
						name : 'recycleCode',
						index : 'recycleCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'recycleOprName',
						index : 'recycleOprName',
						autowidth : true,
						resizable : true,
						align : 'left'
					} , {
						name : 'recycleOrgName',
						index : 'recycleOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'recycleTime',
						index : 'recycleTime',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i:s'}
					}, {
						name : 'confirmOrgName',
						index : 'confirmOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'confirmTime',
						index : 'confirmTime',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i:s'}
					}, {
						name : 'recycleStatusZh',
						index : 'recycleStatusZh',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'recycleStatus',
						index : 'recycleStatus',
						hidedlg : true,
						hidden : true
					} ],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#recycleConfirmGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "resultList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "idVcRecycle"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("recycleConfirmGrid");
				$("#id").val(selectValue);
			},
			
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#id").val("");
				}else{//全部选中时
					var ids = $("#recycleConfirmGrid").jqGrid('getDataIDs');
					$("#id").val(ids);
				}
			},
			onPaging:function(pgButton) {
				$("#id").val("");
			},
			gridComplete: function(){
			    var ids = $("#recycleConfirmGrid").jqGrid('getDataIDs');
			    for(var i=0;i < ids.length;i++){
			     var rowArray = $("#recycleConfirmGrid").jqGrid('getRowData',ids[i]);
			     var editBtn = "<a href='#' onclick='recycleConfirm.hrefView("+rowArray.idVcRecycle+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.recycleCode+"</a>";
			     $("#recycleConfirmGrid").jqGrid('setCell', ids[i], 'recycleCode', editBtn);
			    }
		   }
		});
		
	},
	
	getPageRecycle:function() {
		var vcRecycle = new Object();
		vcRecycle.idVcRecycle = $("#vcRecycle_idVcRecycle").val();
		vcRecycle.disagreeReason = $("#disagreeReason").val();
		return vcRecycle;
	},
	
	ajaxSaveRecycleConfirm:function(vcRecycle) {

		var str = JSON.stringify(vcRecycle);	
		
		var params = {
           jsonStr : str
        };

		$.ajax({   
             type:'post',   
             url: $.taHash.get("ctx")+"/recycleJson/saveRecycleConfirm.do?ajax=true",   
             dataType:'json',   
             data:params,
             success:function(msg){   
             
                alert(msg);
               $("#agreeButRecycleConfirmEdit").removeAttr("disabled");
         	   $("#disagreeButRecycleConfirmEdit").removeAttr("disabled");
               if("操作成功"==msg) {
                   $("#agreeButRecycleConfirmEdit").removeAttr("disabled");
	         	   $("#disagreeButRecycleConfirmEdit").removeAttr("disabled");
	         	   $('#editPageRecycleConfirm').dialog('close');
	         	   
               recycleConfirm.gridReload();
               }
         	 }
         }); 	
	},
	hrefView:function(id) {
		$("#editPageRecycleConfirm").empty();
		
		common.ajaxGetData("readOnlyPageRecycle", $.taHash.get("ctx")+"/recycle/viewRecycleReadonly.do?id="+id+"&ajax=true",null);
		
		setTimeout(function(){
			$('#readOnlyPageRecycle').dialog('open');
		},200);
	},
	clearGridData:function(gridId) {
		$("#"+gridId).clearGridData(); 
		$("#id").val("");
	}
}