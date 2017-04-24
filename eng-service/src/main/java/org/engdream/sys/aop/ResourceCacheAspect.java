package org.engdream.sys.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.engdream.base.aop.AbstractCacheAspect;
import org.engdream.sys.entity.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by heyx on 2017/4/19.
 */
@Component
@Aspect
public class ResourceCacheAspect extends AbstractCacheAspect<Resource, Long> {
    public ResourceCacheAspect() {
        setCacheName("resource");
    }

    @Override
    @Pointcut(value = "target(org.engdream.sys.service.ResourceService)")
    protected void targetPointcut() {
    }

    @Pointcut(value = "execution(* findAllMenu()) ")
    private void cacheAllPointcut() {
    }

    @Around(value = "targetPointcut() && cacheAllPointcut()")
    public Object findAllMenuAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String key = getAllMenuKey();
        List<Resource> resourceList = get(key);
        if(resourceList != null){
            return resourceList;
        }
        resourceList = (List<Resource>) proceedingJoinPoint.proceed();
        put(key, resourceList);
        return resourceList;
    }

    /**
     * 移动后清除缓存
     */
    @Pointcut(value ="execution(* move(..))")
    protected void cacheMoveEvictPointcut() {
    }

    @After(value =  "targetPointcut() && cacheMoveEvictPointcut()")
    protected void cacheEvictAllAdvice(){
        clear();
    }

    /**
     * 添加进缓存同时清掉列表缓存
     * @param key
     * @param obj
     */
    @Override
    protected void cachePut(String key, Object obj) {
        super.cachePut(key, obj);
        evict(getAllMenuKey());
    }
    /**
     * 缓存清除同时清掉列表缓存
     * @param key
     */
    @Override
    protected void cacheEvict(String key) {
        super.cacheEvict(key);
        evict(getAllMenuKey());
    }

    private String getAllMenuKey() {
        return cacheName+":"+"allMenu";
    }

}
