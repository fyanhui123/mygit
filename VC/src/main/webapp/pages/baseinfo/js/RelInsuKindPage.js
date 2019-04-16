/**
* @fileOverview  险种关联 js文件
* @author whj
* @date 2013-06-04* 
*/
var relInsuKindPage = {
	//leftKey记录左边框所选险类的所有险种
	leftKey:new Object(),
	loadInsuKindUrl: '/baseinfoJson/queryAllNoRelInsuKind.do?ajax=true',
	
	loadRelInsuKindList: function(idVcDocVersionInfo) {	  
		 //险类
		$("#insuTypeCondition").dropDownList({
			dataField:'value',
			dataType:'json',
		    url: $.taHash.get("ctx")+"/baseinfoJson/getJSONInsuTypeList.do?ajax=true&random="+ Math.random()
		});
		//加载未关联的险种
		$.ajax({
			datatype: 'json',
			async:false,  //必须
			//url: $.taHash.get("ctx") + "/baseinfoJson/queryAllNoRelInsuKind.do?ajax=true&idVcDocVersionInfo="+idVcDocVersionInfo,
			url: $.taHash.get("ctx") + "/baseinfoJson/queryRelInsuKindByInsuType.do?ajax=true&insuTypeCode=",
			type: "post",
			success: function(data){
			   $('#noRelInsuKind option').remove();	//此补必须，不然会有重复
			   relInsuKindPage.leftKey=new Object();
			   var allInsuKinds=data.oneTypeInsuKindList;	
				for(var index in allInsuKinds){	
					//leftKey初始记录所有险种
					relInsuKindPage.leftKey[allInsuKinds[index].insuKindCode] = allInsuKinds[index].insuKindCode;
					$('#noRelInsuKind').append("<option style='height:20px;' value='"+allInsuKinds[index].insuKindCode+"'>"+allInsuKinds[index].insuKindName+"</option>");
				}
			}
		});
		//加载已关联的险种
		$.ajax({
			datatype: 'json',
			async:false, //必须
			url: $.taHash.get("ctx")+"/baseinfoJson/queryHaveRelInsuKind.do?ajax=true&idVcDocVersionInfo="+idVcDocVersionInfo,
			type: "post",
			success: function(data){			  
				var relInsuKinds=data.haveRelInsuKindList;					
				for(var item in relInsuKinds){
					$('#noRelInsuKind option[value="'+relInsuKinds[item].insuKindCode+'"]').appendTo('#haveRelInsuKind');
				}				
			}
		});
		
	},

	
	initRelInsuKindPage: function() {
		$("#relInsuKindTabs" ).tabs();
		$("#insuTypeCondition").change( function() {
			insuTypeCode=$(this).val();
			//alert(insuTypeCode);
			//加载未关联的险种
			$.ajax({
				datatype: 'json',
				async:false,
				url: $.taHash.get("ctx") + "/baseinfoJson/queryRelInsuKindByInsuType.do?ajax=true&insuTypeCode="+insuTypeCode,
				type: "post",
				success: function(data){
				   $('#noRelInsuKind option').remove();
					var insuKinds=data.oneTypeInsuKindList;	
					var obj=new Object();  //用于判断在左框且不在右框的元素
					$('#haveRelInsuKind option').each(function(){
						obj[$(this).val()] = $(this).val();
					});
					//leftKey记录左边框所选险类的所有险种
					relInsuKindPage.leftKey=new Object();					
					for(var index in insuKinds){
						relInsuKindPage.leftKey[insuKinds[index].insuKindCode] = insuKinds[index].insuKindCode;
                        if(!obj[insuKinds[index].insuKindCode]){//右边框不存在的元素
						  $('#noRelInsuKind').append("<option style='height:20px;' value='"+insuKinds[index].insuKindCode+"'>"+insuKinds[index].insuKindName+"</option>");
                        }
					}
				}
			});
		});
		
		
		/**
		* @description 添加关联险种
		*/
		$('#leftToRight').click(function(){
			$('#noRelInsuKind option:selected').appendTo('#haveRelInsuKind');
		});
		
		/**
		* @description 删除当前关联的险种
		*/
		$('#rightToLeft').click(function(){
			$('#haveRelInsuKind option:selected').each(function(){
				if(!relInsuKindPage.leftKey[$(this).val()]){//左边框所选险类的所有险种不存在的元素
					$(this).remove();
				}else{
					$(this).appendTo('#noRelInsuKind');
				}
			});
		});
		
		/**
		 * @description 提交险种关联
		 */
		$('#btnSubmitRelInsuKind').click(function(){
			$('#btnSubmitRelInsuKind').attr("disabled",true);
			var idVcDocVersionInfo=$('#docVersioninfoId_toRel').val();
			var insuKindCodes="";
			$('#haveRelInsuKind option').each(function(){
				insuKindCodes+=","+$(this).val();
			});
			
			insuKindCodes=insuKindCodes.substring(1);
			
			var param="selectVcDocVersionInfoIds="+ idVcDocVersionInfo +"&selectRelInsuKindCodes="+insuKindCodes;
			url=$.taHash.get("ctx")+"/baseinfoJson/saveRelInsuKind.do?ajax=true";		
			common.ajaxSubmitDfCallBack(relInsuKindPage.callback, 
										url , param);			
			
		});
		
	},
	
	callback:function(data){		
		  alert("关联险种成功！");
		  $('#btnSubmitRelInsuKind').removeAttr("disabled");
		  $("#relInsuKindPage").dialog('close');		 
	}
		
}