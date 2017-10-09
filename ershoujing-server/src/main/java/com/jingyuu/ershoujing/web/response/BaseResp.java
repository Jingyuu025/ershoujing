package com.jingyuu.ershoujing.web.response;

import lombok.*;

/**
 * 返回消息
 *
 * @version 2016-04-19-09:30
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseResp<T> {
    @Getter
    private Integer retCode;
    @Getter
    private String retMsg;
    @Getter
    private T data;

    /**
     * ok
     *
     * @param data 响应内容
     * @param <T>  响应内容类型
     * @return
     */
    public static <T> BaseResp<T> ok(T data) {
        return BaseResp.<T>builder()
                .data(data)
                .retCode(0)
                .retMsg("操作成功")
                .build();
    }

    /**
     * ok
     *
     * @return
     */
    public static BaseResp ok() {
        return ok(null);
    }

    /**
     * fail
     *
     * @param failCode 错误编码
     * @param failMsg  错误消息
     */
    public static BaseResp fail(Integer failCode, String failMsg) {
        return BaseResp.builder()
                .retCode(failCode)
                .retMsg(failMsg)
                .build();
    }


    /**
     * fail
     */
    public static BaseResp fail() {
        return fail(null);
    }

    /**
     * fail
     *
     * @param data 响应内容
     * @param <T>  响应内容类型
     */
    public static <T> BaseResp<T> fail(T data) {
        return BaseResp.<T>builder()
                .retCode(9999)
                .retMsg("操作失败")
                .data(data)
                .build();
    }

}
