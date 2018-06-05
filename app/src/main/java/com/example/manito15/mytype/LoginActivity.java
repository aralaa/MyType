package com.example.manito15.mytype;

import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final EditText userEmail=(EditText)findViewById(R.id.login_edt_email);
        final EditText userPassword=(EditText) findViewById(R.id.login_edt_pw);
        final Button loginButton=(Button) findViewById(R.id.login_btn_login);
        final Button joinButton=(Button) findViewById(R.id.login_btn_join);

        joinButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent joinIntent=new Intent(LoginActivity.this, JoinActivity.class);
                LoginActivity.this.startActivity(joinIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //final String userEmail=userEmail.getText().toString();
                //final String userPassword=userPassword.getText().toString();

                Response.Listener<String> responseListener=new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                String userEmail = jsonResponse.getString("userEmail");
                                String userPassword = jsonResponse.getString("userPassword");

                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                intent.putExtra("userEmail", userEmail);
                                intent.putExtra("userPassword", userPassword);
                                LoginActivity.this.startActivity(intent);


                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("로그인에 실패하였습니다.")
                                        .setNegativeButton("다시 시도", null)
                                        .create()
                                        .show();

                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };
                //LoginRequest loginRequest=new LoginRequest(userEmail,userPassword,responseListener);
                RequestQueue queue =Volley.newRequestQueue(LoginActivity.this);
                //queue.add(loginRequest);
            }
        });


    }
}
