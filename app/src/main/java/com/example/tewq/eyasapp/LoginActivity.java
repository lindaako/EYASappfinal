package com.example.tewq.eyasapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.UUID;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Properties;

import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LoginActivity extends AppCompatActivity {

        Button bLogin, bRegister;
        EditText etUsername, etPassword;
        TextView etForgotPassword;
        EditText edittext;
        GMailSender sender;

        String id = null;

        //private static String URL  ="https://youngashly.000webhostapp.com/login.php";
        private static String URL ="http://eyas.dx.am/login.php";
        private static String URL2 ="http://eyas.dx.am/emailcheck.php";
        private Snackbar snack;
        private Snackbar snackbar;
        private ProgressDialog pd;
        private ProgressDialog em;
        private AlertDialog.Builder alert;
        private AlertDialog.Builder exit_declaration;


        public static class Singleton
        {
        private  static Singleton instance = null;
        private int data;
        private String e_mail;
        private String pass_reset;
        private String uname;

        private int A_leveldata;
        private int A_accuracydata;
        private double A_reactiontimedata;
        private int V_leveldata;
        private int V_accuracydata;
        private double V_reactiontimedata;
        private int M_leveldata;
        private int M_accuracydata;
        private double M_reactiontimedata;


        protected Singleton()
        {

            // Exists only to defeat instantiation.
        }

        public void setData(int d)
        {
            this.data=d;
        }
        public void setString(String s)
            {
                this.e_mail=s;
            }
        public void setCodeString(String cc)
            {
                this.pass_reset=cc;
            }
        public void setStringuname(String u) { this.uname=u; }

        public void setDataALev(int alv) { this. A_leveldata=alv; }
        public void setDataAAcc(int aad) { this. A_accuracydata=aad; }
        public void setDataARt(double artd) { this.A_reactiontimedata=artd; }

        public void setDataVLev(int vlv) { this. V_leveldata=vlv; }
        public void setDataVAcc(int vad)
            {
                this.V_accuracydata=vad;
            }
        public void setDataVRt(double vrtd)
            {
                this.V_reactiontimedata=vrtd;
            }

        public void setDataMLev(int mlv) { this. M_leveldata=mlv; }
        public void setDataMAcc(int mad)
            {
                this.M_accuracydata=mad;
            }
        public void setDataMRt(double mrtd)
            {
                this.M_reactiontimedata=mrtd;
            }

        public int getData() { return this.data; }
        public String getString()
            {
                return this.e_mail;
            }
        public String getCodeString() { return this.pass_reset;}
        public String getStringuname() { return this.uname; }

        public int getDataALev() { return this. A_leveldata; }
        public int getDataAAcc() { return this. A_accuracydata; }
        public double getDataARt() { return this.A_reactiontimedata; }

        public int getDataVLev() { return this. V_leveldata; }
        public int getDataVAcc()
            {
                return this.V_accuracydata;
            }
        public double getDataVRt() { return this.V_reactiontimedata; }

        public int getDataMLev() { return this. M_leveldata; }
        public int getDataMAcc()
            {
                return this.M_accuracydata;
            }
        public double getDataMRt()
            {
                return this.M_reactiontimedata;
            }

        public static Singleton getInstance()
        {
            if(instance == null)
            {
                instance = new Singleton();
            }
            return instance;
        }
        }


        Singleton e_mail=Singleton.getInstance();
        Singleton pass_reset=Singleton.getInstance();
        Singleton uname=Singleton.getInstance();



        @Override
        protected void onCreate(Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
            setContentView(R.layout.activity_login);
            final Context mcontext=this;
            etForgotPassword = findViewById(R.id.etForgotPassword);
            etUsername =  findViewById(R.id.etUsername);
            etPassword =  findViewById(R.id.etPassword);
            pd = new ProgressDialog(LoginActivity.this);
            em = new ProgressDialog(LoginActivity.this);
            bLogin = findViewById(R.id.bLogin);
            bRegister = findViewById(R.id.bRegister);


            etForgotPassword.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {

                    alert = new AlertDialog.Builder(mcontext);
                    edittext = new EditText(LoginActivity.this);
                    alert.setMessage("인증 코드를 받으려면 계정과 연결된 이메일 주소를 입력하십시오.");
                    alert.setTitle("비밀번호를 잊으 셨나요");
                    alert.setView(edittext);
                    alert.setPositiveButton("잇다", new DialogInterface.OnClickListener()
                    {
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            emailcheck();

                        }
                    });

                    alert.setNegativeButton("뒤로", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int whichButton)
                        {
                            dialog.cancel();
                        }
                    });

                    alert.show();


                }
            });


            bRegister.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v) {

                    Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                    LoginActivity.this.startActivity(registerIntent);
                }
            });

            bLogin.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    loginRequest();
                }
                });



        }



    private void loginRequest()
    {
        pd.setMessage("Signing In . . .");
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(LoginActivity.this);
        String response = null;

        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        pd.hide();
                        //showSnackbar(response);

                        if(response.equals("Login"))
                        {
                            showSnackbar("Login Successful");
                            //Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), UserActivity.class));
                        }

                        else{
                            showSnackbar("Invalid Login credentials!");

                        }


                    }

                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // error
                        pd.hide();
                        Log.d("ErrorResponse", finalResponse);

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("username", etUsername.getText().toString());
                uname.setStringuname(etUsername.getText().toString());
                params.put("password", etPassword.getText().toString());
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


    public void sendEmailpuk() {



             //sender = new GMailSender("noreply.eyasapp@gmail.com", "YBqak56jKj6xtLx");
			 sender = new GMailSender("mobileappeyas@gmail.com", "YBqak56jKj6xtLx");


        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);


        try {

            new MyAsyncClass().execute();



        } catch (Exception ex) {

           // Toast.makeText(getApplicationContext(), ex.toString(), Toast.LENGTH_SHORT).show();


        }







    }




    class MyAsyncClass extends AsyncTask<Void, Void, Void> {



        ProgressDialog pDialog;


        @Override

        protected void onPreExecute() {

            super.onPreExecute();



            pDialog = new ProgressDialog(LoginActivity.this);

            pDialog.setMessage("Please wait...");

            pDialog.show();



        }



        @Override

        protected Void doInBackground(Void... mApi) {


            id = UUID.randomUUID().toString();
            String code = id.substring(0, 7);
            pass_reset.setCodeString(code);

            try {

                String user_email = e_mail.getString();

                String subject = "비밀번호 재설정 코드 (Password reset code)";
                String messages = "안녕하세요!"+ '\n'+'\n'+
                        "비밀번호 재설정 코드 " + code + '\n'+'\n'+
                        "드림," + '\n'+
                        "EYAS team";


                // Add subject, Body, your mail Id, and receiver mail Id.

                sender.sendMail(subject, messages, "mobileappeyas@gmail.com", user_email);






            }



            catch (Exception ex) {



            }

            return null;

        }



        @Override

        protected void onPostExecute(Void result) {

            super.onPostExecute(result);

            pDialog.cancel();

           // Toast.makeText(getApplicationContext(), "Email send", Toast.LENGTH_SHORT).show();


        }

    }

    private void emailcheck ()
        {
            em.setMessage("Checking for emails existence . . .");
            em.show();
            RequestQueue queue2 = Volley.newRequestQueue(LoginActivity.this);

            String response2 = null;
            final String finalResponse2 = response2;
            StringRequest postRequest2 = new StringRequest(Request.Method.POST, URL2,
                    new Response.Listener<String>()
                    {
                        @Override
                        public void onResponse(String response2) {

                            em.hide();
                            showSnackbar2(response2);

                            if(response2.equals("Email exists"))
                            {
                                sendEmailpuk();

                                new CountDownTimer(2000,1000)
                                {
                                    public void onFinish()
                                    {
                                        startActivity(new Intent(getApplicationContext(), ForgotPasswordActivity.class));
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
                        public void onErrorResponse(VolleyError error2)
                        {
                            // error
                            em.hide();
                            Log.d("ErrorResponse2", finalResponse2);


                        }
                    }
            ) {
                @Override
                protected Map<String, String> getParams()
                {
                    Map<String, String>  params = new HashMap<String, String>();
                    params.put("email", edittext.getText().toString());
                    e_mail.setString(edittext.getText().toString());



                    return params;


                }
            };
            postRequest2.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            queue2.add(postRequest2);

        }




        public void showSnackbar2(String stringSnackbar2)
        {
            snack.make(findViewById(android.R.id.content), stringSnackbar2.toString(), Snackbar.LENGTH_SHORT)
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