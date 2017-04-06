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
    <style type="text/css">
	body {
		margin: 0;
		padding: 0;
		background-color: #fff;
	}
	</style>
</head>
<body class=" theme-black">
	<div class="row">
	    <form:form action="${ctx}/sys/resource/update" cssClass="form-horizontal" id="editForm" commandName="m">
	        <input id="_method" type="hidden" name="_method" value="put">
	        <form:hidden path="id"/>
	        <div class="col-md-12">
	            <div class="form-body">
	                <div class="form-group">
	                    <label class="control-label col-md-3">资源名称
	                        <span class="required"> * </span>
	                    </label>
	                    <div class="col-md-4">
	                        <form:input path="name" cssClass="form-control required" />
	                        <span class="help-block"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-md-3">资源类型
	                        <span class="required"> * </span>
	                    </label>
	                    <div class="col-md-4">
	                        <form:select path="type" cssClass="form-control required"/>
	                        <span class="help-block"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-md-3">跳转链接
	                    </label>
	                    <div class="col-md-4">
	                        <form:input path="url" cssClass="form-control " />
	                        <span class="help-block"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-md-3">权重
	                    </label>
	                    <div class="col-md-4">
	                        <form:input path="weight" cssClass="form-control " />
	                        <span class="help-block"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-md-3">父节点
	                    </label>
	                    <div class="col-md-4">
	                        <form:input path="parentId" cssClass="form-control " />
	                        <span class="help-block"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-md-3">路径
	                    </label>
	                    <div class="col-md-4">
	                        <form:input path="parentIds" cssClass="form-control " />
	                        <span class="help-block"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-md-3">权限
	                        <span class="required"> * </span>
	                    </label>
	                    <div class="col-md-4">
	                        <form:select path="permission" cssClass="form-control required"/>
	                        <span class="help-block"></span>
	                    </div>
	                </div>
	                <div class="form-group">
	                    <label class="control-label col-md-3">是否可用
	                        <span class="required"> * </span>
	                    </label>
	                    <div class="col-md-4">
	                        <form:radiobuttons path="available" items="${booleanList}" itemLabel="info" element="div class=\"app-inline\""/>
	                        <span class="help-block"></span>
	                    </div>
	                </div>
	                <div class="form-actions">
	                    <div class="row">
	                        <div class="col-md-3">
	                            <button type="submit" class="btn green submit-btn">保存</button>
	                            <button id="reset" type="reset" class="btn default">重置</button>
	                        </div>
	                    </div>
	                </div>
	
	            </div>
	        </div>
	    </form:form>
	</div>
<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-form.jsp"%>
<script type="text/javascript">
    $(function() {
        $("#editForm").submitForm();

    });
</script>
</body>
</html>