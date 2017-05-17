package com.example.repository;

import com.example.model.Tag;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Florentin NOËL on 16/05/17.
 */
@Repository
public interface TagRepository extends PagingAndSortingRepository<Tag, Long> {
}
