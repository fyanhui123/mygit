/**
* @fileOverview  demo js文件
* @author 廉超
* @date 2013-02-25
* @version 1.0
*/
var jsTree = {
		
		initPage: function(){
      		
	  		//重定向 树形控件样式地
			$.jstree._themes = $.taHash.get("ctx") + "/style/default/themes/";
			
			/**
			* @description html初始化树组件
			* @param  isCheckBox 是否复选框 
			* @return 
			*/
			$("#treeDiv1").taTree({
				isCheckBox: 'Y'
			});
			
			/**
			* @description 动态初始化树组件
			* @param  treeDiv 显示树的div Id （必填）
			* @param  elementId 显示树的div Id （必填）
			* @param  targetId 树URL显示的目标Id（必填）
			* @param  isCheckBox 是否复选框 
			* @param  source 动态加载树的url（必填）
			* @return 
			*/
			$("#treeDiv").taTree({
				elementId : 'treeDiv',
				targetId : 'mainDiv',
				isCheckBox: 'N',
				source : $.taHash.get("ctx") +'/queryBranchList.do?ajax=true'
			});
      		
			
			$("#getBut").click( function() {
				var nodes=$("#treeDiv").jstree("get_checked");  //使用get_checked方法
				$.each(nodes, function(idx, item) {   
					alert($("#treeDiv").jstree("get_text",$(item))+">>>>>"+$(item).attr("id"));
				});
			});
			
			
			
			$("#treeGrid").jqGrid({ 
	        	treeGrid: true, 
	            treeGridModel: 'adjacency', 
	            ExpandColumn: 'name', 
	            ExpandColClick: true, 
	            url: $.taHash.get("ctx")+"/pages/subfunc/js/menu.json",
	            datatype : 'json', 
	            colNames: ["id","功能菜单",'url'], 
	            colModel: [{ 
	            	name : 'id', 
	                index : 'id',
	                resizable: false,
	                sortable : false,
	                hidden:true
	            },{ 
	            	name : 'name', 
	                index : 'name',
	                resizable: false,
	                sortable : false
	            },{ 
	            	name : 'url', 
	                index : 'url',
	                resizable: false,
	                sortable : false,
	                hidden:true
	            }],
	            ExpandColClick: true,
	        	treeIcons: {leaf:'ui-icon-document-b'},
	            pager: "false", 
	            height: 'auto', 
	            width: '180', 
	            viewrecords: true, 
	            jsonReader: { 
	                repeatitems: false,
	                id : 'id' 
	            },
	            multiselect: true,
	            onSelectRow: function( rowid ) 
	            {
	            	var treedata = $("#treeGrid").jqGrid('getRowData', rowid);
	            	
	            		alert(treedata.name);
	            	
	            }
	      	});
			
      	}
}