var takerEdit = {

	/**
	 * 初始化函数
	 */
	initPage : function(){
	
		/**
		 * 初始化表单验证组件
		 */
		$("#takerForm").validate();
		
		/**
		 * 保存按钮事件
		 */
		$("#saveTaker").click( function(){
			if($("#takerForm").validate().form()){
				$("#saveTaker").attr("disabled",true);
				var formStr = $("#takerForm").serialize();
				common.ajaxSubmitDfCallBack(takerEdit.saveCallback, 
						 $.taHash.get("ctx")+"/vclevelJson/saveVcTaker.do?ajax=true", 
						 formStr);
			}else{
				return false;
			}
		});
		
		/**
		 * 返回按钮事件
		 */
		$("#takerReturn").click( function(){
			$('#takerEditPage').dialog('close');
		});
		
		//初始化使用人类型下拉框
		$("#takerTypeCode").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/vclevelJson/getJSONCodeList.do?ajax=true&codeType=TakerType"
		});
		
		//初始化卡折类型下拉框
		$("#passbookOrCard").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/vclevelJson/getJSONCodeList.do?ajax=true&codeType=CardType"
		});
		
		//给下拉框设置默认值
		setTimeout(function(){
			$("#takerTypeCode").dropDownList("selected",$("#takerTypeCodeDropDownList").val());
			$("#passbookOrCard").dropDownList("selected",$("#passbookOrCardDropDownList").val());
			$("#takerAgencyOrgCode").dropDownList("selected",$("#agencyOrgCodeDropDownList").val());
		},300);
	},
	
	/**
	 * 保存的回调函数
	 */
	saveCallback : function(data){
		var flag=data.flag;
		if("0"==flag){
			alert(data.msg);
			$("#saveTaker").removeAttr("disabled");
		}else if("-1"==flag){
			alert("该用户不存在！");
			$("#saveTaker").removeAttr("disabled");
		}else if("1"==flag){
			alert("保存成功！");
			//关闭dialog
			$('#takerEditPage').dialog('close');
			$("#saveTaker").removeAttr("disabled");
			//清空表单数据
			$("#takerForm")[0].reset();
			//查询使用人列表
			vcLevelSet.queryTaker();
		}
	}
}