package com.ottocheung.jdbc.dao.subtask;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.ottocheung.jdbc.dao.story.StoryDaoImpl;
import com.ottocheung.jdbc.entity.SubTask;
import com.ottocheung.jdbc.helpers.JdbcHelper;

public class SubTaskDaoImpl implements SubTaskDao {

    private static final Logger LOGGER = Logger.getLogger(StoryDaoImpl.class.getName());

    private Connection connection;

    public SubTaskDaoImpl() {

        try {
            this.connection = JdbcHelper.getConnection();
            this.createSubTaskTable();
            LOGGER.info("Subtask table created successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            LOGGER.severe("Error creating subtask table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void createSubTaskTable() throws SQLException, ClassNotFoundException {
        LOGGER.info("Creating subtask table...");
        String createSubtaskTableSQL = "CREATE TABLE IF NOT EXISTS subtask (" +
                "subtask_id SERIAL PRIMARY KEY," +
                "story_id INT," +
                "title VARCHAR(255)," +
                "description TEXT," +
                "status VARCHAR(50)," +
                "created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP," +
                "updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (story_id) REFERENCES story(story_id)" +
                ")";
        connection.createStatement().execute(createSubtaskTableSQL);
    }

    @Override
    public void insertSubTask(SubTask subTask) {
        try {
            LOGGER.info("Inserting subtask...");
            String insertSubTaskSQL = "INSERT INTO subtask (story_id, title, description, status) VALUES (?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(insertSubTaskSQL);
            ps.setLong(1, subTask.getStoryId());
            ps.setString(2, subTask.getTitle());
            ps.setString(3, subTask.getDescription());
            ps.setString(4, subTask.getStatus());
            ps.executeUpdate();
            LOGGER.info("Subtask inserted successfully.");
        } catch (Exception e) {
            LOGGER.severe("Error inserting subtask: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void updateSubTask(SubTask toBeUpdated) {
        try {
            LOGGER.info("Updating subtask...");
            String updateSubTaskSQL = "UPDATE subtask SET story_id = ?, title = ?, description = ?, status = ? WHERE subtask_id = ?";
            PreparedStatement ps = connection.prepareStatement(updateSubTaskSQL);
            ps.setLong(1, toBeUpdated.getStoryId());
            ps.setString(2, toBeUpdated.getTitle());
            ps.setString(3, toBeUpdated.getDescription());
            ps.setString(4, toBeUpdated.getStatus());
            ps.setLong(5, toBeUpdated.getSubtaskId());
            ps.executeUpdate();
            LOGGER.info("Subtask updated successfully.");
        } catch (Exception e) {
            LOGGER.severe("Error updating subtask: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSubTaskById(Long subTaskId) {
        try {
            LOGGER.info("Deleting subtask...");
            String deleteSubTaskSQL = "DELETE FROM subtask WHERE subtask_id = ?";
            PreparedStatement ps = connection.prepareStatement(deleteSubTaskSQL);
            ps.setLong(1, subTaskId);
            ps.executeUpdate();
            LOGGER.info("Subtask deleted successfully.");
        } catch (Exception e) {
            LOGGER.severe("Error deleting subtask: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public SubTask getSubTaskById(Long subTaskId) {
        try {
            LOGGER.info("Getting subtask by id...");
            String getSubTaskByIdSQL = "SELECT * FROM subtask WHERE subtask_id = ?";
            PreparedStatement ps = connection.prepareStatement(getSubTaskByIdSQL);
            ps.setLong(1, subTaskId);
            SubTask subTask = (SubTask) ps.executeQuery();

            LOGGER.info("Subtask retrieved successfully.");
            return subTask;

        } catch (Exception e) {
            LOGGER.severe("Error getting subtask by id: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SubTask> getAllSubTasks() {
        try {
            LOGGER.info("Getting all subtasks...");
            String getAllSubTasksSQL = "SELECT * FROM subtask";
            PreparedStatement ps = connection.prepareStatement(getAllSubTasksSQL);
            List<SubTask> subTasks = new ArrayList<SubTask>();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                SubTask subTask = new SubTask();
                subTask.setSubtaskId(resultSet.getLong("subtask_id"));
                subTask.setStoryId(resultSet.getLong("story_id"));
                subTask.setTitle(resultSet.getString("title"));
                subTask.setDescription(resultSet.getString("description"));
                subTask.setStatus(resultSet.getString("status"));
                subTask.setCreatedAt(resultSet.getDate("created_at"));
                subTask.setUpdatedAt(resultSet.getDate("updated_at"));
                subTasks.add(subTask);
            }

            LOGGER.info("Subtasks retrieved successfully.");
            return subTasks;
        } catch (Exception e) {
            LOGGER.severe("Error getting all subtasks: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SubTask> getSubTasksByTitle(String title) {
        try {
            LOGGER.info("Getting subtasks by title...");
            String getSubTasksByTitleSQL = "SELECT * FROM subtask WHERE title = ?";
            PreparedStatement ps = connection.prepareStatement(getSubTasksByTitleSQL);
            ps.setString(1, title);
            List<SubTask> subTasks = new ArrayList<>();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                SubTask subTask = new SubTask();
                subTask.setSubtaskId(resultSet.getLong("subtask_id"));
                subTask.setStoryId(resultSet.getLong("story_id"));
                subTask.setTitle(resultSet.getString("title"));
                subTask.setDescription(resultSet.getString("description"));
                subTask.setStatus(resultSet.getString("status"));
                subTask.setCreatedAt(resultSet.getDate("created_at"));
                subTask.setUpdatedAt(resultSet.getDate("updated_at"));
                subTasks.add(subTask);
            }

            LOGGER.info("Subtasks retrieved successfully.");
            return subTasks;
        } catch (Exception e) {
            LOGGER.severe("Error getting subtasks by title: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<SubTask> getSubTasksByStoryId(Long storyId) {
        try {
            LOGGER.info("Getting subtasks by story id...");
            String getSubTasksByStoryIdSQL = "SELECT * FROM subtask WHERE story_id = ?";
            PreparedStatement ps = connection.prepareStatement(getSubTasksByStoryIdSQL);
            ps.setLong(1, storyId);
            List<SubTask> subTasks = new ArrayList<>();
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                SubTask subTask = new SubTask();
                subTask.setSubtaskId(resultSet.getLong("subtask_id"));
                subTask.setStoryId(resultSet.getLong("story_id"));
                subTask.setTitle(resultSet.getString("title"));
                subTask.setDescription(resultSet.getString("description"));
                subTask.setStatus(resultSet.getString("status"));
                subTask.setCreatedAt(resultSet.getDate("created_at"));
                subTask.setUpdatedAt(resultSet.getDate("updated_at"));
                subTasks.add(subTask);
            }

            LOGGER.info("Subtasks retrieved successfully.");
            return subTasks;
        } catch (Exception e) {
            LOGGER.severe("Error getting subtasks by story id: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
