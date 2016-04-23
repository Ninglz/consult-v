<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>生产数据列表</title>
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
                        <a href="#">生产数据列表</a>
                    </li>
                    <a class="btn btn-primary btn-line upload_a" href="javascript:importExcel();">导入</a>
                </ul>
            </div>
            <div class="inner bg-light lter">
                <!-- 用户基本信息 -->
                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-6">
                            <div id="dataTable_filter" class="dataTables_filter"><label>MAC： <input id="mac"
                                                                                                    type="search"
                                                                                                    value="${mac}"
                                                                                                    name="mac"
                                                                                                    class="form-control input-sm"
                                                                                                    aria-controls="dataTable"/>
                            </label>&nbsp;&nbsp;<a id="serachCondition"  class="btn btn-info btn-sm btn-line">搜索</a>
                                &nbsp;&nbsp;<a href="/admin/production/export/template" target="_blank" class="btn btn-info btn-sm btn-line">模板下载</a>
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <br/>
                    </div>
                    <table id="production_table" class="table table-bordered table-striped">
                        <thead>
                        <th class="whitecustumer" data-dynatable-column="staff" data-dynatable-column>
                            上传人用户名
                        </th>
                        <th class="whitecustumer" data-dynatable-column="mac">
                            MAC
                        </th>
                        <th class="whitecustumer" data-dynatable-column="batch">
                            批次
                        </th>
                        <th class="whitecustumer" data-dynatable-column="voltageFirstMin">电压0</th>
                        <th class="whitecustumer" data-dynatable-column="voltageSecondMin">电压0.1</th>
                        <th class="whitecustumer" data-dynatable-column="voltageThirdMin">电压0.2</th>
                        <th class="whitecustumer" data-dynatable-column="voltageFourthMin">电压0.4</th>
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
<!-- 模态框（Modal） -->
<div class="modal fade" id="importModal" tabindex="-1" role="dialog"
     aria-labelledby="importModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="importModalLabel">
                    生产数据导入
                </h4>
            </div>
            <form class="form-horizontal" id="importForm" enctype="multipart/form-data" method="post" action="/admin/production/import">
                <div class="modal-body">

                    <div class="form-group row">
                        <label class="control-label col-lg-3">excel文件：</label>
                        <div class="col-lg-4">
                            <div id="fileUpload"  class="uploader">
                                <input id="adjust" name="adjust" type="file">

                            </div>
                        </div>
                    </div>
                    <input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                </div>
                <div class="modal-footer">
                    <button type="button" onclick="submitForm();" class="btn btn-default">
                        提交
                    </button>
                    <button type="button" class="btn btn-default" id="cancelForm">取消
                    </button>
                </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
</div>
<!-- /.modal -->
</body>
</html>

<script>
    var dynatable;
    $(document).ready(function() {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });

        $('#adjust').uniform({
            fileDefaultHtml : "未选择文件",
            fileButtonHtml : "选择文件"

        });

        $('#cancelForm').click(function() {
            $('#importModal').modal('hide');
        });

        function productionRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            for (var i = 0, len = columns.length; i < len; i++) {
                if(columns[i].id == "batch"){
                    record.batch = record.batch + "批次";
                }
                if(columns[i].id == "voltageFirstMin"){
                    record.voltageFirstMin = record.voltageFirstMin + "~" + record.voltageFirstMax;
                }
                if(columns[i].id == "voltageSecondMin"){
                    record.voltageSecondMin = record.voltageSecondMin + "~" + record.voltageSecondMax;
                }
                if(columns[i].id == "voltageThirdMin"){
                    record.voltageThirdMin = record.voltageThirdMin + "~" + record.voltageThirdMax;
                }
                if(columns[i].id == "voltageFourthMin"){
                    record.voltageFourthMin = record.voltageFourthMin + "~" + record.voltageFourthMax;
                }

                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        dynatable = $('#production_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/admin/production/list',
                queries: {
                    mac: "${mac}"
                }
            },
            writers: {
                _rowWriter: productionRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var mac = $("#mac").val();
            if (mac == "") {
                dynatable.queries.remove("mac");
            } else {
                dynatable.queries.add("mac",mac);
            }
            dynatable.process();
        });


    });

    function submitForm(){
        $("body").showLoading();
        var fd = new FormData(document.getElementById("importForm"));
        $.ajax({
            type: "POST",
            url: "/admin/production/import" + "?_csrf="+$("#csrf").attr("value"),
            data: fd,
            processData: false,
            contentType: false,
            success: function (data) {
                dynatable.process();
                $("body").hideLoading();
                $('#importModal').modal('hide');
            }
        });
    }

    function importExcel(){
        $('#importModal').modal('show');
    }



</script>
