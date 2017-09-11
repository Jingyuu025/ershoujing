package com.jingyuu.ershoujing.common.statics.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author owen
 * @date 2017-09-07
 */
@Getter
public enum RoleEnum {
    CUSTOMER             (1, "客户"),
    BUSINESS             (2, "商户"),
    EXPRESS              (3, "物流师傅"),
    STORE_MAN            (4, "库管专员"),
    CUSTOMER_STAFF       (5, "客服专员"),
    ADMIN                (6, "系统管理员")
    ;

    private final int value;
    private final String name;

    RoleEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static RoleEnum fromValue(Integer value) {
        if (null == value) {
            return null;
        }

        return Stream.of(RoleEnum.values())
                .filter(val -> value.equals(val.getValue()))
                .findFirst()
                .orElse(null);
    }
}
