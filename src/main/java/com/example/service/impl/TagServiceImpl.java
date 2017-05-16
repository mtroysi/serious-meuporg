package com.example.service.impl;

import com.example.dto.TagDTO;
import com.example.model.Tag;
import com.example.repository.TagRepository;
import com.example.service.TagService;
import com.example.transformers.Transformers;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
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


    @Override
    public TagDTO createTag(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Tag tag = new Tag();
        BeanUtils.populate(tag,values);
        return (TagDTO) transformers.convertEntityToDto(tagRepository.save(tag), TagDTO.class);
    }

    @Override
    public TagDTO listTag(Long id) {
        return null;
    }

    @Override
    public TagDTO updateTag(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return null;
    }

    @Override
    public void deleteTag(Long id) {

    }

}
