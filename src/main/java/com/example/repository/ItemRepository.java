package com.example.repository;

import com.example.model.Item;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Morgane TROYSI on 19/05/17.
 */

@Repository
public interface ItemRepository extends PagingAndSortingRepository<Item, Long> {
}
