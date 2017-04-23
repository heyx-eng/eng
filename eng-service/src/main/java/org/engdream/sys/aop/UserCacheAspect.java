package org.engdream.sys.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.engdream.base.aop.AbstractCacheAspect;
import org.engdream.sys.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by heyx on 2017/4/19.
 */
@Component
@Aspect
public class UserCacheAspect extends AbstractCacheAspect<User, Long> {
    public UserCacheAspect() {
        setCacheName("user");
    }

    /**
     * 匹配用户Service
     */
    @Override
    @Pointcut(value = "target(org.engdream.sys.service.UserService)")
    protected void targetPointcut() {
    }

    /**
     * put 或 get
     * 比如查询
     */
    @Override
    @Pointcut(value ="execution(* findByUsername(*))"+
            "|| execution(* findById(*))")
    protected void cacheablePointcut() {
    }

    @Override
    @Around(value = "targetPointcut() && cacheablePointcut()")
    public User cacheableAdvice(ProceedingJoinPoint pjp) throws Throwable {
        if(pjp.getArgs()[0] == null){
            return null;
        }
        String arg = String.valueOf(pjp.getArgs()[0]);
        String key = cacheName + ":" + arg;
        User user = get(key);
        if(user != null){
            return user;
        }
        user = (User) pjp.proceed();
        put(key, user);
        return user;
    }
}
