package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Task;
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
	
	/**
	 * Retorune la liste des taches qui ne sont pas aux enchères et qui n'ont pas d'utilisateur assigné
	 * @param boardId : id du tableau
	 * @return liste des taches
	 */
	@Query(value="SELECT * FROM task_user ttuu LEFT JOIN task_user_user tuu ON ttuu.id = tuu.task_user_id WHERE tuu.user_id IS NULL AND ttuu.task_id IN ( SELECT t.id FROM task t WHERE t.is_bid = true AND t.board_id=?1)", nativeQuery = true)
	List<TaskUser> findTaskUserByUserIsNullAndBoardId(Long boardId);
	
	/**
	 * Retourne la liste des taches aux enchères dasn la date de fin est supérieure à la date en parametre
	 * @param boardId : id du tableau
	 * @param dateEndBid : date de fin des enchères
	 * @return liste des taches
	 */
	@Query(value="SELECT * FROM task_user ttuu WHERE ttuu.task_id IN (SELECT t.id FROM task t WHERE t.is_bid = true AND t.board_id=?1 AND t.date_end_bid > ?2)", nativeQuery = true)
	List<TaskUser> findTaskUserBidByBoardIdAndDate(Long boardId, Date dateEndBid);
}
