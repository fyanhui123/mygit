var apply = {
	/**
	 * 
	 */
		  lineDataArry : [
		          		{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldLableName:"docVerCodeName",fieldClass:"required", type:"2", width:"180px", 
		          			dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no",height:"210px",minLength:0}]},
		          		{title:"调拨数量",fieldId:"applyNum",fieldName:"applyNum",fieldClass:"required digits",width:"100px", type:"4"},
		          		{title:"调出机构有效库存",fieldId:"validNum",fieldName:"validNum",fieldClass:"digits",width:"170px", type:"4",disabled:"disabled"},
		          		{title:"操作",fieldId:"edit",fieldName:"edit",fieldVal:"查看", type:"8",width:"100px"}
		          	],
	/**
	 * 
	 */ 
	lineDataArryDisabled : [
		{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldLableName:"docVerCodeName",type:"2", width:"180px", 
						dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no",height:"210px",minLength:0}],disabled:"disabled"},
		{title:"印刷批次",fieldId:"pressBatchNo",fieldName:"pressBatchNo",width:"100px",type:"4", disabled:"disabled"},
		{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",width:"100px",type:"4",disabled:"disabled"},
		{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",width:"100px",type:"4",disabled:"disabled"},
		{title:"申请数量",fieldId:"applyNum",fieldName:"applyNum",fieldClass:"required digits",width:"100px", type:"4",disabled:"disabled",maxLength:10},
		{title:"有效库存",fieldId:"validNum",fieldName:"validNum",fieldClass:"",width:"100px", type:"4",disabled:"disabled"},
		{title:"操作",fieldId:"edit",fieldName:"edit",fieldVal:"查看", type:"8",width:"40px"}
	],
	//申领单处理状态（0删除/1新建/2等待发放/3发放退回/4等待确认/6已确认/5确认退回）
	/**
	 * 下拉框的数据
	 */
	allotQueryEnableStatus : [
		{"label":"已删除", "value":"0"},
		{"label":"新建", "value":"1"},
		{"label":"等待发放", "value":"2"},
		{"label":"发放退回", "value":"3"},
		{"label":"等待确认", "value":"4"},
		{"label":"确认退回", "value":"5"},
		{"label":"已确认", "value":"6"}
	],
	
	
	/**
	 * 初始化新增页面
	 */
	initApplyNew: function() {
		$("#DocListAllotNew").mulLineWidget({
			columnData: apply.lineDataArry,
			exeFuction: apply.resetVal
			
		});
		
		$("#backButApplyNew").click(function() {
			$('#newPageAllot').dialog('close');
		});
		
		$("#applyNew").validate();
		$("#allotListDtoOrgCode").dropDownDivList({	
			//source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&minLevel=4&tagCodeId=allotListDtoOrgCode&tagNameId=allotListDtoOrgCodeName"
			//source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&minLevel=4&tagCodeId=roleListDtoOrgCode&tagNameId=allotListDtoOrgCodeName"
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=allotListDtoOrgCode&tagNameId=allotInquiryVoOrgCodeName&pageTableName=allotListDtoOrgCodeName"
		});
		
		//fyh
		$("#saveButAllotNew").click(function() {
			var valid = $("#applyNew").validate().form();
			if(valid) {
			    //禁用按钮
				$("#saveButAllotNew").attr("disabled",true);
				 $("#submitButAllotNew").attr("disabled",true);
				var jsonArr = $("#DocListAllotNew").mulLineWidget.getData();
				if(jsonArr==null||jsonArr.length<=0) {
					alert("请输入明细");
					$("#saveButAllotNew").removeAttr("disabled");
					 $("#submitButAllotNew").removeAttr("disabled");
					return;
				}else {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
					}
					if(common.isRepeat("单证申领申请明细存在重复单证类型",docVerCodeArr)) {
					 $("#saveButAllotNew").removeAttr("disabled");
					  $("#submitButAllotNew").removeAttr("disabled");
						return;
					}				
				}
			
			// 校验申请数量长度
			var jsonArr = $("#DocListAllotNew").mulLineWidget.getData();
			 for(var j=0;j<jsonArr.length;j++){
			     var allotNumLen=jsonArr[j].applyNum;
			     if(allotNumLen.length>7){
			       alert("输入的申请数量长度不能超过7！");
			       $("#saveButAllotNew").removeAttr("disabled");
			       $("#submitButAllotNew").removeAttr("disabled");
			       return ;
			     }	
			 }	
				var vcAllot = apply.getPageApplyNew();
				vcAllot.allotStatus = statusValues.VC_APPLY_STATUS_APPLY_TEMPSAVE;  //暂存状态
				apply.ajaxSave(vcAllot,"new");
				apply.gridReload();
			} else {
				return;
			}

		});
		$("#submitButAllotNew").click(function() {
			var valid = $("#applyNew").validate().form();
			if(valid) {
		    	// 防止重复提交
			  $("#submitButAllotNew").attr("disabled",true);
			  $("#saveButAllotNew").attr("disabled",true);
			   
				var jsonArr = $("#DocListAllotNew").mulLineWidget.getData();
				if(jsonArr==null||jsonArr.length<=0) {
					  alert("请输入明细");
					 $("#submitButAllotNew").removeAttr("disabled");
					  $("#saveButAllotNew").removeAttr("disabled");
					return;
				} else {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
					}
					if(common.isRepeat("单证申领申请明细存在重复单证类型",docVerCodeArr)) {
					 $("#submitButAllotNew").removeAttr("disabled");
					 $("#saveButAllotNew").removeAttr("disabled");
						return;
					}
				}
				// 校验申请数量长度
		    var jsonArr = $("#DocListAllotNew").mulLineWidget.getData();
			 for(var j=0;j<jsonArr.length;j++){
			     var allotNumLen=jsonArr[j].applyNum;
			     if(allotNumLen.length>7){
			       alert("输入的申请数量长度不能超过7！");
			        $("#submitButAllotNew").removeAttr("disabled");
			        $("#saveButAllotNew").removeAttr("disabled");
			       return ;
			     }	
			 }	
				var vcAllot = apply.getPageApplyNew();
				vcAllot.allotStatus=statusValues.VC_APPLY_STATUS_APPLY_AWAITPROVIDE;  //等待发放状态
				apply.ajaxSave(vcAllot,"new");
				apply.gridReload();
			} else {
				return;
			}
		});
	},	
	
	/**
	 * @description 自定义函数，让多行录入组件回调
	 * 
	 */
	resetVal : function (rIndex, objId){
		/**
		 * @description 更改字段值
		 * @param  参数1(org） 要更改值的标签Id
		 * @param  参数2(2） 要更改值的标签value
		 * 
		 */
		if(objId == "docVerCode") {
			var jsonArr = $("#DocListAllotNew").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			var QueryCondition = new Object();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			var provideOrgCode = $("#allotListDtoOrgCode").val();
			$.ajax({   
	             type:'post',   
	             async : false,
	             url: $.taHash.get("ctx")+"/applyJson/getValidStorageNum2.do?ajax=true",   
	             dataType:'json',   
	             data : {"docVerCode":docVerCode,"provideOrgCode":provideOrgCode},
	             success:function(msg){
	             	$("#DocListAllotNew").mulLineWidget.resetVal(rIndex, "validNum", msg);
	         	 }
	         });			
		}
		
		if(objId == "edit"){
			var jsonArr = $("#DocListAllotNew").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			
			if(docVerCode==null ||docVerCode=="") {
				alert("请选择单证类型");
				return;
			} else {
				$("#validStorageQuery_docVerCode").val(docVerCode);
				//$("#validOrg").val("validOrg");//查询本级及所有下级机构
				//申领机构
				var new_applyOrgCode=$("#new_applyOrgCode").val();
				$("#validOrg").val(new_applyOrgCode);//查询本级及所有下级机构
				//add by chy 20130916
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
	
	resetValEdit : function (rIndex, objId){
		/**
		 * @description 更改字段值
		 * @param  参数1(org） 要更改值的标签Id
		 * @param  参数2(2） 要更改值的标签value
		 * 
		 */
		if(objId == "docVerCode") {
			var jsonArr = $("#DocListApplyEdit").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			var provideOrgCode=$("#allotListDtoOrgCode").val()
			var QueryConditions = new Object();
			QueryConditions.docVerCode = jsonArr[rIndex-1].docVerCode;
			QueryConditions.provideOrgCode = $("#allotListDtoOrgCode").val();
			$.ajax({   
	             type:'post',   
	             url: $.taHash.get("ctx")+"/applyJson/getValidStorageNum2.do?ajax=true",   
	             dataType:'json',   
	             data : {"docVerCode":docVerCode,"provideOrgCode":provideOrgCode},
	             success:function(msg){
	             	$("#DocListApplyEdit").mulLineWidget.resetVal(rIndex, "validNum", msg);
	         	 }
	         });
		}
		if(objId == "edit"){
			var jsonArr = $("#DocListApplyEdit").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			
			if(docVerCode==null ||docVerCode=="") {
				alert("请选择单证类型");
				return;
			} else {
				$("#validStorageQuery_docVerCode").val(docVerCode);
				//$("#validOrg").val("validOrg");//查询本级及所有下级机构
				//申领机构
				var edit_applyOrgCode=$("#edit_applyOrgCode").val();
				$("#validOrg").val(edit_applyOrgCode);//查询本级及所有下级机构
				//add by chy 20130916
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
	
	resetValView : function (rIndex, objId){
		
		/**
		 * @description 更改字段值
		 * @param  参数1(org） 要更改值的标签Id
		 * @param  参数2(2） 要更改值的标签value
		 * 
		 */
		if(objId == "docVerCode") {
			
		}
		if(objId == "edit"){
			var jsonArr = $("#DocListApplyView").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			
			if(docVerCode==null ||docVerCode=="") {
				alert("请选择单证类型");
				return;
			} else {
				$("#validStorageQuery_docVerCode").val(docVerCode);
				//$("#validOrg").val("validOrg");//查询本级及所有下级机构
				//申领机构
				var view_applyOrgCode=$("#view_applyOrgCode").val();
				$("#validOrg").val(view_applyOrgCode);//查询本级及所有下级机构
				//add by chy 20130916
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

	/**
	 * 初始化修改页面
	 */
	initApplyEdit : function (){
		
		//applyEdit
		
		$("#applyEdit").validate();
		
		/*
		$("#DocListApply").mulLineWidget({
			hiddenPlus: '0',		//新增,是否隐藏添加一行的标志：0为显示，1为隐藏
			hiddenSubtraction: '0',	//新增,是否隐藏删除一行的标志：0为显示，1为隐藏
			columnData: apply.lineDataArry,
			initJsonDate: '[]'
		});
		*/
		$("#backButApplyEdit").click(function() {
			$('#editPageApply').dialog('close');
		});
		
		$("#saveButApplyEdit").click(function() {
			
			var valid = $("#applyEdit").validate().form();
			
			if(valid) {
			
				  //禁用按钮
				$("#saveButApplyEdit").attr("disabled",true);
				$("#submitButApplyEdit").attr("disabled",true);
			
				
				var jsonArr = $("#DocListApplyEdit").mulLineWidget.getData();
				if(jsonArr==null||jsonArr.length<=0) {
					alert("请输入明细");
					$("#saveButApplyEdit").removeAttr("disabled");
					$("#submitButApplyEdit").removeAttr("disabled");
					return;
				} else {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
					}
					
					if(common.isRepeat("单证申领申请明细存在重复单证类型",docVerCodeArr)) {
					$("#saveButApplyEdit").removeAttr("disabled");
					$("#submitButApplyEdit").removeAttr("disabled");
						return;
					}				
				}
			// 校验申请数量长度
		    var jsonArr = $("#DocListApplyEdit").mulLineWidget.getData();
			 for(var j=0;j<jsonArr.length;j++){
			     var applyNumLen=jsonArr[j].applyNum;
			     if(applyNumLen.length>7){
			       alert("输入的申请数量长度不能超过7！");
			       $("#saveButApplyEdit").removeAttr("disabled");
			       $("#submitButApplyEdit").removeAttr("disabled");
			       return ;
			     }	
			 }		
				
				
				var vcApply = apply.getPageApplyEdit();
				vcApply.applyStatus = statusValues.VC_APPLY_STATUS_APPLY_TEMPSAVE;  //暂存状态
				apply.ajaxSave(vcApply,"edit");	
			} else {
				return;
			}
		});
		
		$("#submitButApplyEdit").click(function() {
			var valid = $("#applyEdit").validate().form();
			
			if(valid) {
			
			     //禁用按钮
			    $("#saveButApplyEdit").attr("disabled",true);
				$("#submitButApplyEdit").attr("disabled",true);
				
				var jsonArr = $("#DocListApplyEdit").mulLineWidget.getData();
				if(jsonArr==null||jsonArr.length<=0) {
					alert("请输入明细");
					 $("#submitButApplyEdit").removeAttr("disabled");
					 $("#saveButApplyEdit").removeAttr("disabled");
					return;
				} else {
					var docVerCodeArr = new Array();
					for(var i=0; i<jsonArr.length;i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
					}
					
					if(common.isRepeat("单证申领申请明细存在重复单证类型",docVerCodeArr)) {
					$("#submitButApplyEdit").removeAttr("disabled");
					$("#saveButApplyEdit").removeAttr("disabled");
						return;
					}				
				}
				
				// 校验申请数量长度
		     var jsonArr = $("#DocListApplyEdit").mulLineWidget.getData();
			 for(var j=0;j<jsonArr.length;j++){
			     var applyNumLen=jsonArr[j].applyNum;
			     if(applyNumLen.length>7){
			       alert("输入的申请数量长度不能超过7！");
			       $("#submitButApplyEdit").removeAttr("disabled");
			       $("#saveButApplyEdit").removeAttr("disabled");
			       return ;
			     }	
			 }		
			 
				
				var vcApply = apply.getPageApplyEdit();
				vcApply.applyStatus=statusValues.VC_APPLY_STATUS_APPLY_AWAITPROVIDE;  //等待发放状态
				apply.ajaxSave(vcApply,"edit");
			} else {
				return;
			}

		});	
		
		var initData = $("#edit_hiddenDet").html();
		//var url = (id==null||id=="")?'':common.ctx+"/applyJson/getApplyDetList.do?ajax=true&id="+id
		$("#DocListApplyEdit").mulLineWidget({
			columnData: apply.lineDataArry,
			exeFuction: apply.resetValEdit,
			initJsonDate: initData
		});
	},
	
	/**
	 * 初始化浏览页面
	 */
	initApplyView : function (){
		
		$("#backButApplyView").click(function() {
			$('#viewPageAllot').dialog('close');
		});
				
		var initData = $("#view_hiddenDet").html();
//		setTimeout(function(){
		$("#DocListApplyView").mulLineWidget({
			hiddenPlus: '1',		
			hiddenSubtraction: '1',
			exeFuction: apply.resetValView,
			columnData: apply.lineDataArryDisabled,
			initJsonDate: initData
		  });
//		 }, 500);
	},	
	
	/**
	 * 加载jqGrid数据
	 */
	gridReload: function () {
		var gridReloadUrl = common.ctx+"/allotJson/allotQueryInfo.do?ajax=true";
		var QueryCondition = new Object();
		QueryCondition.queryCode = $("#allotCode").val();
		QueryCondition.startDate = $("#initStartDate").val();
		QueryCondition.endDate = $("#initEndDate").val();
		QueryCondition.queryStatus = $("#allotStatusDropDownList").val();
		jQuery("#allotGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型 
			postData : {
				jsonStr : JSON.stringify(QueryCondition)
			},
			page : 1
		});
		jQuery("#allotGrid").trigger("reloadGrid");
		$("#id").val("");
	},
	
	/**
	 * 初始化查询页面
	 */
	initQueryPage: function(){
		
		$("#editPageApply").css("display","none");
		
		$("#newPageAllot").css("display","none");
		
		$("#viewPageAllot").css("display","none");
		
		$("#validStorageDiv").css("display","none");
		
		$("#allotStatusDropDownList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getPubCode.do?ajax=true&codeType=ApplyStatus"
//			data:apply.applyQueryEnableStatus
		});
		
		$("#allotStatusDropDownList").dropDownList("selected","");
		
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#editPageApply").dialog({
			modal: true,
			height:430,
			width:850,
			autoOpen: false
		});
		
		$("#newPageAllot").dialog({
			modal: true,
			height:430,
			width:850,
			autoOpen: false
		});
		
		$("#viewPageAllot").dialog({
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
		$("#queryAllot").click(function() {
			//apply.gridReload();
			var gridReloadUrl = common.ctx+"/allotJson/allotQueryInfo.do?ajax=true";
			var QueryCondition = new Object();
			QueryCondition.queryCode = $("#allotCode").val();
			QueryCondition.startDate = $("#initStartDate").val();
			QueryCondition.endDate = $("#initEndDate").val();
			QueryCondition.queryStatus = $("#allotStatusDropDownList").val();
			jQuery("#allotGrid").jqGrid('setGridParam', {
				url : gridReloadUrl,
				datatype: "json", //设置数据类型 
				postData : {
					jsonStr : JSON.stringify(QueryCondition)
				},
				page : 1
			});
			jQuery("#allotGrid").trigger("reloadGrid");
			$("#id").val("");
			
		});
		
		/**
		 * 
		 */
		$("#newAllot").click(function() {
			//调用dialog组件
			$("#newPageAllot").empty();
			$("#editPageApply").empty();
			$("#viewPageAllot").empty();
			common.ajaxGetData("newPageAllot", common.ctx+"/allot/allotAddInfo.do?ajax=true" + '&random=' + Math.random(),null);
			setTimeout(function() {
				$("#newPageAllot").dialog('open');
			},300);
		});
		/**
		 * 浏览按钮单击事件
		 */
		$("#viewAllot").click( function() {
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行浏览");
				} else {
					$("#newPageAllot").empty();
					$("#editPageAllot").empty();
					$("#viewPageAllot").empty();
					
					
					//ajax获取要修改的数据
					common.ajaxGetData("viewPageAllot", $.taHash.get("ctx")+"/apply/viewApplyView.do?id="+id+"&ajax=true",null);
					
					setTimeout(function(){
						$('#viewPageAllot').dialog('open');
					},300);
					
				}
			}else{
				alert("请选择一条记录进行浏览");
			}
			
		});
		$("#deleteAllot").click(function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			var action = $.taHash.get("ctx")+"/allotJson/deleteAllotList.do?ajax=true";
			if("" != id){
				//ajax获取要修改的数据
				var idArr = id.split(",");
				for(var i=0; i<idArr.length;i++) {
					var rowDatas = $("#allotGrid").jqGrid('getRowData', idArr[i]); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var rowStatus = rowDatas["allotStatus"];
					var rowApplyCode = rowDatas["allotCode"];
					if(('1'!=rowStatus)&&('3'!=rowStatus)) {
						alert("所选申领单号非[新建]或者[发放退回]状态,不可以进行删除操作");
						return;
					}				
				}
				
				if(confirm("确定删除已选记录?")) {
					var param = {"deleteList":id};
					common.ajaxSubmitDfCallBack(apply.callBack, action, param);				
				}
			}else{
				alert("请选择记录进行删除");
			}			
		});
		$("#submitAllot").click(function() {
			//获取选中行的id
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			var action = $.taHash.get("ctx")+"/allotJson/submitAllotList.do?ajax=true";
			if("" != id){
				//ajax获取要修改的数据
				var idArr = id.split(",");
				for(var i=0; i<idArr.length;i++) {
					var rowDatas = $("#allotGrid").jqGrid('getRowData', idArr[i]); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var rowStatus = rowDatas["allotStatus"];
					var rowAllotCode = rowDatas["allotCode"];
					if(('1'!=rowStatus)&&('3'!=rowStatus)) {
						alert("所选申领单号非[新建]或者[发放退回]状态,不可以进行提交操作");
						return;
					}				
				}
				
				var param = {"submitList":id};
				common.ajaxSubmitDfCallBack(apply.callBack, action, param);				
			}else{
				alert("请选择记录进行提交操作");
			}			
		});
		$("#allotGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id', '调拨单号','调拨申请人','调拨机构','调拨日期','调拨申请状态','allotStatus'],
			colModel : [{
						name : 'id',
						index : 'id',
						hidedlg : true,
						hidden : true
					},{
						name : 'allotCode',
						index : 'allotCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'allotOprName',
						index : 'allotOprName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'allotOrgName',
						index : 'allotOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'allotTime',
						index : 'allotTime',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i:s'}
					},{
						name : 'allotStatusName',
						index : 'allotStatusName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'allotStatus',
						index : 'allotStatus',
						hidedlg : true,
						hidden : true
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#applyGridPager',
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
				var selectValue = common.selectRowValue("allotGrid");
				$("#id").val(selectValue);
			},
			
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#id").val("");
				}else{//全部选中时
					var ids = $("#allotGrid").jqGrid('getDataIDs');
					$("#id").val(ids);
				}
			},
			onPaging:function(pgButton) {
				$("#id").val("");
			},
			gridComplete: function(){
			    var ids = $("#allotGrid").jqGrid('getDataIDs');
			    for(var i=0;i < ids.length;i++){
			     var rowArray = $("#allotGrid").jqGrid('getRowData',ids[i]);
			     var editBtn = "<a href='#' onclick='apply.hrefView("+rowArray.id+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.allotCode+"</a>";
			     $("#allotGrid").jqGrid('setCell', ids[i], 'allotCode', editBtn);
			    }
		   }
		});
	},
	
	callBack:function(data) {
		 alert(data);
		 apply.gridReload();
	},
	/**
	 * 获取jsp新增的对象
	 */
	getPageApplyNew:function() {
		var jsonArr = $("#DocListAllotNew").mulLineWidget.getData();
		var allotDetList = jsonArr;
			
		var vcAllot = new Object();
		vcAllot.allotDetList = allotDetList;
		vcAllot.allotOrgCode=$("#new_allotOrgCode").val();
		vcAllot.allotOprCode=$("#new_allotOprCode").val();
		vcAllot.provideOrgCode = $("#allotListDtoOrgCode").val();
		vcAllot.allotTime=$("#new_allotTime").val();
		vcAllot.allotReason=$("#new_allotReason").val();
		return vcAllot;
	},
	
	/**
	 * 获取jsp修改的对象
	 */
	getPageApplyEdit:function() {
		var jsonArr = $("#DocListApplyEdit").mulLineWidget.getData();
		var applyDetList = jsonArr;
		var vcApply = new Object();
		vcApply.applyDetList = applyDetList;
		vcApply.id = $("#vcApply_id").val();
		vcApply.applyOrgCode=$("#edit_applyOrgCode").val();
		vcApply.applyOprCode=$("#edit_applyOprCode").val();
		vcApply.provideOrgCode=$("#edit_provideOrgCode").val();
		vcApply.applyTime=$("#edit_applyTime").val();
		vcApply.applyReason=$("#edit_applyReason").val();
		return vcApply;
	},	
	//fyhsave
	ajaxSave:function(vcApply, type) {
		var str = JSON.stringify(vcApply);	
		var params = {
           jsonStr : str
        };
		$.ajax({   
             type:'post',   
             url: $.taHash.get("ctx")+"/allotJson/saveAllot.do?ajax=true",   
             dataType:'json',   
             data:params,
             success:function(msg){
             	    $("#saveButAllotNew").removeAttr("disabled");
	                $("#submitButAllotNew").removeAttr("disabled");
	                $("#saveButApplyEdit").removeAttr("disabled");
	                $("#submitButApplyEdit").removeAttr("disabled");   
             	var index = msg.toString().indexOf('成功');
             	if(index!=-1) {
	             	if("new"==type) {
	             		$('#newPageAllot').dialog('close');
	             	}
	             	if("edit"==type) {
	             		$('#editPageApply').dialog('close');
	             	}
	             	
	             	$("#saveButAllotNew").removeAttr("disabled");
	                $("#submitButAllotNew").removeAttr("disabled");
	                $("#saveButApplyEdit").removeAttr("disabled");
	                $("#submitButApplyEdit").removeAttr("disabled");
	                
		            apply.gridReload();
             	}
         	 }
         }); 
	},
	
	hrefView:function(id) {
		$("#newPageAllot").empty();
		$("#editPageAllot").empty();
		
		//ajax获取要修改的数据
		common.ajaxGetData("viewPageAllot", $.taHash.get("ctx")+"/allot/viewAllotView.do?id="+id+"&ajax=true",null);
		
		setTimeout(function(){
			$('#viewPageAllot').dialog('open');
		},300);
	}
		
}