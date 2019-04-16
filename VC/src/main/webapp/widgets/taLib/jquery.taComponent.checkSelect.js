/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.0 $
 *   
 */
(function($){var source="";var position="left";var wigth="400";var height="300";var isCache="";var elementId="";$.fn.dropDownDivList=function(options,val){if(typeof(options)=='string'){switch(options.toLowerCase()){case"default":return this.each(function(){processString($(this),val);});break;case"json":return this.each(function(){processJson($(this),val);});break;}}
var opts=$.extend({},$.fn.dropDownDivList.defaults,options);return this.each(function(){var $this=$(this);var id=$this.attr("id");elementId=id;$(this).dblclick(function(event){initParam(opts);displayDivList(this);});$(document).click(function(event){var eventId=event.target.id;if(eventId==''){var divId="selectItem"+id;var targetId=$("#"+divId);if(isCache!="true"){targetId.attr("id",null);targetId.html("");targetId.fadeOut("slow");}else{targetId.fadeOut("slow");}}});});};$.fn.dropDownDivList.defaults={position:'left',source:'',wigth:'',height:'',isCache:''};function initParam(opts){source=opts.source;if(opts.position!=undefined){position=opts.position.toLowerCase();}
if(opts.isCache!=undefined){isCache=opts.isCache;}
if(opts.wigth!=undefined){wigth=opts.wigth;}
if(opts.useCache!=undefined){height=opts.height;}}
function processString(targetObj,date){var hiddenId=targetObj.attr("id")+"_hidd";targetObj.val(date);$("#"+hiddenId).val(date);}
function processJson(targetObj,date){var jsonObj=date;var keyBuf="";var valueBuf="";var hiddenId=targetObj.attr("id")+"_hidd";$.each(jsonObj,function(idx,item){if(keyBuf!=""){keyBuf+=","+item.value;}else{keyBuf+=item.value;}
if(valueBuf!=""){valueBuf+=","+item.lable;}else{valueBuf+=item.lable;}});targetObj.val(valueBuf);$("#"+hiddenId).val(keyBuf);}
function displayDivList(targetObj){createDiv(targetObj);}
function createDiv(targetObj){var viewHtml="";var divId="selectItem"+targetObj.id;var targetId=$("#"+divId);if(targetId.attr("id")==undefined){viewHtml=viewHtml+'<div id="'+divId+'" class="ui-autocomplete ui-widget-content ui-corner-all" style = "width:420px;"></div>';$(viewHtml).insertAfter(targetObj);if(isCache!="true"){source=source+'&ajax=true&random='+Math.random();}
loadHtml(divId,targetObj.id);}else{targetId.fadeIn();}}
function loadHtml(divId,id){$("#"+divId).load(source,{"divId":id},function(){if($.browser.msie){if($.browser.version<="7.0"){var pos=$("#"+id).position();if(position=="right"){var divWith=$(".floatDiv").width();var b_top=pos.top+$("#"+id).outerHeight(true);var b_left=pos.left-(divWith-$("#"+id).width());$("#"+divId).css({"top":b_top+"px","left":b_left+"px"}).fadeIn();}else if(position=="left"){var A_left=pos.left;var A_top=pos.top+$("#"+id).outerHeight(true);$("#"+divId).css({"top":A_top+"px","left":A_left+"px"}).fadeIn();}}}});}
function closeDivList(targetObj){var divId="selectItem"+targetObj.id;var targetId=$("#"+divId);targetId.fadeOut();}})(jQuery);