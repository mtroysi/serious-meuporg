package com.example.service.impl;

import com.example.dto.ColonneKanbanDTO;
import com.example.model.ColonneKanban;
import com.example.repository.BoardRepository;
import com.example.repository.ColonneKanbanRepository;
import com.example.service.ColonneKanbanService;
import com.example.transformers.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ColonneKanbanServiceImpl implements ColonneKanbanService {
	
    @Autowired
    private ColonneKanbanRepository colonneKanbanRepository;
    
    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private Transformers transformers;

	
	/* 
	 * (non-Javadoc)
	 * @see com.example.service.ColonneKanbanService#createColonneKanban(com.example.dto.ColonneKanbanDTO, java.lang.Long)
	 */
	@Override
	public ColonneKanbanDTO createColonneKanban(ColonneKanbanDTO colonneKanbanDTO, Long boardId) {
		ColonneKanban ck;
		ck = (ColonneKanban)transformers.convertDtoToEntity(colonneKanbanDTO, ColonneKanban.class);
		ck.setBoard(boardRepository.findOne(boardId));
        return (ColonneKanbanDTO) transformers.convertEntityToDto(colonneKanbanRepository.save(ck), ColonneKanbanDTO.class);
	}

	
	/* 
	 * (non-Javadoc)
	 * @see com.example.service.ColonneKanbanService#updateColonneKanban(java.lang.Long, com.example.dto.ColonneKanbanDTO)
	 */
	@Override
	public ColonneKanbanDTO updateColonneKanban(Long id, ColonneKanbanDTO colonneDTO) {
		ColonneKanban colonne = colonneKanbanRepository.findOne(id);
		colonne.setColor(colonneDTO.getColor());
		colonne.setTitle(colonneDTO.getTitle());
        return (ColonneKanbanDTO) transformers.convertEntityToDto(colonneKanbanRepository.save(colonne), ColonneKanbanDTO.class);
	}

	
	/* 
	 * (non-Javadoc)
	 * @see com.example.service.ColonneKanbanService#deleteColonneKanban(java.lang.Long)
	 */
	@Override
	public void deleteColonneKanban(Long id) {
		colonneKanbanRepository.updateColonneKanbanDelete(id);
		colonneKanbanRepository.delete(id);
	}
}
