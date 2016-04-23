<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>用户列表</title>
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
                        <a href="#">用户列表</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">
                <!-- 用户基本信息 -->
                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-4">
                            <div id="dataTable_filter" class="dataTables_filter"><label>手机号： <input id="cellNo"
                                                                                                    type="search"
                                                                                                    name="cellNo"
                                                                                                    class="form-control input-sm"
                                                                                                    aria-controls="dataTable"/>
                            </label>&nbsp;&nbsp;<a id="serachCondition"  class="btn btn-info btn-sm btn-line">搜索</a>
                            </div>
                        </div>


                    </div>

                    <div class="row">
                        <br/>
                    </div>
                    <table id="customer_table" class="table table-bordered table-striped">
                        <thead>
                        <th  class="whitecustumer" data-dynatable-column="cellNo">
                            手机号
                        </th>
                        <th  class="whitecustumer" data-dynatable-column="created">注册时间</th>
                        <th  class="whitecustumer" data-dynatable-column="active">用户状态</th>
                        <th  class="whitecustumer" data-dynatable-column="lastSigned">最近登陆时间</th>

                        <%--<th  class="whitecustumer" data-dynatable-column="id">操作</th>--%>
                        </thead>
                        <tbody>
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

<script>
    var dynatable;
    $(document).ready(function() {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });

        function customerRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            var activeFlag = "";
            for (var i = 0, len = columns.length; i < len; i++) {
               /* if(columns[i].id == "id"){
                    var operation = "";
                    operation +=  '<a class="btn btn-info btn-xs btn-line" href="/admin/customer/detail?customerId='+ record.id +'">详情</a>' + '&nbsp;';
                    if(record.active == "<fmt:message key="info.customer.active.normal"/>"){
                        operation +=  '<a class="btn btn-info btn-xs btn-line" href="javascript:lockCustomer('+ record.id +')">冻结</a>' + '&nbsp;';
                    }else{
                        operation +=  '<a class="btn btn-info btn-xs btn-line" href="javascript:unLockCustomer('+ record.id +')">解冻</a>' + '&nbsp;';
                    }
                    record.id = operation;
                }*/

                if(columns[i].id == "active"){
                    activeFlag = record.active;
                    record.active = (record.active == "true") ? "<fmt:message key="info.customer.active.normal"/>":"<fmt:message key="info.customer.active.lock"/>";
                }
                if(columns[i].id == "username"){
                    if(record.username == "null"){
                        record.username = "";
                    }
                }
                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        dynatable = $('#customer_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/admin/customer/list'
            },
            writers: {
                _rowWriter: customerRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var cellNo = $("#cellNo").val();
            if (cellNo == "") {
                dynatable.queries.remove("cellNo");
            } else {
                dynatable.queries.add("cellNo",cellNo);
            }
            dynatable.process();
        });



    });

    function lockCustomer(id){
        $("body").showLoading();
        var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
        $.ajax({
            type: "POST",
            url: "/admin/customer/status",
            data: "customerId=" + id + "&status=" + false + "&" + token,
            success: function (data) {
                dynatable.process();
                $("body").hideLoading();
            }
        });
    }

    function unLockCustomer(id){
        $("body").showLoading();
        var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
        $.ajax({
            type: "POST",
            url: "/admin/customer/status",
            data: "customerId=" + id + "&status=" + true + "&" + token,
            success: function (data) {
                dynatable.process();
                $("body").hideLoading();
            }
        });
    }

</script>
