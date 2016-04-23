<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>用户详情</title>
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
                        <a href="#">用户设备列表</a> <span class="divider">/</span>
                    </li>
                    <li>
                        <a href="#">用户详情</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">
            <div id="msg_div">

            </div>
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
                                <form class="form-horizontal">
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">账号信息：</label>

                                        <div class="col-lg-4">
                                            <input type="text" readonly="" value="${consumer.getCellNo()}"
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">年龄：</label>

                                        <div class="col-lg-4">
                                            <input type="text" readonly=""
                                                   value="${consumer.age}"
                                                   class="form-control">
                                        </div>
                                    </div>
                                    <div class="form-group row">
                                        <label class="control-label col-lg-2">性别：</label>

                                        <div class="col-lg-4">
                                            <input type="text" readonly=""
                                                   value="<c:if test="${consumer.sex}"><fmt:message key="info.customer.sex.male"/></c:if><c:if test="${!consumer.sex}"><fmt:message key="info.customer.sex.female"/></c:if>"
                                                   class="form-control">
                                        </div>
                                        <label class="control-label col-lg-2">体重：</label>

                                        <div class="col-lg-4">
                                            <input type="text" readonly=""
                                                   value="${consumer.weight}"
                                                   class="form-control">
                                        </div>
                                    </div>

                                </form>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-lg-12">
                        <div class="box">
                            <header>
                                <h5>设备信息</h5>

                                <div class="toolbar">
                                    <div class="btn-group">
                                        <a class="btn btn-default btn-sm minimize-box" data-toggle="collapse"
                                           href="#condensedTable">
                                            <i class="fa fa-angle-up"></i>
                                        </a>
                                    </div>
                                </div>
                            </header>
                            <div class="body collapse in" id="condensedTable">
                                <table id="customer_table" class="table table-bordered table-striped">
                                    <thead>
                                    <th  class="whitecustumer" data-dynatable-column="cellNo">
                                        设备类型
                                    </th>
                                    <th  class="whitecustumer" data-dynatable-column="created">MAC</th>
                                    <th  class="whitecustumer" data-dynatable-column="active">最近使用时间
                                    </th>
                                    <th  class="whitecustumer" data-dynatable-column="lastSigned">同步数据量</th>

                                    <th  class="whitecustumer" data-dynatable-column="id">操作</th>
                                    </thead>
                                    <tbody>
                                    <c:forEach items="${equipments}" var="equip">
                                        <tr>
                                            <td style="text-align: left;">${equip.type}</td>
                                            <td style="text-align: left;">${equip.mac}</td>
                                            <td style="text-align: left;">${equip.lastTime}</td>
                                            <td style="text-align: left;">${equip.counting}</td>
                                            <td style="text-align: left;"><a class="btn btn-info btn-xs btn-line" href="/admin/equipment/user_record?active=true&consumerId=${equip.id}&equipmentId=${equip.equipmentId}">详情</a></td>
                                        </tr>
                                    </c:forEach>
                                    </tbody>
                                </table>
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
