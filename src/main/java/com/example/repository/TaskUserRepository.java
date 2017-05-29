package com.example.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.TaskUser;


@Repository
public interface TaskUserRepository extends PagingAndSortingRepository<TaskUser, Long> {
	
	/**
	 * Retourne la liste des taches par rapport à un utilisateur et a un tableau
	 * @param userId : id de l'utilisateur
	 * @param boardId : id du tableau
	 * @return liste de TaskUser
	 */
	List<TaskUser> findAllByUserIdAndTaskBoardId(Long userId, Long boardId);
	
	/**
	 * Retourne la liste des taches par tableau
	 * @param boardId : id du tableau
	 * @return liste de TaskUser
	 */
	List<TaskUser> findAllByTaskBoardId(Long boardId);
	
	/**
	 * Retourne la liste des taches par utilisateur
	 * @param boardId : id du tableau
	 * @return liste de TaskUser
	 */
	List<TaskUser> findAllByUserId(Long boardId);
}
