package com.example.repository;

import com.example.model.Task;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Florentin NOËL on 11/05/17.
 */
@Repository
public interface TaskRepository extends PagingAndSortingRepository<Task, Long> {
}
