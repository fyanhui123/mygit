var docStorageInOutAdd = {
		
		//操作失败提示信息
		alertResultMsg:function(actionType,msg){
			if(actionType == 'saveFail'){
				alert(msg);
			}
         },
         
		lineDataArryP : [
			{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldClass:"required",width:"180px",type:"2",
				dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no&notContainActivateCard=yes",
			//  type:"3"    /visa/baseinfoJson/getJSONUserSend.do?trigger=no&notContainActivateCard=yes   下拉框
					height:'210px', minLength:0}]},
			{title:"印刷批次",fieldId:"pressBatchNo",fieldName:"pressBatchNo",fieldClass:"required noBlankSpace",width:"100px",type:"4"},											
			{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",fieldClass:"required noBlankSpace",width:"100px",type:"4",minlength:"8",maxlength:"10"},
			{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",fieldClass:"required noBlankSpace",width:"100px",type:"4",minlength:"8",maxlength:"10"},
			{title:"数量",fieldId:"docNum",fieldName:"docNum",fieldClass:"required digits",width:"100px",type:"4",disabled:"disabled"},			
			{title:"单证状态",fieldName:"docStatus",fieldId:"docStatus",width:"60px",fieldClass:"required",type:"3",disabled:"disabled",
				dataFormat:[{selectedValue:"S1", data:[{"value":"S1","label":"印刷入库"},{"value":"S2","label":"申领/调拨入库"},{"value":"S3","label":"回收入库"},{"value":"OT","label":"已出库"}]}]
			},
			{title:"有效库存",fieldId:"storage",fieldName:"storage",fieldVal:"查看",type:"8"}
			],
			
		initInOutPage: function(){
	
			$("#validStorageDiv").dialog({
				modal: true,
				height:430,
				width:1000,
				autoOpen: false
			});
	
			//$("#editForm").validate();
			/**
			 * 初始化多行输入域
			 */
			var initData = $("#edit_hiddenDet").html();
			$("#docInOutDetinput").mulLineWidget({
				hiddenPlus: '0',		// 新增,是否隐藏添加一行的标志：0为显示，1为隐藏
				hiddenSubtraction: '0',	// 新增,是否隐藏删除一行的标志：0为显示，1为隐藏
				columnData: docStorageInOutAdd.lineDataArryP,
				exeFuction: docStorageInOutAdd.resetValP,	
				initJsonDate: initData
			});
			
			/**
			 * 保存按钮添加事件
			 */
			$("#saveBut").click(function(){
				if($("#addForm").validate().form()){
			     	//  防止重复提交
			     // $("#saveBut").attr("disabled",true);
					var actionType=$("#actionType").val();
					var jsonArr = $("#docInOutDetinput").mulLineWidget.getData();
					if(jsonArr.length==0){
						alert("请输入单证信息");
						$("#saveBut").removeAttr("disabled");
						return;
					}
					//双重循环判断同意单证是否流水号区间有重叠
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveBut").removeAttr("disabled");
						$("#submitBut").removeAttr("disabled");
						return;
					}
			   
					var jsonStr = JSON.stringify(jsonArr);					
					$("#jsonStr").val(jsonStr);
					url=$.taHash.get("ctx")+ "/storeInOut/executeStorageInOut.do";
					$('#addForm').attr("action",url);
					$('#addForm').submit();
					
				}else{
					return false;
				}
			});
			
			$("#goBackBtn").click(function(){				
				url=$.taHash.get("ctx")+ "/storeInOut/initQueryStorageInOut.do";
				$('#backMainForm').attr("action",url);
				$('#backMainForm').submit();
				//common.formSubmit("queryForm",$.taHash.get("ctx")+ "/storeInOut/initQueryStorageInOut.do");
			});
		},
		
		saveCallBack:function (data){
			 alert(data);
			 $("#saveBut").removeAttr("disabled");
			if(data=="操作成功"){
			    $("#saveBut").removeAttr("disabled");
				$('#docIssuedAddPage').dialog('close');
			}
			servantDoc.gridReload();
		},
		
		resetValP : function (rIndex, objId){
			/**
			 * @description 更改字段值
			 * @param 参数1(org）
			 *            要更改值的标签Id
			 * @param 参数2(2）
			 *            要更改值的标签value
			 * 
			 */
			if(objId == "docVerCode"){
				//VC-48 不能操作激活卡
				var curDocVerCode = $("#" + "docVerCode__" + rIndex).val();
				var curType =curDocVerCode.substr(0,2);
				if (curType=='JK'){
					$("#" + "docVerCode__" + rIndex).val("");
					return false;
				}
			}
            if(objId == "endNum"||objId == "startNum"){				
				 common.autoLengthAndTotalNum(rIndex,"docNum","docIssuedInput",objId,8);
			}
			
			// 本级有效库存
			if(objId == "storage"){
				var docVerCode = $("#" + "docVerCode__" + rIndex).val();
				if(docVerCode==null ||docVerCode=="") {
					alert("请选择单证类型");
					return;
				} else {				
					$("#validStorageQuery_docVerCode").val(docVerCode);
				    $("#validOrg").val(""); 
				    if($("#operateType").val()=='I'){ ///入库
				    	$("#validDocStatus").val("OT"); 
				    }
					$("#rowIndex").val(rIndex);
					$("#tableName").val("docInOutDetinput");
					$("#hiddenQueryBtn").click();
					setTimeout(function(){
						//add by chy 20130916
						$("#selectStorageRec").removeAttr("disabled");
						$('#validStorageDiv').dialog('open');
					},300);
				}
			}
		}
	}
