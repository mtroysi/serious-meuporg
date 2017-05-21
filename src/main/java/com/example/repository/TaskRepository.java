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
	
	@Query(value="SELECT t.* FROM task t WHERE t.is_bid = true AND t.board_id=?1 AND date_end_bid > ?2", nativeQuery = true)
	List<Task> findTaskBidByBoardIdAndDate(Long boardId, Date dateFinBid);
	
	@Query(value="SELECT * FROM task t WHERE t.board_id=?1 AND t.id NOT IN (SELECT task_id FROM task_user)", nativeQuery = true)
	List<Task> findTaskByUserIsNullAndBoardId(Long boardId);
}
