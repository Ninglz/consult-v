<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>新闻详情</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" name="viewport">
<link href="/resources/website/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/website/css/newsdetail_app.css" rel="stylesheet" type="text/css" />
</head>

<body>
<div class="wapper">
	<p class="title">${news.title }</p>
	<p class="clearfix times"><span class="fl color_979 f14">${news.lastModified}</span>
	<span class="fr color_979 f14"><img src="/resources/website/img/news_type.png" width="15" class="mr5" />${news.industryName }</span></p>
	<p class="title_img"><img src="${news.titleImg }" width="100%" /></p>
	<!--div里放详细-->
	<div class="main mt20 f14">
		${news.content }
	</div>
</div>
</body>
</html>
