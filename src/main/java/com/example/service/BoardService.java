package com.example.service;

import com.example.dto.BoardDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */


public interface BoardService {

    BoardDTO createBoard(String name);

    BoardDTO updateBoard(Long id, Map<String, Object> values) throws IllegalAccessException, InvocationTargetException;

    void deleteBoard(Long id);

    BoardDTO getBoard(Long id);
}
