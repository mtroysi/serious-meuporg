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

	/**
	 * Crée une colonne Kanban
	 * @param colonneKanbanDTO informations de la colonne à créer
	 * @param boardId id du tableau dans lequel créer la colonne
	 * @return DTO de la colonne créée
	 */
	@Override
	public ColonneKanbanDTO createColonneKanban(ColonneKanbanDTO colonneKanbanDTO, Long boardId) {
		ColonneKanban ck;
		ck = (ColonneKanban)transformers.convertDtoToEntity(colonneKanbanDTO, ColonneKanban.class);
		ck.setBoard(boardRepository.findOne(boardId));
        return (ColonneKanbanDTO) transformers.convertEntityToDto(colonneKanbanRepository.save(ck), ColonneKanbanDTO.class);
	}

	/**
	 * Modifie une colonne Kanban
	 * @param id l'id de la colonne à modifier
	 * @param colonneDTO données de la colonne modifiée
	 * @return DTO de la colonne modifiée
	 */
	@Override
	public ColonneKanbanDTO updateColonneKanban(Long id, ColonneKanbanDTO colonneDTO) {
		ColonneKanban colonne = colonneKanbanRepository.findOne(id);
		colonne.setColor(colonneDTO.getColor());
		colonne.setTitle(colonneDTO.getTitle());
        return (ColonneKanbanDTO) transformers.convertEntityToDto(colonneKanbanRepository.save(colonne), ColonneKanbanDTO.class);
	}

	/**
	 * Supprime une colonne Kanban
	 * @param id l'id de la colonne à supprimer
	 */
	@Override
	public void deleteColonneKanban(Long id) {
		colonneKanbanRepository.updateColonneKanbanDelete(id);
		colonneKanbanRepository.delete(id);
	}
}
