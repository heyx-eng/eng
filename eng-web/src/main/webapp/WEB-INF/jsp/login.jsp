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
    <script>
        if(top.location!=self.location){
            top.location = "${ctx}/login";
        }
    </script>
</head>
<body class=" theme-black">
<div class="navbar navbar-default" role="navigation">
    <div class="navbar-header">
        <a class="" href="javascript:void(0);"><span class="navbar-brand">管理平台</span></a></div>

    <div class="navbar-collapse collapse" style="height: 1px;">

    </div>
</div>


<div class="dialog">
    <div class="panel panel-info">
        <div class="panel-heading no-collapse">登录</div>
        <div class="panel-body">
            <label style="color:red" id="msg"></label>
            <form id="submitForm" action="${ctx}/login" method="post">
                <div class="form-group">
                    <label>账号</label>
                    <input type="text" name="username" class="form-control span12">
                </div>
                <div class="form-group">
                    <label>密码</label>
                    <input type="password" name="password" class="form-controlspan12 form-control">
                </div>
                <input type="submit" class="btn btn-primary pull-right" value="登录">
                <div class="clearfix"></div>
            </form>
        </div>
    </div>
</div>
<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-form.jsp"%>
<script type="text/javascript">
    $(function() {
        $("#submitForm").submitForm({
            onSuccess: function () {
                location.href = "${ctx}/index";
            }
        });
    });
</script>
</body>
</html>