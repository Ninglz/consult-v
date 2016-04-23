<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>操作日志</title>
    <%@ include file="/WEB-INF/admin/views/common/header.jsp" %>
</head>
<body>
<div class="bg-light lter" id="wrap">
    <%@ include file="/WEB-INF/admin/views/common/top.jsp"%>
    <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
        <jsp:param name="activeMenu" value="staff" />
    </jsp:include>
    <div id="content">
        <div class="outer">
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">操作日志</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">
                <!-- 用户基本信息 -->
                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-6">
                            <div id="dataTable_filter" class="dataTables_filter"><label>用户名： <input id="username"
                                                                                                    type="search"
                                                                                                    name="username"
                                                                                                    class="form-control input-sm"
                                                                                                    aria-controls="dataTable"/>
                            </label> &nbsp;&nbsp;<a id="serachCondition"  class="btn btn-info btn-sm btn-line">搜索</a>
                            </div>
                        </div>
                    </div>
                    </div>

                    <div class="row">
                        <br/>
                    </div>
                    <table id="staff_log_table" class="table table-bordered table-striped">
                        <thead>
                        <th data-dynatable-column="username" data-dynatable-column>
                            操作人
                        </th>
                        <th data-dynatable-column="operation">
                            操作类型
                        </th>
                        <th data-dynatable-column="params">
                            参数
                        </th>
                        <th data-dynatable-column="created">操作时间</th>
                        <th data-dynatable-column="succeed">是否成功</th>
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
    $(document).ready(function() {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });

        function staffLogRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            for (var i = 0, len = columns.length; i < len; i++) {

                if(columns[i].id == "username"){
                    if(record.username == "null"){
                        record.username = "";
                    }
                }
                if(columns[i].id == "params"){
                    if(record.username == "null"){
                        record.username = "";
                    }
                }
                if(columns[i].id == "operation"){
                    if(record.operation == "CUSTOMER_STATUS"){
                        record.operation = "<fmt:message key="info.log.backend.operation.CUSTOMER_STATUS"/>";
                    }else if(record.operation == "STAFF_STATUS"){
                        record.operation = "<fmt:message key="info.log.backend.operation.STAFF_STATUS"/>";
                    }else if(record.operation == "STAFF_DEL"){
                        record.operation = "<fmt:message key="info.log.backend.operation.STAFF_DEL"/>";
                    }else if(record.operation == "STAFF_RESET_PASSWORD"){
                        record.operation = "<fmt:message key="info.log.backend.operation.STAFF_RESET_PASSWORD"/>";
                    }else if(record.operation == "STAFF_ADD"){
                        record.operation = "<fmt:message key="info.log.backend.operation.STAFF_ADD"/>";
                    }else if(record.operation == "STAFF_EDIT"){
                        record.operation = "<fmt:message key="info.log.backend.operation.STAFF_EDIT"/>";
                    }else if(record.operation == "STAFF_CHANGE_ROLE"){
                        record.operation = "<fmt:message key="info.log.backend.operation.STAFF_CHANGE_ROLE"/>";
                    }else if(record.operation == "CHANGE_ROLE_RESOURCE"){
                        record.operation = "<fmt:message key="info.log.backend.operation.CHANGE_ROLE_RESOURCE"/>";
                    }else if(record.operation == "NEWS_ADD"){
                        record.operation = "<fmt:message key="info.log.backend.operation.NEWS_ADD"/>";
                    }else if(record.operation == "NEWS_EDIT"){
                        record.operation = "<fmt:message key="info.log.backend.operation.NEWS_EDIT"/>";
                    }else if(record.operation == "VERSION_CHANGE"){
                        record.operation = "<fmt:message key="info.log.backend.operation.VERSION_CHANGE"/>";
                    }else if(record.operation == "VERSION_ADD"){
                        record.operation = "<fmt:message key="info.log.backend.operation.VERSION_ADD"/>";
                    }else if(record.operation == "SYSTEM_CONFIG_CHANGE"){
                        record.operation = "<fmt:message key="info.log.backend.operation.SYSTEM_CONFIG_CHANGE"/>";
                    }
                }
                if(columns[i].id == "succeed"){
                    record.succeed = (record.succeed == true) ? "<fmt:message key="info.log.true"/>" : "<fmt:message key="info.log.false"/>"
                }
                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        var dynatable = $('#staff_log_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/admin/staff/optLog/list'
            },
            writers: {
                _rowWriter: staffLogRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var username = $("#username").val();
            if (username == "") {
                dynatable.queries.remove("username");
            } else {
                dynatable.queries.add("username",username);
            }
            dynatable.process();
        });
    });





</script>
