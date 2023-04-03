package com.example.notification;

import java.time.LocalDateTime;

public class Notification {
    private final Integer id;
    private final String text;
    private final Integer userToId;
    private final LocalDateTime date;
    private boolean seen;

    public Notification(Integer id, String text, Integer userToId) {
        this.id = id;
        this.text = text;
        this.userToId = userToId;
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
