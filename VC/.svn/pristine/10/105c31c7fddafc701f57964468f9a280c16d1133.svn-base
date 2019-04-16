var companyTree = {
		initPage: function(){
			var url = $.taHash.get("ctx")+"/vclevelJson/queryOrgTreeList.do?ajax=true&random="+Math.random();
			var name = "companys";
			var showid = "companyTree";
			$('#companyTree').css('backgroundColor','#84C1FF');
			
			//$(obj).css('backgroundColor','#84C1FF');
			makeCheckBoxDtree(url, name, showid,null);
			$("#companyButtom").click( function() {
				var ids = "";
				$("[name='companys']:checked").each(function (){
					ids+=$(this).val()+","
				});
				$("#companyCode").val(ids.substring(0, ids.length-1));
				$('#companyPage').dialog('close');
			});
	}
};
