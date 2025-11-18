package com.fcx.fcx;

// Import desnecessário (javax.swing.Spring) removido

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class FcxApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(FcxApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(FcxApplication.class, args);

		System.out.println("Ola mundo");
    }

}
