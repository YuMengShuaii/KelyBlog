package com.kowa.app.aop;

import com.kowa.app.context.ContextHolder;
import com.kowa.app.jsonmodel.JsonUtils;
import com.kowa.app.po.UserPo;
import com.kowa.app.vo.UserVo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoginHelperAspect {
    /**
     * 方法切入
     */
    @Pointcut("execution(@com.kowa.app.aop.LoginHelper * *(..))")
    public void methodAnnotated() {
    }

    @Around("methodAnnotated()")//在连接点进行方法替换
    public Object aroundJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        if (ContextHolder.getCurrentMember() == null) {
            return JsonUtils.getErrorJson("未登录!");
        } else {
            return joinPoint.proceed();
        }
    }
}
