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
public enum TagKeyTypeEnum {
    BASE_INFORMATION                (1, "基本信息"),
    APPERAANCE                      (2, "外观成色"),
    FUNCTION_PROBLEM                (3, "功能性问题"),
    ;

    private final int value;
    private final String name;

    TagKeyTypeEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static TagKeyTypeEnum fromValue(Integer value) {
        if (null == value) {
            return null;
        }

        return Stream.of(TagKeyTypeEnum.values())
                .filter(val -> value.equals(val.getValue()))
                .findFirst()
                .orElse(null);
    }
}
