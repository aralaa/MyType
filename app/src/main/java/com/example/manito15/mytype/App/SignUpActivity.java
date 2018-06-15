package com.example.manito15.mytype.App;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.manito15.mytype.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "SignUpActivity";

    private Context mContext;
    private String email, username, password, age;
    private EditText mEmail, mPassword, mUsername, mAge;
    private RadioGroup rgGender;
    private TextView loadingPleaseWait;
    private Button btnSignUp;
    private ProgressBar mProgressBar;


    //파이어베이스
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    //private FirebaseMethods firebaseMethods;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference myRef;

    private String append="";




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitiy_signup);

        mContext = SignUpActivity.this;
        //firebaseMethods = new FirebaseMethods(mContext);

        Log.d(TAG, "onCreate: started.");

        Button prev = (Button) findViewById(R.id.btn_prev);
        prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
//
//        initWidgets();
//        setupFirebaseAuth();
//        init();
    }

//    private void init(){
//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                email = mEmail.getText().toString();
//                username = mUsername.getText().toString();
//                password = mPassword.getText().toString();
//                age = mAge.getText().toString();
//
//                if(checkInputs(email, password, username, age)){
//                    mProgressBar.setVisibility(View.VISIBLE);
//                    loadingPleaseWait.setVisibility(View.VISIBLE);
//
//                    firebaseMethods.registerNewEmail(email, password, username, age);
//                }
//            }
//        });
//
//    }
//
//    private boolean checkInputs(String email, String password, String username, String age){
//        Log.d(TAG, "checkInputs: checking input for null values.");
//        if (email.equals("") || password.equals("") || username.equals("") || age.equals("")){
//            Toast.makeText(mContext, "모든 정보를 입력해야 합니다.", Toast.LENGTH_SHORT).show();
//            return false;
//        }else{
//            return true;
//        }
//    }
//
//    private void initWidgets(){
//        Log.d(TAG, "initWidgets: Initializing Widgets.");
//        mEmail = (EditText) findViewById(R.id.input_email);
//        mPassword = (EditText) findViewById(R.id.input_password);
//        mUsername = (EditText) findViewById(R.id.input_name);
//
//        //내가 추가한 곳
//        mAge = (EditText) findViewById(R.id.input_age);
//        rgGender = (RadioGroup) findViewById(R.id.rg_gender);
//
//        mProgressBar = (ProgressBar) findViewById(R.id.signupRequestLoadingProgressbar);
//        loadingPleaseWait = (TextView) findViewById(R.id.loadingPleaseWait);
//
//        mContext = SignUpActivity.this;
//        mProgressBar.setVisibility(View.GONE);
//        loadingPleaseWait.setVisibility(View.GONE);
//
//        btnSignUp = (Button) findViewById(R.id.btn_signup);
//
//    }
//
//    private boolean isStringNull(String string) {
//        Log.d(TAG, "isStringNull: checking string if null");
//
//        if (string.equals("")) {
//            return true;
//        } else {
//            return false;
//        }
//    }
//
//    /**
//     *  -------------- 파이어베이스 -----------------------------------------------------
//     */
//
//    /**
//     * checks to see if the @param 'user' is logged in
//     *
//     * @param
//     */
//    private void setupFirebaseAuth() {
//        mAuth = FirebaseAuth.getInstance();
//        mFirebaseDatabase = FirebaseDatabase.getInstance();
//        myRef = mFirebaseDatabase.getReference();
//
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//
//                if (user != null) {
//                    //user id signed in
//                    Log.d(TAG, "onAuthStateChanged: signed_in " + user.getUid());
//
//                    myRef.addListenerForSingleValueEvent(new ValueEventListener() {
//                        @Override
//                        public void onDataChange(DataSnapshot dataSnapshot) {
//                            //성공
//                            // 1st check : make sure the username is nat already in use
//                            if(firebaseMethods.checkIfUsernameExists(username, dataSnapshot)){
//                                append = myRef.push().getKey().substring(3, 10);
//                                Log.d(TAG, "onDataChange: username already exists. Appending Random string to namel : " + append);
//                            }
//                            username = username + append;
//
//                            //add new user to the database
//                            firebaseMethods.addNewUser(email, username, age, "");
//                            Toast.makeText(mContext, "Sign succecssful. Sending verification email", Toast.LENGTH_SHORT).show();
//
//
//                            //add new user to user_account_settig to the database
//
//                        }
//
//                        @Override
//                        public void onCancelled(DatabaseError databaseError) {
//                            //실패
//
//                        }
//                    });
//                } else {
//                    //user is signed out
//                    Log.d(TAG, "onAuthStateChanged: signde_out");
//                }
//            }
//        };
//    }
//
//    @Override
//    public void onStart() {
//        super.onStart();
//        mAuth.addAuthStateListener(mAuthListener);
//    }
//
//    @Override
//    public void onStop() {
//        super.onStop();
//        mAuth.removeAuthStateListener(mAuthListener);
//    }
}
