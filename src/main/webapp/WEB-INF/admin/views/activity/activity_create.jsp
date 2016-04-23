<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>新闻添加</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
     <style>#container{ width: 800px;}</style>
</head>
<body>
<input type="hidden" id="activeMenu" value="createActivity">
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         
         <!-- main --start -->
         <p class="main mt20"><a href="">平台管理</a> > <a href="">活动管理</a> > 添加活动</p>
         <div class="main">
            <form method="post" action="/admin/activity/create" enctype="multipart/form-data" id="activity_form">
               <input  hidden="true"  id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
               <p class="f14 color_33 mt30">名称：<input type="text" class="td_input w650" name="name" id="name" /></p>
               <div class="f14 color_33 mt30 clearfix"><span class="fl mt30">图片：<input type="file" name="img"  id="img" /></span><div class="fl" id="img_show"><img src="" /></div></div>
               <p class="f14 color_33 mt30">简介：<textarea class="td_info w90" name="describe" id="describe"></textarea></p>
               <p class="f14 color_33 mt30">链接：<input type="text" class="td_input w650" name="url" id="url" /></p>
               <p class="f14 color_33 mt30"><a href="javascript:" class="del_btn color_ff f14" id="sub_activity">发布</a></p>
            </form>
         </div>

         <!-- main --end -->
        <%@ include file="/WEB-INF/admin/views/common/footer.jsp"%>
        <script src="/resources/admin/js/add_news_s.js"></script>
         <script src="/resources/admin/js/common/js/uploadPreview.js"></script>
        <script src="/resources/admin/js/nav.js"></script>
      </td>
   </tr>
</table>


<script>
  $(document).ready(function() {
	  var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
  });


</script>

</body>
</html>
