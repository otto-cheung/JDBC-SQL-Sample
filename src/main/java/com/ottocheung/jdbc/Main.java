package com.ottocheung.jdbc;

import java.sql.SQLException;
import java.util.List;

import com.ottocheung.jdbc.dao.story.StoryDaoImpl;
import com.ottocheung.jdbc.dao.subtask.SubTaskDaoImpl;
import com.ottocheung.jdbc.dao.subtask.SubTaskStatus;
import com.ottocheung.jdbc.entity.Story;
import com.ottocheung.jdbc.entity.SubTask;

public class Main {
    public static void main(String[] args) throws SQLException {

        try {
            /// Initialize Database and DAOs ///
            StoryDaoImpl storyDaoImpl = new StoryDaoImpl();
            SubTaskDaoImpl subTaskDaoImpl = new SubTaskDaoImpl();
            // clean the table if it is not empty
            if (!storyDaoImpl.isTableEmpty()) {
                storyDaoImpl.deleteAllStories();
            }

            /// Story queries ///
            Story story1 = new Story("Story 1", "Story 1 description");
            Story story2 = new Story("Story 2", "Story 2 description");
            // create story
            storyDaoImpl.insertStory(story1);
            storyDaoImpl.insertStory(story2);
            // update story
            story1.setTitle("Story 1 updated");
            storyDaoImpl.updateStory(story1);
            // get all stories
            List<Story> stories = storyDaoImpl.getAllStories();
            System.out.println("All stories: " + stories);
            // get story by id
            System.out.println("Story 1: " + storyDaoImpl.getStoryById(1L));
            // get story by title
            System.out.println("Story 2: " + storyDaoImpl.getStoriesByTitle("Story 2"));

            /// SubTask queries ///
            SubTask subTask1 = new SubTask(1L, "SubTask 1", "SubTask 1 description", SubTaskStatus.TODO);
            SubTask subTask2 = new SubTask(2L, "SubTask 2", "SubTask 2 description", SubTaskStatus.IN_PROGRESS);
            // create subtask
            subTaskDaoImpl.insertSubTask(subTask1);
            subTaskDaoImpl.insertSubTask(subTask2);
            // update subtask
            subTask1.setStatus(SubTaskStatus.DONE);
            subTaskDaoImpl.updateSubTask(subTask2);
            // get all subtasks
            List<SubTask> subTasks = subTaskDaoImpl.getAllSubTasks();
            System.out.println("All subtasks: " + subTasks);
            // get subtask by story
            System.out.println("Subtasks of story 1: " + subTaskDaoImpl.getSubTasksByStoryId(1L));
            // get subtask by title
            System.out.println("Subtasks with title 'SubTask 2': " + subTaskDaoImpl.getSubTasksByTitle("SubTask 2"));
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }

    }
}