package com.example.helloworld;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "user.db";
    private static final int DATABASE_VERSION = 1;

    public UserDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tables will be created in their respective helper classes
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS skills");
        db.execSQL("DROP TABLE IF EXISTS tasks");
        onCreate(db);
    }

    public void createSkillsTableIfNotExists() {
        SQLiteDatabase db = this.getWritableDatabase();
        SkillDatabaseHelper skillDbHelper = new SkillDatabaseHelper(db);
        skillDbHelper.createTable();
    }

    public void createTasksTableIfNotExists() {
        SQLiteDatabase db = this.getWritableDatabase();
        TaskDatabaseHelper taskDbHelper = new TaskDatabaseHelper(db);
        taskDbHelper.createTable();
    }

    public SkillDatabaseHelper getSkillDatabaseHelper() {
        return new SkillDatabaseHelper(this.getWritableDatabase());
    }

    public TaskDatabaseHelper getTaskDatabaseHelper() {
        return new TaskDatabaseHelper(this.getWritableDatabase());
    }
}
