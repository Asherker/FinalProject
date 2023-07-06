package com.uch.finalproject.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.GameDetailEntity;
import com.uch.finalproject.model.GameDetailResponse;

@RestController
public class GameDetailController {
    @RequestMapping(value = "/gameDetail", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public GameDetailResponse gameDetail(int id) {  // 這個API需要傳入id參數來找到指定的遊戲資訊
        return new GameDetailResponse(-1, "mock", new GameDetailEntity());
    }
    
}
