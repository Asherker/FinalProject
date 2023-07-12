package com.uch.finalproject.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
public class MySqlConfigBean {
    @Value("${spring.datasource.food.url}")
    private String urlFood;

    @Value("${spring.datasource.game.url}")
    private String urlGame;

    @Value("${spring.datasource.shopping.url}")
    private String urlShopping;

    @Value("${spring.datasource.username}")
    private String username;

    @Value("${spring.datasource.password}")
    private String password;

    @Value("${spring.datasource.driver-class-name}")
    private String driverClassName;
}
