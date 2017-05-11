package com.example.controller;

import com.example.dto.BoardDTO;
import com.example.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@RestController
@RequestMapping("/api/board")
public class BoardController {

    @Autowired
    BoardService boardService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BoardDTO createBoard(@RequestBody String name) {
        return boardService.createBoard(name);
    }
}
