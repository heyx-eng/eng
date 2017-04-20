package org.engdream.sys.cache;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.engdream.base.cache.BaseCacheAspect;
import org.engdream.sys.entity.User;
import org.springframework.stereotype.Component;

/**
 * Created by heyx on 2017/4/19.
 */
@Component
@Aspect
public class UserCacheAspect extends BaseCacheAspect {
    public UserCacheAspect() {
        System.out.println("cache running.....");
        setCacheName("sys-userCache");
    }

    /**
     * 匹配用户Service
     */
    @Pointcut(value = "target(org.engdream.sys.service.impl.UserServiceImpl)")
    private void userServicePointcut() {
    }

    /**
     * only put
     * 如 新增 修改 登录 改密 改状态  只有涉及修改即可
     */
    @Pointcut(value =
            "execution(* newUser(..)) " +
                    "|| execution(* insert(..)) ")
    private void cachePutPointcut() {
    }


    /**
     * put 或 get
     * 比如查询
     */
    @Pointcut(value =
            "(execution(* findByUsername(*)))")
    private void cacheablePointcut() {
    }

    @Around(value = "userServicePointcut() && cacheablePointcut()")
    public Object cacheableAdvice(ProceedingJoinPoint pjp) throws Throwable {

        String key = "";
        User user = get(key);
        //cache hit
        if (user != null) {
            log.debug("cacheName:{}, hit key:{}", cacheName, key);
            return user;
        }
        log.debug("cacheName:{}, miss key:{}", cacheName, key);

        user = (User) pjp.proceed();

        put(user);
        System.out.println(user);
        return user;

    }
    public void put(User user) {
        if (user == null) {
            return;
        }
        Long id = user.getId();
        super.put("user"+id, user);
    }
}
