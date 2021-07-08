package com.wangzhe.springboot.config;

import com.wangzhe.springboot.bean.People;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {


    @Bean
    public People getPeople() {
        People people = new People();
        people.setName("haha");
        return people;
    }

}
