<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="right_top clearfix">
       <ul class="right_nav fr">
          <li class="fl color_ff mr20"><!-- <sec:authentication property="name"/> -->${sessionScope.staff.username}，欢迎登录问堂后台管理系统</li>
          <li class="fl"><a href="/admin/consumer/resetPwd?consumerId=${sessionScope.staff.id}" class="color_ff">修改密码</a></li>
          <li class="fl"><a id="logout_btn"  href="#" class="color_ff" >退出登录</a></li>
       </ul>
 </div>
 
 <form id="logout_form" action="/admin/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
