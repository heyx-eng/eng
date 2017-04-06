function setIframeHeight(iframeId) {
	var cwin = document.getElementById(iframeId);
	if (document.getElementById) {
		if (cwin && !window.opera) {
			if (cwin.contentDocument
					&& cwin.contentDocument.body.offsetHeight) {
				cwin.height = cwin.contentDocument.body.offsetHeight + 20; //FF NS   
			} else if (cwin.Document && cwin.Document.body.scrollHeight) {
				cwin.height = cwin.Document.body.scrollHeight + 10;//IE   
			}
		} else {
			if (cwin.contentWindow.document
					&& cwin.contentWindow.document.body.scrollHeight)
				cwin.height = cwin.contentWindow.document.body.scrollHeight;//Opera   
		}
	}
};
(function () {
    var Form = function ($this, options) {
        var defaults = {
            beforeSubmit:null,
            onSuccess:null
        };
        var settings = $.extend({}, defaults, options);
        $this.validate({
            submitHandler: function(form) {
                if(settings.beforeSubmit){
                    settings.beforeSubmit();
                }
                $(form).ajaxSubmit({
                    beforeSubmit: function() {
                        $.waiting("show");
                        $(".submit-btn").attr("disabled", true);
                        if(settings.onSuccess){
                            settings.onSuccess();
                        }
                    },
                    success: function(response) {
                        $(".submit-btn").removeAttr("disabled");
                        $.waiting("hide");
                        alert(response);
                        //清除隐藏域  --> true
                        $(form).clearForm(true);
                        $("#_method").val("post");
                        $(form).prop("action", $(form).prop("action").replace("update", "create"));
                    },
                    error: function(e){
                        $.waiting("hide");
                        $(".submit-btn").removeAttr("disabled");
                        alert(e.responseText);
                    }
                });
            },
            errorPlacement: function (error, element) {
                if (element.is(':radio') || element.is(':checkbox')) { //如果是radio或checkbox
                    element.parent().parent().addClass("has-error");
                    error.appendTo(element.parent().parent().find(".help-block")); //将错误信息添加当前元素的父结点后面
                } else {
                    element.parent().parent().parent().addClass("has-error");
                    error.appendTo(element.parent().find(".help-block"));
                }
            }
        });
    };
    $.fn.submitForm = function (options) {
        return new Form($(this), options);
    };
})();
$.validator.addMethod("phone",function(value,element){
    var score = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
    return this.optional(element) || (score.test(value));
},"电话号码格式不正确");
$.validator.addMethod("idcard",function(value,element){
    var score = /(^\d{15}$)|(^\d{17}([0-9]|(X|x))$)/;
    return this.optional(element) || (score.test(value));
},"身份证号码格式不正确");
$.validator.addMethod("tel",function(value,element){
    var score = /^(\d3,4|\d{3,4}-)?\d{7,8}$/;
    return this.optional(element) || (score.test(value));
},"电话号码格式不正确");
$.validator.addMethod("nonzero",function(value,element){
    var score = /^\+?[1-9][0-9]*$/;
    return this.optional(element) || (score.test(value));
},"只能输入非零整数");
$.validator.addMethod("code",function(value,element){
    var score = /^[0-9]{6}$/;
    return this.optional(element) || (score.test(value));
},"请输入六位数字验证码");
$.validator.addMethod("username",function(value,element){
    var score = /^[a-zA-Z_].{6,15}$/;
    return this.optional(element) || (score.test(value));
},"用户名只能以字母或下划线开头，长度为6-15位");
$.validator.addMethod("pwd",function(value,element){
    var score = /^[a-zA-Z_0-9]{6,}$/;
    return this.optional(element) || (score.test(value));
},"密码长度至少为6位");