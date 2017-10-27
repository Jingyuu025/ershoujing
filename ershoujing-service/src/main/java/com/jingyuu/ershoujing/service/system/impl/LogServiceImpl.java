package com.jingyuu.ershoujing.service.system.impl;

import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.sytem.LogEntity;
import com.jingyuu.ershoujing.dao.jpa.entity.user.UserEntity;
import com.jingyuu.ershoujing.dao.jpa.repository.system.LogRepository;
import com.jingyuu.ershoujing.service.support.event.ActionEvent;
import com.jingyuu.ershoujing.service.system.LogService;
import com.jingyuu.ershoujing.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author owen
 * @date 2017-09-21
 */
@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private UserService userService;
    @Autowired
    private LogRepository logRepository;

    /**
     * 监听行为事件异步记录日志
     *
     * @param event
     */
    @Async
    @EventListener
    @Transactional(isolation = Isolation.READ_COMMITTED, rollbackFor = Exception.class)
    public void onAction(ActionEvent event) {
        String token = event.getToken(); // 令牌
        String userId = event.getUserId(); // 用户编号

        // 查询用户详情
        UserEntity userEntity = null;
        if (CommonUtil.isNotEmpty(token)) {
            userEntity = userService.get(token, userId);
        } else {
            userEntity = userService.get(userId);
        }

        // 保存日志
        logRepository.save(
                LogEntity.builder()
                        .userId(event.getUserId())
                        .nickName(userEntity.getNickName())
                        .telephone(userEntity.getTelephone())
                        .action(event.getAction())
                        .path(event.getPath())
                        .parameter(event.getParameter())
                        .ip(event.getIp())
                        .build()
        );
    }
}
