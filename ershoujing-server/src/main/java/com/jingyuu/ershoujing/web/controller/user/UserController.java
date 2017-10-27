package com.jingyuu.ershoujing.web.controller.user;

import com.jingyuu.ershoujing.common.exception.JyuException;
import com.jingyuu.ershoujing.common.statics.constants.JyuConstant;
import com.jingyuu.ershoujing.common.statics.enums.TerminalEnum;
import com.jingyuu.ershoujing.common.utils.IPUtil;
import com.jingyuu.ershoujing.dao.mybatis.bo.LoginBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.ModifyPasswordBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.RegisterBo;
import com.jingyuu.ershoujing.dao.mybatis.bo.RetrievalPasswordBo;
import com.jingyuu.ershoujing.dao.mybatis.vo.LoginVo;
import com.jingyuu.ershoujing.service.support.annotation.Log;
import com.jingyuu.ershoujing.service.user.UserService;
import com.jingyuu.ershoujing.web.controller.BaseController;
import com.jingyuu.ershoujing.web.domain.User;
import com.jingyuu.ershoujing.web.request.LoginRequest;
import com.jingyuu.ershoujing.web.request.ModifyPasswordRequest;
import com.jingyuu.ershoujing.web.request.RegisterRequest;
import com.jingyuu.ershoujing.web.request.RetrievalPasswordRequest;
import com.jingyuu.ershoujing.web.response.BaseResp;
import com.jingyuu.ershoujing.web.response.LoginResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * @author owen
 * @date 2017-09-07
 */
@Api(tags = "用户")
@RestController
@RequestMapping(value = "/user")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<LoginResult>> login(@RequestBody @Valid LoginRequest loginRequest,
                                                       HttpServletRequest request) throws JyuException {
        TerminalEnum terminal = TerminalEnum.fromValue(loginRequest.getTerminal()); // 终端
        // 构建登录Bo
        LoginBo loginBo = LoginBo.builder()
                .terminalEnum(terminal) // 终端
                .telephone(loginRequest.getTelephone())
                .password(loginRequest.getPassword())
                .ip(IPUtil.getIpAddress(request))
                .build();

        // 登录
        LoginVo loginVo = userService.login(loginBo);
        return ResponseEntity.ok(
                BaseResp.ok(
                        LoginResult.builder()
                                .terminal(terminal)
                                .user(User.builder()
                                        .userId(loginVo.getUserId())
                                        .nickName(loginVo.getNickName())
                                        .telephone(loginVo.getTelephone())
                                        .role(loginVo.getRole())
                                        .build()
                                )
                                .token(loginVo.getToken())
                                .build()
                )
        );
    }

    @ApiOperation(value = "短信登录")
    @RequestMapping(value = "/sms-login", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<LoginResult>> smsLogin(@RequestBody @Valid LoginRequest loginRequest,
                                                          HttpServletRequest request) throws JyuException {
        TerminalEnum terminal = TerminalEnum.fromValue(loginRequest.getTerminal()); // 终端
        // 构建登录Bo
        LoginBo loginBo = LoginBo.builder()
                .terminalEnum(terminal) // 终端
                .telephone(loginRequest.getTelephone())
                .code(loginRequest.getCode())
                .ip(IPUtil.getIpAddress(request))
                .build();

        // 登录
        LoginVo loginVo = userService.smsLogin(loginBo);
        return ResponseEntity.ok(
                BaseResp.ok(
                        LoginResult.builder()
                                .terminal(terminal)
                                .user(User.builder()
                                        .userId(loginVo.getUserId())
                                        .nickName(loginVo.getNickName())
                                        .telephone(loginVo.getTelephone())
                                        .role(loginVo.getRole())
                                        .build()
                                )
                                .token(loginVo.getToken())
                                .build()
                )
        );
    }

    /**
     * TODO 注册直接返回token
     * @param registerRequest
     * @param request
     * @return
     * @throws JyuException
     */
    @ApiOperation(value = "注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<String>> register(@RequestBody @Valid RegisterRequest registerRequest,
                                                     HttpServletRequest request) throws JyuException {
        // 注册
        userService.register(
                RegisterBo.builder()
                        .telephone(registerRequest.getTelephone())
                        .password(registerRequest.getPassword())
                        .code(registerRequest.getCode())
                        .ip(IPUtil.getIpAddress(request))
                        .build()
        );
        return ResponseEntity.ok(BaseResp.ok("注册成功"));
    }

    @Log(value = "修改密码")
    @ApiOperation(value = "修改密码")
    @RequestMapping(value = "/password/modify", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<String>> modifyPassword(
            @RequestHeader(JyuConstant.TOKEN_HEADER) String token,
            @RequestBody @Valid ModifyPasswordRequest modifyPasswordRequest) throws JyuException {
        // 获取当前
        String telephone = this.getUserTelephone(token);

        // 构建ModifyPasswordBo
        ModifyPasswordBo modifyPasswordBo = ModifyPasswordBo.builder()
                .telephone(telephone)
                .oldPassword(modifyPasswordRequest.getOldPassword())
                .newPassword(modifyPasswordRequest.getNewPassword())
                .build();
        userService.modifyPassword(modifyPasswordBo);

        return ResponseEntity.ok(BaseResp.ok("修改密码成功"));
    }

    @Log(value = "找回密码")
    @ApiOperation(value = "找回密码")
    @RequestMapping(value = "/password/retrieval", method = RequestMethod.POST)
    public ResponseEntity<BaseResp<String>> retrievalPassword(
            @RequestBody @Valid RetrievalPasswordRequest retrievalPasswordRequest) throws JyuException {
        String telephone = retrievalPasswordRequest.getTelephone();
        String password = retrievalPasswordRequest.getPassword();
        String code = retrievalPasswordRequest.getCode();

        // 构建RetrievalPasswordBo
        RetrievalPasswordBo retrievalPassword = RetrievalPasswordBo.builder()
                .telephone(telephone)
                .password(password)
                .code(code)
                .build();
        userService.retrievalPassword(retrievalPassword);

        return ResponseEntity.ok(BaseResp.ok("找回密码成功"));
    }
}
