package com.example.service;

import com.example.dto.CommentDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Florentin NOËL on 17/05/17.
 */
public interface CommentService {
    CommentDTO findComment(Long id);
    CommentDTO addCommentToTask(Long id, Map<String, Object> values, Long idUser) throws InvocationTargetException, IllegalAccessException;
    void deleteComment(Long id);
    CommentDTO updateComment(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
}
