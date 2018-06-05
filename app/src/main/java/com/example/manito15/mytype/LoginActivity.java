package com.example.manito15.mytype;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_JOIN=101;

    EditText login_edt_email, login_edt_pw;
    String ID,PWD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        login_edt_email=(EditText) findViewById(R.id.login_edt_email); //findViewById()는 무조건 onCreate()안에서 해줘야 함.(아주 중요)
        login_edt_pw=(EditText) findViewById(R.id.login_edt_pw);

    }

    public void onLoginClicked(View view) {
        // id, pwd check
        SharedPreferences pref = getSharedPreferences("a", MODE_PRIVATE);

        ID = pref.getString("ID", "");
        PWD = pref.getString("PWD", "");

        if(ID.equals(login_edt_email.getText().toString()) && PWD.equals(login_edt_pw.getText().toString())){

            Intent intent =new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("name",login_edt_email.getText().toString());
            startActivity(intent);

        }
        else{  // id나 pwd가 불일치할 때
            Toast.makeText(getApplicationContext(),"등록되지 않은 ID 혹은 틀린 패스워드입니다.",Toast.LENGTH_LONG).show();
        }

    }

    public void onJoinClicked(View view) {

        Intent intent =new Intent(getApplicationContext(), JoinActivity.class);
        startActivityForResult(intent, REQUEST_CODE_JOIN);

        // 회원가입 화면에 가면 아이디 입력창과 비밀번호 입력창이 비워지도록 설정
        login_edt_email.setText("");
        login_edt_email.setHint("아이디 입력");
        login_edt_pw.setText("");
        login_edt_pw.setHint("비밀번호 입력");

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==REQUEST_CODE_JOIN){
            if(resultCode==RESULT_OK){
                Toast.makeText(getApplicationContext(),"회원가입 완료",Toast.LENGTH_LONG).show();
            }
        }
    }
}
