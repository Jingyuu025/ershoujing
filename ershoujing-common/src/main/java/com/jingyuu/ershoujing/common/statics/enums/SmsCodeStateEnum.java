package com.jingyuu.ershoujing.common.statics.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * 验证码状态枚举
 *
 * @author owen
 * @date 2017-09-07
 */
@Getter
public enum SmsCodeStateEnum {
    WAITING_VERIFY       (1, "等待验证"),
    VERIFIED             (2, "验证通过"),
    ;

    private final int value;
    private final String name;

    SmsCodeStateEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static SmsCodeStateEnum fromValue(Integer value) {
        if (null == value) {
            return null;
        }

        return Stream.of(SmsCodeStateEnum.values())
                .filter(val -> value.equals(val.getValue()))
                .findFirst()
                .orElse(null);
    }
}
