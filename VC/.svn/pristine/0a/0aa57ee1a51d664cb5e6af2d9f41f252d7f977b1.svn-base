var insuranceCardQuery = {	
		
	loadGridUrl : "/insucardJson/queryInsuCard.do?ajax=true",
	
	initPage:function() {		
		//险类
		$("#queryDtoInsuTypeList").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true"
		});
		//险种
		$("#queryDtoInsuKindList").dropDownList({
			trigger: '#queryDtoInsuTypeList',    
			event: 'change',
			dataField:'name',			
			dataType:'json',		  
			url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuKindList.do?ajax=true"
		});
		
		 //单证类型
		/*$("#queryDtoDocVerCode").dropDownList({			
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONDocVersionInfoList.do?ajax=true&docTypeCode=JK&trigger=no"
		});*/
		 //机构选择
		$("#queryDtoArea").dropDownDivList({
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&minLevel=3&root=notCurOrg&singleSelect=true&tagCodeId=queryDtoArea&tagNameId=queryDtoAreaName"
		});
		
		
		
		/**
		* @description 查询按钮事件
		* @param  queryResource 查询按钮ID
		* @return 
		*/
		$("#queryInsuranceCardBut").click( function() {
			insuranceCardQuery.gridReload();
		});
		
		/**
		 * 导入 2013-06-04
		 */
		$("#importInsuranceCardBut").click( function() {
			var selectIds = $("#selectIds").attr("value");
			var patt = new RegExp(',');			
			if ("" != selectIds) {	
				if(patt.test(selectIds)){ //字符串存在返回true否则返回false
				    alert("只能选择一条记录进行关联");
				    return false;
				}else{	
					url=$.taHash.get("ctx")+"/insucard/initImport.do?idVcDocVersionInfo="+selectIds;
					//common.ajaxSubmit("insuCardImport",$.taHash.get("ctx")+"/insucard/initImport.do?ajax=true&idVcDocVersionInfo="+selectIds);
					/*setTimeout(function(){					
						$("#insuCardImport").dialog('open');
					},200);*/
					common.formSubmit ("queryForm",url);
					//common.localHref(url);
						
				}
			} else {
				alert("请选择激活卡！");
				return false;
			}			
		});		
		
		$("#insuranceCardGrid").jqGrid({			
			url : $.taHash.get("ctx")+ insuranceCardQuery.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',			
			colNames : ['单证类型代码', '单证类型名称','所属机构','面值'],
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
						name : 'value',
						index : 'value',
						autowidth : true,
						resizable : true,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#insuranceCardGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "resultList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "idVcDocVersionInfo"
		     },
			multiselect: true,			
		
		    gridComplete: function(){
		     },
			
			/**
			* @description 获取选中的值
			* @param  
			* @return 
			*/
			onSelectRow: function(id){
				var selectValue = common.selectRowValue("insuranceCardGrid");
				$("#selectIds").val(selectValue);
			}
		});
		
		
	   
	},
	
	/**
	* @description 根据条件查询信息
	* @param  
	* @return 
	*/
	gridReload : function() {
		$("#selectIds").val("");
		var queryParam=$("#queryForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+ insuranceCardQuery.loadGridUrl+"&"+queryParam;
		jQuery("#insuranceCardGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{},
			page : 1
		});
		jQuery("#insuranceCardGrid").trigger("reloadGrid");
	}
}