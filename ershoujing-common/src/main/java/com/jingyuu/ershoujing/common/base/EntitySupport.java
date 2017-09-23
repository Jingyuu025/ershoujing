package com.jingyuu.ershoujing.common.base;

import org.springframework.util.ReflectionUtils;

/**
 * 实体支持类
 * 属性拷贝
 */
public class EntitySupport {

    /**
     * 属性合并
     *
     * @param beforeObject 原对象
     * @param afterObject  目标对象
     * @param <T>
     */
    public static <T> T merge(final T beforeObject, final T afterObject) {
        return merge(beforeObject, afterObject, null);
    }

    /***
     *
     * 属性合并
     *
     * 以beforeObject为基准 合并afterObject
     *
     * @param beforeObject - 修改前的Entity
     * @param afterObject  - 待修改的内容
     * @param fieldFilter  - 过滤字段
     * @param <T>
     */
    public static <T> T merge(final T beforeObject, final T afterObject, ReflectionUtils.FieldFilter fieldFilter) {
        try {
            final T result = (T) beforeObject.getClass().newInstance();

            ReflectionUtils.doWithFields(beforeObject.getClass(), field -> {
                ReflectionUtils.makeAccessible(field);
                ReflectionUtils.setField(field, result, ReflectionUtils.getField(field, beforeObject)); // 初始值，从beforeObject读取

                Object afterFieldValue = ReflectionUtils.getField(field, afterObject);
                if (null != afterFieldValue) {
                    ReflectionUtils.setField(field, result, ReflectionUtils.getField(field, afterObject)); // 用afterObject中值来覆盖
                }
            }, fieldFilter);
            return result;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

