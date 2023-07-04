package com.uch.finalproject.controller;

import java.util.ArrayList;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.FoodEntitly;
import com.uch.finalproject.model.FoodResponse;

@RestController
public class FoodController {
    @RequestMapping(value = "/foods", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodResponse foods() {
        FoodEntitly foodEntitly = new FoodEntitly();
        foodEntitly.setId(0);
        foodEntitly.setName("蘋果");
        FoodEntitly foodEntitly1 = new FoodEntitly();
        foodEntitly1.setId(0);
        foodEntitly1.setName("蘋果");
        ArrayList<FoodEntitly> data = new ArrayList<>();
        data.add(foodEntitly);
        data.add(foodEntitly1);
        return new FoodResponse(0, "OK", data);
    }
}
