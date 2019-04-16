
function changeType(value){
	   common.clearGridData('docMngRuleGrid');
    	switch(value){
    		case "0" :	
    			$("#typeOrgCode").show();
    			$("#typeOrgCode").attr("class","ui-tianan-input-text required");
    			$("#typeTakerCode").hide();  
    			$("#typeTakerCode").attr("class","ui-tianan-input-text");
    			$("#vcDocMngRuleDtoOrgCode").val("");
    			$("#vcDocMngRuleDtoOrgCodeName").val("");
    			$("#vcDocMngRuleDtoTakerCode").val("");    			
    			break;
    		case "1" :  
    			$("#typeOrgCode").hide();
    			$("#typeOrgCode").attr("class","ui-tianan-input-text");
    			$("#typeTakerCode").show();  
    			$("#typeTakerCode").attr("class","ui-tianan-input-text required");
    			$("#vcDocMngRuleDtoOrgCode").val("");
    			$("#vcDocMngRuleDtoOrgCodeName").val("");
    			$("#vcDocMngRuleDtoTakerCode").val("");
    			break;
  }
};

//当前屏幕宽度
var windowWidth=0;
var curMaxStoreTime =1;
var curMaxStore =1;
    
var docMngRule = {
	  
	    lineDataArry123 : [ 		        			
		    			{title:"单证类型代码",fieldId:"docVerCode",fieldName:"docVerCode",width:0.22,disabled:"disabled",type:"4"},
		    			{title:"单证类型名称",fieldId:"docVerName",fieldName:"docVerName",width:0.42,disabled:"disabled",type:"4"},
		        		{title:"库存上限",fieldId:"maxStore",fieldName:"maxStore",width:0.16,fieldClass:"number",min:0,max:200,type:"4"},
		        		{title:"最长库存时间",fieldId:"maxStoreTime",fieldName:"maxStoreTime",width:0.16,fieldClass:"number",min:0,max:200,type:"4"},
		        		{title:"是否启用",fieldName:"isChecked",fieldId:"isChecked",width:0.08,fieldClass:"required",type:"3",
		        			dataFormat:[{selectedValue:"0", data:[{"value":"0","label":" <font color='red'>否</font>"},{"value":"1","label":"是"}]}] }
	                    ],
     lineDataArry : [ 		        			
	    			{title:"单证类型代码",fieldId:"docVerCode",fieldName:"docVerCode",width:0.22,disabled:"disabled",type:"4"},
	    			{title:"单证类型名称",fieldId:"docVerName",fieldName:"docVerName",width:0.42,disabled:"disabled",type:"4"},
	        		{title:"库存上限",fieldId:"maxStore",fieldName:"maxStore",width:0.16,fieldClass:"required number",minNum:0,maxNum:200,type:"4"},
	        		{title:"最长库存时间",fieldId:"maxStoreTime",fieldName:"maxStoreTime",width:0.16,fieldClass:"required number",minNum:0,maxNum:200,type:"4"},
	        		{title:"是否启用",fieldName:"isChecked",fieldId:"isChecked",width:0.08,fieldClass:"required",type:"3",
	        			dataFormat:[{ data:[{"value":"0","label":" <font color='red'>否</font>"},{"value":"1","label":"是"}]}] }
                    ],
        lineDataArryBase : [ 		        			
		    			{fieldId:"docVerCode",width:0.16},
		    			{fieldId:"docVerName",width:0.35},
		        		{fieldId:"maxStore",width:0.12},
		        		{fieldId:"maxStoreTime",width:0.12},
		        		{fieldName:"isChecked",width:0.06 }
	                    ],
	
	loadConfigGridUrl : "/baseinfoJson/queryListAndSetDefaultConfig.do?ajax=true",
	
	freshCallBack: function(data) {
		alert(data);	
		//起用
		$("#saveConfigBut").removeAttr("disabled");
		
		$("#docMngList").next().remove();
	},
	
	initPage : function() {
		 //机构选择
		$("#vcDocMngRuleDtoOrgCode").dropDownDivList({
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=vcDocMngRuleDtoOrgCode&tagNameId=vcDocMngRuleDtoOrgCodeName"
		});
		
		/**
		 * 使用人自动完成组建
		 */
		$("#vcDocMngRuleDtoTakerCode").taAutoComplete({
			source : "/visa/baseinfoJson/getJsonVcTaker.do?ajax=true",
			minLength : 0,
			height : 210,
			width : 220
		});
		
		//单证种类
		$( "#docQueryDtoDocTypeList" ).dropDownList({
			dataField:'value',
			dataType:'json',		  
			url:  $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&valueType=code&trigger=no"			
		});
		//单证类型
		$("#docQueryDtoDocVersionInfoList").dropDownList({
			trigger: '#docQueryDtoDocTypeList',    
			event: 'change',
			dataField:'name',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true"
		}); 
		
		//单证险类
		$("#docQueryDtoInsuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true"
		}); 
		
		
		//单证险种
		$("#docQueryDtoInsuKindList").dropDownList({
			trigger: '#docQueryDtoInsuTypeList',    
			event: 'change',
			dataField:'name',
			dataType:'json',
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true"
		    //url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true&insuTypeCode="+$("#docQueryDtoInsuTypeList").val()
		}); 
		
		
		$("#mainForm").validate();
		
		
		 //重置按钮		
		$("#resetBut").click(function() {
			//删除原来数据
			$("#docMngList").next().remove();
		});
		
		   //提交保存配置		
		$("#saveConfigBut").click(function() {
			/*var records=$("select[plugType='3'][value='1']");
			var ttt=$("#tabledocMngList > select");
			var arrtemp=new Array();
			records.each(function(index,obj){
				var tempobj=new Object();
				//获取行号
				var rowId=($(obj).attr('id')).split("__")[1];
			
				tempobj.docVerCode = $("#" + "docVerCode__" + rowId).val();
				tempobj.maxStore = $("#" + "maxStore__" + rowId).val();
				tempobj.maxStoreTime = $("#" + "maxStoreTime__" + rowId).val();
					arrtemp.push(tempobj);
			});*/
			
			
		  if($("#mainForm").validate().form()){		

			 if($("#takerType").attr("checked")=="checked"){
					var takerCode=$("#vcDocMngRuleDtoTakerCode").val();
					$("#vcDocMngRuleDtoMngObjectCode").val(takerCode);	
				}else{
					var orgCode=$("#vcDocMngRuleDtoOrgCode").val();
					$("#vcDocMngRuleDtoMngObjectCode").val(orgCode);
			}
			/*var nTrs= $("table[id='tabledocMngList']>tbody").children("tr");
			if($(nTrs).length<=1){
				alert("没有数据，不能提交！");
				return;
			}*/
			//管控信息		
			var jsonArr = $("#docMngList").mulLineWidget.getData();
			if(jsonArr.length<1){
				alert("没有数据，不能提交！");
				return;
			}
				
			if(confirm("确定提交？")){
				//禁用
				$("#saveConfigBut").attr("disabled",true);
					
				//var jsonArr = $("#docMngList").mulLineWidget.getData();
				var submitArr=new Array();
				for(var i=0;i<jsonArr.length;i++){
					if(jsonArr[i].isChecked=='1'){
						delete jsonArr[i].docVerName;
						submitArr.push(jsonArr[i]);
					}
				}
				var jsonData = JSON.stringify(submitArr);				
				var mainParam = $("#mainForm").serialize()+"&jsonData="+jsonData;
				setTimeout(function(){
					$("#saveConfigBut").removeAttr("disabled");
				},500)
				common.ajaxSubmitDfCallBack(
						 docMngRule.freshCallBack, 
						 $.taHash.get("ctx")+"/baseinfoJson/saveDocMngRuleList.do?ajax=true", 
						 mainParam );
			}
		  }else{			  
			 return false;
		  }
			
		});
		
		/**
		 * @description 查询按钮事件
		 * @param queryDoc
		 *            查询按钮ID
		 * @return
		 */
		
       $("#queryDoc").click(function() {			
				//执行查询前先查找默认的最大库存量及最大库存时间
				var MngObjectCode=$("#vcDocMngRuleDtoOrgCode").val();
				 if($("#takerType").attr("checked")=="checked"){
					MngObjectCode=$("#vcDocMngRuleDtoTakerCode").val();
				}
				 if(MngObjectCode=='' || MngObjectCode==null){
					 alert("请选择机构或使用人！");
					 return false;
				 }
				url= $.taHash.get("ctx")+"/baseinfoJson/getMngDefaultDate.do?ajax=true&MngObjectCode="+MngObjectCode;
				//查询当前配置机构、人员的做大库存量及库存时间
				$.post(url,
						"",
						function(date){
					     curMaxStoreTime=date.defaultMaxStoreAndStoreTime[1];
					     curMaxStore=date.defaultMaxStoreAndStoreTime[0];
					     setTimeout(function(){
					    	 docMngRule.reloadMulLine();
					     },50);
					     setTimeout(function(){
					    	 $("#mainForm").validate();
					     },200);
					     
				        });
			
		});
		
	},
	


	
	reloadMulLine : function() {
		//删除原来数据
		$("#docMngList").next().remove();
		windowWidth=$("#docPowerLimitListPage").width();
		if($("#takerType").attr("checked")=="checked"){
			var takerCode=$("#vcDocMngRuleDtoTakerCode").val();
			$("#vcDocMngRuleDtoMngObjectCode").val(takerCode);	
		}else{
			var orgCode=$("#vcDocMngRuleDtoOrgCode").val();
			$("#vcDocMngRuleDtoMngObjectCode").val(orgCode);
		}	
		for(var i=0;i<docMngRule.lineDataArry.length;i++){
			temp=docMngRule.lineDataArry[i];
			temp.width=docMngRule.lineDataArryBase[i].width*windowWidth+"px";;
			if(temp.fieldId=="maxStoreTime"){
				temp.maxNum=curMaxStoreTime;
			}else{
			  if(temp.fieldId=="maxStore"){
				temp.maxNum=curMaxStore;
			  }
			}
		}
		var tmpStr = $("#mainForm").serialize();	
		var gridReloadUrl = $.taHash.get("ctx")+"/baseinfoJson/queryMngDefaultListForMulLine.do?ajax=true&random="+Math.random()+"&"+tmpStr;
	  $("#docMngList").mulLineWidget({
		  hiddenPlus: '1',
		  hiddenSubtraction: '1',
	      columnData: docMngRule.lineDataArry,
	      exeFuction: docMngRule.resetVal,
		  initDataUrl: gridReloadUrl
       });
	
	 
	},
	
	//数值校验
	resetVal : function (rIndex, objId){/*	
		
		if(objId == "maxStore"){
			var storeTemp = $("#" + "maxStore__" + rIndex).val();
			var stdStoreValue= parseFloat(curMaxStore);
			if(storeTemp!=""){
				 var inputValue= parseFloat(storeTemp);
				 if(stdStoreValue<inputValue){
					 $("#" + "maxStore__" + rIndex).val("");
					 alert("不能超过库存上限最大值["+stdStoreValue+"]");
				 }
			}
		}
		if(objId == "maxStoreTime"){
			var storeTimeTemp = $("#" + "maxStoreTime__" + rIndex).val();
			var stdStoreTimeValue= parseFloat(curMaxStoreTime);
			if(storeTimeTemp!=""){
				 var inputTimeValue= parseFloat(storeTimeTemp);
				 if(stdStoreTimeValue<inputTimeValue){
					 $("#" + "maxStoreTime__" + rIndex).val("");
					 alert("不能超过最长库存时间["+stdStoreTimeValue+"]");
				 }
			}
		}
	*/}
	
	
}
