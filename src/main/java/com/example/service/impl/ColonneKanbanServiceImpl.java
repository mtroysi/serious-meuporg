package com.example.service.impl;

import java.util.*;

import com.example.dto.UserDTO;
import com.example.model.BoardUser;
import com.example.model.ColonneKanban;
import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.BoardDTO;
import com.example.dto.ColonneKanbanDTO;
import com.example.model.Board;
import com.example.repository.BoardRepository;
import com.example.repository.ColonneKanbanRepository;
import com.example.service.BoardService;
import com.example.service.ColonneKanbanService;
import com.example.transformers.Transformers;
import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;


@Service
public class ColonneKanbanServiceImpl implements ColonneKanbanService {
	
    @Autowired
    private ColonneKanbanRepository colonneKanbanRepository;
    
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private Transformers transformers;

    
	@Override
	public ColonneKanbanDTO createColonneKanban(ColonneKanbanDTO colonneKanbanDTO, Long boardId) {
		ColonneKanban ck;
		ck = (ColonneKanban)transformers.convertDtoToEntity(colonneKanbanDTO, ColonneKanban.class);
		ck.setBoard(boardRepository.findOne(boardId));
        return (ColonneKanbanDTO) transformers.convertEntityToDto(colonneKanbanRepository.save(ck), ColonneKanbanDTO.class);
	}

	@Override
	public ColonneKanbanDTO updateColonneKanban(Long id, ColonneKanbanDTO colonneDTO) {
		ColonneKanban colonne = colonneKanbanRepository.findOne(id);
		colonne.setColor(colonneDTO.getColor());
		colonne.setTitle(colonneDTO.getTitle());
        return (ColonneKanbanDTO) transformers.convertEntityToDto(colonneKanbanRepository.save(colonne), ColonneKanbanDTO.class);
	}

	@Override
	public void deleteColonneKanban(Long id) {
		colonneKanbanRepository.updateColonneKanbanDelete(id);
		colonneKanbanRepository.delete(id);
	}
}
