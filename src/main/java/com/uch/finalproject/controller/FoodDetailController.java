package com.uch.finalproject.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.FoodDetailEntity;
import com.uch.finalproject.model.FoodDetailResponse;

@RestController
public class FoodDetailController {
    @RequestMapping(value = "/foodDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public FoodDetailResponse foodDetail(int id) {
        return new FoodDetailResponse(id, "OK", new FoodDetailEntity());
    }

}
