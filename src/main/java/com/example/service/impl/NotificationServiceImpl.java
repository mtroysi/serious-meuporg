package com.example.service.impl;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dto.NotificationDTO;
import com.example.model.Notification;
import com.example.model.User;
import com.example.repository.NotificationRepository;
import com.example.repository.UserRepository;
import com.example.service.NotificationService;
import com.example.transformers.Transformers;


@Service
public class NotificationServiceImpl implements NotificationService {
	
    @Autowired
    private NotificationRepository notificationRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private Transformers transformers;

	@Override
	public List<NotificationDTO> getNotificationByUserId(Long userId) {
		  List<Notification> list = notificationRepository.findAllByUserIdOrderByDateCreation(userId);


        return list.stream()
                .map((Notification notif) -> (NotificationDTO)transformers.convertEntityToDto(notif, NotificationDTO.class))
                .collect(Collectors.toList());
	}

	@Override
	public void readAllNotification(Long userId) {
		notificationRepository.updateReadNotificationByUser(userId);
	}
	

	@Override
	public NotificationDTO createNotification(NotificationDTO notifDTO){
		Notification notif = (Notification) transformers.convertDtoToEntity(notifDTO, NotificationDTO.class);
		User user = userRepository.findOne(notifDTO.getUserId());
		notif.setId(null);
		notif.setIsRead(true);
		notif.setUser(user);
		notif.setDateCreation(new Date());

        return (NotificationDTO)transformers.convertEntityToDto(notificationRepository.save(notif), NotificationDTO.class);
	}
   
}
