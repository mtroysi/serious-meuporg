package com.example.repository;

import com.example.model.Board;
import com.example.model.Notification;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {

	List<Notification> findAllByUserIdOrderByDateCreation(Long userId);
}
