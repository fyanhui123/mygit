 var insuCardRefundEdit = {

		lineDataArry : [
			/*{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldClass:"required",type:"3",
				dataFormat:[{url: "/visa/baseinfoJson/getJSONUserSend.do?trigger=no&&style=autodocTypeCode=16",
					height:'210px', minLength:0}]},*/
			{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldLableName:"docVerName",fieldClass:"required",	width:"180px",type:"2",
				dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no&docTypeCode=JK"
					, height:'210px',minLength:0}]},
			{title:"印刷批次",fieldId:"pressBatchNo",fieldName:"pressBatchNo",fieldClass:"required noBlankSpace",type:"4"},											
			{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",fieldClass:"required noBlankSpace",type:"4",minlength:"10",maxlength:"10"},
			{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",fieldClass:"required noBlankSpace",type:"4",minlength:"10",maxlength:"10"},
			{title:"数量",fieldId:"saleNum",fieldName:"saleNum",fieldClass:"required digits",type:"4",disabled:"disabled"}
			],
		initPage: function(){
	 
	        //退卡人类型
	        $("#bankInfoDtoPayeeType").dropDownList("selected",$("#bankInfoDtoPayeeType_hidden").val()); 
	
			$("#insuCardRefundEditForm").validate();
			/**
			 * 初始化多行输入域
			 */
			$("#insuCardDetList").mulLineWidget({
				hiddenPlus: '0',		// 新增,是否隐藏添加一行的标志：0为显示，1为隐藏
				hiddenSubtraction: '0',	// 新增,是否隐藏删除一行的标志：0为显示，1为隐藏
				columnData: insuCardRefundEdit.lineDataArry,
				exeFuction: insuCardRefundEdit.resetValP,
				initJsonDate: $("#detListJsonId").html()
			});
			
			/**
			 * 保存按钮添加事件
			 */
			$("#saveBut").click(function(){
				//保存
				insuCardRefundEdit.clickButDeal("1","saveBut");
			});
		   
		
			/**
			 *提价按钮添加事件
			 */
			$("#submitBut").click(function(){
				//提交
				insuCardRefundEdit.clickButDeal("2","submitBut");
			});
				
			
			
			$("#cancelBtn").click(function(){
				url = $.taHash.get("ctx")+"/insucard/initInsuCardRefundQuery.do";
				window.location.href=url;
			});
			
			//add by chy 20131025 begin
			$("#businessMode").dropDownList("selected",$("#businessModeDropDownList").val());
			if("2" == $("#businessModeDropDownList").val()){
				$("#agreementNoTD1").show();
				$("#agreementNoTD2").show();
			}else{
				$("#agreementNoTD1").hide();
				$("#agreementNoTD2").hide();
			}
			//add by chy 20131025 end
		},
		/**
		 * 保存按钮添加事件
		 */
		clickButDeal:function(type,butid){
			if($("#insuCardRefundEditForm").validate().form()){
				 //  防止重复提交
				$("#saveBut").attr("disabled",true);
				$("#submitBut").attr("disabled",true);
				var jsonArr = $("#insuCardDetList").mulLineWidget.getData();
				if(jsonArr.length==0){
					$("#saveBut").removeAttr("disabled");
					$("#submitBut").removeAttr("disabled");
					alert("请输入单证信息");
					return;
				}
				//双重循环判断同意单证是否流水号区间有重叠
				var isOverLap = common.checkOverLap(jsonArr,"docVerCode","pressBatchNo","startNum","endNum");
				if(isOverLap){  //有重叠
					$("#saveBut").removeAttr("disabled");
					$("#submitBut").removeAttr("disabled");
					return;
				}
				setTimeout(function(){
					$("#saveBut").removeAttr("disabled");
					$("#submitBut").removeAttr("disabled");
				},5000);
				var jsonStr = JSON.stringify(jsonArr);
				url = $.taHash.get("ctx")+"/insucard/saveInsuCardRefund.do?type="+type+"&jsonStr="+jsonStr;
				common.formSubmit("insuCardRefundEditForm", url);						
			}else{
				return false;
			}
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
			if(objId == "endNum"||objId == "startNum"){
				common.totalNum(rIndex,"saleNum","insuCardDetList",objId);
			}
		}
	}
