package com.example.notification;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/notifications")
@RequiredArgsConstructor
public class NotificationController {
    private final NotificationService notificationService;

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificationService.createNotification(notification));
    }

    @GetMapping
    public ResponseEntity<List<Notification>> getNotificationsByUserToIdAndSeen(@RequestParam Integer userToId,
                                                                         @RequestParam Optional<Boolean> seen){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(getNotifications(userToId, seen));
    }

    private List<Notification> getNotifications(Integer userToId, Optional<Boolean> seen){
        return seen
                .map(isSeen -> notificationService.getNotificationsByUserToIdAndSeen(userToId, isSeen))
                .orElseGet(() -> notificationService.getNotificationsByUserToId(userToId));
    }

    @PostMapping("/set-seen")
    public ResponseEntity<Notification> setNotificationSeen(@RequestParam Integer notificationId){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(notificationService.setNotificationSeen(notificationId));
    }
}
