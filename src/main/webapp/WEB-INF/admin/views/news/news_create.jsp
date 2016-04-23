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
  <%response.setHeader("X-Frame-Options", "SAMEORIGIN"); %>
     <style>#container{ width: 800px;}</style>
</head>
<body>
<input type="hidden" id="activeMenu" value="createNews">
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         
         <!-- main --start -->
         <p class="main mt20"><a href="">平台管理</a> > <a href="">新闻管理</a> > 添加新闻</p>
         <div class="main">
            <form id="news_form" method="post" action="/admin/news/create" enctype="multipart/form-data">
            	<input  hidden="true"  id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
               <p class="f14 color_33 mt30">标题：<input type="text" class="td_input w650" maxlength=15 name="title" id="title" />
               <span class="color_ee3 ml10">最多可输入15个字</span></p>
               <p class="f14 color_33 mt30">
               	行业：<select name="industryId" id="industry" class="fany_input ml10">
	                  <option value="" selected="selected">请选择行业</option>
	                  <c:forEach items="${industryList}" var="list">
	                  	  <option value="${list.id}">${list.name}</option>
	                  </c:forEach>
	               </select>
            </p>
<!--                <p class="f14 color_33 mt30">图片：<input type="file"name="img"  id="img" /></p> -->
               <div class="f14 color_33 mt30 clearfix"><span class="fl mt30">图片：<input type="file"name="img"  id="img" /></span><div class="fl" id="img_show"><img src="" /></div></div>
               
               <p class="f14 color_33 mt30">摘要：<textarea class="td_info w90"  maxlength=80 name="summary" id="summary"></textarea>
               <span class="color_ee3 ml10">最多可输入80个字</span></p>
               <div class="f14 color_33 mt30">内容：<input type="hidden" name="content" id="content">
                  <!-- 加载编辑器的容器 -->
                  <script id="container" name="contents" type="text/plain"></script>
                  <!-- 实例化编辑器 -->
                  <!-- umeditor -->
				<script src="/resources/admin/js/umeditor1_2_2-utf8-jsp/umeditor.config.js"></script>
				<script src="/resources/admin/js/umeditor1_2_2-utf8-jsp/umeditor.min.js"></script>
                  <script type="text/javascript">
                     var um = UM.getEditor('container');
                  </script>
               </div>
               <p class="f14 color_33 mt30"><a href="javascript:" class="del_btn color_ff f14" id="sub_btn">发布</a></p>
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
	  /* CKEDITOR.replace("ckeditor", {
          filebrowserImageUploadUrl: "/admin/news/img/upload?${_csrf.parameterName}=${_csrf.token}&newsId=${newsForm.id}"
      })*/
      window.UMEDITOR_CONFIG.imageUrl="/admin/news/img/upload?${_csrf.parameterName}=${_csrf.token}";
	  /* window.UMEDITOR_CONFIG.replace("ckeditor", {
		  imageUrl: "/admin/news/img/upload?${_csrf.parameterName}=${_csrf.token}"
      })  */
    
	  var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
    
    
  });


</script>

</body>
</html>
