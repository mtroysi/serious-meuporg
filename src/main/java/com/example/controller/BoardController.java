package com.example.controller;

import com.example.dto.board.BoardDTO;
import com.example.dto.board.BoardWithDetailDTO;
import com.example.service.BoardService;
import com.example.service.BoardUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@RestController
@RequestMapping("/api/board")
public class BoardController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    BoardService boardService;

    @Autowired
    BoardUserService boardUserService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public BoardDTO createBoard(@RequestBody String name) {
        return boardService.createBoard(name);
    }

    /**
     * Return list of board by user
     * @param user_id
     * @return list of BoardWithDetailDTO
     */
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<BoardWithDetailDTO>> getListBoardByUser(@PathVariable(value = "user_id") Long user_id) {
        logger.info("Calling BoardController.getListBoardByUser with {}", user_id);

        List<BoardWithDetailDTO> list = boardUserService.getListBoardByUserId(user_id);

        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
