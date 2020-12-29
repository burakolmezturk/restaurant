package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.dto.ApplicationPropertiesTagNameDTO;
import com.restaurantapi.restaurantapi.dto.MediaDTO;
import com.restaurantapi.restaurantapi.services.MediaService;
import liquibase.pro.packaged.S;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @Value("${media.pathname}")
    private String path;
    @GetMapping
    public List<MediaDTO> getAllMedia(){
        return mediaService.getAllMedia();
    }
    @PostMapping
    public MediaDTO addMedia(@RequestParam String fileName, @RequestParam MultipartFile file) throws IOException {
        return mediaService.addMedia(fileName,file,path);
    }
}
