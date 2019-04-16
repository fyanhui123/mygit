var docVersionInfo = {
	loadGridUrl : "/baseinfoJson/queryVcDocVersionInfoByPages.do?ajax=true",
	
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
	
	
	//新增、修改保存成功提示信息
	alertMsg:function(flag,msg){	
	  if(flag == 'SaveAfter'){
		alert(msg);
		docVersionInfo.gridReload();
	  }
    },
	
	initPage: function(){
    	
    	//单证种类类型（0-业务类、1-财务类）
		$("#queryDtoDocType").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=DocumentType&actionType=query"
		});		
		$("#queryDtoDocType").change( function() {
			 var select = $("#queryDtoDocType").val();
			 setTimeout(function(){
				/*$("#queryDtoDocTypeList").dropDownList({					
				    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&docType="+select
					//url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true"
				});*/
			   if(select=='1'){
					$("#relInsuKindCondition").hide();
					$("#queryDtoInsuTypeList").val('');
					$("#queryDtoInsuKindList").val('');				
			   }else{
				    $("#relInsuKindCondition").show();
			   }
			 },20);
		 });
		
		//单证种类
		$("#queryDtoDocTypeList").dropDownList({
			trigger: '#queryDtoDocType',    
			event: 'change',
			dataField:'name',	
			dataType:'json',
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&random="+Math.random()
		});
		
		
		//险类
		$("#queryDtoInsuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true&random="+ Math.random()
		});
		
		/*
		$("#queryDtoInsuTypeList").change( function() {
			 var select = $("#queryDtoInsuTypeList").val();
			 setTimeout(function(){
				 $("#queryDtoInsuKindList").dropDownList({
					//url : $.taHash.get("ctx")+ "/baseinfoJson/initInsuKindListWithCondition.do?ajax=true&insuTypeCode="+select
					 url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true&insuTypeCode="+select
				 });
			 },20);
		 });*/
		
		//险种
		$("#queryDtoInsuKindList").dropDownList({
			trigger: '#queryDtoInsuTypeList',    
			event: 'change',
			dataField:'name',			
			dataType:'json',		  
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true&random="+ Math.random()
		});
		
		
		
		$("#queryDtoStatus").dropDownList( {
			data : docVersionInfo.statusData
		});
		
		$("#queryDtoIsOrder").dropDownList( {
			data : docVersionInfo.isOrderData
		});
		
		
		//险种关联 2013-06-04
		$("#relInsuKindPage").dialog({
			modal: true,
			height:450,
			width:700,
			autoOpen: false
		});
		
		/**
		 * 险种关联 2013-06-04
		 */
		$("#relInsuKindBut_new").click( function() {
			var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			//alert(selectIds);
			if ("" != selectIds) {	
				if(patt.test(selectIds)){ //字符串存在返回true否则返回false
				    alert("只能选择一条记录进行关联");
				    return false;
				}else{
					var status=common.selectRowShowCollValue("docVersionInfoGrid","status",selectIds);
					if(status=="0"){
						alert("无效的记录,不能进行该操作！");
					    return false;
					}	
					/*var docType=common.selectRowShowCollValue("docTypeGrid","docType",selectIds);
					if(docType=="1"){
						alert("财务类单证不能修改！");
					    return false;
					}		*/			
					$('#docVersioninfoId_toRel').val(selectIds);
					//调用dialog组件
					$('#relInsuKindPage').dialog('open');
					//清空旧的角色数据
					$('#noRelInsuKind option').remove();
					$('#haveRelInsuKind option').remove();
					relInsuKindPage.loadRelInsuKindList(selectIds);
				}
			} else {
				alert("请选择记录进行关联！");
				return false;
			}			
		});
		
		//地区关联 2013-06-04
		$("#relAreaPage").dialog({
			modal: true,
			height:450,
			width:700,
			autoOpen: false
		});
		
		/**
		 * 地区关联 2013-06-04
		 */
		$("#relAreaBut_new").click( function() {
			var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			//alert(selectIds);
			if ("" != selectIds) {	
				if(patt.test(selectIds)){ //字符串存在返回true否则返回false
				    alert("只能选择一条记录进行关联");
				    return false;
				}else{
					var status=common.selectRowShowCollValue("docVersionInfoGrid","status",selectIds);
					if(status=="0"){
						alert("无效的记录,不能进行该操作！");
					    return false;
					}	
					/*var docType=common.selectRowShowCollValue("docTypeGrid","docType",selectIds);
					if(docType=="1"){
						alert("财务类单证不能修改！");
					    return false;
					}		*/			
					$('#docVersioninfoId_toRelArea').val(selectIds);
					//调用dialog组件
					$('#relAreaPage').dialog('open');
					
					relAreaPage.loadRelAreaZTree(selectIds);
				}
			} else {
				alert("请选择记录进行关联！");
				return false;
			}			
		});
		
		$("#toAddDocVersionInfoView").dialog({
			modal: true,
			height:550,
			width:950,
			autoOpen: false
		});
		
		 //机构选择
		$("#queryDtoArea").dropDownDivList({		
			//source: docVersionInfo.orgCodeLabelData
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&minLevel=3&singleSelect=true&tagCodeId=queryDtoArea&tagNameId=queryDtoAreaName"
			//source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=queryDtoArea&tagNameId=queryDtoAreaName"
			});
		
		/**
		* @description 添加按钮事件
		* @param  addContact 添加按钮ID
		* @return 
		*/
		$("#addDocVersionInfoBut").click( function() {
			/*var docType=$("#docType").attr("value");			
			common.ajaxSubmit("toAddDocVersionInfo",$.taHash.get("ctx")+"/baseinfo/initAddVcDocVersionInfo.do?ajax=true&idVcDocVersionInfo=&actionType=insert&docType="+docType);
			setTimeout(function(){	
				$("#toAddDocVersionInfo").dialog('open');
			},200);*/
			url=$.taHash.get("ctx")+"/baseinfo/initAddVcDocVersionInfo.do?idVcDocVersionInfo=&actionType=insert";
			$('#queryForm').attr("action",url);
			$('#queryForm').submit();
		});
		
		$("#backBut").click( function() {
			window.history.go(-1);

		});
		
		/**
		* @description 查询按钮事件
		* @param  queryResource 查询按钮ID
		* @return 
		*/
		$("#queryDocVersionInfoBut").click( function() {
			docVersionInfo.gridReload();
		});
		
		$("#updateDocVersionInfoBut").click( function() {
		    var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			if("" != selectIds){
				if(patt.test(selectIds)){//字符串存在返回true否则返回false
				    alert("只能选择一条记录进行修改");
				    return false;
				}else{
					var status=common.selectRowShowCollValue("docVersionInfoGrid","status",selectIds);
					if(status=="0"){
						alert("无效状态的记录不能修改！");
					    return false;
					}	
					/*common.ajaxSubmit("toAddDocVersionInfo",$.taHash.get("ctx")+"/baseinfo/prepareUpdateVcDocVersionInfo.do?idVcDocVersionInfo="+selectIds+"&actionType=update",null);
					setTimeout(function(){	
						$("#toAddDocVersionInfo").dialog('open');				    	
					},200);*/
					url=$.taHash.get("ctx")+"/baseinfo/prepareUpdateVcDocVersionInfo.do?idVcDocVersionInfo="+selectIds+"&actionType=update";
					$('#queryForm').attr("action",url);
					$('#queryForm').submit();
			    }
			}else{
				alert("请选择一条记录进行修改");
				return false;
			}
			
		});
		
		$("#viewDocVersionInfoBut").click( function() {
		    var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			if("" != selectIds){
				if(patt.test(selectIds)){//字符串存在返回true否则返回false
				    alert("只能选择一条记录");
				    return false;
				}else{
					common.ajaxSubmit("toAddDocVersionInfoView", $.taHash.get("ctx")+ "/baseinfo/viewVcDocVersionInfo.do?ajax=true&idVcDocVersionInfo="+ selectIds + "&actionType=view", null);
					setTimeout(function(){	
					  $('#toAddDocVersionInfoView').dialog('open');
					},200);
					/*common.ajaxSubmit("toAddDocVersionInfo", $.taHash.get("ctx")+ "/baseinfo/viewVcDocVersionInfo.do?ajax=true&idVcDocVersionInfo="+ selectIds + "&actionType=view", null);
					$('#toAddDocVersionInfo').dialog('open');*/
			    }
			}else{
				alert("请选择一条记录");
				return false;
			}
			
		});
		/**
		 * 启用、 停用
		 */
		$("#makeUseBut").click(function() {
			var selectIds = $("#selectIds").attr("value");	
			var patt = new RegExp(',');//
				if ("" != selectIds) {
					if(patt.test(selectIds)){//字符串存在返回true否则返回false
					    alert("只能选择一条记录");
					    return false;
					}else{
						if(confirm("确定启用/停用?")){	
							 common.ajaxSubmitDfCallBack(docVersionInfo.freshCallBack, 
									 $.taHash.get("ctx")+ "/baseinfoJson/updateVcDocVersionInfoStatus.do?ajax=true", 
									 {selectVcDocVersionInfoIds : selectIds});
							//common.ajaxSubmit("", $.taHash.get("ctx")+ "/baseinfo/updateVcDocVersionInfoStatus.do?ajax=true&selectVcDocVersionInfoIds="+ selectIds, "");
							//docVersionInfo.gridReload();
						}
					}
				} else {
					alert("请选择记录");
					return false;
				}
			});
		
		$("#docVersionInfoGrid").jqGrid({
			//要区分单证类型
			url : $.taHash.get("ctx")+ docVersionInfo.loadGridUrl+"&docType="+$("#docType").val(),
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',			
			colNames : ['单证类型代码', '单证类型名称','所属机构','是否征订','状态','status'],
			colModel : [{
						name : 'docVerCode',
						index : 'docVerCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'docVerName',
						index : 'docVerName',
						autowidth : true,
						resizable : true,
						align : 'left'					
					},{
						name : 'orgName',
						index : 'orgName',
						autowidth : true,
						resizable : true,
						align : 'left'
						
					},{
						name : 'isOrder',
						index : 'isOrder',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:function(value,row,index){
					         if(value=='1'){
					        	 return "是";
					         }if(value=='0'){
					        	 return "否";
					         }else{
					        	 return ""; 
					         }
						 }	
					},{
						name : 'status',
						index : 'statusName',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:function(value,row,index){
					         if(value=='0'){
					        	 return "停用";
					         }else{
					        	 return "启用"; 
					         }
						 }	
					},{
						name : 'status',
						index : 'status',
						hidden:true,						
						resizable : true,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#docVersionInfoGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcDocVersionInfoList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "idVcDocVersionInfo"
		     },
			multiselect: true,
			
			
			 /**
			    * 给主键添加链接
			    */
		   gridComplete: function(){
			     ids = $("#docVersionInfoGrid").jqGrid('getDataIDs');
			     for(var i=0;i < ids.length;i++){
				     var rowArray = $("#docVersionInfoGrid").jqGrid('getRowData',ids[i]);
				     var editBtn = "<a href='#' onclick='docVersionInfo.viewDocVersionInfo("+ids[i]+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.docVerCode+"</a>";
				     $("#docVersionInfoGrid").jqGrid('setCell', ids[i], 'docVerCode', editBtn);
			    }
		   },


			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("docVersionInfoGrid");
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
					var ids = $("#docVersionInfoGrid").jqGrid('getDataIDs');				
					$("#selectIds").val(ids);
				}
			},			
			
			onCellSelect : function(id,iCol,fieldValue){
				//alert('@@主键'+id+'@@'+iCol+'@@'+fieldValue);
				if(iCol == 1){				
					/*common.ajaxSubmit("toAddDocVersionInfo", $.taHash.get("ctx")+ "/baseinfo/viewVcDocVersionInfo.do?ajax=true&idVcDocVersionInfo="+ id + "&actionType=view", null);
					$('#toAddDocVersionInfo').dialog('open');*/
				}
			}
		});
	},
	
	//进入详情查看view
	viewDocVersionInfo: function(id) {
				common.ajaxSubmit("toAddDocVersionInfoView", $.taHash.get("ctx")+ "/baseinfo/viewVcDocVersionInfo.do?ajax=true&idVcDocVersionInfo="+ id + "&actionType=view", null);
				setTimeout(function(){	
				  $('#toAddDocVersionInfoView').dialog('open');
				},200);
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#selectIds").val("");
		var queryParam=$("#queryForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+ docVersionInfo.loadGridUrl+"&"+queryParam;
		jQuery("#docVersionInfoGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{
				/*status : $("#queryDtoStatus").val()	,
				docVerName : $("#queryDtoDocVerName").val(),
				docVerCode : $("#queryDtoDocVerCode").val(),
				docTypeCode : $("#queryDtoDocTypeList").val(),
				docType : $("#docType").val()*/  /*docType业务财务类，必有*/
			},
			page : 1
		});
		jQuery("#docVersionInfoGrid").trigger("reloadGrid");
	},
	
	freshCallBack: function(data) {
		alert(data);		
		docVersionInfo.gridReload();
	}
}