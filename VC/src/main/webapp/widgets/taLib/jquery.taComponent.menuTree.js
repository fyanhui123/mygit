/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.05 $
 *   
 */
(function($){var menuDate="";var isCache="true";var system="";var titilArray=new Array();var TITLE_PREFIX="当前位置：";var TITLE_SEP=" > ";$.fn.menuTree=function(options,val){var opts=$.extend({},$.fn.menuTree.defaults,options);initParam(opts);loadAjaxMenu(opts,this);};$.fn.menuTree.defaults={source:'',userId:'',isCash:'',system:''};function initParam(opts){if(opts.isCache!=undefined){isCache=opts.isCache;}
if(opts.system!=undefined){system=opts.system;}}
function createMainIframe(){}
function loadAjaxMenu(opts,targetObj){var userId=opts.userId;var source=opts.source;if(!isCache){source+='&random='+Math.random();}
$.get(source,{"userId":userId},function(data){menuDate=data.menuList;loadMenu(targetObj);});}
function initRootArray(){var titleView='';var divId="";$.each(menuDate,function(idx,item){if(item.parentNode=="-1"){titilArray.push(item);}});}
function loadMenu(targetObj){initRootArray();for(var i=0;i<titilArray.length;i++){var titleId=titilArray[i].id;var titleName=TITLE_PREFIX+titilArray[i].menuName;buildChildTree(titleId,titleName,$("#treeDivId_"+titleId),'Y');}}
function buildTree(menuArray,titleName){for(var i=0;i<menuArray.length;i++){var menuId=menuArray[i];buildChildTree(menuId,titleName,$("#"+menuId));}}
function buildChildTree(parentId,titleName,targetObj,isMain){var ulHtml='<ul id="ul-'+parentId+'"></ul>';if(isMain=="Y"){$(ulHtml).appendTo(targetObj);}else{$(ulHtml).insertAfter(targetObj);}
var liHtml="";var childArray=new Array();var tmpTitle="";$.each(menuDate,function(idx,item){if(item.parentNode==parentId){tmpTitle=titleName+TITLE_SEP+item.menuName;var url=item.sysResource.url;if(item.isChild=="N"){liHtml+='<li><a href="'+url+'" id='+item.id+' class="treeNodeParent">'+item.menuName+'</a></li>';}else{liHtml+='<li><a href="'+url+'" class="treeNode"  id='+item.id+' target="mainContent" >'+item.menuName+'</a></li>';}
childArray.push(item.id);}});$(liHtml).appendTo($("#ul-"+parentId));$.each(menuDate,function(idx,item){if(item.parentNode==parentId){if(item.isChild!="N"){$("#"+item.id).bind('click',function(event){dispatchView(item.sysResource.url,item.menuName);});}}});$("#mydiv").val($(".treeDiv").html());$(".treeDiv").taTree();buildTree(childArray,tmpTitle);}
function dispatchView(url,titleName){$("#mainContent").attr("src",url);$("#mainContent").attr("height",730);}})(jQuery);