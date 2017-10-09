package com.jingyuu.ershoujing.common.statics.enums;

import lombok.Getter;

import java.util.stream.Stream;

/**
 * @author owen
 * @date 2017-09-07
 */
@Getter
public enum FileStateEnum {
    STORAGE_LOCAL             (1, "本地存储"),
    STORAGE_REMOTE            (2, "远程存储"),
    DELETE                    (9, "删除"),
    ;

    private final int value;
    private final String name;

    FileStateEnum(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public static FileStateEnum fromValue(Integer value) {
        if (null == value) {
            return null;
        }

        return Stream.of(FileStateEnum.values())
                .filter(val -> value.equals(val.getValue()))
                .findFirst()
                .orElse(null);
    }
}
