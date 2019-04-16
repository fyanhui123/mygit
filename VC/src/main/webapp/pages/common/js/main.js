var page = {
	tabList: '',
	init: function(){
	//	$.ajaxSetup({cache:false}) ;
		//初始化菜单
		$("#taMenu").taMenuWidget({
			
			source : $.taHash.get("ctx") + '/index/menu.do?ajax=true&random='+ Math.random()
        });
		//add by chy 20131015 系统切换 begin
	 	$.ajax({
			datatype: 'html',
			url: $.taHash.get("ctx") +'/index/querySystemList.do?ajax=true',
			type: "post",
			success: function(data){
				if(data!=undefined && data != null){
					$.each(data, function(index, obj){
						var sysItem = '<a href="'+obj.appUrl+'">'+obj.appName+'</a><br />';
						$("#switchSys").append(sysItem);
					});
				}
			}
		});
		//add by chy 20131015 系统切换 end
		page.tabList = new Array();
		var  $tabs, centerAccordionLayout; 
		/*$.jstree._themes = $.taHash.get("ctx") + "/style/default/themes/";
		$.get('user.action?action=displayleftaccordion&random='+Math.random(), function(data) {
			//初始化左侧滑动框
		  	$('#accordion-west').html(data);
			$("#accordion-west").accordion({
				autoHeight: false,
				navigation: true,
				changestart: function(event, ui) { 
					ui.newContent.jstree("set_focus");
				}
	
			});
			//初始化左侧树型控件
			$(".treeDiv").taTree();
			
		});*/

		$("#accordion-west").accordion({
			autoHeight: false,
			navigation: true,
			changestart: function(event, ui) { 
				ui.newContent.jstree("set_focus");
			}

		});
		//初始化左侧树型控件
		//$(".treeDiv").taTree();
		
		//初始化右侧TAB页控件
		$tabs = $("#tabs-center").tabs({
			tabTemplate: "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>",
			cache: false,
			spinner: 'Retrieving data...' 
		});
				
		//初始化头部和左侧收缩效果
		$("body").layout({ 
			//west__size:186,	
			north__size:85,
			north__resizable:false,
			closable:false,	// pane can open & close
			resizable:false,	// when open, pane can be resized 
			slidable:true	// when closed, pane can 'slide' open over other panes - closes on mouse-out

		}); 
		//初始化下侧收缩效果
		$("#main-center").layout({
			south__size:31,
			center__paneSelector:".inner-center",
			south__resizable:false,
			onresize_end:	function(){
				page.resizeMainLayout();
			}
		});

		//响应关闭TAB页按钮动作
		$( "#tabs-center span.ui-icon-close" ).live( "click", function() {
			var index = $( "li", $tabs ).index( $( this ).parent() );
			$("#tabs-center").tabs( "remove", index );
			/*for(var i=0;i<page.tabList.length;i++){
				if(index ==page.tabList[i].index ){
					page.tabList[i].obj = null;
					page.tabList[i].index = -1;
				}
			}*/
		});
		
		//相应主系统切换按钮鼠标滑动和点击动作
		$(".system-button").mouseover(function(){
			if(!$(this).hasClass('system-button-selected')){
				if($(this).attr("src").indexOf("_hover")>0){
				}else{
					$(this).attr("src",$(this).attr("src").replace(".png","_hover.png"));
				}
			}
		}).mouseout(function(){
			if(!$(this).hasClass('system-button-selected')){
				if($(this).attr("src").indexOf("_hover")>0){
					$(this).attr("src",$(this).attr("src").replace("_hover.png",".png"));
				}
			}
		}).click(function(){
			//切换左侧滑动框和树型控件			
			$('#accordion-west').accordion("destroy");
			$(".treeDiv").jstree("destroy");
			$.get($(this).attr("url"), function(data) {
			  $('#accordion-west').html(data);
				$("#accordion-west").accordion({
					autoHeight: false,
					navigation: true,
					changestart: function(event, ui) { 
						ui.newContent.jstree("set_focus");
					}
		
				});

				//$(".treeDiv").taTree();
				
			});
			$(".system-button").each(function(){
				if($(this).attr("src").indexOf("_hover")>0){
					$(this).attr("src",$(this).attr("src").replace("_hover.png",".png"));
					$(this).removeClass("system-button-selected");
				}
			});
			$(this).attr("src",$(this).attr("src").replace(".png","_hover.png"));
			$(this).addClass("system-button-selected");
		});
		page.resizeMainLayout();
		$(".page-title").live("click",function(){
			var img = $(this).find("img");
			if($(img).attr("src").indexOf("open")>-1){
				$(this).parent().next().show("blind");
				$(img).attr("src",$(img).attr("src").replace("open","close"));
			}else{
				$(this).parent().next().hide("blind");
				$(img).attr("src",$(img).attr("src").replace("close","open"));
			}
			
		});
		$("#switchDialog").dialog({
			modal: true,
			autoOpen: false,
			height: 130
		
		});
		$("#newContract").button({
			icons: {
                primary: "ui-icon-check"
            }
		});
		
		$("#goSearch").button({
			icons: {
                primary: "ui-icon-check"
            }
		});
		$("#newContract").click(function(){
			$( "#switchDialog" ).dialog("close");
			$( ".managementOrgnization" ).val('');
			$( ".moLabel" ).val('');
			$( ".saleChannel" ).val('');
			$( ".scLabel" ).val('');
			$( ".contractApplyDate" ).val('');
			$( ".insuranceSlipNo" ).val('');
		});
		
		$("#goSearch").click(function(){
			$("#switchDialog").dialog("close");
			var has = false;
			var index = 0;
			var obj = $("#searchContractLink");
			for(var i=0;i<page.tabList.length;i++){
				if($(page.tabList[i].obj).text() == $(obj).text()){
					has = true;
					index = page.tabList[i].index;
				}
			}
			if(!has){
				var item = new Object();
				$("#tabs-center").tabs( "add", $(obj).attr("data")+'?a='+Math.random() ,  $.trim($(obj).text()));
				$("#tabs-center").tabs("select",$("#tabs-center").tabs("length")-1);	
				item.obj = obj;
				item.index = $("#tabs-center").tabs("length")-1;
				page.tabList.push(item);
			}else{
				$("#tabs-center").tabs("select",index);	
			}
			page.resizeMainLayout();
		});
		
		$("#checkDialog").dialog({
			modal: true,
			autoOpen: false,
			height: 130
		
		});
		$("#checkConfirm").button({
			icons: {
                primary: "ui-icon-check"
            }
		});
		$("#checkConfirm").live("click",function(){
			$( "#checkDialog" ).dialog("close");
			var index = $( "li", $tabs ).index( $( ".ui-tabs-selected" ));
			$("#tabs-center").tabs( "remove", index );
			for(var i=0;i<page.tabList.length;i++){
				if(index ==page.tabList[i].index ){
					page.tabList[i].obj = null;
					page.tabList[i].index = -1;
				}
			}
		});
		
		$("#pullDialog").dialog({
			modal: true,
			autoOpen: false,
			height: 130
		
		});
		$("#pullConfirm").button({
			icons: {
                primary: "ui-icon-check"
            }
		});
		$("#pullConfirm").live("click",function(){
			$( "#pullDialog" ).dialog("close");
			var index = $( "li", $tabs ).index( $( ".ui-tabs-selected" ));
			$("#tabs-center").tabs( "remove", index );
			for(var i=0;i<page.tabList.length;i++){
				if(index ==page.tabList[i].index ){
					page.tabList[i].obj = null;
					page.tabList[i].index = -1;
				}
			}
		});

	},
	resizeMainLayout: function (){
		//$(".ui-tabs-panel").height($("#tabs-center").height()-56);
		//$(".frameStyle").height(document.documentElement.clientHeight);
		$(".frameStyle").height(document.body.scrollHeight-185);
	},
	addTab:	function(obj){
		if($(obj).attr("mulit-open")=="mulit-open"){
			$("#tabs-center").tabs( "add", $(obj).attr("data")+'?a='+Math.random() ,  $.trim($(obj).text()));
			$("#tabs-center").tabs("select",$("#tabs-center").tabs("length")-1);	
		}else{
			/*var has = false;
			var index = 0;
			for(var i=0;i<page.tabList.length;i++){
				if( $(page.tabList[i].obj).text() == $(obj).text() ){
					has = true;
					index = page.tabList[i].index;
				}
			}*/
			var menuId = $(obj).attr("id");
			var tabPage = "#tabs-center-" + menuId;
			if($( tabPage ).html() == null){
				var item = new Object();
				//$("#tabs-center").tabs( "add", $(obj).attr("data")+'?a='+Math.random() ,  $.trim($(obj).text()));
				$("#tabs-center").tabs( "add", tabPage ,  $.trim($(obj).text()));
				$(tabPage,"#tabs-center").append('<iframe src="' + $(obj).attr("data")+'?a='+Math.random() + '" class="frameStyle" style = "width:100%; height :100%; overflow-x:hidden; overflow-y:auto; margin:0; padding:0;"  allowtransparency=true  frameborder=0></iframe>');
				
				$("#tabs-center").tabs("select", tabPage);	
				//item.obj = obj;
				//item.index = $("#tabs-center").tabs("length")-1;
				//page.tabList.push(item);
			}else{
				$("#tabs-center").tabs("select", tabPage);	
			}
		}
		page.resizeMainLayout();
	}
	
}