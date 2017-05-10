package com.example.controller;

import com.example.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Morgane TROYSI on 22/04/2017.
 */


@RestController
@RequestMapping("/api")
public class HelloController {
    @Autowired
    HomeService homeService;

    @RequestMapping(value = "/resource", method = RequestMethod.GET)
    public Map<String,Object> home() {
        return homeService.getResource();
    }
}
