package com.example.service;

import com.example.dto.CommentDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * Created by Florentin NOËL on 17/05/17.
 */
public interface CommentService {
	
	/**
     * Retourne le commentaire dont l'id est passé en paramètre
     * @param id l'id du commentaire
     * @return le commentaire correspondant
     */
    CommentDTO findComment(Long id);
    
    /**
     * Ajoute un commentaire à une tâche
     * @param id l'id de la tâche
     * @param values données du commentaire à créer
     * @param idUser l'id de l'utilisateur
     * @return
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    CommentDTO addCommentToTask(Long id, Map<String, Object> values, Long idUser) throws InvocationTargetException, IllegalAccessException;
    
    /**
     * Supprime un commentaire
     * @param id l'id du commentaire à supprimer
     */
    void deleteComment(Long id);
    
    /**
     * Modifie un commentaire
     * @param id l'id du commentaire à modifier
     * @param values données à modifier
     * @return DTO du commentaire modifié
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    CommentDTO updateComment(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
}
