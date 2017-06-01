package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Task;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
	
	/**
	 * Retourne la liste des tâches dont le titre contient la requête
	 * @param query requête
	 * @return liste de Task
	 */
	List<Task> findByTitleContaining(String query);
}
