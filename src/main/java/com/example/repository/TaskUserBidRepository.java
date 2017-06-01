package com.example.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Task;
import com.example.model.TaskUserBid;


@Repository
public interface TaskUserBidRepository extends PagingAndSortingRepository<TaskUserBid, Long> {
	
	/**
	 * Retourne la liste des taches aux enhères qui se trouve avant la date de fin des enchères
	 * @param boardId : id du tableau
	 * @param dateFinBid : date de fin
	 * @return liste de TaskUserBid
	 */
	List<TaskUserBid> findByTaskUserTaskBoardIdAndTaskUserTaskDateEndBidBefore(Long boardId, Date dateFinBid);
	
	/**
	 * Retourne une tache enchères par rapport à un utilisateur et une tacheUser
	 * @param task_id : id de la tacheUser
	 * @param user_id : id de l'utilisateur
	 * @return liste de tache
	 */
	TaskUserBid findByTaskUserIdAndUserId(Long taskUser_id, Long user_id);
	
}
