package com.example.tewq.eyasapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

public class TrainingModeActivity2 extends AppCompatActivity
{

    final LoginActivity.Singleton training = LoginActivity.Singleton.getInstance();
    final int data=training.getData();
    TextView textView;
    Button bLogout, bMind, bAuditory, bVision, bBack;
    private AlertDialog.Builder exit_declaration;
    private AlertDialog.Builder logout_declaration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_training_mode2);


        bBack=findViewById(R.id.bBack);
        bAuditory=findViewById(R.id.bAuditory);
        bVision=findViewById(R.id.bVision);
        bMind=findViewById(R.id.bMind);
        bLogout=findViewById(R.id.bLogout);

        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent backIntent = new Intent(TrainingModeActivity2.this, UserActivity.class);
                TrainingModeActivity2.this.startActivity(backIntent);
            }
        });

        bLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {


                logout_declaration = new AlertDialog.Builder(TrainingModeActivity2.this);
                logout_declaration.setMessage("로그 아웃 하시겠습니까?");
                logout_declaration.setPositiveButton("예", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int whichButton)
                    {

                        Intent logoutIntent = new Intent(TrainingModeActivity2.this, LoginActivity.class);
                        TrainingModeActivity2.this.startActivity(logoutIntent);

                    }
                });

                logout_declaration.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int whichButton)
                    {
                        dialog.cancel();
                    }
                });

                logout_declaration.show();


                Intent logoutIntent = new Intent(TrainingModeActivity2.this, LoginActivity.class);
                TrainingModeActivity2.this.startActivity(logoutIntent);
            }
        });


        bAuditory.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //go to Auditory page
                Intent auditoryIntent = new Intent(TrainingModeActivity2.this, AudioActivity.class);
                TrainingModeActivity2.this.startActivity(auditoryIntent);
            }
        });

        bVision.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //go to Visual page
                Intent visualIntent = new Intent(TrainingModeActivity2.this, VisualActivity.class);
                TrainingModeActivity2.this.startActivity(visualIntent);
            }
        });

        bMind.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                //go to information processing page
                Intent mindIntent = new Intent(TrainingModeActivity2.this, Information.class);
                TrainingModeActivity2.this.startActivity(mindIntent);
            }
        });



        String trainingno = String.valueOf(data);
        textView = findViewById(R.id.textView);

        if ((trainingno.equals("0")))
        {
            //assessement mode
            textView.setText("Assessement Mode");
        }
        else if ((trainingno.equals("1")))
        {
            //training mode
            textView.setText("Training Mode");
        }
        else
            {

            }

    }


    public void clickexit(View v)
    {

        exit_declaration = new AlertDialog.Builder(this);
        exit_declaration.setMessage("종료 하시겠습니까?");
        exit_declaration.setPositiveButton("예", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int whichButton)
            {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });

        exit_declaration.setNegativeButton("아니요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton)
            {
                dialog.cancel();
            }
        });

        exit_declaration.show();


    }
}
