package com.jingyuu.ershoujing.service.support.aspect;

import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.common.utils.IPUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserSessionEntity;
import com.jingyuu.ershoujing.service.support.annotation.Log;
import com.jingyuu.ershoujing.service.support.event.ActionEvent;
import com.jingyuu.ershoujing.service.user.UserSessionService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author owen
 * @date 2017-09-22
 */
@Slf4j
@Aspect
@Component
public class ControllerLogAspect {
    // 基本数据类型及其包装器，字符串
    private static String[] BASIC_TYPE_AND_WRAPPER_WITH_STRING = {
            "java.lang.Integer", "int",
            "java.lang.Long", "long",
            "java.lang.Short", "short",
            "java.lang.Double", "double",
            "java.lang.Float", "float",
            "java.lang.Byte", "byte",
            "java.lang.Boolean", "boolean",
            "java.lang.Char", "char",
            "java.lang.String"
    };

    // 敏感的属性名称
    private static String[] SENSITIVE_NAME = {
            "password"
    };

    @Autowired
    private UserSessionService userSessionService;
    @Autowired
    private ApplicationEventPublisher eventPublisher;


    @Pointcut("com.jingyuu.ershoujing.service.support.aspect.AspectPointcut.controllerLogPointcut()")
    public void controllerLogPointcut() {
    }

    /**
     * @param joinPoint 连接点
     */
    @After(value = "controllerLogPointcut()")
    public void logController(JoinPoint joinPoint) throws Exception {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String ip = IPUtil.getIpAddress(request); // IP
        String token = request.getHeader(JyuConstant.TOKEN_HEADER); // Token
        if (CommonUtil.isEmpty(token)) {
            return;
        }

        // 查询用户会话信息
        UserSessionEntity userSessionEntity = userSessionService.loadByAccessToken(token);
        String userId = userSessionEntity.getUserId(); // 用户编号

        Object target = joinPoint.getTarget(); // 目标对象

        // 行为
        String action = null;
        Log log = AnnotationUtils.findAnnotation(((MethodSignature) joinPoint.getSignature()).getMethod(), Log.class);
        if (CommonUtil.isNotEmpty(log)) {
            action = log.value();
        }

        // 请求地址
        // 类上的RequestMapping
        String[] classRequestMappingValues = AnnotationUtils.findAnnotation(target.getClass(), RequestMapping.class).value();
        String classRequestMappingValue = classRequestMappingValues[0];// 默认取第一个

        // 方法上的RequestMapping
        Method methodRequestMapping = ((MethodSignature) joinPoint.getSignature()).getMethod();// 获取方法
        String[] methodRequestMappingValues = AnnotationUtils.findAnnotation(methodRequestMapping, RequestMapping.class).value();


        // 请求参数
        List<String> requestParameterList = new ArrayList<>();
        Object[] parameterValues = joinPoint.getArgs(); // 请求参数数组
        Class[] parameterClasses = ((MethodSignature) joinPoint.getSignature()).getParameterTypes(); // 参数类型数组
        if (CommonUtil.isNotEmpty(parameterValues) && parameterValues.length != 0) {
            for (int i = 0, j = parameterValues.length; i < j; i++) {
                Class parameterClass = parameterClasses[i]; // 参数类型

                // 基本类型
                if (Arrays.asList(BASIC_TYPE_AND_WRAPPER_WITH_STRING).contains(parameterClass.getName())) {
                    requestParameterList.add(this.decoratorFieldValue(parameterClass.getName(), String.valueOf(parameterValues[i])));
                    continue;
                }

                // 非基本类型
                Field[] fileds = parameterClasses[i].getDeclaredFields(); // 属性数组
                for (Field field : fileds) {
                    field.setAccessible(true); // 设置accessible

                    String fieldName = field.getName(); // 属性名称
                    Object fieldValue = field.get(parameterValues[i]); // 属性值
                    if (CommonUtil.isNotEmpty(fieldValue)) {
                        requestParameterList.add(this.decoratorFieldValue(fieldName, String.valueOf(fieldValue)));
                    }
                }
            }
        }

        // 发布行为事件
        eventPublisher.publishEvent(
                ActionEvent.builder()
                        .token(token)
                        .userId(userId)
                        .action(action)
                        .path(
                                Stream.of(methodRequestMappingValues)
                                        .map(classRequestMappingValue::concat)
                                        .collect(Collectors.joining(",")))
                        .parameter(
                                requestParameterList.stream()
                                        .collect(Collectors.joining(",", "{", "}")))
                        .ip(ip)
                        .build()
        );
    }

    /**
     * 装饰属性值
     * 过滤敏感的属性值，比如密码
     *
     * @param fieldName  属性名称
     * @param fieldValue 属性值
     * @return
     */
    private String decoratorFieldValue(String fieldName, String fieldValue) {
        boolean decorator = false;
        for (String name : Arrays.asList(SENSITIVE_NAME)) {
            if (fieldName.toLowerCase().contains(name.toLowerCase())) {
                decorator = true;
            }
        }
        return fieldName.concat(":").concat(decorator ? "******" : fieldValue);
    }
}
