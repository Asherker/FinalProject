package com.uch.finalproject.model;

import lombok.Data;

@Data
public class UploadResponse extends BaseResponse {
    String data;

    public UploadResponse(int code, String message, String data) {
        super(code, message);
        this.data = data;
    }
    
}
