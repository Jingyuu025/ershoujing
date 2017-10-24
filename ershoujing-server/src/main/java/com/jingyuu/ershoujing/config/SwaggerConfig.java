package com.jingyuu.ershoujing.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;


/**
 * @author owen
 * @date 2017-07-26
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket hadesApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ershoujing-api")
                .apiInfo(apiInfo())
                .select()
                .paths(hadesPaths())
                .build();
    }

    private Predicate<String> hadesPaths() {
        return or(
                regex("/system.*"),            // 系统
                regex("/file.*"),              // 文件
                regex("/user.*"),              // 用户
                regex("/category.*"),          // 类目
                regex("/brand.*"),             // 品牌
                regex("/tag.*"),               // 标签
                regex("/item-template.*")      // 商品模板
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Er shou jing 🐳")
                .description("Er shou jing API")
                .contact(new Contact("Owen（i-owen@live.cn）", "http://www.jingyuu.com", "i-owen@live.cn"))
                .version("1.0.0")
                .build();
    }
}
