var recycle = {
	
	lineDataArry : [ 
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName",width:"180px",fieldClass:"required",type:"2",
					dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no", height:"210px", width:'300px',minLength:0}]},
		// source:"/visa/baseinfoJson/autoGetVcDocVerLabels.do?ajax=true
		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",fieldClass:"required noBlankSpace",type:"4"},
		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",fieldClass:"required noBlankSpace",type:"4",minlength:"8",maxlength:"10"},
		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",fieldClass:"required noBlankSpace",type:"4",minlength:"8",maxlength:"10"},
		{title:"回收数量",fieldName:"recycleNum",fieldId:"recycleNum",width:"50px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"单证状态",fieldName:"docStatus",fieldId:"docStatus",width:"50px",fieldClass:"required",type:"3",disabled:"disabled",
			dataFormat:[{selectedValue:"S2", data:[{"value":"S1","label":"印刷入库"},{"value":"S2","label":"申领入库"},{"value":"S3","label":"回收入库"}]}]
		},
		{title:"操作",fieldId:"edit",fieldName:"edit",fieldVal:"查看", type:"8",width:"100px"}
	],
	
	lineDataArryDisabled : [
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName",width:"180px",fieldClass:"required",type:"2",
					dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no", height:'100px', width:'300px',minLength:0}],disabled:"disabled"},
		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"回收数量",fieldName:"recycleNum",fieldId:"recycleNum",width:"50px",fieldClass:"required",type:"4",disabled:"disabled"},
		{title:"单证状态",fieldName:"docStatus",fieldId:"docStatus",width:"50px",fieldClass:"required",type:"3",
			dataFormat:[{selectedValue:"S2", data:[{"value":"S1","label":"印刷入库"},{"value":"S2","label":"申领/调拨入库"},{"value":"S3","label":"回收入库"}]}],
		disabled:"disabled"}
	],
		
	gridReload: function () {
		var gridReloadUrl = $.taHash.get("ctx")+"/recycleJson/queryRecycleListByPages.do?ajax=true";
		
		var QueryCondition = new Object();
		QueryCondition.queryCode = $("#recycleCode").val();
		QueryCondition.startDate = $("#initStartDate").val();
		QueryCondition.endDate = $("#initEndDate").val();
		QueryCondition.queryStatus = $("#recycleStatusSelect").val();
		
		jQuery("#recycleGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型 
			postData : {
				jsonStr : JSON.stringify(QueryCondition)
			},				
			page : 1
		});
		
		jQuery("#recycleGrid").trigger("reloadGrid");
		
		$("#id").val("");
	},
	
	resetValNew : function (rIndex, objId){
		/**
		 * @description 更改字段值
		 * @param  参数1(org） 要更改值的标签Id
		 * @param  参数2(2） 要更改值的标签value
		 * 
		 */
		if(objId == "startNum" || objId == "endNum"){
		   // common.totalNum(rIndex,"recycleNum","DocListRecycleNew",objId);
		    common.autoLengthAndTotalNum(rIndex,"recycleNum","DocListRecycleNew",objId,8);
			/*
			var jsonArr = $("#DocListRecycleNew").mulLineWidget.getData();
			var startNum = jsonArr[rIndex-1].startNum ;
			var endNum = jsonArr[rIndex-1].endNum;
			if(startNum!="" && endNum!=""){
			   alert(endNum);
			   alert(startNum);
			   if(endNum<startNum) {
			    alert("终止流水号要大于起始流水号");
			    $("#DocListRecycleNew").mulLineWidget.resetVal(rIndex, "recycleNum", null);
			    return;
			  }else{
			   $("#DocListRecycleNew").mulLineWidget.resetVal(rIndex, "recycleNum", (endNum-startNum)+1);
			   }
			   }*/
			   
		}
		 
		if(objId == "edit"){
			var jsonArr = $("#DocListRecycleNew").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			
			if(docVerCode==null ||docVerCode=="") {
				alert("请选择单证类型");
				return;
			} else {
				$("#validStorageQuery_docVerCode").val(docVerCode);
				//add by chy 20130916
				$("#validOrg").val("");
				$("#rowIndex").val(rIndex);
				$("#tableName").val("DocListRecycleNew");
				
				$("#hiddenQueryBtn").click();
				setTimeout(function(){
					//add by chy 20130916
					$("#selectStorageRec").removeAttr("disabled");
					$('#validStorageDiv').dialog('open');
				},300);
			}
		}		
	},		
	
	initNewPage:function() {
		
		$("#recycleNew").validate();
		
		$("#DocListRecycleNew").mulLineWidget({
			exeFuction: recycle.resetValNew,
			columnData: recycle.lineDataArry
		});
		
		$("#saveButRecycleNew").click(function() {
			/*
			var idVcDocVersionInfoArray = $("input[name='idVcDocVersionInfo']");
			var isRepeat = storeCommon.isRepeat("单证版本号重复", idVcDocVersionInfoArray);
			
			if(isRepeat) {
				return false;
			}
			*/	
			
			var valid = $("#recycleNew").validate().form();

			if(valid) {
				 //  防止重复提交
			    $("#saveButRecycleNew").attr("disabled",true);
			    $("#submitButRecycleNew").attr("disabled",true);
				var jsonArr = $("#DocListRecycleNew").mulLineWidget.getData();
				if(jsonArr==null||jsonArr.length<=0) {
					alert("请输入明细");
					$("#saveButRecycleNew").removeAttr("disabled");
					$("#submitButRecycleNew").removeAttr("disabled");
					return;
				} else {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
						if(jsonArr[i].docStatus==null||jsonArr[i].docStatus=='') {
							alert('详细信息第' + (i+1)+ '行请选择单证状态');
							$("#saveButRecycleNew").removeAttr("disabled");
							$("#submitButRecycleNew").removeAttr("disabled");
							return;
						}
						var startNum=jsonArr[i].startNum;
						var endNum=jsonArr[i].endNum;
						if(parseFloat(startNum)>parseFloat(endNum)){
							  alert('单证【'+jsonArr[i].docVerCode+'】起始流水大于终止流水！');
							 return false;
						}
					}
					/*
					if(common.isRepeat("回收申请明细存在重复单证类型",docVerCodeArr)) {
					$("#saveButRecycleNew").removeAttr("disabled");
					$("#submitButRecycleNew").removeAttr("disabled");
						return;
					}*/
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveButRecycleNew").removeAttr("disabled");
					    $("#submitButRecycleNew").removeAttr("disabled");
						return;
					}
					
				}
				
				
			
				var vcRecycle = recycle.getPageRecycleNew();
				vcRecycle.recycleStatus=statusValues.VC_RECYCLE_STATUS_TEMPSAVE;
			
				recycle.ajaxSaveRecycle(vcRecycle, "new");
				recycle.gridReload();
			} else {
				return;
			}			
			
		});
		
		$("#submitButRecycleNew").click(function() {
			/*
			var idVcDocVersionInfoArray = $("input[name='idVcDocVersionInfo']");
			var isRepeat = storeCommon.isRepeat("单证版本号重复", idVcDocVersionInfoArray);
			
			if(isRepeat) {
				return false;
			}
			*/			
			var valid = $("#recycleNew").validate().form();

			if(valid) {
			
				
				 //  防止重复提交
			   $("#submitButRecycleNew").attr("disabled",true);
			   $("#saveButRecycleNew").attr("disabled",true);
			   
				var jsonArr = $("#DocListRecycleNew").mulLineWidget.getData();
				if(jsonArr==null||jsonArr.length<=0) {
					alert("请输入明细");
					 $("#submitButRecycleNew").removeAttr("disabled");
					 $("#saveButRecycleNew").removeAttr("disabled");
					return;
				} else {

					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
						if(jsonArr[i].docStatus==null||jsonArr[i].docStatus=='') {
							alert('详细信息第' + (i+1)+ '行请选择单证状态');
							 $("#submitButRecycleNew").removeAttr("disabled");
							 $("#saveButRecycleNew").removeAttr("disabled");
							return;
						}						
					}
					/*
					if(common.isRepeat("回收申请明细存在重复单证类型",docVerCodeArr)) {
					 $("#submitButRecycleNew").removeAttr("disabled");
					 $("#saveButRecycleNew").removeAttr("disabled");
						return;
					}*/
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveButRecycleNew").removeAttr("disabled");
					    $("#submitButRecycleNew").removeAttr("disabled");
						return;
					}
								
				}
				
				
			  
				var vcRecycle = recycle.getPageRecycleNew();
				vcRecycle.recycleStatus=statusValues.VC_RECYCLE_STATUS_AWAITCONFIRM;
				
				recycle.ajaxSaveRecycle(vcRecycle, "new");
				recycle.gridReload();
			} else {
				return;
			}				
		});
		
		$("#cancelButRecycleNew").click(function() {
			$('#newPageRecycle').dialog('close');
		});		
	},	
	
	resetVal : function (rIndex, objId){
		/**
		 * @description 更改字段值
		 * @param  参数1(org） 要更改值的标签Id
		 * @param  参数2(2） 要更改值的标签value
		 * 
		 */
		if(objId == "edit"){
			var jsonArr = $("#DocListRecycle").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			
			if(docVerCode==null ||docVerCode=="") {
				alert("请选择单证类型");
				return;
			} else {
				$("#validStorageQuery_docVerCode").val(docVerCode);
				//add by chy 20130916
				$("#validOrg").val("");
				$("#rowIndex").val(rIndex);
				$("#tableName").val("DocListRecycleNew");
				
				$("#hiddenQueryBtn").click();
				setTimeout(function(){
					//add by chy 20130916
					$("#selectStorageRec").removeAttr("disabled");
					$('#validStorageDiv').dialog('open');
				},300);
			}
		}		
	},	
	
	initEditPage:function() {
		var initData = $("#editRecycle_hiddenDet").html();
		$("#recycleEdit").validate();
		
//		setTimeout(function(){
		$("#DocListRecycle").mulLineWidget({
			columnData: recycle.lineDataArry,
			exeFuction: recycle.resetVal,
			initJsonDate: initData
		  });
//		 }, 500);		
		
		$("#saveButRecycleEdit").click(function() {
			/*var idVcDocVersionInfoArray = $("input[name='idVcDocVersionInfo']");
			var isRepeat = storeCommon.isRepeat("单证版本号重复", idVcDocVersionInfoArray);
			
			if(isRepeat) {
				return false;
			}
			*/	
			
			var valid = $("#recycleEdit").validate().form();

			if(valid) {
			  $("#saveButRecycleEdit").attr("disabled",true);
			   $("#submitButRecycleEdit").attr("disabled",true);
				var jsonArr = $("#DocListRecycle").mulLineWidget.getData();
				if(jsonArr==null||jsonArr.length<=0) {
					alert("请输入明细");
					 $("#saveButRecycleEdit").removeAttr("disabled");
					 $("#submitButRecycleEdit").removeAttr("disabled");
					return;
				}else {

					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
						if(jsonArr[i].docStatus==null||jsonArr[i].docStatus=='') {
							alert('详细信息第' + (i+1)+ '行请选择单证状态');
							 $("#saveButRecycleEdit").removeAttr("disabled");
							 $("#submitButRecycleEdit").removeAttr("disabled");
							return;
						}						
					}
					/*
					if(common.isRepeat("回收申请明细存在重复单证类型",docVerCodeArr)) {
					   $("#saveButRecycleEdit").removeAttr("disabled");
					   $("#submitButRecycleEdit").removeAttr("disabled");
						return;
					}*/
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveButRecycleEdit").removeAttr("disabled");
					    $("#submitButRecycleEdit").removeAttr("disabled");
						return;
					}
								
				}
				
				
			   
				var vcRecycle = recycle.getPageRecycle();
				vcRecycle.recycleStatus=statusValues.VC_RECYCLE_STATUS_TEMPSAVE;
				
				recycle.ajaxSaveRecycle(vcRecycle, "edit");
				recycle.gridReload();
			} else {
				return;
			}					
			
		});
		
		$("#submitButRecycleEdit").click(function() {
			/*
			var idVcDocVersionInfoArray = $("input[name='idVcDocVersionInfo']");
			var isRepeat = storeCommon.isRepeat("单证版本号重复", idVcDocVersionInfoArray);
			
			if(isRepeat) {
				return false;
			}
			*/			
			
			var valid = $("#recycleEdit").validate().form();

			if(valid) {
			
			   $("#submitButRecycleEdit").attr("disabled",true);
			   $("#saveButRecycleEdit").attr("disabled",true);
			
				var jsonArr = $("#DocListRecycle").mulLineWidget.getData();
				if(jsonArr==null||jsonArr.length<=0) {
					alert("请输入明细");
					 $("#submitButRecycleEdit").removeAttr("disabled");
					 $("#saveButRecycleEdit").removeAttr("disabled");
					return;
				}else {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
						if(jsonArr[i].docStatus==null||jsonArr[i].docStatus=='') {
							alert('详细信息第' + (i+1)+ '行请选择单证状态');
							 $("#submitButRecycleEdit").removeAttr("disabled");
							 $("#saveButRecycleEdit").removeAttr("disabled");
							return;
						}						
					}
					/*
					if(common.isRepeat("回收申请明细存在重复单证类型",docVerCodeArr)) {
					 $("#submitButRecycleEdit").removeAttr("disabled");
					 $("#saveButRecycleEdit").removeAttr("disabled");
						return;
					}*/
					
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveButRecycleEdit").removeAttr("disabled");
					    $("#submitButRecycleEdit").removeAttr("disabled");
						return;
					}
					
				}
				
			   
				var vcRecycle = recycle.getPageRecycle();
				vcRecycle.recycleStatus=statusValues.VC_RECYCLE_STATUS_AWAITCONFIRM;
				
				recycle.ajaxSaveRecycle(vcRecycle, "edit");
				recycle.gridReload();
			} else {
				return;
			}	
		});		
		
		$("#cancelButRecycleEdit").click(function() {
			$('#editPageRecycle').dialog('close');
		});
		
	},
	
	initReadonlyPage:function() {
		var initData = $("#readOnlyRecycle_hiddenDet").html();
		
//		setTimeout(function(){
		$("#DocListRecycleReadOnly").mulLineWidget({
			hiddenPlus: '1',		
			hiddenSubtraction: '1',			
			columnData: recycle.lineDataArryDisabled,
			initJsonDate: initData
		  });
//		 }, 500);	
		  
		  $("#backButRecycleReadOnly").click(function () {
		  	$("#readOnlyPageRecycle").dialog("close");
		  });
	},	
	
	recycleQueryEnableStatus : [
		{"label":"已删除", "value":"0"},
		{"label":"新建", "value":"1"},
		{"label":"待审批", "value":"2"},
		{"label":"审批退回", "value":"3"},
		{"label":"审批通过", "value":"4"}
	],
	
	initQueryPage:function() {
		$("#newPageRecycle").css("display","none");
		
		$("#editPageRecycle").css("display","none");
		
		$("#readOnlyPageRecycle").css("display","none");
		
		$("#validStorageDiv").css("display","none");
		
		
		$("#recycleStatusSelect").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getPubCode.do?ajax=true&codeType=RecycleStatus"			
//			data:recycle.recycleQueryEnableStatus
		});
		
		$("#newPageRecycle").dialog({
			modal: true,
			height:550,
			width:950,
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
		$("#editPageRecycle").dialog({
			modal: true,
			height:550,
			width:950,
			autoOpen: false
		});
		
		$("#readOnlyPageRecycle").dialog({
			modal: true,
			height:550,
			width:950,
			autoOpen: false
		});		
		
		$("#validStorageDiv").dialog({
			modal: true,
			height:430,
			width:850,
			autoOpen: false
		});
		
		$("#recycleNewBtn").click(function() {
			$("#newPageRecycle").empty();
			$("#editPageRecycle").empty();
			$("#readOnlyPageRecycle").empty();
			
			common.ajaxGetData("newPageRecycle", $.taHash.get("ctx")+"/recycle/viewRecycleNew.do?&ajax=true",null);
			
//			setTimeout(function(){
			$('#newPageRecycle').dialog('open');
//			},500);
		});
		
		/**
		 * 
		 */
		$("#queryRecycle").click( function() {
			recycle.gridReload();
		});
		
		$("#recycleSubmitBtn").click(function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			var action = $.taHash.get("ctx")+"/recycleJson/submitRecycleList.do?ajax=true";
			if("" != id){
				//ajax获取要修改的数据
				var idArr = id.split(",");
				for(var i=0; i<idArr.length;i++) {
					var rowDatas = $("#recycleGrid").jqGrid('getRowData', idArr[i]); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var rowStatus = rowDatas["recycleStatus"];
					var rowRecycleCode = rowDatas["recycleCode"];
					if((1!=rowStatus)&&(3!=rowStatus)) {
						alert("所选回收单号非[暂存]或者[审批退回]状态,不可以进行提交操作");
						return;
					}				
				}
				
				$("#newPageRecycle").empty();
				$("#editPageRecycle").empty();
				$("#readOnlyPageRecycle").empty();
				
				var param = {"jsonStr":id};
				common.ajaxSubmitDfCallBack(recycle.callBack, action, param);
			}else{
				alert("请选择记录进行提交");
			}					
		});
		
		$("#recycleDelBtn").click(function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			var action = $.taHash.get("ctx")+"/recycleJson/deleteRecycleList.do?ajax=true";
			if("" != id){
				//ajax获取要修改的数据
				var idArr = id.split(",");
				for(var i=0; i<idArr.length;i++) {
					var rowDatas = $("#recycleGrid").jqGrid('getRowData', idArr[i]); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var rowStatus = rowDatas["recycleStatus"];
					var rowRecycleCode = rowDatas["recycleCode"];
					if((1!=rowStatus)&&(3!=rowStatus)) {
						alert("所选回收单号非[暂存]或者[审批退回]状态,不可以进行删除操作");
						return;
					}				
				}
				
				$("#newPageRecycle").empty();
				$("#editPageRecycle").empty();
				$("#readOnlyPageRecycle").empty();
				
				if(confirm("确定删除已选记录?")) {
					var param = {"jsonStr":id};
					common.ajaxSubmitDfCallBack(recycle.callBack, action, param);
				}
			}else{
				alert("请选择记录进行删除");
			}			
		});
		
		$("#recycleViewBtn").click(function() {

			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行浏览");
				} else {
					$("#newPageRecycle").empty();
					$("#editPageRecycle").empty();
					$("#readOnlyPageRecycle").empty();
					
					//ajax获取要修改的数据
					common.ajaxGetData("readOnlyPageRecycle", $.taHash.get("ctx")+"/recycle/viewRecycleReadonly.do?id="+id+"&ajax=true",null);
					
					setTimeout(function(){
						$('#readOnlyPageRecycle').dialog('open');
					},200);
				}
			}else{
				alert("请选择一条记录进行浏览");
			}
					
		});
		
		/**
		 * 发放按钮单击事件
		 */
		$("#recycleUptBtn").click(function() {
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行修改");
				} else {
					var rowDatas = $("#recycleGrid").jqGrid('getRowData', id); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var rowStatus = rowDatas["recycleStatus"];
					var rowRecycleCode = rowDatas["recycleCode"];
					if((1!=rowStatus)&&(3!=rowStatus)) {
						alert("所选回收单号非[暂存]或者[审批退回]状态,不可以进行修改操作");
						return;
					}	
					
					$("#newPageRecycle").empty();
					$("#editPageRecycle").empty();
					$("#readOnlyPageRecycle").empty();
					
					//ajax获取要修改的数据
					common.ajaxGetData("editPageRecycle", $.taHash.get("ctx")+"/recycle/viewRecycleEdit.do?id="+id+"&ajax=true",null);
					
					setTimeout(function(){
						$('#editPageRecycle').dialog('open');
					},200);
				}
			}else{
				alert("请选择一条记录进行修改");
			}
		});
			
		$("#recycleGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '210px',
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
			pager : '#recycleGridPager',
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
				var selectValue = common.selectRowValue("recycleGrid");
				$("#id").val(selectValue);
			}, 
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#id").val("");
				}else{//全部选中时
					var ids = $("#recycleGrid").jqGrid('getDataIDs');
					$("#id").val(ids);
				}
			},
			onPaging:function(pgButton) {
				$("#id").val("");
			},
			gridComplete: function(){
			    var ids = $("#recycleGrid").jqGrid('getDataIDs');
			    for(var i=0;i < ids.length;i++){
			     var rowArray = $("#recycleGrid").jqGrid('getRowData',ids[i]);
			     var editBtn = "<a href='#' onclick='recycle.hrefView("+rowArray.idVcRecycle+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.recycleCode+"</a>";
			     $("#recycleGrid").jqGrid('setCell', ids[i], 'recycleCode', editBtn);
			    }
		   }
		});
		
	},
	getPageRecycleNew:function() {
		var jsonArr = $("#DocListRecycleNew").mulLineWidget.getData();
		var vcRecycleDets = jsonArr;
		
		var vcRecycle = new Object();
		vcRecycle.vcRecycleDets = vcRecycleDets;
		vcRecycle.recycleOprCode = $("#new_recycleOprCode").val();
		vcRecycle.recycleOrgCode = $("#new_recycleOrgCode").val();
		vcRecycle.recycleReason = $("#new_recycleReason").val();
		return vcRecycle;
	},
	
	getPageRecycle:function() {
		
		var jsonArr = $("#DocListRecycle").mulLineWidget.getData();
		var vcRecycleDets = jsonArr;
		var vcRecycle = new Object();
		
		vcRecycle.vcRecycleDets = vcRecycleDets;
		vcRecycle.idVcRecycle = $("#vcRecycle_idVcRecycle").val();
		vcRecycle.recycleOprCode = $("#edit_recycleOprCode").val();
		vcRecycle.recycleOrgCode = $("#edit_recycleOrgCode").val();
		vcRecycle.recycleReason = $("#edit_recycleReason").html();
		return vcRecycle;
	},
	
	ajaxSaveRecycle:function(vcRecycle, type) {

		var str = JSON.stringify(vcRecycle);	
		var params = {
           jsonStr : str
        };
        
        setTimeout(function(){
			         $("#saveButRecycleNew").removeAttr("disabled");
	         	     $("#submitButRecycleNew").removeAttr("disabled");
	         	     $("#saveButRecycleEdit").removeAttr("disabled");
	         	     $("#submitButRecycleEdit").removeAttr("disabled");
		},800);
		
		$.ajax({   
             type:'post',   
             url: $.taHash.get("ctx")+"/recycleJson/saveRecycle.do?ajax=true",   
             dataType:'json',   
             data:params,
             success:function(msg){   
             	alert(msg);
             	     $("#saveButRecycleNew").removeAttr("disabled");
	         	     $("#submitButRecycleNew").removeAttr("disabled");
	         	     $("#saveButRecycleEdit").removeAttr("disabled");
	         	     $("#submitButRecycleEdit").removeAttr("disabled");
	         	     
         	   	if("操作成功"==msg) {
					if("new" == type) {
	         	   		$('#newPageRecycle').dialog('close');
	         	    }
	         	   
	         	    if("edit" == type) {
						$('#editPageRecycle').dialog('close');
	         	    }  
	         	    
	         	     $("#saveButRecycleNew").removeAttr("disabled");
	         	     $("#submitButRecycleNew").removeAttr("disabled");
	         	     $("#saveButRecycleEdit").removeAttr("disabled");
	         	     $("#submitButRecycleEdit").removeAttr("disabled");
	         	      
	         	      
	         	    recycle.gridReload();
         	   	}
         	 }
         }); 	
	},
	callBack:function(data) {
		 alert(data);
		 recycle.gridReload();
	},
	clearGridData:function(gridId) {
		$("#"+gridId).clearGridData(); 
		$("#id").val("");
	},
	hrefView:function(id) {
		$("#newPageRecycle").empty();
		$("#editPageRecycle").empty();
		
		common.ajaxGetData("readOnlyPageRecycle", $.taHash.get("ctx")+"/recycle/viewRecycleReadonly.do?id="+id+"&ajax=true",null);
		
		setTimeout(function(){
			$('#readOnlyPageRecycle').dialog('open');
		},200);
	}
}