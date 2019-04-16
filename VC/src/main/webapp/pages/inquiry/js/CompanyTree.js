
 
var companyTree = {
		
	initPage: function(){	
		companyTree.reloadCompanyDefTree();
	},
	
	reloadCompanyDefTree:function(){
		
		//url :ajax 请求url ;name 页面一组 checkbox共同的名字(html元素);showid 指定页面显示dtree的html元素id
		//function makeCheckBoxDtrer(url, name, showid,jsEvt) 
		//var singleSelect=$("#singleSelect").val();	
		
		if($("#singleSelect").val()=='false'){
			/*************多选**************/
			var url=$.taHash.get("ctx")+"/vclevelJson/queryOrgTreeList.do?ajax=true&random="+Math.random();		
			makeCheckBoxDtree(url,"orgSelectCheckBox","companyTree","");	
			
			$("#orgSelectConfirmBut").click(function() {
			
				var checkValues=$("input[type='checkbox'][name='orgSelectCheckBox']");
				var strOrgCode='';
				var strOrgName='';
				for(var i=0;i<checkValues.length;i++){
					if(checkValues[i].checked){
						strOrgCode += ","+checkValues[i].value;
						strOrgName += ","+$(checkValues[i]).next().text();
					}
			    }
				strOrgCode=strOrgCode.substr(1);
				strOrgName=strOrgName.substr(1);
				alert(strOrgCode);
				var returnOrgId=$("#tagCodeId").val();
				var returnOrgName=$("#tagNameId").val();		
				$("#"+returnOrgId).val(strOrgCode);
				$("#"+returnOrgName).val(strOrgName);
				
			});
		}else{
 
		}
	}
};

var changeBackgroundColor = function(obj) {
		$('#companyTree').find('a').css('backgroundColor','');
		$(obj).css('backgroundColor','#84C1FF');
		var returnOrgId=$("#tagCodeId").val();
		var returnOrgName=$("#tagNameId").val();		
		$("#"+returnOrgId).val(obj.code);
		$("#"+returnOrgName).val(obj.name);
		/*$("#inStoreInquiryDtoApplyOrgCode").val(obj.code);
		$("#inStoreInquiryDtoApplyOrgCodeName").val(obj.name);*/
		
};
