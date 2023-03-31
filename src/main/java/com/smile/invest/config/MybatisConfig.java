package com.smile.invest.config;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.smile.invest", annotationClass = Mapper.class)
public class MybatisConfig {


}


