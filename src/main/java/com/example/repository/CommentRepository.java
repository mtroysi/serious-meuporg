package com.example.repository;

import com.example.model.Comment;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by Florentin NOËL on 17/05/17.
 */
public interface CommentRepository extends PagingAndSortingRepository<Comment, Long> {
}
