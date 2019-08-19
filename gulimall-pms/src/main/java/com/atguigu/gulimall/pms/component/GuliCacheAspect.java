package com.atguigu.gulimall.pms.component;

import com.alibaba.fastjson.JSON;
import com.atguigu.gulimall.pms.annotation.GuliCache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.aspectj.lang.Signature;

import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@Aspect
@Component
public class GuliCacheAspect {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private ReentrantLock lock = new ReentrantLock();

    @Around("@annotation(com.atguigu.gulimall.pms.annotation.GuliCache)")
    public Object aroundCache(ProceedingJoinPoint points)
    {
        Object result = null;
        try{
            Object[] args = points.getArgs();
            //拿到注解值
            MethodSignature methods = (MethodSignature) points.getSignature();
            GuliCache guliCache = methods.getMethod().getAnnotation(GuliCache.class);
            System.out.println("guliCache：" + guliCache);
            if(guliCache == null) {
                log.info("无需缓存");
                return points.proceed(args);//无需缓存
            }
            //需要缓存
            String prefix = guliCache.pexfix();
            if(args != null)
            {
                for (Object arg: args)
                    prefix += ":" + arg.toString();
            }
            Object cache = getFromCache(prefix, methods);
            log.info("cache：" + cache);
            if(cache != null)
                return cache;//缓存存在直接返回
            //lock锁
            lock.lock();
            cache = getFromCache(prefix, methods);
            if(cache == null)
            {
                log.info("缓存没命中.....");
                result = points.proceed(args);
                redisTemplate.opsForValue().set(prefix, JSON.toJSONString(result));
                return result;
            }
            return cache;
        } catch (Throwable e) {
            log.error(e.getMessage());
        } finally {
            log.info("缓存切面介入工作....后置通知");
            if(lock.isLocked())
                lock.unlock();//唤醒锁机制
        }
        return result;
    }

    private Object getFromCache(String prefix, Signature signature) {
        Object objectJSON = null;
        String cache = redisTemplate.opsForValue().get(prefix);
        if(!StringUtils.isEmpty(cache))
        {
            Class type = ((MethodSignature)signature).getReturnType();
            objectJSON = JSON.parseObject(cache, type);
        }
        return objectJSON;
    }
}
