 var accountModify = {
		 
		initPage: function(){
		 
		 $("#accountModifyForm").validate();
		 
	     /**
		 *提价按钮添加事件
		 */
		$("#submitModifyBut").click(function(){
			var id=$("#idVcInsuCardSalesRecord").val();
			if(id==''){
				alert("记录主键不存在！");
				return;
			}
			//提交
			if($("#accountModifyForm").validate().form()){
				var url = $.taHash.get("ctx")+"/insucard/submitAccountModify.do?idVcInsuCardSalesRecord="+id;
				common.formSubmit("accountModifyForm", url);						
			}else{
				return false;
			}
		});
		
		
		$("#cancelModifyBut").click(function(){
			window.history.go(-1);
		});
     }
 }
