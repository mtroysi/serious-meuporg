package com.example.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.dto.BoardDTO;
import com.example.dto.NotificationDTO;
import com.example.service.NotificationService;



@RestController
@RequestMapping("/api/notification")
public class NotificationController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    NotificationService notificationService;


    /**
     * Return list of notifications by user
     * @param user_id user id
     * @return list of NotificationDTO
     */
    @RequestMapping(value = "/user/{user_id}", method = RequestMethod.GET)
    public ResponseEntity<List<NotificationDTO>> getListByUser(@PathVariable(value = "user_id") Long user_id) {
        logger.info("Calling NotificationController.getListBoardByUser with {}", user_id);

        List<NotificationDTO> list = notificationService.getNotificationByUserId(user_id);

        if (CollectionUtils.isEmpty(list)) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
    
    /**
     * Read all notification by user
     * @param user_id
     * @return list of NotificationDTO
     */
    @RequestMapping(value = "/user/{user_id}/read", method = RequestMethod.GET)
    public void readAllNotification(@PathVariable(value = "user_id") Long user_id) {
        logger.info("Calling NotificationController.readAllNotification with {}", user_id);
        notificationService.readAllNotification(user_id);
    }
    
    /**
     * Create Notification
     * @param notifDTO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public NotificationDTO createBoard(@RequestBody NotificationDTO notifDTO) {
        return notificationService.createNotification(notifDTO);
    }
    
    

}
