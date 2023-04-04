package com.example.notification;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String text;
    private Integer userToId;
    private LocalDateTime date;
    private boolean seen;

    public Notification() {
        this.date = LocalDateTime.now();
        this.seen = false;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Integer getUserToId() {
        return userToId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }
}
