package com.example.service;

import java.util.List;

import com.example.dto.NotificationDTO;

public interface NotificationService {

	/**
	 * Get list of Notification by user id 
	 * @param userId
	 * @return
	 */
	List<NotificationDTO> getNotificationByUserId(Long userId);
 
}
