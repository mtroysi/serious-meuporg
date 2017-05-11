package com.example.service.impl;

import com.example.dto.BoardDTO;
import com.example.model.Board;
import com.example.repository.BoardRepository;
import com.example.service.BoardService;
import com.example.transformers.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    BoardRepository boardRepository;

    @Autowired
    Transformers transformers;

    @Override
    public BoardDTO createBoard(String name) {
        Board board = new Board();
        board.setName(name);
        board.setDateCreation(Calendar.getInstance().getTime());
        return transformers.transformBoardToBoardDto(boardRepository.save(board));
    }
}
