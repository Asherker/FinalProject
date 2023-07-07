package com.uch.finalproject.model;

import java.util.ArrayList;

import lombok.Data;

@Data
public class FoodDetailListResponse extends BaseResponse {
    ArrayList<FoodDetailEntity> data;

    public FoodDetailListResponse(int code, String message, ArrayList<FoodDetailEntity> data) {
        super(code, message);

        this.data = data;
    }
}
