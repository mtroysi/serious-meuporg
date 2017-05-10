package com.example.repository;

import com.example.model.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Morgane TROYSI on 27/04/2017.
 */

@Repository
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long> {
    List<Customer> findAllByName(String name);
}
