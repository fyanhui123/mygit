var lostApplyEdit = {
		
		lineDataArry : [ 
			{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldLableName:"docVerName",fieldClass:"required",	width:"180px",type:"2",
				dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no"
					, height:'210px',minLength:0}]},
			{title:"印刷批次",fieldId:"pressBatchNo",fieldName:"pressBatchNo",fieldClass:"required noBlankSpace",width:"100px",type:"4",maxlength:"16"},
			{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",fieldClass:"required noBlankSpace",width:"100px",type:"4",minlength:"8",maxlength:"10"},
			{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",fieldClass:"required noBlankSpace",width:"100px",type:"4",minlength:"8",maxlength:"10"},
			{title:"遗失数量",fieldId:"lostNum",fieldName:"lostNum",fieldClass:"required",width:"60px",type:"4",disabled:"disabled"},
			{title:"单证状态",fieldId:"docStatus",fieldName:"docStatus",fieldClass:"required",width:"60px",type:"3",
					dataFormat:[{selectedValue:"S1", data:[{"value":"S1","label":"印刷入库"},{"value":"S2","label":"申领/调拨入库"},{"value":"S3","label":"回收入库"},{"value":"A","label":"可使用"}]}]
				},
			{title:"操作",fieldId:"validView",fieldName:"validView",fieldVal:"查看", type:"8",width:"100px"}
		],
		
		//新增、修改保存失败提示信息
		alertErrorMsg:function(hasError,msg){
			if(hasError == 'true'){
				alert(msg);
			}
		},

		initPage: function(){
			$("#validStorageDiv").dialog({
				modal: true,
				height:550,
				width:950,
				autoOpen: false
			});
			
			$("#editForm").validate();
			
			var initJson =  $("#edit_hiddenLostDet").html();
			/**
			 * 初始化多行输入域
			 */
			$("#lostApplyEditList").mulLineWidget({
				columnData: lostApplyEdit.lineDataArry,
				initJsonDate: initJson,
				exeFuction : lostApplyEdit.resetVal
				
				//initDataUrl:"/visa/lostApplyJson/initEditLostApply.do?ajax=true&id="+$("#vcLostId").val()
			});

			/**
			 * 保存按钮添加事件
			 */
			$("#saveBut").click(function(){
				saveLostApply("1","saveBut");
			});
			
			$("#submitBut").click(function(){
				saveLostApply("2","submitBut");
			});
			
			$("#saveBackBtn").click(function(){
				//$('#lostApplyEditPage').dialog('close');
				common.localHref($.taHash.get("ctx")
						+ "/lostApply/initLostApplyQuery.do");
			});
			
			/**
			 * action 1.保存 2.提交
			 */
			function saveLostApply(lostStatus,butId){
				if($("#lostEditForm").validate().form()){
					$("#submitBut").attr("disabled",true);
					$("#saveBut").attr("disabled",true);
					//
					//验证通过执行的代码块
					//单证类型
					
					var jsonArr = $("#lostApplyEditList").mulLineWidget.getData();
					if(jsonArr.length==0){
						alert("请输入遗失单证信息");
						$("#submitBut").removeAttr("disabled"); 
						$("#saveBut").removeAttr("disabled");
						return;
					}
					var docVerCodeArr = new Array();
					for ( var i = 0; i < jsonArr.length; i++) {
						docVerCodeArr.push(jsonArr[i].docVerCode);
					}
					//双重循环判断同意单证是否流水号区间有重叠
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#submitBut").removeAttr("disabled"); 
						$("#saveBut").removeAttr("disabled");
						return;
					}					
					
					//禁用按钮
					//$("#"+butId).attr("disabled",true);
					
					
					
					
					var jsonStr = JSON.stringify(jsonArr);
					//var lostReason = $("#lostReason").text();
					//var actionType = $("#actionType").val();
					//var id = $("#vcLostId").val();;
					var formStr = "?lostStatus="+lostStatus+"&jsonStr="+jsonStr;
					var action = $.taHash.get("ctx")+"/lostApply/saveLostApply.do";
					var fm = document.getElementById("lostEditForm");
					fm.action = action+formStr;
					fm.submit();
					//common.ajaxSubmitDfCallBack(lostApplyEdit.callBack,
					//		action, formStr);
				}else{
					return false;
				}
			}
		},
		
			
		callBack: function(data){
			alert(data);
			if(data == '操作成功'){
				$('#lostApplyEditPage').dialog('close');
				lostApply.gridReload();
			}
		},
		resetVal : function (rIndex, objId){
			/**
			 * @description 更改字段值
			 * @param  参数1(org） 要更改值的标签Id
			 * @param  参数2(2） 要更改值的标签value
			 * 
			 */
			if(objId == "endNum"||objId == "startNum"){
				//common.totalNum(rIndex,"lostNum","lostApplyEditList",objId);
				common.autoLengthAndTotalNum(rIndex,"lostNum","lostApplyEditList",objId,8);
				/*var start = $("#" + "startNum__" + rIndex).val();
				var end = $("#" + "endNum__" + rIndex).val();
				var total = Number(end) - Number(start) + 1;
				$("#lostApplyEditList").mulLineWidget.resetVal(rIndex, "lostNum", total);*/
			}
			if(objId == "validView"){
				var jsonArr = $("#lostApplyEditList").mulLineWidget.getData();
				var docVerCode = jsonArr[rIndex-1].docVerCode;
				var Status = jsonArr[rIndex-1].docStatus;
				if(docVerCode==null ||docVerCode=="") {
					alert("请选择单证类型");
					return;
				} else {
					$("#validStorageQuery_docVerCode").val(docVerCode);
					$("#validOrg").val("");
					$("#validQueryType").val("3");//查询当前机构下的库存和可使用表
					$("#rowIndex").val(rIndex);
					$("#tableName").val("lostApplyEditList");
					$("#validStatus").val(Status);
					$("#hiddenQueryBtn").click();
					setTimeout(function(){
						$("#selectStorageRec").removeAttr("disabled");
						$('#validStorageDiv').dialog('open');
					},300);
				}
			}		
		}

	}
