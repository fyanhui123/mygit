/**
 * @preserve jquery.tianan.Component 1.0 beta1
 * $Date: 2013-02-19 $
 * $author: lianchao $
 * $Rev: 1.05 $
 *   
 */
(function($){$.taBlockUI={message:"请求处理中，请稍候.",active:function(){$.blockUI({message:$.taBlockUI.message,showOverlay:true,css:{border:'none',padding:'15px',backgroundColor:'#000','-webkit-border-radius':'10px','-moz-border-radius':'10px',color:'#fff'},overlayCSS:{backgroundColor:'#b3b3b3'}})},inactive:function(){$.unblockUI();},setMessage:function(msg){$.taBlockUI.message=msg;}}})(jQuery);