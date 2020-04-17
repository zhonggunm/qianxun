package com.qianxun.qxtag;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Created by Tomasz Kucharzyk
 */
@SpringBootApplication
@EnableCaching
public class QianxunTagApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(QianxunTagApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(QianxunTagApplication.class, args);
    }

}
