package com.uch.finalproject;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.uch.finalproject.model.ProductResponse;

// 註釋annotation

@RestController
public class ProductController {

    // API入口
    @RequestMapping(value="/product", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ProductResponse Product() {
        return new ProductResponse(0, "test", null);
    }
}
