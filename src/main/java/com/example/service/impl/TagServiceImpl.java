package com.example.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.TagDTO;
import com.example.model.Tag;
import com.example.repository.TagRepository;
import com.example.service.TagService;
import com.example.transformers.Transformers;

/**
 * Created by Florentin NOËL on 16/05/17.
 */
@Service
public class TagServiceImpl implements TagService {
	
    @Autowired
    private TagRepository tagRepository;
    
    @Autowired
    private Transformers transformers;

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TagService#getTag(java.lang.Long)
     */
    @Override
    public TagDTO getTag(Long id) {
        return (TagDTO) transformers.convertEntityToDto(tagRepository.findOne(id), TagDTO.class);
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TagService#listAllTags()
     */
    @Override
    public List<TagDTO> listAllTags() {
        List<Tag> tags = (List<Tag>) tagRepository.findAll();
        List<TagDTO> tagDtoList = new ArrayList<>();
        tags.stream().forEach(tag -> tagDtoList.add((TagDTO) transformers.convertEntityToDto(tag, TagDTO.class)));
        return tagDtoList;
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TagService#createTag(java.util.Map)
     */
    @Override
    public TagDTO createTag(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        Tag tag = new Tag();
        BeanUtils.populate(tag, values);
        return (TagDTO) transformers.convertEntityToDto(tagRepository.save(tag), TagDTO.class);
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TagService#updateTag(java.lang.Long, java.util.Map)
     */
    @Override
    public TagDTO updateTag(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException {
        return null;
    }

    
    /* 
     * (non-Javadoc)
     * @see com.example.service.TagService#deleteTag(java.lang.Long)
     */
    @Override
    public void deleteTag(Long id) {

    }

}
