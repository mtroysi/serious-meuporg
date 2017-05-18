package com.example.service;

import com.example.dto.BoardDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */


public interface BoardService {

    BoardDTO createBoard(BoardDTO boardDTO);

//    BoardDTO updateBoard(Long id, Map<String, Object> values) throws IllegalAccessException, InvocationTargetException;

    BoardDTO updateBoard(BoardDTO boardDTO);

    void deleteBoard(Long id);

    BoardDTO getBoard(Long id);
}
