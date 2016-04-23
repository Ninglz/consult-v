<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>系统配置信息</title>
    <%@ include file="/WEB-INF/admin/views/common/header.jsp" %>
</head>
<body>
<div class="bg-light lter" id="wrap">
    <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>

    <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
        <jsp:param name="activeMenu" value="systemProperty" />
    </jsp:include>
    <div id="content">
        <div class="outer">
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">系统配置</a> <span class="divider">/</span>
                    </li>
                    <li>
                        <a href="#">系统配置信息</a>
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
                                <h5>配置信息</h5>
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
                                <form:form commandName="property" id="system_property_form" method="post" action="/admin/system_property/edit" class="form-horizontal">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <input type="hidden" name="id" value="${property.id}"/>

                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">系统主机：</label>
                                        <div class="col-lg-4">
                                            <input type="text" name="systemHostUrl"
                                                   value="${property.systemHostUrl}"
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">上传文件路径前缀：</label>
                                        <div class="col-lg-4">
                                            <input type="text" name="storageFilePrefix"
                                                   value="${property.storageFilePrefix}"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">静态视图前缀：</label>
                                        <div class="col-lg-4">
                                            <input type="text" name="websiteStaticUrl"
                                                   value="${property.websiteStaticUrl}"
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">上传app前缀：</label>
                                        <div class="col-lg-4">
                                            <input type="text" name="appPrefix"
                                                   value="${property.appPrefix}"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">资讯存储前缀：</label>
                                        <div class="col-lg-4">
                                            <input type="text" name="newsPrefix"
                                                   value="${property.newsPrefix}"
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">硬件存储前缀：</label>
                                        <div class="col-lg-4">
                                            <input type="text" name="hardwarePrefix"
                                                   value="${property.hardwarePrefix}"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <div class="col-lg-1 col-lg-offset-6">
                                            <button type="submit" id="submit_btn"
                                                    class="btn btn-primary btn-sm btn-line">保存
                                            </button>
                                            <a class="btn btn-primary btn-sm btn-line" href="/admin/system_property/"
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

</body>
</html>

