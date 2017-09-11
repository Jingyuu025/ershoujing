package com.jingyuu.ershoujing.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan("com.jingyuu.ershoujing.dao.jpa.entity")
@EnableJpaRepositories("com.jingyuu.ershoujing.dao.jpa.repository")
@MapperScan("com.jingyuu.ershoujing.dao.mybatis.mapper")
public class MapperAndJpaConfig {
}
