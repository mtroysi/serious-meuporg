package com.example.transformers;

import com.example.dto.BoardDTO;
import com.example.model.Board;
import org.springframework.stereotype.Service;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@Service
public class Transformers {

    public BoardDTO transformBoardToBoardDto(Board board) {
        BoardDTO boardDTO = new BoardDTO();
        boardDTO.setId(board.getId());
        boardDTO.setName(board.getName());
        return boardDTO;
    }
}
