package com.example.demo.model;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class Task {
    private long id;
    private String content;
    private LocalDateTime updatedTime;

    public Task() {
    }

    public Task(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime() {
        this.updatedTime = LocalDateTime.now();
    }

    public void setContent(String content) {
        this.content = content;
    }
}