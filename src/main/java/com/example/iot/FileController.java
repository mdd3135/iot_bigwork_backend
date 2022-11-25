package com.example.iot;

import java.io.File;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
    private String path = "/var/iot";

    @PostMapping("/upload")
    @ResponseBody
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file){
        String oldName = file.getOriginalFilename();
        String fileName = String.valueOf(System.currentTimeMillis()) + oldName.substring(oldName.lastIndexOf("."));
        String filePath = path + "/" + fileName;
        File dest = new File(filePath);
        if(dest.getParentFile().exists() == false){
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
        } catch (Exception e) {
            e.printStackTrace();
            return Map.of("code", 3);
        }
        return Map.of("code", 0, "file_name", fileName);
    }
}
