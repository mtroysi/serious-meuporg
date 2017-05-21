package com.example.repository;

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
	
	@Query(value="SELECT * FROM task t WHERE t.board_id=?1 AND t.id NOT IN (SELECT task_id FROM task_user)", nativeQuery = true)
	List<Task> findTaskByUserIsNullAndBoardId(Long boardId);
}
