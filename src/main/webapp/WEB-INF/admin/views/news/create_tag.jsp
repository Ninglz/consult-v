<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/16
  Time: 16:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>关键字</title>
    <%@ include file="/WEB-INF/admin/views/common/header.jsp" %>
</head>
<body>
<div class="bg-light lter" id="wrap">
<%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
<%@ include file="/WEB-INF/admin/views/common/left.jsp" %>
<input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>
<div id="content">
<div class="outer">
<div>
    <ul class="breadcrumb">
        <li>
            <a href="#">关键字</a>
        </li>
    </ul>
</div>
<div class="inner bg-light lter">
<div class="row">
    <div class="col-lg-12">
        <div class="box">
            <header>
                <div class="icons">
                    <i class="fa fa-th"></i>
                </div>
                <h5>基本信息</h5>

                <!-- .toolbar -->
                <div class="toolbar">
                    <nav style="padding: 8px;">
                        <a class="btn btn-default btn-xs collapse-box" href="javascript:;">
                            <i class="fa fa-minus"></i>
                        </a>
                        <a class="btn btn-default btn-xs full-box" href="javascript:;">
                            <i class="fa fa-expand"></i>
                        </a>
                    </nav>
                </div>
                <!-- /.toolbar -->
            </header>
            <div class="body collapse in" id="div-5" aria-expanded="true" style="">
<form:form commandName="tag" id="tag_form" method="post" action="/admin/tag/create" class="form-horizontal">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    <div class="form-group row">
                        <label class="control-label col-lg-2">关键字：</label>

                        <div class="col-lg-4">
                            <input type="text" name="name" value="${name}"
                                   class="form-control">
                            <form:errors path="name"></form:errors>
                        </div>
                    </div>
                <div class="form-group">
                    <div class="form-group row">
                        <div class="col-lg-1 col-lg-offset-6">
                            <button type="submit" id="submit_btn"
                                    class="btn btn-primary btn-sm btn-line">保存
                            </button>
                            <a class="btn btn-primary btn-sm btn-line" href="/admin/news/list"
                               data-original-title="返回" title="">返回</a>
                        </div>
                    </div>
                </div>
            </form:form>
            </div>
        </div>
    </div>
</div>

</div>
<!-- /.inner -->
</div>
<!-- /.outer -->
</div>
<!-- /#content -->

</div>
<!-- /#wrap -->
<%@ include file="/WEB-INF/admin/views/common/footer.jsp" %>
</body>
</html>

<script>
    $(document).ready(function () {

    });
</script>
