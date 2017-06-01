package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BidDTO;
import com.example.dto.TaskLiteDTO;
import com.example.dto.TaskUserBidDTO;
import com.example.dto.TaskUserDTO;
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
    
    /**
     * Retourne la liste des taches d'un tableaux qui n'ont pas d'utilisateur assigne
     * @param idBoard
     * @return list TaskUserDTO
     */
    @RequestMapping(value = "/withoutuser/board/{idBoard}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskUserDTO>> getTaskUserWithoutUser(@PathVariable("idBoard") Long idBoard) {
    	logger.info("Calling BidController.getTaskUserWithoutUser with {}", idBoard);
    	
    	List<TaskUserDTO> list = taskService.getTaskWithoutUser(idBoard);
    	
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Retourne la liste des taches aux encheres qui sont finies
     * @param idBoard
     * @return liste de TaskUserBid
     */
    @RequestMapping(value = "/end/board/{idBoard}", method = RequestMethod.GET)
    public ResponseEntity<List<TaskUserBidDTO>> getTaskBidEnd(@PathVariable("idBoard") Long idBoard) {
    	logger.info("Calling BidController.getTaskBidEnd with {}", idBoard);
    	
    	List<TaskUserBidDTO> list = taskUserBidService.getTaskUserBidEndByBoard(idBoard);
    	
        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Retourne la liste des enchères pour un tableau et/ou un utilisateur
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
    
    /**
     * Affecte un temps a une tache aux encheres
     * @param task_id : id de la tache
     * @param duration : duree
     * @return liste de TaskUserBidDTO
     */
    @RequestMapping(value = "/task/{task_id}", method = RequestMethod.POST)
    public ResponseEntity<TaskUserBidDTO> addOrUpdateTaskUserBid(@PathVariable(value = "task_id") Long task_id, @RequestBody Double duration) {
        logger.info("Calling BidController.addOrUpdateTaskUserBid with {}", task_id);

        TaskUserBidDTO tub = taskUserBidService.addOrUpdateTaskUserBid(task_id, duration);

        return new ResponseEntity<>(tub, HttpStatus.OK);
    }
    
    /**
     * Ajoute une/ ou plusieurs taches aux encheres
     * @param datetime
     * @param listIdTasks : liste des idtache a créer.
     * @return liste des taskUserBidDTO
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<List<TaskUserBidDTO>> addBid(@RequestParam(value = "dateend") Long datetime, @RequestBody List<Long> listIdTasks) {
        logger.info("Calling BidController.addBid with {}", datetime);
        
        List<TaskUserBidDTO> list = taskUserBidService.addNewTaskInBid(listIdTasks, datetime);


        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Valide les encheres d'un tableau
     * @param board_id : id du tableau
     * @param listBidDTO : liste de BidDTO
     */
    @RequestMapping(value = "/valid/board/{board_id}", method = RequestMethod.POST)
    public void validBidByBoard(@PathVariable(value = "board_id") Long board_id, @RequestBody List<BidDTO> listBidDTO) {
        logger.info("Calling BidController.validBidByBoard with  {}", board_id);

        taskUserBidService.validBidByBoard(board_id, listBidDTO);
    }
}
