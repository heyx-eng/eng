package org.engdream.ueditor.upload;

import org.apache.commons.fileupload.FileUploadException;
import org.engdream.ueditor.define.AppInfo;
import org.engdream.ueditor.define.BaseState;
import org.engdream.ueditor.define.State;
import org.engdream.upload.FileUploadUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

public class BinaryUploader {
	
	public static final State save(HttpServletRequest request,
			Map<String, Object> conf) {
		 //最大上传大小 字节为单位
	    long maxSize = FileUploadUtils.DEFAULT_MAX_SIZE;
	    //允许的文件内容类型
	    String[] allowedExtension = FileUploadUtils.DEFAULT_ALLOWED_EXTENSION;
	    //文件上传下载的父目录
	    String baseDir = FileUploadUtils.getDefaultBaseDir();
	    
		try {
			MultipartHttpServletRequest req = (MultipartHttpServletRequest)request;
			MultipartFile file = req.getFile("upfile");
			String url = FileUploadUtils.upload(request, baseDir, file, allowedExtension, maxSize, true);
			State storageState = new BaseState(true);
			if (storageState.isSuccess()) {
				storageState.putInfo("url", url);
				storageState.putInfo("type", file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."), file.getOriginalFilename().length()));
				storageState.putInfo("original", file.getOriginalFilename());
			}
			return storageState;
		} catch (FileUploadException e) {
			return new BaseState(false, AppInfo.PARSE_REQUEST_ERROR);
		} catch (IOException e) {
		}
		return new BaseState(false, AppInfo.IO_ERROR);
	}
}
