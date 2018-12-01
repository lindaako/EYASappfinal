package com.example.tewq.eyasapp;


import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;
import cyd.awesome.material.AwesomeText;
import cyd.awesome.material.FontCharacterMaps;

public class RegisterActivity extends AppCompatActivity {
    //private static String S_URL ="https://youngashly.000webhostapp.com/signup.php";
    private static String S_URL ="http://eyas.dx.am/signup.php";
    EditText etEmail,etUsername,etPassword,etConfirmPassword,etAge;
    Button bRegister, bLogin;
    AwesomeText show_hide_ic;
    boolean pwd_status = true;
    RadioButton radioButton2;private Snackbar snackbar;   private ProgressDialog pd;
    private AlertDialog.Builder exit_declaration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        pd = new ProgressDialog(RegisterActivity.this);
        bRegister = findViewById(R.id.bRegister);
        bLogin = findViewById(R.id.bLogin);
        etAge = findViewById(R.id.etAge);
        etEmail = findViewById(R.id.etEmail);
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        show_hide_ic = (AwesomeText) findViewById(R.id.pwd_show_hide);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        radioButton2 = findViewById(R.id.radioButton2);

        show_hide_ic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwd_status) {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pwd_status = false;
                    show_hide_ic.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY);
                    etPassword.setSelection(etPassword.length());
                } else {
                    etPassword.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                    pwd_status = true;
                    show_hide_ic.setMaterialDesignIcon(FontCharacterMaps.MaterialDesign.MD_VISIBILITY_OFF);
                    etPassword.setSelection(etPassword.length());

                }
            }
        });

        bLogin.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                                      Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                                      RegisterActivity.this.startActivity(loginIntent);
            }
        });

        bRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Con_pass = etConfirmPassword.getText().toString();
                String pass = etPassword.getText().toString();
                String uname = etUsername.getText().toString();
                String e_mail = etEmail.getText().toString();
                String s_age = etAge.getText().toString();

                if ((pass.equals("")) || (uname.equals("")) || (e_mail.equals("")) || (Con_pass.equals("")) || (s_age.equals("")) )
                {
                    Toast.makeText(getApplicationContext(), "Please fill in all the required fields!", Toast.LENGTH_SHORT).show();
                }

                else if(!(Patterns.EMAIL_ADDRESS.matcher(e_mail).matches()))
                {
                    Toast.makeText(getApplicationContext(), "Invalid email address!", Toast.LENGTH_SHORT).show();
                }

                else
                    {

                if (!(pass.equals(Con_pass)))
                    {
                    Toast.makeText(getApplicationContext(), "The Password and Confirm password must match!", Toast.LENGTH_SHORT).show();
                    }
                else
                    {

                    if (radioButton2.isChecked())
                         {

                        signupRequest();

                        }
                    else
                        {

                        Toast.makeText(getApplicationContext(), "Please Accept Terms & Services", Toast.LENGTH_SHORT).show();
                        }
                     }
                 }
            }
        });
    }

    private void signupRequest(){
        pd.setMessage("Signing Up . . .");
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(RegisterActivity.this);
        String response = null;
        final String finalResponse = response;


        StringRequest postRequest = new StringRequest(Request.Method.POST, S_URL,
                new Response.Listener<String>()
                {

                    @Override
                    public void onResponse(String response) {
                        pd.hide();
                        //Response
                        showSnackbar(response);

                        if(response.equals("Successfully Signed In"))
                        {

                            startActivity(new Intent(getApplicationContext(), LoginActivity.class));

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
                params.put("password", etPassword.getText().toString());
                params.put("username", etUsername.getText().toString());
                params.put("age", etAge.getText().toString());

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



