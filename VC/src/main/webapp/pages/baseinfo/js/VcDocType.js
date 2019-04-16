var vcDocType = {
		
	loadGridUrl : "/baseinfoJson/queryDocTypeByPages.do?ajax=true",
 	
	initPage: function(){	
		$("#docType").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=DocumentType&actionType=query"
		}); 	
		
		$("#status").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=Status&actionType=query"
		});
		
		$("#toAddDocType").dialog({
			modal: true,
			height:450,
			width:800,
			autoOpen: false
		});
		$("#toAddDocTypeView").dialog({
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
		$("#addDocTypeBut").click( function() {			
			common.ajaxSubmit("toAddDocType",$.taHash.get("ctx")+"/baseinfo/initAddDocType.do?ajax=true&idVcDocType=&actionType=insert");
			setTimeout(function(){	
			  $("#toAddDocType").dialog('open');
			},200);
		});
		
		$("#backBut").click( function() {
			window.history.go(-1);

		});
		
		/**
		* @description 查询按钮事件
		* @param  queryResource 查询按钮ID
		* @return 
		*/
		$("#queryBut").click( function() {
			vcDocType.gridReload();
		});
		
		$("#updateDocTypeBut").click( function() {
		    var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			if("" != selectIds){
				if(patt.test(selectIds)){//字符串存在返回true否则返回false
				    alert("只能选择一条记录进行修改");
				    return false;
				}else{
					var status=common.selectRowShowCollValue("docTypeGrid","status",selectIds);
					if(status=="0"){
						alert("无效状态的记录不能修改！");
					    return false;
					}					
					common.ajaxSubmit("toAddDocType",$.taHash.get("ctx")+"/baseinfo/prepareUpdateDocType.do?ajax=true&idVcDocType="+selectIds+"&actionType=update",null);
					setTimeout(function(){	
						$("#toAddDocType").dialog('open');			    	
					},200);
			    }
			}else{
				alert("请选择一条记录进行修改");
				return false;
			}
			
		});
		
		$("#viewDocTypeBut").click( function() {
		    var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
			if("" != selectIds){
				if(patt.test(selectIds)){//字符串存在返回true否则返回false
				    alert("只能选择一条记录!");
				    return false;
				}else{	
					common.ajaxSubmit("toAddDocTypeView", $.taHash.get("ctx")+ "/baseinfo/viewDocType.do?ajax=true&idVcDocType="+ selectIds + "&actionType=view", null);
					setTimeout(function(){	
						$('#toAddDocTypeView').dialog('open');
					},200);
			    }
			}else{
				alert("请选择一条记录");
				return false;
			}
			
		});
		
		//注销、恢复
		$("#deleteDocTypeBut").click(function() {
			var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');//
				if ("" != selectIds) {
					if(patt.test(selectIds)){//字符串存在返回true否则返回false
					    alert("只能选择一条记录!");
					    return false;
					}else{	
						if(confirm("确定注销/恢复?")){
							 common.ajaxSubmitDfCallBack(vcDocType.freshCallBack, 
									 $.taHash.get("ctx")+ "/baseinfoJson/deleteOrUndeleteDocType.do?ajax=true", 
									 {selectVcDocTypeIds : selectIds, actionType:"delete"});
						}
					}
				} else {
					alert("请选择记录进行注销/恢复");
					return false;
				}
			});
		
		
		$("#docTypeGrid").jqGrid({
			url : $.taHash.get("ctx")+ vcDocType.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',			
			colNames : ['单证种类代码', '单证种类名称','类型','状态','status'],
			colModel : [{
						name : 'docTypeCode',
						index : 'docTypeCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'docTypeName',
						index : 'docTypeName',
						autowidth : true,
						resizable : true,
						align : 'left'					
					},{
						name : 'docType',
						index : 'docType',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:function(value,row,index){
					         if(value=='0'){
					        	 return "单证";
					         }else{
					        	 return "发票"; 
					         }
							
						 }	
					},{
						name : 'status',
						index : 'statusCName',
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
			pager : '#docTypeGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcDocTypeList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "idVcDocType"
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
			     ids = $("#docTypeGrid").jqGrid('getDataIDs');
			     for(var i=0;i < ids.length;i++){
				     var rowArray = $("#docTypeGrid").jqGrid('getRowData',ids[i]);
				     var editBtn = "<a href='#' onclick='vcDocType.viewDocType("+ids[i]+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.docTypeCode+"</a>";
				     $("#docTypeGrid").jqGrid('setCell', ids[i], 'docTypeCode', editBtn);
			    }
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
					ids = $("#docTypeGrid").jqGrid('getDataIDs');				
					$("#selectIds").val(ids);
				}
			},
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("docTypeGrid");
				$("#selectIds").val(selectValue);
			},
			onCellSelect : function(idVcDocType,iCol,fieldValue){
				if(iCol == 1){				
					/*common.ajaxSubmit("toAddDocType", $.taHash.get("ctx")+ "/baseinfo/viewDocType.do?ajax=true&idVcDocType="+ idVcDocType + "&actionType=view", null);
					$('#toAddDocType').dialog('open');*/
				}
			}
		});
		
	},
	
	//进入详情查看view
	viewDocType: function(id) {
		common.ajaxSubmit("toAddDocTypeView", $.taHash.get("ctx")+ "/baseinfo/viewDocType.do?ajax=true&idVcDocType="+ id + "&actionType=view", null);
		setTimeout(function(){	
			$('#toAddDocTypeView').dialog('open');
		},200);
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#selectIds").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ vcDocType.loadGridUrl;			
		jQuery("#docTypeGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{ 
					docTypeCode : $("#docTypeCode").val(),
					docTypeName : $("#docTypeName").val(),
					docType : $("#docType").val(),
					status : $("#status").val()
				   },
			page : 1
		});
		jQuery("#docTypeGrid").trigger("reloadGrid");
	},
	
	 existMsgType:function(data){
			
	},
	
	
	initEditPage : function(){	
			
		$("#vcDocTypeDtoDocType").dropDownList({ 
		    url: "/visa/baseinfoJson/initDownList.do?codeType=DocumentType&actionType=query"
		});
	
		if ($("#actionType").val() != "insert") {
			//设置默认值
			setTimeout(function(){
			  $("#vcDocTypeDtoDocType").dropDownList("selected",$("#hide_vcDocTypeDtoDocType").val()); 
			},250);
			 //$("#docTypeForm :input").attr('disabled','disabled');
			 $("#vcDocTypeDtoDocTypeCode").attr('disabled','disabled');	
			 $("#vcDocTypeDtoDocType").attr('disabled','disabled');	
			/* if ($("#actionType").val() == "view") {
				 $("#vcDocTypeDtoDocTypeName").attr('disabled','disabled');
			 }*/
		};
		
		
		
		$("#docTypeForm").taValidator();
		
		
		/**
		* @description 添加事件
		* @param  输入框ID
		* @return 
		*/
		$("#vcDocTypeDtoDocTypeCode").blur( function() {
			return;
			var param ="actionType="+$("#actionType").val()+"&vcDocTypeDto.docTypeCode="+$(this).val();
			alert(param);
			//alert($(this).val());	
			if($(this).val()!=""){
				var url=$.taHash.get("ctx")+"/baseinfo/updateDocType.do?ajax=true";
			    common.ajaxSubmitDfCallBack(existMsgType, url, param);
			}
			
		});
		
		/**
		* @description 添加按钮事件
		* @param  saveBut 添加按钮ID
		* @return 
		*/
		$("#saveDocTypeBut").click( function() {
			var patt = new RegExp(' ');//			
			var docTypeCode=$("#vcDocTypeDtoDocTypeCode").val();
			var docTypeName=$("#vcDocTypeDtoDocTypeName").val();
			
			var actionType=$("#actionType").val();
			var url="";
			if(actionType=="insert"){
				url=$.taHash.get("ctx")+"/baseinfoJson/saveDocType.do?ajax=true";	
			}else{
				url=$.taHash.get("ctx")+"/baseinfoJson/updateDocType.do?ajax=true";
			}			
			if($("#docTypeForm").validate().form()){
				if (patt.test(docTypeCode)){
					alert("单证种类代码存在空格！");
					return false;
				}
				if (patt.test(docTypeName)){
					alert("单证种类名称存在空格！");
					return false;
				}
				$("#saveDocTypeBut").attr("disabled",true);
				setTimeout(function(){
					$("#saveDocTypeBut").removeAttr("disabled");
				},500)
				var tmpStr = $("#docTypeForm").serialize();	
				
				common.ajaxSubmitDfCallBack(vcDocType.saveCallBack, url, tmpStr );
				
				//common.ajaxSubmitAndCloseDialog("toAddDocType",url,tmpStr);			
				
			}
		});
		
		/**
		* @description 返回查询页面事件
		* @param  backBut 返回按钮ID
		* @return 
		*/
		$("#backToDocTypeMainBut").click( function() {			
			$("#toAddDocType").dialog('close');
		});
	},
	
	saveCallBack : function(data) {
		//alert("保存成功！");
		alert(data);
		$("#saveDocTypeBut").removeAttr("disabled");
		var index = data.toString().indexOf('成功');	
		if(index!=-1) {
			$('#toAddDocType').dialog('close');
			vcDocType.gridReload();
		}
	},
	freshCallBack: function(data) {
		alert(data);
		//alert("操作成功！");		
		vcDocType.gridReload();
	}

}