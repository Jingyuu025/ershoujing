package com.jingyuu.ershoujing.service.support.event;

import lombok.Builder;
import lombok.Data;

/**
 * @author owen
 * @date 2017-09-21
 */
@Data
public class ActionEvent extends AbstractEvent implements JyuEvent {
    /**
     * 行为
     */
    private String action;

    /**
     * 路径
     */
    private String path;

    /**
     * 参数
     */
    private String parameter;

    @Builder
    public ActionEvent(String userId, String action, String path, String parameter, String ip) {
        super(userId, ip);
        this.action = action;
        this.parameter = parameter;
        this.path = path;
    }
}


