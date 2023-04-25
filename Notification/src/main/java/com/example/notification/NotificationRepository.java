package com.example.notification;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends CrudRepository<Notification, Integer> {
    List<Notification> findByUserToId(Integer userToId);
    List<Notification> findAllByUserToIdAndSeen(Integer userToId, boolean seen);
}
