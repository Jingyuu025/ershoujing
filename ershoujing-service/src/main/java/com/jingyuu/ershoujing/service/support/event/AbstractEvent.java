package com.jingyuu.ershoujing.service.support.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author owen
 * @date 2017-09-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
abstract class AbstractEvent {
    /**
     * 用户编号
     */
    private String userId;

    /**
     * IP地址
     */
    private String ip;
}
