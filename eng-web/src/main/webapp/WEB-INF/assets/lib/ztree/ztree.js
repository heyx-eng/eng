(function(){
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
                enable: false
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
            baseUrl: '',
            editFrame: '#editFrame'
        };
        var setting = $.extend({}, defaults, opts);
        function zTreeOnClick(event, treeId, treeNode) {
        	if(opts.onClick){
        		opts.onClick(treeNode);
        	} else{
        		$(setting.editFrame).prop("src", setting.baseUrl+"/page/edit?id="+treeNode.id);
        	}
            /*$.waiting("show");
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
            });*/
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
            var url = opts.baseUrl+"/delete";
            $.ajax({
            	url: url,
            	method: 'post',
            	data: {
            		"id":treeNode.id,
            		"_method": "delete"
            	},
            	success: function(response){
            		alert(response);
            	}
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
                    var newNode = response;
                    var node = { 
                		id:newNode.id, 
                		pId:newNode.pId, 
                		name:newNode.name, 
                		iconSkin:newNode.iconSkin, 
                		open: true, 
                		click : newNode.click, 
                		root :newNode.root,
                		isParent:newNode.isParent
                    };
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
})(jQuery);