/***
     // modify by ljin   修改日期  2013-5-23 14.05
     //  把单证版本号改为单证类型
**/  
var orderLaunchEdit = {   
		lineDataArry : [  
			{title:"单证类型",fieldId:"versionCode",fieldName:"versionCode",fieldLableName:"versionName",type:"2",
						width:"200px",fieldClass:"required",
						dataFormat:[{source:"/visa/baseinfoJson/queryOrderDocDropDownList.do?ajax=true",height:"210px",minLength:0}]},
			{title:"申请数量",fieldId:"orderCount",fieldName:"orderCount",type:"4",width:"180px",fieldClass:"required digits",maxlength:8,minNum:1},
			{title:"辖内有效库",fieldId:"allStore",fieldName:"allStore",type:"4",width:"180px",disabled:"disabled"}
		], 
		
		/**
		 *回调函数，用于自动回写辖内有效库存的值
		 */
		callBackAllStore : function (rIndex, objId){
			if(objId == "versionCode"){
				
				var jsonArr = $("#orderInput").mulLineWidget.getData();
				var docVerCode = jsonArr[rIndex-1].versionCode;
				
				var params = {
					jsonData : docVerCode
		        };
				$.ajax({
					type:'post',
					url: $.taHash.get("ctx")+"/orderJson/findValidStorageNum.do?ajax=true",
					dataType:'json',
					data:params,
					success:function(msg){
					  $("#orderInput").mulLineWidget.resetVal(rIndex, "allStore", msg);
					}
				});	
			}
		},

		initOrderInput : function (){
	
			/**
			 * 初始化表单验证组件
			 */
			$("#editForm").validate();
			
			$("#orderLuanchCancelBtn").click(function(){
				$('#orderEditPage').dialog('close');
			});
			
			/**
			* @description 初始化多行录入组件
			* @param  hiddenPlus 是否隐藏添加一行的标志：0为显示，1为隐藏
			* @param  hiddenSubtraction 是否隐藏删除一行的标志：0为显示，1为隐藏
			* @param  columnData 表格列模版
			* @param  exeFuction 执行的自定义函数（用于更改某列的值）
			* @param  initJsonDate 初始化表格时加载的数据(本地数据)
			* @param  initDataUrl 初始化表格加载数据的URL(ajax方式获取数据，返回类型为json)
			* 
			* @return 
			*/
		/*	$("#orderInput").mulLineWidget({
				columnData: orderLaunchEdit.lineDataArry,
				initJsonDate:'[{"versionCode":"001","orderCount":"12"}]',
				//initDataUrl : $.taHash.get("ctx")+"/initOrderLaunchDet.do?ajax=true&id="+$("#editId").val()
				//initDate: '[{"SEX":"F","INSUM":"100","INSUMDATE":"2011-01-02","DEPTNO":"102","PLANCODE":"021","POLNO":"p11111111"}]'
			});
			*/
			/** 
			 * 保存按钮添加事件
			 */
			$("#saveBut").click(function(){
				if($("#editForm").validate().form()){
					$("#saveBut").attr("disabled",true);
					$("#orderSubmitBtn").attr("disabled",true);
					var editType= $("#editType").val();
					var editId = $("#editId").val();
					var jsonArr = $("#orderInput").mulLineWidget.getData();
					if(jsonArr && jsonArr.length>0){
						var itemTypeArr = new Array(); //判断类型是否重复
						var invalidVerCode = "";//无效的单证类型代码
						for(var i=0;i<jsonArr.length;i++){
							itemTypeArr.push(jsonArr[i].versionCode);
							if(!jsonArr[i].versionCodeName||""==jsonArr[i].versionCodeName){
								invalidVerCode += ","+jsonArr[i].versionCode;
							}
						}
						if (common.isRepeat("存在重复的单证类型", itemTypeArr)) {
							$("#saveBut").removeAttr("disabled");
							$("#orderSubmitBtn").removeAttr("disabled");
							return false;
						}
						if(""!=invalidVerCode){
							alert("无效的单证类型代码【"+invalidVerCode.substring(1)+"】");
							$("#saveBut").removeAttr("disabled");
							$("#orderSubmitBtn").removeAttr("disabled");
							return false;
						}
						var jsonData = JSON.stringify(jsonArr);
						common.ajaxSubmitDfCallBack(orderLaunchEdit.saveCallBack, 
								 $.taHash.get("ctx")+"/orderManager/saveOrderLaunch.do?ajax=true", 
								 {jsonData : jsonData,editType:editType,editId:editId});
					}else{
						alert("请录入订单申请！");
						$("#saveBut").removeAttr("disabled");
						$("#orderSubmitBtn").removeAttr("disabled");
					}
				}else{
					return false;
				}
			});
			
			/** 
			 * 提交按钮添加事件
			 */
			$("#orderSubmitBtn").click(function(){
				if($("#editForm").validate().form()){
					$("#saveBut").attr("disabled",true);
					$("#orderSubmitBtn").attr("disabled",true);
					var editType= $("#editType").val();
					var editId = $("#editId").val();
					var jsonArr = $("#orderInput").mulLineWidget.getData();
					if(jsonArr && jsonArr.length>0){
						var itemTypeArr = new Array(); //判断类型是否重复
						var invalidVerCode = "";//无效的单证类型代码
						for(var i=0;i<jsonArr.length;i++){
							itemTypeArr.push(jsonArr[i].versionCode);
							if(!jsonArr[i].versionCodeName||""==jsonArr[i].versionCodeName){
								invalidVerCode += ","+jsonArr[i].versionCode;
							}
						}
						if (common.isRepeat("存在重复的单证类型", itemTypeArr)) {
							$("#saveBut").removeAttr("disabled");
							$("#orderSubmitBtn").removeAttr("disabled");
							return false;
						}
						if(""!=invalidVerCode){
							alert("无效的单证类型代码【"+invalidVerCode.substring(1)+"】");
							$("#saveBut").removeAttr("disabled");
							$("#orderSubmitBtn").removeAttr("disabled");
							return false;
						}
						var jsonData = JSON.stringify(jsonArr);
						common.ajaxSubmitDfCallBack(orderLaunchEdit.saveCallBack, 
								 $.taHash.get("ctx")+"/orderManager/saveOrderLaunch.do?ajax=true&submitFlag=submit", 
								 {jsonData : jsonData,editType:editType,editId:editId});
					}else{
						alert("请录入订单申请！");
						$("#saveBut").removeAttr("disabled");
						$("#orderSubmitBtn").removeAttr("disabled");
					}
				}else{
					return false;
				}
			});
	},
	   
	
	saveCallBack : function() {
		alert("保存成功！");
		$('#orderEditPage').dialog('close');
		$("#saveBut").removeAttr("disabled");
		$("#orderSubmitBtn").removeAttr("disabled");
		orderLaunch.gridReload();
	}
}