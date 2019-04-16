var docVersionInfoEdit = {
	loadGridUrl : "/baseinfoJson/queryVcDocVersionInfoByPages.do?ajax=true",
	
	lineDocPrtNoRuleArry : [ 	        		
	        		{title:"元素顺序",fieldId:"sortNum",fieldName:"sortNum",width:"80px",fieldClass:"required digits",type:"4"},
	        		/*{title:"元素项",fieldId:"itemTypeCode",fieldName:"itemTypeCode",fieldLableName:"itemTypeName",width:"90px",inputClass:"required",type:"2",
	    				dataFormat:[{source: "/baseinfoJson/queryVcDocVerLabels.do?ajax=true",minLength:0}]},	 */  
	        		{title:"元素项",fieldId:"itemTypeCode",fieldName:"itemTypeCode",type:"3",
						width:"80px",fieldClass:"required",
						dataFormat:[{selectedValue:"P", data:[{value:"P",label:"P-险种代码"},
												{value:"V",label:"V-版本"},
												{value:"Y",label:"Y-年份"},	        																						
												{value:"S",label:"S-流水号"}]}]},  
	        		/*{title:"元素项",fieldId:"itemTypeCode",fieldName:"itemTypeCode",type:"3",
						width:"80px",fieldClass:"required",
						dataFormat:[{url:"/visa/baseinfoJson/initDownList.do?codeType=ItemType&ItemType=query"}]},*/
	        		{title:"元素值",fieldId:"itemValue",fieldName:"itemValue",width:"80px",type:"4"},
	    			{title:"元素长度",fieldId:"itemLength",fieldName:"itemLength",width:"80px",fieldClass:"required digits",type:"4"}
	        	    ],
	          	
  	statusData : [{
  		"label" : "启用",
		 "value" : "1"
	 },{
		 "label" : "停用",
		 "value" : "0"
	} ],
	
	isAutoGenNoData : [ {
  		"label" : "系统自动生成",
		 "value" : "1"
	 },{
		 "label" : "手工生成",
		 "value" : "0"
	} ],
	
	//是否征订
	isOrderData : [ {
  		"label" : "是",
		 "value" : "1"
	 },{
		 "label" : "否",
		 "value" : "0"
	} ],
	
	//新增、修改保存失败提示信息
	alertErrorMsg:function(hasError,msg){
		if(hasError == 'true'){
			alert(msg);
		}
	},
	
	resetVal : function (rIndex, objId){
		
		if(objId == "itemTypeCode"){
			//var obj = $("#docPrtNoRuleList").mulLineWidget.getData(rIndex)[0];
			var arrObj = $("#docPrtNoRuleList").mulLineWidget.getData();
			//alert(obj.sortNum);
			//alert(rIndex+">>>"+JSON.stringify(arrObj[rIndex-1]));
			var obj=arrObj[rIndex-1];
			var lengthValue=0;
			if(obj.itemTypeCode=='P'){
				lengthValue	=4;
			}
			else if(obj.itemTypeCode=='V'){
				lengthValue=2;
			}
			else if(obj.itemTypeCode=='Y'){
				lengthValue=2;
			}
			else if(obj.itemTypeCode=='S'){
				lengthValue=8;
			}
			$("#docPrtNoRuleList").mulLineWidget.resetVal(rIndex, "itemLength", lengthValue);
		}
	},
	
	initEditPage : function(){
		$("#docVersionInfoDtoIdVcDocTyp").dropDownList({			
			dataField:'value',
			dataType:'json',
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&trigger=no"		   
		}); 
		
		//状态
		$("#docVersionInfoDtoStatus").dropDownList( {
			data : docVersionInfoEdit.statusData
		});
		
		//自动流水生成标志
		$("#docVersionInfoDtoIsAutoGenNo").dropDownList( {
			data : docVersionInfoEdit.isAutoGenNoData
		});
		
		//是否征订
		$("#docVersionInfoDtoIsOrder").dropDownList( {
			data : docVersionInfoEdit.isOrderData
		});
		
		//联次
		$("#docVersionInfoDtoDocCountCode").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=DocCountCode&actionType=query"
		});
		
		
		if($("#actionType").val() == "insert"){
		   $("#docVersionInfoDtoStatus").dropDownList("selected",1);		   
		   $("#docPrtNoRuleList").mulLineWidget({
				columnData: docVersionInfoEdit.lineDocPrtNoRuleArry,
				exeFuction: docVersionInfoEdit.resetVal 
			});
		}else{
			//i$("#actionType").val() == "update"
			//设置默认值	
			setTimeout(function(){	
				$("#docVersionInfoDtoIdVcDocTyp").dropDownList("selected",$("#hide_docVersionInfoDtoIdVcDocTyp").val()); 
				$("#docVersionInfoDtoIsAutoGenNo").dropDownList("selected",$("#hide_docVersionInfoDtoIsAutoGenNo").val());
				$("#docVersionInfoDtoDocCountCode").dropDownList("selected",$("#hide_docVersionInfoDtoDocCountCode").val());
				$("#docVersionInfoDtoIsOrder").dropDownList("selected",$("#hide_docVersionInfoDtoIsOrder").val());
				$("#docVersionInfoDtoStatus").dropDownList("selected",$("#hide_docVersionInfoDtoStatus").val());
				
			   $("#docPrtNoRuleList").mulLineWidget({
					columnData: docVersionInfoEdit.lineDocPrtNoRuleArry,
					exeFuction: docVersionInfoEdit.resetVal,
					initDataUrl: $.taHash.get("ctx")+"/baseinfoJson/queryJsondocPrtNoRuleListByDocId.do?ajax=true&idVcDocVersionInfo="+$("#currentIdVcDocVersionInfo").val()
				 });
				$("#docVersionInfoDtoDocVerCode").attr('disabled','disabled');
			},800);
		};
		
		$("#docVersionInfoForm").validate();

		
		/**
		* @description 添加按钮事件
		* @param  saveBut 添加按钮ID
		* @return 
		*/
		$("#saveDocVersionInfoBut").click( function() {
			if($("#docVersionInfoForm").validate().form()){
				//单证管控规则信息
				var ss=$("input[name='maxStore'][index='1']").val();
				var mngArr=new Array();			
				for(var i=1;i<=6;i++){
					var obj=new Object();
					var selecter1="#maxStore_"+i;
					var selecter2="#maxStoreTime_"+i;
					obj.maxStore=$(selecter1).val();
					obj.maxStoreTime=$(selecter2).val();
					obj.levelNo=i;
					mngArr[i-1]=obj;
				}
				$("#jsonMngDate").val(JSON.stringify(mngArr));
				
				var jsonArr = $("#docPrtNoRuleList").mulLineWidget.getData();
				if(jsonArr && jsonArr.length>0){				
					var strValue="";
					var strLength=0;
					var isTrue=true;
					var msg="";
					var iteamName="";
					var itemTypeArr = new Array(); //判断类型是否重复
					var sortNumArr=new Array(); //判断排序编号是否重复且连续
					for(var i=0;i<jsonArr.length;i++){
						itemTypeArr.push(jsonArr[i].itemTypeCode);
						sortNumArr.push(jsonArr[i].sortNum);
						strValue=jsonArr[i].itemValue;
						strLength+=parseInt(jsonArr[i].itemLength);					
						if(strValue.length!=jsonArr[i].itemLength){
							isTrue=false;
							if(jsonArr[i].itemTypeCode=="S"){
								iteamName="流水号"
							}
							if(jsonArr[i].itemTypeCode=="V"){
								iteamName="版本号"
							}
							if(jsonArr[i].itemTypeCode=="P"){
								iteamName="险种代码"
							}
							if(jsonArr[i].itemTypeCode=="Y"){
								iteamName="年份"
							}
							msg=msg+iteamName+'元素值长度不正确！\r'
						}
					}					
					if(!isTrue){
						alert("印刷流水规则：\r"+msg);
						return false;
					}
					if (common.isRepeat("存在重复流水规则元素项！", itemTypeArr)) {
						return false;
					}
					if(strLength>16){
						alert("印刷流水规则：印刷流水总长度不能大于16！");
						return false;
					}
					if (common.isRepeat("元素顺序编号存在重复！", sortNumArr)) {
						return false;
					}
					sortNumArr.sort();					
					if (sortNumArr.length!=sortNumArr[sortNumArr.length-1]) {
						alert("元素顺序编号不连续或不是从1开始！");
						return false;
					}
				}
				////保存按钮禁用
				$("#saveDocVersionInfoBut").attr("disabled",true);
				
				//单证承印信息
				var jsonData = JSON.stringify(jsonArr);
				//主表数据
				var mainParam = $("#docVersionInfoForm").serialize();	
				var param= mainParam+"&jsonData="+jsonData;	
				
				var actionType=$("#actionType").val();
				var url="";
				if(actionType=="insert"){
					url=$.taHash.get("ctx")+"/baseinfo/saveVcDocVersionInfo.do?jsonData="+jsonData;	
				}else{
					url=$.taHash.get("ctx")+"/baseinfo/updateVcDocVersionInfo.do?jsonData="+jsonData;	
				}	
				 common.formSubmit("docVersionInfoForm", url);	
				
			}else{
				 return false;
			}
		});
		
		/**
		* @description 返回查询页面事件
		* @param  backBut 返回按钮ID
		* @return 
		*/
		$("#backToDocVersionInfoMainBut").click( function() {
			url=$.taHash.get("ctx")+"/baseinfo/docVersionInfoMain.do";
			$('#backMainForm').attr("action",url);
			$('#backMainForm').submit();
		});
		
	}
}