package com.uch.finalproject.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class FoodDetailListEntity {
    ArrayList<FoodDetailEntity> foods;
    int total;
}
