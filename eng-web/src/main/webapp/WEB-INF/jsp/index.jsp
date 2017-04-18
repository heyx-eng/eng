<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/tags.jsp"%>
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
            <iframe src="${ctx }/sys/user/page/list" id="mainFrame" name="mainFrame" onload="startInit('mainFrame', 100)" scrolling="no" frameborder="0" style="width: 100%;"></iframe>
			<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
	<%@include file="/WEB-INF/jsp/ref/plugin-datatable-js.jsp"%>
	<script type="text/javascript">
		$(function() {
		});
	</script>
</body>
</html>