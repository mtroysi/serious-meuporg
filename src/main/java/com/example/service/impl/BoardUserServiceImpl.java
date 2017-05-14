package com.example.service.impl;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BoardWithDetailDTO;
import com.example.model.BoardUser;
import com.example.repository.BoardUserRepository;
import com.example.service.BoardUserService;
import com.example.transformers.Transformers;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@Service
public class BoardUserServiceImpl implements BoardUserService {
    @Autowired
    BoardUserRepository boardUserRepository;

    @Autowired
    Transformers transformers;

    @Override
    public List<BoardWithDetailDTO> getListBoardByUserId(Long user_id) {
        List<BoardUser> list = boardUserRepository.findAllByUserId(user_id);


        return list.stream()
                .sorted(Comparator.comparing(board -> board.getBoard().getDateCreation()))
                .map((BoardUser board) -> new BoardWithDetailDTO(board.getBoard().getId(), board.getBoard().getName(), board.getRole().getCode()))
                .collect(Collectors.toList());
    }
}
