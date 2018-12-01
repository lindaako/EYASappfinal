package com.example.tewq.eyasapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.Image;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;

public class Information extends AppCompatActivity  {

    final LoginActivity.Singleton training = LoginActivity.Singleton.getInstance();
    final int data=training.getData();
    final LoginActivity.Singleton M_leveldata=LoginActivity.Singleton.getInstance();
    final LoginActivity.Singleton M_accuracydata=LoginActivity.Singleton.getInstance();
    final LoginActivity.Singleton M_reactiontimedata=LoginActivity.Singleton.getInstance();
    int score;
    Button bBack;
    ImageView bg1, information_result_bg, information_result_level, information_result_line, information_result_line2, information_result_score, information_result_time;
    ImageButton button1, button2, information_result_restart, information_result_back;
    TextView information_result_level_txt, information_result_score_txt, information_result_time_txt;
    int selected_game = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_information);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        bBack = findViewById(R.id.bBack);
        bg1 = (ImageView)findViewById(R.id.information_select_bg1);
        button1 = (ImageButton)findViewById(R.id.information_select_button1);
        button2 = (ImageButton)findViewById(R.id.information_select_button2);

        information_result_bg = (ImageView)findViewById(R.id.information_result_bg);
        information_result_level = (ImageView)findViewById(R.id.information_result_level);
        information_result_line = (ImageView)findViewById(R.id.information_result_line);
        information_result_line2 = (ImageView)findViewById(R.id.information_result_line2);
        information_result_score = (ImageView)findViewById(R.id.information_result_score);
        information_result_time = (ImageView)findViewById(R.id.information_result_time);

        information_result_restart = (ImageButton)findViewById(R.id.information_result_restart);
        information_result_back = (ImageButton)findViewById(R.id.information_result_back);

        information_result_level_txt = (TextView) findViewById(R.id.information_result_level_txt);
        information_result_score_txt = (TextView) findViewById(R.id.information_result_score_txt);
        information_result_time_txt = (TextView) findViewById(R.id.information_result_time_txt);
        //getSupportActionBar().hide();

        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent backIntent = new Intent(Information.this, TrainingModeActivity2.class);
                Information.this.startActivity(backIntent);
            }
        });
    }


    public void onClickButton(View v)
    {
        switch (v.getId()) {
            case R.id.information_select_button1:
                selected_game = 1;
                Intent intent1 = new Intent(Information.this, Information_1.class);
                intent1.putExtra("level",1);
                startActivityForResult(intent1, score);
                break;
            case R.id.information_select_button2:
                selected_game = 2;
                Intent intent2 = new Intent(Information.this, Information_2.class);
                intent2.putExtra("level",1);
                startActivityForResult(intent2, score);
                break;
            case R.id.information_result_restart:
                bg1.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

                information_result_bg.setVisibility(View.INVISIBLE);
                information_result_level.setVisibility(View.INVISIBLE);
                information_result_line.setVisibility(View.INVISIBLE);
                information_result_score.setVisibility(View.INVISIBLE);

                information_result_restart.setVisibility(View.INVISIBLE);
                information_result_back.setVisibility(View.INVISIBLE);

                information_result_level_txt.setVisibility(View.INVISIBLE);
                information_result_score_txt.setVisibility(View.INVISIBLE);

                information_result_line2.setVisibility(View.INVISIBLE);
                information_result_time.setVisibility(View.INVISIBLE);
                information_result_time_txt.setVisibility(View.INVISIBLE);

                if (selected_game == 1)
                {
                    Intent intent3 = new Intent(Information.this, Information_1.class);
                    intent3.putExtra("level",1);
                    startActivityForResult(intent3, score);
                }
                else if (selected_game == 2)
                {
                    Intent intent4 = new Intent(Information.this, Information_2.class);
                    intent4.putExtra("level",1);
                    startActivityForResult(intent4, score);
                }
                break;
            case R.id.information_result_back:
                bg1.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

                information_result_bg.setVisibility(View.INVISIBLE);
                information_result_level.setVisibility(View.INVISIBLE);
                information_result_line.setVisibility(View.INVISIBLE);
                information_result_score.setVisibility(View.INVISIBLE);

                information_result_restart.setVisibility(View.INVISIBLE);
                information_result_back.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);

                information_result_level_txt.setVisibility(View.INVISIBLE);
                information_result_score_txt.setVisibility(View.INVISIBLE);
                information_result_line2.setVisibility(View.INVISIBLE);
                information_result_time.setVisibility(View.INVISIBLE);
                information_result_time_txt.setVisibility(View.INVISIBLE);
                break;
        }
    }

    @Override
    protected void onActivityResult (int requestCode, int resultCode, Intent data)
    {
        if (resultCode == RESULT_OK)
        {
            int score = data.getIntExtra("Score", 0);
            double responseTime = data.getDoubleExtra("ResponseTime", 0);
            int level = data.getIntExtra("Level", 1);
            bg1.setVisibility(View.INVISIBLE);
            button1.setVisibility(View.INVISIBLE);
            button2.setVisibility(View.INVISIBLE);

            information_result_bg.setVisibility(View.VISIBLE);
            information_result_level.setVisibility(View.VISIBLE);
            information_result_line.setVisibility(View.VISIBLE);
            information_result_score.setVisibility(View.VISIBLE);

            information_result_restart.setVisibility(View.VISIBLE);
            information_result_back.setVisibility(View.VISIBLE);
            bBack.setVisibility(View.INVISIBLE);

            information_result_level_txt.setVisibility(View.VISIBLE);
            information_result_score_txt.setVisibility(View.VISIBLE);
            information_result_line2.setVisibility(View.VISIBLE);
            information_result_time.setVisibility(View.VISIBLE);
            information_result_time_txt.setVisibility(View.VISIBLE);
            information_result_level_txt.setText(level+" 단계");
            information_result_score_txt.setText(score+" %");
            information_result_time_txt.setText(responseTime+" 초");


            String trainingno = String.valueOf(data);
            if ((trainingno.equals("0")))
            {
                //assessement mode
                M_leveldata.setDataMLev(level);
                M_accuracydata.setDataMAcc(score);
                M_reactiontimedata.setDataMRt(responseTime);
            }
            else if ((trainingno.equals("1")))
            {
                //training mode
                //Do nothing
            }
            else
            {

            }


        }
    }
}
