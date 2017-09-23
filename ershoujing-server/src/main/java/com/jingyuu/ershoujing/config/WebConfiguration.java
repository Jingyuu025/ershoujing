package com.jingyuu.ershoujing.config;

import com.jingyuu.ershoujing.interceptor.SessionInterceptor;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {
    // 业务路径
    private static final String[] BUSINESS_EXCLUDES_PATH = {
            "/",
            "/error/**",
            "/user/login",                                    // 登录
            "/user/sms-login",                                // 短信登录
            "/user/logout",                                   // 登出
            "/user/register",                                 // 获取短信验证码
            "/system/sms-code"
    };

    // Swagger路径
    private static final String[] SWAGGER_EXCLUDES_PATH = {
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs"
    };

    @Bean
    public SessionInterceptor sessionInterceptorConfig() {
        return new SessionInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludes = ArrayUtils.addAll(SWAGGER_EXCLUDES_PATH, BUSINESS_EXCLUDES_PATH);
        registry.addInterceptor(sessionInterceptorConfig()).excludePathPatterns(excludes);
        super.addInterceptors(registry);
    }
}
