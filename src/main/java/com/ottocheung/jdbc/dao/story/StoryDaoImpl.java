package com.ottocheung.jdbc.dao.story;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public boolean isTableEmpty() {
        try {
            String isTableEmptySQL = "SELECT * FROM story";
            PreparedStatement ps = connection.prepareStatement(isTableEmptySQL);
            ResultSet resultSet = ps.executeQuery();
            return !resultSet.next();
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception checking if story table is empty: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Error checking if story table is empty: " + e.getMessage());
        }
        return false;
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
    public void insertStory(Story story) {
        try {
            LOGGER.info("Inserting story...");
            String insertStorySQL = "INSERT INTO story (title, description) VALUES (?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertStorySQL);
            ps.setString(1, story.getTitle());
            ps.setString(2, story.getDescription());
            ps.executeUpdate();
            LOGGER.info("Story inserted successfully.");
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception inserting story: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            LOGGER.severe("Error inserting story: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateStory(Story toBeUpdated) {
        try {
            LOGGER.info("Updating story...");
            String updateStorySQL = "UPDATE story SET title = ?, description = ? WHERE story_id = ?";
            PreparedStatement ps = connection.prepareStatement(updateStorySQL);
            ps.setString(1, toBeUpdated.getTitle());
            ps.setString(2, toBeUpdated.getDescription());
            ps.setLong(3, toBeUpdated.getStoryId());
            ps.executeUpdate();
            LOGGER.info("Story updated successfully.");
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception updating story: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Error updating story: " + e.getMessage());
        }
    }

    @Override
    public void deleteStoryById(Long storyId) {
        try {
            LOGGER.info("Deleting story...");
            String deleteStorySQL = "DELETE FROM story WHERE story_id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteStorySQL);

            ps.setLong(1, storyId);
            ps.executeUpdate();

            String deleteSubstoryAssociatedWithStorySQL = "DELETE FROM subtask WHERE story_id = ?";
            PreparedStatement ps2 = connection.prepareStatement(deleteSubstoryAssociatedWithStorySQL);
            ps2.setLong(1, storyId);
            ps2.executeUpdate();

            LOGGER.info("Story deleted successfully.");
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception deleting story: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Error deleting story: " + e.getMessage());
        }
    }

    @Override
    public Story getStoryById(Long storyId) {
        try {
            LOGGER.info("Getting story by ID...");
            String getStorySQL = "SELECT * FROM story WHERE story_id = ?";
            PreparedStatement ps = connection.prepareStatement(getStorySQL);
            ps.setLong(1, storyId);
            ResultSet resultSet = ps.executeQuery();

            Story story = new Story(
                    resultSet.getLong("story_id"),
                    resultSet.getString("title"),
                    resultSet.getString("description"),
                    resultSet.getTimestamp("created_at"),
                    resultSet.getTimestamp("updated_at"));
            LOGGER.info("Story retrieved successfully.");

            return story;
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception getting story: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Error getting story: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Story> getAllStories() {
        try {
            LOGGER.info("Getting all stories...");
            String getAllStoriesSQL = "SELECT * FROM story";
            PreparedStatement ps = connection.prepareStatement(getAllStoriesSQL);
            ResultSet resultSet = ps.executeQuery();
            ArrayList<Story> stories = new ArrayList<Story>();

            while (resultSet.next()) {
                Story story = new Story(
                        resultSet.getLong("story_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getTimestamp("updated_at"));
                stories.add(story);

            }
            LOGGER.info("Stories retrieved successfully.");
            return stories;
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception getting all stories: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Error getting all stories: " + e.getMessage());
        }

        return null;
    }

    @Override
    public List<Story> getStoriesByTitle(String title) {
        try {
            LOGGER.info("Getting stories by title...");
            String getStoriesByTitleSQL = "SELECT * FROM story WHERE title = ?";
            PreparedStatement ps = connection.prepareStatement(getStoriesByTitleSQL);
            ps.setString(1, title);
            ResultSet resultSet = ps.executeQuery();
            ArrayList<Story> stories = new ArrayList<Story>();

            while (resultSet.next()) {
                Story story = new Story(
                        resultSet.getLong("story_id"),
                        resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getTimestamp("created_at"),
                        resultSet.getTimestamp("updated_at"));
                stories.add(story);

            }

            return stories;
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception getting stories by title: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Error getting stories by title: " + e.getMessage());
        }

        return null;
    }

    public void deleteAllStories() {
        try {
            LOGGER.info("Deleting all stories...");
            String deleteAllStoriesSQL = "DELETE FROM story; ALTER SEQUENCE story_story_id_seq RESTART WITH 1";
            PreparedStatement ps = connection.prepareStatement(deleteAllStoriesSQL);
            ps.executeUpdate();

            String deleteAllSubstoriesAssociatedWithStorySQL = "DELETE FROM subtask; ALTER SEQUENCE subtask_subtask_id_seq RESTART WITH 1";
            PreparedStatement ps2 = connection.prepareStatement(deleteAllSubstoriesAssociatedWithStorySQL);
            ps2.executeUpdate();

            LOGGER.info("All stories and subtasks deleted successfully.");
        } catch (SQLException e) {
            LOGGER.severe("SQL Exception deleting all stories: " + e.getMessage());
        } catch (Exception e) {
            LOGGER.severe("Error deleting all stories: " + e.getMessage());
        }
    }

}
