/**
* @fileOverview  js文件
* @author hyihong
* @date 2013-03-28
* @version 1.0
*/
var dd = new dTreeNew('dd'); 

function changeBackgroundColor(obj){
	$('#menuTree').find('a').css('backgroundColor','');
	if($(obj).attr('value')!=$('#hidParentMenuId').val()){
		$(obj).css('backgroundColor','#84C1FF');
			
		$('#hidParentMenuId').val($(obj).attr('value'));
		$('#parentMenuName').val($(obj).attr('name'));
	}else{
		$('#hidParentMenuId').val('-1');
		$('#parentMenuName').val('无');
	}
	
}
var menuDef = {	
	loadGridUrl : "/queryMenuDefByPages.do?ajax=true",
	initPage: function(){
		/**
		* @description 是否有效标志（为json对象）
		* @param  source 如果source为URL，则返回结果为json对象
		* @return 
		*/
		$( "#validFlag" ).taAutoComplete({
			data: commonData.validFlagData
		});
		
		/* @description 是否有效标志（为json对象）
		* @param  source 如果source为URL，则返回结果为json对象
		* @return 
		*/
		$( "#validFlagOfSearch" ).dropDownList({
			selectedValue:'1',
			data: commonData.validFlagData
		});
		
		/**
		* @description 初始化dialog
		* @param  modal 是否模式层
		* @param  height 模式层高度(像素)
		* @param  width 模式层宽度
		* @param  autoOpen 是否默认打开模式层
		* @return 
		*/
		$("#editPage").dialog({
			modal: true,
			height:450,
			width:700,
			autoOpen: false

		});
		
		
		
		/**
		* @description 添加按钮事件
		* @param  addMenuDef 添加按钮ID
		* @return 
		*/
		
		$("#addMenuDef").click( function() {
			//清空表单数据
			$("form input[type='text']").attr("value","");	
			//调用dialog组件
			$('#editPage').dialog('open');
		});
		
		/**
		 * @description 重置按钮事件
		 * @param  resetSearch 重置按钮ID
		 * @return 
		 */
		$("#resetSearch").click( function() {
			form1.reset();
		});
		
		/**
		* @description 查询按钮事件
		* @param  queryMenuDef 查询按钮ID
		* @return 
		*/
		$("#queryMenuDef").click( function() {
			//执行查询方法
			menuDef.gridReload();
		});
		
		
		
		/**
		* @description 修改按钮事件
		* @param  modifyMenuDef 修改按钮ID
		* @return 
		*/
		$("#modifyMenuDef").click( function() {
			//获取选中行的id
			var id = common.selectRowValue("menuDefGrid");
			var patt = new RegExp(',');//		   
			if("" != id){		
				var validFlag = common.selectRowShowCollValue("menuDefGrid", "validFlag", id);
				//判断是否选中多行（字符串存在返回true否则返回false）
				if(patt.test(id)){
				    alert("只能选择一条记录进行修改");
				    return false;
				} else if(validFlag == commonData.validFlagObj.invalid.label){					
					alert("请选择有效的菜单进行修改");
					return false;                    				
				} else{					
					//ajax获取要修改的数据
					common.ajaxGetData("editPage", $.taHash.get("ctx")+"/vcMenu/editMenuDef.do?ajax=true&id="+id+"&action=loading",null);
					//调用dialog组件
					$('#editPage').dialog('open');
				}
			}else{
				alert("请选择一条记录进行修改");
				return false;
			}
			
		});
		
		/**
		* @description 删除按钮事件
		* @param  deleteMenuDef 删除按钮ID
		* @return 
		*/
		$("#deleteMenuDef").click( function() {
			//获取选中行的id
			var id = common.selectRowValue("menuDefGrid");
			if("" != id){
				var message = "";
				var strId = common.selectRowShowCollValue("menuDefGrid", "menuId", id).split(",");	
				var menuName = common.selectRowShowCollValue("menuDefGrid", "menuName", id).split(",");
				
				var validFlag = common.selectRowShowCollValue("menuDefGrid", "validFlag", id).split(",");
				for(var index in strId) {
					if(validFlag[index] == commonData.validFlagObj.invalid.label) {
						message += menuName[index]+"\n";
					}					
				}
				if(message != "") {
					alert("菜单名称为: "+ message + "为无效菜单,无需删除!");
					return ;
				}
				
				//处理提示信息
				var delMsg = "确定要删除：\n";
				var selNum = menuName.length;
				if(selNum<3){
					for(var i = 0; i < selNum; i++){
						delMsg += "菜单及其子菜单："+menuName[i]+"。\n";
					}
				}else{
					for(var i = 0; i < 3; i++){
						delMsg += "菜单及其子菜单："+menuName[i]+"。\n";
					}
				}
//				if(confirm("确定删除该菜单及其子菜单吗？")){
				if(confirm(delMsg)){
					$.post($.taHash.get("ctx")+"/vcMenu/deleteMenuDef.do?ajax=true","id="+id,function(data,status){
						alert("删除成功!");
						menuDef.gridReload();
					});
				}
			}else{
				alert("请选择一条记录进行删除");
				return false;
			}	
		});
	
		/**
		 * @description 还原按钮事件
		 * @param  restoreMenuDef 还原按钮ID
		 * @return 
		 */
		$("#restoreMenuDef").click( function() {
			//获取选中行的id
			var id = common.selectRowValue("menuDefGrid");
			if("" != id){
				var message = "";
				var strId = common.selectRowShowCollValue("menuDefGrid", "menuId", id).split(",");	
				
				var validFlag = common.selectRowShowCollValue("menuDefGrid", "validFlag", id).split(",");
				var menuName = common.selectRowShowCollValue("menuDefGrid", "menuName", id).split(",");
				for(var index in strId) {
					if(validFlag[index] == commonData.validFlagObj.valid.label) {
						message += menuName[index]+"\n";
					}					
				}
				if(message != "") {
					alert("菜单名称为: "+ message + "为有效菜单,无需还原!");
					return ;
				}
				
				//处理提示信息
				var delMsg = "确定要还原：\n";
				var selNum = menuName.length;
				if(selNum<3){
					for(var i = 0; i < selNum; i++){
						delMsg += "菜单及其父菜单："+menuName[i]+"。\n";
					}
				}else{
					for(var i = 0; i < 3; i++){
						delMsg += "菜单及其父菜单："+menuName[i]+"。\n";
					}
				}
				if(confirm(delMsg)){
					$.post($.taHash.get("ctx")+"/vcMenu/restoreMenuDef.do?ajax=true","id="+id,function(data,status){
						alert("还原成功!");
						menuDef.gridReload();
					});
				}
			}else{
				alert("请选择一条记录进行还原");
				return false;
			}
			
			
		});
		
		/**
		* @description 初始化表格数据
		* @param  menuDefGrid 表格ID
		* @param  menuDefGridPager 分页标签Id
		* @return 
		*/
		$("#menuDefGrid").jqGrid({
			mtype: "POST", 
			datatype : "json",
			autowidth : true,
			height: 'auto',
			colNames : ['菜单ID','菜单名称',  '访问地址','所属系统', '是否有效'],
			colModel : [{
					     name : 'menuId',
					     index : 'menuId',
					     autowidth : true,
					     resizable : true,
					     align : 'center',
						 hidden: true
					},{
						name : 'menuName',
						index : 'menuName',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'menuUrl',
						index : 'menuUrl',
						autowidth : true,
						resizable : true,
						align : 'left'
					}, {
						name : 'systemDef.chineseName',
						index : 'systemDef.chineseName',
						autowidth : true,
						resizable : true,
						align : 'left'
					},{
						name : 'validFlag',
						index : 'validFlag',
						autowidth : true,
						resizable : true,
						align : 'center',
						formatter:commonData.validFlagFormatter
					}],
			shrinkToFit : true,
			rowNum : 10,
			rowList : [ 10, 30, 50 ],
			pager : '#menuDefGridPager',
			viewrecords : true,
			jsonReader: {
		     	root: "menuDefList",
		        repeatitems : false,
		        page : 'page',
				total : 'total',
				records : 'records',
				id: "menuId"
		     },
			multiselect: true
		});
	},
	
	/**
	* @description 根据条件查询信息
	* @param  menuDefGrid表格ID
	* @param  chinese_name查询条件	
	* @return 
	*/
	gridReload : function() {
		var gridReloadUrl = $.taHash.get("ctx")+ menuDef.loadGridUrl;
		jQuery("#menuDefGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData:{
				"menuDef.menuName" : $("#menuName").val(),
				"menuDef.validFlag" : $("#validFlagOfSearch").val(),
				"menuDef.systemDef.systemCode" : $("#systemCode").val()
			},
			page : 1
		});
		jQuery("#menuDefGrid").trigger("reloadGrid");
	},
	
	/**
	* @description 初始化编辑页面
	* @param 
	* @return 
	*/
	initEditPage : function(){
		
		//是否有效标志
		$("#validFlag").taAutoComplete({
			source: menuDef.codeLabelData,
			minLength: 0
		});
		
		$('#resetSave').click(function(){
			$(':reset').trigger('click');
		});
		
		$( "#validFlagForSave" ).dropDownList({
			data: commonData.validFlagData,
			selectedValue:'1'
		});
		
		/**
		* @description 表单验证
		* @param  saveBut 保存按钮ID
		* @return 
		*/
		$("#menuDef").validate();
		 menuDef.reloadMenuTree();
		/**
		* @description 保存按钮事件
		* @param  saveBut 保存按钮ID
		* @return 
		*/
			$("#saveBut").click( function() {						
				if($("#menuDef").validate().form()){
					var tmpStr = $("#menuDef").serialize();					
					common.ajaxSubmitDfCallBack(function(data) {
													alert("菜单信息,修改成功!");
													$('#editPage').dialog('close');
													menuDef.gridReload();
					                             }, $.taHash.get("ctx")+"/vcMenu/saveMenuDef.do?ajax=true" , tmpStr);
					
				}else{
					return false;
				}
			});
		
	},
	
	initAddPage: function(){	
		/**
		 * 初始化加载菜单树
		 */
		menuDef.reloadMenuTree();
		/**
		 * @description 重置按钮事件
		 * @param  resetSearch 重置按钮ID
		 * @return 
		 */
		
		$("#reset").click( function() {
			//执行重置方法
			menuForm.reset();
		});
		
		/**
		* @description 表单验证
		* @param  saveBut 保存按钮ID
		* @return 
		*/
		$("#menuForm").validate();
		
		/**
		* @description 保存按钮事件
		* @param  saveBut 保存按钮ID
		* @return 
		*/
		$("#saveBut").click( function() {	
			if($("#menuForm").validate().form()){
				//菜单父级是系统，则设置上级菜单ID为－1，即一级菜单
				if($('#hidParentMenuId').val() == $('#systemCode').val()) {
					$('#hidParentMenuId').val('-1');
				}
				var tmpStr = $("#menuForm").serialize();
				$.post($.taHash.get("ctx")+"/vcMenu/saveMenuDef.do?ajax=true",tmpStr,function(data,status){
					alert("菜单信息,保存成功!");
					menuDef.reloadMenuTree();
					$('#txtMenuName').val('');
					$('#txtMenuIndex').val('');
					$('#txtMenuUrl').val('');
					$('#parentMenuName').val('');
					$('#hidParentMenuId').val('-1');
				});
			}else{
				return false;
			}
		});
	},
	
	/**
	* @description 根据条件查询信息
	* @param  systemDefGrid表格ID
	* @param  chineseName查询条件	
	* @return 
	*/
	gridSystemDefReload : function() {
		var gridReloadUrl = $.taHash.get("ctx")+ "/querySystemDefByPages.do?ajax=true&validFlag=1";
		jQuery("#systemDefGrid").jqGrid('setGridParam', {
			url : gridReloadUrl,
			postData:{
				chineseName : $("#chineseName").val(),
				validFlag: $("#validFlag").val()			
			},
			page : 1
		});
		jQuery("#systemDefGrid").trigger("reloadGrid");
		$("#systemCode").val("");
	},
	
	reloadMenuTree:function(){
		$('#menuTree').html('');
		dd = new dTreeNew('dd');
		$.post($.taHash.get("ctx")+"/queryDTreeDtosBySystemCode.do?ajax=true&systemCode="+ $('#systemCode').val()+'&random=' + Math.random(),null,function(data){
			var treeJson=data.dtreeDtoList;
			for(var item in treeJson){
				var comment=treeJson[item];
				 var htmls='';
				  if(comment.info!=null){
					  if(comment.id.indexOf("menu_")>=0){
						  htmls += "<a href='#' treeDtoId='"+comment.id+"' name='"+comment.info+"' value='"+comment.value+"' onclick='changeBackgroundColor(this);'>";
					  }else{
						  htmls += "<a href='#' name='"+comment.info+"' value='"+comment.value+"' onclick='changeBackgroundColor(this);'>";
					  }
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
		});
	}
}