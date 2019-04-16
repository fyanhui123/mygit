var mulLineRecovery = {
		lineDataArryR : [
			{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldClass:"required",width:"180px",type:"2",
				dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no&notContainActivateCard=yes"
			//  /visa/baseinfoJson/getJSONDocVersionInfoList.do?style=auto&notContainActivateCard=yes
					, height:'210px',minLength:0}]},
			{title:"印刷批次",fieldId:"pressBatchNo",fieldName:"pressBatchNo",fieldClass:"required noBlankSpace",width:"100px",type:"4"},											
			{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",fieldClass:"required noBlankSpace",width:"100px",type:"4",minlength:"8",maxlength:"10"},
			{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",fieldClass:"required noBlankSpace",width:"100px",type:"4",minlength:"8",maxlength:"10"},
			{title:"数量",fieldId:"docNum",fieldName:"docNum",fieldClass:"required digits",width:"100px",type:"4",disabled:"disabled"},
			{title:"可使用单证",fieldId:"available",fieldName:"available",fieldVal:"查看",type:"8"}
			],

		initPageR: function(){
	
			/**
			 * 使用人自动完成
			 */
			$("#editTakerCode").taAutoComplete(
			{
				source : "/visa/baseinfoJson/getTakerJson.do?type=auto",
				//source : "/visa/baseinfoJson/getJsonVcTaker.do?ajax=true",
				minLength : 0,
				height : 210,
				width : 220
			});
			
			$("#editTakerCode").blur(function (){
				var takerCode = $('#editTakerCode').val();
				var action = "/visa/baseinfoJson/findOrgCodeJson.do?ajax=true";
				$.ajax({
					datatype: 'html',
					url: action,
					type: "post",
					data: {"takerCode":takerCode},
					success: function(data){
						if(data != null && data != ""){
							data = eval("("+data+")");
							$("#takerOrgCode").val(data.orgCode);
							$("#takerOrgName").val(data.orgName);
						}
					}	
				})
			});
	
			$("#editForm").validate();
			/**
			 * 初始化多行输入域
			 */
			$("#docRecoveryInput").mulLineWidget({
				hiddenPlus: '0',		// 新增,是否隐藏添加一行的标志：0为显示，1为隐藏
				hiddenSubtraction: '0',	// 新增,是否隐藏删除一行的标志：0为显示，1为隐藏
				columnData: mulLineRecovery.lineDataArryR,
				exeFuction: mulLineRecovery.resetValR
				// isHaveTitle:'0',
				// initDate:
				// '[{"SEX":"F","INSUM":"100","INSUMDATE":"2011-01-02","DEPTNO":"102","PLANCODE":"021","POLNO":"p11111111"}]'
			});
			
			/**
			 * 保存按钮添加事件
			 */
			$("#saveBut").click(function(){
				if($("#addForm").validate().form()){
				    //  防止重复提交
			       $("#saveBut").attr("disabled",true);
			       
					// 验证通过执行的代码块
					var takerCode = $("input[name='takerCode']");
					var editTakerCodeName=$("#editTakerCodeName").val();
					if(takerCode[0].value == ""  ||  editTakerCodeName =="" ){
						alert("使用人代码或名称不能为空");
						$("#saveBut").removeAttr("disabled");
						return;
					}
					//var acceptOrgCode = $("input[name='acceptOrgCode']");
					var acceptOrgCode = $("#takerOrgCode").val();
					var actionType=$("#actionType").val();
					var jsonArr = $("#docIssuedInput").mulLineWidget.getData();
					if(jsonArr.length==0){
						alert("请输入单证信息");
						$("#saveBut").removeAttr("disabled");
						return;
					}
					
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveBut").removeAttr("disabled");
						$("#submitBut").removeAttr("disabled");
						return;
					}
					
					var jsonStr = JSON.stringify(jsonArr);
					var formStr = "";
					
					formStr = formStr + "&takerCode="+takerCode[0].value+"&acceptOrgCode="+acceptOrgCode+"&jsonStr="+jsonStr;
					var action = $.taHash.get("ctx")+"/servantDocManageJson/saveServantDocRecovery.do?ajax=true";
					// common.ajaxSubmitAndCloseDialog("docIssuedAddPage",action
					// ,formStr);
					common.ajaxSubmitDfCallBack(mulLineRecovery.saveCallBack,action,formStr);
				}else{
					return false;
				}
			});
			
			$("#saveBackBtn").click(function(){
				$('#docRecoveryAddPage').dialog('close');
			});
			
			/**
			 * 使用人自动完成
			
			$("#takerCode").taAutoComplete(
					{
						source : $.taHash.get("ctx")
								+ "/baseinfoJson/queryVcDocVerLabels.do",
						minLength : 0,
						height : 200,
						width : 220
					});
			 */
		},
		
		saveCallBack:function (data){
			alert(data);
			$("#saveBut").removeAttr("disabled");
			if(data=="操作成功"){
				$('#docRecoveryAddPage').dialog('close');
				
				$("#saveBut").removeAttr("disabled");
				
			}
			servantDoc.gridReload();
		},
		
		resetValR : function (rIndex, objId){
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
			}else if(objId == "endNum"||objId == "startNum"){
				//common.totalNum(rIndex,"docNum","docRecoveryInput",objId);
				common.autoLengthAndTotalNum(rIndex,"docNum","docRecoveryInput",objId,8);
				/*var start = $("#" + "startNum__" + rIndex).val();
				var end = $("#" + "endNum__" + rIndex).val();
				var total = Number(end) - Number(start) + 1;*/
				//$("#inStoreInput").mulLineWidget.resetVal(rIndex, "docNum", total);
				// condition = $("#" + "docVerCode__" + rIndex).val();
			}else if(objId=="available"){
				var takerCode = $("#editTakerCode").val();
				var docVerCode = $("#" + "docVerCode__" + rIndex).val();
				if(takerCode==null || takerCode==""){
					alert("请选择使用人");
					return false;
				}
				if(docVerCode==null ||docVerCode=="") {
					alert("请选择单证类型");
					return;
				} else {
					var orgCode = $("#takerOrgCode").val();
					$("#validStorageQuery_docVerCode").val(docVerCode);
					$("#takerCode").val(takerCode);
					$("#validOrg").val(orgCode);
					$("#rowIndex").val(rIndex);
					//MODIFY BY zhxiao VC-112 使用人单证批量发放、回收 BEGIN
					$("#tableName").val("docRecoveryInput");
					//MODIFY BY zhxiao VC-112 使用人单证批量发放、回收 END
					$("#hiddenQueryBtn").click();
					setTimeout(function(){
						$("#selectStorageRec").removeAttr("disabled");
						$('#validAvailableDiv').dialog('open');
					},300);
				}
			}
		}
	}
