package com.imooc.web.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @date 2020/5/7
 */
@Aspect
@Component
public class TimeAspect {

    @Around("execution(* com.imooc.web.controller.UserController.*(..))")
    public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("time aspect start");
        //获取传的参数
        Object[] args = pjp.getArgs();
        for (Object arg: args) {
            System.out.println("arg is " + arg);
        }
        long start = new Date().getTime();

        //进入controller的方法
        Object object = pjp.proceed();
        System.out.println("time aspect 耗时：" + (new Date().getTime() - start));

        System.out.println("time aspect end");

        return object;
    }
}
