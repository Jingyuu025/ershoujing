package com.jingyuu.ershoujing.common.statics.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author owen
 * @date 2017-09-13
 */
@Getter
public enum TerminalEnum {

    PC             (1, "电脑端"),
    MOBILE         (2, "移动端"),
    ;

    private final int value;
    private final String name;

    TerminalEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TerminalEnum fromValue(Integer value) {
        if (null == value) {
            return null;
        }

        return Stream.of(TerminalEnum.values())
                .filter(val -> value.equals(val.getValue()))
                .findFirst()
                .orElse(null);
    }
}
