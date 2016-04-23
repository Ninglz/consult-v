<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
  <div class="login">
    <p class="title">登录</p>
    <form action="" method="post">
    <P class="mt40 f14">手机号</P>
    <P class="mt10"><input type="text" class="login_input" name="cellNo" id="cellNo" /></P>
    <P class="mt30 f14">密码</P>
    <P class="mt10"><input type="password" class="login_input" name="password" id="password" /></P>
    <P class="mt30">
      <a href="javascript:" class="login_btn f16 color_ff">登录</a>
      <a href="" class="f16 color_4fd ml10">忘记密码？</a>
    </P>
    </form>
    <P class="mt50 f14">
      还未创建账号？立即<a href="register" class="color_4fd">注册</a>
    </P>
  </div>
  <!--底部-->
  <%@ include file="/WEB-INF/website/views/common/footer.jsp"%>
  <script src="/resources/website/js/login.js"></script>
</body>
</html>

