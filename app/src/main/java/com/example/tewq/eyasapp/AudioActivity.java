package com.example.tewq.eyasapp;

        import android.content.Intent;
        import android.content.pm.ActivityInfo;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageButton;
        import android.widget.ImageView;
        import android.widget.TextView;

public class AudioActivity extends AppCompatActivity {

    final LoginActivity.Singleton training = LoginActivity.Singleton.getInstance();
    final int data=training.getData();
    final LoginActivity.Singleton A_leveldata = LoginActivity.Singleton.getInstance();
    final LoginActivity.Singleton A_accuracydata=LoginActivity.Singleton.getInstance();
    final LoginActivity.Singleton A_reactiontimedata=LoginActivity.Singleton.getInstance();
    int score;
    Button bBack;
    ImageView bg1, audio_result_bg, audio_result_level, audio_result_line, audio_result_line2, audio_result_score, audio_result_time;
    ImageButton button1, button2, audio_result_restart, audio_result_back;
    TextView audio_result_level_txt, audio_result_score_txt, audio_result_time_txt;
    int selected_game = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(android.R.style.Theme_DeviceDefault_NoActionBar_Fullscreen);
        setContentView(R.layout.activity_audio);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE );

        bBack = findViewById(R.id.bBack);
        bg1 = (ImageView)findViewById(R.id.audio_select_bg1);
        button1 = (ImageButton)findViewById(R.id.audio_select_button1);
        button2 = (ImageButton)findViewById(R.id.audio_select_button2);


        audio_result_bg = (ImageView)findViewById(R.id.audio_result_bg);
        audio_result_level = (ImageView)findViewById(R.id.audio_result_level);
        audio_result_line = (ImageView)findViewById(R.id.audio_result_line);
        audio_result_line2 = (ImageView)findViewById(R.id.audio_result_line2);
        audio_result_score = (ImageView)findViewById(R.id.audio_result_score);
        audio_result_time = (ImageView)findViewById(R.id.audio_result_time);

        audio_result_restart = (ImageButton)findViewById(R.id.audio_result_restart);
        audio_result_back = (ImageButton)findViewById(R.id.audio_result_back);

        audio_result_level_txt = (TextView) findViewById(R.id.audio_result_level_txt);
        audio_result_score_txt = (TextView) findViewById(R.id.audio_result_score_txt);
        audio_result_time_txt = (TextView) findViewById(R.id.audio_result_time_txt);
        //getSupportActionBar().hide();

        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent backIntent = new Intent(AudioActivity.this, TrainingModeActivity2.class);
                AudioActivity.this.startActivity(backIntent);
            }
        });
    }


    public void onClickButton(View v)
    {
        switch (v.getId()) {
            case R.id.audio_select_button1:
                selected_game = 1;
                Intent intent1 = new Intent(AudioActivity.this, Audio_1.class);
                intent1.putExtra("level",1);
                startActivityForResult(intent1, score);
                break;
            case R.id.audio_select_button2:
                selected_game = 2;
                Intent intent2 = new Intent(AudioActivity.this, Audio_2.class);
                intent2.putExtra("level",1);
                startActivityForResult(intent2, score);
                break;
            case R.id.audio_result_restart:
                bg1.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

                audio_result_bg.setVisibility(View.INVISIBLE);
                audio_result_level.setVisibility(View.INVISIBLE);
                audio_result_line.setVisibility(View.INVISIBLE);
                audio_result_score.setVisibility(View.INVISIBLE);

                audio_result_restart.setVisibility(View.INVISIBLE);
                audio_result_back.setVisibility(View.INVISIBLE);

                audio_result_level_txt.setVisibility(View.INVISIBLE);
                audio_result_score_txt.setVisibility(View.INVISIBLE);

                audio_result_line2.setVisibility(View.INVISIBLE);
                audio_result_time.setVisibility(View.INVISIBLE);
                audio_result_time_txt.setVisibility(View.INVISIBLE);

                if (selected_game == 1)
                {
                    Intent intent3 = new Intent(AudioActivity.this, Audio_1.class);
                    intent3.putExtra("level",1);
                    startActivityForResult(intent3, score);
                }
                else if (selected_game == 2)
                {
                    Intent intent4 = new Intent(AudioActivity.this, Audio_2.class);
                    intent4.putExtra("level",1);
                    startActivityForResult(intent4, score);
                }
                break;
            case R.id.audio_result_back:
                bg1.setVisibility(View.VISIBLE);
                button1.setVisibility(View.VISIBLE);
                button2.setVisibility(View.VISIBLE);

                audio_result_bg.setVisibility(View.INVISIBLE);
                audio_result_level.setVisibility(View.INVISIBLE);
                audio_result_line.setVisibility(View.INVISIBLE);
                audio_result_score.setVisibility(View.INVISIBLE);

                audio_result_restart.setVisibility(View.INVISIBLE);
                audio_result_back.setVisibility(View.INVISIBLE);
                bBack.setVisibility(View.VISIBLE);

                audio_result_level_txt.setVisibility(View.INVISIBLE);
                audio_result_score_txt.setVisibility(View.INVISIBLE);
                audio_result_line2.setVisibility(View.INVISIBLE);
                audio_result_time.setVisibility(View.INVISIBLE);
                audio_result_time_txt.setVisibility(View.INVISIBLE);
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

            audio_result_bg.setVisibility(View.VISIBLE);
            audio_result_level.setVisibility(View.VISIBLE);
            audio_result_line.setVisibility(View.VISIBLE);
            audio_result_score.setVisibility(View.VISIBLE);

            audio_result_restart.setVisibility(View.VISIBLE);
            audio_result_back.setVisibility(View.VISIBLE);
            bBack.setVisibility(View.INVISIBLE);

            audio_result_level_txt.setVisibility(View.VISIBLE);
            audio_result_score_txt.setVisibility(View.VISIBLE);
            audio_result_line2.setVisibility(View.VISIBLE);
            audio_result_time.setVisibility(View.VISIBLE);
            audio_result_time_txt.setVisibility(View.VISIBLE);
            audio_result_level_txt.setText(level+" 단계");
            audio_result_score_txt.setText(score+" %");
            audio_result_time_txt.setText(responseTime+" 초");

            String trainingno = String.valueOf(data);
            if ((trainingno.equals("0")))
            {
                //assessement mode
                A_leveldata.setDataALev(level);
                A_accuracydata.setDataAAcc(score);
                A_reactiontimedata.setDataARt(responseTime);
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
