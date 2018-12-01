package com.example.tewq.eyasapp;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class ResultsActivity extends AppCompatActivity {

    Button bBack;
    TextView etAuditoryLevValueA, etAuditoryAccValueA, etAuditoryReaValueA, etVisualReaValueA, etVisualAccValueA, etVisuaLevValueA,
            etMindReaValueA, etMindAccValueA, etMindLevValueA;

    final LoginActivity.Singleton training = LoginActivity.Singleton.getInstance();
    final int data=training.getData();

    LoginActivity.Singleton uname=LoginActivity.Singleton.getInstance();
    final String Username=uname.getStringuname();
    private static String URL ="http://eyas.dx.am/insertscores.php";
    private Snackbar snackbar;
    private ProgressDialog pd;

    final LoginActivity.Singleton A_leveldata=LoginActivity.Singleton.getInstance();
    final int ALevdata=A_leveldata.getDataALev();
    final LoginActivity.Singleton A_accuracydata=LoginActivity.Singleton.getInstance();
    final int AAdata=A_accuracydata.getDataAAcc();
    final LoginActivity.Singleton A_reactiontimedata=LoginActivity.Singleton.getInstance();
    final double ARTdata=A_reactiontimedata.getDataARt();

    final LoginActivity.Singleton V_leveldata=LoginActivity.Singleton.getInstance();
    final int VLevdata=V_leveldata.getDataVLev();
    final LoginActivity.Singleton V_accuracydata=LoginActivity.Singleton.getInstance();
    final int VAdata=V_accuracydata.getDataVAcc();
    final LoginActivity.Singleton V_reactiontimedata=LoginActivity.Singleton.getInstance();
    final double VRTdata=V_reactiontimedata.getDataVRt();

    final LoginActivity.Singleton M_leveldata=LoginActivity.Singleton.getInstance();
    final int MLevdata=M_leveldata.getDataMLev();
    final LoginActivity.Singleton M_accuracydata=LoginActivity.Singleton.getInstance();
    final int MAdata=M_accuracydata.getDataMAcc();
    final LoginActivity.Singleton M_reactiontimedata=LoginActivity.Singleton.getInstance();
    final double MRTdata=M_reactiontimedata.getDataMRt();



    private AlertDialog.Builder exit_declaration;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_results);

        pd = new ProgressDialog(ResultsActivity.this);
        bBack = findViewById(R.id.bBack);

        etMindLevValueA = findViewById(R.id.etMindLevValueA);
        etMindAccValueA = findViewById(R.id.etMindAccValueA);
        etMindReaValueA = findViewById(R.id.etMindReaValueA);

        etVisuaLevValueA = findViewById(R.id.etVisuaLevValueA);
        etVisualAccValueA = findViewById(R.id.etVisualAccValueA);
        etVisualReaValueA = findViewById(R.id.etVisualReaValueA);

        etAuditoryLevValueA = findViewById(R.id.etAuditoryLevValueA);
        etAuditoryAccValueA = findViewById(R.id.etAuditoryAccValueA);
        etAuditoryReaValueA = findViewById(R.id.etAuditoryReaValueA);


        String mlevdata = String.valueOf(MLevdata);
        etMindLevValueA.setText(mlevdata + " 단계");
        String madata = String.valueOf(MAdata);
        etMindAccValueA.setText(madata + "%");
        String mrtdata = String.valueOf(MRTdata);
        etMindReaValueA .setText(mrtdata + " 초");


        String vlevdata = String.valueOf(VLevdata);
        etVisuaLevValueA.setText(vlevdata + " 단계");
        String vadata = String.valueOf(VAdata);
        etVisualAccValueA.setText(vadata + "%");
        String vrtdata = String.valueOf(VRTdata);
        etVisualReaValueA.setText(vrtdata + " 초");


        String alevdata = String.valueOf(ALevdata);
        etAuditoryLevValueA.setText(alevdata + " 단계" );
        String aadata = String.valueOf(AAdata);
        etAuditoryAccValueA.setText(aadata + "%");
        String artdata = String.valueOf(ARTdata);
        etAuditoryReaValueA.setText(artdata + " 초");


        String trainingmodeactive = String.valueOf(data);

        if ((trainingmodeactive.equals("0")))
        {
            //assessement mode
            new CountDownTimer(2000,1000)
            {
                public void onFinish()
                {
                    sendresultdata();
                }

                public void onTick(long millisUntilFinished)
                {

                }

            }.start();


        }
        else if ((trainingmodeactive.equals("1")))
        {
            //training mode

        }
        else
        {

        }



        bBack.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent backIntent = new Intent(ResultsActivity.this, UserActivity.class);
                ResultsActivity.this.startActivity(backIntent);
            }
        });
    }

    private void sendresultdata()
    {
        pd.setMessage("Saving Results data . . .");
        pd.show();
        RequestQueue queue = Volley.newRequestQueue(ResultsActivity.this);
        String response = null;

        final String finalResponse = response;

        StringRequest postRequest = new StringRequest(Request.Method.POST, URL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response) {

                        pd.hide();
                        showSnackbar(response);

                        if(response.equals("Data saved successfully"))
                        {

                            //Toast.makeText(getApplicationContext(), "Login Successful!", Toast.LENGTH_SHORT).show();

                        }

                        else{


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
                String username = String.valueOf(Username);
                params.put("username", username);

                String vvadata = String.valueOf(VAdata);
                params.put("accuracy_vision", vvadata);
                params.put("score_visual", vvadata);
                String vvrtdata = String.valueOf(VRTdata);
                params.put("reactiontime_visual", vvrtdata);

                String aaadata = String.valueOf(AAdata);
                params.put("accuracy_auditory", aaadata);
                params.put("score_auditory", aaadata);
                String aartdata = String.valueOf(ARTdata);
                params.put("reactiontime_auditory", aartdata);

                String mmadata = String.valueOf(MAdata);
                params.put("accuracy_mind", mmadata);
                params.put("score_mind", mmadata);
                String mmrtdata = String.valueOf(MRTdata);
                params.put("reactiontime_mind", mmrtdata);
                return params;
            }
        };
        postRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(postRequest);

    }


    public void showSnackbar(String stringSnackbar)
    {
        snackbar.make(findViewById(android.R.id.content), stringSnackbar.toString(), Snackbar.LENGTH_SHORT)
                .setActionTextColor(getResources().getColor(R.color.colorPrimary))
                .show();
    }

    public void clickexit(View v)
    {

        exit_declaration = new AlertDialog.Builder(ResultsActivity.this);
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
