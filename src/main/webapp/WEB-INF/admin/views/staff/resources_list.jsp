<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>后台资源列表</title>
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
                        <a href="#">后台资源列表</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">

                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>
                    </div>
                    <table id="resource_table" class="table table-bordered table-striped">
                        <thead>
                        <th class="whitecustumer" data-dynatable-column="name" data-dynatable-column>
                            资源名称
                        </th>
                        <th class="whitecustumer" data-dynatable-column="patternUrl">
                            匹配路径
                        </th>

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
<%@ include file="/WEB-INF/admin/views/common/footer.jsp" %>
</body>
</html>

<script>
    $(document).ready(function() {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });

        function resourceRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            for (var i = 0, len = columns.length; i < len; i++) {
                if(columns[i].id == "patternUrl"){
                    if(record.patternUrl == null){
                        record.patternUrl = "";
                    }
                }
                if(columns[i].id == "name"){
                    if(record.name == null){
                        record.name = "";
                    }
                }
                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        var dynatable = $('#resource_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/admin/staff/resources/list'
            },
            writers: {
                _rowWriter: resourceRowWriter
            }
        }).data('dynatable');



    });

</script>
