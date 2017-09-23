package com.jingyuu.ershoujing.common.statics.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * 短信状态枚举
 *
 * @author owen
 * @date 2017-09-07
 */
@Getter
public enum SmsStateEnum {
    DEALING             (1, "发送处理中"),
    SUCCESS             (2, "发送成功"),
    FAIL                (3, "发送失败"),
    ;

    private final int value;
    private final String name;

    SmsStateEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static SmsStateEnum fromValue(Integer value) {
        if (null == value) {
            return null;
        }

        return Stream.of(SmsStateEnum.values())
                .filter(val -> value.equals(val.getValue()))
                .findFirst()
                .orElse(null);
    }
}
