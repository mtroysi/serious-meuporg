package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TaskLiteDTO;
import com.example.dto.TaskUserBidDTO;
import com.example.service.TaskService;
import com.example.service.TaskUserBidService;


@RestController
@RequestMapping("/api/bid")
public class BidController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    TaskUserBidService taskUserBidService;
    
    @Autowired
    TaskService taskService;
    	
    @RequestMapping(value = "/withoutuser/board/{idBoard}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskLiteDTO>> getTaskWithoutUser(@PathVariable("idBoard") Long idBoard) {
    	
    	List<TaskLiteDTO> list = taskService.getTaskWithoutUser(idBoard);
    	
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Return list of taskUserBid by user and board
     * @param user_id
     * @return list of TaskUserBidDTO
     */
    @RequestMapping(value = "/board/{board_id}/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskUserBidDTO>> getListBidByBoardAndUser(@PathVariable(value = "user_id") Long user_id, @PathVariable(value = "board_id") Long board_id) {
        logger.info("Calling BidController.getListBidByBoardAndUser with {} {}", user_id, board_id);

        List<TaskUserBidDTO> list = taskUserBidService.getTaskUserBidByBoardAndUser(board_id, user_id);

        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/task/{task_id}", method = RequestMethod.POST)
    public ResponseEntity<TaskUserBidDTO> addOrUpdateTaskUserBid(@PathVariable(value = "task_id") Long task_id) {
        logger.info("Calling BidController.addOrUpdateTaskUserBid with {}", task_id);

        TaskUserBidDTO tub = taskUserBidService.getTaskUserBidByBoardAndUser(task_id, );



        return new ResponseEntity<>(tub, HttpStatus.OK);
    }

}
