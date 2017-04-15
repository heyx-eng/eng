;$(function($){
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