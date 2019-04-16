var docDetailInquiryNew = {
	docSelectUrl : "/baseinfoJson/queryDocForDocDetInquiry.do?ajax=true",
	docDetailInquiryUrl : "/inquiryJson/queryDocDetailInquiryListByPages.do?ajax=true",
	detail : "/baseinfoJson/queryDocDetDocDetInquiry.do?ajax=true",			
	exploreDetailUrl :"/inquiryJson/exportDocDetailInquiry.do?ajax=true",
	statusData : [{
  		"label" : "启用",
		 "value" : "1"
	 },{
		 "label" : "停用",
		 "value" : "0"
	} ],
	
	initPage : function() {		
		//单证种类
		$("#queryDtoDocTypeList").dropDownList({
			dataField:'value',	
			dataType:'json',
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocTypeList.do?ajax=true&trigger=no&random="+Math.random()
		});
		
		
		//险类
		$("#queryDtoInsuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/pubJson/queryJsonPubRiskClassList.do?ajax=true&trigger=no&random="+ Math.random()
		});
		
		//险种
		$("#queryDtoInsuKindList").dropDownList({
			trigger: '#queryDtoInsuTypeList',    
			event: 'change',
			dataField:'name',			
			dataType:'json',		  
			url: $.taHash.get("ctx")+"/pubJson/queryJsonPubRiskList.do?ajax=true&trigger=yes&random="+ Math.random()
		});
		
		$("#queryDtoStatus").dropDownList( {
			data : docDetailInquiryNew.statusData
		});
		
		//机构选择
		$("#docDetailInquiryDtoOrgCode").dropDownDivList({		
			//source:$.taHash.get("ctx")+"/pages/inquiry/jsp/companyTree3.jsp?ajax=true"
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&tagCodeId=docDetailInquiryDtoOrgCode&tagNameId=docDetailInquiryDtoOrgCodeName"
		});
		
		/**
		 * 操作人自动完成组建
		 */
		$("#docDetailInquiryDtoOprCode").taAutoComplete({
			source : "/visa/baseinfoJson/getJsonVcTaker.do?ajax=true",
			minLength : 0,
			height : 210,
			width : 220
		});
		
		$("#docDetailInquiryDtoIsContainSubOrg").change( function() {		
			  if($(this).attr("checked")=="checked"){
				  $(this).val("1"); 
			  }else{
				  $(this).val("0");   
			  }		 
		 });
		
        //$("#docQueryForm").validate();
		
		
		//单证查询重置按钮
		$("#docQueryRestBut").click( function() {			
			common.clearGridData('docVersionInfoGrid');
			$("#queryDtoDocTypeList").dropDownList("selected","1"); //默认为保单			
			$("#queryDtoStatus").dropDownList("selected","1"); //默认为有效
		});
		
		//返回
		$("#goBackBut").click( function() {
			 common.clearGridData('docDetailInquiryGrid');
			//显示查询条件div
			$("#docDetailInquiryResult").attr("style","display:none");
			$("#docDetailInquiryNew").attr("style","display:");			
			$("#goBackBut").attr("disabled",true);
			$("#explore").removeAttr("disabled");
		});
		 
		//返回
		$("#goBackBut1").click( function() {
			 common.clearGridData('docDetailListGrid');
			 $("#docDetailInquiryResult").attr("style","display:");
			//显示查询条件
			$("#docDetailListResult").attr("style","display:none");			
			$("#goBackBut1").attr("disabled",true);			
		});
		
		//导出
		$("#explore").click( function() {
			$("#explore").attr("disabled",true);
            var curSelectIds = common.selectRowValue("docVersionInfoGrid");            
			//导出时时间不得大于一个月
			var t1=$("#operateStartDate").val();
			var t2=$("#operateEndDate").val();			
			var d1=new Date(t1.replace(/-/g,"/"));
			var d2=new Date(t2.replace(/-/g,"/"));
			var day= (d2.getTime() - d1.getTime())/(24 * 60 * 60 * 1000)+1; 
			var curOrg=$("#docDetailInquiryDtoOrgCode").val();
			url= $.taHash.get("ctx")+"/inquiryJson/checkBeforeDetailInquiry.do?ajax=true";
			$.post(url,
					{'docDetailInquiryDto.orgCode':curOrg},
					function(date){                       
						//docDetailInquiryNew.gridReload();
						var tmpStr = $("#subForm").serialize();
						var arrSelectId = common.selectRowValue("docVersionInfoGrid");  
						var curSelectIds="";
						for(var i=0;i<arrSelectId.length;i++){
							curSelectIds=curSelectIds+","+arrSelectId[i];
						}
						curSelectIds=curSelectIds.substr(1);
						var gridReloadUrl = $.taHash.get("ctx")+docDetailInquiryNew.exploreDetailUrl+"&"+tmpStr+"&docVerCodes="+ curSelectIds;
						common.ajaxSubmitDfCallBack(docDetailInquiryNew.exportHref, gridReloadUrl, null);
					});
		});
		
		$("#docVersionInfoGrid").jqGrid({
			//要区分单证类型
			url : $.taHash.get("ctx")+ docDetailInquiryNew.docSelectUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '170',			
			colNames : ['单证类型代码', '单证类型名称','所属机构','状态','status'],
			colModel : [{
						name : 'docVerCode',
						index : 'docVerCode',
						width:100,
						resizable : true,
						align : 'left'
					}, {
						name : 'docVerName',
						index : 'docVerName',
						width:340,
						resizable : true,
						align : 'left'					
					},{
						name : 'orgName',
						index : 'orgName',
						width:220,
						resizable : true,
						align : 'left'
						
					},{
						name : 'status',
						index : 'statusName',
						width:40,
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
			rowNum : 1000,
			//rowList : [1000,2000],
			//pager : '#docVersionInfoGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcDocVersionInfoList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "docVerCode"   ///查询条件用docVerCode
		     },
			multiselect: true,
			rownumbers: true,   ///序号

			/**
			    * 给主键添加链接
			    */
		   gridComplete: function(){
			     ids = $("#docVersionInfoGrid").jqGrid('getDataIDs');
			     for(var i=0;i < ids.length;i++){
				     var rowArray = $("#docVersionInfoGrid").jqGrid('getRowData',ids[i]);
				     var editBtn = "<a href='#' onclick='' style='text-decoration:underline;font-weight:normal;'>"+rowArray.docVerCode+"</a>";
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
					
				}
			}
		});
		$("#docDetailListGrid").jqGrid({
			//要区分单证类型
			url : $.taHash.get("ctx")+ docDetailInquiryNew.detail,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '170',			
			colNames : ['保单号', '缴费期次','拆分批次'],
			colModel : [{
						name : 'businessNo',
						index : 'businessNo',
						width:180,
						resizable : true,
						align : 'left'
					}, {
						name : 'batchNo',
						index : 'batchNo',
						width:340,
						resizable : true,
						align : 'left'					
					},{
						name : 'payNo',
						index : 'payNo',
						width:220,
						resizable : true,
						align : 'left'
						
					}],
			shrinkToFit : true,
			rowNum : 1000,
			viewrecords : true,
			jsonReader: {
		     	root: "detileNormalVO",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "docVerCode"   ///查询条件用docVerCode
		     },
			multiselect: true,
			rownumbers: true,   ///序号					
		});
		$("#docDetailInquiryGrid").jqGrid( {
			url : $.taHash.get("ctx")+docDetailInquiryNew.docDetailInquiryUrl,
			mtype : "POST",
			datatype : "local",
			width : $(window).width()*0.99,			
			height : '470',	
			colNames : ['id', '单证类型代码','单证类型名称', '起始流水','终止流水','所属机构', '操作人','所属人', '操作时间','业务号', '单证状态' ],
			colModel : [ {
				name : 'id',
				 index : 'id',
				 hidedlg : true,
				 hidden : true
			},{
				name : 'docVerCode',
				index : 'docVerCode',
				width : 120,
				//autowidth : true,	
				resizable : true,
				align : 'left'
				//hidden : true
			}, {
				name : 'docVerName',
				index : 'docVerName',
				width : 200,
				//autowidth : true,	
				resizable : true,
				align : 'left'
			}, {
				name : 'startNum',
				index : 'startNum',
				width : 90,
				//autowidth : true,				
				resizable : true,
				align : 'left'
			}, {
				name : 'endNum',
				index : 'endNum',
				width : 90,
				//autowidth : true,	
				resizable : true,				
				align : 'left'
			},{
				name : 'orgName',
				index : 'orgName',
				width : 200,
				//autowidth : true,	
				resizable : true,				
				align : 'left'
			},{
				name : 'oprName',
				index : 'oprName',
				width : 60,
				//autowidth : true,				
				resizable : true,
				align : 'left'
			},{
				name : 'ownerName',
				index : 'ownerName',
				width : 60,
				//autowidth : true,				
				resizable : true,
				align : 'left'
			}, {
				name : 'operateTime',
				index : 'operateTime',
				width : 100,
				//autowidth : true,		
				resizable : true,	
				formatter:"date",
				formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d'},	
				align : 'left'
			},{
				name : 'businessNo',
				index : 'businessNo',
				width : 200,
				//autowidth : true,	
				resizable : true,				
				align : 'center'
			},{
				name : 'statusName',
				index : 'statusName',
				width : 60,
				//autowidth : true,		
				resizable : true,
				align : 'left'
			}],
			shrinkToFit : true,
			rowNum : 20,
			rowList : [20, 50,80 ],
			pager : '#docDetailInquiryGridPager',
			viewrecords : true,
			jsonReader : {
				root : "docDetailInquiryDtoList",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				id : "docInStoreDetId"
			},
			multiselect : false,
			//rownumbers: true,   ///序号
			
		/*	gridComplete: function(){
				$("#goBackBut").removeAttr("disabled");
			},*/
			
			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				var selectValue = common.selectRowValue("docDetailInquiryGrid");
				$("#docStatusId").val(selectValue);
			},
			/**
			    * 给主键添加链接
			    */
			 gridComplete: function(){
				   $("#goBackBut").removeAttr("disabled");
				    ids = $("#docDetailInquiryGrid").jqGrid('getDataIDs');
				    for(var i=0;i < ids.length;i++){
				     var rowArray = $("#docDetailInquiryGrid").jqGrid('getRowData',ids[i]);
				     var editBtn = "<a href='#' onclick='docDetailInquiryNew.mysubmit(\""+rowArray.docVerCode+"\",\""+rowArray.startNum+"\",\""+rowArray.businessNo+"\",\""+rowArray.statusName+"\")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.docVerCode+"</a>";
				     $("#docDetailInquiryGrid").jqGrid('setCell', ids[i], 'docVerCode', editBtn);
				    }
				   }

		});
		
		setTimeout(function(){
			$("#queryDtoDocTypeList").dropDownList("selected","1"); //默认为保单			
			$("#queryDtoStatus").dropDownList("selected","1"); //默认为有效
			$("#queryDtoDocTypeList option[value='']").remove();//去掉请选择
			$("#queryDtoStatus option[value='']").remove();//去掉请选择
		},800);
		
		
		/**
		* @description 单证查询按钮事件
		* @param  queryResource 查询按钮ID
		* @return 
		*/
		$("#queryDocVersionInfoBut").click( function() {
			if(!$("#docQueryForm").validate().form()){
				return false;
			}
			
			var  myselect=document.getElementById("queryDtoDocTypeList");
			var index=myselect.selectedIndex ;
			var ZP=myselect.options[index].text;
			var docType = $("#queryDtoDocTypeList").val();
			var riskClass = $("#queryDtoInsuTypeList").val();
			if(((docType!="13")&&(ZP!="ZP_增值税发票")) && riskClass==""){
				$("#queryDtoInsuTypeList").focus();
				alert("请选择险类！");				
				return false;
			}
			$("#selectIds").val("");
			docDetailInquiryNew.docGridReload();
		});
		
		
		/**
		 * @description 查询按钮事件
		 * @param queryDocDetailInquiry
		 *            查询按钮ID
		 * @return
		 */
		$("#queryDocDetailInquiry").click(function() {
			if(!$("#subForm").validate().form()){
				return false;
			}
            var curSelectIds = common.selectRowValue("docVersionInfoGrid");            
			if ("" == curSelectIds) {
				alert("请选择单证类型！");
				return false;
			}
			//导出时时间不得大于一个月
			var t1=$("#operateStartDate").val();
			var t2=$("#operateEndDate").val();			
			var d1=new Date(t1.replace(/-/g,"/"));
			var d2=new Date(t2.replace(/-/g,"/"));
			var day= (d2.getTime() - d1.getTime())/(24 * 60 * 60 * 1000)+1; 
			if(day>93){
				$("#operateStartDate").focus();
				alert("统计时间段不能超过3个月（92天）！");
				return false;
			}
			//查询前输入的查询机构校验
			var curOrg=$("#docDetailInquiryDtoOrgCode").val();
			url= $.taHash.get("ctx")+"/inquiryJson/checkBeforeDetailInquiry.do?ajax=true";
			$.post(url,
					{'docDetailInquiryDto.orgCode':curOrg},
					function(date){                       
				        common.clearGridData('docDetailInquiryGrid');
						docDetailInquiryNew.gridReload();
						//显示查询结果div
						$("#docDetailInquiryResult").attr("style","display:");
						$("#docDetailInquiryNew").attr("style","display:none");
			       });
		});
	},
	/**
	* @description 根据条件查询单证详细信息
	* @param  
	* @return 
	*/
	mysubmit : function(docVerCode,startNum,businessNo,statusName) {	
		//alert("docVerCode--"+docVerCode+"startNum-->"+startNum+"businessNo-->"+businessNo+"statusName-->"+statusName);
		
		var status;
		//非正常核销表
		if(statusName=="库存作废"||statusName=="已使用作废"||statusName=="已使用作废不统计"||statusName=="销毁"||statusName=="缴销"||statusName=="库存遗失"||statusName=="可使用遗失"||statusName=="作废遗失")
		{
			$("#docDetailInquiryResult").attr("style","display:none");
			$("#goBackBut1").attr("disabled",false);	
			var gridReloadUrl = $.taHash.get("ctx")+docDetailInquiryNew.detail+"&actionType=VcAb&docVerCode="+ docVerCode+"&startNum="+ startNum +"&businessNo="+businessNo;
			//var action = $.taHash.get("ctx")+"/baseinfoJson/queryDocDetDocDetInquiry.do?ajax=true&actionType=Vc&docVerCode=" + docVerCode+"&startNum="+ startNum +"&businessNo="+businessNo;			
			jQuery("#docDetailListGrid").jqGrid('setGridParam', {
				url : gridReloadUrl,
				datatype : "json",
				postData:{},
				page : 1
			});
			jQuery("#docDetailListGrid").trigger("reloadGrid");
			docDetailInquiryNew.gridReloadDet(gridReloadUrl);
			//显示查询结果div       
			$("#docDetailListResult").attr("style","display:");
			$("#docDetailInquiryNew").attr("style","display:none");
		}
		else if(statusName=="已使用"||statusName=="手工已使用登记")	{
			$("#docDetailInquiryResult").attr("style","display:none");
			$("#goBackBut1").attr("disabled",false);	
			//var action = $.taHash.get("ctx")+"/baseinfoJson/queryDocDetDocDetInquiry.do?ajax=true&actionType=VcAb&docVerCode=" + docVerCode+"&startNum="+ startNum +"&businessNo="+businessNo;			
			var gridReloadUrl = $.taHash.get("ctx")+docDetailInquiryNew.detail+"&actionType=Vc&docVerCode="+ docVerCode+"&startNum="+ startNum +"&businessNo="+businessNo;
			jQuery("#docDetailListGrid").jqGrid('setGridParam', {
				url : gridReloadUrl,
				datatype : "json",
				postData:{},
				page : 1
			});
			jQuery("#docDetailListGrid").trigger("reloadGrid");
			docDetailInquiryNew.gridReloadDet(gridReloadUrl);
			//显示查询结果div       
			$("#docDetailListResult").attr("style","display:");
			$("#docDetailInquiryNew").attr("style","display:none");
		}else{
			$("#docDetailInquiryResult").attr("style","display:");
			$("#goBackBut1").attr("disabled",false);	
			alert("单证状态为"+statusName+"没有单证明细");
		}
	},
	
	/**
	* @description 根据条件查询单证信息
	* @param  
	* @return 
	*/
	docGridReload : function() {		
		var queryParam=$("#docQueryForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+ docDetailInquiryNew.docSelectUrl+"&"+queryParam;		
		jQuery("#docVersionInfoGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{},
			page : 1
		});
		jQuery("#docVersionInfoGrid").trigger("reloadGrid");
	},
	gridReloadDet : function(action) {
		/*alert("路径-->"+action);
		
		jQuery("#docDetailListGrid").trigger("reloadGrid");
		$("#goBackBut").attr("disabled",true);*/
	},
	gridReload : function() {
		var tmpStr = $("#subForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+docDetailInquiryNew.docDetailInquiryUrl+"&"+tmpStr;
		var arrSelectId = common.selectRowValue("docVersionInfoGrid");  
		var curSelectIds="";
		for(var i=0;i<arrSelectId.length;i++){
			curSelectIds=curSelectIds+","+arrSelectId[i];
		}
		curSelectIds=curSelectIds.substr(1);
		jQuery("#docDetailInquiryGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{docVerCodes:curSelectIds},   //queryDto.arrDocVerCode
			page : 1
		});

		jQuery("#docDetailInquiryGrid").trigger("reloadGrid");
		$("#goBackBut").attr("disabled",true);
	},
	exportHref : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#export").removeAttr("disabled");
			return false;
		}
		var filePath=data.substring(0,data.indexOf("=="));
		var fileName=data.substring(data.indexOf("==")+2, data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#explore").removeAttr("disabled");
	}
}
