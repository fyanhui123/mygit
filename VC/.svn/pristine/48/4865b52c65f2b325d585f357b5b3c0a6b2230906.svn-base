//多选 
var setting_M = {
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
			onCheck: onCheck
		}
	};
 //单选
 var setting_S = {
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
			//beforeClick: beforeClick_S,
			onClick: onClick_S
		}
	};
	//多选事件
	function onCheck(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		nodes = zTree.getCheckedNodes(true);
		var strOrgCode="";
		var strOrgName="";				
		for(var i=0; i<nodes.length;i++) {
			strOrgCode += ","+ nodes[i].value ;
			strOrgName += ","+ nodes[i].info ;
		}
		strOrgCode=strOrgCode.substr(1);
		strOrgName=strOrgName.substr(1);
		
		var returnOrgId=$("#ztree_tagCodeId").val();
		var returnOrgName=$("#ztree_tagNameId").val();		
		$("#"+returnOrgId).val(strOrgCode);
		$("#"+returnOrgName).val(strOrgName);
	};
	
	//单选事件
	function onClick_S(event, treeId, treeNode, clickFlag) {
		var returnOrgId=$("#ztree_tagCodeId").val();
		var returnOrgName=$("#ztree_tagNameId").val();		
		$("#"+returnOrgId).val(treeNode.value);
		$("#"+returnOrgName).val(treeNode.info);
	};
			
			
	var companyZTree = {
			
		initZTree: function(){
				var tree_minLevel=$("#ztree_minLevel").val();
				var tree_singleSelect=$("#ztree_singleSelect").val();
				var tree_root=$("#ztree_root").val();
				var ztree_onlyOrgFlag=$("#ztree_onlyOrgFlag").val();
				//modify by zhxiao3 20141113:VC-135单证库存查询优化 begin
				var ztree_pageTableName=$("#ztree_pageTableName").val();				
				var url=$.taHash.get("ctx")+"/commonJson/initQueryOrgZTree.do?ajax=true&minLevel="+tree_minLevel+"&root="+tree_root+"&onlyOrgFlag="+ztree_onlyOrgFlag +"&pageTableName="+ztree_pageTableName +"&random="+Math.random();
				//modify by zhxiao3 20141113:VC-135单证库存查询优化 end
				 $.ajax({
					datatype: 'json',
					url: url,
					type: "post",
					data: {},
					success: function(data){
						if(tree_singleSelect){
							$.fn.zTree.init($("#treeDemo"), setting_S, data.zTreeList);
						}else{
							$.fn.zTree.init($("#treeDemo"), setting_M, data.zTreeList);
						}
					}	
				});	
		    }
	};
			