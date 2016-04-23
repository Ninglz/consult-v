<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>403</title>
    <meta name="msapplication-TileColor" content="#5bc0de" />
    <meta name="msapplication-TileImage" content="assets/img/metis-tile.png" />

    <!-- Bootstrap -->
    <link rel="stylesheet" href="/resources/admin/lib/bootstrap/css/bootstrap.min.css">

    <!-- Font Awesome -->
    <link rel="stylesheet" href="/resources/admin/lib/font-awesome/css/font-awesome.min.css">

    <!-- Metis core stylesheet -->
    <link rel="stylesheet" href="/resources/admin/css/main.css">
</head>
<body class="error">
<div class="container">
    <div class="col-lg-8 col-lg-offset-2 text-center">
        <div class="logo">
            <h1>403</h1>
        </div>
        <p class="lead text-muted">您的访问已被禁止，您没有访问此功能的权限!</p>
        <div class="clearfix"></div>
        <div class="col-lg-6 col-lg-offset-3">
            <form action="index.html">
                <div class="input-group">
                    <input type="text" placeholder="search ..." class="form-control">
              <span class="input-group-btn">
			<button class="btn btn-default" type="button"><i class="fa fa-search"></i></button>
		      </span>
                </div>
            </form>
        </div>
        <div class="clearfix"></div>
        <br>
        <div class="col-lg-6  col-lg-offset-3">
            <div class="btn-group btn-group-justified">
                <a href="/admin/signed" class="btn btn-info">返回首页</a>
                <a href="javascript:loginout();" class="btn btn-warning">退出</a>

            </div>
        </div>
    </div><!-- /.col-lg-8 col-offset-2 -->
</div>
<form id="logout_form" action="/admin/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
</body>
</html>
<!--jQuery 2.1.1 -->
<script src="/resources/admin/lib/jquery/jquery.min.js"></script>
<script>
    function loginout(){
        $("#logout_form").submit();
    }
</script>
