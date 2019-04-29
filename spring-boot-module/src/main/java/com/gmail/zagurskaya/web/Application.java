package com.gmail.zagurskaya.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages ={
        "com.gmail.zagurskaya.repository",
        "com.gmail.zagurskaya.service",
        "com.gmail.zagurskaya.web"})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
