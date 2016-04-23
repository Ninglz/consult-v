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
  <div class="login">
    <p class="title">注册</p>
    <P class="mt40 f14">手机号</P>
    <P class="mt10"><input type="text" class="login_input" name="cellNo" id="cellNo" /></P>
    <P class="mt30 f14">验证码</P>
    <P class="mt10"><input type="text" class="login_input w60" name="code" id="code" /><span class="code_second f14" style="display: none;"><i>59</i>秒后重获</span><a href="javascript:" class="code_btn f14"><img src="/resources/website/img/code_img.jpg" class="mr5" />获取验证码</a></P>
    <P class="mt30 f14">登录密码</P>
    <P class="mt10"><input type="password" class="login_input" name="password" id="password" /></P>
    <P class="mt30 f14">确认密码</P>
    <P class="mt10"><input type="password" class="login_input" name="confirmPassword" id="confirmPassword" /></P>
    <P class="mt30">
      <a href="javascript:" class="register_btn f16 color_ff">注册</a>
    </P>
    <P class="mt50 f14">
      已创建账号？立即<a href="" class="color_4fd">登录</a>
    </P>
  </div>
  <!--底部-->
   <%@ include file="/WEB-INF/website/views/common/footer.jsp"%>

<script src="/resources/website/js/login.js"></script>
</body>
</html>
