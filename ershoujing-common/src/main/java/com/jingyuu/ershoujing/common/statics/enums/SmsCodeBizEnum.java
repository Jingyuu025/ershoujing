package com.jingyuu.ershoujing.common.statics.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * 验证码业务类型枚举
 *
 * @author owen
 * @date 2017-09-07
 */
@Getter
public enum SmsCodeBizEnum {
    REGISTER             (1, "注册","register"),
    LOGIN                (2, "登录","login"),
    ;

    private final int value;
    private final String name;
    private final String alias;

    SmsCodeBizEnum(int value, String name, String alias) {
        this.value = value;
        this.name = name;
        this.alias = alias;
    }

    public static SmsCodeBizEnum fromValue(Integer value) {
        if (null == value) {
            return null;
        }

        return Stream.of(SmsCodeBizEnum.values())
                .filter(val -> value.equals(val.getValue()))
                .findFirst()
                .orElse(null);
    }
}
