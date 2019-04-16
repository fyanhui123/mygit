var destroyEdit = {

	lineDataArry : [ {
		title : "单证类型",
		fieldId : "docVerCode",
		fieldName : "docVerCode",
		fieldLableName : "docVerName",
		fieldClass : "required",
		width:"160px",
		type : "2",
		dataFormat : [ {
			source : "/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no",
			height : '210px',
			minLength : 0
		} ]
	}, {
		title : "印刷批次",
		fieldId : "pressBatchNo",
		fieldName : "pressBatchNo",
		fieldClass : "required noBlankSpace",
		maxlength:"16",
		width:"100px",
		type : "4"
	}, {
		title : "起始流水号",
		fieldId : "startNum",
		fieldName : "startNum",
		fieldClass : "required noBlankSpace",
		minlength:"8",
		maxlength:"10",
		width:"90px",
		type : "4"
	}, {
		title : "终止流水号",
		fieldId : "endNum",
		fieldName : "endNum",
		fieldClass : "required noBlankSpace",
		minlength:"8",
		maxlength:"10",
		width:"90px",
		type : "4"
	}, {
		title : "销毁数量",
		fieldId : "destroyNum",
		fieldName : "destroyNum",
		fieldClass : "required",
		width:"60px",
		type : "4",
		disabled:"disabled"
	}, {
		title:"单证状态",
		fieldId:"docStatus",
		fieldName:"docStatus",
		fieldClass:"required",
		width:"50px",
		type:"3",
		disabled:"disabled",
		dataFormat:[{
			selectedValue:"S2", 
			data:[{"value":"S1","label":"印刷入库"},{"value":"S2","label":"申领/调拨入库"},{"value":"S3","label":"回收入库"}]
		}]
	},
	{title:"操作",fieldId:"validView",fieldName:"validView",fieldVal:"查看", type:"8",width:"100px"}],

	initPage : function() {
		$("#validStorageDiv").dialog({
			modal: true,
			height:550,
			width:950,
			autoOpen: false
		});
		
		$("#editForm").validate();
		$("#destroyType_ajax").dropDownList({ 
			selectedValue : $("#destroyTypeValue").val(),
		    url: "/visa/baseinfoJson/initDownList.do?codeType=DestroyType"
		});   

		
		var initJson = $("#edit_hiddenDestroy").html();
		/**
		 * 初始化多行输入域
		 */
		$("#destroyEditList").mulLineWidget( {
			columnData : destroyEdit.lineDataArry,
			initJsonDate :initJson,
			exeFuction : destroyEdit.resetVal
				});

		/**
		 * 保存按钮添加事件
		 */
		$("#saveBut").click(function() {
			saveDestroy("1","saveBut");
		});

		$("#submitBut").click(function() {
			saveDestroy("2","submitBut");
		});

		$("#saveBackBtn").click(function() {
			//$('#destroyEditPage').dialog('close');
			common.localHref($.taHash.get("ctx")
					+ "/destroy/initDestroyQuery.do");
		});

		/**
		 * action 1.保存 2.提交
		 */
		function saveDestroy(destroyStatus,butId) {
			if ($("#destroyEditForm").validate().form()) {
			
			   $("#saveBut").attr("disabled",true);
			   $("#submitBut").attr("disabled",true);
			  
				if($("#destroyType_ajax").val() == ""){
					alert("请选择销毁类型");
					$("#saveBut").removeAttr("disabled");      
					$("#submitBut").removeAttr("disabled"); 
					return;
				}
				var jsonArr = $("#destroyEditList").mulLineWidget.getData();
				if (jsonArr.length == 0) {
					alert("请输入遗失单证信息");
					$("#saveBut").removeAttr("disabled"); 
					$("#submitBut").removeAttr("disabled");      
					return;
				}
				var docVerCodeArr = new Array();
				for ( var i = 0; i < jsonArr.length; i++) {
					docVerCodeArr.push(jsonArr[i].docVerCode);
				}
				//双重循环判断同意单证是否流水号区间有重叠
				var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
				if(isOverLap){  //有重叠
					$("#saveBut").removeAttr("disabled"); 
				    $("#submitBut").removeAttr("disabled"); 
					return;
				}
				/*if (common.isRepeat("销毁申请明细存在重复单证类型", docVerCodeArr)) {
				    $("#saveBut").removeAttr("disabled"); 
				    $("#submitBut").removeAttr("disabled"); 
					return;
				}*/
				//禁用按钮
				//$("#"+butId).attr("disabled",true);

				var jsonStr = JSON.stringify(jsonArr);
				$("#jsonStr").val(jsonStr);
				var formStr = "?destroyStatus=" + destroyStatus;
				var action = $.taHash.get("ctx") + "/destroy/saveDestroy.do";
				var fm = document.getElementById("destroyEditForm");
				fm.action = action + formStr;
				fm.submit();
				// common.ajaxSubmitDfCallBack(destroyEdit.callBack,
				// action, formStr);
			} else {
				return false;
			}
		}
	},

	callBack : function(data) {
		alert(data);
		if (data == '操作成功') {
			$('#destroyEditPage').dialog('close');
			 $("#saveBut").removeAttr("disabled");
			 $("#submitBut").removeAttr("disabled"); 
			 destroy.gridReload();
		}
	},
	resetVal : function(rIndex, objId) {
		if(objId == "endNum"||objId == "startNum"){
			//common.totalNum(rIndex,"destroyNum","destroyEditList",objId);
			common.autoLengthAndTotalNum(rIndex,"destroyNum","destroyEditList",objId,8);
		}
		if(objId == "validView"){
			var jsonArr = $("#destroyEditList").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			if(docVerCode==null ||docVerCode=="") {
				alert("请选择单证类型");
				return;
			} else {
				$("#validStorageQuery_docVerCode").val(docVerCode);
				$("#validOrg").val("");
				$("#rowIndex").val(rIndex);
				$("#tableName").val("destroyEditList");
				$("#hiddenQueryBtn").click();
				setTimeout(function(){
					$("#selectStorageRec").removeAttr("disabled");
					$('#validStorageDiv').dialog('open');
				},300);
			}
		}		
		
	}

}
