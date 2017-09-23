package com.jingyuu.ershoujing.service.support.aspect;

import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Chenlm on 7/1/16.
 */
public class AspectPointcut {
    /**
     * 业务切点
     */
    @Pointcut("execution(* com.jingyuu.ershoujing.service..*.*(..))")
    public void servicePointcut() {
    }

    /**
     * 控制器入参切点
     */
    @Pointcut("execution(* com.jingyuu.ershoujing.web.controller..*.*(..)) && " +
            "@annotation(com.jingyuu.ershoujing.service.support.annotation.Log)")
    public void controllerLogPointcut() {
    }

    /**
     * 无参切点
     */
    @Pointcut("args()")
    public void noArgs() {
    }

}
