package com.example.service.impl;

import com.example.ConstanteGameMaster;
import com.example.dto.BoardDTO;
import com.example.enumeration.RoleEnum;
import com.example.enumeration.StatusEnum;
import com.example.exception.GameMasterException;
import com.example.model.Board;
import com.example.model.BoardUser;
import com.example.model.ColonneKanban;
import com.example.model.User;
import com.example.repository.BoardRepository;
import com.example.repository.RoleRepository;
import com.example.service.BoardService;
import com.example.service.UserService;
import com.example.transformers.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    /**
     * Retourne le tabbleau dont l'id est passé en paramètre
     * @param id l'id du tableau
     * @return le DTO du tableau correspondant
     */
    @Override
    public BoardDTO getBoard(Long id) {
        return transformers.transformBoardToBoardDto(boardRepository.findOne(id));
    }

    /**
     * Crée un tableau
     * @param boardDTO informations nécessaires à la création du tableau
     * @return le DTO du tableau créé
     */
    @Override
    public BoardDTO createBoard(BoardDTO boardDTO) {
        Board board = new Board();
        board.setName(boardDTO.getName());
        board.setColor(boardDTO.getColor());
        board.setDateCreation(Calendar.getInstance().getTime());
        board.setMoneyDoneTask(boardDTO.getMoneyDoneTask());
        board.setMoneyWinBid(boardDTO.getMoneyWinBid());
        board.setExpDoneTask(boardDTO.getExpDoneTask());
        board.setExpWinBid(boardDTO.getExpWinBid());
        
        inviteUsers(board, boardDTO);
        
        //Add column Kanban
        ArrayList<ColonneKanban> list = new ArrayList<>();
        ColonneKanban col1 = new ColonneKanban();
        col1.setBoard(board);
        col1.setTitle(StatusEnum.TODO.name());
        col1.setStatus(StatusEnum.TODO);
        list.add(col1);
        ColonneKanban col2 = new ColonneKanban();
        col2.setBoard(board);
        col2.setTitle(StatusEnum.IN_PROGRESS.name());
        col2.setStatus(StatusEnum.IN_PROGRESS);
        list.add(col2);
        ColonneKanban col3 = new ColonneKanban();
        col3.setBoard(board);
        col3.setTitle(StatusEnum.DONE.name());
        col3.setStatus(StatusEnum.DONE);
        list.add(col3);
        board.setColonneKanbans(list);
        
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

    /**
     * Met à jour un tableau
     * @param boardDTO données du tableau modifié
     * @return le DTO du tableau modifié
     */
    @Override
    public BoardDTO updateBoard(BoardDTO boardDTO) {
        Board board = boardRepository.findOne(boardDTO.getId());
        if(board.getCreator().getId().equals(userService.getCurrentUser().getId())) {
            board.setColor(boardDTO.getColor());
            board.setDateCreation(Calendar.getInstance().getTime());
            board.setMoneyDoneTask(boardDTO.getMoneyDoneTask());
            board.setMoneyWinBid(boardDTO.getMoneyWinBid());
            board.setExpDoneTask(boardDTO.getExpDoneTask());
            board.setExpWinBid(boardDTO.getExpWinBid());
            board.setName(boardDTO.getName());
//            board.getBoardUsers().stream().forEach(boardUser -> boardUserRepository.delete(boardUser.getId()));
            board.getBoardUsers().clear();
            inviteUsers(board, boardDTO);
            return transformers.transformBoardToBoardDto(boardRepository.save(board));
        } else {
            throw new GameMasterException(ConstanteGameMaster.UNAUTHORIZED_ERROR);
        }
    }

    /**
     * Supprime un tableau
     * @param id l'id du tableau à supprimer
     */
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
