/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.05 $
 *   
 */
(function($){if($.validator){$.validator.addMethod("noBlankSpace", function(value, element) { var reg = /^[\S]+$/g; return this.optional(element) || (reg.test(value));}, "Please enter no BlankSpace."); $.validator.addMethod("nochinese", function(value, element) { var tel = /^[^\u4e00-\u9fa5]+$/; return this.optional(element) || (tel.test(value));}, "Please enter no Chinese character.");$.validator.addMethod("alphanumeric",function(value,element){return this.optional(element)||/^\w+$/i.test(value);},"Letters, numbers or underscores only please");$.validator.addMethod("lettersonly",function(value,element){return this.optional(element)||/^[a-z]+$/i.test(value);},"Letters only please");$.validator.addMethod("phone",function(value,element){return this.optional(element)||/^[0-9 \(\)]{7,30}$/.test(value);},"Please specify a valid phone number");$.validator.addMethod("postcode",function(value,element){return this.optional(element)||/^[0-9 A-Za-z]{5,20}$/.test(value);},"Please specify a valid postcode");$.validator.addMethod("date",function(value,element){value=value.replace(/\s+/g,"");if(String.prototype.parseDate){var $input=$(element);var pattern=$input.attr('dateFmt')||'yyyy-MM-dd';return!$input.val()||$input.val().parseDate(pattern);}else{return this.optional(element)||value.match(/^\d{4}[\/-]\d{1,2}[\/-]\d{1,2}$/);}},"Please enter a valid date.");$.validator.addMethod("customvalid",function(value,element,params){try{return eval('('+params+')');}catch(e){return false;}},"Please fix this field.");$.validator.addClassRules({noBlankSpace:{noBlankSpace:true},date:{date:true},alphanumeric:{alphanumeric:true},lettersonly:{lettersonly:true},phone:{phone:true},postcode:{postcode:true}});$.validator.setDefaults({errorElement:"span"});$.validator.autoCreateRanges=true;}})(jQuery);