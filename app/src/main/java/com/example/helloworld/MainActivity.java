package com.example.helloworld;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity
{
    PopupWindow menupopup;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

    }

    public void skillSet(View view)
    {
        Popup("Skills","Add Skill");
    }
    public void PersonalAchievement(View view)
    {
        Popup("Personal Achievement","Add Achievement");
    }
    public void Popup(String headerTxt, String btnTxt)
    {
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
            }
        });
    }

    public void closePopup(View view)
    {
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
}

