package com.kingmartinien.springbootblogapi;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootBlogApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootBlogApiApplication.class, args);
    }

    @Bean
    Faker faker() {
        return new Faker();
    }
}
