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
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    PopupWindow menupopup;
    public SkillDatabaseHelper dbSkill;

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
    }

    public void skillSet()
    {
        Popup("Skills","Add Skill");
    }
    public void skillSet(View view)
    {
        Popup("Skills","Add Skill");
    }
    public void PersonalAchievement(View view)
    {
        Popup("Personal Achievement","Add Achievement");
    }
    public void Popup(String headerTxt, String btnTxt) {
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupViewer = inflater.inflate(R.layout.profile_menu_popup, null);
        TextView header = popupViewer.findViewById(R.id.profile_menu_popup_header);
        TextView button = popupViewer.findViewById(R.id.btn_add);
        header.setText(headerTxt);
        button.setText(btnTxt);

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
                            case "Personal Achievement":
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
    public void addItemsToPopup(String header) {
        if (menupopup != null && menupopup.isShowing()) {
            // Inflate the item layout
            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
            LinearLayout container = menupopup.getContentView().findViewById(R.id.popup_container);

            dbSkill = new SkillDatabaseHelper(MainActivity.this);

            dbSkill.getAllSkillsAsArrayList();
            ArrayList<Skill> skillsList = dbSkill.getAllSkillsAsArrayList();

            for (Skill itemText : skillsList) {
                // Inflate the item layout
                View itemView = inflater.inflate(R.layout.popup_card, container, false);

                // Set the item text
                TextView itemTextView = itemView.findViewById(R.id.card_name);
                itemTextView.setText(itemText.getName());

                // Add the item view to the container
                container.addView(itemView);
            }
        }
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

                dbSkill = new SkillDatabaseHelper(MainActivity.this);
                add.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String skillName = name.getText().toString();
                        String skillDetail = detail.getText().toString();
                        dbSkill.addSkill(skillName, skillDetail);
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

