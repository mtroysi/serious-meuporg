package com.example.controller;

import com.example.dto.ColonneKanbanDTO;
import com.example.service.ColonneKanbanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;

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
     * Update colonne Kanban
     * @param kanban_id id colonne Kanban
     * @param values updated datas
     * @return updatd colonne Kanban
     */
    @RequestMapping(value = "/{kanban_id}", method = RequestMethod.PUT)
    public ResponseEntity<ColonneKanbanDTO> editColonneKanban(@PathVariable(value = "kanban_id") Long kanban_id, @RequestBody ColonneKanbanDTO values) {
        logger.info("Calling ColonneKanbanController.editColonneKanban with {}", kanban_id);

        ColonneKanbanDTO colonne = colonneKanbanService.updateColonneKanban(kanban_id, values);

        if (colonne == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(colonne, HttpStatus.OK);
    }

    /**
     * Crée une colonne Kanban dans un tableau board
     * @param board_id board id
     * @param colonneKanbanDTO : objet a créer
     * @return new colonne Kanban
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    @RequestMapping(value = "/board/{board_id}", method = RequestMethod.POST)
    public ResponseEntity<ColonneKanbanDTO> createColonneKanban(@PathVariable(value = "board_id") Long board_id, @RequestBody ColonneKanbanDTO colonneKanbanDTO) throws IllegalAccessException, InvocationTargetException {
        logger.info("Calling ColonneKanbanController.createColonneKanban with {}", board_id);

        ColonneKanbanDTO colonne = colonneKanbanService.createColonneKanban(colonneKanbanDTO, board_id);

        if (colonne == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(colonne, HttpStatus.OK);
    }


    /**
     * Supprime une colonne Kanban.
     * @param kanban_id : id colonne Kanban a supprimer
     */
    @RequestMapping(value = "/{kanban_id}", method = RequestMethod.DELETE)
    public void deleteColonneKanban(@PathVariable(value = "kanban_id") Long kanban_id) {
        logger.info("Calling ColonneKanbanController.editColonneKanban with {}", kanban_id);

        colonneKanbanService.deleteColonneKanban(kanban_id);
    }


}
