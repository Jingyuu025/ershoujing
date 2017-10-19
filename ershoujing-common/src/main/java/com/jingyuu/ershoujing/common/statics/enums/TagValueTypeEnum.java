package com.jingyuu.ershoujing.common.statics.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author owen
 * @date 2017-09-13
 */
@Getter
public enum TagValueTypeEnum {
    SHORT             (1, "短值"),
    LONG              (2, "长值"),
    ;

    private final int value;
    private final String name;

    TagValueTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TagValueTypeEnum fromValue(Integer value) {
        if (null == value) {
            return null;
        }

        return Stream.of(TagValueTypeEnum.values())
                .filter(val -> value.equals(val.getValue()))
                .findFirst()
                .orElse(null);
    }
}
