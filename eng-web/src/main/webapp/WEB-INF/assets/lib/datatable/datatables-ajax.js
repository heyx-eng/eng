var TableDatatablesAjax = function () {

    /*var initPickers = function () {
        //init date pickers
        $('.date-picker').datepicker({
            rtl: App.isRTL(),
            autoclose: true
        });
    }*/

    var handleRecords = function (opt) {
    	var columnDefOpt = [{
            searchable: false,
            orderable: false,
            className: 'select-checkbox',
            targets:   0
        }];
        if(opt.columnDefs){
            columnDefOpt = columnDefOpt.concat(opt.columnDefs);
        }
        var grid = new Datatable();

        grid.init({
            src: $("#datatable_ajax"),
            onSuccess: function (grid, response) {
                // grid:        grid object
                // response:    json object of server side ajax response
                // execute some code after table records loaded
            },
            onError: function (grid) {
                // execute some code on network or other general error  
            },
            onDataLoad: function(grid) {
                // execute some code on ajax data load
            },
            loadingMessage: '加载中...',
            dataTable: {
                "bStateSave": true, // save datatable state(pagination, sort, etc) in cookie.

                "lengthMenu": [
                    [10, 20, 50, 100, 150, -1],
                    [10, 20, 50, 100, 150, "All"] // change per page values here
                ],
                "pageLength": 10, // default record count per page
                "ajax": {
                    "url": opt.baseurl+'list', // ajax source
                },
                columns: opt.columns,
                select: {
                    style:    'multi',
                    selector: 'td:first-child'
                },
                columnDefs: columnDefOpt,
                "order": []
            }
        });

        // handle group actionsubmit button click
        grid.getTableWrapper().on('click', '.table-group-action-submit', function (e) {
            e.preventDefault();
            var action = $(".table-group-action-select", grid.getTableWrapper());
            if (action.val() != "") {
            	var input = $(".table-group-action-input", grid.getTableWrapper());
                grid.setAjaxParam(action.val(), input.val());
                grid.getDataTable().ajax.reload();
                grid.clearAjaxParams();
            } else if (action.val() == "") {
            	alert("请选择查询条件")
            }
        });
        
        grid.getTableWrapper().on('click', '#editable_create', function (e) {
            e.preventDefault();
            location.href = opt.baseurl+'page/create';
        });
        grid.getTableWrapper().on('click', '#editable_edit', function (e) {
            e.preventDefault();
            var rows = grid.getDataTable().rows( { selected: true } );
            if(rows.data().length < 1){
                alert("请选择数据!");
                return;
            }
            if(rows.data().length > 1){
                alert("只能选择一条数据!");
                return;
            }
            location.href = opt.baseurl+'page/edit?id='+rows.data()[0].id;
        });
        grid.getTableWrapper().on('click', '#editable_delete', function (e) {
            e.preventDefault();
            var rows = grid.getDataTable().rows( { selected: true } );
            if(rows.data().length < 1){
                alert("请选择数据!");
                return;
            }
            if(rows.data().length == 1){
                if(confirm("确定删除?")){
                    $.ajax({
                        url: opt.baseurl+"delete",
                        type: "post",
                        data: {
                            "id": rows.data()[0].id,
                            "_method": "delete"
                        },
                        success: function (result) {
                            alert("删除成功");
                            grid.getDataTable().ajax.reload();
                        },
                        error: function (e) {
                            alert(e.responseText);
                        }
                    });
                }
            } else{
                if(confirm("确定删除?")){
                    var ids = "";
                    for(var i=0; i< rows.data().length; i++){
                        if(ids == ""){
                            ids = rows.data()[i].id;
                        } else{
                            ids += "," + rows.data()[i].id;
                        }
                    }
                    $.ajax({
                        url: opt.baseurl+"batchDelete",
                        type: "post",
                        data: {
                            "ids": ids,
                            "_method": "delete"
                        },
                        success: function (result) {
                            alert("删除成功");
                            grid.getDataTable().ajax.reload();
                        },
                        error: function (e) {
                            alert(e.responseText);
                        }
                    });
                }
            }
        });

        //grid.setAjaxParam("customActionType", "group_action");
        //grid.getDataTable().ajax.reload();
        //grid.clearAjaxParams();
    }

    return {
        init: function (options) {
            handleRecords(options);
        }

    };

}();
