package com.jingyuu.ershoujing.config;

import com.jingyuu.ershoujing.interceptor.FileUploadInterceptor;
import com.jingyuu.ershoujing.interceptor.SessionInterceptor;
import com.jingyuu.ershoujing.service.system.impl.FileConfig;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
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
            "/user/password/retrieval",                       // 找回密码
            "/user/logout",                                   // 登出
            "/user/register",                                 // 注册
            "/system/sms-code",                               // 获取短信验证码
            "/file/pic/**"                                    // 图片查看
    };
    // Swagger路径
    private static final String[] SWAGGER_EXCLUDES_PATH = {
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/v2/api-docs"
    };

    @Autowired
    private FileConfig fileConfig;

    @Bean
    public SessionInterceptor sessionInterceptorConfig() {
        return new SessionInterceptor();
    }

    @Bean
    public FileUploadInterceptor fileUploadInterceptor() {
        FileUploadInterceptor fileUploadInterceptor = new FileUploadInterceptor();
        fileUploadInterceptor.setMaxSize(fileConfig.getMaxFileSize());
        return fileUploadInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        String[] excludes = ArrayUtils.addAll(SWAGGER_EXCLUDES_PATH, BUSINESS_EXCLUDES_PATH);
        registry.addInterceptor(sessionInterceptorConfig()).excludePathPatterns(excludes);
        registry.addInterceptor(fileUploadInterceptor()).excludePathPatterns(excludes);
        super.addInterceptors(registry);
    }
}
