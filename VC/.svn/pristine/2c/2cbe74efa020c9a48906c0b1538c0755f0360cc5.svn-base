/**
* @fileOverview  demo js文件
* @author 廉超
* @date 2013-02-25
* @version 1.0
*/
var widgets = {
	
	/**
	* @description 所属机构数据（为json对象）
	* @param  
	* @return 
	*/
	codeLabelData : [ 
 		{"value":"SH","lable":"上海"},
 		{"value":"BJ","lable":"北京"},
 		{"value":"JS","lable":"江苏"},
 		{"value":"GD","lable":"广东"}
 	],
 	nameLableData : [ 
 		{value:"001",lable:"管理员"},
 		{value:"002",lable:"用户一"},
 		{value:"003",lable:"用户二"}
 	],
 	
	initPage: function(){
	
		/**
		* @description 所属机构数据（为json对象）
		* @param  source 如果source为URL，则返回结果为json对象
		* @return 
		*/
		$( "#deptCode" ).taAutoComplete({
			source: widgets.codeLabelData,
			minLength: 0
		});
		/**
		* @description 初始化支付类型自动完成组建（返回结果为json对象）
		* @param className为要查询的的数据表hibernate映射java文件名（VO）
		* @param  codeType为数据库表中代码类型
		* @param  minLength 输入多少字符执行查询
		* @return 
		*/
		$( "#InsuredType" ).taAutoComplete({
			source: $.taHash.get("ctx")+ "/queryCodeLable.do?className=CommonCode&codeType=CarFeeType",
			minLength: 0,
			height: 200,
			width:220
		});
		
		//初始化下拉选择
		$("#userName").taAutoComplete({
		    source: widgets.nameLableData,
		    twoInput: false,
		    minLength: 0
		});
		
		$( "#RiskType" ).taAutoComplete({
			source: $.taHash.get("ctx")+ "/queryCodeLable.do?className=CommonCode&codeType=RiskType",
			minLength: 1,
			height: 200,
			width:220,
			twoInput: false
		});
		
		/*
		* @description 初始化下拉框
		* @param  selectedValue 默认选中的值
		* @data  下拉列表的数据
		* @return
		 */
		$("#singliDropDownList").dropDownList({
			selectedValue : 3,
		    data: [{"label":"test1","value":"1"},
		           {"label":"test2","value":"2"},
		           {"label":"test3","value":"3"}]    
		});
		
		$("#address1").dropDownDivList( {
			isCache : 'false',
			source : $.taHash.get("ctx")+'/pages/subfunc/jsp/divData.jsp'
		});
		
	}
	
	
}