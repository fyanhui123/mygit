/** 公用数据js* */
var commonData = {
		
	/**
	 * 是否有效标志数据定义，用于数据绑定 
	 */
	validFlagData : [ {
		label : "有效",
		value : "1"
	}, {
		label : "无效",
		value : "0"
	} ],	
	/**
	 * 是否有归档志数据定义，用于数据绑定 
	 */
	isArchiveFlagData : [ {
		label : "是",
		value : "1"
	}, {
		label : "否",
		value : "0"
	} ],	
	/**
	 * 是否有效标志对象，用于数据转换
	 */
	validFlagObj : {
		valid : {
			label : "有效",
			value : "1"
		},
		invalid : {
			label : "无效",
			value : "0"
		}
	},	
	
	/**
	 * 是否归档格式化
	 */
	isArchiveFlagFormatter : function(cellvalue, options, row) {
		for ( var item in commonData.isArchiveFlagData) {
			if (commonData.isArchiveFlagData[item].value == cellvalue) {
				return commonData.isArchiveFlagData[item].label;
			}
		}

		return "";
	},
	
	
	/**
	 * 是否有效格式化
	 */
	validFlagFormatter : function(cellvalue, options, row) {
		for ( var item in commonData.validFlagData) {
			if (commonData.validFlagData[item].value == cellvalue) {
				return commonData.validFlagData[item].label;
			}
		}

		return "";
	},
	/**
	 * 用户类型定义
	 */
	userTypeData : [ {
		value : "2",
		label : "系统负责人"
	}, {
		value : "3",
		label : "普通用户"
	} ],
	
	/**
	 * 用户类型转换
	 */
	userTypeFormatter : function(cellvalue, options, row) {
		for ( var item in commonData.userTypeData) {
			if (commonData.userTypeData[item].value == cellvalue) {
				return commonData.userTypeData[item].label;
			}
		}

		return "";
	},
	

	/**
	 * 机构层级数据定义，用于数据绑定 
	 */
	comLevelData : [ {
		label : "总公司",
		value : "1"
	}, {
		label : "分公司",
		value : "2"
	}, {
		label : "中支、分公司部门、总部部门",
		value : "3"
	},{
		label : "支公司、营销服务部、三级机构部门",
		value : "4"
	},{
		label : "支公司部门、业务部门",
		value : "5"
	},{
		label : "销售网点",
		value : "6"
	}],
	
	/**
	 * 机构层级转换
	 */
	comLevelFormatter : function(cellvalue, options, row) {
		for ( var item in commonData.comLevelData) {
			if (commonData.comLevelData[item].value == cellvalue) {
				return commonData.comLevelData[item].label;
			}
		}

		return "";
	},
	
	/**
	 * 机构类型数据定义，用于数据绑定 
	 */
	comTypeData : [ {
		label : "出单机构",
		value : "1"
	}, {
		label : "归属机构",
		value : "2"
	}, {
		label : "收付机构",
		value : "3"
	},{
		label : "分保机构",
		value : "4"
	} ],
	
	/**
	 * 机构类型转换
	 */
	comTypeFormatter : function(cellvalue, options, row) {
		for ( var item in commonData.comTypeData) {
			if (commonData.comTypeData[item].value == cellvalue) {
				return commonData.comTypeData[item].label;
			}
		}

		return "";
	},
	
	/**
	 * 管理级别数据定义，用于数据绑定 
	 */
	centerIndData : [ {
		label : "管理单位",
		value : "0"
	}, {
		label : "核算单位",
		value : "1"
	}, {
		label : "基层单位",
		value : "2"
	},{
		label : "部门",
		value : "3"
	} ],
	
	/**
	 * 管理级别转换
	 */
	centerIndFormatter : function(cellvalue, options, row) {
		for ( var item in commonData.centerIndData) {
			if (commonData.centerIndData[item].value == cellvalue) {
				return commonData.centerIndData[item].label;
			}
		}

		return "";
	},
	
	/**
	 * 机构属性数据定义，用于数据绑定 
	 */
	comAttributeData : [ {
		label : "机构",
		value : "O"
	}, {
		label : "管理部门",
		value : "M"
	}, {
		label : "销售网点（业务）",
		value : "B"
	}, {
		label : "财务部门",
		value : "F"
	} ],
	/**
	 * 机构归属渠道数据定义，用于数据绑定 
	 */
	channelCodeData : [ {
		label : "直管渠道",
		value : "01"
	}, {
		label : "车商渠道",
		value : "02"
	}, {
		label : "重客渠道",
		value : "03"
	},{
		label : "银保渠道",
		value : "04"
	},{
		label : "电商渠道",
		value : "05"
	},{
		label : "未分渠道",
		value : "99"
	}
	],
	
	/**
	 * 机构属性转换
	 */
	comAttributeFormatter : function(cellvalue, options, row) {
		for ( var item in commonData.comAttributeData) {
			if (commonData.comAttributeData[item].value == cellvalue) {
				return commonData.comAttributeData[item].label;
			}
		}

		return "";
	},
	
	/**
	 * 角色级别数据定义，用于数据绑定 
	 */
	roleLevelData : [ {
		label : "总公司",
		value : "1"
	}, {
		label : "省公司",
		value : "2"
	}, {
		label : "地市",
		value : "3"
	},{
		label : "最低级别",
		value : "9"
	} ],	
	
	/**
	 * 显示成功消息
	 */
	showSuccessMsg : function(msg) {
		alert("操作执行成功！");
	},
	
	selectCellValue: function(gridId, cellId, id) {
		return $("#"+gridId).jqGrid('getRowData', id)[cellId];
	},
	
	/**
	 * 通知状态定义
	 */
	noticeStatusData : [ {
		value : "0",
		label : "新建"
	}, {
		value : "1",
		label : "发布"
	}, {
		value : "2",
		label : "删除"
	}, {
		value : "3",
		label : "撤销"
	} ],
	
	/**
	 * 通知状态转换
	 */
	noticeStatusFormatter : function(cellvalue, options, row) {
		for ( var item in commonData.noticeStatusData) {
			if (commonData.noticeStatusData[item].value == cellvalue) {
				return commonData.noticeStatusData[item].label;
			}
		}

		return "";
	},
	
	/**
	 * 窗口公共高度
	 */
	dialog:{commonHeight:640},
	/**
	 * 日期格式化
	 * @param date 日期对象
	 * @param pattern 日期格式化字符串
	 * 月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符，
	 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
	 */
	formatDate : function(date, pattern) {
		if (!(date instanceof Date)) {
			return;
		}
		var o = {
			"M+" : date.getMonth() + 1, //月份   
			"d+" : date.getDate(), //日   
			"h+" : date.getHours(), //小时   
			"m+" : date.getMinutes(), //分   
			"s+" : date.getSeconds(), //秒   
			"q+" : Math.floor((date.getMonth() + 3) / 3), //季度   
			"S" : date.getMilliseconds()//毫秒   
		};
		if(/(y+)/.test(pattern)) {
			
			pattern=pattern.replace(RegExp.$1, (""+date.getFullYear()).substr(4 - RegExp.$1.length));
		}
		//如果占位符在一位以上，实际值不足则用0补位
		for ( var k in o) {

			if (new RegExp("(" + k + ")").test(pattern)) {

				pattern = pattern.replace(RegExp.$1,
						(RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k])
								.substr(("" + o[k]).length)));
			}
		}
		return pattern;
	},
	
	/**
	 * 机构运营状态数据定义，用于数据绑定 
	 */
	runStatusData : [ {
		label : "正常",
		value : "0"
	}, {
		label : "异常",
		value : "1"
	}, {
		label : "撤销",
		value : "2"
	}],
	
	/**
	 * 营运状态格式化
	 */
	runStatusFormatter : function(cellvalue, options, row) {
		for ( var item in commonData.runStatusData) {
			if (commonData.runStatusData[item].value == cellvalue) {
				return commonData.runStatusData[item].label;
			}
		}

		return "";
	},
	
	/**
	 * 出单内勤综合查询 分公司数据定义，用于数据绑定 
	 */
	branchCompany : [ {
		label : "总公司",
		value : "01"
	}, {
		label : "深圳分公司",
		value : "0120"
	}, {
		label : "广东分公司",
		value : "0130"
	}, {
		label : "湖北分公司",
		value : "0131"
	}, {
		label : "湖南分公司",
		value : "0132"
	}, {
		label : "广西分公司",
		value : "0135"
	}, {
		label : "海南分公司",
		value : "0140"
	}, {
		label : "甘肃分公司",
		value : "0141"
	}, {
		label : "吉林分公司",
		value : "0142"
	}, {
		label : "江西分公司",
		value : "0143"
	}, {
		label : "新疆分公司",
		value : "0145"
	}, {
		label : "四川分公司",
		value : "0150"
	}, {
		label : "重庆分公司",
		value : "0151"
	}, {
		label : "云南分公司",
		value : "0152"
	}, {
		label : "贵州分公司",
		value : "0153"
	}, {
		label : "上海分公司",
		value : "0160"
	}, {
		label : "江苏分公司",
		value : "0161"
	}, {
		label : "宁波分公司",
		value : "0163"
	}, {
		label : "浙江分公司",
		value : "0164"
	}, {
		label : "福建分公司",
		value : "0165"
	}, {
		label : "安徽分公司",
		value : "0166"
	}, {
		label : "山东分公司",
		value : "0167"
	}, {
		label : "青岛分公司",
		value : "0168"
	}, {
		label : "厦门分公司",
		value : "0169"
	}, {
		label : "陕西分公司",
		value : "0170"
	}, {
		label : "山西分公司",
		value : "0171"
	}, {
		label : "北京分公司",
		value : "0180"
	}, {
		label : "天津分公司",
		value : "0181"
	}, {
		label : "河北分公司",
		value : "0182"
	}, {
		label : "河南分公司",
		value : "0183"
	}, {
		label : "辽宁分公司",
		value : "0190"
	}, {
		label : "大连分公司",
		value : "0191"
	}, {
		label : "黑龙江分公司",
		value : "0193"
	}],
	
	/**
	 *  分公司数据定义，用于数据绑定 
	 */
	onlyBranchCompany : [ {
		label : "深圳分公司",
		value : "0120"
	}, {
		label : "广东分公司",
		value : "0130"
	}, {
		label : "湖北分公司",
		value : "0131"
	}, {
		label : "湖南分公司",
		value : "0132"
	}, {
		label : "广西分公司",
		value : "0135"
	}, {
		label : "海南分公司",
		value : "0140"
	}, {
		label : "甘肃分公司",
		value : "0141"
	}, {
		label : "吉林分公司",
		value : "0142"
	}, {
		label : "江西分公司",
		value : "0143"
	}, {
		label : "新疆分公司",
		value : "0145"
	}, {
		label : "四川分公司",
		value : "0150"
	}, {
		label : "重庆分公司",
		value : "0151"
	}, {
		label : "云南分公司",
		value : "0152"
	}, {
		label : "贵州分公司",
		value : "0153"
	}, {
		label : "上海分公司",
		value : "0160"
	}, {
		label : "江苏分公司",
		value : "0161"
	}, {
		label : "宁波分公司",
		value : "0163"
	}, {
		label : "浙江分公司",
		value : "0164"
	}, {
		label : "福建分公司",
		value : "0165"
	}, {
		label : "安徽分公司",
		value : "0166"
	}, {
		label : "山东分公司",
		value : "0167"
	}, {
		label : "青岛分公司",
		value : "0168"
	}, {
		label : "厦门分公司",
		value : "0169"
	}, {
		label : "陕西分公司",
		value : "0170"
	}, {
		label : "山西分公司",
		value : "0171"
	}, {
		label : "北京分公司",
		value : "0180"
	}, {
		label : "天津分公司",
		value : "0181"
	}, {
		label : "河北分公司",
		value : "0182"
	}, {
		label : "河南分公司",
		value : "0183"
	}, {
		label : "辽宁分公司",
		value : "0190"
	}, {
		label : "大连分公司",
		value : "0191"
	}, {
		label : "黑龙江分公司",
		value : "0193"
	}],
	
/**
 * 柜面人员标识
 */	
	counterType : [
	   {
		   label : "柜面专员",
		   value : "1"
	   },{
		   label : "综合柜员",
		   value : "2"
	   },{
		   label : "兼职柜员",
		   value : "3"
	   }],
	   
	  counterTypeFormatter : function (cellvalue, options, row){
		for(var item in commonData.counterType){
			if(commonData.counterType[item].value == cellvalue){
				return commonData.counterType[item].label;
			}
		}
		return "";
	  },
	/**
	 * 外部用户标识
	 */
	outerUser : [{
			label : "非外部用户",
			value : "0"
		},{
			label : "外部用户",
			value : "1"
		}],
		/**
		 * 账户类别（真实/虚拟）
		 */
		userCategory : [{
				label : "虚拟用户",
				value : "0"
			},{
				label : "真实用户",
				value : "1"
			}]
};

