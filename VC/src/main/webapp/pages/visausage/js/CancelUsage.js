
var cancelUsage = {
	lineDataArry : [ 
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName", width:"180px",fieldClass:"required",type:"2",
					dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no",height:"210px",minLength:0}]},
		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",fieldClass:"required noBlankSpace",type:"4",maxlength:"16"},
		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",fieldClass:"required noBlankSpace",type:"4",minlength:"8",maxlength:"10"},
		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",fieldClass:"required noBlankSpace",type:"4",minlength:"8",maxlength:"10"},
		{title:"作废数量",fieldName:"cancelNum",fieldId:"cancelNum",width:"60px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"单证状态",fieldName:"docStatus",fieldId:"docStatus",width:"60px",fieldClass:"required",type:"3",
			dataFormat:[{selectedValue:"U1", data:[{"value":"U1","label":"已使用"},{"value":"U2","label":"手工已使用登记"}]}]
		}
	],
	
	lineDataArryDisabled : [ 
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName", width:"180px",fieldClass:"required",type:"2",
					dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no",height:"210px",minLength:0}],disabled:"disabled"},
		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"作废数量",fieldName:"cancelNum",fieldId:"cancelNum",width:"60px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"单证状态",fieldName:"docStatus",fieldId:"docStatus",width:"60px",fieldClass:"required",type:"3",
			dataFormat:[{selectedValue:"U1", data:[{"value":"U1","label":"已使用"},{"value":"U2","label":"手工已使用登记"}]}],
			disabled:"disabled"}
	],
	gridReload: function () {
	    $("#id").val("");
		var gridReloadUrl = $.taHash.get("ctx")+"/visaUsageJson/queryCancelUsageListByPages.do?ajax=true";
		
		var QueryCondition = new Object();
		QueryCondition.cancelCode = $("#cancelCode").val();
		QueryCondition.startDate = $("#initStartDate").val();
		QueryCondition.endDate = $("#initEndDate").val();
		QueryCondition.cancelStatus = $("#cancelStatusSelect").val();
		
		jQuery("#cancelUsageGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型 
			postData : {
				jsonStr : JSON.stringify(QueryCondition)
			},			
			page : 1
		});
		
		jQuery("#cancelUsageGrid").trigger("reloadGrid");
	},
	
	//计算单证数量
	newResetVal : function (rIndex, objId){
		if(objId == "endNum"||objId == "startNum"){
			//common.totalNum(rIndex,"cancelNum","DocListCancelUsageNew",objId);
			common.autoLengthAndTotalNum(rIndex,"cancelNum","DocListCancelUsageNew",objId,8);
		}
	},
	editResetVal : function (rIndex, objId){		
		if(objId == "endNum"||objId == "startNum"){
			//common.totalNum(rIndex,"cancelNum","DocListCancelUsageEdit",objId);
			common.autoLengthAndTotalNum(rIndex,"cancelNum","DocListCancelUsageEdit",objId,8);
		}
	},
	
	initNewPage:function() {
		$("#cancelUsageNew").validate();
		
		$("#DocListCancelUsageNew").mulLineWidget({
			columnData: cancelUsage.lineDataArry,
			exeFuction: cancelUsage.newResetVal
		});
				
		$("#saveButCancelUsageNew").click(function() {
			var rs = $("#cancelUsageNew").validate().form();
			if(rs) {
				$("#saveButCancelUsageNew").attr("disabled",true);
				$("#submitButCancelUsageNew").attr("disabled",true);
				var jsonArr = $("#DocListCancelSNew").mulLineWidget.getData();
				if(jsonArr!=null&&jsonArr!=""&&jsonArr.length>0) {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
						if(jsonArr[i].docStatus==null||jsonArr[i].docStatus=='') {
							alert('详细信息第' + (i+1)+ '行请选择单证状态');
							$("#saveButCancelUsageNew").removeAttr("disabled");
							$("#submitButCancelUsageNew").removeAttr("disabled");
							return;
						}
					}
					//双重循环判断同意单证是否流水号区间有重叠
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveButCancelUsageNew").removeAttr("disabled");
						$("#submitButCancelUsageNew").removeAttr("disabled");
						return;
					}
				} else {
					alert("请填写作废申请明细");
					$("#saveButCancelUsageNew").removeAttr("disabled");
					$("#submitButCancelUsageNew").removeAttr("disabled");
					return;
				}				
				
				var vcCancel = cancelUsage.getPageCancelNew();
				vcCancel.cancelStatus = "1";
				cancelUsage.ajaxSaveCancelUsage(vcCancel,"new","saveButCancelUsageNew");
				setTimeout(function (){
					$("#saveButCancelUsageNew").removeAttr("disabled");
					$("#submitButCancelUsageNew").removeAttr("disabled");
				},500);
			} else {
				return;
			}
		});
		
		$("#submitButCancelUsageNew").click(function() {
			var rs = $("#cancelUsageNew").validate().form();
			
			if(rs) {
				$("#saveButCancelUsageNew").attr("disabled",true);
				$("#submitButCancelUsageNew").attr("disabled",true);
				var jsonArr = $("#DocListCancelSNew").mulLineWidget.getData();
				if(jsonArr!=null&&jsonArr!=""&&jsonArr.length>0) {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
						if(jsonArr[i].docStatus==null||jsonArr[i].docStatus=='') {
							alert('详细信息第' + (i+1)+ '行请选择单证状态');
							$("#saveButCancelUsageNew").removeAttr("disabled");
							$("#submitButCancelUsageNew").removeAttr("disabled");							
							return;
						}
					}
					
					//双重循环判断同意单证是否流水号区间有重叠
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveButCancelUsageNew").removeAttr("disabled");
						$("#submitButCancelUsageNew").removeAttr("disabled");
						return;
					}					
				} else {
					alert("请填写作废申请明细");
					$("#saveButCancelUsageNew").removeAttr("disabled");
					$("#submitButCancelUsageNew").removeAttr("disabled");
					return;
				}	
				
				var vcCancel = cancelUsage.getPageCancelNew();
				vcCancel.cancelStatus = "2";
				cancelUsage.ajaxSaveCancelUsage(vcCancel,"new","submitButCancelUsageNew");
				setTimeout(function (){
					$("#saveButCancelUsageNew").removeAttr("disabled");
					$("#submitButCancelUsageNew").removeAttr("disabled");
				},500);
			} else {
				return;
			}
		});	
		
		$("#cancelButCancelUsageNew").click(function() {
			$("#saveButCancelUsageNew").removeAttr("disabled");
			$("#submitButCancelUsageNew").removeAttr("disabled");
			$('#newPageCancelUsage').dialog('close');
		});
	},	
	
	initEditPage:function() {
		$("#cancelUsageEdit").validate();
		var initJson = $("#edit_hiddenDet").html();
		
		$("#DocListCancelUsageEdit").mulLineWidget({
			columnData: cancelUsage.lineDataArry,
			exeFuction: cancelUsage.editResetVal,
			initJsonDate: initJson
		});
		
		$("#saveButCancelUsageEdit").click(function() {
			var rs = $("#cancelUsageEdit").validate().form();
			if(rs) {
				$("#saveButCancelUsageEdit").attr("disabled",true);
				$("#submitButCancelUsageEdit").attr("disabled",true);
				var jsonArr = $("#DocListCancelSEdit").mulLineWidget.getData();
				if(jsonArr!=null&&jsonArr!=""&&jsonArr.length>0) {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
						if(jsonArr[i].docStatus==null||jsonArr[i].docStatus=='') {
							alert('详细信息第' + (i+1)+ '行请选择单证状态');
							$("#saveButCancelUsageEdit").removeAttr("disabled");
							$("#submitButCancelUsageEdit").removeAttr("disabled");
							return;
						}
					}
					
					//双重循环判断同意单证是否流水号区间有重叠
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveButCancelUsageEdit").removeAttr("disabled");
						$("#submitButCancelUsageEdit").removeAttr("disabled");
						return;
					}
				} else {
					alert("请填写作废申请明细");
					$("#saveButCancelUsageEdit").removeAttr("disabled");
					$("#submitButCancelUsageEdit").removeAttr("disabled");
					return;
				}	
				
				var vcCancel = cancelUsage.getPageCancel();
				vcCancel.cancelStatus = "1";
				cancelUsage.ajaxSaveCancelUsage(vcCancel, "edit","saveButCancelUsageEdit");
				setTimeout(function (){
					$("#saveButCancelUsageEdit").removeAttr("disabled");
					$("#submitButCancelUsageEdit").removeAttr("disabled");
				},500);
			} else {
				return false;
			}
		});
		
		$("#submitButCancelUsageEdit").click(function() {
			var rs = $("#cancelUsageEdit").validate().form();
			
			if(rs) {
				$("#saveButCancelUsageEdit").attr("disabled",true);
				$("#submitButCancelUsageEdit").attr("disabled",true);
				var jsonArr = $("#DocListCancelSEdit").mulLineWidget.getData();
				if(jsonArr!=null&&jsonArr!=""&&jsonArr.length>0) {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
						if(jsonArr[i].docStatus==null||jsonArr[i].docStatus=='') {
							alert('详细信息第' + (i+1)+ '行请选择单证状态');
							$("#saveButCancelUsageEdit").removeAttr("disabled");
							$("#submitButCancelUsageEdit").removeAttr("disabled");
							return;
						}
					}
					//双重循环判断同意单证是否流水号区间有重叠
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveButCancelUsageEdit").removeAttr("disabled");
						$("#submitButCancelUsageEdit").removeAttr("disabled");
						return;
					}					
				} else {
					alert("请填写作废申请明细");
					$("#saveButCancelUsageEdit").removeAttr("disabled");
					$("#submitButCancelUsageEdit").removeAttr("disabled");
					return;
				}
				
				var vcCancel = cancelUsage.getPageCancel();
				vcCancel.cancelStatus = "2";
				cancelUsage.ajaxSaveCancelUsage(vcCancel, "edit","submitButCancelUsageEdit");
				setTimeout(function (){
					$("#saveButCancelUsageEdit").removeAttr("disabled");
					$("#submitButCancelUsageEdit").removeAttr("disabled");
				},500);
			} else {
				return false;
			}
		});	
		
		$("#cancelButCancelUsageEdit").click(function() {
			$("#saveButCancelUsageEdit").removeAttr("disabled");
			$("#submitButCancelUsageEdit").removeAttr("disabled");
			$('#editPageCancelUsage').dialog('close');
		});
	},
	
	initReadonlyPage:function() {
		var initJson = $("#readOnly_hiddenDet").html();
		$("#DocListCancelUsageReadonly").mulLineWidget({
			hiddenPlus: '1',		
			hiddenSubtraction: '1',
			columnData: cancelUsage.lineDataArryDisabled,
			initJsonDate: initJson
		});		
		$("#cancelButCancelUsageReadoly").click(function() {
			$('#readOnlyPageCancelUsage').dialog('close');
		});
	},	
	
	cancelQueryEnableStatus : [
		{"label":"已删除", "value":"0"},
		{"label":"新建", "value":"1"},
		{"label":"待审批", "value":"2"},
		{"label":"审批退回", "value":"3"},
		{"label":"审批通过", "value":"4"}
	],
	
	initQueryPage:function() {
		$("#newPageCancelUsage").css("display","none");
		$("#editPageCancelUsage").css("display","none");
		$("#readOnlyPageCancelUsage").css("display","none");
		
		$("#cancelStatusSelect").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getPubCode.do?ajax=true&codeType=CancelStatus"				
//			data:cancelUsage.cancelQueryEnableStatus
		});
		
		$("#newPageCancelUsage").dialog({
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
		$("#editPageCancelUsage").dialog({
			modal: true,
			height:550,
			width:1000,
			autoOpen: false
		});
		
		$("#readOnlyPageCancelUsage").dialog({
			modal: true,
			height:550,
			width:1000,
			autoOpen: false
		});
		
		$("#cancelUsageNewBtn").click(function() {
			$("#editPageCancelUsage").empty();
			$("#readOnlyPageCancelUsage").empty();			
			
			common.ajaxGetData("newPageCancelUsage", $.taHash.get("ctx")+"/visaUsage/viewCancelUsageNew.do?ajax=true",null);
			
			$('#newPageCancelUsage').dialog('open');
		});
		
		/**
		 * 
		 */
		$("#queryCancelUsage").click( function() {
			cancelUsage.gridReload();
		});
		
		$("#cancelUsageViewBtn").click(function() {
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行浏览");
				} else {
					$("#newPageCancelUsage").empty();
					$("#editPageCancelUsage").empty();
					
					//ajax获取要修改的数据
					common.ajaxGetData("readOnlyPageCancelUsage", $.taHash.get("ctx")+"/visaUsage/viewCancelUsageReadonly.do?id="+id+"&ajax=true",null);
					//调用dialog组件
					
					
					$('#readOnlyPageCancelUsage').dialog('open');
				}
			}else{
				alert("请选择一条记录进行浏览");
			}
		});
		
		/**
		 * 发放按钮单击事件
		 */
		$("#cancelUsageUptBtn").click(function() {
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行修改");
				} else {
					
					var rowDatas = $("#cancelUsageGrid").jqGrid('getRowData', id); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var rowStatus = rowDatas["cancelStatus"];
					var rowCancelCode = rowDatas["cancelCode"];
					if((1!=rowStatus)&&(3!=rowStatus)) {
						alert("所选作废单号非[暂存]或者[审批退回]状态,不可以进行修改操作");
						return;
					}	
					
					$("#newPageCancelUsage").empty();
					$("#readOnlyPageCancelUsage").empty();					
					
					//ajax获取要修改的数据
					common.ajaxGetData("editPageCancelUsage", $.taHash.get("ctx")+"/visaUsage/viewCancelUsageEdit.do?id="+id+"&ajax=true",null);
					
					$('#editPageCancelUsage').dialog('open');
				}
			}else{
				alert("请选择一条记录进行修改");
			}
		});
			
		$("#cancelUsageGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '210px',
			colNames : ['ID','作废单号','申请机构','申请人','申请时间','申请单状态','cancelStatus'],
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
					}, {
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
			pager : '#cancelUsageGridPager',
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
				var selectValue = common.selectRowValue("cancelUsageGrid");
				$("#id").val(selectValue);
			},
			onPaging:function(pgButton) {
				$("#id").val("");
			},
			gridComplete: function(){
			    var ids = $("#cancelUsageGrid").jqGrid('getDataIDs');
			    for(var i=0;i < ids.length;i++){
			     var rowArray = $("#cancelUsageGrid").jqGrid('getRowData',ids[i]);
			     var editBtn = "<a href='#' onclick='cancelUsage.hrefView("+rowArray.idVcCancel+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.cancelCode+"</a>";
			     $("#cancelUsageGrid").jqGrid('setCell', ids[i], 'cancelCode', editBtn);
			    }
		   }
		});
		
		$("#cancelUsageSubmitBtn").click(function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			var action = $.taHash.get("ctx")+"/visaUsageJson/submitCancelUsageList.do?ajax=true";
			if("" != id){
				//ajax获取要修改的数据
				var idArr = id.split(",");
				for(var i=0; i<idArr.length;i++) {
					var rowDatas = $("#cancelUsageGrid").jqGrid('getRowData', idArr[i]); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var rowStatus = rowDatas["cancelStatus"];
					var rowCancelCode = rowDatas["cancelCode"];
					if((1!=rowStatus)&&(3!=rowStatus)) {
						alert("所选作废单号非[暂存]或者[审批退回]状态,不可以进行提交操作");
						return;
					}				
				}				
				var param = {"jsonStr":id};
				common.ajaxSubmitDfCallBack(cancelUsage.callBack, action, param);
			}else{
				alert("请选择记录进行提交");
			}			
		});
		
		$("#cancelUsageDelBtn").click(function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			var action = $.taHash.get("ctx")+"/visaUsageJson/deleteCancelList.do?ajax=true";
			if("" != id){
				//ajax获取要修改的数据
				var idArr = id.split(",");
				for(var i=0; i<idArr.length;i++) {
					var rowDatas = $("#cancelUsageGrid").jqGrid('getRowData', idArr[i]); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var rowStatus = rowDatas["cancelStatus"];
					var rowCancelCode = rowDatas["cancelCode"];
					if((1!=rowStatus)&&(3!=rowStatus)) {
						alert("所选作废单号非[暂存]或者[审批退回]状态,不可以进行删除操作");
						return;
					}				
				}		
				
				if(confirm("确定删除已选记录?")) {
					var param = {"jsonStr":id};
					common.ajaxSubmitDfCallBack(cancelUsage.callBack, action, param);
				}
			}else{
				alert("请选择记录进行删除");
			}				
		});
		
	},
	
	getPageCancelNew:function() {
		var jsonArr = $("#DocListCancelSNew").mulLineWidget.getData();
		var vcCancelDets = jsonArr;
		
		var vcCancel = new Object();
		
		vcCancel.vcCancelDets = vcCancelDets;
		vcCancel.cancelTime=$("#new_cancelTime").val();
		vcCancel.cancelReason = $("#new_cancelReason").html();
		
		return vcCancel;
	},	
	getPageCancel:function() {
		var jsonArr = $("#DocListCancelSEdit").mulLineWidget.getData();
		var vcCancelDets = jsonArr;
		var vcCancel = new Object();
		vcCancel.vcCancelDets = vcCancelDets;
		vcCancel.idVcCancel = $("#idVcCancel").val();
		vcCancel.cancelTime=$("#edit_cancelTime").val();
		vcCancel.cancelReason = $("#edit_cancelReason").html();
		return vcCancel;
	},
	
	ajaxSaveCancelUsage:function(vcCancelUsage,type,butId) {
		var str = JSON.stringify(vcCancelUsage);	
		
		var params = {
           jsonStr : str
        };
	//	common.ajaxSubmitAndCloseDialog("editPageCancelUsage", $.taHash.get("ctx")+"/visaUsageJson/saveCancelUsage.do?ajax=true", params)
		$.ajax({   
             type:'post',   
             url: $.taHash.get("ctx")+"/visaUsageJson/saveCancelUsage.do?ajax=true",   
             dataType:'json',   
             data:params,
             success:function(msg){ 
             	alert(msg);
             	if("操作成功"==msg) {
             		$("#"+butId).removeAttr("disabled");
					if("new"==type) {
	             		$('#newPageCancelUsage').dialog('close');
	             	}	
	             	if("edit"==type) {
	             		$('#editPageCancelUsage').dialog('close');
	             	}             	
             	}
         	 }
         });
	},
	callBack:function(data) {
		 alert(data);
		 cancelUsage.gridReload();
	},
	hrefView:function(id) {
		$("#newPageCancelUsage").empty();
		$("#editPageCancelUsage").empty();
		
		//ajax获取要修改的数据
		common.ajaxGetData("readOnlyPageCancelUsage", $.taHash.get("ctx")+"/visaUsage/viewCancelUsageReadonly.do?id="+id+"&ajax=true",null);
		//调用dialog组件
		
		
		$('#readOnlyPageCancelUsage').dialog('open');
	},
	clearGridData:function(gridId) {
		$("#"+gridId).clearGridData(); 
		$("#id").val("");
	}
}