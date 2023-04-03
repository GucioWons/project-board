package com.example.notification;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NotificationRepository {
    private final List<Notification> notifications;

    public NotificationRepository() {
        this.notifications = new ArrayList<>();
    }

    public Notification getNotificationById(Integer id){
        return notifications.stream()
                .filter(notification -> notification.getId().equals(id)).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Notification> getNotificationsByUserToId(Integer userToId){
        return notifications.stream()
                .filter(notification -> notification.getUserToId().equals(userToId))
                .toList();
    }

    public List<Notification> getNotificationsByUserToIdAndSeen(Integer userToId, boolean seen){
        return getNotificationsByUserToId(userToId).stream()
                .filter(notification -> notification.isSeen() == seen)
                .toList();
    }

    public Notification save(Notification newNotification){
        notifications.stream()
                .filter(notification -> notification.getId().equals(newNotification.getId())).findFirst()
                .ifPresent(notifications::remove);
        notifications.add(newNotification);
        return newNotification;
    }
}
