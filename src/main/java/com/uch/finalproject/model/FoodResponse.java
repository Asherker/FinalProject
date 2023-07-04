package com.uch.finalproject.model;

import java.util.ArrayList;

public class FoodResponse extends BaseResponse {
    ArrayList<FoodEntitly> data;

    public FoodResponse(int code, String message, ArrayList<FoodEntitly> data) {
        super(code, message);

        this.data = data;
    }
    
}
