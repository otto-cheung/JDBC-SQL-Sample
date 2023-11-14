package com.ottocheung.jdbc.dao.subtask;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
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
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'insertSubTask'");
    }

    @Override
    public void updateSubTask(SubTask toBeUpdated) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateSubTask'");
    }

    @Override
    public void deleteSubTaskById(Long subTaskId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteSubTask'");
    }

    @Override
    public SubTask getSubTaskById(Long subTaskId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubTaskById'");
    }

    @Override
    public List<SubTask> getAllSubTasks() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllSubTasks'");
    }

    @Override
    public List<SubTask> getSubTasksByTitle(String title) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubTasksByTitle'");
    }

    @Override
    public List<SubTask> getSubTasksByStoryId(Long storyId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSubTasksByStoryId'");
    }

}
