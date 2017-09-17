package com.kowa.app.aop;

import com.kowa.app.context.ContextHolder;
import com.kowa.app.jsonmodel.JsonUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AppAspect {
    /**
     * LoginHelper方法切入
     */
    @Pointcut("execution(@com.kowa.app.aop.LoginHelper * *(..))")
    public void loginHelpermethodAnnotated() {
    }

    @Around("loginHelpermethodAnnotated()")//在连接点进行方法替换
    public Object loginHelper(ProceedingJoinPoint joinPoint) throws Throwable {
        if (ContextHolder.getCurrentMember() == null) {
            return JsonUtils.getErrorJson("未登录!");
        } else {
            return joinPoint.proceed();
        }
    }

    @Pointcut("execution(@com.kowa.app.aop.MemoryCache * *(..))")
    public void memoryCacheMethodAnnotated(){

    }

    @Around("memoryCacheMethodAnnotated()")
    public Object memoryCache(ProceedingJoinPoint joinPoint)throws Throwable{
        return null;
    }
}
