/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.05 $
 *   
 */
(function($){$.extend($.validator.messages,{noBlankSpace:"不能包含空格",nochinese:"请输入非中文字符",required:"必填字段",remote:"请修正该字段",email:"请输入正确格式的电子邮件",url:"请输入合法的网址",date:"请输入合法的日期",dateISO:"请输入合法的日期 (ISO).",number:"请输入合法的数字",digits:"只能输入整数",creditcard:"请输入合法的信用卡号",equalTo:"请再次输入相同的值",accept:"请输入拥有合法后缀名的字符串",maxlength:$.validator.format("长度最多是 {0} 的字符串"),maxByteLen:$.validator.format("长度最多是 {0} 字节的字符串,一个汉字为3个字节"),minlength:$.validator.format("长度最少是 {0} 的字符串"),rangelength:$.validator.format("长度介于 {0} 和 {1} 之间的字符串"),range:$.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),max:$.validator.format("请输入一个最大为 {0} 的值"),min:$.validator.format("请输入一个最小为 {0} 的值"),rangeNum:$.validator.format("请输入一个介于 {0} 和 {1} 之间的值"),maxNum:$.validator.format("请输入一个最大为 {0} 的值"),minNum:$.validator.format("请输入一个最小为 {0} 的值"),alphanumeric:"字母、数字、下划线",lettersonly:"必须是字母",phone:"请输入正确格式的电话"});})(jQuery);