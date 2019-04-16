 var insuCardRefundAdd = {

		lineDataArry : [
			/*{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldClass:"required",type:"3",
				dataFormat:[{url: "/visa/baseinfoJson/getJSONUserSend.do?trigger=no&style=auto&docTypeCode=16",
					height:'210px', minLength:0}]},*/
			{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldLableName:"docVerName",fieldClass:"required", width:"180px",type:"2",
				dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no&docTypeCode=JK"
					, height:'210px',minLength:0}]},
			{title:"印刷批次",fieldId:"pressBatchNo",fieldName:"pressBatchNo",fieldClass:"required noBlankSpace",type:"4"},											
			{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",fieldClass:"required noBlankSpace",type:"4",minlength:"10",maxlength:"10"},
			{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",fieldClass:"required noBlankSpace",type:"4",minlength:"10",maxlength:"10"},
			{title:"数量",fieldId:"saleNum",fieldName:"saleNum",fieldClass:"required digits",type:"4",disabled:"disabled"}
			],
		initPage: function(){
	       //本级有效库存
			 $("#validStorageDiv").dialog({
					modal: true,
					height:430,
					width:1000,
					autoOpen: false
				});
			/**
			 * 退卡人自动完成
			 */
			
			 
			/*$("#insuCardSaleRecordVoSellerCode").taAutoComplete(
			{
				source : "/visa/baseinfoJson/getTakerJson.do?type=auto",
				minLength : 0,
				height : 210,
				width : 220
			});*/
			
			
			/*$("#insuCardSaleRecordVoSellerCode").blur(function (){
				var takerCode = $('#insuCardSaleRecordVoSellerCode').val();
				var action = "/visa/baseinfoJson/findOrgCodeJson.do?ajax=true&takerCode="+takerCode;
			 	$.ajax({
					datatype: 'html',
					url: action,
					type: "post",
					success: function(data){
						if(data != null && data != ""){
							data = eval("("+data+")");
							$("#insuCardSaleRecordVoSaleOrgCode").val(data.orgCode);
							$("#insuCardSaleRecordVoSaleOrgCodeName").val(data.orgName);
			    
						}
					}	
				})
			});*/
	
			$("#insuCardRefundAddForm").validate();
			/**
			 * 初始化多行输入域
			 */
			$("#insuCardDetList").mulLineWidget({
				hiddenPlus: '0',		// 新增,是否隐藏添加一行的标志：0为显示，1为隐藏
				hiddenSubtraction: '0',	// 新增,是否隐藏删除一行的标志：0为显示，1为隐藏
				columnData: insuCardRefundAdd.lineDataArry,
				exeFuction: insuCardRefundAdd.resetValP,
				initJsonDate: $("#detListJsonId").html()
			});
			
			/**
			 * 保存按钮添加事件
			 */
			$("#saveBut").click(function(){
				//保存
				insuCardRefundAdd.clickButDeal("1","saveBut");
			});
		   
		
			/**
			 *提价按钮添加事件
			 */
			$("#submitBut").click(function(){
				//提交
				insuCardRefundAdd.clickButDeal("2","submitBut");
			});
				
			
			
			$("#cancelBtn").click(function(){
				url = $.taHash.get("ctx")+"/insucard/initInsuCardRefundQuery.do";
				window.location.href=url;
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
			
			//add by chy 20131010 直接业务从VC_TAKER表查询，间接业务从销管系统查询 begin
			/**
			 * 业务方式下拉框事件
			 */
			/*$("#businessType").change(function(){
				var _businessType = $("#businessType").val();
				if("1"==_businessType){
					$("#typeOneTr").show();
					$("#typeTwoTr").hide();
				}else if("2"==_businessType){
					$("#typeOneTr").hide();
					$("#typeTwoTr").show();
				}
				//清空元素
				$("#insuCardSaleRecordVoSellerCode").val("");
				$("#insuCardSaleRecordVoSellerCodeName").val("");
				$("#insuCardSaleRecordVoSaleOrgCode").val("");
				$("#insuCardSaleRecordVoSaleOrgCodeName").val("");
				$("#sellerSelectTab input").val("");
			});*/
			
			/**
			 * 直接业务-销售人自动完成
			 */
			/*$("#typeOneSellerCode").taAutoComplete({
				source : "/visa/baseinfoJson/getTakerJson.do?type=auto",
				minLength : 0,
				height : 210,
				width : 220
			});
			$("#typeOneSellerCode").change(function (){
				var takerCode = $('#typeOneSellerCode').val();
				if(""==takerCode){
					//清空显示的销售机构
					$("#typeOneSaleOrgCode").val("");
					$("#typeOneSaleOrgCodeName").val("");
					//给隐藏的销售机构、销售人赋值
					$("#insuCardSaleRecordVoSaleOrgCode").val("");
					$("#insuCardSaleRecordVoSaleOrgCodeName").val("");
					$("#insuCardSaleRecordVoSellerCode").val("");
					$("#insuCardSaleRecordVoSellerCodeName").val("");
					return false;
				}
				var action = "/visa/baseinfoJson/findOrgCodeJson.do?ajax=true&takerCode="+takerCode;
			 	$.ajax({
					datatype: 'html',
					url: action,
					type: "post",
					success: function(data){
						if(data != null && data != ""){
							data = eval("("+data+")");
							$("#typeOneSaleOrgCode").val(data.orgCode);
							$("#typeOneSaleOrgCodeName").val(data.orgName);
							//给隐藏的机构赋值
							$("#insuCardSaleRecordVoSaleOrgCode").val(data.orgCode);
							$("#insuCardSaleRecordVoSaleOrgCodeName").val(data.orgName);
						}
					}
				});
			 	setTimeout(function(){
					//给隐藏的销售人赋值
					$("#insuCardSaleRecordVoSellerCode").val($("#typeOneSellerCode").val());
					$("#insuCardSaleRecordVoSellerCodeName").val($("#typeOneSellerCodeName").val());
				}, 500);
			});*/
			
			/**
			 * 间接业务-所属机构选择
			 */
			/*$("#typeTwoComCode").dropDownDivList({
				source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=typeTwoComCode&tagNameId=typeTwoComCodeName"
			});
			$("#typeTwoComCode").bind('propertychange',function(){
				var orgCode = $("#typeTwoComCode").val();
				$("#typeTwoTeamCode").taAutoComplete({
					source : "/visa/baseinfoJson/queryTeamListJson.do?ajax=true&orgCode="+orgCode,
					minLength : 0,
					height : 210,
					width : 220
				});
				//清空所属团队和销售人
				$("#typeTwoTeamCode").val("");
				$("#typeTwoTeamCodeName").val("");
				$("#typeTwoSellerCode").val("");
				$("#typeTwoSellerCodeName").val("");
			});*/
			
			/**
			 * 间接业务-所属团队自动完成
			 */
			/*$("#typeTwoTeamCode").taAutoComplete({
				source : "/visa/baseinfoJson/queryTeamListJson.do?ajax=true",
				minLength : 0,
				height : 210,
				width : 220
			});
			$("#typeTwoTeamCode").change(function(){
				var teamCode = $("#typeTwoTeamCode").val();
				$("#typeTwoSellerCode").taAutoComplete({
					source : "/visa/baseinfoJson/queryUserListJson.do?ajax=true&teamCode="+teamCode,
					minLength : 0,
					height : 210,
					width : 220
				});
				//清空销售人
				$("#typeTwoSellerCode").val("");
				$("#typeTwoSellerCodeName").val("");
			});*/
			
			/**
			 * 间接业务-销售人自动完成
			 */
			/*$("#typeTwoSellerCode").taAutoComplete({
				source : "/visa/baseinfoJson/queryUserListJson.do?ajax=true",
				minLength : 0,
				height : 210,
				width : 220
			});*/
//			$("#typeTwoSellerCode").change(function(){
//				/*setTimeout(function(){
//					//给隐藏的机构、销售人赋值
//					$("#insuCardSaleRecordVoSellerCode").val($("#typeTwoSellerCode").val());
//					$("#insuCardSaleRecordVoSellerCodeName").val($("#typeTwoSellerCodeName").val());
//					$("#insuCardSaleRecordVoSaleOrgCode").val($("#typeTwoSaleOrgCode").val());
//					$("#insuCardSaleRecordVoSaleOrgCodeName").val($("#typeTwoSaleOrgCodeName").val());
//				}, 500);*/
//				var userCode = $('#typeTwoSellerCode').val();
//				if(""==userCode){
//					//清空显示的销售机构
//					$("#typeTwoSaleOrgCode").val("");
//					$("#typeTwoSaleOrgCodeName").val("");
//					//给隐藏的销售机构、销售人赋值
//					$("#insuCardSaleRecordVoSaleOrgCode").val("");
//					$("#insuCardSaleRecordVoSaleOrgCodeName").val("");
//					$("#insuCardSaleRecordVoSellerCode").val("");
//					$("#insuCardSaleRecordVoSellerCodeName").val("");
//					return false;
//				}
//				var action = "/visa/baseinfoJson/findUserDetailJson.do?ajax=true&userCode="+userCode;
//			 	$.ajax({
//					datatype: 'html',
//					url: action,
//					type: "post",
//					success: function(data){
//						if(data != null && data != ""){
//							data = eval("("+data+")");
//							$("#typeTwoSaleOrgCode").val(data.orgCode);
//							$("#typeTwoSaleOrgCodeName").val(data.orgName);
//							//给隐藏的机构赋值
//							$("#insuCardSaleRecordVoSaleOrgCode").val(data.orgCode);
//							$("#insuCardSaleRecordVoSaleOrgCodeName").val(data.orgName);
//						}
//					}
//				});
//			 	setTimeout(function(){
//					//给隐藏的销售人赋值
//					$("#insuCardSaleRecordVoSellerCode").val($("#typeTwoSellerCode").val());
//					$("#insuCardSaleRecordVoSellerCodeName").val($("#typeTwoSellerCodeName").val());
//				}, 500);
//			});
			//add by chy 20131010 直接业务从VC_TAKER表查询，间接业务从销管系统查询 end
			
//****************add by chy 20131024 激活卡销售与销管交互改造*****begin****************
			/**
			 * 业务方式
			 */
			$("#businessMode").change(function(){
				var _businessType = $("#businessMode").val();
				if("1"==_businessType){
					$("#agencyTr").hide();
					$("#businessSource").removeAttr("disabled");
					$("#teamCode").removeAttr("disabled");
					$("#insuCardSaleRecordVoSellerCode").removeAttr("disabled");
				}else if("2"==_businessType){
					$("#agencyTr").show();
					//业务来源、团队代码、销售人由协议带出
					$("#businessSource").attr("disabled",true);
					$("#teamCode").attr("disabled",true);
					$("#insuCardSaleRecordVoSellerCode").attr("disabled",true);
				}
				//清空元素
				$("#sellerSelectTab input").val("");
			});
			/**
			 * 渠道类型自动完成
			 */
			$("#channelDetailCode").taAutoComplete({
				source : "/visa/baseinfoJson/queryChannelDetailCode.do?ajax=true&codeType=UnderWriteChannel",
				minLength : 0,
				height : 210,
				width : 220
			});
			/**
			 * 业务来源自动完成
			 */
			$("#businessSource").taAutoComplete({
				source : "/visa/baseinfoJson/queryChannelDetailCode.do?ajax=true&codeType=BusinessSource&remark=01",
				minLength : 0,
				height : 210,
				width : 220
			});
			/**
			 * 销售机构自动完成
			 */
			$("#saleOrgCode").taAutoComplete({
				source : "/visa/baseinfoJson/querySaleOrgCodeList.do?ajax=true",
				minLength : 0,
				height : 210,
				width : 220
			});
			$("#saleOrgCode").change(function(){
				var orgCode = $("#saleOrgCode").val();
				var channelDetailCode = $("#channelDetailCode").val();
				$("#teamCode").taAutoComplete({
					source : "/visa/baseinfoJson/queryTeamListJson.do?ajax=true&orgCode="+orgCode+"&channelDetailCode="+channelDetailCode,
					minLength : 0,
					height : 210,
					width : 220
				});
				$("#agreementNo").taAutoComplete({
					source : "/visa/baseinfoJson/queryAgreementNoList.do?ajax=true&orgCode="+orgCode+"&channelDetailCode="+channelDetailCode,
					minLength : 0,
					height : 210,
					width : 220
				});
				//清空业务来源、团队代码、销售人
				$("#businessSource").val("");
				$("#businessSourceName").val("");
				$("#agreementNo").val("");
				$("#teamCode").val("");
				$("#insuCardSaleRecordVoSellerCode").val("");
				$("#insuCardSaleRecordVoSellerCodeName").val("");
			});
			/**
			 * 渠道类型
			 */
			$("#channelDetailCode").change(function(){
				var orgCode = $("#saleOrgCode").val();
				var channelDetailCode = $("#channelDetailCode").val();
				$("#teamCode").taAutoComplete({
					source : "/visa/baseinfoJson/queryTeamListJson.do?ajax=true&orgCode="+orgCode+"&channelDetailCode="+channelDetailCode,
					minLength : 0,
					height : 210,
					width : 220
				});
				$("#agreementNo").taAutoComplete({
					source : "/visa/baseinfoJson/queryAgreementNoList.do?ajax=true&orgCode="+orgCode+"&channelDetailCode="+channelDetailCode,
					minLength : 0,
					height : 210,
					width : 220
				});
				//清空业务来源、团队代码、销售人
				$("#businessSource").val("");
				$("#businessSourceName").val("");
				$("#agreementNo").val("");
				$("#teamCode").val("");
				$("#insuCardSaleRecordVoSellerCode").val("");
				$("#insuCardSaleRecordVoSellerCodeName").val("");
			});
			/**
			 * 团队代码自动完成
			 */
			$("#teamCode").taAutoComplete({
				source : "/visa/baseinfoJson/queryTeamListJson.do?ajax=true",
				minLength : 0,
				height : 210,
				width : 220,
				twoInput: false
			});
			$("#teamCode").change(function(){
				var teamCode = $("#teamCode").val();
				$("#insuCardSaleRecordVoSellerCode").taAutoComplete({
					source : "/visa/baseinfoJson/queryUserListJson.do?ajax=true&teamCode="+teamCode,
					minLength : 0,
					height : 210,
					width : 220
				});
				//清空销售人
				$("#insuCardSaleRecordVoSellerCode").val("");
				$("#insuCardSaleRecordVoSellerCodeName").val("");
			});
			/**
			 * 销售人自动完成
			 */
			$("#insuCardSaleRecordVoSellerCode").taAutoComplete({
				source : "/visa/baseinfoJson/queryUserListJson.do?ajax=true",
				minLength : 0,
				height : 210,
				width : 220
			});
			/**
			 * 中介人协议号
			 */
			$("#agreementNo").taAutoComplete({
				source : "/visa/baseinfoJson/queryAgreementNoList.do?ajax=true",
				minLength : 0,
				height : 210,
				width : 220
			});
			$("#agreementNo").change(function(){
				var agreementNo = $('#agreementNo').val();
				if(agreementNo==undefined || agreementNo==""){
					//清空业务来源、团队代码、销售人
					$("#businessSource").val("");
					$("#businessSourceName").val("");
					$("#teamCode").val("");
					$("#insuCardSaleRecordVoSellerCode").val("");
					$("#insuCardSaleRecordVoSellerCodeName").val("");
					return false;
				}
				var action = "/visa/baseinfoJson/queryAgreementDto.do?ajax=true&agreementNo="+agreementNo;
			 	$.ajax({
					datatype: 'html',
					url: action,
					type: "post",
					success: function(data){
						if(data != null && data != ""){
							data = eval("("+data+")");
							$("#businessSource").val(data.agentClass);
							$("#businessSourceName").val(data.agentType);
							$("#teamCode").val(data.teamCode);
							$("#insuCardSaleRecordVoSellerCode").val(data.agentCode);
							$("#insuCardSaleRecordVoSellerCodeName").val(data.agentName);
						}
					}
				});
			});
//****************add by chy 20131024 激活卡销售与销管交互改造*****end****************
		},
		/**
		 * 保存按钮添加事件
		 */
		clickButDeal:function(type,butid){
			if($("#insuCardRefundAddForm").validate().form()){
			    //  防止重复提交
				$("#saveBut").attr("disabled",true);
				$("#submitBut").attr("disabled",true);
				
				
				// 验证通过执行的代码块
				/*var sellerCode = $("#insuCardSaleRecordVoSellerCode").val();
				var sellerName = $("#insuCardSaleRecordVoSellerCodeName").val();
				if(sellerCode == "" || sellerName==""){
					$("#saveBut").removeAttr("disabled");
					$("#submitBut").removeAttr("disabled");
					alert("退卡人不能为空");
					return;
				}
				var saleOrgCode = $("#insuCardSaleRecordVoSaleOrgCode").val();
				if(saleOrgCode==""){
					$("#saveBut").removeAttr("disabled");
					$("#submitBut").removeAttr("disabled");
					alert("所属机构不能为空");
					return;
				}*/
				var actionType=$("#actionType").val();
				var jsonArr = $("#insuCardDetList").mulLineWidget.getData();
				if(jsonArr.length==0){
					$("#saveBut").removeAttr("disabled");
					$("#submitBut").removeAttr("disabled");
					alert("请输入单证信息");
					return;
				}
				//add by chy 20131107 每次销售的激活卡总数不能超过800 begin
				var total=0;
				for(var i=0;i<jsonArr.length;i++){
					total += jsonArr[i]["saleNum"];
				}
				if(total > 800){
					alert("激活卡退卡总数不能超过800");
					$("#saveBut").removeAttr("disabled");
					$("#submitBut").removeAttr("disabled");
					return false;
				}
				//add by chy 20131107 每次销售的激活卡总数不能超过800 end
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
				/*var docVerCodeArr = new Array();
				for ( var i = 0; i < jsonArr.length; i++) {
					docVerCodeArr.push(jsonArr[i].docVerCode);
				}
				if (common.isRepeat("存在重复单证类型", docVerCodeArr)) {
					return;
				}*/
				var jsonStr = JSON.stringify(jsonArr);
				//add by chy 20131025 begin
				$("#businessSource").removeAttr("disabled");
				$("#teamCode").removeAttr("disabled");
				$("#insuCardSaleRecordVoSellerCode").removeAttr("disabled");
				//add by chy 20131025 end
				url = $.taHash.get("ctx")+"/insucard/saveInsuCardRefund.do?type="+type+"&jsonStr="+jsonStr;
				common.formSubmit("insuCardRefundAddForm", url);						
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
