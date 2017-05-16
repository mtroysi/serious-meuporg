package com.example.controller;

import com.example.dto.TagDTO;
import com.example.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Florentin NOËL on 16/05/17.
 */
@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    TagService tagService;

    @RequestMapping(method = RequestMethod.POST)
    public TagDTO createTag(@RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return tagService.createTag(values);
    }
}
