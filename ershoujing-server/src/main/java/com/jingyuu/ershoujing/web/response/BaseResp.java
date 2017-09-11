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
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResp<T> ok(T data) {
        return BaseResp.<T>builder()
                .data(data)
                .retCode(0)
                .retMsg("操作成功")
                .<T>build();
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

}
