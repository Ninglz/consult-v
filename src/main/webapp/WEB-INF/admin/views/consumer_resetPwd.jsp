<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>用户设置</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
</head>
<body>
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
         <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
    	 <jsp:param name="activeMenu" value="consumer" />
  		</jsp:include> 
      </td>
      <td class="right" valign="top">
         <div class="right_top clearfix">
            <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         </div>
         <p class="main mt20"><a href="">平台管理</a> > 修改密码</p>
         <div class="main">
            <form action="/admin/consumer/doResetPwd" method="post" id="password_form">
            	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <input type="hidden" name="consumerId" value="${consumerId}"/>
               <table cellspacing="0" cellpadding="0" width="100%" class="form_table">
                  <tr>
                     <td width="150" class="text_right">新密码：</th>
                     <td><input type="text" name="password" id="" class="fany_input ml10" "/></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right">确认密码：</th>
                     <td><input type="text" name="confirmPassword" id="" class="fany_input ml10" "/></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right"></td>
                     <td><a href="javascript:" class="fany_btn f14 color_ff ml10" onClick="submit()">设置</a></td>
                  </tr>
               </table>
            </form>
         </div>
        <div class="footer text_center">&copy;copyright 2015 问堂有限公司  版权所有</div>
      </td>
   </tr>
</table>
<script src="/resources/admin/js/common/js/jquery-1.7.1.min.js"></script>
<script src="/resources/admin/js/common/js/jquery.longyuJs.js"></script>
<script src="/resources/admin/js/common/js/jquery.dialog.js"></script>
<script src="/resources/admin/js/nav.js"></script>
<script>
   $(function(){
	   var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
   });
   function submit(){
	   var int=$.trim($("input[name='password']").val());
		if(int==""){
			$.dialog({type:2,text:"请输入新密码"});
			return false;
		}
	   var int1=$.trim($("input[name='confirmPassword']").val());
		if(int1==""){
			$.dialog({type:2,text:"请输入确认密码"});
			return false;
		}
	   $("#password_form").submit();
   }
</script>
</body>
</html>
