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
        return notificationRepository.save(notification);
    }

    public List<Notification> getNotificationsByUserToId(Integer userId){
        return notificationRepository.findByUserToId(userId);
    }

    public List<Notification> getNotificationsByUserToIdAndSeen(Integer userId, boolean seen){
        return notificationRepository.findAllByUserToIdAndSeen(userId, seen);
    }

    public Notification setNotificationSeen(Integer notificationId){
        return notificationRepository.findById(notificationId)
                .map(this::updateNotificationSeen)
                .orElseThrow(() -> new IllegalArgumentException("Wrong notification id!"));
    }

    private Notification updateNotificationSeen(Notification notification){
        notification.setSeen(true);
        return notificationRepository.save(notification);
    }
}
