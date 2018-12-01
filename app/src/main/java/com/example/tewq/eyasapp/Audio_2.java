package com.example.tewq.eyasapp;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class Audio_2 extends AppCompatActivity {

    ImageView gamebg; ImageButton exnext;
    ImageView viewox;
    ImageButton animalbtn[];
    int answer;
    int animallist[] = new int[7];
    MediaPlayer effect_sound;
    MediaPlayer animal_sound;
    int correctCount = 0;
    int gameCount = 10;
    int score = 100;
    final static int CAT = 0;
    final static int CHICK = 1;
    final static int COW = 2;
    final static int DOG = 3;
    final static int DUCK = 4;
    final static int HORSE = 5;
    final static int LION = 6;
    boolean touchable = true;
    int level;
    Random random = new Random();
    long pressedTime = 0;
    long currentTime = 0;
    double responseTime = 0;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_audio_2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 1);
        handler = new Handler();

        gamebg = (ImageView)findViewById(R.id.animal_game_background);
        exnext = (ImageButton)findViewById(R.id.animal_explain_nextbutton);

        animalbtn = new ImageButton[7];
        animalbtn[0] = (ImageButton)findViewById(R.id.animal_button1);
        animalbtn[1] = (ImageButton)findViewById(R.id.animal_button2);
        animalbtn[2] = (ImageButton)findViewById(R.id.animal_button3);
        animalbtn[3] = (ImageButton)findViewById(R.id.animal_button4);
        animalbtn[4] = (ImageButton)findViewById(R.id.animal_button5);
        animalbtn[5] = (ImageButton)findViewById(R.id.animal_button6);
        animalbtn[6] = (ImageButton)findViewById(R.id.animal_button7);

        viewox = (ImageView)findViewById(R.id.animal_ox) ;

        gamebg.setVisibility(View.VISIBLE);
        exnext.setVisibility(View.VISIBLE);

        effect_sound = MediaPlayer.create(this, R.raw.o);
        animal_sound = MediaPlayer.create(this, R.raw.audio_2_cat);

        switch (level)
        {
            case 1:
                gameCount = 5;
                break;
            case 2:
                gameCount = 7;
                break;
            case 3:
                gameCount = 10;
                break;
            case 4:
                gameCount = 15;
                break;
            case 5:
                gameCount = 20;
                break;
        }
    }


    @Override
    public void onBackPressed() {
        if ( pressedTime == 0 ) {
            Toast.makeText(Audio_2.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
            pressedTime = System.currentTimeMillis();
        }
        else {
            int seconds = (int) (System.currentTimeMillis() - pressedTime);

            if ( seconds > 2000 ) {
                Toast.makeText(Audio_2.this, " 한 번 더 누르면 종료됩니다." , Toast.LENGTH_LONG).show();
                pressedTime = 0 ;
            }
            else {
                handler.removeCallbacksAndMessages(null);
                animal_sound.stop();
                animal_sound.release();
                effect_sound.stop();
                effect_sound.release();
                setResult(RESULT_CANCELED);
                finish();
            }
        }
    }

    public void onClickButton(View v) {
        if(touchable) {
            switch (v.getId()) {
                default:
                    break;
                case R.id.animal_explain_nextbutton:
                    gamebg.setImageResource(R.drawable.audio_2_background_normal);
                    exnext.setVisibility(View.INVISIBLE);
                    for (int i = 0; i < 7; i++) {
                        animalbtn[i].setVisibility(View.VISIBLE);
                    }
                    gameMainF(true);
                    break;
                case R.id.animal_button1:
                    if (answer == 0)
                        correctF();
                    else
                        falseF();
                    break;
                case R.id.animal_button2:
                    if (answer == 1)
                        correctF();
                    else
                        falseF();
                    break;
                case R.id.animal_button3:
                    if (answer == 2)
                        correctF();
                    else
                        falseF();
                    break;
                case R.id.animal_button4:
                    if (answer == 3)
                        correctF();
                    else
                        falseF();
                    break;
                case R.id.animal_button5:
                    if (answer == 4)
                        correctF();
                    else
                        falseF();
                    break;
                case R.id.animal_button6:
                    if (answer == 5)
                        correctF();
                    else
                        falseF();
                    break;
                case R.id.animal_button7:
                    if (answer == 6)
                        correctF();
                    else
                        falseF();
                    break;
            }
        }
    }

    private void gameMainF(boolean bool)
    {

        answer = random.nextInt(7);
        animal_sound.stop();
        animal_sound.release();
        currentTime = System.currentTimeMillis();
        switch (answer) {//울음소리 재생
            case CAT:
                animal_sound = MediaPlayer.create(Audio_2.this, R.raw.audio_2_cat);
                animal_sound.start();
                break;

                case CHICK:
                    animal_sound = MediaPlayer.create(Audio_2.this, R.raw.audio_2_chick);
                    animal_sound.start();
                    break;
                case COW:
                    animal_sound = MediaPlayer.create(Audio_2.this, R.raw.audio_2_cow);
                    animal_sound.start();
                    break;
                case DOG:
                    animal_sound = MediaPlayer.create(Audio_2.this, R.raw.audio_2_dog);
                    animal_sound.start();
                    break;
                case DUCK:
                    animal_sound = MediaPlayer.create(Audio_2.this, R.raw.audio_2_duck);
                    animal_sound.start();
                    break;
                case HORSE:
                    animal_sound = MediaPlayer.create(Audio_2.this, R.raw.audio_2_horse);
                    animal_sound.start();
                    break;
                case LION:
                    animal_sound = MediaPlayer.create(Audio_2.this, R.raw.audio_2_lion);
                    animal_sound.start();
                    break;
            }

    }

    private void  correctF()
    {
        touchable = false;
        responseTime += ( System.currentTimeMillis()-currentTime);
        effect_sound.stop();
        effect_sound.release();
        effect_sound= MediaPlayer.create(this, R.raw.o);
        effect_sound.start();
        viewox.setImageResource(R.drawable.audio_2_correct);
        viewox.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameCount--;
                if (gameCount == 0)
                {
                    animal_sound.stop();
                    animal_sound.release();
                    effect_sound.stop();
                    effect_sound.release();
                    responseTime/=10000.0;
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
                    touchable = true;
                    gameMainF(true);
                }
            }
        },500);

    }

    private void  falseF()
    {
        touchable = false;
        responseTime += ( System.currentTimeMillis()-currentTime);
        switch (level)
        {
            case 1:
                score -= 20;
                break;
            case 2:
                score -= 14;
                break;
            case 3:
                score -= 10;
                break;
            case 4:
                score -= 6;
                break;
            case 5:
                score -= 5;
                break;
        }
        effect_sound.stop();
        effect_sound.release();
        effect_sound= MediaPlayer.create(this, R.raw.x);
        effect_sound.start();
        viewox.setImageResource(R.drawable.audio_2_incorrect);
        viewox.setVisibility(View.VISIBLE);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gameCount--;
                if (gameCount == 0)
                {
                    animal_sound.stop();
                    animal_sound.release();
                    effect_sound.stop();
                    effect_sound.release();
                    responseTime/=10000.0;
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
                    touchable = true;
                    gameMainF(true);
                }
            }
        },500);
    }
}
