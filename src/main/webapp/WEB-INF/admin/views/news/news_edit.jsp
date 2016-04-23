<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/3/9
  Time: 17:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>资讯编辑</title>
    <%@ include file="/WEB-INF/admin/views/common/header.jsp" %>
</head>
<body>
<div class="bg-light lter" id="wrap">
    <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
    <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
            <jsp:param name="activeMenu" value="news" />
        </jsp:include>
    <div id="content">
        <div class="outer">
            <div class="inner bg-light lter">
                <div class="row">
                    <div class="col-lg-12">
                        <div class="box">
                            <header>
                                <div class="icons">
                                    <i class="fa fa-th"></i>
                                </div>
                                <h5>资讯编辑</h5>

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
                                <form:form commandName="newsForm" id="news_form" method="post" action="/admin/news/edit" class="form-horizontal">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <input type="hidden" id="news_serial" name="serial" value="${newsForm.serial}"/>
                                    <input type="hidden" id="news_id" name="id" value="${newsForm.id}"/>

                                    <div class="form-group">
                                        <label class="control-label col-lg-2">资讯主题</label>
                                        <div class="col-lg-4">
                                            <input id="mainTitle" name="mainTitle" class="form-control" type="text" placeholder="主题" value="${newsForm.mainTitle}" >
                                        </div>
                                        <form:errors path="mainTitle"></form:errors>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-lg-2">类别</label>
                                        <div class="col-lg-4">
                                            <select id="news_type"
                                                    name="type"
                                                    class="form-control"
                                                    aria-controls="dataTable">
                                                <option value="">请选择</option>
                                                <c:forEach items="${typeMap}" var="t">
                                                    <option value="${t.value}" <c:if test="${t.value eq newsForm.type}">selected="selected" </c:if>><fmt:message key="${t.key}"></fmt:message></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                        <form:errors path="type"></form:errors>
                                    </div>

                                    <div class="form-group">
                                        <label class="control-label col-lg-2">关键字(用空格隔开)</label>
                                        <div class="col-lg-4">
                                            <input id="keywords" name="keywords" class="form-control" type="text" placeholder="关键字" value="${newsForm.keywords}" >
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="control-label col-lg-2">内容</label>
                                        <div class="col-lg-8">
                                            <textarea id="ckeditor" name="content" class="ckeditor">${newsForm.content}</textarea>
                                        </div>
                                        <div class="form-group row">
                                            <div class="col-lg-1 col-lg-offset-6">
                                                <button type="submit" id="submit_btn"
                                                        class="btn btn-primary btn-sm btn-line">保存
                                                </button>
                                                <a class="btn btn-primary btn-sm btn-line" href="/admin/news/list"
                                                   data-original-title="返回" title="">返回</a>
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
<!-- CKEditor -->
<script src="/resources/admin/lib/ckeditor/ckeditor.js"></script>
</body>
</html>

<script>
    $(document).ready(function () {
        $('#tag-multiple-selected').multiselect({
            buttonText: function(options, select) {
                if (options.length === 0) {
                    return '请选择';
                }else {
                    var labels = [];
                    options.each(function() {
                        if ($(this).attr('label') !== undefined) {
                            labels.push($(this).attr('label'));
                        }
                        else {
                            labels.push($(this).html());
                        }
                    });
                    return labels.join(', ') + '';
                }
            }
        });

        $("#submit_btn").click(function () {
            $("#news_form").submit();
        });
        CKEDITOR.replace("ckeditor", {
            filebrowserImageUploadUrl: "/admin/news/img/upload?${_csrf.parameterName}=${_csrf.token}&newsId=${newsForm.id}"
        })
    });
</script>

