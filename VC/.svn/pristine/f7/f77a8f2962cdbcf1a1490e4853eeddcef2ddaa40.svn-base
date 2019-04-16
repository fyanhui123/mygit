
var roleListInquiry = {
	loadGridUrl : "/roleJson/roleList.do?ajax=true",
	
		initPage : function() {
		/**
		 * 导出按钮事件
		 */
		$("#exportRoleListInquiry").click(function() {
			var total = $("#rolelistquery").jqGrid('getGridParam','records');
			$("#exportRoleListInquiry").attr("disabled",true);
			var formStr = $("#subForm").serialize();
			var url = $.taHash.get("ctx")+ "/roleJson/exportRoleListInquiry.do?ajax=true&"+formStr+"&total="+total;
			common.ajaxSubmitDfCallBack(roleListInquiry.exportHref, url, null);
		});
	    //机构选择
		$("#roleListDtoOrgCode").dropDownDivList({		
			source:$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&minLevel=4&tagCodeId=roleListDtoOrgCode&tagNameId=roleListDtoOrgCodeName"
		});
		$("#roleListIsContainSubOrg").change( function() {		
			  if($(this).attr("checked")=="checked"){
				  $(this).val("1"); 
			  }else{
				  $(this).val("0");   
			  }		 
		    });
		/**
		 * @description 查询按钮事件
		 * @param queryInStoreInquiry
		 *            查询按钮ID
		 * @return
		 */
		$("#rolelistquerys").click(function(){	
			roleListInquiry.gridReload();
		});
		$("#rolelistquery").jqGrid( {
			url : $.taHash.get("ctx")+roleListInquiry.loadGridUrl,
			mtype : "POST",
			datatype : "local",
			autowidth : true,
			height : 'auto',	
			colNames : ['机构代码','机构名称','所属部门名称','用户名称','用户代码','员工代码','状态'],
			colModel : [ {
				name : 'companyCode',
				index : 'companyCode',
				width : '7%',
				resizable : true,
				align : 'left'
					
			}, {
				name : 'unitName',
				index : 'unitName',
				width : '25%',
				resizable : true,
				align : 'left'
			}, {
				name : 'departMent',
				index : 'departMent',
				width : '25%',
				resizable : true,
				align : 'left'
			}, {
				name : 'userName',
				index : 'userName',
				width : '10%',
				resizable : true,	
				align : 'left'
			},{
				name : 'userCode',
				index : 'userCode',
				width : '10%',
				resizable : true,	
				align : 'left'
			},{
				name : 'employeeId',
				index : 'employeeId',
				width : '8%',
				resizable : true,
				align : 'left'
			},{
				name : 'validKind',
				index : 'validKind',
				width : '5%',
				resizable : true,
				formatter : validKindFormatter,
				align : 'left'
			}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#inStoreInquiryGridPager',
			viewrecords : true,
			jsonReader : {
				root : "pubUserDefinfo",
				repeatitems : false,
				page : 'page',
				total : 'total',
				records : 'records',
				//id : "id"
			},
			multiselect : false,
			/**
			 * @description 获取选中的值
			 * @param
			 * @return
			 */
			onSelectRow : function(id) {
				var selectValue = common.selectRowValue("rolelistquery");
				$("#id").val(selectValue);
			}
		});
		function validKindFormatter(cellvalue, options, rowObject) {
			if (cellvalue == '0') {
				return '无效';
			} else if (cellvalue == '1') {
				return '有效';
			}
		}
		;
	},
	hiddenRelod :function(){
		$("#rolelistquery tr").find('td:eq(2)').hide();
		$(".ui-jqgrid-htable tr").find('th:eq(2)').hide();
	
	},
	showRelod : function(){
		$("#rolelistquery tr").find('td:eq(2)').show();
		$(".ui-jqgrid-htable tr").find('th:eq(2)').show();
		
	},
	gridReload : function() {
		var tmpStr = $("#subForm").serialize();
		var gridReloadUrl = $.taHash.get("ctx")+roleListInquiry.loadGridUrl+"&"+tmpStr;
		jQuery("#rolelistquery").jqGrid('setGridParam', {
			url : gridReloadUrl,
			datatype : "json",
			postData:{},
			page : 1
		});
		jQuery("#rolelistquery").trigger("reloadGrid");
	},
	exportHref : function(data) {
		if(!data || data==""){
			alert("生成文件失败！");
			$("#exportRoleListInquiry").removeAttr("disabled");
			return false;
		}
		var filePath=data.substring(0,data.indexOf("=="));
		var fileName=data.substring(data.indexOf("==")+2, data.length);
		var url = $.taHash.get("ctx") + "/common/download.do?fileUrl="+filePath+"&fileName="+fileName;
		url = encodeURI(url, "UTF-8");
		window.location.href=url;
		$("#exportRoleListInquiry").removeAttr("disabled");
	}
	
}
