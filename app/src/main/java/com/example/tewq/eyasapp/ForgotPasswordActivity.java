package com.example.tewq.eyasapp;

import android.app.AlertDialog;
import android.net.Uri;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class ForgotPasswordActivity extends AppCompatActivity
{
    LoginActivity.Singleton pass_reset=LoginActivity.Singleton.getInstance();
    final String passwordResetCodes = pass_reset.getCodeString();
    private static String URL ="http://eyas.dx.am/Resetpassword.php";
    LoginActivity.Singleton e_mail=LoginActivity.Singleton.getInstance();
    final String user_email = e_mail.getString();
    EditText etEmail, etPassResetCode,etNewPassword;
    Button bResetPassword,bBack;
    private Snackbar snackbar;   private ProgressDialog pd;
    private AlertDialog.Builder exit_declaration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forgot_password);
        pd = new ProgressDialog(ForgotPasswordActivity.this);
        etEmail =  findViewById(R.id.etEmail);
        etEmail.setText(user_email);

        etPassResetCode =  findViewById(R.id.etPassResetCode);
        etNewPassword =  findViewById(R.id.etNewPassword);
        bResetPassword = findViewById(R.id.bResetPassword);
        bBack = findViewById(R.id.bBack);


        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent backIntent = new Intent(ForgotPasswordActivity.this, LoginActivity.class);
                ForgotPasswordActivity.this.startActivity(backIntent);
            }
        });
        bResetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String mail = etEmail.getText().toString();
                String PRcode = etPassResetCode.getText().toString();
                String passwordResetCode = String.valueOf(passwordResetCodes);
                String new_pass = etNewPassword.getText().toString();

                if (!((PRcode.equals("")) || (mail.equals("")) || (new_pass.equals(""))))
                {

                    if ((passwordResetCode.equals(PRcode)))
                    {
                        //Toast.makeText(getApplicationContext(), "The codes match!", Toast.LENGTH_SHORT).show();
                        UpdatePassword();

                    }

                    else
                    {
                        Toast.makeText(getApplicationContext(), "The codes do not match,try again!", Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Please fill in all the required fields!", Toast.LENGTH_SHORT).show();
                }

            }


        });
    }



    private void UpdatePassword(){
        pd.setMessage("Changing password . . .");
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(ForgotPasswordActivity.this);
        String response = null;
        final String finalResponse = response;


        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response) {
                        pd.hide();
                        //Response
                        showSnackbar(response);

                        if(response.equals("Password changed successfully"))
                        {
                            new CountDownTimer(2000,1000)
                            {
                                public void onFinish()
                                {
                                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                                }

                                public void onTick(long millisUntilFinished)
                                {

                                }

                            }.start();



                        }
                        else
                        {

                        }
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        Log.d("ErrorResponse", finalResponse);


                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();

                params.put("email", etEmail.getText().toString());
                params.put("password", etNewPassword.getText().toString());

                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }

    public void showSnackbar(String stringSnackbar){
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
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

        exit_declaration.setNegativeButton("아니", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int whichButton)
            {
                dialog.cancel();
            }
        });

        exit_declaration.show();


    }


    }
