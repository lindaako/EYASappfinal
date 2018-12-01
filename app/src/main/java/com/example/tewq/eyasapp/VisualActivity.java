package com.example.tewq.eyasapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class VisualActivity extends AppCompatActivity {

    Button bGameone,bGametwo,bBack;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_visual);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        bBack=findViewById(R.id.bBack);
        bGameone=findViewById(R.id.bGameone);
        bGametwo=findViewById(R.id.bGametwo);


        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent backIntent = new Intent(VisualActivity.this, TrainingModeActivity2.class);
                VisualActivity.this.startActivity(backIntent);
                finish();
            }
        });

        bGameone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                //Go to first game
                Intent intent1 = new Intent(VisualActivity.this, Visual_1.class);
                startActivity(intent1);
                finish();
            }
        });

        bGametwo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                //Go to second game
                Intent intent1 = new Intent(VisualActivity.this, Visual_2.class);
                startActivity(intent1);
                finish();
            }
        });
    }


}
