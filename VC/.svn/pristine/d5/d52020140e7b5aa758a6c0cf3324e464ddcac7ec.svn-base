var orgChangeEdit = {
		
	//岗位人员机构变更
	initRoleOrgChangePage : function(){
		/**
		 * 初始化机构自动完成组件
		 */	   
		$( "#newRoleUpperCode" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/vclevelJson/getJSONCompanyList.do?ajax=true",
			minLength: 4,
			height: 200,
			width:220
		});
		
		/**
		 * 初始化表单验证组件
		 */
		$("#roleOrgChangeForm").validate();
		
		/**
		 * 保存按钮事件
		 */
		$("#saveRoleOrgChange").click( function(){
			if($("#roleOrgChangeForm").validate().form()){
				var oldOrgCode=$("#oldRoleUpperCode").val();
				var newOrgCode=$("#newRoleUpperCode").val();
				if(oldOrgCode==newOrgCode){
					alert("新机构和原机构相同，请重新选择！");
					return false;
				}
				$("#saveRoleOrgChange").attr("disabled",true);				
				var formStr = $("#roleOrgChangeForm").serialize();
				common.ajaxSubmitDfCallBack(orgChangeEdit.roleOrgChangeCallback, 
						 $.taHash.get("ctx")+"/vclevelJson/roleOrgChange.do?ajax=true", 
						 formStr);
			}else{
				return false;
			}
		});
		
		/**
		 * 返回按钮事件
		 */
		$("#roleOrgChangeReturn").click( function(){
			$('#roleOrgChangePage').dialog('close');
		});
	},
	
	/**
	 * 岗位人员变更保存的回调函数
	 */
	roleOrgChangeCallback : function(data){
			alert("操作成功！");
			$('#roleOrgChangePage').dialog('close');
			$("#saveRoleOrgChange").removeAttr("disabled");
			//清空表单数据
			$("#roleOrgChangeForm")[0].reset();
			//查询岗位列表
			vcLevelSet.queryRole();	
	},
	
	
	
	//单证使用人机构变更
	initTakerOrgChangePage : function(){
		/**
		 * 初始化机构自动完成组件
		 */		
		$( "#newTakerComCode" ).taAutoComplete({
			source: $.taHash.get("ctx")+"/vclevelJson/getJSONCompanyList.do?ajax=true",
			minLength: 4,
			height: 200,
			width:220
		});
		
		/**
		 * 初始化表单验证组件
		 */
		$("#takerOrgChangeForm").validate();
		
		/**
		 * 保存按钮事件
		 */
		$("#saveTakerOrgChange").click( function(){
			if($("#takerOrgChangeForm").validate().form()){
				var oldOrgCode=$("#oldTakerComCode").val();
				var newOrgCode=$("#newTakerComCode").val();
				if(oldOrgCode==newOrgCode){
					alert("新机构和原机构相同，请重新选择！");
					return false;
				}
				$("#saveTakerOrgChange").attr("disabled",true);
				var formStr = $("#takerOrgChangeForm").serialize();
				common.ajaxSubmitDfCallBack(orgChangeEdit.takerOrgChangeCallback, 
						 $.taHash.get("ctx")+"/vclevelJson/takerOrgChange.do?ajax=true", 
						 formStr);
			}else{
				return false;
			}
		});
		
		/**
		 * 返回按钮事件
		 */
		$("#takerOrgChangeReturn").click( function(){
			$('#takerOrgChangePage').dialog('close');
		});
	},
	
	/**
	 * 单证使用人机构变更保存的回调函数
	 */
	takerOrgChangeCallback : function(data){
			alert("操作成功！");
			$('#takerOrgChangePage').dialog('close');
			$("#saveTakerOrgChange").removeAttr("disabled");
			//清空表单数据
			$("#takerOrgChangeForm")[0].reset();
			//查询岗位列表
			vcLevelSet.queryTaker();		
	}
}