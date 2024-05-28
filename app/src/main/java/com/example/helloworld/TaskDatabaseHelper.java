package com.example.helloworld;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class TaskDatabaseHelper {

    private SQLiteDatabase db;

    public TaskDatabaseHelper(SQLiteDatabase db) {
        this.db = db;
    }

    public void createTable() {
        String CREATE_TASKS_TABLE =
                  "CREATE TABLE IF NOT EXISTS tasks ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "task_name TEXT,"
                + "task_description TEXT,"
                + "reward INTEGER,"
                + "last_completion TEXT,"
                + "related_skill TEXT)";
        db.execSQL(CREATE_TASKS_TABLE);
    }

    public void addTask(Task task) {
        ContentValues values = new ContentValues();
        values.put("task_name", task.getName());
        values.put("task_description", task.getDescription());
        values.put("reward", task.getReward());
        values.put("last_completion", task.getLastCompletion());

        db.insert("tasks", null, values);
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        Cursor cursor = db.query("tasks", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("task_name"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("task_description"));
                @SuppressLint("Range") int reward = cursor.getInt(cursor.getColumnIndex("reward"));
                @SuppressLint("Range") String lastCompletion = cursor.getString(cursor.getColumnIndex("last_completion"));

                Task task = new Task(id, name, description, reward, lastCompletion);
                taskList.add(task);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return taskList;
    }
}
