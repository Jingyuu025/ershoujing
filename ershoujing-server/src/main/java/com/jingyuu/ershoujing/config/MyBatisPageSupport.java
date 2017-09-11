package com.jingyuu.ershoujing.config;

import com.github.pagehelper.PageHelper;
import com.jingyuu.ershoujing.common.page.PageQuery;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * MyBatis 分页支持
 */
@Aspect
@Component
public class MyBatisPageSupport {
    /**
     * 分页方法切点
     */
    @Pointcut("execution(public com.jingyuu.ershoujing.common.page.PageBean *(com.jingyuu.ershoujing.common.page.PageQuery))")
    public void pageMethod() {
    }

    /**
     * 需要分页的业务类切点
     */
    @Pointcut("execution(public * com.jingyuu.ershoujing.service..*.*(..)) ")
    public void serviceMethod() {
    }


    // 分页业务
    @Around(value = "serviceMethod() && pageMethod()")
    public Object mybatisPageProcess(ProceedingJoinPoint pjp) throws Throwable {
        // 分页参数
        PageQuery pageQuery = (PageQuery) pjp.getArgs()[0];
        // 分页
        PageHelper.offsetPage(pageQuery.getStartIndex(), pageQuery.getPageSize());
        return pjp.proceed();
    }
}
