package com.example.controller;

import com.example.dto.BoardDTO;
import com.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping(method = RequestMethod.POST)
    public BoardDTO createBoard(@RequestBody String name) {
        return boardService.createBoard(name);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public BoardDTO updateBoard(@PathVariable("id") Long id, @RequestBody Map<String, Object> values) throws IllegalAccessException, InvocationTargetException {
        return boardService.updateBoard(id, values);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteBoard(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);
    }
}
