<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>红外编码管理</title>
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
                        <a href="#">红外编码管理</a>
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
                            <div id="dataTable_filter" class="dataTables_filter"><label>红外编码： <input id="code"
                                                                                                    type="search"
                                                                                                    name="code"
                                                                                                    class="form-control input-sm"
                                                                                                    aria-controls="dataTable"/>
                            </label>&nbsp;&nbsp;<a id="serachCondition"  class="btn btn-info btn-sm btn-line">搜索</a>
                                &nbsp;&nbsp;<a href="/admin/infrared/export/template" target="_blank" class="btn btn-info btn-sm btn-line">模板下载</a>
                            </div>
                        </div>

                    </div>

                    <div class="row">
                        <br/>
                    </div>
                    <table id="infrared_table" class="table table-bordered table-striped">
                        <thead>
                        <th class="whitecustumer" data-dynatable-column="code" data-dynatable-column>
                            红外编码
                        </th>
                        <th class="whitecustumer" data-dynatable-column="appliances">
                            家电类型
                        </th>
                        <th class="whitecustumer" data-dynatable-column="created">
                            上传日期
                        </th>
                        <th class="whitecustumer" data-dynatable-column="description">内容</th>
                        <th class="whitecustumer" data-dynatable-column="id">操作</th>

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
                    红外编码数据导入
                </h4>
            </div>
            <form class="form-horizontal" id="importForm" enctype="multipart/form-data" method="post" action="/admin/infrared/import">
                <div class="modal-body">

                    <div class="form-group row">
                        <label class="control-label col-lg-3">excel文件：</label>
                        <div class="col-lg-4">
                            <div id="fileUpload"  class="uploader">
                                <input id="infrared" name="infrared" type="file">

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

        $('#infrared').uniform({
            fileDefaultHtml : "未选择文件",
            fileButtonHtml : "选择文件"

        });

        $('#cancelForm').click(function() {
            $('#importModal').modal('hide');
        });

        function infraredRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            for (var i = 0, len = columns.length; i < len; i++) {
                if(columns[i].id == "id"){
                    var operation = "";
                    operation +=  '<a class="btn btn-info btn-xs btn-line" href="javascript:delSales('+ record.id +')">删除</a>' + '&nbsp;';
                    record.id = operation;
                }


                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        dynatable = $('#infrared_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/admin/infrared/list',
                queries: {
                    mac: "${mac}"
                }
            },
            writers: {
                _rowWriter: infraredRowWriter
            }
        }).data('dynatable');
        $("#serachCondition").click(function () {
            var tbId = $("#tbId").val();
            if (tbId == "") {
                dynatable.queries.remove("tbId");
            } else {
                dynatable.queries.add("tbId",tbId);
            }
            dynatable.process();
        });



    });

    function delInfrared(id){
        $("body").showLoading();
        var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
        $.ajax({
            type: "POST",
            url: "/admin/Infrared/del",
            data: "id=" + id + "&" + token,
            success: function (data) {
                dynatable.process();
                $("body").hideLoading();
            }
        });
    }

    function importExcel(){
        $('#importModal').modal('show');
    }

    function submitForm(){
        $("body").showLoading();
        var fd = new FormData(document.getElementById("importForm"));
        $.ajax({
            type: "POST",
            url: "/admin/infrared/import" + "?_csrf="+$("#csrf").attr("value"),
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


</script>
