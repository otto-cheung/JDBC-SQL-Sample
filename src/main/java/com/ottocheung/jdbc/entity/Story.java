package com.ottocheung.jdbc.entity;

import java.util.Date;

public class Story {
    private Long storyId;
    private String title;
    private String description;
    private Date createdAt;
    private Date updatedAt;

    public Story() {
    }

    public Story(Long storyId, String title, String description, Date createdAt, Date updatedAt) {
        this.storyId = storyId;
        this.title = title;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Story(String title, String description) {
        this.title = title;
        this.description = description;
    }

    // getters and setters
    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long storyId) {
        this.storyId = storyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "Story [storyId=" + storyId + ", title=" + title + ", description=" + description + ", createdAt="
                + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}
