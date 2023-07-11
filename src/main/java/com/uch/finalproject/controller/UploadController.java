package com.uch.finalproject.controller;

import java.io.File;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uch.finalproject.model.UploadResponse;

import lombok.Data;

@RestController
public class UploadController {
    @Value("${upload.url.base-path}")
    private String urlBasePath;

    @Value("${upload.server.path}")
    private String serverUploadPath;

    @PostMapping("/file")
    public UploadResponse uploadFileMulti(@RequestParam("files") MultipartFile[] uploadfiles) throws InterruptedException {

        Thread.sleep(3000);
    
        String fileName = uploadfiles[0].getOriginalFilename();
        try {
            uploadfiles[0].transferTo(new File(new File(serverUploadPath).getAbsolutePath() + "/" + fileName));
        } catch (Exception e) {
            return new UploadResponse(1, e.toString(), null);
        }
        return new UploadResponse(0, "成功上傳", urlBasePath + fileName);
    }
}
