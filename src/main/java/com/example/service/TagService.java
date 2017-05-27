package com.example.service;

import com.example.dto.TagDTO;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

/**
 * Created by Florentin NOËL on 16/05/17.
 */
public interface TagService {

    TagDTO getTag(Long id);
    List<TagDTO> listAllTags();
    TagDTO createTag(Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
    TagDTO updateTag(Long id, Map<String, Object> values) throws InvocationTargetException, IllegalAccessException;
    void deleteTag(Long id);
}
