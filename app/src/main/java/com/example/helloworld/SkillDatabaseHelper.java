package com.example.helloworld;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class SkillDatabaseHelper {

    private SQLiteDatabase db;

    public SkillDatabaseHelper(SQLiteDatabase db) {
        this.db = db;
    }

    public void createTable() {
        String CREATE_SKILLS_TABLE =
                  "CREATE TABLE IF NOT EXISTS skills ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "skill_name TEXT,"
                + "skill_desc TEXT,"
                + "skill_level INTEGER DEFAULT 0,"
                + "skill_exp INTEGER DEFAULT 0)";
        db.execSQL(CREATE_SKILLS_TABLE);
    }

    public void addSkill(String name, String desc) {
        ContentValues values = new ContentValues();
        values.put("skill_name", name);
        values.put("skill_desc", desc);
        db.insert("skills", null, values);
    }

    public ArrayList<Skill> getAllSkills() {
        ArrayList<Skill> skillsList = new ArrayList<>();
        Cursor cursor = db.query("skills", null, null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                @SuppressLint("Range") long id = cursor.getLong(cursor.getColumnIndex("id"));
                @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("skill_name"));
                @SuppressLint("Range") String description = cursor.getString(cursor.getColumnIndex("skill_desc"));
                @SuppressLint("Range") int level = cursor.getInt(cursor.getColumnIndex("skill_level"));
                @SuppressLint("Range") int exp = cursor.getInt(cursor.getColumnIndex("skill_exp"));

                Skill skill = new Skill(id, name, description, level, exp);
                skillsList.add(skill);
            } while (cursor.moveToNext());
            cursor.close();
        }
        return skillsList;
    }
}
