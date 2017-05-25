package com.example.repository;

import com.example.model.Board;
import com.example.model.Notification;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {

	List<Notification> findAllByUserIdOrderByDateCreationDesc(Long userId);

	@Modifying
	@Transactional
    @Query(value = "UPDATE notification n SET n.is_read=true WHERE n.user_id=?1", nativeQuery = true)
	void updateReadNotificationByUser(Long userId);
}
