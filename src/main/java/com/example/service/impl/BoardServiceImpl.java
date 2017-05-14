package com.example.service.impl;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BoardDTO;
import com.example.model.Board;
import com.example.repository.BoardRepository;
import com.example.service.BoardService;
import com.example.transformers.Transformers;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.Calendar;
import java.util.Map;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private Transformers transformers;

    @Override
    public BoardDTO createBoard(String name) {
        Board board = new Board();
        board.setName(name);
        board.setDateCreation(Calendar.getInstance().getTime());
        return (BoardDTO)transformers.convertEntityToDto(boardRepository.save(board), BoardDTO.class);
    }

    @Override
    public BoardDTO updateBoard(Long id, Map<String, Object> values) throws IllegalAccessException, InvocationTargetException {
        //TODO: vérifier si l'utilisateur connecté a le droit de modification sur le tableau
        Board board = boardRepository.findOne(id);
        BeanUtils.populate(board, values);
        return (BoardDTO)transformers.convertEntityToDto(boardRepository.save(board), BoardDTO.class);
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.delete(id);
    }
}
