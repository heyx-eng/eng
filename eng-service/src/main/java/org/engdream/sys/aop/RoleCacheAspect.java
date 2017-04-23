package org.engdream.sys.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.engdream.base.aop.AbstractCacheAspect;
import org.engdream.sys.entity.Role;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by heyx on 2017/4/19.
 */
@Component
@Aspect
public class RoleCacheAspect extends AbstractCacheAspect<Role, Long> {

    public RoleCacheAspect() {
        setCacheName("role");
    }

    @Pointcut(value = "target(org.engdream.sys.service.RoleService)")
    protected void targetPointcut() {
    }

    /**
     * 缓存全部角色
     */
    @Pointcut(value = "execution(* findAll())")
    private void cacheAllpointcut() {
    }

    @Around(value = "targetPointcut() && cacheAllpointcut()")
    public Object cacheAllAdvice(ProceedingJoinPoint pjp) throws Throwable {
        String key = cacheName+":"+"all";
        List<Role> list = get(key);
        if(list != null){
            return list;
        }
        list = (List<Role>) pjp.proceed();
        put(key, list);
        return list;
    }

    /**
     * 添加进缓存同时清掉列表缓存
     * @param key
     * @param obj
     */
    @Override
    protected void cachePut(String key, Object obj) {
        super.cachePut(key, obj);
        evict(cacheName+":"+"all");
    }
    /**
     * 缓存清除同时清掉列表缓存
     * @param key
     */
    @Override
    protected void cacheEvict(String key) {
        super.cacheEvict(key);
        evict(cacheName+":"+"all");
    }
}
