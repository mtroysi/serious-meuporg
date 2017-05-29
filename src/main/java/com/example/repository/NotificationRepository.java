package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.model.Notification;


@Repository
public interface NotificationRepository extends PagingAndSortingRepository<Notification, Long> {
	
	/**
	 * Retourne la liste des notifications par utilisateur
	 * @param userId : id de l'utilisateur
	 * @return liste des notifications
	 */
	List<Notification> findAllByUserIdOrderByDateCreationDesc(Long userId);
	
	/**
	 * Modifie le statut "is_read" des notifications d'un utilisateur
	 * @param userId : id de l'utilisateur
	 */
	@Modifying
	@Transactional
    @Query(value = "UPDATE notification n SET n.is_read=true WHERE n.user_id=?1", nativeQuery = true)
	void updateReadNotificationByUser(Long userId);
}
