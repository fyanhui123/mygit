var docNumAccount = {

		
	initPage : function() {	
		/**
		* 文件导入按钮事件
		*/
		$("#importFile").click( function() {
			url=$.taHash.get("ctx")+"/account/insuranceImportFile.do";
			$("#accountForm").attr("action",url);
			$("#accountForm").submit();
		});
		
		/**
		* 人工对账按钮事件
		*/
		$("#accountFile").click( function() {
			url=$.taHash.get("ctx")+"/account/insuranceAccountFile.do";
			alert("url :"+url);
			$("#accountForm").attr("action",url);
			$("#accountForm").submit();
		});
	}
	
		
}
