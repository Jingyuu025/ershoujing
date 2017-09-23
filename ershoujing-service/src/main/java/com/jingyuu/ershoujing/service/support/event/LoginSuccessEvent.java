package com.jingyuu.ershoujing.service.support.event;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * @author owen
 * @date 2017-09-13
 */
@Data
@AllArgsConstructor
public class LoginSuccessEvent extends AbstractEvent implements JyuEvent {
    @Builder
    public LoginSuccessEvent(String userId, String ip) {
        super(userId, ip);
    }
}
