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
            //document.getElementById("accordion-west").style.height = document.documentElement.clientHeight-12;
            //document.getElementById("tabs-center").style.height = document.documentElement.clientHeight+1200;
            page.init();
            taMenu.taMenu({
                menus: taMenu.getElByClassName('ta-menu', 'li', 'taMenu-side'),
                contents: taMenu.getElByClassName('ta-submnu', 'ul', 'taMenu-side'),
                hideAll: true
            });
        });
    </script>
</head>
<body>
    <!-- 顶部可收缩块 -->
    <div id="northHead" class="ui-layout-north page-head" style="overflow:hidden;">
        <%@ include file="header.jsp" %> 
         <div id="accordion-west">
            <%@ include file="leftaccordion.jsp" %>
        </div>
        
    </div>
    <!-- 左侧可收缩块 -->
   <%--  <div id="leftWest" class="ui-layout-west no-scrollbar add-padding"> 
          <!-- 左侧滑动框 -->
          <div id="accordion-west">
                <%@ include file="leftaccordion.jsp" %>
          </div>
    </div> --%>
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
       <div id="bottom-message-bar" class="ui-layout-south getbottom"><div class="gettxt">Powered by TF © 2013 天安财产保险股份有限公司版权所有.</div> </div>
    </div>  
</body>
</html>