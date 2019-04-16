/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.05 $
 *   
 */

(function($){var isCheckBox='N';var source='';var isCache="true";var elementId='';var targetId='#';var rootArray=new Array();$.fn.taTree=function(options){var opts=$.extend({},$.fn.taTree.defaults,options);initParm(opts);if(source!=''&&typeof(source)=='string'){loadAjaxMenu(opts,this);var $this=$(this);}else{this.each(function(){var $this=$(this);var o=$.meta?$.extend({},this.opts,$this.data()):opts;if(isCheckBox=='Y'){$this.jstree({"plugins":["themes","html_data","checkbox"]},o);}else{$this.jstree(o);}});}
$($(this)[0]).jstree("set_focus");return;}
function initParm(opts){if(opts.isCheckBox!=undefined){isCheckBox=opts.isCheckBox;}
if(opts.source!=undefined){source=opts.source;}
if(opts.isCache!=undefined){isCache=opts.isCache;}
if(opts.elementId!=undefined){elementId=opts.elementId;}
if(opts.targetId!=undefined){targetId=opts.targetId;}}
$.fn.taTree.defaults={source:'',isCheckBox:'N',elementId:'',targetId:'#'};function loadAjaxMenu(opts,targetObj){var userId=opts.userId;var source=opts.source;if(!isCache){source+='&random='+Math.random();}
$.get(source,{"userId":userId},function(data){loadTreeNode(data,targetObj);});}
function initRootArray(data){$.each(data,function(idx,item){var flag=false;var parentNode=item.parentNodeId;if(itemContains(parentNode,data)){rootArray.push(item);}});}
function itemContains(containsValue,data){var count=0;$.each(data,function(dIdx,dItem){if(dItem.nodeId==containsValue){count++;return;}});if(count==0){return true;}else{return false;}}
function loadTreeNode(data,targetObj){initRootArray(data);for(var i=0;i<rootArray.length;i++){var nodeId=rootArray[i].nodeId;var parentNodeId=rootArray[i].parentNodeId;if(i==0){buildRootNode(parentNodeId,data,targetObj,"Y");}
buildChildNode(nodeId,data,$("#li__"+nodeId));}}
function loadChideNode(childArray,data){for(var i=0;i<childArray.length;i++){var nodeId=childArray[i];buildChildNode(nodeId,data,$("#li__"+nodeId));}}
function buildRootNode(nodeId,data,targetObj,isRootNode){var ulHtml='<ul id="ul__'+nodeId+'"></ul>';if(isRootNode=="Y"){$(ulHtml).appendTo(targetObj);}else{$(ulHtml).insertAfter(targetObj);}
var liHtml="";$.each(data,function(idx,item){if(item.parentNodeId==nodeId){var url="#";if(item.url!="#"){url=common.ctx+item.url+'&random='+Math.random();}
if(item.isChecked=="Y"){liHtml+='<li class= "jstree-checked" id="'+item.nodeId+'"><a href="#" id="li__'+item.nodeId+'"';}else{liHtml+='<li id="'+item.nodeId+'"><a href="#" id="li__'+item.nodeId+'" target="'+targetId+'"';}
if(item.url!="#"&&isCheckBox=="N"&&item.url!=null){liHtml+='onclick=javascript:window.location.href="'+url+'" ';}
if(isRootNode=="Y"){liHtml+='class="treeNodeParent">'+item.nodeName+'</a></li>';}else{liHtml+='>'+item.nodeName+'</a></li>';}}});$(liHtml).appendTo($("#ul__"+nodeId));$("#divVal").val($("#treeDiv").html());}
function buildChildNode(nodeId,data,targetObj,isRootNode){var ulHtml='<ul id="ul__'+nodeId+'"></ul>';if(isRootNode=="Y"){$(ulHtml).appendTo(targetObj);}else{$(ulHtml).insertAfter(targetObj);}
var liHtml="";var childArray=new Array();$.each(data,function(idx,item){if(item.parentNodeId==nodeId){var url="#";if(item.url!="#"){url=common.ctx+item.url+'&random='+Math.random();}
if(item.isChecked=="Y"){liHtml+='<li class= "jstree-checked" id="'+item.nodeId+'"><a href="#" id="li__'+item.nodeId+'"';}else{liHtml+='<li id="'+item.nodeId+'"><a href="#" id="li__'+item.nodeId+'" target="'+targetId+'" ';}
if(item.url!="#"&&isCheckBox=="N"&&item.url!=null){liHtml+='onclick=javascript:window.location.href="'+url+'" ';}
if(isRootNode=="Y"){liHtml+='class="treeNodeParent">'+item.nodeName+'</a></li>';}else{liHtml+='>'+item.nodeName+'</a></li>';}
childArray.push(item.nodeId);}});$(liHtml).appendTo($("#ul__"+nodeId));loadChideNode(childArray,data);if(isCheckBox=='Y'){$("#"+elementId).jstree({"plugins":["themes","html_data","checkbox"]});}else{$("#"+elementId).jstree();}}})(jQuery);