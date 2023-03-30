package com.example.notification;

import java.util.ArrayList;
import java.util.List;

public class NotificationRepository {
    private final List<Notification> notifications;

    public NotificationRepository() {
        this.notifications = new ArrayList<>();
    }

    public void addNotification(Notification notification){
        notifications.add(notification);
    }

    public Notification getNotificationById(Integer id){
        return notifications.stream()
                .filter(notification -> notification.getId() == id).findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public List<Notification> getNotificationsByUserToId(Integer userToId){
        return notifications.stream()
                .filter(notification -> notification.getUserToId() == userToId)
                .toList();
    }
}
