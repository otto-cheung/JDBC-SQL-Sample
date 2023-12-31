package com.ottocheung.jdbc.entity;

import java.util.Date;
import com.ottocheung.jdbc.dao.subtask.SubTaskStatus;

public class SubTask {
    private Long subtaskId;
    private Long storyId; // Foreign key referencing story_id
    private String title;
    private String description;
    private SubTaskStatus status;
    private Date createdAt;
    private Date updatedAt;

    public SubTask() {
    }

    public SubTask(Long subtaskId, Long storyId, String title, String description, SubTaskStatus status, Date createdAt,
            Date updatedAt) {
        this.subtaskId = subtaskId;
        this.storyId = storyId;
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public SubTask(Long storyId, String title, String description, SubTaskStatus status) {
        this.storyId = storyId;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    // getters and setters
    public Long getSubtaskId() {
        return subtaskId;
    }

    public void setSubtaskId(Long subtaskId) {
        this.subtaskId = subtaskId;
    }

    public Long getStoryId() {
        return storyId;
    }

    public void setStoryId(Long story_id) {
        this.storyId = story_id;
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

    public String getStatus() {
        return status.toString();
    }

    public void setStatus(SubTaskStatus status) {
        this.status = status;
    }

    public void setStatus(String status) {
        this.status = SubTaskStatus.valueOf(status);
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
        return "SubTask [subtaskId=" + subtaskId + ", storyId=" + storyId + ", title=" + title + ", description="
                + description + ", status=" + status + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "]";
    }

}
