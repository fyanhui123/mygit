var mulLine = {

	lineDataArry : [ 
		{title:"投保单号",fieldId:"polNo",fieldName:"polNo",type:"4",fieldClass:"required", minlength:"1",maxlength:"10"},
		{title:"险种代码",fieldId:"planCode",fieldName:"planCode",fieldLableName:"planName",type:"2", width:"180px", dataFormat:[{source: $.taHash.get("ctx")+ "/queryCodeLable.do?className=CommonCode&codeType=CarFeeType",minLength:0}]},
		{title:"性别",fieldId:"sex",fieldName:"sex",type:"2", width:"55px",dataFormat:[{ source:[{value:"M",lable:"男"},{value:"F",lable:"女"}],twoInput:false,minLength:0}]},
		{title:"总部",fieldId:"org",fieldName:"org",type:"3",width:"80px",dataFormat:[{ data: [{"label":"test 1","value":"1"},{"label":"test 2","value":"2"}]}]},
		{title:"分中心",fieldId:"subOrg",fieldName:"subOrg",type:"3",width:"80px",dataFormat:[{selectedValue:"2", trigger: 'org',event: 'change',dataField:'name', url: $.taHash.get("ctx")+ "/queryDropDownCodeLable.do?ajax=true"}]},
		{title:"投保金额",fieldId:"insum",fieldName:"insum",width:"55px",type:"4"},
		{title:"投保日期",fieldId:"insumdate",fieldName:"insumdate",type:"5"},
		{title:"密码",fieldId:"password",fieldName:"password",type:"7",width:"120px",disabled:"disabled"},
		{title:"操作",fieldId:"edit",fieldName:"edit",fieldVal:"按钮", type:"8",width:"40px"}
		],

	initPage : function (){
		
		$("#getBut").click( function() {
			var jsonArr = $("#RiskPlan").mulLineWidget.getData();
			alert(JSON.stringify(jsonArr));
			//common.formSubmit("mulLineForm", $.taHash.get("ctx")+"/subfunc/savePrinting.do?action=savePrinting&jsonArrStr="+JSON.stringify(jsonArr));
		});
		
		$("#mulLineForm").validate();
		
		$("#subBut").click( function() {
			//if($("#mulLineForm").validate().form()){
				alert($("#mulLineForm").validate().form());
				//common.formSubmit("mulLineForm", "#");
			//}
		});
		
		/**
		* @description 初始化表单验证组建
		* @param  saveBut 保存按钮ID
		* @return 
		*/
		$("#mulLineForm").validate();
		
		/**
		* @description 初始化多行录入组件
		* @param  hiddenPlus 是否隐藏添加一行的标志：0为显示，1为隐藏
		* @param  hiddenSubtraction 是否隐藏删除一行的标志：0为显示，1为隐藏
		* @param  columnData 表格列模版
		* @param  exeFuction 执行的自定义函数（用于更改某列的值）
		* @param  initJsonDate 初始化表格时加载的数据(本地数据)
		* @param  initDataUrl 初始化表格加载数据的URL(ajax方式获取数据，返回类型为json)
		* 
		* @return 
		*/
		$("#RiskPlan").mulLineWidget({
			hiddenPlus: '0',
			hiddenSubtraction: '0',
			columnData: mulLine.lineDataArry,
			exeFuction: mulLine.resetVal,
			initJsonDate: '[{"sex":"F","insum":"100","insumdate":"2011-01-02","org":"2","subOrg":"4","planCode":"D012","polNo":"p11111111","password":"ta88888"},{"sex":"F","insum":"100","insumdate":"2011-01-02","org":"1","subOrg":"3","planCode":"D010","polNo":"20604593832","password":"ta88888"}]'
		});
	},
	
	/**
	 * @description 自定义函数，让多行录入组件回调
	 * 
	 */
	resetVal : function (rIndex, objId){
		/**
		 * @description 更改字段值
		 * @param  参数1(org） 要更改值的标签Id
		 * @param  参数2(2） 要更改值的标签value
		 * 
		 */
		if(objId == "polNo"){
			$("#RiskPlan").mulLineWidget.resetVal(rIndex, "org", "2");
		}
		if(objId == "edit"){
			alert("你好，请在这里写按钮的点击事件");
		}
	}
}