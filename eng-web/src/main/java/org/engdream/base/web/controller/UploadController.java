package org.engdream.base.web.controller;

import org.apache.commons.fileupload.FileUploadBase;
import org.engdream.common.util.LogUtil;
import org.engdream.upload.FileUploadUtils;
import org.engdream.upload.exception.FileNameLengthLimitExceededException;
import org.engdream.upload.exception.InvalidExtensionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("file")
public class UploadController {


    //最大上传大小 字节为单位
    private long maxSize = 1048576*10;
    //允许的文件内容类型
    private String[] allowedExtension = FileUploadUtils.DEFAULT_ALLOWED_EXTENSION;
    //文件上传下载的父目录
    private String baseDir = FileUploadUtils.getDefaultBaseDir();

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    public ResponseEntity<String> upload(
            HttpServletRequest request, HttpServletResponse response,
            @RequestParam(value = "file", required = false) MultipartFile file){
        if(file == null){
            return new ResponseEntity<>("文件不能为空", HttpStatus.BAD_REQUEST);
        }
        try {
            String url = FileUploadUtils.upload(request, baseDir, file, allowedExtension, maxSize, true);
            return ResponseEntity.ok(url);
        } catch (InvalidExtensionException e) {
            return new ResponseEntity<>("不允许上传的类型", HttpStatus.BAD_REQUEST);
        } catch (FileUploadBase.FileSizeLimitExceededException e) {
            return new ResponseEntity<>("文件大小超出限制", HttpStatus.BAD_REQUEST);
        } catch (FileNameLengthLimitExceededException e) {
            return new ResponseEntity<>("文件名长度超出限制", HttpStatus.BAD_REQUEST);
        } catch (IOException e) {
            LogUtil.e("file upload error", e);
            return new ResponseEntity<>("服务器错误，请稍后重试", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
