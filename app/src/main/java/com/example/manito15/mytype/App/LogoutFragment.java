package com.example.manito15.mytype.App;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.manito15.mytype.R;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class LogoutFragment extends Fragment {

    private FirebaseAuth auth;
    View v;

    public LogoutFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        auth = FirebaseAuth.getInstance();
        auth.signOut();
        Intent intent = new Intent(getContext(),LoginActivity.class);
        startActivity(intent);

        return inflater.inflate(R.layout.fragment_logout, container, false);
    }

}
