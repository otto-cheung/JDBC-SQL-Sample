package com.ottocheung.jdbc.dao.story;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import com.ottocheung.jdbc.entity.Story;
import com.ottocheung.jdbc.helpers.JdbcHelper;

public class StoryDaoImpl implements StoryDao {

    private static final Logger LOGGER = Logger.getLogger(StoryDaoImpl.class.getName());

    private Connection connection;

    public StoryDaoImpl() {

        try {
            this.connection = JdbcHelper.getConnection();
            this.createStoryTable();
            LOGGER.info("Story table created successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.severe("Error creating story table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void createStoryTable() throws SQLException, ClassNotFoundException {
        LOGGER.info("Creating story table...");
        String createStoryTableSQL = "CREATE TABLE IF NOT EXISTS story (" +
                "story_id SERIAL PRIMARY KEY," +
                "title VARCHAR(255)," +
                "description TEXT," +
                "created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP," +
                "updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP" +
                ")";
        connection.createStatement().execute(createStoryTableSQL);
    }

    @Override
    public void insertStory(Story story) throws SQLException {

    }

    @Override
    public void updateStory(Story toBeUpdated) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateStory'");
    }

    @Override
    public void deleteStoryById(Long storyId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteStory'");
    }

    @Override
    public Story getStoryById(Long storyId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStoryById'");
    }

    @Override
    public List<Story> getAllStories() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllStories'");
    }

    @Override
    public List<Story> getStoriesByTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getStoriesByTitle'");
    }

}
