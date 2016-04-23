<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>后台管理系统登录</title>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=no" id="viewport" name="viewport">
<link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys_login.css" rel="stylesheet" type="text/css" />

</head>

<body>
<div class="warpper">
   <p class="text_center"><img src="/resources/admin/img/logo_login.png" /></p>
   <form method="post" action="/admin/login">
   				<p class="text-muted text-center">
                    <!--Enter your username and password-->
                    
                </p>
      <table cellpadding="0" cellspacing="0" width="100%" class="mt70" id="login_div">
         <tr>
            <td class="f16 color_ff text_right">用户名：</td>
            <td><input type="text" class="input_txt color_33 f16" data-num="1" name="username" id="username" onkeydown="enterLogin()" /></td>
         </tr>
         <tr>
            <td class="f16 color_ff text_right">密码：</td>
            <td><input type="password" class="input_txt color_33 f16" data-num="2" name="password" id="password" onkeydown="enterLogin()" /></td>
         </tr>
         <tr>
            <td class="f16 color_ff text_right"></td>
            <td class="text_center color_eb f16" >
					<span id="input_tip"></span>		 
					<c:if test="${not empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
                        <span class="error">用户名密码错误</span>
                    </c:if>
                    <%-- <c:if test="${empty sessionScope.SPRING_SECURITY_LAST_EXCEPTION}">
                        <span>请输入用户名和密码</span>
                    </c:if>           --%> 
            </td>
         </tr>
         <tr>
            <td class="f16 color_ff text_right"></td>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <td><a href="javascript:" class="input_txt color_ff login_btn f16">登录</a></td>
            
                
         </tr>
      </table>
   </form>
</div>
<script src="/resources/admin/js/common/js/jquery-1.7.1.min.js"></script>
<script src="/resources/admin/js/common/js/jquery.longyuJs.js"></script>
<script src="/resources/admin/js/common/js/jquery.dialog.js"></script>
<script src="/resources/admin/js/login_s.js"></script>
</body>
</html>
