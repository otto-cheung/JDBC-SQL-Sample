package com.ottocheung.jdbc.dao.subtask;

import java.sql.SQLException;
import java.util.List;

import com.ottocheung.jdbc.entity.SubTask;

public interface SubTaskDao {
    public void createSubTaskTable() throws SQLException, ClassNotFoundException;

    public void insertSubTask(SubTask subTask);

    public void updateSubTask(SubTask subTask);

    public void deleteSubTaskById(Long subTaskId);

    public SubTask getSubTaskById(Long subTaskId);

    public List<SubTask> getAllSubTasks();

    public List<SubTask> getSubTasksByTitle(String title);

    public List<SubTask> getSubTasksByStoryId(Long storyId);
}
