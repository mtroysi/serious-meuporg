package com.example.controller;

import com.example.dto.TagDTO;
import com.example.dto.TaskDTO;
import com.example.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TagDTO listTag(@PathVariable("id") Long id) {
        return tagService.listTag(id);
    }

    @RequestMapping(method = RequestMethod.POST)
    public TagDTO createTag(@RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return tagService.createTag(values);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TagDTO updateTag(@PathVariable("id") Long id, @RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return tagService.updateTag(id, values);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable("id") Long id) {
        tagService.deleteTag(id);
    }
}
