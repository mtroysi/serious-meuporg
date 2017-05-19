package com.example.service.impl;

import java.util.*;

import com.example.ConstanteGameMaster;
import com.example.dto.UserDTO;
import com.example.enumeration.RoleEnum;
import com.example.exception.GameMasterException;
import com.example.model.BoardUser;
import com.example.model.Role;
import com.example.model.User;
import com.example.repository.BoardUserRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
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

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@Service
public class BoardServiceImpl implements BoardService {
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private Transformers transformers;

    @Override
    public BoardDTO getBoard(Long id) {
        return transformers.transformBoardToBoardDto(boardRepository.findOne(id));
    }

    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setName(boardDTO.getName());
        board.setColor(boardDTO.getColor());
        board.setDateCreation(Calendar.getInstance().getTime());

        inviteUsers(board, boardDTO);

        return transformers.transformBoardToBoardDto(boardRepository.save(board));
    }

/*    @Override
    public BoardDTO updateBoard(Long id, Map<String, Object> values) throws IllegalAccessException, InvocationTargetException {
        Board board = boardRepository.findOne(id);
        if(board.getCreator().getId().equals(userService.getCurrentUser().getId())) {
            BeanUtils.populate(board, values);
            return transformers.transformBoardToBoardDto(boardRepository.save(board));
        } else {
            throw new GameMasterException(ConstanteGameMaster.UNAUTHORIZED_ERROR);
        }
    }*/

    @Override
    public BoardDTO updateBoard(BoardDTO boardDTO) {
        Board board = boardRepository.findOne(boardDTO.getId());
        if(board.getCreator().getId().equals(userService.getCurrentUser().getId())) {
            board.setColor(boardDTO.getColor());
            board.setName(boardDTO.getName());
//            board.getBoardUsers().stream().forEach(boardUser -> boardUserRepository.delete(boardUser.getId()));
            board.getBoardUsers().clear();
            inviteUsers(board, boardDTO);
            return transformers.transformBoardToBoardDto(boardRepository.save(board));
        } else {
            throw new GameMasterException(ConstanteGameMaster.UNAUTHORIZED_ERROR);
        }
    }

    @Override
    public void deleteBoard(Long id) {
        boardRepository.delete(id);
    }

    private void inviteUsers(Board board, BoardDTO boardDTO) {

        /* Gestion du créateur/administrateur du tableau */
        User creator = userService.getCurrentUser();
        board.setCreator(creator);
        BoardUser boardUserCreator = new BoardUser();
        boardUserCreator.setBoard(board);
        boardUserCreator.setRole(roleRepository.findByCode(RoleEnum.ADMIN));
        boardUserCreator.setUser(creator);
        board.getBoardUsers().add(boardUserCreator);

        /* Gestion des utilisateurs invités */
        boardDTO.getUsers().stream().forEach(userDTO -> {
            BoardUser boardUser = new BoardUser();
            boardUser.setBoard(board);
            boardUser.setRole(roleRepository.findByCode(RoleEnum.USER));
            boardUser.setUser((User) transformers.convertDtoToEntity(userDTO, User.class));
            board.getBoardUsers().add(boardUser);
        });
    }
}
