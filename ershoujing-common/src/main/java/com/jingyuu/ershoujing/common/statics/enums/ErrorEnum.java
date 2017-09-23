package com.jingyuu.ershoujing.common.statics.enums;

import lombok.Getter;

/**
 * @author owen
 * @date 2017-09-07
 */
@Getter
public enum ErrorEnum {
    ARGS_IS_EMPTY                               (0x1001, "参数为空"),
    ARGS_IS_ERROR                               (0x1002, "参数错误"),
    DATA_REPEAT_ERROR                           (0x1003, "数据重复"),
    DATA_NOT_FOUND                              (0x1004, "数据不存在"),
    DATA_IS_ERROR                               (0x1005, "数据异常"),
    NOT_ALLOWED_ACTION                          (0x1006, "不允许的操作"),
    UNKNOW_ERROR                                (0x1007, "系统未知异常"),
    RPC_EXCEPTION                               (0x1008, "服务器内部通信异常"),
    UPLOAD_SIZE_EXCEEDED                        (0x1009, "上传文件大小超过限制"),
    HTTP_MESSAGE_NOT_READABLE                   (0x1010, "HTTP消息不可读"),
    DATA_UPDATE_FAILED                          (0x1011, "数据更新失败"),


    USER_SESSION_TOKEN_EXPIRED                  (0x2001, "用户访问令牌过期"),

    ;
    private final Integer errCode;

    private final String errMessage;

    ErrorEnum(Integer errCode, String errMessage) {
        this.errCode = errCode;
        this.errMessage = errMessage;
    }
}
