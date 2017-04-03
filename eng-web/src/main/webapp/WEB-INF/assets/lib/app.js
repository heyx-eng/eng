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

    var ZTree = function(ztreeObj, opts){
        var defaults = {
            async: {
                enable: true,
                url: opts.baseUrl+"/tree",
                autoParam: ["id"],
                otherParam: {},
                dataFilter: null
            },
            view: {
                expandSpeed: "",
                selectedMulti: false,
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom
            },
            edit: {
                enable: true
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            check: {
                chkboxType: {
                    "Y": "ps",
                    "N": "ps"
                },
                enable: false,
                autoCheckTrigger: true
            },
            callback: {
                beforeRemove: beforeRemove,
                onRemove: zTreeOnRemove,
                onClick: zTreeOnClick
            },
            baseUrl: ''
        };
        var setting = $.extend({}, defaults, opts);
        function zTreeOnClick(event, treeId, treeNode) {
            $.waiting("show");
            $.ajax({
                url: opts.baseUrl+"/get?id="+treeNode.id,
                type:"get",
                dataType:"json",
                success: function(data){
                    $.waiting("hide");
                    if(opts.onClick){
                        opts.onClick(data);
                    }
                }
            });
        };

        function filter(treeId, parentNode, childNodes) {
            if (!childNodes) return null;
            for (var i=0, l=childNodes.length; i<l; i++) {
                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            }
            return childNodes;
        }
        function beforeRemove(treeId, treeNode) {
            var ztree = $.fn.zTree.getZTreeObj(treeId);
            ztree.selectNode(treeNode);
            return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
        }
        function zTreeOnRemove(event, treeId, treeNode){
            var url = opts.baseUrl+"/delete?id="+treeNode.id;
            $.getJSON(url, function(dt) {
            });
        }
        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='add node' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_"+treeNode.tId);
            if (btn) btn.bind("click", function(){
                onAdd(treeId, treeNode);
                return false;
            });
        };
        /**
         * 添加新节点
         * @param e
         * @param treeId
         * @param treeNode
         */
        function onAdd(treeId, treeNode) {
            var ztree = $.fn.zTree.getZTreeObj(treeId);
            var url = opts.baseUrl+"/appendChild?parentId="+treeNode.id;
            $.ajax({
                url: url,
                dataType: 'json',
                method: 'post',
                success: function(response) {
                    var newNode = response.data;
                    var node = { id:newNode.id, pId:newNode.pId, name:newNode.name, iconSkin:newNode.iconSkin, open: true,
                        click : newNode.click, root :newNode.root,isParent:newNode.isParent};
                    var newNode = ztree.addNodes(treeNode, node)[0];
                    //                zTree.selectNode(newNode);
                    $("#" + newNode.tId + "_a").click();
                }
            });
        }
        function removeHoverDom(treeId, treeNode) {
            $("#addBtn_"+treeNode.tId).unbind().remove();
        };
        return $.fn.zTree.init(ztreeObj, setting);
    };
    $.fn.tree = function(opts){
        return new ZTree($(this), opts);
    };

});