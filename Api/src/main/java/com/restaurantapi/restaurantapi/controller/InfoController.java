package com.restaurantapi.restaurantapi.controller;

import com.restaurantapi.restaurantapi.model.ApplicationPropertiesTagName;
import com.restaurantapi.restaurantapi.services.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/info")
public class InfoController {

   @Autowired
   private InfoService infoService;

    @GetMapping("/get")
    public String getInfo() {
        return infoService.getInfo();
    }

}
