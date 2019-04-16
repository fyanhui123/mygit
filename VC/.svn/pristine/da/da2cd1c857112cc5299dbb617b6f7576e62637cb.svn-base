/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.05 $
 *   
 */
(function($){$.validator.addMethod("isIdCardNo",function(value,element){return this.optional(element)||/^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$/.test(value)||/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/.test(value);},"请正确输入您的身份证号码");$.validator.addMethod("isMobile",function(value,element){var length=value.length;var mobile=/^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;return this.optional(element)||(length==11&&mobile.test(value));},"请正确填写您的手机号码");$.fn.taValidator=function(options){var opts=$.extend({},$.fn.taValidator.defaults,options);return this.each(function(){var $this=$(this);var o=$.meta?$.extend({},this.opts,$this.data()):opts;$(this).validate(o);});}
$.fn.taValidator.defaults={errorPlacement:function(error,element){error.appendTo(element.parent());},errorLabelContainer:"#errormsg",wrapper:'li'};})(jQuery);