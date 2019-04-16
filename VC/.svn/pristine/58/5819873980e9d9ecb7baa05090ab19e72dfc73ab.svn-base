/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.05 $
 *   
 */

(function($){var columnData="";var hiddenPlus='0';var hiddenSubtraction='0';var initJsonDate="";var isHaveTitle='0';var initDataUrl="";var rowIdx=1;var elementId="";var exeFuction="";$.fn.mulLineWidget=function(options,haveTitle){var opts=$.extend({},$.fn.mulLineWidget.defaults,options);if(typeof(options)=='string'){switch(options.toLowerCase()){case"deloneline":return this.each(function(){delRow($(this));reSetSeqVal();});}}
var $this=$(this);elementId=$this.attr("id");rowIdx=1;exeFuction="";columnData="";hiddenPlus='0';hiddenSubtraction='0';initJsonDate="";isHaveTitle='0';initDataUrl="";initParm(opts);loadMulLineTable(this);initLineDate(this,opts);return this.each(function(){var $this=$(this);var id=$this.attr("id");$("#addOneLine"+id).click(function(event){addNewRow(id,opts,isHaveTitle);eachColumnAndAddWidget(id,opts,isHaveTitle,rowIdx);bindFunction();});});};function initParm(opts){columnData=opts.columnData;if(opts.hiddenPlus!=undefined){hiddenPlus=opts.hiddenPlus;}
if(opts.hiddenSubtraction!=undefined){hiddenSubtraction=opts.hiddenSubtraction;}
if(opts.initJsonDate!=undefined){initJsonDate=opts.initJsonDate;}
if(opts.isHaveTitle!=undefined){isHaveTitle=opts.isHaveTitle;}
if(opts.initDataUrl!=undefined){initDataUrl=opts.initDataUrl;}
if(opts.exeFuction!=undefined){exeFuction=opts.exeFuction;}}
$.fn.mulLineWidget.defaults={hiddenPlus:'0',hiddenSubtraction:'0',columnData:'',initJsonDate:'',initDataUrl:''};function loadMulLineTable(obj){if(isHaveTitle=='0'){createTable(obj);insertAddBut();addTableHeader();}else if(isHaveTitle=='1'){}}
function createTable(obj){var idName="table"+elementId;var theadDiv='<div class="ui-jqgrid"><table id="'+idName+'" border="0" cellSpacing="0" cellPadding="0" width="100%"></table></div>';$(theadDiv).insertAfter(obj);}
function insertAddBut(){if(hiddenPlus=='0'){var tableObj=$('#table'+elementId);var addButton='<span style="margin-top:5px;display:block;margin-left:20px;" class="ui-icon ui-icon-circle-plus" id="addOneLine'+elementId+'")"></span><div>';$(addButton).insertAfter(tableObj);}}
function addDel(row,tableId,havaTitle){var delText="";var delTd=$("<td align='center' class=\"mulLineWidgetRight\"></td>");delText=$('<span style="cursor: pointer;" class="ui-icon ui-icon-circle-minus" id="delOneLine'+tableId+'" onclick="javascript:$(this).mulLineWidget(\'delOneline\',\''+tableId+'\')"></span>');delTd.append(delText);row.append(delTd);}
function delRow(obj){obj.parent().parent().remove();}
function addTableHeader(){var tableObj=$('#table'+elementId);var row=$("<tr></tr>");var rowIdxTd=$("<th class='mulLine-state-default' width='40px;'></th>");var rowIdxText="序号";rowIdxTd.append(rowIdxText);row.append(rowIdxTd);$.each(columnData,function(idx,item){var titleName=item.title;var width="10%";if(undefined!=item.width){width=item.width;}
var td=$("<th class='mulLine-state-default' width='"+width+"' align='center'></th>");td.append(titleName);row.append(td);});if(hiddenSubtraction=='0'){var operTd=$("<th class='mulLine-state-default' width='35px' height='22px'></th>");var operText="删除";operTd.append(operText);row.append(operTd);}
tableObj.append(row);}
function initLineDate(obj,opts){if(initJsonDate!=""){var jsonDate=eval(initJsonDate);initDataAndCteateRow(jsonDate,obj,opts);}else if(initDataUrl!=""){var sourceUrl=initDataUrl;sourceUrl+='&random='+Math.random();$.get(sourceUrl,function(data){initDataAndCteateRow(data,obj,opts);});}
bindFunction();}
function initDataAndCteateRow(jsonDate,obj,opts){$.each(jsonDate,function(idx,dateItem){createRow(dateItem,obj.attr("id"),isHaveTitle);if(idx<jsonDate.length){rowIdx++;}});eachColumnAndAddWidget(obj.attr("id"),opts,isHaveTitle);}
function getValue(key,item){var rsValue="";$.each(item,function(fieldName,fieldValue){if(key==fieldName){rsValue=fieldValue;return;}});return rsValue;}
function getLableValue(key,jsonArr){var resultValue="";$.each(jsonArr,function(indx,item){if(key==item['value']){resultValue=item['lable'];return;}});return resultValue;}
function createRow(jsonDate,eleId,isHaveTitle){if(rowIdx==0){rowIdx=1;}
var tableObj;if(isHaveTitle=='0'){tableObj=$('#table'+eleId);}else if(isHaveTitle=='1'){tableObj=$('#'+eleId);}
var rowHtml=$("<tr></tr>");var seqDiv=$("<td width='35px;' class=\"mulLineWidgetLeft\"></td>");var seqText="<span id = 'rIdx' style='text-align:center;display:block;'>"+rowIdx+"</span>";seqDiv.append(seqText);rowHtml.append(seqDiv);$.each(columnData,function(idx,item){var type=item.type;var fieldId=item.fieldId;var fieldName=item.fieldName+"__"+rowIdx;var fieldValue=getValue(fieldId,jsonDate);var inputId=fieldId+"__"+rowIdx;var fieldClass=item.fieldClass;var min=item.min;var max=item.max;var minNum=item.minNum;var maxNum=item.maxNum;var minlength=item.minlength;var maxlength=item.maxlength;var lableVal="";var twoInput="";var dataArr=item.dataFormat;var disabled="";if(undefined!=item.disabled){disabled="disabled=\""+item.disabled+"\"";}
if(undefined!=item.fieldVal){fieldValue=item.fieldVal;}
if(undefined!=dataArr){var source="";$.each(dataArr,function(idx,item){source=item.source;twoInput=item.twoInput;});if(typeof(source)!='string'){if(undefined!=source){lableVal=getLableValue(fieldValue,source);}}else if(typeof(source)=='string'){if(undefined!=source&&type=="2"){source=source.replace("undefined",$.taHash.get("ctx"));source+="&codeCode="+fieldValue;$.get(source,function(data){var labelVal="";$.each(data,function(index,value){if(value.value==fieldValue){labelVal=value.lable;}});var lableId=inputId+"Name";$("#"+lableId).val(labelVal);});}}}
var width="10%";if(undefined!=item.width){width=item.width;}
var dateFmt="yyyy-MM-dd";if(item.dateFmt!=undefined){dateFmt=item.dateFmt;}
var tdTag=$("<td width='"+width+"' class='mulLineWidgetLeft'></td>");var tdHtml="";switch(type){case"1":tdHtml+="<input type='text' width='"+width+"px;' plugType='1' name='"+fieldName+"' id='"+inputId+"' value='"+fieldValue+"' "+disabled+"/>";break;case"2":var codeClass=inputId+" ui-autocomplete-input "+fieldClass;tdHtml+="<input type='text' class='"+codeClass+"' plugType='2' name='"+fieldName+"' id='"+inputId+"' value='"+fieldValue+"' "+disabled+"/>";if(twoInput!=false){var labelName=inputId+"Name";tdHtml+="<input type='text' class='ui-autocomplete-label' readonly='readonly' name='"+labelName+"' id='"+inputId+"Name' value='"+lableVal+"'/>";}
break;case"3":var selectClass="selectStyle "+fieldClass;tdHtml+="<select type='select' class='"+selectClass+"' width='"+width+"px;' plugType='3' name='"+fieldName+"' id='"+inputId+"' "+disabled+" title='"+fieldValue+"'/>";break;case"4":var textStyle="";if(undefined!=item.width){textStyle="width:"+item.width;}
tdHtml+="<input type='text' class='"+fieldClass+"' style='"+textStyle+"' plugType='4' name='"+fieldName+"' id='"+inputId+"' value='"+fieldValue+"'  "+disabled+" ";if(min!=undefined){tdHtml+=" min='"+min+"' ";}
if(max!=undefined){tdHtml+=" max='"+max+"' ";}
if(minNum!=undefined){tdHtml+=" minNum='"+minNum+"' ";}
if(maxNum!=undefined){tdHtml+=" maxNum='"+maxNum+"' ";}
if(minlength!=undefined){tdHtml+=" minlength='"+minlength+"' ";}
if(maxlength!=undefined){tdHtml+=" maxlength='"+maxlength+"' ";}
tdHtml+=" />";break;case"5":var dateTextClass="Wdate "+fieldClass;var textStyle="";if(undefined!=item.width){textStyle="width:"+item.width;}
tdHtml+="<input type='text' class='"+dateTextClass+"' style='"+textStyle+"' plugType='5' name='"+fieldName+"' id='"+inputId+"' value='"+fieldValue+"' "+disabled+"  onclick='WdatePicker({dateFmt:\""+dateFmt+"\"})'/>";break;case"6":tdHtml+="<select type='select' class='"+fieldClass+"' plugType='6' name='"+fieldName+"' id='"+inputId+"' selectValue='"+fieldValue+"' "+disabled+" />";break;case"7":var textStyle="";if(undefined!=item.width){textStyle="width:"+item.width;}
tdHtml+="<input type='password' class='"+fieldClass+"' style='"+textStyle+"' plugType='7' name='"+fieldName+"' id='"+inputId+"' value='"+fieldValue+"' "+disabled+" />";break;case"8":tdHtml+="<button id='"+inputId+"' type='button' buttonId='"+inputId+"' class='taButton'> "+fieldValue+"</button>";break;}
tdTag.append(tdHtml);rowHtml.append(tdTag);});if(hiddenSubtraction=='0'){addDel(rowHtml,eleId,isHaveTitle);}
tableObj.append(rowHtml);}
function addNewRow(id,opts,havaTitle){var tableId='table'+elementId;var nTrs=$('table[id='+tableId+']>tbody').children("tr");if($(nTrs).length!=rowIdx){rowIdx++;}
var rowHtml=$("<tr></tr>");var seqDiv=$("<td width='35px;' class=\"mulLineWidgetLeft\"></td>");var seqText="<span id = 'rIdx' style='text-align:center;display:block;'>"+rowIdx+"</span>";seqDiv.append(seqText);rowHtml.append(seqDiv);$.each(columnData,function(idx,item){var type=item.type;var fieldId=item.fieldId;var fieldName=item.fieldName+"__"+rowIdx;var fieldValue=getValue(fieldId,columnData);var inputId=fieldId+"__"+rowIdx;var fieldClass=item.fieldClass;var min=item.min;var max=item.max;var minNum=item.minNum;var maxNum=item.maxNum;var minlength=item.minlength;var maxlength=item.maxlength;var twoInput="";var dataArr=item.dataFormat;if(undefined!=dataArr){$.each(dataArr,function(idx,item){twoInput=item.twoInput;});}
var width="10%";if(undefined!=item.width){width=item.width;}
var disabled="";if(undefined!=item.disabled){disabled=item.disabled;}
var dateFmt="yyyy-MM-dd";if(item.dateFmt!=undefined){dateFmt=item.dateFmt;}
if(undefined!=item.fieldVal){fieldValue=item.fieldVal;}
var tdTag=$("<td width='"+width+"' class='mulLineWidgetLeft'></td>");var tdHtml="";switch(type){case"1":tdHtml+="<input type='text' width='"+width+"px;' plugType='1' name='"+fieldName+"' id='"+inputId+"' value='' "+disabled+"/>";break;case"2":var codeClass=inputId+" ui-autocomplete-input "+fieldClass;tdHtml+="<input type='text' class='"+codeClass+"' plugType='2' name='"+fieldName+"' id='"+inputId+"' "+disabled+" />";if(twoInput!=false){var labelName=inputId+"Name";tdHtml+="<input type='text' class='ui-autocomplete-label' readonly='readonly' name='"+labelName+"' id='"+inputId+"Name'/>";}
break;case"3":var selectClass="selectStyle "+fieldClass;tdHtml+="<select type='select' class='"+selectClass+"' plugType='3' name='"+fieldName+"' id='"+inputId+"' "+disabled+"/>";break;case"4":var textStyle="";if(undefined!=item.width){textStyle="width:"+item.width;}
tdHtml+="<input type='text' class='"+fieldClass+"' style='"+textStyle+"' plugType='4' name='"+fieldName+"' id='"+inputId+"' value='' "+disabled+" ";if(min!=undefined){tdHtml+=" min='"+min+"' ";}
if(max!=undefined){tdHtml+=" max='"+max+"' ";}
if(minNum!=undefined){tdHtml+=" minNum='"+minNum+"' ";}
if(maxNum!=undefined){tdHtml+=" maxNum='"+maxNum+"' ";}
if(minlength!=undefined){tdHtml+=" minlength='"+minlength+"' ";}
if(maxlength!=undefined){tdHtml+=" maxlength='"+maxlength+"' ";}
tdHtml+=" />";break;case"5":var dateTextClass="Wdate "+fieldClass;var textStyle="";if(undefined!=item.width){textStyle="width:"+item.width;}
tdHtml+="<input type='text' class='"+dateTextClass+"' style='"+textStyle+"' plugType='5' name='"+fieldName+"' id='"+inputId+"' value='' "+disabled+" onclick='WdatePicker({dateFmt:\""+dateFmt+"\"})'/>";break;case"6":tdHtml+="<select type='select' plugType='6' name='"+fieldName+"' id='"+inputId+"' "+disabled+"/>";break;case"7":var textStyle="";if(undefined!=item.width){textStyle="width:"+item.width;}
tdHtml+="<input type='password' class='"+fieldClass+"' style='"+textStyle+"' plugType='7' name='"+fieldName+"' id='"+inputId+"' value='' "+disabled+"/>";break;case"8":tdHtml+="<button id='"+inputId+"' type='button' buttonId='"+inputId+"' class='taButton'> "+fieldValue+"</button>";break;}
tdTag.append(tdHtml);rowHtml.append(tdTag);});if(hiddenSubtraction=='0'){addDel(rowHtml,id,isHaveTitle);}
var tableId=id;if(havaTitle=='0'){tableId='table'+id;}
$("#"+tableId).append(rowHtml);}
function eachColumnAndAddWidget(id,opts,isHavaTitle,cRowIdx){if(id==''||id==undefined){id=elementId;}
var tableObj;if(isHavaTitle=='0'){tableObj=$('#table'+id);}else{tableObj=$('#'+id);}
var nTr=$('tr',tableObj);var indx=1;nTr.each(function(rowIdx){if(rowIdx>0){var ntd=null;if(cRowIdx!=null){if(cRowIdx==rowIdx){ntd=$("td",nTr[rowIdx]);}else{return;}}else{ntd=$("td",nTr[rowIdx]);}
ntd.each(function(colIdx){var inputObj=$(this).children();if(inputObj.attr("type")=='text'||inputObj.attr("type")=='hidden'||inputObj.attr("type")=='select'||inputObj.attr("type")=='password'){var inputValue="";if(inputObj.attr("type")=='select'){inputValue=inputObj.attr("title");}else{inputValue=inputObj.attr("value");}
initWidget(inputObj.attr("id"),inputObj.attr("plugType"),inputObj.attr("name"),rowIdx,inputValue,opts);}});}});}
function initWidget(id,type,name,rIndex,initValue,opts){var filedFormat="";var colJsonDate=opts.columnData;$.each(colJsonDate,function(idx,items){var fieldName=items.fieldName+"__"+rIndex;var filedType=items.type;if(filedType==type&&fieldName==name){filedFormat=items.dataFormat;}});switch(type){case"2":var source="";var height="";var width="";var twoInput="";var minLength=0;$.each(filedFormat,function(idx,itemData){source=itemData.source;height=itemData.height;width=itemData.width;twoInput=itemData.twoInput;minLength=itemData.minLength;});if(undefined==twoInput){twoInput=true;}
$("#"+id).taAutoComplete({source:source,height:height,width:width,twoInput:twoInput,minLength:minLength});break;case"3":var dorpData="";var url="";var trigger="";var event="";var dataField="";var selectedValue="";$.each(filedFormat,function(idx,item){if(item.data!=undefined){dorpData=item.data;}
if(item.url!=undefined){url=item.url;}
if(item.trigger!=undefined){trigger='#'+item.trigger+"__"+rIndex;}
if(item.event!=undefined){event=item.event;}
if(item.dataField!=undefined){dataField=item.dataField;}
if(undefined!=item.selectedValue){selectedValue=item.selectedValue;}});if(""!=initValue){selectedValue=initValue;}
$("#"+id).dropDownList({selectedValue:selectedValue,trigger:trigger,data:dorpData,url:url,event:event,dataField:dataField});break;}}
function reSetSeqVal(){var tableId;if(isHaveTitle=='0'){tableId='table'+elementId;}else{tableId=elementId;}
var nTrs=$('table[id='+tableId+']>tbody').children("tr");nTrs.each(function(rIndex){if(rIndex>0){var ntd=$("td",nTrs[rIndex]);var seqObj=$("span",ntd[0]);var seqId="rIdx";if(undefined!=seqObj.attr("id")){if(seqObj.attr("id")==seqId){seqObj.text(rIndex);return;}}}});rowIdx=($(nTrs).length)-1;}
function bindFunction(){var tableId;if(isHaveTitle=='0'){tableId='table'+elementId;}else{tableId=elementId;}
var nTrs=$('table[id='+tableId+']>tbody').children("tr");nTrs.each(function(rIndex){if(rIndex>0){var ntd=$("td",nTrs[rIndex]);ntd.each(function(tIndex){var chName=$(this).children().attr("id");if($(this).children().attr("type")=="button"){$("#"+chName).bind("click",function(event){if(""!=exeFuction){var butId=$(this).attr("id");butId=butId.substring(0,butId.lastIndexOf("_")-1);exeFuction(rIndex,butId);event.stopPropagation();}});}else{$("#"+chName).bind("change",function(event){if(""!=exeFuction){var inputId=$(this).attr("id");inputId=inputId.substring(0,inputId.lastIndexOf("_")-1);exeFuction(rIndex,inputId);event.stopPropagation();}});}});}});rowIdx=($(nTrs).length)-1;}
$.fn.mulLineWidget.resetVal=function(rIndex,targetId,targetVal){var tableId;if(isHaveTitle=='0'){tableId='table'+elementId;}else{tableId=elementId;}
var nTrs=$('table[id='+tableId+']>tbody').children("tr");targetId=targetId+"__"+rIndex;nTrs.each(function(rIdx){if(rIdx==rIndex){var ntd=$("td",nTrs[rIdx]);ntd.each(function(tIndex){if($(this).children().attr("name")!=undefined){if($(this).children().attr("name")==targetId){if($("#"+targetId).attr("type")=="text"){$("#"+targetId).val(targetVal);}else if($("#"+targetId).attr("type")=="select"){$("#"+targetId).dropDownList("selected",targetVal);}
/*$("#"+targetId).unbind();*/};}});}});}
$.fn.mulLineWidget.getData=function(){var tableId;if(isHaveTitle=='0'){tableId='table'+elementId;}else{tableId=elementId;}
var nTrs=$('table[id='+tableId+']>tbody').children("tr");var valStr="[";var count=0;var tdLen=0;var trLen=$(nTrs).length;nTrs.each(function(rIdx){var ntd=$("td",nTrs[rIdx]);tdLen=$(ntd).length;if($(ntd).length==0){count++;return;}
var btCount=0;ntd.each(function(tdIdx){if($(this).children().attr("type")=="button"){btCount++;}});tdLen=tdLen-btCount;valStr+="{";ntd.each(function(tIndex){if($(this).children().length>1){var fName=$(this).children().first().attr("name");fName=fName.substring(0,fName.lastIndexOf("_")-1);var fVal=$(this).children().first().attr("value");valStr+="\""+fName+"\":"+"\""+fVal+"\",";var lName=fName+"Name";var lVal=$(this).children().last().attr("value");valStr+="\""+lName+"\":"+"\""+lVal+"\"";if(hiddenSubtraction=='1'&&tIndex<(tdLen-count)){valStr+=",";}else if(tIndex<(tdLen-count-1)){valStr+=",";}}else{var name="";if(undefined!=$(this).children().attr("name")){name=$(this).children().attr("name");name=name.substring(0,name.lastIndexOf("_")-1);}
var val="";if(undefined!=$(this).children().attr("value")&&undefined!=$(this).children().attr("name")){val=$(this).children().attr("value");}
if(""!=name){valStr+="\""+name+"\":"+"\""+val+"\"";if(hiddenSubtraction=='1'&&tIndex<(tdLen-count)){valStr+=",";}else if((tIndex<(tdLen-count-1))){valStr+=",";}}}});valStr+="}";if(rIdx<trLen-1){valStr+=",";}});valStr+="]";return JSON.parse(valStr);}})(jQuery);