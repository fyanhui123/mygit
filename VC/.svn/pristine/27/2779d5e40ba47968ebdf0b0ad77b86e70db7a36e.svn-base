var d = new dTreeNew('d'); 
var var_orgId;//选中的机构ID
var var_orgCode;//选中的机构代码
var var_orgName;//选中的机构名称

var setting = {
		
 		data: {
				key: {
					title:"info",
					name:"info"					
				},
				simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pid", 	                       
						rootPId: -1
				}
			},
			callback: {				
				onClick: onClick_S
			}
		};

function onClick_S(event, treeId, treeNode, clickFlag) {
	//给全局变量赋值
	var_orgId = treeNode.id;
	var_orgCode = treeNode.value;
	var_orgName = treeNode.info;	
	//查询机构列表
	vcLevelSet.queryCompany();
	//查询岗位列表
	vcLevelSet.queryRole();	
	//查询中介机构列表
	vcLevelSet.queryAgency();
	//查询使用人列表
	vcLevelSet.queryTaker();
}		

var vcLevelSet = {
	//loadGridUrl :$.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&minLevel=4",	
	loadGridUrl : "/vclevelJson/queryLevelSet.do?ajax=true",
	loadAgencyGridUrl : "/vclevelJson/queryAgencyOrg.do?ajax=true",
	loadTakerGridUrl : "/vclevelJson/queryTaker.do?ajax=true",
	initPage: function(){
	
		$("#tabs").tabs();
		
		$('body').layout({
				west__size:320,
				west__resizable:false,
				west__closable:true,
				closable:false,	// pane can open & close
				resizable:false,	// when open, pane can be resized 
				slidable:true	// when closed, pane can 'slide' open over other panes - closes on mouse-out
		});

		$('#tabs-1,#tabs-2,#tabs-3,#tabs-4').height($('#rightDiv').height()-Math.round($('#ulHead').height())-27);
		$('#tabs-1').layout({
			north__resizable:false,
			closable:false,	// pane can open & close
			resizable:false,	// when open, pane can be resized 
			slidable:true	//
		});
		
		/**
		 * 加载机构数
		 */
		vcLevelSet.reloadCompanyTree();
		
		/**
		* @description 初始化机构dialog
		*/
		$("#companyEditPage").dialog({
			modal: true,
			height:400,
			width:850,
			autoOpen: false
		});
		
		/**
		* @description 初始化岗位dialog
		*/
		$("#roleEditPage").dialog({
			modal: true,
			height:400,
			width:850,
			autoOpen: false
		});
		
		/**
		* @description 初始化中介机构dialog
		*/
		$("#agencyEditPage").dialog({
			modal: true,
			height:400,
			width:850,
			autoOpen: false
		});
		
		/**
		* @description 初始化使用人dialog
		*/
		$("#takerEditPage").dialog({
			modal: true,
			height:400,
			width:850,
			autoOpen: false
		});
		
		/**
		* @description 岗位人员机构变更dialog
		*/
		$("#roleOrgChangePage").dialog({
			modal: true,
			height:400,
			width:850,
			autoOpen: false
		});
		
		/**
		* @description 单证使用人机构变更dialog
		*/
		$("#takerOrgChangePage").dialog({
			modal: true,
			height:400,
			width:850,
			autoOpen: false
		});
		
		/**
		 * 新增机构按钮事件
		 */
		$("#addCompany").click(function(){
			if(!var_orgId || var_orgId==""){
				alert("请先选择左侧机构树中的机构！");
				return false;
			}
			//清空数据
			$("#companyEditPage input").each(function() {
				$(this).val("");
			});
			$("#comParentOrgId").val(var_orgId);
			$("#comUpperCode").val(var_orgCode);
			$("#comUpperName").val(var_orgName);
			$('#comEditType').val("new");
			$("#comCode").removeAttr("disabled");
			//$("#comType").removeAttr("disabled");
			$('#companyEditPage').dialog('open');
			
			/**
			 * 初始化自动完成组件
			 */
			var comType = $("#comType").val();
			var upperComCode = $("#comUpperCode").val();
			$( "#comCode" ).taAutoComplete({
				source: $.taHash.get("ctx")+"/vclevelJson/getJSONCompanyList.do?ajax=true&comType="+comType+"&upperComCode="+upperComCode,
				minLength: 0,
				height: 200,
				width:220
			});
		});
		
		/**
		 * 新增岗位按钮事件
		 */
		$("#addRole").click(function(){
			if(!var_orgId || var_orgId==""){
				alert("请先选择左侧机构树中的机构！");
				return false;
			}
			//清空数据
			$("#roleEditPage input").each(function() {
				$(this).val("");
			});
			$("#roleParentOrgId").val(var_orgId);
			$("#roleUpperCode").val(var_orgCode);
			$("#roleUpperName").val(var_orgName);
			$('#roleEditType').val("new");
			$("#roleCode").removeAttr("disabled");
			$('#roleEditPage').dialog('open');
			
			/**
			 * 初始化自动完成组件
			 */
			var comCode = $("#roleUpperCode").val();
			$("#roleCode").taAutoComplete({
				source: $.taHash.get("ctx")+"/vclevelJson/getJSONUserList.do?ajax=true&comCode="+comCode,
				minLength: 0,
				height: 200,
				width:220
			});
		});
		
		/**
		 * 新增中介机构按钮事件
		 */
		$("#addAgency").click(function(){
			if(!var_orgId || var_orgId==""){
				alert("请先选择左侧机构树中的机构！");
				return false;
			}
			//清空数据
			$("#agencyEditPage input").each(function() {
				$(this).val("");
			});
			$("#agencyComCode").val(var_orgCode);
			$("#agencyComName").val(var_orgName);
			$('#agencyEditType').val("new");
			$("#agencyCode").removeAttr("readOnly");
			$("#agencyName").removeAttr("readOnly");
			$('#agencyEditPage').dialog('open');
		});
		
		/**
		 * 新增使用人按钮事件
		 */
		$("#addTaker").click(function(){
			if(!var_orgId || var_orgId==""){
				alert("请先选择左侧机构树中的机构！");
				return false;
			}
			//清空数据
			$("#takerEditPage input").each(function() {
				$(this).val("");
			});
			$("#takerComCode").val(var_orgCode);
			$("#takerComName").val(var_orgName);
			$('#takerEditType').val("new");
			$("#takerCode").removeAttr("disabled");
			//$("#takerName").removeAttr("readOnly");
			$("#takerCodeName").attr("readOnly",true);
			$('#takerEditPage').dialog('open');
			
			/**
			 * 初始化使用人自动完成组件
			 */
			var comCode = $("#takerComCode").val();
			$("#takerCode").taAutoComplete({
				source: $.taHash.get("ctx")+"/vclevelJson/getJSONTakerList.do?ajax=true&comCode="+comCode,
				minLength: 0,
				height: 200,
				width:220
			});
			//初始化所属中介机构下拉框
			$("#takerAgencyOrgCode").dropDownList({
				dataField:'value',
				dataType:'json',
			    url: $.taHash.get("ctx")+"/vclevelJson/getJSONAgencyOrgList.do?ajax=true&comCode="+var_orgCode
			});
		});
		
		/**
		 * 修改机构按钮事件
		 */
		$("#modifyCompany").click(function(){
			//获取选中行的id
			var id = $("#companyId").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					// ajax获取要修改的数据
					//common.ajaxGetData("companyEditPage", $.taHash.get("ctx")+"/vclevel/beforeLevelSetUpdate.do?ajax=true&id="+id,null);
					$.ajax({
						datatype: 'html',
						url: $.taHash.get("ctx")+"/vclevel/beforeCompanyUpdate.do?ajax=true&id="+id + "&random=" + Math.random(),
						type: "post",
						data: null,
						success: function(data){
							$("#companyEditPage").html(data);
							$("#comParentOrgId").val(var_orgId);
							$("#comUpperCode").val(var_orgCode);
							$("#comUpperName").val(var_orgName);
							//机构代码不能修改
							$("#comCode").attr("disabled",true);
							//机构类型不能修改
							//$("#comType").attr("disabled",true);
							$('#comEditType').val("edit");
						}	
					});
					$('#companyEditPage').dialog('open');
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		/**
		 * 修改岗位按钮事件
		 */
		$("#modifyRole").click(function(){
			//获取选中行的id
			var id = $("#roleId").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					// ajax获取要修改的数据
					//common.ajaxGetData("companyEditPage", $.taHash.get("ctx")+"/vclevel/beforeLevelSetUpdate.do?ajax=true&id="+id,null);
					$.ajax({
						datatype: 'html',
						url: $.taHash.get("ctx")+"/vclevel/beforeRoleUpdate.do?ajax=true&id="+id + "&random=" + Math.random(),
						type: "post",
						data: null,
						success: function(data){
							$("#roleEditPage").html(data);
							$("#roleParentOrgId").val(var_orgId);
							$("#roleUpperCode").val(var_orgCode);
							$("#roleUpperName").val(var_orgName);
							//人员代码不能修改
							$("#roleCode").attr("disabled",true);
							$('#roleEditType').val("edit");
						}	
					});
					$('#roleEditPage').dialog('open');
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		/**
		 * 修改中介机构按钮事件
		 */
		$("#modifyAgency").click(function(){
			//获取选中行的id
			var id = $("#agencyId").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					$.ajax({
						datatype: 'html',
						url: $.taHash.get("ctx")+"/vclevel/beforeAgencyOrgUpdate.do?ajax=true&id="+id + "&random=" + Math.random(),
						type: "post",
						data: null,
						success: function(data){
							$("#agencyEditPage").html(data);
							$("#agencyComCode").val(var_orgCode);
							$("#agencyComName").val(var_orgName);
							$("#agencyCode").attr("readOnly",true);
							$("#agencyName").attr("readOnly",true);
							$('#agencyEditType').val("edit");
						}	
					});
					$('#agencyEditPage').dialog('open');
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		/**
		 * 修改使用人按钮事件
		 */
		$("#modifyTaker").click(function(){
			//获取选中行的id
			var id = $("#takerId").attr("value");
			var patt = new RegExp(',');//
			if("" != id){
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录！");
				}else{
					$.ajax({
						datatype: 'html',
						url: $.taHash.get("ctx")+"/vclevel/beforeVcTakerUpdate.do?ajax=true&id="+id + "&random=" + Math.random(),
						type: "post",
						data: null,
						success: function(data){
							$("#takerEditPage").html(data);
							$("#takerComCode").val(var_orgCode);
							$("#takerComName").val(var_orgName);
							$("#takerCode").attr("disabled",true);
							//$("#takerName").attr("readOnly",true);
							$("#takerCodeName").removeAttr("readOnly");
							//初始化所属中介机构下拉框
							$("#takerAgencyOrgCode").dropDownList({
								dataField:'value',
								dataType:'json',
							    url: $.taHash.get("ctx")+"/vclevelJson/getJSONAgencyOrgList.do?ajax=true&comCode="+var_orgCode
							});
							$('#takerEditType').val("edit");
						}	
					});
					$('#takerEditPage').dialog('open');
				}
			}else{
				alert("请选择一条记录！");
			}
		});
		
		/**
		 * 删除机构按钮事件
		 */
		$("#deleteCompany").click(function(){
			var id = $("#companyId").attr("value");
			if("" != id){
				$.post($.taHash.get("ctx")+"/vclevelJson/checkChildCompany.do?ajax=true",{id:id},function(data,testStuatus){
					if(data=="1"){
						if(confirm("确定注销/恢复?")){
							common.ajaxSubmitDfCallBack(vcLevelSet.deleteCompanyCallback, $.taHash.get("ctx")+ "/vclevel/deleteLevelSet.do?ajax=true", {id : id});
						}
					}else if(data=="0"){
						alert("该机构存在下属机构或人员，不能删除！");
					}
				});
			}else{
				alert("请选择需要注销/恢复的记录！");
			}
		});
		
				
		/**
		 * 删除岗位按钮事件
		 */
		$("#deleteRole").click(function(){
			var id = $("#roleId").attr("value");
			if("" != id){
				if(confirm("确定注销/恢复?")){
					common.ajaxSubmitDfCallBack(vcLevelSet.deleteRoleCallback, $.taHash.get("ctx")+ "/vclevel/deleteLevelSet.do?ajax=true", {id : id});
				}
			}else{
				alert("请选择需要注销/恢复的记录！");
			}
		});		
		
		//岗位人员机构变更
		$("#changeRoleOrg").click(function(){
			var id = $("#roleId").val();
			var patt = new RegExp(',');//
			if("" != id){
				if(patt.test(id)){ //字符串存在返回true否则返回false
				    alert("只能选择一条记录进行机构变更");
				    return false;
				}else{
					$.post($.taHash.get("ctx")+"/vclevelJson/checkBeforeChangeOrg.do?ajax=true",{id:id,objectFlag:"2"},function(data){
						if(data.hasError==false){
							 common.ajaxSubmit("roleOrgChangePage",
									          $.taHash.get("ctx")+"/vclevel/initRoleOrgChange.do?ajax=true&id="+id + "&random=" + Math.random(),
									          null);
								setTimeout(function(){	
									$('#roleOrgChangePage').dialog('open');
								});							
							}else {
							alert(data.msg);
						}
					});
				}
			}else{
				alert("请选择记录！");
			}
		});
		
		/**
		 * 删除中介机构按钮事件
		 */
		$("#deleteAgency").click(function(){
			var id = $("#agencyId").attr("value");
			if("" != id){
				if(confirm("确定注销/恢复?")){
					common.ajaxSubmitDfCallBack(vcLevelSet.deleteAgencyCallback, $.taHash.get("ctx")+ "/vclevel/deleteVcAgencyOrg.do?ajax=true", {id : id});
				}
			}else{
				alert("请选择需要注销/恢复的记录！");
			}
		});
		
		/**
		 * 删除使用人按钮事件
		 */
		$("#deleteTaker").click(function(){
			var id = $("#takerId").attr("value");
			if("" != id){
				if(confirm("确定注销/恢复?")){
					common.ajaxSubmitDfCallBack(vcLevelSet.deleteTakerCallback, $.taHash.get("ctx")+ "/vclevel/deleteVcTaker.do?ajax=true", {id : id});
				}
			}else{
				alert("请选择需要注销/恢复的记录！");
			}
		});
			
		
		//单证使用人机构变更
		$("#changeTakerOrg").click(function(){
			var id = $("#takerId").val();
			var patt = new RegExp(',');//
			if("" != id){
				if(patt.test(id)){ //字符串存在返回true否则返回false
				    alert("只能选择一条记录进行机构变更");
				    return false;
				}else{
					$.post($.taHash.get("ctx")+"/vclevelJson/checkBeforeChangeOrg.do?ajax=true",{id:id,objectFlag:"3"},function(data){
						if(data.hasError==false){
							 common.ajaxSubmit("takerOrgChangePage",
									          $.taHash.get("ctx")+"/vclevel/initTakerOrgChange.do?ajax=true&id="+id + "&random=" + Math.random(),
									          null);
								setTimeout(function(){	
									$('#takerOrgChangePage').dialog('open');
								});							
							}else {
							alert(data.msg);
						}
					});
				}
			}else{
				alert("请选择记录！");
			}
		});
		
		/**
		 * 初始化机构分页表格     fyh 
		 */
		$("#companyGrid").jqGrid({
			//url : $.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&minLevel=4",
			url : $.taHash.get("ctx")+ vcLevelSet.loadGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','机构代码','机构名称', '上级机构', '显示序号', '状态'],
			colModel : [{
						name : 'id',
						index : 'id',
						hidden : true
					},{
						name : 'unitCode',
						index : 'unitCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'unitName',
						index : 'unitName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'comName',
						index : 'comName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'displayNo',
						index : 'displayNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'validStatus',
						index : 'validStatus',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#companyGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcLevelList",
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
				var selectValue = common.selectRowValue("companyGrid");
				$("#companyId").val(selectValue);
			},
			
			/**
			* @description 全选触发时候获取所有选中的Id值
			* @return 
			*/
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#companyId").val("");
				}else{//全部选中时
					var ids = $("#companyGrid").jqGrid('getDataIDs');
					$("#companyId").val(ids);
				}
			}
		});
		
		
		/**
		 * 初始化岗位分页表格
		 */
		$("#roleGrid").jqGrid({
		//  url : $.taHash.get("ctx")+ vcLevelSet.loadGridUrl,
			url : $.taHash.get("ctx")+"/common/initQueryOrgZTree.do?ajax=true&singleSelect=true&minLevel=4",
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','人员代码','人员名称', '上级机构', '显示序号', '状态','人员类型'],
			colModel : [{
						name : 'id',
						index : 'id',
						hidden : true
					},{
						name : 'unitCode',
						index : 'unitCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'unitName',
						index : 'unitName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'comName',
						index : 'comName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'displayNo',
						index : 'displayNo',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'validStatus',
						index : 'validStatus',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'invoiceFlag',
						index : 'invoiceFlag',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#roleGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcLevelList",
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
				var selectValue = common.selectRowValue("roleGrid");
				$("#roleId").val(selectValue);
			},
			
			/**
			* @description 全选触发时候获取所有选中的Id值
			* @return 
			*/
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#roleId").val("");
				}else{//全部选中时
					var ids = $("#roleGrid").jqGrid('getDataIDs');
					$("#roleId").val(ids);
				}
			}
		});
		
		/**
		 * 初始化中介机构分页表格
		 */
		$("#agencyGrid").jqGrid({
			url : $.taHash.get("ctx")+ vcLevelSet.loadAgencyGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','中介机构代码','中介机构名称', '归属机构', '销售渠道大类', '销售渠道小类', '是否销售网点', '状态'],
			colModel : [{
						name : 'id',
						index : 'id',
						hidden : true
					},{
						name : 'agencyOrgCode',
						index : 'agencyOrgCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'agencyOrgName',
						index : 'agencyOrgName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'orgName',
						index : 'orgName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'businessName',
						index : 'businessName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'businessDetailName',
						index : 'businessDetailName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'isSalesNet',
						index : 'isSalesNet',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'status',
						index : 'status',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#agencyGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcAgencyOrgList",
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
				var selectValue = common.selectRowValue("agencyGrid");
				$("#agencyId").val(selectValue);
			},
			
			/**
			* @description 全选触发时候获取所有选中的Id值
			* @return 
			*/
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#agencyId").val("");
				}else{//全部选中时
					var ids = $("#agencyGrid").jqGrid('getDataIDs');
					$("#agencyId").val(ids);
				}
			}
		});
		
		/**
		 * 初始化使用人分页表格
		 */
		$("#takerGrid").jqGrid({
			url : $.taHash.get("ctx")+ vcLevelSet.loadTakerGridUrl,
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: '230px',
			colNames : ['id','使用人代码','使用人名称', '归属机构', '所属中介机构', '使用人类型', '状态'],
			colModel : [{
						name : 'id',
						index : 'id',
						hidden : true
					},{
						name : 'takerCode',
						index : 'takerCode',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'takerName',
						index : 'takerName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'orgName',
						index : 'orgName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'agencyOrgName',
						index : 'agencyOrgName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'takerTypeName',
						index : 'takerTypeName',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					},{
						name : 'status',
						index : 'status',
						autowidth : true,
						resizable : true,
						sortable : false,
						align : 'left'
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#takerGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "vcTakerList",
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
				var selectValue = common.selectRowValue("takerGrid");
				$("#takerId").val(selectValue);
			},
			
			/**
			* @description 全选触发时候获取所有选中的Id值
			* @return 
			*/
			onSelectAll: function(id,status){
				//没有选中时
				if(status==false){
					$("#takerId").val("");
				}else{//全部选中时
					var ids = $("#takerGrid").jqGrid('getDataIDs');
					$("#takerId").val(ids);
				}
			}
		});
	},
	
	/**
	 * 重新加载机构数
	 */
	reloadCompanyTree:function(){
		//清空全局变量
		var_orgId = "";
		var_orgCode = "";
		var_orgName = "";
		//清空表单数据
		$("#companyForm")[0].reset();
		$("#roleForm")[0].reset();
		$("#agencyForm")[0].reset();
		$("#takerForm")[0].reset();
		//清空列表
		$("#companyGrid").clearGridData();
		$("#roleGrid").clearGridData();
		$("#agencyGrid").clearGridData();
		$("#takerGrid").clearGridData();
		//清空勾选的id
		$("#companyId").val("");
		$("#roleId").val("");
		$("#agencyId").val("");
		$("#takerId").val("");
		//刷新机构树
		$.ajax({
			datatype: 'json',
			url: $.taHash.get("ctx")+"/vclevelJson/initQueryOrgZTree.do?ajax=true&random="+Math.random(),
			type: "post",
			data: {root:"notCurOrg"},
			success: function(data){
			   $.fn.zTree.init($("#companyTree"), setting, data.zTreeList);
			}	
		});		
	},
	
	/**
	 * 进行机构分页表格查询
	 */
	queryCompany : function() {
		$("#companyId").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ vcLevelSet.loadGridUrl;
		jQuery("#companyGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				parentOrgId : var_orgId,
				unitType : "0"
			},
			datatype : "json",
			page : 1
		});
		jQuery("#companyGrid").trigger("reloadGrid");
	},
	
	/**
	 * 进行岗位分页表格查询
	 */
	queryRole : function() {
		$("#roleId").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ vcLevelSet.loadGridUrl;
		jQuery("#roleGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				parentOrgId : var_orgId,
				unitType : "1"
			},
			datatype : "json",
			page : 1
		});
		jQuery("#roleGrid").trigger("reloadGrid");
	},
	
	/**
	 * 进行中介机构分页表格查询
	 */
	queryAgency : function() {
		$("#agencyId").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ vcLevelSet.loadAgencyGridUrl;
		jQuery("#agencyGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				orgCode : var_orgCode
			},
			datatype : "json",
			page : 1
		});
		jQuery("#agencyGrid").trigger("reloadGrid");
	},
	
	/**
	 * 进行使用人分页表格查询
	 */
	queryTaker : function() {
		$("#takerId").val("");
		var gridReloadUrl = $.taHash.get("ctx")+ vcLevelSet.loadTakerGridUrl;
		jQuery("#takerGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData : {
				orgCode : var_orgCode
			},
			datatype : "json",
			page : 1
		});
		jQuery("#takerGrid").trigger("reloadGrid");
	},
	
	/**
	 * 删除机构的回调函数
	 */
	deleteCompanyCallback : function(){
		alert("操作成功！");
		//重新加载机构数
		vcLevelSet.reloadCompanyTree();
	},
	
	/**
	 * 删除岗位的回调函数
	 */
	deleteRoleCallback : function(){
		alert("操作成功！");
		//查询岗位列表
		vcLevelSet.queryRole();	
	},
	
	/**
	 * 删除中介机构的回调函数
	 */
	deleteAgencyCallback : function(){
		alert("操作成功！");
		//查询中介机构列表
		vcLevelSet.queryAgency();
	},
	
	/**
	 * 删除使用人的回调函数
	 */
	deleteTakerCallback : function(){
		alert("操作成功！");
		//查询使用人列表
		vcLevelSet.queryTaker();	
	}
}