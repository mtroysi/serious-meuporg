package com.example.service;

import com.example.dto.TagDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by Florentin NOËL on 16/05/17.
 */
public interface TagService {
	
	/**
     * Retourne le tag dont l'id est passé en paramètre
     *
     * @param id l'id du tag
     * @return le tg correspondant
     */
    TagDTO getTag(Long id);
    
    /**
     * Retourne la liste de tous les tags
     * @return liste des tags
     */
    List<TagDTO> listAllTags();
    
    
    /**
     * Crée un tag.
     *
     * @param values donnés du tag à créer
     * @return le DTO du tag créé
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    TagDTO createTag(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
   
    
    /**
     * Modifie un tag existant.
     *
     * @param id     l'id du tag à modifier
     * @param values les données à modifier
     * @return le DTO du tag modifié
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    TagDTO updateTag(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
    
    
    /**
     * Supprime un tag
     *
     * @param id l'id du tag à supprimer
     */
    void deleteTag(Long id);
}
