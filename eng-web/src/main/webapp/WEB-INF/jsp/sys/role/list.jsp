<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title></title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <%@include file="/WEB-INF/jsp/ref/core-css.jsp"%>
    <%@include file="/WEB-INF/jsp/ref/plugin-datatable-css.jsp"%>
</head>
<body>
    <div class="table-container">
        <div class="table-edit-wrapper">
            <shiro:hasPermission name="sys:role:create">
            <button id="editable_create" class="btn sbold green"> 新建
                <i class="glyphicon glyphicon-plus"></i>
            </button>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:role:update">
            <button id="editable_edit" class="btn sbold btn-success"> 编辑
                <i class="glyphicon glyphicon-pencil"></i>
            </button>
            </shiro:hasPermission>
            <shiro:hasPermission name="sys:role:delete">
            <button id="editable_delete" class="btn sbold btn-danger"> 删除
                <i class="glyphicon glyphicon-minus"></i>
            </button>
            </shiro:hasPermission>
        </div>
        <div class="table-actions-wrapper">
            <span> </span>
            <select class="table-group-action-select form-control input-inline input-small input-sm">
                <option value="">请选择...</option>
            </select>
            <input type="input" class="table-group-action-input form-control input-inline input-small input-sm">
            <button class="btn btn-sm green table-group-action-submit">
                <i class="fa fa-check"></i> 查询
            </button>
        </div>
        <table class="display" id="datatable_ajax">
            <thead>
            <tr role="row" class="heading">
                <th width="2%">
                    <label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                        <input type="checkbox" class="group-checkable"/>
                        <span></span>
                    </label>
                </th>
                <th>角色名称</th>
                <th>角色标识</th>
                <th>菜单资源</th>
                <th>是否可用</th>
            </tr>
            </thead>
            <tbody></tbody>
        </table>
    </div>
<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-datatable-js.jsp"%>
<script type="text/javascript">
    $(function () {
        TableDatatablesAjax.init({
            baseurl: '${ctx}/sys/role/',
            columns: [
                {"data": "id"},
                {"data": "name", "defaultContent": ""},
                {"data": "role", "defaultContent": ""},
                {"data": "resourceIds", "defaultContent": ""},
                {"data": "available", "defaultContent": ""}
            ],
            ajaxParam: [
                {
                    "deleted": 0
                }
            ],
            columnDefs: [
                {
                    targets:   -1,
                    render: function (data) {
                        return data ? '是' : '否';
                    }
                }
            ]
        });
    });
</script>
</body>
</html>