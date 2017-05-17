package com.example.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Periodicity;


@Repository
public interface PeriodicityRepository extends PagingAndSortingRepository<Periodicity, Long> {
}
