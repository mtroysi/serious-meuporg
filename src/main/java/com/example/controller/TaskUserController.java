package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TaskUserDTO;
import com.example.service.TaskUserService;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@RestController
@RequestMapping("/api/taskUser")
public class TaskUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    TaskUserService taskUserService;

    @RequestMapping(value = "/board/{board_id}/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskUserDTO>> listTaskByBoardAndUser(@PathVariable(value = "user_id") Long user_id, @PathVariable(value = "board_id") Long board_id){
        logger.info("Calling TaskUserController.listTaskByBoardAndUser with {}  {}", user_id, board_id);
        
        List<TaskUserDTO> list = taskUserService.getTaskUserByUserIdAndBoardId(user_id, board_id);
        
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @RequestMapping(value = "/board/{board_id}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskUserDTO>> listTaskByBoard(@PathVariable(value = "board_id") Long board_id){
    	logger.info("Calling TaskUserController.listTaskByBoardAndUser with {}", board_id);
        

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        List<TaskUserDTO> list = taskUserService.getTaskUserByBoardId(board_id);
        
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

}
