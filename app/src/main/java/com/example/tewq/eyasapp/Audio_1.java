package com.example.tewq.eyasapp;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.drawable.AnimationDrawable;
import android.media.MediaPlayer;
import android.os.Handler;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class Audio_1 extends AppCompatActivity {
    ImageView gamebg; TextView extxt;  ImageButton exnext;
    ImageButton exup; ImageButton exdown; ImageButton exstop;  ImageButton exnext2;
    ImageView startfloorbg; TextView startfloortxt;
    ImageView gameanim;
    ImageButton[] ansbtn; TextView[] anstxt;
    ImageView viewox;
    AnimationDrawable animdrawble;
    Timer timer;
    int CurFloor;
    int[] answers;
    MediaPlayer elavator_sound;
    MediaPlayer effect_sound;
    int level;
    int score = 0;
    int gameCount = 5;
    long pressedTime = 0;
    long currentTime = 0;
    double responseTime = 0;
    Random random = new Random();
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_audio_1);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 1);
        handler = new Handler();


        gamebg = (ImageView)findViewById(R.id.elevator_game_background);
        extxt   = (TextView)findViewById(R.id.elevator_explain_text);
        exnext = (ImageButton)findViewById(R.id.elevator_explain_nextbutton);

        exup = (ImageButton)findViewById(R.id.elevator_explain2_up);
        exdown = (ImageButton)findViewById(R.id.elevator_explain2_down);
        exstop = (ImageButton)findViewById(R.id.elevator_explain2_stop);
        exnext2 = (ImageButton)findViewById(R.id.elevator_explain_nextbutton2);

        startfloorbg = (ImageView)findViewById(R.id.elevator_game_startfloor_background);
        startfloortxt = (TextView)findViewById(R.id.elevator_game_startfloor_text);

        gameanim = (ImageView)findViewById(R.id.elevator_game_anim);

        ansbtn = new ImageButton[6]; anstxt = new TextView[6];
        ansbtn[0] = (ImageButton)findViewById(R.id.elevator_game_answer_1);
        anstxt[0] = (TextView)findViewById(R.id.elevator_game_answer_1_text);
        ansbtn[1] = (ImageButton)findViewById(R.id.elevator_game_answer_2);
        anstxt[1] = (TextView)findViewById(R.id.elevator_game_answer_2_text);
        ansbtn[2] = (ImageButton)findViewById(R.id.elevator_game_answer_3);
        anstxt[2] = (TextView)findViewById(R.id.elevator_game_answer_3_text);
        ansbtn[3] = (ImageButton)findViewById(R.id.elevator_game_answer_4);
        anstxt[3] = (TextView)findViewById(R.id.elevator_game_answer_4_text);
        ansbtn[4] = (ImageButton)findViewById(R.id.elevator_game_answer_5);
        anstxt[4] = (TextView)findViewById(R.id.elevator_game_answer_5_text);
        ansbtn[5] = (ImageButton)findViewById(R.id.elevator_game_answer_6);
        anstxt[5] = (TextView)findViewById(R.id.elevator_game_answer_6_text);

        viewox = (ImageView)findViewById(R.id.elevator_ox) ;

        elavator_sound = MediaPlayer.create(this, R.raw.elevator_up);
        effect_sound = MediaPlayer.create(Audio_1.this, R.raw.o);


        gamebg.setVisibility(View.VISIBLE);
        extxt.setVisibility(View.VISIBLE);
        exnext.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        if ( pressedTime == 0 ) {
            Toast.makeText(Audio_1.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 2000 ) {
                Toast.makeText(Audio_1.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
                pressedTime = 0 ;
            }
            else {
                handler.removeCallbacksAndMessages(null);
                elavator_sound.stop();
                elavator_sound.release();
                effect_sound.stop();
                effect_sound.release();
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }

    public void onClickButton(View v)
    {
        switch (v.getId()) {
            case R.id.elevator_explain_nextbutton:
                gamebg.setImageResource(R.drawable.wharfloor_bg);
                extxt.setVisibility(View.INVISIBLE);
                exnext.setVisibility(View.INVISIBLE);

                exup.setVisibility(View.VISIBLE);
                exdown.setVisibility(View.VISIBLE);
                exstop.setVisibility(View.VISIBLE);
                exnext2.setVisibility(View.VISIBLE);

                break;

            case R.id.elevator_explain2_up:
                exup.setImageResource(R.drawable.up0002);
                exdown.setImageResource(R.drawable.up0001);
                exstop.setImageResource(R.drawable.stop0001);
                elavator_sound.stop();
                elavator_sound.release();
                elavator_sound = MediaPlayer.create(this, R.raw.elevator_up);
                elavator_sound.start();
                break;

            case R.id.elevator_explain2_down:
                exdown.setImageResource(R.drawable.up0002);
                exup.setImageResource(R.drawable.up0001);
                exstop.setImageResource(R.drawable.stop0001);

                elavator_sound.stop();
                elavator_sound.release();
                elavator_sound = MediaPlayer.create(this, R.raw.elevator_down);
                elavator_sound.start();
                break;

            case R.id.elevator_explain2_stop:
                exup.setImageResource(R.drawable.up0001);
                exdown.setImageResource(R.drawable.up0001);
                exstop.setImageResource(R.drawable.stop0002);
                elavator_sound.stop();
                elavator_sound.release();
                elavator_sound = MediaPlayer.create(this, R.raw.elevator_stop);
                elavator_sound.start();
                break;

            case R.id.elevator_explain_nextbutton2:
                exup.setVisibility(View.INVISIBLE);
                exdown.setVisibility(View.INVISIBLE);
                exstop.setVisibility(View.INVISIBLE);
                exnext2.setVisibility(View.INVISIBLE);

                gameMainF(true);
                break;

            case R.id.elevator_game_answer_1:
                if (answers[0] == CurFloor)
                {
                    correctF(true);
                }
                else
                {
                    falseF(true);
                }
                break;

            case R.id.elevator_game_answer_2:
                if (answers[1] == CurFloor)
                {
                    correctF(true);
                }
                else
                {
                    falseF(true);
                }
                break;

            case R.id.elevator_game_answer_3:
                if (answers[2] == CurFloor)
                {
                    correctF(true);
                }
                else
                {
                    falseF(true);
                }
                break;

            case R.id.elevator_game_answer_4:
                if (answers[3] == CurFloor)
                {
                    correctF(true);
                }
                else
                {
                    falseF(true);
                }
                break;

            case R.id.elevator_game_answer_5:
                if (answers[4] == CurFloor)
                {
                    correctF(true);
                }
                else
                {
                    falseF(true);
                }
                break;

            case R.id.elevator_game_answer_6:
                if (answers[5] == CurFloor)
                {
                    correctF(true);
                }
                else
                {
                    falseF(true);
                }
                break;
        }
    }


    public void gameMainF(boolean gamebool)
    {
        int CurFloor = 0;
        boolean startFloorOn = true;
        long startTime;
        int i;
        int elevatorTime = 12500;

        switch (level)
        {
            case 1:
                elevatorTime = 7500;
                break;
            case 2:
                elevatorTime = 6750;
                break;
            case 3:
                elevatorTime = 11250;
                break;
            case 4:
                elevatorTime = 10000;
                break;
            case 5:
                elevatorTime = 14000;
                break;
        }

        gamestartfloorF(true);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameBeartoelevator(true);
            }
        }, 3000);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameMoveElevator(true);
            }
        }, 5500);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
               gameAnswer(true);
            }
        }, 5500+elevatorTime);

    }

    private void gamestartfloorF(boolean bool) {


        CurFloor = random.nextInt(9) + 1;
        gamebg.setImageResource(R.drawable.wharfloor_curver);
        startfloortxt.setVisibility(View.VISIBLE);
        startfloortxt.setText(Integer.toString(CurFloor));

        Animation startFloorAnim = new AlphaAnimation(0.0f, 2.0f);
        startFloorAnim.setDuration(300); //You can manage the time of the blink with this parameter
        startFloorAnim.setStartOffset(0);
        startFloorAnim.setRepeatMode(Animation.REVERSE);
        startFloorAnim.setRepeatCount(10);
        startfloortxt.startAnimation(startFloorAnim);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startfloortxt.clearAnimation();
                startfloortxt.setVisibility(View.INVISIBLE);
            }
        }, 3000);
    }

    private void gameBeartoelevator(boolean bool) {

        gamebg.setImageResource(R.drawable.wharfloor_bg_1);
        gameanim.setImageResource(R.drawable.bear_anim);
        gameanim.setVisibility(View.VISIBLE);
        animdrawble = (AnimationDrawable) gameanim.getDrawable();
        animdrawble.start();

        Animation scaleanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scaletosmall);
        gameanim.startAnimation(scaleanim);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animdrawble.stop();
                gameanim.clearAnimation();
                gameanim.setVisibility(View.INVISIBLE);
            }
        },2000);
    }

    private void gameMoveElevator(boolean bool)
    {
        boolean goup;
        int elevatorDelay = 2500;
        int elevatorCount = 3;
        if (level == 1 )
            elevatorDelay = 2500;
        else if (level >=2 && level <=3)
            elevatorDelay = 2250;
        else if (level >=4 && level <=5)
            elevatorDelay = 2000;

        if (level >= 1 && level <= 2)
            elevatorCount = 3;
        else if (level >= 3 && level <= 4)
            elevatorCount = 5;
        else if (level == 5 )
            elevatorCount = 7;

        for (int i = 0; i < elevatorCount; i++)
        {
            goup = random.nextBoolean();
            if (goup)
            {
                if (CurFloor == 9)
                {
                    --i;
                }
                else
                {
                    CurFloor += 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            elavator_sound.stop();
                            elavator_sound.release();
                            elavator_sound = MediaPlayer.create(Audio_1.this, R.raw.elevator_up);
                            elavator_sound.start();
                        }
                    }, i * elevatorDelay);
                }
            }
            else
            {
                if (CurFloor == 1)
                {
                    --i;
                }
                else {
                    CurFloor -= 1;
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            elavator_sound.stop();
                            elavator_sound.release();
                            elavator_sound = MediaPlayer.create(Audio_1.this, R.raw.elevator_down);
                            elavator_sound.start();
                        }
                    }, i * elevatorDelay);
                }
            }
        }
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                elavator_sound.stop();
                elavator_sound.release();
                elavator_sound = MediaPlayer.create(Audio_1.this, R.raw.elevator_stop);
                elavator_sound.start();
            }
        }, elevatorDelay*elevatorCount);
    }

    private  void gameAnswer(boolean bool)
    {
        answers = new int[6];

        int[] answers_ = new int[6];
        answers_[0] = CurFloor;
        for (int i = 1; i < 6; i++)
        {
            answers_[i] = random.nextInt(9) + 1;
            while(true)
            {
                boolean isthere = false;
                for (int j = 0;j<i;j++)
                {
                    if (answers_[i] == answers_[j])
                        isthere = true;
                }
                if (isthere)
                    answers_[i] = random.nextInt(9) + 1;
                else
                    break;
            }
        }

        for (int i = 0; i < 6; i++)
        {
            answers[i] = random.nextInt(6);
            while(true)
            {
                boolean isthere = false;
                for (int j = 0;j<i;j++)
                {
                    if (answers[i] == answers[j])
                        isthere = true;
                }
                if (isthere)
                    answers[i] = random.nextInt(6);
                else
                    break;
            }
        }

        for (int i = 0; i < 6; i++)
            answers[i] = answers_[answers[i]];

        gameanim.setVisibility(View.VISIBLE);
        animdrawble = (AnimationDrawable) gameanim.getDrawable();
        animdrawble.start();

        Animation scaleanim = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.scaletolarge);
        gameanim.startAnimation(scaleanim);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                animdrawble.stop();
                gameanim.clearAnimation();
                gameanim.setVisibility(View.INVISIBLE);
                gamebg.setImageResource(R.drawable.wharfloor_bg);
                for(int i = 0; i < 6; i++)
                {
                    ansbtn[i].setVisibility(View.VISIBLE);
                    anstxt[i].setText(Integer.toString(answers[i]));
                    anstxt[i].setVisibility(View.VISIBLE);
                }
                currentTime = System.currentTimeMillis();
            }
        },2000);

    }

    private void correctF(boolean bool)
    {
        responseTime += ( System.currentTimeMillis()-currentTime);
        for(int i = 0; i < 6; i++)
        {
            ansbtn[i].setVisibility(View.INVISIBLE);
            anstxt[i].setVisibility(View.INVISIBLE);
        }
        score += 20;
        viewox.setImageResource(R.drawable.audio_2_correct);
        viewox.setVisibility(View.VISIBLE);
        effect_sound.stop();
        effect_sound.release();
        effect_sound = MediaPlayer.create(Audio_1.this, R.raw.o);
        effect_sound.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewox.setVisibility(View.INVISIBLE);
                gameCount--;
                if (gameCount == 0)
                {
                    elavator_sound.stop();
                    elavator_sound.release();
                    effect_sound.stop();
                    effect_sound.release();
                    responseTime/=5000.0;
                    responseTime = Math.round(responseTime*10)/10.0;
                    Intent temp = new Intent();
                    temp.putExtra("Level", level);
                    temp.putExtra("Score", score);
                    temp.putExtra("ResponseTime", responseTime);
                    setResult(RESULT_OK, temp);
                    finish();
                }
                else
                {
                    viewox.setVisibility(View.INVISIBLE);
                    gameMainF(true);
                }
            }
        },500);
    }

    private void falseF(boolean bool)
    {
        responseTime += ( System.currentTimeMillis()-currentTime);
        for(int i = 0; i < 6; i++)
        {
            ansbtn[i].setVisibility(View.INVISIBLE);
            anstxt[i].setVisibility(View.INVISIBLE);
        }
        viewox.setImageResource(R.drawable.audio_2_incorrect);
        viewox.setVisibility(View.VISIBLE);
        effect_sound.stop();
        effect_sound.release();
        effect_sound = MediaPlayer.create(Audio_1.this, R.raw.x);
        effect_sound.start();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                viewox.setVisibility(View.INVISIBLE);
                gameCount--;
                if (gameCount == 0)
                {
                    elavator_sound.stop();
                    elavator_sound.release();
                    effect_sound.stop();
                    effect_sound.release();
                    responseTime/=5000.0;
                    responseTime = Math.round(responseTime*10)/10.0;
                    Intent temp = new Intent();
                    temp.putExtra("Level", level);
                    temp.putExtra("Score", score);
                    temp.putExtra("ResponseTime", responseTime);
                    setResult(RESULT_OK, temp);
                    finish();
                }
                else
                {
                    viewox.setVisibility(View.INVISIBLE);
                    gameMainF(true);
                }
            }
        },500);
    }

}
