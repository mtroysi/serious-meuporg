package com.example.transformers;

import com.example.dto.BoardDTO;
import com.example.dto.ResultDto;
import com.example.dto.UserDTO;
import com.example.model.Board;
import com.example.model.Task;
import com.example.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */
@Service
public class Transformers {

    /**
     * Convertit une entité en son DTO associé
     * @param entity entité source à convertir
     * @param dtoClass DTO de destination
     * @return objet du type du DTO de destination
     */
    public Object convertEntityToDto(Object entity, Class dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, dtoClass);
    }

    /**
     * Convertit un DTO en son entité associée
     * @param dto dto source à convertir
     * @param entityClass entité de destination
     * @return objet du type de l'entité de destination
     */
    public Object convertDtoToEntity(Object dto, Class entityClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, entityClass);
    }

    /**
     * Méthode spécifique pour transformer un tableau en son DTO
     * @param board tableau source
     * @return BoardDTO tableau de destination
     */
    public BoardDTO transformBoardToBoardDto(Board board) {
        BoardDTO boardDTO = (BoardDTO)convertEntityToDto(board, BoardDTO.class);
        boardDTO.setUsers(new ArrayList<>());
        board.getBoardUsers().stream().forEach(boardUser ->
            boardDTO.getUsers().add(this.transformUserToUserDto(boardUser.getUser()))
        );
        return boardDTO;
    }

    /**
     * Méthode spécifique pour transformer un utilisateur en son DTO
     * @param user utilisateur source
     * @return UserDTO
     */
    public UserDTO transformUserToUserDto(User user) {
        UserDTO userDTO = (UserDTO)convertEntityToDto(user, UserDTO.class);
        userDTO.setFullName(user.getFirstName() + ' ' + user.getLastName());
        return userDTO;
    }

    public ResultDto transformUserToResultDto(User user) {
        ResultDto resultDTO = new ResultDto();
        resultDTO.setId(user.getId());
        resultDTO.setName(user.getFirstName() + ' ' + user.getLastName());
        resultDTO.setType("USER");
        return resultDTO;
    }

    public ResultDto transformTaskToResultDto(Task task) {
        ResultDto resultDTO = new ResultDto();
        resultDTO.setId(task.getId());
        resultDTO.setName(task.getTitle());
        resultDTO.setType("TASK");
        return resultDTO;
    }
}
