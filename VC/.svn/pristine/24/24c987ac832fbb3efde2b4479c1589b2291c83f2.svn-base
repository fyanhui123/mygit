var agencyEdit = {
		
	/**
	 * 初始化方法
	 */
	initPage : function(){
		
		/**
		 * 初始化表单验证组件
		 */
		$("#agencyForm").validate();
		
		/**
		 * 保存按钮事件
		 */
		$("#saveAgency").click( function(){
			if($("#agencyForm").validate().form()){
				$("#saveAgency").attr("disabled",true);
				var formStr = $("#agencyForm").serialize();
				common.ajaxSubmitDfCallBack(agencyEdit.saveCallback, 
						 $.taHash.get("ctx")+"/vclevelJson/saveAgencyOrg.do?ajax=true", 
						 formStr);
			}else{
				return false;
			}
		});
		
		/**
		 * 返回按钮事件
		 */
		$("#agencyReturn").click( function(){
			$('#agencyEditPage').dialog('close');
		});
		
		//初始化销售渠道大类下拉框
		$("#businessCode").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/vclevelJson/getJSONBusiness.do?ajax=true"
		});
		
		//初始化销售渠道小类下拉框
		$("#businessDetailCode").dropDownList({
			trigger: '#businessCode',    
			event: 'change',
			dataField:'name',
			url: $.taHash.get("ctx")+"/vclevelJson/getJSONBusinessDetail.do?ajax=true",
			dataType:'json'
		});
		
		//给下拉框设置默认值
		setTimeout(function(){
			$("#businessCode").dropDownList("selected",$("#businessCodeDropDownList").val());
			setTimeout(function(){
				$("#businessDetailCode").dropDownList("selected",$("#businessDetailCodeDropDownList").val());
			},300);
		},300);
	},
	
	/**
	 * 保存的回调函数
	 */
	saveCallback : function(data){
		if("0"==data){
			alert("中介机构代码已存在，不能重复！");
			$("#saveAgency").removeAttr("disabled");
		}else if("1"==data){
			alert("保存成功！");
			//关闭dialog
			$('#agencyEditPage').dialog('close');
			$("#saveAgency").removeAttr("disabled");
			//清空表单数据
			$("#agencyForm")[0].reset();
			//查询中介机构列表
			vcLevelSet.queryAgency();
		}
	}
}