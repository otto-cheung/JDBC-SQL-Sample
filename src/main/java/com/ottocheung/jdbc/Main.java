package com.ottocheung.jdbc;

import java.sql.SQLException;

import com.ottocheung.jdbc.dao.story.StoryDaoImpl;
import com.ottocheung.jdbc.dao.subtask.SubTaskDaoImpl;

public class Main {
    public static void main(String[] args) throws SQLException {

        StoryDaoImpl storyDaoImpl = new StoryDaoImpl();
        SubTaskDaoImpl subTaskDaoImpl = new SubTaskDaoImpl();

    }
}