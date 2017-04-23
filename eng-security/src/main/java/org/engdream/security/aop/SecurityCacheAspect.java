package org.engdream.security.aop;

import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.engdream.base.aop.BaseCacheAspect;

/**
 * Created by heyx on 2017/4/22.
 * 角色-资源-菜单 缓存管理
 */
public class SecurityCacheAspect extends BaseCacheAspect {

    public SecurityCacheAspect(){
        setCacheName("securityCache");
    }

    @Pointcut(value = "execution(* findAllMenu())||" +
            "execution(* findById(*)) ||" +
            "execution(* findAll(*))")
    private void resourceCacheablePointcut(){}

    @Pointcut(value = "execution(* save(*))")
    private void resourceCachePutPointcut(){}

    @Pointcut(value = "execution(* delete*(..)) ||" +
            "execution(* update*(..))")
    private void resourceCacheEvictAllPointcut(){}

    @Before(value = "resourcePointcut() && resourceCacheEvictAllPointcut()")
    private void resourceCacheEvictAllAdvice(){
        clear();
    }

    @Pointcut(value = "target(org.engdream.sys.service.RoleService)")
    private void rolePointcut(){}

    @Pointcut(value = "execution(* findAll())||" +
            "execution(* findById(*))")
    private void roleCacheablePointcut(){}

    @Pointcut(value = "execution(* save(*))")
    private void roleCachePutPointcut(){}

    @Pointcut(value = "execution(* delete*(..)) ||" +
            "execution(* update*(..))")
    private void roleCacheEvictAllPointcut(){}




}
