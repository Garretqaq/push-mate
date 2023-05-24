package com.dato.push.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@MapperScan("com.dato.push.app.dao.mapper")
@Configuration
public class PushAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(PushAppApplication.class, args);
    }

}
