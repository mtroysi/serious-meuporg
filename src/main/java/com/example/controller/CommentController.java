package com.example.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.CommentDTO;
import com.example.service.CommentService;

/**
 * Created by Florentin NOËL on 18/05/17.
 */
@RestController
@RequestMapping("/api/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    /**
     * delete a comment.
     * @param id id of the comment to delete
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteComment(@PathVariable("id") Long id) {
        commentService.deleteComment(id);
    }

    /**
     * update a comment.
     * @param id id of the comment to update
     * @param values updated datas of the comment
     * @return updated comment
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public CommentDTO updateComment(@PathVariable("id") Long id, @RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return commentService.updateComment(id,values);
    }
}
