package com.jingyuu.ershoujing.common.statics.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author owen
 * @date 2017-09-08
 */
@Getter
public enum UserStateEnum {
    OK             (1, "正常"),
    LOCKED         (2, "锁定"),
    BAN            (3, "禁用"),
    ;

    private final int value;
    private final String name;

    UserStateEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static UserStateEnum fromValue(Integer value) {
        if (null == value) {
            return null;
        }

        return Stream.of(UserStateEnum.values())
                .filter(val -> value.equals(val.getValue()))
                .findFirst()
                .orElse(null);
    }
}
