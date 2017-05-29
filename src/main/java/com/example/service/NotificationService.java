package com.example.service;

import java.util.List;

import com.example.dto.NotificationDTO;

public interface NotificationService {

	/**
	 * Retourne la liste des notifications d'un utilisateur donné
	 * @param userId id de l'utilisateur
	 * @return la liste des notifications d'un utilisateur donné
	 */
	List<NotificationDTO> getNotificationByUserId(Long userId);
	
	/**
	 * Marque toutes les notifications de l'utilisateur comme lues.
	 * @param userId l'id de l'utilisateur
	 */
	void readAllNotification(Long userId);
	
	/**
	 * Crée une notifications
	 * @param notif données de la notification
	 * @return DTO de la notification créée
	 */
	NotificationDTO createNotification(NotificationDTO notif);
	
 
}
