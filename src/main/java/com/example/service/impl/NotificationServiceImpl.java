package com.example.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.NotificationDTO;
import com.example.model.Notification;
import com.example.repository.NotificationRepository;
import com.example.service.NotificationService;
import com.example.transformers.Transformers;


@Service
public class NotificationServiceImpl implements NotificationService {
    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private Transformers transformers;

	@Override
	public List<NotificationDTO> getNotificationByUserId(Long userId) {
		  List<Notification> list = notificationRepository.findAllByUserIdOrderByDateCreation(userId);


        return list.stream()
                .map((Notification notif) -> (NotificationDTO)transformers.convertEntityToDto(notif, NotificationDTO.class))
                .collect(Collectors.toList());
	}

   
}
