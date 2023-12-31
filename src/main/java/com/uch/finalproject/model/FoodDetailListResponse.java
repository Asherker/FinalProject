package com.uch.finalproject.model;


import java.util.ArrayList;

import lombok.Data;

@Data
public class FoodDetailListResponse extends BaseResponse {
    FoodDetailListEntity data;

    public FoodDetailListResponse(int code, String message, ArrayList<FoodDetailEntity>  foods, int total) {
        super(code, message);

        this.data = new FoodDetailListEntity();
        this.data.foods = foods;
        this.data.total = total;
    }
}
