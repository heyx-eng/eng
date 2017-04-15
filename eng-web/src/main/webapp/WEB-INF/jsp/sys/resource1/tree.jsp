<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en" xmlns:form="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>ENG-资源</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <%@include file="/WEB-INF/jsp/ref/core-css.jsp"%>
    <%@include file="/WEB-INF/jsp/ref/plugin-ztree-css.jsp"%>
</head>
<body class=" theme-black">
<%@include file="/WEB-INF/jsp/include/nav.jsp"%>
<%@include file="/WEB-INF/jsp/include/sidebar.jsp"%>
<div class="content">
    <%@include file="/WEB-INF/jsp/include/header.jsp"%>
    <div class="main-content">
        <div class="row">
        	<div class="col-md-offset-1 col-md-4">
            	<div id="ztree" class="ztree"></div>
            </div>
            <div class="col-md-4">
            	<iframe src="${ctx }/sys/resource/page/edit?id=1" id="editFrame" name="editFrame" onload="setIframeHeight('editFrame')" scrolling="no" frameborder="0" style="width: 100%;"></iframe>
            </div>
        </div>
        <%@include file="/WEB-INF/jsp/include/footer.jsp"%>
    </div>
</div>
<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-form.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-ztree.jsp"%>
<script type="text/javascript">
    $(function() {
        $("#editForm").submitForm();

		var ztree = $("#ztree").tree({
            baseUrl: '${ctx }/sys/resource',
            check: {
            	enable: true
            },
            edit: {
                enable: true
            }
        });
    });
</script>
</body>
</html>