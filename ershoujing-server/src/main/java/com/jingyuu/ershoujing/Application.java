package com.jingyuu.ershoujing;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableAsync;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author owen
 * @date 2017-09-07
 */
@Slf4j
@EnableAsync
@SpringBootApplication
public class Application {
    @Resource
    private Environment environment;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    public void initApplication() {
        log.info("默认环境参数：{}", Arrays.toString(environment.getDefaultProfiles()));
    }

    @PreDestroy
    public void destroyApplication() {
        log.info("应用关闭.");
    }
}
