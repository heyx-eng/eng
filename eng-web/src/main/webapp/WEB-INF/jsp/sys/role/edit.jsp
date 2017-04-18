<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/include/tags.jsp"%>
<!doctype html>
<html lang="en" xmlns:form="http://www.w3.org/1999/html">
<head>
    <meta charset="utf-8">
    <title>ENG-</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <%@include file="/WEB-INF/jsp/ref/core-css.jsp"%>
    <%@include file="/WEB-INF/jsp/ref/plugin-ztree-css.jsp"%>
</head>
<body>
    <div class="row">
        <form:form action="${ctx}/sys/user/${operator}" cssClass="form-horizontal" id="editForm" commandName="m">
            <c:if test="${operator eq 'create'}">
                <input id="_method" type="hidden" name="_method" value="post">
            </c:if>
            <c:if test="${operator eq 'update'}">
                <input id="_method" type="hidden" name="_method" value="put">
            </c:if>
            <form:hidden path="id"/>
            <div class="col-md-12">
                <div class="form-body">
                    <div class="form-group">
                        <label class="control-label col-md-3">角色
                            <span class="required"> * </span>
                        </label>
                        <div class="col-md-4">
                            <form:input path="role" cssClass="form-control required" />
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">描述
                        </label>
                        <div class="col-md-4">
                            <form:textarea path="description" cssClass="form-control"></form:textarea>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="control-label col-md-3">资源
                        </label>
                        <div class="col-md-6">
                            <div id="ztree" class="ztree"></div>
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
                            <div class="col-md-offset-3 col-md-3">
                                <button type="submit" class="btn green submit-btn">提交</button>
                                <button id="reset" type="reset" class="btn default">重置</button>
                            </div>
                            <div class="col-md-3">
                                <button id="backBtn" type="button" class="btn default pull-right">返回列表</button>
                            </div>
                        </div>
                    </div>

                </div>
            </div>
        </form:form>
    </div>
<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-form.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-ztree.jsp"%>
<script type="text/javascript">
    $(function() {
        $("#editForm").submitForm();

		var ztree = $("#ztree").tree({
            baseUrl: '${ctx}/sys/resource',
            check: {
            	enable: true
            }
        });
        $("#backBtn").on("click", function () {
            location.href = '${ctx}/sys/role/page/list';
        });
    });
</script>
</body>
</html>