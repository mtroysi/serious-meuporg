package com.example.service.impl;

import com.example.ConstanteGameMaster;
import com.example.dto.BoardDTO;
import com.example.dto.UserDTO;
import com.example.enumeration.RoleEnum;
import com.example.enumeration.StatusEnum;
import com.example.enumeration.TypeNotifEnum;
import com.example.exception.GameMasterException;
import com.example.model.Board;
import com.example.model.BoardUser;
import com.example.model.ColonneKanban;
import com.example.model.Notification;
import com.example.model.User;
import com.example.repository.BoardRepository;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.BoardService;
import com.example.service.UserService;
import com.example.transformers.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
    private UserRepository userRepository;
    
    @Autowired
    private Transformers transformers;


    /* 
     * (non-Javadoc)
     * @see com.example.service.BoardService#getBoard(java.lang.Long)
     */
    @Override
    public BoardDTO getBoard(Long id) {
        return transformers.transformBoardToBoardDto(boardRepository.findOne(id));
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.BoardService#createBoard(com.example.dto.BoardDTO)
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
        
        inviteUsers(board, boardDTO, true);
        
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

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.BoardService#updateBoard(com.example.dto.BoardDTO)
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
            inviteUsers(board, boardDTO, false);
            return transformers.transformBoardToBoardDto(boardRepository.save(board));
        } else {
            throw new GameMasterException(ConstanteGameMaster.UNAUTHORIZED_ERROR);
        }
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.BoardService#deleteBoard(java.lang.Long)
     */
    @Override
    public void deleteBoard(Long id) {
        boardRepository.delete(id);
    }

    /**
     * Gestionnaire des utilisateurs invités
     * @param board
     * @param boardDTO
     */
    private void inviteUsers(Board board, BoardDTO boardDTO, Boolean creation) {
    	
    	/* On supprime tous les utilisateurs */
        List<BoardUser> listBoardUser = board.getBoardUsers();
        
        
        /* Gestion du créateur/administrateur du tableau */
        User creator = userService.getCurrentUser();
        if(creation){
	        board.setCreator(creator);
	        BoardUser boardUserCreator = new BoardUser();
	        boardUserCreator.setBoard(board);
	        boardUserCreator.setRole(roleRepository.findByCode(RoleEnum.ADMIN));
	        boardUserCreator.setUser(creator);
	        board.getBoardUsers().add(boardUserCreator);
        }
        
        /* On supprime les utilisateurs supprimés dans le boardDTO */
        board.getBoardUsers().removeAll(board.getBoardUsers().stream().filter((BoardUser bu) -> {
        	return !bu.getUser().getId().equals(creator.getId()) || boardDTO.getUsers().stream().filter((UserDTO userDTO) -> userDTO.getId().equals(bu.getUser().getId())).findFirst().isPresent();
        }).collect(Collectors.toList()));
        
        /* Gestion des utilisateurs invités */
        boardDTO.getUsers().stream().forEach(userDTO -> {
        	// Si l'utilisateur n'est pas deja présent dans la liste BoardUser alors on le rajoute
        	if( !listBoardUser.stream().filter((BoardUser bu) -> bu.getUser().getId().equals(userDTO.getId())).findFirst().isPresent()){
                BoardUser boardUser = new BoardUser();
                boardUser.setBoard(board);
                boardUser.setRole(roleRepository.findByCode(RoleEnum.USER));
                User user = userRepository.findOne(userDTO.getId());
                
                if( user != null) {
                	// Notification pour l'ajout
                	Notification notif = new Notification();
            		notif.setContent(ConstanteGameMaster.ASSIGNMENT_BOARD + " " + boardDTO.getName());
            		notif.setTitle(ConstanteGameMaster.ASSIGNMENT_BOARD_TITLE);
            		notif.setDateCreation(new Date());
            		notif.setIsRead(false);
            		notif.setType(TypeNotifEnum.information);
            		notif.setUser(user);
            		
	                boardUser.setUser(user);
            		boardUser.getUser().addNotification(notif);
	                board.getBoardUsers().add(boardUser);
                }
        	}
        });
    }
}
