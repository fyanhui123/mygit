var printery = {
		
	loadGridUrl : "/baseinfoJson/queryPrinteryByPages.do?ajax=true",
	
	
	
	lineDataArry : [ 
	        		{title:"单证类型",fieldId:"docVerCode",fieldName:"docVerCode",fieldLableName:"docVerName",width:"200px",fieldClass:"required",type:"2",
	    				dataFormat:[{source:"/visa/baseinfoJson/queryVcDocVerLabels.do?ajax=true",height:"210px",minLength:0}]},	    				
	        		{title:"单价",fieldId:"unitPrice",fieldName:"unitPrice",width:"140px",fieldClass:"required number",type:"4"}
	        	    ],
	
	//新增、修改保存成功提示信息
  	alertMsg:function(flag,msg){	
		if(flag == 'SaveAfter'){
			alert(msg);
			//printery.gridReload();
		}
	},
	//新增、修改保存失败提示信息
	alertErrorMsg:function(hasError,msg){
		if(hasError == 'true'){
			alert(msg);
		}
	},
	
	initPage: function(){
		
		$("#status").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=Status&actionType=query"
		});
		/*$("#status").dropDownList( {
			data : printery.statusData
		});*/
		
		/*$("#toAddPrintery").dialog({
			modal: true,
			height:450,
			width:800,
			autoOpen: false
		});*/
		$("#toAddPrinteryView").dialog({
			modal: true,
			height:450,
			width:800,
			autoOpen: false
		});
		
		/**
		* @description 添加按钮事件
		* @param  addContact 添加按钮ID
		* @return 
		*/
		$("#addPrinteryBut").click( function() {			
			/*common.ajaxSubmit("toAddPrintery",$.taHash.get("ctx")+"/baseinfo/initAddPrintery.do?ajax=true&idVcPrintery=&actionType=insert");
			setTimeout(function(){	
				$("#toAddPrintery").dialog('open');
			},200);*/
			
			//printingForm.action=$.taHash.get("ctx")+"/baseinfo/initAddPrintery.do?idVcPrintery=&actionType=insert";
			url=$.taHash.get("ctx")+"/baseinfo/initAddPrintery.do?idVcPrintery=&actionType=insert";
			$('#printingForm').attr("action",url);
			//common.formSubmit("printingForm", url);	
			$('#printingForm').submit();
			//target="_self" 
			//target="_parent"
		});
		
		$("#backBut").click( function() {
			window.history.go(-1);

		});
		
		/**
		* @description 查询按钮事件
		* @param  queryResource 查询按钮ID
		* @return 
		*/
		$("#queryPrinteryBut").click( function() {
			printery.gridReload();
		});
		
		$("#updatePrinteryBut").click( function() {
		    var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			if("" != selectIds){
				if(patt.test(selectIds)){//字符串存在返回true否则返回false
				    alert("只能选择一条记录进行修改");
				    return false;
				}else{
					var status=common.selectRowShowCollValue("printeryGrid","status",selectIds);
					if(status=="0"){
						alert("无效状态的记录不能修改！");
					    return false;
					}		
					/*common.ajaxSubmit("toAddPrintery",$.taHash.get("ctx")+"/baseinfo/prepareUpdatePrintery.do?ajax=true&idVcPrintery="+selectIds+"&actionType=update",null);
					setTimeout(function(){	
						$("#toAddPrintery").dialog('open');				    	
					},200);*/
					url=$.taHash.get("ctx")+"/baseinfo/prepareUpdatePrintery.do?idVcPrintery="+selectIds+"&actionType=update";
					$('#printingForm').attr("action",url);
					$('#printingForm').submit();
			    }
			}else{
				alert("请选择一条记录进行修改");
				return false;
			}
			
		});
		$("#viewPrinteryBut").click( function() {
		    var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			if("" != selectIds){
				if(patt.test(selectIds)){//字符串存在返回true否则返回false
				    alert("只能选择一条记录");
				    return false;
				}else{	
					common.ajaxSubmit("toAddPrinteryView", $.taHash.get("ctx")+ "/baseinfo/viewPrintery.do?ajax=true&idVcPrintery="+ selectIds + "&actionType=view", null);
					
					setTimeout(function(){	
						$('#toAddPrinteryView').dialog('open');
					},200);
			    }
			}else{
				alert("请选择一条记录");
				return false;
			}
			
		});
		
		
		//注销、恢复
		$("#deleteOrUnDeletePrinteryBut").click(function() {
			var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
				if ("" != selectIds) {
					if(patt.test(selectIds)){//字符串存在返回true否则返回false
					    alert("只能选择一条记录");
					    return false;
					}else{	
						if(confirm("确定注销/恢复?")){	
							 common.ajaxSubmitDfCallBack(printery.freshCallBack, 
									 $.taHash.get("ctx")+ "/baseinfoJson/deleteOrUnDeletePrintery.do?ajax=true", 
									 {selectPrinteryIds : selectIds, actionType:"delete"});
						}
					}
				} else {
					alert("请选择记录进行注销/恢复");
					return false;
				}
			});
		
		
		$("#printeryGrid").jqGrid({
			url : $.taHash.get("ctx")+ printery.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',			
			colNames : ['印刷厂代码', '印刷厂名称','状态','status'],
			colModel : [{
						name : 'printeryCode',
						index : 'printeryCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'printeryName',
						index : 'printeryName',
						autowidth : true,
						resizable : true,
						align : 'left'					
					},{
						name : 'status',
						index : 'statusName',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:function(value,row,index){
					         if(value=='0'){
					        	 return "无效";
					         }else{
					        	 return "有效"; 
					         }
							
						 }	
					},{
						name : 'status',
						index : 'status',
						autowidth : true,
						hidden:true,
						resizable : true,
						align : 'left'
						
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#printeryGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "printeryList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "idVcPrintery"
		     },
			multiselect: true,
			
		  /**
		    * 给主键添加链接
		    */
		   gridComplete: function(){
			     ids = $("#printeryGrid").jqGrid('getDataIDs');
			     for(var i=0;i < ids.length;i++){
				     var rowArray = $("#printeryGrid").jqGrid('getRowData',ids[i]);
				     var editBtn = "<a href='#' onclick='printery.viewPrintery("+ids[i]+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.printeryCode+"</a>";
				     $("#printeryGrid").jqGrid('setCell', ids[i], 'printeryCode', editBtn);
			    }
		   },
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("printeryGrid");
				$("#selectIds").val(selectValue);
			},
			/**
			* @description 全选触发时候获取所有选中的Id值
			* @return 
			*/
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#selectIds").val("");
				}else{//全部选中时
					ids = $("#printeryGrid").jqGrid('getDataIDs');				
					$("#selectIds").val(ids);
				}
			},
			onCellSelect : function(id,iCol,fieldValue){
				
				if(iCol == 1){				
					/*common.ajaxSubmit("toAddPrintery", $.taHash.get("ctx")+ "/baseinfo/viewPrintery.do?ajax=true&idVcPrintery="+ id + "&actionType=view", null);
					$('#toAddPrintery').dialog('open');*/
				}
			}
		});
		
	},
	
	//进入详情查看view
	viewPrintery: function(id) {
		common.ajaxSubmit("toAddPrinteryView", $.taHash.get("ctx")+ "/baseinfo/viewPrintery.do?ajax=true&idVcPrintery="+ id + "&actionType=view", null);
		setTimeout(function(){	
			$('#toAddPrinteryView').dialog('open');
		},200);
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#selectIds").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ printery.loadGridUrl;
		jQuery("#printeryGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{ 
					printeryCode : $("#printeryCode").val(),
					printeryName : $("#printeryName").val(),	
					status : $("#status").val()	
					},
			page : 1
		});
		jQuery("#printeryGrid").trigger("reloadGrid");
	},
	
    resetVal : function (rIndex, objId){
		
	},
	
	initEditPage : function(){
		
		if ($("#actionType").val() == "update") {			
			 $("#printeryDtoPrinteryCode").attr('disabled','disabled');
		};
		
		if ($("#actionType").val() == "update") {
			$("#printDocVersionList").mulLineWidget({
			    columnData: printery.lineDataArry,
				exeFuction: printery.resetVal ,
				//initJsonDate: $("#jsonPrintDocVersionList").val()	
				initDataUrl: $.taHash.get("ctx")+"/baseinfoJson/queryJsonPrtDocVersionListByPrinteryId.do?ajax=true&idVcPrintery="+$("#currentIdVcPrintery").val()
		     });
			
		};
		if ($("#actionType").val() == "insert") {				
			$("#printDocVersionList").mulLineWidget({
			    columnData: printery.lineDataArry,
				exeFuction: printery.resetVal
			});
			
		};
		
		
		$("#printeryForm").validate();

		
		/**
		* @description 添加按钮事件
		* @param  saveBut 添加按钮ID
		* @return 
		*/
		$("#savePrinteryBut").click( function() {	
			/*if($("#printeryForm").validate().form()){
				//单证承印信息			
				var jsonArr = $("#printDocVersionList").mulLineWidget.getData();
				var jsonData = JSON.stringify(jsonArr);
				//主表数据
				var mainParam = $("#printeryForm").serialize();	
				var param= mainParam+"&jsonData="+jsonData;	
				
				var actionType=$("#actionType").val();
				var url="";
				if(actionType=="insert"){
					url=$.taHash.get("ctx")+"/baseinfoJson/savePrintery.do?ajax=true";	
				}else{
					url=$.taHash.get("ctx")+"/baseinfoJson/updatePrintery.do?ajax=true";
				}
				 common.ajaxSubmitDfCallBack(printery.saveCallBack, url, param );
				
			}else{
				 return false;
			}*/
			
			if($("#printeryForm").validate().form()){
				//单证承印信息			
				var jsonArr = $("#printDocVersionList").mulLineWidget.getData();
				var jsonData = JSON.stringify(jsonArr);
				
				var actionType=$("#actionType").val();
				var action="";
				if(actionType=="insert"){
					action=$.taHash.get("ctx")+"/baseinfo/savePrintery.do?jsonData="+jsonData;	
				}else{
					action=$.taHash.get("ctx")+"/baseinfo/updatePrintery.do?jsonData="+jsonData;
				}
				
				//var action = $.taHash.get("ctx")+"/store/saveDocInStore.do"+formStr;
				var fm = document.getElementById("printeryForm");
				//var fm = $("#printeryForm");
				//$('#toAddPrintery').dialog('close');
						
				$("#savePrinteryBut").attr("disabled",true);
				common.formSubmit("printeryForm", action);				
				//fm.action = action;	
				//fm.submit();
				//alert("保存成功！");
				//$('#toAddPrintery').dialog('close');
				/*setTimeout(function(){
					alert("保存成功！");
				 // printery.gridReload();
				},2000);*/
			}else{
				 return false;
			}
			
		});
		
		/**
		* @description 返回查询页面事件
		* @param  backBut 返回按钮ID
		* @return 
		*/
		$("#backToPrinteryMainBut").click( function() {
			url=$.taHash.get("ctx")+"/baseinfo/printeryMain.do";
			$('#backMainForm').attr("action",url);
			$('#backMainForm').submit();
			
		});
	},
	saveCallBack : function(data) {
		alert(data);
		$('#toAddPrintery').dialog('close');
		printery.gridReload();
	},
	freshCallBack: function(data) {
		alert(data);		
		printery.gridReload();
	}

}