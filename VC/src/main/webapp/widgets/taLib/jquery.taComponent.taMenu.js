/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.05 $
 *   
 */

var taMenu=function(){var D=document,OP=Object.prototype,OA='object Array',OF='[object Function]',nt='nodeType',reCache={},patterns={HYPHEN:/(-[a-z])/i,ROOT_TAG:/body|html/i,CLASS_RE_TOKENS:/([\.\(\)\^\$\*\+\?\|\[\]\{\}])/g},CUSTOM_ATTRIBUTES=(!document.documentElement.hasAttribute)?{'for':'htmlFor','class':'className'}:{'htmlFor':'for','className':'class'},_CLASS='class',CLASS_NAME='className',EMPTY='',SPACE=' ',C_START='(?:^|\\s)',C_END='(?= |$)',_G='g';return{isArray:function(obj){return OP.toString.apply(obj)===OA;},isString:function(s){return typeof s==='string';},isFunction:function(fn){return OP.toString.apply(fn)===OF;},isIE:function(){var A=navigator.userAgent.match(/MSIE\s([^;]*)/);if(A&&A[1]){return parseFloat(A[1]);}
else{return false;}}(),trim:function(str){try{return str.replace(/^\s+|\s+$/g,'');}
catch(a){return str;}},toCamel:function(property){if(!patterns.HYPHEN.test(property)){return property;}
if(propertyCache[property]){return propertyCache[property];}
var converted=property;while(patterns.HYPHEN.exec(converted)){converted=converted.replace(RegExp.$1,RegExp.$1.substr(1).toUpperCase());}
propertyCache[property]=converted;return converted;},getEl:function(elem){var elemID,E,m,i,k,length,len;if(elem){if(elem[nt]||elem.item){return elem;}
if(this.isString(elem)){elemID=elem;elem=D.getElementById(elem);if(elem&&elem.id===elemID){return elem;}
else{if(elem&&elem.all){elem=null;E=D.all[elemID];for(i=0,len=E.length;i<len;i+=1){if(E[i].id===elemID){return E[i];}}}}
return elem;}
else{if(elem.DOM_EVENTS){elem=elem.get("element");}
else{if(this.isArray(elem)){m=[];for(k=0,length=elem.length;k<length;k+=1){m[m.length]=this.getEl(elem[k]);}
return m;}}}}
return null;},getStyle:function(el,property){if(D.defaultView&&D.defaultView.getComputedStyle){var value=null;if(property=='float'){property='cssFloat';}
var computed=D.defaultView.getComputedStyle(el,'');if(computed){value=computed[this.toCamel(property)];}
return el.style[property]||value;}
else{if(D.documentElement.currentStyle&&this.isIE){switch(this.toCamel(property)){case'opacity':var val=100;try{val=el.filters['DXImageTransform.Microsoft.Alpha'].opacity;}
catch(e){try{val=el.filters('alpha').opacity;}
catch(e){}}
return val/100;break;case'float':property='styleFloat';default:var value=el.currentStyle?el.currentStyle[property]:null;return(el.style[property]||value);}}
else{return el.style[property];}}},setStyle:function(el,property,val){if(this.isIE){switch(property){case'opacity':if(this.isString(el.style.filter)){el.style.filter='alpha(opacity='+val*100+')';if(!el.currentStyle||!el.currentStyle.hasLayout){el.style.zoom=1;}}
break;case'float':property='styleFloat';default:el.style[property]=val;}}
else{if(property==='float'){property='cssFloat';}
el.style[property]=val;}},setStyles:function(el,propertys){for(var p in propertys){this.setStyle(el,p,propertys[p]);}
return el;},setOpacity:function(el,val){this.setStyle(el,'opacity',val);},hasClass:function(el,className){var ret=false,current;if(el&&className){current=this.getAttribute(el,CLASS_NAME)||EMPTY;if(className.exec){ret=className.test(current);}
else{ret=className&&(SPACE+current+SPACE).indexOf(SPACE+className+SPACE)>-1;}}
else{throw new Error('hasClass called with invalid arguments');}
return ret;},addClass:function(el,className){var ret=false,current;if(el&&className){current=this.getAttribute(el,CLASS_NAME)||EMPTY;if(!this.hasClass(el,className)){this.setAttribute(el,CLASS_NAME,this.trim(current+SPACE+className));ret=true;}}
else{throw new Error('addClass called with invalid arguments');}
return ret;},removeClass:function(el,className){var ret=false,current,newClass,attr;if(el&&className){current=this.getAttribute(el,CLASS_NAME)||EMPTY;this.setAttribute(el,CLASS_NAME,current.replace(this.getClassRegex(className),EMPTY));newClass=this.getAttribute(el,CLASS_NAME);if(current!==newClass){this.setAttribute(el,CLASS_NAME,this.trim(newClass));ret=true;if(this.getAttribute(el,CLASS_NAME)===''){attr=(el.hasAttribute&&el.hasAttribute(_CLASS))?_CLASS:CLASS_NAME;el.removeAttribute(attr);}}}
else{throw new Error('removeClass called with invalid arguments');}
return ret;},replaceClass:function(el,classObj){var className,from,to,ret=false,current;if(el&&classObj){from=classObj.from;to=classObj.to;if(!to){ret=false;}
else{if(!from){ret=this.addClass(el,classObj.to);}
else{if(from!==to){current=this.getAttribute(el,CLASS_NAME)||EMPTY;className=(SPACE+current.replace(this.getClassRegex(from),SPACE+to)).split(this.getClassRegex(to));className.splice(1,0,SPACE+to);this.setAttribute(el,CLASS_NAME,this.trim(className.join(EMPTY)));ret=true;}}}}
else{throw new Error('replaceClass called with invalid arguments');}
return ret;},setAttribute:function(el,attr,val){attr=CUSTOM_ATTRIBUTES[attr]||attr;el.setAttribute(attr,val);},getAttribute:function(el,attr){attr=CUSTOM_ATTRIBUTES[attr]||attr;return el.getAttribute(attr);},getClassRegex:function(className){var re;if(className!==undefined){if(className.exec){re=className;}
else{re=reCache[className];if(!re){className=className.replace(patterns.CLASS_RE_TOKENS,'\\$1');re=reCache[className]=new RegExp(C_START+className+C_END,_G);}}}
return re;},getElByClassName:function(className,tag,rootId){var elems=[],i,tempCnt=this.getEl(rootId).getElementsByTagName(tag),len=tempCnt.length;for(i=0;i<len;++i){if(this.hasClass(tempCnt[i],className)){elems.push(tempCnt[i]);}}
if(elems.length<1){return false;}
else{return elems;}},fadeUp:function(elem){if(elem){var level=0,fade=function(){var timer=null;level+=0.05;if(timer){clearTimeout(timer);timer=null;}
if(level>1){taMenu.setOpacity(elem,1);return false;}
else{taMenu.setOpacity(elem,level);}
timer=setTimeout(fade,50);};fade();}},moveElement:function(element,finalX,finalY,speed){var elem=this.isString(element)?this.getEl(element):element,style=null,xpos=0,ypos=0,dist=0,that=this;if(elem){if(elem.movement){clearTimeout(elem.movement);}
if(!this.getStyle(elem,'left')){this.setStyle(elem,'left',0);}
if(!this.getStyle(elem,'top')){this.setStyle(elem,'top',0);}
xpos=parseInt(this.getStyle(elem,'left'),10);ypos=parseInt(this.getStyle(elem,'top'),10);if(xpos===finalX&&ypos===finalY){return true;}
if(xpos<finalX){dist=Math.ceil((finalX-xpos)/10);xpos+=dist;}
else{if(xpos>finalX){dist=Math.ceil((xpos-finalX)/10);xpos-=dist;}}
if(ypos<finalY){dist=Math.ceil((finalY-ypos)/10);ypos+=dist;}
else{if(ypos>finalY){dist=Math.ceil((ypos-finalY)/10);ypos-=dist;}}
this.setStyle(elem,'left',(xpos+'px'));this.setStyle(elem,'top',(ypos+'px'));elem.movement=setTimeout(function(){that.moveElement(element,finalX,finalY,speed);},speed);}},getEvent:function(e,boundEl){var ev=e||window.event;if(!ev){var c=this.getEvent.caller;while(c){ev=c.arguments[0];if(ev&&Event==ev.constructor){break;}
c=c.caller;}}
return ev;},on:function(el,sType,fn,obj,overrideContext,bCapture){var oEl=null,context=null,wrappedFn=null,that=this;if(this.isString(el)){el=this.getEl(el);}
if(!el||!fn||!fn.call){return false;}
bCapture=bCapture||false;context=el;if(overrideContext){if(overrideContext===true){context=obj;}
else{context=overrideContext;}}
wrappedFn=function(e){return fn.call(context,that.getEvent(e,el),obj);};try{if(window.addEventListener){el.addEventListener(sType,wrappedFn,bCapture);}
else{if(window.attachEvent){el.attachEvent('on'+sType,wrappedFn);}
else{el['on'+sType]=wrappedFn;}}}
catch(e){return false;}},stopEvent:function(evt){this.stopPropagation(evt);this.preventDefault(evt);},stopPropagation:function(evt){if(evt.stopPropagation){evt.stopPropagation();}
else{evt.cancelBubble=true;}},preventDefault:function(evt){if(evt.preventDefault){evt.preventDefault();}
else{evt.returnValue=false;}},taMenu:function(){var i,len=arguments.length,menus=[];for(i=0;i<len;++i){menus[i]=new menuView(arguments[i]);menus[i].init();}
return menus;}}}();var menuView=function(oConfigs){this.tabRoot=taMenu.isString(oConfigs.tabRoot)?taMenu.getEl(oConfigs.tabRoot):(oConfigs.tabRoot||null);this.menus=taMenu.isString(oConfigs.menus)?this.tabRoot.getElementsByTagName(oConfigs.menus):(oConfigs.menus||null);this.contents=taMenu.isString(oConfigs.contents)?this.tabRoot.getElementsByTagName(oConfigs.contents):(oConfigs.contents||null);if(!this.menus||!this.contents){return false;}
this.length=this.menus.length||0;this.defaultIndex=oConfigs.defaultIndex||0;this.lastIndex=this.defaultIndex;this.lastMenu=this.menus[this.lastIndex]||null;this.lastContent=this.contents[this.lastIndex]||null;this.evtName=oConfigs.evt||'mouseover';this.evtMouseName='';if($.browser.msie){this.evtMouseName='mouseleave';}else{this.evtMouseName='mouseout';}
this.defaultClass=oConfigs.defaultClass||this.CURRENT_TAB_CLASS;this.previousClass=oConfigs.previousClass||'';this.hideAll=oConfigs.hideAll||false;this.auto=oConfigs.auto||false;this.autoSpeed=oConfigs.autoSpeed||6000;this.fadeUp=oConfigs.fadeUp||false;this.scroll=oConfigs.scroll||false;this.scrollId=oConfigs.scrollId||null;this.scrollSpeed=oConfigs.scrollSpeed||5;this.direction=oConfigs.direction||'V';this.activeTag=oConfigs.activeTag||'IMG';this.stepHeight=oConfigs.stepHeight||0;this.stepWidth=oConfigs.stepWidth||0;this.ajax=oConfigs.ajax||false;this.ajaxDefaultInfo=this.contents.innerHTML;this.aPath=oConfigs.aPath||'';};menuView.prototype.timer=null;menuView.prototype.isPause=false;menuView.prototype.CURRENT_TAB_CLASS='current',menuView.prototype.init=function(){var i,that=this;if(this.menus&&this.contents){if(this.auto){this.timer=setTimeout(function(){that.autoChange();},that.autoSpeed);}
if(!this.hideAll){taMenu.addClass(this.lastMenu,this.defaultClass);if(!this.ajax&&!this.scroll){if(this.lastContent){this.lastContent.style.display='block';}}
if(this.scroll){this.scrollCnt((this.lastContent||this.contents),this.defaultIndex);}}
else{taMenu.removeClass(this.lastMenu,this.defaultClass);}
for(i=0;i<this.length;++i){if(i!==this.defaultIndex){taMenu.removeClass(this.menus[i],this.CURRENT_TAB_CLASS);if(!this.ajax&&!this.scroll){this.contents[i].style.display='none';}}
taMenu.on(this.menus[i],this.evtName,function(index){return function(event){var evt=null,curClass=(this.menus[index]==this.menus[this.defaultIndex])?this.defaultClass:this.CURRENT_TAB_CLASS;if(!taMenu.hasClass(this.menus[index],curClass)){var currentContent=(this.ajax||(this.scroll&&(this.stepHeight||this.stepWidth)))?this.contents:(this.contents[index]||null);this.setCurrent(currentContent,index);this.lastIndex=index;}
if(this.auto){this.isPause=true;}
evt=event||window.event;taMenu.stopEvent(evt);}}(i),this.menus[i],that);taMenu.on(this.menus[i],this.evtMouseName,function(index){return function(){var curMenu=this.menus[index];if(this.hideAll&&this.evtName=='mouseover'){if(this.lastMenu==curMenu){taMenu.removeClass(curMenu,(taMenu.hasClass(curMenu,that.defaultClass)?this.defaultClass:this.CURRENT_TAB_CLASS));}
if(this.previousClassMenu){taMenu.removeClass(this.previousClassMenu,this.previousClass);}
if(!this.scroll&&!this.ajax){this.contents[index].style.display='none';}}
else{if(this.auto){this.isPause=false;}}}}(i),this.menus[i],that);taMenu.on(this.menus[i],'click',function(index){return function(){var curMenu=this.menus[index];if(this.hideAll&&this.evtName=='mouseover'){if(this.lastMenu==curMenu){taMenu.removeClass(curMenu,(taMenu.hasClass(curMenu,that.defaultClass)?this.defaultClass:this.CURRENT_TAB_CLASS));}
if(this.previousClassMenu){taMenu.removeClass(this.previousClassMenu,this.previousClass);}
if(!this.scroll&&!this.ajax){this.contents[index].style.display='none';}}
else{if(this.auto){this.isPause=false;}}}}(i),this.menus[i],that);}}};menuView.prototype.autoChange=function(){var that=this;if(!this.isPause){var currentContent=null,currentMenu=null;if(this.timer){clearTimeout(this.timer);this.timer=null;}
this.lastIndex=this.lastIndex+1;if(this.lastIndex===this.length){this.lastIndex=0;}
currentContent=this.ajax?this.contents:(this.contents[this.lastIndex]||null);this.setCurrent(currentContent,this.lastIndex);this.timer=setTimeout(function(){that.autoChange();},this.autoSpeed);}
else{this.timer=setTimeout(function(){that.autoChange()},this.autoSpeed);return false;}};menuView.prototype.setCurrent=function(curCnt,index){var activeObj=null;curMenu=this.menus[index];taMenu.removeClass(this.lastMenu,(taMenu.hasClass(this.lastMenu,this.defaultClass)?this.defaultClass:this.CURRENT_TAB_CLASS));if(curMenu===this.menus[this.defaultIndex]){taMenu.addClass(curMenu,this.defaultClass);}
else{taMenu.addClass(curMenu,this.CURRENT_TAB_CLASS);}
if(this.previousClass){if(this.previousClassMenu){taMenu.removeClass(this.previousClassMenu,this.previousClass);}
if(index!==0){taMenu.addClass(this.menus[index-1],this.previousClass);if((index-1)===this.defaultIndex){taMenu.removeClass(this.menus[index-1],this.defaultClass);}
this.previousClassMenu=(this.menus[index-1]);}}
if(!this.scroll&&!this.ajax){if(this.lastContent){this.lastContent.style.display="none";}
if(curCnt){curCnt.style.display="block";}}
if(this.fadeUp){activeObj=(curCnt.tagName.toUpperCase()==='IMG')?curCnt:curCnt.getElementsByTagName('img')[0];if(this.lastContent!==curCnt){taMenu.fadeUp(activeObj);}}
else{if(this.scroll){this.scrollCnt(curCnt,index);}}
this.lastMenu=curMenu;};