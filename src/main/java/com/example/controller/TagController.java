package com.example.controller;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.TagDTO;
import com.example.service.TagService;

/**
 * Created by Florentin NOËL on 16/05/17.
 */
@RestController
@RequestMapping("/api/tag")
public class TagController {
    @Autowired
    TagService tagService;

    /**
     * Retourne la liste de tous les tags.
     * @return list of TagDTO
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<TagDTO> listAllTags() {
        return tagService.listAllTags();
    }

    /**
     * Retourne le tag dont l'id est passé en paramètre
     * @param id tag id
     * @return TagDTO
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public TagDTO getTag(@PathVariable("id") Long id) {
        return tagService.getTag(id);
    }

    /**
     * Crée un tag.
     * @param values données du tag à créer
     * @return TagDTO
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(method = RequestMethod.POST)
    public TagDTO createTag(@RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return tagService.createTag(values);
    }

    /**
     * Modifie un tag.
     * @param id id du tag à modifier
     * @param values données modifiées du tag
     * @return updated TagDTO
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public TagDTO updateTag(@PathVariable("id") Long id, @RequestBody Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return tagService.updateTag(id, values);
    }

    /**
     * Supprime un tag.
     * @param id id du tag à supprimer
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteTag(@PathVariable("id") Long id) {
        tagService.deleteTag(id);
    }
}
