package org.engdream.base.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.engdream.base.entity.BaseEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by heyx on 2017/4/19.
 */
public abstract class AbstractCacheAspect<T extends BaseEntity<ID>, ID extends Serializable> extends BaseCacheAspect {

    protected abstract void targetPointcut();

    @Pointcut(value = "execution(* findById(*))")
    protected void cacheablePointcut(){
    }

    @Around(value = "targetPointcut() && cacheablePointcut()")
    protected T cacheableAdvice(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        Object arg = proceedingJoinPoint.getArgs()[0];
        String key = cacheName+":"+arg;
        T t = get(key);
        if(t != null){
            return t;
        }
        t = (T) proceedingJoinPoint.proceed();
        cachePut(key, t);
        return t;
    }

    @Pointcut(value =
            "execution(* save(..))"+
                    "||execution(* update(..))"+
                    "||execution(* updateById(..))")
    protected void cachePutPointcut() {
    }

    @AfterReturning(value = "targetPointcut() && cachePutPointcut()", argNames = "t", returning = "t")
    protected void cachePutAdvice(Object t) throws Throwable{
        cachePut(getKey((T) t), t);
    }

    /**
     * 批量保存时直接清空缓存
     */
    @Pointcut(value =
            "execution(* saveBatch(..))"+
                    "|| execution(* updateBatchById(..))")
    protected void cacheEvictAllPointcut() {
    }

    @After(value =  "targetPointcut() && cacheEvictAllPointcut()")
    protected void cacheEvictAllAdvice(){
        clear();
    }

    /**
     * 删除时清除缓存
   /*  */
    @Pointcut(value ="execution(* deleteById(*)) && args(id)"+
            "|| execution(* markDelete(*)) && args(id)", argNames = "id")
    protected void cacheEvictPointcut(ID id) {
    }

    @Before(value = "targetPointcut() && cacheEvictPointcut(id)", argNames = "id")
    protected void cacheEvictAdvice(ID id){
        cacheEvict(getKey(id));
    }

    /**
     * 批量删除时清除缓存
     */
    @Pointcut(value ="execution(* deleteBatchIds(*))&&args(idList)", argNames = "idList")
    protected void cacheEvictListPointcut(List<ID> idList) {
    }

    @Before(value = "targetPointcut() && cacheEvictListPointcut(idList)", argNames = "idList")
    protected void cacheEvictListAdvice(List<ID> idList){
        for(ID id : idList){
            cacheEvict(getKey(id));
        }
    }

    /**
     * 考虑到添加和删除缓存时，子类可覆盖此方法清除 list 缓存
     * @param key
     * @param obj
     */
    protected void cachePut(String key, Object obj) {
        put(key, obj);
    }
    /**
     * 考虑到添加和删除缓存时，子类可覆盖此方法清除 list 缓存
     * @param key
     */
    protected void cacheEvict(String key){
        evict(key);
    }

    private String getKey(T t) {
        return getKey(t.getId());
    }
    private String getKey(ID id) {
        return cacheName+":"+id;
    }
}
