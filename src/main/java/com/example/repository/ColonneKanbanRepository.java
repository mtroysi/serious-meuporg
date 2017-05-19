package com.example.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.ColonneKanban;


@Repository
public interface ColonneKanbanRepository extends PagingAndSortingRepository<ColonneKanban, Long> {


	@Modifying
	@Transactional
    @Query(value = "UPDATE task_user t SET t.colonne_kanban_id=null WHERE t.colonne_kanban_id=?1", nativeQuery = true)
	void updateColonneKanbanDelete(Long id);
}
