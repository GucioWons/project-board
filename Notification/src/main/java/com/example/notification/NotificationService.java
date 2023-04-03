package com.example.notification;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationService {
    private final NotificationRepository notificationRepository;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public Notification createNotification(Notification notification){
        notificationRepository.addNotification(notification);
        return notification;
    }

    public List<Notification> getNotificationsByUserToId(Integer userId){
        return notificationRepository.getNotificationsByUserToId(userId);
    }
}
