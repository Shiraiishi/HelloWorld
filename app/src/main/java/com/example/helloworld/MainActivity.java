package com.example.helloworld;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity
{
    PopupWindow menupopup;
    private UserDatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        dbHelper = new UserDatabaseHelper(this);
        dbHelper.createTasksTableIfNotExists();
        dbHelper.createSkillsTableIfNotExists();
        start();
    }

    public void start(){
            // Inflate the item layout
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout container = findViewById(R.id.task_container_main);
        GetCard.taskDaily(inflater,container,dbHelper);
    }

    public void daily(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout container = findViewById(R.id.task_container_main);
        GetCard.taskDaily(inflater,container,dbHelper);
    }
    public void weekly(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout container = findViewById(R.id.task_container_main);
        GetCard.taskWeekly(inflater,container,dbHelper);
    }
    public void limited(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        LinearLayout container = findViewById(R.id.task_container_main);
        GetCard.taskLimited(inflater,container,dbHelper);
    }

    public void skillSet()
    {
        Popup("Skills","Add Skill");
    }
    public void skillSet(View view)
    {
        Popup("Skills","Add Skill");
    }
    public void personalAchievement()
    {
        Popup("Personal Achievement","Add Achievement");
    }
    public void personalAchievement(View view)
    {
        Popup("Personal Achievement","Add Achievement");
    }
    public void achievement()
    {
        Popup("Achievement"," ");
    }
    public void achievement(View view)
    {
        Popup("Achievement"," ");
    }
    public void collection()
    {
        Popup("Collections"," ");
    }
    public void collection(View view)
    {
        Popup("Collections"," ");
    }

    public void Popup(String headerTxt, String btnTxt) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupViewer = inflater.inflate(R.layout.profile_menu_popup, null);
        TextView header = popupViewer.findViewById(R.id.profile_menu_popup_header);
        TextView button = popupViewer.findViewById(R.id.btn_add);
        header.setText(headerTxt);
        button.setText(btnTxt);
        if(headerTxt =="Collections" ||headerTxt =="Achievement")
        {
            button.setVisibility(View.INVISIBLE);
        }
        View layout = findViewById(R.id.main);

        int width   = ViewGroup.LayoutParams.MATCH_PARENT,
            height  = ViewGroup.LayoutParams.MATCH_PARENT;

        menupopup = new PopupWindow(popupViewer, width, height, true);
        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                menupopup.showAtLocation(layout, Gravity.CENTER, 0, 0);
                addItemsToPopup(headerTxt);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch(headerTxt) {
                            case "Skills":
                                    addSkill();
                                break;
                            case "Achievement":
                                    // placeholder
                                break;
                            case "Personal Achievement":
                                // placeholder
                                break;
                            case "Collection":
                                // placeholder
                                break;
                            default:
                                // code block
                        }
                    }
                });
            }
        });
    }

    public void addItemsToPopup(String headerTxt) {
        if (menupopup != null && menupopup.isShowing()) {
            // Inflate the item layout
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout container = menupopup.getContentView().findViewById(R.id.popup_container);

            switch(headerTxt) {
                case "Skills":
                    GetCard.skill(inflater,container,dbHelper);
                    break;
                case "Achievement":
                    // GetCard.achievement (inflater,container,dbHelper);
                    break;
                case "Personal Achievement":
                    GetCard.personalAchievement(inflater,container,dbHelper);
                    break;
                case "Collection":
                    // GetCard.collection(inflater,container,dbHelper);
                    break;
                default:
                    // code block
            }
        }
    }

    public void addTask(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View addTaskForm = inflater.inflate(R.layout.add_task_form, null);
        View layout = findViewById(R.id.main);

        int width   = ViewGroup.LayoutParams.MATCH_PARENT,
            height  = ViewGroup.LayoutParams.MATCH_PARENT;

        PopupWindow addTaskWindow = new PopupWindow(addTaskForm, width, height, true);

        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                addTaskWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);
                SeekBar  seekBar = addTaskWindow.getContentView().findViewById(R.id.seekBar);
                TextView back = addTaskWindow.getContentView().findViewById(R.id.add_task_form_back),
                         exp = addTaskWindow.getContentView().findViewById(R.id.exp_display);
                Button   add = addTaskWindow.getContentView().findViewById(R.id.add_task_form_btn);
                EditText name = addTaskWindow.getContentView().findViewById(R.id.task_name_input),
                         detail = addTaskWindow.getContentView().findViewById(R.id.task_detail_input);

                seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        exp.setText(progress*10 + " EXP");
                    }

                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {

                    }

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {

                    }
                });

                TaskDatabaseHelper taskDbHelper = dbHelper.getTaskDatabaseHelper();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cardName = name.getText().toString();
                        String cardDetail = detail.getText().toString();
                        taskDbHelper.addTask(cardName, cardDetail);
                        popupDismiss(addTaskWindow);
                        start();
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupDismiss(addTaskWindow);
                        start();
                    }
                });
            }
        });
    }

    public void addSkill(){
        popupDismiss(menupopup);
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View addSkillForm = inflater.inflate(R.layout.add_skill_form, null);
        View layout = findViewById(R.id.main);

        int width   = ViewGroup.LayoutParams.MATCH_PARENT,
            height  = ViewGroup.LayoutParams.MATCH_PARENT;

        PopupWindow addSkillWindow = new PopupWindow(addSkillForm, width, height, true);

        runOnUiThread(new Runnable()
        {
            @Override
            public void run()
            {
                addSkillWindow.showAtLocation(layout, Gravity.CENTER, 0, 0);

                TextView back = addSkillWindow.getContentView().findViewById(R.id.add_skill_form_back);
                Button add = addSkillWindow.getContentView().findViewById(R.id.add_skill_form_btn);
                EditText name = addSkillWindow.getContentView().findViewById(R.id.skill_name_input),
                         detail = addSkillWindow.getContentView().findViewById(R.id.skill_detail_input);

                SkillDatabaseHelper skillDbHelper = dbHelper.getSkillDatabaseHelper();
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String cardName = name.getText().toString();
                        String cardDetail = detail.getText().toString();
                        skillDbHelper.addSkill(cardName, cardDetail);
                        popupDismiss(addSkillWindow);
                        skillSet();
                    }
                });
                back.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupDismiss(addSkillWindow);
                        skillSet();
                    }
                });
            }
        });
    }
    public void closePopup(View view) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Dismiss the popup on the main UI thread
                if (menupopup != null && menupopup.isShowing()) {
                    menupopup.dismiss();
                }
            }
        });
    }
    public void popupDismiss(PopupWindow popup) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // Dismiss the popup on the main UI thread
                if (popup != null && popup.isShowing()) {
                    popup.dismiss();
                }
            }
        });
    }
}