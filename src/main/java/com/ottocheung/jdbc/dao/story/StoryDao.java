package com.ottocheung.jdbc.dao.story;

import java.sql.SQLException;
import java.util.List;

import com.ottocheung.jdbc.entity.Story;

public interface StoryDao {
    public void createStoryTable() throws SQLException, ClassNotFoundException;

    public void insertStory(Story story) throws SQLException;

    public void updateStory(Story story) throws SQLException;

    public void deleteStoryById(Long storyId) throws SQLException;

    public Story getStoryById(Long storyId) throws SQLException;

    public List<Story> getAllStories() throws SQLException;

    public List<Story> getStoriesByTitle(String title) throws SQLException;
}
