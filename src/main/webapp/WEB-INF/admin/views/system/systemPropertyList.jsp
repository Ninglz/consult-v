<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html >
<head>
    <meta charset="UTF-8">
    <title>系统信息配置</title>
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
                        <a href="#">系统配置</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">

                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>
                        <c:if test="${list.size() <= 0}">
                            <div class="col-xs-4">
                                <a href="/admin/system_property/detail" class="btn btn-info btn-sm btn-line">新增</a>
                            </div>
                        </c:if>
                    </div>

                    <div class="row">
                        <br/>
                    </div>
                    <table class="table table-bordered table-striped">
                        <thead>
                        <th >
                            主机地址
                        </th>
                        <th >
                            文件存储前缀
                        </th>
                        <th >
                            静态视图URL
                        </th>
                        <th >
              APP存储前缀
                        </th>
                        <th >
                            操作
                        </th>
                        </thead>
                        <tbody>
                        <c:forEach items="${list}" var="property">
                            <td class="whitecustumer">
                                    ${property.systemHostUrl}
                            </td>
                            <td class="whitecustumer">
                                    ${property.storageFilePrefix}
                            </td>
                            <td class="whitecustumer">
                                    ${property.websiteStaticUrl}
                            </td>
                            <td class="whitecustumer">
                                    ${property.appPrefix}
                            </td>
                            <td class="whitecustumer">
                                <a class="btn btn-info btn-xs btn-line" href="/admin/system_property/detail?id=${property.id}">编辑</a>
                            </td>
                        </c:forEach>
                        </tbody>
                    </table>
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

