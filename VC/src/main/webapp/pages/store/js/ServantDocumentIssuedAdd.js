 var mulLineIssued = {

		lineDataArryP : [
			{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldClass:"required",width:"180px",type:"2",
				dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no&notContainActivateCard=yes",
			//  type:"3"    /visa/baseinfoJson/getJSONUserSend.do?trigger=no&notContainActivateCard=yes   下拉框
					height:'210px', minLength:0}]},
			{title:"印刷批次",fieldId:"pressBatchNo",fieldName:"pressBatchNo",fieldClass:"required noBlankSpace",width:"100px",type:"4"},											
			{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",fieldClass:"required noBlankSpace",width:"100px",type:"4",minlength:"8",maxlength:"10"},
			{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",fieldClass:"required noBlankSpace",width:"100px",type:"4",minlength:"8",maxlength:"10"},
			{title:"使用截止日期",fieldId:"deadline",fieldName:"deadline",fieldClass:"required",width:"100px",type:"5"},
			{title:"数量",fieldId:"docNum",fieldName:"docNum",fieldClass:"required digits",width:"100px",type:"4",disabled:"disabled"},
			{title:"本级有效库存",fieldId:"storage",fieldName:"storage",fieldVal:"查看",type:"8"}
			],
		initPageP: function(){
		
		
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
				var action = "/visa/baseinfoJson/findOrgCodeJson.do?ajax=true&takerCode="+takerCode;
			 	$.ajax({
					datatype: 'html',
					url: action,
					type: "post",
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
			$("#docIssuedInput").mulLineWidget({
				hiddenPlus: '0',		// 新增,是否隐藏添加一行的标志：0为显示，1为隐藏
				hiddenSubtraction: '0',	// 新增,是否隐藏删除一行的标志：0为显示，1为隐藏
				columnData: mulLineIssued.lineDataArryP,
				exeFuction: mulLineIssued.resetValP
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
					if(takerCode[0].value == "" ||  editTakerCodeName ==""){
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
					//双重循环判断同意单证是否流水号区间有重叠
					var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
					if(isOverLap){  //有重叠
						$("#saveBut").removeAttr("disabled");
						$("#submitBut").removeAttr("disabled");
						return;
					}
			   
					var jsonStr = JSON.stringify(jsonArr);
					var formStr = "";
					
					formStr = formStr + "&takerCode="+takerCode[0].value+"&acceptOrgCode="+acceptOrgCode+"&jsonStr="+jsonStr;
					var action = "";
					if("P"==actionType ){
						action = $.taHash.get("ctx")+"/servantDocManageJson/saveServantDocIssued.do?ajax=true";
					}else if("R"==actionType ){
						action = $.taHash.get("ctx")+"/servantDocManageJson/saveServantDocRecovery.do?ajax=true";
					}
					// common.ajaxSubmitAndCloseDialog("docIssuedAddPage",action
					// ,formStr);
					common.ajaxSubmitDfCallBack(mulLineIssued.saveCallBack,action,formStr);
					
				}else{
					return false;
				}
			});
			
			$("#saveBackBtn").click(function(){
				$('#docIssuedAddPage').dialog('close');
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
			}else if(objId == "endNum"||objId == "startNum"){
				//common.totalNum(rIndex,"docNum","docIssuedInput",objId);
				 common.autoLengthAndTotalNum(rIndex,"docNum","docIssuedInput",objId,8);
				/*var start = $("#" + "startNum__" + rIndex).val();
				var end = $("#" + "endNum__" + rIndex).val();
				var total = Number(end) - Number(start) + 1;
				if(total<=0){
					alert("起始流水号大于终止流水号，请修改");
					return;
				}
				$("#inStoreInput").mulLineWidget.resetVal(rIndex, "docNum", total);*/
				// condition = $("#" + "docVerCode__" + rIndex).val();
			}
			//MODIFY BY zhxiao VC-112 使用人单证批量发放、回收 BEGIN
			if(objId == "docVerCode"){
				var docVerCode = $("#" + "docVerCode__" + rIndex).val();
				var orgCode = $("#takerOrgCode").val();
				var editTakerCode = $("#editTakerCode").val();
				var action = $.taHash.get("ctx")+"/servantDocManageJson/findMaxStoreTime.do?ajax=true&mngType=1&maxStoreType=maxStoreTime&docVerCode="+docVerCode+"&orgCode="+orgCode+"&editTakerCode="+editTakerCode;
				$.ajax({
					datatype: 'html',
					url: action,
					type: "post",
					success: function(data){
						if(data != null && data != ""){
							$("#" + "deadline__" + rIndex).val(data);
						}
					}
				})

			}
			
			// 本级有效库存
			if(objId == "storage"){
				var docVerCode = $("#" + "docVerCode__" + rIndex).val();
				var deadLine = $("#" + "deadline__" + rIndex).val();

				if(docVerCode==null ||docVerCode=="") {
					alert("请选择单证类型");
					return;
				} else {
				//	var orgCode = $("#takerOrgCode").val();
					$("#validStorageQuery_docVerCode").val(docVerCode);
				//	$("#validOrg").val(orgCode);
				    $("#validOrg").val(""); 
				    //add by chy 20130916 begin
					$("#rowIndex").val(rIndex);
					$("#tableName").val("docIssuedInput");
				    //add by chy 20130916 end
					
					$("#validDeadline").val(deadLine);
					
					$("#hiddenQueryBtn").click();
					setTimeout(function(){
						//add by chy 20130916
						$("#selectStorageRec").removeAttr("disabled");
						$('#validStorageDiv').dialog('open');
					},300);
				}
			}
			//MODIFY BY zhxiao VC-112 使用人单证批量发放、回收 END

		}
	}
