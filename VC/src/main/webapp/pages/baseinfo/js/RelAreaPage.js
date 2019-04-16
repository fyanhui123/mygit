/**
* @fileOverview  险种关联 js文件
* @author whj
* @date 2013-06-04* 
*/
var d = new dTreeNew('d');
		var orgId;//选中的机构ID
		var orgCode;//选中的机构代码
		var orgName;//选中的机构名称

var setting = {
		check: {
		   enable: true,
		   chkboxType: {"Y":"", "N":""}
	       },
 		data: {
				key: {
					title:"info",
					name:"info"					
				    },
				simpleData: {
						enable: true,
						idKey: "id",
						pIdKey: "pid", 	                       
						rootPId: -1
				    }
			},
			callback: {				
				onClick: onClick_S
			}
       };

	function onClick_S(event, treeId, treeNode, clickFlag) {		
	  };		

var relAreaPage = {
	
	loadRelAreaZTree : function(idVcDocVersionInfo) {		
		//加载未关联的险种
		$.ajax({
			datatype: 'json',
			async:false,
			url: $.taHash.get("ctx") + "/baseinfoJson/queryRelAreaZTree.do?ajax=true&idVcDocVersionInfo="+idVcDocVersionInfo,
			type: "post",
			success: function(data){
			   $.fn.zTree.init($("#relAreaZTree"), setting, data.relAreaZTree);
			}
		});
		
	},
	
	initRelAreaPage: function() {
		/**
		 * @description 树形展开
		 */
		$("#expandAllBtn").click(function(){
			var zTree = $.fn.zTree.getZTreeObj("relAreaZTree");
			zTree.expandAll(true);
		});
		
		/**
		 * @description 树形折叠
		 */		
		$("#collapseAllBtn").click(function(){
			var zTree = $.fn.zTree.getZTreeObj("relAreaZTree");
			zTree.expandAll(false);
		});
		
		/**
		 * @description 提交险种关联
		 */
		$('#btnSubmitRelArea').click(function(){
			$('#btnSubmitRelArea').attr("disabled",true);
			var idVcDocVersionInfo=$('#docVersioninfoId_toRelArea').val();
			//获取勾选的节点
			var zTree = $.fn.zTree.getZTreeObj("relAreaZTree");
			nodes = zTree.getCheckedNodes(true);
			var strOrgCode="";			
			for(var i=0; i<nodes.length;i++) {
				strOrgCode += ","+ nodes[i].value ;
			}
			strOrgCode=strOrgCode.substr(1);			
			var param="selectVcDocVersionInfoIds="+ idVcDocVersionInfo +"&selectRelAreaCodes="+strOrgCode;
			url=$.taHash.get("ctx")+"/baseinfoJson/saveRelArea.do?ajax=true";
			common.ajaxSubmitDfCallBack(relAreaPage.callback, 
					url , param);
		});
		
	},
	
	callback:function(data){		
		  alert("关联地区成功！");
		  $('#btnSubmitRelArea').removeAttr("disabled");
		  $("#relAreaPage").dialog('close');		 
	}
		
}