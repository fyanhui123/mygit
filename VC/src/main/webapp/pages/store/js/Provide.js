var provide = {
	/**
	 * 
	 */
	/*lineDataArry : [ 
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",disabled:"disabled",fieldLableName:"docVerCodeName",width:"200px",type:"2",
					dataFormat:[{source:"/visa/baseinfoJson/queryVcDocVerLabels.do?ajax=true", height:"210px", width:'300px',minLength:0}],disabled:"disabled"},
		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName",width:"200px",type:"2",
					dataFormat:[{source:"/visa/provideJson/getJsonDocVersion.do?ajax=true", height:"210px", width:'300px',minLength:0}]},
		{title:"申请数量",fieldName:"applyNum",fieldId:"applyNum",width:"50px",type:"4", disabled:"disabled"},
		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",type:"4", fieldClass:"required"},
		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",type:"4",fieldClass:"required"},
		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",type:"4",fieldClass:"required"},
		{title:"发放数量",fieldName:"provideNum",fieldId:"provideNum",width:"50px",type:"4",fieldClass:"required",disabled:"disabled"},
		{title:"使用截止日期",fieldName:"deadlineStr",fieldId:"deadlineStr",width:"100px",type:"5",fieldClass:"required"},
		{title:"申领机构有效库存",fieldId:"edit",fieldName:"edit",fieldVal:"查看1", type:"8",width:"100px"},
		{title:"本级有效库存",fieldName:"selfValidNum",fieldId:"selfValidNum",fieldVal:"查看2",type:"8",width:"100px"}
		],	*/
	/**
	 * 
	 */	
	applyQueryEnableStatus : [
		{"label":"已删除", "value":"0"},
		{"label":"新建", "value":"1"},
		{"label":"等待发放", "value":"2"},
		{"label":"发放退回", "value":"3"},
		{"label":"等待确认", "value":"4"},
		{"label":"确认退回", "value":"5"},
		{"label":"已确认", "value":"6"}
	], 
	
	/**
	 * 
	 */
	gridReload: function () {
		var gridReloadUrl = $.taHash.get("ctx")+"/provideJson/queryApplyListByPages.do?ajax=true";
		var QueryCondition = new Object();
		QueryCondition.queryCode = $("#applyCode").val();
		QueryCondition.startDate = $("#initStartDate").val();
		QueryCondition.endDate = $("#initEndDate").val();
		QueryCondition.queryStatus = $("#applyStatusDropDownList").val();
				
		jQuery("#provideGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype: "json", //设置数据类型 
			postData : {
				json : JSON.stringify(QueryCondition)
			},			
			page : 1
		});
		
		jQuery("#provideGrid").trigger("reloadGrid");
		$("#id").val("");
	},
	
	resetVal : function (rIndex, objId){
		/**
		 * @description 更改字段值
		 * @param  参数1(org） 要更改值的标签Id
		 * @param  参数2(2） 要更改值的标签value
		 * 
		 */
		
		if(objId == "startNum" || objId == "endNum"){
			var jsonArr = $("#DocListProvide").mulLineWidget.getData();
			var startNum = jsonArr[rIndex-1].startNum ;
			var endNum = jsonArr[rIndex-1].endNum;
			if(startNum!="" && endNum!=""){
			  startNum = parseFloat(startNum);
			  endNum = parseFloat(endNum);
			  if(endNum<startNum) {
			    alert("终止流水号要大于起始流水号");
			    $("#DocListProvide").mulLineWidget.resetVal(rIndex, objId, null);
			    $("#DocListProvide").mulLineWidget.resetVal(rIndex, "provideNum", null);
			    return;
			  }else{
			   $("#DocListProvide").mulLineWidget.resetVal(rIndex, "provideNum", (endNum-startNum)+1);
			   }
			}
		}
		/*
		 if(objId == "startNum"){    
				      var jsonArr = $("#DocListProvide").mulLineWidget.getData();
					  var startNum = jsonArr[rIndex-1].startNum ;
					  var endNum = jsonArr[rIndex-1].endNum;
					  $("#DocListProvide").mulLineWidget.resetVal(rIndex, "endNum", (1);
					   $("#DocListProvide").mulLineWidget.resetVal(rIndex, "provideNum", (1);
		  }
		*/		
				
		if(objId == "selfValidNum"){
			var jsonArr = $("#DocListProvide").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			
			if(docVerCode==null ||docVerCode=="") {
				alert("请选择单证类型");
				return;
			} else {
				$("#validStorageQuery_docVerCode").val(docVerCode);
				$("#validOrg").val("");
				$("#rowIndex").val(rIndex);
				$("#tableName").val("DocListProvide");
				
				$("#hiddenQueryBtn").click();
				setTimeout(function(){
					$("#selectStorageRec").removeAttr("disabled");
					$('#validStorageDiv').dialog('open');
				},300);
			}
		}
		
		if(objId == "edit"){
			var jsonArr = $("#DocListProvide").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			
			if(docVerCode==null ||docVerCode=="") {
				alert("请选择单证类型");
				return;
			} else {
				$("#validStorageQuery_docVerCode").val(docVerCode);
				//申领机构
				var applyOrgCode=$("#applyOrgCode").val();
				$("#validOrg").val(applyOrgCode);
				$("#rowIndex").val("");
				$("#tableName").val("");
				
				$("#hiddenQueryBtn").click();
				setTimeout(function(){
					$("#selectStorageRec").attr("disabled",true);
					$('#validStorageDiv').dialog('open');
				},300);
			}
		}
		
		if(objId == "docVerCode"){
			var jsonArr = $("#DocListProvide").mulLineWidget.getData();
			var docVerCode = jsonArr[rIndex-1].docVerCode;
			if(docVerCode==null || docVerCode==""){
				return;
			}else{
				var applyId = $("#vcApply_id").val();
				var applyOrgCode = $("#applyOrgCode").val();
				//回写申请数量
				setTimeout(function(){
					$.ajax({
						type:'post',
						url: $.taHash.get("ctx")+"/provideJson/getJsonApplyNum.do?ajax=true",
						dataType:'json',
						data:{applyId:applyId,docVerCode:docVerCode},
						success:function(data){
						  $("#DocListProvide").mulLineWidget.resetVal(rIndex, "applyNum", data);
						}
					});
				},200);
				//回写使用截止日期
				setTimeout(function(){
					$.ajax({
						type:'post',
						url: $.taHash.get("ctx")+"/provideJson/getJsonDaedLine.do?ajax=true&mngType=0&maxStoreType=maxStoreTime",
						dataType:'json',
						data:{docVerCode:docVerCode,orgCode:applyOrgCode},
						success:function(data){
						  $("#DocListProvide").mulLineWidget.resetVal(rIndex, "deadlineStr", data);
						}
					});
				},200);
			}
		}
	},
	/**
	 * 初始化页面
	 */
	initEditPage:function() {
		$("#provideEdit").validate();
		var initData = $("#editProvide_hiddenDet").html();
		var applyId = $("#vcApply_id").val();
		var lineData = [ 
		        		{title:"单证类型",fieldName:"docVerCode",fieldId:"docVerCode",fieldLableName:"docVerCodeName",width:"200px",type:"2",fieldClass:"required",
		        					dataFormat:[{source:"/visa/provideJson/getJsonDocVersion.do?ajax=true&applyId="+applyId, height:"210px", width:'300px',minLength:0}]},
		        		{title:"申请数量",fieldName:"applyNum",fieldId:"applyNum",width:"50px",type:"4", disabled:"disabled"},
		        		{title:"印刷批次",fieldName:"pressBatchNo",fieldId:"pressBatchNo",width:"100px",type:"4", fieldClass:"required noBlankSpace"},
		        		{title:"起始流水号",fieldName:"startNum",fieldId:"startNum",width:"100px",type:"4",fieldClass:"required noBlankSpace",minlength:"8",maxlength:"10"},
		        		{title:"终止流水号",fieldName:"endNum",fieldId:"endNum",width:"100px",type:"4",fieldClass:"required noBlankSpace",minlength:"8",maxlength:"10"},
		        		{title:"发放数量",fieldName:"provideNum",fieldId:"provideNum",width:"50px",type:"4",fieldClass:"required",disabled:"disabled"},
		        		{title:"使用截止日期",fieldName:"deadlineStr",fieldId:"deadlineStr",width:"100px",type:"5",fieldClass:"required"},
		        		{title:"申领机构有效库存",fieldId:"edit",fieldName:"edit",fieldVal:"查看申领机构", type:"8",width:"100px"},
		        		{title:"本级有效库存",fieldName:"selfValidNum",fieldId:"selfValidNum",fieldVal:"查看本级",type:"8",width:"100px"}
		        		];
		$("#DocListProvide").mulLineWidget({
			/*hiddenPlus: '1',		
			hiddenSubtraction: '1',	*/
			hiddenPlus: '0',		
			hiddenSubtraction: '0',	
			//columnData: provide.lineDataArry,
			columnData : lineData,
			exeFuction: provide.resetVal,
			initJsonDate: initData
		});
		
		$("#saveButProvideEdit").click(function() {
			var valid = $("#provideEdit").validate().form();

			if(valid) {
				var n = {};
				var applyDetList = $("#DocListProvide").mulLineWidget.getData();
				for(var i=0;i<applyDetList.length;i++){
					var st1=applyDetList[i].applyNum;
					var st2=applyDetList[i].provideNum;
					var startNum=applyDetList[i].startNum;
					var endNum=applyDetList[i].endNum;
					if(st1=='NaN'){
						alert('单证【'+applyDetList[i].docVerCode+'】申请数量为空！');
						return false;
					}
					if(st2=='NaN'){
						alert('单证【'+applyDetList[i].docVerCode+'】发放数量为空！');
						return false;
					}
					if(parseFloat(startNum)>parseFloat(endNum)){
						  alert('单证【'+applyDetList[i].docVerCode+'】起始流水大于终止流水！');
						 return false;
					}
					if(parseFloat(st1)<parseFloat(st2)){
					  alert('单证【'+applyDetList[i].docVerCode+'】发放数量大于申请数量！');
					 return false;
					}
					//add by chy 20130725 ,不同行的同一单证类型，发放数量总和不能大于申请数量 begin
					var _docVerCode = applyDetList[i].docVerCode;
					if(n[_docVerCode]){
						n[_docVerCode].provideNum += parseFloat(st2);
					}else{
						var _obj = new Object();
						_obj.docVerCode = _docVerCode;
						_obj.applyNum = parseFloat(st1);
						_obj.provideNum = parseFloat(st2);
						n[_docVerCode] = _obj;
					}
				}
				for(i in n){
					if(n[i].provideNum>n[i].applyNum){
						alert('单证【'+i+'】的合计发放数量不能大于申请数量！');
						return false;
					}
				}
				//add by chy 20130725 ,不同行的同一单证类型，发放数量总和不能大于申请数量 end
				
				//流水号区间是否重叠
				var isRepeat = common.checkOverLap(applyDetList, "docVerCode", "pressBatchNo", "startNum", "endNum");
				if(isRepeat){
					return false;
				}
			
			    //  防止重复提交
				$("#saveButProvideEdit").attr("disabled",true);
				$("#submitButProvideEdit").attr("disabled",true);
				var vcApply = provide.getPageProvide();
				vcApply.applyStatus="4";  //暂存状态
				
				provide.ajaxSaveProvide(vcApply);
			} else {
				return;
			}					
		});
		
		$("#submitButProvideEdit").click(function() {
			var backReason = $("#backReason").val();
			if(backReason!=null && backReason!="") {
			
			     //  防止重复提交
			 $("#submitButProvideEdit").attr("disabled",true);
			  $("#saveButProvideEdit").attr("disabled",true);
			
				var vcApply = provide.getPageProvide();
				vcApply.applyStatus="3";  //提交状态
				provide.ajaxSaveProvide(vcApply);
			} else {
				alert("退回时请填写发放意见");
				 $("#submitButProvideEdit").removeAttr("disabled");
				 $("#saveButProvideEdit").removeAttr("disabled");
				return;
			}				
		});
		
		$("#backButProvideEdit").click(function() {
			$("#editPageProvide").dialog("close");
		});
	},
	
	/**
	 * 初始化页面
	 */
	initQueryPage:function() {
		$("#editPageProvide").css("display","none");
		$("#viewPageApply").css("display","none");
		$("#validStorageDiv").css("display","none");
		
		$("#applyStatusDropDownList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getPubCode.do?ajax=true&codeType=ApplyStatus"			
//			data:provide.applyQueryEnableStatus
		});
		
		/**
		$("#applyStatusDropDownList").dropDownList("disable",2);		
		**/
		
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#editPageProvide").dialog({
			modal: true,
			height:450,
			width:1050,
			autoOpen: false
		});
		
		$("#viewPageApply").dialog({
			modal: true,
			height:430,
			width:850,
			autoOpen: false
		});
		
		$("#validStorageDiv").dialog({
			modal: true,
			height:430,
			width:850,
			autoOpen: false
		});
		
		/**
		 * 
		 */
		$("#queryProvide").click( function() {
			provide.gridReload();
		});
		
		/**
		 * 发放按钮单击事件
		 */
		$("#provideBtn").click(function() {
			var id = $("#id").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行发放");
				} else {
					//根据id获取行数据,返回的是列表 
					var rowDatas = $("#provideGrid").jqGrid('getRowData', id); 
					//取到选中行某一字段的值，其中name为colModel中定义的字段名 
					var row = rowDatas["applyStatus"]; 
					var rowApplyCode = rowDatas["applyCode"];
					if(('2'!=row)) {
						alert("所选申领单号非[等待发放]状态,不可以进行发放操作");
						return;
					}
					$("#viewPageApply").empty();

					//ajax获取要修改的数据
					common.ajaxGetData("editPageProvide", $.taHash.get("ctx")+"/provide/viewProvideEdit.do?id="+id+"&ajax=true&mngType=0&maxStoreType=maxStoreTime",null); 
					
					setTimeout(function(){
						$('#editPageProvide').dialog('open');
					},200);
				}
			}else{
				alert("请选择一条记录进行发放");
			}
		});
			
		$("#provideGrid").jqGrid({
			url : '',
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '210px',
			colNames : ['ID', '申领单号', '申请人', '申请机构','申请日期', '申领单状态','applyStatus'],
			colModel : [{
						name : 'id',
						index : 'id',
						width : 50,
						resizable : true,
						align : 'center'
					},{
						name : 'applyCode',
						index : 'applyCode',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyOprName',
						index : 'applyOprName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyOrgName',
						index : 'applyOrgName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyTime',
						index : 'applyTime',
						autowidth : true,
						resizable : true,
						align : 'left',
						formatter:"date",
						formatoptions:{srcformat:'Y-m-d H:i:s', newformat:'Y-m-d H:i:s'}
					},{
						name : 'applyStatusZh',
						index : 'applyStatusZh',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'applyStatus',
						index : 'applyStatus',
						hidedlg : true,
						hidden : true
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#provideGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "resultList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "id"
		     },
			multiselect: true,
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("provideGrid");
				$("#id").val(selectValue);
			},
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#id").val("");
				}else{//全部选中时
					var ids = $("#provideGrid").jqGrid('getDataIDs');
					$("#id").val(ids);
				}
			},
			onPaging:function(pgButton) {
				$("#id").val("");
			},
			gridComplete: function(){
			    var ids = $("#provideGrid").jqGrid('getDataIDs');
			    for(var i=0;i < ids.length;i++){
			     var rowArray = $("#provideGrid").jqGrid('getRowData',ids[i]);
			     var editBtn = "<a href='#' onclick='provide.hrefView("+rowArray.id+")' style='text-decoration:underline;font-weight:normal;'>"+rowArray.applyCode+"</a>";
			     $("#provideGrid").jqGrid('setCell', ids[i], 'applyCode', editBtn);
			    }
		   }		
		});
		
	},
	/**
	 * 获取页面上的对象
	 */
	getPageProvide:function() {
		var jsonArr = $("#DocListProvide").mulLineWidget.getData();
		var applyDetList = jsonArr;
		
		var vcApply = new Object();
		vcApply.applyDetList = applyDetList;
		vcApply.id = $("#vcApply_id").val();
		vcApply.backReason = $("#backReason").val();
		
		return vcApply;
	},
	/**
	 * ajax提交
	 */
	ajaxSaveProvide:function(vcProvide) {
		var str = JSON.stringify(vcProvide);	
		var params = {
           json : str
        };
		setTimeout(function(){
			 $("#saveButProvideEdit").removeAttr("disabled");
			  $("#submitButProvideEdit").removeAttr("disabled");
		},800);
		
		$.ajax({   
             type:'post',   
             url: $.taHash.get("ctx")+"/provideJson/saveProvide.do?ajax=true",   
             dataType:'json',   
             data:params,
             success:function(msg){   
             	  alert(msg);
             	  
             	  $("#saveButProvideEdit").removeAttr("disabled");
	              $("#submitButProvideEdit").removeAttr("disabled");
	              
             	  if(msg=="操作成功") {
	             	  $('#editPageProvide').dialog('close');
	             	  
	             	  $("#saveButProvideEdit").removeAttr("disabled");
	             	  $("#submitButProvideEdit").removeAttr("disabled");
	             	  
	             	  provide.gridReload();             	  	
             	  }

         	 }
         }); 	
	}, clearGridData:function(gridId) {
		$("#"+gridId).clearGridData();
		$("#id").val("");
	},
	hrefView:function(id) {
		$("#editPageProvide").empty();
		
		//ajax获取要修改的数据
		common.ajaxGetData("viewPageApply", $.taHash.get("ctx")+"/apply/viewApplyView.do?id="+id+"&ajax=true",null);
		
		setTimeout(function(){
			$('#viewPageApply').dialog('open');
		},300);
	}
}