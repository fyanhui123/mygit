<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false" %>
<%@ include file="/common/taglib.jspf"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head><meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8">
        <!-- 加载自定义js -->
        <script type="text/javascript" src="${ctx}/pages/common/js/main.js"></script>
        
        <script type="text/javascript">     
        $(document).ready(function() {
            $.taHash.set("ctx","${ctx}");
            page.init();
        });
    </script>
</head>
<body>
    <!-- 顶部可收缩块 -->
    <div id="northHead" class="ui-layout-north page-head" style="overflow:hidden;">
        <%@ include file="header.jsp" %> 
         <div id="accordion-west">
            <div id="taMenu"></div>
        </div>
        
    </div>
    <div id="main-center" class="ui-layout-center main-center"> 
       <!-- TAB页 -->
       <div id="tabs-center" class="inner-center ui-corner-none"> 
           <!-- centerTabsLayout -->
           <ul >
             <li><a href="#tab-center-1" >欢迎页</a></li>
           </ul>
           <div id="tab-center-1">
             <%@ include file="welcome.jsp" %>
           </div>
       </div>
       <!-- 底部可收缩块 -->
       <div id="bottom-message-bar" class="ui-layout-south getbottom"><div class="gettxt">Powered by TianFrame © 2013 天安财产保险股份有限公司版权所有</div> </div>
    </div>  
</body>
</html>