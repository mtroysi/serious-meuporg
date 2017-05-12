package com.example.transformers;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

/**
 * Created by Morgane TROYSI on 11/05/17.
 */

@Service
public class Transformers {

    public Object convertEntityToDto(Object entity, Class dtoClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, dtoClass);
    }

    public Object convertDtoToEntity(Object dto, Class entityClass) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, entityClass);
    }
}
