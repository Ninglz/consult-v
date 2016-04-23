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
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
   <!--头部-->
	<%@ include file="/WEB-INF/website/views/common/header.jsp"%>
	<link href="/resources/website/css/news.css" rel="stylesheet" type="text/css" />
   <!--主要内容区-->
  <div class="main">
    <p class="news_type clearfix">
      <!-- <span class="fl news_type_fl">
        <a href="javascript:" data-id="all">全部</a>
        <a href="javascript:" class="current" data-id="0">互联网</a>
        <a href="javascript:" data-id="1">金融</a>
        <a href="javascript:" data-id="2">教育学习</a>
        <a href="javascript:" data-id="3">健身营养</a>
      </span> -->
      <a href="javascript:history.back(-1)" class="fr color_4fd mt5"><img src="/resources/website/img/news_back_tip.jpg" class="mr5" />返回列表</a>
    </p>
    <p class="bordert_ff"></p>
    <div class="news_detail">
      <p class="text_center title">${news.title }</p>
      <p class="color_979 mt5">
        <span class="">${news.lastModified }</span>
        <span class="ml20"><img src="/resources/website/img/news_type_tip.jpg" class="mr5" />${news.industryName}</span>
      </p>
      <p class="text_center img"><img src="${news.titleImg}" /></p>
      <div class="summary">
        <img src="/resources/website/img/news_summary_tip.jpg" class="news_summary_tip" />
        <img src="/resources/website/img/news_summary_tip2.jpg" class="news_summary_tip2" />
        <p>${news.digest }</p>
      </div>
      <div class="content">
        ${news.content }
      </div>
    </div>
  </div>
  <!--底部-->
   <%@ include file="/WEB-INF/website/views/common/footer.jsp"%>

<script src="/resources/website/js/login.js"></script>
<script src="/resources/website/js/page.js"></script>
<script src="/resources/website/js/news.js"></script>
</body>
</html>
