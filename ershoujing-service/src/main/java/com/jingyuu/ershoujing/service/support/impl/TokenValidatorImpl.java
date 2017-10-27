package com.jingyuu.ershoujing.service.support.impl;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.common.statics.enums.ErrorEnum;
import com.jingyuu.ershoujing.common.utils.CommonUtil;
import com.jingyuu.ershoujing.service.support.HttpValidator;
import com.jingyuu.ershoujing.service.support.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static com.jingyuu.ershoujing.common.statics.constants.RedisKeyConstant.USER_SESSION;

/**
 * @author owen
 * @date 2017-09-29
 */
@Component
public class TokenValidatorImpl implements HttpValidator {
//    @Autowired
//    private UserSessionService userSessionService;
    @Autowired
    private RedisService redisService;

    @Override
    public void validate(HttpServletRequest request) throws JyuException {
        String token = request.getHeader(JyuConstant.TOKEN_HEADER); // 获取访问令牌
        if (CommonUtil.isEmpty(token)) {
            throw new JyuException(ErrorEnum.ARGS_IS_EMPTY, "访问令牌缺失");
        }

        String tokenCache = (String) redisService.get(USER_SESSION + token); // key： "user:session:access_token"
        if (CommonUtil.isEmpty(tokenCache)) {
            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "访问令牌无效或过期");
        }

//        // 查询用户会话信息
//        UserSessionEntity userSessionEntity = userSessionService.getByAccessToken(token);
//        if (CommonUtil.isEmpty(userSessionEntity)) {
//            throw new JyuException(ErrorEnum.DATA_IS_ERROR, "访问令牌无效或过期");
//        }
    }

}
