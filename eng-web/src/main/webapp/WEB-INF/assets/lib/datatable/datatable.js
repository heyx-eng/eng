/***
Wrapper/Helper Class for datagrid based on jQuery Datatable Plugin
***/
var Datatable = function() {

    var tableOptions; // main options
    var dataTable; // datatable object
    var table; // actual table jquery object
    var tableContainer; // actual table container object
    var tableWrapper; // actual table wrapper jquery object
    var tableInitialized = false;
    var ajaxParams = {}; // set filter mode
    var the;
    return {
        //main function to initiate the module
        init: function(options) {

            if (!$().dataTable) {
                return;
            }

            the = this;

            // default settings
            options = $.extend(true, {
                src: "", // actual table  
                filterApplyAction: "filter",
                filterCancelAction: "filter_cancel",
                resetGroupActionInputOnSuccess: true,
                loadingMessage: '加载中...',
                dataTable: {
                    "dom": "<'row'<'col-md-6 col-sm-12'<'table-edit-actions pull-left'>><'col-md-6 col-sm-12'<'table-group-actions pull-right'>>r><'table-responsive't><'row'<'col-md-4 col-sm-12'i><'col-md-8 col-sm-12'<'pull-right'p>>>", // datatable layout
                    "pageLength": 10, // default records per page
                    "language": { // language settings
                        // metronic spesific
                        "metronicGroupActions": "_TOTAL_ 条数据被选中",
                        "metronicAjaxRequestGeneralError": "服务器无法连接,请检查网络!",

                        // data tables spesific
                        "lengthMenu": "每页  _MENU_ 条",
                        "info": "第 _PAGE_ 页 / 共 _PAGES_ 页",
                        "infoEmpty": "暂无内容",
                        "emptyTable": "暂无可用数据",
                        "zeroRecords": "无匹配数据",
                        "paginate": {
                            "previous": "上一页",
                            "next": "下一页",
                            "last": "末页",
                            "first": "首页",
                            "page": "",
                            "pageOf": "  "
                        }
                    },

                    "orderCellsTop": true,
                    "columnDefs": [{ // define columns sorting options(by default all columns are sortable extept the first checkbox column)
                        'orderable': false,
                        'targets': [0],
                        'className': 'select-checkbox',
                        'render': function(data){
                        	return '';
                        }
                    }],
                    //"pagingType": "bootstrap_full_number", // pagination type(bootstrap, bootstrap_full_number or bootstrap_extended)
                    "bAutoWidth": true, // disable fixed width and enable fluid table
                    "sScrollX": true,
                    "processing": false, // enable/disable display message box on record load
                    "serverSide": true, // enable/disable server side ajax loading
                    "ajax": { // define ajax settings
                        "url": "", // ajax URL
                        "type": "GET", // request type
                        "timeout": 20000,
                        "data": function(data) { // add request parameters before submit
                        	var page = 1;
                            var size = 10;
                            var draw = 1;
                        	for(var key in data){
                        		if("draw" == key){
                        			draw = data[key];
                                } else if("start" == key){
                                	page = data[key];
                                }else if("length" == key){
                                	size = data[key];
                                }
                                delete data[key];
                        	}
                        	data['page'] = Math.floor(page/size)+1;
                        	data['size'] = size;
                            $.each(ajaxParams, function(key, value) {
                                data[key] = value;
                            });
                        },
                        "dataSrc": function(res) { // Manipulate the data returned from the server
                            if (tableOptions.onSuccess) {
                                tableOptions.onSuccess.call(undefined, the, res);
                            }
                            return res.data;
                        },
                        "error": function() { // handle general connection errors
                            if (tableOptions.onError) {
                                tableOptions.onError.call(undefined, the);
                            }
                        }
                    },

                    "drawCallback": function(oSettings) { // run some code on table redraw
                        if (tableInitialized === false) { // check if table has been initialized
                            tableInitialized = true; // set table initialized
                            table.show(); // display table
                        }
                        // callback for ajax data load
                        if (tableOptions.onDataLoad) {
                            tableOptions.onDataLoad.call(undefined, the);
                        }
                    }
                }
            }, options);

            tableOptions = options;

            // create table's jquery object
            table = $(options.src);
            tableContainer = table.parents(".table-container");

            // apply the special class that used to restyle the default datatable
            var tmp = $.fn.dataTableExt.oStdClasses;

            $.fn.dataTableExt.oStdClasses.sWrapper = $.fn.dataTableExt.oStdClasses.sWrapper + " dataTables_extended_wrapper";
            $.fn.dataTableExt.oStdClasses.sFilterInput = "form-control input-xs input-sm input-inline";
            $.fn.dataTableExt.oStdClasses.sLengthSelect = "form-control input-xs input-sm input-inline";

            // initialize a datatable
            dataTable = table.DataTable(options.dataTable);

            // revert back to default
            $.fn.dataTableExt.oStdClasses.sWrapper = tmp.sWrapper;
            $.fn.dataTableExt.oStdClasses.sFilterInput = tmp.sFilterInput;
            $.fn.dataTableExt.oStdClasses.sLengthSelect = tmp.sLengthSelect;

            // get table wrapper
            tableWrapper = table.parents('.dataTables_wrapper');

            // build table group actions panel
            if ($('.table-actions-wrapper', tableContainer).size() === 1) {
                $('.table-group-actions', tableWrapper).html($('.table-actions-wrapper', tableContainer).html());
                $('.table-actions-wrapper', tableContainer).remove();
            }
            if ($('.table-edit-wrapper', tableContainer).size() === 1) {
                $('.table-edit-actions', tableWrapper).html($('.table-edit-wrapper', tableContainer).html());
                $('.table-edit-wrapper', tableContainer).remove();
            }
            // handle group checkboxes check/uncheck
            $('.group-checkable').change(function(e) {
            	if(!$(this).prop("checked")){
            		dataTable.rows().deselect();
                }else{
                	dataTable.rows().select();
                }
            });

            // handle row's checkbox click
           /* table.on('change', 'tbody > tr > td:nth-child(1) input[type="checkbox"]', function() {
                countSelectedRecords();
            });*/

            // handle filter submit button click
            table.on('click', '.filter-submit', function(e) {
                e.preventDefault();
                the.submitFilter();
            });

            // handle filter cancel button click
            table.on('click', '.filter-cancel', function(e) {
                e.preventDefault();
                the.resetFilter();
            });
        },

        submitFilter: function() {
            the.setAjaxParam("action", tableOptions.filterApplyAction);

            // get all typeable inputs
            $('textarea.form-filter, select.form-filter, input.form-filter:not([type="radio"],[type="checkbox"])', table).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all checkboxes
            $('input.form-filter[type="checkbox"]:checked', table).each(function() {
                the.addAjaxParam($(this).attr("name"), $(this).val());
            });

            // get all radio buttons
            $('input.form-filter[type="radio"]:checked', table).each(function() {
                the.setAjaxParam($(this).attr("name"), $(this).val());
            });

            dataTable.ajax.reload();
        },

        resetFilter: function() {
            $('textarea.form-filter, select.form-filter, input.form-filter', table).each(function() {
                $(this).val("");
            });
            $('input.form-filter[type="checkbox"]', table).each(function() {
                $(this).attr("checked", false);
            });
            the.clearAjaxParams();
            the.addAjaxParam("action", tableOptions.filterCancelAction);
            dataTable.ajax.reload();
        },

        setAjaxParam: function(name, value) {
            ajaxParams[name] = value;
        },

        addAjaxParam: function(name, value) {
            if (!ajaxParams[name]) {
                ajaxParams[name] = [];
            }

            skip = false;
            for (var i = 0; i < (ajaxParams[name]).length; i++) { // check for duplicates
                if (ajaxParams[name][i] === value) {
                    skip = true;
                }
            }

            if (skip === false) {
                ajaxParams[name].push(value);
            }
        },

        clearAjaxParams: function(name, value) {
            ajaxParams = {};
        },

        getDataTable: function() {
            return dataTable;
        },

        getTableWrapper: function() {
            return tableWrapper;
        },

        gettableContainer: function() {
            return tableContainer;
        },

        getTable: function() {
            return table;
        }

    };

};