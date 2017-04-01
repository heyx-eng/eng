package org.engdream.base.web.annotation.support;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.engdream.base.web.annotation.SearchParam;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.baomidou.mybatisplus.plugins.Page;


public class SearchParamMethodArgumentResolver implements HandlerMethodArgumentResolver {

	private static final String PARAM_SIZE = "size";
	private static final String PARAM_PAGE = "page";
	private static final int DEFAULT_SIZE = 10;
	private static final int DEFAULT_PAGE = 1;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.hasParameterAnnotation(SearchParam.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		String pageStr = webRequest.getParameter(PARAM_PAGE);
		String sizeStr = webRequest.getParameter(PARAM_SIZE);
		int current = DEFAULT_PAGE;
		int size = DEFAULT_SIZE;
		if(StringUtils.isNotBlank(pageStr)){
			current = Integer.valueOf(pageStr);
		}
		if(StringUtils.isNotBlank(sizeStr)){
			size = Integer.valueOf(sizeStr);
		}
		Page<?> page = new Page<>(current, size);
		Map<String, String[]> param = webRequest.getParameterMap();
		for(Map.Entry<String, String[]> entry : param.entrySet()){
			String key = entry.getKey();
			String value = entry.getValue()[0];
			if(!PARAM_SIZE.equals(key) && 
					!PARAM_PAGE.equals(key) && 
					!"_".equals(key) &&
					StringUtils.isNotEmpty(value)){
				page.getCondition().put(key, value);
			}
		}
		return page;
	}

}
