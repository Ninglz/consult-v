<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/admin/views/common/commonInclude.jsp" %>
<!doctype html>
<html class="no-js">
<head>
    <meta charset="UTF-8">
    <title>后台角色列表</title>
    <%@ include file="/WEB-INF/admin/views/common/header.jsp" %>
</head>
<body>
<div class="bg-light lter" id="wrap">
    <%@ include file="/WEB-INF/admin/views/common/top.jsp" %>
    <jsp:include page="/WEB-INF/admin/views/common/left.jsp" flush="true">
        <jsp:param name="activeMenu" value="staff"/>
    </jsp:include>
    <div id="content">
        <div class="outer">
            <div>
                <ul class="breadcrumb">
                    <li>
                        <a href="#">后台角色列表</a>
                    </li>
                </ul>
            </div>
            <div class="inner bg-light lter">

                <div class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <br/>

                        <div class="col-xs-6">
                            <div id="dataTable_filter" class="dataTables_filter"><a id="addRole"
                                                                                    href="javascript:addRole();"
                                                                                    class="btn btn-info btn-sm btn-line">新增</a>
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <br/>
                    </div>
                    <table id="role_table" class="table table-bordered table-striped">
                        <thead>
                        <th class="whitecustumer" data-dynatable-column="name" data-dynatable-column>
                            角色名称
                        </th>
                        <th class="whitecustumer" data-dynatable-column="code">
                            角色代码
                        </th>
                        <th class="whitecustumer" data-dynatable-column="resources">
                            角色资源
                        </th>
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
<%@ include file="/WEB-INF/admin/views/common/footer.jsp" %>
<div class="modal fade" id="roleModal" tabindex="-1" role="dialog"
     aria-labelledby="roleModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h4 class="modal-title" id="myModalLabel">
                    管理用户新增
                </h4>
            </div>
            <form class="form-horizontal" id="roleForm">
            <div class="modal-body">

                    <div class="form-group row" id="roleNameDiv">
                        <label class="control-label col-lg-3">角色名：</label>

                        <div class="col-lg-4">
                            <input id="name" name="name" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row" id="roleCodeDiv">
                        <label class="control-label col-lg-3">角色代码：</label>

                        <div class="col-lg-4">
                            <input id="code" name="code" type="text" class="form-control">
                        </div>
                    </div>
                    <div class="form-group row">
                        <label class="control-label col-lg-3">角色资源：</label>

                        <div class="col-lg-4">
                            <select id="resources" name="resources" data-placeholder="请选择资源" size="5"
                                    class="chosen-select chosen-select-width" tabindex="-1" multiple="">
                                <option value=""></option>
                                <c:forEach items="${resources}" var="r">
                                    <option value="${r.id}">${r.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <input hidden="true" id="id" name="id"/>
                    <input hidden="true" id="csrf" name="${_csrf.parameterName}" value="${_csrf.token}"/>

            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-default">
                    提交
                </button>
                <button type="button" class="btn btn-default" id="cancelForm">取消
                </button>
            </div>
            </form>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal -->

</body>
</html>

<script>
    var dynatable;
    $(document).ready(function () {
        $("#logout_btn").click(function () {
            $("#logout_form").submit();
        });

        $('#cancelForm').click(function() {
            $('#roleForm').data('bootstrapValidator').resetForm(true);
            $('#roleModal').modal('hide');
        });

        $('#roleForm').bootstrapValidator({
            excluded: [':disabled', ':hidden', ':not(:visible)'],
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                name: {
                    group: '.col-lg-4',
                    validators: {
                        notEmpty: {
                            message: '<fmt:message key="err.role.info.name.null"/>'
                        },
                        stringLength: {
                            message: '<fmt:message key="err.role.info.name.length"/>',
                            max: 20
                        }
                    }
                },
                code: {
                    group: '.col-lg-4',
                    validators: {
                        notEmpty: {
                            message: '<fmt:message key="err.role.info.code.null"/>'
                        },
                        stringLength: {
                            message: '<fmt:message key="err.role.info.code.length"/>',
                            max: 12
                        },
                        regexp: {
                            regexp: /^[a-zA-Z_]+$/,
                            message: '<fmt:message key="err.role.info.code.formate"/>'
                        }
                    }
                }
            }
        }).on('success.form.bv', function(e) {
            // Prevent form submission


            e.preventDefault();

            // Get the form instance
            var $form = $(e.target);
            // Get the BootstrapValidator instance
            var bv = $form.data('bootstrapValidator');
            roleAddOperation()
            // Use Ajax to submit form data
//            $.post($form.attr('action'), $form.serialize(), function(result) {
//                console.log(result);
//            }, 'json');
        });


        function roleRowWriter(rowIndex, record, columns, cellWriter) {
            var tr = '';
            for (var i = 0, len = columns.length; i < len; i++) {
                if (columns[i].id == "id") {
                    var operation = "";
                    if(record.code != "ROLE_ADMIN"){
                        operation += '<a class="btn btn-info btn-xs btn-line" href="javascript:editRole(' + record.id + ')">编辑</a>&nbsp;';
                        operation += '&nbsp;' + '<a class="btn btn-info btn-xs btn-line" href="javascript:roleDel(' + record.id + ')">删除</a>&nbsp;';
                    }
                    record.id = operation;
                }
                if (columns[i].id == "code") {
                    if (record.code == null) {
                        record.code = "";
                    }
                }
                if (columns[i].id == "name") {
                    if (record.name == null) {
                        record.name = "";
                    }
                }
                if (columns[i].id == "resources") {
                    if (record.resources == null) {
                        record.resources = "";
                    } else {
                        if (record.resources.split(",").length > 4) {
                            record.resources = "<a href='javascript:showPover(" + record.id + ")' class='btn btn-default' name='resourceName" + record.id + "' rel='popover' data-content='" + record.resources + "' data-original-title='角色资源详情'>查看详情</a>";


                        }
                    }
                }
                tr += cellWriter(columns[i], record);
            }

            return '<tr>' + tr + '</tr>';
        }

        dynatable = $('#role_table').dynatable({
            table: {
                bodyRowSelector: 'td'
            },
            dataset: {
                ajaxUrl: '/admin/staff/role/list'
            },
            writers: {
                _rowWriter: roleRowWriter
            }
        }).data('dynatable');
        $("#resources").chosen({
            no_results_text: '未找到!',
            disable_search: true,
            width: "100%"
        });
    });

    function addRole() {
        $("#myModalLabel").text("管理角色新增");
        $("#name").val("");
        $("#code").val("");
        $("#id").val("");
        $("#roleNameDiv").show();
        $("#roleCodeDiv").show();
        chose_mult_set_ini();
        $('#roleModal').modal('show');
    }

    function editRole(id) {
        $.ajax({
            type: "GET",
            url: "/admin/staff/role/detail",
            data: "id=" + id,
            success: function (data) {
                $("#myModalLabel").text("管理角色编辑");
                $("#id").val(data.m.id);
                $("#roleNameDiv").hide();
                $("#roleCodeDiv").hide();
                chose_mult_set_ini_role_resource(data.m.resources);
                $('#roleModal').modal('show');
                $("body").hideLoading();
            }
        });

    }
    //
    function roleAddOperation() {
        var d1 = new $.Jq_alert();
        var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
        var name = $("#name").val();
        var code = $("#code").val();
        var resources = $("#resources").val();
        var id = $("#id").val();
        $("body").showLoading();
        $.ajax({
            type: "POST",
            url: "/admin/staff/role/add",
            data: "id=" + id
                    + "&name=" + name
                    + "&code=" + code
                    + "&resources=" + resources
                    + "&" + token,
            success: function (data) {
                if(data.c != 0){
                    $("body").hideLoading();
                    $('#roleModal').modal('hide');
                    if(data.c == 10027){
                        d1.init({
                            msg:"<fmt:message key="err.role.add.failed"/>"
                        });
                    }
                }else{
                    dynatable.process();
                    $('#roleModal').modal('hide');
                    $("body").hideLoading();
                }

            }
        });
    }

    function roleDel(id) {
        var d1 = new $.Jq_alert();
        var token = $("#csrf").attr("name") + "=" + $("#csrf").attr("value");
        $("body").showLoading();
        $.ajax({
            type: "POST",
            url: "/admin/staff/role/del",
            data: "roleId=" + id
                    + "&" + token,
            success: function (data) {
                if(data.c != 0){
                    $("body").hideLoading();
                    if(data.c == 10023){
                        d1.init({
                            msg:"<fmt:message key="err.role.no.such"/>"
                        });
                    }
                    if(data.c == 10026){
                        d1.init({
                            msg:"<fmt:message key="err.role.no.del"/>"
                        });
                    }
                    if(data.c == 10001){
                        d1.init({
                            msg:"<fmt:message key="err.role.del.failed"/>"
                        });
                    }
                }else{
                    dynatable.process();
                    $("body").hideLoading();
                }

            }
        });


    }

    function chose_mult_set_ini() {
        $("#resources option").removeAttr('selected');
        $("#resources").trigger("chosen:updated");
    }

    function chose_mult_set_ini_role_resource(values) {
        var arr = values.split(',');
        var length = arr.length;
        for (var i = 0; i < length; i++) {
            $("#resources option[value='" + arr[i] + "']").attr('selected', 'selected');
        }
        $("#resources").trigger("chosen:updated");
    }

    function getCommaIndex(str) {
        var arr = str.split("");
        var arr2 = [];
        for (var i = 0; i < arr.length; i++) {
            if (arr[i] == ",") {
                arr2.push(",");
            }
            if (arr2.length == 4) {
                return i;
            }
        }
    }

    function showPover(id) {
        $('a[name=resourceName' + id + ']').popover({
            trigger: 'hover',
            placement: 'right',
            animation: true,
            html: true
        }).on("show.bs.popover", function () {
            $(this).data("bs.popover").tip().css("max-width", "800px");
        });
    }

</script>
