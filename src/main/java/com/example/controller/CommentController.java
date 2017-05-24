package com.example.controller;

import com.example.dto.CommentDTO;
import com.example.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Florentin NOËL on 18/05/17.
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTask(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CommentDTO updateTask(@PathVariable("id") Long id, @RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return commentService.updateComment(id,values);
    }
}
