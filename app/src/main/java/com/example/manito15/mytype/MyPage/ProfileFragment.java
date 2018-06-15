package com.example.manito15.mytype.MyPage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.manito15.mytype.App.LoginActivity;
import com.example.manito15.mytype.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileFragment extends Fragment implements View.OnClickListener{

    private FirebaseAuth auth;
    Activity context;

    View v;

    public ProfileFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = this.getActivity();
        auth = FirebaseAuth.getInstance();
        v = inflater.inflate(R.layout.fragment_profile, container, false);
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button profile_btn_logout = (Button) v.findViewById(R.id.profile_btn_logout);
        Button profile_btn_edit = (Button) v.findViewById(R.id.profile_btn_edit);

        profile_btn_logout.setOnClickListener(this);
        profile_btn_edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.profile_btn_logout:
                auth.signOut();
                Intent intent = new Intent(getContext(),LoginActivity.class);
                startActivity(intent);
                //finish();
                break;
            case R.id.profile_btn_edit: //수정하기 버튼
                break;

        }

    }


}