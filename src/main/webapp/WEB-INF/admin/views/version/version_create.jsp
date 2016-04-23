<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp"%>
<!doctype html>
<html class="no-js">
<head>
  <meta charset="UTF-8">
  <title>版本添加</title>
  <link href="/resources/admin/css/base.css" rel="stylesheet" type="text/css" />
<link href="/resources/admin/css/sys.css" rel="stylesheet" type="text/css" />
  <%@ include file="/WEB-INF/admin/views/common/header.jsp"%>
     <style>#container{ width: 800px;}</style>
</head>
<body>
<input type="hidden" id="activeMenu" value="createVersion">
<table cellpadding="0" cellspacing="0" class="warpper">
   <tr>
      <td width="199" class="left text_center" valign="top">
      <%@ include file="/WEB-INF/admin/views/common/left.jsp"%>
      </td>
      <td class="right" valign="top">
         <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
         
         <!-- main --start -->
         <p class="main mt20"><a href="">平台管理</a> > <a href="">版本管理</a> > 添加版本</p>
         <div class="main">
            <form method="post" action="/admin/version/create" enctype="multipart/form-data">
            	 <input  hidden="true"  id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
               <table cellspacing="0" cellpadding="0" width="100%" class="form_table">
                  <tr>
                     <td width="160" class="text_right">版本号：</td>
                     <td><input type="text" name="name" id="version" class="fany_input ml10" /></td>
                  </tr>
                  <tr>
                     <td width="160" class="text_right">安卓包:</td>
                     <td><input type="file" name="new_apk" id="package" class="ml10" /></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right">是否强制更新:</td>
                     <td><input type="radio" name="forceUpdate" value="true"/>是 <input type="radio" name="forceUpdate" checked value="false"/>否</td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right">APP版本更新介绍：</td>
                     <td><textarea class="td_info w90 ml10" name="description" id="summary"></textarea></td>
                  </tr>
                  <tr>
                     <td width="150" class="text_right"></td>
                     <td><a href="javascript:" class="fany_btn f14 color_ff ml10">设置</a></td>
                  </tr>
               </table>
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
	  var cw=$(window).height();
	   $(".warpper").css("min-height",cw-2);
	   
	   //提交版本设置
	      $(".fany_btn").click(function(){
	         var version=$.trim($("#version").val());
	         var package=$.trim($("#package").val());
	         var summary=$.trim($("#summary").val());
	         if(version==""){
	            $.dialog({type:2,text:"请输入版本号"});
	            return false;
	         }
	         if(package==""){
	            $.dialog({type:2,text:"请选择安装包"});
	            return false;
	         }
	         if(summary==""){
	            $.dialog({type:2,text:"请输入版本更新介绍"});
	            return false;
	         }
	         $("form").submit();
	      });
  });

</script>

</body>
</html>
