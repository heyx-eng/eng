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
<body class=" theme-black">
<%@include file="/WEB-INF/jsp/include/nav.jsp"%>
<%@include file="/WEB-INF/jsp/include/sidebar.jsp"%>
<div class="content">
    <%@include file="/WEB-INF/jsp/include/header.jsp"%>
    <div class="main-content">
        <div class="table-container">
            <div class="table-edit-wrapper">
                <shiro:hasPermission name="sys:user:create">
                <button id="editable_create" class="btn sbold green"> 新建
                    <i class="glyphicon glyphicon-plus"></i>
                </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="sys:user:update">
                <button id="editable_edit" class="btn sbold btn-success"> 编辑
                    <i class="glyphicon glyphicon-pencil"></i>
                </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="sys:user:delete">
                <button id="editable_delete" class="btn sbold btn-danger"> 删除
                    <i class="glyphicon glyphicon-minus"></i>
                </button>
                </shiro:hasPermission>
                <shiro:hasPermission name="sys:user:review">
                <div class="btn-group">
                    <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        审核 <span class="caret"></span>
                    </button>
                    <ul class="dropdown-menu">
                        <li><a href="#"><i class="glyphicon glyphicon-ok"></i> 通过</a></li>
                        <li><a href="#"><i class="glyphicon glyphicon-remove"></i> 拒绝</a></li>
                    </ul>
                </div>
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
                    <th>用户名</th>
                    <th>昵称</th>
                    <th>头像</th>
                    <th>密码</th>
                    <th></th>
                    <th>角色</th>
                    <th>是否锁定</th>
                    <th>是否删除</th>
                    <th>邮箱</th>
                    <th>手机</th>
                    <th>QQ</th>
                    <th>微信</th>
                </tr>
                </thead>
                <tbody></tbody>
            </table>
        </div>
        <%@include file="/WEB-INF/jsp/include/footer.jsp"%>
    </div>
</div>
<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-datatable-js.jsp"%>
<script type="text/javascript">
    $(function () {
        TableDatatablesAjax.init({
            baseurl: '${ctx}/sys/user/',
            columns: [
                {"data": "id"},
                {"data": "username", "defaultContent": ""},
                {"data": "nickname", "defaultContent": ""},
                {"data": "avatar", "defaultContent": ""},
                {"data": "password", "defaultContent": ""},
                {"data": "salt", "defaultContent": ""},
                {"data": "roleIds", "defaultContent": ""},
                {"data": "locked", "defaultContent": ""},
                {"data": "deleted", "defaultContent": ""},
                {"data": "email", "defaultContent": ""},
                {"data": "mobile", "defaultContent": ""},
                {"data": "qq", "defaultContent": ""},
                {"data": "wechat", "defaultContent": ""}
            ]
        });
    });
</script>
</body>
</html>