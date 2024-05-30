package com.example.helloworld;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GetCard {

    public static void taskDaily(LayoutInflater inflater, LinearLayout container, UserDatabaseHelper dbHelper) {
        container.removeAllViews();
        TaskDatabaseHelper taskDbHelper = dbHelper.getTaskDatabaseHelper();
        ArrayList<Task> taskList = taskDbHelper.getAllTasks();

        for (Task itemText : taskList) {
            // Inflate the item layout
            View itemView = inflater.inflate(R.layout.task_popup_card, container, false);
            // Set the item text
            TextView itemTextView = itemView.findViewById(R.id.task_card_name);
            itemTextView.setText(itemText.getName());
            TextView desc = itemView.findViewById(R.id.task_card_desc);
            desc.setText(itemText.getDescription());
            // Add the item view to the container
            container.addView(itemView);
        }
    }
    public static void taskWeekly(LayoutInflater inflater, LinearLayout container, UserDatabaseHelper dbHelper) {
        container.removeAllViews();
//        TaskDatabaseHelper taskDbHelper = dbHelper.getTaskWeeklyDatabaseHelper();
//        ArrayList<Task> taskList = taskDbHelper.getAllTasks();
//
//        for (Task itemText : taskList) {
//            // Inflate the item layout
//            View itemView = inflater.inflate(R.layout.task_popup_card, container, false);
//            // Set the item text
//            TextView itemTextView = itemView.findViewById(R.id.task_card_name);
//            itemTextView.setText(itemText.getName());
//            TextView desc = itemView.findViewById(R.id.task_card_desc);
//            desc.setText(itemText.getDescription());
//            // Add the item view to the container
//            container.addView(itemView);
//        }
    }
    public static void taskLimited(LayoutInflater inflater, LinearLayout container, UserDatabaseHelper dbHelper) {
        container.removeAllViews();
//        TaskDatabaseHelper taskDbHelper = dbHelper.getTaskLimitedDatabaseHelper();
//        ArrayList<Task> taskList = taskDbHelper.getAllTasks();
//
//        for (Task itemText : taskList) {
//            // Inflate the item layout
//            View itemView = inflater.inflate(R.layout.task_popup_card, container, false);
//            // Set the item text
//            TextView itemTextView = itemView.findViewById(R.id.task_card_name);
//            itemTextView.setText(itemText.getName());
//            TextView desc = itemView.findViewById(R.id.task_card_desc);
//            desc.setText(itemText.getDescription());
//            // Add the item view to the container
//            container.addView(itemView);
//        }
    }
    public static void skill(LayoutInflater inflater, LinearLayout container, UserDatabaseHelper dbHelper) {
        SkillDatabaseHelper skillDbHelper = dbHelper.getSkillDatabaseHelper();
        ArrayList<Skill> skillsList = skillDbHelper.getAllSkills();

        for (Skill itemText : skillsList) {
            // Inflate the item layout
            View itemView = inflater.inflate(R.layout.popup_card, container, false);

            // Set the item text
            TextView itemTextView = itemView.findViewById(R.id.card_name);
            itemTextView.setText(itemText.getName());
            TextView desc = itemView.findViewById(R.id.card_desc);
            desc.setText(itemText.getDescription());

            // Add the item view to the container
            container.addView(itemView);
        }
    }

    public static void personalAchievement(LayoutInflater inflater, LinearLayout container, UserDatabaseHelper dbHelper) {
        SkillDatabaseHelper skillDbHelper = dbHelper.getSkillDatabaseHelper();
        ArrayList<Skill> skillsList = skillDbHelper.getAllSkills();

        for (Skill itemText : skillsList) {
            // Inflate the item layout
            View itemView = inflater.inflate(R.layout.popup_card, container, false);

            // Set the item text
            TextView itemTextView = itemView.findViewById(R.id.card_name);
            itemTextView.setText(itemText.getName());
            TextView desc = itemView.findViewById(R.id.card_desc);
            desc.setText(itemText.getDescription());

            // Add the item view to the container
            container.addView(itemView);
        }
    }
}