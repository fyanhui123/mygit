 var insuCardSalesView = {
	lineDataArry : [
	    			/*{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldClass:"required",type:"3",disabled:"disabled",
	    				dataFormat:[{url: "/visa/baseinfoJson/getJSONUserSend.do?trigger=no&style=auto&docTypeCode=16",
	    					height:'210px', minLength:0}]},*/
					{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldLableName:"docVerName",fieldClass:"required",disabled:"disabled", width:"180px",type:"2",
						dataFormat:[{source:"/visa/baseinfoJson/getJSONUserSend.do?ajax=true&style=auto&trigger=no&docTypeCode=JK"
							, height:'210px',minLength:0}]},
	    			{title:"印刷批次",fieldId:"pressBatchNo",fieldName:"pressBatchNo",fieldClass:"required digits",type:"4",disabled:"disabled"},											
	    			{title:"起始流水号",fieldId:"startNum",fieldName:"startNum",fieldClass:"required",type:"4",disabled:"disabled"},
	    			{title:"终止流水号",fieldId:"endNum",fieldName:"endNum",fieldClass:"required",type:"4",disabled:"disabled"},
	    			{title:"数量",fieldId:"saleNum",fieldName:"saleNum",fieldClass:"required digits",type:"4",disabled:"disabled"}
	    			],
		initPage: function(){
			/**
			 * 初始化多行输入域
			 */
			$("#insuCardDetList").mulLineWidget({
				hiddenPlus: '1',		// 新增,是否隐藏添加一行的标志：0为显示，1为隐藏
				hiddenSubtraction: '1',	// 新增,是否隐藏删除一行的标志：0为显示，1为隐藏
				columnData: insuCardSalesView.lineDataArry,
				exeFuction: insuCardSalesView.resetValP,
				initJsonDate: $("#detListJsonId").html()
			});
			
			$("#returnBtn").click(function(){
				$('#insuCardSalesViewPage').dialog('close');
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
				//common.totalNum(rIndex,"saleNum","insuCardDetList",objId);
			}
		}
	}
