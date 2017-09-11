package com.jingyuu.ershoujing.web.controller;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.utils.IPUtil;
import com.jingyuu.ershoujing.dao.jpa.entity.UserEntity;
import com.jingyuu.ershoujing.dao.mybatis.bo.LoginBo;
import com.jingyuu.ershoujing.service.LoginService;
import com.jingyuu.ershoujing.service.user.UserService;
import com.jingyuu.ershoujing.web.request.LoginResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author owen
 * @date 2017-09-07
 */
@Api(value = "登录、登出", tags = "登录、登出")
@RestController
@RequestMapping(value = "/")
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private LoginService loginService;

    /**
     * 登录
     *
     * @param loginResult
     * @return
     */
    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<String> login(@RequestBody @Valid LoginResult loginResult, HttpServletRequest request) throws JyuException {
        LoginBo loginBo = LoginBo.builder()
                .telephone(loginResult.getTelephone())
                .password(loginResult.getPassword())
                .ip(IPUtil.getIpAddress(request))
                .build();
        UserEntity userEntity = loginService.login(loginBo);

        String token = request.getSession().getId();

        return ResponseEntity.ok(token);
    }
}
