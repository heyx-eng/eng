<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
</head>
<body class=" theme-black">
<%@include file="/WEB-INF/jsp/include/nav.jsp"%>
<%@include file="/WEB-INF/jsp/include/sidebar.jsp"%>
<div class="content">
    <%@include file="/WEB-INF/jsp/include/header.jsp"%>
    <div class="main-content">
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
                            <label class="control-label col-md-3">用户名
                            </label>
                            <div class="col-md-4">
                                <form:input path="username" cssClass="form-control username" />
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">密码
                            </label>
                            <div class="col-md-4">
                                <form:password path="password" cssClass="form-control pwd" />
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <form:hidden path="salt"/>

                        <div class="form-group">
                            <label class="control-label col-md-3">角色
                                <span class="required"> * </span>
                            </label>
                            <div class="col-md-4">
                                <form:checkboxes path="roleIds" items="${roles}" cssClass="required" element="div class=\"app-inline\""/>
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">是否锁定
                                <span class="required"> * </span>
                            </label>
                            <div class="col-md-4">
                                <form:radiobuttons path="locked" items="${booleanList}" itemLabel="info" element="div class=\"app-inline\""/>
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">昵称
                            </label>
                            <div class="col-md-4">
                                <form:input path="nickname" cssClass="form-control " />
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">头像
                                <span class="required"> * </span>
                            </label>
                            <div id="uploader" class="col-md-3">
                                <form:hidden path="avatar"/>
                                <!--用来存放文件信息-->
                                <div id="thelist" class="uploader-list">
                                <c:if test="${avatar ne ''}">
                                    <div class="file-item">
                                        <img style="width: 100px;height:100px;display: block;margin-bottom: 5px;" src="${ctx}/${m.avatar}"/>
                                    </div>
                                </c:if>
                                </div>
                                <div id="picker">选择文件</div>
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">邮箱
                            </label>
                            <div class="col-md-4">
                                <form:input path="email" cssClass="form-control email" />
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">手机
                            </label>
                            <div class="col-md-4">
                                <form:input path="mobile" cssClass="form-control phone" />
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">QQ
                            </label>
                            <div class="col-md-4">
                                <form:input path="qq" cssClass="form-control qq" />
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">微信
                            </label>
                            <div class="col-md-4">
                                <form:input path="wechat" cssClass="form-control " />
                                <span class="help-block"></span>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-md-3">是否删除
                                <span class="required"> * </span>
                            </label>
                            <div class="col-md-4">
                                <form:radiobuttons path="deleted" cssClass="" items="${booleanList}" itemLabel="info" element="div class=\"app-inline\""/>
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
        <%@include file="/WEB-INF/jsp/include/footer.jsp"%>
    </div>
</div>
<%@include file="/WEB-INF/jsp/ref/core-js.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-form.jsp"%>
<%@include file="/WEB-INF/jsp/ref/plugin-upload.jsp"%>
<script type="text/javascript">
    $(function() {
        $("#editForm").submitForm();
        
        $("#backBtn").on("click", function () {
            location.href = '${ctx}/sys/user/page/list';
        });
        
        $.fileupload({
            onSuccess: function (response) {
                $("#avatar").val(response);
            }
        });
    });
</script>
</body>
</html>