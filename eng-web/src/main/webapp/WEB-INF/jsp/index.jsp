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
	</head>
<body class=" theme-black">
	<%@include file="/WEB-INF/jsp/include/nav.jsp"%>
	<%@include file="/WEB-INF/jsp/include/sidebar.jsp"%>
	<div class="content">
		<%@include file="/WEB-INF/jsp/include/header.jsp"%>
		<div class="main-content">
			<table id="datatable" class="display" width="100%" cellspacing="0">
	            <thead>
	            <tr>
	                <th style="text-align: center;"><input type="checkbox" value="全选" id="selectAll"></th>
	                <th>账号</th>
	                <th>真实姓名</th>
	                <th>昵称</th>
	                <th>角色</th>
	            </tr>
	            </thead>
	            <tbody></tbody>
	        </table>
			<%@include file="/WEB-INF/jsp/include/footer.jsp"%>
		</div>
	</div>
	<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
	<script type="text/javascript">
		$(function() {
		});
	</script>
</body>
</html>