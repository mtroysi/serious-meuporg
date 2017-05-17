package com.example.transformers;

import com.example.dto.BoardDTO;
import com.example.dto.UserDTO;
import com.example.model.Board;
import com.example.model.BoardUser;
import com.example.model.User;
import com.example.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */
@Service
public class Transformers {

    public Object convertEntityToDto(Object entity, Class dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, dtoClass);
    }

    public Object convertDtoToEntity(Object dto, Class entityClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, entityClass);
    }

    public BoardDTO transformBoardToBoardDto(Board board) {
        BoardDTO boardDTO = (BoardDTO)convertEntityToDto(board, BoardDTO.class);
        boardDTO.setUsers(new ArrayList<>());
        board.getBoardUsers().stream().forEach(boardUser ->
            boardDTO.getUsers().add((UserDTO)convertEntityToDto(boardUser.getUser(), UserDTO.class))
        );
        return boardDTO;
    }
}
