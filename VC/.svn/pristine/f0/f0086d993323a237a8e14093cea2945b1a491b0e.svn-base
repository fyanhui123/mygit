/**
* @fileOverview  demo js文件
* @author pengzhaosong
* @date 2013-03-18
* @version 1.0
*/
var sysPrefix="sys_";
var rolePrefix="role_";
var MENU_CHK_PREFIX="menu_";
var DEFAULT_ROLE_SEARCH_VALUE="请输入角色名称！";
var dd = new dTreeNew('dd'); 
dd.config.checkboxEx=MENU_CHK_PREFIX;
var saveRoleId=null;
var distributeResource = {	
	loadGridUrl : $.taHash.get("ctx")+"/queryRoleDefByPages.do?ajax=true",
	loadMenuDefsBySystemOfRole: $.taHash.get("ctx")+"/queryMenuDefsBySystemOfRole.do?ajax=true",
	
	initPage: function(){	
		$( "#tabs" ).tabs();
		$("#roleNameOfSearch").val(DEFAULT_ROLE_SEARCH_VALUE).css('color','#CCCCCC');
		$("#roleNameOfSearch").focus(function(){
			if($.trim($(this).val())==DEFAULT_ROLE_SEARCH_VALUE){
				$(this).css('color','black');
				$(this).val('');
			}
		});
		
		$("#roleNameOfSearch").blur(function(){
			if($.trim($(this).val())==''){
				$(this).css('color','#CCCCCC');
				$(this).val(DEFAULT_ROLE_SEARCH_VALUE);
			}
		});
		
		 $('body').layout({ 
				west__size:270,
				west__resizable:false,
				west__closable:true,
				closable:false,	// pane can open & close
				resizable:false,	// when open, pane can be resized 
				slidable:true	// when closed, pane can 'slide' open over other panes - closes on mouse-out
		}); 

		 $('#tabs-1,#tabs-2,#tabs-3').height($('#rightDiv').height()-Math.round($('#ulHead').height())-27);
		 $('#tabs-1').layout({ 
			north__resizable:false,
			closable:false,	// pane can open & close
			resizable:false,	// when open, pane can be resized 
			slidable:true	//
		 });  
		
		$('#distributeSaveBut').click(function(){
			var tmpStr = "roleId="+saveRoleId;
			if(saveRoleId == null) {
				alert("请选择要进行资源分配的角色");
				return false;
			}
			$(':checkbox[id^="'+MENU_CHK_PREFIX+'"]').each(function(){
				if(this.checked)
					tmpStr+="&menuId="+$(this).attr('value');
			});
			common.ajaxSubmitDfCallBack(function(data){
				alert("保存成功！");
			}, $.taHash.get("ctx")+"/savedistributeMenuDef.do??ajax=true",tmpStr);
			
		});
		
		/**
		 * @description 查询按钮事件
		 * @param  btnQueryRole 查询按钮ID
		 * @return 
		 */
		$("#btnQueryRole").click( function() {
			//执行查询方法
			distributeResource.gridReload();
		});
		
		/**
		* @description 初始化表格数据
		* @param  roleDefGrid 表格ID
		* @param  roleDefGridPager 分页标签Id
		* @return 
		*/
		$("#roleDefGrid").jqGrid({
			mtype: "POST", 
			datatype : "local",
			autowidth : true,
			height: 'auto',
			colNames : ['角色名称'],
			colModel : [
						{
							name : 'roleName',
							index : 'roleName',
							autowidth : true,
							resizable : true,
							align : 'left'
						}
					],
			shrinkToFit : true,
			rowNum:15,
			rowList : [ 15, 30,50],
			pager : '#roleDefGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "roleDefList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "roleId"
		     },
			multiselect: false,
			/**
			* @description 获取选中的值
			* @param  roleDefGrid 表格ID
			* @return 
			*/
			onSelectRow: function(id){
				distributeResource.changeRole(id);
			}
		});
	},
	/**
	* @description 根据条件查询信息
	* @param  roleDefGrid表格ID
	* @param  roleName 查询条件	
	* @return 
	*/
	gridReload : function() {
		var gridReloadUrl =  $.taHash.get("ctx") + "/queryRoleDefByPages.do?ajax=true&random="+Math.random();
		jQuery("#roleDefGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData:{
				"roleDef.roleName" : $.trim($("#roleNameOfSearch").val())==DEFAULT_ROLE_SEARCH_VALUE?"":$.trim($("#roleNameOfSearch").val()),
				"roleDef.validFlag":"1"
			},
			datatype : "json",
			page : 1
		});
		jQuery("#roleDefGrid").trigger("reloadGrid");
	},
	
	changeRole: function(roleId){			
		saveRoleId=roleId;
		$('#menuTree').html('');
		dd = new dTreeNew('dd');
		dd.config.checkboxEx=MENU_CHK_PREFIX;
		$.post($.taHash.get("ctx")+"/queryDTreeDtosOfValid.do?ajax=true&roleId="+ roleId+'&random=' + Math.random(),null,function(data){
			var treeJson=data.dtreeDtoList;
			for(var item in treeJson){
				var comment=treeJson[item];
				 var htmls='';
				  if(comment.id.indexOf(MENU_CHK_PREFIX)>=0){
						  htmls = "<input onclick=\"javascript:dd.checkselect('" + comment.id + "',true)\" type='checkbox'   name='" + name + "' ";
						  htmls += " id='"+MENU_CHK_PREFIX + comment.id + "' ";	
						  
						  if(comment.value!=null)
						  htmls += " value='" + comment.value + "' ";						  
						  htmls += " />"; 
				  }		  

				  if(comment.info!=null){ 
					  htmls += "<a href=\"javascript: dd.o('" + comment.id + "')\">";
					  htmls += comment.info;
					  htmls += "</a>";
				  } 
				  if(Number(comment.pid)<2){
				  		dd.add(comment.id,comment.pid,htmls,'','',true);   
				  }else{
				  		dd.add(comment.id,comment.pid,htmls);         
				  }    
				  
			}
			$('#menuTree').html(dd.toString());
			var menuJson=data.menuDefs;
			for(var item in menuJson){
				$('#'+MENU_CHK_PREFIX+MENU_CHK_PREFIX+menuJson[item].menuId).attr('checked',true);
			}
		});
	}
}
