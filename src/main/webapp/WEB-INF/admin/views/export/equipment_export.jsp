<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2015/6/30
  Time: 17:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>数据导出</title>
    <%@ include file="/WEB-INF/admin/views/common/header.jsp" %>
</head>
<body>
<div class="bg-light lter" id="wrap">
    <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
    <%@ include file="/WEB-INF/admin/views/common/left.jsp" %>
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
                                <h5>数据导出</h5>

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
                                <form:form id="export_form" class="form-horizontal bv-form" action="/admin/equipment/export" method="post">

                                    <div id="selectTime" class="form-group">
                                        <label class="control-label col-lg-4">选择时间</label>
                                        <div class="col-lg-3">
                                            <div class="input-group  input-append date">
                                                <span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
                                                <input type="text" readonly="" name="from" id="from"
                                                       class="form-control"  value="">

                                            </div>
                                        </div>
                                        <div class="col-lg-3">
                                            <div class="input-group  input-append date">
                                                <span class="input-group-addon add-on"><i class="fa fa-calendar"></i></span>
                                                <input type="text" readonly="" name="to" id="to"
                                                       class="form-control"  value="">

                                            </div>
                                        </div>
                                    </div>

                                    <div class="form-group">
                                        <div class="col-lg-1 col-lg-offset-4">
                                            <a id="submit_btn" class="btn btn-primary btn-sm btn-line">导出</a>
                                        </div>

                                    </div>
                                    <input type="hidden" name="${_csrf.parameterName}"
                                           value="${_csrf.token}"/>
                                    <input type="hidden" name="category"
                                           value="${category}"/>
                                    <c:if test="${not empty consumerId}">
                                        <input type="hidden" name="consumerId" value="${consumerId}"/>
                                    </c:if>
                                    <c:if test="${not empty equipmentId}">
                                        <input type="hidden" name="equipmentId" value="${equipmentId}"/>
                                    </c:if>
                                    <c:if test="${not empty active}">
                                        <input type="hidden" name="active" value="${active}"/>
                                    </c:if>

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

    <jsp:include page="/WEB-INF/admin/views/common/footer.jsp"/>

</div>

<script>

    $(document).ready(function() {
        $("#submit_btn").click(function () {
            $("#export_form").submit();
            return false;
        });

        $('#from').datepicker({
            language: 'zh-cn',
            pickTime: false,
            todayBtn: true,
            autoClose: true,
            minView: '2',
            forceParse: false,
            format:"yyyy-mm-dd"
        });
        $('#to').datepicker({
            language: 'zh-cn',
            pickTime: false,
            todayBtn: true,
            autoClose: true,
            minView: '2',
            forceParse: false,
            format:"yyyy-mm-dd"
        });
    });
</script>
</body>
</html>



