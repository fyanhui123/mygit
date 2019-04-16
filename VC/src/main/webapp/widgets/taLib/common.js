/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.0 $
 *   
 */
var common = {
	// 收起展开按钮
    ctx: '',    
	hideTR : function (trid, imgId){
		if(document.getElementById(trid).style.display==""){
			document.getElementById(trid).style.display="none";
			//var imgSrc = $.taHash.get("ctx") + "/style/default/images/open.png";
			//$("#"+imgId).attr("src", imgSrc); 
		}else{
			document.getElementById(trid).style.display="";
			//var imgSrc = $.taHash.get("ctx") + "/style/default/images/close.png";
			//$("#"+imgId).attr("src", imgSrc);
		}
	},
	//判断是否为ie6
	isIE6:function(){
		var browser_ver = $.browser.version;
		var accurate_value = browser_ver.substr(0,1);
		return $.browser.msie && accurate_value == '6';
	},
	// 自适应窗口大小
	reFreshWindow : function () {
		common.scroll();
		//common.topDiv();
	},
	// 用于消息栏隐藏显示按钮的图片
	messageButtonState : 'down',
	// 消息栏隐藏显示
	divMessageUpAndDown : function (){
		//隐藏
		if(common.messageButtonState == 'up'){
			common.messageButtonState = 'down';
			$("#divMessage").hide();
			common.scroll();
		}
		//显示
		else{
			common.messageButtonState = 'up';
			$("#divMessage").show();
			common.scroll();
		}
	},
	// 消息栏随窗口移动
	scroll : function (){
	    document.getElementById("messageDialog").style.top=(document.documentElement.scrollTop+document.documentElement.clientHeight-document.getElementById("messageDialog").offsetHeight)+"px";
	    document.getElementById("messageDialog").style.left=(document.documentElement.scrollLeft)+"px";			
	    
	    //右侧标签隐藏状态
		  if(common.rightDrawerCompandState == -1)
		  {
			  $("#messageDialog").css("width",document.documentElement.clientWidth-common.rightWidth-8);		
		  }
		  //右侧标签显示状态
		  else 
		  {
			  $("#messageDialog").css("width",document.documentElement.clientWidth-common.rightWidth-18);	
		}
	    
	    
	},
	
	//t : setTimeout(common.time(),1000),
	//var t = setTimeout(common.time,1000)
    
    //顶部浮动栏
    topDiv : function() {
    	//alert();
    	document.getElementById("pagetab").style.top=(document.documentElement.scrollTop)+"px";
    	document.getElementById("pagetab").style.left=(document.documentElement.scrollLeft)+"px";
    	
    	//右侧标签隐藏状态
		if(common.rightDrawerCompandState == -1){
			$("#pagetab").css("width",document.documentElement.clientWidth-common.rightWidth-8);		
		}
		//右侧标签显示状态
		else {
			$("#pagetab").css("width",document.documentElement.clientWidth-common.rightWidth-15);	
		}
	},
	
	/**
	*  公共表单ajax查询数据
	*/
	ajaxGetData: function(divId, action, param){
		var jspId = $("#jspId").val();
		var buttonId = $("#buttonId").val();
		var titleName = $("#titleName").val();
		action += "&jspId=" + jspId + "&buttonId=" + buttonId + "&titleName=" + titleName;
		$.ajax({
			datatype: 'html',
			url: action + '&random=' + Math.random(),
			type: "post",
			data: param,
			success: function(data){
				$("#"+divId).html(data);
				

			}	
		})
	},
	
	/**
	*  公共表单ajax提交方法
	*/
	ajaxSubmit: function(divId, action, param){
		var jspId = $("#jspId").val();
		var buttonId = $("#buttonId").val();
		var titleName = $("#titleName").val();
		action += "&jspId=" + jspId + "&buttonId=" + buttonId + "&titleName=" + titleName;
		$.ajax({
			datatype: 'html',
			url: action + '&random=' + Math.random(),
			type: "post",
			data: param,
			success: function(data){
				$("#"+divId).html(data);

			}	
		})
	},
	
	/**
	*  公共表单ajax提交方法
	*/
	ajaxSubmitAndCloseDialog: function(dialogId, action, param){
		var jspId = $("#jspId").val();
		var buttonId = $("#buttonId").val();
		var titleName = $("#titleName").val();
		action += "&jspId=" + jspId + "&buttonId=" + buttonId + "&titleName=" + titleName;
		$.ajax({
			datatype: 'html',
			url: action + '&random=' + Math.random(),
			type: "post",
			data: param,
			success: function(data){
				alert("success");
				$("#"+dialogId).dialog('close');
			}	
		})
	},
	
	/**
	*  公共表单ajax提交方法,functionID为自定义的方法，作为回调
	*/
	ajaxSubmitDfCallBack: function(functionId, action, param){
		$.ajax({
			datatype: 'html',
			url: action + '&random=' + Math.random(),
			type: "post",
			data: param,
			success: function(data){
				functionId(data);
			}	
		})
	},
	
	
	/**
	*  公共表单提交方法
	*/
	formSubmit : function(formId,url) {
		var jspId = $("#jspId").val();
		var buttonId = $("#buttonId").val();
		var titleName = $("#titleName").val();
		url += "&jspId=" + jspId + "&buttonId=" + buttonId + "&titleName=" + titleName;
		$("#" + formId).attr("action",url);
		$("#" + formId).submit();

	},
	/**
	*  禁用表单内所有元素
	*/
	disabledForm : function(){
		$("form input").attr("readonly", true);
		$("form input").attr("disabled", true); //选择所有的form元素中的input元素 ,包括input, textarea, select 和 button
		$("form :enabled").attr("disabled", true);  //选择form元素中所有的可操作的表单元素 
	},
	/**
	*  禁用表单内所有元素
	*/
	disabledForm : function(){
		$("form input").attr("readonly", true);
		$("form input").attr("disabled", true); //选择所有的form元素中的input元素 ,包括input, textarea, select 和 button
		$("form :enabled").attr("disabled", true);  //选择form元素中所有的可操作的表单元素 
	},
	/**
	*  禁用指定元素
	*  @param  fieldId 禁用元素ID
	*/
	disabledField : function(fieldId){
		$("#" + fieldId).attr("readonly", true);
		$("#" + fieldId).attr("disabled", true); 
	},
	
	/**
	*  启用表单内所有元素
	*/
	enableForm : function(){
		$("form input").attr("readonly", false);
		$("form input").attr("disabled", false); //选择所有的form元素中的input元素 ,包括input, textarea, select 和 button
		$("form :enabled").attr("disabled", false);  //选择form元素中所有的可操作的表单元素 
	},
	
	/**
	*  启用指定元素
	*  @param  fieldId 禁用元素ID
	*/
	enableField : function(fieldId){
		$("#" + fieldId).attr("readonly", true);
		$("#" + fieldId).attr("disabled", true); 
	},
	
	/**
	*  公共页面跳转方法
	*/
	localHref : function(url) {
		//var jspId = $("#jspId").val();
		//var buttonId = $("#buttonId").val();
		//var titleName = $("#titleName").val();
		//url += "&jspId=" + jspId + "&buttonId=" + buttonId + "&titleName=" + titleName;
		window.location = url;
	},
	
	/**
	*  获取组装URL方法
	*/
	getLocalHref : function(url) {
		//var jspId = $("#jspId").val();
		//var buttonId = $("#buttonId").val();
		//var titleName = $("#titleName").val();
		//url += "&jspId=" + jspId + "&buttonId=" + buttonId + "&titleName=" + titleName;
		return url;
	},
	
	/**
	*  jqGrid获取组选中行的值
	*/
	selectRowValue : function(gridID){
		 var selectValue = $("#"+gridID).jqGrid('getGridParam','selarrrow');
		 return selectValue;
	},
	
	/**
	*  jqGrid获取组选中行，指定列的值
	*/
	selectRowShowCollValue : function(gridID, colName, id){
		var selectValue = $("#"+gridID).jqGrid('getGridParam','selarrrow');
		var alertString = '';
		if (selectValue) { 
			//var list = ids.split(",");
			$.each(selectValue,function(i,id){
				var ret = $("#"+gridID).jqGrid('getRowData',id); 
				$.each(ret,function(index, value){
					//alertString += index + "=" + value + '\n';
					
					if(index == colName){
						alertString += value +",";;
					}
					
				});
			});
		}
		var selectColName = alertString.substring(0,alertString.length-1);
		return selectColName;
	},
	
	/**
	*  锁屏组件
	*/
	startBlock : function() {
		//启动锁屏
		$.taBlockUI.active();
		//2秒钟后锁屏解除
		setTimeout(function(){$.taBlockUI.inactive();}, 2000);

	},
	
	/**
	*  锁屏组件-自定义提示信息
	*/
	startBlockWithMsg : function(message) {
		$.taBlockUI.message = message;
		//启动锁屏
		$.taBlockUI.active();
		//2秒钟后锁屏解除
		setTimeout(function(){$.taBlockUI.inactive();}, 2000);

	},
	
	initHtml : function (jspId) {		
		//初始化JspId
		$("#jspId").val(jspId);
		//初始化ButtonId
		$(".taButton").click(function() {
			$("#buttonId").val($(this).attr("id"))
		});	
		//初始化错误显示类型
		//$("#showType").val(errorType);
	},
	
	
	
	initMessage : function (messageJson) {
		var showErrorType = $("#showType").val();
		messageJson=eval(messageJson);
		//alert(JSON.stringify(messageJson));
		/*$.each(messageJson, function(idx, item) {
			errorMsg += (idx+1) + ": " + item.message + "<br>";
		});*/
		var msgType = "";
		var message = "";
		if(undefined != messageJson && '' != messageJson){
			msgType = messageJson['type'];
			message = messageJson['message'];
			if(showErrorType == "1"){
				common.showButtonMessage(msgType, message);
			}else if(showErrorType == "2"){
				common.showAlertMessage(msgType, message);
			}
		}
	},
	
	showButtonMessage : function (msgType, messageInfo) {
		
		if(messageInfo != ""){
			//显示消息框
			common.messageButtonState = 'up';
			$("#divMessage").show();
		 	$("#errorShowArea").html("错误信息:<br>"+ messageInfo );
		}
	},
	
	showAlertMessage : function (msgType, messageInfo) {
		if(messageInfo != ""){
			if(msgType == "success"){
				$(".ui-dialog-title").removeClass("ui-dialog-title-red");
				$("#promptMsgSpan").html(messageInfo);
				$("#promptMsgArea").dialog('open');
			}else{
				$(".ui-dialog-title").addClass("ui-dialog-title-red");
				$("#errorMsgSpan").html(messageInfo);
				$("#errorMsgArea").dialog('open');
			}
			
		}
	},
	
	/**
	 * 清空查询结果
	 * @param {} gridId jqGrid控件id 
	 */
	clearGridData:function(gridId) {
		$("#"+gridId).clearGridData(); 
	},
	/**
	 * 判断数组中是否有重复元素
	 * @param {} title 警示标题
	 * @param {} ary 数组
	 * @return {Boolean}
	 */
	isRepeat : function(title,ary) {
		var nary = ary.sort();
		if(nary.length>1){
			for(var i=0;i<nary.length-1;i++) {
				if (nary[i]==nary[i+1]) {
					alert(title+"[" + nary[i]+"]");
					return true;
				}
			}
		}
		return false;
	},
	
	isAvaNum:function(start,end,objId) {
		
		if(isNaN(Number(start))){
			if(objId == "startNum" ){
				return "请输入正确的流水号";
			}
		}
		if(isNaN(Number(end))){
			if(objId == "endNum" ){
				return "请输入正确的流水号";
			}
		}
		return Number(end)-Number(start)+1;
	},
	
	/**
	 * 左边填充字符串到指定长度
	 * @param v_source 源字符串
	 * @param v_length  待填充到的长度
	 * @param v_char 待填充字符
	 * @return  补充字符后的字符串
	 */
	 lpad:function(v_source, v_length, v_char) {
	    var len = v_source.length;
	    if(len==0){
	    	return v_source;
	    }
	    while(len < v_length) {
	    	v_source = v_char + v_source;
	        len++;
	    }
	    return v_source;
	},
	
	//流水号长度自动补0，并计算单证数量
    autoLengthAndTotalNum:function(rIndex,totalAmount,inputName,objId,vlength){
		var start = $("#" + "startNum__" + rIndex).val();
		var end = $("#" + "endNum__" + rIndex).val();
		//起始流水自动补长
		if(objId == "startNum"){
			var lpadStrart= common.lpad(start,vlength,'0');
			$("#" + "startNum__" + rIndex).val(lpadStrart);
		}else{
			var lpadEnd= common.lpad(end,vlength,'0');
			$("#" + "endNum__" + rIndex).val(lpadEnd);
		}
		var result = common.isAvaNum(start,end,objId);
		if(isNaN(Number(result))){
			alert(result);
			$("#" + totalAmount+"__" + rIndex).val("");
			return;
		}
		if(end!=""){
			var total = result;
			if(total<=0){
				alert("起始流水号大于终止流水号，请修改");
				$("#" + totalAmount+"__" + rIndex).val("");
				$("#" + objId+"__" + rIndex).val(null);
				return;
			}
		}
		$("#"+inputName).mulLineWidget.resetVal(rIndex, totalAmount, total);
	},

	totalNum:function(rIndex,totalAmount,inputName,objId){
		var start = $("#" + "startNum__" + rIndex).val();
		var end = $("#" + "endNum__" + rIndex).val();
		var result = common.isAvaNum(start,end,objId);
		if(isNaN(Number(result))){
			alert(result);
			$("#" + totalAmount+"__" + rIndex).val("");
			return;
		}
		if(end!=""){
			var total = result;
			if(total<=0){
				alert("起始流水号大于终止流水号，请修改");
				$("#" + totalAmount+"__" + rIndex).val("");
				$("#" + objId+"__" + rIndex).val(null);
				return;
			}
		}
		$("#"+inputName).mulLineWidget.resetVal(rIndex, totalAmount, total);
	},
	
	/**
	 * 校验流水号区间是否有重叠
	 * @jsonArr 数组
	 * @docVerCode 单证类型属性名
	 * @pressBatchNo 印刷批次属性名
	 * @startNum 起始流水属性名
	 * @endNum 终止属性名
	 * @return false-无重叠 true-有重叠
	 */
	checkOverLap:function(jsonArr,docVerCode,pressBatchNo,startNum,endNum){
		//双重循环判断同意单证是否流水号区间有重叠
		for ( var ii = 0; ii < jsonArr.length-1; ii++) {
			for(var jj=ii+1;jj<jsonArr.length;jj++){
				if(jsonArr[ii][docVerCode]==jsonArr[jj][docVerCode] && jsonArr[ii][pressBatchNo]==jsonArr[jj][pressBatchNo]){
					var star_ii=parseFloat(jsonArr[ii][startNum]);
					var end_ii=parseFloat(jsonArr[ii][endNum]);
					var star_jj=parseFloat(jsonArr[jj][startNum]);
					var end_jj=parseFloat(jsonArr[jj][endNum]);
					if((star_ii>=star_jj && star_ii<=end_jj)||(star_jj>=star_ii && star_jj<=end_ii)){
						var str="单证【"+jsonArr[ii][docVerCode] +"】流水号区间【"+ jsonArr[ii][startNum]+"~"+jsonArr[ii][endNum] +"】";
						str=str+"与区间【"+ jsonArr[jj][startNum]+"~"+jsonArr[jj][endNum] +"】有交叉重叠";
						alert(str);
						return true;
					}
				}	
			}
		}
		return false;
	},
	
	/**
	 * 格式化数字，不够位数加前导0
	 */
	formatNumber:function(str, len){
		if(str.length>=len){
			return str;
		}else{
			return common.formatNumber("0"+str, len);
		}
	}
	
};

$(document).ajaxError(function(event, xhr, settings, exception){  
    common.initMessage(xhr.responseText);
});