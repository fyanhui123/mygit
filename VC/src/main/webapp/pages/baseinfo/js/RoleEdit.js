var roleEdit = {
		
	initPage : function(){
		
		/**
		 * 初始化表单验证组件
		 */
		$("#roleForm").validate();
		
		/**
		 * 保存按钮事件
		 */
		$("#saveRole").click( function(){
			if($("#roleForm").validate().form()){
				$("#saveRole").attr("disabled",true);
				var formStr = $("#roleForm").serialize();
				common.ajaxSubmitDfCallBack(roleEdit.saveCallback, 
						 $.taHash.get("ctx")+"/vclevelJson/saveRole.do?ajax=true", 
						 formStr);
			}else{
				return false;
			}
		});
		
		/**
		 * 返回按钮事件
		 */
		$("#roleReturn").click( function(){
			$('#roleEditPage').dialog('close');
		});
	},
	
	/**
	 * 保存的回调函数
	 */
	saveCallback : function(data){
		if("0"==data){
			alert("人员代码已存在，不能重复！");
			$("#saveRole").removeAttr("disabled");
		}else if("1"==data){
			alert("保存成功！");
			//关闭dialog
			$('#roleEditPage').dialog('close');
			$("#saveRole").removeAttr("disabled");
			//清空表单数据
			$("#roleForm")[0].reset();
			//查询岗位列表
			vcLevelSet.queryRole();	
		}
	}
}