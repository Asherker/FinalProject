package com.uch.finalproject.controller;

import java.io.File;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.uch.finalproject.model.UploadResponse;

@RestController
public class UploadController {
    @PostMapping("/file")
    public UploadResponse uploadFileMulti(@RequestParam("files") MultipartFile[] uploadfiles) {

        String fileName = uploadfiles[0].getOriginalFilename();
        try {
            uploadfiles[0].transferTo(new File(new File("src/main/resources/static/imgs/").getAbsolutePath() + "/" + fileName));
        } catch (Exception e) {
            return new UploadResponse(1, e.toString(), null);
        }
        return new UploadResponse(0, "成功上傳", "/imgs/" + fileName);
    }
}
