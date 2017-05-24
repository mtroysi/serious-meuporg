package com.example.service.impl;

import com.example.dto.ItemDto;
import com.example.dto.TagDTO;
import com.example.model.Item;
import com.example.model.Tag;
import com.example.repository.TagRepository;
import com.example.service.TagService;
import com.example.transformers.Transformers;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Florentin NOËL on 16/05/17.
 */
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagRepository tagRepository;
    @Autowired
    private Transformers transformers;

    /**
     * Retourne le tag dont l'id est passé en paramètre
     *
     * @param id l'id du tag
     * @return le tg correspondant
     */
    @Override
    public TagDTO listTag(Long id) {
        return (TagDTO) transformers.convertEntityToDto(tagRepository.findOne(id), TagDTO.class);
    }

    @Override
    public List<TagDTO> listAllTags() {
        List<Tag> tags = (List<Tag>) tagRepository.findAll();
        List<TagDTO> tagDtoList = new ArrayList<>();
        tags.stream().forEach(tag -> tagDtoList.add((TagDTO) transformers.convertEntityToDto(tag, TagDTO.class)));
        return tagDtoList;
    }

    /**
     * Crée un tag.
     *
     * @param values donnés du tag à créer
     * @return le DTO du tag créé
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public TagDTO createTag(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Tag tag = new Tag();
        BeanUtils.populate(tag, values);
        return (TagDTO) transformers.convertEntityToDto(tagRepository.save(tag), TagDTO.class);
    }

    /**
     * Modifie un tag existant.
     *
     * @param id     l'id du tag à modifier
     * @param values les données à modifier
     * @return le DTO du tag modifié
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    @Override
    public TagDTO updateTag(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return null;
    }

    /**
     * Supprime un tag
     *
     * @param id l'id du tag à supprimer
     */
    @Override
    public void deleteTag(Long id) {

    }

}
