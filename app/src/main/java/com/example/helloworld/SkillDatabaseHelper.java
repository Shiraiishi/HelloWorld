package com.example.helloworld;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;


public class SkillDatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "skills.db";
    private static final int DATABASE_VERSION = 1;

    // Table name
    private static final String TABLE_SKILLS = "skills";

    // Column names
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_SKILL_NAME = "skill_name";
    private static final String COLUMN_SKILL_DESC = "skill_desc";
    private static final String COLUMN_SKILL_LEVEL = "skill_level";
    private static final String COLUMN_SKILL_EXP = "skill_exp";

    // Create table SQL statement
    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_SKILLS + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_SKILL_NAME + " TEXT, " +
                    COLUMN_SKILL_DESC + " TEXT, " +
                    COLUMN_SKILL_LEVEL + " INTEGER DEFAULT 1, " +
                    COLUMN_SKILL_EXP + " INTEGER DEFAULT 0);";

    private Context context;

    public SkillDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        // Check if database exists before creating it
        if (!doesDatabaseExist(context, DATABASE_NAME)) {
            SQLiteDatabase db = this.getWritableDatabase();
            onCreate(db);
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILLS);
        onCreate(db);
    }

    // Method to check if the database already exists
    private boolean doesDatabaseExist(Context context, String dbName) {
        SQLiteDatabase db = null;
        try {
            String path = context.getDatabasePath(dbName).getPath();
            db = SQLiteDatabase.openDatabase(path, null, SQLiteDatabase.OPEN_READONLY);
            db.close();
        } catch (Exception e) {
            Log.e("Database", "Database does not exist");
        }
        return db != null;
    }

    // Method to add a new skill
    public void addSkill(String name, String desc) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SKILL_NAME, name);
        values.put(COLUMN_SKILL_DESC, desc);
        db.insert(TABLE_SKILLS, null, values);
        db.close();
    }

    // Method to get all skills
    public ArrayList<Skill> getAllSkillsAsArrayList() {
        ArrayList<Skill> skillsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_SKILLS, null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") int id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(COLUMN_SKILL_NAME));
                @SuppressLint("Range") String desc = cursor.getString(cursor.getColumnIndex(COLUMN_SKILL_DESC));
                @SuppressLint("Range") int level = cursor.getInt(cursor.getColumnIndex(COLUMN_SKILL_LEVEL));
                @SuppressLint("Range") int exp = cursor.getInt(cursor.getColumnIndex(COLUMN_SKILL_EXP));

                Skill skill = new Skill(id, name, desc, level, exp);
                skillsList.add(skill);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return skillsList;
    }

    // Method to update a skill
    public int updateSkill(long id, String name, String desc, int level, int exp) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SKILL_NAME, name);
        values.put(COLUMN_SKILL_DESC, desc);
        values.put(COLUMN_SKILL_LEVEL, level);
        values.put(COLUMN_SKILL_EXP, exp);
        return db.update(TABLE_SKILLS, values, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    // Method to delete a skill
    public void deleteSkill(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SKILLS, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
        db.close();
    }
}
