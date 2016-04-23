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
	<link href="/resources/website/css/login.css" rel="stylesheet" type="text/css" />
   <!--主要内容区-->
  <div class="register_success text_center">
    <p class="f30"><img src="/resources/website/img/register_success.png" class="mr20" />注册成功</p>
    <p class="f14 color_66 mt70">您已经注册成为咨询汇普通用户，请继续完善资料申请成为专家.</p>
    <P class="mt70">
      <a href="/beExport" class="apply_btn f16 color_ff">申请成为专家</a>
    </P>
   <!--  <P class="mt20">
      <a href="/login" class="back_btn f16 color_ff">返回登录</a>
    </P> -->
  </div>
  <!--底部-->
   <%@ include file="/WEB-INF/website/views/common/footer.jsp"%>

<script src="/resources/website/js/login.js"></script>
</body>
</html>
