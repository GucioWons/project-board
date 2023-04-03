package com.example.notification;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificationService.createNotification(notification));
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getNotificationsByUserToId(@RequestParam Integer userToId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationService.getNotificationsByUserToId(userToId));
    }
}
