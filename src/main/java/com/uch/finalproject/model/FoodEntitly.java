package com.uch.finalproject.model;

import java.sql.Date;

import lombok.Data;

@Data
public class FoodEntitly {
    int id;
    String name;
    String category;
    Date buyDate;
    Date expDate;
    int quantity;
}
