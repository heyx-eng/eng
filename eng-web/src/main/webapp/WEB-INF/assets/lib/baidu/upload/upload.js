(function () {
    var FileUpload = function (options) {
        var defaults = {
            uploader: {
                auto: true,
                swf: ctx + "/assets/lib/baidu/upload/Uploader.swf",

                // 文件接收服务端。
                server: ctx + '/file/upload',
                pick: {
                    id: '#picker',
                    multiple: false
                },
                resize: false,
                accept: {
                    title: 'Images',
                    extensions: 'gif,jpg,jpeg,bmp,png',
                    mimeTypes: 'image/*'
                },
                fileNumLimit: 1
            },
            onSuccess: null
        };
        var seetings = $.extend({}, defaults, options);
        var fileId;
        var uploader = WebUploader.create(seetings.uploader);
        uploader.on("beforeFileQueued", function (file) {
            if (fileId) {
                uploader.removeFile(fileId, true);
            }
        });
        var $list = $("#thelist");
        uploader.on('fileQueued', function (file) {
            fileId = file.id;
            var $li = $(
                    '<div id="' + file.id + '" class="file-item">' +
                    '   <img>' +
                    '   <div>' + file.name + '</div>' +
                    '</div>'
                ),
                $img = $li.find('img');
            $list.html($li);
            uploader.makeThumb(file, function (error, src) {
                if (error) {
                    $img.replaceWith('<span>不能预览</span>');
                    return;
                }
                $img.attr('src', src);
            });
        });

        uploader.on('uploadProgress', function (file, percentage) {
            var $li = $('#' + file.id),
                $percent = $li.find('.progress span');
            if (!$percent.length) {
                $percent = $('<p class="progress"><span></span></p>')
                    .appendTo($li)
                    .find('span');
            }

            $percent.css('width', percentage * 100 + '%');
        });

        uploader.on('uploadSuccess', function (file) {
            $('#' + file.id).addClass('upload-state-done');
        });

        // 文件上传失败，显示上传出错。
        uploader.on('uploadError', function (file, reason) {
            var $li = $('#' + file.id),
                $error = $li.find('div.error');

            // 避免重复创建
            if (!$error.length) {
                $error = $('<div class="error"></div>').appendTo($li);
            }

            $error.text('上传失败');
        });

        uploader.on('uploadAccept', function (file, response) {
            if (typeof seetings.onSuccess == "function") {
                seetings.onSuccess(response._raw);
            }
        });

        // 完成上传完了，成功或者失败，先删除进度条。
        uploader.on('uploadComplete', function (file) {
            $('#' + file.id).find('.progress').remove();
        });
    };
    $.fileupload = function (options) {
        return new FileUpload(options);
    }
})(jQuery);