package com.example.tewq.eyasapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class Visual_2 extends AppCompatActivity {
    ImageButton button[];
    ImageButton next;
    ImageView back1, back2;
    ImageView image[];
    ImageView image2[];
    ImageView background;
    ImageView explain2;
    ImageView audio_result_bg, audio_result_level, audio_result_line, audio_result_line2, audio_result_score, audio_result_time;
    ImageButton button1, button2, audio_result_restart;
    TextView audio_result_level_txt, audio_result_score_txt, audio_result_time_txt;
    final LoginActivity.Singleton V_leveldata=LoginActivity.Singleton.getInstance();
    final LoginActivity.Singleton V_accuracydata=LoginActivity.Singleton.getInstance();
    final LoginActivity.Singleton V_reactiontimedata=LoginActivity.Singleton.getInstance();
    final LoginActivity.Singleton training = LoginActivity.Singleton.getInstance();
    final int data=training.getData();

    double responseTime = 0;
    long currentTime = 0;
    int icon_number;
    int count_blue = 0, count_yellow = 0, count_green = 0, count_red = 0, count_bora = 0;
    int count_num = 0; // 최대 클릭 수
    int rand_num; // 랜덤
    int point = 100;
    int level;
    int find_level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_visual_2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        Intent intent = getIntent(); // 데이터 받을 때 쓸거
        level = intent.getIntExtra("level", 5);
        point = intent.getIntExtra("Score",5);


        if(point > 90 && point <=100){
            find_level = 5;
        }
        else if(point > 80 && point <=90){
            find_level = 4;
        }
        else if(point >70&& point <=80){
            find_level = 3;
        }
        else if(point >60 && point <=70){
            find_level = 2;
        }
        else if(point >50&& point <=60){
            find_level = 1;
        }

        level = find_level;

        button = new ImageButton[6];

        button[0] = (ImageButton) findViewById(R.id.image_buuton_0);
        button[1] = (ImageButton) findViewById(R.id.image_buuton_1);
        button[2] = (ImageButton) findViewById(R.id.image_buuton_2);
        button[3] = (ImageButton) findViewById(R.id.image_buuton_3);
        button[4] = (ImageButton) findViewById(R.id.image_buuton_4);
        button[5] = (ImageButton) findViewById(R.id.image_buuton_5);
        next = (ImageButton) findViewById(R.id.next2);

        image = new ImageView[5];

        image[0] = (ImageView) findViewById(R.id.icon_blue_1);
        image[1] = (ImageView) findViewById(R.id.icon_red_1);
        image[2] = (ImageView) findViewById(R.id.icon_green_1);
        image[3] = (ImageView) findViewById(R.id.icon_bora_1);
        image[4] = (ImageView) findViewById(R.id.icon_yellow_1);

        explain2 = (ImageView) findViewById(R.id.explain2);
        background = (ImageView) findViewById(R.id.back);

        image2 = new ImageView[5];

        image2[0] = (ImageView) findViewById(R.id.icon_blue_5);
        image2[1] = (ImageView) findViewById(R.id.icon_red_5);
        image2[2] = (ImageView) findViewById(R.id.icon_green_5);
        image2[3] = (ImageView) findViewById(R.id.icon_bora_5);
        image2[4] = (ImageView) findViewById(R.id.icon_yellow_5);

        back1 = (ImageView) findViewById(R.id.first_back);
        back2 = (ImageView) findViewById(R.id.final_back);


        back1.setImageResource(R.drawable.my_back2_first); // 처음
        back2.setImageResource(R.drawable.my_back_final); // 나중


        audio_result_bg = (ImageView)findViewById(R.id.audio_result_bg);
        audio_result_level = (ImageView)findViewById(R.id.audio_result_level);
        audio_result_line = (ImageView)findViewById(R.id.audio_result_line);
        audio_result_line2 = (ImageView)findViewById(R.id.audio_result_line2);
        audio_result_score = (ImageView)findViewById(R.id.audio_result_score);
        audio_result_time = (ImageView)findViewById(R.id.audio_result_time);

        audio_result_restart = (ImageButton)findViewById(R.id.audio_result_restart);

        audio_result_level_txt = (TextView) findViewById(R.id.audio_result_level_txt);
        audio_result_score_txt = (TextView) findViewById(R.id.audio_result_score_txt);
        audio_result_time_txt = (TextView) findViewById(R.id.audio_result_time_txt);
        //  Handler delay = new Handler();

        explain2.setVisibility(View.VISIBLE);
        next.setVisibility(View.VISIBLE);

        // back1.setVisibility(View.INVISIBLE);
        back2.setVisibility(View.INVISIBLE);


    }

    public void play_game_2() { // 게임


        Random random1 = new Random();
        // int count = random1.nextInt(500);
        int cr;
        //for(int i=0; i<count; i++) {
        Random random = new Random();
        cr = random.nextInt(5);
        switch (cr) {
            case 0:

                image[0].setImageResource(R.drawable.icon_blue_1);
                Handler delayHandler = new Handler();
                image[0].bringToFront();
                image[0].setVisibility(View.VISIBLE);
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image[0].setVisibility(View.INVISIBLE);
                        //   image[0].setImageResource(R.drawable.icon_blue_1);
                        count_blue++;
                    }
                }, 4000-(find_level*500));
                break;
            case 1:

                image[1].setImageResource(R.drawable.icon_red_1);
                image[1].bringToFront();
                Handler delayHandler1 = new Handler();
                image[1].setVisibility(View.VISIBLE);
                delayHandler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image[1].setVisibility(View.INVISIBLE);
                        //image[1].setImageResource(R.drawable.icon_red_1);
                        count_red++;
                    }
                }, 4000-(find_level*500));
                break;
            case 2:

                image[2].setImageResource(R.drawable.icon_green_1);
                image[2].bringToFront();
                Handler delayHandler2 = new Handler();
                image[2].setVisibility(View.VISIBLE);
                delayHandler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image[2].setVisibility(View.INVISIBLE);
                        // image[2].setImageResource(R.drawable.icon_green_1);
                        count_green++;
                    }
                }, 4000-(find_level*500));
                break;
            case 3:

                image[3].setImageResource(R.drawable.icon_bora_1);
                image[3].bringToFront();
                Handler delayHandler3 = new Handler();
                image[3].setVisibility(View.VISIBLE);
                delayHandler3.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image[3].setVisibility(View.INVISIBLE);
                        // image[3].setImageResource(R.drawable.icon_bora_1);
                        count_bora++;
                    }
                }, 4000-(find_level*500));
                break;
            case 4:

                image[4].setImageResource(R.drawable.icon_yellow_1);
                Handler delayHandler4 = new Handler();
                image[4].bringToFront();
                image[4].setVisibility(View.VISIBLE);
                delayHandler4.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image[4].setVisibility(View.INVISIBLE);
                        // image[4].setImageResource(R.drawable.icon_yellow_1);
                        count_yellow++;
                    }
                }, 4000-(find_level*500));
                break;
        }

        // }

    }


    public void Corrent_color(int cr) { // 옷 색

        back1.setVisibility(View.VISIBLE);
        Handler delayHandler = new Handler();
        switch (cr) {

            case 0:
                icon_number = 0;
                image2[0].setVisibility(View.VISIBLE);
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image2[0].setVisibility(View.INVISIBLE);
                        back1.setVisibility(View.INVISIBLE);
                    }
                }, 4000);
                break;
            case 1:
                icon_number = 1;
                image2[1].setVisibility(View.VISIBLE);
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image2[1].setVisibility(View.INVISIBLE);
                        back1.setVisibility(View.INVISIBLE);
                    }
                }, 4000);
                break;
            case 2:
                icon_number = 2;
                image2[2].setVisibility(View.VISIBLE);
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image2[2].setVisibility(View.INVISIBLE);
                        back1.setVisibility(View.INVISIBLE);
                    }
                }, 4000);
                break;
            case 3:
                icon_number = 3;
                image2[3].setVisibility(View.VISIBLE);
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image2[3].setVisibility(View.INVISIBLE);
                        back1.setVisibility(View.INVISIBLE);
                    }
                }, 4000);
                break;
            case 4:
                icon_number = 4;
                image2[4].setVisibility(View.VISIBLE);
                delayHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        image2[4].setVisibility(View.INVISIBLE);
                        back1.setVisibility(View.INVISIBLE);
                    }
                }, 4000);
                break;
        }

    }

    public void onClickButton(View v) {

        switch (v.getId()) {
            case R.id.next2:
                Random random = new Random();
                rand_num = random.nextInt(5);

                explain2.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                Corrent_color(rand_num);
                Handler gameHandler2 = new Handler();
                gameHandler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        background.setVisibility(View.VISIBLE);
                    }
                }, 4000);

                Handler gameHandler = new Handler();
                gameHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Handler gameHandler1 = new Handler();

                        for (int i = 1000; i <= 3000; i += 500) {
                            gameHandler1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (icon_number == 2) {
                                        count_green++;
                                    }
                                    currentTime = System.currentTimeMillis();
                                    play_game_2();

                                    if (count_num == 4) {
                                        background.setVisibility(View.INVISIBLE);
                                        choice();
                                    }
                                    count_num++;
                                }

                            }, i);


                        }
                    }

                }, 4000);

        }
    }

    public void onClickButton2(View v) {

        responseTime += ( System.currentTimeMillis()-currentTime);
        switch (v.getId()) {
            case R.id.image_buuton_0:
                responseTime/=10000.0;
                responseTime = Math.round(responseTime*10)/10.0;
                if (icon_number == 0) {
                    if (count_blue != 0)
                        point -= 10;

                    if (count_blue == 0) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 1) {
                    if (count_red != 0)
                        point -= 10;

                    if (count_red == 0) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 2) {
                    if (count_green != 0)
                        point -= 10;

                    if (count_green == 0) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 3) {
                    if (count_bora != 0)
                        point -= 10;

                    if (count_bora == 0) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 4) {
                    if (count_yellow != 0)
                        point -= 10;

                    if (count_yellow == 0) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                }
                break;
            case R.id.image_buuton_1:
                responseTime/=10000.0;
                responseTime = Math.round(responseTime*10)/10.0;
                if (icon_number == 0) {
                    if (count_blue != 1)
                        point -= 10;

                    if (count_blue == 1) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 1) {
                    if (count_red != -1 && count_red != 1)
                        point -= 10;

                    if (count_red == 1) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 2) {
                    if (count_green != -1 && count_green != 1)
                        point -= 10;

                    if (count_green == 1) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 3) {
                    if (count_bora != -1 && count_bora != 1)
                        point -= 10;

                    if (count_bora == 1) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 4) {
                    if (count_yellow != -1 && count_yellow != 1)
                        point -= 10;

                    if (count_yellow == 1) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                }
                break;
            case R.id.image_buuton_2:
                responseTime/=10000.0;
                responseTime = Math.round(responseTime*10)/10.0;
                if (icon_number == 0) {
                    if (count_blue != -1 && count_blue != 2)
                        point -= 10;

                    if (count_blue == 2) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 1) {
                    if (count_red != -1 && count_red != 2)
                        point -= 10;

                    if (count_red == 2) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 2) {
                    if (count_green != -1 && count_green != 2)
                        point -= 10;

                    if (count_green == 2) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 3) {
                    if (count_bora != -1 && count_bora != 2)
                        point -= 10;

                    if (count_bora == 2) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 4) {
                    if (count_yellow != -1 && count_yellow != 2)
                        point -= 10;

                    if (count_yellow == 2) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                }
                break;
            case R.id.image_buuton_3:
                responseTime/=10000.0;
                responseTime = Math.round(responseTime*10)/10.0;
                if (icon_number == 0) {
                    if (count_blue != -1 && count_blue != 3)
                        point -= 10;

                    if (count_blue == 3) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 1) {
                    if (count_red != -1 && count_red != 3)
                        point -= 10;

                    if (count_red == 3) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 2) {
                    if (count_green != -1 && count_green != 3)
                        point -= 10;

                    if (count_green == 3) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 3) {
                    if (count_bora != -1 && count_bora != 3)
                        point -= 10;

                    if (count_bora == 3) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 4) {
                    if (count_yellow != -1 && count_yellow != 3)
                        point -= 10;

                    if (count_yellow == 3) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                }
                break;
            case R.id.image_buuton_4:
                responseTime/=10000.0;
                responseTime = Math.round(responseTime*10)/10.0;
                if (icon_number == 0) {
                    if (count_blue != -1 && count_blue != 4)
                        point -= 10;

                    if (count_blue == 4) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 1) {
                    if (count_red != -1 && count_red != 4)
                        point -= 10;

                    if (count_red == 4) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);

                        result_display();
                    }
                } else if (icon_number == 2) {
                    if (count_green != -1 && count_green != 4)
                        point -= 10;

                    if (count_green == 4) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 3) {
                    if (count_bora != -1 && count_bora != 4)
                        point -= 10;

                    if (count_bora == 4) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 4) {
                    if (count_yellow != -1 && count_yellow != 4)
                        point -= 10;

                    if (count_yellow == 4) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                }
                break;
            case R.id.image_buuton_5:
                responseTime/=10000.0;
                responseTime = Math.round(responseTime*10)/10.0;
                if (icon_number == 0) {
                    if (count_blue != -1 && count_blue != 5)
                        point -= 10;

                    if (count_blue == 5) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 1) {
                    if (count_red != -1 && count_red != 5)
                        point -= 10;

                    if (count_red == 5) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 2) {
                    if (count_green != -1 && count_green != 5)
                        point -= 10;

                    if (count_green == 5) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 3) {
                    if (count_bora != -1 && count_bora != 5)
                        point -= 10;

                    if (count_bora == 5) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                } else if (icon_number == 4) {
                    if (count_yellow != -1 && count_yellow != 5)
                        point -= 10;

                    if (count_yellow == 5) {
                        Intent temp = new Intent(this, VisualActivity.class);
                        temp.putExtra("Score", point);
                        temp.putExtra("ResponseTime", responseTime);
                        temp.putExtra("Level", level);
                        result_display();
                    }
                }
                break;

        }
        if(point <0) point = 0;
        currentTime = System.currentTimeMillis();
    }
    public void choice() {

        back2.setVisibility(View.VISIBLE);
        for (int i = 0; i < 6; i++) {
            button[i].setVisibility(View.VISIBLE);
        }


    }

    public void result_display(){

        back2.setVisibility(View.INVISIBLE);
        for (int i = 0; i < 6; i++) {
            button[i].setVisibility(View.INVISIBLE);
        }

        audio_result_bg.setVisibility(View.VISIBLE);
        audio_result_level.setVisibility(View.VISIBLE);
        audio_result_line.setVisibility(View.VISIBLE);
        audio_result_score.setVisibility(View.VISIBLE);

        audio_result_restart.setVisibility(View.VISIBLE);

        audio_result_restart.setVisibility(View.VISIBLE);
        audio_result_level_txt.setVisibility(View.VISIBLE);
        audio_result_score_txt.setVisibility(View.VISIBLE);
        audio_result_line2.setVisibility(View.VISIBLE);
        audio_result_time.setVisibility(View.VISIBLE);
        audio_result_time_txt.setVisibility(View.VISIBLE);
        audio_result_level_txt.setText(""+ find_level + "단계");
        audio_result_score_txt.setText(""+ point + " %");
        audio_result_time_txt.setText(""+ responseTime  + "초");


        String trainingno = String.valueOf(data);
        if ((trainingno.equals("0")))
        {
            //assessement mode
            V_leveldata.setDataVLev(find_level);
            V_accuracydata.setDataVAcc(point);
            V_reactiontimedata.setDataVRt(responseTime);
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


    public void onClickButton3(View v) {

        //if(point >60)
        switch (v.getId()) {
            case R.id.audio_result_restart:
                Intent temp = new Intent(this, VisualActivity.class);
                startActivity(temp);
                finish();
                break;

        }
    }

}