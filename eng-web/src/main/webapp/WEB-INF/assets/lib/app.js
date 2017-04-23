var browserVersion = window.navigator.userAgent.toUpperCase();
var isOpera = false, isFireFox = false, isChrome = false, isSafari = false, isIE = false;
function reinitIframe(iframeId, minHeight) {
    try {
        var iframe = document.getElementById(iframeId);
        var bHeight = 0;
        if (isChrome == false && isSafari == false)
            bHeight = iframe.contentWindow.document.body.scrollHeight;

        var dHeight = 0;
        if (isFireFox == true)
            dHeight = iframe.contentWindow.document.documentElement.offsetHeight + 2;
        else if (isIE == false && isOpera == false)
            dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
        else if (isIE == true && ! -[1, ] == false) { } //ie9+
        else
            bHeight += 3;

        var height = Math.max(bHeight, dHeight);
        if (height < minHeight) height = minHeight;
        iframe.style.height = height + "px";
    } catch (ex) { }
}
function startInit(iframeId, minHeight) {
    isOpera = browserVersion.indexOf("OPERA") > -1 ? true : false;
    isFireFox = browserVersion.indexOf("FIREFOX") > -1 ? true : false;
    isChrome = browserVersion.indexOf("CHROME") > -1 ? true : false;
    isSafari = browserVersion.indexOf("SAFARI") > -1 ? true : false;
    if (!!window.ActiveXObject || "ActiveXObject" in window)
        isIE = true;
    window.setInterval("reinitIframe('" + iframeId + "'," + minHeight + ")", 100);
}
$(function () {
    $(".nav-list a").each(function () {
        $(this).bind('click', function () {
            $(".nav-list li").each(function () {
                $(this).removeClass('active');
            });
            $(this).parent().addClass('active');
        })
    });
});
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
}

$(function($){
    var waiting = {
        overlay : null,
        show : function(){
            var opts = {
                lines: 13, // The number of lines to draw
                length: 11, // The length of each line
                width: 5, // The line thickness
                radius: 17, // The radius of the inner circle
                corners: 1, // Corner roundness (0..1)
                rotate: 0, // The rotation offset
                color: '#FFF', // #rgb or #rrggbb
                speed: 1, // Rounds per second
                trail: 60, // Afterglow percentage
                shadow: false, // Whether to render a shadow
                hwaccel: false, // Whether to use hardware acceleration
                className: 'spinner', // The CSS class to assign to the spinner
                zIndex: 2e9, // The z-index (defaults to 2000000000)
                top: 'auto', // Top position relative to parent in px
                left: 'auto' // Left position relative to parent in px
            };
            var target = document.createElement("div");
            document.body.appendChild(target);
            var spinner = new Spinner(opts).spin(target);
            waiting.overlay = iosOverlay({
                text: "加载中...",
                spinner: spinner
            });
        },
        hide: function(){
            if(waiting.overlay){
                waiting.overlay.hide();
            }
        }
    };
    $.waiting = function(opt){
        if(opt=="show"){
            return waiting.show();
        }else if(opt=="hide"){
            return waiting.hide();
        }
    };


    var Msg = function(opt){
        this.defaults = {
            title:'',
            content:'',
            columnClass: 'col-md-4 col-md-offset-4',
            confirmButton: '确定',
            cancelButton: '取消',
            icon: 'fa fa-warning',
            confirmButtonClass: 'btn-info',
            animation: 'zoom'
        };
        this.options = $.extend({}, this.defaults, opt);
    };
    Msg.prototype = {
        confirm: function(){
            $.confirm(this.options);
        },
        success: function(){
            $.confirm(this.options);
        }
    };
    $.msg = function(method ,options){
        var msg = new Msg(options);
        switch(method){
            case 'confirm':
                return msg.confirm();
        }
    };

    var Iframe = {
    	onload: function(id, callback){
    		var iframe = document.getElementById(id);   
            if (iframe.attachEvent) {      
                iframe.attachEvent("onload", function() {
                	alert(1);
                	callback();
                });      
            } else {      
                iframe.onload = function() {   
                	alert(2);
                	callback();
                };      
            }
    	},
    	fillHeight: function(iframeId){
    		Iframe.onload(iframeId, function(){
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
    		});
    	}
    };
    $.iframe = function(id){
    	return Iframe.fillHeight(id);
    }
});