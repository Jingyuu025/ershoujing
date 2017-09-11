package com.jingyuu.ershoujing.common.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;

/**
 * @author owen
 * @date 2017-09-07
 */
@Slf4j
public class BaseBean implements Serializable {
    /**
     * 对象转换
     *
     * @param srcObj       元对象
     * @param requiredType 转换类型
     */
    public <T> T fromBean(Object srcObj, Class<T> requiredType) {
        try {
            T targetObj = requiredType.newInstance();
            BeanUtils.copyProperties(srcObj, targetObj);
            return targetObj;
        } catch (Exception e) {
            log.error("对象转换异常，异常信息:{}", e.getMessage());
            return null;
        }
    }

    /**
     * 转换成目标类型的对象
     *
     * @param requiredType 目标类型
     */
    public <T> T toBean(Class<T> requiredType) {
        return this.fromBean(this, requiredType);
    }
}