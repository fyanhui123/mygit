var mulLine = {
		
		lineDataArry : [
			{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldLableName:"docVerName",width:"200",fieldClass:"required",type:"2",
				dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no&docType=1", height:'210px', width:'300px',minLength:0}]},
			// url:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no"
			{title:"印刷批次/发票代码",fieldId:"pressBatchNo",fieldName:"pressBatchNo",width:"100px",fieldClass:"required noBlankSpace",maxlength:"16",type:"4"},											
			{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",width:"100px",fieldClass:"required noBlankSpace",type:"4",minlength:"8",maxlength:"8"},
			{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",width:"100px",fieldClass:"required noBlankSpace",type:"4",minlength:"8",maxlength:"8"},
			{title:"入库数量",fieldId:"totalAmount",fieldName:"totalAmount",width:"100px",fieldClass:"required digits",type:"4",disabled:"disabled"},
			{title:"印刷厂/税务部门",fieldId:"printeryCode",fieldName:"printeryCode",fieldLableName:"printeryName",type:"3",width:"180px",
				dataFormat:[{url:"/visa/baseinfoJson/printeryJsonListWithCondition.do?ajax=true",height:'100px', width:'180px',minLength:1}]},
			{title:"单价",fieldId:"unitPrice",fieldName:"unitPrice",width:"50px",fieldClass:"required number",type:"4",disabled:"disabled"},
			{title:"费用",fieldId:"printingFee",fieldName:"printingFee",width:"100px",fieldClass:"required number",type:"4",disabled:"disabled"}
			],

		initPageEdit: function(){
			$("#editForm").validate();
			/**
			 * 初始化多行输入域
			 */
			/*
			 * $("#inStoreInput").mulLineWidget({ hiddenPlus: '0',
			 * //新增,是否隐藏添加一行的标志：0为显示，1为隐藏 hiddenSubtraction: '0',
			 * //新增,是否隐藏删除一行的标志：0为显示，1为隐藏 lineData: mulLine.lineDataArry,
			 * //isHaveTitle:'0', //initDate: '[{"fieldName":""}]' });
			 */
			var initJson = $("#edit_hiddenInStoreDet").html();
			
			$("#inStoreInput").mulLineWidget({
				columnData: mulLine.lineDataArry,
				initJsonDate: initJson,
				exeFuction: mulLine.resetVal
			});
	
			/**
			 * 保存按钮添加事件
			 */
			$("#saveBut").click(function(){
				saveDocInStore("save");
			});
			
			$("#submitBut").click(function(){
				saveDocInStore("submit");
			});
			
			$("#saveBackBtn").click(function(){
				//$('#docInStoreEditPage').dialog('close');
				common.localHref($.taHash.get("ctx")
						+ "/store/initDocInStoreQuery.do");
			});
			
			
			function saveDocInStore(actionType2){
				if($("#editForm").validate().form()){
				
				    //  防止重复提交
					$("#saveBut").attr("disabled",true);
					$("#submitBut").attr("disabled",true);
					
					var jsonArr = $("#inStoreInput").mulLineWidget.getData();
					if(jsonArr.length==0){
						alert("请输入单证信息");
						$("#saveBut").removeAttr("disabled");
						$("#submitBut").removeAttr("disabled");
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
					var formStr = "?jsonStr="+jsonStr;
					formStr = formStr + "&actionType2="+actionType2;
					var action = $.taHash.get("ctx")+"/store/saveDocInStore.do"+formStr;
					var fm = document.getElementById("editForm");
					fm.action = action;
					common.formSubmit("editForm", $.taHash.get("ctx")+"/store/saveDocInStore.do"+formStr);
					
					
					//var tmpStr = $("#editForm").serialize();
					//common.ajaxSubmitAndCloseDialog("docInStoreEditPage",action,tmpStr);
					//fm.submit();
					// common.ajaxSubmitDfCallBack(mulLine.callBack,action,
					// formStr);
				}else{
					return false;
				}
			}
		},
		callBack:function(data){
			alert(data);
			
			if(data == "操作成功"){
				$("#docInStoreEditPage").dialog("close");
				
				$("#saveBut").removeAttr("disabled");
			    $("#submitBut").removeAttr("disabled");
				
				//docInStore.gridReload();
			}
		},
		
		resetVal : function (rIndex, objId){
			/**
			 * @description 更改字段值
			 * @param 参数1(org）
			 *            要更改值的标签Id
			 * @param 参数2(2）
			 *            要更改值的标签value
			 * 
			 */
			if(objId == "docVerCode"){

				var jsonArr = $("#inStoreInput").mulLineWidget.getData();
				var docVerCode = jsonArr[rIndex-1].docVerCode;
				$("#" + "printeryCode__" + rIndex).dropDownList({ 
				   url: "/visa/baseinfoJson/printeryJsonListWithCondition.do?ajax=true&docVerCode="+docVerCode
				});
				if(printeryCode == null || printeryCode == ""){
				  $("#inStoreInput").mulLineWidget.resetVal(rIndex, "unitPrice", "0.00");
				  $("#inStoreInput").mulLineWidget.resetVal(rIndex, "printingFee", "0.00");
				}
			}
			if(objId == "endNum"||objId == "startNum"){
				common.autoLengthAndTotalNum(rIndex,"totalAmount","inStoreInput",objId,8);
				//common.totalNum(rIndex,"totalAmount","inStoreInput",objId);
			    var start = $("#" + "startNum__" + rIndex).val();
				var end = $("#" + "endNum__" + rIndex).val();
				if(start.length>10){
				   alert("输入的起始流水号长度不能大于10!");
				   return ;
				}
				if(end.length>10){
				   alert("输入的终止流水号长度不能大于10!");
				   return ;
				}
				/*
				var total = 0;
				if(end != ""){
					var result = common.isAvaNum(start,end);
					if(isNaN(Number(result))){
						alert(result);
						$("#" + "totalAmount__" + rIndex).val("");
						return;
					}
					total = result;
					if(total<=0){
						alert("起始流水号大于终止流水号，请修改");
						$("#" + "totalAmount__" + rIndex).val("");
						return;
					}
				}
				$("#inStoreInput").mulLineWidget.resetVal(rIndex, "totalAmount", total);*/
				// condition = $("#" + "docVerCode__" + rIndex).val();
			}
			if(objId == "printeryCode"){
				
				var printeryCode = $("#" + "printeryCode__" + rIndex).val();
				
				if(printeryCode == null || printeryCode == ""){
				  //	return;
				  $("#inStoreInput").mulLineWidget.resetVal(rIndex, "unitPrice", "0.00");
				  $("#inStoreInput").mulLineWidget.resetVal(rIndex, "printingFee", "0.00");
				  return;
				}
				
				var docVerCode = $("#" + "docVerCode__" + rIndex).val();
				if(docVerCode == null||docVerCode == ""){
					alert("请选择单证类型");
					return;
				}
				var total = $("#" + "totalAmount__" + rIndex).val();
				if(total == "" || total == null){
					alert("入库数量错误");
					return;
				}
				var action = "/visa/baseinfoJson/findUnitPrice.do?printeryCode="+printeryCode+"&docVerCode="+docVerCode;
				$.ajax({
					datatype: 'json',
					url: action,
					type: "post",
					success: function(data){
						//alert(data);
						if(data == null ||data == ""){
							alert("未找到价格记录");
							return;
						}
						$("#inStoreInput").mulLineWidget.resetVal(rIndex, "unitPrice", data);
						$("#inStoreInput").mulLineWidget.resetVal(rIndex, "printingFee", changeTwoDecimal_f(parseFloat(data)*parseFloat(total)));
					}
				});
				
			}
			
			if(objId == "pressBatchNo"){
				var pressBatchNo = $("#" + "pressBatchNo__" + rIndex).val();
				if(pressBatchNo.length > 16){
					alert("印刷批次最长16位");
					$("#" + "pressBatchNo__" + rIndex).val("");
				};
			}
		}
	}
/**
 * 功能：将浮点数四舍五入，取小数点后2位，如果不足2位则补0,这个函数返回的是字符串的格式 
 * 用法：changeTwoDecimal(3.1415926) 返回 3.14 
 *       changeTwoDecimal(3.1) 返回 3.10
 * 
 * @param x
 * @returns
 */
function changeTwoDecimal_f(x) {
	var f_x = parseFloat(x);
	if (isNaN(f_x)) {
		// alert('function:changeTwoDecimal->parameter error');
		return false;
	}
	f_x = Math.round(f_x * 100) / 100;
	var s_x = f_x.toString();
	var pos_decimal = s_x.indexOf('.');
	if (pos_decimal < 0) {
		pos_decimal = s_x.length;
		s_x += '.';
	}
	while (s_x.length <= pos_decimal + 2) {
		s_x += '0';
	}
	return s_x;
}