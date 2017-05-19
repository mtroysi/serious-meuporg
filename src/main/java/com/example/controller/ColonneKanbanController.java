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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import com.example.dto.BoardDTO;
import com.example.dto.BoardWithDetailDTO;
import com.example.dto.ColonneKanbanDTO;
import com.example.service.BoardService;
import com.example.service.BoardUserService;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@RestController
@RequestMapping("/api/colonneKanban")
public class ColonneKanbanController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ColonneKanbanService colonneKanbanService;

    
    /**
     * Return list of board by user
     * @param user_id
     * @return list of BoardWithDetailDTO
     */
    @RequestMapping(value = "/{kanban_id}", method = RequestMethod.PUT)
    public ResponseEntity<ColonneKanbanDTO> editColonneKanban(@PathVariable(value = "kanban_id") Long kanban_id, @RequestBody Map<String, Object> values) {
        logger.info("Calling ColonneKanbanController.editColonneKanban with {}", kanban_id);

       ColonneKanbanDTO colonne = colonneKanbanService.update(user_id);

        if (colonne == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(colonne, HttpStatus.OK);
    }



}
