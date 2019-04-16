var pubCodeType = {
		
	loadGridUrl : "/baseinfoJson/queryVcPubCodeTypeByPages.do?ajax=true",
	
	
	
	pubCodeArry : [ 
	    			{title:"业务代码",fieldId:"codeCode",fieldName:"codeCode",width:"140px",maxlength:"20",fieldClass:"required noBlankSpace",type:"4"},
	    			{title:"业务代码中文含义",fieldId:"codeCName",fieldName:"codeCName",width:"140px",maxlength:"50",fieldClass:"required noBlankSpace",type:"4"},
	    			{title:"业务代码英文含义",fieldId:"codeEName",fieldName:"codeEName",width:"140px",maxlength:"50",fieldClass:"required",type:"4"}
	        	    ],
	
	
	/*statusData : [{
  		"label" : "有效",
		 "value" : "1"
	 },{
		 "label" : "无效",
		 "value" : "0"
	} ],*/
 	
	initPage: function(){		
		$("#queryDtoStatus").dropDownList( {
			 url: "/visa/baseinfoJson/initDownList.do?codeType=Status&actionType=query"
		});
		
		$("#toAddPubCodeType").dialog({
			modal: true,
			height:450,
			width:800,
			autoOpen: false
		});
		$("#toViewPubCodeType").dialog({
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
		$("#addPubCodeTypeBut").click( function() {			
			common.ajaxSubmit("toAddPubCodeType",$.taHash.get("ctx")+"/baseinfo/initAddVcPubCodeType.do?ajax=true&idVcPubCodeType=&actionType=insert");
			setTimeout(function(){	
				$("#toAddPubCodeType").dialog('open');
			},100);
		});
		
		$("#backBut").click( function() {
			window.history.go(-1);
		});
		
		/**
		* @description 查询按钮事件
		* @param  queryResource 查询按钮ID
		* @return 
		*/
		$("#queryPubCodeTypeBut").click( function() {
			pubCodeType.gridReload();
		});
		
		$("#updatePubCodeTypeBut").click( function() {
		    var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			if("" != selectIds){
				if(patt.test(selectIds)){//字符串存在返回true否则返回false
				    alert("只能选择一条记录进行修改");
				    return false;
				}else{	
					common.ajaxSubmit("toAddPubCodeType",$.taHash.get("ctx")+"/baseinfo/prepareUpdateVcPubCodeType.do?ajax=true&idVcPubCodeType="+selectIds+"&actionType=update",null);
					setTimeout(function(){	
						$("#toAddPubCodeType").dialog('open');				    	
					},200);
			    }
			}else{
				alert("请选择一条记录进行修改");
				return false;
			}
			
		});
		
		$("#viewPubCodeTypeBut").click( function() {
		    var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			if("" != selectIds){
				if(patt.test(selectIds)){//字符串存在返回true否则返回false
				    alert("只能选择一条记录进行修改");
				    return false;
				}else{	
					common.ajaxSubmit("toViewPubCodeType", $.taHash.get("ctx")+ "/baseinfo/viewVcPubCodeType.do?ajax=true&idVcPubCodeType="+ selectIds + "&actionType=view", null);
					setTimeout(function(){	
						$('#toViewPubCodeType').dialog('open');		    	
					},100);
			    }
			}else{
				alert("请选择一条记录进行修改");
				return false;
			}
			
		});
		
		$("#deleteOrUnDeleteVcPubCodeTypesBut").click(function() {
			var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			if ("" != selectIds) {	
				if(patt.test(selectIds)){//字符串存在返回true否则返回false
				    alert("只能选择一条记录进行修改");
				    return false;
				}else{	
					if(confirm("确定删除/恢复?")){	
						 common.ajaxSubmitDfCallBack(pubCodeType.freshCallBack, 
								 $.taHash.get("ctx")+ "/baseinfoJson/deleteOrUnDeleteVcPubCodeTypes.do?ajax=true", 
								 {selectVcPubCodeTypeIds : selectIds,actionType:"delete"});
						/*common.ajaxSubmit("", $.taHash.get("ctx")+ "/baseinfo/deleteOrUnDeleteVcPubCodeTypes.do?ajax=true&selectVcPubCodeTypeIds="+ selectIds + "&actionType=delete", "");
						pubCodeType.gridReload();*/
					}
				}
			} else {
				alert("请选择记录进行删除/恢复");
				return false;
			}
		});
		
		
		$("#pubCodeTypeGrid").jqGrid({
			url : $.taHash.get("ctx")+ pubCodeType.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',			
			colNames : ['代码类型', '类型描述','状态'],
			colModel : [{
						name : 'codeType',
						index : 'codeType',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'codeTypeDesc',
						index : 'codeTypeDesc',
						autowidth : true,
						resizable : true,
						align : 'left'					
					},{
						name : 'status',
						index : 'status',
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
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#pubCodeTypeGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcPubCodeTypeList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "idVcPubCodeType"
		     },
			multiselect: true,
			
			
			/**
			* @description 给ID添加链接
			* @param  contractGrid 表格Id
			* @param  id 表格Id
			* @remark 其他参数不变，为规范写法，添加连接方式可以自行定义，这里以<a> 标签为例
			* @return 
			*/
		   gridComplete: function(){
			     ids = $("#pubCodeTypeGrid").jqGrid('getDataIDs');
			     for(var i=0;i < ids.length;i++){
				     var rowArray = $("#pubCodeTypeGrid").jqGrid('getRowData',ids[i]);
				     var editBtn = "<a href='#' onclick='pubCodeType.viewPubCodeType("+ids[i]+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.codeType+"</a>";
				     $("#pubCodeTypeGrid").jqGrid('setCell', ids[i], 'codeType', editBtn);
			    }
		   },
		   
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("pubCodeTypeGrid");
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
					ids = $("#pubCodeTypeGrid").jqGrid('getDataIDs');				
					$("#selectIds").val(ids);
				}
			},
			onCellSelect : function(id,iCol,fieldValue){
				//alert('@@主键'+id+'@@'+iCol+'@@'+fieldValue);
				if(iCol == 1){				
				
				}
			}
		});
		
	},
	
	//进入详情查看view
	viewPubCodeType: function(id) {
		common.ajaxSubmit("toViewPubCodeType", $.taHash.get("ctx")+ "/baseinfo/viewVcPubCodeType.do?ajax=true&idVcPubCodeType="+ id + "&actionType=view", null);
		setTimeout(function(){	
			$('#toViewPubCodeType').dialog('open');		    	
		},100);
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#selectIds").val("");
		var param=$("#queryForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+ pubCodeType.loadGridUrl+"&"+param;
		jQuery("#pubCodeTypeGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{ 
					pubCodeTypeCode : $("#pubCodeTypeCode").val(),
					pubCodeTypeName : $("#pubCodeTypeName").val(),	
					status : $("#status").val()	
					},
			page : 1
		});
		jQuery("#pubCodeTypeGrid").trigger("reloadGrid");
	},
	
    resetVal : function (rIndex, objId){
		
	},
	
	initEditPage : function(){
		if ($("#actionType").val() == "insert") {
			setTimeout(function(){
				$("#pubCodeList").mulLineWidget({
				    columnData: pubCodeType.pubCodeArry,
					exeFuction: pubCodeType.resetVal
				});
			},150);
			
		};
		if ($("#actionType").val() == "update") {
			 setTimeout(function(){
				 $("#vcPubCodeTypeCodeType").attr('disabled','disabled');
				 $("#pubCodeList").mulLineWidget({
					    columnData: pubCodeType.pubCodeArry,
						exeFuction: pubCodeType.resetVal ,					
						initDataUrl: $.taHash.get("ctx")+"/baseinfoJson/queryJsonVcPubCodeListByTypeId.do?ajax=true&idVcPubCodeType="+$("#currentIdVcPubCodeType").val()
				  });
			 },250);
		};
		
		
		//$("#pubCodeTypeForm").taValidator();
		$("#pubCodeTypeForm").validate();

		
		/**
		* @description 添加按钮事件
		* @param  saveBut 添加按钮ID
		* @return 
		*/
		$("#savePubCodeTypeBut").click( function() {	
			
		   if($("#pubCodeTypeForm").validate().form()){
			  
				//通用代码信息
				var jsonArr = $("#pubCodeList").mulLineWidget.getData();
				if(jsonArr && jsonArr.length>0){
					var codeCodeArr = new Array();
					for ( var i = 0; i < jsonArr.length; i++) {
						codeCodeArr.push(jsonArr[i].codeCode);
					}
					if (common.isRepeat("业务代码存在重复", codeCodeArr)) {
						return false;
					}
					
				}
				var jsonData = JSON.stringify(jsonArr);
				
				//保存按钮禁用
				 $("#savePubCodeTypeBut").attr("disabled",true);
				
				//主表数据
				var mainParam = $("#pubCodeTypeForm").serialize();	
				var param= mainParam+"&jsonData="+jsonData;				
				var actionType=$("#actionType").val();
				var url="";
				if(actionType=="insert"){
					url=$.taHash.get("ctx")+"/baseinfoJson/saveVcPubCodeType.do?ajax=true";	
				}else{
					url=$.taHash.get("ctx")+"/baseinfoJson/updateVcPubCodeType.do?ajax=true";
				}
				
				 common.ajaxSubmitDfCallBack(pubCodeType.saveCallBack, url, param );
				 
			   }else{
				 return false;
			  }
		
		});
		
		/**
		* @description 返回查询页面事件
		* @param  backBut 返回按钮ID
		* @return 
		*/
		$("#backToPubCodeTypeMainBut").click( function() {
			$("#toAddPubCodeType").dialog('close');
		});
	},
	
	saveCallBack : function(data) {
		alert(data);
		//保存按钮取消禁用
		$("#savePubCodeTypeBut").removeAttr("disabled");
		var index = data.toString().indexOf('成功');	
		if(index!=-1) {
			$('#toAddPubCodeType').dialog('close');
			pubCodeType.gridReload();
		}
	},
	freshCallBack: function(data) {
		alert(data);		
		pubCodeType.gridReload();
	}

}