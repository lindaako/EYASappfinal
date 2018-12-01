package com.example.tewq.eyasapp;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;
import android.widget.ToggleButton;
import org.mozilla.javascript.Context;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;

import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Arrays;

public class Information_2 extends AppCompatActivity {

    int score = 0;
    MediaPlayer correct_sound;
    MediaPlayer incorrect_sound;
    int QUESTION_NUM = 10;
    int[] num_arr;
    char[] char_arr;
    int play_num;
    int[] temp_arr;
    int error = 0;
    String ans1, ans2;
    TextView tv;
    String formula="";
    String formula_1;
    String formula_2;
    Random random = new Random();
    int[] level_arr = {7,random.nextInt(1)+6, 6, random.nextInt(1)+5,5};
    int level = 0;
    Button[] button_arr;
    ToggleButton[] num_button_arr;

    ImageView start_img;
    ImageButton buttons,buttonb;

    long startTime;
    long endTime;
    double responseTime = 0;
    Timer timer;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_information_2);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        startTime = System.currentTimeMillis();

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 1);

        correct_sound= MediaPlayer.create(this, R.raw.correct);
        incorrect_sound = MediaPlayer.create(this, R.raw.incorrect);

        num_arr=new int[8];
        char_arr=new char[7];
        button_arr = new Button[10];
        num_button_arr = new ToggleButton[8];

        tv = (TextView)findViewById(R.id.textView);

        button_arr[0] = (Button) findViewById(R.id.button1);
        button_arr[1] = (Button) findViewById(R.id.button2);
        button_arr[2] = (Button) findViewById(R.id.button3);
        button_arr[3] = (Button) findViewById(R.id.button4);
        button_arr[4] = (Button) findViewById(R.id.button5);
        button_arr[5] = (Button) findViewById(R.id.button6);
        button_arr[6] = (Button) findViewById(R.id.button7);
        button_arr[7] = (Button) findViewById(R.id.button8);
        button_arr[8] = (Button) findViewById(R.id.button9);
        button_arr[9] = (Button) findViewById(R.id.button10);

        num_button_arr[0] = (ToggleButton) findViewById(R.id.num_button1);
        num_button_arr[1] = (ToggleButton) findViewById(R.id.num_button2);
        num_button_arr[2] = (ToggleButton) findViewById(R.id.num_button3);
        num_button_arr[3] = (ToggleButton) findViewById(R.id.num_button4);
        num_button_arr[4] = (ToggleButton) findViewById(R.id.num_button5);
        num_button_arr[5] = (ToggleButton) findViewById(R.id.num_button6);
        num_button_arr[6] = (ToggleButton) findViewById(R.id.num_button7);
        num_button_arr[7] = (ToggleButton) findViewById(R.id.num_button8);

        char_arr[0] = '(';
        char_arr[1] = ')';
        char_arr[2] = '+';
        char_arr[3] = '-';
        char_arr[4] = '×';
        char_arr[5] = '÷';
        char_arr[6] = '=';

        start_img = (ImageView)findViewById(R.id.info2start_img);

        buttons = (ImageButton) findViewById(R.id.infostart);
        buttonb = (ImageButton) findViewById(R.id.infoback);

        tv.setTextSize(17);
        tv.setText("최대한 많은 숫자를 이용해서\n식을 만들어 보세요");
    }

    public void play(boolean gamebool)//게임 메인 함수
    {
        formula="";

        for (int i = 0; i <8; i++) {
            num_arr[i] = random.nextInt(9);
        }
        for (int i = 0; i <8; i++) {
            num_button_arr[i].setTextOff("" + num_arr[i]);
        }

        temp_arr = new int[8-level_arr[level]];
        for (int i = 0; i <8-level_arr[level]; i++) {
            temp_arr[i] = random.nextInt(8);

            for (int j = 0; j < i; j++) {
                if (temp_arr[j] == temp_arr[i]) {
                    temp_arr[i] = random.nextInt(8);
                    j = 0;
                }
            }
        }
        for (int i = 0; i <8-level_arr[level]; i++) {
            num_button_arr[temp_arr[i]].setTextOff("");
            num_button_arr[temp_arr[i]].setEnabled(false);
        }

        for(int i=0; i<8; i++){
            num_button_arr[i].setChecked(false);
            num_button_arr[i].setEnabled(true);
        }
    }
    public void bs(View view) {
        switch (view.getId()) {
            case R.id.infostart:
                start_img.setVisibility(View.GONE);
                buttons.setVisibility(View.GONE);
                play(true);
                break;
            case R.id.infoback:
                finish();
                break;
        }
    }
    public void num_btn(View view) {
        tv.setTextSize(30);
        switch (view.getId()) {
            case R.id.num_button1:
                num_button_arr[0].setEnabled(false);
                formula = formula + num_arr[0];
                tv.setText(formula);
                break;
            case R.id.num_button2:
                num_button_arr[1].setEnabled(false);
                formula = formula + num_arr[1];
                tv.setText(formula);
                break;
            case R.id.num_button3:
                num_button_arr[2].setEnabled(false);
                formula = formula + num_arr[2];
                tv.setText(formula);
                break;
            case R.id.num_button4:
                num_button_arr[3].setEnabled(false);
                formula = formula + num_arr[3];
                tv.setText(formula);
                break;
            case R.id.num_button5:
                num_button_arr[4].setEnabled(false);
                formula = formula + num_arr[4];
                tv.setText(formula);
                break;
            case R.id.num_button6:
                num_button_arr[5].setEnabled(false);
                formula = formula + num_arr[5];
                tv.setText(formula);
                break;
            case R.id.num_button7:
                num_button_arr[6].setEnabled(false);
                formula = formula + num_arr[6];
                tv.setText(formula);
                break;
            case R.id.num_button8:
                num_button_arr[7].setEnabled(false);
                formula = formula + num_arr[7];
                tv.setText(formula);
                break;
        }
    }

    public void btn(View view) {
        tv.setTextSize(30);
        switch (view.getId()) {
            case R.id.button1:
                formula = formula + char_arr[0];
                tv.setText(formula);
                break;
            case R.id.button2:
                formula = formula + char_arr[1];
                tv.setText(formula);
                break;
            case R.id.button3:
                formula = formula + char_arr[2];
                tv.setText(formula);
                break;
            case R.id.button4:
                formula = formula + char_arr[3];
                tv.setText(formula);
                break;
            case R.id.button5:
                formula = formula + char_arr[4];
                tv.setText(formula);
                break;
            case R.id.button6:
                formula = formula + char_arr[5];
                tv.setText(formula);
                break;
            case R.id.button7:
                formula = formula + char_arr[6];
                tv.setText(formula);
                break;

            case R.id.button8:
                tv.setText("");
                formula ="";
                for(int i=0; i<8; i++){
                    num_button_arr[i].setChecked(false);
                    num_button_arr[i].setEnabled(true);
                }
                for (int i = 0; i <8-level_arr[level]; i++) {
                    num_button_arr[temp_arr[i]].setTextOff("");
                    num_button_arr[temp_arr[i]].setEnabled(false);
                }
                break;
            case R.id.button9:
                if (formula.length() == 0 )
                    break;
                char temp;
                temp=formula.charAt(formula.length()-1);
                for(int i=0;i<8;i++){
                    if((int)temp-'0'==num_arr[i]){
                        if(num_button_arr[i].isChecked()) {
                            num_button_arr[i].setChecked(false);
                            num_button_arr[i].setEnabled(true);
                            break;
                        }
                    }
                }
                formula= formula.substring(0, formula.length()-1);

                tv.setText(formula);
                break;


            case R.id.button10:

                int cut;
                cut=formula.indexOf("=");
                if(cut<0)
                    return;

                formula = formula.replaceAll("×", "*");
                formula = formula.replaceAll("÷", "/");

                formula_1 = formula.substring(0,cut);
                formula_2 = formula.substring(cut+1);

                cal_enter();

                if(error==1){
                    break;
                }
                tv.setText("");

                if(ans1.equals(ans2)){
                    score += 100/QUESTION_NUM;
                    play_num++;
                    correctF(true);
                } else {//오답
                    play_num++;
                    incorrectF(true);
                }
                if (play_num == QUESTION_NUM)
                {
                        Intent itemp = new Intent();
                        endTime = System.currentTimeMillis();
                        responseTime = (endTime - startTime)/1000/(double)QUESTION_NUM;
                        itemp.putExtra("Score", score);
                        itemp.putExtra("ResponseTime", responseTime);
                        itemp.putExtra("Level", level);
                        setResult(RESULT_OK, itemp);
                        finish();
                }

                break;
        }
    }

    public void correctF(boolean bool) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                correct_sound.start();
            }
        }, 1000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tv.setText("");
                formula ="";
                for(int i=0; i<8; i++){
                    num_button_arr[i].setChecked(false);
                    num_button_arr[i].setEnabled(true);
                }
                for (int i = 0; i <8-level_arr[level]; i++) {
                    num_button_arr[temp_arr[i]].setTextOff("");
                    num_button_arr[temp_arr[i]].setEnabled(false);
                }
                play(true);
            }
        }, 1000);
    }

    public void incorrectF(boolean bool) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                incorrect_sound.start();
            }
        }, 1000);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                play(true);
            }
        }, 1000);
    }

    public void cal_enter(){

        Context rhino = Context.enter();

        rhino.setOptimizationLevel(-1);
        try {
            Scriptable scope = rhino.initStandardObjects();

            Object obj = rhino.evaluateString(scope, formula_1, "JavaScript", 1, null);
            ans1 = obj.toString();

            Object obj2 = rhino.evaluateString(scope, formula_2, "JavaScript", 1, null);
            ans2 = obj2.toString();
            error=0;
        }catch (Exception e){
            error = 1;
        }
        finally {
            Context.exit();
        }
    }
}