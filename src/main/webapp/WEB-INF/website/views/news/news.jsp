<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>咨询汇</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" name="viewport">
</head>

<body>
<div class="wapper">
  <form action="/news/detail" method="post" id="detail_form">
	<input type="hidden" name="id" id="detail_id" />
	<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
  </form>
   <!--头部-->
	<%@ include file="/WEB-INF/website/views/common/header.jsp"%>
	<link href="/resources/website/css/news.css" rel="stylesheet" type="text/css" />
   <!--主要内容区-->
  <div class="main">
    <p class="news_type clearfix">
      <span class="fl news_type_fl">
        <a href="javascript:" data-id="" class="current"  data-id="all">全部</a>
      </span>
      <a href="javascript:" class="news_sort fr">时间排序</a>
    </p>
    <ul class="news_list clearfix">
      
    </ul>
    <!--页码-->
    <div class="page mt20" id="pagenum">
      <a href="" class=""><i class="icon_prev"></i></a>
      <a href="" class="pagenum">1</a>
      <a href="" class="pagenum current">2</a>
      <a href="" class="pagenum">3</a>
      <span class="numoff">...</span>
      <a href="" class="pagenum">10</a>
      <a href="" class=""><i class="icon_next"></i></a>
    </div>
  </div>

  <!--底部-->
   <%@ include file="/WEB-INF/website/views/common/footer.jsp"%>

<script src="/resources/website/js/login.js"></script>
<script src="/resources/website/js/page.js"></script>
<script src="/resources/website/js/news.js"></script>
</body>
</html>
