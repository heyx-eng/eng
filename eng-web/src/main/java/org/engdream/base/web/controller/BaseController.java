package org.engdream.base.web.controller;

import org.apache.shiro.authz.UnauthorizedException;
import org.engdream.base.entity.BaseEntity;
import org.engdream.common.util.LogUtil;
import org.engdream.common.util.ReflectUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Serializable;

public abstract class BaseController<M extends BaseEntity<ID>, ID extends Serializable> {
	protected static final String PERMS_VIEW = "view";
	protected static final String PERMS_REVIEW = "review";
	protected static final String PERMS_CREATE = "create";
	protected static final String PERMS_UPDATE = "update";
	protected static final String PERMS_DELETE = "delete";
	 /**
     * 实体类型
     */
    protected final Class<M> entityClass;

    private String viewPrefix;


    protected BaseController() {
        this.entityClass = ReflectUtils.findParameterizedType(getClass(), 0);
        setViewPrefix(defaultViewPrefix());
    }

    protected void setCommonDate(Model model){

    }
    /**
     * 当前模块 视图的前缀
     * 默认
     * 1、获取当前类头上的@RequestMapping中的value作为前缀
     * 2、如果没有就使用当前模型小写的简单类名
     */
    public void setViewPrefix(String viewPrefix) {
        if (viewPrefix.startsWith("/")) {
            viewPrefix = viewPrefix.substring(1);
        }
        this.viewPrefix = viewPrefix;
    }

    public String getViewPrefix() {
        return viewPrefix;
    }

    protected M newModel() {
        try {
            return entityClass.newInstance();
        } catch (Exception e) {
            throw new IllegalStateException("can not instantiated model : " + this.entityClass, e);
        }
    }

    /**
     * 获取视图名称：即prefixViewName + "/" + suffixName
     *
     * @return
     */
    public String viewName(String suffixName) {
        if (!suffixName.startsWith("/")) {
            suffixName = "/" + suffixName;
        }
        return getViewPrefix() + suffixName;
    }

    /**
     * @param backURL null 将重定向到默认getViewPrefix()
     * @return
     */
    protected String redirectToUrl(String backURL) {
        if (StringUtils.isEmpty(backURL)) {
            backURL = getViewPrefix();
        }
        if (!backURL.startsWith("/") && !backURL.startsWith("http")) {
            backURL = "/" + backURL;
        }
        return "redirect:" + backURL;
    }

    protected String defaultViewPrefix() {
        String currentViewPrefix = "";
        RequestMapping requestMapping = AnnotationUtils.findAnnotation(getClass(), RequestMapping.class);
        if (requestMapping != null && requestMapping.value().length > 0) {
            currentViewPrefix = requestMapping.value()[0];
        }

        if (StringUtils.isEmpty(currentViewPrefix)) {
            currentViewPrefix = this.entityClass.getSimpleName();
        }

        return currentViewPrefix;
    }
    
    @ExceptionHandler(Exception.class)
	public void exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e)
			throws Exception {
    	LogUtil.e("异常", e);
    	if(e instanceof UnauthorizedException){
    		response.setStatus(HttpStatus.UNAUTHORIZED.value());
    		response.getWriter().write("你没有权限访问");
    		return;
    	}
        if(e instanceof MethodArgumentTypeMismatchException){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("请求参数错误");
            return;
        }

    	response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		response.getWriter().write("服务器错误，请稍后重试!");
	}
    
    protected void assertPermission(String perms) {
    	return;
		/*Subject subject = SecurityUtils.getSubject();
		subject.checkPermission(getResourcePrefix()+":"+perms);*/
	}
    
	protected abstract String getResourcePrefix();
	
}
