var provideConfirm = {
	
	lineDataArry : [ 
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName",width:"200px",type:"2",
					dataFormat:[{source:"/visa/baseinfoJson/queryVcDocVerLabels.do?ajax=true", height:"210px", width:'300px',minLength:0}],disabled:"disabled"},
		{title:"申请数量",fieldName:"applyNum",fieldId:"applyNum",width:"50px",type:"4", disabled:"disabled"},
		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",type:"4", disabled:"disabled"},
		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",type:"4",disabled:"disabled"},
		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",type:"4",disabled:"disabled"},
		{title:"发放数量",fieldName:"provideNum",fieldId:"provideNum",width:"50px",type:"4",disabled:"disabled"},
		{title:"使用截止日期",fieldName:"deadlineStr",fieldId:"deadlineStr",width:"100px",type:"5",disabled:"disabled"},
		{title:"操作",fieldId:"edit",fieldName:"edit",fieldVal:"查看", type:"8",width:"100px"}
		],
	/**
	 * 
	 */	
	applyQueryEnableStatus : [
		{"label":"已删除", "value":"0"},
		{"label":"新建", "value":"1"},
		{"label":"等待发放", "value":"2"},
		{"label":"发放退回", "value":"3"},
		{"label":"等待确认", "value":"4"},
		{"label":"确认退回", "value":"5"},
		{"label":"已确认", "value":"6"}
	],
	
	gridReload: function () {
		var gridReloadUrl = common.ctx+"/applyJson/queryApplyListByPages.do?ajax=true";
		var QueryCondition = new Object();
		QueryCondition.queryCode = $("#applyCode").val();
		QueryCondition.startDate = $("#initStartDate").val();
		QueryCondition.endDate = $("#initEndDate").val();
		QueryCondition.queryStatus = $("#applyStatusDropDownList").val();
		
		jQuery("#provideConfirmGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型 
			postData : {
				jsonStr : JSON.stringify(QueryCondition)
			},
			page : 1
		});
			
		jQuery("#provideConfirmGrid").trigger("reloadGrid");
		$("#id").val("");
	},
	
	resetVal : function (rIndex, objId){
		/**
		 * @description 更改字段值
		 * @param  参数1(org） 要更改值的标签Id
		 * @param  参数2(2） 要更改值的标签value
		 * 
		 */
		if(objId == "startNum"){
			var jsonArr = $("#DocListProvideConfirm").mulLineWidget.getData();
			var startNum = jsonArr[rIndex-1].startNum ;
			var endNum = jsonArr[rIndex-1].endNum;
			if(endNum==null||endNum=="") {
				$("#DocListProvideConfirm").mulLineWidget.resetVal(rIndex, "endNum", startNum);
				$("#DocListProvideConfirm").mulLineWidget.resetVal(rIndex, "provideNum", 1);				
			}
		}
		if(objId == "edit"){
			var jsonArr = $("#DocListProvideConfirm").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			
			if(docVerCode==null ||docVerCode=="") {
				alert("请选择单证类型");
				return;
			} else {
				$("#validStorageQuery_docVerCode").val(docVerCode);
				//add by chy 20130916
				//申领机构
				var applyOrgCode=$("#applyOrgCode").val();
				$("#validOrg").val(applyOrgCode);
				$("#rowIndex").val("");
				$("#tableName").val("");
				
				$("#hiddenQueryBtn").click();
				setTimeout(function(){
					//add by chy 20130916 选择按钮控制只读
					$("#selectStorageRec").attr("disabled",true);
					$('#validStorageDiv').dialog('open');
				},300);
			}
		}		
	},
	
	initEditPage:function() {

		var initData = $("#editProvideConfirm_hiddenDet").html();
		$("#DocListProvideConfirm").mulLineWidget({
			hiddenPlus: '1',		
			hiddenSubtraction: '1',			
			columnData: provideConfirm.lineDataArry,
			exeFuction: provideConfirm.resetVal,
			initJsonDate: initData
		});
				
		$("#submitButProvideConfirmEdit").click(function() {
			var vcApply = provideConfirm.getPageProvideConfirm();
			var reason = $("#backReason").val();
			
			 //  防止重复提交
			$("#submitButProvideConfirmEdit").attr("disabled",true);
			$("#backButProvideConfirmEdit").attr("disabled",true);
					      
			vcApply.applyStatus = "6";  //领用确认
			vcApply.backReason=reason;  //领用退回原因
			provideConfirm.ajaxSaveProvideConfirm(vcApply);
		});
		
		$("#backButProvideConfirmEdit").click(function() {
			var backReason = $("#backReason").val();
			//  防止重复提交
			$("#submitButProvideConfirmEdit").attr("disabled",true);
			$("#backButProvideConfirmEdit").attr("disabled",true);
			if(backReason=="" || backReason==null) {
				alert("领用退回请填写入库意见");
				$("#submitButProvideConfirmEdit").removeAttr("disabled");
				$("#backButProvideConfirmEdit").removeAttr("disabled");
				return;
			} else {
				var vcApply = provideConfirm.getPageProvideConfirm();
				vcApply.applyStatus="5";  //领用退回
				vcApply.backReason=backReason;  //领用退回原因
			}
			
			provideConfirm.ajaxSaveProvideConfirm(vcApply); 			
			
		});
		
		$("#cancelButProvideConfirmEdit").click(function() {
			$('#editPageProvideConfirm').dialog('close');
		});
	},
	
	initQueryPage:function() {
		$("#editPageProvideConfirm").css("display","none");
		$("#viewPageApply").css("display","none");
		$("#validStorageDiv").css("display","none");
		
		$("#applyStatusDropDownList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getPubCode.do?ajax=true&codeType=ApplyStatus"			
//			data:provideConfirm.applyQueryEnableStatus
		});
		
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#editPageProvideConfirm").dialog({
			modal: true,
			height:550,
			width:1050,
			autoOpen: false
		});
		
		$("#viewPageApply").dialog({
			modal: true,
			height:430,
			width:850,
			autoOpen: false
		});
		
		$("#validStorageDiv").dialog({
			modal: true,
			height:430,
			width:850,
			autoOpen: false
		});
		
		/**
		 * 
		 */
		$("#queryProvideConfirm").click( function() {
			provideConfirm.gridReload();
		});
		
		/**
		 * 发放按钮单击事件
		 */
		$("#provideConfirmUptBtn").click(function() {
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行确认入库");
				} else {
					//根据id获取行数据,返回的是列表 
					var rowDatas = $("#provideConfirmGrid").jqGrid('getRowData', id); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var row = rowDatas["applyStatus"]; 
					var rowApplyCode = rowDatas["applyCode"];
					if((4!=row)) {
						alert("所选申领单号非[等待确认]状态,不可以进行确认入库操作");
						return;
					}
					
					$("#viewPageApply").empty();
					
					//ajax获取要修改的数据
					common.ajaxGetData("editPageProvideConfirm", $.taHash.get("ctx")+"/provide/viewProvideConfirmEdit.do?id="+id+"&ajax=true",null);
					setTimeout(function(){
						$('#editPageProvideConfirm').dialog('open');
					},200);
				}
			}else{
				alert("请选择一条记录进行确认入库");
			}
		});
			
		$("#provideConfirmGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '210px',
			colNames : ['ID', '申领单号', '申请人', '申请机构','申请日期','发放人','发放机构','发放时间', '申领单状态','applyStatus'],
			colModel : [{
						name : 'id',
						index : 'id',
						width : 50,
						resizable : true,
						align : 'center'
					},{
						name : 'applyCode',
						index : 'applyCode',
						width : 200,
						resizable : true,
						align : 'left'
					},{
						name : 'applyOprName',
						index : 'applyOprName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyOrgName',
						index : 'applyOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyTime',
						index : 'applyTime',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i:s'}
					},{
						name : 'provideOprName',
						index : 'provideOprName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'provideOrgName',
						index : 'provideOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'provideTime',
						index : 'provideTime',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i:s'}
					},{
						name : 'applyStatusZh',
						index : 'applyStatusZh',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyStatus',
						index : 'applyStatus',
						hidedlg : true,
						hidden : true
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#provideConfirmGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "resultList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "id"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("provideConfirmGrid");
				$("#id").val(selectValue);
			},
			onPaging:function(pgButton) {
				$("#id").val("");
			},
			gridComplete: function(){
			    var ids = $("#provideConfirmGrid").jqGrid('getDataIDs');
			    for(var i=0;i < ids.length;i++){
			     var rowArray = $("#provideConfirmGrid").jqGrid('getRowData',ids[i]);
			     var editBtn = "<a href='#' onclick='provideConfirm.hrefView("+rowArray.id+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.applyCode+"</a>";
			     $("#provideConfirmGrid").jqGrid('setCell', ids[i], 'applyCode', editBtn);
			    }
		   }
		});
		
	},
	
	getPageProvideConfirm:function() {
	
		var vcApply = new Object();
		vcApply.id = $("#vcApply_id").val();
		vcApply.backReason = $("#backReason").val();
		return vcApply;
		
	},
	
	ajaxSaveProvideConfirm:function(vcProvide) {

		var str = JSON.stringify(vcProvide);	
		
		var params = {
           json : str
        };

		$.ajax({
             type:'post',   
             url: $.taHash.get("ctx")+"/provideJson/saveProvideConfirm.do?ajax=true",   
             dataType:'json',   
             data:params,
             success:function(msg){  
               alert(msg);
               $("#backButProvideConfirmEdit").removeAttr("disabled");
			   $("#submitButProvideConfirmEdit").removeAttr("disabled");
         	   if("操作成功"==msg) {
				$('#editPageProvideConfirm').dialog('close');
				
				$("#backButProvideConfirmEdit").removeAttr("disabled");
				$("#submitButProvideConfirmEdit").removeAttr("disabled");
				
         	   	provideConfirm.gridReload();         	   
         	   }
         	 }
         }); 	
	}, clearGridData:function(gridId) {
		$("#"+gridId).clearGridData();
		$("#id").val("");
	},	hrefView:function(id) {
		$("#editPageProvideConfirm").empty();
		
		//ajax获取要修改的数据
		common.ajaxGetData("viewPageApply", $.taHash.get("ctx")+"/apply/viewApplyView.do?id="+id+"&ajax=true",null);
		
		setTimeout(function(){
			$('#viewPageApply').dialog('open');
		},300);
	}
}